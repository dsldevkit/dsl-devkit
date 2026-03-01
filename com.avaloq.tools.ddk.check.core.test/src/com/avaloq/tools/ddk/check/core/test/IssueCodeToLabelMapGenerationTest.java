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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.xbase.testing.JavaSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;


/**
 * Unit test for auto generation of check issue code to label map.
 */
// CHECKSTYLE:CONSTANTS-OFF
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class IssueCodeToLabelMapGenerationTest extends AbstractCheckGenerationTestCase {

  private static final String PACKAGE_NAME = "mypackage";

  private static final String CATALOG_NAME = "MyCatalog";

  /**
   * Test the map generated from a catalog with no checks.
   */
  @Test
  public void testMapGenerationWithNoChecks() {
    // ARRANGE
    StringBuilder builder = new StringBuilder(512);
    builder.append("package ");
    builder.append(PACKAGE_NAME);
    builder.append('\n');
    builder.append('\n');
    builder.append("catalog ");
    builder.append(CATALOG_NAME);
    builder.append('\n');
    builder.append("for grammar com.avaloq.tools.ddk.check.Check {\n");
    builder.append('\n');
    builder.append("}\n");
    final String source = builder.toString();

    // check for the construction of an empty map
    final List<String> expectedCatalog = List.of("ImmutableMap.<String,String>builderWithExpectedSize(0).build()");

    // ACT AND ASSERT
    testMapGeneration(source, expectedCatalog);
  }

  /**
   * Test the map generated from a catalog with checks.
   */
  @Test
  public void testMapGeneration() {
    // ARRANGE
    // CHECKSTYLE:CHECK-OFF VariableDeclarationUsageDistance
    // @Format-Off
    StringBuilder builder = new StringBuilder(2048);
    builder.append("package ");
    builder.append(PACKAGE_NAME);
    builder.append('\n');
    builder.append('\n');
    builder.append("import com.avaloq.tools.ddk.check.check.Check\n");
    builder.append("import com.avaloq.tools.ddk.check.check.Context\n");
    builder.append("import com.avaloq.tools.ddk.check.check.Documented\n");
    builder.append('\n');
    builder.append("catalog ");
    builder.append(CATALOG_NAME);
    builder.append('\n');
    builder.append("for grammar com.avaloq.tools.ddk.check.Check {\n");
    builder.append('\n');
    builder.append("  live error ID1 \"Label 1\"\n");
    builder.append("  message \"Message 1\" {\n");
    builder.append("    for Documented elem {\n");
    builder.append("      switch elem {\n");
    builder.append("        Context : issue on elem\n");
    builder.append("        Check : issue on elem\n");
    builder.append("      }\n");
    builder.append("    }\n");
    builder.append("  }\n");
    builder.append('\n');
    builder.append("  live error ID2 \"Label 2\"\n");
    builder.append("  message \"Message 2\" {\n");
    builder.append("    for Documented elem {\n");
    builder.append("      switch elem {\n");
    builder.append("        Context : issue on elem\n");
    builder.append("        Check : issue on elem\n");
    builder.append("      }\n");
    builder.append("    }\n");
    builder.append("  }\n");
    builder.append("}\n");
    final String source = builder.toString();
    // CHECKSTYLE:CHECK-ON VariableDeclarationUsageDistance
    // @Format-On

    final List<String> expectedCatalog = List.of("put(MyCatalogIssueCodes.ID_1,\"Label1\")", "put(MyCatalogIssueCodes.ID_2,\"Label2\")");

    // ACT AND ASSERT
    testMapGeneration(source, expectedCatalog);
  }

  /**
   * Test the map generated from a catalog.
   *
   * @param source
   *          the catalog, must not be {@code null}
   * @param expectedCatalog
   *          the expected map, may be {@code null}
   */
  public void testMapGeneration(final String source, final List<String> expectedCatalog) {
    // ACT
    final ByteArrayInputStream sourceStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
    final List<JavaSource> compiledClassesList = generateAndCompile(sourceStream);

    // ASSERT
    final String catalogClassName = CATALOG_NAME + CATALOG_NAME_SUFFIX;

    final String catalogClass = compiledClassesList.stream()
        .filter(s -> s.getFileName().equals(catalogClassName))
        .findFirst()
        .orElseThrow()
        .getCode();

    for (final String code : expectedCatalog) {
      assertTrue(catalogClass.replaceAll("\\s+", "").contains(code), catalogClassName + " was generated correctly");
    }
  }
}
// CHECKSTYLE:CONSTANTS-ON
