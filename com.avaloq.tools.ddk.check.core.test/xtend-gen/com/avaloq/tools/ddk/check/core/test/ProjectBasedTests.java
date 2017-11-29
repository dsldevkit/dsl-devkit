/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.core.test;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase;
import com.google.common.collect.Lists;
import java.io.InputStream;
import java.util.List;
import junit.framework.TestCase;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(CheckUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class ProjectBasedTests extends AbstractCheckTestCase {
  private boolean initialized;
  
  public List<String> getRequiredSourceFileNames() {
    return Lists.<String>newArrayList("bugdsl27/BugDsl27", "bugdsl281/BugDsl281");
  }
  
  @Override
  public String getFullFileName(final String fileName) {
    String _sourceFolderPath = this.getSourceFolderPath();
    String _fileName = this.getFileName(fileName);
    return (_sourceFolderPath + _fileName);
  }
  
  public void initializeTestProject() {
    if ((!this.initialized)) {
      this.initialized = true;
      List<String> _requiredSourceFileNames = this.getRequiredSourceFileNames();
      this.addSourcesToWorkspace(ProjectBasedTests.class, _requiredSourceFileNames);
      IResourcesSetupUtil.waitForAutoBuild();
    }
  }
  
  private boolean isEmpty(final IFile file) {
    try {
      final InputStream s = file.getContents();
      try {
        int _read = s.read();
        return (_read < 0);
      } finally {
        s.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertGeneratedJavaCodeHasNoErrorMarkers(final String fileName) {
    try {
      final IProject project = this.getOrCreatePluginProject();
      final IFile file = project.getFile(fileName);
      boolean _exists = file.exists();
      TestCase.assertTrue("Generated file should exist", _exists);
      boolean _isEmpty = this.isEmpty(file);
      TestCase.assertFalse("Generated file should not be empty", _isEmpty);
      int _findMaxProblemSeverity = file.findMaxProblemSeverity(null, true, IResource.DEPTH_INFINITE);
      boolean _lessThan = (_findMaxProblemSeverity < IMarker.SEVERITY_ERROR);
      TestCase.assertTrue("Generated file should not have errors", _lessThan);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testBugDsl27CanCompile() {
    this.initializeTestProject();
    this.assertGeneratedJavaCodeHasNoErrorMarkers("src-gen/bugdsl27/BugDsl27CheckImpl.java");
  }
  
  @Test
  public void testBugDsl281EmptyList() {
    this.initializeTestProject();
    this.assertGeneratedJavaCodeHasNoErrorMarkers("src-gen/bugdsl281/BugDsl281PreferenceInitializer.java");
  }
}
