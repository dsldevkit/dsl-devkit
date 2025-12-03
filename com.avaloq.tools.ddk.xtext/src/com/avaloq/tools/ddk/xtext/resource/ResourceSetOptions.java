/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;


/**
 * A class to set and get DDK specific options over an EMF ResourceSet, similar to {@link org.eclipse.xtext.resource.ResourceSetContext}.
 */
@SuppressWarnings("nls")
public final class ResourceSetOptions {

  private static final String INSTALL_DERIVED_STATE = "com.avaloq.tools.ddk.xtext.resource.ResourceSetOptions.installDerivedState";
  private static final String SKIP_MODEL = "com.avaloq.tools.ddk.xtext.resource.ResourceSetOptions.skipModel";

  private ResourceSetOptions() {
    // utility class
  }

  /**
   * If the derived state should be installed.
   *
   * @param resourceSet
   *          the resource set
   * @return true, If the derived state should be installed
   */
  public static boolean installDerivedState(final @NonNull ResourceSet resourceSet) {
    Object object = resourceSet.getLoadOptions().get(INSTALL_DERIVED_STATE);
    return object == null || (boolean) object;
  }

  /**
   * Sets the install derived state property.
   *
   * @param resourceSet
   *          the resource set
   * @param installDerivedState
   *          {@code true} if the derived state should be installed
   */
  public static void setInstallDerivedState(final @NonNull ResourceSet resourceSet, final @Nullable Boolean installDerivedState) {
    resourceSet.getLoadOptions().put(INSTALL_DERIVED_STATE, installDerivedState);
  }

  public static boolean skipModel(final @NonNull ResourceSet resourceSet) {
    Object object = resourceSet.getLoadOptions().get(SKIP_MODEL);
    return object != null && (boolean) object; // default is false
  }

  public static void setSkipModel(final @NonNull ResourceSet resourceSet, final @Nullable Boolean skipModel) {
    resourceSet.getLoadOptions().put(SKIP_MODEL, skipModel);
  }
}
