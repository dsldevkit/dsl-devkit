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
package com.avaloq.tools.ddk.check.ui.test.util;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.ITestProjectManager;
import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager;
import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.ui.internal.CheckActivator;
import com.google.inject.Injector;


/**
 * Test utility class for Check.
 */
public class CheckXtextTestUtil extends AbstractXtextTestUtil {
  private static final CheckXtextTestUtil INSTANCE = new CheckXtextTestUtil();

  public static CheckXtextTestUtil getInstance() {
    return INSTANCE;
  }

  @Override
  protected Injector getInjector() {
    return CheckActivator.getInstance().getInjector(CheckConstants.GRAMMAR);
  }

  @Override
  protected ITestProjectManager createTestProjectManager() {
    return new PluginTestProjectManager(getInjector());
  }
}

