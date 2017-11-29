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
package com.avaloq.tools.ddk.xtext.valid.ui.labeling;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.avaloq.tools.ddk.xtext.valid.valid.Category;
import com.avaloq.tools.ddk.xtext.valid.valid.Import;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.QuickFix;
import com.avaloq.tools.ddk.xtext.valid.valid.Rule;
import com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind;


/**
 * Label Provider for the Valid Language (mostly used in outline).
 */
@SuppressWarnings("restriction")
public class ValidLabelProvider extends DefaultEObjectLabelProvider {

  /**
   * Name of the image for Category.
   * 
   * @param category
   *          the category
   * @return name of the image file
   */
  String image(final Category category) {
    return "category.gif"; //$NON-NLS-1$
  }

  /**
   * Name of the image for Rule.
   * 
   * @param rule
   *          the rule
   * @return name of the image file
   */
  String image(final Rule rule) {
    return rule.getSeverity() == SeverityKind.ERROR ? "error.gif" : "warning.gif"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Name of the image for QuickFix.
   * 
   * @param quickFix
   *          the quick fix
   * @return name of the image file
   */
  String image(final QuickFix quickFix) {
    return "quickfix.gif"; //$NON-NLS-1$
  }

  /**
   * The image for Import.
   * 
   * @param impaurt
   *          :-)
   *          the import
   * @return the import image
   */
  Image image(final Import impaurt) {
    return JavaPlugin.getImageDescriptorRegistry().get(JavaPluginImages.DESC_OBJS_IMPDECL);
  }

  /**
   * Text label for Import.
   * 
   * @param impaurt
   *          the import
   * @return the label, evtl. "unresolved ..." if the reference is not found
   */
  String text(final Import impaurt) {
    return (impaurt.getPackage() != null) ? impaurt.getPackage().getName() : "<unresolved Package>"; //$NON-NLS-1$
  }

  /**
   * Name of the image for NativeContext.
   * 
   * @param nativeContext
   *          the native context
   * @return name of the image file
   */
  String image(final NativeContext nativeContext) {
    return "eclass.gif"; //$NON-NLS-1$
  }

  /**
   * Text label for NativeContext.
   * 
   * @param nativeContext
   *          the native context
   * @return the label, evtl. "unresolved ..." if the reference is not found
   */

  String text(final NativeContext nativeContext) {
    return (nativeContext.getContextType() != null) ? nativeContext.getContextType().getName() : "<unresolved EClass>"; //$NON-NLS-1$
  }

}
