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
package com.avaloq.tools.ddk.xtext.naming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

import org.eclipse.xtext.naming.QualifiedName;
import org.junit.jupiter.api.Test;


/**
 * Tests lookups in a {@link QualifiedNameSegmentTreeLookup} while another thread inserts into nodes with many children.
 * <p>
 * A lookup during an insertion is not exact in general: it searches a child and then gets it by its index, and a child inserted in between shifts the
 * children after it. The tests assert what holds nevertheless: no lookup fails, and a name which sorts before all names inserted later, and so keeps its
 * index, is found with its own values.
 */
@SuppressWarnings("nls")
public class QualifiedNameSegmentTreeLookupConcurrencyTest {

  private static final long TIMEOUT_SECONDS = 60;
  private static final int READERS = 4;

  /** A node keeps 4096 or more children in chunks; the prefilled nodes have a few children less. */
  private static final int PREFILLED = 4088;
  /** The prefilled name after which the first test inserts. */
  private static final int INSERTION_POINT = 2000;
  private static final int ROUNDS = 10;
  private static final int INSERTS = 20_000;
  /** Readers look up one of this many names inserted last, which are in the chunks being split. */
  private static final int RECENT = 1100;

  /** The size of a chunk; the nodes of a lookup read from its serialized form have full chunks, which the first insertion splits. */
  private static final int CHUNK_SIZE = 1024;
  private static final int CHUNKS = 64;
  private static final int SPLIT_ROUNDS = 20;

  private static final String CHILD = "c";
  private static final String NESTED = "big";

  /**
   * A read done repeatedly by every reader thread.
   */
  @FunctionalInterface
  private interface Read {
    /**
     * Reads from the lookup under test.
     *
     * @param random
     *          the random numbers of the reader thread, never {@code null}
     * @param inserted
     *          the number of insertions done so far; the next one may be under way
     */
    void read(Random random, int inserted);
  }

  /**
   * The root and a node below it grow from just below the size of a large node while readers look up, exactly and by pattern, the names inserted last, which
   * have no child {@value #CHILD} and so no values, and the prefilled names before the insertion point.
   */
  @Test
  public void testLookupsWhileNodesBecomeLarge() throws InterruptedException {
    String[] inserted = new String[INSERTS];
    for (int i = 0; i < INSERTS; i++) {
      // all between two prefilled names, in ascending order
      inserted[i] = String.format("%s_%07d", prefilledName(INSERTION_POINT), i);
    }
    for (int round = 0; round < ROUNDS; round++) {
      QualifiedNameLookup<String> lookup = new QualifiedNameSegmentTreeLookup<>(String.class, round % 2 == 0);
      for (int i = 0; i < PREFILLED; i++) {
        put(lookup, QualifiedName.create(prefilledName(i), CHILD));
        put(lookup, QualifiedName.create(NESTED, prefilledName(i), CHILD));
      }
      readDuring(INSERTS, (random, done) -> {
        String recent = inserted[Math.max(0, done - random.nextInt(RECENT))];
        switch (random.nextInt(4)) {
        case 0 -> lookup.get(QualifiedName.create(recent, CHILD));
        case 1 -> lookup.get(QualifiedName.create(NESTED, recent, CHILD));
        case 2 -> lookup.get(QualifiedNamePattern.create(recent.substring(0, recent.length() - 1) + '*'), false);
        default -> {
          String kept = prefilledName(random.nextInt(INSERTION_POINT + 1));
          assertFound(lookup, QualifiedName.create(kept, CHILD));
          assertFound(lookup, QualifiedName.create(NESTED, kept, CHILD));
        }
        }
      }, i -> {
        put(lookup, QualifiedName.create(inserted[i]));
        put(lookup, QualifiedName.create(NESTED, inserted[i]));
      });

      for (int i = 0; i < PREFILLED; i++) {
        assertFound(lookup, QualifiedName.create(prefilledName(i), CHILD));
        assertFound(lookup, QualifiedName.create(NESTED, prefilledName(i), CHILD));
      }
      for (String name : inserted) {
        assertFound(lookup, QualifiedName.create(name));
        assertFound(lookup, QualifiedName.create(NESTED, name));
      }
    }
  }

  /**
   * Every chunk of a large root is split once, in ascending order, while readers look up names of the upper half of the chunk being split which sort before
   * the inserted name. The assertions do not depend on the size of a chunk, only how likely the readers are to search a chunk during its split.
   */
  @Test
  public void testExactLookupsWhileChunksSplit() throws InterruptedException, IOException, ClassNotFoundException {
    QualifiedName[] names = new QualifiedName[CHUNKS * CHUNK_SIZE];
    QualifiedNameLookup<String> source = new QualifiedNameSegmentTreeLookup<>(String.class, false);
    for (int i = 0; i < names.length; i++) {
      names[i] = QualifiedName.create(String.format("s%07d", i));
      put(source, names[i]);
    }
    ByteArrayOutputStream serialized = new ByteArrayOutputStream();
    try (ObjectOutputStream out = new ObjectOutputStream(serialized)) {
      out.writeObject(source);
    }
    for (int round = 0; round < SPLIT_ROUNDS; round++) {
      QualifiedNameLookup<String> lookup = read(serialized.toByteArray());
      readDuring(CHUNKS, (random, done) -> assertFound(lookup, names[done * CHUNK_SIZE + CHUNK_SIZE / 2 + random.nextInt(CHUNK_SIZE / 4)]),
          // after the names which the readers look up
          i -> put(lookup, QualifiedName.create(names[i * CHUNK_SIZE + CHUNK_SIZE * 3 / 4].getFirstSegment() + '_')));

      for (QualifiedName name : names) {
        assertFound(lookup, name);
      }
    }
  }

  /**
   * Runs the given read in {@value #READERS} reader threads while this thread does the given insertions, and fails if a read failed.
   *
   * @param inserts
   *          the number of insertions
   * @param read
   *          the read which the readers repeat until the last insertion is done, must not be {@code null}
   * @param insert
   *          does the insertion with the given index, must not be {@code null}
   */
  private static void readDuring(final int inserts, final Read read, final IntConsumer insert) throws InterruptedException {
    AtomicInteger inserted = new AtomicInteger();
    CountDownLatch reading = new CountDownLatch(READERS);
    List<Throwable> failures = Collections.synchronizedList(new ArrayList<>());
    List<Thread> readers = new ArrayList<>();
    for (int r = 0; r < READERS; r++) {
      Random random = new Random(r);
      Thread reader = new Thread(() -> {
        reading.countDown();
        for (int done = inserted.get(); done < inserts; done = inserted.get()) {
          read.read(random, done);
        }
      }, "reader-" + r);
      reader.setDaemon(true);
      reader.setUncaughtExceptionHandler((thread, failure) -> failures.add(failure));
      readers.add(reader);
      reader.start();
    }
    try {
      assertTrue(reading.await(TIMEOUT_SECONDS, TimeUnit.SECONDS), "readers started");
      for (int i = 0; i < inserts; i++) {
        insert.accept(i);
        inserted.set(i + 1);
      }
    } finally {
      // stops the readers
      inserted.set(inserts);
    }
    for (Thread reader : readers) {
      reader.join(TimeUnit.SECONDS.toMillis(TIMEOUT_SECONDS));
      assertFalse(reader.isAlive(), "reader finished");
    }
    if (!failures.isEmpty()) {
      fail(failures.size() + " of " + READERS + " readers failed during the insertions, the first with " + failures.get(0), failures.get(0));
    }
  }

  private static String prefilledName(final int i) {
    return String.format("m%04d", i);
  }

  /** Maps the given name to itself, as a string. */
  private static void put(final QualifiedNameLookup<String> lookup, final QualifiedName name) {
    lookup.put(name, name.toString());
  }

  private static void assertFound(final QualifiedNameLookup<String> lookup, final QualifiedName name) {
    assertEquals(List.of(name.toString()), lookup.get(name), "values of a name which keeps its index");
  }

  @SuppressWarnings("unchecked")
  private static QualifiedNameLookup<String> read(final byte[] serialized) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(serialized))) {
      return (QualifiedNameLookup<String>) in.readObject();
    }
  }

}
