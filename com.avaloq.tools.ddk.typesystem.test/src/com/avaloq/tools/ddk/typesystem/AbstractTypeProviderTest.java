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
package com.avaloq.tools.ddk.typesystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.junit.Before;
import org.junit.Test;

import com.avaloq.tools.ddk.typesystem.typemodel.IExpression;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.IType;
import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelFactory;
import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage;


public class AbstractTypeProviderTest {

  protected class TypeImpl extends EObjectImpl implements IType {}

  protected class ExpressionImpl extends EObjectImpl implements IExpression {}

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
  private final TypeModelPackage typeModelPackage = TypeModelPackage.eINSTANCE;

  private ITypeProvider delegateProvider;

  private EClass createEClass(final String name, final EClass... superTypes) {
    EcoreFactory modelFactory = EcoreFactory.eINSTANCE;
    EClass clazz = modelFactory.createEClass();
    clazz.setName(name);
    for (EClass superType : superTypes) {
      clazz.getESuperTypes().add(superType);
    }
    testModelPackage.getEClassifiers().add(clazz);
    return clazz;
  }

  @Before
  public void init() {
    EcoreFactory modelFactory = EcoreFactory.eINSTANCE;
    testModelPackage = modelFactory.createEPackage();
    testModelPackage.setName("TypeProviderTestEPackage");
    testModelPackage.setNsPrefix("typeprovidertestpackage");
    testModelPackage.setNsURI("http://testabstracttype");
    EFactory instanceFactory = testModelPackage.getEFactoryInstance();
    EClass clazz = createEClass("ExpressionContainer");
    expressionContainerReference = modelFactory.createEReference();
    clazz.getEStructuralFeatures().add(expressionContainerReference);
    expressionContainerReference.setName("expression");
    expressionContainerReference.setEType(typeModelPackage.getIExpression());
    expressionContainerReference.setContainment(true);
    expression1Container = instanceFactory.create(clazz);
    expression1Container.eSet(expressionContainerReference, expression1);
    expression2Container = instanceFactory.create(clazz);
    expression2Container.eSet(expressionContainerReference, expression2);
    expression3Container = instanceFactory.create(clazz);
    expression3Container.eSet(expressionContainerReference, expression3);
  }

  private void testPlainProvider(final ITypeProvider plainProvider) {
    assertEquals("type for expression1 not type1", type1, plainProvider.getType(expression1));
    assertEquals("type for expression2 not type2", type2, plainProvider.getType(expression2));
    assertNull("type for expression3 not null", plainProvider.getType(expression3));
    assertNull("type for null not null", plainProvider.getType(null));
    assertEquals("expected type for container1 not type1", type1, plainProvider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)));
    assertEquals("expected type for container2 not type2", type2, plainProvider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)));
    assertNull("expected type for container3 not null", plainProvider.getExpectedType((IExpression) expression3Container.eGet(expressionContainerReference)));
    assertNull("expected type for null not null", plainProvider.getExpectedType(null));
    assertEquals("type for namedElement1 not type1", type1, plainProvider.getTypeForNamedElement(namedElement1));
    assertEquals("type for namedElement2 not type2", type2, plainProvider.getTypeForNamedElement(namedElement2));
    assertNull("type for namedElement3 not null", plainProvider.getTypeForNamedElement(namedElement3));
    assertNull("type for named element null not null", plainProvider.getTypeForNamedElement(null));

  }

  @Test
  public void testTypeProviderPlain() {
    ITypeProvider provider = new PlainTypeProvider();
    testPlainProvider(provider);
  }

  @Test
  public void testTypeProviderCyclicDefault() {
    ITypeProvider provider = new CyclicDefaultTypeProvider();
    assertNull("cylic type for expression1 not null", provider.getType(expression1));
    assertNull("cylic type for expression2 not null", provider.getType(expression2));
    assertNull("cyclic expected type for container1 not null", provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)));
    assertNull("cyclic expected type for container2 not null", provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)));
    assertNull("cyclic type for namedElement1 not null", provider.getTypeForNamedElement(namedElement1));
    assertNull("cyclic type for namedElement2 not null", provider.getTypeForNamedElement(namedElement2));
  }

  @Test
  public void testTypeProviderCyclicOverride() {
    ITypeProvider provider = new CyclicOverrideTypeProvider();
    assertEquals("cyclic override type for expression1 not type1", type1, provider.getType(expression1));
    assertEquals("cyclic override type for expression2 not type1", type1, provider.getType(expression2));
    assertEquals("cyclic override expected type for container1 not type2", type2, provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)));
    assertEquals("cyclic override expected type for container2 not type2", type2, provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)));
    assertEquals("cyclic override type for namedElement1 not type3", type3, provider.getTypeForNamedElement(namedElement1));
    assertEquals("cyclic override type for namedElement2 not type3", type3, provider.getTypeForNamedElement(namedElement2));
  }

  @Test
  public void testTypeProviderCyclicOverrideMixed() {
    ITypeProvider provider = new CyclicOverrideMixedTypeProvider();
    assertEquals("cyclic mixed type for expression1 not type1", type1, provider.getType(expression1));
    assertEquals("cyclic mixed type for expression2 not type1", type1, provider.getType(expression2));
    assertEquals("cyclic mixed expected type for container1 not type2", type2, provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)));
    assertEquals("cyclic mixed expected type for container2 not type2", type2, provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)));
    assertEquals("cyclic mixed type for namedElement1 not type1", type1, provider.getTypeForNamedElement(namedElement1));
    assertEquals("cyclic mixed type for namedElement2 not type1", type1, provider.getTypeForNamedElement(namedElement2));
  }

  @Test
  public void testDelegatingTypeProvider() {
    delegateProvider = new PlainTypeProvider();
    testPlainProvider(new DelegatingTypeProvider());
  }

  @Test
  public void testDoNothingTypeProvider() {
    // we are mainly testing that the AbstractTypeProvider does not crash when the subclass does nothing
    ITypeProvider provider = new DoNothingTypeProvider();
    assertNull("did something for type of expression1", provider.getType(expression1));
    assertNull("did something for type of expression2", provider.getType(expression2));
    assertNull("did something for type of expression3", provider.getType(expression3));
    assertNull("did something for type of null", provider.getType(null));
    assertNull("did something for expected type of expression1", provider.getExpectedType((IExpression) expression1Container.eGet(expressionContainerReference)));
    assertNull("did something for expected type of expression2", provider.getExpectedType((IExpression) expression2Container.eGet(expressionContainerReference)));
    assertNull("did something for expected type of expression3", provider.getExpectedType((IExpression) expression3Container.eGet(expressionContainerReference)));
    assertNull("did something for expected type of null", provider.getExpectedType(null));
    assertNull("did something for type of namedElement1", provider.getTypeForNamedElement(namedElement1));
    assertNull("did something for type of namedElement2", provider.getTypeForNamedElement(namedElement2));
    assertNull("did something for type of namedElement3", provider.getTypeForNamedElement(namedElement3));
    assertNull("did something for type of namedElement null", provider.getTypeForNamedElement(null));
  }

  protected class PlainTypeProvider extends AbstractTypeProvider {

    @Override
    protected boolean providesTypeFor(final IExpression expression) {
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
    protected boolean providesTypeForNamedElement(final INamedElement element) {
      return false;
    }

    @Override
    protected ITypeProvider getTypeProviderFor(final EObject eObject) {
      assertNotNull("there is no delegate provider", delegateProvider);
      return delegateProvider;
    }

  }

  protected class DoNothingTypeProvider extends AbstractTypeProvider {

    @Override
    protected boolean providesTypeFor(final IExpression expression) {
      return false;
    }

    @Override
    protected boolean providesTypeForNamedElement(final INamedElement element) {
      return false;
    }

  }

}
