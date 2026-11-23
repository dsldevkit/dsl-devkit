/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.XtextResource;

import com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProvider;
import com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProvider.FragmentPath;


/**
 * Computes the URIs of the objects of one resource visited by a traversal of its contents in containment pre-order, without computing the fragment segments of
 * their common ancestors again. The URIs are the same as those returned by {@link EcoreUtil#getURI(EObject)}.
 * <p>
 * Instances are not thread-safe and must only be used during one traversal, while the contents of the resource do not change.
 * </p>
 */
public final class TraversalUris {

  private final Resource resource;
  private final FragmentPath path;

  /**
   * Creates a new instance for the given resource.
   *
   * @param resource
   *          the traversed resource, must not be {@code null}
   */
  public TraversalUris(final Resource resource) {
    this.resource = resource;
    if (resource instanceof XtextResource xtextResource && xtextResource.getFragmentProvider() instanceof AbstractFragmentProvider provider
        && usesFragmentProvider(resource)) {
      this.path = provider.createFragmentPath();
    } else {
      this.path = null;
    }
  }

  /**
   * Returns whether the fragments of the given resource are those of its fragment provider, i.e. {@link XtextResource#getURIFragment(EObject)} is not
   * overridden.
   *
   * @param resource
   *          the resource, must not be {@code null}
   * @return {@code true} if the resource uses {@link XtextResource#getURIFragment(EObject)}
   */
  private static boolean usesFragmentProvider(final Resource resource) {
    try {
      return resource.getClass().getMethod("getURIFragment", EObject.class).getDeclaringClass() == XtextResource.class; //$NON-NLS-1$
    } catch (NoSuchMethodException e) {
      return false;
    }
  }

  /**
   * Returns the URI of the given object.
   *
   * @param object
   *          the object, must not be {@code null}
   * @return the same URI as {@link EcoreUtil#getURI(EObject)}, never {@code null}
   */
  public URI getURI(final EObject object) {
    if (path != null && !object.eIsProxy() && object.eResource() == resource) {
      final String fragment = path.getFragment(object);
      if (fragment != null) {
        return resource.getURI().appendFragment(fragment);
      }
    }
    return EcoreUtil.getURI(object);
  }
}
