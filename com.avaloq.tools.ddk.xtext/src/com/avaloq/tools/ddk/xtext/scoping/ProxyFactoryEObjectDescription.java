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
package com.avaloq.tools.ddk.xtext.scoping;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;


/**
 * Wrapper IEObjectDescription that tries to set fields in a proxy from the user data, based on matching structural feature names
 * and user data keys, so that our default name functions can also work with proxies.
 */
public class ProxyFactoryEObjectDescription implements IEObjectDescription {
  private final IEObjectDescription delegate;
  private EObject cachedProxy;

  /**
   * Instantiates a new proxy factory e object description.
   *
   * @param delegate
   *          the delegate
   */
  public ProxyFactoryEObjectDescription(final IEObjectDescription delegate) {
    this.delegate = delegate;
  }

  /** {@inheritDoc} */
  @Override
  public String[] getUserDataKeys() {
    return delegate.getUserDataKeys();
  }

  /** {@inheritDoc} */
  @Override
  public String getUserData(final String name) {
    return delegate.getUserData(name);
  }

  /** {@inheritDoc} */
  @Override
  public QualifiedName getQualifiedName() {
    return delegate.getQualifiedName();
  }

  /** {@inheritDoc} */
  @Override
  public QualifiedName getName() {
    return delegate.getName();
  }

  /** {@inheritDoc} */
  @Override
  public URI getEObjectURI() {
    return delegate.getEObjectURI();
  }

  /** {@inheritDoc} */
  @Override
  public EObject getEObjectOrProxy() {
    EObject result = cachedProxy;
    if (result == null) {
      result = delegate.getEObjectOrProxy();
      if (result.eIsProxy()) {
        // Safety measure: ContainerQuery generates ProxyFactoryEObjectDescriptions only for proxies, but the class is
        // public, and we can't know what the delegate may return. So make sure we only do this for real proxies.
        assignUserData(result);
      }
      cachedProxy = result;
    }
    return result;
  }

  /**
   * copies the index user data into the respective EObject's attributes.
   *
   * @param obj
   *          object to assign user data to
   */
  private void assignUserData(final EObject obj) {
    final EClass clazz = getEClass();
    for (final String key : getUserDataKeys()) {
      final EStructuralFeature feature = clazz.getEStructuralFeature(key);
      if (feature != null && feature.isChangeable()) {
        final String userData = getUserData(key);
        obj.eSet(feature, EcoreUtil.createFromString((EDataType) feature.getEType(), userData));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public EClass getEClass() {
    return delegate.getEClass();
  }

  @Override
  public String toString() {
    return delegate.toString();
  }

}
