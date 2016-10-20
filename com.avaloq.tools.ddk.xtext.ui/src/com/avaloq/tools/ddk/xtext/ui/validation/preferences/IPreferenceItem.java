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

import org.eclipse.swt.graphics.Image;


/**
 * The Interface IPreferenceItem specifies the properties of the items that may
 * appear in the {@link ValidPreferenceCheckedTreeViewer} on the {@link ValidPreferencePage}.
 */
interface IPreferenceItem {

  /**
   * Gets the label of the item. (It appears on the node in the Treeviewer.)
   *
   * @return the label
   */
  String getLabel();

  /**
   * Gets the (possibly decorated with a lock) image of the item. (It appears
   * on the node in the Treeviewer.)
   *
   * @return the image
   */
  Image getImage();

  /**
   * Gets the "raw" undecorated image that corresponds to the kind of item, no
   * matter if it's locked (mandatory) or not (optional).
   *
   * @return the undecorated image
   */
  Image getUndecoratedImage();

  /**
   * Gets the description (which appears on the pane under the TreeViewer).
   *
   * @return the description
   */
  String getDescription();

  /**
   * Gets the parent (for rule, the category to which it belongs, otherwise
   * null).
   *
   * @return the parent
   */
  IPreferenceItem getParent();

  /**
   * Gets the key that is used to uniquely identify the item and to calculate
   * the property's name under which the item is stored in the
   * PreferenceStore. (This item is prefixed and suffixed in [@link
   * PreferencePage).)
   *
   * @return the key
   */
  String getKey();

}