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
package fr.xpdustry.nucleus.mindustry.service;

import fr.xpdustry.distributor.api.DistributorProvider;
import fr.xpdustry.distributor.api.plugin.PluginListener;
import fr.xpdustry.nucleus.mindustry.NucleusPlugin;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import mindustry.net.Administration;

public final class ConventionService implements PluginListener {

    private final NucleusPlugin plugin;

    public ConventionService(final NucleusPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginInit() {
        Administration.Config.serverName.set("[cyan]<[white] Xpdustry [cyan]\uF821[white] "
                + plugin.getConfiguration().getServerDisplayName() + " [cyan]>[white]");

        Administration.Config.motd.set(
                "[cyan]>>>[] Bienvenue sur [cyan]Xpdustry[], le seul serveur mindustry français. N'hésitez pas à nous rejoindre sur Discord avec la commande [cyan]/discord[].");

        final var random = new Random();
        DistributorProvider.get()
                .getPluginScheduler()
                .scheduleAsync(plugin)
                .repeat(1L, TimeUnit.MINUTES)
                .execute(() -> {
                    final var quote = this.plugin
                            .getConfiguration()
                            .getQuotes()
                            .get(random.nextInt(
                                    this.plugin.getConfiguration().getQuotes().size()));
                    Administration.Config.desc.set("\"" + quote + "\" [white]https://discord.xpdustry.fr");
                });
    }
}
