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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Allows to register a log listener, which is notified when errors are logged in the error log.
 * <p>
 * The listener has to be registered with {@link ErrorLogListener#register()} before it can be used. Do not forget to unregister the listener with
 * {@link ErrorLogListener#unregister()} when it is no longer needed.
 * </p>
 */
public class ErrorLogListener {
  /** The log listener. */
  private final LogListener logListener = new LogListener();
  /** Exceptions that shall be ignored. */
  private final Collection<Class<? extends Throwable>> ignoredExceptions = Sets.newHashSet();
  /** Ignored exceptions and locations at which they shall be ignored. */
  private final HashMultimap<Class<? extends Throwable>, String> ignoredExceptionLocations = HashMultimap.create();

  /**
   * Registers the log listener.
   */
  public void register() {
    Platform.addLogListener(logListener);
  }

  /**
   * Returns the logged status of all severities (OK, CANCEL, INFO, WARNING, ERROR).
   * 
   * @return the logged status, never {@code null}
   */
  public Collection<IStatus> getLoggedStatuses() {
    return logListener.getLoggedStatuses();
  }

  /**
   * Returns the logged exceptions (ERRORS which have a exception attached) in the order
   * they occurred.
   * <p>
   * <em>Note</em>: Ignored exceptions are not considered.
   * </p>
   * 
   * @return the logged exceptions, never {@code null}
   */
  public List<Throwable> getLoggedExceptions() {
    List<Throwable> loggedExceptions = Lists.newArrayList();
    for (IStatus loggedStatus : logListener.getLoggedStatuses()) {
      if (isException(loggedStatus)) {
        loggedExceptions.add(loggedStatus.getException());
      }
    }
    return loggedExceptions;
  }

  /**
   * Ignores the given class of exceptions.
   * <p>
   * <em>Note</em>: Subclasses of the given class are not ignored and thus must be specifically ignored.
   * </p>
   * 
   * @param exceptionClass
   *          the class of exceptions to ignore, must not be {@code null}
   */
  public void ignoreException(final Class<? extends Throwable> exceptionClass) {
    ignoredExceptions.add(exceptionClass);
  }

  /**
   * Ignores the given class of exceptions thrown at a given location in the stacktrace.
   * <p>
   * <em>Note</em>: All elements of the stacktrace are taken into consideration.
   * </p>
   * <p>
   * <em>Note</em>: The location is matched against the start of the fully qualified class name and method name (without parentheses), where the exception was
   * thrown. Examples:
   * <li> {@code org.eclipse.ui.internal.views.markers.MarkerCategory.getChildren}
   * <li> {@code org.eclipse.ui.internal.views.markers.MarkerCategory}
   * <li> {@code org.eclipse}
   * </p>
   * 
   * @param exceptionClass
   *          the class of exceptions to ignore, must not be {@code null}
   * @param location
   *          the location where the exception is thrown, must not be {@code null}
   */
  public void ignoreException(final Class<? extends Throwable> exceptionClass, final String location) {
    ignoredExceptionLocations.put(exceptionClass, location);
  }

  /**
   * Whether any exception is logged (ERRORS which have a exception attached).
   * <p>
   * <em>Note</em>: Ignored exceptions are not considered.
   * </p>
   * 
   * @return {@code true} if any exception is logged, else {@code false}
   */
  public boolean isAnyExceptionLogged() {
    for (IStatus loggedStatus : logListener.getLoggedStatuses()) {
      if (isException(loggedStatus)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Whether the given status represents an exception (ERROR severity with an exception attached).
   * <p>
   * <em>Note</em>: Ignored exceptions are not considered.
   * </p>
   * 
   * @param loggedStatus
   *          the logged status, must not be {@code null}
   * @return {@code true} if the given status represents an exception, else {@code false}
   */
  private boolean isException(final IStatus loggedStatus) {
    return loggedStatus.getSeverity() == IStatus.ERROR && loggedStatus.getException() != null && !isExceptionIgnored(loggedStatus.getException());
  }

  /**
   * Whether the exception (or any causes of the exception) are in the list of exceptions to be ignored.
   * 
   * @param exception
   *          the logged {@link Throwable}, must not be {@code null}
   * @return {@code true} if the exception is ignored, else {@code false}
   */
  private boolean isExceptionIgnored(final Throwable exception) {
    Throwable currentException = exception;
    while (currentException != null) {
      final Class<? extends Throwable> exceptionClass = currentException.getClass();
      if (ignoredExceptions.contains(exceptionClass)) {
        return true;
      }
      final StackTraceElement[] stackTrace = currentException.getStackTrace();
      for (StackTraceElement stackTraceElement : stackTrace) {
        final String qualifiedMethodName = stackTraceElement.getClassName() + '.' + stackTraceElement.getMethodName();
        for (final String location : ignoredExceptionLocations.get(exceptionClass)) {
          if (qualifiedMethodName.startsWith(location)) {
            return true;
          }
        }
      }
      currentException = currentException.getCause();
    }
    return false;
  }

  /**
   * Whether an exception of the given exception type is logged.
   * 
   * @param exceptionType
   *          the type of the exception to be checked, must not be {@code null}
   * @return {@code true} if an exception of the given type is logged, else {@code false}
   */
  public boolean isExceptionLogged(final Class<? extends Exception> exceptionType) {
    for (IStatus loggedError : logListener.getLoggedStatuses()) {
      if (exceptionType.isInstance(loggedError.getException())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Clears the logged errors.
   */
  public void clearLoggedErrors() {
    logListener.clear();
  }

  /**
   * Unregisters the log listeners.
   */
  public void unregister() {
    Platform.removeLogListener(logListener);

  }

  /**
   * A log listeners listening for logged {@link IStatus}.
   */
  private class LogListener implements ILogListener {
    /** The list holding the logged status. */
    private final List<IStatus> loggedStatus = new ArrayList<IStatus>();

    /**
     * {@inheritDoc}
     */
    public void logging(final IStatus status, final String plugin) {
      loggedStatus.add(status);
    }

    /**
     * Clears the status being logged.
     */
    private void clear() {
      loggedStatus.clear();
    }

    /**
     * Returns the status being logged.
     * 
     * @return the status being logged
     */
    private Collection<IStatus> getLoggedStatuses() {
      return loggedStatus;
    }
  }
}

