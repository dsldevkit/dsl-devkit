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
package com.avaloq.tools.ddk.xtext.expression.generator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;


/**
 * Comparator to sort EClasses with more specific before super types.
 */
public class EClassComparator implements Comparator<EClass> {

  /**
   * Returns a list sorted according to related groups of types.
   *
   * @param objects
   *          collection to sort
   * @param mapping
   *          mapping function
   * @param <T>
   *          object type
   * @return sorted list
   */
  public static <T> ListMultimap<EPackage, T> sortedEPackageGroups(final Iterable<T> objects, final Function<T, EClass> mapping) {
    ListMultimap<EPackage, T> index = Multimaps.index(objects, (Function<T, EPackage>) from -> {
      EClass eClass = mapping.apply(from);
      if (eClass != null) {
        return eClass.getEPackage();
      }
      return null;
    });
    ListMultimap<EPackage, T> result = LinkedListMultimap.create(index.keySet().size());
    for (EPackage pkg : index.keySet()) {
      result.putAll(pkg, sortedGroups(index.get(pkg), mapping));
    }
    return result;
  }

  /**
   * Returns a list sorted according to related groups of types.
   *
   * @param objects
   *          collection to sort
   * @param mapping
   *          mapping function
   * @param <T>
   *          object type
   * @return sorted list
   */
  public static <T> List<T> sortedGroups(final Iterable<T> objects, final Function<T, EClass> mapping) {
    final Iterable<T> eObjectObjects = Iterables.filter(objects, new Predicate<T>() {
      @Override
      public boolean apply(final T input) {
        return isEObjectType(mapping.apply(input));
      }
    });
    final Map<EClass, T> typeMap = Maps.newHashMap();
    Graph<EClass> graph = Graph.create(Iterables.transform(Iterables.filter(objects, new Predicate<T>() {
      @Override
      public boolean apply(final T input) {
        return !Iterables.contains(eObjectObjects, input);
      }
    }), mapping));
    for (T o : objects) {
      EClass type = mapping.apply(o);
      typeMap.put(type, o);
      if (!isEObjectType(type)) {
        addType(graph, type);
      }
    }
    return Lists.newArrayList(Iterables.concat(Iterables.filter(Iterables.transform(graph.sort(), new Function<EClass, T>() {
      @Override
      public T apply(final EClass from) {
        return typeMap.get(from);
      }
    }), Predicates.notNull()), eObjectObjects));
  }

  /**
   * Adds an EClass and all its super types to the graph.
   *
   * @param graph
   *          destination graph
   * @param type
   *          type to add
   */
  private static void addType(final Graph<EClass> graph, final EClass type) {
    graph.addNode(type);
    for (EClass superType : type.getESuperTypes()) {
      addType(graph, superType);
      graph.addEdge(type, superType);
    }
  }

  /** {@inheritDoc} */
  @Override
  public int compare(final EClass o1, final EClass o2) {
    if (o1 == o2) { // NOPMD
      return 0;
    } else if (o1 == null) {
      return 1;
    } else if (o2 == null) {
      return -1;
    } else {
      if (isEObjectType(o1) || o1.isSuperTypeOf(o2)) {
        return 1;
      } else if (isEObjectType(o2) || o2.isSuperTypeOf(o1)) {
        return -1;
      }
    }
    return 0;
  }

  /**
   * Tests whether the given EClass corresponds to the {@link EcorePackage.Literals#EOBJECT EObject type}. This will work regardless of whether the type was
   * loaded from some Ecore.ecore file or from the EPackage registered in the registry.
   *
   * @param eClass
   *          EClass, can also be {@code null}
   * @return {@code true} if type is EObject
   */
  public static boolean isEObjectType(final EClass eClass) {
    if (eClass == null || eClass.eIsProxy()) {
      return false;
    }
    return eClass == EcorePackage.Literals.EOBJECT || Objects.equals(eClass.getInstanceClassName(), EcorePackage.Literals.EOBJECT.getInstanceClassName())
        || (Objects.equals(eClass.getName(), EcorePackage.Literals.EOBJECT.getName())
            && Objects.equals(eClass.getEPackage().getNsURI(), EcorePackage.Literals.EOBJECT.getEPackage().getNsURI()));
  }
}
