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

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;


/**
 * TreeViewer used to display an EClass' inheritance/super-type hierarchy.
 */
public class EClassTypeViewer extends TreeViewer implements ISelectionProvider, ISelectionChangedListener {

  public EClassTypeViewer(final Composite parent, final int style) {
    super(parent, style);
    setAutoExpandLevel(ALL_LEVELS);
  }

  /** {@inheritDoc} */
  public void selectionChanged(final SelectionChangedEvent event) {
    if (!event.getSelection().isEmpty() && event.getSource() instanceof XtextElementSelectionListener) {
      setInput(((XtextElementSelectionListener) event.getSource()).getSelectedElementType());
    }
  }

}
