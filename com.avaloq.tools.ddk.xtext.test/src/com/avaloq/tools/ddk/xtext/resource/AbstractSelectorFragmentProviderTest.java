/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
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

import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider;
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
    public CharSequence getFragmentSegment(final EObject object) {
      EClass eClass = object.eClass();
      EPackage ePackage = eClass.getEPackage();
      if (ePackage == XtextPackage.eINSTANCE) {
        int classifierID = eClass.getClassifierID();
        switch (classifierID) {
        case XtextPackage.GRAMMAR:
          return getFragmentSegment((Grammar) object);
        case XtextPackage.ENUM_RULE:
        case XtextPackage.PARSER_RULE:
        case XtextPackage.TERMINAL_RULE:
          return getFragmentSegment((AbstractRule) object);
        case XtextPackage.KEYWORD:
          if (((Keyword) object).getValue().equals("selectCardinality")) {
            return getFragmentSegment((AbstractElement) object);
          } else {
            return getFragmentSegment((Keyword) object);
          }
        default:
          return super.getFragmentSegment(object);
        }
      }
      return super.getFragmentSegment(object);
    }

    protected CharSequence getFragmentSegment(final Grammar obj) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.GRAMMAR__NAME, true);
    }

    protected CharSequence getFragmentSegment(final AbstractRule obj) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.ABSTRACT_RULE__NAME, false);
    }

    protected CharSequence getFragmentSegment(final AbstractElement obj) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.ABSTRACT_ELEMENT__CARDINALITY, false);
    }

    protected CharSequence getFragmentSegment(final Keyword obj) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.KEYWORD__VALUE, false);
    }

  }
}

