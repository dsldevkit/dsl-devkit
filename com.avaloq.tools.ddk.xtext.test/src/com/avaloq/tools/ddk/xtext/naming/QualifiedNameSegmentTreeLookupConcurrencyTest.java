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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

  /** More children than the smallest large node has, so that the nodes are large before the readers start. */
  private static final int LARGE = 4200;
  private static final int TRAVERSAL_ROUNDS = 5;
  /** A lookup throws if a clear lands in the short window between searching a child and getting it; few clears with long reads in between keep that rare. */
  private static final int CLEARS = 2;
  private static final int CLEARED = 20_000;

  private static final String CHILD = "c";
  private static final String SHARED = "shared";
  private static final String NESTED = "big";
  private static final String CLEARED_PREFIX = "k";

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
   * have no child {@value #CHILD} and so no values, and, exactly and by recursive pattern, the prefilled names before the insertion point.
   */
  @Test
  public void testLookupsWhileNodesBecomeLarge() throws InterruptedException {
    String[] inserted = insertedNames();
    for (int round = 0; round < ROUNDS; round++) {
      QualifiedNameLookup<String> lookup = new QualifiedNameSegmentTreeLookup<>(String.class, round % 2 == 0);
      for (int i = 0; i < PREFILLED; i++) {
        put(lookup, QualifiedName.create(prefilledName(i), CHILD));
        put(lookup, QualifiedName.create(NESTED, prefilledName(i), CHILD));
      }
      readDuring(INSERTS, (random, done) -> {
        String recent = inserted[Math.max(0, done - random.nextInt(RECENT))];
        switch (random.nextInt(5)) {
        case 0 -> lookup.get(QualifiedName.create(recent, CHILD));
        case 1 -> lookup.get(QualifiedName.create(NESTED, recent, CHILD));
        case 2 -> lookup.get(QualifiedNamePattern.create(recent.substring(0, recent.length() - 1) + '*'), false);
        case 3 -> {
          // before the insertion point, so no inserted name matches
          String kept = prefilledName(random.nextInt(INSERTION_POINT));
          assertEquals(List.of(kept + '.' + CHILD), lookup.get(QualifiedNamePattern.create(kept + "**"), false), "values matching a recursive pattern");
          assertEquals(List.of(NESTED + '.' + kept + '.' + CHILD), lookup.get(QualifiedNamePattern.create(NESTED, kept + "**"), false),
              "values matching a nested recursive pattern");
        }
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
   * Every chunk of a large root is split once, in ascending order, while readers look up, exactly and by recursive pattern, names of the upper half of the
   * chunk being split which sort before the inserted name. The assertions do not depend on the size of a chunk, only how likely the readers are to search a chunk during its split.
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
      readDuring(CHUNKS, (random, done) -> {
        QualifiedName name = names[done * CHUNK_SIZE + CHUNK_SIZE / 2 + random.nextInt(CHUNK_SIZE / 4)];
        assertFound(lookup, name);
        // reads the children from the name on in one go
        assertEquals(List.of(name.toString()), lookup.get(QualifiedNamePattern.create(name.getFirstSegment() + "**"), false), "values matching a pattern");
      },
          // after the names which the readers look up
          i -> put(lookup, QualifiedName.create(names[i * CHUNK_SIZE + CHUNK_SIZE * 3 / 4].getFirstSegment() + '_')));

      for (QualifiedName name : names) {
        assertFound(lookup, name);
      }
    }
  }

  /**
   * The root and a node below it, both large from the start, grow while readers visit all their children: to find the names mapped to a value, and to match
   * a recursive pattern. Such a traversal works on the children which a large node has at one moment, so it does not fail, finds every name which was
   * there before, and may or may not find a name inserted meanwhile. (Iterating over the children of a small node during an insertion fails.)
   */
  @Test
  public void testTraversalsWhileLargeNodesGrow() throws InterruptedException {
    String[] inserted = insertedNames();
    for (int round = 0; round < TRAVERSAL_ROUNDS; round++) {
      QualifiedNameLookup<String> lookup = new QualifiedNameSegmentTreeLookup<>(String.class, round % 2 == 0);
      List<String> nested = new ArrayList<>();
      for (int i = 0; i < LARGE; i++) {
        put(lookup, QualifiedName.create(prefilledName(i), CHILD));
        QualifiedName name = QualifiedName.create(NESTED, prefilledName(i), CHILD);
        put(lookup, name);
        nested.add(name.toString());
      }
      // before and after the insertion point
      Set<QualifiedName> shared = Set.of(QualifiedName.create(prefilledName(1), CHILD), QualifiedName.create(prefilledName(LARGE - 1), CHILD), QualifiedName.create(NESTED, prefilledName(2), CHILD), QualifiedName.create(NESTED, prefilledName(LARGE - 2), CHILD));
      for (QualifiedName name : shared) {
        lookup.put(name, SHARED);
      }
      // matches the node "big" and all below it
      QualifiedNamePattern pattern = QualifiedNamePattern.create(NESTED.substring(0, 2) + "**");
      readDuring(INSERTS, (random, done) -> {
        if (random.nextBoolean()) {
          assertEquals(shared, Set.copyOf(lookup.getMappings(SHARED)), "names mapped to a value");
        } else {
          Collection<String> values = lookup.get(pattern, true);
          assertTrue(values.containsAll(nested), "values of the names which were there before");
        }
      }, i -> {
        put(lookup, QualifiedName.create(inserted[i]));
        put(lookup, QualifiedName.create(NESTED, inserted[i]));
      });

      assertEquals(shared, Set.copyOf(lookup.getMappings(SHARED)), "names mapped to a value after the insertions");
      assertEquals(nested.size() + INSERTS + 1, lookup.get(pattern, true).size(), "values below the nested node");
    }
  }

  /**
   * A large root is cleared and refilled while readers match a pattern which reads all its children. A read across a clear may return any part of the names,
   * but it does not fail.
   */
  @Test
  public void testPatternLookupsWhileLargeNodeIsCleared() throws InterruptedException {
    QualifiedNameLookup<String> lookup = new QualifiedNameSegmentTreeLookup<>(String.class, false);
    IntConsumer fill = i -> {
      for (int n = 0; n < CLEARED; n++) {
        put(lookup, QualifiedName.create(String.format("%s%05d", CLEARED_PREFIX, n)));
      }
    };
    // a large node stays large when it is cleared
    fill.accept(0);
    QualifiedNamePattern pattern = QualifiedNamePattern.create(CLEARED_PREFIX + "**");
    readDuring(CLEARS, (random, done) -> lookup.get(pattern, false), i -> {
      lookup.clear();
      fill.accept(i);
    });

    assertEquals(CLEARED, lookup.get(pattern, false).size(), "values matching the pattern after the last refill");
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

  /** The names to insert: all between two prefilled names, in ascending order. */
  private static String[] insertedNames() {
    String[] inserted = new String[INSERTS];
    for (int i = 0; i < INSERTS; i++) {
      inserted[i] = String.format("%s_%07d", prefilledName(INSERTION_POINT), i);
    }
    return inserted;
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
