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
package fr.xpdustry.nucleus.mindustry.testing.ui;

import fr.xpdustry.distributor.api.plugin.PluginAware;
import fr.xpdustry.nucleus.mindustry.testing.ui.state.State;
import mindustry.gen.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface Interface extends PluginAware {

    View open(final Player viewer, final State state, final @Nullable View parent);

    default View open(final Player viewer, final State state) {
        return open(viewer, state, null);
    }
}
