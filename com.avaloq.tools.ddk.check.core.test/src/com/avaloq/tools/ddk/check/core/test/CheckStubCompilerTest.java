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
package com.avaloq.tools.ddk.check.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.generator.CheckStubCompiler;
import com.google.inject.Inject;


/**
 * Tests that the generated catalog stub declares the public API of a catalog and none of its implementation.
 */
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckStubCompilerTest {

  private static final String MODEL_PATH = "storage/StorageChecks";

  /** Strings that only occur in the implementation of the catalog and must therefore not be part of the stub. */
  private static final String[] IMPLEMENTATION_MARKERS = { //
      "hidden-member-literal", "hidden-implementation-literal", "hidden-guard-literal", //
      "hiddenCatalogMember", "hiddenImplementation", "hiddenImplementationVariable", //
      "import", "def ", "guard", "issue"};

  @Inject
  private ParseHelper<CheckCatalog> parser;

  @Inject
  private CheckStubCompiler stubCompiler;

  /**
   * Compiles the stub of the test catalog.
   *
   * @return the stub source, never {@code null}
   * @throws Exception
   *           if the test model cannot be read or parsed
   */
  private String compileStub() throws Exception {
    try (InputStream in = CheckStubCompilerTest.class.getResourceAsStream(MODEL_PATH)) {
      assertNotNull(in, "Test model must be available");
      CheckCatalog catalog = parser.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8));
      assertNotNull(catalog, "Test model must be parsable");
      return stubCompiler.compile(catalog).toString();
    }
  }

  /**
   * Tests that the stub declares the catalog and all data needed to reference its checks.
   */
  @Test
  void testStubDeclaresPublicApi() throws Exception {
    String stub = compileStub();

    assertTrue(stub.contains("package storage"), stub);
    assertTrue(stub.contains("catalog StorageChecks for grammar com.avaloq.tools.ddk.check.Check {"), stub);
    assertTrue(stub.contains("category PublicCategoryId \"Public category label\" {"), stub);
    assertTrue(stub.contains("@SeverityRange(warning .. error)"), stub);
    assertTrue(stub.contains(
        "live error PublicCheckId \"Public check label\" (int publicParameter = 23 \"Public parameter label\") message \"Public message\" {"), stub);
    assertTrue(stub.contains("for com.avaloq.tools.ddk.check.check.Documented publicContextVariable {}"), stub);
    assertTrue(stub.contains("external warning ExternalCheckId \"External check label\""), stub);
  }

  /**
   * Tests that the stub contains none of the implementation of the catalog.
   */
  @Test
  void testStubContainsNoImplementation() throws Exception {
    String stub = compileStub();

    for (String marker : IMPLEMENTATION_MARKERS) {
      assertFalse(stub.contains(marker), "Stub must not contain '" + marker + "':\n" + stub);
    }
  }

  /**
   * Tests that the stub can be parsed again and yields the same public API.
   */
  @Test
  void testStubIsParsable() throws Exception {
    CheckCatalog catalog = parser.parse(compileStub());

    assertNotNull(catalog, "Stub must be parsable");
    assertFalse(((XtextResource) catalog.eResource()).getParseResult().hasSyntaxErrors(), "Stub must not have syntax errors");
    assertEquals("storage", catalog.getPackageName(), "Package name");
    assertEquals("StorageChecks", catalog.getName(), "Catalog name");
    assertNotNull(catalog.getGrammar(), "Grammar reference");
    assertEquals(2, catalog.getAllChecks().size(), "Number of checks");
    assertTrue(catalog.getMembers().isEmpty(), "Members");
    assertTrue(catalog.getImplementations().isEmpty(), "Implementations");

    Category category = catalog.getCategories().get(0);
    assertEquals("PublicCategoryId", category.getId(), "Category id");
    Check check = category.getChecks().get(0);
    assertEquals("PublicCheckId", check.getId(), "Check id");
    assertEquals("Public check label", check.getLabel(), "Check label");
    assertEquals("Public message", check.getMessage(), "Check message");
    assertEquals(SeverityKind.ERROR, check.getDefaultSeverity(), "Default severity");
    assertEquals(SeverityKind.WARNING, check.getSeverityRange().getMinSeverity(), "Minimum severity");
    assertEquals("publicParameter", check.getFormalParameters().get(0).getName(), "Parameter name");
    assertEquals("Public parameter label", check.getFormalParameters().get(0).getLabel(), "Parameter label");

    Context context = check.getContexts().get(0);
    assertEquals("publicContextVariable", context.getContextVariable().getName(), "Context variable name");
    assertNotNull(context.getConstraint(), "Constraint is an empty block");
    assertTrue(catalog.getChecks().get(0).isExternal(), "External check");
  }

}
