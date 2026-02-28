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

package com.avaloq.tools.ddk.checkcfg.scoping;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.test.core.jupiter.BugTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractScopingTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class CheckCfgScopeProviderTest extends AbstractScopingTest {

  private final IScopeProvider scopeProvider = getScopeProvider();

  /** {@inheritDoc} */
  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance();
  }

  /** {@inheritDoc} */
  @Override
  protected void registerRequiredSources() {
  }

  /**
   * Regression test for DSL-1498 Incorrect Catalog Name inserted by Content Assist
   * <p>
   * All catalogs supplied to Context Assist should be in the correct fully-qualified package.
   * </p>
   */
  @BugTest(value = "DSL-1498")
  public void testCatalogsAreInCorrectPackage() throws Exception {

    // ARRANGE

    final List<String> EXP_PACKAGE_NAME_PREFIX = List.of("com", "avaloq", "tools", "ddk");

    // Define test data
    final int CURSOR_POS = getTag();
    final StringBuilder sourceBuilder = new StringBuilder();
    sourceBuilder.append("check configuration testCheckCfg {\n");
    sourceBuilder.append("  ").append(mark(CURSOR_POS)).append("\n");
    sourceBuilder.append("}\n");
    final String SOURCE_CONTENT = sourceBuilder.toString();

    // Register a check configuration source, and get a context model
    registerModel(getTestSourceFileName(), SOURCE_CONTENT);
    final EObject context = getMarkerTagsInfo().getModel(CURSOR_POS);
    if (null == context) {
      throw new NullPointerException("Got null context model");
    }

    // ACT

    // Get catalogs
    final Iterable<IEObjectDescription> elements = scopeProvider.getScope(context, CheckcfgPackage.Literals.CONFIGURED_CATALOG__CATALOG).getAllElements();
    if (!elements.iterator().hasNext()) {
      throw new Exception("Scope has no elements");
    }

    // ASSERT

    elements.forEach((IEObjectDescription element) -> {
      // Check catalog has the correct fully-qualified package name
      final List<String> actualName = element.getName().getSegments();
      final List<String> actualPackageName = actualName.subList(0, EXP_PACKAGE_NAME_PREFIX.size());
      assertArrayEquals(EXP_PACKAGE_NAME_PREFIX.toArray(), actualPackageName.toArray(), "Catalog must have the correct fully-qualified package name");
    });
  }
}
