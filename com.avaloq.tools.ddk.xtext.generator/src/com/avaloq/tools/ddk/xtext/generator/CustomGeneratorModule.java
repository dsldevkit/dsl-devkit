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

import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;


/**
 * Custom generator module for controlling what is injected into the mwe2 workflow.
 */
public class CustomGeneratorModule extends DefaultGeneratorModule {
  /**
   * Use a custom generator naming class.
   *
   * @return the custom generator naming class.
   */
  public Class<? extends XtextGeneratorNaming> bindXtextGeneratorNaming() {
    return CustomGeneratorNaming.class;
  }

}
