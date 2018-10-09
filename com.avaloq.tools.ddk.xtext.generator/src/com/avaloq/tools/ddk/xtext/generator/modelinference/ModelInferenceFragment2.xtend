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

  /** Indicates if support for model inference is generated. */
  var boolean generateModelInference;

  /**
   * Indicates if support for model inference is generated.
   *
   * @return true if model inference support will be generated, false otherwise.
   */
  def boolean isGenerateModelInference() {
    return generateModelInference
  }

  /**
   * Sets the flag to generate support for model inference.
   * <p>
   * The default is that support for model inference is not generated.
   * </p>
   *
   * @param generateModelInference
   *          specifies if model inference support is to be generated. A value of true
   *          will cause model inference support to be generated.
   */
  def void setGenerateModelInference(boolean generateModelInference) {
    this.generateModelInference = generateModelInference
  }

  override generate() {
    if (generateModelInference) {
      new GuiceModuleAccess.BindingFactory()
      .addTypeToTypeSingleton(IInferredModelAssociations.typeRef, InferredModelAssociator.typeRef)
      .addTypeToTypeSingleton(IInferredModelAssociator.typeRef, InferredModelAssociator.typeRef)
      .addTypeToTypeSingleton(IDerivedStateComputer.typeRef, InferredModelAssociator.typeRef)
      .contributeTo(language.runtimeGenModule)
      new GuiceModuleAccess.BindingFactory()
      .addTypeToType(ReferenceQueryExecutor.typeRef, InferredModelReferenceQueryExecutor.typeRef)
      .contributeTo(language.eclipsePluginGenModule)
    }
    if (projectConfig.runtime.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += "com.avaloq.tools.ddk.xtext"
    }
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += "com.avaloq.tools.ddk.xtext.ui"
    }
  }

}
