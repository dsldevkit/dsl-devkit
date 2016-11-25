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
package com.avaloq.tools.ddk.xtext.ui.editor.findrefs;

import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchViewTreeNode;

import com.google.common.base.Supplier;


/**
 * Custom ReferenceSearchViewTreeNode implementation which dynamically computes its label using a supplier.
 */
public class DynamicReferenceSearchViewTreeNode extends ReferenceSearchViewTreeNode {

  private final Supplier<?> labelDescriptionSupplier;

  public DynamicReferenceSearchViewTreeNode(final ReferenceSearchViewTreeNode parent, final Object description, final Supplier<?> labelDescriptionSupplier) {
    super(parent, description, null);
    this.labelDescriptionSupplier = labelDescriptionSupplier;
  }

  @Override
  public Object getLabelDescription() {
    return labelDescriptionSupplier.get();
  }
}
