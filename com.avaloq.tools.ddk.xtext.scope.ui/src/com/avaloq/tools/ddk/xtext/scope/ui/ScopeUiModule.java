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
package com.avaloq.tools.ddk.xtext.scope.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.preferences.BuilderConfigurationBlock;
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver;

import com.avaloq.tools.ddk.xtext.scope.ui.builder.ScopeBuilderConfigurationBlock;
import com.avaloq.tools.ddk.xtext.scope.ui.builder.ScopeBuilderParticipant;
import com.avaloq.tools.ddk.xtext.ui.templates.KeywordAwareCrossReferenceTemplateVariableResolver;


/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings("restriction")
public class ScopeUiModule extends com.avaloq.tools.ddk.xtext.scope.ui.AbstractScopeUiModule {
  public ScopeUiModule(final AbstractUIPlugin plugin) {
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
   * Binds the Scope builder participant which honors the per-language preference controlling whether the generated artifacts are regenerated on a workspace
   * build, overriding the generated {@code BuilderParticipant} binding.
   *
   * @return {@link ScopeBuilderParticipant}
   */
  @Override
  public Class<? extends IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
    return ScopeBuilderParticipant.class;
  }

  /**
   * Binds the configuration block contributing the Scope-specific checkbox to the "Compiler" preference page.
   *
   * @return {@link ScopeBuilderConfigurationBlock}
   */
  public Class<? extends BuilderConfigurationBlock> bindBuilderConfigurationBlock() {
    return ScopeBuilderConfigurationBlock.class;
  }

}
