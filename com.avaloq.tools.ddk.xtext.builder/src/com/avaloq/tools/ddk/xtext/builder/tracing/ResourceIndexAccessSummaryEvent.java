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
 * Event which contains index access statistics for a given resource. This event will typically have a {@link ResourceProcessingEvent} as its parent, but it
 * could also be a more specific event like a {@link ResourceValidationEvent}.
 */
public class ResourceIndexAccessSummaryEvent extends ResourceEvent {

  /**
   * Creates a new instance of {@link ResourceIndexAccessSummaryEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data, where the first data object is expected to be the resource's {@link org.eclipse.emf.common.util.URI} this event pertains to
   */
  public ResourceIndexAccessSummaryEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
  }

}
