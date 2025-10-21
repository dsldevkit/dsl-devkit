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
package com.avaloq.tools.ddk.check.core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.avaloq.tools.ddk.check.runtime.configuration.ModelLocation;
import com.avaloq.tools.ddk.check.scoping.CatalogFromExtensionPointScope;
import com.google.common.collect.Iterables;


/**
 * Some tests for AIG-1314, testing that the CatalogFromExtensionPointScope does not make the resource set grow more than expected.
 */
@RunWith(XtextRunner.class)
@InjectWith(CheckInjectorProvider.class)
@SuppressWarnings("nls")
public class BugAig1314 {

  /** Constructor of super class is protected... */
  private static class TestScope extends CatalogFromExtensionPointScope {
    TestScope(final IModelLocation location, final XtextResourceSet resourceSet) {
      super(IScope.NULLSCOPE, location, resourceSet);
    }
  }

  private static final String TEST_CATALOG_EXTENSION = ".check";
  private static final String TEST_CATALOG_FILE = "SampleChecks";

  /**
   * Creates a URL for our test. Will never be used.
   *
   * @return Some syntactically correct but otherwise meaningless URL.
   */
  private URL createURL() throws MalformedURLException, URISyntaxException {
    return new java.net.URI("http://" + TEST_CATALOG_FILE + TEST_CATALOG_EXTENSION).toURL();
  }

  /**
   * Creates a ModelLocation that returns the contents of our test model string as an InputStream.
   *
   * @param url
   *          to use
   * @return the ModelLocation
   */
  private ModelLocation createModelLocation(final URL url) {
    return new ModelLocation(url, TEST_CATALOG_FILE + TEST_CATALOG_EXTENSION) {
      @Override
      public InputStream getCatalogStream() {
        return BugAig1314.class.getResourceAsStream(TEST_CATALOG_FILE);
      }
    };
  }

  /**
   * Assert that the resource set is empty.
   *
   * @param rs
   *          resource set to check.
   */
  private void assertResourceSetEmpty(final XtextResourceSet rs) {
    assertTrue("ResourceSet should be empty", rs.getURIResourceMap().isEmpty() && rs.getResources().isEmpty());
  }

  /**
   * Assert that the resource set contains a specific number of resources, and that all resources in the list are also in the map.
   *
   * @param rs
   *          resource set to check.
   * @param expected
   *          number of resources.
   * @param inMap
   *          expected number of resources in the resource map
   */
  private void assertResourceSet(final XtextResourceSet rs, final int expected, final int inMap) {
    assertTrue("Test wrong: must expect more than zero resources", expected > 0 && inMap > 0);
    assertEquals("ResourceSet should not grow", expected, rs.getResources().size());
    assertEquals("ResourceSet map size", inMap, rs.getURIResourceMap().size());
    for (Resource r : rs.getResources()) {
      URI uri = r.getURI();
      assertTrue(uri.toString() + " not found in ResourceSet map", rs.getURIResourceMap().containsKey(uri));
    }
  }

  /**
   * Assert that an iterable is not empty.
   *
   * @param iterable
   *          to check
   */
  private void assertIterableNotEmpty(final Iterable<?> iterable) {
    assertFalse("Iterable should not be empty", Iterables.isEmpty(iterable));
  }

  /**
   * Tests that querying the same scope twice doesn't make the resource set grow.
   */
  @Test
  public void testSameScopeUseTwice() throws MalformedURLException, URISyntaxException {
    XtextResourceSet rs = new XtextResourceSet();
    URL url = createURL();
    ModelLocation modelLocation = createModelLocation(url);
    CatalogFromExtensionPointScope scope = new TestScope(modelLocation, rs);
    assertResourceSetEmpty(rs);
    Iterable<IEObjectDescription> elements = scope.getAllElements();
    assertIterableNotEmpty(elements);
    int nofResourcesInMap = rs.getURIResourceMap().size();
    int nofResourcesInSet = rs.getResources().size();
    elements = scope.getAllElements();
    assertIterableNotEmpty(elements);
    assertResourceSet(rs, nofResourcesInSet, nofResourcesInMap);
  }

  /**
   * Tests that querying two different scopes doesn't make the resource set grow. That one was the real cause of bug AIG-1314.
   */
  @Test
  public void testDifferentScopeUseTwice() throws MalformedURLException, URISyntaxException {
    XtextResourceSet rs = new XtextResourceSet();
    URL url = createURL();
    ModelLocation modelLocation = createModelLocation(url);
    CatalogFromExtensionPointScope scope = new TestScope(modelLocation, rs);
    assertResourceSetEmpty(rs);
    Iterable<IEObjectDescription> elements = scope.getAllElements();
    assertIterableNotEmpty(elements);
    int nofResourcesInMap = rs.getURIResourceMap().size();
    int nofResourcesInSet = rs.getResources().size();
    CatalogFromExtensionPointScope otherScope = new TestScope(modelLocation, rs);
    assertResourceSet(rs, nofResourcesInSet, nofResourcesInMap);
    elements = otherScope.getAllElements();
    assertIterableNotEmpty(elements);
    assertResourceSet(rs, nofResourcesInSet, nofResourcesInMap);
  }
}
