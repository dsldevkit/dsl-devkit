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
package com.avaloq.tools.ddk.check.runtime.context;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.ImplementedBy;


/**
 * A CheckContext instance provides a method for checking if an individual check is valid in its current
 * context, which is used by an individual check to decide whether or not to apply that validation. A default implementation
 * where a check is always valid is provided.
 */
@ImplementedBy(ICheckContext.DefaultCheckContext.class)
public interface ICheckContext {
  /**
   * Decides if a check (represented by an issue code) should be applied to the given context object.
   *
   * @param context
   *          the context to ask
   * @param issueCode
   *          the issueCode to query applicability
   * @return true when check is valid with that context; false otherwise.
   */
  boolean isCheckValid(EObject context, String issueCode);

  /**
   * Default implementation of the CheckContext interface.
   * Checks are always valid by default, which preserves the behaviour expected before conditional check was available.
   */
  class DefaultCheckContext implements ICheckContext {

    @Override
    public boolean isCheckValid(final EObject context, final String issueCode) {
      return true;
    }

  }
}

