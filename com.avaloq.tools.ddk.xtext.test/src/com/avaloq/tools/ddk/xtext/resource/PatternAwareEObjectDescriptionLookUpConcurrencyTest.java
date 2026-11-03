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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNameLookup;
import com.google.common.collect.ImmutableList;


/**
 * Tests the lazy initialization of the name lookup of {@link PatternAwareEObjectDescriptionLookUp} under concurrent access.
 */
@SuppressWarnings({"PMD.JUnitAssertionsShouldIncludeMessage", "nls"})
public class PatternAwareEObjectDescriptionLookUpConcurrencyTest {

  private static final long TIMEOUT_SECONDS = 30;
  private static final String FOO_NAME = "Foo";
  private static final IEObjectDescription FOO = description(FOO_NAME);

  /**
   * Look-up which counts how often the name lookup is built and blocks the first build until released.
   */
  private static final class BlockingLookUp extends PatternAwareEObjectDescriptionLookUp {
    private final AtomicInteger builds = new AtomicInteger();
    private final CountDownLatch firstBuildStarted = new CountDownLatch(1);
    private final CountDownLatch releaseFirstBuild = new CountDownLatch(1);

    BlockingLookUp(final List<IEObjectDescription> exportedObjects) {
      super(exportedObjects);
    }

    @Override
    public Iterable<IEObjectDescription> getExportedObjects() {
      if (builds.incrementAndGet() == 1) {
        firstBuildStarted.countDown();
        try {
          releaseFirstBuild.await(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      return super.getExportedObjects();
    }

    QualifiedNameLookup<IEObjectDescription> lookup() {
      return getNameToObjectsLookup();
    }
  }

  @Test
  public void testSingleThreadedAccessReturnsSameLookup() {
    BlockingLookUp lookUp = new BlockingLookUp(List.of(FOO));
    lookUp.releaseFirstBuild.countDown();
    QualifiedNameLookup<IEObjectDescription> first = lookUp.lookup();
    assertSame(first, lookUp.lookup());
    assertEquals(1, lookUp.builds.get());
  }

  @Test
  public void testConcurrentFirstAccessBuildsLookupOnce() throws InterruptedException {
    BlockingLookUp lookUp = new BlockingLookUp(List.of(FOO, description("Bar")));
    AtomicReference<QualifiedNameLookup<IEObjectDescription>> firstResult = new AtomicReference<>();
    AtomicReference<QualifiedNameLookup<IEObjectDescription>> secondResult = new AtomicReference<>();
    Thread first = new Thread(() -> firstResult.set(lookUp.lookup()));
    Thread second = new Thread(() -> secondResult.set(lookUp.lookup()));

    first.start();
    assertTrue(lookUp.firstBuildStarted.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
    second.start();
    // the second thread has seen no lookup yet and waits for the lock held by the first thread
    long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(TIMEOUT_SECONDS);
    while (second.getState() != Thread.State.BLOCKED && second.isAlive() && System.nanoTime() < deadline) {
      Thread.onSpinWait();
    }
    lookUp.releaseFirstBuild.countDown();
    first.join(TimeUnit.SECONDS.toMillis(TIMEOUT_SECONDS));
    second.join(TimeUnit.SECONDS.toMillis(TIMEOUT_SECONDS));

    assertEquals(1, lookUp.builds.get());
    assertSame(firstResult.get(), secondResult.get());
    assertEquals(List.of(FOO), ImmutableList.copyOf(lookUp.getExportedObjects(EcorePackage.Literals.ECLASS, QualifiedName.create(FOO_NAME), false)));
  }

  private static IEObjectDescription description(final String name) {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName(name);
    return EObjectDescription.create(QualifiedName.create(name), eClass);
  }
}
