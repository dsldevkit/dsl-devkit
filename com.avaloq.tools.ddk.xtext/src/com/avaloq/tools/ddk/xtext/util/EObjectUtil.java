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

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.SimpleCache;
import org.eclipse.xtext.util.Tuples;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


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

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(EObjectUtil.class);

  /** The Constant EOBJECT_ECONTAINER. */
  public static final EStructuralFeature EOBJECT_ECONTAINER = EcoreFactory.eINSTANCE.createEReference();

  /** The Constant TYPE_SELECT_PATTERN. */
  private static final Pattern TYPE_SELECT_PATTERN = Pattern.compile("typeSelect\\((\\w+)::(\\w+)\\)"); //$NON-NLS-1$

  /** Used as fallback when navigating null objects. */
  private static final Function<EObject, Iterable<?>> EMPTY_ITERABLE_FUNCTION = new Function<EObject, Iterable<?>>() {
    @Override
    public Iterable<?> apply(final EObject from) {
      return Collections.emptyList();
    }
  };

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
   * Feature iterator.
   *
   * @param <T>
   *          the generic type
   * @param obj
   *          the obj
   * @param feature
   *          the feature
   * @return the iterable
   */
  @SuppressWarnings("unchecked")
  public static <T> Iterable<T> featureIterator(final EObject obj, final EStructuralFeature feature) {
    if (feature == null) {
      LOGGER.error("iteration through null feature.", new IllegalArgumentException()); //$NON-NLS-1$
      return Collections.EMPTY_LIST;
    }
    if (obj == null) {
      LOGGER.error("iteration through null object.", new IllegalArgumentException()); //$NON-NLS-1$
      return Collections.EMPTY_LIST;
    }
    if (EOBJECT_ECONTAINER.equals(feature)) {
      return Collections.singletonList((T) obj.eContainer());
    }
    if (feature.isMany()) {
      return (Iterable<T>) obj.eGet(feature);
    }
    return Collections.singletonList((T) obj.eGet(feature));
  }

  /**
   * Feature function.
   *
   * @param <T>
   *          the generic type
   * @param feature
   *          the feature
   * @return the function
   */
  public static <T> Function<? extends EObject, Iterable<T>> featureFunction(final EStructuralFeature feature) {
    return new Function<EObject, Iterable<T>>() {
      @Override
      public Iterable<T> apply(final EObject from) {
        return featureIterator(from, feature);
      }
    };
  }

  /**
   * Navigate.
   *
   * @param <T>
   *          the generic type
   * @param obj
   *          the obj
   * @param features
   *          the features
   * @return the iterable
   */
  public static <T> Iterable<T> navigate(final EObject obj, final EStructuralFeature... features) {
    final Iterable<EStructuralFeature> featuresIterable = Lists.newArrayList(features);
    return navigate(obj, featuresIterable);
  }

  /**
   * Navigate.
   *
   * @param <T>
   *          the generic type
   * @param obj
   *          the obj
   * @param navigations
   *          the navigations
   * @return the iterable
   */
  @SuppressWarnings("unchecked")
  public static <T> Iterable<T> navigate(final EObject obj, final Iterable<EStructuralFeature> navigations) {
    final Iterable<Object> first = featureIterator(obj, Iterables.get(navigations, 0));

    final Iterable<EStructuralFeature> remaining = tail(navigations);
    if (Iterables.isEmpty(remaining)) {
      return (Iterable<T>) first;
    }

    return Iterables.concat(Iterables.transform(first, new Function<Object, Iterable<T>>() {
      @Override
      public Iterable<T> apply(final Object from) {
        return navigate((EObject) from, remaining);
      }
    }));
  }

  /**
   * Return an iterable with all but the first element of the given iterable.
   *
   * @param <T>
   *          the generic type
   * @param iter
   *          the iterable
   * @return the iterable
   */
  private static <T> Iterable<T> tail(final Iterable<T> iter) {
    return iter instanceof List<?> ? ((List<T>) iter).subList(1, ((List<T>) iter).size()) : new Iterable<T>() {

      @Override
      public Iterator<T> iterator() {
        final Iterator<T> it = iter.iterator();
        if (it.hasNext()) {
          it.next();
        }
        return new AbstractIterator<T>() {

          @Override
          protected T computeNext() {
            return it.hasNext() ? it.next() : endOfData();
          }
        };
      }
    };
  }

  /**
   * Navigate path.
   *
   * @param <T>
   *          the generic type
   * @param obj
   *          the obj
   * @param features
   *          the features
   * @return the iterable
   */
  public static <T> Iterable<T> navigatePath(final EObject obj, final String... features) {
    final Iterable<String> featuresIterable = Lists.newArrayList(features);
    return navigatePath(obj, featuresIterable);
  }

  /**
   * Navigate path.
   *
   * @param <T>
   *          the generic type
   * @param obj
   *          the obj
   * @param navigations
   *          the navigations
   * @return the iterable
   */
  @SuppressWarnings("unchecked")
  public static <T> Iterable<T> navigatePath(final EObject obj, final Iterable<String> navigations) {
    return (Iterable<T>) navigationFunction(obj, navigations).apply(obj);
  }

  /** The Constant functionCache. */
  private static final SimpleCache<Pair<EClass, Iterable<String>>, Function<EObject, Iterable<?>>> FUNCTION_CACHE = new SimpleCache<Pair<EClass, Iterable<String>>, Function<EObject, Iterable<?>>>(new Function<Pair<EClass, Iterable<String>>, Function<EObject, Iterable<?>>>() {
    @Override
    public Function<EObject, Iterable<?>> apply(final Pair<EClass, Iterable<String>> key) {
      return newNavigationFunction(key);
    }
  });

  /**
   * Navigation function.
   *
   * @param obj
   *          the obj
   * @param navigations
   *          the navigations
   * @return the function< e object, iterable<?>>
   */
  public static Function<EObject, Iterable<?>> navigationFunction(final EObject obj, final Iterable<String> navigations) {
    if (obj == null) {
      LOGGER.error("function iteration through null object.", new IllegalArgumentException()); //$NON-NLS-1$
      return EMPTY_ITERABLE_FUNCTION;
    }
    return navigationFunction(obj.eClass(), navigations);
  }

  /**
   * Navigation function.
   *
   * @param eClass
   *          the e class
   * @param navigations
   *          the navigations
   * @return the function< e object, iterable<?>>
   */
  public static Function<EObject, Iterable<?>> navigationFunction(final EClass eClass, final Iterable<String> navigations) {
    return FUNCTION_CACHE.get(Tuples.create(eClass, navigations));
  }

  /**
   * New navigation function.
   *
   * @param key
   *          the key
   * @return the function< e object, iterable<?>>
   */
  private static Function<EObject, Iterable<?>> newNavigationFunction(final Pair<EClass, Iterable<String>> key) {
    return new Function<EObject, Iterable<?>>() {
      @Override
      public Iterable<?> apply(final EObject from) {
        if (from == null) {
          LOGGER.error(MessageFormat.format("Attempted navigation through null object, remaining navigations: {0}", key), new IllegalArgumentException()); //$NON-NLS-1$
          return Collections.EMPTY_LIST;
        }
        final String firstNavigation = Iterables.get(key.getSecond(), 0);
        Iterable<?> first = null;
        final Matcher typeSelectMatcher = TYPE_SELECT_PATTERN.matcher(firstNavigation);
        if ("eContainer".equals(firstNavigation)) { //$NON-NLS-1$
          first = featureIterator(from, EOBJECT_ECONTAINER);
        } else if ("this".equals(firstNavigation)) { //$NON-NLS-1$
          first = Lists.newArrayList(from);
        } else if (typeSelectMatcher.matches()) {
          final EClassifier type = TYPE_CACHE.get(Tuples.create(typeSelectMatcher.group(1), typeSelectMatcher.group(2)));
          first = type.isInstance(from) ? Lists.newArrayList(from) : Collections.emptyList();
        } else {
          first = featureIterator(from, from.eClass().getEStructuralFeature(firstNavigation));
        }

        final Iterable<String> remaining = tail(key.getSecond());
        if (Iterables.isEmpty(remaining)) {
          return first;
        }

        return Iterables.concat(Iterables.transform(first, new Function<Object, Iterable<?>>() {
          @Override
          public Iterable<?> apply(final Object from) {
            return navigatePath((EObject) from, remaining);
          }
        }));
      }

    };
  }

  /** The Constant typeCache. */
  private static final SimpleCache<Pair<String, String>, EClassifier> TYPE_CACHE = new SimpleCache<Pair<String, String>, EClassifier>(new Function<Pair<String, String>, EClassifier>() {
    @Override
    public EClassifier apply(final Pair<String, String> from) {
      for (final String uri : EPackage.Registry.INSTANCE.keySet()) {
        final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
        if (from.getFirst().equals(ePackage.getName())) {
          return ePackage.getEClassifier(from.getSecond());
        }
      }
      return null;
    }
  });

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
