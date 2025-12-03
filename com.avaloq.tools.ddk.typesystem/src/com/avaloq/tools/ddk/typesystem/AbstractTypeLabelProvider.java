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
package com.avaloq.tools.ddk.typesystem;

import java.util.Collections;

import org.eclipse.xtext.util.Exceptions;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.util.PolymorphicDispatcher.ErrorHandler;

import com.avaloq.tools.ddk.typesystem.typemodel.IType;


/**
 * An abstract type-label provider base class that uses polymorphic dispatch.
 */
public abstract class AbstractTypeLabelProvider implements ITypeLabelProvider {

  private PolymorphicDispatcher<String> textDispatcher;

  private PolymorphicDispatcher<String> getPolymorphicDispatcher() {
    if (textDispatcher == null) {
      textDispatcher = new PolymorphicDispatcher<String>("text", 1, 1, Collections.singletonList(this), new ErrorHandler<String>() { //$NON-NLS-1$
        @Override
        public String handle(final Object[] params, final Throwable e) {
          return handleTextError(params, e);
        }
      });
    }
    return textDispatcher;
  }

  /**
   * Handles error exceptions from polymorphic text dispatcher.
   *
   * @param params
   *          parameters passed by the dispatcher.
   * @param e
   *          exception encountered by the dispatcher.
   * @return default text for a null value if exception was a NullPointerException, else wraps and throws exception
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  protected String handleTextError(final Object[] params, final Throwable e) {
    if (e instanceof NullPointerException) {
      return text(null);
    }
    return Exceptions.throwUncheckedException(e);
  }

  @Override
  public String getText(final IType type) {
    return getPolymorphicDispatcher().invoke(type);
  }

  /**
   * Provides the default label for a null value.
   *
   * @param type
   *          a null value
   * @return the label for {@code type}
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  protected String text(final Void type) {
    return "<null>"; //$NON-NLS-1$
  }

}
