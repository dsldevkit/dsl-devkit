/*******************************************************************************
 * Copyright (c) 2020 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.build;

/**
 * This interface is used when a default package needs to be initialized.
 */
public interface IDynamicDefaultPackageSetupService {

  /**
   * Returns the name of the package.
   *
   * @return the name
   */
  String getPackageName();

  /**
   * Initialize package.
   */
  void doPackageSetup();

}
