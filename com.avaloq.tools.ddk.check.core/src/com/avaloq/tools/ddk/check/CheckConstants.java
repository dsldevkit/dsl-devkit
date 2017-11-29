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
package com.avaloq.tools.ddk.check;

import org.eclipse.xtext.naming.QualifiedName;


/**
 * Provides language specific constants for Check.
 */
public final class CheckConstants {
  public static final String GRAMMAR = "com.avaloq.tools.ddk.check.Check"; //$NON-NLS-1$
  public static final String FILE_EXTENSION = "check"; //$NON-NLS-1$

  public static final String IT = "it"; //$NON-NLS-1$
  public static final QualifiedName QNIT = QualifiedName.create(IT);

  private CheckConstants() {}
}
