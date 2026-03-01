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
package com.avaloq.tools.ddk.check.core.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.google.common.collect.Lists;


@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class ProjectBasedTests extends AbstractCheckTestCase {

  private boolean initialized;

  public List<String> getRequiredSourceFileNames() {
    // No file extension to prevent code generation in development workbench. Will get .check extension when copied into
    // runtime workspace the test runs in.
    return Lists.newArrayList("bugdsl27/BugDsl27", "bugdsl281/BugDsl281");
  }

  @Override
  protected String getFullFileName(final String fileName) {
    // Make sure it is put into the src folder even if the name contains a dash!
    return getSourceFolderPath() + getFileName(fileName);
  }

  public void initializeTestProject() {
    if (!initialized) {
      initialized = true;
      // sources are copied into the project and then built by the Xtext builder
      addSourcesToWorkspace(ProjectBasedTests.class, getRequiredSourceFileNames());

      // wait for build to finish, otherwise included catalog may not be resolvable
      IResourcesSetupUtil.waitForBuild();
    }
  }

  private boolean isEmpty(final IFile file) throws CoreException {
    final InputStream s = file.getContents();
    try {
      return s.read() < 0;
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      try {
        s.close();
      } catch (Exception e) {
        // ignore
      }
    }
  }

  private void assertGeneratedJavaCodeHasNoErrorMarkers(final String fileName) throws CoreException {
    final IProject project = getOrCreatePluginProject();
    final IFile file = project.getFile(fileName);
    // enumerateContents(project);
    assertTrue(file.exists(), "Generated file should exist");
    assertFalse(isEmpty(file), "Generated file should not be empty");
    assertTrue(file.findMaxProblemSeverity(null, true, IResource.DEPTH_INFINITE) < IMarker.SEVERITY_ERROR, "Generated file should not have errors");
  }

  @Test
  public void testBugDsl27CanCompile() throws CoreException {
    initializeTestProject();

    assertGeneratedJavaCodeHasNoErrorMarkers("src-gen/bugdsl27/BugDsl27CheckImpl.java");
  }

  @Test
  public void testBugDsl281EmptyList() throws CoreException {
    initializeTestProject();

    assertGeneratedJavaCodeHasNoErrorMarkers("src-gen/bugdsl281/BugDsl281PreferenceInitializer.java");
  }
}
