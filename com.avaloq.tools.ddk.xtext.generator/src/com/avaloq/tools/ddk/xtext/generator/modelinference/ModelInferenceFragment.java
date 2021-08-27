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
package com.avaloq.tools.ddk.xtext.generator.modelinference;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.DefaultGeneratorFragment;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceQueryExecutor;

import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations;
import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociator;
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.ui.editor.findrefs.InferredModelReferenceQueryExecutor;


/**
 * This fragment generates bindings for languages that implement model inference.
 */
@SuppressWarnings({"restriction", "deprecation"})
public class ModelInferenceFragment extends DefaultGeneratorFragment {

  /** Indicates if support for model inference is generated. */
  private boolean generateModelInference;

  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    BindFactory factory = new BindFactory();
    if (generateModelInference) {
      factory.addTypeToTypeSingleton(IInferredModelAssociations.class.getName(), InferredModelAssociator.class.getName());
      factory.addTypeToTypeSingleton(IInferredModelAssociator.class.getName(), InferredModelAssociator.class.getName());
      factory.addTypeToTypeSingleton(IDerivedStateComputer.class.getName(), InferredModelAssociator.class.getName());
    }
    return factory.getBindings();
  }

  @Override
  public Set<Binding> getGuiceBindingsUi(final Grammar grammar) {
    BindFactory factory = new BindFactory();
    if (generateModelInference) {
      factory.addTypeToType(ReferenceQueryExecutor.class.getName(), InferredModelReferenceQueryExecutor.class.getName());
    }
    return factory.getBindings();
  }

  @Override
  public String[] getRequiredBundlesRt(final Grammar grammar) {
    return new String[] {"com.avaloq.tools.ddk.xtext"};
  }

  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    return new String[] {"com.avaloq.tools.ddk.xtext.ui"};
  }

  /**
   * Indicates if support for model inference is generated.
   *
   * @return true if model inference support will be generated, false otherwise.
   */
  public boolean isGenerateModelInference() {
    return generateModelInference;
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
  public void setGenerateModelInference(final boolean generateModelInference) {
    this.generateModelInference = generateModelInference;
  }

}
