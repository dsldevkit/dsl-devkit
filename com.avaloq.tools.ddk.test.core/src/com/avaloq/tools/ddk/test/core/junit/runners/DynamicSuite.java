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

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;


/**
 * Using {@link DynamicSuite} as a runner allows you to manually build a suite containing
 * tests from many classes, as with {@link Suite}. While {@link Suite} has static references
 * to the test classes, {@link DynamicSuite} resolves the class names at runtime.
 */
public class DynamicSuite extends Suite {

  /**
   * Annotation allows to specify names of suite classes as separate strings.
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target({java.lang.annotation.ElementType.TYPE})
  @Inherited
  public static @interface SuiteClasses {
    String[] value();
  }

  public DynamicSuite(final Class<?> clazz, final RunnerBuilder builder) throws InitializationError {
    this(clazz, builder.runners(clazz, getSuiteClasses(clazz)));
  }

  public DynamicSuite(final RunnerBuilder builder, final Class<?>[] classes) throws InitializationError {
    super(builder, classes);
  }

  public DynamicSuite(final Class<?> clazz, final Class<?>[] suiteClasses) throws InitializationError {
    super(clazz, suiteClasses);
  }

  public DynamicSuite(final Class<?> clazz, final List<Runner> runners) throws InitializationError {
    super(clazz, runners);
  }

  public DynamicSuite(final RunnerBuilder builder, final Class<?> clazz, final Class<?>[] suiteClasses) throws InitializationError {
    super(builder, clazz, suiteClasses);
  }

  /**
   * Collect suite classes from annotation.
   * 
   * @param clazz
   *          main suite to get suite classes annotation from
   * @return list of suite classes
   * @throws InitializationError
   *           thrown when annotation is missing or class can not be found
   */
  private static Class<?>[] getSuiteClasses(final Class<?> clazz) throws InitializationError {
    // get annotation
    SuiteClasses annotation = clazz.getAnnotation(SuiteClasses.class);
    if (annotation == null) {
      throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", new Object[] {clazz.getName()}));
    }

    // get classes from class names
    List<Class<?>> suiteClasses = new ArrayList<Class<?>>();
    for (String className : annotation.value()) {
      try {
        suiteClasses.add(Class.forName(className, true, clazz.getClassLoader())); // use class loader of suite class in order to resolve specified classes
      } catch (ClassNotFoundException e) {
        throw new InitializationError(String.format("class '%s' not found", className)); // NOPMD: InitializationError has no parameter for Exception
      }
    }

    // return suite classes
    return suiteClasses.toArray(new Class<?>[suiteClasses.size()]);
  }
}

