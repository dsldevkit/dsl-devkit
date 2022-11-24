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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.util.Modules2;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;


/**
 * Tests for {@code AbstractSelectorFragmentProvider}.
 */
// CHECKSTYLE:OFF
@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
public class AbstractSelectorFragmentProviderTest extends AbstractXtextTests {
  // CHECKSTYLE:ON

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    with(Modules2.mixin(new XtextRuntimeModule(), new AbstractModule() {
      @Override
      protected void configure() {
        bind(IFragmentProvider.class).to(TestSelectorFragmentProvider.class);
      }
    }));
  }

  @Test
  public void testTopLevelObject() throws Exception {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0", grammar);
  }

  @Test
  public void testMultiValuedContainment() throws Exception {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0", grammar.getRules().get(0));
  }

  @Test
  public void testSingleValuedContainment() throws Exception {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo'+;");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2(3='foo')", grammar.getRules().get(0).getAlternatives());
  }

  @Test
  public void testNullSelectorValue() throws Exception {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'selectCardinality';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2(0=null)", grammar.getRules().get(0).getAlternatives());

    grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo'|'bar'*;");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2/3(3='bar')#0", ((Alternatives) grammar.getRules().get(0).getAlternatives()).getElements().get(1));
  }

  @Test
  public void testEscapedSelectorValue() throws Exception {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo.bar#';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2(3='foo.bar#')", grammar.getRules().get(0).getAlternatives());
  }

  private void assertFragmentMatchesAndResolves(final Resource res, final String expectedFragment, final EObject obj) {
    String fragment = res.getURIFragment(obj);
    assertEquals(expectedFragment, fragment);
    assertSame(obj, res.getEObject(fragment));
  }

  private static class TestSelectorFragmentProvider extends AbstractSelectorFragmentProvider {

    @Override
    public boolean appendFragmentSegment(final EObject object, final StringBuilder builder) {
      EClass eClass = object.eClass();
      EPackage ePackage = eClass.getEPackage();
      if (ePackage == XtextPackage.eINSTANCE) {
        int classifierID = eClass.getClassifierID();
        switch (classifierID) {
        case XtextPackage.GRAMMAR:
          return appendFragmentSegment((Grammar) object, builder);
        case XtextPackage.ENUM_RULE:
        case XtextPackage.PARSER_RULE:
        case XtextPackage.TERMINAL_RULE:
          return appendFragmentSegment((AbstractRule) object, builder);
        case XtextPackage.KEYWORD:
          if (((Keyword) object).getValue().equals("selectCardinality")) {
            return appendFragmentSegment((AbstractElement) object, builder);
          } else {
            return appendFragmentSegment((Keyword) object, builder);
          }
        default:
          return super.appendFragmentSegment(object, builder);
        }
      }
      return super.appendFragmentSegment(object, builder);
    }

    protected boolean appendFragmentSegment(final Grammar obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.GRAMMAR__NAME, true, builder);
    }

    protected boolean appendFragmentSegment(final AbstractRule obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.ABSTRACT_RULE__NAME, false, builder);
    }

    protected boolean appendFragmentSegment(final AbstractElement obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.ABSTRACT_ELEMENT__CARDINALITY, false, builder);
    }

    protected boolean appendFragmentSegment(final Keyword obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.KEYWORD__VALUE, false, builder);
    }

  }
}
