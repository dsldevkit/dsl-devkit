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
package com.avaloq.tools.ddk.xtext.ui.validation.preferences;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * The Class ValidModelTreeLabelProvider.
 */
class ValidModelTreeLabelProvider extends BaseLabelProvider implements ILabelProvider {

  /** {@inheritDoc} */
  @Override
  public Image getImage(final Object treeitem) {
    return ((IPreferenceItem) treeitem).getImage();
  }

  /** {@inheritDoc} */
  @Override
  public String getText(final Object treeitem) {
    return ((IPreferenceItem) treeitem).getLabel();
  }

}
