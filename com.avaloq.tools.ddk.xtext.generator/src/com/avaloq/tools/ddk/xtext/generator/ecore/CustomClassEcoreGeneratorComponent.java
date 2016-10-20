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

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;


/**
 * This code is a modified copy from MWE2 project (org.eclipse.emf.mwe2.ecore.EcoreGenerator). It will be replaced when this
 * code becomes available in the next release of Xtext.
 * <p>
 * This class internally uses the {@link CustomClassEcoreGeneratorFragment} to generate the code.
 */
public class CustomClassEcoreGeneratorComponent implements IWorkflowComponent {

  static {
    EcorePackage.eINSTANCE.getEFactoryInstance();
    GenModelPackage.eINSTANCE.getEFactoryInstance();
  }

  private boolean generateEdit;
  private boolean generateEditor;
  private boolean generateCustomClasses;

  private String srcPath;
  private String genModelPath;

  public void setGenerateEdit(final boolean generateEdit) {
    this.generateEdit = generateEdit;
  }

  public void setGenerateEditor(final boolean generateEditor) {
    this.generateEditor = generateEditor;
  }

  public void setGenerateCustomClasses(final boolean generateCustomClasses) {
    this.generateCustomClasses = generateCustomClasses;
  }

  public void setSrcPath(final String srcPath) {
    this.srcPath = srcPath;
  }

  public void setGenModel(final String path) {
    this.genModelPath = path;
  }

  /** {@inheritDoc} */
  @Override
  public void preInvoke() {
    new ResourceSetImpl().getResource(URI.createURI(genModelPath), true);
  }

  /** {@inheritDoc} */
  @Override
  public void postInvoke() {}

  /** {@inheritDoc} */
  @Override
  public void invoke(final IWorkflowContext ctx) {
    final ResourceSet resSet = new ResourceSetImpl();
    Resource resource = resSet.getResource(URI.createURI(genModelPath), true);
    final GenModel genModel = (GenModel) resource.getContents().get(0);
    genModel.setCanGenerate(true);
    genModel.reconcile();
    final CustomClassEcoreGeneratorFragment generator = new CustomClassEcoreGeneratorFragment() {
      @Override
      public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
        doGenerate(genModel);
        setBasePackage(genModel.getGenPackages().get(0).getBasePackage());
      }
    };
    generator.addJavaModelSrcDirectory(srcPath);
    generator.setGenerateCustomClasses(generateCustomClasses);
    generator.setGenerateEdit(generateEdit);
    generator.setGenerateEditor(generateEditor);
    generator.generate((Grammar) null, null);
  }

}
