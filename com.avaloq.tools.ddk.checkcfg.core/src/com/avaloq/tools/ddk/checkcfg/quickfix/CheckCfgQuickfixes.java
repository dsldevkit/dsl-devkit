/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.checkcfg.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.EcoreUtil2;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;

/**
 * Model transformations backing the Check Configuration quickfixes.
 * <p>
 * These operate purely on the EMF model and carry no UI dependency, so they can be unit-tested without an editor or
 * workbench. The UI quickfix provider is a thin wrapper that invokes them from an {@code ISemanticModification}.
 * </p>
 */
public final class CheckCfgQuickfixes {

  private CheckCfgQuickfixes() {
    // utility class
  }

  /**
   * Removes duplicate language configurations from the {@link CheckConfiguration} containing the given validator, by
   * merging the contents (catalog and parameter configurations) of every later occurrence of the same language into the
   * first occurrence and deleting the duplicates.
   *
   * @param element
   *          a configured language validator flagged as a duplicate; the merge target is derived from its containing
   *          configuration and its language name, so the result is independent of which duplicate occurrence is passed
   */
  public static void mergeDuplicateLanguageConfigurations(final ConfiguredLanguageValidator element) {
    final CheckConfiguration configuration = EcoreUtil2.getContainerOfType(element, CheckConfiguration.class);
    final String languageName = element.getLanguage();
    if (configuration == null || languageName == null) {
      return;
    }
    ConfiguredLanguageValidator first = null;
    final List<ConfiguredLanguageValidator> duplicates = new ArrayList<ConfiguredLanguageValidator>();
    for (final ConfiguredLanguageValidator validator : configuration.getLanguageValidatorConfigurations()) {
      if (languageName.equals(validator.getLanguage())) {
        if (first == null) {
          first = validator;
        } else {
          duplicates.add(validator);
        }
      }
    }
    for (final ConfiguredLanguageValidator duplicate : duplicates) {
      first.getParameterConfigurations().addAll(duplicate.getParameterConfigurations());
      first.getCatalogConfigurations().addAll(duplicate.getCatalogConfigurations());
      configuration.getLanguageValidatorConfigurations().remove(duplicate);
    }
  }

}
