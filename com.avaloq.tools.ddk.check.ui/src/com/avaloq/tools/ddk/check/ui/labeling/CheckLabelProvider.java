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
package com.avaloq.tools.ddk.check.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.xbase.ui.labeling.XbaseImages2;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;
import org.eclipse.xtext.xtype.XImportDeclaration;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.ContextVariable;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.Member;
import com.google.inject.Inject;


/**
 * Provides labels and images for EObjects.
 */
public class CheckLabelProvider extends XbaseLabelProvider {

  @Inject
  private CheckImages images;

  @Inject
  private XbaseImages2 xbaseImages;

  @Inject
  public CheckLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }

  /**
   * A text for context labels.
   * 
   * @param context
   *          any context
   * @return a textual representation of the context
   */
  public String text(final Context context) {
    ContextVariable var = context.getContextVariable();
    if (var != null) {
      JvmTypeReference type = var.getType();
      if (type != null) {
        return type.getSimpleName();
      }
    }
    return null;
  }

  /**
   * A text for check labels.
   * 
   * @param check
   *          any check
   * @return a textual representation of the check
   */
  public String text(final Check check) {
    return check.getName();
  }

  /**
   * A text for category labels.
   * 
   * @param category
   *          any category
   * @return a textual representation of the category
   */
  public String text(final Category category) {
    return category.getName();
  }

  /**
   * An image for catalog labels.
   * 
   * @param catalog
   *          any catalog
   * @return a graphical representation of the catalog
   */
  public Image image(final CheckCatalog catalog) {
    return images.forCheckCatalog();
  }

  /**
   * An image for category labels.
   * 
   * @param category
   *          any category
   * @return a graphical representation of the category
   */
  public Image image(final Category category) {
    return images.forCategory();
  }

  /**
   * An image for check labels.
   * 
   * @param check
   *          any check
   * @return a graphical representation of the check
   */
  public Image image(final Check check) {
    return images.forCheck(check.getDefaultSeverity());
  }

  /**
   * An image for context labels.
   * 
   * @param context
   *          any context
   * @return a graphical representation of the context
   */
  public Image image(final Context context) {
    return images.forContext();
  }

  /**
   * An image for member labels.
   * 
   * @param context
   *          any context
   * @return a graphical representation of the context
   */
  public Image image(final Member context) {
    return images.getJdtImage(xbaseImages.forField(JvmVisibility.PRIVATE, 0));
  }

  /**
   * An image for member labels.
   * 
   * @param context
   *          any context
   * @return a graphical representation of the context
   */
  public Image image(final ContextVariable context) {
    return images.getJdtImage(xbaseImages.forLocalVariable(0));
  }

  /**
   * An image for member labels.
   * 
   * @param context
   *          any context
   * @return a graphical representation of the context
   */
  public Image image(final FormalParameter context) {
    return images.getJdtImage(xbaseImages.forLocalVariable(0));
  }

  /**
   * A label for imports. Is missing in xbase super class, but present in XtendLabelProvider.
   * 
   * @param context
   *          the import
   * @return its label.
   */
  @Override
  public String text(final XImportDeclaration context) {
    String namespace = context.getImportedNamespace();
    if (namespace != null) {
      return namespace;
    }
    return context.getImportedTypeName();
  }
}
