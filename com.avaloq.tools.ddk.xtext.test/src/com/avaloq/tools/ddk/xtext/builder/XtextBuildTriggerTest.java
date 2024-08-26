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
package com.avaloq.tools.ddk.xtext.builder;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.xtext.builder.impl.BuildScheduler;
import org.eclipse.xtext.builder.impl.IBuildFlag;
import org.eclipse.xtext.testing.AbstractXtextTests;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import com.avaloq.tools.ddk.xtext.builder.layered.XtextBuildTrigger;
import com.google.inject.AbstractModule;


@SuppressWarnings({"restriction", "deprecation", "removal"})
public class XtextBuildTriggerTest extends AbstractXtextTests {

  private IWorkspace workspace;
  private BuildScheduler scheduler;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    workspace = mock(IWorkspace.class);
    scheduler = mock(BuildScheduler.class);
    with(new AbstractModule() {
      @Override
      protected void configure() {
        bind(BuildScheduler.class).toInstance(scheduler);
        bind(IWorkspace.class).toInstance(workspace);
      }
    });
  }

  @Test
  public void testTriggerRespectsAutoBuilding() {
    XtextBuildTrigger buildTrigger = get(XtextBuildTrigger.class);

    // auto-build disabled
    when(workspace.isAutoBuilding()).thenReturn(false);
    buildTrigger.scheduleFullBuild();
    verify(scheduler, never()).scheduleBuildIfNecessary(ArgumentMatchers.<Iterable<IProject>> any(), ArgumentMatchers.<IBuildFlag> any());

    reset(workspace);
    reset(scheduler);

    // auto-build enabled
    IWorkspaceRoot root = mock(IWorkspaceRoot.class);
    IProject[] projects = new IProject[0];
    when(workspace.isAutoBuilding()).thenReturn(true);
    when(workspace.getRoot()).thenReturn(root);
    when(root.getProjects()).thenReturn(projects);
    buildTrigger.scheduleFullBuild();
    verify(scheduler).scheduleBuildIfNecessary(eq(Arrays.asList(projects)), ArgumentMatchers.<IBuildFlag[]> any());
  }
}
