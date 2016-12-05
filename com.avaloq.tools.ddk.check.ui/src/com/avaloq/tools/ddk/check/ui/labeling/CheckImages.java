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
package com.avaloq.tools.ddk.check.ui.labeling;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.viewsupport.JavaElementImageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.IImageHelper;

import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.google.inject.Inject;


/**
 * The Class CheckImages provides icons for check model elements.
 */
@SuppressWarnings("restriction")
public class CheckImages {

  private static final String RESOURCE_IMAGE = "editor.gif";

  private static final String CATEGORY_IMAGE = "category.gif";

  private static final String SEVERITY_RANGE_IMAGE = "range.gif";

  private static final String ERROR_IMAGE = "error.gif";

  private static final String WARNING_IMAGE = "warning.gif";

  private static final String INFO_IMAGE = "info.gif";

  private static final String IGNORE_IMAGE = "ignore.gif";

  @Inject
  private IImageHelper imageHelper;

  /** @return an image representing a Java package */
  public Image forPackage() {
    return getJdtImage(JavaPluginImages.DESC_OBJS_PACKAGE);
  }

  /** @return an image representing a Check resource */
  public Image forResource() {
    return imageHelper.getImage(RESOURCE_IMAGE);
  }

  /** @return an image representing a JDT import container. */
  public Image forImportContainer() {
    return getJdtImage(JavaPluginImages.DESC_OBJS_IMPCONT);
  }

  /** @return an image representing a Java import statement. */
  public Image forImport() {
    return getJdtImage(JavaPluginImages.DESC_OBJS_IMPDECL);
  }

  /** @return an image representing a Check catalog. */
  public Image forCheckCatalog() {
    return forResource();
  }

  /** @return an image representing a category. */
  public Image forCategory() {
    return imageHelper.getImage(CATEGORY_IMAGE);
  }

  /** @return an image representing a category. */
  public Image forSeverityRange() {
    return imageHelper.getImage(SEVERITY_RANGE_IMAGE);
  }

  /**
   * Returns an image for a Check according to its severity (error, warning, info, ignore).
   * 
   * @param severity
   *          the severity of the check
   * @return an image representing a Check
   */
  public Image forCheck(final SeverityKind severity) {
    switch (severity) {
    case ERROR:
      return imageHelper.getImage(ERROR_IMAGE);
    case WARNING:
      return imageHelper.getImage(WARNING_IMAGE);
    case INFO:
      return imageHelper.getImage(INFO_IMAGE);
    default:
    case IGNORE:
      return imageHelper.getImage(IGNORE_IMAGE);
    }
  }

  /** @return an image representing a Check context. */
  public Image forContext() {
    return getJdtImage(JavaElementImageProvider.getTypeImageDescriptor(false, true, Flags.AccPublic, false));
  }

  /**
   * Return an Image shared by JDT.
   * 
   * @param descriptor
   *          an image descriptor (path)
   * @return the corresponding image
   */
  public Image getJdtImage(final ImageDescriptor descriptor) {
    return JavaPlugin.getImageDescriptorRegistry().get(descriptor);
  }

}

