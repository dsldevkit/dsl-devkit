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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;
import com.google.inject.Inject;

/**
 * Tests the UI-independent model transformation behind the duplicate-language-configuration quickfix
 * ({@link CheckCfgQuickfixes#mergeDuplicateLanguageConfigurations(ConfiguredLanguageValidator)}).
 */
@InjectWith(CheckCfgUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckCfgQuickfixesTest {

  private static final String LANG = "com.avaloq.tools.ddk.^check.TestLanguage";
  private static final String OTHER_LANG = "org.example.OtherLanguage";
  private static final String DUPLICATES_REMOVED = "duplicates removed";

  @Inject
  private ParseHelper<CheckConfiguration> parser;

  /** Builds a {@code for <language> { <body> }} validator block. */
  private static String forBlock(final String language, final String body) {
    return "  for " + language + " {\n    " + body + "\n  }\n";
  }

  /** Assembles the given validator blocks into a single check configuration source. */
  private static String source(final String... blocks) {
    return "check configuration Test\n" + String.join("", blocks);
  }

  private static List<ConfiguredLanguageValidator> validators(final CheckConfiguration model) {
    return model.getLanguageValidatorConfigurations();
  }

  /** Two occurrences of the same language, each with a distinct catalog, merge into one validator holding both catalogs. */
  @Test
  public void testMergesDistinctCatalogs() throws Exception {
    final CheckConfiguration model = parser.parse(source(
        forBlock(LANG, "catalog a.CatA { default CheckA }"),
        forBlock(LANG, "catalog b.CatB { default CheckB }")));
    CheckCfgQuickfixes.mergeDuplicateLanguageConfigurations(validators(model).get(0));
    assertEquals(1, validators(model).size(), DUPLICATES_REMOVED);
    assertEquals(2, validators(model).get(0).getCatalogConfigurations().size(), "both catalogs merged into the survivor");
  }

  /** The inherited parameter-configuration list is merged too, not just catalogs. */
  @Test
  public void testMergesParameterConfigurations() throws Exception {
    final CheckConfiguration model = parser.parse(source(
        forBlock(LANG, "integrationRelevant = true"),
        forBlock(LANG, "nameOverrides = #['x']")));
    CheckCfgQuickfixes.mergeDuplicateLanguageConfigurations(validators(model).get(0));
    assertEquals(1, validators(model).size(), DUPLICATES_REMOVED);
    assertEquals(2, validators(model).get(0).getParameterConfigurations().size(), "both parameters merged into the survivor");
  }

  /** More than two occurrences all collapse into the first. */
  @Test
  public void testMergesThreeOccurrences() throws Exception {
    final CheckConfiguration model = parser.parse(source(
        forBlock(LANG, "catalog a.CatA { default CheckA }"),
        forBlock(LANG, "catalog b.CatB { default CheckB }"),
        forBlock(LANG, "catalog c.CatC { default CheckC }")));
    CheckCfgQuickfixes.mergeDuplicateLanguageConfigurations(validators(model).get(0));
    assertEquals(1, validators(model).size(), "all duplicates removed");
    assertEquals(3, validators(model).get(0).getCatalogConfigurations().size(), "all three catalogs merged into the survivor");
  }

  /** Only the targeted language is merged; an unrelated duplicated language is left untouched. */
  @Test
  public void testOnlyTargetLanguageMerged() throws Exception {
    final CheckConfiguration model = parser.parse(source(
        forBlock(LANG, "catalog a.CatA { default CheckA }"),
        forBlock(OTHER_LANG, "catalog b.CatB { default CheckB }"),
        forBlock(LANG, "catalog c.CatC { default CheckC }"),
        forBlock(OTHER_LANG, "catalog d.CatD { default CheckD }")));
    CheckCfgQuickfixes.mergeDuplicateLanguageConfigurations(validators(model).get(0));
    assertEquals(3, validators(model).size(), "only the two occurrences of the target language collapse to one");
    assertEquals(2, validators(model).stream().filter(v -> OTHER_LANG.equals(v.getLanguage())).count(), "the other duplicated language is untouched");
  }

  /** The result is independent of which duplicate occurrence the fix is invoked from. */
  @Test
  public void testIndependentOfInvokedOccurrence() throws Exception {
    final CheckConfiguration fromFirst = parser.parse(source(
        forBlock(LANG, "catalog a.CatA { default CheckA }"),
        forBlock(LANG, "catalog b.CatB { default CheckB }")));
    CheckCfgQuickfixes.mergeDuplicateLanguageConfigurations(validators(fromFirst).get(0));

    final CheckConfiguration fromLast = parser.parse(source(
        forBlock(LANG, "catalog a.CatA { default CheckA }"),
        forBlock(LANG, "catalog b.CatB { default CheckB }")));
    CheckCfgQuickfixes.mergeDuplicateLanguageConfigurations(validators(fromLast).get(1));

    assertEquals(1, validators(fromFirst).size(), "invoking from the first occurrence merges");
    assertEquals(1, validators(fromLast).size(), "invoking from the last occurrence merges identically");
    assertEquals(validators(fromFirst).get(0).getCatalogConfigurations().size(),
        validators(fromLast).get(0).getCatalogConfigurations().size(), "same merged content either way");
  }

  /** An empty duplicate is simply removed; no crash, survivor unchanged. */
  @Test
  public void testEmptyDuplicateRemoved() throws Exception {
    final CheckConfiguration model = parser.parse(source(
        forBlock(LANG, "catalog a.CatA { default CheckA }"),
        forBlock(LANG, "")));
    CheckCfgQuickfixes.mergeDuplicateLanguageConfigurations(validators(model).get(0));
    assertEquals(1, validators(model).size(), "empty duplicate removed");
    assertEquals(1, validators(model).get(0).getCatalogConfigurations().size(), "survivor's catalog preserved");
  }

}
