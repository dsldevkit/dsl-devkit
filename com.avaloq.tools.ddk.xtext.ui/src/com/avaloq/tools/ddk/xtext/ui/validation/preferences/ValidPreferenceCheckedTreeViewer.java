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
package com.avaloq.tools.ddk.xtext.ui.validation.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;


/**
 * The Class ValidPreferenceCheckedTreeViewer is an extension of {@link CheckboxTreeViewer} the implementation of which is based on
 * {@link org.eclipse.ui.dialogs.ContainerCheckedTreeViewer}, but with enough semantic differences that is is implemented a new
 * class. The grayed state (for a Category) is used to visualize the checked state of its rules. Containers are checked and non-gray
 * if all contained leafs are checked. The container is grayed if some but not all leafs are checked. Contrary to the
 * {@link org.eclipse.ui.dialogs.ContainerCheckedTreeViewer}, the ValidPreferenceCheckedTreeViewer allows non-uncheckable nodes (the
 * mandatory rules), and the selection/deselection of grayed nodes for which not all nodes are checked/unchecked.
 */
public class ValidPreferenceCheckedTreeViewer extends CheckboxTreeViewer implements ICheckStateListener {

  /**
   * Instantiates a new Valid preference checked tree viewer.
   *
   * @param parent
   *          the parent
   */
  public ValidPreferenceCheckedTreeViewer(final Composite parent) {
    // CHECKSTYLE:OFF
    super(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.CHECK);
    // CHECKSTYLE:ON
    initViewer(); // NOPMD, Overridable method 'doCheckStateChanged' called during object construction
    new PreferenceItemToolTip(parent, getTree());
    addCheckStateListener(this);
  }

  /**
   * Update the parent and children nodes after a checkstate change (e.g. recalculation of the grayed state, automatic
   * checking/unchecking of children)
   *
   * @param element
   *          the element that was checked/unchecked
   */
  protected void doCheckStateChanged(final Object element) {
    final Widget item = findItem(element);
    if (item instanceof TreeItem) {
      final TreeItem treeItem = (TreeItem) item;
      updateChildrenItems(treeItem);

      final Item[] children = getChildren(item);
      if (children.length > 0) {
        boolean containsChecked = false;
        boolean containsUnchecked = false;
        for (final Item element2 : children) {
          final TreeItem curr = (TreeItem) element2;
          containsChecked |= curr.getChecked();
          containsUnchecked |= (!curr.getChecked() || curr.getGrayed());
        }
        treeItem.setChecked(containsChecked);
        treeItem.setGrayed(containsChecked && containsUnchecked);
      }
      updateParentItems(treeItem.getParentItem());
    }
  }

  /**
   * Updates the check state of all children underneath a parent node, taking their optional status in consideration.
   *
   * @param parent
   *          the parent
   */
  private void updateChildrenItems(final TreeItem parent) {

    boolean state = parent.getChecked();
    if (!state && parent.getData() instanceof PreferenceCategory && ((PreferenceCategory) parent.getData()).hasMandatoryRules()) {
      boolean allOptionalDisabled = true;
      for (final TreeItem curr : (TreeItem[]) getChildren(parent)) {
        if (curr.getData() instanceof PreferenceRule && ((PreferenceRule) curr.getData()).isOptional() && curr.getChecked()) {
          allOptionalDisabled = false;
          break;
        }
      }
      state = allOptionalDisabled;
    }
    for (final TreeItem curr : (TreeItem[]) getChildren(parent)) {
      if (curr.getData() != null) {
        if (curr.getData() instanceof PreferenceRule && !((PreferenceRule) curr.getData()).isOptional()) {
          curr.setChecked(true);
          curr.setGrayed(true);
        } else {
          curr.setChecked(state);
        }
        updateChildrenItems(curr);
      }
    }

  }

  /**
   * Updates the check / gray state of all parent items.
   *
   * @param item
   *          the item
   */
  private void updateParentItems(final TreeItem item) {
    if (item != null) {
      final Item[] children = getChildren(item);
      boolean containsChecked = false;
      boolean containsUnchecked = false;
      for (final Item element : children) {
        final TreeItem curr = (TreeItem) element;
        containsChecked |= curr.getChecked();
        containsUnchecked |= (!curr.getChecked() || curr.getGrayed());
      }
      item.setChecked(containsChecked);
      item.setGrayed(containsChecked && containsUnchecked);
      updateParentItems(item.getParentItem());
    }
  }

  /** {@inheritDoc} */
  @Override
  public void checkStateChanged(final CheckStateChangedEvent event) {
    // If the item is checked . . .
    update(event.getElement(), null);

    if (event.getElement() instanceof PreferenceRule) {
      ruleChecked(event, (PreferenceRule) event.getElement());
    } else if (event.getElement() instanceof PreferenceCategory) {
      categoryChecked(event, (PreferenceCategory) event.getElement());
    }
  }

  /**
   * Emit a warning (a dialog box) whenever the users tries to uncheck a mandatory rule.
   *
   * @param event
   *          the event
   * @param rule
   *          the rule
   */
  private void ruleChecked(final CheckStateChangedEvent event, final PreferenceRule rule) {
    if (!event.getChecked() && !rule.isOptional()) {
      // revert the change
      event.getCheckable().setChecked(rule, true);
    }
  }

  /**
   * React to a change event on the Checked state of a category.
   *
   * @param event
   *          the event
   * @param category
   *          the category
   */
  private void categoryChecked(final CheckStateChangedEvent event, final PreferenceCategory category) {
    if (!event.getChecked() && category.hasOnlyMandatoryRules()) {
      // revert the change
      event.getCheckable().setChecked(category, true);
    }
  }

  /**
   * Initialize the tree viewer.
   */
  private void initViewer() {
    setUseHashlookup(true);
    addCheckStateListener(new ICheckStateListener() {
      @Override
      public void checkStateChanged(final CheckStateChangedEvent event) {
        doCheckStateChanged(event.getElement());
      }
    });
    addTreeListener(new ITreeViewerListener() {
      @Override
      public void treeCollapsed(final TreeExpansionEvent event) {
        // nothing to do here
      }

      @Override
      public void treeExpanded(final TreeExpansionEvent event) {
        final Widget item = findItem(event.getElement());
        if (item instanceof TreeItem) {
          initializeItem((TreeItem) item);
        }
      }
    });
  }

  /**
   * The item has expanded. Updates the checked state of its children.
   *
   * @param item
   *          the item
   */
  private void initializeItem(final TreeItem item) {
    if (item.getChecked() && !item.getGrayed()) {
      updateChildrenItems(item);
    }
  }

  /** {@inheritDoc} */
  @Override
  public boolean setChecked(final Object element, final boolean state) {
    if (super.setChecked(element, state)) {
      doCheckStateChanged(element);
      return true;
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public void setCheckedElements(final Object[] elements) {
    super.setCheckedElements(elements);
    for (final Object element : elements) {
      doCheckStateChanged(element);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void setExpanded(final Item item, final boolean expand) {
    super.setExpanded(item, expand);
    if (expand && item instanceof TreeItem) {
      initializeItem((TreeItem) item);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Object[] getCheckedElements() {
    final Object[] checked = super.getCheckedElements();
    // add all items that are children of a checked node but not created yet
    final ArrayList<Object> result = new ArrayList<Object>();
    for (final Object curr : checked) {
      result.add(curr);
      final Widget item = findItem(curr);
      if (item != null) {
        final Item[] children = getChildren(item);
        // check if contains the dummy node
        if (children.length == 1 && children[0].getData() == null) {
          // not yet created
          collectChildren(curr, result);
        }
      }
    }
    return result.toArray();
  }

  /**
   * Recursively add the filtered children of element to the result.
   *
   * @param element
   *          the element
   * @param result
   *          the result
   */
  private void collectChildren(final Object element, final List<Object> result) {
    final Object[] filteredChildren = getFilteredChildren(element);
    for (final Object curr : filteredChildren) {
      result.add(curr);
      collectChildren(curr, result);
    }
  }

  /**
   * A tooltip which, given a model element, will display its icon (if there is one), name, and a description (if there is one).
   */
  class PreferenceItemToolTip extends ToolTip {

    /** The Constant MIN_TOOLTIP_WIDTH. */
    private static final int MIN_TOOLTIP_WIDTH = 160;

    /** The tree. */
    private final Tree tree;

    /** The tooltip heading. */
    private final Font tooltipHeading;

    /**
     * Instantiates a new preference item tool tip.
     *
     * @param parent
     *          the parent
     * @param tree
     *          the tree
     */
    PreferenceItemToolTip(final Composite parent, final Tree tree) {
      super(tree, NO_RECREATE, false);
      this.tree = tree;
      this.setHideOnMouseDown(false);
      final FontData[] defaultFont = JFaceResources.getDefaultFont().getFontData();
      final FontData boldFontData = new FontData(defaultFont[0].getName(), defaultFont[0].getHeight(), SWT.BOLD);
      tooltipHeading = new Font(parent.getDisplay(), boldFontData);
    }

    /**
     * Adds logic to only show a tooltip if a meaningful item is under the cursor.
     *
     * @param event
     *          the event
     * @return true, if should create tool tip
     */
    @Override
    protected boolean shouldCreateToolTip(final Event event) {
      return super.shouldCreateToolTip(event) && getModelElement(event) != null;
    }

    /** {@inheritDoc} */
    @Override
    protected Composite createToolTipContentArea(final Event event, final Composite parent) {
      final Object modelElement = getModelElement(event);

      Image iconImage = null;
      String nameString = null;
      String descriptionString = null;

      // Create the content area
      final Composite composite = new Composite(parent, SWT.NONE);
      composite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
      composite.setLayout(new GridLayout(2, false));

      // Gather model information
      if (modelElement instanceof IPreferenceItem) {
        final IPreferenceItem item = (IPreferenceItem) modelElement;

        iconImage = item.getUndecoratedImage();
        nameString = item.getLabel();
        descriptionString = item.getDescription();

        // The title area with the icon (if there is one) and label.
        final Label title = createEntry(composite, iconImage, nameString);
        title.setFont(tooltipHeading);
        GridDataFactory.createFrom((GridData) title.getLayoutData()).hint(SWT.DEFAULT, SWT.DEFAULT).minSize(MIN_TOOLTIP_WIDTH, 1).applyTo(title);

        // The description (if there is one)
        if (descriptionString != null) {
          createEntry(composite, null, descriptionString);
        }

      }
      return composite;
    }

    /**
     * Adds a line of information to <code>parent</code>. If <code>icon</code> is not <code>null</code>, an icon is placed on
     * the left, and then a label with <code>text</code>.
     *
     * @param parent
     *          the composite to add the entry to
     * @param icon
     *          the icon to place next to the text. <code>null</code> for none.
     * @param text
     *          the text to display
     * @return the created label
     */
    protected Label createEntry(final Composite parent, final Image icon, final String text) {
      if (icon != null) {
        final Label iconLabel = new Label(parent, SWT.NONE);
        iconLabel.setImage(icon);
        iconLabel.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
        iconLabel.setData(new GridData());
      }

      final Label textLabel = new Label(parent, SWT.WRAP);

      if (icon == null) {
        GridDataFactory.generate(textLabel, 2, 1);
      } else {
        GridDataFactory.generate(textLabel, 1, 1);
      }

      textLabel.setText(text);
      textLabel.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
      return textLabel;
    }

    /** {@inheritDoc} */
    @Override
    public Point getLocation(final Point tipSize, final Event event) {
      // try to position the tooltip at the bottom of the cell
      final ViewerCell cell = ValidPreferenceCheckedTreeViewer.this.getCell(new Point(event.x, event.y));

      if (cell != null) {
        return tree.toDisplay(event.x, cell.getBounds().y + cell.getBounds().height);
      }

      return super.getLocation(tipSize, event);
    }

    /** {@inheritDoc} */
    @Override
    protected Object getToolTipArea(final Event event) {
      // Ensures that the tooltip is hidden when the cell is left
      return ValidPreferenceCheckedTreeViewer.this.getCell(new Point(event.x, event.y));
    }

    /**
     * Gets the model element.
     *
     * @param event
     *          the event
     * @return the model element
     */
    protected Object getModelElement(final Event event) {
      final org.eclipse.swt.widgets.TreeItem treeItem = tree.getItem(new Point(event.x, event.y));
      if (treeItem == null) {
        return null;
      }
      return treeItem.getData();
    }
  }

}
