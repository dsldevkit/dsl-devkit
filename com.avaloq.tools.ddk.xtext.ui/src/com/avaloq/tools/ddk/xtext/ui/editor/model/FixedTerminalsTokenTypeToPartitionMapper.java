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
package com.avaloq.tools.ddk.xtext.ui.editor.model;

import org.antlr.runtime.Token;
import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.ui.editor.model.TerminalsTokenTypeToPartitionMapper;

import com.google.inject.Singleton;


/**
 * Fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=385667 (Inconsistent token mappers confuse the DocumentPartitioner).
 */
@Singleton
public class FixedTerminalsTokenTypeToPartitionMapper extends TerminalsTokenTypeToPartitionMapper {
  @Override
  public String getPartitionType(final int antlrTokenType) {
    // on lexer error return default content type
    if (antlrTokenType == Token.INVALID_TOKEN_TYPE) {
      return IDocument.DEFAULT_CONTENT_TYPE;
    }
    return getMappedValue(antlrTokenType);
  }
}
