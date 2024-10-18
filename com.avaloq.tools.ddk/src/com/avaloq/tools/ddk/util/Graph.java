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
package com.avaloq.tools.ddk.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A generic graph which can be topologically sorted.
 *
 * @param <T>
 *          type of nodes
 */
public class Graph<T> {

  /**
   * Internal representation of a node in the graph.
   *
   * @param <T>
   *          type of nodes
   */
  private static class Node<T> {
    private final T ref;
    private final Set<Node<T>> inEdges = new LinkedHashSet<>();
    private final Set<Node<T>> outEdges = new LinkedHashSet<>();

    Node(final T ref) {
      this.ref = ref;
    }

    /**
     * Adds an outgoing edge to this node.
     *
     * @param node
     *          target node
     * @return this
     */
    public Node<T> addEdge(final Node<T> node) {
      outEdges.add(node);
      node.inEdges.add(this);
      return this;
    }

    @Override
    public String toString() {
      return ref.toString();
    }
  }

  private final Map<T, Node<T>> nodes = new LinkedHashMap<>();

  /**
   * Helper method to create a new graph.
   *
   * @param <T>
   *          node type
   * @param nodes
   *          nodes in graph
   * @return graph
   */
  public static <T> Graph<T> create(final Iterable<T> nodes) {
    Graph<T> g = new Graph<T>();
    for (T node : nodes) {
      g.addNode(node);
    }
    return g;
  }

  /**
   * Adds a node to this graph.
   *
   * @param node
   *          node to add
   * @return graph
   */
  public Graph<T> addNode(final T node) {
    if (!nodes.containsKey(node)) {
      nodes.put(node, new Node<T>(node));
    }
    return this;
  }

  /**
   * Adds an edge to this graph. Edges where {@code from.equals(to)} are ignored.
   *
   * @param from
   *          from
   * @param to
   *          to
   * @return this graph
   */
  public Graph<T> addEdge(final T from, final T to) {
    if (!from.equals(to)) {
      nodes.get(from).addEdge(nodes.get(to));
    }
    return this;
  }

  /**
   * Sorts this graph topologically and returns the sorted result.
   *
   * @return sorted graph
   */
  public List<T> sort() {
    // L <- Empty list that will contain the sorted elements
    List<Node<T>> l = new ArrayList<>();

    // S <- Set of all nodes with no incoming edges
    Set<Node<T>> s = new LinkedHashSet<>();
    for (Node<T> n : nodes.values()) {
      if (n.inEdges.isEmpty()) {
        s.add(n);
      }
    }

    // while S is non-empty do
    while (!s.isEmpty()) {
      // remove a node n from S
      Node<T> n = s.iterator().next();
      s.remove(n);

      // insert n into L
      l.add(n);

      // for each node m with an edge e from n to m do
      for (Iterator<Node<T>> it = n.outEdges.iterator(); it.hasNext();) {
        // remove edge e from the graph
        Node<T> e = it.next();
        Node<T> m = e;
        it.remove();// Remove edge from n
        m.inEdges.remove(n);// Remove edge from m

        // if m has no other incoming edges then insert m into S
        if (m.inEdges.isEmpty()) {
          s.add(m);
        }
      }
    }

    // Check to see if all edges are removed
    for (Node<T> n : nodes.values()) {
      if (!n.inEdges.isEmpty()) {
        throw new IllegalStateException("Cycle present, topological sort not possible: " + n.ref + " -> " + n.inEdges); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    return l.stream().map(f -> f.ref).toList();
  }

}
