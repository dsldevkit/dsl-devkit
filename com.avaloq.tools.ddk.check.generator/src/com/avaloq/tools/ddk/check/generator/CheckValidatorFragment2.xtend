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

package com.avaloq.tools.ddk.check.generator

import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess
import com.avaloq.tools.ddk.check.runtime.validation.AbstractCheckValidator
import com.avaloq.tools.ddk.check.runtime.validation.DefaultCheckValidator

class CheckValidatorFragment2 extends AbstractXtextGeneratorFragment {

  static val RUNTIME_PLUGIN = "com.avaloq.tools.ddk.check.runtime.core"

  override generate() {
    new GuiceModuleAccess.BindingFactory().addTypeToTypeEagerSingleton(AbstractCheckValidator.typeRef,
      DefaultCheckValidator.typeRef).contributeTo(language.runtimeGenModule)

    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest.requiredBundles += RUNTIME_PLUGIN
    }
  }
}