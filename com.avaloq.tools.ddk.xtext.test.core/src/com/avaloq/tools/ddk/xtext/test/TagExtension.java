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
package com.avaloq.tools.ddk.xtext.test;

import java.lang.reflect.Field;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * JUnit Jupiter extension that replicates the compile-time behaviour of the {@link Tag} active annotation in plain Java.
 * <p>
 * When Xtend processes a class containing {@link Tag}-annotated fields, {@link TagCompilationParticipant} assigns
 * sequential integer values starting from {@link TagCompilationParticipant#COUNTER_BASE} to those fields at compile
 * time. This extension performs the equivalent assignment at runtime, immediately before each test method, so that
 * plain Java test classes can use {@link Tag} without the Xtend compiler.
 * </p>
 * <p>
 * Fields are processed in their declaration order. The counter is reset to {@link TagCompilationParticipant#COUNTER_BASE}
 * for each test instance, matching the per-class scope of the Xtend compile-time transformation.
 * </p>
 * <p>
 * Usage: add {@code @ExtendWith(TagExtension.class)} to the test class. The annotated fields must not be
 * {@code final}.
 * </p>
 */
public class TagExtension implements BeforeEachCallback {

  /**
   * Assigns sequential integer values to all {@link Tag}-annotated fields declared on the test class.
   * <p>
   * Only fields declared directly on the test class are processed, not inherited ones. This matches
   * the per-class scope of {@link TagCompilationParticipant}.
   * </p>
   *
   * @param context
   *          the current extension context, must not be {@code null}
   * @throws IllegalAccessException
   *           if a {@link Tag}-annotated field cannot be written via reflection
   */
  @Override
  public void beforeEach(final ExtensionContext context) throws IllegalAccessException {
    Object testInstance = context.getRequiredTestInstance();
    int counter = TagCompilationParticipant.COUNTER_BASE;
    for (Field field : testInstance.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(Tag.class)) {
        field.setAccessible(true);
        field.setInt(testInstance, counter++);
      }
    }
  }
}
