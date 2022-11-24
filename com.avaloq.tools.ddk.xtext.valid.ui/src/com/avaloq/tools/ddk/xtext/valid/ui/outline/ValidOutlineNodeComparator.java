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
package com.avaloq.tools.ddk.xtext.valid.ui.outline;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.actions.SortOutlineContribution;
import org.eclipse.xtext.ui.editor.outline.impl.EStructuralFeatureNode;

import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;


/**
 * Comparator used to sort imports group first in outline.
 */
public class ValidOutlineNodeComparator extends SortOutlineContribution.DefaultComparator {

  private static final int IMPORTS_ORDER = -10;

  @Override
  public int getCategory(final IOutlineNode node) {
    if (node instanceof EStructuralFeatureNode) {
      EStructuralFeature eStructuralFeature = ((EStructuralFeatureNode) node).getEStructuralFeature();
      if (eStructuralFeature == ValidPackage.Literals.VALID_MODEL__IMPORTS) {
        return IMPORTS_ORDER;
      }
    }
    return 0;
  }
}

