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
import static org.junit.jupiter.api.Assertions.assertNull;
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
import java.util.LinkedHashSet;
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

  /** The number of names before those inserted by the test of searches in a growing node; only every other one is put. */
  private static final int LOWER = 12_000;
  private static final int SEARCH_ROUNDS = 5;
  /** The name of a growing node which sorts between the inserted ones. */
  private static final char ABSENT_SUFFIX = '~';
  private static final String NEVER_PUT_BETWEEN = "values of a name between inserted ones which was never put";

  /** The number of children of each small node in the trimming test. */
  private static final int TRIMMED_CHILDREN = 8;
  private static final int TRIMS = 50;

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
   * A large root grows by names in random order, which splits its chunks all over its upper part and shifts the chunks after them, while readers search it
   * for names below the inserted ones, which keep their index, and for names which are never inserted, below them and between them. A search does not fail,
   * finds a name below the inserted ones with its own values, and does not match a name which was never inserted.
   */
  @Test
  public void testSearchesWhileLargeNodeGrows() throws InterruptedException {
    for (int round = 0; round < SEARCH_ROUNDS; round++) {
      Random names = new Random(round);
      Set<String> unique = new LinkedHashSet<>();
      while (unique.size() < INSERTS) {
        // after all lower names, which start with p
        unique.add('q' + Integer.toString(names.nextInt(1_000_000_000), Character.MAX_RADIX));
      }
      String[] inserted = unique.toArray(new String[0]);
      QualifiedNameLookup<String> lookup = new QualifiedNameSegmentTreeLookup<>(String.class, round % 2 == 0);
      for (int i = 0; i < LOWER; i += 2) {
        put(lookup, QualifiedName.create(lowerName(i)));
      }
      readDuring(INSERTS, (random, done) -> {
        int i = random.nextInt(LOWER);
        QualifiedName lower = QualifiedName.create(lowerName(i));
        if (i % 2 == 0) {
          assertFound(lookup, lower);
        } else {
          assertNull(lookup.get(lower), "values of a lower name which was never put");
        }
        // sorts after the inserted name and before the next one, which a search may compare it with
        QualifiedName absent = QualifiedName.create(inserted[random.nextInt(INSERTS)] + ABSENT_SUFFIX);
        assertNull(lookup.get(absent), NEVER_PUT_BETWEEN);
      }, i -> put(lookup, QualifiedName.create(inserted[i])));

      for (int i = 0; i < LOWER; i += 2) {
        assertFound(lookup, QualifiedName.create(lowerName(i)));
      }
      for (String name : inserted) {
        assertFound(lookup, QualifiedName.create(name));
        assertNull(lookup.get(QualifiedName.create(name + ABSENT_SUFFIX)), NEVER_PUT_BETWEEN);
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
   * A lookup with small nodes of several children and a large root is trimmed repeatedly while readers match recursive patterns, which iterate over the
   * children of the small nodes, and look up names exactly. Trimming changes no mapping, so every read finds what was put before.
   */
  @Test
  public void testLookupsWhileTrimming() throws InterruptedException {
    QualifiedNameLookup<String> lookup = new QualifiedNameSegmentTreeLookup<>(String.class, false);
    for (int i = 0; i < LARGE; i++) {
      for (int c = 0; c < TRIMMED_CHILDREN; c++) {
        put(lookup, QualifiedName.create(prefilledName(i), CHILD + c));
      }
    }
    readDuring(TRIMS, (random, done) -> {
      String name = prefilledName(random.nextInt(LARGE));
      assertEquals(TRIMMED_CHILDREN, lookup.get(QualifiedNamePattern.create(name + "**"), false).size(), "values matching a recursive pattern while trimming");
      assertFound(lookup, QualifiedName.create(name, CHILD + random.nextInt(TRIMMED_CHILDREN)));
    }, i -> lookup.trimToSize());
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

  private static String lowerName(final int i) {
    return String.format("p%05d", i);
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
