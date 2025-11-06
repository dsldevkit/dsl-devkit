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
package com.avaloq.tools.ddk.xtext.test.format.util;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.avaloq.tools.ddk.xtext.format.FormatConstants;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.ui.internal.FormatActivator;
import com.avaloq.tools.ddk.xtext.test.ITestProjectManager;
import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.inject.Injector;


@SuppressWarnings("nls")
public final class FormatTestUtil extends AbstractXtextTestUtil {
  public static final String TEST_MODEL_NAME = "FormatterTestLanguage";
  public static final String NEW_LINE = "\n";
  private XtextResourceSet resourceSet;

  private FormatTestUtil() {
    // private constructor
  }

  /**
   * The singleton instance.
   */
  private static final class InstanceHolder {
    // Initialize-on-demand holder pattern.
    private static final FormatTestUtil INSTANCE = new FormatTestUtil();

    public static FormatTestUtil get() {
      return INSTANCE;
    }
  }

  public static FormatTestUtil getInstance() {
    return InstanceHolder.get();
  }

  @Override
  protected Injector getInjector() {
    return FormatActivator.getInstance().getInjector(FormatConstants.GRAMMAR);
  }

  @Override
  protected ITestProjectManager createTestProjectManager() {
    return new PluginTestProjectManager(getInjector());
  }

  /**
   * Gets a semantic FormatConfiguration model from an input string.
   *
   * @param contents
   *          the input string (i.e. the model as a string)
   * @return a Format model instance
   */
  public FormatConfiguration getModelFromString(final String contents) {
    return getModelFromString(contents, TEST_MODEL_NAME);
  }

  /**
   * Gets a semantic FormatConfiguration model from an input string.
   *
   * @param contents
   *          the input string (i.e. the model as a string)
   * @param sourceFileName
   *          the source's name
   * @return a Format model instance
   */
  public FormatConfiguration getModelFromString(final String contents, final String sourceFileName) {
    EObject result = null;
    try {
      result = getModel(sourceFileName + "." + FormatConstants.FILE_EXTENSION, contents);
    } catch (IOException e) {
      fail("Could not create model: " + e.getMessage());
    }
    return (FormatConfiguration) result;
  }

  /**
   * Reset / re-create resourceSet.
   */
  public void resetResourceSet() {
    resourceSet = super.getResourceSet();
  }

  /**
   * Overrides in order to always return the same resource set.
   *
   * @see #resetResourceSet()
   *      {@inheritDoc}
   */
  @Override
  public XtextResourceSet getResourceSet() {
    if (resourceSet == null) {
      resourceSet = super.getResourceSet();
    }
    return resourceSet;
  }
}
