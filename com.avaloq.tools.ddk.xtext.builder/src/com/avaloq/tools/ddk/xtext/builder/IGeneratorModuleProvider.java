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
package com.avaloq.tools.ddk.xtext.builder;

/**
 * Specifies which module id should be used by the generator.
 */
public interface IGeneratorModuleProvider {
  /**
   * Returns the generator module id.
   *
   * @return the generator module id, never {@code null} or empty
   */
  String getGeneratorModuleId();
}
