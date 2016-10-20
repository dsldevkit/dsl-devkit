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
package com.avaloq.tools.ddk.xtext.formatting.locators;

import org.eclipse.emf.ecore.EObject;


/**
 * Should be implemented for each condition of the matcher of format language (Xbase block expression) to indicate when corresponding locator (formatting
 * behaviour) should be applied.
 *
 * @param <T>
 *          type of EObject
 */
public interface LocatorActivator<T extends EObject> {

  /**
   * Indicates whether a concrete locator (extending {@code AbstractFormattingConfig.ElementLocator}) should be activated (its formatting behaviour is being
   * enforced) or not for a given semantic grammar element ({@code EObject}).
   *
   * @param object
   *          for which locator should be active or not
   * @param currentColumn
   *          the current column offset, can be {@code null}
   * @return true if locator should be active
   */
  boolean activate(T object, Integer currentColumn);
}
