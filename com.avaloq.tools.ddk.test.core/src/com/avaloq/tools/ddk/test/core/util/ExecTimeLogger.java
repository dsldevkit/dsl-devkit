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

import static com.avaloq.tools.ddk.test.core.util.Reflect.getCallingClass;
import static com.avaloq.tools.ddk.test.core.util.Reflect.getCallingMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Class for logging execution times. First {@link start()} and {@link stop()}
 * must be called to measure the time of an arbitrary operation. Afterwards
 * {@link log()} or {@link getExecTime()} may be called to get the result.
 */
public class ExecTimeLogger {

  private final DateFormat df;

  private long startTime = -1;
  private long stopTime = -1;
  private String caller;

  private final boolean strict;

  /**
   * Default Constructor.
   */
  public ExecTimeLogger() {
    this(false);
  }

  /**
   * Constructor that enables strict mode. In strict mode, this class'
   * {@link start()} and {@link stop()} methods must be called from the
   * same method. This is helpful when sharing the same instance among
   * several methods.
   *
   * @param strict
   *          Whether to activate strict mode
   */
  public ExecTimeLogger(final boolean strict) {
    this.strict = strict;
    df = new SimpleDateFormat("HH:mm:ss", Locale.GERMAN);
    df.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  /**
   * Resets this logger so that it can be used to log another execution.
   */
  @SuppressWarnings("PMD.NullAssignment")
  public void reset() {
    startTime = -1;
    stopTime = -1;
    caller = null;
  }

  /**
   * Starts measurement of execution time.
   */
  public void start() {
    if (startTime != -1 || stopTime != -1 || caller != null) {
      throw new IllegalStateException("ExecTimeLogger needs to be reset before calling start()");
    }
    caller = getCallingMethod();
    startTime = System.currentTimeMillis();
  }

  /**
   * Stops measurement of execution time.
   */
  public void stop() {
    if (startTime == -1 || stopTime != -1) {
      throw new IllegalStateException("ExecTimeLogger is not running or already stopped");
    }
    if (strict && (caller == null || !caller.equals(getCallingMethod()))) {
      throw new IllegalStateException("Corresponding start() and stop() must be called from same method");
    }
    stopTime = System.currentTimeMillis();
  }

  /**
   * Logs execution time to log4j logger of calling class. The time is logged
   * for the method that called {@link start()}.
   */
  public void log() {
    if (startTime == -1 || stopTime == -1 || caller == null) {
      throw new IllegalStateException("No stopped run to log available");
    }
    Logger logger = LogManager.getLogger(getCallingClass());
    Date interval = new Date(getExecTime());
    logger.info("Execution statistics: " + getCallingMethod() + " took " + df.format(interval));
  }

  /**
   * Returns the execution time (so far). This method may be called for
   * stopped runs as well as for ongoing runs.
   *
   * @return the exec time
   */
  public long getExecTime() {
    if (startTime == -1) {
      throw new IllegalStateException("No active or stopped run available");
    }
    return stopTime - startTime;
  }
}
