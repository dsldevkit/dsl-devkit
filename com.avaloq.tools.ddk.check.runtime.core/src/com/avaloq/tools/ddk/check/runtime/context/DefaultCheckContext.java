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

import com.google.inject.Singleton;


/**
 * Default implementation of the CheckContext interface.
 * Checks are always valid by default, which preserves the behaviour expected before conditional check was available.
 */
@Singleton
public class DefaultCheckContext implements ICheckContext {

  @Override
  public boolean isCheckValid(final EObject context, final String issueCode) {
    return true;
  }

}

