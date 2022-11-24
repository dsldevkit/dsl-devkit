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

package com.avaloq.tools.ddk.xtext.modelinference;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.ImplementedBy;


/**
 * Provides the URI fragments to be associated with inferred model elements.
 */
@ImplementedBy(DefaultInferredElementFragmentProvider.class)
public interface IInferredElementFragmentProvider {

  /**
   * Calculates the URI fragment segment of an inferred model element to be added to the {@link InferenceContainer#getContents() contents} of an
   * {@link InferenceContainer}. This fragment segment is expected to be stable with respect to the element's {@link EObject#eClass() type} and
   * {@link org.eclipse.xtext.naming.IQualifiedNameProvider#getFullyQualifiedName(EObject) name}, if available.
   * <p>
   * If the returned fragment is already occupied then {@link InferenceContainer} will itself append a suffix to make it unique.
   *
   * @param object
   *          inferred model element, must not be {@code null}
   * @param assignedFragments
   *          set of already assigned fragments, must not be {@code null}
   * @return string (preferably not contained in {@code assignedFragments}) or {@code null} if a simple positional fragment should be used instead
   */
  String getFragmentSegment(EObject object, Set<String> assignedFragments);

}
