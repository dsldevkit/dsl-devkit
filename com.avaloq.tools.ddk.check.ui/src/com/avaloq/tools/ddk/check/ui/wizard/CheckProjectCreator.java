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
package com.avaloq.tools.ddk.check.ui.wizard;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.AbstractPluginProjectCreator;

import com.avaloq.tools.ddk.check.ui.internal.CheckNature;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * The Class creates a new check plugin project.
 */
public class CheckProjectCreator extends AbstractPluginProjectCreator { // extends CheckProjectCreator

  protected static final String SRC_ROOT = "src";
  private static final String SRC_GEN_ROOT = "src-gen";
  private static final String XTEND_GEN = "xtend-gen"; //$NON-NLS-1$
  private static final List<String> SRC_FOLDER_LIST = ImmutableList.of(SRC_ROOT, SRC_GEN_ROOT, XTEND_GEN);

  @Inject
  private Provider<CheckProjectFactory> projectFactoryProvider;

  @Inject
  private CheckCatalogGenerator generatorUtil;

  @Override
  protected CheckProjectFactory createProjectFactory() {
    return projectFactoryProvider.get();
  }

  @Override
  protected ProjectFactory configureProjectFactory(final ProjectFactory factory) {
    ProjectFactory result = super.configureProjectFactory(factory);
    result.addProjectNatures(CheckNature.CHECK_NATURE_ID);
    return result;
  }

  @Override
  protected CheckProjectInfo getProjectInfo() {
    return (CheckProjectInfo) super.getProjectInfo();
  }

  /** Default set of required bundles. */
  private final List<String> requiredBundles = Lists.newArrayList("org.eclipse.emf.ecore", // EStructuralFeature scoping requires this
  "org.eclipse.xtext", // required for code generation
  "com.avaloq.tools.ddk.check.runtime.core", // Check runtime
  "com.avaloq.tools.ddk.check.lib", // Check user library
  "com.avaloq.tools.targetdefinition.check.core;resolution:=optional", // Check configuration preferences, TODO should only be added for ASMD
  "org.eclipse.core.runtime", //
  "org.eclipse.xtend.lib", // required for CheckGenerator operations, e.g. 'StringConcatenation()'
  "org.eclipse.xtext.xbase.lib", // required for Xbase operations, e.g. '=='
  "com.avaloq.tools.dsl.check.lib;resolution:=optional" // utility operations (ASMD specific)
  );

  /**
   * Uses the generator utility to generate the initial Check model to ensure that it is generated
   * the same way as using the file wizard. {@inheritDoc} <br>
   * Furthermore, it generates a default quickfix provider and a .css stylesheet which formats the content
   * of ice help for Check.
   */
  @Override
  protected void enhanceProject(final IProject project, final IProgressMonitor monitor) throws CoreException {
    IPath projectPath = project.getLocation().makeAbsolute();
    generatorUtil.generateCheckFile(projectPath, getProjectInfo());
    generatorUtil.generateDefaultQuickfixProvider(projectPath, getProjectInfo());
    // Create the docs directory to avoid the check compiler from creating it as a source folder later. This would prevent help from accessing the files.
    generatorUtil.generateDocsDirectory(projectPath, getProjectInfo());
    project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
  }

  @Override
  protected List<String> getAllFolders() {
    return SRC_FOLDER_LIST;
  }

  @Override
  protected List<String> getRequiredBundles() {
    Set<String> bundles = Sets.newLinkedHashSet(requiredBundles);
    bundles.addAll(getProjectInfo().getDslDependency());
    return Lists.newArrayList(bundles);
  }

  /**
   * @return the names of the exported packages. May not be <code>null</code>
   */
  @Override
  protected List<String> getExportedPackages() {
    CheckProjectInfo projectInfo = getProjectInfo();
    return Lists.newArrayList(projectInfo.getPackageName());
  }

  @Override
  protected String getModelFolderName() {
    return SRC_ROOT;
  }

}
