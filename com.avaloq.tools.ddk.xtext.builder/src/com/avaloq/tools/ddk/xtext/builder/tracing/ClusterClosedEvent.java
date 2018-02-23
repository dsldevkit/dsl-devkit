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
package com.avaloq.tools.ddk.xtext.builder.tracing;

import com.avaloq.tools.ddk.xtext.tracing.TraceEvent;


/**
 * Event that occurs when the builder closes a cluster.
 * Holds the size of the cluster as data.
 */
public class ClusterClosedEvent extends TraceEvent {

  /**
   * Creates a new instance of {@link ClusterClosedEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          the first and only item is expected to represent the size of the cluster
   */
  public ClusterClosedEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
  }

}
