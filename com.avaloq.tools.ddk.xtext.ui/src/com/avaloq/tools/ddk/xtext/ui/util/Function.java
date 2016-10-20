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
package com.avaloq.tools.ddk.xtext.ui.util;

/**
 * A function is a {@link Runnable} with a return value.
 *
 * @param <T>
 *          the type of the result
 */
public interface Function<T> {
  /**
   * Runs this function and returns the result.
   *
   * @return the result of this function
   */
  T run();
}

/* Copyright (c) Avaloq Evolution AG */