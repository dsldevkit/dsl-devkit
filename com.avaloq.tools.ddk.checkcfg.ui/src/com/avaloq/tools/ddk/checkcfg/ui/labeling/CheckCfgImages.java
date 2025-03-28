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
package com.avaloq.tools.ddk.checkcfg.ui.labeling;

import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.IImageHelper;

import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.google.inject.Inject;


/**
 * Provides icons for Check Configuration model elements.
 */
public class CheckCfgImages {

  private static final String RESOURCE_IMAGE = "editor.gif";
  private static final String CATEGORY_IMAGE = "category.gif";
  private static final String ERROR_IMAGE = "error.gif";
  private static final String WARNING_IMAGE = "warning.gif";
  private static final String INFO_IMAGE = "info.gif";
  private static final String IGNORE_IMAGE = "ignore.gif";

  @Inject
  private IImageHelper imageHelper;

  /** @return an image representing a Check Configuration resource */
  public Image forResource() {
    return imageHelper.getImage(RESOURCE_IMAGE);
  }

  /** @return an image representing a Check Configuration */
  public Image forCheckConfiguration() {
    return forResource();
  }

  /** @return an image representing a configured category */
  public Image forConfiguredCatalog() {
    return imageHelper.getImage(CATEGORY_IMAGE);
  }

  /**
   * Returns an image for a Configured Check according to its severity (error, warning, info, ignore).
   *
   * @param severity
   *          the severity of the check
   * @return an image representing a Configured Check
   */
  public Image forConfiguredCheck(final SeverityKind severity) {
    switch (severity) {
    case ERROR:
      return imageHelper.getImage(ERROR_IMAGE);
    case WARNING:
      return imageHelper.getImage(WARNING_IMAGE);
    case INFO:
      return imageHelper.getImage(INFO_IMAGE);
    default:
      return imageHelper.getImage(IGNORE_IMAGE);
    }
  }

}
