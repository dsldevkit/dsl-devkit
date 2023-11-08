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
package com.avaloq.tools.ddk.check.runtime.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;
import com.google.inject.Provider;


/**
 * TODO Document class.
 */
public class CheckValidatorDescriptor implements Provider<ICheckValidatorImpl>, ICheckImplDescriptor {

  private static final Logger LOGGER = LogManager.getLogger(CheckValidatorDescriptor.class);

  private final IConfigurationElement element;
  private final String attClass;
  private ICheckValidatorImpl validator;

  public CheckValidatorDescriptor(final IConfigurationElement element, final String attClass) {
    this.element = element;
    this.attClass = attClass;
  }

  @Override
  public IConfigurationElement getElement() {
    return element;
  }

  /**
   * Gets the validator as specified in the {@code plugin.xml} file.
   * Will log error and return {@code null} if no implementation could be found.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public synchronized ICheckValidatorImpl get() {
    if (this.validator == null && element.isValid()) {
      try {
        this.validator = (ICheckValidatorImpl) element.createExecutableExtension(attClass);
      } catch (CoreException e) {
        LOGGER.error(e.getMessage(), e);
      }
    }
    return this.validator;
  }

}
