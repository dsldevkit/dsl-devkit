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
package com.avaloq.tools.ddk.xtextspy;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.google.inject.Singleton;


/**
 * ContentProvider for {@link EClassTypeViewer} - provides the (super-)type hierarchy for an EClass instance.
 */
@Singleton
public class EClassTypeContentProvider implements ITreeContentProvider {

  /** {@inheritDoc} */
  public void dispose() {}

  /** {@inheritDoc} */
  public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {}

  /** {@inheritDoc} */
  public Object[] getElements(final Object inputElement) {
    if (inputElement instanceof EClass) {
      return new EClassNode[] {new EClassNode((EClass) inputElement, null)};
    } else if (inputElement instanceof EObject) {
      return getElements(((EObject) inputElement).eClass());
    }
    return new Object[] {};
  }

  /** {@inheritDoc} */
  public Object[] getChildren(final Object parentElement) {
    if (parentElement instanceof EClassNode) {
      Collection<EClassNode> children = ((EClassNode) parentElement).getChildren();
      return children.toArray(new EClassNode[children.size()]);
    }
    return new Object[] {};
  }

  /** {@inheritDoc} */
  public Object getParent(final Object element) {
    if (element instanceof EClassNode) {
      return ((EClassNode) element).getParent();
    }
    return null;
  }

  /** {@inheritDoc} */
  public boolean hasChildren(final Object element) {
    if (element instanceof EClassNode) {
      return ((EClassNode) element).hasChildren();
    }
    return false;
  }

}

