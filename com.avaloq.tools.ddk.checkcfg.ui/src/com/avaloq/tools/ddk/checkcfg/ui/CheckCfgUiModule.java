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
package com.avaloq.tools.ddk.checkcfg.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.DirtyStateEditorSupport;
import org.eclipse.xtext.ui.editor.IXtextEditorCallback;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider;
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType;
import org.eclipse.xtext.xbase.compiler.GeneratorConfigProvider;
import org.eclipse.xtext.xbase.compiler.IGeneratorConfigProvider;

import com.avaloq.tools.ddk.checkcfg.ui.templates.CheckCfgTemplateContextType;
import com.avaloq.tools.ddk.checkcfg.ui.templates.CheckCfgTemplateProposalProvider;
import com.avaloq.tools.ddk.xtext.ui.editor.FixedDirtyStateEditorSupport;
import com.avaloq.tools.ddk.xtext.ui.templates.KeywordAwareCrossReferenceTemplateVariableResolver;


/**
 * Use this class to register components to be used within the IDE.
 */
public class CheckCfgUiModule extends com.avaloq.tools.ddk.checkcfg.ui.AbstractCheckCfgUiModule {
  public CheckCfgUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  /**
   * Binds a {@link XtextTemplateContextType} which adds
   * {@link com.avaloq.tools.ddk.xtext.ui.templates.ResourceNameTemplateVariableResolver ResourceNameTemplateVariableResolver} and
   * {@link com.avaloq.tools.ddk.xtext.ui.templates.SimpleEnumTemplateVariableResolver SimpleEnumTemplateVariableResolver}.
   *
   * @return {@link CheckCfgTemplateContextType}
   */
  public Class<? extends XtextTemplateContextType> bindXtextTemplateContextType() {
    return CheckCfgTemplateContextType.class;
  }

  /** Binds a proposal provider for check configuration templates. {@inheritDoc} */
  @Override
  public Class<? extends ITemplateProposalProvider> bindITemplateProposalProvider() {
    return CheckCfgTemplateProposalProvider.class;
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
   * Fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=383919 (honor container visibility).
   *
   * @return FixedDirtyStateEditorSupport
   */
  @Override
  public Class<? extends DirtyStateEditorSupport> bindDirtyStateEditorSupport() {
    return FixedDirtyStateEditorSupport.class;
  }

  /**
   * Fix for NPE in EclipseGeneratorConfigProvider.
   *
   * @return GeneratorConfigProvider
   */
  @Override
  public Class<? extends IGeneratorConfigProvider> bindIGeneratorConfigProvider() {
    return GeneratorConfigProvider.class;
  }

  /**
   * When the user opens a Check Configuration editor on a source coming from the ice project, we should not show the prompt for adding the Xtext nature to the
   * ice project.
   *
   * @return IXtextEditorCallback.NullImpl
   */
  @Override
  public Class<? extends IXtextEditorCallback> bindIXtextEditorCallback() {
    return IXtextEditorCallback.NullImpl.class;
  }
}
