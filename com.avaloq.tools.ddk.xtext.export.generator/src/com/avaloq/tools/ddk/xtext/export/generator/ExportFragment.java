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
package com.avaloq.tools.ddk.xtext.export.generator;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowInterruptedException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.export.ExportStandaloneSetup;
import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.generator.util.ModelValidator;
import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


/**
 * MWE fragment for the export language.
 */
public class ExportFragment extends AbstractGeneratorFragment {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(ExportFragment.class);

  /** Our file extension. */
  private static final String EXPORT_FILE_EXTENSION = "export"; //$NON-NLS-1$

  /** Resource checker used to validate loaded model. */
  private static final ModelValidator VALIDATOR = new ExportStandaloneSetup().createInjector().getInstance(ModelValidator.class);

  /** The model for the export resource. */
  private ExportModel model;

  /** Flag telling us whether we already tried to load the model. */
  private boolean modelLoaded;

  /** Optional export file URI. If not set, the fragment assumes the export file is in the same place as the grammar's xtext file. */
  private String exportFileURI;

  /**
   * Sets the URI of the export file. If non given, the fragment assumes the export file is in the same place as the grammar's xtext file.
   *
   * @param exportFileURI
   *          The URI
   */
  public void setExportFileURI(final String exportFileURI) {
    if (exportFileURI != null) {
      String trimmed = exportFileURI.trim();
      if (trimmed.length() > 0) {
        this.exportFileURI = trimmed;
      }
    }
  }

  /**
   * Get the URI of the export file.
   *
   * @return The string representation of the URI of the export file.
   */
  private String getExportFileURI() {
    return exportFileURI;
  }

  /**
   * Get the export model that we have to process.
   *
   * @param grammar
   *          The grammar
   * @return The model
   */
  private synchronized ExportModel getModel(final Grammar grammar) { // NOPMD NPathComplexity by wth on 24.11.10 08:22
    if (modelLoaded) {
      return model;
    }
    modelLoaded = true;
    Resource resource = grammar.eResource();
    if (resource == null) {
      return null;
    }

    final ResourceSet resourceSet = resource.getResourceSet();
    URI uri = null;
    if (getExportFileURI() != null) {
      uri = URI.createURI(getExportFileURI());
    } else {
      uri = grammar.eResource().getURI().trimFileExtension().appendFileExtension(EXPORT_FILE_EXTENSION);
    }
    try {
      resource = resourceSet.getResource(uri, true);
      final List<Issue> issues = VALIDATOR.validate(resource, LOGGER);

      for (final Issue issue : issues) {
        if (issue.isSyntaxError() || issue.getSeverity() == Severity.ERROR) {
          throw new WorkflowInterruptedException(NLS.bind(Messages.ExportFragment_EXPORT_ERRORS, uri));
        }
      }
      if (resource.getContents().size() == 0) {
        return null;
      }
      model = (ExportModel) resource.getContents().get(0);
      return model;
    } catch (final ClasspathUriResolutionException e) {
      // Resource does not exist.
      if (getExportFileURI() != null) {
        // Explicit file specified, but not found: stop the workflow.
        throw new WorkflowInterruptedException(NLS.bind(Messages.ExportFragment_NO_EXPORT_FILE, uri)); // NOPMD PreserveStackTrace by wth on 24.11.10 08:27
      }
      // No file found at implicit location: work with a null model, generating code that implements the default behavior.
      return null;
    }
  }

  /** {@inheritDoc} */
  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind(Messages.ExportFragment_EXECUTING_GENERATE, getClass().getName()));
    }
    ExportStandaloneSetup.doSetup();
    model = getModel(grammar);
  }

  /** {@inheritDoc} */
  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    final BindFactory bindFactory = new BindFactory();
    final String namingPrefix = GrammarUtil.getNamespace(grammar) + ".naming." + GrammarUtil.getSimpleName(grammar); //$NON-NLS-1$
    final String resourcePrefix = GrammarUtil.getNamespace(grammar) + ".resource." + GrammarUtil.getSimpleName(grammar); //$NON-NLS-1$

    ExportModel m = getModel(grammar);
    if (m != null) {
      bindFactory.addTypeToType(IQualifiedNameProvider.class.getName(), namingPrefix + "ExportedNamesProvider"); //$NON-NLS-1$
      if (!m.getInterfaces().isEmpty()) {
        bindFactory.addTypeToType(IFingerprintComputer.class.getName(), resourcePrefix + "FingerprintComputer"); //$NON-NLS-1$
      }
      if (!m.getExports().isEmpty()) {
        bindFactory.addTypeToType(IDefaultResourceDescriptionStrategy.class.getName(), resourcePrefix + "ResourceDescriptionStrategy"); //$NON-NLS-1$
      }
      if (Iterables.any(m.getExports(), new Predicate<Export>() {
        @Override
        public boolean apply(final Export input) {
          return input.isFingerprint() && input.getFragmentAttribute() != null;
        }
      })) {
        bindFactory.addTypeToType(IFragmentProvider.class.getName(), resourcePrefix + "FragmentProvider"); //$NON-NLS-1$
      }
    }
    bindFactory.addTypeToType(IResourceDescription.Manager.class.getName(), resourcePrefix + "ResourceDescriptionManager"); //$NON-NLS-1$
    return bindFactory.getBindings();
  }

  /** Bundle name for our DDK xtext runtime. */
  private static final String DDK_XTEXT_RUNTIME_BUNDLE = "com.avaloq.tools.ddk.xtext"; //$NON-NLS-1$

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesRt(final Grammar grammar) {
    return new String[] {"org.eclipse.emf.ecore", DDK_XTEXT_RUNTIME_BUNDLE}; //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    return new String[] {DDK_XTEXT_RUNTIME_BUNDLE};
  }

  /** {@inheritDoc} */
  @Override
  public String[] getExportedPackagesRt(final Grammar grammar) {
    if (getModel(grammar) != null) {
      return new String[] {GrammarUtil.getNamespace(grammar) + ".naming", GrammarUtil.getNamespace(grammar) + ".resource"}; //$NON-NLS-1$ //$NON-NLS-2$
    } else {
      return new String[] {GrammarUtil.getNamespace(grammar) + ".resource"}; //$NON-NLS-1$
    }
  }

}
