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
package com.avaloq.tools.ddk.check.ui;

import org.eclipse.osgi.util.NLS;


/**
 * TODO: Document type Messages.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.check.ui.messages"; //$NON-NLS-1$
  public static String DeployAction_DeployBundleLocally;
  public static String DeployJob_DialogOk;
  public static String DeployJob_CouldNotDeployCheckBundle;
  public static String DeployJob_BundleAlreadyDeployed;
  public static String DeployJob_TooManyCheckConfigurations;
  public static String DeployJob_CannotDeployMoreThanOneCheckConfiguration;
  public static String DeployJob_CouldNotCreateTemporaryDirectoryForJar;
  public static String DeployJob_CouldNotSetPathVariable;
  public static String DeployJob_CouldntGetBundleLocation;
  public static String DeployJob_ExceptionWhileReadingTheCheckConfigurationFile;
  public static String DeployJob_FailedToInstallAndStartBundle;
  public static String DeployJob_FailedToReadBundle;
  public static String UndeployAction_UndeployLocalBundle;
  public static String UndeployJob_DundleUndeployed;
  public static String UndeployJob_FailedToStopAndUninstallBundleWithSymbolicName;
  public static String UndeployJob_FailedToUndeployCheckCofiguration;
  public static String UndeployJob_NoBundleWithSymbolicNameInstalled;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {}
}

