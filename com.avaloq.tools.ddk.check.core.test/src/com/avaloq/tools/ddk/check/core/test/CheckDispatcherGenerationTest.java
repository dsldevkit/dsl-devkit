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

package com.avaloq.tools.ddk.check.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.testing.InMemoryJavaCompiler;
import org.eclipse.xtext.xbase.testing.JavaSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.core.test.util.RegistryIsolatingCheckInjectorProvider;


/**
 * Unit tests for the dispatching {@code validate} method generated for check catalogs.
 */
@InjectWith(RegistryIsolatingCheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckDispatcherGenerationTest extends AbstractCheckGenerationTestCase {

  private static final String CATALOG_NAME = "MyCatalog";

  /** Pattern for the name of a variable holding the validated object as the type of a context variable. */
  private static final String CAST_OBJECT = "castObject\\d*";

  private static final String SOURCE = """
      package mypackage

      import com.avaloq.tools.ddk.check.check.CheckCatalog
      import com.avaloq.tools.ddk.check.check.Documented
      import org.eclipse.emf.ecore.EObject

      catalog %s
      for grammar com.avaloq.tools.ddk.check.Check {

        live error ObjectCheck "Label 1"
        message "Message 1" {
          for EObject {
            issue on it
          }
        }

        live error DocumentedCheck "Label 2"
        message "Message 2" {
          for Documented elem {
            issue on elem
          }
        }

        live error CatalogCheck "Label 3" (boolean enabled = true)
        message "Message 3" {
          for CheckCatalog c {
            if (enabled) {
              issue on c
            }
          }
        }
      }
      """.formatted(CATALOG_NAME);

  /**
   * Each context method must be passed as a lambda that captures nothing, so that no object is allocated per check and per validated object.
   */
  @Test
  public void testContextMethodsAreDispatchedWithoutCapturingLambdas() {
    final String validatorName = CATALOG_NAME + VALIDATOR_NAME_SUFFIX;
    final List<JavaSource> sources = generateAndCompile(new ByteArrayInputStream(SOURCE.getBytes(StandardCharsets.UTF_8)));
    final String validator = sources.stream().filter(s -> validatorName.equals(s.getFileName())).findFirst().orElseThrow().getCode();
    final String code = validator.replaceAll("\\s+", "");

    assertDispatch(code, "objectCheckEObject", "object");
    assertDispatch(code, "documentedCheckDocumented", CAST_OBJECT);
    assertDispatch(code, "catalogCheckCheckCatalog", CAST_OBJECT);
    assertFalse(code.contains("()->"), "No context method should be passed as a capturing Runnable");

    final InMemoryJavaCompiler.Result result = new InMemoryJavaCompiler(getClass().getClassLoader(), JavaVersion.JAVA21).compile(sources.toArray(new JavaSource[0]));
    assertEquals(List.of(), result.getCompilationProblems().stream() //
        .filter(p -> p.isError() && new String(p.getOriginatingFileName()).contains(validatorName)) //
        .map(p -> p.getMessage()) //
        .toList(), "The generated validator should compile");
  }

  /**
   * Asserts that the given context method is dispatched with the given target variable and a non-capturing lambda.
   *
   * @param code
   *          the generated validator source without whitespace, must not be {@code null}
   * @param methodName
   *          the name of the context method, must not be {@code null}
   * @param targetPattern
   *          a pattern for the name of the variable passed as target, must not be {@code null}
   */
  private void assertDispatch(final String code, final String methodName, final String targetPattern) {
    final String expected = "validate\\(\"" + methodName + "\",\"" + CATALOG_NAME + "\\." + methodName + "\",object," + targetPattern
        + ",\\(catalog,target,collector\\)->\\(\\(" + CATALOG_NAME + VALIDATOR_NAME_SUFFIX + "\\)catalog\\)\\." + methodName
        + "\\(target,collector\\),diagnosticCollector\\);";
    assertTrue(Pattern.compile(expected).matcher(code).find(), "Unexpected dispatch of " + methodName + " in:\n" + code);
  }

}
