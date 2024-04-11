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

import java.util.Comparator;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Sorter;
import org.junit.runners.ParentRunner;


/**
 * Utility to specify a {@link Sorter}.
 */
@SuppressWarnings("nls")
public final class SorterUtil {

  public static final String SORTER_ALPHANUMERIC = "alphanumeric";
  public static final String PROPERTY_SORTER = "com.avaloq.test.sorter";
  private static final String EMPTY = "";
  private final Sorter sorter;

  private static final class SingletonHolder {
    private static SorterUtil instance = new SorterUtil();

    public static SorterUtil get() {
      return instance;
    }
  }

  /**
   * Returns the singleton instance of {@link SorterUtil}.
   *
   * @return the singleton instance of {@link SorterUtil}, never {@code null}
   */
  public static SorterUtil getInstance() {
    return SingletonHolder.get();
  }

  /**
   * Creates a new instance of {@link SorterUtil}.
   */
  private SorterUtil() {
    final String propertySorter = System.getProperty(PROPERTY_SORTER, EMPTY);
    if (SORTER_ALPHANUMERIC.equalsIgnoreCase(propertySorter)) {
      sorter = new Sorter(new Comparator<Description>() {
        @Override
        public int compare(final Description o1, final Description o2) {
          return o1.getDisplayName().compareTo(o2.getDisplayName());
        }
      });
    } else {
      sorter = Sorter.NULL;
    }
  }

  /**
   * Initializes the test sorter.
   *
   * @param parentRunner
   *          the {@link ParentRunner} to initialize, must not be {@code null}
   */
  public void initializeSorter(final ParentRunner<?> parentRunner) {
    parentRunner.sort(sorter);
  }
}
