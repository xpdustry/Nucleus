/*
 * Nucleus, the software collection powering Xpdustry.
 * Copyright (C) 2022  Xpdustry
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package fr.xpdustry.nucleus.common.database.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.SslSettings;
import fr.xpdustry.nucleus.api.application.lifecycle.LifecycleListener;
import fr.xpdustry.nucleus.api.database.DatabaseService;
import fr.xpdustry.nucleus.api.database.model.PunishmentManager;
import fr.xpdustry.nucleus.api.database.model.UserManager;
import fr.xpdustry.nucleus.common.configuration.NucleusConfiguration;
import java.util.Collections;
import javax.inject.Inject;
import org.bson.BsonDocument;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

public final class MongoDatabaseService implements DatabaseService, LifecycleListener {

    private final MongoObjectIdentifierGenerator identifierGenerator = new MongoObjectIdentifierGenerator();
    private final MongoClientSettings settings;
    private final String databaseName;
    private @MonotonicNonNull MongoClient client = null;
    private @MonotonicNonNull UserManager userManager = null;
    private @MonotonicNonNull PunishmentManager punishmentManager = null;

    @Inject
    public MongoDatabaseService(final NucleusConfiguration configuration) {
        this.settings = MongoClientSettings.builder()
                .applicationName("Nucleus")
                .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(
                        new ServerAddress(configuration.getMongoHost(), configuration.getMongoPort()))))
                .credential(MongoCredential.createCredential(
                        configuration.getMongoUsername(),
                        configuration.getMongoAuthDatabase(),
                        configuration.getMongoPassword().toCharArray()))
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .strict(true)
                        .deprecationErrors(true)
                        .build())
                .applyToSslSettings(builder -> builder.applySettings(SslSettings.builder()
                        .enabled(configuration.isMongoConnectionSsl())
                        .build()))
                .build();
        this.databaseName = configuration.getMongoDatabase();
    }

    @Override
    public void onLifecycleInit() {
        this.client = MongoClients.create(this.settings);
        final var database = this.client.getDatabase(this.databaseName);
        this.userManager = new MongoUserManager(database.getCollection("users", BsonDocument.class));
        this.punishmentManager =
                new MongoPunishmentManager(database.getCollection("punishments", BsonDocument.class), this.userManager);
    }

    @Override
    public void onLifecycleExit() {
        this.client.close();
    }

    @Override
    public MongoObjectIdentifierGenerator getObjectIdentifierGenerator() {
        return identifierGenerator;
    }

    @Override
    public UserManager getUserManager() {
        return this.userManager;
    }

    @Override
    public PunishmentManager getPunishmentManager() {
        return this.punishmentManager;
    }
}
