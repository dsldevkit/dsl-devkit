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

package com.avaloq.tools.ddk.check.core.generator

import org.eclipse.xtext.testing.InjectWith
import org.junit.runner.RunWith
import com.avaloq.tools.ddk.check.CheckInjectorProvider
import org.eclipse.xtext.testing.XtextRunner
import com.avaloq.tools.ddk.check.core.test.AbstractCheckGenerationTestCase
import org.junit.Test
import java.util.List
import org.eclipse.xtext.xbase.testing.JavaSource
import java.io.ByteArrayInputStream

@InjectWith(CheckInjectorProvider)
@RunWith(XtextRunner)
class IssueCodeValueTest extends AbstractCheckGenerationTestCase {

  static final String PACKAGE_NAME = "mypackage"
  static final String CATALOG_NAME = "MyCatalog"

  /**
   * Test the map generated from a catalog with checks.
   */
  @Test
  def void testIssueCodeValue() {
    // ARRANGE
    // @Format-Off
    val source = '''
      package «PACKAGE_NAME»

      import com.avaloq.tools.ddk.check.check.Check
      import com.avaloq.tools.ddk.check.check.Context
      import com.avaloq.tools.ddk.check.check.Documented

      catalog «CATALOG_NAME»
      for grammar com.avaloq.tools.ddk.check.Check {

        live error MyCheck1 "Label 1"
        message "Message 1" {
          for Documented elem {
            switch elem {
              Context : issue on elem
              Check : issue on elem
            }
          }
        }

        live error MyCheck_2 "Label 2"
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

    val expectedIssueCodeValues = #{'MY_CHECK_1' -> 'MyCheck1', 'MY_CHECK_2' -> 'MyCheck2'}

    // ACT
    var List<JavaSource> compiledClassesList
    val sourceStream = new ByteArrayInputStream(source.getBytes());
    try {
      compiledClassesList = generateAndCompile(sourceStream);
    } finally {
      sourceStream.close
    }

    // ASSERT
    val issueCodesClassName = '''«CATALOG_NAME»«ISSUE_CODES_SUFFIX»'''

    val issueCodesClass = compiledClassesList.findFirst[s | s.fileName.equals(issueCodesClassName)].code;

    for (issueCode: expectedIssueCodeValues.entrySet) {
      val expectedIssueCodeAssignment = '''public static final String «issueCode.key» = "«PACKAGE_NAME».«CATALOG_NAME»«ISSUE_CODES_SUFFIX».«issueCode.value»";'''
      assertTrue('''«issueCodesClassName» was generated correctly''', issueCodesClass.contains(expectedIssueCodeAssignment))
    }
  }

}
