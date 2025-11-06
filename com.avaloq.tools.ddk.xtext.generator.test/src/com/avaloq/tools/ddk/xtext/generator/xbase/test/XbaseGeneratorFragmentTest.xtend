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

package com.avaloq.tools.ddk.xtext.generator.xbase.test

import com.avaloq.tools.ddk.test.core.BugTest
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractMetamodelDeclaration
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.Group
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.TypeRef
import org.eclipse.xtext.XtextPackage

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import org.eclipse.xtext.xtext.generator.xbase.XbaseUsageDetector
import org.junit.jupiter.api.^extension.ExtendWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.^extension.RegisterExtension
import com.avaloq.tools.ddk.test.core.jupiter.BugTestAwareRule

/**
 * Tests for {@link XbaseUsageDetector}.
 */
@ExtendWith(InjectionExtension)
class XbaseGeneratorFragmentTest {
  
  @RegisterExtension
  public BugTestAwareRule bugTestRule = BugTestAwareRule.getInstance();

  val thisPackageName = "thisPackage"
  val xtypePackageName = "xtype"
  val xImportSectionRuleName = "XImportSection"

  val detector = new XbaseUsageDetector

  /**
   * Set expectations prior to calling usesXImportSection.apply().
   *
   * @param mockRule    Mock Rule, in which to set expectations.
   * @param packageName Package name to use.
   * @param ruleName    Rule name to use.
   */
  private def setExpectationsForApply(ParserRule mockRule, String packageName, String ruleName) {
    val mockType = mock(TypeRef)
    val mockMetamodel = mock(AbstractMetamodelDeclaration)
    val mockEPackage = mock(EPackage)

    when(mockRule.name).thenReturn(ruleName)

    when(mockRule.type).thenReturn(mockType)
    when(mockType.metamodel).thenReturn(mockMetamodel)
    when(mockMetamodel.EPackage).thenReturn(mockEPackage)
    when(mockEPackage.name).thenReturn(packageName)
  }

  /**
   * Set expectations prior to calling usesXImportSection().
   *
   * @param mockGrammar                     Mock Grammar, in which to set expectations.
   * @param packageAndRuleNamesOfLeafRules  Package and rule names to use for leaf rules.
   */
  private def setExpectationsForUsesXImportSection(Grammar mockGrammar, Pair<String, String>... packageAndRuleNamesOfLeafRules) {
    val mockRootRule = mock(ParserRule)
    val mockAlternatives = mock(Group)
    val mockElements = new BasicEList<AbstractElement>

    val mockLeafRules = new BasicEList<ParserRule>
    packageAndRuleNamesOfLeafRules.forEach[mockLeafRules.add(mock(ParserRule))]

    val mockRules = new BasicEList<AbstractRule>()
    mockRules.add(mockRootRule)
    mockRules.addAll(mockLeafRules)

    // Calls made by UsedRulesFinder.compute()
    when(mockGrammar.rules).thenReturn(mockRules);

    // Calls made by doSwitch(firstRule)
    when(mockRootRule.eClass).thenReturn(XtextPackage.Literals.PARSER_RULE)
    when(mockRootRule.alternatives).thenReturn(mockAlternatives)

    when(mockAlternatives.eClass).thenReturn(XtextPackage.Literals.GROUP)
    when(mockAlternatives.elements).thenReturn(mockElements)

    // Calls made per leaf rule by doSwitch(firstRule)
    mockLeafRules.forEach[mockLeafRule |
      val mockAssignment = mock(Assignment)
      val mockTerminal = mock(RuleCall)
      mockElements.add(mockAssignment)

      when(mockAssignment.eClass).thenReturn(XtextPackage.Literals.ASSIGNMENT)
      when(mockAssignment.terminal).thenReturn(mockTerminal)

      when(mockTerminal.eClass).thenReturn(XtextPackage.Literals.RULE_CALL)
      when(mockTerminal.rule).thenReturn(mockLeafRule)

      when(mockLeafRule.eClass).thenReturn(XtextPackage.Literals.PARSER_RULE)
      when(mockLeafRule.alternatives).thenReturn(null)
      when(mockLeafRule.definesHiddenTokens).thenReturn(false)
    ]

    // Calls made by doSwitch(grammar)
    when(mockGrammar.eClass).thenReturn(XtextPackage.Literals.GRAMMAR)
    when(mockGrammar.definesHiddenTokens).thenReturn(false)
    when(mockGrammar.usedGrammars).thenReturn(new BasicEList<Grammar>)

    // Calls made per rule by XbaseGeneratorFragmentOverride.usesXImportSection.apply()
    mockRootRule.setExpectationsForApply(thisPackageName, "rootRule")

    val mockLeafRuleIterator = mockLeafRules.iterator
    val packageAndRuleNameIterator = packageAndRuleNamesOfLeafRules.iterator
    while (mockLeafRuleIterator.hasNext) {
      val mockLeafRule = mockLeafRuleIterator.next
      val packageandRuleName = packageAndRuleNameIterator.next

      val packageName = packageandRuleName.key
      val ruleName = packageandRuleName.value

      mockLeafRule.setExpectationsForApply(packageName, ruleName)
    }
  }

  /**
   * Test usesXImportSection() when passed a null XtextResource
   */
  @Test
  def void testUsesXImportSectionWithNullGrammar() {
    assertThrows(NullPointerException, [|detector.usesXImportSection(null)]);
  }

  /**
   * Test usesXImportSection() when the grammar does not use xtype::XImportSection().
   */
  @Test
  @BugTest(value = "DSL-244")
  def void testUsesXImportSectionWhenNotUsed() {
    // ARRANGE
    val mockGrammar = mock(Grammar)

    // Use a selection of rules which do not include xtype::XImportSection
    mockGrammar.setExpectationsForUsesXImportSection(
      null -> "leafRule1",
      thisPackageName -> "leafRule2",
      xtypePackageName -> "leafRule3",
      null -> xImportSectionRuleName,
      thisPackageName -> xImportSectionRuleName
    )

    // ACT
    val usesXImportSection = detector.usesXImportSection(mockGrammar)

    // ASSERT
    assertFalse(usesXImportSection, "usesXImportSection() should return false when the grammar does not use XImportSection")
  }

  /**
   * Test usesXImportSection() when the grammar uses xtype::XImportSection().
   */
  @Test
  @BugTest(value = "DSL-244")
  def void testUsesXImportSectionWhenUsed() {
    // ARRANGE
    val mockGrammar = mock(Grammar)

    // Use a selection of rules including xtype::XImportSection
    mockGrammar.setExpectationsForUsesXImportSection(
      null -> "leafRule1",
      thisPackageName -> "leafRule2",
      xtypePackageName -> "leafRule3",
      null -> xImportSectionRuleName,
      thisPackageName -> xImportSectionRuleName,
      xtypePackageName -> xImportSectionRuleName
    )

    // ACT
    val usesXImportSection = detector.usesXImportSection(mockGrammar)

    // ASSERT
    assertTrue(usesXImportSection, "usesXImportSection() should return true when the grammar uses XImportSection")
  }

}
