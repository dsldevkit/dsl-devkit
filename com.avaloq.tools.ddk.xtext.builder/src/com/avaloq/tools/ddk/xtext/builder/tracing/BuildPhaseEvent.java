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
 * An event representing a phase of the build which typically is a rather long operation.
 */
// CHECKSTYLE:CHECK-OFF AbstractClassName
public abstract class BuildPhaseEvent extends TraceEvent {
  // CHECKSTYLE:CHECK-ON AbstractClassName

  /**
   * Creates a new instance of {@link BuildPhaseEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data
   */
  public BuildPhaseEvent(final Trigger trigger, final Object[] data) {
    super(trigger, data);
  }

}
