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
package com.avaloq.tools.ddk.xtext.valid.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IComparator;

import com.avaloq.tools.ddk.xtext.valid.ui.outline.ValidOutlineNodeComparator;


/**
 * Use this class to register components to be used within the IDE.
 */
public class ValidUiModule extends com.avaloq.tools.ddk.xtext.valid.ui.AbstractValidUiModule {
  public ValidUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  @Override
  // CHECKSTYLE:OFF
  public Class<? extends IComparator> bindOutlineFilterAndSorter$IComparator() { // NOPMD
    // CHECKSTYLE:ON
    return ValidOutlineNodeComparator.class;
  }

}
