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
package com.avaloq.tools.ddk.xtext.export.ui.labeling;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Import;
import com.avaloq.tools.ddk.xtext.export.export.Interface;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.google.inject.Inject;


/**
 * Provides labels for a EObjects.
 */
@SuppressWarnings("restriction")
public class ExportLabelProvider extends DefaultEObjectLabelProvider {

  /**
   * Creator.
   * 
   * @param delegate
   *          The delegate to use.
   */
  @Inject
  public ExportLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }

  /**
   * The serializer to use.
   */
  @Inject
  private ISerializer serializer;

  /**
   * Some internal label constants.
   */
  private static final String EXPORTS_LABEL = "exports"; //$NON-NLS-1$
  private static final String EXPORT_LABEL = "export "; //$NON-NLS-1$
  private static final String INTERFACE_LABEL = "interface "; //$NON-NLS-1$
  private static final String UNKNOWN_TYPE_NAME = "<unknown type>"; //$NON-NLS-1$

  /**
   * Return label text for an Exports object.
   * 
   * @param context
   *          The Exports object.
   * @return The label text.
   */
  public String text(final ExportModel context) {
    final EList<Import> importedPackages = context.getImports();
    if (importedPackages.isEmpty() || importedPackages.get(0) == null) {
      return EXPORTS_LABEL;
    }
    return EXPORTS_LABEL + ' ' + importedPackages.get(0).getPackage().getName();
  }

  /**
   * Return the label to use for an Interface object.
   * 
   * @param context
   *          The Interface object
   * @return The label text
   */
  public String text(final Interface context) {
    if (context.getType() == null) {
      return INTERFACE_LABEL + UNKNOWN_TYPE_NAME;
    }
    return INTERFACE_LABEL + context.getType().getName();
  }

  /** Trim and add an ellipsis for longer labels. */
  private static final int MAXIMUM_LABEL_LENGTH = 30;

  /**
   * Return label text for an Export.
   * 
   * @param context
   *          The Export.
   * @return The label text.
   */
  public String text(final Export context) {
    if (context.getType() == null) {
      return EXPORT_LABEL + UNKNOWN_TYPE_NAME;
    }
    Expression namingExpression = context.getNaming();
    if (namingExpression == null) {
      return EXPORT_LABEL + context.getType().getName();
    }
    String naming = serializer.serialize(context.getNaming());
    if (naming.length() > MAXIMUM_LABEL_LENGTH) {
      naming = naming.substring(0, MAXIMUM_LABEL_LENGTH) + "..."; //$NON-NLS-1$
    }
    return EXPORT_LABEL + context.getType().getName() + " as " + naming; //$NON-NLS-1$
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

}

