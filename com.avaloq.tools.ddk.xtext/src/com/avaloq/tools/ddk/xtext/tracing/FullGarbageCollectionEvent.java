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

package com.avaloq.tools.ddk.xtext.tracing;

import java.util.Arrays;


/**
 * An event representing the occurrence of a full GC in the JVM. As this event is already delivered asynchronously from the JVM it cannot reliably be correlated
 * with other events.
 */
public class FullGarbageCollectionEvent extends TraceEvent {

  private final long time;
  private final Object[] data;

  public FullGarbageCollectionEvent(final Trigger trigger, final Object... data) {
    super(trigger);
    this.time = (Long) data[0];
    this.data = new Object[data.length - 1];
    System.arraycopy(data, 1, this.data, 0, this.data.length);
  }

  @Override
  public long getTime() {
    return time;
  }

  @Override
  public Object[] getData() {
    return Arrays.copyOf(data, data.length);
  }

}
