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
package com.avaloq.tools.ddk.test.core.junit.runners;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sorter;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;


/**
 * Discerns tests using test filters.
 */
public class DiscerningSuite extends Suite {

  private boolean descriptionOutdated = true;
  private Description description;
  private Filter testFilter;

  /**
   * Creates a new instance of {@link DiscerningSuite}.
   *
   * @param klass
   *          root of the suite
   * @param runners
   *          for each class in the suite, a {@link Runner}
   * @throws InitializationError
   *           if an error occurred during the initialization
   */
  public DiscerningSuite(final Class<?> klass, final List<Runner> runners) throws InitializationError {
    super(klass, runners);
    initialize();
  }

  /**
   * Creates a new instance of {@link DiscerningSwtBotNonSwtBotSuite}.
   *
   * @param klass
   *          the root of the suite
   * @param suiteClasses
   *          the classes in the suite
   * @throws InitializationError
   *           if an error occurred during the initialization
   */
  public DiscerningSuite(final Class<?> klass, final Class<?>... suiteClasses) throws InitializationError {
    super(klass, suiteClasses);
    initialize();
  }

  /**
   * Creates a new instance of {@link DiscerningSwtBotNonSwtBotSuite}.
   *
   * @param klass
   *          the root class
   * @param builder
   *          builds runners for classes in the suite
   * @throws InitializationError
   *           if an error occurred during the initialization
   */
  public DiscerningSuite(final Class<?> klass, final RunnerBuilder builder) throws InitializationError {
    super(klass, builder);
    initialize();
  }

  /**
   * Creates a new instance of {@link DiscerningSwtBotNonSwtBotSuite}.
   *
   * @param builder
   *          builds runners for classes in the suite
   * @param klass
   *          the root of the suite
   * @param suiteClasses
   *          the classes in the suite
   * @throws InitializationError
   *           if an error occurred during the initialization
   */
  public DiscerningSuite(final RunnerBuilder builder, final Class<?> klass, final Class<?>... suiteClasses) throws InitializationError {
    super(builder, klass, suiteClasses);
    initialize();
  }

  /**
   * Creates a new instance of {@link DiscerningSwtBotNonSwtBotSuite}.
   *
   * @param builder
   *          builds runners for classes in the suite
   * @param classes
   *          the classes in the suite
   * @throws InitializationError
   *           if an error occurred during the initialization
   */
  public DiscerningSuite(final RunnerBuilder builder, final Class<?>... classes) throws InitializationError {
    super(builder, classes);
    initialize();
  }

  /**
   * Initializes the {@link DiscerningSuite}.
   */
  private void initialize() {
    SorterUtil.getInstance().initializeSorter(this);
    FilterRegistry.initializeFilter(this);
  }

  @Override
  public Description getDescription() {
    if (descriptionOutdated) {
      description = super.getDescription();
      descriptionOutdated = false;
    }
    return description;
  }

  @Override
  public void sort(final Sorter sorter) {
    super.sort(sorter);
    descriptionOutdated = true;
  }

  
  @Override
  public void filter(final Filter filter) throws NoTestsRemainException {
    if (testFilter == null || !testFilter.equals(filter)) {
      testFilter = filter;
      super.filter(filter);
      descriptionOutdated = true;
    }
  }
}
