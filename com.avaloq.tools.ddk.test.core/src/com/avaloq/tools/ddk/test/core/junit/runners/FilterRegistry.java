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
package com.avaloq.tools.ddk.test.core.junit.runners;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runners.ParentRunner;
import org.junit.runners.Suite;

import com.google.common.collect.Lists;


/**
 * A delegating filter that allows registration of suite, test class and test method filters.
 * <p>
 * <em>Note</em>: Filters can be registered using the system properties {@value #PROPERTY_SUITE_FILTERS}, {@value #PROPERTY_CLASS_FILTERS} and
 * {@value #PROPERTY_METHOD_FILTERS}.
 * </p>
 */
public final class FilterRegistry extends Filter {
  private static final String NEGATOR = "!";
  private static final String FILTER_SEPARATOR = ",";
  private static final String EMPTY_STRING = "";
  /** The system property to register test suite filters. */
  private static final String PROPERTY_SUITE_FILTERS = "com.avaloq.test.suitefilters";
  /** The system property to register test class filters. */
  private static final String PROPERTY_CLASS_FILTERS = "com.avaloq.test.classfilters";
  /** The system property to register test method filters. */
  private static final String PROPERTY_METHOD_FILTERS = "com.avaloq.test.methodfilters";
  private static final String MESSAGE_INVALID_FILTER = "Invalid filter ''{0}'' registered in property ''{1}''.";
  /** The package prefix for default filters, including the last segment separator. */
  private static final String PACKAGE_PREFIX = FilterRegistry.class.getCanonicalName().substring(0, FilterRegistry.class.getCanonicalName().length()
      - FilterRegistry.class.getSimpleName().length());
  private static final FilterRegistry INSTANCE = new FilterRegistry();
  private static final Logger LOGGER = Logger.getLogger(FilterRegistry.class);
  /** The list of test suite filters. */
  private final List<Filter> suiteFilters;
  /** The list of test class filters. */
  private final List<Filter> classFilters;
  /** The list of test method filters. */
  private final List<Filter> methodFilters;

  /**
   * Initializes the test filter.
   *
   * @param parentRunner
   *          the {@link ParentRunner} to initialize, must not be {@code null}
   */
  public static void initializeFilter(final ParentRunner<?> parentRunner) {
    try {
      parentRunner.filter(INSTANCE);
    } catch (NoTestsRemainException e) {
      // we ignore the case where no children are left
    }
  }

  /**
   * Creates a new instance of {@link FilterRegistry}.
   * <p>
   * <em>Note</em>: This filter is initialized using the values of the system properties {@value #PROPERTY_SUITE_FILTERS}, {@value #PROPERTY_CLASS_FILTERS} and
   * {@value #PROPERTY_METHOD_FILTERS}.
   * </p>
   * <p>
   * The values are separated by {@value #FILTER_SEPARATOR}. A filter can be negated by prepending a {@value #NEGATOR}.
   * </p>
   */
  private FilterRegistry() {
    suiteFilters = parseFilters(PROPERTY_SUITE_FILTERS);
    classFilters = parseFilters(PROPERTY_CLASS_FILTERS);
    methodFilters = parseFilters(PROPERTY_METHOD_FILTERS);
  }

  /**
   * Parses and instantiates filters registered with the given system property.
   *
   * @param property
   *          the name of the system property to parse, must not be {@code null} or empty
   * @return the list of filters, never {@code null}
   */
  private List<Filter> parseFilters(final String property) {
    final List<Filter> result = Lists.newArrayList();
    final String filtersProperty = System.getProperty(property, EMPTY_STRING);
    if (filtersProperty.length() == 0) {
      return result;
    }
    final String[] filterNames = filtersProperty.split(FILTER_SEPARATOR);
    for (final String splitSegment : filterNames) {
      String splitSegmentTrimmed = splitSegment.trim();
      if (splitSegmentTrimmed.isEmpty()) {
        continue;
      }

      // Handle negation
      boolean negated = splitSegmentTrimmed.startsWith(NEGATOR);
      String filterClassOrLabel = splitSegmentTrimmed;
      if (negated) {
        filterClassOrLabel = splitSegmentTrimmed.substring(1);
      }

      // Try to find a Filter subclass with this name
      String qualifiedClassName = filterClassOrLabel;
      if (!filterClassOrLabel.contains(".")) {
        qualifiedClassName = PACKAGE_PREFIX + filterClassOrLabel;
      }
      try {
        Filter filter = null;
        try {
          Class<?> filterClass = Class.forName(qualifiedClassName);
          if (Filter.class.isAssignableFrom(filterClass)) {
            final Object filterInstance = filterClass.newInstance();
            filter = (Filter) filterInstance;
          }
        } catch (ClassNotFoundException e) {
          // If no class exists with this name, interpret the String as a label and create a label filter for it
          filter = new TestLabelFilter(filterClassOrLabel);
        }

        if (filter != null) {
          if (negated) {
            result.add(new NegatedFilter(filter));
          } else {
            result.add(filter);
          }
        }
      } catch (InstantiationException e) {
        LOGGER.error(NLS.bind(MESSAGE_INVALID_FILTER, qualifiedClassName, property), e);
      } catch (IllegalAccessException e) {
        LOGGER.error(NLS.bind(MESSAGE_INVALID_FILTER, qualifiedClassName, property), e);
      }
    }
    return result;
  }

  /**
   * Determines if the given {@link Description} is describing a {@link Suite}.
   *
   * @param description
   *          the {@link Description} to check, must not be {@code null}
   * @return whether the description is describing a Suite
   */
  public static boolean isSuite(final Description description) {
    final RunWith runWithAnnotation = description.getAnnotation(RunWith.class);
    return runWithAnnotation != null && Suite.class.isAssignableFrom(runWithAnnotation.value());
  }

  /**
   * Determines if the given {@link Description} is describing a test class.
   *
   * @param description
   *          the {@link Description} to check, must not be {@code null}
   * @return whether the description is describing a test class
   */
  public static boolean isTestClass(final Description description) {
    return !isSuite(description) && !isTestMethod(description);
  }

  /**
   * Determines if the given {@link Description} is describing a test method.
   *
   * @param description
   *          the {@link Description} to check, must not be {@code null}
   * @return whether the description is describing a test method
   */
  public static boolean isTestMethod(final Description description) {
    return description.getMethodName() != null;
  }

  /** {@inheritDoc} */
  @Override
  public boolean shouldRun(final Description description) {
    if (isSuite(description)) {
      // check if the description passes all suite filters
      for (final Filter filter : suiteFilters) {
        if (!filter.shouldRun(description)) {
          return false;
        }
      }
      if (!hasRunnableChildren(description)) {
        return false;
      }
    } else if (isTestClass(description)) {
      if (!hasRunnableChildren(description)) {
        return false;
      }
      // check if the description passes all test class filters
      for (final Filter filter : classFilters) {
        if (!filter.shouldRun(description)) {
          return false;
        }
      }
    } else if (isTestMethod(description)) {
      // check if the description passes all test method filters
      for (final Filter filter : methodFilters) {
        if (!filter.shouldRun(description)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check recursively if at least one child description should be run.
   *
   * @param description
   *          the parent {@link Description}, must not be {@code null}
   * @return whether there are runnable children
   */
  private boolean hasRunnableChildren(final Description description) {
    for (final Description child : description.getChildren()) {
      if (shouldRun(child)) {
        return true;
      }
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public String describe() {
    final StringBuilder description = new StringBuilder("FilterRegistry");
    description.append(describeFilters(suiteFilters, "suite"));
    description.append(describeFilters(classFilters, "class"));
    description.append(describeFilters(methodFilters, "method"));
    return description.toString();
  }

  /**
   * Describes the list of filters.
   *
   * @param filters
   *          the filters to describe, must not be {@code null}
   * @param title
   *          the title for the filters, may be {@code null}
   * @return
   *         the description for the given filters, never {@code null}
   */
  private String describeFilters(final List<Filter> filters, final String title) {
    assert filters != null;
    final StringBuilder description = new StringBuilder();
    if (!filters.isEmpty()) {
      description.append(" (").append(title).append(" filters:");
      for (final Filter filter : filters) {
        description.append(' ').append(filter.describe());
      }
      description.append(')');
    }
    return description.toString();
  }
}
