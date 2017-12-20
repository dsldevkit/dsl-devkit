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

/**
 * An event representing the inference of model elements during the build.
 */
public class ResourceInferenceEvent extends ResourceEvent {

  /**
   * Creates a new instance of {@link ResourceInferenceEvent}.
   *
   * @param trigger
   *          event {@link Trigger}, must not be {@code null}
   * @param data
   *          event data, where the first data object is expected to be the resource's {@link org.eclipse.emf.common.util.URI} this event pertains to, must not
   *          be {@code null}
   */
  public ResourceInferenceEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
  }
}
