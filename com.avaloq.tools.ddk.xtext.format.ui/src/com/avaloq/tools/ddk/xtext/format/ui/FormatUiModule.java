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
package com.avaloq.tools.ddk.xtext.format.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.preferences.BuilderConfigurationBlock;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver;
import org.eclipse.xtext.xtext.generator.model.project.IXtextProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.XtextProjectConfig;

import com.avaloq.tools.ddk.xtext.format.ui.builder.FormatBuilderConfigurationBlock;
import com.avaloq.tools.ddk.xtext.format.ui.builder.FormatBuilderParticipant;
import com.avaloq.tools.ddk.xtext.format.ui.hyperlinking.FormatHyperlinkHelper;
import com.avaloq.tools.ddk.xtext.ui.templates.KeywordAwareCrossReferenceTemplateVariableResolver;
import com.google.inject.Binder;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@SuppressWarnings("restriction")
public class FormatUiModule extends AbstractFormatUiModule {

  public FormatUiModule(final AbstractUIPlugin plugin) {
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

  /**
   * Bind hyperlink helper to provide hyperlinking from "override" keyword to extended rule.
   *
   * @return FormatHyperlinkHelper.class
   */
  @Override
  public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
    return FormatHyperlinkHelper.class;
  }

  @Override
  public Class<? extends IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
    return FormatBuilderParticipant.class;
  }

  /**
   * Binds the Format-specific builder configuration block, which contributes the "disable the builder participant on workspace build" checkbox to the Compiler
   * page.
   *
   * @return the Format builder configuration block
   */
  @Override
  public Class<? extends BuilderConfigurationBlock> bindBuilderConfigurationBlock() {
    return FormatBuilderConfigurationBlock.class;
  }

  @Override
  public void configure(final Binder binder) {
    super.configure(binder);
    binder.bind(IXtextProjectConfig.class).to(XtextProjectConfig.class);
  }
}
