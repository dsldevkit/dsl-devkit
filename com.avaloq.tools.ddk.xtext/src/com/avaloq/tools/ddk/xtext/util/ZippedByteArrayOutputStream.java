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
package com.avaloq.tools.ddk.xtext.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;


/**
 * Utility class to simplify fetching the byte array result from a deflater output stream.
 */
public final class ZippedByteArrayOutputStream extends DeflaterOutputStream {
  public ZippedByteArrayOutputStream(final int size) {
    super(new ByteArrayOutputStream(size), new Deflater(Deflater.BEST_COMPRESSION));
  }

  /**
   * Retrieves the byte array form the underlying {@link ByteArrayOutputStream}.
   *
   * @return the byte array
   */
  public byte[] toByteArray() {
    return ((ByteArrayOutputStream) out).toByteArray();
  }
}
