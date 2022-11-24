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
package com.avaloq.tools.ddk.xtext.builder;

import org.eclipse.osgi.util.NLS;


//CHECKSTYLE:OFF
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.xtext.builder.messages"; //$NON-NLS-1$
  public static String MonitoredClusteringBuilderState_CANNOT_LOAD_RESOURCE;
  public static String MonitoredClusteringBuilderState_NO_MORE_RESOURCES;
  public static String MonitoredClusteringBuilderState_PHASE_ONE_DONE;
  public static String MonitoredClusteringBuilderState_UPDATE_DESCRIPTIONS;
  public static String MonitoredClusteringBuilderState_WRITE_DESCRIPTIONS;
  public static String MonitoredClusteringBuilderState_WRITE_ONE_DESCRIPTION;
  public static String MonitoredClusteringBuilderState_COULD_NOT_PROCESS_DUE_TO_STACK_OVERFLOW_ERROR;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}
