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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
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

  private String exportedNamesProviderFor(final String uri) {
    final Resource resource = new ResourceImpl(URI.createURI(uri));
    final ExportModel model = ExportFactory.eINSTANCE.createExportModel();
    resource.getContents().add(model);
    return exportGeneratorX.getExportedNamesProvider(model);
  }
}
