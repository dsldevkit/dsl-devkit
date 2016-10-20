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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;


/**
 * The Class PreferenceCategory represents the Valid's categories in the model
 * for the {@link ValidPreferenceCheckedTreeViewer}.
 */
class PreferenceCategory extends AbstractPreferenceItem implements IPreferenceItem {

  /** The rules defined for that category. */
  private final List<PreferenceRule> rules;
  private boolean onlyMandatoryRules = true;
  private boolean mandatoryRules;

  /**
   * Instantiates a new preference category.
   *
   * @param id
   *          the id
   * @param label
   *          the label
   * @param description
   *          the description
   */
  PreferenceCategory(final String id, final String label, final String description) {
    super(id, label, description);
    rules = new ArrayList<PreferenceRule>();
  }

  /** {@inheritDoc} */
  @Override
  public Image getImage() {
    final Image categoryImage = getUndecoratedImage();
    if (hasRules() && hasOnlyMandatoryRules()) {
      final ImageDescriptor[] descriptors = new ImageDescriptor[ValidPreferenceConstants.DECORATION_QUADRANTS_COUNT];
      descriptors[IDecoration.BOTTOM_RIGHT] = ValidPreferenceConstants.LOCK_OVERLAY_IMAGE_DESCRIPTOR;
      // Overlay custom image over base image
      return getImageManager().createImage(new DecorationOverlayIcon(categoryImage, descriptors));
    } else {
      // No decoration
      return categoryImage;
    }
  }

  /** {@inheritDoc} */
  @Override
  public Image getUndecoratedImage() {
    return ValidPreferenceConstants.CATEGORY_IMAGE_DESCRIPTOR.createImage();
  }

  /**
   * Adds a rule to the category.
   *
   * @param rule
   *          the rule
   */
  public void addRule(final PreferenceRule rule) {
    rules.add(rule);
    if (rule.isOptional()) {
      onlyMandatoryRules = false;
    } else {
      mandatoryRules = true;
    }
    rule.setParent(this);
  }

  /**
   * Gets the rules in the category.
   *
   * @return the rules
   */
  public IPreferenceItem[] getRules() {
    return rules.toArray(new IPreferenceItem[rules.size()]);
  }

  /**
   * Checks whether the category contains rules.
   *
   * @return true, if successful
   */
  public boolean hasRules() {
    return !rules.isEmpty();
  }

  /**
   * Checks whether the category contains only mandatory rules.
   *
   * @return true, if the category has rules and all its rules are mandatory. Returns false if the category contains no rules.
   */
  public boolean hasOnlyMandatoryRules() {
    return hasRules() && onlyMandatoryRules;
  }

  /**
   * Checks if the category contains mandatory rules.
   *
   * @return true, if successful
   */
  public boolean hasMandatoryRules() {
    return mandatoryRules;
  }

}