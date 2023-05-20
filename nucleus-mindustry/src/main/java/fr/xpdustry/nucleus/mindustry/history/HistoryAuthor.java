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
package fr.xpdustry.nucleus.mindustry.history;

import fr.xpdustry.nucleus.common.annotation.ImmutableNucleusStyle;
import java.util.Optional;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import org.immutables.value.Value.Immutable;

@Immutable(copy = false, builder = false)
@ImmutableNucleusStyle
public sealed interface HistoryAuthor permits ImmutableHistoryAuthor {

    static HistoryAuthor of(final Unit unit) {
        return ImmutableHistoryAuthor.of(
                unit.isPlayer() ? Optional.of(unit.getPlayer().uuid()) : Optional.empty(), unit.team(), unit.type());
    }

    Optional<String> getUuid();

    Team getTeam();

    UnitType getUnit();
}
