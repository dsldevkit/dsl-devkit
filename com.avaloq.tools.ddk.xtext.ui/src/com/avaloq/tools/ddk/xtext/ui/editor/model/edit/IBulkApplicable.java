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
package com.avaloq.tools.ddk.xtext.ui.editor.model.edit;

/**
 * An interface that defines the bulk-applicability property on (Xtext-based) Quickfixes.
 * A quickfix is bulk applicable if it can be applied across multiple markers in the same
 * resource without having to recalculate the markers after each application, e.g.
 * when used form the problems view.
 */
public interface IBulkApplicable {

  /**
   * True if the associated quickfix resolution is bulk-applicable.
   * <p>
   * <strong>This must be a fast operation.</strong>
   * </p>
   *
   * @return true if the quickfix is bulk-applicable.
   */
  boolean isBulkApplicable();

}
