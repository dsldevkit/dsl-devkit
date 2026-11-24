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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.xbase.testing.JavaSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.core.test.util.RegistryIsolatingCheckInjectorProvider;


/**
 * Tests that the names the Check generator derives from catalog and check names do not depend on the default locale.
 */
@InjectWith(RegistryIsolatingCheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class LocaleIndependentGenerationTest extends AbstractCheckGenerationTestCase {

  private static final String CATALOG_NAME = "InfoCatalog";

  /**
   * Generates a catalog under a Turkish default locale, where {@code "i".toUpperCase()} is a dotted capital I, and checks
   * that issue codes and generated method names use the English case mapping.
   */
  @Test
  public void testGeneratedNamesIgnoreTurkishDefaultLocale() {
    final String source = """
        package mypackage
        import com.avaloq.tools.ddk.check.check.Documented
        catalog %s
        for grammar com.avaloq.tools.ddk.check.Check {
          live error invalidId "Invalid id"
          message "Invalid id" {
            for Documented elem {
              issue on elem
            }
          }
        }
        """.formatted(CATALOG_NAME);

    final Locale defaultLocale = Locale.getDefault();
    final List<JavaSource> generated;
    try {
      Locale.setDefault(Locale.of("tr", "TR"));
      generated = generateAndCompile(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)));
    } finally {
      Locale.setDefault(defaultLocale);
    }

    final String issueCodes = code(generated, CATALOG_NAME + ISSUE_CODES_SUFFIX);
    assertTrue(issueCodes.contains("INVALID_ID"), "issue code constant uses English upper case:\n" + issueCodes);
    final String catalog = code(generated, CATALOG_NAME + CATALOG_NAME_SUFFIX);
    assertTrue(catalog.contains("getInvalidIdMessage"), "message getter uses English upper case:\n" + catalog);
  }

  /**
   * Returns the code of the generated file with the given name.
   *
   * @param generated
   *          the generated files
   * @param fileName
   *          the file name, without extension
   * @return the code of the file
   */
  private static String code(final List<JavaSource> generated, final String fileName) {
    return generated.stream().filter(s -> fileName.equals(s.getFileName())).findFirst().orElseThrow().getCode();
  }
}
