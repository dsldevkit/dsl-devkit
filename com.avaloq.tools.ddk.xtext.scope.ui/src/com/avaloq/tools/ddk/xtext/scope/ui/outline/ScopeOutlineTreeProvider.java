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
package com.avaloq.tools.ddk.xtext.scope.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;


/**
 * customization of the default outline structure.
 */
@SuppressWarnings("restriction")
public class ScopeOutlineTreeProvider extends DefaultOutlineTreeProvider {

  /**
   * Create the child nodes for a given native Context. We only show quick fixes.
   *
   * @param parentNode
   *          the parent node
   * @param modelElement
   *          the scope model
   */
  // CHECKSTYLE:OFF
  protected void _createChildren(final DocumentRootNode parentNode, final ScopeModel modelElement) {
    // CHECKSTYLE:ON
    Image importContainerImage = JavaPlugin.getImageDescriptorRegistry().get(JavaPluginImages.DESC_OBJS_IMPCONT);

    if (!modelElement.getImports().isEmpty()) {
      createEStructuralFeatureNode(parentNode, modelElement, ScopePackage.Literals.SCOPE_MODEL__IMPORTS, importContainerImage, "import declarations", false); //$NON-NLS-1$
    }

    for (EObject content : modelElement.getScopes()) {
      createNode(parentNode, content);
    }
  }

  /**
   * Specify Expression as leaf.
   *
   * @param modelElement
   *          the expression
   * @return true
   */
  // CHECKSTYLE:OFF
  protected boolean _isLeaf(final Expression modelElement) {
    // CHECKSTYLE:ON
    return true;
  }

  /**
   * Specify ScopeRule as leaf.
   *
   * @param modelElement
   *          the rule
   * @return true
   */
  // CHECKSTYLE:OFF
  protected boolean _isLeaf(final ScopeRule modelElement) {
    // CHECKSTYLE:ON
    return true;
  }

}
