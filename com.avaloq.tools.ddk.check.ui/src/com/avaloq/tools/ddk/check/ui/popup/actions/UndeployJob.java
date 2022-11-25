/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.ui.popup.actions;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.avaloq.tools.ddk.check.ui.Messages;
import com.avaloq.tools.ddk.check.ui.internal.Activator;
import com.avaloq.tools.ddk.checkcfg.CheckCfgConstants;


/**
 * Job undeploying the plugin bundle that my have been deployed from this project.
 * If a check configuration file is present, unddeploys it too.
 */
public class UndeployJob extends Job {

  private static final Logger LOGGER = LogManager.getLogger(UndeployJob.class);

  private final IProject project;

  /**
   * Creates a new instance of {@link UndeployJob}.
   *
   * @param name
   *          the name of the job
   * @param project
   *          the project whose bundle should be deployed.
   */
  public UndeployJob(final String name, final IProject project) {
    super(name);
    this.project = project;
  }

  @Override
  protected IStatus run(final IProgressMonitor monitor) {
    Bundle managedBundle = Platform.getBundle(project.getName());
    if (managedBundle == null) {
      LOGGER.info(NLS.bind(Messages.UndeployJob_NoBundleWithSymbolicNameInstalled, project.getName()));
      return new Status(Status.INFO, Activator.getPluginId(), NLS.bind(Messages.UndeployJob_NoBundleWithSymbolicNameInstalled, project.getName()));
    }
    try {
      undeployBundle(managedBundle);
    } catch (BundleException e) {
      LOGGER.error(NLS.bind(Messages.UndeployJob_FailedToStopAndUninstallBundleWithSymbolicName, managedBundle.getSymbolicName()), e);
      return new Status(Status.ERROR, Activator.getPluginId(), NLS.bind(Messages.UndeployJob_FailedToStopAndUninstallBundleWithSymbolicName, managedBundle.getSymbolicName()), e);
    }
    LOGGER.info(NLS.bind(Messages.UndeployJob_DundleUndeployed, managedBundle.getSymbolicName()));

    try {
      undeployCheckConfiguration();
    } catch (CoreException e) {
      LOGGER.error(Messages.UndeployJob_FailedToUndeployCheckCofiguration, e);
      return new Status(Status.ERROR, Activator.getPluginId(), Messages.UndeployJob_FailedToUndeployCheckCofiguration, e);
    }
    return Status.OK_STATUS;

  }

  /**
   * Undeploys the check configuration.
   *
   * @throws CoreException
   *           core exception.
   */
  public static void undeployCheckConfiguration() throws CoreException {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IPathVariableManager pathMan = workspace.getPathVariableManager();
    if (pathMan.getURIValue(CheckCfgConstants.CHECK_CFG_VAR_NAME) == null) {
      return;
    }
    pathMan.setURIValue(CheckCfgConstants.CHECK_CFG_VAR_NAME, null);
  }

  /**
   * Undeploys a bundle.
   *
   * @param managedBundle
   *          the bundle to be undeployed.
   * @throws BundleException
   *           bundle exception.
   */
  public static void undeployBundle(final Bundle managedBundle) throws BundleException {
    managedBundle.stop(Bundle.STOP_TRANSIENT);
    managedBundle.uninstall();
  }
}

