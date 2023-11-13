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
package com.avaloq.tools.ddk.test.core.util;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;


/**
 * Listens and registers resource build events.
 */
public class ResourceChangeListener implements IResourceChangeListener {

  private final Set<String> sourceTypes = Sets.newHashSet();
  private final Set<String> sources = Sets.newHashSet();

  /**
   * Resets the {@link ResourceChangeListener}.
   */
  public void reset() {
    sourceTypes.clear();
    sources.clear();
  }

  @Override
  public void resourceChanged(final IResourceChangeEvent event) {
    final IResourceDelta delta = event.getDelta();
    if (delta != null) {
      for (final IResourceDelta resourceDelta : filterDelta(flattenDelta(delta))) {
        sourceTypes.add(resourceDelta.getResource().getFileExtension());
        sources.add(resourceDelta.getResource().getName());
      }
    }
  }

  /**
   * Flattens the delta tree to a list of delta paths.
   *
   * @param delta
   *          the {@link IResourceDelta} to flatten, must not be {@code null}
   * @return the flattened delta tree, never {@code null} or empty
   */
  private Collection<IResourceDelta> flattenDelta(final IResourceDelta delta) {
    final Set<IResourceDelta> result = Sets.newHashSet(delta);
    for (final IResourceDelta child : delta.getAffectedChildren()) {
      result.addAll(flattenDelta(child));
    }
    return result;
  }

  /**
   * Returns the set of source types that were collected as delta.
   *
   * @return the set of source types that were collected as delta, never {@code null}
   */
  public Set<String> getDeltaSourceTypes() {
    return sourceTypes;
  }

  /**
   * Returns the set of source names that were collected as delta.
   *
   * @return the set of source names that were collected as delta, never {@code null}
   */
  public Set<String> getDeltaSources() {
    return sources;
  }

  /**
   * Filters the delta set to only include normal source files (files that have a name and source type).
   *
   * @param delta
   *          the delta set, must not be {@code null}
   * @return the filtered delta set, never {@code null}
   */
  private Collection<IResourceDelta> filterDelta(final Collection<IResourceDelta> delta) {
    return Collections2.filter(delta, new Predicate<IResourceDelta>() {
      @Override
      public boolean apply(final IResourceDelta delta) {
        final IResource resource = delta.getResource();
        return !Strings.isNullOrEmpty(resource.getFileExtension()) && resource.getName().charAt(0) != '.';
      }
    });
  }
}
