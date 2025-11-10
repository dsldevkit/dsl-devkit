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

package com.avaloq.tools.ddk.check.core.test

import com.avaloq.tools.ddk.check.CheckInjectorProvider
import java.io.ByteArrayInputStream
import java.util.List
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.xbase.testing.JavaSource
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith

import static org.junit.jupiter.api.Assertions.*

/**
 * Unit test for auto generation of check issue code to label map.
 */
@InjectWith(CheckInjectorProvider)
@ExtendWith(InjectionExtension)
class IssueCodeToLabelMapGenerationTest extends AbstractCheckGenerationTestCase {

  static final String PACKAGE_NAME = "mypackage"
  static final String CATALOG_NAME = "MyCatalog"

  /**
   * Test the map generated from a catalog with no checks.
   */
  @Test
  def void testMapGenerationWithNoChecks() {
    // ARRANGE
    val source = '''
      package «PACKAGE_NAME»

      catalog «CATALOG_NAME»
      for grammar com.avaloq.tools.ddk.check.Check {

      }
    ''';

    // check for the construction of an empty map
    val expectedCatalog = #['ImmutableMap.<String,String>builderWithExpectedSize(0).build()']

    // ACT AND ASSERT
    testMapGeneration(source, expectedCatalog)
  }

  /**
   * Test the map generated from a catalog with checks.
   */
  @Test
  def void testMapGeneration() {
    // ARRANGE
    // @Format-Off
    val source = '''
      package «PACKAGE_NAME»

      import com.avaloq.tools.ddk.check.check.Check
      import com.avaloq.tools.ddk.check.check.Context
      import com.avaloq.tools.ddk.check.check.Documented

      catalog «CATALOG_NAME»
      for grammar com.avaloq.tools.ddk.check.Check {

        live error ID1 "Label 1"
        message "Message 1" {
          for Documented elem {
            switch elem {
              Context : issue on elem
              Check : issue on elem
            }
          }
        }

        live error ID2 "Label 2"
        message "Message 2" {
          for Documented elem {
            switch elem {
              Context : issue on elem
              Check : issue on elem
            }
          }
        }
      }
    ''';
    // @Format-On

    val expectedCatalog = #['put(MyCatalogIssueCodes.ID_1,"Label1")','put(MyCatalogIssueCodes.ID_2,"Label2")']

    // ACT AND ASSERT
    testMapGeneration(source, expectedCatalog)
  }

  /**
   * Test the map generated from a catalog.
   * @param source
   *          the catalog, must not be {@code null}
   * @param expectedMap
   *          the expected map, may be {@code null}
   */
  def void testMapGeneration(String source, List<String> expectedCatalog) {
    // ACT
    var List<JavaSource> compiledClassesList
    val sourceStream = new ByteArrayInputStream(source.getBytes());
    try {
      compiledClassesList = generateAndCompile(sourceStream);
    } finally {
      sourceStream.close
    }

    // ASSERT
    val catalogClassName = '''«CATALOG_NAME»«CATALOG_NAME_SUFFIX»'''

    val catalogClass = compiledClassesList.findFirst[s | s.fileName.equals(catalogClassName)].code;

    for (code: expectedCatalog) {
      assertTrue( catalogClass.replaceAll("\\s+","").contains(code), '''«catalogClassName» was generated correctly''')
    }
  }

}
