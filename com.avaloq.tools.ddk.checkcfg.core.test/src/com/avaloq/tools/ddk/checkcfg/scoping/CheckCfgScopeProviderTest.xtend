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

package com.avaloq.tools.ddk.checkcfg.scoping

import com.avaloq.tools.ddk.test.core.BugTest
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil
import com.avaloq.tools.ddk.xtext.test.scoping.AbstractScopingTest

import static org.junit.Assert.assertArrayEquals

final class CheckCfgScopeProviderTest extends AbstractScopingTest {

  private final val scopeProvider = getScopeProvider();

  /** {@inheritDoc} */
  override protected getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance;
  }

  /** {@inheritDoc} */
  override protected registerRequiredSources() {}

  /**
   * Regression test for DSL-1498 Incorrect Catalog Name inserted by Content Assist
   * <p>
   * All catalogs supplied to Context Assist should be in the correct fully-qualified package.
   * </p>
   */
  @BugTest(value="DSL-1498")
  def testCatalogsAreInCorrectPackage() {

    // ARRANGE

    val EXP_PACKAGE_NAME = #["com", "avaloq", "tools", "ddk", "check", "validation"];

    // Define test data
    val CURSOR_POS = getTag;
    val SOURCE_CONTENT = '''
      check configuration testCheckCfg {
        «mark(CURSOR_POS)»
      }
    ''';

    // Register a check configuration source, and get a context model
    registerModel(getTestSourceFileName, SOURCE_CONTENT);
    val context = getMarkerTagsInfo().getModel(CURSOR_POS);
    if (null == context) {
      throw new NullPointerException("Got null context model");
    }

    // ACT

    // Get catalogs
    val elements = scopeProvider.getScope(context, CheckcfgPackage.Literals.CONFIGURED_CATALOG__CATALOG).getAllElements;
    if (elements.empty) {
      throw new Exception("Scope has no elements");
    }

    // ASSERT

    elements.forEach[element |
      // Check catalog has the correct fully-qualified package name
      val actualName = element.name.segments;
      val actualPackageName = actualName.take(actualName.size - 1);
      assertArrayEquals("Catalog must have the correct fully-qualified package name", EXP_PACKAGE_NAME, actualPackageName);
    ]
  }
}
