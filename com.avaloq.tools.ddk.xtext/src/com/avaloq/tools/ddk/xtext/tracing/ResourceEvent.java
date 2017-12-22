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

import org.eclipse.emf.common.util.URI;


/**
 * An event representing an operation on a resource.
 */
// CHECKSTYLE:CHECK-OFF AbstractClassName
public abstract class ResourceEvent extends TraceEvent {
  // CHECKSTYLE:CHECK-ON AbstractClassName

  private static final String RESOURCE_PREFIX = "Resource"; //$NON-NLS-1$
  private static final String EVENT_SUFFIX = "Event"; //$NON-NLS-1$

  private URI uri;

  /**
   * Creates a new instance of {@link ResourceEvent}.
   *
   * @param trigger
   *          event {@link Trigger}, must not be {@code null}
   * @param data
   *          event data, where the first data object is expected to be the resource's {@link URI} this event pertains to, must not be {@code null}
   */
  public ResourceEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
    if (data.length > 0) {
      uri = (URI) data[0];
    }
  }

  /**
   * Returns the {@link URI} this event pertains to.
   *
   * @return the {@link URI} this event pertains to, or {@code null} if none
   */
  public URI getURI() {
    return uri;
  }

  /**
   * Returns the display name for this event.
   *
   * @return the display name for this event, never {@code null}
   */
  public String getDisplayName() {
    String result = getClass().getSimpleName();
    if (result.startsWith(RESOURCE_PREFIX)) {
      result = result.substring(RESOURCE_PREFIX.length());
    }
    if (result.endsWith(EVENT_SUFFIX)) {
      result = result.substring(0, result.length() - EVENT_SUFFIX.length());
    }
    return result;
  }
}
