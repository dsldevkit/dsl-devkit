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
package com.avaloq.tools.ddk.xtext.modelcache;

/**
 * The list of the existing model types that can be handled by binary model cache.
 */
public enum ResourceModelType {
  EMF,
  NODE;

  /**
   * Gets a numeric ID representing the given model type.
   *
   * @param type
   *          the model type
   * @return the ID
   */
  public static int getId(final ResourceModelType type) {
    return type.ordinal();
  }

  /**
   * Gets the model type corresponding to a numeric ID.
   *
   * @param id
   *          the ID
   * @return the model type
   */
  public static ResourceModelType getType(final int id) {
    return values()[id];
  }
}
