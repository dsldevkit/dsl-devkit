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
package com.avaloq.tools.ddk.xtext.format.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowInterruptedException;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.Generator;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.format.FormatConstants;
import com.avaloq.tools.ddk.xtext.format.FormatStandaloneSetup;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.generator.util.ModelValidator;


/**
 * Various utility functions for the format generator.
 */
@SuppressWarnings("deprecation")
public final class FormatFragmentUtil {

  /** Class-wide logger. */
  private static final Logger LOG = LogManager.getLogger(FormatFragmentUtil.class);

  /** Resource checker used to validate loaded model. */
  private static ModelValidator modelValidator;

  /**
   * Inhibit public instantiation.
   */
  private FormatFragmentUtil() {
    // No public constructor
  }

  /**
   * Returns a URI corresponding to the default location for format files. This is in the SRC outlet where the Xtext grammar file usually is.
   *
   * @param grammar
   *          Xtext grammar to get format file URI for
   * @param ctx
   *          xpand execution context (defines required SRC outlet)
   * @return the file URI to the default format file location
   */
  private static URI getDefaultFormatLocation(final Grammar grammar, final XpandExecutionContext ctx) {
    final String xmiPath = GrammarUtil.getClasspathRelativePathToXmi(grammar);
    return URI.createFileURI(new File(ctx.getOutput().getOutlet(Generator.SRC).getPath(), xmiPath).getAbsolutePath()).trimFileExtension().appendFileExtension(FormatConstants.FILE_EXTENSION);
  }

  /**
   * Retrieve the format model associated with a given grammar.
   * <p>
   * <em>Note</em>: Expected to either be in same folder with the same name (except for the extension) or in the SRC outlet.
   * </p>
   *
   * @param grammar
   *          the grammar, must not be {@code null}
   * @param context
   *          xpand execution context, must not be {@code null}
   * @return the format model, or {@code null} if the resource could not be loaded
   * @throws FileNotFoundException
   *           thrown if the format file could not be found
   */
  @SuppressWarnings("PMD.NPathComplexity")
  public static FormatConfiguration getFormatModel(final Grammar grammar, final XpandExecutionContext context) throws FileNotFoundException {
    Variable resourceUriVariable = context.getVariable("resourceUri");
    if (resourceUriVariable == null) {
      return null;
    }
    URI uri = (URI) resourceUriVariable.getValue();
    final Resource grammarResource = grammar.eResource();
    final ResourceSet resourceSet = grammarResource.getResourceSet();
    Resource formatResource = null;
    try {
      formatResource = resourceSet.getResource(uri, true);
    } catch (final ClasspathUriResolutionException e) {
      // make another attempt
      uri = getDefaultFormatLocation(grammar, context);
      try {
        formatResource = resourceSet.getResource(uri, true);
      } catch (WrappedException ex) {
        formatResource = resourceSet.getResource(uri, false);
        if (formatResource != null) {
          resourceSet.getResources().remove(formatResource);
        }
        throw new FileNotFoundException(uri.toString()); // NOPMD
      }
    }
    if (formatResource == null) {
      throw new FileNotFoundException(uri.toString());
    }

    final List<Issue> issues = getModelValidator().validate(formatResource, LOG);

    for (final Issue issue : issues) {
      if (issue.isSyntaxError() || issue.getSeverity() == Severity.ERROR) {
        throw new WorkflowInterruptedException("Errors found in " + uri.toString() + ": " + issue.getMessage());
      }
    }

    return formatResource.getContents().size() == 0 ? null : (FormatConfiguration) formatResource.getContents().get(0);
  }

  /**
   * Returns the {@link ModelValidator}.
   *
   * @return the {@link ModelValidator}, never {@code null}
   */
  private static synchronized ModelValidator getModelValidator() {
    if (modelValidator == null) {
      modelValidator = new FormatStandaloneSetup().createInjector().getInstance(ModelValidator.class);
    }
    return modelValidator;
  }

}
