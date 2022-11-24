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
package com.avaloq.tools.ddk.xtext.util;

import java.io.ByteArrayInputStream;
import java.util.zip.InflaterInputStream;


/**
 * Utility class to simplify storing a deflater output stream result to a byte array.
 */
public final class ZippedByteArrayInputStream extends InflaterInputStream {
  public ZippedByteArrayInputStream(final byte[] data) {
    super(new ByteArrayInputStream(data));
  }
}
