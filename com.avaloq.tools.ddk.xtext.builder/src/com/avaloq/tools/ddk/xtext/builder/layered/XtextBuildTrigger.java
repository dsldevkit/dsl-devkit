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
package com.avaloq.tools.ddk.xtext.builder.layered;

import java.util.Arrays;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.xtext.builder.impl.BuildScheduler;

import com.google.inject.Inject;


/**
 * Build trigger that actually does trigger a full build. Assumes we have a {@link IWorkspace} and a {@link BuildScheduler}.
 */
@SuppressWarnings({"deprecation", "removal"})
public class XtextBuildTrigger implements IXtextBuildTrigger {

  @Inject
  private BuildScheduler buildScheduler;

  @Inject
  private IWorkspace workspace;

  @Override
  public void scheduleFullBuild() {
    if (workspace != null && workspace.isAutoBuilding()) {
      buildScheduler.scheduleBuildIfNecessary(Arrays.asList(workspace.getRoot().getProjects()));
    }
  }

}
