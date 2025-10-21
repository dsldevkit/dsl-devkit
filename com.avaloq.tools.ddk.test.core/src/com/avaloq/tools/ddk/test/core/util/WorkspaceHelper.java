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

package com.avaloq.tools.ddk.test.core.util;

import org.eclipse.core.runtime.jobs.Job;


/** Shared workpace-state-related logic. */
public class WorkspaceHelper {

  private static final int WORKSPACE_BUILD_TRY_COUNT = 4;
  private int workspaceBuildTries;

  /** Listener for events regarding eclipse jobs. */
  private final JobChangeListener jobChangeListener;

  public WorkspaceHelper() {
    jobChangeListener = new JobChangeListener();
    Job.getJobManager().addJobChangeListener(jobChangeListener);
  }

  /**
   * Returns the {@link JobChangeListener}.
   *
   * @return the {@link JobChangeListener}, never {@code null}
   */
  public JobChangeListener getJobChangeListener() {
    return jobChangeListener;
  }

  /**
   * Whether the workspace is built or ready i.e. that no
   * background job is running which:
   * <li>loads the platform</li>
   * <li>updates the projects</li>
   * <li>builds the workspace</li>
   * <li>initializes java tooling</li>
   * The method is designed to be like isWorkspaceBuilt() for the first number of times it is called,
   * and afterwards changes its behavior to be like isWorkspaceReady().
   *
   * @return {@code true} if the workspace is ready, else {@code false}
   */
  public boolean isWorkspaceBuiltOrReady() {
    if (workspaceBuildTries < WORKSPACE_BUILD_TRY_COUNT) {
      workspaceBuildTries++;
      return isWorkspaceBuilt();
    }
    return isWorkspaceReady();
  }

  /**
   * Whether the workspace has been built and is ready.
   *
   * @return {@code true} if the workspace has been built, {@code false} otherwise
   */
  public boolean isWorkspaceBuilt() {
    boolean result = getJobChangeListener().isJobDone(JobChangeListener.JOB_BUILDING_WORKSPACE);
    result &= !getJobChangeListener().hasJob(JobChangeListener.JOB_BACKGROUND_BUILD);
    result &= isWorkspaceReady();
    return result;
  }

  /**
   * Whether the workspace is ready i.e. that no
   * background job is running which:
   * <li>loads the platform</li>
   * <li>updates the projects</li>
   * <li>builds the workspace</li>
   * <li>initializes java tooling</li>
   *
   * @return {@code true} if the workspace is ready, else {@code false}
   */
  public boolean isWorkspaceReady() {
    boolean result = getJobChangeListener().hasNoJobs(JobChangeListener.JOB_LOAD_PLATFORM, JobChangeListener.JOB_UPDATING_PROJECTS, JobChangeListener.JOB_BACKGROUND_BUILD, JobChangeListener.JOB_REFRESHING_WORKSPACE, JobChangeListener.JOB_INITIALIZING_JAVA_TOOLING);
    result &= !getJobChangeListener().hasJob(JobChangeListener.JOB_REFRESHING_CONTEXT);
    return result && !getJobChangeListener().jobExists("Refresh DBObject"); //$NON-NLS-1$
  }

}
