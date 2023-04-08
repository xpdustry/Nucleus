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

import com.mongodb.client.MongoCollection;
import fr.xpdustry.nucleus.api.database.Identifier;
import fr.xpdustry.nucleus.api.database.model.Punishment;
import fr.xpdustry.nucleus.api.database.model.PunishmentManager;
import fr.xpdustry.nucleus.api.database.model.UserManager;
import org.bson.BsonDocument;

public final class MongoPunishmentManager extends MongoEntityManager<Punishment, Identifier>
        implements PunishmentManager {

    public MongoPunishmentManager(final MongoCollection<BsonDocument> collection, final UserManager manager) {
        super(collection, new MongoPunishmentCodec(manager));
    }

    public static final class MongoPunishmentCodec implements MongoEntityCodec<Punishment> {

        private final UserManager manager;

        public MongoPunishmentCodec(final UserManager manager) {
            this.manager = manager;
        }

        @Override
        public BsonDocument encode(final Punishment entity) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Punishment decode(final BsonDocument entity) {
            throw new UnsupportedOperationException();
        }
    }
}
