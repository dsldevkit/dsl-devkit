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
package com.avaloq.tools.ddk.test.core;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import org.junit.runners.model.MultipleFailureException;

import com.google.common.collect.Lists;


/**
 * Contains a list of problems.
 */
public class MultipleTestProblems extends AssertionError {
  private static final long serialVersionUID = 1L;
  private final List<Throwable> problems = Lists.newArrayList();

  /**
   * Creates a new instance of {@link MultipleTestProblems}.
   */
  public MultipleTestProblems() {
    this(null);
  }

  /**
   * Creates a new instance of {@link MultipleTestProblems}.
   *
   * @param problems
   *          an initial set of problems, may be {@code null}
   */
  public MultipleTestProblems(final List<Throwable> problems) {
    super("Multiple Test Problems occurred, see stacktrace for info.");
    if (problems != null) {
      addProblems(problems);
    }
  }

  /**
   * Adds a new problem.
   *
   * @param problem
   *          the {@link Throwable} to be added, must not be {@code null}
   */
  public final void addProblem(final Throwable problem) {
    if (problem instanceof MultipleTestProblems) {
      addProblems((MultipleTestProblems) problem);
    } else if (problem instanceof MultipleFailureException) {
      addProblems(((MultipleFailureException) problem).getFailures());
    } else {
      problems.add(problem);
    }
  }

  /**
   * Adds new problems.
   *
   * @param additionalProblems
   *          the list of {@link Throwable} to be added, must not be {@code null}
   */
  public final void addProblems(final List<Throwable> additionalProblems) {
    for (final Throwable problem : additionalProblems) {
      addProblem(problem);
    }
  }

  /**
   * Adds new problems of another {@link MutlipleTestProblems} instance.
   *
   * @param multipleTestProblems
   *          the list of {@link Throwable} to be added, must not be {@code null}
   */
  public final void addProblems(final MultipleTestProblems multipleTestProblems) {
    addProblems(multipleTestProblems.getProblems());
  }

  /**
   * Returns all problems.
   *
   * @return all problems, never {@code null}
   */
  public List<Throwable> getProblems() {
    return Lists.newArrayList(problems);
  }

  /**
   * Returns {@code true} if there are problems.
   *
   * @return {@code true} if there are problems, {@code false} otherwise
   */
  public boolean hasProblems() {
    return !problems.isEmpty();
  }

  /** {@inheritDoc} */
  @Override
  public void printStackTrace(final PrintWriter writer) {
    int i = 1;
    writer.println(getMessage());
    for (final Throwable problem : problems) {
      writer.print(i++ + ". ");
      problem.printStackTrace(writer);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printStackTrace(final PrintStream stream) {
    int i = 1;
    stream.println(getMessage());
    for (final Throwable problem : problems) {
      stream.print(i++ + ". ");
      problem.printStackTrace(stream);
    }
  }

  /**
   * Checks if this {@link MultipleTestProblem} has any problems.
   * <p>
   * <em>Note</em>: If there is only one problem, and it is either a {@link RuntimeException} or an {@link Error}, then that problem is thrown. Otherwise this
   * {@link MultipleTestProblem} is thrown.
   * </p>
   */
  public void assertEmpty() {
    if (problems.isEmpty()) {
      return;
    }
    if (problems.size() == 1) {
      final Throwable problem = problems.get(0);
      if (problem instanceof RuntimeException) {
        throw (RuntimeException) problem;
      }
      if (problem instanceof Error) {
        throw (Error) problem;
      }
    }
    printStackTrace();
    throw this;
  }
}

