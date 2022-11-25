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

package com.avaloq.tools.ddk.xtext.ui.quickfix;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;

import com.google.common.collect.Ordering;


/**
 * Aggregates methods to sort a collection of {@link IMarker}s.
 */
public final class MarkerSorter {

  private static final Ordering<IMarker> NAME_ORDERING = Ordering.natural().onResultOf((final IMarker from) -> from != null
      ? from.getAttribute(IMarker.CHAR_START, Integer.MIN_VALUE)
      : Integer.MIN_VALUE).reverse();

  private MarkerSorter() {
    // Utility classes should not have a public or default constructor
  }

  /**
   * Sort markers by line number and invert order.
   * This allows multiple resolutions to be applied to a single file as
   * we avoid an update 'pushing text down' and invalidating line number information.
   *
   * @param markers
   *          the markers
   * @return the list
   */
  public static List<IMarker> sortByLineNumber(final Collection<IMarker> markers) {
    return NAME_ORDERING.sortedCopy(markers);
  }
}
