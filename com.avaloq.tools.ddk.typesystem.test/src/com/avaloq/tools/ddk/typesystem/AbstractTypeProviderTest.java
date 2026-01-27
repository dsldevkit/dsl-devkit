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
package com.avaloq.tools.ddk.typesystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.typesystem.typemodel.IExpression;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.IType;
import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelFactory;
import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage;


@SuppressWarnings("nls")
class AbstractTypeProviderTest {

  protected class TypeImpl extends EObjectImpl implements IType {
  }

  protected class ExpressionImpl extends EObjectImpl implements IExpression {
  }

  private final IType type1 = new TypeImpl();
  private final IType type2 = new TypeImpl();
  private final IType type3 = new TypeImpl();
  private final IExpression expression1 = new ExpressionImpl();
  private final IExpression expression2 = new ExpressionImpl();
  private final IExpression expression3 = new ExpressionImpl();
  private final INamedElement namedElement1 = TypeModelFactory.eINSTANCE.createNamedElement();
  private final INamedElement namedElement2 = TypeModelFactory.eINSTANCE.createNamedElement();
  private final INamedElement namedElement3 = TypeModelFactory.eINSTANCE.createNamedElement();
  private EObject expression1Container;
  private EObject expression2Container;
  private EObject expression3Container;
  private EReference expressionContainerReference;

  private EPackage testModelPackage;
  private static final TypeModelPackage TYPE_MODEL_PACKAGE = TypeModelPackage.eINSTANCE;

  private ITypeProvider delegateProvider;

  private EClass createEClass(final String name, final EClass... superTypes) {
    EcoreFactory modelFactory = EcoreFactory.eINSTANCE;
    EClass clazz = modelFactory.createEClass();
    clazz.setName(name);
    clazz.getESuperTypes().addAll(Arrays.asList(superTypes));
    testModelPackage.getEClassifiers().add(clazz);
    return clazz;
  }

  @BeforeEach
  void init() {
    EcoreFactory modelFactory = EcoreFactory.eINSTANCE;
    testModelPackage = modelFactory.createEPackage();
    testModelPackage.setName("TypeProviderTestEPackage");
    testModelPackage.setNsPrefix("typeprovidertestpackage");
    testModelPackage.setNsURI("http://testabstracttype");
    EClass clazz = createEClass("ExpressionContainer");
    expressionContainerReference = modelFactory.createEReference();
    clazz.getEStructuralFeatures().add(expressionContainerReference);
    expressionContainerReference.setName("expression");
    expressionContainerReference.setEType(TYPE_MODEL_PACKAGE.getIExpression());
    expressionContainerReference.setContainment(true);
    EFactory instanceFactory = testModelPackage.getEFactoryInstance();
    expression1Container = instanceFactory.create(clazz);
    expression1Container.eSet(expressionContainerReference, expression1);
    expression2Container = instanceFactory.create(clazz);
    expression2Container.eSet(expressionContainerReference, expression2);
    expression3Container = instanceFactory.create(clazz);
    expression3Container.eSet(expressionContainerReference, expression3);
  }

  private void testPlainProvider(final ITypeProvider plainProvider) {
    assertEquals(type1, plainProvider.getType(expression1), "type for expression1 not type1");
    assertEquals(type2, plainProvider.getType(expression2), "type for expression2 not type2");
    assertNull(plainProvider.getType(expression3), "type for expression3 not null");
    assertNull(plainProvider.getType(null), "type for null not null");
    assertEquals(type1, plainProvider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)), "expected type for container1 not type1");
    assertEquals(type2, plainProvider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)), "expected type for container2 not type2");
    assertNull(plainProvider.getExpectedType((IExpression) expression3Container.eGet(expressionContainerReference)), "expected type for container3 not null");
    assertNull(plainProvider.getExpectedType(null), "expected type for null not null");
    assertEquals(type1, plainProvider.getTypeForNamedElement(namedElement1), "type for namedElement1 not type1");
    assertEquals(type2, plainProvider.getTypeForNamedElement(namedElement2), "type for namedElement2 not type2");
    assertNull(plainProvider.getTypeForNamedElement(namedElement3), "type for namedElement3 not null");
    assertNull(plainProvider.getTypeForNamedElement(null), "type for named element null not null");

  }

  @Test
  void testTypeProviderPlain() {
    ITypeProvider provider = new PlainTypeProvider();
    testPlainProvider(provider);
  }

  @Test
  void testTypeProviderCyclicDefault() {
    ITypeProvider provider = new CyclicDefaultTypeProvider();
    assertNull(provider.getType(expression1), "cylic type for expression1 not null");
    assertNull(provider.getType(expression2), "cylic type for expression2 not null");
    assertNull(provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)), "cyclic expected type for container1 not null");
    assertNull(provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)), "cyclic expected type for container2 not null");
    assertNull(provider.getTypeForNamedElement(namedElement1), "cyclic type for namedElement1 not null");
    assertNull(provider.getTypeForNamedElement(namedElement2), "cyclic type for namedElement2 not null");
  }

  @Test
  void testTypeProviderCyclicOverride() {
    ITypeProvider provider = new CyclicOverrideTypeProvider();
    assertEquals(type1, provider.getType(expression1), "cyclic override type for expression1 not type1");
    assertEquals(type1, provider.getType(expression2), "cyclic override type for expression2 not type1");
    assertEquals(type2, provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)), "cyclic override expected type for container1 not type2");
    assertEquals(type2, provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)), "cyclic override expected type for container2 not type2");
    assertEquals(type3, provider.getTypeForNamedElement(namedElement1), "cyclic override type for namedElement1 not type3");
    assertEquals(type3, provider.getTypeForNamedElement(namedElement2), "cyclic override type for namedElement2 not type3");
  }

  @Test
  void testTypeProviderCyclicOverrideMixed() {
    ITypeProvider provider = new CyclicOverrideMixedTypeProvider();
    assertEquals(type1, provider.getType(expression1), "cyclic mixed type for expression1 not type1");
    assertEquals(type1, provider.getType(expression2), "cyclic mixed type for expression2 not type1");
    assertEquals(type2, provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)), "cyclic mixed expected type for container1 not type2");
    assertEquals(type2, provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)), "cyclic mixed expected type for container2 not type2");
    assertEquals(type1, provider.getTypeForNamedElement(namedElement1), "cyclic mixed type for namedElement1 not type1");
    assertEquals(type1, provider.getTypeForNamedElement(namedElement2), "cyclic mixed type for namedElement2 not type1");
  }

  @Test
  void testDelegatingTypeProvider() {
    delegateProvider = new PlainTypeProvider();
    testPlainProvider(new DelegatingTypeProvider());
  }

  @Test
  void testDoNothingTypeProvider() {
    // we are mainly testing that the AbstractTypeProvider does not crash when the subclass does nothing
    ITypeProvider provider = new DoNothingTypeProvider();
    assertNull(provider.getType(expression1), "did something for type of expression1");
    assertNull(provider.getType(expression2), "did something for type of expression2");
    assertNull(provider.getType(expression3), "did something for type of expression3");
    assertNull(provider.getType(null), "did something for type of null");
    assertNull(provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)), "did something for expected type of expression1");
    assertNull(provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)), "did something for expected type of expression2");
    assertNull(provider.getExpectedType((IExpression) expression3Container.eGet(expressionContainerReference)), "did something for expected type of expression3");
    assertNull(provider.getExpectedType(null), "did something for expected type of null");
    assertNull(provider.getTypeForNamedElement(namedElement1), "did something for type of namedElement1");
    assertNull(provider.getTypeForNamedElement(namedElement2), "did something for type of namedElement2");
    assertNull(provider.getTypeForNamedElement(namedElement3), "did something for type of namedElement3");
    assertNull(provider.getTypeForNamedElement(null), "did something for type of namedElement null");
  }

  protected class PlainTypeProvider extends AbstractTypeProvider {

    @Override
    protected boolean providesTypeFor(final IExpression expression) {
      return true;
    }

    @Override
    protected boolean providesExpectedTypeFor(final IExpression expression) {
      return true;
    }

    @Override
    protected boolean providesTypeForNamedElement(final INamedElement element) {
      return true;
    }

    @Override
    protected IType type(final IExpression expression) {
      if (expression == expression1) { // NOPMD
        return type1;
      } else if (expression == expression2) { // NOPMD
        return type2;
      } else {
        return super.type(expression);
      }
    }

    @Override
    protected IType expectedType(final EObject container, final EReference reference, final int index) {
      if (container == expression1Container && reference == expressionContainerReference && index == -1) { // NOPMD
        return type1;
      } else if (container == expression2Container && reference == expressionContainerReference && index == -1) { // NOPMD
        return type2;
      } else {
        return super.expectedType(container, reference, index);
      }
    }

    @Override
    protected IType typeForNamedElement(final INamedElement element) {
      if (element == namedElement1) { // NOPMD
        return type1;
      } else if (element == namedElement2) { // NOPMD
        return type2;
      } else {
        return super.getTypeForNamedElement(element);
      }
    }

  }

  protected class CyclicDefaultTypeProvider extends AbstractTypeProvider {

    @Override
    protected boolean providesTypeFor(final IExpression expression) {
      return true;
    }

    @Override
    protected boolean providesExpectedTypeFor(final IExpression expression) {
      return true;
    }

    @Override
    protected boolean providesTypeForNamedElement(final INamedElement element) {
      return true;
    }

    @Override
    protected IType type(final IExpression expression) {
      if (expression == expression1) { // NOPMD
        return getType(expression2);
      } else if (expression == expression2) { // NOPMD
        return getType(expression1);
      } else {
        return super.type(expression);
      }
    }

    @Override
    protected IType expectedType(final EObject container, final EReference reference, final int index) {
      if (container == expression1Container && reference == expressionContainerReference && index == -1) { // NOPMD
        return getExpectedType(expression2);
      } else if (container == expression2Container && reference == expressionContainerReference && index == -1) { // NOPMD
        return getExpectedType(expression1);
      } else {
        return super.expectedType(container, reference, index);
      }
    }

    @Override
    protected IType typeForNamedElement(final INamedElement element) {
      if (element == namedElement1) { // NOPMD
        return getTypeForNamedElement(namedElement2); // test on mixed calls;
      } else if (element == namedElement2) { // NOPMD
        return getTypeForNamedElement(namedElement1);
      } else {
        return super.getTypeForNamedElement(element);
      }
    }

  }

  protected class CyclicOverrideTypeProvider extends CyclicDefaultTypeProvider {

    @Override
    protected IType handleCyclicGetType(final IExpression expression) {
      return type1;
    }

    @Override
    protected IType handleCycleGetExpectedType(final IExpression expression) {
      return type2;
    }

    @Override
    protected IType handleCycleGetTypeForNamedElement(final INamedElement element) {
      return type3;
    }
  }

  protected class CyclicOverrideMixedTypeProvider extends CyclicOverrideTypeProvider {
    @Override
    protected IType type(final IExpression expression) { // NOPMD
      if (expression == expression1) { // NOPMD
        return getType(expression2);
      } else if (expression == expression2) { // NOPMD
        return getExpectedType(expression1);
      } else {
        return super.type(expression);
      }
    }

    @Override
    protected IType expectedType(final EObject container, final EReference reference, final int index) {
      if (container == expression1Container && reference == expressionContainerReference && index == -1) { // NOPMD
        return getExpectedType(expression2);
      } else if (container == expression2Container && reference == expressionContainerReference && index == -1) { // NOPMD
        return getType(expression1);
      } else {
        return super.expectedType(container, reference, index);
      }
    }

    @Override
    protected IType typeForNamedElement(final INamedElement element) {
      if (element == namedElement1) { // NOPMD
        return getTypeForNamedElement(namedElement2); // test on mixed calls;
      } else if (element == namedElement2) { // NOPMD
        return getType(expression1);
      } else {
        return super.getTypeForNamedElement(element);
      }
    }

  }

  protected class DelegatingTypeProvider extends AbstractTypeProvider {

    @Override
    protected boolean providesTypeFor(final IExpression expression) {
      return false;
    }

    @Override
    protected boolean providesExpectedTypeFor(final IExpression expression) {
      return false;
    }

    @Override
    protected boolean providesTypeForNamedElement(final INamedElement element) {
      return false;
    }

    @Override
    protected ITypeProvider getTypeProviderFor(final EObject eObject) {
      assertNotNull(delegateProvider, "there is no delegate provider");
      return delegateProvider;
    }

  }

  protected class DoNothingTypeProvider extends AbstractTypeProvider {

    @Override
    protected boolean providesTypeFor(final IExpression expression) {
      return false;
    }

    @Override
    protected boolean providesExpectedTypeFor(final IExpression expression) {
      return false;
    }

    @Override
    protected boolean providesTypeForNamedElement(final INamedElement element) {
      return false;
    }

  }

}
