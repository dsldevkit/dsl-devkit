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
package com.avaloq.tools.ddk.xtext.builder.layered;

import java.util.Collection;

import org.eclipse.xtext.resource.IResourceDescription;

import com.google.inject.ImplementedBy;


/**
 * Loader for Xtext target platforms.
 */
@ImplementedBy(DefaultXtextTargetPlatformManager.class)
public interface IXtextTargetPlatformManager {

  /**
   * Return the currently loaded platform.
   *
   * @return the target platform.
   */
  IXtextTargetPlatform getPlatform();

  /**
   * Add a listener for changes in target platform.
   *
   * @param listener
   *          to add
   */
  void addListener(Listener listener);

  /**
   * Remove a listener for changes in target platform. A no-op if the listener was not registered.
   *
   * @param listener
   *          to add
   */
  void removeListener(Listener listener);

  /**
   * Listeners may be registered and will be invoked if the target platform itself changes.
   */
  interface Listener {

    /**
     * Invoked on registered listeners when the platform has changed.
     *
     * @param newPlatform
     *          is the platform that will be used from now on
     * @param deltas
     *          The list of deltas that the switch generated, if known.
     * @param mustRebuild
     *          true if a rebuild is needed in any case.
     */
    void platformChanged(IXtextTargetPlatform newPlatform, Collection<IResourceDescription.Delta> deltas, boolean mustRebuild);

  }

}
