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

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ResourceManager;


/**
 * The Class AbstractPreferenceItem defines a basic implementation for the items that may appear in the {@link ValidPreferenceCheckedTreeViewer} on the
 * {@link ValidPreferencePage}.
 */
abstract class AbstractPreferenceItem implements IPreferenceItem {

  /** The id. */
  private final String id;

  /** The label. */
  private final String label;

  /** The parent. */
  private IPreferenceItem parent;

  /** The description. */
  private final String description;

  /**
   * Instantiates a new abstract preference item.
   *
   * @param id
   *          the id
   * @param label
   *          the label
   * @param description
   *          the description
   */
  AbstractPreferenceItem(final String id, final String label, final String description) {
    this.id = id;
    this.label = label;
    this.description = description;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public String getLabel() {
    return label;
  }

  /** {@inheritDoc} */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Sets the parent.
   *
   * @param parent
   *          the new parent
   */
  public void setParent(final IPreferenceItem parent) {
    this.parent = parent;
  }

  /** {@inheritDoc} */
  @Override
  public IPreferenceItem getParent() {
    return parent;
  }

  /**
   * Gets the image manager.
   *
   * @return the image manager
   */
  protected ResourceManager getImageManager() {
    return JFaceResources.getResources();
  }

  /** {@inheritDoc} */
  @Override
  public String getKey() {
    return ((getParent() == null) ? "" : getParent().getKey() + ValidPreferenceConstants.PREFERENCE_SEPARATOR) + getId(); //$NON-NLS-1$
  }

}
