/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.eclipse.xtext.util.OnChangeEvictingCache.CacheAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.build.BuildPhases;


@SuppressWarnings("nls")
public class ResourceOnlyCacheAdapterTest {

  private static final String KEY = "key";
  private static final String VALUE = "value";
  private static final EcoreFactory FACTORY = EcoreFactory.eINSTANCE;

  private EClass root;
  private EAttribute attribute;
  private CountingResource resource;

  /** Resource counting the calls to {@link #getContents()}. */
  private static class CountingResource extends ResourceImpl {
    private int getContentsCalls;

    CountingResource() {
      super(URI.createURI("test:/r.xmi"));
    }

    @Override
    public EList<EObject> getContents() {
      getContentsCalls++;
      return super.getContents();
    }
  }

  /** Derived state aware resource which is loaded without parsing anything. */
  private static class LoadedDerivedStateResource extends DerivedStateAwareResource {
    LoadedDerivedStateResource() {
      isLoaded = true;
    }
  }

  @BeforeEach
  void setUp() {
    root = FACTORY.createEClass();
    attribute = FACTORY.createEAttribute();
    root.getEStructuralFeatures().add(attribute);
    resource = new CountingResource();
    resource.getContents().add(root);
    resource.getContentsCalls = 0;
  }

  private CacheAdapter installWithValue() {
    CacheAdapter adapter = ResourceOnlyCacheAdapter.install(resource);
    adapter.set(KEY, VALUE);
    return adapter;
  }

  @Test
  void adaptsOnlyTheResource() {
    CacheAdapter adapter = ResourceOnlyCacheAdapter.install(resource);
    assertInstanceOf(ResourceOnlyCacheAdapter.class, adapter);
    assertSame(adapter, new OnChangeEvictingCache().getOrCreate(resource));
    assertFalse(root.eAdapters().contains(adapter));
    assertFalse(attribute.eAdapters().contains(adapter));
    assertEquals(0, resource.getContentsCalls, "installing must not access the contents");

    EClass newRoot = FACTORY.createEClass();
    resource.getContents().add(newRoot);
    assertFalse(newRoot.eAdapters().contains(adapter));
  }

  @Test
  void keepsExistingCacheAdapter() {
    CacheAdapter existing = new OnChangeEvictingCache().getOrCreate(resource);
    assertSame(existing, ResourceOnlyCacheAdapter.install(resource));
    assertEquals(1, resource.eAdapters().size());
  }

  @Test
  void deepChangeKeepsValues() {
    CacheAdapter adapter = installWithValue();
    attribute.setName("changedAttribute");
    root.setName("changedClass");
    assertEquals(VALUE, adapter.get(KEY));
  }

  @Test
  void addingRootClearsValues() {
    CacheAdapter adapter = installWithValue();
    resource.getContents().add(FACTORY.createEClass());
    assertNull(adapter.get(KEY));
  }

  @Test
  void removingRootClearsValues() {
    CacheAdapter adapter = installWithValue();
    resource.getContents().remove(root);
    assertNull(adapter.get(KEY));
  }

  @Test
  void unloadClearsValues() {
    CacheAdapter adapter = installWithValue();
    resource.unload();
    assertNull(adapter.get(KEY));
  }

  @Test
  void explicitClearClearsValues() {
    CacheAdapter adapter = installWithValue();
    new OnChangeEvictingCache().clear(resource);
    assertNull(adapter.get(KEY));
  }

  @Test
  void removingAdapterDoesNotAccessContents() {
    CacheAdapter adapter = ResourceOnlyCacheAdapter.install(resource);
    resource.eAdapters().remove(adapter);
    assertEquals(0, resource.getContentsCalls);
  }

  @Test
  void derivedStateInstallAndDiscardClearValues() {
    int[] installs = new int[1];
    DerivedStateAwareResource derivedResource = new LoadedDerivedStateResource();
    derivedResource.setCache(new OnChangeEvictingCache());
    derivedResource.setDerivedStateComputer(new IDerivedStateComputer() {
      @Override
      public void installDerivedState(final DerivedStateAwareResource res, final boolean preIndexingPhase) {
        installs[0]++;
      }

      @Override
      public void discardDerivedState(final DerivedStateAwareResource res) {
        // nothing to discard
      }
    });

    CacheAdapter adapter = ResourceOnlyCacheAdapter.install(derivedResource);
    assertEquals(0, installs[0], "installing must not install the derived state");

    adapter.set(KEY, VALUE);
    derivedResource.installDerivedState(false);
    assertEquals(1, installs[0]);
    assertNull(adapter.get(KEY));

    adapter.set(KEY, VALUE);
    derivedResource.discardDerivedState();
    assertNull(adapter.get(KEY));
  }

  @Test
  void installerOnlyInstallsWhileIndexing() {
    ResourceSet resourceSet = new ResourceSetImpl();
    ResourceOnlyCacheAdapter.IndexingInstaller.addTo(resourceSet);
    ResourceOnlyCacheAdapter.IndexingInstaller.addTo(resourceSet);
    assertEquals(1, resourceSet.eAdapters().size());

    Resource notIndexed = resourceSet.createResource(URI.createURI("test:/a.xmi"));
    assertFalse(new OnChangeEvictingCache().getOrCreate(notIndexed) instanceof ResourceOnlyCacheAdapter);

    BuildPhases.setIndexing(resourceSet, true);
    Resource indexed = resourceSet.createResource(URI.createURI("test:/b.xmi"));
    assertInstanceOf(ResourceOnlyCacheAdapter.class, new OnChangeEvictingCache().getOrCreate(indexed));
  }

  @Test
  void resourceCacheStatisticsReadAdapterFields() {
    ResourceOnlyCacheAdapter.install(resource);
    ResourceCache<String, String> cache = new ResourceCache<>(resource, false);
    cache.set(KEY, VALUE);
    assertEquals(1, cache.getStatistics().getEntries());
  }
}
