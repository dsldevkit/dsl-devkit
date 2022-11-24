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
package com.avaloq.tools.ddk.xtext.serializer;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting.IFormatterExtension;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;
import org.eclipse.xtext.parsetree.reconstr.impl.TokenStringBuffer;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.ISequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.TokenStreamSequenceAdapter;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic;
import org.eclipse.xtext.serializer.impl.Serializer;


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
    ISerializationDiagnostic.Acceptor errors = ISerializationDiagnostic.EXCEPTION_THROWING_ACCEPTOR;
    ITokenStream formatterTokenStream;
    if (formatter instanceof IFormatterExtension) {
      formatterTokenStream = ((IFormatterExtension) formatter).createFormatterStream(obj, initialIndentation, tokenStream, !options.isFormatting());
    } else {
      formatterTokenStream = formatter.createFormatterStream(initialIndentation, tokenStream, !options.isFormatting());
    }
    ISerializationContext context = getIContext(obj);
    ISequenceAcceptor acceptor = new TokenStreamSequenceAdapter(formatterTokenStream, grammar.getGrammar(), errors);
    serialize(context, obj, acceptor, errors);
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
