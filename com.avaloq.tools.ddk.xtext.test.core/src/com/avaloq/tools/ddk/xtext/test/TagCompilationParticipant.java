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

import java.util.List;

import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.TransformationParticipant;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtext.xbase.lib.Extension;


/**
 * The compilation participant for {@link Tag} annotation.
 */
public class TagCompilationParticipant implements TransformationParticipant<MutableFieldDeclaration> {

  /**
   * Start number for the counter. Range up to this counter is reserved for local tags.
   */
  public static final int COUNTER_BASE = 10000;

  /**
   * The compilation strategy for an integer literal.
   */
  private static class IntegerLiteral implements CompilationStrategy {

    private final int value;

    /**
     * Instantiates a new integer literal.
     *
     * @param value
     *          the value of the integer literal
     */
    IntegerLiteral(final int value) {
      this.value = value;
    }

    /**
     * Call back method invoked during compilation.
     *
     * @param context
     *          of compilation, providing useful services.
     * @return the Java code
     */
    @Override
    public CharSequence compile(final CompilationContext context) {
      return String.valueOf(value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void doTransform(final List<? extends MutableFieldDeclaration> annotatedTargetElements, @Extension final TransformationContext context) {
    int counter = COUNTER_BASE;
    for (MutableFieldDeclaration field : annotatedTargetElements) {
      field.setInitializer(new IntegerLiteral(counter++));
    }

  }
}

