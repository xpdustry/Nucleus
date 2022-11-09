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
package fr.xpdustry.nucleus.discord;

import java.util.Set;
import org.aeonbits.owner.Config;

@Config.Sources({"classpath:nucleus.properties", "file:./nucleus.properties"})
interface NucleusBotConfig extends Config {

    @Config.Key("fr.xpdustry.nucleus.discord.prefix")
    @Config.DefaultValue("!")
    String getCommandPrefix();

    @Config.Key("fr.xpdustry.nucleus.discord.token")
    @Config.DefaultValue("")
    String getToken();

    @Config.Key("fr.xpdustry.nucleus.discord.javelin.port")
    @Config.DefaultValue("8080")
    int getJavelinServerPort();

    @Config.Key("fr.xpdustry.nucleus.discord.owners")
    @Config.DefaultValue("")
    Set<Long> getBotOwners();

    @Config.Key("fr.xpdustry.nucleus.discord.server-chat-category")
    @Config.DefaultValue("")
    long getServerChatCategory();

    @Config.Key("fr.xpdustry.nucleus.discord.channel.report")
    @Config.DefaultValue("")
    long getReportChannel();

    @Config.Key("fr.xpdustry.nucleus.logging.level")
    @Config.DefaultValue("info")
    String getLogLevel();
}
