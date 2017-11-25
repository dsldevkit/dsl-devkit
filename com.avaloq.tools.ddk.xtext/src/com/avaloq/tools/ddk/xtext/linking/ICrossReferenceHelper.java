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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;

import com.google.inject.ImplementedBy;


/**
 * Helper object used to get additional information about cross references.
 */
@ImplementedBy(DefaultCrossReferenceHelper.class)
public interface ICrossReferenceHelper {

  /**
   * Determines whether the given cross reference instance (context object together with {@link EReference reference}) is
   * <i>optional</i>. An optional reference which cannot be resolved will not result in a corresponding error diagnostic or
   * imported name in the builder state.
   *
   * @param context
   *          context object for reference instance
   * @param reference
   *          reference from meta model
   * @param node
   *          node model corresponding to reference instance which may be <code>null</code>
   * @return <code>true</code> if the given reference instance is optional
   */
  boolean isOptionalReference(final EObject context, final EReference reference, final INode node);

  /**
   * Determines whether the given cross reference instance (context object together with {@link EReference reference}) should be
   * exported to the builder state.
   *
   * @param context
   *          context object for reference instance
   * @param reference
   *          reference from meta model
   * @param target
   *          target object of cross-reference, could be {@code null} or a proxy
   * @return <code>true</code> if the given reference instance should be exported
   */
  boolean exportReference(final EObject context, final EReference reference, EObject target);

  /**
   * Creates an unresolved name for the given qualified name (last segment taken only).
   * <p>
   * Assumes a canonical String representation of qualified names using '.' as namespace delimiter.
   * Languages that do not use a canonical namespace delimiter, must override this method.
   *
   * @param name
   *          potentially qualified name for an unresolved reference
   * @return an unresolved qualified name
   */
  QualifiedName toUnresolvedReferenceName(final String name);
}
