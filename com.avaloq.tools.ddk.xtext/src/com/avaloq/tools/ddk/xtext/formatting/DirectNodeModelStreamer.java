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
package com.avaloq.tools.ddk.xtext.formatting;

import java.io.IOException;

import org.eclipse.xtext.formatting.impl.NodeModelStreamer;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;


/**
 * Custom implementation of INodeModelStreamer which does not use the IValueConverterService to format the tokens corresponding to
 * datatype rules. The reason is that the value converter doesn't know how to format the token when there are multiple valid
 * alternatives.
 */

public class DirectNodeModelStreamer extends NodeModelStreamer {

  @Override
  protected void writeSemantic(final ITokenStream out, final ICompositeNode node) throws IOException {
    final String text = tokenUtil.serializeNode(node);
    if (out instanceof ExtendedFormattingConfigBasedStream) {
      ((ExtendedFormattingConfigBasedStream) out).setNode(node);
    }
    out.writeSemantic(node.getGrammarElement(), text);
  }

  @Override
  protected void writeHidden(final ITokenStream out, final ILeafNode node) throws IOException {
    if (out instanceof ExtendedFormattingConfigBasedStream) {
      ((ExtendedFormattingConfigBasedStream) out).setNode(node);
    }
    out.writeHidden(node.getGrammarElement(), node.getText());
  }

  @Override
  protected void writeSemantic(final ITokenStream out, final ILeafNode node) throws IOException {
    if (out instanceof ExtendedFormattingConfigBasedStream) {
      ((ExtendedFormattingConfigBasedStream) out).setNode(node);
    }
    out.writeSemantic(node.getGrammarElement(), node.getText());
  }

}

