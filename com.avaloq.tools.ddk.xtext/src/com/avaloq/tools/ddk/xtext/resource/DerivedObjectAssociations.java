/*******************************************************************************
 * Copyright (c) 2018 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.resource;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 * Derived objects associated with resource description.
 * <p>
 * Instances of this class are attached to resource descriptions or resource description delta.
 * </p>
 * <p>
 * Main purpose of the associations is to manage life cycle of the derived resources.
 * Associations enable generators to identify what derived objects (sources) should be dropped.
 * </p>
 * <p>
 * There can be many producers of derived objects, where generators executed by builder participants
 * are the most common kind. Objects produced by generators may be other sources, but could also be some
 * entities specific to the business application of a DSL (i.e. database objects). Derived objects
 * could also be valid Xtext resources visible to the builder. Therefore the framework in not trying
 * to interpret the information about the derived objects, but stores it grouped by the producer (generator id).
 * </p>
 * <p>
 * For simplicity we will refer to all producers of derived objects as to generators.
 * </p>
 */
public class DerivedObjectAssociations {

  private final Map<String, Set<String>> derivedObjects = Maps.newHashMap();

  /**
   * Adds the association for a derived object created the given generator.
   *
   * @param generatorType
   *          an identifier of the generator that created the object, must not be {@code null}
   * @param derivedObjectUri
   *          the URI of the derived object (freely defined by the generator), must not be {@code null}
   */
  public void add(final String generatorType, final String derivedObjectUri) {
    derivedObjects.computeIfAbsent(generatorType, s -> Sets.newHashSet()).add(derivedObjectUri);
  }

  /**
   * Returns identifiers of all generators that created derived objects for the given resource.
   *
   * @return identifiers of generators that created derived objects, never {@code null}
   */
  public Set<String> getGeneratorTypes() {
    return derivedObjects.keySet();
  }

  /**
   * Returns set of derived object URIs for the given generator.
   *
   * @param generatorType
   *          the identifier of the generator, must not be {@code null}
   * @return set of URIs of derived objects, never {@code null}
   */
  public Set<String> getDerivedObjects(final String generatorType) {
    return derivedObjects.getOrDefault(generatorType, Collections.emptySet());
  }

  @Override
  public String toString() {
    return derivedObjects.toString();
  }

}
