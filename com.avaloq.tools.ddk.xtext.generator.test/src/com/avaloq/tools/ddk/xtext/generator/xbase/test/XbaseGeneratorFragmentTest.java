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

package com.avaloq.tools.ddk.xtext.generator.xbase.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;

import com.avaloq.tools.ddk.test.core.jupiter.BugTest;
import com.avaloq.tools.ddk.test.core.jupiter.BugTestAwareRule;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xtext.generator.xbase.XbaseUsageDetector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * Tests for {@link XbaseUsageDetector}.
 */
@ExtendWith(InjectionExtension.class)
public class XbaseGeneratorFragmentTest {

  // CHECKSTYLE:CONSTANTS-OFF
  @RegisterExtension
  private final BugTestAwareRule bugTestRule = BugTestAwareRule.getInstance();

  private static final String THIS_PACKAGE_NAME = "thisPackage";
  private static final String XTYPE_PACKAGE_NAME = "xtype";
  private static final String X_IMPORT_SECTION_RULE_NAME = "XImportSection";

  private final XbaseUsageDetector detector = new XbaseUsageDetector();

  /**
   * Set expectations prior to calling usesXImportSection.apply().
   *
   * @param mockRule    Mock Rule, in which to set expectations.
   * @param packageName Package name to use.
   * @param ruleName    Rule name to use.
   */
  @SuppressWarnings("unchecked")
  private void setExpectationsForApply(final ParserRule mockRule, final String packageName, final String ruleName) {
    final TypeRef mockType = mock(TypeRef.class);
    final AbstractMetamodelDeclaration mockMetamodel = mock(AbstractMetamodelDeclaration.class);
    final EPackage mockEPackage = mock(EPackage.class);

    when(mockRule.getName()).thenReturn(ruleName);

    when(mockRule.getType()).thenReturn(mockType);
    when(mockType.getMetamodel()).thenReturn(mockMetamodel);
    when(mockMetamodel.getEPackage()).thenReturn(mockEPackage);
    when(mockEPackage.getName()).thenReturn(packageName);
  }

  /**
   * Set expectations prior to calling usesXImportSection().
   *
   * @param mockGrammar                     Mock Grammar, in which to set expectations.
   * @param packageAndRuleNamesOfLeafRules  Package and rule names to use for leaf rules.
   */
  @SafeVarargs
  @SuppressWarnings("unchecked")
  private void setExpectationsForUsesXImportSection(final Grammar mockGrammar, final Pair<String, String>... packageAndRuleNamesOfLeafRules) {
    final ParserRule mockRootRule = mock(ParserRule.class);
    final Group mockAlternatives = mock(Group.class);
    final BasicEList<AbstractElement> mockElements = new BasicEList<AbstractElement>();

    final BasicEList<ParserRule> mockLeafRules = new BasicEList<ParserRule>();
    for (final Pair<String, String> pair : packageAndRuleNamesOfLeafRules) {
      mockLeafRules.add(mock(ParserRule.class));
    }

    final BasicEList<AbstractRule> mockRules = new BasicEList<AbstractRule>();
    mockRules.add(mockRootRule);
    mockRules.addAll(mockLeafRules);

    // Calls made by UsedRulesFinder.compute()
    when(mockGrammar.getRules()).thenReturn(mockRules);

    // Calls made by doSwitch(firstRule)
    when(mockRootRule.eClass()).thenReturn(XtextPackage.Literals.PARSER_RULE);
    when(mockRootRule.getAlternatives()).thenReturn(mockAlternatives);

    when(mockAlternatives.eClass()).thenReturn(XtextPackage.Literals.GROUP);
    when(mockAlternatives.getElements()).thenReturn(mockElements);

    // Calls made per leaf rule by doSwitch(firstRule)
    for (final ParserRule mockLeafRule : mockLeafRules) {
      final Assignment mockAssignment = mock(Assignment.class);
      final RuleCall mockTerminal = mock(RuleCall.class);
      mockElements.add(mockAssignment);

      when(mockAssignment.eClass()).thenReturn(XtextPackage.Literals.ASSIGNMENT);
      when(mockAssignment.getTerminal()).thenReturn(mockTerminal);

      when(mockTerminal.eClass()).thenReturn(XtextPackage.Literals.RULE_CALL);
      when(mockTerminal.getRule()).thenReturn(mockLeafRule);

      when(mockLeafRule.eClass()).thenReturn(XtextPackage.Literals.PARSER_RULE);
      when(mockLeafRule.getAlternatives()).thenReturn(null);
      when(mockLeafRule.isDefinesHiddenTokens()).thenReturn(false);
    }

    // Calls made by doSwitch(grammar)
    when(mockGrammar.eClass()).thenReturn(XtextPackage.Literals.GRAMMAR);
    when(mockGrammar.isDefinesHiddenTokens()).thenReturn(false);
    when(mockGrammar.getUsedGrammars()).thenReturn(new BasicEList<Grammar>());

    // Calls made per rule by XbaseGeneratorFragmentOverride.usesXImportSection.apply()
    setExpectationsForApply(mockRootRule, THIS_PACKAGE_NAME, "rootRule");

    Iterator<ParserRule> mockLeafRuleIterator = mockLeafRules.iterator();
    Iterator<Pair<String, String>> packageAndRuleNameIterator = Arrays.asList(packageAndRuleNamesOfLeafRules).iterator();
    while (mockLeafRuleIterator.hasNext()) {
      final ParserRule mockLeafRule = mockLeafRuleIterator.next();
      final Pair<String, String> packageandRuleName = packageAndRuleNameIterator.next();

      final String packageName = packageandRuleName.getKey();
      final String ruleName = packageandRuleName.getValue();

      setExpectationsForApply(mockLeafRule, packageName, ruleName);
    }
  }

  /**
   * Test usesXImportSection() when passed a null XtextResource
   */
  @Test
  public void testUsesXImportSectionWithNullGrammar() {
    assertThrows(NullPointerException.class, () -> detector.usesXImportSection(null));
  }

  /**
   * Test usesXImportSection() when the grammar does not use xtype::XImportSection().
   */
  @Test
  @BugTest(value = "DSL-244")
  public void testUsesXImportSectionWhenNotUsed() {
    // ARRANGE
    final Grammar mockGrammar = mock(Grammar.class);

    // Use a selection of rules which do not include xtype::XImportSection
    setExpectationsForUsesXImportSection(
        mockGrammar,
        Pair.of(null, "leafRule1"),
        Pair.of(THIS_PACKAGE_NAME, "leafRule2"),
        Pair.of(XTYPE_PACKAGE_NAME, "leafRule3"),
        Pair.of(null, X_IMPORT_SECTION_RULE_NAME),
        Pair.of(THIS_PACKAGE_NAME, X_IMPORT_SECTION_RULE_NAME)
    );

    // ACT
    final boolean usesXImportSection = detector.usesXImportSection(mockGrammar);

    // ASSERT
    assertFalse(usesXImportSection, "usesXImportSection() should return false when the grammar does not use XImportSection");
  }

  /**
   * Test usesXImportSection() when the grammar uses xtype::XImportSection().
   */
  @Test
  @BugTest(value = "DSL-244")
  public void testUsesXImportSectionWhenUsed() {
    // ARRANGE
    final Grammar mockGrammar = mock(Grammar.class);

    // Use a selection of rules including xtype::XImportSection
    setExpectationsForUsesXImportSection(
        mockGrammar,
        Pair.of(null, "leafRule1"),
        Pair.of(THIS_PACKAGE_NAME, "leafRule2"),
        Pair.of(XTYPE_PACKAGE_NAME, "leafRule3"),
        Pair.of(null, X_IMPORT_SECTION_RULE_NAME),
        Pair.of(THIS_PACKAGE_NAME, X_IMPORT_SECTION_RULE_NAME),
        Pair.of(XTYPE_PACKAGE_NAME, X_IMPORT_SECTION_RULE_NAME)
    );

    // ACT
    final boolean usesXImportSection = detector.usesXImportSection(mockGrammar);

    // ASSERT
    assertTrue(usesXImportSection, "usesXImportSection() should return true when the grammar uses XImportSection");
  }
  // CHECKSTYLE:CONSTANTS-ON

}
