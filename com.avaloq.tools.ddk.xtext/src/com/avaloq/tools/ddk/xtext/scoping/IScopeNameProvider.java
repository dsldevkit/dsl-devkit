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
package com.avaloq.tools.ddk.xtext.scoping;

import org.eclipse.emf.ecore.EClass;


/**
 * Interface for scope name providers.
 */
public interface IScopeNameProvider {

  /**
   * Return the name functions for a certain EClass.
   *
   * @param eClass
   *          The EClass
   * @return The name functions
   */
  Iterable<INameFunction> getNameFunctions(EClass eClass);

}
