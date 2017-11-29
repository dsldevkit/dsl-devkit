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
package com.avaloq.tools.ddk.xtext.generator.test.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.avaloq.tools.ddk.xtext.generator.util.Graph;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;


@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
public class GraphTest {
  // CHECKSTYLE:CONSTANTS-OFF

  @Test
  public void testTopologicalSorting() {
    Multimap<String, String> graph = HashMultimap.create();
    graph.put("7", "11");
    graph.put("7", "8");
    graph.put("3", "8");
    graph.put("3", "10");
    graph.put("11", "2");
    graph.put("11", "9");
    graph.put("11", "10");
    graph.put("8", "8");
    graph.put("8", "9");
    graph.put("8", "10");

    List<String> sorted = Graph.create(graph).sort();
    assertSorting(graph, sorted);
  }

  @Test
  public void testDependencyCycle() {
    Multimap<String, String> graph = ImmutableMultimap.of("1", "2", "2", "3", "3", "1");

    try {
      Graph.create(graph).sort();
      fail();
    } catch (IllegalStateException e) {
      assertTrue(true); // NOPMD
    }
  }

  protected <T> void assertSorting(final Multimap<T, T> expected, final List<T> actual) {
    for (Map.Entry<T, T> entry : expected.entries()) {
      assertTrue(actual.indexOf(entry.getKey()) <= actual.indexOf(entry.getValue()));
    }
  }

}

