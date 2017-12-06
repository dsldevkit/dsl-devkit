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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;


/**
 * Fingerprint computer which directly streams all export items into a hash function. This saves memory because the export items don't first need to be buffered
 * in a string or byte array. Also there are less objects which need to be allocated.
 */
public abstract class AbstractStreamingFingerprintComputer implements IFingerprintComputer {

  /**
   * This enumeration is used to specify whether or not the order of element in a list is important
   * for the value of the fingerprint.
   */
  protected enum FingerprintOrder {
    /**
     * Indicates that order in the list is significant.
     */
    ORDERED,

    /**
     * Indicates that order in the list is insignificant.
     */
    UNORDERED
  }

  /**
   * This enumeration is used to specify whether objects in a list shall be treated as indirections
   * or not. If INDIRECT is specified and a list element is an EObject, a generated fingerprint
   * operation is called to determine the object's fingerprint, otherwise the URI of the object
   * is taken as part of the fingerprint.
   * For list elements that are not EObjects, the value included in the fingerprint is the the
   * object's contents as returned by toString() on the object.
   */
  protected enum FingerprintIndirection {
    /**
     * Indicates that the expression is to be fingerprinted as a value.
     */
    DIRECT,

    /**
     * Indicates that the expression is to be fingerprinted as a reference.
     */
    INDIRECT
  }

  protected static final HashFunction HASH_FUNCTION = Hashing.murmur3_128();

  /** Constant designating "nothing to export/fingerprint here". */
  protected static final HashCode NO_EXPORT = HASH_FUNCTION.hashBytes(new byte[0]);

  /** Separator used between items written to hasher. */
  protected static final char ITEM_SEP = ',';

  /** Marker for null references in the fingerprint. */
  private static final String NULL_STRING = "null"; //$NON-NLS-1$

  /** Marker for unresolved references in the fingerprint. */
  private static final String UNRESOLVED_STRING = "unresolved"; //$NON-NLS-1$

  /** {@inheritDoc} */
  @Override
  public String computeFingerprint(final Resource resource) {
    return computeFingerprint(resource.getContents());
  }

  /** {@inheritDoc} */
  @Override
  public String computeFingerprint(final EObject object) {
    if (object == null) {
      return null;
    }
    Hasher hasher = HASH_FUNCTION.newHasher();
    fingerprint(object, hasher);
    HashCode export = hasher.hash();
    if (export.equals(NO_EXPORT)) {
      return null;
    }
    return export.toString();
  }

  /**
   * Computes a fingerprint for a collection of {@link EObject}.
   * <p>
   * The collection of eobjects to fingerprint is specified by an {@link Iterable}. When determining how to compute the fingerprint the first item of the
   * Iterable supplies the context for all eobjects in the collection. The collection is fingerprinted as {@link #FingerprintOrder}.{@link #UNORDERED} and each
   * eobject in the collection is fingerprinted as {@link #FingerprintIndirection}.{@link #INDIRECT}.
   * </p>
   *
   * @see #fingerprintIndirection(EObject, EObject, EReference, Hasher)
   * @see #fingerprintIterable(Iterable, EObject, FingerprintOrder, FingerprintIndirection, Hasher)
   * @param objects
   *          an {@link Iterable} that specifies the collection of {@link EObject}s to be fingerprinted, may be {@code null}
   * @return the fingerprint for the collection of {@code objects} or {@code null} if {@code objects} is {@code null} or empty.
   */
  protected String computeFingerprint(final Iterable<? extends EObject> objects) {
    if (objects == null || Iterables.isEmpty(objects)) {
      return null;
    }
    Hasher hasher = HASH_FUNCTION.newHasher();
    fingerprintIterable(objects, Iterables.get(objects, 0), FingerprintOrder.UNORDERED, FingerprintIndirection.INDIRECT, hasher);
    return hasher.hash().toString();
  }

  /**
   * Return an Iterable containing all the contents of the given feature of the given object. If the
   * feature is not many-valued, the resulting iterable will have one element. If the feature is an EReference,
   * the iterable may contain proxies. The iterable may contain null values.
   *
   * @param <T>
   *          The Generic type of the objects in the iterable
   * @param obj
   *          The object
   * @param feature
   *          The feature
   * @return An iterable over all the contents of the feature of the object.
   */
  @SuppressWarnings("unchecked")
  private <T> Iterable<T> featureIterable(final EObject obj, final EStructuralFeature feature) {
    if (feature == null) {
      return Collections.emptyList();
    }
    if (feature.isMany()) {
      if (feature instanceof EAttribute || ((EReference) feature).isContainment()) {
        return (Iterable<T>) obj.eGet(feature);
      }
      return new Iterable<T>() {
        @Override
        public Iterator<T> iterator() {
          return ((InternalEList<T>) obj.eGet(feature)).basicIterator(); // Don't resolve
        }
      };
    }
    return Collections.singletonList((T) obj.eGet(feature, false)); // Don't resolve
  }

  /**
   * Generate a fingerprint for the target object using its URI.
   *
   * @param target
   *          The target object
   * @param context
   *          The object containing the reference
   * @param hasher
   *          hasher to stream to
   */
  private void fingerprintEObject(final EObject target, final EObject context, final Hasher hasher) {
    if (target == null) {
      hasher.putUnencodedChars(NULL_STRING);
    } else if (target.eIsProxy()) {
      if (context.eResource() instanceof LazyLinkingResource) {
        final URI proxyUri = ((InternalEObject) target).eProxyURI();
        if (!((LazyLinkingResource) context.eResource()).getEncoder().isCrossLinkFragment(context.eResource(), proxyUri.fragment())) {
          hasher.putUnencodedChars(proxyUri.toString());
          return;
        }
      }
      hasher.putUnencodedChars(UNRESOLVED_STRING);
    } else {
      hasher.putUnencodedChars(EcoreUtil.getURI(target).toString());
    }
  }

  /**
   * Generate a fingerprint of an EReference.
   *
   * @param target
   *          The target object the EReference is linked to. May be a proxy or null. If null, the
   *          fingerprint tries to include the parse tree node value of the reference.
   * @param context
   *          The object containing the EReference
   * @param hasher
   *          hasher to stream to
   */
  private void fingerprintEReferenceValue(final EObject target, final EObject context, final Hasher hasher) {
    if (target == null) {
      hasher.putUnencodedChars(NULL_STRING);
    } else {
      fingerprintEObject(target, context, hasher);
    }
  }

  /**
   * Generate the fingerprint for a possibly many-valued EReference resulting in an order-sensitive
   * fingerprint. References are fingerprinted using the URI of the referenced object, or the proxy
   * URI. To "follow a reference", use fingerprintRef().
   *
   * @param obj
   *          The object containing the EReference
   * @param ref
   *          The EReference
   * @param hasher
   *          hasher to stream to
   */
  protected void fingerprintFeature(final EObject obj, final EReference ref, final Hasher hasher) {
    fingerprintFeature(obj, ref, FingerprintOrder.ORDERED, hasher);
  }

  /**
   * Generate a fingerprint for a possibly many-valued EReference.
   *
   * @param obj
   *          The object containing the EReference
   * @param ref
   *          The EReference
   * @param order
   *          Indicates whether the order of elements is important, if the reference is many-valued
   * @param hasher
   *          hasher to stream to
   */
  protected void fingerprintFeature(final EObject obj, final EReference ref, final FingerprintOrder order, final Hasher hasher) {
    if (obj == null) {
      hasher.putUnencodedChars(NULL_STRING);
      return;
    }
    final Iterable<? extends EObject> targets = featureIterable(obj, ref);
    if (targets == null) {
      hasher.putUnencodedChars(NULL_STRING);
      return;
    }
    hasher.putChar('[');
    if (order == FingerprintOrder.ORDERED || !ref.isMany()) {
      for (final EObject target : targets) {
        fingerprintEReferenceValue(target, obj, hasher);
      }
    } else {
      final List<String> profiles = Lists.newArrayList();
      for (final EObject target : targets) {
        Hasher itemHasher = HASH_FUNCTION.newHasher();
        fingerprintEReferenceValue(target, obj, itemHasher);
        profiles.add(itemHasher.hash().toString());
      }
      Collections.sort(profiles, Ordering.natural());
      for (final String s : profiles) {
        hasher.putUnencodedChars(s).putChar(ITEM_SEP);
      }
    }
    hasher.putChar(']');
  }

  /**
   * Generate the fingerprint for a possibly many-valued EAttribute, resulting in an order-sensitive
   * fingerprint.
   *
   * @param obj
   *          The object containing the EAttribute
   * @param attr
   *          The EAttribute
   * @param hasher
   *          hasher to stream to
   */
  protected void fingerprintFeature(final EObject obj, final EAttribute attr, final Hasher hasher) {
    fingerprintFeature(obj, attr, FingerprintOrder.ORDERED, hasher);
  }

  /**
   * Generate the fingerprint for a possibly many-valued EAttribute.
   *
   * @param obj
   *          The object containing the EAttribute
   * @param attr
   *          The EAttribute
   * @param order
   *          Indicates whether the order of elements is important, if the attribute is many-valued
   * @param hasher
   *          hasher to stream to
   */
  protected void fingerprintFeature(final EObject obj, final EAttribute attr, final FingerprintOrder order, final Hasher hasher) {
    if (obj == null) {
      hasher.putUnencodedChars(NULL_STRING);
      return;
    }
    final Iterable<? extends Object> values = featureIterable(obj, attr);
    if (values == null) {
      hasher.putUnencodedChars(NULL_STRING);
      return;
    }
    hasher.putChar('[');
    if (order == FingerprintOrder.ORDERED || !attr.isMany()) {
      for (final Object value : values) {
        hasher.putUnencodedChars(String.valueOf(value)).putChar(ITEM_SEP);
      }
    } else {
      final List<String> profiles = Lists.newArrayList();
      for (final Object value : values) {
        profiles.add(String.valueOf(value));
      }
      Collections.sort(profiles, Ordering.natural());
      for (final String s : profiles) {
        hasher.putUnencodedChars(s).putChar(ITEM_SEP);
      }
    }
    hasher.putChar(']');
  }

  /**
   * Computes a fingerprint for a referenced {@link EObject} in a given context.
   * <p>
   * If the reference is a containment reference and the referenced eObject is in the same resource as the context eObject, then it calls {@link #fingerprint}
   * to dispatch the finger-print computation for the referenced eObject. This will usually dispatch to a generated method in a subclass. Otherwise it computes
   * the finger-print based on the referenced eObject's URI.
   * <p>
   * <em>Note</em> - if {@code reference} is {@code null} and either {@code context} or {@code context.eResource()} is {@code null}, then the referenced
   * {@code target} eObject is treated as if the reference were a containment reference and the referenced eObject were in the same resource as the context
   * eObject. In other words, in this case it calls {@link #fingerprint} to dispatch the computation.
   * </p>
   *
   * @see #fingerprintEObject(EObject, EObject, Hasher)
   * @see #fingerprint(EObject, Hasher)
   * @param target
   *          the referenced {@link EObject}, may be {@code null}
   * @param context
   *          the context {@link EObject}, may be {@code null}
   * @param reference
   *          the {@link EReference} of the {@code context} eObject that references the {@code target} eObject, may be {@code null}
   * @param hasher
   *          hasher to stream to
   */
  private void fingerprintIndirection(final EObject target, final EObject context, final EReference reference, final Hasher hasher) {
    if (target == null) {
      return;
    }
    final Resource rsc = context != null ? context.eResource() : null;
    if ((reference == null || reference.isContainment()) && (rsc == null || target.eResource() == rsc)) {
      fingerprint(target, hasher);
    } else {
      fingerprintEObject(target, context, hasher);
    }
  }

  /**
   * Computes the fingerprint for a given {@link EObject}.
   *
   * @param eObject
   *          the eObject to get fingerprint for, must not be {@code null}
   * @param hasher
   *          hasher to stream to
   */
  protected abstract void fingerprint(final EObject eObject, Hasher hasher);

  /**
   * Generate a fingerprint for a possibly many-valued EReference using generated operations.
   * If a reference points to another resource, the fingerprint of the referenced object will
   * be used, if available, otherwise the foreign object is fingerprinted as if fingerprintFeature
   * had been used. If the EReference is many-valued, the resulting fingerprint is order-sensitive.
   *
   * @param obj
   *          The object containing the EReference
   * @param ref
   *          The EReference
   * @param hasher
   *          hasher to stream to
   */
  protected void fingerprintRef(final EObject obj, final EReference ref, final Hasher hasher) {
    fingerprintRef(obj, ref, FingerprintOrder.ORDERED, hasher);
  }

  /**
   * Generate a fingerprint for a possibly many-valued EReference using generated operations.
   * If a reference points to another resource, the foreign object is fingerprinted as if fingerprintFeature
   * had been used. If the EReference is many-valued, the resulting fingerprint is order-sensitive.
   *
   * @param obj
   *          The object containing the EReference
   * @param ref
   *          The EReference
   * @param order
   *          Indicates whether the order of elements is important, if the reference is many-valued
   * @param hasher
   *          hasher to stream to
   */
  protected void fingerprintRef(final EObject obj, final EReference ref, final FingerprintOrder order, final Hasher hasher) {
    if (obj == null) {
      return;
    }
    final Iterable<? extends EObject> targets = featureIterable(obj, ref);
    if (targets == null) {
      return;
    }
    hasher.putChar('[');
    if (order == FingerprintOrder.ORDERED || !ref.isMany()) {
      for (final EObject target : targets) {
        fingerprintIndirection(target, obj, ref, hasher);
        hasher.putChar(ITEM_SEP);
      }
    } else {
      final List<String> items = Lists.newArrayList();
      for (final EObject target : targets) {
        Hasher itemHasher = HASH_FUNCTION.newHasher();
        fingerprintIndirection(target, obj, ref, itemHasher);
        items.add(itemHasher.hash().toString());
      }
      Collections.sort(items, Ordering.natural());
      for (final String item : items) {
        hasher.putUnencodedChars(item).putChar(ITEM_SEP);
      }
    }
    hasher.putChar(']');
  }

  /**
   * Computes a fingerprint for a general {@link Object}.
   * <p>
   * If the object is an {@link Iterable} then the fingerprint is computed by concatenating the fingerprints of its elements. If the object is neither an
   * {@link Iterable} nor an {@link EObject} then its fingerprint is its {@link String} value. If the object is an {@link EObject} then its fingerprint depends
   * on the {@code context} and {@code indirection}.
   * </p>
   *
   * @see #fingerprintIterable(Iterable, EObject, FingerprintOrder, FingerprintIndirection, Hasher)
   * @param obj
   *          the object to fingerprint, may be {@code null}
   * @param context
   *          the context eObject if the expression is a reference, may be {@code null}
   * @param order
   *          if the object is an {@link Iterable} then indicates whether the order within the iterable is significant for the fingerprint, must not be
   *          {@code null}
   * @param indirection
   *          if the object is an EObject or an Iterable of EObjects then indicates whether the fingerprint should be built using
   *          the URIs or by calling a generated function, must not be {@code null}
   * @param hasher
   *          hasher to stream to
   */
  @SuppressWarnings("unchecked")
  protected void fingerprintExpr(final Object obj, final EObject context, final FingerprintOrder order, final FingerprintIndirection indirection, final Hasher hasher) {
    if (obj instanceof EObject) {
      if (indirection == FingerprintIndirection.INDIRECT) {
        fingerprintIndirection((EObject) obj, context, null, hasher);
      } else {
        fingerprintEObject((EObject) obj, context, hasher);
      }
    } else if (obj instanceof Iterable) {
      fingerprintIterable((Iterable<? extends Object>) obj, context, order, indirection, hasher); // Unchecked
    } else {
      hasher.putUnencodedChars(String.valueOf(obj));
    }
  }

  /**
   * Computes a fingerprint for an {@link Iterable} of {@link EObject}.
   *
   * @param objects
   *          the objects to fingerprint, may be {@code null}
   * @param context
   *          the context eObject if the expression is an iterable of references, may be {@code null}
   * @param order
   *          indicates whether the order within the iterable is significant for the fingerprint, must not be {@code null}
   * @param indirection
   *          if the elements of the Iterable are EObjects then indicates whether the fingerprint should be built using
   *          their URIs or by calling generated functions, must not be {@code null}
   * @param hasher
   *          hasher to stream to
   */
  protected void fingerprintIterable(final Iterable<? extends Object> objects, final EObject context, final FingerprintOrder order, final FingerprintIndirection indirection, final Hasher hasher) {
    if (objects == null) {
      return;
    }
    hasher.putChar('[');
    if (order == FingerprintOrder.ORDERED) {
      for (final Object o : objects) {
        fingerprintExpr(o, context, order, indirection, hasher);
        hasher.putChar(ITEM_SEP);
      }
    } else {
      final List<String> items = Lists.newArrayList();
      for (final Object o : objects) {
        Hasher itemHasher = HASH_FUNCTION.newHasher();
        fingerprintExpr(o, context, order, indirection, itemHasher);
        items.add(itemHasher.hash().toString());
      }
      Collections.sort(items, Ordering.natural());
      for (final String item : items) {
        hasher.putUnencodedChars(item).putChar(ITEM_SEP);
      }
    }
    hasher.putChar(']');
  }

}
