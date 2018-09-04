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
package com.avaloq.tools.ddk.xtext.test.resource;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;
import org.eclipse.xtext.resource.IResourceDescription
import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.emf.common.util.URI
import java.util.Collection
import org.eclipse.xtext.resource.IResourceDescription.Delta
import com.google.common.collect.HashMultiset
import org.junit.Assert
import com.google.common.collect.Sets
import com.avaloq.tools.ddk.xtext.test.TestSource

/**
 * Abstract base class for {@link AbstractCachingResourceDescriptionManager} tests.
 */
abstract class AbstractResourceDescriptionManagerTest extends AbstractXtextTest {

  /**
   * Simple unchanged {@link Delta} implementation with {@link URI}.
   */
  static class UnchangedDelta implements Delta {

    val URI uri;

    new(URI uri) {
      this.uri = uri;
    }

    override getNew() {
      null
    }

    override getOld() {
      null
    }

    override getUri() {
      uri
    }

    override haveEObjectDescriptionsChanged() {
      false
    }
  }

  val AbstractCachingResourceDescriptionManager resourceDescriptionManager = testUtil.get(IResourceDescription.Manager) as AbstractCachingResourceDescriptionManager;
  val IResourceDescriptions index = testUtil.get(IResourceDescriptions);

  /**
   * Returns the {@link AbstractCachingResourceDescriptionManager) to use in the test.
   *
   * @return the {@link AbstractCachingResourceDescriptionManager) to use in the test, never {@code null}
   */
  def AbstractCachingResourceDescriptionManager getResourceDescriptionManager() {
    return resourceDescriptionManager;
  }

  /**
   * Returns the {@link IResourceDescriptions) to use in the test.
   *
   * @return the {@link IResourceDescriptions) to use in the test, never {@code null}
   */
  def IResourceDescriptions getResourceDescriptions() {
    return index;
  }

  /**
   * Returns the candidates for the affected resource computations.
   * <p>
   * <em>Note</em>: By default, all registered sources are considered as candidates.
   * </p>
   *
   * @return the candidates for the affected resource computation, never {@code null}
   */
  def Collection<URI> getCandidates() {
    var Collection<URI> candidates = testInformation.getTestObject(URI) as Collection<URI>;
    if (candidates === null) {
      candidates = Sets.newHashSet;
      testInformation.putTestObject(URI, candidates);
    }
    return candidates;
  }

  /**
   * Creates a {@link TestSource} and considers it as a candidate by default.
   *
   * @param sourceFileName
   *          file name for the test source, must not be {@code null}
   * @param content
   *          content of source, must not be {@code null}
   * @return a new {@link TestSource} with the given parameters, never {@code null}
   */
  override protected createTestSource(String sourceFileName, String content) {
    val testSource = super.createTestSource(sourceFileName, content);
    getCandidates().add(testSource.uri);
    return testSource;
  }

  /**
   * Creates a {@link Delta} for the given {@link URI}.
   * <p>
   * <em>Note</em>: By default, the delta is an instance of {@link UnchangedDelta}
   * </p>
   *
   * @param uri
   *          the delta {@link URI}, must not be {@code null}
   * @return a new {@link Delta}, never {@code null}
   */
  def Delta createDelta(URI uri) {
    return new UnchangedDelta(uri);
  }

  /**
   * Returns the {@link URI} of the {@link TestSource} with the given file name.
   *
   * @param sourceFileName
   *          file name for the test source, must not be {@code null}
   * @return the {@link URI} of the {@link TestSource} with the given file name, never {@code null}
   */
  def URI getUri(String sourceFileName) {
    return getTestSource(sourceFileName).uri;
  }

  /**
   * Asserts that the given delta causes the expected set of affected resources.
   * <p>
   * <em>Note</em>: Uses the candidates returned by {@link #getCandidates()}.
   * </p>
   *
   * @param deltaSourceName
   *        the delta source name, must not be {@code null}
   * @param expectedSourceNames
   *        the expected affected source names, must not be {@code null}
   */
  def assertAffectedResources(String deltaSourceName, String... expectedSourceNames) {
    val Collection<URI> expectedUris = Sets.newHashSet;
    for (sourceName : expectedSourceNames) {
      expectedUris.add(getUri(sourceName));
    }
    assertAffectedResources(Sets.newHashSet(getUri(deltaSourceName)), getCandidates(), expectedUris);
  }

  /**
   * Asserts that the given set of deltas causes the expected set of affected {@link URI}s.
   * <p>
   * <em>Note</em>: Uses the candidates returned by {@link #getCandidates()}.
   * </p>
   *
   * @param deltaUris
   *        the delta {@link URI}s, must not be {@code null}
   * @param expectedUris
   *        the expected affected {@link URI}s, must not be {@code null}
   */
  def assertAffectedResources(Collection<URI> deltaUris, Collection<URI> expectedUris) {
    assertAffectedResources(deltaUris, getCandidates(), expectedUris);
  }

  /**
   * Asserts that the given set of deltas causes the expected set of affected {@link URI}s.
   *
   * @param deltaUris
   *        the delta {@link URI}s, must not be {@code null}
   * @param candidates
   *        the potential candidates that can be affected, must not be {@code null}
   * @param expectedUris
   *        the expected affected {@link URI}s, must not be {@code null}
   */
  def assertAffectedResources(Collection<URI> deltaUris, Collection<URI> candidates, Collection<URI> expectedUris) {
    assertDeltaAffectedResources(Sets.newHashSet(deltaUris.map[createDelta(it)]), candidates, expectedUris);
  }

  /**
   * Asserts that the given set of deltas causes the expected set of affected {@link URI}s.
   *
   * @param deltas
   *        the {@link Delta}s, must not be {@code null}
   * @param candidates
   *        the potential candidates that can be affected, must not be {@code null}
   * @param expectedUris
   *        the expected affected {@link URI}s, must not be {@code null}
   */
  def assertDeltaAffectedResources(Collection<Delta> deltas, Collection<URI> candidates, Collection<URI> expectedUris) {
    val result = getResourceDescriptionManager().getAffectedResources(deltas, candidates, getResourceDescriptions());
    Assert.assertEquals("Affected URIs must be correct.", HashMultiset.create(expectedUris), HashMultiset.create(result));
  }
}
