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
package com.avaloq.tools.ddk.xtext.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;

import com.google.common.collect.Maps;


/**
 * Simple Test project manager for SDK tests.
 */
public class XtextTestProjectManager implements ITestProjectManager {
  protected static final String TEST_PROJECT_NAME = "TEST";
  private final Map<String, TestSource> testSources = Maps.newHashMap();
  private final Object autoBuildMutex = new Object();

  
  @Override
  public void setup(final Iterable<? extends TestSource> initialSources) {
    try {
      IProject project = IResourcesSetupUtil.createProject(TEST_PROJECT_NAME);
      IResourcesSetupUtil.addNature(project, JavaCore.NATURE_ID);
      IResourcesSetupUtil.addNature(project, XtextProjectHelper.NATURE_ID);
      for (TestSource testSource : initialSources) {
        addSourceToProject(testSource);
      }
    } catch (CoreException e) {
      throw new WrappedException(e); // NOPMD
    } catch (InvocationTargetException e) {
      throw new WrappedException(e); // NOPMD
    } catch (InterruptedException e) {
      throw new WrappedException(e); // NOPMD
    }
  }

  
  @Override
  public void teardown() {
    testSources.clear();
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME);
    if (project != null && project.exists()) {
      try {
        project.delete(true, true, new NullProgressMonitor());
      } catch (CoreException e) {
        throw new WrappedException(e);
      }
    }
  }

  
  @Override
  public void build() {
    IResourcesSetupUtil.waitForBuild();
  }

  
  @Override
  public boolean setAutobuild(final boolean autoBuildStatus) {
    synchronized (autoBuildMutex) {
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceDescription description = workspace.getDescription();
      boolean oldAutoBuildStatus = description.isAutoBuilding();
      if (oldAutoBuildStatus != autoBuildStatus) {
        description.setAutoBuilding(autoBuildStatus);
        try {
          workspace.setDescription(description);
        } catch (CoreException e) {
          throw new WrappedException("Failed to set workspace description", e);
        }
      }
      return oldAutoBuildStatus;
    }
  }

  @Override
  public Collection<TestSource> getTestSources() {
    return testSources.values();
  }

  
  @Override
  public TestSource getTestSource(final String sourceName) {
    return testSources.get(sourceName);
  }

  
  @Override
  public IFile addSourceToProject(final TestSource testSource) {
    try {
      String sourceName = testSource.getSourceFileName();
      testSources.put(sourceName, testSource);
      String filePath = createPlatformUri(sourceName).toPlatformString(true);
      IFile file = IResourcesSetupUtil.createFile(filePath, testSource.getContent());
      // TODO refactor this (should not be a side effect)
      testSource.accept(file, createTestSourceUri(sourceName));
      return file;
    } catch (CoreException e) {
      throw new WrappedException(e);
    } catch (InvocationTargetException e) {
      throw new WrappedException(e);
    } catch (InterruptedException e) {
      throw new WrappedException(e);
    }

  }

  
  @Override
  public void removeTestSource(final TestSource testSource) {
    IFile file = testSource.getiFile();
    if (file != null) {
      try {
        file.delete(true, new NullProgressMonitor());
      } catch (CoreException e) {
        throw new WrappedException(e);
      }
    }
  }

  
  @Override
  public URI createTestSourceUri(final String encodedFileName) {
    return createPlatformUri(encodedFileName);
  }

  /**
   * Create the platform {@link URI} for the encoded file name.
   *
   * @param encodedFileName
   *          a file name representing the target source. The file name must contain all information needed to create the requested URI. Usually the file type
   *          provides enough information. For the context file system, however, the target container must be encoded in the file name.
   * @return the {@link URI} for the given file name
   */
  public URI createPlatformUri(final String encodedFileName) {
    return URI.createPlatformResourceURI('/' + TEST_PROJECT_NAME + "/" + encodedFileName, true);
  }

}
