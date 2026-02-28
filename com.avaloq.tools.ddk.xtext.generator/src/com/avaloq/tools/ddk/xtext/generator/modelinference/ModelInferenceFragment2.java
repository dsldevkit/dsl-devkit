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

package com.avaloq.tools.ddk.xtext.generator.modelinference;

import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations;
import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociator;
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.ui.editor.findrefs.InferredModelReferenceQueryExecutor;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceQueryExecutor;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

public class ModelInferenceFragment2 extends AbstractXtextGeneratorFragment {

  @Override
  public void generate() {
    new GuiceModuleAccess.BindingFactory()
        .addTypeToTypeSingleton(TypeReference.typeRef(IInferredModelAssociations.class), TypeReference.typeRef(InferredModelAssociator.class))
        .addTypeToTypeSingleton(TypeReference.typeRef(IInferredModelAssociator.class), TypeReference.typeRef(InferredModelAssociator.class))
        .addTypeToTypeSingleton(TypeReference.typeRef(IDerivedStateComputer.class), TypeReference.typeRef(InferredModelAssociator.class))
        .contributeTo(getLanguage().getRuntimeGenModule());
    new GuiceModuleAccess.BindingFactory()
        .addTypeToType(TypeReference.typeRef(ReferenceQueryExecutor.class), TypeReference.typeRef(InferredModelReferenceQueryExecutor.class))
        .contributeTo(getLanguage().getEclipsePluginGenModule());

    if (getProjectConfig().getRuntime().getManifest() != null) {
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add("com.avaloq.tools.ddk.xtext");
    }
    if (getProjectConfig().getEclipsePlugin().getManifest() != null) {
      getProjectConfig().getEclipsePlugin().getManifest().getRequiredBundles().add("com.avaloq.tools.ddk.xtext.ui");
    }
    if (getProjectConfig().getGenericIde().getManifest() != null) {
      getProjectConfig().getGenericIde().getManifest().getRequiredBundles().add("com.avaloq.tools.ddk.xtext.ide");
    }
  }

}
