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

package com.avaloq.tools.ddk.checkcfg.util;

import com.avaloq.tools.ddk.checkcfg.ui.internal.CheckcfgActivator;
import com.avaloq.tools.ddk.xtext.test.ITestProjectManager;
import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.inject.Injector;

public class CheckCfgTestUtil extends AbstractXtextTestUtil {

  private static final AbstractXtextTestUtil UTIL_INSTANCE = new CheckCfgTestUtil();

  public static AbstractXtextTestUtil getInstance() {
    return UTIL_INSTANCE;
  }

  @Override
  protected Injector getInjector() {
    return CheckcfgActivator.getInstance().getInjector(CheckcfgActivator.COM_AVALOQ_TOOLS_DDK_CHECKCFG_CHECKCFG);
  }

  @Override
  protected ITestProjectManager createTestProjectManager() {
    return new PluginTestProjectManager(getInjector());
  }
}
