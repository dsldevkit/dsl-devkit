/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.xbase.test;

import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.xtext.generator.xbase.FixedXbaseGeneratorFragment;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import junit.framework.TestCase;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
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
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

/**
 * Tests for {@link FixedXbaseGeneratorFragment}.
 */
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class FixedXbaseGeneratorFragmentTest extends TestCase {
  /**
   * Class to access protected members of {@link XbaseGeneratorFragmentOverride}.
   */
  private static class FragmentAccessor extends FixedXbaseGeneratorFragment {
    @Override
    public String getTemplate() {
      return super.getTemplate();
    }
  }
  
  private final String thisPackageName = "thisPackage";
  
  private final String xtypePackageName = "xtype";
  
  private final String xImportSectionRuleName = "XImportSection";
  
  private final FixedXbaseGeneratorFragment fragment = new FixedXbaseGeneratorFragment();
  
  /**
   * Set expectations prior to calling usesXImportSection.apply().
   * 
   * @param mockRule    Mock Rule, in which to set expectations.
   * @param packageName Package name to use.
   * @param ruleName    Rule name to use.
   */
  private OngoingStubbing<String> setExpectationsForApply(final ParserRule mockRule, final String packageName, final String ruleName) {
    OngoingStubbing<String> _xblockexpression = null;
    {
      final TypeRef mockType = Mockito.<TypeRef>mock(TypeRef.class);
      final AbstractMetamodelDeclaration mockMetamodel = Mockito.<AbstractMetamodelDeclaration>mock(AbstractMetamodelDeclaration.class);
      final EPackage mockEPackage = Mockito.<EPackage>mock(EPackage.class);
      String _name = mockRule.getName();
      OngoingStubbing<String> _when = Mockito.<String>when(_name);
      _when.thenReturn(ruleName);
      TypeRef _type = mockRule.getType();
      OngoingStubbing<TypeRef> _when_1 = Mockito.<TypeRef>when(_type);
      _when_1.thenReturn(mockType);
      AbstractMetamodelDeclaration _metamodel = mockType.getMetamodel();
      OngoingStubbing<AbstractMetamodelDeclaration> _when_2 = Mockito.<AbstractMetamodelDeclaration>when(_metamodel);
      _when_2.thenReturn(mockMetamodel);
      EPackage _ePackage = mockMetamodel.getEPackage();
      OngoingStubbing<EPackage> _when_3 = Mockito.<EPackage>when(_ePackage);
      _when_3.thenReturn(mockEPackage);
      String _name_1 = mockEPackage.getName();
      OngoingStubbing<String> _when_4 = Mockito.<String>when(_name_1);
      _xblockexpression = _when_4.thenReturn(packageName);
    }
    return _xblockexpression;
  }
  
  /**
   * Set expectations prior to calling usesXImportSection().
   * 
   * @param mockGrammar                     Mock Grammar, in which to set expectations.
   * @param packageAndRuleNamesOfLeafRules  Package and rule names to use for leaf rules.
   */
  private void setExpectationsForUsesXImportSection(final Grammar mockGrammar, final Pair<String, String>... packageAndRuleNamesOfLeafRules) {
    final ParserRule mockRootRule = Mockito.<ParserRule>mock(ParserRule.class);
    final Group mockAlternatives = Mockito.<Group>mock(Group.class);
    final BasicEList<AbstractElement> mockElements = new BasicEList<AbstractElement>();
    final BasicEList<ParserRule> mockLeafRules = new BasicEList<ParserRule>();
    final Consumer<Pair<String, String>> _function = (Pair<String, String> it) -> {
      ParserRule _mock = Mockito.<ParserRule>mock(ParserRule.class);
      mockLeafRules.add(_mock);
    };
    ((List<Pair<String, String>>)Conversions.doWrapArray(packageAndRuleNamesOfLeafRules)).forEach(_function);
    final BasicEList<AbstractRule> mockRules = new BasicEList<AbstractRule>();
    mockRules.add(mockRootRule);
    mockRules.addAll(mockLeafRules);
    EList<AbstractRule> _rules = mockGrammar.getRules();
    OngoingStubbing<EList<AbstractRule>> _when = Mockito.<EList<AbstractRule>>when(_rules);
    _when.thenReturn(mockRules);
    EClass _eClass = mockRootRule.eClass();
    OngoingStubbing<EClass> _when_1 = Mockito.<EClass>when(_eClass);
    _when_1.thenReturn(XtextPackage.Literals.PARSER_RULE);
    AbstractElement _alternatives = mockRootRule.getAlternatives();
    OngoingStubbing<AbstractElement> _when_2 = Mockito.<AbstractElement>when(_alternatives);
    _when_2.thenReturn(mockAlternatives);
    EClass _eClass_1 = mockAlternatives.eClass();
    OngoingStubbing<EClass> _when_3 = Mockito.<EClass>when(_eClass_1);
    _when_3.thenReturn(XtextPackage.Literals.GROUP);
    EList<AbstractElement> _elements = mockAlternatives.getElements();
    OngoingStubbing<EList<AbstractElement>> _when_4 = Mockito.<EList<AbstractElement>>when(_elements);
    _when_4.thenReturn(mockElements);
    final Consumer<ParserRule> _function_1 = (ParserRule mockLeafRule) -> {
      final Assignment mockAssignment = Mockito.<Assignment>mock(Assignment.class);
      final RuleCall mockTerminal = Mockito.<RuleCall>mock(RuleCall.class);
      mockElements.add(mockAssignment);
      EClass _eClass_2 = mockAssignment.eClass();
      OngoingStubbing<EClass> _when_5 = Mockito.<EClass>when(_eClass_2);
      _when_5.thenReturn(XtextPackage.Literals.ASSIGNMENT);
      AbstractElement _terminal = mockAssignment.getTerminal();
      OngoingStubbing<AbstractElement> _when_6 = Mockito.<AbstractElement>when(_terminal);
      _when_6.thenReturn(mockTerminal);
      EClass _eClass_3 = mockTerminal.eClass();
      OngoingStubbing<EClass> _when_7 = Mockito.<EClass>when(_eClass_3);
      _when_7.thenReturn(XtextPackage.Literals.RULE_CALL);
      AbstractRule _rule = mockTerminal.getRule();
      OngoingStubbing<AbstractRule> _when_8 = Mockito.<AbstractRule>when(_rule);
      _when_8.thenReturn(mockLeafRule);
      EClass _eClass_4 = mockLeafRule.eClass();
      OngoingStubbing<EClass> _when_9 = Mockito.<EClass>when(_eClass_4);
      _when_9.thenReturn(XtextPackage.Literals.PARSER_RULE);
      AbstractElement _alternatives_1 = mockLeafRule.getAlternatives();
      OngoingStubbing<AbstractElement> _when_10 = Mockito.<AbstractElement>when(_alternatives_1);
      _when_10.thenReturn(null);
      boolean _isDefinesHiddenTokens = mockLeafRule.isDefinesHiddenTokens();
      OngoingStubbing<Boolean> _when_11 = Mockito.<Boolean>when(Boolean.valueOf(_isDefinesHiddenTokens));
      _when_11.thenReturn(Boolean.valueOf(false));
    };
    mockLeafRules.forEach(_function_1);
    EClass _eClass_2 = mockGrammar.eClass();
    OngoingStubbing<EClass> _when_5 = Mockito.<EClass>when(_eClass_2);
    _when_5.thenReturn(XtextPackage.Literals.GRAMMAR);
    boolean _isDefinesHiddenTokens = mockGrammar.isDefinesHiddenTokens();
    OngoingStubbing<Boolean> _when_6 = Mockito.<Boolean>when(Boolean.valueOf(_isDefinesHiddenTokens));
    _when_6.thenReturn(Boolean.valueOf(false));
    EList<Grammar> _usedGrammars = mockGrammar.getUsedGrammars();
    OngoingStubbing<EList<Grammar>> _when_7 = Mockito.<EList<Grammar>>when(_usedGrammars);
    BasicEList<Grammar> _basicEList = new BasicEList<Grammar>();
    _when_7.thenReturn(_basicEList);
    this.setExpectationsForApply(mockRootRule, this.thisPackageName, "rootRule");
    final Iterator<ParserRule> mockLeafRuleIterator = mockLeafRules.iterator();
    final Iterator<Pair<String, String>> packageAndRuleNameIterator = ((List<Pair<String, String>>)Conversions.doWrapArray(packageAndRuleNamesOfLeafRules)).iterator();
    while (mockLeafRuleIterator.hasNext()) {
      {
        final ParserRule mockLeafRule = mockLeafRuleIterator.next();
        final Pair<String, String> packageandRuleName = packageAndRuleNameIterator.next();
        final String packageName = packageandRuleName.getKey();
        final String ruleName = packageandRuleName.getValue();
        this.setExpectationsForApply(mockLeafRule, packageName, ruleName);
      }
    }
  }
  
  /**
   * Test usesXImportSection() when passed a null XtextResource
   */
  @Test(expected = NullPointerException.class)
  public void testUsesXImportSectionWithNullGrammar() {
    this.fragment.usesXImportSection(null);
  }
  
  /**
   * Test usesXImportSection() when the grammar does not use xtype::XImportSection().
   */
  @Test
  @BugTest(value = "DSL-244")
  public void testUsesXImportSectionWhenNotUsed() {
    final Grammar mockGrammar = Mockito.<Grammar>mock(Grammar.class);
    Pair<String, String> _mappedTo = Pair.<String, String>of(null, "leafRule1");
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of(this.thisPackageName, "leafRule2");
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of(this.xtypePackageName, "leafRule3");
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of(null, this.xImportSectionRuleName);
    Pair<String, String> _mappedTo_4 = Pair.<String, String>of(this.thisPackageName, this.xImportSectionRuleName);
    this.setExpectationsForUsesXImportSection(mockGrammar, _mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4);
    final boolean usesXImportSection = this.fragment.usesXImportSection(mockGrammar);
    TestCase.assertFalse("usesXImportSection() should return false when the grammar does not use XImportSection", usesXImportSection);
  }
  
  /**
   * Test usesXImportSection() when the grammar uses xtype::XImportSection().
   */
  @Test
  @BugTest(value = "DSL-244")
  public void testUsesXImportSectionWhenUsed() {
    final Grammar mockGrammar = Mockito.<Grammar>mock(Grammar.class);
    Pair<String, String> _mappedTo = Pair.<String, String>of(null, "leafRule1");
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of(this.thisPackageName, "leafRule2");
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of(this.xtypePackageName, "leafRule3");
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of(null, this.xImportSectionRuleName);
    Pair<String, String> _mappedTo_4 = Pair.<String, String>of(this.thisPackageName, this.xImportSectionRuleName);
    Pair<String, String> _mappedTo_5 = Pair.<String, String>of(this.xtypePackageName, this.xImportSectionRuleName);
    this.setExpectationsForUsesXImportSection(mockGrammar, _mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5);
    final boolean usesXImportSection = this.fragment.usesXImportSection(mockGrammar);
    TestCase.assertTrue("usesXImportSection() should return true when the grammar uses XImportSection", usesXImportSection);
  }
  
  /**
   * Really basic regression test for getTemplate()
   */
  @Test
  public void testGetTemplate() {
    final FixedXbaseGeneratorFragmentTest.FragmentAccessor fragment = new FixedXbaseGeneratorFragmentTest.FragmentAccessor();
    final String template = fragment.getTemplate();
    TestCase.assertEquals("getTemplate() should return the expected string", "org::eclipse::xtext::generator::xbase::XbaseGeneratorFragment", template);
  }
}
