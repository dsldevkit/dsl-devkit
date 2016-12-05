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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.google.inject.Inject;


/**
 * The Class CheckCatalogCreator creates a new Check catalog in an existing Check plugin project.
 */
public class CheckCatalogCreator extends WorkspaceModifyOperation implements ICheckCatalogCreator {

  /** The result. */
  private IFile result;

  /** The project info. */
  private CheckProjectInfo projectInfo;

  /** The generator util. */
  @Inject
  private CheckCatalogGenerator generatorUtil;

  /** {@inheritDoc} */
  public IFile getResult() {
    return result;
  }

  /**
   * Sets the result.
   * 
   * @param result
   *          the new result
   */
  public void setResult(final IFile result) {
    this.result = result;
  }

  /** {@inheritDoc} */
  public void setProjectInfo(final IProjectInfo projectInfo) {
    this.projectInfo = (CheckProjectInfo) projectInfo;
  }

  /**
   * Gets the project info.
   * 
   * @return the project info
   */
  public CheckProjectInfo getProjectInfo() {
    return this.projectInfo;
  }

  /** {@inheritDoc} */
  @Override
  protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
    SubMonitor subMonitor = SubMonitor.convert(monitor, "create new Catalog:" + getProjectInfo().getCatalogName(), 2);

    IFolder outputFolder = (IFolder) getProjectInfo().getPackageFragment().getResource();
    IProject project = outputFolder.getProject();
    IPath path = project.getLocation().makeAbsolute();
    try {
      generatorUtil.generateCheckFile(path, getProjectInfo());
      generatorUtil.generateDefaultQuickfixProvider(path, getProjectInfo());
      project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
    } finally {
      setResult(outputFolder.getFile(getProjectInfo().getCatalogName() + '.' + CheckConstants.FILE_EXTENSION));
      subMonitor.done();
    }
  }

}

