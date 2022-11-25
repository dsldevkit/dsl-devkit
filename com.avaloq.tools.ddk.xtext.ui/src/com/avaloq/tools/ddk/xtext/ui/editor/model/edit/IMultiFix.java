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
package com.avaloq.tools.ddk.xtext.ui.editor.model.edit;

/**
 * An interface that defines the multifix property on (Xtext-based) Quickfixes.
 * A Multifix fixes multiple markers of the provided category, e.g. by invoking the first non-MultiFixResolution for the selected markers.
 */
public interface IMultiFix {

  /**
   * True if the associated quickfix resolution is a multifix resolution.
   * <p>
   * <strong>This must be a fast operation.</strong>
   * </p>
   *
   * @return true if the quickfix is a multi quick-fix.
   */
  boolean isMultiFix();

}
