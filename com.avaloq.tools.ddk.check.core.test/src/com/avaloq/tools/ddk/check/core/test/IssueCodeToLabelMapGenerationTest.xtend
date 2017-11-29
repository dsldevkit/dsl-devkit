/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.check.core.test

import com.avaloq.tools.ddk.check.CheckInjectorProvider
import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl
import com.google.common.collect.ImmutableMap
import java.io.ByteArrayInputStream
import java.util.Collections
import java.util.Map
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Unit test for autogeneration of check issue code to label map.
 */
@InjectWith(CheckInjectorProvider)
@RunWith(XtextRunner)
class IssueCodeToLabelMapGenerationTest extends AbstractCheckGenerationTestCase {

  private static final String PACKAGE_NAME = "mypackage"
  private static final String CATALOG_NAME = "MyCatalog"

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

    val expectedMap = Collections.emptyMap

    // ACT AND ASSERT
    testMapGeneration(source, expectedMap)
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

    val expectedMap = #{"mypackage.MyCatalogIssueCodes.id.1" -> "Label 1",
                        "mypackage.MyCatalogIssueCodes.id.2" -> "Label 2"}
    // @Format-On

    // ACT AND ASSERT
    testMapGeneration(source, expectedMap)
  }

  /**
   * Test the map generated from a catalog.
   * @param source
   *          the catalog, must not be {@code null}
   * @param expectedMap
   *          the expected map, may be {@code null}
   */
  def void testMapGeneration(String source, Map<String, String> expectedMap) {
    // ACT
    var Map<String, Class<?>> compiledClassesMap
    val sourceStream = new ByteArrayInputStream(source.getBytes());
    try {
      compiledClassesMap = generateAndCompile(sourceStream);
    } finally {
      sourceStream.close
    }

    // ASSERT
    val catalogClassName = '''«PACKAGE_NAME».«CATALOG_NAME»«CATALOG_NAME_SUFFIX»'''
    val validatorClassName = '''«PACKAGE_NAME».«CATALOG_NAME»«VALIDATOR_NAME_SUFFIX»'''
    val mapGetterName = "getIssueCodeToLabelMap"

    val catalogClass = compiledClassesMap.get(catalogClassName);
    val validatorClass = compiledClassesMap.get(validatorClassName) as Class<ICheckValidatorImpl>;

    val mapFromCatalog = catalogClass.getMethod(mapGetterName).invoke(null) as ImmutableMap<String, String>;
    val mapFromValidator = validatorClass.newInstance().getIssueCodeToLabelMap();

    assertEquals('''«catalogClassName».«mapGetterName»() was generated correctly''', expectedMap, mapFromCatalog)
    assertEquals('''«validatorClassName».«mapGetterName»() was generated correctly''', expectedMap, mapFromValidator)
  }

}
