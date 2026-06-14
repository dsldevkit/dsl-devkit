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
   * appending the catalog and parameter configurations of every later occurrence of the same language onto the first
   * occurrence in their original order, then deleting the now-redundant later blocks.
   * <p>
   * The merge is a deliberate <em>order-preserving concatenation</em>: it relocates entries but never deduplicates,
   * reorders, or otherwise rewrites them. This keeps it behaviour-preserving with respect to generation: the properties
   * generator applies configurations sequentially with later definitions overriding earlier ones, so moving all entries
   * into a single block in their original order yields exactly the same generated output as the original duplicate
   * blocks did. Deduplicating or recursively merging the moved entries was tried and deliberately reverted, because any
   * scheme that removes the resulting language-scoped duplicates also drops catalog- and check-level parameters that the
   * generator would otherwise have emitted, i.e. it trades a cosmetic duplicate for real data loss.
   * </p>
   * <p>
   * As a consequence the result may still contain entries that are duplicate at language scope (for example the same
   * catalog or parameter configured under two of the merged blocks). Language-scoped catalog and parameter uniqueness is
   * not validated anywhere today; that is a pre-existing gap, independent of and out of scope for this fix, which
   * implements only what the originating issue asks for (duplicate-<em>language</em> detection plus a merge quick-fix).
   * Such residual duplicates are benign at generation time, since the generator resolves them with the same last-wins
   * semantics, and are left for a separate change that adds the missing language-scoped validation.
   * </p>
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
