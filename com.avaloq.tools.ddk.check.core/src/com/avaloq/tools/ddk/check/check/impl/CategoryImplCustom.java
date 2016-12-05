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

import com.avaloq.tools.ddk.check.util.CheckUtil;


/**
 * TODO: Document type CategoryImplCustom.
 */
public class CategoryImplCustom extends CategoryImpl {

  @Override
  public String getName() {

    if (!StringExtensions.isNullOrEmpty(getId())) {
      return getId();
    } else {
      return CheckUtil.toIdentifier(getLabel());
    }
  }
}

