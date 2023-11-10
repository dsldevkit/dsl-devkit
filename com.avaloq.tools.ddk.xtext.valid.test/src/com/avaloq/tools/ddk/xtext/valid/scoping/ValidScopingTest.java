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
package com.avaloq.tools.ddk.xtext.valid.scoping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.junit.Test;

import validscopingtest.ValidscopingtestPackage;
import validscopingtest.impl.ValidscopingtestPackageImpl;

import com.avaloq.tools.ddk.xtext.test.scoping.AbstractScopingTest;
import com.avaloq.tools.ddk.xtext.test.valid.util.ValidTestUtil;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidModel;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;
import com.avaloq.tools.ddk.xtext.scoping.IDomain.NullMapper;


/**
 * Tests scoping of the Valid model ValidScopingTest.valid which is based on the ValidScopingTest.ecore meta model.
 * Note that the ValidScopingTest Ecore model is very simple and only intended to be used for tests concerning the
 * scope provider implementation for the Valid language. In order to extend that (meta) model, edit the Ecore instance,
 * reload the genmodel and generate model code.
 */
public class ValidScopingTest extends AbstractScopingTest {

  public ValidScopingTest() {
    super(new NullMapper());
  }

  private final ValidScopeProvider scopeProvider = (ValidScopeProvider) getScopeProvider();

  
  @Override
  protected void beforeAllTests() {
    // The ValidScopingTest model must be loaded
    ValidscopingtestPackageImpl.init();
    super.beforeAllTests();
  }

  
  @Override
  protected ValidTestUtil getXtextTestUtil() {
    return ValidTestUtil.getInstance();
  }

  /**
   * Tests that the Valid language allows defining EClass instances as contexts for validation rules.
   */
  @Test
  public void testEClassScope() throws IOException {
    final IScope scope = scopeProvider.getScope(getTestSource().getModel(), ValidPackage.Literals.CONTEXT__CONTEXT_TYPE);

    assertNotNull("Found EClass \"Model\"", scope.getSingleElement(QualifiedName.create("Model")));
    assertNull("Found non-existing EClass", scope.getSingleElement(QualifiedName.create("NonExistingEClass")));
  }

  /**
   * Tests that validations may be declared on existing EClass and EStructuralFeature instances.
   */
  @Test
  public void testEStructuralFeatureScope() throws IOException {
    final ValidModel validModel = (ValidModel) getTestSource().getModel();
    final NativeContext context = getXtextTestUtil().getFirstInstanceOf(validModel, NativeContext.class);

    // Check context feature reference
    IScope scope = scopeProvider.getScope(context, ValidPackage.Literals.CONTEXT__CONTEXT_FEATURE);
    IEObjectDescription name = scope.getSingleElement(QualifiedName.create("name"));
    assertNotNull("Found valid EStructuralFeature \"name\"", name);
    final EObject resolvedName = name.getEObjectOrProxy();
    assertNotNull("Valid EStructuralFeature \"name\" can be resolved", resolvedName);

    // Check context type reference
    scope = scopeProvider.getScope(context, ValidPackage.Literals.CONTEXT__CONTEXT_TYPE);
    assertEquals("Scope provider returns correct context type", context.getContextType(), scope.getSingleElement(QualifiedName.create("Model")).getEObjectOrProxy());
    assertEquals("Container of \"name\" reference instance is \"Model\" instance", resolvedName.eContainer(), scope.getSingleElement(QualifiedName.create("Model")).getEObjectOrProxy());

    // Check marker type reference
    scope = scopeProvider.getScope(context, ValidPackage.Literals.NATIVE_CONTEXT__MARKER_TYPE);
    assertEquals("Scope provider returns correct marker type", context.getMarkerType(), scope.getSingleElement(QualifiedName.create("Element")).getEObjectOrProxy());

    // Check marker feature reference
    scope = scopeProvider.getScope(context, ValidPackage.Literals.NATIVE_CONTEXT__MARKER_FEATURE);
    assertEquals("Scope provider returns correct marker feature", context.getMarkerFeature(), scope.getSingleElement(QualifiedName.create("name")).getEObjectOrProxy());
  }

  /**
   * Tests that EPackage instances can be imported by means of their NS URI. If an NS URI to a non-existing EPackage is provided,
   * the scope provider will return <code>null</code> for such an import, i.e. will not locate it.
   */
  @Test
  public void testEPackageScope() throws IOException {
    final ValidModel model = (ValidModel) getTestSource().getModel();
    IScope scope = scopeProvider.getScope(model, ValidPackage.Literals.IMPORT__PACKAGE);

    final EPackage validScopingTestPackage = model.getImports().get(0).getPackage();
    assertEquals("NS URI of first import is valid", ValidscopingtestPackage.eNS_URI, validScopingTestPackage.getNsURI());

    final EPackage nonExistingPackage = model.getImports().get(1).getPackage();
    assertNull("Non-existing EPackage has no NS URI", nonExistingPackage.getNsURI());

    assertNotNull("Import of existing EPackage is valid", scope.getSingleElement(QualifiedName.create(ValidscopingtestPackage.eNS_URI)));
    assertNull("Non-existing EPackage cannot be imported", scope.getSingleElement(QualifiedName.create("http://www.avaloq.com/tools/ddk/acf/nonexistingnsuri")));
  }

}

