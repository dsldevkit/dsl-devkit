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
package com.avaloq.tools.ddk.check.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.preferences.BuilderConfigurationBlock;
import org.eclipse.xtext.builder.preferences.BuilderPreferenceAccess;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ui.LanguageSpecific;
import org.eclipse.xtext.ui.editor.DirtyStateEditorSupport;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider;
import org.eclipse.xtext.ui.editor.handler.ValidateActionHandler;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.xbase.compiler.GeneratorConfigProvider;
import org.eclipse.xtext.xbase.compiler.IGeneratorConfigProvider;

import com.avaloq.tools.ddk.check.compiler.ICheckGeneratorConfigProvider;
import com.avaloq.tools.ddk.check.runtime.ui.editor.PlatformPluginAwareEditorOpener;
import com.avaloq.tools.ddk.check.ui.builder.CheckBuilderConfigurationBlock;
import com.avaloq.tools.ddk.check.ui.builder.CheckBuilderParticipant;
import com.avaloq.tools.ddk.check.ui.builder.CheckBuilderPreferenceAccess;
import com.avaloq.tools.ddk.check.ui.builder.CheckEclipseGeneratorConfigProvider;
import com.avaloq.tools.ddk.check.ui.editor.handler.CheckValidateActionHandler;
import com.avaloq.tools.ddk.check.ui.highlighting.CheckAntlrTokenToAttributeIdMapper;
import com.avaloq.tools.ddk.check.ui.highlighting.CheckHighlightingCalculator;
import com.avaloq.tools.ddk.check.ui.highlighting.CheckHighlightingConfiguration;
import com.avaloq.tools.ddk.check.ui.hover.CheckHoverProvider;
import com.avaloq.tools.ddk.check.ui.navigation.CheckHyperlinkHelper;
import com.avaloq.tools.ddk.check.ui.templates.CheckTemplateContextType;
import com.avaloq.tools.ddk.check.ui.templates.CheckTemplateProposalProvider;
import com.avaloq.tools.ddk.check.ui.wizard.CheckCatalogCreator;
import com.avaloq.tools.ddk.check.ui.wizard.CheckProjectCreator;
import com.avaloq.tools.ddk.check.ui.wizard.ICheckCatalogCreator;
import com.avaloq.tools.ddk.xtext.common.types.ui.access.jdt.JdtFallbackTypeProviderFactory;
import com.avaloq.tools.ddk.xtext.ui.editor.FixedDirtyStateEditorSupport;
import com.avaloq.tools.ddk.xtext.ui.templates.KeywordAwareCrossReferenceTemplateVariableResolver;


/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings({"restriction", "PMD.CouplingBetweenObjects"})
public class CheckUiModule extends com.avaloq.tools.ddk.check.ui.AbstractCheckUiModule {
  public CheckUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  /**
   * Binds a project creator. Used for the wizards.
   *
   * @return project creator
   */
  public Class<? extends IProjectCreator> bindIProjectCreator() {
    return CheckProjectCreator.class;
  }

  /**
   * Bind ICheckCatalogCreator.
   *
   * @return CheckCatalogCreator.class which creates a new Check catalog in an existing Check plugin project
   */
  public Class<? extends ICheckCatalogCreator> bindICheckCatalogCreator() {
    return CheckCatalogCreator.class;
  }

  public Class<? extends XtextTemplateContextType> bindXtextTemplateContextType() {
    return CheckTemplateContextType.class;
  }

  /**
   * Provides support for custom template proposals.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public Class<? extends ITemplateProposalProvider> bindITemplateProposalProvider() {
    return CheckTemplateProposalProvider.class;
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
   * Automatically updates the {@code plugin.xml} and {@code MANIFEST.MF} files.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public Class<? extends org.eclipse.xtext.builder.IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
    return CheckBuilderParticipant.class;
  }

  @Override
  public Class<? extends org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider> bindIEObjectHoverProvider() {
    return CheckHoverProvider.class;
  }

  @Override
  public Class<? extends ISemanticHighlightingCalculator> bindIdeSemanticHighlightingCalculator() {
    return CheckHighlightingCalculator.class;
  }

  /**
   * Binds a semantic highlighting calculator.
   *
   * @return the semantic highlighting calculator>
   */
  @Override
  public final Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
    return CheckHighlightingConfiguration.class;
  }

  /**
   * Binds a lexical highlighting calculator (Token to Attribute ID mapper).
   *
   * @return the lexical highlighting calculator>
   */
  @Override
  public Class<? extends org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
    return CheckAntlrTokenToAttributeIdMapper.class;
  }

  /**
   * Binds a editor opener for platform plugin URIs.
   *
   * @param binder
   *          the binder
   */
  @Override
  public void configureLanguageSpecificURIEditorOpener(final com.google.inject.Binder binder) {
    binder.bind(IURIEditorOpener.class).annotatedWith(LanguageSpecific.class).to(PlatformPluginAwareEditorOpener.class);
  }

  /**
   * Binds a JDT type provider factory capable of creating bundle aware type providers.
   *
   * @return the JDT type provider factory
   */
  public Class<? extends JdtTypeProviderFactory> bindJdtTypeProviderFactory() {
    return JdtFallbackTypeProviderFactory.class;
  }

  /** Used to disable hyperlinking in read-only check editors. {@inheritDoc} */
  @Override
  public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
    return CheckHyperlinkHelper.class;
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
   * Binds a ValidateActionHandler instance.
   *
   * @return custom ValidateActionHandler
   */
  public Class<? extends ValidateActionHandler> bindValidateActionHandler() {
    return CheckValidateActionHandler.class;
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

  @Override
  public Class<? extends BuilderConfigurationBlock> bindBuilderConfigurationBlock() {
    return CheckBuilderConfigurationBlock.class;
  }

  /**
   * Bind check generator configuration provider.
   *
   * @return the check specific configuration provider for Eclipse project
   */
  public Class<? extends ICheckGeneratorConfigProvider> bindICheckGeneratorConfigProvider() {
    return CheckEclipseGeneratorConfigProvider.class;
  }

  @Override
  @SuppressWarnings("PMD.AvoidDollarSigns") // that's how it's defined in Xtext
  public Class<? extends BuilderPreferenceAccess.Initializer> bindBuilderPreferenceAccess$Initializer() {
    return CheckBuilderPreferenceAccess.Initializer.class;
  }
}
