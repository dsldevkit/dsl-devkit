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

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


/**
 * Class which can be used to assert that certain Eclipse jobs finished executing within a given time frame.
 * <p>
 * To use as a synchronization point to wait for <em>already scheduled</em> use as follows:
 *
 * <pre>
 * // run code which schedules some job
 * ...
 *
 * JobMatcher.registerNewJobByFamilyMatcher(myFamily).waitForExistingJobs();
 * </pre>
 *
 * The call to {@link #waitForExistingJobs()} will block (with a timeout) until the matching jobs have finished running.
 * <p>
 * If you instead want to assert that new jobs were scheduled and finished <em>later on</em> use as follows:
 *
 * <pre>
 * JobMatcher matcher = JobMatcher.registerNewJobByNameMatcher(myName);
 *
 * // run code which schedules expected job
 * ...
 *
 * matcher.assertNewJobsFinished();
 * </pre>
 *
 * Again the call to {@link #assertNewJobsFinished()} will block (with a timeout) until the scheduled jobs have finished running.
 * <p>
 * It doesn't really make sense to call <em>both</em> {@link #waitForExistingJobs()} and {@link #assertNewJobsFinished()} on a job matcher.
 */
@SuppressWarnings("nls")
public class JobMatcher extends JobChangeAdapter {

  public static final long DEFAULT_JOB_WAIT_TIMEOUT = TimeUnit.SECONDS.toMillis(30);

  /**
   * Class used to find and match specific waiting, executing and sleeping jobs.
   */
  // CHECKSTYLE:OFF
  public abstract static class JobFinder implements Predicate<Job> {
    // CHECKSTYLE:ON

    /**
     * Returns a new job finder to match all jobs of a given family.
     *
     * @param family
     *          family to match
     * @return job finder
     */
    public static JobFinder forFamily(final Object family) {
      return new JobFinder() {
        @Override
        public boolean apply(final Job input) {
          return input.belongsTo(family);
        }

        @Override
        public List<Job> find() {
          return ImmutableList.copyOf(Job.getJobManager().find(family));
        }

        @Override
        public String toString() {
          return "Predicate(family=" + family + ")";
        }
      };
    }

    /**
     * Returns a new job finder to match jobs by name.
     *
     * @param name
     *          job name to match
     * @return job finder
     */
    public static JobFinder forName(final String name) {
      return new JobFinder() {
        @Override
        public boolean apply(final Job input) {
          return name.equals(input.getName());
        }

        @Override
        public List<Job> find() {
          List<Job> result = Lists.newArrayListWithExpectedSize(1);
          for (Job job : Job.getJobManager().find(null)) {
            if (apply(job)) {
              result.add(job);
            }
          }
          return result;
        }

        @Override
        public String toString() {
          return "Predicate(name=" + name + ")";
        }
      };
    }

    /**
     * Finds any waiting, executing and sleeping jobs matching this predicate.
     *
     * @return all matching jobs, never {@code null}
     */
    public abstract List<Job> find();

  }

  private final JobFinder finder;
  private final long waitTimeout;

  private long timeout;
  private List<Job> existingJobs;
  private List<Job> newJobs;
  private List<Job> finishedJobs;
  private BlockingQueue<Job> jobQueue;

  public JobMatcher(final JobFinder finder) {
    this(finder, DEFAULT_JOB_WAIT_TIMEOUT);
  }

  public JobMatcher(final JobFinder finder, final long waitTimeout) {
    this.finder = finder;
    this.waitTimeout = waitTimeout;
  }

  /**
   * Returns a matcher to match finishing jobs by name. The matcher is automatically registered and expects the jobs to finish within 30 seconds.
   *
   * @param name
   *          job name to match
   * @return matcher to match jobs by name
   */
  public static JobMatcher registerNewJobByNameMatcher(final String name) {
    JobMatcher matcher = new JobMatcher(JobFinder.forName(name));
    matcher.register();
    return matcher;
  }

  /**
   * Returns a matcher to match finishing jobs by family. The matcher is automatically registered and expects the jobs to finish within 30 seconds.
   *
   * @param family
   *          job family to match
   * @return matcher to match jobs by family
   */
  public static JobMatcher registerNewJobByFamilyMatcher(final Object family) {
    JobMatcher matcher = new JobMatcher(JobFinder.forFamily(family));
    matcher.register();
    return matcher;
  }

  /**
   * Registers the matcher as a job change listener to start matching finishing jobs.
   *
   * @return the list of matching existing jobs
   */
  public final synchronized List<Job> register() {
    newJobs = Collections.synchronizedList(Lists.<Job> newArrayList());
    jobQueue = new LinkedBlockingQueue<Job>();
    Job.getJobManager().addJobChangeListener(this);

    // save list of existing jobs *after* adding job listener (in case jobs finish in the mean time)
    existingJobs = ImmutableList.copyOf(finder.find());
    finishedJobs = Collections.synchronizedList(Lists.<Job> newArrayList());
    timeout = System.currentTimeMillis() + waitTimeout;

    return ImmutableList.copyOf(existingJobs);
  }

  /**
   * Deregisters the matcher as a job change listener.
   */
  public final void deregister() {
    Job.getJobManager().removeJobChangeListener(this);
  }

  /**
   * Asserts that the number of jobs that have been scheduled after {@link #register() registering} is equal to the passed argument.
   * <p>
   * <b>Note: </b> In contrast to {@link #assertNewJobsFinished()} and {@link #waitForExistingJobs()}, {@link #deregister()} is not called as part of this
   * method.
   * It is the responsibility of the caller to make sure that {@link #deregister()} will eventually be called.
   *
   * @param expected
   *          the expected number of jobs
   */
  public final void assertNumberOfNewJobs(final int expected) {
    Assert.assertEquals("Wrong number of jobs were scheduled", expected, newJobs.size());
  }

  /**
   * Asserts that there was at least one matching job scheduled <em>after</em> {@link #register() registering} and that all these scheduled jobs finished within
   * {@link #timeout} milliseconds of {@link #register() registering}. This method will block until until all matching jobs finish or until the timeout is
   * reached. In the latter case an {@link junit.framework.AssertionFailedError} will be thrown.
   * <p>
   * After calling this method no additional jobs will be recorded anymore. Call {@link #register()} to reset.
   */
  public final void assertNewJobsFinished() {
    try {
      List<Job> expectedJobs = Lists.newArrayList(newJobs);
      Assert.assertFalse("No matching new jobs were scheduled: " + finder, expectedJobs.isEmpty());
      expectedJobs.removeAll(finishedJobs);
      while (!expectedJobs.isEmpty()) {
        try {
          Job job = getNextJob();
          if (job == null) {
            Assert.fail("Expected new jobs did not finish after " + waitTimeout + " milliseconds: " + expectedJobs);
          }
          expectedJobs.remove(job);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    } finally {
      deregister();
    }
  }

  /**
   * Asserts that all matching jobs already waiting, running, or sleeping when {@link #register() registering} finished within {@link #timeout} milliseconds of
   * {@link #register() registering}. This method will block until all the existing jobs have finished or until the timeout is reached. In the latter case an
   * {@link junit.framework.AssertionFailedError} will be thrown. If no jobs were found upon {@link #register() registering} this method will return
   * immediately.
   * <p>
   * After calling this method no additional jobs will be recorded anymore. Call {@link #register()} to reset.
   */
  public final void waitForExistingJobs() {
    try {
      List<Job> expectedJobs = Lists.newArrayList(existingJobs);
      expectedJobs.removeAll(finishedJobs);
      while (!expectedJobs.isEmpty()) {
        try {
          Job job = getNextJob();
          if (job == null) {
            Assert.fail("Existing jobs did not finish after " + waitTimeout + " milliseconds: " + expectedJobs);
          }
          expectedJobs.remove(job);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    } finally {
      deregister();
    }
  }

  /**
   * Returns the next matching job which finished. This operation may block until the timeout is reached.
   *
   * @return next job or {@code null} if the timeout was exceeded
   * @throws InterruptedException
   *           if operation was interrupted
   */
  private Job getNextJob() throws InterruptedException {
    Job job = jobQueue.poll(timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    if (job != null) {
      finishedJobs.add(job);
    }
    return job;
  }

  @Override
  public synchronized void scheduled(final IJobChangeEvent event) {
    Job job = event.getJob();
    if (finder.apply(job)) {
      newJobs.add(job);
    }
  }

  @Override
  public synchronized void done(final IJobChangeEvent event) {
    Job job = event.getJob();
    if (finder.apply(job)) {
      jobQueue.add(job);
    }
  }
}
