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
package com.avaloq.tools.ddk.xtext.builder;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;

import com.avaloq.tools.ddk.xtext.util.EmfResourceUtil;
import com.google.common.collect.Maps;


/**
 * Watchdog that keeps track of sources processed during a build. When the builder fails to report progress for a period of time, a warning will be logged,
 * along with the stack traces for all threads of the running application.
 */
public class BuilderWatchdog extends Thread {
  private static final Logger LOGGER = Logger.getLogger(BuilderWatchdog.class);

  private static final String NEW_LINE = "\n"; //$NON-NLS-1$
  private static final String INDENTATION = "\t"; //$NON-NLS-1$
  private static final String SEPARATOR = ", "; //$NON-NLS-1$
  private static final String SPACE = " "; //$NON-NLS-1$
  private static final float TO_PERCENT = 100;

  private static final long ENTRY_TIMEOUT_LIMIT = TimeUnit.SECONDS.toMillis(300); // Maximum time without receiving a progress entry before dumping stacks
  private static final long BATCH_TIMEOUT_LIMIT = TimeUnit.SECONDS.toMillis(1200); // Maximum time to go through a batch before dumping stacks

  private static final int BATCH_SIZE = 1000; // Number of messages to collect before logging a summary
  private static final int CHECK_FREQUENCY = 5000; // How frequently should the stopwatches be checked, in milliseconds

  private final ConcurrentMap<String, AtomicInteger> typeCount = Maps.newConcurrentMap();
  private final AtomicInteger progressCount = new AtomicInteger();

  private boolean canDumpStackTraces = true;
  private long entryTime;
  private long batchTime;
  private URI currentURI;

  public BuilderWatchdog() {
    super();
    entryTime = System.currentTimeMillis();
    batchTime = System.currentTimeMillis();
  }

  @Override
  public void run() {
    try {
      LOGGER.debug("Builder Watchdog Started"); //$NON-NLS-1$

      while (!Thread.interrupted()) {
        Thread.sleep(CHECK_FREQUENCY);

        if (!canDumpStackTraces) {
          continue; // We should only dump the stack traces at most once per batch, to prevent polluting the log file
        }

        if (elapsedSince(entryTime) >= ENTRY_TIMEOUT_LIMIT) {
          logStackTrace(String.format("No progress in the last %d seconds", TimeUnit.MILLISECONDS.toSeconds(elapsedSince(entryTime)))); //$NON-NLS-1$
        } else if (elapsedSince(batchTime) >= BATCH_TIMEOUT_LIMIT) {
          logStackTrace(String.format("Slow progress in the last %d seconds", TimeUnit.MILLISECONDS.toSeconds(elapsedSince(batchTime)))); //$NON-NLS-1$
        }

      }
    } catch (InterruptedException e) {
      // Stop gracefully
    } finally {
      LOGGER.debug("Builder Watchdog Stopped"); //$NON-NLS-1$
    }
  }

  /**
   * Report that work started for the given URI. This is used to identify the source that caused trouble if that any problem occurs.
   *
   * @param uri
   *          the URI of the resource that will be processed now
   */
  public void reportWorkStarted(final URI uri) {
    currentURI = uri;
  }

  /**
   * Procedure through which the builder can report its progress. The watchdog will gather statistics and print a progress report at specific intervals.
   *
   * @param processedSources
   *          the number of processed sources so far
   * @param totalSources
   *          the total number of sources that need to be processed, -1 if unknown
   */
  public void reportWorkEnded(final int processedSources, final int totalSources) {
    entryTime = System.currentTimeMillis();
    AtomicInteger newEntry = new AtomicInteger(0);
    AtomicInteger typeEntry = currentURI != null ? typeCount.putIfAbsent(EmfResourceUtil.getFileExtension(currentURI), newEntry) : null;
    typeEntry = typeEntry != null ? typeEntry : newEntry;
    typeEntry.getAndIncrement();

    progressCount.getAndIncrement();
    if (progressCount.compareAndSet(BATCH_SIZE, 0)) {
      reportAndResetProgress(processedSources, totalSources);
    }
  }

  /**
   * Logs a warning containing the stack traces for all threads, along with a message explaining the reason for the stack trace dump.
   *
   * @param headerMessage
   *          message to print before the stack traces
   */
  private void logStackTrace(final String headerMessage) {
    final StringBuilder stackTraceBuilder = new StringBuilder(headerMessage).append(NEW_LINE);
    if (currentURI != null) {
      stackTraceBuilder.append("Was processing " + currentURI + " when this occurred.").append(NEW_LINE); //$NON-NLS-1$ //$NON-NLS-2$
    }
    stackTraceBuilder.append("This batch contained ").append(processedTypeList()).append(". Dumping stack straces:").append(NEW_LINE); //$NON-NLS-1$ //$NON-NLS-2$

    final Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
    for (Thread thread : allStackTraces.keySet()) {
      stackTraceBuilder.append(thread.getName()).append(NEW_LINE);
      StackTraceElement[] stackElements = allStackTraces.get(thread);
      for (StackTraceElement stackElement : stackElements) {
        stackTraceBuilder.append(INDENTATION).append(stackElement).append(NEW_LINE);
      }
      stackTraceBuilder.append(NEW_LINE);
    }

    canDumpStackTraces = false;
    LOGGER.warn(stackTraceBuilder.toString());
  }

  /**
   * Logs a warning containing the stack traces for all threads, along with a message explaining the reason for the stack trace dump.
   * 
   * @param headerMessage
   *          message to print before the stack traces
   */
  public void panic(final String headerMessage) {
    logStackTrace(headerMessage);
  }

  /**
   * Logs a summary of statistics gathered so far in this batch and starts a new batch.
   *
   * @param processedSources
   *          the number of processed sources so far
   * @param totalSources
   *          the total number of sources that need to be processed, -1 if unknown
   */
  private void reportAndResetProgress(final int processedSources, final int totalSources) {
    final long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(elapsedSince(batchTime));
    String progressMessage;
    if (totalSources >= 0) {
      long progress = Math.round((processedSources * TO_PERCENT) / totalSources);
      progressMessage = String.format("%2d%% complete (%5d/%5d). Last batch took %3d seconds (%s)", progress, processedSources, totalSources, elapsedTime, processedTypeList()); //$NON-NLS-1$
    } else {
      progressMessage = String.format("%5d sources processed. Last batch took %3d seconds (%s)", processedSources, elapsedTime, processedTypeList()); //$NON-NLS-1$
    }
    LOGGER.info(progressMessage);

    batchTime = System.currentTimeMillis();
    canDumpStackTraces = true;
    typeCount.clear();
  }

  /**
   * Returns a list of the processed source types and respective count for the current batch, as a string.
   *
   * @return a string containing a formatted representation of the source type list
   */
  private String processedTypeList() {
    final StringBuilder typeListBuilder = new StringBuilder();
    boolean needsSeparator = false;
    for (Entry<String, AtomicInteger> typeEntry : typeCount.entrySet()) {
      if (needsSeparator) {
        typeListBuilder.append(SEPARATOR);
      }
      typeListBuilder.append(typeEntry.getValue().get()).append(SPACE).append(typeEntry.getKey());
      needsSeparator = true;
    }
    return typeListBuilder.toString();
  }

  /**
   * How many milliseconds have elapsed since the given start time.
   *
   * @param start
   *          the start time
   * @return the milliseconds that have elapsed since the start time
   */
  private long elapsedSince(final long start) {
    return System.currentTimeMillis() - start;
  }
}

