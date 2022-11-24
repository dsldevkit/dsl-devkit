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
package com.avaloq.tools.ddk.checkcfg.naming;

import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;


/**
 * Configures qualified names for Check configuration model elements.
 */
public class CheckCfgDeclarativeQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

  @Inject
  private IQualifiedNameConverter converter;

  /**
   * Creates a qualified name for a given JVM generic type.
   * 
   * @param type
   *          the type
   * @return the qualified name
   */
  QualifiedName qualifiedName(final JvmGenericType type) {
    return converter.toQualifiedName(type.getQualifiedName());
  }

}

