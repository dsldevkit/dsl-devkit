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
package com.avaloq.tools.ddk.xtext.test.export.util;

import com.avaloq.tools.ddk.xtext.export.ExportConstants;
import com.avaloq.tools.ddk.xtext.export.ui.internal.ExportActivator;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.google.inject.Injector;


public class ExportTestUtil extends AbstractXtextTestUtil {
  private static final ExportTestUtil INSTANCE = new ExportTestUtil();

  public static ExportTestUtil getInstance() {
    return INSTANCE;
  }

  @Override
  protected Injector getInjector() {
    return ExportActivator.getInstance().getInjector(ExportConstants.GRAMMAR);
  }

}
