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
package com.avaloq.tools.ddk.xtext.export.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Interface;


/**
 * customization of the default outline structure.
 */
@SuppressWarnings("restriction")
public class ExportOutlineTreeProvider extends DefaultOutlineTreeProvider {

  /**
   * Create the child nodes for a given export model: its interfaces and exports.
   * 
   * @param parentNode
   *          the parent node
   * @param modelElement
   *          the export model
   */
  // CHECKSTYLE:OFF
  protected void _createChildren(final DocumentRootNode parentNode, final ExportModel modelElement) {
    // CHECKSTYLE:ON
    Image importContainerImage = JavaPlugin.getImageDescriptorRegistry().get(JavaPluginImages.DESC_OBJS_IMPCONT);

    if (!modelElement.getImports().isEmpty()) {
      createEStructuralFeatureNode(parentNode, modelElement, ExportPackage.Literals.EXPORT_MODEL__IMPORTS, importContainerImage, "import declarations", false);
    }
    for (EObject content : modelElement.getInterfaces()) {
      createNode(parentNode, content);
    }
    for (EObject content : modelElement.getExports()) {
      createNode(parentNode, content);
    }
  }

  /**
   * Specify Interface as leaf.
   * 
   * @param modelElement
   *          the interface
   * @return true
   */
  // CHECKSTYLE:OFF
  protected boolean _isLeaf(final Interface modelElement) {
    // CHECKSTYLE:ON
    return true;
  }

  /**
   * Specify Export as leaf.
   * 
   * @param modelElement
   *          the export
   * @return true
   */
  // CHECKSTYLE:OFF
  protected boolean _isLeaf(final Export modelElement) {
    // CHECKSTYLE:ON
    return true;
  }

}
