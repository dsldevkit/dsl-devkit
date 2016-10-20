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

import org.eclipse.jface.resource.ImageDescriptor;


/**
 * Constants for the ValidPreference and associated classes.
 */
public final class ValidPreferenceConstants {

  /** The lock image descriptor. */
  public static final ImageDescriptor LOCK_IMAGE_DESCRIPTOR = ImageDescriptor.createFromFile(AbstractValidPreferencePage.class, "/icons/big_lock.png"); //$NON-NLS-1$

  /** The lock overlay image descriptor (for decorating other images). */
  public static final ImageDescriptor LOCK_OVERLAY_IMAGE_DESCRIPTOR = ImageDescriptor.createFromFile(AbstractValidPreferencePage.class, "/icons/lock_ovr.gif"); //$NON-NLS-1$

  /** The category image descriptor. */
  public static final ImageDescriptor CATEGORY_IMAGE_DESCRIPTOR = ImageDescriptor.createFromFile(AbstractValidPreferencePage.class, "/icons/category.gif"); //$NON-NLS-1$

  /** The warning image descriptor. */
  public static final ImageDescriptor WARNING_IMAGE_DESCRIPTOR = ImageDescriptor.createFromFile(AbstractValidPreferencePage.class, "/icons/warning.gif"); //$NON-NLS-1$

  /** The error image descriptor. */
  public static final ImageDescriptor ERROR_IMAGE_DESCRIPTOR = ImageDescriptor.createFromFile(AbstractValidPreferencePage.class, "/icons/error.gif"); //$NON-NLS-1$

  /** The separator between fragments in a preference store key. */
  public static final String PREFERENCE_SEPARATOR = "."; //$NON-NLS-1$

  /** Number of quadrants for IDecoration. */
  public static final int DECORATION_QUADRANTS_COUNT = 5;

  /**
   * Private constructor.
   */
  private ValidPreferenceConstants() {}
}
