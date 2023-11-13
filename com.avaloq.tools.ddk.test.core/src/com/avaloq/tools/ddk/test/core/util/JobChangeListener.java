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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 * A job manager for querying information about scheduled {@link org.eclipse.core.runtime.jobs.Job}s and their completion.
 */
public class JobChangeListener extends JobChangeAdapter {
  public static final String JOB_REFRESHING_WORKSPACE = "Refreshing workspace";
  public static final String JOB_REFRESHING_CONTEXT = "Refreshing Context";
  public static final String JOB_BUILDING_WORKSPACE = "Building";
  public static final String JOB_UPDATING_PROJECTS = "Updating projects";
  public static final String JOB_LOAD_PLATFORM = "Load Platform";
  public static final String JOB_INITIALIZING_JAVA_TOOLING = "Initializing Java Tooling";
  public static final String JOB_BACKGROUND_BUILD = "Running Build in the Background";
  private final Map<String, IStatus> completedJobs = Maps.newHashMap();
  private final Set<String> jobNames = Sets.newHashSet();

  /**
   * Return whether the specified job is run.
   *
   * @param jobName
   *          name of the job, must not be {@code null}
   * @return {@code true} if a job with the given name has been completed, {@code false} otherwise
   */
  public synchronized boolean isJobDone(final String jobName) {
    return completedJobs.containsKey(jobName);
  }

  /**
   * Return whether the specified job completed successfully.
   *
   * @param jobName
   *          name of the job, must not be {@code null}
   * @return {@code true} if a job with the given name has been completed successfully, {@code false} otherwise
   */
  public synchronized boolean isJobSuccessful(final String jobName) {
    IStatus status = completedJobs.get(jobName);
    if (status != null) {
      return status.isOK();
    }
    return false;
  }

  /**
   * Return the completion result of the job.
   *
   * @param jobName
   *          name of the job, must not be {@code null}
   * @return the status of the job, or {@code null} if it didn't complete yet.
   */
  public synchronized IStatus getJobResult(final String jobName) {
    return completedJobs.get(jobName);
  }

  /**
   * Return whether the job with the specified prefix in its name is run.
   *
   * @param jobNamePrefix
   *          prefix of the name of the job
   * @return {@code true} if a job with the given name has been completed, {@code false} otherwise
   */
  public synchronized boolean isJobWithPrefixDone(final String jobNamePrefix) {
    for (String name : completedJobs.keySet()) {
      if (name.startsWith(jobNamePrefix)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Reset the set of monitored jobs.
   */
  public synchronized void reset() {
    completedJobs.clear();
    jobNames.clear();
  }

  @Override
  public synchronized void done(final IJobChangeEvent event) {
    final String jobName = event.getJob().getName();
    jobNames.remove(jobName);
    // remember completed jobs
    completedJobs.put(jobName, event.getResult());
  }

  @Override
  public synchronized void scheduled(final IJobChangeEvent event) {
    jobNames.add(event.getJob().getName());
  }

  /**
   * Checks if there is a {@link org.eclipse.core.runtime.jobs.Job} with the given name that was scheduled since this manager was last reset (or created) and
   * not yet completed.
   * <p>
   * <em>Note</em>: If none was found, tries to find a job which name starts with the given name.
   * </p>
   * <p>
   * <em>Note</em>: The check is done case sensitively.
   * </p>
   *
   * @param name
   *          the name of the job, must not be {@code null}
   * @return {@code true} if such a job was found, {@code false} otherwise
   */
  public synchronized boolean hasJob(final String name) {
    Assert.isNotNull(name, "name");
    if (jobNames.contains(name)) {
      return true;
    }
    for (final String jobName : jobNames) {
      if (jobName.startsWith(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Verifies that a job with the given name exists in the system.
   * <p>
   * <em>Note</em>: The check is done case insensitively.
   * </p>
   *
   * @param jobName
   *          the name of the job, must not be {@code null}
   * @return {@code true} if a job with the given name currently exists, {@code false} otherwise
   */
  public boolean jobExists(final String jobName) {
    Job.getJobManager().wakeUp(null);
    for (final Job job : Job.getJobManager().find(null)) {
      if (job.getName().equalsIgnoreCase(jobName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Verifies that none of the specified jobs exist in the system.
   * <p>
   * <em>Note</em>: The check is done case sensitively.
   * </p>
   *
   * @param jobs
   *          the jobs to check, must not be {@code null}
   * @return {code true} if none of the specified jobs exist, {@code false} otherwise
   */
  public boolean hasNoJobs(final String... jobs) {
    Job.getJobManager().wakeUp(null);
    final Set<String> existingJobs = Sets.newHashSet();
    for (final Job job : Job.getJobManager().find(null)) {
      existingJobs.add(job.getName());
    }
    for (final String jobName : jobs) {
      if (existingJobs.contains(jobName)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if there are scheduled jobs (other than the exceptions).
   * <p>
   * <em>Note</em>: The check is done case sensitively.
   * </p>
   *
   * @param exceptions
   *          the names of jobs that shall be ignored, must not be {@code null}
   * @return whether there are scheduled jobs
   */
  public boolean hasScheduledJobs(final Collection<String> exceptions) {
    for (final Job job : Job.getJobManager().find(null)) {
      if (!exceptions.contains(job.getName())) {
        return true;
      }
    }
    return false;
  }
}
