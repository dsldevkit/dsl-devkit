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

package com.avaloq.tools.ddk.xtext.generator.modelinference

import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations
import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociator
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator
import com.avaloq.tools.ddk.xtext.ui.editor.findrefs.InferredModelReferenceQueryExecutor
import org.eclipse.xtext.resource.IDerivedStateComputer
import org.eclipse.xtext.ui.editor.findrefs.ReferenceQueryExecutor
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess

import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*

class ModelInferenceFragment2 extends AbstractXtextGeneratorFragment {

  override generate() {
    new GuiceModuleAccess.BindingFactory()
    .addTypeToTypeSingleton(IInferredModelAssociations.typeRef, InferredModelAssociator.typeRef)
    .addTypeToTypeSingleton(IInferredModelAssociator.typeRef, InferredModelAssociator.typeRef)
    .addTypeToTypeSingleton(IDerivedStateComputer.typeRef, InferredModelAssociator.typeRef)
    .contributeTo(language.runtimeGenModule)
    new GuiceModuleAccess.BindingFactory()
    .addTypeToType(ReferenceQueryExecutor.typeRef, InferredModelReferenceQueryExecutor.typeRef)
    .contributeTo(language.eclipsePluginGenModule)


    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest.requiredBundles += "com.avaloq.tools.ddk.xtext"
    }
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += "com.avaloq.tools.ddk.xtext.ui"
    }
  }

}
