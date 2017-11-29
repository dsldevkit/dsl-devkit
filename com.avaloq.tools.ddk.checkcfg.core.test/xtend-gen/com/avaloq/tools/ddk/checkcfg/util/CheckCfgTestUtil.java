/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.util;

import com.avaloq.tools.ddk.checkcfg.ui.internal.CheckCfgActivator;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.ITestProjectManager;
import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager;
import com.google.inject.Injector;

@SuppressWarnings("all")
public class CheckCfgTestUtil extends AbstractXtextTestUtil {
  private static AbstractXtextTestUtil UTIL_INSTANCE = new CheckCfgTestUtil();
  
  public static AbstractXtextTestUtil getInstance() {
    return CheckCfgTestUtil.UTIL_INSTANCE;
  }
  
  @Override
  protected Injector getInjector() {
    CheckCfgActivator _instance = CheckCfgActivator.getInstance();
    return _instance.getInjector(CheckCfgActivator.COM_AVALOQ_TOOLS_DDK_CHECKCFG_CHECKCFG);
  }
  
  @Override
  protected ITestProjectManager createTestProjectManager() {
    Injector _injector = this.getInjector();
    return new PluginTestProjectManager(_injector);
  }
}
