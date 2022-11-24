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

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectOutputStream;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;

import com.google.common.collect.Lists;


/**
 * {@link EObjectOutputStream} subclass which provides {@link #writeEObjectURI(EObject, Resource)} to serialize an EObject's URI using a compact representation.
 */
class DirectLinkingEObjectOutputStream extends EObjectOutputStream {

  private static final Logger LOG = LogManager.getLogger(DirectLinkingEObjectOutputStream.class);

  static final boolean LOCAL_EOBJECT = true;

  private final List<Integer> list = Lists.newArrayList();

  DirectLinkingEObjectOutputStream(final OutputStream outputStream, final Map<?, ?> options) throws IOException {
    super(outputStream, options);
  }

  /**
   * Writes a binary representation of the given object's URI to this output stream. For objects contained by the given resource the object's
   * {@link Resource#getURIFragment(EObject) URI fragment} will be used. For objects in other resources the
   * {@link org.eclipse.emf.ecore.util.EcoreUtil#getURI(EObject) full URI} will be written.
   *
   * @param obj
   *          object to write, must not be {@code null}
   * @param context
   *          resource being serialized, must not be {@code null}
   * @throws IOException
   *           if an I/O exception occurred
   */
  public void writeEObjectURI(final EObject obj, final Resource context) throws IOException {
    Resource resource = obj.eResource();
    if (resource == context) { // NOPMD
      writeBoolean(LOCAL_EOBJECT);
      writeEObjectURIFragmentPath(obj);
    } else {
      String uriString = null;
      if (obj.eIsProxy()) {
        URI proxyURI = ((InternalEObject) obj).eProxyURI();
        uriString = proxyURI.fragment().startsWith(LazyURIEncoder.XTEXT_LINK) ? null : proxyURI.toString();
      } else if (resource != null) {
        uriString = resource.getURI().toString() + '#' + resource.getURIFragment(obj);
      } else {
        LOG.warn("Encountered dangling object while serializing " + context.getURI() + ": " + obj); //$NON-NLS-1$ //$NON-NLS-2$
      }
      writeBoolean(!LOCAL_EOBJECT);
      writeString(uriString);
    }
  }

  /**
   * Computes a short positional URI fragment path. These are more efficient than fragments returned by {@link org.eclipse.xtext.resource.IFragmentProvider}, as
   * the latter may contain name-based segments, which require a lookup to resolve.
   *
   * @param obj
   *          object to get URI fragment for, must not be {@code null}
   * @return URI fragment path, where the segments encode the feature IDs and position in case of multi-valued features, never {@code null}
   */
  @SuppressWarnings("unchecked")
  private void writeEObjectURIFragmentPath(final EObject obj) throws IOException {
    list.clear();
    InternalEObject internalEObject = (InternalEObject) obj;
    for (InternalEObject container = internalEObject.eInternalContainer(); container != null; container = internalEObject.eInternalContainer()) {
      EStructuralFeature feature = internalEObject.eContainingFeature();
      if (feature.isMany()) {
        list.add(((EList<EObject>) container.eGet(feature, false)).indexOf(internalEObject));
      }
      list.add(container.eClass().getFeatureID(feature));
      internalEObject = container;
    }

    list.add(internalEObject.eResource().getContents().indexOf(internalEObject));
    writeCompressedInt(list.size());
    for (int i = list.size() - 1; i >= 0; i--) {
      writeCompressedInt(list.get(i));
    }
  }

}
