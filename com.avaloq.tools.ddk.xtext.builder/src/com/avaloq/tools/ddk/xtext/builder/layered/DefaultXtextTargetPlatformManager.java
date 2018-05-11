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

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.xtext.resource.IResourceDescription;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Default implementation of an IXtextTargetPlatformManager.
 */
@Singleton
public class DefaultXtextTargetPlatformManager implements IXtextTargetPlatformManager {

  @Inject
  private IXtextTargetPlatformFactory platformFactory;

  /** The current platform. */
  private IXtextTargetPlatform platform;

  /** Flag indicating that the target platform manager is shutting down. */
  private volatile boolean shutdownInProgress; // NOPMD (volatile)

  protected IXtextTargetPlatformFactory getPlatformFactory() {
    return platformFactory;
  }

  /** {@inheritDoc} */
  @Override
  public synchronized IXtextTargetPlatform getPlatform() {
    ensureLoaded();
    return platform;
  }

  /**
   * Return the platform, but without making sure it's loaded. Just return the raw attribute.
   *
   * @return the current target platform, or null if none set.
   */
  protected synchronized IXtextTargetPlatform basicGetPlatform() {
    return platform;
  }

  /**
   * Make sure the platform is loaded.
   */
  protected void ensureLoaded() {
    if (platform == null) {
      load(new NullProgressMonitor());
    }
  }

  /**
   * Loads the target platform.
   *
   * @param monitor
   *          progress monitor
   */
  protected void load(final IProgressMonitor monitor) {
    try {
      IXtextTargetPlatform oldPlatform = basicGetPlatform();
      if (oldPlatform != null) {
        oldPlatform.close(monitor);
      }
      IXtextTargetPlatform newPlatform = getPlatformFactory().get(monitor);
      if (newPlatform != null) {
        newPlatform.open(true, monitor);
      }
      setPlatform(newPlatform);
    } catch (CoreException e) {
      throw new WrappedException(e);
    } catch (IOException e) {
      throw new WrappedException(e);
    }
  }

  private final Collection<IXtextTargetPlatformManager.Listener> listeners;

  protected DefaultXtextTargetPlatformManager() {
    listeners = new CopyOnWriteArraySet<IXtextTargetPlatformManager.Listener>();
  }

  /** {@inheritDoc} */
  @Override
  public void addListener(final IXtextTargetPlatformManager.Listener listener) {
    listeners.add(listener);
  }

  /** {@inheritDoc} */
  @Override
  public void removeListener(final IXtextTargetPlatformManager.Listener listener) {
    listeners.remove(listener);
  }

  /** {@inheritDoc} */
  public void shutdown() {
    shutdownInProgress = true;
    listeners.clear();
  }

  public boolean isShuttingDown() {
    return shutdownInProgress;
  }

  /**
   * Send out notifications when the target platform changes.
   *
   * @param newPlatform
   *          used from now on
   * @param deltas
   *          The list of deltas that the switch generated, if known.
   * @param mustRebuild
   *          whether a rebuild is required in any case
   */
  private void notifyListeners(final IXtextTargetPlatform newPlatform, final Collection<IResourceDescription.Delta> deltas, final boolean mustRebuild) {
    if (isShuttingDown()) {
      return;
    }
    for (IXtextTargetPlatformManager.Listener listener : listeners) {
      listener.platformChanged(newPlatform, deltas, mustRebuild);
    }
  }

  /**
   * Set the new platform and notify all listeners.
   *
   * @param newPlatform
   *          The new platform to set.
   */
  public void setPlatform(final IXtextTargetPlatform newPlatform) {
    setPlatform(newPlatform, Collections.<IResourceDescription.Delta> emptyList(), false);
  }

  /**
   * Set the new platform and notify all listeners.
   *
   * @param newPlatform
   *          The new platform to set.
   * @param deltas
   *          The list of deltas that the switch will generate, if known.
   * @param mustRebuild
   *          whether a rebuild is required in any case.
   */
  public synchronized void setPlatform(final IXtextTargetPlatform newPlatform, final Collection<IResourceDescription.Delta> deltas, final boolean mustRebuild) {
    IXtextTargetPlatform oldPlatform = platform;

    if (oldPlatform == null && newPlatform == null) {
      return; // may occur during initialization...
    }

    if (newPlatform == null) {
      this.platform = new NullXtextTargetPlatform();
    } else {
      this.platform = newPlatform;
    }
    notifyListeners(platform, deltas, mustRebuild);
  }

}
