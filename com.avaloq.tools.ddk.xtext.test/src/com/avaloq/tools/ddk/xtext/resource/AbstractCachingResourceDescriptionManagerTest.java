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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2.ReferenceMatchPolicy;


/**
 * Tests that {@link AbstractCachingResourceDescriptionManager#isReferencedBy} queries the index once per delta and index, not once per candidate.
 */
@SuppressWarnings("nls")
public class AbstractCachingResourceDescriptionManagerTest {

  private static final String CHANGED_URI = "foo:/changed.foo";

  private final TestManager manager = new TestManager();

  private final IResourceDescription referencingObject = description("foo:/referencingObject.foo");
  private final IResourceDescription referencingName = description("foo:/referencingName.foo");
  private final IResourceDescription unrelated = description("foo:/unrelated.foo");

  @Test
  public void testDetailedDeltaQueriesOncePerIndex() {
    Delta delta = new ResourceDescriptionDelta(description(CHANGED_URI, "a", "b:1"), description(CHANGED_URI, "b:2", "c"), null);
    IResourceDescriptions2 index = mock(IResourceDescriptions2.class);
    when(index.findExactReferencingResources(any(), eq(ReferenceMatchPolicy.REFERENCES))).thenReturn(List.of(referencingObject));
    when(index.findExactReferencingResources(any(), eq(ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES))).thenReturn(List.of(referencingName));

    assertTrue(manager.referencedBy(delta, referencingObject, index), "A resource referencing a changed or deleted object must be affected.");
    assertTrue(manager.referencedBy(delta, referencingName, index), "A resource importing the name of an added object must be affected.");
    assertFalse(manager.referencedBy(delta, unrelated, index), "A resource referencing nothing of the delta must not be affected.");

    verify(index).findExactReferencingResources(any(), eq(ReferenceMatchPolicy.REFERENCES));
    verify(index).findExactReferencingResources(any(), eq(ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES));
    verifyNoMoreInteractions(index);
  }

  @Test
  public void testResourceDeltaQueriesOncePerIndex() {
    IResourceDescription newDescription = description(CHANGED_URI, "a");
    Delta delta = new DefaultResourceDescriptionDelta(description(CHANGED_URI), newDescription);
    IResourceDescriptions2 index = mock(IResourceDescriptions2.class);
    when(index.findAllReferencingResources(Set.of(newDescription), ReferenceMatchPolicy.ALL)).thenReturn(List.of(referencingObject));

    assertTrue(manager.referencedBy(delta, referencingObject, index), "A resource referencing the changed resource must be affected.");
    assertFalse(manager.referencedBy(delta, unrelated, index), "A resource not referencing the changed resource must not be affected.");

    verify(index).findAllReferencingResources(Set.of(newDescription), ReferenceMatchPolicy.ALL);
    verifyNoMoreInteractions(index);
  }

  @Test
  public void testEachIndexIsQueriedSeparately() {
    IResourceDescription newDescription = description(CHANGED_URI, "a");
    Delta delta = new DefaultResourceDescriptionDelta(description(CHANGED_URI), newDescription);
    IResourceDescriptions2 index = mock(IResourceDescriptions2.class);
    when(index.findAllReferencingResources(Set.of(newDescription), ReferenceMatchPolicy.ALL)).thenReturn(List.of(referencingObject));
    IResourceDescriptions2 otherIndex = mock(IResourceDescriptions2.class);
    when(otherIndex.findAllReferencingResources(Set.of(newDescription), ReferenceMatchPolicy.ALL)).thenReturn(List.of());

    assertTrue(manager.referencedBy(delta, referencingObject, index), "The resource references the delta in the first index.");
    assertFalse(manager.referencedBy(delta, referencingObject, otherIndex), "The resource does not reference the delta in the other index.");

    verify(index).findAllReferencingResources(Set.of(newDescription), ReferenceMatchPolicy.ALL);
    verify(otherIndex).findAllReferencingResources(Set.of(newDescription), ReferenceMatchPolicy.ALL);
  }

  /**
   * Creates a resource description exporting objects with object fingerprints.
   *
   * @param uri
   *          the resource URI
   * @param objects
   *          the exported objects as {@code name} or {@code name:fingerprint}
   * @return the resource description
   */
  private static IResourceDescription description(final String uri, final String... objects) {
    final URI resourceURI = URI.createURI(uri);
    final List<IEObjectDescription> exported = Arrays.stream(objects).map(object -> {
      String[] nameAndFingerprint = object.split(":");
      String name = nameAndFingerprint[0];
      EObject proxy = EcoreFactory.eINSTANCE.createEObject();
      ((InternalEObject) proxy).eSetProxyURI(resourceURI.appendFragment(name));
      return EObjectDescription.create(QualifiedName.create(name), proxy, Map.of(IFingerprintComputer.OBJECT_FINGERPRINT, nameAndFingerprint[nameAndFingerprint.length - 1]));
    }).toList();
    return new AbstractResourceDescription() {
      @Override
      protected List<IEObjectDescription> computeExportedObjects() {
        return exported;
      }

      @Override
      public Iterable<QualifiedName> getImportedNames() {
        return List.of();
      }

      @Override
      public Iterable<IReferenceDescription> getReferenceDescriptions() {
        return List.of();
      }

      @Override
      public URI getURI() {
        return resourceURI;
      }
    };
  }

  /** Exposes {@link AbstractCachingResourceDescriptionManager#isReferencedBy} to the tests. */
  private static final class TestManager extends AbstractCachingResourceDescriptionManager {
    boolean referencedBy(final Delta delta, final IResourceDescription candidate, final IResourceDescriptions context) {
      return isReferencedBy(delta, candidate, context);
    }
  }

}
