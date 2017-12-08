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

package com.avaloq.tools.ddk.xtext.expression.ui.internal;

import com.google.inject.AbstractModule;
import com.google.inject.Module;


/**
 * Custom activator subclass which also manages the Guice injector created for the GenModel support.
 */
public class Activator extends ExpressionActivator {

  public static final String COM_AVALOQ_TOOLS_DDK_XTEXT_EXPRESSION_GENMODEL = "com.avaloq.tools.ddk.xtext.expression.GenModel";

  @Override
  protected Module getRuntimeModule(final String grammar) {
    if (COM_AVALOQ_TOOLS_DDK_XTEXT_EXPRESSION_GENMODEL.equals(grammar)) {
      return new GenModelRuntimeModule();
    }
    return super.getRuntimeModule(grammar);
  }

  @Override
  protected Module getUiModule(final String grammar) {
    if (COM_AVALOQ_TOOLS_DDK_XTEXT_EXPRESSION_GENMODEL.equals(grammar)) {
      return new AbstractModule() {
        @Override
        protected void configure() {
          // empty
        }
      };
    }
    return super.getUiModule(grammar);
  }
}
