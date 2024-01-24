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
package com.avaloq.tools.ddk.xtext.linking;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.Triple;
import org.eclipse.xtext.util.Tuples;


/**
 * Optimized implementation of the LazyURIEncoder which uses custom string splitting logic.
 */
public class FastLazyURIEncoder extends LazyURIEncoder {

  private static final String SEP = "::"; //$NON-NLS-1$
  private static final int DECODE_START_IDX = 12;

  @Override
  public Triple<EObject, EReference, INode> decode(final Resource res, final String uriFragment) {
    if (isUseIndexFragment(res)) {
      return getLazyProxyInformation(res, uriFragment);
    }
    try {
      int idx = DECODE_START_IDX;
      int idx2 = uriFragment.indexOf(SEP, idx);
      EObject source = resolveShortFragment(res, uriFragment.substring(idx, idx2));

      idx = idx2 + 2;
      idx2 = uriFragment.indexOf(SEP, idx);
      INode node = NodeModelUtils.getNode(source);
      if (node == null) {
        throw new IllegalStateException("Couldn't resolve lazy link, because no node model is attached."); //$NON-NLS-1$
      }

      EReference ref = fromShortExternalForm(source.eClass(), uriFragment.substring(idx, idx2));
      idx = idx2 + 2;
      INode text = getNode(node, uriFragment.substring(idx));
      return Tuples.create(source, ref, text);
      // CHECKSTYLE:OFF Yes, we *do* want to catch RuntimeExceptions here
    } catch (RuntimeException ex) {
      // CHECKSTYLE:ON
      throw new DecodingError(ex);
    }
  }

  /**
   * Unchecked exception type outside the RuntimeException hierarchy to bypass logging in LazyLinkingResource.
   * The exception is thrown when decoding fails catastrophically. It is caught in LazyLinkingResource2.getEObject().
   * See also https://bugs.eclipse.org/bugs/show_bug.cgi?id=378088
   */
  @SuppressWarnings({"serial", "PMD.DoNotExtendJavaLangError"})
  public static class DecodingError extends Error {

    public DecodingError(final RuntimeException cause) {
      super(cause);
    }

    @Override
    public RuntimeException getCause() {
      return (RuntimeException) super.getCause();
    }
  }

}
