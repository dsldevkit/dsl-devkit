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
package com.avaloq.tools.ddk.xtext.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


/**
 * The class EObjectUtil provides helper methods dealing with EMF EObjects.
 */
public final class EObjectUtil {

  /**
   * Predicate to filter out null and EMF proxy objects. I.e. only non-null and non-proxy objects pass this predicate.
   */
  private static final Predicate<EObject> PROXY_FILTER = new Predicate<EObject>() {
    /** {@inheritDoc} */
    @Override
    public boolean apply(final EObject input) {
      return input != null && !input.eIsProxy();
    }
  };

  /** Inhibit public instantiation. */
  private EObjectUtil() {
    // Do nothing.
  }

  /**
   * Find a direct or indirect container matching a given type of a given object. If no such container exists, return null. If the
   * object itself is of the given type, return the object.
   *
   * @param <T>
   *          the generic type
   * @param obj
   *          the object
   * @param type
   *          the type
   * @return The container, as described above.
   */
  @SuppressWarnings("unchecked")
  public static <T extends EObject> T eContainer(final EObject obj, final Class<T> type) {
    for (EObject e = obj; e != null; e = e.eContainer()) {
      if (type.isInstance(e)) {
        return (T) e;
      }
    }
    return null;
  }

  /**
   * Gets the model filename.
   *
   * @param object
   *          the obj
   * @return the model filename
   */
  public static String getModelFilename(final EObject object) {
    return EmfResourceUtil.getFileNameWithoutExtension(object);
  }

  /**
   * Gets the scope provider given an object part of a {@link org.eclipse.xtext.linking.lazy.LazyLinkingResource
   * LazyLinkingResource}.
   *
   * @param object
   *          the object
   * @return the scope provider by e object
   */
  public static IScopeProvider getScopeProviderByEObject(final EObject object) {
    if (object.eResource() instanceof LazyLinkingResource) {
      return getScopeProviderByResource((LazyLinkingResource) object.eResource());
    } else {
      throw new IllegalArgumentException("Scope provider can only be returned if given a LazyLinkingResource"); //$NON-NLS-1$
    }
  }

  /**
   * Returns a scope provider given a {@link org.eclipse.xtext.linking.lazy.LazyLinkingResource LazyLinkingResource}.
   *
   * @param resource
   *          the resource
   * @return the scope provider by resource
   */
  public static IScopeProvider getScopeProviderByResource(final LazyLinkingResource resource) {
    return ((DefaultLinkingService) resource.getLinkingService()).getScopeProvider();
  }

  /**
   * Returns a filtered iterable which doesn't contain any null elements or EMF proxies.
   *
   * @param <T>
   *          type of elements
   * @param iterable
   *          unfiltered iterable
   * @return filtered iterable
   */
  public static <T extends EObject> Iterable<T> filterProxies(final Iterable<T> iterable) {
    return Iterables.filter(iterable, PROXY_FILTER);
  }

  /**
   * Returns the URI without fragment of the given object.
   *
   * @param object
   *          object to get URI for
   * @return URI base of object
   */
  public static URI getURIBase(final EObject object) {
    if (object == null) {
      return null;
    } else if (object.eIsProxy()) {
      return ((InternalEObject) object).eProxyURI().trimFragment();
    } else if (object.eResource() != null) {
      return object.eResource().getURI();
    }
    return null;
  }

  /**
   * Returns the URI fragment of the given object.
   *
   * @param object
   *          object to get URI fragment for
   * @return URI fragment of object
   */
  public static String getURIFragment(final EObject object) {
    if (object == null) {
      return null;
    } else if (object.eIsProxy()) {
      return ((InternalEObject) object).eProxyURI().fragment();
    } else if (object.eResource() != null) {
      return object.eResource().getURIFragment(object);
    }
    return null;
  }

  /**
   * Returns a location string for an EObject suitable to include in debugging error messages.
   *
   * @param obj
   *          object
   * @return location string including URI of object
   */
  public static String getLocationString(final EObject obj) {
    if (obj == null) {
      return null;
    }
    ICompositeNode node = NodeModelUtils.findActualNodeFor(obj);
    URI uri = EcoreUtil.getURI(obj);
    if (node != null) {
      return uri.trimFragment() + ":" + node.getStartLine() + " (" + obj + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    return uri.toString() + " (" + obj + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }

}
