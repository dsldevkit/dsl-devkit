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
package com.avaloq.tools.ddk.test.core.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Assert;


/**
 * Simple progress monitor which can be queried on is done state.
 */
public class SimpleProgressMonitor implements IProgressMonitor {
  private static final String PROGRESS_MONITOR_DID_NOT_GET_DONE_SIGNAL = "Progress monitor did not get done signal";
  private static final int TIMEOUT = 10000;

  /**
   * Indicates whether work is done.
   */
  private boolean workDone;

  /**
   * Indicates whether cancel has been requested.
   */
  private boolean cancelled;

  /**
   * Constructs a new progress monitor.
   */
  public SimpleProgressMonitor() {
    this.workDone = false;
  }

  /**
   * {@inheritDoc} This implementation does nothing.
   * Subclasses may override this method to do interesting
   * processing when a task begins.
   *
   * @see IProgressMonitor#beginTask(String, int)
   */
  @Override
  public void beginTask(final String name, final int totalWork) {
    setDoneState(false);
  }

  /**
   * {@inheritDoc} This implementation does nothing.
   * Subclasses may override this method to do interesting
   * processing when a task is done.
   *
   * @see IProgressMonitor#done()
   */
  @Override
  public void done() {
    setDoneState(true);
  }

  /**
   * {@inheritDoc} This implementation does nothing.
   * Subclasses may override this method.
   *
   * @see IProgressMonitor#internalWorked(double)
   */
  @Override
  public void internalWorked(final double work) {
    setDoneState(false);
  }

  /**
   * {@inheritDoc} This implementation returns the value of the internal
   * state variable set by <code>setCanceled</code>.
   * Subclasses which override this method should
   * override <code>setCanceled</code> as well.
   *
   * @see IProgressMonitor#isCanceled()
   * @see IProgressMonitor#setCanceled(boolean)
   */
  @Override
  public boolean isCanceled() {
    synchronized (this) {
      return cancelled;
    }
  }

  /**
   * {@inheritDoc} This implementation sets the value of an internal state variable.
   * Subclasses which override this method should override <code>isCanceled</code> as well.
   *
   * @see IProgressMonitor#isCanceled()
   * @see IProgressMonitor#setCanceled(boolean)
   */
  @Override
  public void setCanceled(final boolean paramCancelled) {
    synchronized (this) {
      this.cancelled = paramCancelled;
      this.notifyAll();
    }
  }

  /**
   * {@inheritDoc} This implementation does nothing.
   * Subclasses may override this method to do something
   * with the name of the task.
   *
   * @see IProgressMonitor#setTaskName(String)
   */
  @Override
  public void setTaskName(final String name) {
    setDoneState(false);
  }

  /**
   * {@inheritDoc} This implementation does nothing.
   * Subclasses may override this method to do interesting
   * processing when a subtask begins.
   *
   * @see IProgressMonitor#subTask(String)
   */
  @Override
  public void subTask(final String name) {
    setDoneState(false);
  }

  /**
   * {@inheritDoc} This implementation does nothing.
   * Subclasses may override this method to do interesting
   * processing when some work has been completed.
   *
   * @see IProgressMonitor#worked(int)
   */
  @Override
  public void worked(final int work) {
    setDoneState(false);
  }

  /**
   * Test whether the monitored task is done.
   *
   * @return true when the task is done, false otherwise
   */
  public boolean isDone() {
    synchronized (this) {
      return workDone;
    }
  }

  /**
   * Test whether the monitored task is done or cancelled.
   *
   * @return true when the task is done or cancelled, false otherwise
   */
  public boolean isTerminated() {
    synchronized (this) {
      return workDone || cancelled;
    }
  }

  /**
   * Test whether the monitored task is done.
   */
  public void waitForTermination() {
    synchronized (this) {
      final long timeStarted = System.currentTimeMillis();
      while (!isTerminated()) {
        long remainingWaitTime = TIMEOUT + timeStarted - System.currentTimeMillis();
        if (remainingWaitTime <= 0) {
          Assert.fail(PROGRESS_MONITOR_DID_NOT_GET_DONE_SIGNAL);
        }
        try {
          this.wait(remainingWaitTime);
        } catch (InterruptedException e) /* CHECKSTYLE:OFF */ {
        } /* CHECKSTYLE:ON */
      }
    }
  }

  /**
   * Thread safe setter for done state.
   *
   * @param paramDone
   *          new state of done
   */
  private void setDoneState(final boolean paramDone) {
    synchronized (this) {
      this.cancelled = false;
      this.workDone = paramDone;
      this.notifyAll();
    }
  }
}
