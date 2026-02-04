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
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.inject.Injector;


public final class ExportTestUtil extends AbstractXtextTestUtil {
  private ExportTestUtil() {
    // private constructor
  }

  /**
   * The singleton instance.
   */
  private static final class InstanceHolder {
    // Initialize-on-demand holder pattern.
    private static final ExportTestUtil INSTANCE = new ExportTestUtil();

    static ExportTestUtil get() {
      return INSTANCE;
    }
  }

  public static ExportTestUtil getInstance() {
    return InstanceHolder.get();
  }

  @Override
  protected Injector getInjector() {
    return ExportActivator.getInstance().getInjector(ExportConstants.GRAMMAR);
  }

}
