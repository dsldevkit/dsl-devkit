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
package com.avaloq.tools.ddk.xtext.valid.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.mwe.core.WorkflowInterruptedException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xpand2.XpandFacade;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.validation.JavaValidatorFragment;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.validation.CompositeEValidator;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.generator.util.ModelValidator;
import com.avaloq.tools.ddk.xtext.util.EmfResourceUtil;
import com.avaloq.tools.ddk.xtext.valid.ValidStandaloneSetup;
import com.avaloq.tools.ddk.xtext.valid.valid.Category;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeRule;
import com.avaloq.tools.ddk.xtext.valid.valid.Rule;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidModel;
import com.avaloq.tools.ddk.xtext.validation.ValidCompositeEValidator;
import com.google.common.base.Preconditions;


/**
 * {@link org.eclipse.xtext.generator.IGeneratorFragment} implementation to generate a java based validity checker for a
 * given grammar and .valid File.
 */
@SuppressWarnings("deprecation")
public class ValidValidatorFragment extends JavaValidatorFragment {

  private static final String VALID_EXTENSION = "valid"; //$NON-NLS-1$

  private static final String XTEXT_EXTENSION = "xtext"; //$NON-NLS-1$

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(ValidValidatorFragment.class);

  /** Resource checker used to validate loaded model. */
  private static final ModelValidator VALIDATOR = new ValidStandaloneSetup().createInjector().getInstance(ModelValidator.class);

  private boolean generateTests;

  /** Fragment parameter: the composed checks. */
  private final List<String> composedChecks = new ArrayList<String>();

  /** Fragment parameter: the valid URI. */
  private String validURI;

  /** Parsed valid model. */
  private ValidModel model;

  /**
   * Adds the composed check.
   *
   * @param composedCheckValidator
   *          the composed check validator
   */
  @Override
  public void addComposedCheck(final String composedCheckValidator) {
    this.composedChecks.add(composedCheckValidator);
  }

  /**
   * Sets the valid uri.
   *
   * @param validURI
   *          the new valid uri
   */
  public void setValidURI(final String validURI) {
    Preconditions.checkNotNull(validURI, Messages.VALID_URI_NOT_NULL);
    this.validURI = validURI;
  }

  /**
   * Gets the valid uri.
   *
   * @return the valid uri
   */
  public String getValidURI() {
    return this.validURI;
  }

  /**
   * Sets whether validation tests should be generated - default is not / false.
   * Optional.
   *
   * @param generateTests
   *          the new generate tests
   */
  public void setGenerateTests(final boolean generateTests) {
    this.generateTests = generateTests;
  }

  /**
   * Gets the valid model.
   *
   * @param grammar
   *          the grammar
   * @return the valid model
   */
  private ValidModel getValidModel(final Grammar grammar) {
    if (model != null) {
      return model;
    }

    Resource resource = null;
    final String name = GrammarUtil.getSimpleName(grammar) + '.' + XTEXT_EXTENSION;
    URI uri;
    for (final Resource res : grammar.eResource().getResourceSet().getResources()) {
      if (res.getURI() != null && name.equals(EmfResourceUtil.getFileName(res.getURI()))) {
        resource = res;
        break;
      }
    }
    if (getValidURI() == null) {
      Assert.isNotNull(resource, NLS.bind(Messages.RESOURCE_NOT_FOUND, name));
      uri = resource.getURI().trimFileExtension().appendFileExtension(VALID_EXTENSION);
    } else {
      uri = URI.createURI(getValidURI());
    }

    resource = resource.getResourceSet().getResource(uri, true);

    final List<Issue> issues = VALIDATOR.validate(resource, LOGGER);
    for (final Issue issue : issues) {
      if (issue.isSyntaxError() || issue.getSeverity() == Severity.ERROR) {
        throw new WorkflowInterruptedException(NLS.bind(Messages.ERROR_FOUND, uri.toString()));
      }
    }
    model = (ValidModel) resource.getContents().get(0);
    return model;
  }

  /** {@inheritDoc} */
  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind(Messages.EXECUTING_GENERATE, getClass().getName()));
    }

    ValidStandaloneSetup.doSetup();

    final ValidModel validModel = getValidModel(grammar);
    grammar.eResource().getResourceSet().getResources().add(validModel.eResource());
    grammar.eResource().getResourceSet().getResources().add(validModel.getImports().get(0).eResource());

    // profile for generator is:
    // <<DEFINE generate(List[String] packageQNames, List[String] composedChecks, ValidModel validModel) FOR Grammar>>
    XpandFacade.create(ctx).evaluate(getTemplate() + "::generate", // template //$NON-NLS-1$
        grammar, // this (the grammar)
        getParameters(grammar).get(0), // package Qualified Names (first parameter of the argument list is the 'list of
                                       // packages')
        this.composedChecks, // composed checks
        validModel); // validModel

    if (generateTests) {
      XpandFacade.create(ctx).evaluate("com::avaloq::tools::ddk::xtext::valid::generator::ValidatorTests::generate", // template //$NON-NLS-1$
          grammar, // this (the grammar)
          validModel); // validModel
      XpandFacade.create(ctx).evaluate("com::avaloq::tools::ddk::xtext::valid::generator::QuickfixTests::generate", // template //$NON-NLS-1$
          grammar, // this (the grammar)
          validModel); // validModel
    }
  }

  /** {@inheritDoc} */
  @Override
  public void addToPluginXmlUi(final Grammar grammar, final XpandExecutionContext ctx) {
    XpandFacade.create(ctx).evaluate(getTemplate() + "::addToPluginXmlUi", grammar, getParameters(grammar), getValidModel(grammar)); //$NON-NLS-1$
  }

  /**
   * Gets the java validator name.
   *
   * @param grammar
   *          the grammar
   * @param prefix
   *          the prefix (common usage is "Abstract")
   * @return the java validator name
   */
  public static String getJavaValidatorName(final Grammar grammar, final String prefix) {
    return GrammarUtil.getNamespace(grammar) + ".validation." + prefix + GrammarUtil.getSimpleName(grammar) + "JavaValidator"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Gets the quickfix package.
   *
   * @param grammar
   *          the grammar
   * @param naming
   *          generator naming variables and properties
   * @return the quickfix package
   */
  public static String getQuickfixPackage(final Grammar grammar, final Naming naming) {
    return naming.basePackageUi(grammar) + ".quickfix"; //$NON-NLS-1$
  }

  /**
   * Gets the quickfix provider name.
   *
   * @param grammar
   *          the grammar
   * @param naming
   *          generator naming variables and properties
   * @return the quickfix provider name
   */
  public static String getQuickfixProviderName(final Grammar grammar, final Naming naming) {
    return getQuickfixPackage(grammar, naming) + "." + GrammarUtil.getSimpleName(grammar) + "QuickfixProvider"; //$NON-NLS-1$//$NON-NLS-2$
  }

  /**
   * Gets the check validator name.
   *
   * @param grammar
   *          the grammar
   * @param naming
   *          generator naming variables and properties
   * @return the check validator name
   */
  public static String getCheckValidatorName(final Grammar grammar, final Naming naming) {
    return naming.basePackageRuntime(grammar) + ".validation." + GrammarUtil.getSimpleName(grammar) + "CheckValidator"; //$NON-NLS-1$//$NON-NLS-2$
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesRt(final Grammar grammar) {
    // For Checks
    // CHECKSTYLE:OFF
    return new String[] {"com.avaloq.tools.ddk.xtext", "org.apache.commons.logging"}; //$NON-NLS-1$//$NON-NLS-2$
  } // CHECKSTYLE:ON

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    // For Checks
    return new String[] {"com.avaloq.tools.ddk.xtext.ui"}; //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public String[] getExportedPackagesRt(final Grammar grammar) {
    final Set<String> exportedPackages = new LinkedHashSet<String>();
    exportedPackages.add(getValidationPackage(grammar, getNaming()));
    exportedPackages.add(getValidationPackage(grammar, getNaming()) + ".messages"); //$NON-NLS-1$
    return exportedPackages.toArray(new String[exportedPackages.size()]);
  }

  @Override
  public String[] getExportedPackagesUi(final Grammar grammar) {
    final Set<String> exportedPackages = new LinkedHashSet<String>();
    if (hasQuickfixes(getValidModel(grammar))) {
      exportedPackages.add(getQuickfixPackage(grammar, getNaming()));
    }
    return exportedPackages.toArray(new String[exportedPackages.size()]);
  }

  /** {@inheritDoc} */
  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {

    return new BindFactory().addTypeToTypeSingleton(getJavaValidatorName(grammar, ""), getJavaValidatorName(grammar, "")).addTypeToType(CompositeEValidator.class.getName(), ValidCompositeEValidator.class.getName()).getBindings(); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Has the underlying model quickfixes.
   *
   * @param validModel
   *          the valid model
   * @return true, if successful
   */
  private boolean hasQuickfixes(final ValidModel validModel) {
    for (final Category category : validModel.getCategories()) {
      for (final Rule rule : category.getRules()) {
        if (rule instanceof NativeRule) {
          for (final NativeContext contextType : ((NativeRule) rule).getContexts()) {
            if (!contextType.getQuickFixes().isEmpty()) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public Set<Binding> getGuiceBindingsUi(final Grammar grammar) {
    if (hasQuickfixes(getValidModel(grammar))) {
      final BindFactory addTypeToInstance = new BindFactory().addTypeToType(IssueResolutionProvider.class.getName(), getQuickfixProviderName(grammar, getNaming()));
      return addTypeToInstance.getBindings();
    } else {
      return Collections.emptySet();
    }
  }

  @Override
  protected boolean isClassExists(final String className) {
    try {
      Class.forName(className);
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    } catch (NoClassDefFoundError e) {
      return false;
    }
  }

}
