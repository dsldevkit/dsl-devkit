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
package com.avaloq.tools.ddk.xtext.tracing;

import com.google.inject.Inject;


/**
 * Utility methods related to tracing.
 */
public class TraceSetUtil {

  @Inject
  private IExecutionDataCollector dataCollector;

  /**
   * Checks if tracing is enabled.
   *
   * @return true if trace set is instantiated and enabled, false otherwise
   */
  public boolean isTracingEnabled() {
    return dataCollector.isEnabled() && dataCollector instanceof ITraceSet;
  }
}
