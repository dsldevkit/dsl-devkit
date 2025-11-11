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

package com.avaloq.tools.ddk.checkcfg.util

import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager
import com.avaloq.tools.ddk.xtext.test.ITestProjectManager
import com.avaloq.tools.ddk.checkcfg.ui.internal.CheckcfgActivator
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil

class CheckCfgTestUtil extends AbstractXtextTestUtil{


  static AbstractXtextTestUtil UTIL_INSTANCE = new CheckCfgTestUtil()
  def static AbstractXtextTestUtil getInstance(){UTIL_INSTANCE}

  override protected getInjector() {
    CheckcfgActivator.getInstance().getInjector(CheckcfgActivator.COM_AVALOQ_TOOLS_DDK_CHECKCFG_CHECKCFG)
  }

  override protected ITestProjectManager createTestProjectManager() {
    new PluginTestProjectManager(getInjector());
  }
}
