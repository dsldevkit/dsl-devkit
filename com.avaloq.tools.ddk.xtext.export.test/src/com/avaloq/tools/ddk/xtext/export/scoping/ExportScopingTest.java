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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.IScope;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.scoping.AbstractScopingTest;
import com.avaloq.tools.ddk.xtext.scoping.IDomain.NullMapper;


/**
 * Tests scoping of Code Tab Data Source
 */
public class ExportScopingTest extends AbstractScopingTest {
  public ExportScopingTest() {
    super(new NullMapper());
  }

  private final ExportScopeProvider scopeProvider = (ExportScopeProvider) getScopeProvider();

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  @Test
  public void testImportPackageScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_Import_package(model.getImports().get(0), ExportPackage.Literals.IMPORT__PACKAGE);
    assertNotNull("Could not locate Import.", scope.getSingleElement(QualifiedName.create("http://www.avaloq.com/tools/ddk/xtext/export/Export")));
    assertNull("Located non-existent Import.", scope.getSingleElement(QualifiedName.create("http://www.avaloq.com/tools/ddk/xtext/export/ExportX")));
  }

  @Test
  public void testEclassScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_EClass(model, null);
    assertNotNull("Could not locate EClass.", scope.getSingleElement(QualifiedName.create("InterfaceExpression")));
    assertNull("Located non-existent EClass.", scope.getSingleElement(QualifiedName.create("InterfaceExpressionX")));
  }

  @Test
  public void testEStructuralFeatureScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_EStructuralFeature(model.getInterfaces().get(0), null);
    // CHECKSTYLE:OFF (DuplicateString)
    assertNotNull("Could not locate EStructuralFeature.", scope.getSingleElement(QualifiedName.create("unordered")));
    assertNull("Located non-existent EStructuralFeature.", scope.getSingleElement(QualifiedName.create("unorderedX")));
    // CHECKSTYLE:ON
  }

  @Test
  public void testEAttributeScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_EAttribute(model.getInterfaces().get(0), null);
    // CHECKSTYLE:OFF (DuplicateString)
    assertNotNull("Could not locate EStructuralFeature.", scope.getSingleElement(QualifiedName.create("unordered")));
    assertNull("Located non-existent EStructuralFeature.", scope.getSingleElement(QualifiedName.create("expr")));
    // CHECKSTYLE:ON
  }

  @Test
  public void testInterfaceNavigationRefScope() throws IOException {
    ExportModel model = (ExportModel) getTestSource().getModel();
    IScope scope = scopeProvider.scope_InterfaceNavigation_ref(model.getInterfaces().get(0), null);
    // CHECKSTYLE:OFF (DuplicateString)
    assertNotNull("Could not locate InterfaceNavigationRef.", scope.getSingleElement(QualifiedName.create("expr")));
    assertNull("Located non-existent InterfaceNavigationRef.", scope.getSingleElement(QualifiedName.create("unordered")));
    // CHECKSTYLE:ON
  }

}
