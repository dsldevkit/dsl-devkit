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
package com.avaloq.tools.ddk.xtext.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.AbstractEObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;


@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
public class ResourceDescriptionDeltaTest {

  @Test
  public void testGetAddedAndDeletedObjects() {
    ResourceDescriptionDelta delta = new ResourceDescriptionDelta(createDescription("a"), createDescription("a"), null);
    assertDeltaEquals(0, 0, 0, delta);

    delta = new ResourceDescriptionDelta(createDescription("a", "b"), createDescription("a", "b"), null);
    assertDeltaEquals(0, 0, 0, delta);

    delta = new ResourceDescriptionDelta(createDescription("a", "b"), createDescription("c", "d"), null);
    assertDeltaEquals(2, 0, 2, delta);

    delta = new ResourceDescriptionDelta(createDescription("a"), createDescription("a", "b"), null);
    assertDeltaEquals(1, 0, 0, delta);

    delta = new ResourceDescriptionDelta(createDescription("a", "b", "d"), createDescription("a", "c"), null);
    assertDeltaEquals(1, 0, 2, delta);

    delta = new ResourceDescriptionDelta(createDescription("a", "b"), createDescription(), null);
    assertDeltaEquals(0, 0, 2, delta);
  }

  @Test
  public void testGetChangedObjects() {
    ResourceDescriptionDelta delta = new ResourceDescriptionDelta(createDescription("a:a"), createDescription("a:a1"), null);
    assertDeltaEquals(0, 1, 0, delta);

    delta = new ResourceDescriptionDelta(createDescription("a:a", "b:b"), createDescription("a:a1"), null);
    assertDeltaEquals(0, 1, 1, delta);

    delta = new ResourceDescriptionDelta(createDescription("a:a", "b:b"), createDescription("a:a1", "c:c1"), null);
    assertDeltaEquals(1, 1, 1, delta);
  }

  @Test
  public void testDeltaForNewResource() {
    ResourceDescriptionDelta delta = new ResourceDescriptionDelta(null, createDescription("a"), null);
    assertDeltaEquals(1, 0, 0, delta);
  }

  @Test
  public void testDeltaForDeletedResource() {
    ResourceDescriptionDelta delta = new ResourceDescriptionDelta(createDescription("a"), null, null);
    assertDeltaEquals(0, 0, 1, delta);
  }

  @Test
  public void testOldReconstruction() {
    IResourceDescription oldRes = createDescription("a");

    ResourceDescriptionDelta delta = new ResourceDescriptionDelta(oldRes, createDescription("a"), null);
    assertFalse(delta.haveEObjectDescriptionsChanged());
    assertSame(delta.getNew(), delta.getOld());

    delta = new ResourceDescriptionDelta(oldRes, createDescription("a"), null);
    assertDeltaEquals(0, 0, 0, delta);
    assertSame(delta.getNew(), delta.getOld());

    IResourceDescription newRes = createDescription("a", "b");
    delta = new ResourceDescriptionDelta(oldRes, newRes, null);
    assertTrue(delta.haveEObjectDescriptionsChanged());
    assertDeltaEquals(1, 0, 0, delta);
    assertDeltaEquals(0, 0, 0, new ResourceDescriptionDelta(oldRes, delta.getOld(), null));

    delta = new ResourceDescriptionDelta(newRes, oldRes, null);
    assertTrue(delta.haveEObjectDescriptionsChanged());
    assertDeltaEquals(0, 0, 1, delta);
    assertDeltaEquals(0, 0, 0, new ResourceDescriptionDelta(newRes, delta.getOld(), null));
  }

  private void assertDeltaEquals(final int expectedAddedCount, final int expectedChangedCount, final int expectedDeletedCount, final ResourceDescriptionDelta delta) {
    assertEquals(expectedAddedCount, delta.getAddedObjects().size());
    assertEquals(expectedChangedCount, delta.getChangedObjects().size());
    assertEquals(expectedDeletedCount, delta.getDeletedObjects().size());
  }

  private IResourceDescription createDescription(final String... fragments) {
    return createDescriptionWithFingerprints(fragments);
  }

  @SuppressWarnings("PMD.CyclomaticComplexity")
  private IResourceDescription createDescriptionWithFingerprints(final String... fragmentsAndFingerprints) {
    final URI resourceURI = URI.createURI("foo:/foo.foo");
    return new AbstractResourceDescription() {

      @Override
      public Iterable<QualifiedName> getImportedNames() {
        return ImmutableSet.of();
      }

      @Override
      public Iterable<IReferenceDescription> getReferenceDescriptions() {
        return ImmutableSet.of();
      }

      @Override
      public URI getURI() {
        return resourceURI;
      }

      @Override
      protected List<IEObjectDescription> computeExportedObjects() {
        return Lists.transform(Lists.newArrayList(fragmentsAndFingerprints), new Function<String, IEObjectDescription>() {
          @Override
          public IEObjectDescription apply(final String from) {
            final String fragment = from.split(":")[0];
            final String fingerprint = from.indexOf(':') != -1 ? from.split(":")[1] : from;
            return new AbstractEObjectDescription() {

              @Override
              public QualifiedName getQualifiedName() {
                return QualifiedName.create(fragment);
              }

              @Override
              public QualifiedName getName() {
                return getQualifiedName();
              }

              @Override
              public URI getEObjectURI() {
                return resourceURI.appendFragment(fragment);
              }

              @Override
              public EObject getEObjectOrProxy() {
                return null;
              }

              @Override
              public EClass getEClass() {
                return null;
              }

              @Override
              public String[] getUserDataKeys() {
                return new String[] {IFingerprintComputer.OBJECT_FINGERPRINT};
              }

              @Override
              public String getUserData(final String name) {
                return name.equals(IFingerprintComputer.OBJECT_FINGERPRINT) ? Integer.toString(fingerprint.hashCode()) : null; // NOPMD
              }
            };
          }
        });
      }
    };
  }

}
