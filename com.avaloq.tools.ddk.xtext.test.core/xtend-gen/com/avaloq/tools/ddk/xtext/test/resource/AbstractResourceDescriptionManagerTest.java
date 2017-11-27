/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.test.resource;

import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;
import com.avaloq.tools.ddk.xtext.test.TestInformation;
import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.avaloq.tools.ddk.xtext.test.XtextTestSource;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;

/**
 * Abstract base class for {@link AbstractCachingResourceDescriptionManager} tests.
 */
@SuppressWarnings("all")
public abstract class AbstractResourceDescriptionManagerTest extends AbstractXtextTest {
  /**
   * Simple unchanged {@link Delta} implementation with {@link URI}.
   */
  public static class UnchangedDelta implements IResourceDescription.Delta {
    private final URI uri;
    
    public UnchangedDelta(final URI uri) {
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
      return this.uri;
    }
    
    @Override
    public boolean haveEObjectDescriptionsChanged() {
      return false;
    }
  }
  
  private final AbstractCachingResourceDescriptionManager resourceDescriptionManager = ((AbstractCachingResourceDescriptionManager) this.getTestUtil().<IResourceDescription.Manager>get(IResourceDescription.Manager.class));
  
  private final IResourceDescriptions index = this.getTestUtil().<IResourceDescriptions>get(IResourceDescriptions.class);
  
  /**
   * Returns the {@link AbstractCachingResourceDescriptionManager) to use in the test.
   * 
   * @return the {@link AbstractCachingResourceDescriptionManager) to use in the test, never {@code null}
   */
  public AbstractCachingResourceDescriptionManager getResourceDescriptionManager() {
    return this.resourceDescriptionManager;
  }
  
  /**
   * Returns the {@link IResourceDescriptions) to use in the test.
   * 
   * @return the {@link IResourceDescriptions) to use in the test, never {@code null}
   */
  public IResourceDescriptions getResourceDescriptions() {
    return this.index;
  }
  
  /**
   * Returns the candidates for the affected resource computations.
   * <p>
   * <em>Note</em>: By default, all registered sources are considered as candidates.
   * </p>
   * 
   * @return the candidates for the affected resource computation, never {@code null}
   */
  public Collection<URI> getCandidates() {
    TestInformation _testInformation = this.getTestInformation();
    Object _testObject = _testInformation.getTestObject(URI.class);
    Collection<URI> candidates = ((Collection<URI>) _testObject);
    if ((candidates == null)) {
      HashSet<URI> _newHashSet = Sets.<URI>newHashSet();
      candidates = _newHashSet;
      TestInformation _testInformation_1 = this.getTestInformation();
      _testInformation_1.putTestObject(URI.class, candidates);
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
    Collection<URI> _candidates = this.getCandidates();
    URI _uri = testSource.getUri();
    _candidates.add(_uri);
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
  public IResourceDescription.Delta createDelta(final URI uri) {
    return new AbstractResourceDescriptionManagerTest.UnchangedDelta(uri);
  }
  
  /**
   * Returns the {@link URI} of the {@link TestSource} with the given file name.
   * 
   * @param sourceFileName
   *          file name for the test source, must not be {@code null}
   * @return the {@link URI} of the {@link TestSource} with the given file name, never {@code null}
   */
  public URI getUri(final String sourceFileName) {
    XtextTestSource _testSource = this.getTestSource(sourceFileName);
    return _testSource.getUri();
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
    final Collection<URI> expectedUris = Sets.<URI>newHashSet();
    for (final String sourceName : expectedSourceNames) {
      URI _uri = this.getUri(sourceName);
      expectedUris.add(_uri);
    }
    URI _uri_1 = this.getUri(deltaSourceName);
    HashSet<URI> _newHashSet = Sets.<URI>newHashSet(_uri_1);
    Collection<URI> _candidates = this.getCandidates();
    this.assertAffectedResources(_newHashSet, _candidates, expectedUris);
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
    Collection<URI> _candidates = this.getCandidates();
    this.assertAffectedResources(deltaUris, _candidates, expectedUris);
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
    final Function1<URI, IResourceDescription.Delta> _function = (URI it) -> {
      return this.createDelta(it);
    };
    Iterable<IResourceDescription.Delta> _map = IterableExtensions.<URI, IResourceDescription.Delta>map(deltaUris, _function);
    HashSet<IResourceDescription.Delta> _newHashSet = Sets.<IResourceDescription.Delta>newHashSet(_map);
    this.assertDeltaAffectedResources(_newHashSet, candidates, expectedUris);
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
  public void assertDeltaAffectedResources(final Collection<IResourceDescription.Delta> deltas, final Collection<URI> candidates, final Collection<URI> expectedUris) {
    AbstractCachingResourceDescriptionManager _resourceDescriptionManager = this.getResourceDescriptionManager();
    IResourceDescriptions _resourceDescriptions = this.getResourceDescriptions();
    final Collection<URI> result = _resourceDescriptionManager.getAffectedResources(deltas, candidates, _resourceDescriptions);
    HashMultiset<URI> _create = HashMultiset.<URI>create(expectedUris);
    HashMultiset<URI> _create_1 = HashMultiset.<URI>create(result);
    Assert.assertEquals("Affected URIs must be correct.", _create, _create_1);
  }
}
