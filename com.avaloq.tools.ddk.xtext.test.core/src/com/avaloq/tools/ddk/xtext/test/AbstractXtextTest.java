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
package com.avaloq.tools.ddk.xtext.test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResource;


/**
 * Provides a test class specific custom test framework for xtext languages. Provides a language specific {@link AbstractXtextTestUtil}.
 * All exceptions are wrapped and handed over to the JUnit framework.
 */
public abstract class AbstractXtextTest extends AbstractTest {

  /**
   * Returns a language specific {@link AbstractXtextTestUtil}. Test classes specify which TestUtil to use by implementing this method.
   *
   * @return a language specific {@link AbstractXtextTestUtil}
   */
  protected abstract AbstractXtextTestUtil getXtextTestUtil();

  @Override
  protected final AbstractXtextTestUtil getTestUtil() {
    return getXtextTestUtil();
  }

  @Override
  protected XtextTestSource getTestSource() {
    return (XtextTestSource) super.getTestSource();
  }

  @Override
  protected XtextTestSource createTestSource(final String sourceFileName, final String content) {
    XtextTestSource testSource = new XtextTestSource(sourceFileName, content, getTestUtil().getResourceSet());
    getTestProjectManager().addSourceToProject(testSource);
    return testSource;
  }

  /**
   * The default implementation returns the name of the source model calling {@link getTestSourceModelName} and adds the default file extension for the grammar
   * of this test. A test class needs to override this, if the name of the main test source file differs from the default.
   *
   * @return the name of the main test source file
   */
  @Override
  protected String getTestSourceFileName() {
    return this.getTestSourceModelName() + "." + getXtextTestUtil().getFileExtension();
  }

  /**
   * Returns the xtext resource loaded by {@link loadPrimarySource}.
   *
   * @return
   *         the xtext resource loaded by {@link loadPrimarySource}.
   */
  protected XtextResource getXtextTestResource() {
    XtextResource resource = getTestSource(getTestSourceFileName()).getXtextResource();
    // assertNotNull("Resource of main test source is not null.", resource);
    return resource;
  }

  /**
   * Returns the semantic model from the xtext resource loaded by {@link loadPrimarySource}.
   *
   * @return
   *         the semantic model from the xtext resource loaded by {@link loadPrimarySource}.
   */
  protected EObject getSemanticModel() {
    EObject model = getXtextTestResource().getParseResult().getRootASTElement();
    // assertNotNull("Semantic model of main test source is not null.", model);
    return model;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addKernelSourceToWorkspace(final String sourceFileName, final CharSequence sourceContent) {
    refreshSourceContent(sourceFileName, sourceContent.toString());
    super.addKernelSourceToWorkspace(sourceFileName, sourceContent);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addCustomerSourceToWorkspace(final String sourceFileName, final CharSequence sourceContent) {
    refreshSourceContent(CUSTOMER_SOURCE_PREFIX + sourceFileName, sourceContent.toString());
    super.addCustomerSourceToWorkspace(sourceFileName, sourceContent);
  }

  /**
   * Refresh the source content if it had already been added previously to the workspace.
   *
   * @param sourceFileName
   *          the source file name
   * @param content
   *          the content
   */
  private void refreshSourceContent(final String sourceFileName, final String content) {
    XtextTestSource xtextTestSource = getTestSource(sourceFileName);
    if (xtextTestSource != null) {
      xtextTestSource.setContent(content);
    }
  }

}

