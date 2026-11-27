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
package com.avaloq.tools.ddk.check.ui.test.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.BuildType;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.resource.persistence.SourceLevelURIsAdapter;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.ui.test.AbstractCheckTestCase;
import com.google.inject.Key;
import com.google.inject.name.Names;


/**
 * Tests the Check builder participant on catalogs that the builder reloads after clearing its resource set.
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "nls"})
@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckBuilderParticipantTest extends AbstractCheckTestCase {

  private static final String CATALOG_FILE = "src/storagereload/StorageReload.check";
  private static final String GENERATED_FILE = "src-gen/storagereload/StorageReloadCheckImpl.java";
  private static final String STORAGE_FILE = "src-gen/storagereload/.StorageReload.checkbin";
  private static final String CATALOG = """
      package storagereload

      import com.avaloq.tools.ddk.check.check.Check

      catalog StorageReload
      for grammar com.avaloq.tools.ddk.check.Check {

        live error SomeCheck "Some check"
        message "Some check." {
          for Check elem {
            issue on elem
          }
        }
      }
      """;

  private IFile catalog;

  /**
   * A catalog that is neither loaded nor a source-level URI, as after the builder cleared its resource set, is generated from its source.
   */
  @Test
  public void testCatalogNotInResourceSet() throws Exception {
    URI uri = buildCatalog();
    ResourceSet resourceSet = createBuildResourceSet();
    assertParticipantGeneratesFromSource(uri, resourceSet);
  }

  /**
   * A catalog that the build resource set already holds loaded from its binary storage is generated from its source.
   */
  @Test
  public void testCatalogLoadedFromStorage() throws Exception {
    URI uri = buildCatalog();
    ResourceSet resourceSet = createBuildResourceSet();
    assertTrue(((StorageAwareResource) resourceSet.getResource(uri, true)).isLoadedFromStorage(), "precondition: catalog is loaded from storage");
    assertParticipantGeneratesFromSource(uri, resourceSet);
  }

  private URI buildCatalog() throws Exception {
    catalog = createFile(CATALOG_FILE, CATALOG);
    IResourcesSetupUtil.waitForBuild();
    assertTrue(catalog.getProject().getFile(GENERATED_FILE).exists(), "full build generates " + GENERATED_FILE);
    assertTrue(catalog.getProject().getFile(STORAGE_FILE).exists(), "full build stores " + STORAGE_FILE);
    return URI.createPlatformResourceURI(catalog.getFullPath().toString(), true);
  }

  private ResourceSet createBuildResourceSet() {
    ResourceSet resourceSet = getInjector().getInstance(IResourceSetProvider.class).get(catalog.getProject());
    SourceLevelURIsAdapter.setSourceLevelUris(resourceSet, Collections.emptySet());
    return resourceSet;
  }

  private void assertParticipantGeneratesFromSource(final URI uri, final ResourceSet resourceSet) throws Exception {
    IProject project = catalog.getProject();
    IResourceDescription description = getInjector().getInstance(Key.get(IResourceDescriptions.class, Names.named(ResourceDescriptionsProvider.PERSISTED_DESCRIPTIONS))).getResourceDescription(uri);
    IBuildContext context = new TestBuildContext(project, resourceSet, List.of(new DefaultResourceDescriptionDelta(null, description)));
    IXtextBuilderParticipant participant = getInjector().getInstance(IXtextBuilderParticipant.class);
    ResourcesPlugin.getWorkspace().run(monitor -> participant.build(context, monitor), new NullProgressMonitor());

    assertFalse(((StorageAwareResource) resourceSet.getResource(uri, false)).isLoadedFromStorage(), "catalog is loaded from source");
    assertTrue(project.getFile(GENERATED_FILE).exists(), "participant keeps " + GENERATED_FILE);
    assertTrue(project.getFile(STORAGE_FILE).exists(), "participant keeps " + STORAGE_FILE);
    assertEquals(Collections.emptySet(), SourceLevelURIsAdapter.findInstalledAdapter(resourceSet).getSourceLevelURIs(), "source-level URIs are restored");
  }

  /**
   * Incremental build context over a fixed list of deltas.
   */
  private static final class TestBuildContext implements IBuildContext {
    private final IProject project;
    private final ResourceSet resourceSet;
    private final List<IResourceDescription.Delta> deltas;

    TestBuildContext(final IProject project, final ResourceSet resourceSet, final List<IResourceDescription.Delta> deltas) {
      this.project = project;
      this.resourceSet = resourceSet;
      this.deltas = deltas;
    }

    @Override
    public IProject getBuiltProject() {
      return project;
    }

    @Override
    public List<IResourceDescription.Delta> getDeltas() {
      return deltas;
    }

    @Override
    public boolean isSourceLevelURI(final URI uri) {
      return true;
    }

    @Override
    public ResourceSet getResourceSet() {
      return resourceSet;
    }

    @Override
    public BuildType getBuildType() {
      return BuildType.INCREMENTAL;
    }

    @Override
    public void needRebuild() {
      // a fixed list of deltas needs no rebuild
    }
  }
}
