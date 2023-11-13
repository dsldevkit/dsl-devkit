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
package com.avaloq.tools.ddk.xtextspy;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;

import com.avaloq.tools.ddk.xtextspy.EObjectContentProvider.AttributeValuePair;


/**
 * Provides a tree view on the currently selected EObject.
 * The viewer is intended to display information provided by {@link EObjectContentProvider}.
 */
public class EObjectOutline extends TreeViewer implements ISelectionChangedListener {

  private final ViewerComparator sorter = new EObjectOutlineSorter();
  private EClass selectedElementType;

  public EObjectOutline(final Composite parent, final int style) {
    super(parent, style);
    setAutoExpandLevel(ALL_LEVELS);
    setComparator(sorter);
  }

  @Override
  public void selectionChanged(final SelectionChangedEvent event) {
    if (!event.getSelection().isEmpty() && event.getSelectionProvider() instanceof XtextElementSelectionListener) {
      selectedElementType = ((XtextElementSelectionListener) event.getSelectionProvider()).getSelectedElementType();
      setInput(selectedElementType);
    } else if (selectedElementType != null && event.getSelection() instanceof IStructuredSelection) {
      Object element = ((IStructuredSelection) event.getSelection()).getFirstElement();
      if (element instanceof EClassNode && ((EClassNode) element).getEClass().isSuperTypeOf(selectedElementType)) {
        setInput(((EClassNode) element).getEClass());
      }
    }
  }

  /**
   * ViewerSorter that provides sorting by EClass and by attribute/feature/operation.
   */
  public static class EObjectOutlineSorter extends ViewerComparator {
    private static final int OPERATION_CATEGORY = 3;
    private static final int REFERENCE_CATEGORY = 2;
    private static final int ATTRIBUTE_CATEGORY = 1;
    private static final int MAGNITUDE = 100;
    private boolean groupByEClass;
    private boolean groupByElementKind;

    @Override
    public int category(final Object element) {
      int category = 1;
      if (groupByElementKind) {
        category *= MAGNITUDE;
        if (element instanceof AttributeValuePair) {
          category += ATTRIBUTE_CATEGORY;
        } else if (element instanceof EStructuralFeature) {
          category += REFERENCE_CATEGORY;
        } else if (element instanceof EOperation) {
          category += OPERATION_CATEGORY;
        }
      }
      if (groupByEClass) {
        category *= MAGNITUDE;
        if (element instanceof AttributeValuePair) {
          category += ((AttributeValuePair) element).getAttribute().getEContainingClass().getClassifierID();
        } else if (element instanceof EOperation) {
          category += ((EOperation) element).getEContainingClass().getClassifierID();
        } else if (element instanceof EStructuralFeature) {
          category += ((EStructuralFeature) element).getEContainingClass().getClassifierID();
        }
      }
      return category;
    }

    public boolean isGroupByEClass() {
      return groupByEClass;
    }

    public void setGroupByEClass(final boolean sortByEClass) {
      this.groupByEClass = sortByEClass;
    }

    public boolean isGroupByElementKind() {
      return groupByElementKind;
    }

    public void setGroupByElementKind(final boolean groupByElementKind) {
      this.groupByElementKind = groupByElementKind;
    }
  }

  /**
   * ViewerFilter will only show features/operations if the are defined in the input element EClass, i.e. the EClass currently selected in
   * {@link EClassTypeViewer}.
   */
  public static class SelectedEClassOnlyFilter extends ViewerFilter {
    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
      if (element instanceof AttributeValuePair) {
        return viewer.getInput() == ((AttributeValuePair) element).getAttribute().getEContainingClass();
      } else if (element instanceof EOperation) {
        return viewer.getInput() == ((EOperation) element).getEContainingClass();
      } else if (element instanceof EStructuralFeature) {
        return viewer.getInput() == ((EStructuralFeature) element).getEContainingClass();
      }
      return true;
    }
  }
}
