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
package com.avaloq.tools.ddk.xtext.test.resource;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.junit.Assert;

import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;
import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.avaloq.tools.ddk.xtext.test.XtextTestSource;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Sets;

/**
 * Abstract base class for {@link AbstractCachingResourceDescriptionManager} tests.
 */
public abstract class AbstractResourceDescriptionManagerTest extends AbstractXtextTest {

  /**
   * Simple unchanged {@link Delta} implementation with {@link URI}.
   */
  static class UnchangedDelta implements Delta {

    private final URI uri;

    UnchangedDelta(final URI uri) {
      this.uri = uri;
    }

    @Override
    public IResourceDescription getNew() {
      return null;
    }

    @Override
    public IResourceDescription getOld() {
      return null;
    }

    @Override
    public URI getUri() {
      return uri;
    }

    @Override
    public boolean haveEObjectDescriptionsChanged() {
      return false;
    }
  }

  private final AbstractCachingResourceDescriptionManager resourceDescriptionManager = (AbstractCachingResourceDescriptionManager) getTestUtil().get(IResourceDescription.Manager.class);
  private final IResourceDescriptions index = getTestUtil().get(IResourceDescriptions.class);

  /**
   * Returns the {@link AbstractCachingResourceDescriptionManager) to use in the test.
   *
   * @return the {@link AbstractCachingResourceDescriptionManager) to use in the test, never {@code null}
   */
  public AbstractCachingResourceDescriptionManager getResourceDescriptionManager() {
    return resourceDescriptionManager;
  }

  /**
   * Returns the {@link IResourceDescriptions) to use in the test.
   *
   * @return the {@link IResourceDescriptions) to use in the test, never {@code null}
   */
  public IResourceDescriptions getResourceDescriptions() {
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
  @SuppressWarnings("unchecked")
  public Collection<URI> getCandidates() {
    Collection<URI> candidates = (Collection<URI>) getTestInformation().getTestObject(URI.class);
    if (candidates == null) {
      candidates = Sets.newHashSet();
      getTestInformation().putTestObject(URI.class, candidates);
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
  @Override
  protected XtextTestSource createTestSource(final String sourceFileName, final String content) {
    final XtextTestSource testSource = super.createTestSource(sourceFileName, content);
    getCandidates().add(testSource.getUri());
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
  public Delta createDelta(final URI uri) {
    return new UnchangedDelta(uri);
  }

  /**
   * Returns the {@link URI} of the {@link TestSource} with the given file name.
   *
   * @param sourceFileName
   *          file name for the test source, must not be {@code null}
   * @return the {@link URI} of the {@link TestSource} with the given file name, never {@code null}
   */
  public URI getUri(final String sourceFileName) {
    return getTestSource(sourceFileName).getUri();
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
  public void assertAffectedResources(final String deltaSourceName, final String... expectedSourceNames) {
    final Collection<URI> expectedUris = Sets.newHashSet();
    for (final String sourceName : expectedSourceNames) {
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
  public void assertAffectedResources(final Collection<URI> deltaUris, final Collection<URI> expectedUris) {
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
  public void assertAffectedResources(final Collection<URI> deltaUris, final Collection<URI> candidates, final Collection<URI> expectedUris) {
    final Set<Delta> deltas = deltaUris.stream().map(uri -> createDelta(uri)).collect(Collectors.toSet());
    assertDeltaAffectedResources(deltas, candidates, expectedUris);
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
  public void assertDeltaAffectedResources(final Collection<Delta> deltas, final Collection<URI> candidates, final Collection<URI> expectedUris) {
    final Collection<URI> result = getResourceDescriptionManager().getAffectedResources(deltas, candidates, getResourceDescriptions());
    Assert.assertEquals("Affected URIs must be correct.", HashMultiset.create(expectedUris), HashMultiset.create(result));
  }
}
