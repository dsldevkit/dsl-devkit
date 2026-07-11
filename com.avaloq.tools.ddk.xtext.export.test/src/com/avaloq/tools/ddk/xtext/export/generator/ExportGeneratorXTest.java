/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.export.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.XtextFactory;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.export.export.ExportFactory;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;


/**
 * Regression tests for URI-based package derivation in {@link ExportGeneratorX}.
 */
@SuppressWarnings("nls")
public class ExportGeneratorXTest extends AbstractXtextTest {

  private static final String DERIVED_PACKAGE_PROVIDER = "com.avaloq.naming.fooExportedNamesProvider";
  private static final String PROJECT_FALLBACK_PROVIDER = "MyProject.naming.fooExportedNamesProvider";
  private static final String DEFAULT_FALLBACK_PROVIDER = "generated.naming.fooExportedNamesProvider";
  private static final String NO_SIBLING_URI = "platform:/resource/TEST/NoSibling.export";

  private final ExportGeneratorX exportGeneratorX = getXtextTestUtil().get(ExportGeneratorX.class);

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  @Override
  protected String getTestSourceFileName() {
    return null; // all tests fabricate synthetic resource URIs
  }

  @Test
  public void testNestedSourceFolderUrisKeepDerivedPackage() {
    assertEquals(DERIVED_PACKAGE_PROVIDER, exportedNamesProviderFor("platform:/resource/MyProject/src/com/avaloq/foo.export"));
    assertEquals(DERIVED_PACKAGE_PROVIDER, exportedNamesProviderFor("platform:/resource/MyProject/src-gen/com/avaloq/foo.export"));
    assertEquals("main.java.com.avaloq.naming.fooExportedNamesProvider", exportedNamesProviderFor("platform:/resource/MyProject/src/main/java/com/avaloq/foo.export"));
    assertEquals("com.naming.fooExportedNamesProvider", exportedNamesProviderFor("platform:/resource/MyProject/src/com/foo.export")); // 5 segments: derivation boundary
  }

  @Test
  public void testShortUrisFallBackToValidPackage() {
    assertEquals(PROJECT_FALLBACK_PROVIDER, exportedNamesProviderFor("platform:/resource/MyProject/foo.export"));
    assertEquals(PROJECT_FALLBACK_PROVIDER, exportedNamesProviderFor("platform:/resource/MyProject/src/foo.export"));
    assertEquals(DEFAULT_FALLBACK_PROVIDER, exportedNamesProviderFor("platform:/resource/1My-Project/foo.export"));
    assertEquals(DEFAULT_FALLBACK_PROVIDER, exportedNamesProviderFor("MyProject/foo.export")); // 2 segments: below the project-name fallback
  }

  @Test
  public void testSingleSegmentUriFallsBackToDefaultPackage() {
    assertEquals(DEFAULT_FALLBACK_PROVIDER, exportedNamesProviderFor("foo.export"));
  }

  @Test
  public void testGetGrammarReturnsNullWithoutResolvableGrammar() {
    final ExportModel detached = ExportFactory.eINSTANCE.createExportModel();
    assertNull(exportGeneratorX.getGrammar(detached), "model without a resource");

    final ExportModel withoutResourceSet = ExportFactory.eINSTANCE.createExportModel();
    new ResourceImpl(URI.createURI(NO_SIBLING_URI)).getContents().add(withoutResourceSet);
    assertNull(exportGeneratorX.getGrammar(withoutResourceSet), "resource without a resource set");

    final ExportModel withoutSibling = ExportFactory.eINSTANCE.createExportModel();
    final Resource resource = new ResourceSetImpl().createResource(URI.createURI(NO_SIBLING_URI));
    resource.getContents().add(withoutSibling);
    assertNull(exportGeneratorX.getGrammar(withoutSibling), "missing sibling grammar must not throw");

    final Grammar proxy = XtextFactory.eINSTANCE.createGrammar();
    ((InternalEObject) proxy).eSetProxyURI(URI.createURI("platform:/resource/TEST/Unresolved.xtext#/"));
    withoutSibling.setTargetGrammar(proxy);
    assertNull(exportGeneratorX.getGrammar(withoutSibling), "unresolved targetGrammar proxy");
  }

  private String exportedNamesProviderFor(final String uri) {
    final Resource resource = new ResourceImpl(URI.createURI(uri));
    final ExportModel model = ExportFactory.eINSTANCE.createExportModel();
    resource.getContents().add(model);
    return exportGeneratorX.getExportedNamesProvider(model);
  }
}
