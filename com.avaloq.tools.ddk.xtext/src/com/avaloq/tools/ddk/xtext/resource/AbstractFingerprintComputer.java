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
package com.avaloq.tools.ddk.xtext.resource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;


/**
 * Resource description strategy with support for partial export and for fingerprinting.
 */
public abstract class AbstractFingerprintComputer implements IFingerprintComputer {

  private static final int BUFFER_CAPACITY = 100;

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

  /**
   * Internal representation of an exported item, containing its fingerprint and its objects (the object itself, plus its
   * children).
   */
  protected static class ExportItem implements Comparable<ExportItem> {
    private final CharSequence key;
    private String keyAsString;

    /**
     * Create a new exported item description.
     *
     * @param key
     *          The fingerprint.
     */
    public ExportItem(final CharSequence key) {
      this.key = (key == null) ? "" : key; //$NON-NLS-1$
    }

    /**
     * Return the fingerprint.
     *
     * @return The fingerprint.
     */
    public CharSequence getKey() {
      return key;
    }

    /**
     * Returns the value of this item as a string.
     *
     * @return value as string
     */
    public String getKeyAsString() {
      if (keyAsString == null) {
        keyAsString = key.toString();
      }
      return keyAsString;
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(final ExportItem other) {
      return getKeyAsString().compareTo(other.getKeyAsString());
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object other) {
      return other == this || (other instanceof ExportItem && getKeyAsString().equals(((ExportItem) other).getKeyAsString()));
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
      return getKeyAsString().hashCode();
    }

  }

  /** Constant designating "nothing to export/fingerprint here". */
  protected static final ExportItem NO_EXPORT = new ExportItem(null);

  /** Constant user data key for the fingerprint. */
  protected static final String FINGERPRINT = " FINGERPRINT"; //$NON-NLS-1$ // NOPMD

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
    final ExportItem export = fingerprint(object);
    if (export == null) {
      return null;
    }
    return encodeFingerprint(export).toString();
  }

  /**
   * Computes a fingerprint for a collection of {@link EObject}.
   * <p>
   * The collection of eobjects to fingerprint is specified by an {@link Iterable}. When determining how to compute the fingerprint the first item of the
   * Iterable supplies the context for all eobjects in the collection. The collection is fingerprinted as {@link #FingerprintOrder}.{@link #UNORDERED} and each
   * eobject in the collection is fingerprinted as {@link #FingerprintIndirection}.{@link #INDIRECT}.
   * </p>
   *
   * @see #fingerprintIndirection(EObject, EObject, EReference)
   * @see #fingerprintIterable(Iterable, EObject, FingerprintOrder, FingerprintIndirection)
   * @param objects
   *          an {@link Iterable} that specifies the collection of {@link EObject}s to be fingerprinted, may be {@code null}
   * @return the fingerprint for the collection of {@code objects} or {@code null} if {@code objects} is {@code null} or empty.
   */
  protected String computeFingerprint(final Iterable<? extends EObject> objects) {
    if (objects == null || Iterables.isEmpty(objects)) {
      return null;
    }
    final ExportItem export = fingerprintIterable(objects, Iterables.get(objects, 0), FingerprintOrder.UNORDERED, FingerprintIndirection.INDIRECT);
    return encodeFingerprint(export).toString();
  }

  /**
   * Tries to compact a given fingerprint string, typically by computing a one-way hash of it.
   *
   * @param export
   *          an {@link ExportItem} containing the uncompacted fingerprint, must not be {@code null}
   * @return the compacted fingerprint, never {@code null}
   */
  protected final CharSequence encodeFingerprint(final ExportItem export) {
    try {
      final MessageDigest md5 = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
      final byte[] digest = md5.digest(export.getKeyAsString().getBytes(Charsets.UTF_8));
      /* Now encode it as a string. */
      final StringBuilder result = new StringBuilder();
      for (byte element : digest) {
        // CHECKSTYLE:OFF (MagicNumber)
        result.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
        // CHECKSTYLE:ON
      }
      return result;
    } catch (final NoSuchAlgorithmException ex) {
      return Integer.toString(export.hashCode());
    }
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
   * @return Its fingerprint.
   */
  private CharSequence fingerprintEObject(final EObject target, final EObject context) {
    if (target == null) {
      return NULL_STRING;
    } else if (target.eIsProxy()) {
      if (context.eResource() instanceof LazyLinkingResource) {
        final URI proxyUri = ((InternalEObject) target).eProxyURI();
        if (!((LazyLinkingResource) context.eResource()).getEncoder().isCrossLinkFragment(context.eResource(), proxyUri.fragment())) {
          return proxyUri.toString();
        }
      }
      return UNRESOLVED_STRING;
    } else {
      return EcoreUtil.getURI(target).toString();
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
   * @return The fingerprint
   */
  private CharSequence fingerprintEReferenceValue(final EObject target, final EObject context) {
    if (target == null) {
      return NULL_STRING;
    } else {
      return fingerprintEObject(target, context);
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
   * @return The fingerprint
   */
  protected CharSequence fingerprintFeature(final EObject obj, final EReference ref) {
    return fingerprintFeature(obj, ref, FingerprintOrder.ORDERED);
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
   * @return The fingerprint
   */
  protected CharSequence fingerprintFeature(final EObject obj, final EReference ref, final FingerprintOrder order) {
    if (obj == null) {
      return NULL_STRING;
    }
    final Iterable<? extends EObject> targets = featureIterable(obj, ref);
    if (targets == null) {
      return NULL_STRING;
    }
    final StringBuilder profile = new StringBuilder(BUFFER_CAPACITY).append('[');
    if (order == FingerprintOrder.ORDERED || !ref.isMany()) {
      for (final EObject target : targets) {
        addProfile(profile, fingerprintEReferenceValue(target, obj));
      }
    } else {
      final List<String> profiles = Lists.newArrayList();
      for (final EObject target : targets) {
        profiles.add(fingerprintEReferenceValue(target, obj).toString());
      }
      Collections.sort(profiles, Ordering.natural());
      for (final String s : profiles) {
        addProfile(profile, s);
      }
    }
    return profile.append(']');
  }

  /**
   * Generate the fingerprint for a possibly many-valued EAttribute, resulting in an order-sensitive
   * fingerprint.
   *
   * @param obj
   *          The object containing the EAttribute
   * @param attr
   *          The EAttribute
   * @return The fingerprint
   */
  protected CharSequence fingerprintFeature(final EObject obj, final EAttribute attr) {
    return fingerprintFeature(obj, attr, FingerprintOrder.ORDERED);
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
   * @return The fingerprint
   */
  protected CharSequence fingerprintFeature(final EObject obj, final EAttribute attr, final FingerprintOrder order) {
    if (obj == null) {
      return NULL_STRING;
    }
    final Iterable<? extends Object> values = featureIterable(obj, attr);
    if (values == null) {
      return NULL_STRING;
    }
    final StringBuilder profile = new StringBuilder(BUFFER_CAPACITY).append('[');
    if (order == FingerprintOrder.ORDERED || !attr.isMany()) {
      for (final Object value : values) {
        addProfile(profile, String.valueOf(value));
      }
    } else {
      final List<String> profiles = Lists.newArrayList();
      for (final Object value : values) {
        profiles.add(String.valueOf(value));
      }
      Collections.sort(profiles, Ordering.natural());
      for (final String s : profiles) {
        addProfile(profile, s);
      }
    }
    return profile.append(']');
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
   * @see #fingerprintEObject(EObject, EObject)
   * @see #fingerprint(EObject)
   * @param target
   *          the referenced {@link EObject}, may be {@code null}
   * @param context
   *          the context {@link EObject}, may be {@code null}
   * @param reference
   *          the {@link EReference} of the {@code context} eObject that references the {@code target} eObject, may be {@code null}
   * @return the {@code target} eObject's fingerprint
   */
  private ExportItem fingerprintIndirection(final EObject target, final EObject context, final EReference reference) {
    if (target == null) {
      return NO_EXPORT;
    }
    final Resource rsc = context != null ? context.eResource() : null;
    if ((reference == null || reference.isContainment()) && (rsc == null || target.eResource() == rsc)) {
      return fingerprint(target);
    } else {
      return new ExportItem(fingerprintEObject(target, context));
    }
  }

  /**
   * Computes the fingerprint for a given {@link EObject}.
   *
   * @param eObject
   *          the eObject to get fingerprint for, must not be {@code null}
   * @return the computed fingerprint for {@code eObject} if it has one, otherwise {@code null}
   */
  protected abstract ExportItem fingerprint(final EObject eObject);

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
   * @return The fingerprint
   */
  protected ExportItem fingerprintRef(final EObject obj, final EReference ref) {
    return fingerprintRef(obj, ref, FingerprintOrder.ORDERED);
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
   * @return The fingerprint
   */
  protected ExportItem fingerprintRef(final EObject obj, final EReference ref, final FingerprintOrder order) {
    if (obj == null) {
      return NO_EXPORT;
    }
    final Iterable<? extends EObject> targets = featureIterable(obj, ref);
    if (targets == null) {
      return NO_EXPORT;
    }
    final StringBuilder profile = new StringBuilder(BUFFER_CAPACITY).append('[');
    if (order == FingerprintOrder.ORDERED || !ref.isMany()) {
      for (final EObject target : targets) {
        final ExportItem item = fingerprintIndirection(target, obj, ref);
        if (item != null) {
          addProfile(profile, item.getKey());
        }
      }
    } else {
      final List<ExportItem> items = Lists.newArrayList();
      for (final EObject target : targets) {
        final ExportItem item = fingerprintIndirection(target, obj, ref);
        if (item != null) {
          items.add(item);
        }
      }
      Collections.sort(items, Ordering.natural());
      for (final ExportItem item : items) {
        addProfile(profile, item.getKey());
      }
    }
    return new ExportItem(profile.append(']'));
  }

  /**
   * Computes a fingerprint for a general {@link Object}.
   * <p>
   * If the object is an {@link Iterable} then the fingerprint is computed by concatenating the fingerprints of its elements. If the object is neither an
   * {@link Iterable} nor an {@link EObject} then its fingerprint is its {@link String} value. If the object is an {@link EObject} then its fingerprint depends
   * on the {@code context} and {@code indirection}.
   * </p>
   *
   * @see #fingerprintIterable(Iterable, EObject, FingerprintOrder, FingerprintIndirection)
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
   * @return the fingerprint
   */
  @SuppressWarnings("unchecked")
  protected ExportItem fingerprintExpr(final Object obj, final EObject context, final FingerprintOrder order, final FingerprintIndirection indirection) {
    if (obj instanceof EObject) {
      if (indirection == FingerprintIndirection.INDIRECT) {
        return fingerprintIndirection((EObject) obj, context, null);
      } else {
        return new ExportItem(fingerprintEObject((EObject) obj, context));
      }
    }
    if (obj instanceof Iterable) {
      return fingerprintIterable((Iterable<? extends Object>) obj, context, order, indirection); // Unchecked
    } else {
      return new ExportItem(String.valueOf(obj));
    }
  }

  /**
   * Computes a fingerprint for an {@link Iterable} of {@link EObject}.
   *
   * @param eObjects
   *          the eObjects to fingerprint, may be {@code null}
   * @param context
   *          the context eObject if the expression is an iterable of references, may be {@code null}
   * @param order
   *          indicates whether the order within the iterable is significant for the fingerprint, must not be {@code null}
   * @param indirection
   *          if the elements of the Iterable are EObjects then indicates whether the fingerprint should be built using
   *          their URIs or by calling generated functions, must not be {@code null}
   * @return the fingerprint, never {@code null}
   */
  protected ExportItem fingerprintIterable(final Iterable<? extends Object> eObjects, final EObject context, final FingerprintOrder order, final FingerprintIndirection indirection) {
    if (eObjects == null) {
      return NO_EXPORT;
    }
    final StringBuilder profile = new StringBuilder(BUFFER_CAPACITY).append('[');
    if (order == FingerprintOrder.ORDERED) {
      for (final Object o : eObjects) {
        final ExportItem item = fingerprintExpr(o, context, order, indirection);
        addProfile(profile, item.getKey());
      }
    } else {
      final List<ExportItem> items = Lists.newArrayList();
      for (final Object o : eObjects) {
        final ExportItem item = fingerprintExpr(o, context, order, indirection);
        items.add(item);
      }
      Collections.sort(items, Ordering.natural());
      for (final ExportItem item : items) {
        addProfile(profile, item.getKey());
      }
    }
    return new ExportItem(profile.append(']'));
  }

  /**
   * Add a new part to a fingerprint.
   *
   * @param profile
   *          The string builder building the fingerprint
   * @param value
   *          The new value
   */
  protected void addProfile(final StringBuilder profile, final CharSequence value) {
    profile.append(value);
    // Always separate fingerprint components by something to avoid collation of individual fingerprints resulting in mistakenly
    // equal fingerprints.
    profile.append(',');
  }

}

