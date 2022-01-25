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
package com.avaloq.tools.ddk.xtext.linking;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.google.inject.Inject;


/**
 * Default implementation for {@link ICrossReferenceHelper}.
 */
public class DefaultCrossReferenceHelper implements ICrossReferenceHelper {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(DefaultCrossReferenceHelper.class);

  @Inject
  private LazyURIEncoder uriEncoder;

  /**
   * Mark certain references as optional by returning true. By default no references are optional.
   *
   * @param context
   *          The context object containing the reference.
   * @param reference
   *          The EReference.
   * @param node
   *          The parse tree node or subtree for the reference.
   * @return true if this is an optional reference
   */
  @Override
  public boolean isOptionalReference(final EObject context, final EReference reference, final INode node) {
    return false;
  }

  /**
   * Determine whether a particular cross-reference should be exported. By default all references should be exported.
   *
   * @param context
   *          The context object containing the reference.
   * @param reference
   *          The EReference.
   * @param target
   *          The target object of the reference, could be {@code null} for unresolved references.
   * @return {@code true} if the given reference should be exported
   */
  @Override
  public boolean exportReference(final EObject context, final EReference reference, final EObject target) {
    if (target == null) {
      return false;
    } else if (!target.eIsProxy()) {
      if (target.eResource() == null) {
        LOGGER.error("Reference from " + EcoreUtil.getURI(context) + " to " + target + " cannot be exported as target is not contained in a resource."); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        return false;
      }
      return context.eResource() != target.eResource();
    } else {
      URI proxyURI = ((InternalEObject) target).eProxyURI();
      return !getLazyURIEncoder().isCrossLinkFragment(context.eResource(), proxyURI.fragment());
    }
  }

  /** {@inheritDoc} */
  @Override
  public QualifiedName toUnresolvedReferenceName(final String name) {
    int lastSegment = name.lastIndexOf('.');
    String simpleName = lastSegment > 0 && lastSegment < name.length() - 1 ? name.substring(lastSegment + 1) : name;
    return QualifiedNames.toUnresolvedName(simpleName);
  }

  /**
   * Returns the {@link LazyURIEncoder} used for the context language.
   *
   * @return {@link LazyURIEncoder} instance, never {@code null}
   */
  protected LazyURIEncoder getLazyURIEncoder() {
    return uriEncoder;
  }

}
