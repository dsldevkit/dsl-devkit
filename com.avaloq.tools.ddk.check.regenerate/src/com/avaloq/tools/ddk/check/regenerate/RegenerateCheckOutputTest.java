/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.regenerate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.jobs.Job;
import org.junit.jupiter.api.Test;


/**
 * Regenerates the Check builder's output of the repository's projects in place, as a clean build of them in an Eclipse
 * workbench does. Projects with Check settings are imported, cleaned and built; build errors fail the run.
 * <p>
 * Skipped unless {@code check.regenerate.skip=false}, which {@code .github/scripts/regenerate.sh} sets.
 */
@SuppressWarnings("nls")
public class RegenerateCheckOutputTest {

  private static final String CHECK_SETTINGS = ".settings/com.avaloq.tools.ddk.check.Check.prefs";
  private static final IPath MAVEN_OUTPUT = IPath.fromOSString("target");
  private static final long IDLE_TIMEOUT_MILLIS = 600_000;
  private static final long IDLE_POLL_MILLIS = 100;

  /**
   * Cleans and builds the projects with Check settings.
   *
   * @throws CoreException
   *           if a project cannot be imported or built
   * @throws IOException
   *           if the repository cannot be listed
   * @throws InterruptedException
   *           if interrupted while waiting for the build
   */
  @Test
  public void testRegenerate() throws CoreException, IOException, InterruptedException {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    List<IProject> projects = importProjects(workspace, Path.of(System.getProperty("ddk.root")));
    assertFalse(projects.isEmpty(), "projects with Check settings");
    try {
      waitUntilIdle();
      workspace.build(IncrementalProjectBuilder.CLEAN_BUILD, null);
      waitUntilIdle();
      workspace.build(IncrementalProjectBuilder.FULL_BUILD, null);
      waitUntilIdle();
      List<String> errors = new ArrayList<>();
      for (IProject project : projects) {
        for (IMarker marker : project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)) {
          // Maven's copies of the sources under target/ are not the project's sources.
          if (marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR
              && !MAVEN_OUTPUT.isPrefixOf(marker.getResource().getProjectRelativePath())) {
            errors.add(marker.getResource().getFullPath() + ":" + marker.getAttribute(IMarker.LINE_NUMBER, -1) + ": " + marker.getAttribute(IMarker.MESSAGE, ""));
          }
        }
      }
      assertTrue(errors.isEmpty(), "build errors:\n" + String.join("\n", errors));
    } finally {
      for (IProject project : projects) {
        project.delete(false, true, null);
      }
    }
  }

  /**
   * Imports the repository's projects with Check settings into the workspace, at their location in the repository.
   *
   * @param workspace
   *          the workspace
   * @param root
   *          the repository root
   * @return the imported projects
   * @throws CoreException
   *           if a project cannot be imported
   * @throws IOException
   *           if the repository cannot be listed
   */
  private static List<IProject> importProjects(final IWorkspace workspace, final Path root) throws CoreException, IOException {
    List<IProject> projects = new ArrayList<>();
    try (Stream<Path> children = Files.list(root)) {
      for (Path dir : children.filter(d -> Files.isRegularFile(d.resolve(CHECK_SETTINGS))).sorted().toList()) {
        IProjectDescription description = workspace.loadProjectDescription(IPath.fromPath(dir.resolve(".project")));
        IProject project = workspace.getRoot().getProject(description.getName());
        project.create(description, null);
        project.open(null);
        projects.add(project);
      }
    }
    return projects;
  }

  /**
   * Waits until no jobs run, so that classpath containers are resolved and builds are finished.
   *
   * @throws InterruptedException
   *           if interrupted while waiting
   */
  private static void waitUntilIdle() throws InterruptedException {
    long deadline = System.currentTimeMillis() + IDLE_TIMEOUT_MILLIS;
    while (!Job.getJobManager().isIdle()) {
      assertTrue(System.currentTimeMillis() < deadline, "jobs still running after " + IDLE_TIMEOUT_MILLIS + " ms");
      Thread.sleep(IDLE_POLL_MILLIS);
    }
  }
}
