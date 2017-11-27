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
package com.avaloq.tools.ddk.xtext.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetHandle;
import org.eclipse.pde.core.target.ITargetLocation;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.LoadTargetDefinitionJob;
import org.eclipse.pde.internal.core.target.TargetPlatformService;
import org.osgi.framework.Bundle;


/**
 * Workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=343152 (Plugin projects created in tests
 * cannot resolve required bundles -- not even org.eclipse.core.resources due to a mismatch between PDE
 * and OSGI.) Code originally from
 * https://github.com/eclipse/gmf-tooling/blob/master/tests/org.eclipse.gmf.tests/src/org/eclipse/gmf/tests/Utils.java
 * and adapted to our uses.
 */
@SuppressWarnings("restriction")
public final class TargetDefinitionSetup {

  /** Name of our generated target definition. */
  private static final String TARGET_PLATFORM_NAME = "Tycho platform"; //$NON-NLS-1$

  private TargetDefinitionSetup() {
    // Prevent instantiation
  }

  /**
   * If necessary, creates and loads a new target platform. Waits until the new platform has been loaded.
   *
   * @throws IOException
   *           if locating bundle file fails
   */
  public static void initializeTargetPlatform() throws IOException {
    // The work-around is creating a new target platform adding the parent folders of the bundles
    try {
      final ITargetPlatformService targetPlatformService = TargetPlatformService.getDefault();
      final ITargetHandle workspaceTargetHandle = targetPlatformService.getWorkspaceTargetHandle();
      if (workspaceTargetHandle == null || !TARGET_PLATFORM_NAME.equals(workspaceTargetHandle.getTargetDefinition().getName())) {
        final ITargetDefinition targetDefinition = targetPlatformService.newTarget();
        targetDefinition.setName(TARGET_PLATFORM_NAME);
        final List<ITargetLocation> bundleContainers = new ArrayList<ITargetLocation>();
        final Set<String> dirs = new HashSet<String>();
        for (final Bundle bundle : Platform.getBundle("org.eclipse.core.runtime").getBundleContext().getBundles()) { //$NON-NLS-1$
          final File file = FileLocator.getBundleFile(bundle);
          final File folder = file.getParentFile();
          final String path = folder.getAbsolutePath();
          if (dirs.add(path)) {
            ITargetLocation newDirectoryLocation = targetPlatformService.newDirectoryLocation(path);
            bundleContainers.add(newDirectoryLocation);
          }
        }
        targetDefinition.setTargetLocations(bundleContainers.toArray(new ITargetLocation[bundleContainers.size()]));
        targetDefinition.setArch(Platform.getOSArch());
        targetDefinition.setOS(Platform.getOS());
        targetDefinition.setWS(Platform.getWS());
        targetDefinition.setNL(Platform.getNL());
        targetPlatformService.saveTargetDefinition(targetDefinition);

        final CountDownLatch platformLoaded = new CountDownLatch(1);
        LoadTargetDefinitionJob.load(targetDefinition, new JobChangeAdapter() {
          @Override
          public void done(final IJobChangeEvent event) {
            platformLoaded.countDown();
          }
        });

        platformLoaded.await();

      }
    } catch (final CoreException coreException) {
      // Nothing
    } catch (InterruptedException e) {
      // Restore interrupted status
      Thread.currentThread().interrupt();
    }
  }

}

