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
package com.avaloq.tools.ddk.xtext.generator.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe2.ecore.EcoreGenerator;
import org.eclipse.emf.mwe2.runtime.Mandatory;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;


/**
 * Extends {@link EcoreGenerator} with automatic discovery of source paths to custom classes of parent languages.
 * It also checks that the path is indeed available and reports a warning otherwise. Paths may be suppressed in
 * order not to show a warning.
 */
@SuppressWarnings("nls")
public class CustomClassAwareEcoreGenerator extends EcoreGenerator implements IWorkflowComponent {
  private static final Logger LOGGER = LogManager.getLogger(EcoreGenerator.class);

  private String genModel;
  private final List<String> suppressedSrcPaths = Lists.newLinkedList();

  // CHECKSTYLE:OFF
  private boolean generateModel = true;
  private boolean generateEdit = false;
  private boolean generateEditor = false;
  // CHECKSTYLE:ON
  private ResourceSet resourceSet;

  @Override
  public void setGenerateModel(final boolean generateModel) {
    this.generateModel = generateModel;
    super.setGenerateModel(generateModel);
  }

  @Override
  public void setGenerateEdit(final boolean generateEdit) {
    this.generateEdit = generateEdit;
    super.setGenerateEdit(generateEdit);
  }

  @Override
  public void setGenerateEditor(final boolean generateEditor) {
    this.generateEditor = generateEditor;
    super.setGenerateEditor(generateEditor);
  }

  @Override
  public void setResourceSet(final ResourceSet resourceSet) {
    this.resourceSet = resourceSet;
    super.setResourceSet(resourceSet);
  }

  private ResourceSet getResourceSet() {
    return resourceSet == null ? new ResourceSetImpl() : resourceSet;
  }

  @Override
  public void invoke(final IWorkflowContext ctx) {
    ResourceSet resSet = getResourceSet();
    Resource resource = resSet.getResource(URI.createURI(genModel), true);
    final GenModel model = (GenModel) resource.getContents().get(0);
    model.setCanGenerate(true);
    model.reconcile();
    createGenModelSetup().registerGenModel(model);

    Generator generator = new Generator() {
      @Override
      public JControlModel getJControlModel() {
        if (jControlModel == null) {
          jControlModel = new JControlModel();
          jControlModel.initialize(null, options.mergeRulesURI);
        }
        return jControlModel;
      }
    };
    LOGGER.info("generating EMF code for " + this.genModel);
    generator.getAdapterFactoryDescriptorRegistry().addDescriptor(GenModelPackage.eNS_URI, new GeneratorAdapterDescriptor(getTypeMapper(), getLineDelimiter()));
    generator.setInput(model);

    if (generateModel) {
      Diagnostic diagnostic = generator.generate(model, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, new BasicMonitor());

      if (diagnostic.getSeverity() != Diagnostic.OK) {
        LOGGER.info(diagnostic);
      }
    }

    if (generateEdit) {
      Diagnostic editDiag = generator.generate(model, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, new BasicMonitor());
      if (editDiag.getSeverity() != Diagnostic.OK) {
        LOGGER.info(editDiag);
      }
    }

    if (generateEditor) {
      Diagnostic editorDiag = generator.generate(model, GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, new BasicMonitor());
      if (editorDiag.getSeverity() != Diagnostic.OK) {
        LOGGER.info(editorDiag);
      }
    }
  }

  @Override
  @Mandatory
  public void setGenModel(final String genModel) {
    super.setGenModel(genModel);
    this.genModel = genModel;
  }

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

  @Override
  @Deprecated
  public void addSrcPath(final String srcPath) {
    throw new AssertionFailedException("srcPath should not be used in CustomClassAwareEcoreGenerator");
  }

  /**
   * Registers the given source-paths in the generator.
   */
  @Override
  public void preInvoke() {
    ResourceSet resSet = new ResourceSetImpl();
    Resource resource = resSet.getResource(URI.createURI(genModel), true);
    for (EObject obj : resource.getContents()) {
      if (obj instanceof GenModel) {
        GenModel model = (GenModel) obj;
        addSourcePathForPlugin(model.getModelPluginID());
        for (GenPackage usedPackage : model.getUsedGenPackages()) {
          addSourcePathForPlugin(usedPackage.getGenModel().getModelPluginID());
        }
      }
    }
    LOGGER.info("Registered source path to discover custom classes: " + Joiner.on(", ").join(this.srcPaths));
  }

  /**
   * Adds the source path model.
   *
   * @param pluginId
   *          the plugin id
   */
  private void addSourcePathForPlugin(final String pluginId) {
    final String path = "../" + pluginId + "/src";
    if (suppressedSrcPaths.contains(path)) {
      // Workflow has explicitly instructed us not to look for custom classes in this folder
      return;
    }
    URI createURI = URI.createURI(path);
    if (!URIConverter.INSTANCE.exists(createURI, null)) {
      LOGGER.warn("Cannot access path '" + path
          + "'. Make sure to checkout all parent languages before running the workflow. Otherwise custom implementations (*ImplCustom) will not be considered.");
    }
    this.srcPaths.add(path);
  }
}
