/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.checkcfg.ide.contentassist;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistEntry;
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.xbase.ide.contentassist.XbaseIdeContentProposalProvider;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;
import com.avaloq.tools.ddk.checkcfg.services.CheckCfgGrammarAccess;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


public class CheckCfgIdeContentProposalProvider extends XbaseIdeContentProposalProvider {
  private static final String QUOTE = "\""; //$NON-NLS-1$

  @Inject
  private CheckCfgGrammarAccess grammarAccess;

  @Inject
  private CheckCfgUtil checkCfgUtil;

  @Inject
  private IScopeProvider scopeProvider;

  @Inject
  private XbaseInterpreter interpreter;

  @Inject
  private QualifiedNameValueConverter qualifiedNameValueConverter;

  @Override
  protected void _createProposals(final Assignment assignment, final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    EObject model = context.getCurrentModel();
    if (grammarAccess.getConfiguredLanguageValidatorAccess().getLanguageAssignment_1().equals(assignment)) {
      completeLanguages(model, context, acceptor);
    } else if (grammarAccess.getConfiguredParameterAccess().getNewValueAssignment_3().equals(assignment)) {
      completeParameterValue(model, context, acceptor);
    } else {
      super._createProposals(assignment, context, acceptor);
    }
  }

  @Override
  protected Predicate<IEObjectDescription> getCrossrefFilter(final CrossReference reference, final ContentAssistContext context) {
    // we don't want references to dummy java classes to be proposed, and 'real' proposals come from completeParameterValue() anyway.
    if (context.getCurrentModel() instanceof ConfiguredParameter) {
      return Predicates.alwaysFalse();
    }
    return Predicates.alwaysTrue();
  }

  @Override
  protected void _createProposals(final RuleCall ruleCall, final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    if (grammarAccess.getConfiguredCheckRule().equals(ruleCall.getRule())) {
      addConfiguredCheckTemplates(context, acceptor);
    } else {
      super._createProposals(ruleCall, context, acceptor);
    }
  }

  @Override
  protected void _createProposals(final Keyword keyword, final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    if (grammarAccess.getConfiguredCatalogAccess().getCatalogKeyword_1().equals(keyword)) {
      addCatalogConfigurations(context, acceptor);
    } else if (grammarAccess.getCheckConfigurationAccess().getCheckKeyword_0().equals(keyword)) {
      addEmptyCheckConfig(context, acceptor);
    }
    super._createProposals(keyword, context, acceptor);
  }

  protected void completeLanguages(final EObject model, final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    for (String language : checkCfgUtil.getAllLanguages()) {
      ContentAssistEntry entry = getProposalCreator().createProposal(language, context, (final ContentAssistEntry it) -> {
        it.setKind(ContentAssistEntry.KIND_REFERENCE);
        it.getEditPositions().add(new TextRegion(context.getOffset(), language.length()));
        it.setDescription(language);
      });
      acceptor.accept(entry, getProposalPriorities().getDefaultPriority(entry));
    }
  }

  protected void completeParameterValue(final EObject model, final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    FormalParameter parameter = ((ConfiguredParameter) model).getParameter();
    ICheckCfgPropertySpecification propertySpecification = null;
    String[] validValues = null;
    if (parameter != null) {
      propertySpecification = CheckCfgUtil.getPropertySpecification(parameter.getName());
      if (propertySpecification != null) {
        validValues = propertySpecification.getExpectedValues();
      }
    }
    if (validValues != null && validValues.length > 0) {
      String info = propertySpecification.getInfo();
      for (String validValue : validValues) {
        String proposal = QUOTE + validValue + QUOTE;
        ContentAssistEntry entry = getProposalCreator().createProposal(proposal, context, (final ContentAssistEntry it) -> {
          it.setKind(ContentAssistEntry.KIND_VALUE);
          it.getEditPositions().add(new TextRegion(context.getOffset(), proposal.length()));
          it.setLabel(validValue);
          it.setDescription(info);
          it.setPrefix(context.getPrefix());
        });
        acceptor.accept(entry, getProposalPriorities().getDefaultPriority(entry));
      }
    }
  }

  /**
   * Adds template proposals for all checks which may be referenced in current catalog configuration. Only proposals for checks
   * which have not yet been configured are provided.
   *
   * @param context
   *          the context
   * @param acceptor
   *          the acceptor
   */
  @SuppressWarnings("nls")
  private void addConfiguredCheckTemplates(final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    ConfiguredCatalog configuredCatalog = EcoreUtil2.getContainerOfType(context.getCurrentModel(), ConfiguredCatalog.class);
    Iterable<String> alreadyConfiguredCheckNames = Iterables.filter(Iterables.transform(configuredCatalog.getCheckConfigurations(), new Function<ConfiguredCheck, String>() {
      @Override
      public String apply(final ConfiguredCheck from) {
        if (from.getCheck() != null) {
          return from.getCheck().getName();
        }
        return null;
      }
    }), Predicates.notNull());
    final CheckCatalog catalog = configuredCatalog.getCatalog();
    for (final Check check : catalog.getAllChecks()) {
      // create a template on the fly
      final String checkName = check.getName();
      if (!Iterables.contains(alreadyConfiguredCheckNames, checkName)) {
        int paramTabStop = 1;
        final String severity = (catalog.isFinal() || check.isFinal()) ? "default " : getSeverityKindChoice(paramTabStop++); // NOPMD false UnusedAssignment

        // check if referenced check has configurable parameters
        final StringJoiner paramsJoiner = new StringJoiner(", ", " (", ")");
        paramsJoiner.setEmptyValue("");
        for (final FormalParameter param : check.getFormalParameters()) {
          final String paramName = param.getName();
          final Object defaultValue = interpreter.evaluate(param.getRight()).getResult();

          final String valuePlaceholder = createPlaceholderPattern(paramTabStop++, defaultValue);
          paramsJoiner.add(paramName + " = " + valuePlaceholder);
        }

        final String description = "Configures the check \"" + check.getLabel() + "\"";
        final String pattern = severity + qualifiedNameValueConverter.toString(checkName) + paramsJoiner + "${0}";

        ContentAssistEntry entry = getProposalCreator().createProposal(pattern, context, (final ContentAssistEntry it) -> {
          it.setKind(ContentAssistEntry.KIND_SNIPPET);
          it.getEditPositions().add(new TextRegion(context.getOffset(), pattern.length()));
          it.setLabel(checkName);
          it.setDescription(description);
        });
        acceptor.accept(entry, getProposalPriorities().getDefaultPriority(entry));
      }
    }
  }

  private String createPlaceholderPattern(final int tabStop, final Object defaultValue) {
    String defaultPattern = (defaultValue instanceof Boolean) ? "|" + defaultValue + "," + !((Boolean) defaultValue) + "|" : ":" + defaultValue; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    return "${" + tabStop + defaultPattern + "}"; //$NON-NLS-1$//$NON-NLS-2$
  }

  private String getSeverityKindChoice(final int tabStop) {
    StringJoiner choice = new StringJoiner(",", "|", "|"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    for (SeverityKind kind : SeverityKind.VALUES) {
      choice.add(kind.getLiteral());
    }
    return "${" + tabStop + choice + "} "; //$NON-NLS-1$//$NON-NLS-2$
  }

  /**
   * Adds the populated check configuration.
   *
   * @param templateContext
   *          the template context
   * @param context
   *          the context
   * @param acceptor
   *          the acceptor
   */
  private void addCatalogConfigurations(final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    if (context.getRootModel() instanceof CheckConfiguration) {
      final CheckConfiguration conf = (CheckConfiguration) context.getRootModel();
      List<IEObjectDescription> allElements = Lists.newArrayList(scopeProvider.getScope(conf, CheckcfgPackage.Literals.CONFIGURED_CATALOG__CATALOG).getAllElements());

      StringBuilder allBuilder = new StringBuilder();
      StringBuilder choiceBuilder = new StringBuilder();
      String indentation = getIndentation(context);
      for (IEObjectDescription description : allElements) {
        if (description.getEObjectOrProxy() instanceof CheckCatalog) {
          CheckCatalog catalog = (CheckCatalog) description.getEObjectOrProxy();
          if (catalog.eIsProxy()) {
            catalog = (CheckCatalog) EcoreUtil.resolve(catalog, conf);
          }
          if (isCatalogConfigured(conf, catalog)) {
            continue;
          } else if (allElements.indexOf(description) > 0) {
            allBuilder.append(Strings.newLine()).append(indentation);
            choiceBuilder.append(',');
          }
          final String catalogName = qualifiedNameValueConverter.toString(description.getQualifiedName().toString());
          allBuilder.append("catalog ").append(catalogName).append(" {}").append(Strings.newLine()).append(indentation); //$NON-NLS-1$ //$NON-NLS-2$
          choiceBuilder.append(catalogName);
        }

      }

      if (allBuilder.length() > 0) {
        // propose to configure all unconfigured catalogs
        allBuilder.append("${0}"); //$NON-NLS-1$
        ContentAssistEntry allEntry = getProposalCreator().createProposal(allBuilder.toString(), context, (final ContentAssistEntry it) -> {
          it.setKind(ContentAssistEntry.KIND_SNIPPET);
          it.getEditPositions().add(new TextRegion(context.getOffset(), allBuilder.length()));
          it.setLabel("Add all registered catalogs"); //$NON-NLS-1$
          it.setDescription("configures all missing catalogs"); //$NON-NLS-1$
        });
        acceptor.accept(allEntry, getProposalPriorities().getDefaultPriority(allEntry));

        // propose to configure one from a choice of the unconfigured catalogs
        StringBuilder oneBuilder = new StringBuilder();
        oneBuilder.append("catalog ${1|").append(choiceBuilder).append("|} {").append(Strings.newLine()).append(indentation); //$NON-NLS-1$ //$NON-NLS-2$
        oneBuilder.append("  ${0}").append(Strings.newLine()).append(indentation).append('}'); //$NON-NLS-1$
        ContentAssistEntry oneEntry = getProposalCreator().createProposal(oneBuilder.toString(), context, (final ContentAssistEntry it) -> {
          it.setKind(ContentAssistEntry.KIND_SNIPPET);
          it.getEditPositions().add(new TextRegion(context.getOffset(), oneBuilder.length()));
          it.setLabel("Add a catalog"); //$NON-NLS-1$
          it.setDescription("Configures a given catalog"); //$NON-NLS-1$
        });
        acceptor.accept(oneEntry, getProposalPriorities().getDefaultPriority(oneEntry));
      }
    }
  }

  /**
   * Checks if is catalog configured.
   *
   * @param conf
   *          the check configuration
   * @param catalog
   *          the catalog
   * @return true, if is catalog configured
   */
  private boolean isCatalogConfigured(final CheckConfiguration conf, final CheckCatalog catalog) {
    try {
      Iterables.find(conf.getLegacyCatalogConfigurations(), new Predicate<ConfiguredCatalog>() {
        @Override
        public boolean apply(final ConfiguredCatalog input) {
          if (input.getCatalog() == null || input.getCatalog().getName() == null || input.getCatalog().getPackageName() == null) {
            return false;
          }
          return catalog == input.getCatalog()
              || (input.getCatalog().getName().equals(catalog.getName()) && input.getCatalog().getPackageName().equals(catalog.getPackageName()));
        }
      });
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  private void addEmptyCheckConfig(final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    String modelName = context.getResource().getURI().trimFileExtension().lastSegment();
    // CHECKSTYLE:OFF MagicNumber
    StringBuilder proposal = new StringBuilder(34);
    // CHECKSTYLE:ON
    proposal.append("check configuration ${1:").append(modelName).append("} {").append(Strings.newLine()); //$NON-NLS-1$ //$NON-NLS-2$
    proposal.append("  ${0}").append(Strings.newLine()).append('}'); //$NON-NLS-1$
    ContentAssistEntry entry = getProposalCreator().createProposal(proposal.toString(), context, (final ContentAssistEntry it) -> {
      it.setKind(ContentAssistEntry.KIND_SNIPPET);
      it.getEditPositions().add(new TextRegion(context.getOffset(), proposal.length()));
      it.setLabel("CheckConfiguration"); //$NON-NLS-1$
      it.setDescription("Creates a new empty check configuration"); //$NON-NLS-1$
    });
    acceptor.accept(entry, getProposalPriorities().getDefaultPriority(entry));
  }

  private String getIndentation(final ContentAssistContext context) {
    String text = null;
    if (context.getRootNode() != null) {
      text = context.getRootNode().getText();
    }
    if (text != null && text.length() >= context.getOffset()) {
      int lineStart = context.getReplaceRegion().getOffset();
      int indentEnd = lineStart;
      while (lineStart > 0 && text.charAt(lineStart - 1) != '\n') {
        lineStart--;
        if (!Character.isWhitespace(text.charAt(lineStart))) {
          indentEnd = lineStart;
        }
      }
      return text.substring(lineStart, indentEnd);
    }
    return null;
  }
}
