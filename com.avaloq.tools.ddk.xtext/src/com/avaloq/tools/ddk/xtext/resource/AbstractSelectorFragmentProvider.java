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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProvider;
import com.avaloq.tools.ddk.xtext.linking.ShortFragmentProvider;
import com.google.common.collect.Maps;
import com.google.inject.Inject;


/**
 * Fragment provider which understands fragments with selector path segments. E.g. <code>0/1/3(0=='foo')/0</code>.
 * <p>
 * Implementation classes should override {@link #getFragment(EObject, org.eclipse.xtext.resource.IFragmentProvider.Fallback)} and call
 * {@link #computeSelectorFragment(EObject, EStructuralFeature, boolean, org.eclipse.xtext.resource.IFragmentProvider.Fallback)} as appropriate.
 */
public abstract class AbstractSelectorFragmentProvider extends AbstractFragmentProvider {

  public static final char SELECTOR_START = '(';
  public static final char SELECTOR_END = ')';
  public static final char EQ_OP = '=';
  public static final char UNIQUE = '!';
  public static final char VALUE_SEP = '\'';
  private static final int EQ_OP_LENGTH = 1;
  private static final String NULL_VALUE = "null"; //$NON-NLS-1$

  @Inject
  private ShortFragmentProvider shortFragmentProvider;

  private final Map<Object, Object> eClassToCaseSensitive = Maps.newHashMap();

  /**
   * Computes a segment of the fragment with a selector for the given object and appends it to the given {@link StringBuilder}.
   *
   * @param obj
   *          object to compute fragment for
   * @param selectorFeature
   *          selector feature
   * @param unique
   *          {@code true} the values for selectorFeature are unique within the object's containment feature setting
   * @param builder
   *          builder to append fragment segment to, must not be {@code null}
   * @return {@code true} if a fragment segment was appended to {@code builder}
   */
  @SuppressWarnings("unchecked")
  protected boolean computeSelectorFragmentSegment(final EObject obj, final EStructuralFeature selectorFeature, final boolean unique, final StringBuilder builder) {
    final EObject container = obj.eContainer();
    if (container == null) {
      return shortFragmentProvider.appendFragmentSegment(obj, builder);
    }
    // containment feature
    final EStructuralFeature containmentFeature = obj.eContainmentFeature();
    builder.append(container.eClass().getFeatureID(containmentFeature));
    // selector
    final Object selectorValue = obj.eGet(selectorFeature);
    builder.append(SELECTOR_START).append(obj.eClass().getFeatureID(selectorFeature)).append(EQ_OP);
    if (selectorValue != null) {
      builder.append(VALUE_SEP);
      appendEscaped(selectorValue.toString(), builder);
      builder.append(VALUE_SEP);
    } else {
      builder.append(NULL_VALUE);
    }
    if (unique) {
      builder.append(UNIQUE);
    }
    builder.append(SELECTOR_END);

    // selector index
    if (!unique && containmentFeature.isMany()) {
      builder.append(ShortFragmentProvider.LIST_SEPARATOR);
      final EList<? extends EObject> containmentList = (EList<? extends EObject>) container.eGet(containmentFeature);
      int selectorIndex = 0;
      final int objectIndex = containmentList.indexOf(obj);
      for (int i = 0; i < objectIndex; i++) {
        final Object value = containmentList.get(i).eGet(selectorFeature);
        if ((selectorValue == null && value == null) || (selectorValue != null && selectorValue.equals(value))) {
          selectorIndex++;
        }
      }
      builder.append(selectorIndex);
    }
    return true;
  }

  /**
   * Allows override for delegation to extensions.
   *
   * @param object
   *          object to compute fragment for
   * @param builder
   *          builder to append fragment segment to, must not be {@code null}
   * @return {@code true} if a fragment segment was appended to {@code builder}
   */
  protected boolean appendFragmentSegmentFallback(final EObject object, final StringBuilder builder) {
    return shortFragmentProvider.appendFragmentSegment(object, builder);
  }

  /**
   * {@inheritDoc}
   * <p>
   * By default, this method delegates to {@link #appendFragmentSegmentFallback(EObject, StringBuilder)}. Sub classes have to override this method in order to
   * customize which EObject generates a selector segment.
   * </p>
   */
  // TODO DSL-348: change generator for fragment providers to implement getFragmentSegment instead of getFragment
  @Override
  public boolean appendFragmentSegment(final EObject object, final StringBuilder builder) {
    return appendFragmentSegmentFallback(object, builder);
  }

  private Boolean isCaseSensitive(final EClass eClass) {
    return (Boolean) eClassToCaseSensitive.computeIfAbsent(eClass, k -> eClass.getEAllSuperTypes().stream().map(EClass::getName).anyMatch(n -> "ICaseSensitiveNamedElement".equals(n))); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings({"unchecked", "PMD.NPathComplexity"})
  public EObject getEObjectFromSegment(final EObject container, final String segment) {
    final int selectorEndOffset = segment.lastIndexOf(SELECTOR_END);
    if (selectorEndOffset != -1) {
      final int selectorOffset = segment.indexOf(SELECTOR_START);
      final int containmentFeatureId = Integer.parseUnsignedInt(segment.substring(0, selectorOffset));
      final EStructuralFeature containmentFeature = container.eClass().getEStructuralFeature(containmentFeatureId);
      if (containmentFeature == null) {
        return null;
      }
      if (!containmentFeature.isMany()) {
        return (EObject) container.eGet(containmentFeature);
      }
      final int eqOffset = segment.indexOf(EQ_OP, selectorOffset);
      final int selectorFeatureId = Integer.parseUnsignedInt(segment.substring(selectorOffset + 1, eqOffset));
      boolean uniqueMatch = segment.charAt(selectorEndOffset - 1) == UNIQUE;
      int matchedIndex = uniqueMatch ? 0 : Integer.parseUnsignedInt(segment.substring(selectorEndOffset + 2));
      boolean isNull = segment.startsWith(NULL_VALUE, eqOffset + EQ_OP_LENGTH);
      String matchedValue = isNull ? null
          : unescape(segment.substring(eqOffset + EQ_OP_LENGTH + 1, uniqueMatch ? selectorEndOffset - 2 : selectorEndOffset - 1));
      int index = 0;
      EList<? extends EObject> containmentList = (EList<? extends EObject>) container.eGet(containmentFeature);
      int containmentListSize = containmentList.size();
      for (int i = 0; i < containmentListSize; i++) {
        EObject object = containmentList.get(i);
        Object value = object.eGet(object.eClass().getEStructuralFeature(selectorFeatureId));
        if (value == null) {
          if (matchedValue == null && index++ == matchedIndex) {
            return object;
          }
        } else {
          if ((isCaseSensitive(object.eClass()) ? value.toString().equals(matchedValue) : value.toString().equalsIgnoreCase(matchedValue))
              && index++ == matchedIndex) {
            return object;
          }
        }
      }
      return null;
    } else {
      return shortFragmentProvider.getEObjectFromSegment(container, segment);
    }
  }
}
