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

/**
 * An event representing a build's linking phase. This is a top-level event.
 */
public class BuildLinkingEvent extends BuildPhaseEvent {

  /**
   * Creates a new instance of {@link BuildLinkingEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data
   */
  public BuildLinkingEvent(final Trigger trigger, final Object[] data) {
    super(trigger, data);
  }

}
