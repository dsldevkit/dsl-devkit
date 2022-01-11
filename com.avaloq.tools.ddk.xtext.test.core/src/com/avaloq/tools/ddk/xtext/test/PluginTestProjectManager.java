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
package com.avaloq.tools.ddk.xtext.test;

import static com.google.common.collect.Lists.newArrayList;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil;
import org.eclipse.xtext.ui.util.IProjectFactoryContributor;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.junit.Assert;

import com.google.inject.Injector;


/**
 * The Test Project Manager for Plugins.
 */
public class PluginTestProjectManager extends XtextTestProjectManager {
  private static final Logger LOGGER = LogManager.getLogger(PluginTestProjectManager.class);

  public static final String DEFAULT_SOURCE_FOLDER = "src"; //$NON-NLS-1$
  public static final String DEFAULT_SOURCE_GEN_FOLDER = "src-gen"; //$NON-NLS-1$
  public static final String TEST_PROJECT_NAME = "test.project"; //$NON-NLS-1$

  // org.eclipse.osgi needed for NLS
  // org.apache.logging.log4j needed for logging in generated StandaloneSetup
  private static final List<String> REQUIRED_BUNDLES = newArrayList("org.eclipse.xtext.xbase.lib", "org.eclipse.xtend.lib", // //$NON-NLS-1$ //$NON-NLS-2$
      "org.eclipse.emf.ecore", "com.avaloq.tools.ddk.check.core", "com.avaloq.tools.ddk.check.runtime.core", "com.avaloq.tools.ddk.check.lib", // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      "org.eclipse.xtext", "org.eclipse.osgi", "org.eclipse.xtend", "org.eclipse.core.runtime", "org.eclipse.xtext.xbase", "org.apache.logging.log4j"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

  private final Injector injector;

  public PluginTestProjectManager(final Injector injector) {
    this.injector = injector;
  }

  /**
   * Creates a plugin Project.
   *
   * @param injector
   *          the injector
   * @param name
   *          the project name
   * @throws CoreException
   *           when creating the project fails
   * @return the created project
   */
  public static IProject createPluginProject(final Injector injector, final String name) throws CoreException {
    final PluginProjectFactory projectFactory = injector.getInstance(PluginProjectFactory.class);
    projectFactory.setProjectName(name);
    projectFactory.addFolders(newArrayList(DEFAULT_SOURCE_FOLDER, DEFAULT_SOURCE_GEN_FOLDER));
    projectFactory.addBuilderIds(JavaCore.BUILDER_ID, "org.eclipse.pde.ManifestBuilder", "org.eclipse.pde.SchemaBuilder", XtextProjectHelper.BUILDER_ID); //$NON-NLS-1$ //$NON-NLS-2$
    projectFactory.addProjectNatures(JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", XtextProjectHelper.NATURE_ID); //$NON-NLS-1$
    projectFactory.addRequiredBundles(REQUIRED_BUNDLES);
    projectFactory.addContributor(new IProjectFactoryContributor() {
      @Override
      public void contributeFiles(final IProject project, final IFileCreator fileWriter) {
        // Generate a plugin.xml file
        fileWriter.writeToFile("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<?eclipse version=\"3.4\"?>\n<plugin>\n</plugin>\n", "plugin.xml"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    });
    final IProject[] result = new IProject[1];
    WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
      @Override
      protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        result[0] = projectFactory.createProject(monitor, null);
        IJavaProject javaProject = JavaCore.create(result[0]);
        if (javaProject != null) {
          JavaProjectSetupUtil.addJreClasspathEntry(javaProject);
        } else {
          LOGGER.warn("Could not create a new Check project, attempting to reuse the existing project."); //$NON-NLS-1$
        }
      }
    };
    try {
      operation.run(new NullProgressMonitor());
    } catch (InvocationTargetException e) {
      return null;
    } catch (InterruptedException e) {
      return null;
    }
    return result[0];
  }

  /** {@inheritDoc} */
  @Override
  public void setup(final Iterable<? extends TestSource> initialSources) {
    try {
      IResourcesSetupUtil.reallyWaitForAutoBuild();
      createPluginProject(injector, TEST_PROJECT_NAME);
    } catch (CoreException e) {
      throw new IllegalStateException("Failed to create plugin project"); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public void teardown() {
    IResourcesSetupUtil.reallyWaitForAutoBuild();
    // Remove natures from our project first, otherwise PDE's PluginModelManager will try to update the classpath when we
    // delete things.
    WorkspaceModifyOperation removeAllNatures = new WorkspaceModifyOperation() {
      @Override
      protected void execute(final IProgressMonitor monitor) throws CoreException {
        IProject testProject = EcorePlugin.getWorkspaceRoot().getProject(TEST_PROJECT_NAME);
        if (testProject.exists()) {
          IProjectDescription projectDescription = testProject.getDescription();
          projectDescription.setNatureIds(new String[0]);
          testProject.setDescription(projectDescription, null);
        }
      }
    };
    WorkspaceModifyOperation cleanProjects = new WorkspaceModifyOperation() {
      @Override
      protected void execute(final IProgressMonitor monitor) throws CoreException {
        IResourcesSetupUtil.cleanWorkspace();
      }
    };
    try {
      removeAllNatures.run(new NullProgressMonitor());
      IResourcesSetupUtil.reallyWaitForAutoBuild();
      cleanProjects.run(new NullProgressMonitor());
      IResourcesSetupUtil.reallyWaitForAutoBuild();
    } catch (InvocationTargetException e) {
      LOGGER.error(e.getCause().getMessage());
    } catch (InterruptedException e) {
      Assert.fail("Interrupted"); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public URI createPlatformUri(final String encodedFileName) {
    return URI.createPlatformResourceURI('/' + TEST_PROJECT_NAME + "/" + DEFAULT_SOURCE_FOLDER + "/" + encodedFileName, true); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
