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
package com.avaloq.tools.ddk.xtext.expression.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;


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
    private final Set<Node<T>> inEdges = Sets.newLinkedHashSet();
    private final Set<Node<T>> outEdges = Sets.newLinkedHashSet();

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

  private final Map<T, Node<T>> nodes = Maps.newLinkedHashMap();

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
   * Helper method to create a new graph.
   *
   * @param <T>
   *          node type
   * @param graph
   *          graph as multiset where values represent targets of edges with key as source
   * @return graph
   */
  public static <T> Graph<T> create(final Multimap<T, T> graph) {
    Graph<T> g = new Graph<T>();
    for (Map.Entry<T, T> entry : graph.entries()) {
      T from = entry.getKey();
      T to = entry.getValue();
      g.addNode(from);
      g.addNode(to);
      g.addEdge(from, to);
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
    // TODO try to sort to something as close as possible to the original

    // L <- Empty list that will contain the sorted elements
    List<Node<T>> l = Lists.newArrayList();

    // S <- Set of all nodes with no incoming edges
    Set<Node<T>> s = Sets.newLinkedHashSet();
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
        throw new IllegalStateException("Cycle present, topological sort not possible: " + n.ref + " -> " + n.inEdges);
      }
    }

    return Lists.transform(l, new Function<Node<T>, T>() {
      @Override
      public T apply(final Node<T> from) {
        return from.ref;
      }
    });
  }

}
