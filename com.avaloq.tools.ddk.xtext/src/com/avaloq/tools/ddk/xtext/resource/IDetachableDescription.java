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
package com.avaloq.tools.ddk.xtext.resource;

/**
 * This is a marker interface to be implemented by classes implementing one of {@link org.eclipse.xtext.resource.IResourceDescription},
 * {@link org.eclipse.xtext.resource.IEObjectDescription}, {@link org.eclipse.xtext.resource.IReferenceDescription}. The significance of it being that the
 * respective description can be {@link #detach() detached} at which point it no longer depend on any external objects and can thus be regarded as immutable by
 * clients and can be referenced without pulling in a lot of other hard referenced objects (e.g. non proxy EObject).
 * <p>
 * An exception to this is an {@link org.eclipse.xtext.resource.IResourceDescription} implementing this interface: All its
 * {@link org.eclipse.xtext.resource.IEObjectDescription} and {@link org.eclipse.xtext.resource.IReferenceDescription} objects must both be hard referenced and
 * must also all implement this interface.
 * <p>
 * The primary usage of this interface is that a client doesn't need to create an expensive copy (i.e. clone) the corresponding descriptor if it doesn't intend
 * to modify it.
 *
 * @param <T>
 *          type of description (one of IResourceDescription, IEObjectDescription, or IReferenceDescription)
 * @see com.avaloq.tools.ddk.xtext.builder.IDescriptionCopier
 */
public interface IDetachableDescription<T> {

  /**
   * Returns a detached view (see interface description) of this description or the object itself.
   *
   * @return detached copy if description
   */
  T detach();
}
