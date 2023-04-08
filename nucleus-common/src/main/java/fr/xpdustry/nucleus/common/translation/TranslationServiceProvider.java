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
package fr.xpdustry.nucleus.common.translation;

import fr.xpdustry.nucleus.api.application.NucleusRuntime;
import fr.xpdustry.nucleus.api.translation.NoopTranslationService;
import fr.xpdustry.nucleus.api.translation.TranslationService;
import fr.xpdustry.nucleus.common.configuration.NucleusConfiguration;
import javax.inject.Inject;
import javax.inject.Provider;

public final class TranslationServiceProvider implements Provider<TranslationService> {

    private final NucleusConfiguration configuration;
    private final NucleusRuntime runtime;

    @Inject
    public TranslationServiceProvider(final NucleusConfiguration configuration, final NucleusRuntime runtime) {
        this.configuration = configuration;
        this.runtime = runtime;
    }

    @Override
    public TranslationService get() {
        return this.configuration.getDeeplTranslationToken().isBlank()
                ? new NoopTranslationService()
                : new DeeplTranslationService(this.configuration, this.runtime);
    }
}
