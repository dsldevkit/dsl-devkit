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
package com.avaloq.tools.ddk.xtext.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Tests {@link MonitoredClusteringBuilderState#defaultBinaryStorageQueueCapacity(long)}.
 */
public class BinaryStorageQueueCapacityTest {

  private static final long MB = 1024L * 1024;

  @Test
  public void testCapacityIsOneEntryPerTwoMegabytesOfHeap() {
    assertEquals(2304, MonitoredClusteringBuilderState.defaultBinaryStorageQueueCapacity(4608 * MB));
    assertEquals(12_800, MonitoredClusteringBuilderState.defaultBinaryStorageQueueCapacity(25_600 * MB));
  }

  @Test
  public void testCapacityHasLowerBound() {
    assertEquals(100, MonitoredClusteringBuilderState.defaultBinaryStorageQueueCapacity(128 * MB));
  }

  @Test
  public void testCapacityHasUpperBound() {
    assertEquals(15_000, MonitoredClusteringBuilderState.defaultBinaryStorageQueueCapacity(32_768 * MB));
    assertEquals(15_000, MonitoredClusteringBuilderState.defaultBinaryStorageQueueCapacity(Long.MAX_VALUE));
  }

}
