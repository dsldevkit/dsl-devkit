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
package com.avaloq.tools.ddk.xtext.builder.tracing;

import com.avaloq.tools.ddk.xtext.tracing.TraceEvent;


/**
 * An event representing the retrieval of a loaded resource during the build.
 */
public class LoaderDequeueEvent extends TraceEvent {

  /**
   * Creates a new instance of {@link LoaderDequeueEvent}.
   *
   * @param trigger
   *          event {@link Trigger}, must not be {@code null}
   * @param data
   *          event data, must not be {@code null}
   */
  public LoaderDequeueEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
  }
}
