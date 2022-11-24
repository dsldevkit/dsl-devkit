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
package com.avaloq.tools.ddk.xtext.format.scoping;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.GroupBlock;
import com.avaloq.tools.ddk.xtext.test.format.util.FormatTestUtil;
import com.avaloq.tools.ddk.xtext.test.scoping.AbstractScopingTest;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Scoping tests for the Format DSL.
 */
public class FormatScopingTest extends AbstractScopingTest {

  private static final String C_FORMAT = "C.format";
  private static final String C_XTEXT = "C.xtext";
  /** Xtext grammar extending {@link #grammarB}. */
  private Grammar grammarC;
  /** Format for {@link #grammarC}. */
  private FormatConfiguration formatC;

  private static final String B_XTEXT = "B.xtext";
  /** Xtext grammar extending {@link #grammarA}. */
  private Grammar grammarB;

  private static final String A_XTEXT = "A.xtext";
  private static final String A_FORMAT = "A.format";
  /** Xtext grammar extending terminals. */
  private Grammar grammarA;
  /** Format for {@link #grammarA}. */
  private FormatConfiguration formatA;
  private static final Function<EObject, URI> TO_URI = new Function<EObject, URI>() {
    @Override
    public URI apply(final EObject from) {
      return EcoreUtil.getURI(from);
    }
  };

  /** {@inheritDoc} */
  @Override
  protected FormatTestUtil getXtextTestUtil() {
    return FormatTestUtil.getInstance();
  }

  /** {@inheritDoc} */
  @Override
  protected List<String> getRequiredSourceFileNames() {
    return Lists.newArrayList(A_XTEXT, A_FORMAT, B_XTEXT, C_XTEXT, C_FORMAT);
  }

  @Override
  protected String getTestSourceFileName() {
    return "C";
  }

  /**
   * Creates all grammars and format. {@inheritDoc}
   */
  @Override
  protected void beforeEachTest() {
    super.beforeEachTest();
    grammarA = (Grammar) getTestSource(A_XTEXT).getModel();
    grammarB = (Grammar) getTestSource(B_XTEXT).getModel();
    grammarC = (Grammar) getTestSource(C_XTEXT).getModel();
    formatA = (FormatConfiguration) getTestSource(A_FORMAT).getModel();
    formatC = (FormatConfiguration) getTestSource(C_FORMAT).getModel();
  }

  /**
   * Bug AIG-718.
   * Tests that all grammars' rules are scoped.
   */
  @Test
  public void allGrammarsScoped() {
    Set<URI> expectedURIs = Sets.newHashSet(EcoreUtil.getURI(grammarC.getRules().get(0)), EcoreUtil.getURI(grammarB.getRules().get(0)), EcoreUtil.getURI(grammarA.getRules().get(0)), EcoreUtil.getURI(grammarA.getRules().get(1)));
    assertScope(formatC, FormatPackage.Literals.GRAMMAR_RULE__TARGET_RULE, expectedURIs);
  }

  @Test
  public void keywordScoped() {
    AbstractRule parserRuleA = grammarA.getRules().get(0);
    Set<URI> keywordURIs = Sets.newHashSet(Iterables.transform(GrammarUtil.containedKeywords(parserRuleA), TO_URI));
    assertFalse("No keywords found", keywordURIs.isEmpty());
    assertScope(formatC.getRules().get(0), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__KEYWORD, keywordURIs);
    AbstractRule parserRuleC = grammarC.getRules().get(0);
    assertScope(formatA.getRules().get(0), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__KEYWORD, keywordURIs);

    keywordURIs = Sets.newHashSet(Iterables.transform(GrammarUtil.containedKeywords(parserRuleC), TO_URI));
    assertScope(formatC.getRules().get(2), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__KEYWORD, keywordURIs);
  }

  /**
   * Verify assignemnts (=a, =b ...) are scoped.
   */
  @Test
  public void assignmentScoped() {
    AbstractRule parserRuleA = grammarA.getRules().get(0);
    Set<URI> assignmentURIs = Sets.newHashSet(Iterables.transform(GrammarUtil.containedAssignments(parserRuleA), TO_URI));
    assertFalse("No assignments found", assignmentURIs.isEmpty());
    assertScope(formatC.getRules().get(0), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT, assignmentURIs);
    assertScope(formatA.getRules().get(0), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT, assignmentURIs);
  }

  /**
   * Verify Rule { @Rule : ...}.
   */
  @Test
  public void ruleCallScoped() {
    AbstractRule parserRuleAA = grammarA.getRules().get(1);
    Set<URI> ruleCallURIs = Sets.newHashSet(Iterables.transform(GrammarUtil.containedRuleCalls(parserRuleAA), TO_URI));
    assertScope(formatA.getRules().get(1), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL, ruleCallURIs);
    assertScope(formatC.getRules().get(1), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL, ruleCallURIs);
  }

  /**
   * Verify Rule { rule : ...}.
   */
  @Test
  public void ruleSelfScoped() {
    AbstractRule parserRuleA = grammarA.getRules().get(0);
    assertScope(formatC.getRules().get(0), FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__SELF, TO_URI.apply(parserRuleA));
  }

  @Test
  public void groupScoped() {
    ParserRule parserRuleA = (ParserRule) grammarA.getRules().get(0);

    CompoundElement group1 = findSemanticElementByString("(", CompoundElement.class, parserRuleA); // ('-' b=STRING | ('-'c=ID ('-'d=INT | '-'e=STRING)))?
    GroupBlock groupRef1 = findSemanticElementByString("/*@G1*/", GroupBlock.class, formatA);
    assertScope(groupRef1, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group1));
    CompoundElement group11 = findSemanticElementByString("b", CompoundElement.class, group1);// '-' b=STRING
    GroupBlock groupRef12 = findSemanticElementByString("/*@G12*/", GroupBlock.class, formatA);
    CompoundElement group12 = findSemanticElementByString("(", CompoundElement.class, group1);// ('-'c=ID ('-'d=INT | '-'e=STRING))
    assertScope(groupRef12, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group11), TO_URI.apply(group12));
    CompoundElement group121 = findSemanticElementByString("(", CompoundElement.class, group12);// ('-'d=INT | '-'e=STRING)
    GroupBlock groupRef121 = findSemanticElementByString("/*@G121*/", GroupBlock.class, formatA);
    assertScope(groupRef121, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group121));
    CompoundElement group1211 = findSemanticElementByString("d", CompoundElement.class, group121);// '-'d=INT
    CompoundElement group1212 = findSemanticElementByString("e", CompoundElement.class, group121);// '-'e=STRING
    GroupBlock groupRef1212 = findSemanticElementByString("/*@G1212*/", GroupBlock.class, formatA);
    assertScope(groupRef1212, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group1211), TO_URI.apply(group1212));

    groupRef1 = findSemanticElementByString("/*@G1*/", GroupBlock.class, formatC);
    assertScope(groupRef1, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group1));
    groupRef12 = findSemanticElementByString("/*@G12*/", GroupBlock.class, formatC);
    assertScope(groupRef12, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group11), TO_URI.apply(group12));
    groupRef121 = findSemanticElementByString("/*@G121*/", GroupBlock.class, formatC);
    assertScope(groupRef121, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group121));
    groupRef1212 = findSemanticElementByString("/*@G1212*/", GroupBlock.class, formatC);
    assertScope(groupRef1212, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT, TO_URI.apply(group1211), TO_URI.apply(group1212));

  }

  /**
   * Find semantic element by searching for given string. The returned element is the nearest semantic element, at the found text location, that is of the given
   * type.
   *
   * @param <T>
   *          the type of the element
   * @param string
   *          a string contained by the semantic element
   * @param elementType
   *          the element type
   * @param context
   *          the context
   * @return the element of type T or null
   */
  private <T extends EObject> T findSemanticElementByString(final String string, final Class<T> elementType, final EObject context) {
    ILeafNode leafNode = findLeafNode(string, NodeModelUtils.getNode(context));
    if (leafNode != null) {
      EObject nearestSemanticElement = NodeModelUtils.findActualSemanticObjectFor(leafNode);
      return nearestSemanticElement == null ? null : EcoreUtil2.getContainerOfType(nearestSemanticElement, elementType);
    }
    return null;
  }

  /**
   * Find first leaf node exactly matching given string.
   *
   * @param string
   *          the string
   * @param node
   *          the node
   * @return the leaf node or null
   */
  private ILeafNode findLeafNode(final String string, final ICompositeNode node) {
    for (ILeafNode leafNode : node.getLeafNodes()) {
      if (string.equals(leafNode.getText())) {
        return leafNode;
      }
    }
    return null;
  }
}
