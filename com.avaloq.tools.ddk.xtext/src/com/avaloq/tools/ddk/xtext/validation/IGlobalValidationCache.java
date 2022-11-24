/*******************************************************************************
 * Copyright (c) 2017 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.validation;

/** A global validation cache. */
public interface IGlobalValidationCache {

  /**
   * Invalidate the cache.
   */
  void invalidate();

}
