/*******************************************************************************
 * Copyright (c) 2024 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;


/**
 * Like {@code BufferedInputStream} without supporting concurrency.
 */
@SuppressWarnings("nls")
public class NonLockingBufferInputStream extends FilterInputStream {

  private static final int DEFAULT_BUFFER_SIZE = 8192;
  private static final byte[] EMPTY = new byte[0];
  private final int initialSize;

  private byte[] buf;
  private int count;
  private int pos;
  private int markpos = -1;
  private int marklimit;

  private byte[] getBufIfOpen(final boolean allocateIfEmpty) throws IOException {
    if (allocateIfEmpty && buf == EMPTY) {
      buf = new byte[initialSize];
    }
    return buf;
  }

  private byte[] getBufIfOpen() throws IOException {
    return getBufIfOpen(true);
  }

  private void ensureOpen() throws IOException {
    if (buf == null) {
      throw new IOException("Stream closed");
    }
  }

  /**
   * Creates a {@code NonLockingBufferInputStream}.
   *
   * @param in
   *          the underlying input stream.
   */
  public NonLockingBufferInputStream(final InputStream in) {
    this(in, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Creates a {@code NonLockingBufferInputStream}
   * with the specified buffer size.
   *
   * @param in
   *          the underlying input stream.
   * @param size
   *          the buffer size.
   * @throws IllegalArgumentException
   *           if {@code size <= 0}.
   */
  public NonLockingBufferInputStream(final InputStream in, final int size) {
    super(in);
    if (size <= 0) {
      throw new IllegalArgumentException("Buffer size <= 0");
    }
    initialSize = size;
    buf = new byte[size];
  }

  private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

  private static int newLength(final int oldLength, final int minGrowth, final int prefGrowth) {
    int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
    if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
      return prefLength;
    } else {
      return hugeLength(oldLength, minGrowth);
    }
  }

  private static int hugeLength(final int oldLength, final int minGrowth) {
    int minLength = oldLength + minGrowth;
    if (minLength < 0) { // overflow
      throw new OutOfMemoryError("Required array length " + oldLength + " + " + minGrowth + " is too large");
    } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
      return SOFT_MAX_ARRAY_LENGTH;
    } else {
      return minLength;
    }
  }

  private void fill() throws IOException {
    byte[] buffer = getBufIfOpen();
    if (markpos == -1) {
      pos = 0; /* no mark: throw away the buffer */
    } else if (pos >= buffer.length) { /* no room left in buffer */
      if (markpos > 0) { /* can throw away early part of the buffer */
        int sz = pos - markpos;
        System.arraycopy(buffer, markpos, buffer, 0, sz);
        pos = sz;
        markpos = 0;
      } else if (buffer.length >= marklimit) {
        markpos = -1; /* buffer got too big, invalidate mark */
        pos = 0; /* drop buffer contents */
      } else { /* grow buffer */
        int nsz = newLength(pos, 1, pos);
        if (nsz > marklimit) {
          nsz = marklimit;
        }
        byte[] nbuf = new byte[nsz];
        System.arraycopy(buffer, 0, nbuf, 0, pos);
        buffer = nbuf;
      }
    }
    count = pos;
    int n = in.read(buffer, pos, buffer.length - pos);
    if (n > 0) {
      count = n + pos;
    }
  }

  @Override
  @SuppressWarnings("checkstyle:MagicNumberCheck")
  public int read() throws IOException {
    if (pos >= count) {
      fill();
      if (pos >= count) {
        return -1;
      }
    }
    return getBufIfOpen()[pos++] & 0xff;
  }

  private int read1(final byte[] b, final int off, final int len) throws IOException {
    int avail = count - pos;
    if (avail <= 0) {
      /*
       * If the requested length is at least as large as the buffer, and
       * if there is no mark/reset activity, do not bother to copy the
       * bytes into the local buffer. In this way buffered streams will
       * cascade harmlessly.
       */
      int size = Math.max(getBufIfOpen(false).length, initialSize);
      if (len >= size && markpos == -1) {
        return in.read(b, off, len);
      }
      fill();
      avail = count - pos;
      if (avail <= 0) {
        return -1;
      }
    }
    int cnt = (avail < len) ? avail : len;
    System.arraycopy(getBufIfOpen(), pos, b, off, cnt);
    pos += cnt;
    return cnt;
  }

  @Override
  public int read(final byte[] b, final int off, final int len) throws IOException {
    ensureOpen();
    if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
      throw new IndexOutOfBoundsException();
    } else if (len == 0) {
      return 0;
    }

    int n = 0;
    for (;;) {
      int nread = read1(b, off + n, len - n);
      if (nread <= 0) {
        return (n == 0) ? nread : n;
      }
      n += nread;
      if (n >= len) {
        return n;
      }
      // if not closed but no bytes available, return
      InputStream input = in;
      if (input != null && input.available() <= 0) {
        return n;
      }
    }
  }

  @Override
  public long skip(final long n) throws IOException {
    ensureOpen();
    if (n <= 0) {
      return 0;
    }
    long avail = count - pos;

    if (avail <= 0) {
      // If no mark position set then don't keep in buffer
      if (markpos == -1) {
        return in.skip(n);
      }

      // Fill in buffer to save bytes for reset
      fill();
      avail = count - pos;
      if (avail <= 0) {
        return 0;
      }
    }

    long skipped = (avail < n) ? avail : n;
    pos += (int) skipped;
    return skipped;
  }

  @Override
  public int available() throws IOException {
    int n = count - pos;
    int avail = in.available();
    return n > (Integer.MAX_VALUE - avail) ? Integer.MAX_VALUE : n + avail;
  }

  @Override
  public void mark(final int readlimit) {
    marklimit = readlimit;
    markpos = pos;
  }

  @Override
  public void reset() throws IOException {
    ensureOpen();
    if (markpos < 0) {
      throw new IOException("Resetting to invalid mark");
    }
    pos = markpos;
  }

  @Override
  public boolean markSupported() {
    return true;
  }

  @Override
  public void close() throws IOException {
    while (buf != null) {
      buf = null;
      InputStream input = in;
      in = null;
      if (input != null) {
        input.close();
      }
      return;
    }
  }

  @Override
  public long transferTo(final OutputStream out) throws IOException {
    Objects.requireNonNull(out, "out");
    if (markpos == -1) {
      int avail = count - pos;
      if (avail > 0) {
        // Prevent poisoning and leaking of buf
        byte[] buffer = Arrays.copyOfRange(getBufIfOpen(), pos, count);
        out.write(buffer);
        pos = count;
      }
      try {
        return Math.addExact(avail, in.transferTo(out));
      } catch (ArithmeticException ignore) {
        return Long.MAX_VALUE;
      }
    } else {
      return super.transferTo(out);
    }
  }

}
