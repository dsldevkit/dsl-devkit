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
package com.avaloq.tools.ddk.xtext.valid.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;

import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeRule;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidModel;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;


/**
 * Customization of the default outline structure.
 */
@SuppressWarnings("restriction")
public class ValidOutlineTreeProvider extends DefaultOutlineTreeProvider {

  /**
   * Do not create Model nodes in the outline. When the valid model is processed it is not
   * added to the outline but its children are.
   * 
   * @param parentNode
   *          the parent node, this should be the tree root
   * @param modelElement
   *          a valid model
   */
  // CHECKSTYLE:OFF
  protected void _createChildren(final DocumentRootNode parentNode, final ValidModel modelElement) {
    // CHECKSTYLE:ON

    Image importContainerImage = JavaPlugin.getImageDescriptorRegistry().get(JavaPluginImages.DESC_OBJS_IMPCONT);

    if (!modelElement.getImports().isEmpty()) {
      createEStructuralFeatureNode(parentNode, modelElement, ValidPackage.Literals.VALID_MODEL__IMPORTS, importContainerImage, "import declarations", false);
    }
    for (EObject content : modelElement.getCategories()) {
      createNode(parentNode, content);
    }
  }

  /**
   * Create the child nodes for a given native Context. We only show quick fixes.
   * 
   * @param parentNode
   *          the parent node
   * @param modelElement
   *          the native context
   */
  // CHECKSTYLE:OFF
  protected void _createChildren(final IOutlineNode parentNode, final NativeContext modelElement) {
    // CHECKSTYLE:ON
    for (EObject content : modelElement.getQuickFixes()) {
      createNode(parentNode, content);
    }
  }

  /**
   * Create the child nodes for a given native native rule: its contexts.
   * 
   * @param parentNode
   *          the parent node
   * @param modelElement
   *          the native rule
   */
  // CHECKSTYLE:OFF
  protected void _createChildren(final IOutlineNode parentNode, final NativeRule modelElement) {
    // CHECKSTYLE:ON
    for (EObject content : modelElement.getContexts()) {
      createNode(parentNode, content);
    }
  }

}
