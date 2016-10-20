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
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.util.Strings;


/**
 * The Class PreferenceRule represents the Valid rules in the model for the {@link ValidPreferenceCheckedTreeViewer}.
 */
class PreferenceRule extends AbstractPreferenceItem implements IPreferenceItem {

  /** The message. */
  private final String message;

  /** True if the rule is optional. */
  private final boolean optional;

  /** The severity (error/warning). */
  private final SeverityKind severity;

  /** The evaluation mode (Fast, Normal, Expensive). */
  private final EvaluationModeKind evaluationMode;

  /**
   * Instantiates a new preference rule.
   *
   * @param id
   *          the id
   * @param label
   *          the label
   * @param description
   *          the description
   * @param message
   *          the message
   * @param severity
   *          the severity
   * @param evaluationMode
   *          the evaluation mode
   * @param isOptional
   *          true if the rule is optional
   */
  PreferenceRule(final String id, final String label, final String description, final String message, final SeverityKind severity, final EvaluationModeKind evaluationMode, final boolean isOptional) {
    super(id, label, description);
    this.message = message;
    this.optional = isOptional;
    this.severity = severity;
    this.evaluationMode = evaluationMode;
  }

  /** {@inheritDoc} */
  @Override
  public Image getImage() {
    if (isOptional()) {
      // No decoration
      return getUndecoratedImage();
    } else {
      final ImageDescriptor[] descriptors = new ImageDescriptor[ValidPreferenceConstants.DECORATION_QUADRANTS_COUNT];
      descriptors[IDecoration.BOTTOM_RIGHT] = ValidPreferenceConstants.LOCK_OVERLAY_IMAGE_DESCRIPTOR;
      // Overlay custom image over base image
      return getImageManager().createImage(new DecorationOverlayIcon(getUndecoratedImage(), descriptors));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Image getUndecoratedImage() {
    return severity.getImage();
  }

  /**
   * Checks if the rule is optional.
   *
   * @return true if the rule is optional
   */
  public boolean isOptional() {
    return optional;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return AbstractValidPreferencePage.replace(message, "\\{[0-9]+\\}", "..."); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * The Enum SeverityKind.
   */
  public enum SeverityKind {

    /** An ERROR. */
    ERROR(ValidPreferenceConstants.ERROR_IMAGE_DESCRIPTOR),
    /** A WARNING. */
    WARNING(ValidPreferenceConstants.WARNING_IMAGE_DESCRIPTOR);

    /** The image descriptor. */
    private ImageDescriptor imageDescriptor;

    /**
     * Instantiates a new severity kind.
     *
     * @param imageDescriptor
     *          the image descriptor
     */
    SeverityKind(final ImageDescriptor imageDescriptor) {
      this.imageDescriptor = imageDescriptor;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage() {
      return imageDescriptor.createImage();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
      return Strings.toFirstUpper(super.toString().toLowerCase());
    }
  }

  /**
   * Gets the severity.
   *
   * @return the severity
   */
  public SeverityKind getSeverity() {
    return severity;
  }

  /**
   * The Enum EvaluationModeKind.
   */
  public enum EvaluationModeKind {

    /** FAST. */
    FAST,
    /** NORMAL. */
    NORMAL,
    /** EXPENSIVE. */
    EXPENSIVE;

    /** {@inheritDoc} */
    @Override
    public String toString() {
      return Strings.toFirstUpper(super.toString().toLowerCase());
    }

  }

  /**
   * Gets the evaluation mode.
   *
   * @return the evaluation mode
   */
  public EvaluationModeKind getEvaluationMode() {
    return evaluationMode;
  }

}