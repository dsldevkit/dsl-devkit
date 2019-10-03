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

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.AbstractEObjectDescription;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.xtext.resource.extensions.AbstractForwardingResourceDescriptionStrategyMap;
import com.google.common.collect.ImmutableMap;


/**
 * Implementation of {@ink IEObjectDescription} which can be {@link IDetachableDescription#detach() detached} for better performance.
 */
public class DetachableEObjectDescription extends EObjectDescription implements IDetachableDescription<IEObjectDescription> {
  public static final String ALLOW_LOOKUP = "allow_lookup"; //$NON-NLS-1$

  /**
   * Detached view of an {@link DetachableEObjectDescription}.
   */
  public static final class DetachedEObjectDescription extends AbstractEObjectDescription implements IDetachableDescription<IEObjectDescription> {

    private final QualifiedName qualifiedName;
    private final URI eObjectURI;
    private final EClass eClass;
    private final Map<String, String> userData;

    public DetachedEObjectDescription(final QualifiedName qualifiedName, final URI eObjectURI, final EClass eClass, final Map<String, String> userData) {
      super();
      this.qualifiedName = qualifiedName;
      this.eObjectURI = eObjectURI;
      this.eClass = eClass;
      this.userData = userData;
    }

    @Override
    public QualifiedName getName() {
      return qualifiedName;
    }

    @Override
    public QualifiedName getQualifiedName() {
      return qualifiedName;
    }

    /** {@inheritDoc} */
    @Override
    public EObject getEObjectOrProxy() {
      InternalEObject result = (InternalEObject) EcoreUtil.create(eClass);
      result.eSetProxyURI(eObjectURI);
      return result;
    }

    @Override
    public URI getEObjectURI() {
      return eObjectURI;
    }

    @Override
    public EClass getEClass() {
      return eClass;
    }

    @Override
    public String getUserData(final String name) {
      if (userData == null) {
        return null;
      }
      return userData.get(name);
    }

    @Override
    public String[] getUserDataKeys() {
      if (userData == null) {
        return Strings.EMPTY_ARRAY;
      }
      return userData.keySet().toArray(new String[userData.size()]);
    }

    /** {@inheritDoc} */
    @Override
    public IEObjectDescription detach() {
      return this;
    }

  }

  /** We store the user data here in order to be able to copy it when detaching. */
  private final Map<String, String> userData;

  protected DetachableEObjectDescription(final QualifiedName qualifiedName, final EObject element, final Map<String, String> userData) {
    super(qualifiedName, element, userData);
    this.userData = userData;
  }

  /**
   * Factory method to create an object description.
   *
   * @param qualifiedName
   *          name of object
   * @param element
   *          object
   * @param userData
   *          user data for object
   * @return object description
   */
  public static IEObjectDescription create(final QualifiedName qualifiedName, final EObject element, final Map<String, String> userData) {
    return new DetachableEObjectDescription(qualifiedName, element, userData);
  }

  /**
   * Factory method to create an object description.
   *
   * @param qualifiedName
   *          name of object
   * @param element
   *          object
   * @return object description
   */
  public static IEObjectDescription create(final QualifiedName qualifiedName, final EObject element) {
    return new DetachableEObjectDescription(qualifiedName, element, ImmutableMap.<String, String> of());
  }

  /** {@inheritDoc} */
  @Override
  public IEObjectDescription detach() {
    Map<String, String> copiedData;

    if (userData instanceof AbstractForwardingResourceDescriptionStrategyMap) {
      // obtain the delegate, if this is immutable, copyOf will try to avoid the copy.
      copiedData = ImmutableMap.copyOf(((AbstractForwardingResourceDescriptionStrategyMap) userData).delegate());
    } else {
      copiedData = userData == null ? null : ImmutableMap.copyOf(userData);
    }
    return new DetachedEObjectDescription(getQualifiedName(), getEObjectURI(), getEClass(), copiedData);
  }

}
