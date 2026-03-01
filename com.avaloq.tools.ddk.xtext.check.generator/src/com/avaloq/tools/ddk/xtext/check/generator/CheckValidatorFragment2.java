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

package com.avaloq.tools.ddk.xtext.check.generator;

import com.avaloq.tools.ddk.check.runtime.validation.AbstractCheckValidator;
import com.avaloq.tools.ddk.check.runtime.validation.DefaultCheckValidator;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

public class CheckValidatorFragment2 extends AbstractXtextGeneratorFragment {

  private static final String RUNTIME_PLUGIN = "com.avaloq.tools.ddk.check.runtime.core";

  @Override
  public void generate() {
    new GuiceModuleAccess.BindingFactory().addTypeToTypeEagerSingleton(TypeReference.typeRef(AbstractCheckValidator.class),
        TypeReference.typeRef(DefaultCheckValidator.class)).contributeTo(getLanguage().getRuntimeGenModule());

    if (getProjectConfig().getRuntime().getManifest() != null) {
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add(RUNTIME_PLUGIN);
    }
  }
}
