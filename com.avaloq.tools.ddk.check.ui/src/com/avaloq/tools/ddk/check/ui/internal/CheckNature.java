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
package com.avaloq.tools.ddk.check.ui.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;


/**
 * Defines the nature of a check project within the workspace.
 */
public class CheckNature implements IProjectNature {

  public static final String CHECK_NATURE_ID = "com.avaloq.tools.ddk.check.ui.CheckNature"; //$NON-NLS-1$

  /** The project. */
  private IProject project;

  @Override
  public void configure() throws CoreException {
    // nothing to do here.
  }

  @Override
  public void deconfigure() throws CoreException {
    // nothing to do here.
  }

  @Override
  public IProject getProject() {
    return project;
  }

  @Override
  public void setProject(final IProject project) {
    this.project = project;
  }
}
