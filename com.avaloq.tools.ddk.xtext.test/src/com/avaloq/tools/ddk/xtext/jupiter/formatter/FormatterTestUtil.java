/*******************************************************************************
 * Copyright (c) 2025 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.jupiter.formatter;

import com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguageStandaloneSetup;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.inject.Injector;


public final class FormatterTestUtil extends AbstractXtextTestUtil {
  private final Injector injector = new FormatterTestLanguageStandaloneSetup().createInjectorAndDoEMFRegistration();

  private FormatterTestUtil() {
    // private constructor
  }

  /**
   * The singleton instance.
   */
  private static final class InstanceHolder {
    // Initialize-on-demand holder pattern.
    private static final FormatterTestUtil INSTANCE = new FormatterTestUtil();

    public static FormatterTestUtil get() {
      return INSTANCE;
    }
  }

  public static FormatterTestUtil getInstance() {
    return InstanceHolder.get();
  }

  @Override
  protected Injector getInjector() {
    return injector;
  }

}
