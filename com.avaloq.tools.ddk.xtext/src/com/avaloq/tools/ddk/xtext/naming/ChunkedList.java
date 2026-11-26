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

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.concurrent.locks.StampedLock;
import java.util.function.ToIntFunction;


/**
 * A list which stores its elements in chunks of at most B = {@value #CHUNK_CAPACITY} elements. Inserting an element shifts the following elements of its
 * chunk and the start indexes of the following chunks, instead of all following elements as {@link ArrayList#add(int, Object)} does. Inserting into a list of
 * {@code n} elements thus takes O(B + n/B) instead of O(n) time, and building a large sorted list by binary search and insertion is no longer quadratic.
 * {@link #get(int)} takes O(log(n/B)) time.
 * <p>
 * Supports {@link #get(int)}, {@link #add(int, Object)}, {@link #size()}, {@link #clear()}, {@link #toArray()}, {@link #trimToSize()}, iteration, a
 * {@link #binarySearch(ToIntFunction) binary search} which is faster than one with {@link #get(int)} and {@link #copy(Object[], int) copying a range}, which
 * is faster than getting its elements one by one. Other modifications are not supported.
 * <p>
 * The list must be modified by one thread at a time. {@link #get(int)}, {@link #binarySearch(ToIntFunction)}, {@link #toArray()} and
 * {@link #copy(Object[], int)} may run concurrently with a modification. They then see the list before or after it, never a half-done modification, so that
 * they do not fail where reads of an {@link ArrayList} in practice do not fail either.
 *
 * @param <E>
 *          element type
 */
final class ChunkedList<E> extends AbstractList<E> implements RandomAccess {

  /** Maximum number of elements of a chunk. */
  static final int CHUNK_CAPACITY = 1024;

  private static final int HALF_CHUNK = CHUNK_CAPACITY / 2;

  /** Number of chunks there is room for beyond the initial ones. */
  private static final int SPARE_CHUNKS = 8;

  /** Number of reads without a lock before a read which a concurrent modification keeps invalidating takes the read lock and so blocks the writer. */
  private static final int OPTIMISTIC_READ_ATTEMPTS = 16;

  private final StampedLock lock = new StampedLock();

  /** The chunks; only the first {@link #chunkCount} are used. Each holds at least one element unless the list is empty. */
  private Object[][] chunks;

  /** The index in the list of the first element of each chunk. */
  private int[] starts;

  private int chunkCount;

  private int elementCount;

  /**
   * Creates a list with the elements of the given collection, in the order of its iterator.
   *
   * @param source
   *          the elements, must not be {@code null}
   */
  ChunkedList(final Collection<? extends E> source) {
    Object[] elements = source.toArray();
    int count = Math.max(1, (elements.length + CHUNK_CAPACITY - 1) / CHUNK_CAPACITY);
    // the list may be published without synchronization; since the lock is final, a reader sees it released and so the fields initialized
    long stamp = lock.writeLock();
    try {
      chunks = new Object[count + SPARE_CHUNKS][];
      starts = new int[count + SPARE_CHUNKS];
      for (int c = 0; c < count; c++) {
        int from = c * CHUNK_CAPACITY;
        chunks[c] = Arrays.copyOfRange(elements, from, Math.min(from + CHUNK_CAPACITY, elements.length), Object[].class);
        starts[c] = from;
      }
      chunkCount = count;
      elementCount = elements.length;
    } finally {
      lock.unlockWrite(stamp);
    }
  }

  @Override
  public int size() {
    return elementCount;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E get(final int index) {
    for (int attempt = 0; attempt < OPTIMISTIC_READ_ATTEMPTS; attempt++) {
      long stamp = lock.tryOptimisticRead();
      int currentSize = elementCount;
      Object element = find(index);
      if (lock.validate(stamp)) {
        Objects.checkIndex(index, currentSize);
        return (E) element;
      }
      Thread.onSpinWait();
    }
    long stamp = lock.readLock();
    try {
      Objects.checkIndex(index, elementCount);
      return (E) find(index);
    } finally {
      lock.unlockRead(stamp);
    }
  }

  /**
   * Returns the element at the given index. Since {@link #get(int)} calls this without a lock first, it must not fail on a state which a concurrent
   * modification left inconsistent.
   *
   * @param index
   *          the index of the element
   * @return the element, or any value if the state is inconsistent or the index out of range
   */
  private Object find(final int index) {
    Object[][] currentChunks = chunks;
    int[] currentStarts = starts;
    int high = Math.min(chunkCount, Math.min(currentChunks.length, currentStarts.length)) - 1;
    if (high < 0) {
      return null;
    }
    int low = 0;
    while (low < high) {
      int mid = (low + high + 1) >>> 1;
      if (currentStarts[mid] <= index) {
        low = mid;
      } else {
        high = mid - 1;
      }
    }
    Object[] chunk = currentChunks[low];
    int offset = index - currentStarts[low];
    return chunk != null && offset >= 0 && offset < chunk.length ? chunk[offset] : null;
  }

  /**
   * Searches this list, which must be sorted consistently with the given comparison, in O(log(n)) time like
   * {@link java.util.Collections#binarySearch(java.util.List, Object, java.util.Comparator)}. Unlike a search with {@link #get(int)}, it searches the chunks
   * first and then one chunk, instead of the chunks again for every element it compares.
   * <p>
   * The search reads without a lock first and validates once, at the end, that no modification ran meanwhile; else it searches again. So the comparison may
   * be called on an element which a concurrent {@link #add(int, Object)} is still publishing, and which it may see partly initialized. It must not fail then;
   * whatever it returns, the search discards the result.
   *
   * @param comparison
   *          compares an element with the searched key: negative if the element is smaller, zero if equal and positive if greater, must not be {@code null},
   *          and must not fail on an element seen partly initialized
   * @return index of an element equal to the key, or {@code (-(insertion point) - 1)}
   */
  int binarySearch(final ToIntFunction<? super E> comparison) {
    for (int attempt = 0; attempt < OPTIMISTIC_READ_ATTEMPTS; attempt++) {
      long stamp = lock.tryOptimisticRead();
      int result = search(comparison);
      if (lock.validate(stamp)) {
        return result;
      }
      Thread.onSpinWait();
    }
    long stamp = lock.readLock();
    try {
      return search(comparison);
    } finally {
      lock.unlockRead(stamp);
    }
  }

  /**
   * Implements {@link #binarySearch(ToIntFunction)}. Since that calls this without a lock first, it must not fail on a state which a concurrent modification
   * left inconsistent: it bounds every index by the arrays it reads and ends at a {@code null} element, which a consistent state does not have. It does not
   * validate the read before comparing an element, since a validation per comparison costs a load fence and a volatile read; the caller validates the read
   * once after the search and discards its result if a modification ran meanwhile.
   *
   * @param comparison
   *          compares an element with the searched key, must not be {@code null}
   * @return index of an element equal to the key, or {@code (-(insertion point) - 1)}, or any value if the state is inconsistent
   */
  @SuppressWarnings("unchecked")
  private int search(final ToIntFunction<? super E> comparison) {
    Object[][] currentChunks = chunks;
    int[] currentStarts = starts;
    int count = Math.min(chunkCount, Math.min(currentChunks.length, currentStarts.length));
    if (count == 0) {
      return -1;
    }
    // the last chunk whose first element is not greater than the key
    int low = 0;
    int high = count - 1;
    while (low < high) {
      int mid = (low + high + 1) >>> 1;
      Object[] chunk = currentChunks[mid];
      Object first = chunk != null && chunk.length > 0 ? chunk[0] : null;
      if (first == null) {
        return -1;
      }
      int cmp = comparison.applyAsInt((E) first);
      if (cmp < 0) {
        low = mid;
      } else if (cmp > 0) {
        high = mid - 1;
      } else {
        return currentStarts[mid];
      }
    }
    Object[] chunk = currentChunks[low];
    int start = currentStarts[low];
    int chunkSize = (low + 1 < count ? currentStarts[low + 1] : elementCount) - start;
    if (chunk == null || chunkSize < 0 || chunkSize > chunk.length) {
      return -1;
    }
    int lowIdx = 0;
    int highIdx = chunkSize - 1;
    while (lowIdx <= highIdx) {
      int mid = (lowIdx + highIdx) >>> 1;
      Object element = chunk[mid];
      if (element == null) {
        return -1;
      }
      int cmp = comparison.applyAsInt((E) element);
      if (cmp < 0) {
        lowIdx = mid + 1;
      } else if (cmp > 0) {
        highIdx = mid - 1;
      } else {
        return start + mid;
      }
    }
    return -(start + lowIdx + 1);
  }

  @Override
  public void add(final int index, final E element) {
    if (index < 0 || index > elementCount) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elementCount); //$NON-NLS-1$ //$NON-NLS-2$
    }
    long stamp = lock.writeLock();
    try {
      int c = index == elementCount ? chunkCount - 1 : chunkIndex(index);
      int offset = index - starts[c];
      int chunkSize = chunkSize(c);
      if (chunkSize == chunks[c].length) {
        if (chunkSize < CHUNK_CAPACITY) {
          chunks[c] = Arrays.copyOf(chunks[c], CHUNK_CAPACITY);
        } else {
          split(c);
          if (offset > HALF_CHUNK) {
            c++;
            offset -= HALF_CHUNK;
          }
          chunkSize = chunkSize(c);
        }
      }
      Object[] chunk = chunks[c];
      System.arraycopy(chunk, offset, chunk, offset + 1, chunkSize - offset);
      chunk[offset] = element;
      for (int i = c + 1; i < chunkCount; i++) {
        starts[i]++;
      }
      elementCount++;
      modCount++;
    } finally {
      lock.unlockWrite(stamp);
    }
  }

  @Override
  public void clear() {
    long stamp = lock.writeLock();
    try {
      chunks = new Object[1 + SPARE_CHUNKS][];
      chunks[0] = new Object[0];
      starts = new int[1 + SPARE_CHUNKS];
      chunkCount = 1;
      elementCount = 0;
      modCount++;
    } finally {
      lock.unlockWrite(stamp);
    }
  }

  @Override
  public Object[] toArray() {
    long stamp = lock.readLock();
    try {
      Object[] result = new Object[elementCount];
      for (int c = 0; c < chunkCount; c++) {
        System.arraycopy(chunks[c], 0, result, starts[c], chunkSize(c));
      }
      return result;
    } finally {
      lock.unlockRead(stamp);
    }
  }

  /**
   * Copies the elements from the given index on into the given array, as many as it holds and there are. Copies nothing if the index is not below the size,
   * so that a reader which advances its index across a {@link #clear()} ends there.
   *
   * @param target
   *          the array to copy into, from its start, must not be {@code null}
   * @param from
   *          the index of the first element to copy, must not be negative
   * @return the number of elements copied
   * @throws IndexOutOfBoundsException
   *           if the index is negative
   */
  int copy(final Object[] target, final int from) {
    if (from < 0) {
      throw new IndexOutOfBoundsException(from);
    }
    long stamp = lock.readLock();
    try {
      int count = Math.max(0, Math.min(target.length, elementCount - from));
      int copied = 0;
      int c = count > 0 ? chunkIndex(from) : 0;
      while (copied < count) {
        int offset = from + copied - starts[c];
        int length = Math.min(chunkSize(c) - offset, count - copied);
        System.arraycopy(chunks[c], offset, target, copied, length);
        copied += length;
        c++;
      }
      return count;
    } finally {
      lock.unlockRead(stamp);
    }
  }

  /**
   * Trims every chunk to its elements; this counts as a modification. A later insertion into a full chunk which is smaller than {@value #CHUNK_CAPACITY}
   * grows it to that size again.
   */
  void trimToSize() {
    long stamp = lock.writeLock();
    try {
      for (int c = 0; c < chunkCount; c++) {
        int chunkSize = chunkSize(c);
        if (chunks[c].length > chunkSize) {
          chunks[c] = Arrays.copyOf(chunks[c], chunkSize);
        }
      }
    } finally {
      lock.unlockWrite(stamp);
    }
  }

  /**
   * Returns the index of the chunk holding the element at the given index.
   *
   * @param index
   *          the index of an element, {@code 0 <= index < size()}
   * @return the index of the chunk
   */
  private int chunkIndex(final int index) {
    int low = 0;
    int high = chunkCount - 1;
    while (low < high) {
      int mid = (low + high + 1) >>> 1;
      if (starts[mid] <= index) {
        low = mid;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }

  private int chunkSize(final int c) {
    return (c + 1 < chunkCount ? starts[c + 1] : elementCount) - starts[c];
  }

  /**
   * Splits the given full chunk into two chunks of half its size.
   *
   * @param c
   *          the index of a chunk holding {@value #CHUNK_CAPACITY} elements
   */
  private void split(final int c) {
    if (chunkCount == chunks.length) {
      int newLength = chunkCount + (chunkCount >> 1) + 1;
      chunks = Arrays.copyOf(chunks, newLength);
      starts = Arrays.copyOf(starts, newLength);
    }
    System.arraycopy(chunks, c + 1, chunks, c + 2, chunkCount - c - 1);
    System.arraycopy(starts, c + 1, starts, c + 2, chunkCount - c - 1);
    Object[] left = chunks[c];
    Object[] right = new Object[CHUNK_CAPACITY];
    System.arraycopy(left, HALF_CHUNK, right, 0, CHUNK_CAPACITY - HALF_CHUNK);
    Arrays.fill(left, HALF_CHUNK, CHUNK_CAPACITY, null);
    chunks[c + 1] = right;
    starts[c + 1] = starts[c] + HALF_CHUNK;
    chunkCount++;
  }

}
