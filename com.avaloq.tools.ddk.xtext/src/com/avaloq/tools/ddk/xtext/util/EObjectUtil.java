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
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
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
  private static final Predicate<EObject> PROXY_FILTER = input -> input != null && !input.eIsProxy();

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
    return (T) eContainer(obj, e -> type.isInstance(e));
  }

  /**
   * Find a direct or indirect container that satisfies a given predicate. If no such container exists, return null. If the
   * object itself satisfies it, return the object.
   *
   * @param obj
   *          the object
   * @param predicate
   *          the predicate
   * @return The container, as described above.
   */
  public static EObject eContainer(final EObject obj, final Predicate<EObject> predicate) {
    for (EObject e = obj; e != null; e = e.eContainer()) {
      if (predicate.apply(e)) {
        return e;
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
   * Checks if the given object represents an Xtext lazy-linking proxy.
   *
   * @param object
   *          object to check, can be {@code null}
   * @return {@code true} if the given object is non-{@code null}, is a {@link EObject#eIsProxy() proxy} and has a
   *         {@link LazyURIEncoder#isCrossLinkFragment(org.eclipse.emf.ecore.resource.Resource, String) lazy-linking proxy URI fragment}.
   */
  public static boolean isLazyLinkingProxy(final EObject object) {
    return object != null && object.eIsProxy() && ((InternalEObject) object).eProxyURI().fragment().startsWith(LazyURIEncoder.XTEXT_LINK);
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

  /**
   * Return the fie location of a given object in a text resource as the path to the file and the line number.
   * <p>
   * This is very similar to {@link #getLocationString(EObject)} but it will not include the {@link Object#toString()} representation of the object.
   *
   * @param object
   *          any EObject
   * @return a string representation of the location of this object in its file, never {@code null}
   */
  public static String getFileLocation(final EObject object) {
    if (object == null || object.eResource() == null) {
      return ""; //$NON-NLS-1$
    }

    URI uri = object.eResource().getURI();
    // CHECKSTYLE:CHECK-OFF MagicNumber
    String path = uri.isPlatform() ? '/' + String.join("/", uri.segmentsList().subList(3, uri.segmentCount())) : uri.path(); //$NON-NLS-1$
    // CHECKSTYLE:CHECK-ON MagicNumber
    StringBuilder result = new StringBuilder(path);
    final ICompositeNode node = NodeModelUtils.getNode(object);
    if (node != null) {
      result.append(':').append(node.getStartLine());
    }

    return result.toString();
  }

}
