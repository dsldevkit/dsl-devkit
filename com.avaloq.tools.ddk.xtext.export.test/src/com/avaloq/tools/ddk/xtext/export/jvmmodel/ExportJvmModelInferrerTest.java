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
package com.avaloq.tools.ddk.xtext.export.jvmmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;


/**
 * Tests the JVM model inferred for an export model without an {@code export &lt;name&gt; for &lt;grammar&gt;} header.
 * <p>
 * That header is optional, so {@code ExportModel.name} is {@code null} for such models. The documentation of the inferred
 * resource description manager must then be rendered with an empty name rather than with the string {@code "null"}.
 */
@SuppressWarnings("nls")
public class ExportJvmModelInferrerTest extends AbstractXtextTest {

  /** Name of the header-less export model parsed by this test. */
  private static final String MODEL_NAME = "HeaderlessExportModel";

  /** Source of the header-less export model parsed by this test. */
  private static final String MODEL_SOURCE = "import \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\n"
      + "\n"
      + "export ecore::EClass as name\n";

  /** Simple name suffix of the inferred resource description manager. */
  private static final String MANAGER_SUFFIX = "ResourceDescriptionManager";

  /** Documentation expected on the inferred resource description manager; the name is empty, hence the two blanks. */
  private static final String EXPECTED_DOCUMENTATION = "Resource description manager for  resources.";

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  /**
   * This test builds its source in memory and has no test source file. {@inheritDoc}
   */
  @Override
  protected String getTestSourceFileName() {
    return null;
  }

  @Test
  public void testResourceDescriptionManagerDocumentationOfHeaderlessModel() throws IOException {
    final Resource resource = parseHeaderlessModel();
    final JvmGenericType manager = findInferredManager(resource);
    assertNotNull(manager, "The inferrer must infer a resource description manager for a header-less export model.");
    final JvmTypesBuilder typesBuilder = getXtextTestUtil().get(JvmTypesBuilder.class);
    assertEquals(EXPECTED_DOCUMENTATION, typesBuilder.getDocumentation(manager),
        "A null export model name must render as an empty string, not as \"null\".");
  }

  /**
   * Parses the header-less export model and installs its derived state; the documentation under test does not depend on the
   * grammar, see {@link ExportTestUtil#parseWithoutGrammar(URI, String)}.
   *
   * @return the loaded resource, never {@code null}
   * @throws IOException
   *           if the model cannot be parsed
   */
  private Resource parseHeaderlessModel() throws IOException {
    return getXtextTestUtil().parseWithoutGrammar(getTargetSourceUri(MODEL_NAME + ".export"), MODEL_SOURCE);
  }

  /**
   * Returns the inferred resource description manager of the given resource.
   *
   * @param resource
   *          the resource of the export model, must not be {@code null}
   * @return the inferred type, or {@code null} if the inferrer did not produce one
   */
  private JvmGenericType findInferredManager(final Resource resource) {
    for (final EObject content : resource.getContents()) {
      if (content instanceof JvmGenericType && ((JvmGenericType) content).getSimpleName().endsWith(MANAGER_SUFFIX)) {
        return (JvmGenericType) content;
      }
    }
    return null;
  }

}
