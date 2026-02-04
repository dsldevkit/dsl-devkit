/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.check.jvmmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;


/**
 * A Java type relationship analyzer for the purpose of ordering type-based dispatch
 * of actions on an object, based on <code>instanceof</code> checks.
 * <p>
 * Does analysis to the extent that supertype relationship information is available
 * and supported for the types involved. If for a given type supertype information
 * is not available then there should be no particular harm in that, as the type
 * should simply get treated as a base type.
 * <p>
 * The results are returned as a forest structure of inheritance relationships.
 * If it consists of multiple trees they likely implicitly share a common base type,
 * and could be regarded as a single tree under it, even if the type in question
 * is not of interest to the caller, and thus not included in the result.
 * <p>
 * Where a type inherits from multiple types it is placed under one of the supertypes
 * only, meaning that it is not appropriate to <code>else</code> separate
 * checks for the supertypes when generating dispatch code.
 * <p>
 * It is in any case not possible to know that two types do not have a common subtype,
 * except when dealing with closed sets of types. Therefore determining whether two
 * <code>instanceof</code> checks are mutually exclusive (if one holds, the other cannot)
 * is also not possible in general.
 */
@SuppressWarnings("nls")
public final class InstanceOfCheckOrderer {

  /**
   * Private constructor for a utility class.
   */
  private InstanceOfCheckOrderer() {
  }

  /**
   * Orders the specified types.
   *
   * @param typeRefs
   *          the types
   * @return the result structure of one or more type trees
   */
  public static Forest orderTypes(final Collection<JvmTypeReference> typeRefs) {
    try {
      Map<String, Set<String>> superTypesMap = new HashMap<>();
      for (JvmTypeReference ref : typeRefs) {
        collectSuperTypes(superTypesMap, ref);
      }

      final Set<String> typesSubset = typeRefs.stream().//
          map(JvmTypeReference::getQualifiedName).//
          collect(Collectors.toSet());

      return internalOrderTypes(superTypesMap, typesSubset);
    } catch (CycleDetectedException e) {
      // Fallback implementation, treating all types as distinct.
      return orderedForestOf(typeRefs.stream().//
          map(JvmTypeReference::getQualifiedName).//
          sorted().//
          collect(Collectors.toList()));
    }
  }

  /**
   * Orders the types specified by the argument map.
   *
   * @param typesMap
   *          the map
   * @return the ordered result
   */
  public static Forest internalOrderTypes(final Map<String, Set<String>> typesMap) {
    return internalOrderTypes(typesMap, typesMap.keySet());
  }

  /**
   * Orders the specified subset of the types specified by the argument map.
   *
   * @param typesMap
   *          the map
   * @param typesSubset
   *          the subset
   * @return the ordered result
   */
  public static Forest internalOrderTypes(final Map<String, Set<String>> typesMap, final Set<String> typesSubset) {
    return makeForest(makeSuperTypesSubsetMap(typesMap, typesSubset));
  }

  private static Forest makeForest(final Map<String, Set<String>> typesMap) {
    Graph graph = Graph.fromTypesMap(typesMap);
    if (!graph.isForest()) {
      graph.breakDiamonds();
      if (!graph.isForest()) {
        graph.forestify();
        assert graph.isForest();
      }
    }
    return graph;
  }

  /**
   * Non-destructively creates a sub-map of supertype relationships.
   *
   * @param typesMap
   *          a map of types and their relationships
   * @param typesSubset
   *          a subset of the keys to use in the result map
   * @return the result map
   */
  private static Map<String, Set<String>> makeSuperTypesSubsetMap(final Map<String, Set<String>> typesMap, final Set<String> typesSubset) {
    return typesMap.entrySet().stream().//
        filter(entry -> typesSubset.contains(entry.getKey())).//
        collect(Collectors.toMap(Entry::getKey, //
            entry -> superTypesInSubset(new HashSet<>(), new HashSet<>(), entry.getKey(), typesMap, typesSubset)));
  }

  private static Set<String> superTypesInSubset(final Set<String> resultSet, final Set<String> seenSet, final String key, final Map<String, Set<String>> typesMap, final Set<String> typesSubset) {
    for (String superType : typesMap.get(key)) {
      if (typesSubset.contains(superType)) {
        resultSet.add(superType); // direct supertype
      } else if (!seenSet.contains(superType)) {
        seenSet.add(superType); // to avoid effort duplication (diamonds) and non-termination (cycles)
        superTypesInSubset(resultSet, seenSet, superType, typesMap, typesSubset);
      }
    }
    return resultSet;
  }

  /**
   * Collects all direct and indirect supertype information for the specified type.
   *
   * @param map
   *          the result map, which need not be empty, and must be modifiable
   * @param typeRef
   *          the type whose supertypes to collect
   */
  private static void collectSuperTypes(final Map<String, Set<String>> map, final JvmTypeReference typeRef) {
    String qualifiedName = typeRef.getQualifiedName();
    if (!map.containsKey(qualifiedName)) {
      List<JvmTypeReference> superTypes = getSuperTypes(typeRef);
      map.put(qualifiedName, superTypes.stream().//
          map(JvmTypeReference::getQualifiedName).//
          collect(Collectors.toUnmodifiableSet()));
      for (JvmTypeReference ref : superTypes) {
        collectSuperTypes(map, ref);
      }
    }
  }

  /**
   * Gets the supertypes of the specified type expression.
   * Only certain types and expressions are supported, and
   * for others an empty list is returned.
   *
   * @param typeRef
   *          the type expression
   * @return the supertype expressions, or no expressions
   */
  public static List<JvmTypeReference> getSuperTypes(final JvmTypeReference typeRef) {
    JvmDeclaredType declaredType = getDeclaredType(typeRef);
    return declaredType == null ? Collections.emptyList() : declaredType.getSuperTypes();
  }

  /**
   * Gets the type of the specified type expression.
   * Only certain types and expressions are supported.
   *
   * @param typeRef
   *          the type expression
   * @return the type, or {@code null} for unsupported arguments
   */
  public static JvmDeclaredType getDeclaredType(final JvmTypeReference typeRef) {
    // We are conservative and support only the common case.
    // Other type expressions are supported, but without considering type hierarchy.
    if (typeRef instanceof JvmParameterizedTypeReference pTypeRef && pTypeRef.getArguments().isEmpty()) {
      JvmType type = pTypeRef.getType();
      if (type instanceof JvmGenericType gType && !gType.isAnonymous()) {
        return gType;
      }
    }
    return null;
  }

  /**
   * An interface for accessing result structures.
   */
  public interface Forest {
    /**
     * Returns the child nodes for the specified tree node.
     * For a {@code null} argument returns the root nodes.
     *
     * @param nodeKey
     *          the node name
     * @return the child node names
     */
    Collection<String> getSubTypes(String nodeKey);

    /**
     * Returns the root nodes of the trees of the forest.
     *
     * @return the nodes
     */
    default Collection<String> getBaseTypes() {
      return getSubTypes(null);
    }
  }

  private static Forest orderedForestOf(final List<String> vertexNames) {
    return new Forest() {
      @Override
      public Collection<String> getSubTypes(final String parentNode) {
        return parentNode == null ? vertexNames : Collections.emptySet();
      }
    };
  }

  /**
   * A graph vertex.
   * <p>
   * The name of the vertex is stored out of band.
   * Qualified type names are used as vertex names.
   * <p>
   * The inbound vertices represent supertypes,
   * and the outbound ones represent subtypes.
   *
   * @param inbound
   *          the names of adjacent vertices with inbound edges
   * @param outbound
   *          the names of adjacent vertices with outbound edges
   */
  private static record Vertex(Set<String> inbound, Set<String> outbound) {
    boolean isTreeNode() {
      return inbound().size() <= 1;
    }
  }

  /**
   * A directed acyclic graph implementation.
   * Represented as a vertex map, keyed by vertex name.
   */
  private static final class Graph implements Forest {
    private final Map<String, Vertex> vertexMap;

    /**
     * Constructs a graph from the specified types and supertype relations.
     *
     * @param typesMap
     *          the map
     * @return the result graph
     */
    static Graph fromTypesMap(final Map<String, Set<String>> typesMap) {
      Graph graph = new Graph();
      for (Entry<String, Set<String>> entry : typesMap.entrySet()) {
        String toKey = entry.getKey();
        Set<String> superTypes = entry.getValue();
        if (superTypes.isEmpty()) {
          graph.addVertex(toKey);
        } else {
          for (String fromKey : superTypes) {
            graph.addEdgeWithVertices(fromKey, toKey);
          }
        }
      }
      return graph;
    }

    /**
     * Creates a new instance of {@link Graph}, such that it does not use an ordered
     * {@link #vertexMap} as lookups of individual vertices do not benefit from that.
     * For operations that require deterministic order we must sort the keys first.
     */
    Graph() {
      vertexMap = new HashMap<>();
    }

    /**
     * Whether the graph only consists of trees.
     *
     * @return a boolean result
     */
    boolean isForest() {
      return vertexMap.values().stream().allMatch(Vertex::isTreeNode);
    }

    /**
     * Makes a vertex using ordered sets for deterministic ordering of operations.
     *
     * @return a new object
     */
    private Vertex makeVertex() {
      return new Vertex(new TreeSet<>(), new TreeSet<>());
    }

    /**
     * Adds a vertex for the specified key if it does not exist.
     *
     * @param key
     *          the key
     * @return the added or existing vertex
     */
    Vertex addVertex(final String key) {
      return vertexMap.computeIfAbsent(key, x -> makeVertex());
    }

    /**
     * Adds an edge with any required vertices.
     *
     * @param from
     *          a start vertex for the edge
     * @param to
     *          the end vertex
     * @throws CycleDetectedException
     *           if adding the edge would create a cycle
     */
    void addEdgeWithVertices(final String from, final String to) throws CycleDetectedException {
      if (from.equals(to) || isReachable(to, from)) {
        throw new CycleDetectedException(from, to);
      }
      Vertex fromVertex = addVertex(from);
      if (!fromVertex.outbound().contains(to)) {
        Vertex toVertex = addVertex(to);
        fromVertex.outbound().add(to);
        toVertex.inbound().add(from);
      }
    }

    /**
     * Removes the specified (directed) edge, which must exist.
     *
     * @param from
     *          the source vertex
     * @param to
     *          the target vertex
     */
    void removeEdge(final String from, final String to) {
      Vertex fromVertex = vertexMap.get(from);
      Vertex toVertex = vertexMap.get(to);
      fromVertex.outbound().remove(to);
      toVertex.inbound().remove(from);
    }

    /**
     * Removes any redundant inbound edges to the specified vertex.
     * They are only redundant if after removal another walk between
     * the vertices would remain. Effective on "diamond" inheritance.
     *
     * @param toKey
     *          the target vertex, which should have multiple inbound edges
     */
    private void breakDiamondTo(final String toKey) {
      Vertex toVertex = vertexMap.get(toKey);
      List<String> fromVertices = toVertex.inbound().stream().toList();
      int n = fromVertices.size();
      for (int i = 0; i < n; i++) {
        String fromKey = fromVertices.get(i);
        // If there still is a fromKey -> toKey edge,
        // see if it can be removed without loss of knowledge
        // about one being a subtype of the other.
        if (toVertex.inbound().contains(fromKey) && isReachableIndirectly(fromKey, toKey)) {
          removeEdge(fromKey, toKey);
        }
      }
    }

    /**
     * Tries to break diamond-structured inheritance hierarchies.
     * A no-operation if that is not applicable or possible.
     */
    void breakDiamonds() {
      List<String> toVertices = vertexMap.entrySet().stream().//
          filter(entry -> !entry.getValue().isTreeNode()).map(Entry::getKey).sorted().toList();
      toVertices.forEach(toKey -> breakDiamondTo(toKey));
    }

    /**
     * Removes multiple inheritance from the graph, removing excess edges where necessary.
     * <p>
     * This operation could be implemented differently by removing the problematic subtrees
     * into a separate {@link Graph}, which would not prevent the supertypes from being treated
     * in a mutually exclusive way. As it is we assume that we can leave each problem vertex
     * under any one of its supertypes, without it ending up excluded from checking.
     */
    void forestify() {
      List<String> toVertices = vertexMap.entrySet().stream().//
          filter(entry -> !entry.getValue().isTreeNode()).map(Entry::getKey).sorted().toList();
      for (String toKey : toVertices) {
        Vertex vertex = vertexMap.get(toKey);
        if (vertex != null && !vertex.isTreeNode()) {
          List<String> fromKeys = vertex.inbound().stream().skip(1).toList();
          for (String fromKey : fromKeys) {
            removeEdge(fromKey, toKey);
          }
        }
      }
    }

    /**
     * Whether one vertex is reachable from another via directed edges.
     * If so, then {@code from} is strictly a supertype of {@code to}.
     * <p>
     * Note that a vertex is not considered reachable from itself unless
     * it is reachable via edges, but loops are not allowed.
     *
     * @param from
     *          a vertex
     * @param to
     *          another vertex
     * @return a boolean result
     */
    private boolean isReachable(final String from, final String to) {
      Vertex fromVertex = vertexMap.get(from);
      if (fromVertex != null) {
        Set<String> obs = fromVertex.outbound();
        return obs.contains(to) || obs.stream().anyMatch(x -> isReachable(x, to));
      }
      return false;
    }

    /**
     * Whether one vertex is reachable from another without using any direct edge
     * between the two vertices.
     *
     * @param from
     *          a vertex
     * @param to
     *          another vertex
     * @return a boolean result
     */
    private boolean isReachableIndirectly(final String from, final String to) {
      Vertex fromVertex = vertexMap.get(from);
      return fromVertex != null && fromVertex.outbound().stream().anyMatch(x -> isReachable(x, to));
    }

    @Override
    public Collection<String> getSubTypes(final String nodeKey) {
      if (nodeKey == null) {
        return vertexMap.entrySet().stream().//
            filter(entry -> entry.getValue().inbound().isEmpty()).//
            map(entry -> entry.getKey()).//
            sorted().toList();
      }
      return vertexMap.get(nodeKey).outbound();
    }

    @Override
    public String toString() {
      return "Graph[" + vertexMap + "]";
    }
  }

  /**
   * Indicates a cycle, disallowed for a DAG.
   */
  @SuppressWarnings("serial")
  public static final class CycleDetectedException extends RuntimeException {
    private final String fromVertex;
    private final String toVertex;

    private CycleDetectedException(final String fromVertex, final String toVertex) {
      this.fromVertex = fromVertex;
      this.toVertex = toVertex;
    }

    @Override
    public String toString() {
      return "CycleDetectedException [from=" + fromVertex + ", to=" + toVertex + "]";
    }
  }

}
