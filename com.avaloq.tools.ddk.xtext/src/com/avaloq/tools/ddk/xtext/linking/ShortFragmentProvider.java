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
package com.avaloq.tools.ddk.xtext.linking;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.google.common.base.CharMatcher;


/**
 * Fragment provider implementation using the same kind of compressed URI fragments as the <code>LazyURIEncoder</code>.
 */
public class ShortFragmentProvider extends AbstractFragmentProvider {

  @Override
  public boolean appendFragmentSegment(final EObject object, final StringBuilder builder) {
    final EReference containmentFeature = object.eContainmentFeature();
    if (containmentFeature == null) {
      builder.append(object.eResource().getContents().indexOf(object));
    } else {
      final EObject container = object.eContainer();
      builder.append(container.eClass().getFeatureID(containmentFeature));
      if (containmentFeature.isMany()) {
        final List<?> list = (List<?>) container.eGet(containmentFeature, false);
        builder.append(LIST_SEPARATOR).append(list.indexOf(object));
      }
    }
    return true;
  }

  @Override
  public EObject getEObjectFromSegment(final EObject container, final String segment) {
    final int listSeparatorIndex = segment.indexOf(LIST_SEPARATOR);
    int featureId;
    if (listSeparatorIndex == -1) {
      featureId = Integer.parseUnsignedInt(segment);
    } else {
      featureId = Integer.parseUnsignedInt(segment.substring(0, listSeparatorIndex));
    }
    final EReference reference = (EReference) container.eClass().getEStructuralFeature(featureId);
    if (reference == null) {
      return null;
    }
    if (reference.isMany()) {
      if (listSeparatorIndex == -1) {
        // if the model expects a list but the uri does not provide a listIndex, or the model changed and the URI is outdated,
        // then simply return null
        return null;
      }
      final List<?> list = (List<?>) container.eGet(reference, false);
      final int listIndex = Integer.parseUnsignedInt(segment.substring(listSeparatorIndex + 1));
      // If the uri references an element outside of the list range return null (i.e. cannot find element)
      if (listIndex >= list.size()) {
        return null;
      }
      return (EObject) list.get(listIndex);
    } else {
      return (EObject) container.eGet(reference, false);
    }
  }

  @Override
  protected int indexOfUnescapedChar(final String str, final CharMatcher ch, final int fromIndex) {
    int nextIndex = ch.indexIn(str, fromIndex);
    return nextIndex == -1 ? str.length() : nextIndex;
  }
}
