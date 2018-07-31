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
package com.avaloq.tools.ddk.xtext.export.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IComparator;
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver;

import com.avaloq.tools.ddk.xtext.export.ui.outline.ExportOutlineNodeComparator;
import com.avaloq.tools.ddk.xtext.ui.templates.KeywordAwareCrossReferenceTemplateVariableResolver;


/**
 * Use this class to register components to be used within the IDE.
 */
public class ExportUiModule extends com.avaloq.tools.ddk.xtext.export.ui.AbstractExportUiModule {
  public ExportUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  /**
   * Binds a {@link CrossReferenceTemplateVariableResolver} which prefixes keywords with escape characters.
   *
   * @return {@link KeywordAwareCrossReferenceTemplateVariableResolver}
   */
  public Class<? extends CrossReferenceTemplateVariableResolver> bindCrossReferenceTemplateVariableResolver() {
    return KeywordAwareCrossReferenceTemplateVariableResolver.class;
  }

  @Override
  // CHECKSTYLE:OFF
  public Class<? extends IComparator> bindOutlineFilterAndSorter$IComparator() { // NOPMD
    // CHECKSTYLE:ON
    return ExportOutlineNodeComparator.class;
  }
}
