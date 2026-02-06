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
package com.avaloq.tools.ddk.xtext.export.scoping;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.IScope;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.scoping.IDomain.NullMapper;
import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractScopingTest;


/**
 * Tests scoping of Code Tab Data Source
 */
@SuppressWarnings("nls")
class ExportScopingTest extends AbstractScopingTest {
  ExportScopingTest() {
    super(new NullMapper());
  }

  private final ExportScopeProvider scopeProvider = (ExportScopeProvider) getScopeProvider();

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  @Test
  void testImportPackageScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_Import_package(model.getImports().get(0), ExportPackage.Literals.IMPORT__PACKAGE);
    assertNotNull(scope.getSingleElement(QualifiedName.create("http://www.avaloq.com/tools/ddk/xtext/export/Export")), "Could not locate Import.");
    assertNull(scope.getSingleElement(QualifiedName.create("http://www.avaloq.com/tools/ddk/xtext/export/ExportX")), "Located non-existent Import.");
  }

  @Test
  void testEclassScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_EClass(model, null);
    assertNotNull(scope.getSingleElement(QualifiedName.create("InterfaceExpression")), "Could not locate EClass.");
    assertNull(scope.getSingleElement(QualifiedName.create("InterfaceExpressionX")), "Located non-existent EClass.");
  }

  @Test
  void testEStructuralFeatureScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_EStructuralFeature(model.getInterfaces().get(0), null);
    // CHECKSTYLE:OFF (DuplicateString)
    assertNotNull(scope.getSingleElement(QualifiedName.create("unordered")), "Could not locate EStructuralFeature.");
    assertNull(scope.getSingleElement(QualifiedName.create("unorderedX")), "Located non-existent EStructuralFeature.");
    // CHECKSTYLE:ON
  }

  @Test
  void testEAttributeScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_EAttribute(model.getInterfaces().get(0), null);
    // CHECKSTYLE:OFF (DuplicateString)
    assertNotNull(scope.getSingleElement(QualifiedName.create("unordered")), "Could not locate EStructuralFeature.");
    assertNull(scope.getSingleElement(QualifiedName.create("expr")), "Located non-existent EStructuralFeature.");
    // CHECKSTYLE:ON
  }

  @Test
  void testInterfaceNavigationRefScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_InterfaceNavigation_ref(model.getInterfaces().get(0), null);
    // CHECKSTYLE:OFF (DuplicateString)
    assertNotNull(scope.getSingleElement(QualifiedName.create("expr")), "Could not locate InterfaceNavigationRef.");
    assertNull(scope.getSingleElement(QualifiedName.create("unordered")), "Located non-existent InterfaceNavigationRef.");
    // CHECKSTYLE:ON
  }

}
