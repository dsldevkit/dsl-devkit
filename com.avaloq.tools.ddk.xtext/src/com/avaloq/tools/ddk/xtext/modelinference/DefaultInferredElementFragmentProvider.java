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

package com.avaloq.tools.ddk.xtext.modelinference;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.inject.Inject;


/**
 * Default implementation returning a hash of the model element's {@link EClass} and {@link IQualifiedNameProvider#getFullyQualifiedName(EObject) qualified
 * name}.
 */
public class DefaultInferredElementFragmentProvider implements IInferredElementFragmentProvider {

  private static final int HASHER_CAPACITY = 100;

  @Inject
  private IQualifiedNameProvider qualifiedNameProvider;

  private final HashFunction hashFunction = Hashing.murmur3_32_fixed();

  private final Map<EClass, byte[]> eClassToUriBytesMap = Maps.newHashMap();

  @Override
  public String getFragmentSegment(final EObject object, final Set<String> assignedFragments) {
    EClass eClass = getEClass(object);
    if (eClass == null) {
      return null;
    }

    HashCode hash = computeHash(eClass, getQualifiedName(object));
    return getFinalResult(object, hash, assignedFragments);
  }

  /**
   * Return {@link EClass} to use in hash.
   * <p>
   * The default implementation simply returns {@code object.eClass()}.
   *
   * @param object
   *          model element, must not be {@code null}
   * @return EClass or {@code null} if {@link #getFragmentSegment(EObject, Set)} should return {@code null} for this object
   */
  protected EClass getEClass(final EObject object) {
    return object.eClass();
  }

  /**
   * Return qualified name to use in hash.
   * <p>
   * The default implementation returns {@code getQualifiedNameProvider().getFullyQualifiedName(object)}.
   *
   * @param object
   *          model element, must not be {@code null}
   * @return qualified name or {@code null} if none can be derived
   */
  protected QualifiedName getQualifiedName(final EObject object) {
    return getQualifiedNameProvider().getFullyQualifiedName(object);
  }

  protected IQualifiedNameProvider getQualifiedNameProvider() {
    return qualifiedNameProvider;
  }

  /**
   * Computes a hash code for the given {@link #getEClass(EObject) EClass} and {@link #getQualifiedName(EObject) qualified name}.
   *
   * @param eClass
   *          EClass to base hash on, must not be {@code null}
   * @param name
   *          qualified name of inferred model element, can be {@code null}
   * @return hash code, never {@code null}
   */
  protected HashCode computeHash(final EClass eClass, final QualifiedName name) {
    byte[] eClassUriBytes = eClassToUriBytesMap.get(eClass);
    if (eClassUriBytes == null) {
      eClassUriBytes = EcoreUtil.getURI(eClass).toString().getBytes(Charsets.UTF_8);
      eClassToUriBytesMap.put(eClass, eClassUriBytes);
    }

    Hasher hasher = hashFunction.newHasher(HASHER_CAPACITY);
    hasher.putBytes(eClassUriBytes);

    if (name != null) {
      hasher.putChar('/');

      for (int j = 0; j < name.getSegmentCount(); j++) {
        hasher.putUnencodedChars(name.getSegment(j)).putChar('.');
      }
    }

    return hasher.hash();
  }

  /**
   * Returns the final result to be returned by {@link #getFragmentSegment(EObject, Set)}.
   *
   * @param object
   *          inferred model element, must not be {@code null}
   * @param hash
   *          hash code as computed by {@link #computeHash(EObject, EClass)}, must not be {@code null}
   * @param assignedFragments
   *          set of already assigned fragments, must not be {@code null}
   * @return string (preferably not contained in {@code assignedFragments}), never {@code null}
   */
  protected String getFinalResult(final EObject object, final HashCode hash, final Set<String> assignedFragments) {
    return hash.toString();
  }

}
