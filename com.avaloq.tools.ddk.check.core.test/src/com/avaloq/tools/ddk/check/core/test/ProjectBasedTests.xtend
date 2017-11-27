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
package com.avaloq.tools.ddk.check.core.test

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider
import com.google.common.collect.Lists
import java.io.InputStream
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IMarker
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IResource
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil

@InjectWith(typeof(CheckUiInjectorProvider))
@RunWith(typeof(XtextRunner))
class ProjectBasedTests extends AbstractCheckTestCase {

  private boolean initialized;

  def List<String> getRequiredSourceFileNames() {
    // No file extension to prevent code generation in development workbench. Will get .check extension when copied into
    // runtime workspace the test runs in.
    Lists::newArrayList("bugdsl27/BugDsl27", "bugdsl281/BugDsl281")
  }

  override def getFullFileName(String fileName) {
    // Make sure it is put into the src folder even if the name contains a dash!
    return getSourceFolderPath() + getFileName(fileName);
  }

  def void initializeTestProject() {
    if (!initialized) {
      initialized = true;
      // sources are copied into the project and then built by the Xtext builder
      addSourcesToWorkspace(typeof(ProjectBasedTests), requiredSourceFileNames)

      // wait for build to finish, otherwise included catalog may not be resolvable
      IResourcesSetupUtil::waitForAutoBuild
    }
  }

  private def boolean isEmpty(IFile file) {
    val InputStream s = file.getContents();
    try {
      return s.read < 0;
    } finally {
      s.close;
    }
  }

  def private void assertGeneratedJavaCodeHasNoErrorMarkers(String fileName) {
    val IProject project = getOrCreatePluginProject()
    val IFile file = project.getFile(fileName);
    // enumerateContents(project);
    assertTrue("Generated file should exist", file.exists);
    assertFalse("Generated file should not be empty", isEmpty(file));
    assertTrue("Generated file should not have errors", file.findMaxProblemSeverity(null, true, IResource::DEPTH_INFINITE) < IMarker::SEVERITY_ERROR);

  }

  @Test
  def void testBugDsl27CanCompile() {
    initializeTestProject();

    assertGeneratedJavaCodeHasNoErrorMarkers("src-gen/bugdsl27/BugDsl27CheckImpl.java");
  }

  @Test
  def void testBugDsl281EmptyList() {
    initializeTestProject();

    assertGeneratedJavaCodeHasNoErrorMarkers("src-gen/bugdsl281/BugDsl281PreferenceInitializer.java");
  }
}
