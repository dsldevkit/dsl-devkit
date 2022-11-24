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

import com.avaloq.tools.ddk.xtext.tracing.ResourceEvent;


/**
 * An event representing the loading of a resource during the build. This event will typically be a top-level event, as it is produced by an asynchronous
 * resource loader.
 */
public class ResourceLoadEvent extends ResourceEvent {

  /**
   * Creates a new instance of {@link ResourceLoadEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data, where the first data object is expected to be the resource's {@link org.eclipse.emf.common.util.URI} this event pertains to
   */
  public ResourceLoadEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
  }

}
