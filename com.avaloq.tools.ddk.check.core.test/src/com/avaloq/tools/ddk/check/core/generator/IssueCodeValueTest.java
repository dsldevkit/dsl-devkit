/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.check.core.generator;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.xbase.testing.JavaSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.check.core.test.AbstractCheckGenerationTestCase;

import static org.junit.jupiter.api.Assertions.assertTrue;

@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class IssueCodeValueTest extends AbstractCheckGenerationTestCase {

  private static final String PACKAGE_NAME = "mypackage";
  private static final String CATALOG_NAME = "MyCatalog";

  /**
   * Test the map generated from a catalog with checks.
   */
  @Test
  public void testIssueCodeValue() throws Exception {
    // ARRANGE
    // @Format-Off
    String source = "package " + PACKAGE_NAME + "\n"
        + "\n"
        + "import com.avaloq.tools.ddk.check.check.Check\n"
        + "import com.avaloq.tools.ddk.check.check.Context\n"
        + "import com.avaloq.tools.ddk.check.check.Documented\n"
        + "\n"
        + "catalog " + CATALOG_NAME + "\n"
        + "for grammar com.avaloq.tools.ddk.check.Check {\n"
        + "\n"
        + "  live error MyCheck1 \"Label 1\"\n"
        + "  message \"Message 1\" {\n"
        + "    for Documented elem {\n"
        + "      switch elem {\n"
        + "        Context : issue on elem\n"
        + "        Check : issue on elem\n"
        + "      }\n"
        + "    }\n"
        + "  }\n"
        + "\n"
        + "  live error MyCheck_2 \"Label 2\"\n"
        + "  message \"Message 2\" {\n"
        + "    for Documented elem {\n"
        + "      switch elem {\n"
        + "        Context : issue on elem\n"
        + "        Check : issue on elem\n"
        + "      }\n"
        + "    }\n"
        + "  }\n"
        + "\n"
        + "  live error MYCheck3 \"Label 3\"\n"
        + "  message \"Message 3\" {\n"
        + "    for Documented elem {\n"
        + "      switch elem {\n"
        + "        Context : issue on elem\n"
        + "        Check : issue on elem\n"
        + "      }\n"
        + "    }\n"
        + "  }\n"
        + "}\n";
    // @Format-On

    Map<String, String> expectedIssueCodeValues = Map.of(
        "MY_CHECK_1", "MyCheck1",
        "MY_CHECK_2", "MyCheck2",
        "MY_CHECK_3", "MyCheck3");

    // ACT
    List<JavaSource> compiledClassesList;
    ByteArrayInputStream sourceStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
    try {
      compiledClassesList = generateAndCompile(sourceStream);
    } finally {
      sourceStream.close();
    }

    // ASSERT
    String issueCodesClassName = CATALOG_NAME + ISSUE_CODES_SUFFIX;

    String issueCodesClass = compiledClassesList.stream()
        .filter(s -> s.getFileName().equals(issueCodesClassName))
        .findFirst()
        .orElse(null)
        .getCode();

    for (Map.Entry<String, String> issueCode : expectedIssueCodeValues.entrySet()) {
      String expectedIssueCodeAssignment = "public static final String " + issueCode.getKey()
          + " = \"" + PACKAGE_NAME + "." + CATALOG_NAME + ISSUE_CODES_SUFFIX + "." + issueCode.getValue() + "\";";
      assertTrue(issueCodesClass.contains(expectedIssueCodeAssignment),
          issueCodesClassName + " contains correct initialization of " + issueCode.getKey());
    }
  }

}
