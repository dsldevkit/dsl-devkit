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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Utility class for combined Xtext-GMF resources.
 */
public class XtextGMFResourceUtil {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(XtextGMFResourceUtil.class);

  /** Size of local input buffer to allocate. */
  private static final int INPUT_BUFFER_SIZE = 2048;

  /** Default encoding to use for separator. */
  private static final String DEFAULT_ENCODING = "UTF-8"; //$NON-NLS-1$

  /** String that separates Xtext part of input from GMF part. */
  private static final String SEPARATOR = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //$NON-NLS-1$

  protected XtextGMFResourceUtil() { // Utility classes do not have a public constructor.
  }

  /**
   * Get the text that separates the Xtext part from the GMF part.
   *
   * @param encoding
   *          the encoding to use for the separator. <code>null</code> if default encoding should be used.
   * @return the separator text as a byte array encoded using <code>encoding</code>
   */
  public static byte[] getSeparator(final String encoding) {
    try {
      return SEPARATOR.getBytes(encoding != null ? encoding : DEFAULT_ENCODING);
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("unsupported encoding: " + encoding, e); //$NON-NLS-1$
      return SEPARATOR.getBytes(); // NOPMD RelianceOnDefaultCharset
    }
  }

  /**
   * Read the full stream into a byte array.
   *
   * @param input
   *          stream to read from
   * @return byte array representation of input
   * @throws IOException
   *           if an I/O occurs.
   */
  public static byte[] readFullStream(final InputStream input) throws IOException {
    InputStream readStream = null;
    boolean wrappedInput = false;
    try {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      byte[] buffer = new byte[INPUT_BUFFER_SIZE];
      if (!(input instanceof BufferedInputStream)) {
        readStream = new BufferedInputStream(input);
        wrappedInput = true;
      } else {
        readStream = input;
      }
      int actualRead;
      do {
        actualRead = readStream.read(buffer);
        if (actualRead > 0) {
          result.write(buffer, 0, actualRead);
        }
      } while (actualRead >= 0);

      return result.toByteArray();
    } finally {
      try {
        if (wrappedInput && readStream != null) {
          readStream.close();
        }
      } catch (IOException e) {
        LOGGER.error("Error closing wrapped input stream", e); //$NON-NLS-1$ // to late to worry
      }
    }
  }

  /**
   * Locate separator within input.
   *
   * @param input
   *          byte array containing encoded input.
   * @param separator
   *          byte array containing encoded separator
   * @return position of <code>separator</code> within <code>input</code>
   */
  public static int findSeparator(final byte[] input, final byte[] separator) {
    int max = input.length - separator.length + 1; // last possible position of separator + 1
    int i = 0;
    while (i < max) {
      if (input[i] != separator[0]) {
        i++;
        while (i < max && input[i] != separator[0]) {
          i++;
        }
      }
      // (input[i] = separator[0] && i < max) || i >= max
      if (i < max) {
        int end = i + separator.length;
        int j = i + 1;
        for (int k = 1; j < end && input[j] == separator[k]; k++) {
          j++;
        }
        if (j == end) {
          return i;
        }
      }
      i++;
    }
    return -1;
  }

}
