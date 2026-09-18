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
package com.avaloq.tools.ddk.xtext.export.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.preferences.BuilderConfigurationBlock;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IComparator;
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver;

import com.avaloq.tools.ddk.xtext.export.ui.builder.ExportBuilderConfigurationBlock;
import com.avaloq.tools.ddk.xtext.export.ui.builder.ExportBuilderParticipant;
import com.avaloq.tools.ddk.xtext.export.ui.outline.ExportOutlineNodeComparator;
import com.avaloq.tools.ddk.xtext.ui.templates.KeywordAwareCrossReferenceTemplateVariableResolver;


/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings("restriction")
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

  /**
   * Binds the Export builder participant, which honors the per-language preference allowing regeneration to be disabled on a workspace build.
   *
   * @return the {@link ExportBuilderParticipant} class
   */
  @Override
  public Class<? extends IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
    return ExportBuilderParticipant.class;
  }

  /**
   * Binds the Export builder configuration block, which contributes the "disable the builder participant on workspace build" checkbox to the Compiler page.
   *
   * @return the {@link ExportBuilderConfigurationBlock} class
   */
  public Class<? extends BuilderConfigurationBlock> bindBuilderConfigurationBlock() {
    return ExportBuilderConfigurationBlock.class;
  }
}
