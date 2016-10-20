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
 * An event representing the clearing of the builder's main resource set. This event will have a {@link BuildIndexingEvent} or a {@link BuildLinkingEvent} as
 * its parent.
 */
public class BuildResourceSetClearEvent extends BuildPhaseEvent {

  /**
   * Creates a new instance of {@link BuildResourceSetClearEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data
   */
  public BuildResourceSetClearEvent(final Trigger trigger, final Object[] data) {
    super(trigger, data);
  }

}
