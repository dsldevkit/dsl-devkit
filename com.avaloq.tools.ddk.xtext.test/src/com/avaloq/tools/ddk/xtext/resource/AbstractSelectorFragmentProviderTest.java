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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.inject.Inject;


/**
 * Tests for {@code AbstractSelectorFragmentProvider}.
 */
// CHECKSTYLE:OFF
@SuppressWarnings({"PMD.JUnitAssertionsShouldIncludeMessage", "nls"})
@InjectWith(AbstractXtextTestsInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class AbstractSelectorFragmentProviderTest {
  // CHECKSTYLE:ON

  private static final String NAME_FEATURE = "name";
  private static final String CHILDREN_FEATURE = "children";

  @Inject
  private ParseHelper<EObject> parseHelper;

  @Test
  public void testTopLevelObject() {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0", grammar);
  }

  @Test
  public void testMultiValuedContainment() {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0", grammar.getRules().get(0));
  }

  @Test
  public void testSingleValuedContainment() {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo'+;");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2(3='foo')", grammar.getRules().get(0).getAlternatives());
  }

  @Test
  public void testNullSelectorValue() {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'selectCardinality';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2(0=null)", grammar.getRules().get(0).getAlternatives());

    grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo'|'bar'*;");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2/3(3='bar')#0", ((Alternatives) grammar.getRules().get(0).getAlternatives()).getElements().get(1));
  }

  @Test
  public void testEscapedSelectorValue() {
    Grammar grammar = (Grammar) getModel("grammar foo.Foo\n" + "generate foo 'http://www.foo.com/foo'\n" + "Foo: 'foo.bar#';");
    assertFragmentMatchesAndResolves(grammar.eResource(), "/0/5(0='Foo')#0/2(3='foo.bar#')", grammar.getRules().get(0).getAlternatives());
  }

  @Test
  public void testCaseSensitiveSelectorValue() {
    EObject container = createContainer(true, "foo", "FOO");
    for (EObject child : children(container)) {
      assertSame(child, resolve(container, segment(child)));
    }
  }

  @Test
  public void testCaseInsensitiveSelectorValue() {
    EObject container = createContainer(false, "foo");
    EObject child = children(container).get(0);
    assertSame(child, resolve(container, segment(child).replace("'foo'", "'FOO'")));
  }

  @Test
  public void testCaseInsensitiveSiblingsDifferingInCase() {
    EObject container = createContainer(false, "foo", "FOO");
    for (EObject child : children(container)) {
      assertSame(child, resolve(container, segment(child)));
    }
  }

  /**
   * Creates a container whose children are named by the given names, using a dynamic model whose child class is case sensitive if requested.
   */
  private static EObject createContainer(final boolean caseSensitive, final String... names) {
    EcoreFactory factory = EcoreFactory.eINSTANCE;
    EPackage ePackage = factory.createEPackage();
    ePackage.setName("test");
    ePackage.setNsURI("http://www.foo.com/test/" + caseSensitive);
    EClass childClass = factory.createEClass();
    childClass.setName("Child");
    if (caseSensitive) {
      // the provider only checks the name of the super type
      EClass marker = factory.createEClass();
      marker.setName("ICaseSensitiveNamedElement");
      marker.setInterface(true);
      marker.setAbstract(true);
      ePackage.getEClassifiers().add(marker);
      childClass.getESuperTypes().add(marker);
    }
    EAttribute name = factory.createEAttribute();
    name.setName(NAME_FEATURE);
    name.setEType(EcorePackage.Literals.ESTRING);
    childClass.getEStructuralFeatures().add(name);
    EClass containerClass = factory.createEClass();
    containerClass.setName("Container");
    EReference childrenReference = factory.createEReference();
    childrenReference.setName(CHILDREN_FEATURE);
    childrenReference.setEType(childClass);
    childrenReference.setContainment(true);
    childrenReference.setUpperBound(-1);
    containerClass.getEStructuralFeatures().add(childrenReference);
    ePackage.getEClassifiers().add(childClass);
    ePackage.getEClassifiers().add(containerClass);

    EObject container = EcoreUtil.create(containerClass);
    for (String childName : names) {
      EObject child = EcoreUtil.create(childClass);
      child.eSet(name, childName);
      children(container).add(child);
    }
    return container;
  }

  @SuppressWarnings("unchecked")
  private static List<EObject> children(final EObject container) {
    return (List<EObject>) container.eGet(container.eClass().getEStructuralFeature(CHILDREN_FEATURE));
  }

  private static String segment(final EObject child) {
    return new TestSelectorFragmentProvider().nameSegment(child);
  }

  private static EObject resolve(final EObject container, final String segment) {
    return new TestSelectorFragmentProvider().getEObjectFromSegment(container, segment);
  }

  private void assertFragmentMatchesAndResolves(final Resource res, final String expectedFragment, final EObject obj) {
    String fragment = res.getURIFragment(obj);
    assertEquals(expectedFragment, fragment);
    assertSame(obj, res.getEObject(fragment));
  }

  public EObject getModel(final String model) {
    try {
      return parseHelper.parse(model);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new AssertionError(e);
    }
  }

  static final class TestSelectorFragmentProvider extends AbstractSelectorFragmentProvider {

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
          if ("selectCardinality".equals(((Keyword) object).getValue())) {
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

    // the test runs in another bundle, so protected methods are only reachable from this subclass
    String nameSegment(final EObject obj) {
      StringBuilder builder = new StringBuilder();
      computeSelectorFragmentSegment(obj, obj.eClass().getEStructuralFeature(NAME_FEATURE), false, builder);
      return builder.toString();
    }

    private boolean appendFragmentSegment(final Grammar obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.GRAMMAR__NAME, true, builder);
    }

    private boolean appendFragmentSegment(final AbstractRule obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.ABSTRACT_RULE__NAME, false, builder);
    }

    private boolean appendFragmentSegment(final AbstractElement obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.ABSTRACT_ELEMENT__CARDINALITY, false, builder);
    }

    private boolean appendFragmentSegment(final Keyword obj, final StringBuilder builder) {
      return computeSelectorFragmentSegment(obj, XtextPackage.Literals.KEYWORD__VALUE, false, builder);
    }

  }
}
