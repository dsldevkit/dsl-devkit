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
package com.avaloq.tools.ddk.xtext.delegation;

import org.eclipse.xtext.nodemodel.INode;


/**
 * Helps detecting the context of the getting services for the delegated language.
 */
public interface ILanguageDelegate {

  /**
   * Checks if is delegate rule.
   *
   * @param node
   *          the concrete syntax tree node, may be {@code null}
   * @return {@code true}, if is delegate rule, for {@code null} returns {@code false}
   */
  boolean isDelegated(INode node);

  /**
   * Gets the single instance of ILanguageDelegate.
   *
   * @param <T>
   *          the generic type
   * @param clazz
   *          the class
   * @return single instance of ILanguageDelegate
   */
  <T> T getInstance(Class<T> clazz);

}
