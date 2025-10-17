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
package com.avaloq.tools.ddk.check.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.BackgroundOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.EStructuralFeatureNode;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.ui.labeling.CheckImages;
import com.google.inject.Inject;


/**
 * Customization of the default outline structure.
 */
public class CheckOutlineTreeProvider extends BackgroundOutlineTreeProvider {

  @Inject
  private CheckImages checkImages;

  @Override
  protected void internalCreateChildren(final DocumentRootNode parentNode, final EObject modelElement) {
    CheckCatalog catalog = (CheckCatalog) modelElement;
    if (catalog.getPackageName() != null) {
      getOutlineNodeFactory().createEStructuralFeatureNode(parentNode, catalog, CheckPackage.Literals.CHECK_CATALOG__PACKAGE_NAME, ImageDescriptor.createFromImage(checkImages.forPackage()), catalog.getPackageName(), true);
    }

    if (catalog.getImports() != null && !catalog.getImports().getImportDeclarations().isEmpty()) {
      EStructuralFeatureNode importNode = getOutlineNodeFactory().createEStructuralFeatureNode(parentNode, catalog, CheckPackage.Literals.CHECK_CATALOG__IMPORTS, ImageDescriptor.createFromImage(checkImages.forImportContainer()), "Import declarations", false); //$NON-NLS-1$
      for (final org.eclipse.xtext.xtype.XImportDeclaration imported : catalog.getImports().getImportDeclarations()) {
        createNode(importNode, imported);
      }
    }

    EObjectNode catalogNode = createNode(parentNode, catalog);

    for (final Category category : catalog.getCategories()) {
      createNode(catalogNode, category);
    }
    for (final Check check : catalog.getChecks()) {
      createNode(catalogNode, check);
    }

  }

  /**
   * Customizes outline nodes for a {@link Category} and {@link Check}.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public void createChildren(final IOutlineNode parentNode, final EObject modelElement) {
    if (modelElement instanceof Category) {
      for (final Check c : ((Category) modelElement).getChecks()) {
        createNode(parentNode, c);
      }
    } else if (modelElement instanceof Check) {
      for (final Context c : ((Check) modelElement).getContexts()) {
        createNode(parentNode, c);
      }
    } else {
      super.createChildren(parentNode, modelElement);
    }
  }

  /**
   * Defines that no children nodes of a {@link Context} are used for creating outline nodes.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected boolean isLeaf(final EObject context) {
    if (context instanceof Context) {
      return true;
    } else if (context instanceof Check) {
      return ((Check) context).getContexts().isEmpty();
    } else {
      return super.isLeaf(context);
    }
  }

}
