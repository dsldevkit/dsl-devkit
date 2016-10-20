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

import com.avaloq.tools.ddk.xtext.tracing.ResourceEvent;


/**
 * An event representing the unloading of a resource during the build. This event can practically take place at any time, but note that there will be no events
 * of this type as part of a {@link BuildResourceSetClearEvent}.
 */
public class ResourceUnloadEvent extends ResourceEvent {

  /**
   * Creates a new instance of {@link ResourceUnloadEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data, where the first data object is expected to be the resource's {@link org.eclipse.emf.common.util.URI} this event pertains to
   */
  public ResourceUnloadEvent(final Trigger trigger, final Object[] data) {
    super(trigger, data);
  }

}
