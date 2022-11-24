/*******************************************************************************
 * Copyright (c) 2016 itemis AG (http://www.itemis.com) and others.
 * Copyright (c) 2021 Avaloq Group AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.ide.formatting;

import java.util.Collections;
import java.util.List;

import org.eclipse.lsp4j.FormattingOptions;
import org.eclipse.lsp4j.TextEdit;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.ide.server.Document;
import org.eclipse.xtext.ide.server.formatting.FormattingService;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.TextRegion;

import com.google.inject.Inject;


/**
 * A {@link FormattingService} that does not support {@code IFormatter2}.
 * Adapts code for instead supporting {@link INodeModelFormatter} from
 * https://github.com/eclipse/xtext-core/blob/3d94ad1741b9f26b0043dfdae3d062849f187809/org.eclipse.xtext.ide/src/org/eclipse/xtext/ide/server/formatting/FormattingService.xtend
 */
public class FormattingService1 extends FormattingService {

  @Inject(optional = true)
  private INodeModelFormatter formatter1;

  @Override
  public List<TextEdit> format(final XtextResource resource, final Document document, final int offset, final int length, final FormattingOptions options) {
    if (formatter1 != null) {
      IFormattedRegion region = format1(resource, new TextRegion(offset, length));
      if (region != null) {
        return Collections.singletonList(toTextEdit(document, region.getFormattedText(), region.getOffset(), region.getLength()));
      }
    }
    /* If no formatter is configured, then return no edits. */
    return Collections.emptyList();
  }

  /**
   * Format the text of a resource, or a selection thereof.
   *
   * @param resource
   *          the resource
   * @param selection
   *          a selection, may be {@code null} for none
   * @return a formatted region, or {@code null} if the document of the Xtext resource has not been parsed
   * @throws NullPointerException
   *           if there is a parsed document, but no {@link INodeModelFormatter} has been configured
   */
  protected IFormattedRegion format1(final XtextResource resource, final ITextRegion selection) {
    IParseResult parseResult = resource.getParseResult();
    if (parseResult == null) {
      return null;
    }
    ICompositeNode rootNode = parseResult.getRootNode();
    ITextRegion region = selection;
    if (region == null) {
      /* Without a selection format the entire document. */
      region = new TextRegion(rootNode.getOffset(), rootNode.getLength());
    }
    return formatter1.format(rootNode, region.getOffset(), region.getLength());
  }

}
