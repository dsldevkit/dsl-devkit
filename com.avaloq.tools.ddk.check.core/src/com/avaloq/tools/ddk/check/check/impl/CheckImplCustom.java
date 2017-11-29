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
package com.avaloq.tools.ddk.check.check.impl;

import org.eclipse.xtext.xbase.lib.StringExtensions;


/**
 * Provides custom behavior for {@link CheckImpl}.
 */
public class CheckImplCustom extends CheckImpl {

  @Override
  public String getName() {
    return getId();
  }

  @Override
  public String getMessage() {
    if (!StringExtensions.isNullOrEmpty(getGivenMessage())) {
      return getGivenMessage();
    } else {
      return getLabel();
    }
  }
}
