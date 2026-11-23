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

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.testing.InMemoryJavaCompiler;
import org.eclipse.xtext.xbase.testing.JavaSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.check.CheckFactory;
import com.avaloq.tools.ddk.check.core.test.util.RegistryIsolatingCheckInjectorProvider;
import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;


/**
 * Unit tests for the {@code hasChecksFor} method generated for check catalogs.
 */
@InjectWith(RegistryIsolatingCheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckHasChecksForGenerationTest extends AbstractCheckGenerationTestCase {

  private static final String PACKAGE_NAME = "mypackage";

  private static final String CATALOG_NAME = "MyCatalog";

  private static final String VALIDATOR_NAME = CATALOG_NAME + VALIDATOR_NAME_SUFFIX;

  /**
   * A catalog has checks for the subtypes of the base types of its context variables, and only for those.
   *
   * @throws ReflectiveOperationException
   *           if the generated catalog cannot be instantiated
   */
  @Test
  public void testCatalogHasChecksForSubtypesOfContextTypes() throws ReflectiveOperationException {
    final String source = """
        package %s

        import com.avaloq.tools.ddk.check.check.CheckCatalog
        import com.avaloq.tools.ddk.check.check.ContextVariable
        import com.avaloq.tools.ddk.check.check.Documented

        catalog %s
        for grammar com.avaloq.tools.ddk.check.Check {

          live error DocumentedCheck "Label 1"
          message "Message 1" {
            for Documented elem {
              issue on elem
            }
          }

          live error CatalogCheck "Label 2"
          message "Message 2" {
            for CheckCatalog c {
              issue on c
            }
          }

          live error VariableCheck "Label 3"
          message "Message 3" {
            for ContextVariable v {
              issue on v
            }
          }
        }
        """.formatted(PACKAGE_NAME, CATALOG_NAME);

    final List<JavaSource> sources = generateAndCompile(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)));
    assertTrue(getValidatorCode(sources).contains("returnContextVariable.class.isAssignableFrom(type)||Documented.class.isAssignableFrom(type);"), "Only the base types should be tested");

    final InMemoryJavaCompiler.Result result = new InMemoryJavaCompiler(getClass().getClassLoader(), JavaVersion.JAVA21).compile(sources.toArray(new JavaSource[0]));
    assertEquals(List.of(), result.getCompilationProblems().stream() //
        .filter(p -> p.isError() && new String(p.getOriginatingFileName()).contains(VALIDATOR_NAME)) //
        .map(p -> p.getMessage()) //
        .toList(), "The generated validator should compile");

    final ICheckValidatorImpl catalog = (ICheckValidatorImpl) result.getClassLoader().loadClass(PACKAGE_NAME + '.' + VALIDATOR_NAME).getDeclaredConstructor().newInstance();
    final CheckFactory factory = CheckFactory.eINSTANCE;
    assertTrue(catalog.hasChecksFor(factory.createCheckCatalog().getClass()), "A subtype of a context type should be checked");
    assertTrue(catalog.hasChecksFor(factory.createContextVariable().getClass()), "A context type should be checked");
    assertFalse(catalog.hasChecksFor(factory.createFormalParameter().getClass()), "Another type should not be checked");
  }

  /**
   * A catalog with a context for {@code EObject} has checks for every object.
   */
  @Test
  public void testCatalogForEObjectHasChecksForEverything() {
    final String source = """
        package %s

        import org.eclipse.emf.ecore.EObject

        catalog %s
        for grammar com.avaloq.tools.ddk.check.Check {

          live error ObjectCheck "Label 1"
          message "Message 1" {
            for EObject {
              issue on it
            }
          }
        }
        """.formatted(PACKAGE_NAME, CATALOG_NAME);

    final List<JavaSource> sources = generateAndCompile(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)));
    assertTrue(getValidatorCode(sources).contains("publicbooleanhasChecksFor(finalClass<?>type){returntrue;}"), "Every object should be checked");
  }

  /**
   * Returns the source of the generated validator without whitespace.
   *
   * @param sources
   *          the generated sources, must not be {@code null}
   * @return the source of the validator, never {@code null}
   */
  private String getValidatorCode(final List<JavaSource> sources) {
    return sources.stream().filter(s -> VALIDATOR_NAME.equals(s.getFileName())).findFirst().orElseThrow().getCode().replaceAll("\\s+", "");
  }

}
