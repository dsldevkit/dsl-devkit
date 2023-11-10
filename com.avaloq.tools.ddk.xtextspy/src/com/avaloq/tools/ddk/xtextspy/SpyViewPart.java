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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.part.ViewPart;

import com.avaloq.tools.ddk.xtextspy.EObjectOutline.EObjectOutlineSorter;
import com.avaloq.tools.ddk.xtextspy.EObjectOutline.SelectedEClassOnlyFilter;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * Viewpart displaying information about the current selection in any Xtext-based language.
 */
public class SpyViewPart extends ViewPart {

  /**
   * The ID of the view as specified by the extension.
   */
  public static final String ID = "com.avaloq.tools.ddk.xtextspy.SpyView"; //$NON-NLS-1$

  @Inject
  private XtextElementSelectionListener selectionListener;
  @Inject
  private EObjectContentProvider objectContentProvider;
  @Inject
  private EClassTypeContentProvider typeContentProvider;
  @Inject
  private ILabelProvider labelProvider;

  private GrammarView grammarView;
  private EObjectOutline eObjectOutline;
  private EClassTypeViewer eClassTypeView;

  @Override
  public void dispose() {
    super.dispose();
    uninstallSelectionListeners();
  }

  /**
   * Install selection listeners.
   */
  private void installSelectionListeners() {
    ISelectionService service = getSite().getService(ISelectionService.class);
    service.addPostSelectionListener(selectionListener);
    selectionListener.addSelectionChangedListener(grammarView);
    selectionListener.addSelectionChangedListener(eClassTypeView);
    eClassTypeView.addPostSelectionChangedListener(eObjectOutline);
    selectionListener.addSelectionChangedListener(eObjectOutline);
  }

  /**
   * Uninstall selection listeners.
   */
  private void uninstallSelectionListeners() {
    ISelectionService service = getSite().getService(ISelectionService.class);
    service.removePostSelectionListener(selectionListener);
    selectionListener.removeSelectionChangedListener(grammarView);
    selectionListener.removeSelectionChangedListener(eClassTypeView);
    eClassTypeView.removePostSelectionChangedListener(eObjectOutline);
    selectionListener.removeSelectionChangedListener(eObjectOutline);
  }

  // ////////////////////////
  @Override
  public void createPartControl(final Composite parent) {
    GridLayout layout = new GridLayout();
    parent.setLayout(layout);
    // Grammar view
    grammarView = new GrammarView(parent, SWT.NONE | SWT.BORDER);
    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    grammarView.setLayoutData(gridData);

    // Sash containing EClassType and EObjectContent views
    gridData = new GridData();
    gridData.verticalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = true;
    gridData.grabExcessVerticalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
    sashForm.setLayoutData(gridData);
    eClassTypeView = new EClassTypeViewer(sashForm, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    eClassTypeView.setContentProvider(typeContentProvider);
    eClassTypeView.setLabelProvider(labelProvider);
    eObjectOutline = new EObjectOutline(sashForm, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    eObjectOutline.setLabelProvider(labelProvider);
    eObjectOutline.setContentProvider(objectContentProvider);

    contributeMenuActions();
    // install selection listeners
    installSelectionListeners();
  }

  @Override
  public void setFocus() {
  }

  /**
   * Contribute menu actions "Group by EClass", "Group by feature/operation" and "Limit to EClass selection".
   */
  private void contributeMenuActions() {
    final Action selectedEClassOnlyAction = new Action(Messages.SpyViewPart_LimitToEClass, Action.AS_CHECK_BOX) {
      private final ViewerFilter filter = new SelectedEClassOnlyFilter();

      @Override
      public String getToolTipText() {
        return Messages.SpyViewPart_LimitToEClassTT;
      }

      @Override
      public void run() {
        boolean hasFilter = Iterables.contains(Iterables.cycle(eObjectOutline.getFilters()), filter);
        if (hasFilter) {
          eObjectOutline.removeFilter(filter);
        } else {
          eObjectOutline.addFilter(filter);
        }
        setChecked(!hasFilter);
      }
    };
    Action groupByEClassAction = new Action(Messages.SpyViewPart_GroupByEClass, Action.AS_CHECK_BOX) {
      @Override
      public String getToolTipText() {
        return Messages.SpyViewPart_GroupByEClassTT;
      }

      @Override
      public void run() {
        EObjectOutlineSorter sorter = (EObjectOutlineSorter) eObjectOutline.getComparator();
        sorter.setGroupByEClass(!sorter.isGroupByEClass());
        setChecked(sorter.isGroupByEClass());
        eObjectOutline.refresh();
      }

      @Override
      public boolean isEnabled() {
        return super.isEnabled() && !selectedEClassOnlyAction.isChecked();
      }
    };
    Action groupByElementKindAction = new Action(Messages.SpyViewPart_GroupByFeature, Action.AS_CHECK_BOX) {
      @Override
      public String getToolTipText() {
        return Messages.SpyViewPart_GroupByFeatureTT;
      }

      @Override
      public void run() {
        EObjectOutlineSorter sorter = (EObjectOutlineSorter) eObjectOutline.getComparator();
        sorter.setGroupByElementKind(!sorter.isGroupByElementKind());
        setChecked(sorter.isGroupByElementKind());
        eObjectOutline.refresh();
      }
    };
    getViewSite().getActionBars().getMenuManager().add(groupByEClassAction);
    getViewSite().getActionBars().getMenuManager().add(groupByElementKindAction);
    getViewSite().getActionBars().getMenuManager().add(selectedEClassOnlyAction);
  }
}
