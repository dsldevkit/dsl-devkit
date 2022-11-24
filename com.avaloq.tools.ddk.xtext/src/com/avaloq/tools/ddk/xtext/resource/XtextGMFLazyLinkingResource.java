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
package com.avaloq.tools.ddk.xtext.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.eclipse.xtext.resource.XtextResource;

import com.avaloq.tools.ddk.xtext.linking.LazyLinkingResource2;


/**
 * A Lazy Linking Resource that handles combined Xtext and GMF models.
 * This class does not override doSave on purpose. So a call to doSave
 * will serialize only the model into the specified output stream.
 */
public class XtextGMFLazyLinkingResource extends LazyLinkingResource2 implements IXtextGMFResource {

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(XtextGMFLazyLinkingResource.class);

  /** Encoding used to load resource. */
  private String encoding;

  /** Buffer containing loaded Xtext part. */
  private byte[] xtextBuffer;

  /** Separator encoded with encoding. */
  private byte[] separator;

  /** Buffer containing loaded GMF diagram part. */
  private byte[] diagramBuffer;

  /**
   * Load the complete combined resource and split it into Xtext and Diagram parts.
   * 
   * @param input
   *          the input stream to read the resource from.
   * @param encodingForLoad
   *          character encoding to use for resource
   * @throws IOException
   *           if there is an IO error while reading resource
   */
  protected void loadFromFullContents(final InputStream input, final String encodingForLoad) throws IOException {
    this.encoding = encodingForLoad;
    byte[] fullContents = XtextGMFResourceUtil.readFullStream(input);
    separator = XtextGMFResourceUtil.getSeparator(encoding).clone();
    int position = XtextGMFResourceUtil.findSeparator(fullContents, separator);
    if (position >= 0) {
      xtextBuffer = new byte[position];
      diagramBuffer = new byte[fullContents.length - position - separator.length];

      System.arraycopy(fullContents, 0, xtextBuffer, 0, position);
      System.arraycopy(fullContents, position + separator.length, diagramBuffer, 0, fullContents.length - position - separator.length);
    } else {
      xtextBuffer = fullContents;
      diagramBuffer = new byte[0];
    }

  }

  /**
   * Get encoding options for loading.
   * 
   * @param loadOptions
   *          options map from which to obtain encoding options
   * @return returns encoding options from <code>loadOptions</code> if present, otherwise default encoding options.
   */
  private String getEncodingFromOptions(final Map<?, ?> loadOptions) {
    String resultEncoding = getEncoding();
    if (loadOptions != null && loadOptions.containsKey(XtextResource.OPTION_ENCODING)) {
      resultEncoding = (String) loadOptions.get(XtextResource.OPTION_ENCODING);
    }
    return resultEncoding;
  }

  /** {@inheritDoc} */
  public InputStream getXtextInput() {
    return new ByteArrayInputStream(xtextBuffer != null ? xtextBuffer : new byte[0]);
  }

  /** {@inheritDoc} */
  @Override
  public String getSourceText() throws IOException {
    return new String(xtextBuffer != null ? xtextBuffer : new byte[0], getEncoding());
  }

  /** {@inheritDoc} */
  public String getDiagramText() {
    if (isLoaded()) {
      StringBuffer result = new StringBuffer();
      try {
        if (diagramBuffer != null && diagramBuffer.length > 0) {
          result.append(new String(separator, encoding));
          result.append(new String(diagramBuffer, encoding));
        }
      } catch (UnsupportedEncodingException e) {
        LOGGER.error("Strange, encoding: " + encoding + " was OK when resource loaded"); //$NON-NLS-1$ //$NON-NLS-2$
        return EMPTY_STRING;
      }
      return result.toString();
    } else {
      LOGGER.error("Attempt to get diagram text when resource not loaded"); //$NON-NLS-1$
      return EMPTY_STRING;
    }
  }

  @Override
  protected void doLoad(final InputStream inputStream, final Map<?, ?> options) throws IOException {
    loadFromFullContents(inputStream, getEncodingFromOptions(options));
    super.doLoad(getXtextInput(), options);
  }

  @Override
  public void reparse(final String newContent) throws IOException {
    super.reparse(newContent + getDiagramText());
  }

  /** {@inheritDoc} */
  public boolean isCombinedResource() {
    return diagramBuffer != null && diagramBuffer.length > 0;
  }

  @Override
  protected void doUnload() {
    super.doUnload();
    diagramBuffer = null; // NOPMD
    xtextBuffer = null; // NOPMD
    separator = null; // NOPMD
  }

  /** {@inheritDoc} */
  public void updateDiagram(final IXtextGMFResource fromResource) throws CharacterCodingException {
    if (fromResource instanceof XtextGMFLazyLinkingResource && encoding.equals(((XtextGMFLazyLinkingResource) fromResource).encoding)) {
      // Note that the content of the diagram buffer is never modified. So it
      // is safe to share the buffer.
      diagramBuffer = ((XtextGMFLazyLinkingResource) fromResource).diagramBuffer;
    } else {
      ByteBuffer buffer = Charset.forName(encoding).newEncoder().encode(CharBuffer.wrap(fromResource.getDiagramText()));
      if (buffer.hasArray()) {
        diagramBuffer = buffer.array();
      } else {
        diagramBuffer = new byte[buffer.limit()];
        buffer.get(diagramBuffer);
      }

    }
  }
}

