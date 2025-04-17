/*
 * Copyright (c) Avaloq Licence AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.tools.ddk.xtext.build;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

import com.avaloq.tools.ddk.xtext.build.IBinaryModelStore.NullBinaryModelStore;
import com.google.inject.ImplementedBy;


/**
 * Manages the binary resources.
 */
@ImplementedBy(NullBinaryModelStore.class)
public interface IBinaryModelStore {

  String SIZE_OPTION = "SIZE"; //$NON-NLS-1$

  /**
   * Creates an input stream from which a binary model can be read.
   *
   * @param uri
   *          binary model URI, must not be {@code null}
   * @param options
   *          a map of options to influence the kind of stream that is returned; can be {@code null} and unrecognized options are ignored
   * @return input stream which can be used to read binary model
   * @throws IOException
   *           in case of an I/O error
   */
  InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException;

  /**
   * Creates an output stream to which a binary model can be written.
   *
   * @param uri
   *          binary model URI, must not be {@code null}
   * @param options
   *          a map of options to influence the kind of stream that is returned; can be {@code null} and unrecognized options are ignored
   * @return output stream which can be used to write binary model
   * @throws IOException
   *           if a database error occurred
   */
  OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException;

  /**
   * Checks whether a binary model exists with the given URI.
   *
   * @param uri
   *          binary model URI, must not be {@code null}
   * @return {@code true} if binary model with given URI exists
   * @throws IOException
   *           if a database error occurred
   */
  boolean exists(URI uri) throws IOException;

  /**
   * Deletes the binary model for the given URI.
   *
   * @param uri
   *          binary model URI, must not be {@code null}
   * @throws IOException
   *           if I/O exception occurred
   */
  void delete(URI uri) throws IOException;

  /**
   * Invalidate cached data for the given collection of URIs.
   *
   * @param uris
   *          the URIs to invalidate cached data for.
   */
  default void invalidateCache(final Collection<URI> uris) {
    // do nothing by default
  }

  /**
   * Default no-op implementation throwing runtime exceptions when calling {@link #createInputStream(URI)} or {@link #createOutputStream(URI)} as that means
   * the application has not been configured correctly.
   */
  class NullBinaryModelStore implements IBinaryModelStore {

    @Override
    public InputStream createInputStream(final URI uri, final Map<?, ?> options) throws IOException {
      throw new UnsupportedOperationException();
    }

    @Override
    public OutputStream createOutputStream(final URI uri, final Map<?, ?> options) throws IOException {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(final URI uri) throws IOException {
      return false;
    }

    @Override
    public void delete(final URI uri) throws IOException {
      throw new UnsupportedOperationException();
    }

  }
}

/* Copyright (c) Avaloq Licence AG */