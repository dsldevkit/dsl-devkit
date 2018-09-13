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

package com.avaloq.tools.ddk.xtext.generator;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;


/**
 * Give the generated packages the names we want.
 */
public class GeneratorNaming extends XtextGeneratorNaming {
  private static final String PACKAGE_SEPARATOR = "."; //$NON-NLS-1$
  private static final String TESTS_PACKAGE_SUFFIX = ""; //$NON-NLS-1$
  private static final String IDE_PACKAGE_SUFFIX = PACKAGE_SEPARATOR + "ide"; //$NON-NLS-1$
  private static final String UI_PACKAGE_SUFFIX = PACKAGE_SEPARATOR + "ui"; //$NON-NLS-1$

  @Override
  public String getRuntimeTestBasePackage(final Grammar grammar) {
    return getRuntimeBasePackage(grammar) + TESTS_PACKAGE_SUFFIX;
  }

  @Override
  public String getGenericIdeBasePackage(final Grammar grammar) {
    return getRuntimeBasePackage(grammar) + IDE_PACKAGE_SUFFIX;
  }

  @Override
  public String getGenericIdeTestBasePackage(final Grammar grammar) {
    return getGenericIdeBasePackage(grammar) + TESTS_PACKAGE_SUFFIX;
  }

  @Override
  public String getEclipsePluginBasePackage(final Grammar grammar) {
    return getRuntimeBasePackage(grammar) + UI_PACKAGE_SUFFIX;
  }

  @Override
  public String getEclipsePluginTestBasePackage(final Grammar grammar) {
    return getEclipsePluginBasePackage(grammar) + TESTS_PACKAGE_SUFFIX;
  }
}