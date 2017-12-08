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
package com.avaloq.tools.ddk.xtext.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting.IFormatterExtension;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;
import org.eclipse.xtext.parsetree.reconstr.impl.TokenStringBuffer;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.acceptor.ISequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.TokenStreamSequenceAdapter;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic;
import org.eclipse.xtext.serializer.impl.Serializer;
import org.eclipse.xtext.validation.IConcreteSyntaxValidator;


/**
 * Implements IIndentPreservingSerializer based on {@link Serializer}.
 */
public class IndentingSerializer extends Serializer implements IIndentingSerializer {

  /** {@inheritDoc} */
  @Override
  public void serialize(final EObject obj, final ITokenStream tokenStream, final SaveOptions options) throws IOException {
    serialize(obj, tokenStream, options, null);
  }

  /**
   * Serialize the given object into tokenStream using save options.
   * The initial indentation is passed on to the formatter.
   * This implementation is based on {@link Serializer#serialize(EObject, ITokenStream, SaveOptions)}.
   *
   * @param obj
   *          the obj
   * @param tokenStream
   *          the token stream
   * @param options
   *          the options
   * @param initialIndentation
   *          the initial indentation
   * @throws IOException
   *           Signals that an I/O exception has occurred.
   */
  protected void serialize(final EObject obj, final ITokenStream tokenStream, final SaveOptions options, final String initialIndentation) throws IOException {
    if (options.isValidating()) {
      List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
      validator.validateRecursive(obj, new IConcreteSyntaxValidator.DiagnosticListAcceptor(diagnostics), new HashMap<Object, Object>());
      if (!diagnostics.isEmpty()) {
        throw new IConcreteSyntaxValidator.InvalidConcreteSyntaxException("These errors need to be fixed before the model can be serialized.", diagnostics); //$NON-NLS-1$
      }
    }

    ISerializationDiagnostic.Acceptor errors = ISerializationDiagnostic.EXCEPTION_THROWING_ACCEPTOR;
    ITokenStream formatterTokenStream;
    if (formatter instanceof IFormatterExtension) {
      formatterTokenStream = ((IFormatterExtension) formatter).createFormatterStream(obj, initialIndentation, tokenStream, !options.isFormatting());
    } else {
      formatterTokenStream = formatter.createFormatterStream(initialIndentation, tokenStream, !options.isFormatting());
    }
    EObject context = getContext(obj);
    ISequenceAcceptor acceptor = new TokenStreamSequenceAdapter(formatterTokenStream, errors);
    serialize(obj, context, acceptor, errors);
    formatterTokenStream.flush();
  }

  /**
   * This method implementation is copied from {@link Serializer#serialize(EObject, SaveOptions)} with the addition of the indentation parameter. {@inheritDoc}
   */
  @Override
  public String serialize(final EObject obj, final SaveOptions options, final String initialIndentation) {
    TokenStringBuffer tokenStringBuffer = new TokenStringBuffer();
    try {
      serialize(obj, tokenStringBuffer, options, initialIndentation);
    } catch (IOException e) {
      throw new RuntimeException(e);// NOPMD
    }
    return tokenStringBuffer.toString();
  }

}
