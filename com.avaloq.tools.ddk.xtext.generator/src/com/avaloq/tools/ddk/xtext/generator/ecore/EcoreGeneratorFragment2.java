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

package com.avaloq.tools.ddk.xtext.generator.ecore;

import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;

import com.avaloq.tools.ddk.xtext.generator.util.CustomClassAwareEcoreGenerator;
import com.google.common.collect.Lists;


/**
 * A copied and extended {@link org.eclipse.emf.mwe2.ecore.EcoreGenerator} with automatic discovery of source paths 
 * to custom classes of parent languages to be called as an fragment.
 */
public class EcoreGeneratorFragment2 extends AbstractXtextGeneratorFragment {

  private String genModel;
  private final List<String> suppressedSrcPaths = Lists.newLinkedList();

  private Boolean generateModel;
  private Boolean generateEdit;
  private Boolean generateEditor;
  private Boolean generateCustomClasses;

  private ResourceSet resourceSet;

  /**
   * Adds the suppress source path in order not to consider it when searching for *ImplCustom classes and hence not to report a warning if this path is
   * discovered as required.
   *
   * @param srcPath
   *          the path to custom classes
   */
  public void addSuppressSrcPath(final String srcPath) {
    suppressedSrcPaths.add(srcPath);
  }

  public void setGenerateModel(final boolean generateModel) {
    this.generateModel = generateModel;
  }

  public void setGenerateEdit(final boolean generateEdit) {
    this.generateEdit = generateEdit;
  }

  public void setGenerateEditor(final boolean generateEditor) {
    this.generateEditor = generateEditor;
  }

  public void setResourceSet(final ResourceSet resourceSet) {
    this.resourceSet = resourceSet;
  }

  public void setGenerateCustomClasses(final boolean generateCustomClasses) {
    this.generateCustomClasses = generateCustomClasses;
  }

  public void setGenModel(final String genModel) {
    this.genModel = genModel;
  }

  @Override
  public void generate() {
    CustomClassAwareEcoreGenerator ecoreGenerator = new CustomClassAwareEcoreGenerator();
    if (this.generateCustomClasses != null) {
      ecoreGenerator.setGenerateCustomClasses(this.generateCustomClasses);
    }
    if (this.generateModel != null) {
      ecoreGenerator.setGenerateModel(this.generateModel);
    }
    if (this.generateEdit != null) {
      ecoreGenerator.setGenerateEdit(this.generateEdit);
    }
    if (this.generateEditor != null) {
      ecoreGenerator.setGenerateEditor(this.generateEditor);
    }
    for (String path : this.suppressedSrcPaths) {
      ecoreGenerator.addSuppressSrcPath(path);
    }
    ecoreGenerator.setResourceSet(this.resourceSet);
    ecoreGenerator.setGenModel(this.genModel);
    ecoreGenerator.preInvoke();
    ecoreGenerator.invoke(null);
    ecoreGenerator.postInvoke();
  }
}
