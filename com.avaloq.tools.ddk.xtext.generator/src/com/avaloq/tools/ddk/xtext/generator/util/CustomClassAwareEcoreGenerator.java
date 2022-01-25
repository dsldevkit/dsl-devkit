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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe2.ecore.EcoreGenerator;
import org.eclipse.emf.mwe2.runtime.Mandatory;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;


/**
 * Extends {@link EcoreGenerator} with automatic discovery of source paths to custom classes of parent languages.
 * It also checks that the path is indeed available and reports a warning otherwise. Paths may be suppressed in
 * order not to show a warning.
 */
public class CustomClassAwareEcoreGenerator extends EcoreGenerator implements IWorkflowComponent {
  private static final Logger LOGGER = LogManager.getLogger(EcoreGenerator.class);

  private String genModel;
  private final List<String> suppressedSrcPaths = Lists.newLinkedList();

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
