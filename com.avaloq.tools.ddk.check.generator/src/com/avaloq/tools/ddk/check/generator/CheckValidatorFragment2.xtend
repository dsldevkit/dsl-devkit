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

package com.avaloq.tools.ddk.check.generator

import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess

class CheckValidatorFragment2 extends AbstractXtextGeneratorFragment {

  static val RUNTIME_PLUGIN = "com.avaloq.tools.ddk.check.runtime.core"

  override generate() {
    new GuiceModuleAccess.BindingFactory().contributeTo(language.runtimeGenModule)

    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest.requiredBundles += RUNTIME_PLUGIN
    }
  }
}