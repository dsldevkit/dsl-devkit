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
package com.avaloq.tools.ddk.xtext.formatting;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting.impl.DefaultNodeModelFormatter;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;
import org.eclipse.xtext.parsetree.reconstr.impl.TokenStringBuffer;
import org.eclipse.xtext.util.ITextRegion;


/**
 * RegionNodeModelFormatter extends DefaultNodeModelFormatter to provide proper support for
 * formatting of text regions. To accomplish this the entire text up to offset + length
 * is formatted, but the text up to offset is dumped into a null stream. That is so that state
 * can be built inside FormattingConfigBasedStream.
 */
public class RegionNodeModelFormatter extends DefaultNodeModelFormatter {

  /**
   * FilterFirstWhitespaceStream is only overridden so that instances thereof can be instantiated
   * inside this class.
   * We need a filter that ignores everything until the first node.
   */
  public class FilterStream extends FilterFirstWhitespaceStream {

    protected FilterStream(final ITokenStream out) {
      super(out);
      enabled = false;
    }

    private boolean enabled;

    /**
     * Start capturing the input.
     */
    public void start() {
      enabled = true;
    };

    private boolean firstPassed;

    @Override
    public void writeHidden(final EObject grammarElement, final String value) throws IOException {
      if (enabled) {
        if (firstPassed) {
          out.writeHidden(grammarElement, value);
        } else {
          // Ignore all before the last new line. The only thing we need is the
          // padding for our first line
          out.writeHidden(grammarElement, value.indexOf('\n') < 0 ? value : StringUtils.substringAfterLast(value, "\n")); //$NON-NLS-1$
          firstPassed = true;
        }
      }
    }

    @Override
    public void writeSemantic(final EObject grammarElement, final String value) throws IOException {
      if (enabled) {
        firstPassed = true;
        out.writeSemantic(grammarElement, value);
      }
    }
  }

  @Override
  public IFormattedRegion format(final ICompositeNode root, final int offset, final int length) {
    final TokenStringBuffer buf = new TokenStringBuffer();
    final ITokenStream out = offset == 0 ? buf : new FilterStream(buf);
    final ITokenStream formatStream = formatter.createFormatterStream(null, out, false);
    if (!(formatStream instanceof IDelegatingTokenStream)) {
      return super.format(root, offset, length);
    }
    try {
      ITextRegion range;
      if (offset > 0) {
        int noffset = root.getText().lastIndexOf('\n', offset) + 1; // Keep the new line
        int nlength = length + (offset - noffset); // Always start in the beginning of the line
        range = nodeModelStreamer.feedTokenStream(formatStream, root, noffset, nlength);
      } else {
        range = nodeModelStreamer.feedTokenStream(formatStream, root, offset, length);
      }
      return new FormattedRegion(range.getOffset(), range.getLength(), buf.toString());
    } catch (IOException e) {
      // this should never happen since TokenStringBuffer doesn't throw IOEs.
      throw new RuntimeException(e); // NOPMD
    }
  }
}
