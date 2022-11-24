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
package com.avaloq.tools.ddk.xtext.scope.naming;

import org.eclipse.xtext.naming.IQualifiedNameConverter;


/**
 * ScopeQualifiedNameConverter.
 */
public class ScopeQualifiedNameConverter extends IQualifiedNameConverter.DefaultImpl {

  @Override
  public String getDelimiter() {
    return "::";
  }

}

