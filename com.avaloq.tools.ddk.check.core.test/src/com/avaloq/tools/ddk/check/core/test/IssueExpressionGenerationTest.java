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
 * Unit tests for the code generated for the various forms of the {@code issue} expression: markers on a text region, markers on a dynamically
 * computed structural feature, and checks declared {@code external}.
 */
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class IssueExpressionGenerationTest extends AbstractCheckGenerationTestCase {

  private static final String PACKAGE_NAME = "mypackage";

  private static final String CATALOG_NAME = "MyCatalog";

  /**
   * An {@code issue ... at <region>} expression must produce an offset and length based marker, and must fall back to the object based marker for an
   * absent or empty region.
   */
  @Test
  public void testRegionBasedIssue() {
    final String source = """
        package %s

        import com.avaloq.tools.ddk.check.check.Documented
        import org.eclipse.xtext.nodemodel.util.NodeModelUtils

        catalog %s
        for grammar com.avaloq.tools.ddk.check.Check {

          live error ID1 "Label 1"
          message "Message 1" {
            for Documented elem {
              issue on elem at NodeModelUtils.getNode(elem)
            }
          }
        }
        """.formatted(PACKAGE_NAME, CATALOG_NAME);

    final String validator = generateAndRead(source, VALIDATOR_NAME_SUFFIX);
    assertTrue(validator.contains(".getOffset()"), "The region offset should be passed to the acceptor");
    assertTrue(validator.contains(".getLength()"), "The region length should be passed to the acceptor");
    assertTrue(validator.contains("INSIGNIFICANT_INDEX"), "The fall back to an object based marker should be generated");
  }

  /**
   * An {@code issue on <object> # (<expression>)} expression must pass the computed structural feature to the acceptor.
   */
  @Test
  public void testDynamicMarkerFeature() {
    final String source = """
        package %s

        import com.avaloq.tools.ddk.check.check.CheckPackage
        import com.avaloq.tools.ddk.check.check.Documented

        catalog %s
        for grammar com.avaloq.tools.ddk.check.Check {

          live error ID1 "Label 1"
          message "Message 1" {
            for Documented elem {
              issue on elem # (CheckPackage.eINSTANCE.checkCatalog_Name)
            }
          }
        }
        """.formatted(PACKAGE_NAME, CATALOG_NAME);

    final String validator = generateAndRead(source, VALIDATOR_NAME_SUFFIX);
    assertTrue(validator.replaceAll("\\s+", "").contains("getCheckCatalog_Name()"), "The computed structural feature should be passed to the acceptor");
  }

  /**
   * An {@code external} check carries no issue expression, yet its issue code and label must still be generated for the hand-written code that
   * raises the issue, and its constraint must still be executed. The latter matters for checks that exist only to trigger the computation of a
   * derived property.
   */
  @Test
  public void testExternalCheckProducesIssueCodeAndLabel() {
    final String source = """
        package %s

        import com.avaloq.tools.ddk.check.check.Documented

        catalog %s
        for grammar com.avaloq.tools.ddk.check.Check {

          external live error ID1 "Label 1"
          message "Message 1" {
            for Documented elem {
              elem.description
            }
          }
        }
        """.formatted(PACKAGE_NAME, CATALOG_NAME);

    final String issueCodes = generateAndRead(source, ISSUE_CODES_SUFFIX);
    assertTrue(issueCodes.contains("ID_1"), "The issue code of an external check should be generated");

    final String catalog = generateAndRead(source, CATALOG_NAME_SUFFIX);
    assertTrue(catalog.replaceAll("\\s+", "").contains("put(MyCatalogIssueCodes.ID_1,\"Label1\")"), "The label of an external check should be generated");

    final String validator = generateAndRead(source, VALIDATOR_NAME_SUFFIX);
    assertTrue(validator.contains("getDescription()"), "The constraint of an external check should still be executed");
  }

  /**
   * Generates the given catalog and returns the source of one of the generated Java classes.
   *
   * @param source
   *          the check catalog source, must not be {@code null}
   * @param classNameSuffix
   *          the suffix identifying the generated class, must not be {@code null}
   * @return the generated Java source, never {@code null}
   */
  private String generateAndRead(final String source, final String classNameSuffix) {
    final ByteArrayInputStream sourceStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
    final List<JavaSource> compiledClassesList = generateAndCompile(sourceStream);
    return compiledClassesList.stream() //
        .filter(s -> s.getFileName().equals(CATALOG_NAME + classNameSuffix)) //
        .findFirst() //
        .orElseThrow() //
        .getCode();
  }

}
