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
package com.avaloq.tools.ddk.xtext.generator.util;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.mwe.utils.GenModelHelper;
import org.eclipse.emf.mwe.utils.StandaloneSetup;

import com.google.common.collect.Lists;


/**
 * Subclass of StandaloneSetup which adds a property registerGenModelFiles which can be a CSV of gen models.
 */
public class DdkStandaloneSetup extends StandaloneSetup {

  /**
   * Registers the given files in the gen model.
   *
   * @param fileNames
   *          the comma separated file names
   */
  public void setRegisterGenModelFiles(final String fileNames) {
    GenModelHelper helper = createGenModelHelper();
    for (URI uri : splitCommaSeparatedString(fileNames)) {
      helper.registerGenModel(resourceSet, uri);
    }
  }

  @Override
  protected GenModelHelper createGenModelHelper() {
    return new DdkGenModelHelper();
  }

  /**
   * Convert a comma-separated string of items into a list of URIs, each element corresponding to one item.
   *
   * @param input
   *          The original string, containing either a comma-separated list of items, or a single item (and no comma), or whitespace only.
   * @return A list of URIs, if there are any, or an empty list otherwise. If input is null, return the empty list.
   */
  private List<URI> splitCommaSeparatedString(final String input) {
    if (input == null) {
      return Collections.<URI> emptyList();
    }
    String trimmed = input.trim();
    if (trimmed.length() == 0) {
      return Collections.<URI> emptyList();
    }
    List<URI> result = Lists.newArrayList();
    for (String uri : trimmed.split("\\s*,\\s*")) { //$NON-NLS-1$
      result.add(createURI(uri));
    }
    return result;
  }

  /**
   * Copied from super class.
   *
   * @param path
   *          path to genmodel
   * @return URI
   */
  private URI createURI(final String path) {
    if (path == null) {
      throw new IllegalArgumentException();
    }

    URI uri = URI.createURI(path);
    if (uri.isRelative()) {
      return URI.createFileURI(new File(path).getAbsolutePath());
    }
    return uri;
  }

}
