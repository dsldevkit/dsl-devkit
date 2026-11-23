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
package com.avaloq.tools.ddk.xtext.linking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IFragmentProvider;

import com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer;
import com.google.common.base.CharMatcher;


/**
 * Fragment provider implementation using the same kind of compressed URI fragments as the <code>LazyURIEncoder</code>.
 */
public abstract class AbstractFragmentProvider implements IFragmentProvider {

  public static final char SEGMENT_SEPARATOR = '/';
  public static final char REP_SEPARATOR = '*';
  public static final char LIST_SEPARATOR = '#';

  private static final int FRAGMENT_BUFFER_CAPACITY = 8;
  private static final int DECIMAL_RADIX = 10;
  private static final char ESCAPE_CHARACTER = '\\';
  private static final CharMatcher CHARACTERS_TO_ESCAPE = CharMatcher.anyOf(new String(new char[] {SEGMENT_SEPARATOR, ESCAPE_CHARACTER,
      REP_SEPARATOR})).precomputed();
  private static final CharMatcher SEGMENT_SEPARATOR_MATCHER = CharMatcher.is(SEGMENT_SEPARATOR).precomputed();
  private static final CharMatcher END_MATCHER = CharMatcher.anyOf(new String(new char[] {SEGMENT_SEPARATOR, REP_SEPARATOR})).precomputed();

  /**
   * Helper object used to iterate over the segments in a URI fragment and extract the number of repetitions of the segments.
   */
  @SuppressWarnings("PMD.PublicMemberInNonPublicType") // Public methods needed for subclass access in other packages
  protected class FragmentSegmentIterator implements Iterator<String> {
    private final String fragment;
    private final int length;
    private int endIdx;
    private int reps;

    /**
     * Creates a new instance of {@link FragmentSegmentIterator}.
     *
     * @param fragment
     *          URI fragment to create iterator for, must not be {@code null}
     * @throws IllegalArgumentException
     *           if fragment does not start with segment separator
     */
    public FragmentSegmentIterator(final String fragment) {
      if (fragment.charAt(0) != SEGMENT_SEPARATOR) {
        throw new IllegalArgumentException("Fragment should start with the char '" + SEGMENT_SEPARATOR + "'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      this.fragment = fragment;
      this.length = fragment.length();
      this.endIdx = 0;
    }

    /**
     * Check if there are more segments which get returned by {@link #next()}.
     *
     * @return {@code true} if there are more segments to be parsed
     */
    @Override
    public boolean hasNext() {
      return endIdx < length;
    }

    /**
     * Returns the next segment in the URI fragment. This method should only be called after using {@link #hasNext()} to make sure there are more segments.
     *
     * @return the next segment
     */
    @Override
    public String next() { // NOPMD - this isn't actually complex, and I much prefer a ternary to a if-else
      int startIdx = endIdx + 1;
      int idx = indexOfUnescapedChar(fragment, END_MATCHER, startIdx);
      int repIdx = idx < length && fragment.charAt(idx) == REP_SEPARATOR ? idx : -1;
      endIdx = repIdx == -1 ? idx : indexOfUnescapedChar(fragment, SEGMENT_SEPARATOR_MATCHER, repIdx);
      reps = repIdx < startIdx ? 1 : parseUnsignedInt(fragment, repIdx + 1, endIdx);
      return fragment.substring(startIdx, reps == 1 ? endIdx : repIdx);
    }

    /**
     * Returns the number of repetitions of the last segment returned by {@link #nextSegment()}.
     *
     * @return number of repetitions
     */
    public int repetitions() {
      return reps;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * Computes the fragments of the objects visited by a traversal of a resource's contents in containment pre-order. The fragment of each object is the same as
   * the one returned by {@link AbstractFragmentProvider#getFragment(EObject, Fallback)}, but the segments of the ancestors it shares with the previously
   * requested object are not computed again. Objects may be requested in any order, but only containment pre-order avoids recomputing segments.
   * <p>
   * Instances are not thread-safe and must only be used while the containment of the visited objects does not change.
   * </p>
   */
  @SuppressWarnings("PMD.AvoidStringBufferField") // instances only live during one traversal
  public final class FragmentPath {
    private static final int INITIAL_DEPTH = 16;

    /** Objects of the current path, from the topmost container down. */
    private final List<EObject> objects = new ArrayList<>(INITIAL_DEPTH);
    /** Fragment segment of each object of the current path. */
    private final List<String> segments = new ArrayList<>(INITIAL_DEPTH);
    /** Repetitions of each level's segment ending at that level; the run starts at level {@code level - repetitions + 1}. */
    private int[] repetitions = new int[INITIAL_DEPTH];
    /** Length of {@link #closedRuns} before the run containing each level. */
    private int[] closedLengths = new int[INITIAL_DEPTH];
    /** Encoded fragment of the runs of the current path which are followed by a different segment. */
    private final StringBuilder closedRuns = new StringBuilder();
    private final StringBuilder segment = new StringBuilder(FRAGMENT_BUFFER_CAPACITY);
    private final List<EObject> containers = new ArrayList<>(INITIAL_DEPTH);

    private FragmentPath() {
      // created by AbstractFragmentProvider#createFragmentPath
    }

    /**
     * Returns the fragment of the given object.
     *
     * @param object
     *          the object, must not be {@code null}
     * @return the same fragment as {@link AbstractFragmentProvider#getFragment(EObject, Fallback)}, or {@code null} if the topmost container of
     *         {@code object} is not contained in a resource, in which case that method uses the fallback
     */
    public String getFragment(final EObject object) {
      final EObject container = object.eContainer();
      for (int level = objects.size() - 1; level >= 0; level--) {
        final EObject current = objects.get(level);
        if (current == object) {
          truncate(level + 1);
          return encode(level);
        } else if (current == container) {
          truncate(level + 1);
          push(object);
          return encode(level + 1);
        }
      }
      containers.clear();
      for (EObject current = object; current != null; current = current.eContainer()) {
        containers.add(current);
      }
      final int depth = containers.size();
      if (containers.get(depth - 1).eResource() == null) {
        return null;
      }
      int common = 0;
      final int maxCommon = Math.min(depth, objects.size());
      while (common < maxCommon && objects.get(common) == containers.get(depth - 1 - common)) {
        common++;
      }
      truncate(common);
      for (int index = depth - 1 - common; index >= 0; index--) {
        push(containers.get(index));
      }
      containers.clear();
      return encode(depth - 1);
    }

    private void truncate(final int depth) {
      for (int level = objects.size() - 1; level >= depth; level--) {
        objects.remove(level);
        segments.remove(level);
      }
      closedRuns.setLength(depth == 0 ? 0 : closedLengths[depth - 1]);
    }

    private void push(final EObject object) {
      final int level = objects.size();
      if (level == repetitions.length) {
        repetitions = Arrays.copyOf(repetitions, level * 2);
        closedLengths = Arrays.copyOf(closedLengths, level * 2);
      }
      segment.setLength(0);
      internalAppendFragmentSegment(object, segment);
      final String value = segment.toString();
      if (level > 0 && value.equals(segments.get(level - 1))) {
        repetitions[level] = repetitions[level - 1] + 1;
        closedLengths[level] = closedLengths[level - 1];
      } else {
        if (level > 0) {
          appendRun(closedRuns, segments.get(level - 1), repetitions[level - 1]);
        }
        repetitions[level] = 1;
        closedLengths[level] = closedRuns.length();
      }
      objects.add(object);
      segments.add(value);
    }

    private String encode(final int level) {
      final String last = segments.get(level);
      final StringBuilder result = new StringBuilder(closedLengths[level] + last.length() + FRAGMENT_BUFFER_CAPACITY);
      result.append(closedRuns, 0, closedLengths[level]);
      appendRun(result, last, repetitions[level]);
      return result.toString();
    }
  }

  /**
   * Creates a {@link FragmentPath} computing the fragments of this provider for the objects of a traversal.
   *
   * @return new fragment path, never {@code null}
   */
  public FragmentPath createFragmentPath() {
    return new FragmentPath();
  }

  /**
   * Appends a run of equal segments in the format used by {@link #getFragment(EObject, Fallback)}.
   *
   * @param builder
   *          builder to append to, must not be {@code null}
   * @param segment
   *          the repeated segment, must not be {@code null}
   * @param reps
   *          number of repetitions, at least 1
   */
  private static void appendRun(final StringBuilder builder, final CharSequence segment, final int reps) {
    builder.append(SEGMENT_SEPARATOR).append(segment);
    if (reps == 2 && segment.length() == 1) {
      builder.append(SEGMENT_SEPARATOR).append(segment);
    } else if (reps > 1) {
      builder.append(REP_SEPARATOR).append(reps);
    }
  }

  @Override
  public String getFragment(final EObject object, final Fallback fallback) {
    final Deque<EObject> containingObjects = new ArrayDeque<>();
    EObject current = object;
    while (current != null) {
      containingObjects.push(current);
      current = current.eContainer();
    }
    if (containingObjects.peek().eResource() == null) {
      // could happen while unloading objects
      return fallback.getFragment(object);
    }
    final StringBuilder result = new StringBuilder(containingObjects.size() * FRAGMENT_BUFFER_CAPACITY);
    StringBuilder segment = new StringBuilder(FRAGMENT_BUFFER_CAPACITY);
    internalAppendFragmentSegment(containingObjects.pop(), segment);
    int reps = 1;
    result.append(SEGMENT_SEPARATOR);
    result.append(segment);
    StringBuilder previousSegment = new StringBuilder(FRAGMENT_BUFFER_CAPACITY);
    while (!containingObjects.isEmpty()) {
      StringBuilder temp = previousSegment;
      previousSegment = segment;
      segment = temp;
      segment.setLength(0);
      internalAppendFragmentSegment(containingObjects.pop(), segment);
      if (equal(previousSegment, segment)) {
        reps++;
      } else {
        if (reps == 2 && previousSegment.length() == 1) {
          result.append(SEGMENT_SEPARATOR).append(previousSegment);
          reps = 1;
        } else if (reps > 1) {
          result.append(REP_SEPARATOR).append(reps);
          reps = 1;
        }
        result.append(SEGMENT_SEPARATOR).append(segment);
      }
    }
    if (reps == 2 && previousSegment.length() == 1) {
      result.append(SEGMENT_SEPARATOR).append(previousSegment);
    } else if (reps > 1) {
      result.append(REP_SEPARATOR).append(reps);
    }
    return result.toString();
  }

  /**
   * Internal method which calls {@link InferenceContainer#getFragmentSegment(EObject)} if {@code object}'s container is an {@link InferenceContainer} and
   * otherwise calls {@link #appendFragmentSegment(EObject, StringBuilder)}.
   *
   * @param object
   *          the {@link EObject} for which to calculate the fragment segment, must not be {@code null}
   * @param builder
   *          builder to append fragment segment to, must not be {@code null}
   */
  private void internalAppendFragmentSegment(final EObject object, final StringBuilder builder) {
    if (object.eContainer() instanceof InferenceContainer) {
      builder.append(((InferenceContainer) object.eContainer()).getFragmentSegment(object));
    } else {
      appendFragmentSegment(object, builder);
    }
  }

  /**
   * Checks whether the two given {@link StringBuilder} objects represent the same string value.
   *
   * @param builder1
   *          first builder, must not be {@code null}
   * @param builder2
   *          second builder, must not be {@code null}
   * @return {@code true} if both represent the same string, {@code false} otherwise
   */
  private boolean equal(final StringBuilder builder1, final StringBuilder builder2) {
    int length = builder1.length();
    if (builder2.length() != length) {
      return false;
    }
    for (int i = 0; i < length; i++) {
      if (builder1.charAt(i) != builder2.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Parses the unsigned decimal integer in the given range of a URI fragment (segment) without creating a substring.
   *
   * @param text
   *          text containing the integer, must not be {@code null}
   * @param beginIndex
   *          index of the first character of the integer, inclusive
   * @param endIndex
   *          index after the last character of the integer, exclusive
   * @return the parsed integer
   * @throws NumberFormatException
   *           if the range does not contain a parsable unsigned integer
   * @throws IndexOutOfBoundsException
   *           if the range is not within {@code text}
   */
  protected static int parseUnsignedInt(final CharSequence text, final int beginIndex, final int endIndex) {
    return Integer.parseUnsignedInt(text, beginIndex, endIndex, DECIMAL_RADIX);
  }

  /**
   * Calculates and append the URI fragment segment for the given object to the given {@link StringBuilder}, without segment separators.
   *
   * @param object
   *          the {@link EObject} for which to calculate the fragment segment, must not be {@code null}
   * @param builder
   *          builder to append fragment segment to, must not be {@code null}
   * @return {@code true} if a fragment segment was appended to {@code builder}
   */
  public abstract boolean appendFragmentSegment(EObject object, StringBuilder builder);

  @Override
  public EObject getEObject(final Resource resource, final String fragment, final Fallback fallback) {
    try {
      FragmentSegmentIterator iterator = new FragmentSegmentIterator(fragment);
      String segment = iterator.next();
      int reps = iterator.repetitions();

      final int contentsIndex = Integer.parseUnsignedInt(segment);
      EObject container = resource.getContents().get(contentsIndex);

      while (iterator.hasNext() || reps > 1) {
        if (reps > 1) {
          reps--;
        } else {
          segment = iterator.next();
          reps = iterator.repetitions();
        }
        final EObject nextEObject = internalGetEObjectFromSegment(segment, container);
        if (nextEObject == null || nextEObject.equals(container)) {
          return null;
        }
        container = nextEObject;
      }
      return container;
    } catch (NumberFormatException e) {
      return fallback.getEObject(fragment);
    }
  }

  /**
   * Internal method which calls {@link InferenceContainer#getEObject(String)} if {@code container} is an {@link InferenceContainer} and otherwise calls
   * {@link #getEObjectFromSegment(EObject, String)}.
   *
   * @param container
   *          the container {@link EObject}, must not be {@code null}
   * @param segment
   *          the URI fragment segment, must not be {@code null}
   * @return the contained object, or {@code null} if none
   */
  private EObject internalGetEObjectFromSegment(final String segment, final EObject container) {
    if (container instanceof InferenceContainer) {
      return ((InferenceContainer) container).getEObject(segment);
    }
    return getEObjectFromSegment(container, segment);
  }

  /**
   * Retrieves the contained object given the container and segment of a URI fragment.
   *
   * @param container
   *          the container {@link EObject}, must not be {@code null}
   * @param segment
   *          the URI fragment segment, must not be {@code null}
   * @return the contained object, or {@code null} if none
   */
  public abstract EObject getEObjectFromSegment(EObject container, String segment);

  /**
   * Returns the index of the next unescaped occurrence matching {@code ch} in {@code str} (after {@code fromIndex}). If no such match exists the method must
   * return {@code str#length()}.
   *
   * @param str
   *          the string, must not be {@code null}
   * @param ch
   *          character match, must not be {@code null}
   * @param fromIndex
   *          index at which to start scanning for matches
   * @return index of the next occurrence in the given string, or {@code str#length()} if none can be found
   */
  protected int indexOfUnescapedChar(final String str, final CharMatcher ch, final int fromIndex) {
    boolean escaped = false;
    for (int index = fromIndex; index < str.length(); index++) {
      if (escaped) {
        escaped = false;
        continue;
      }
      char c = str.charAt(index);
      if (ch.matches(c)) {
        return index;
      } else if (c == ESCAPE_CHARACTER) {
        escaped = true;
      }
    }
    return str.length();
  }

  /**
   * Escapes the reserved characters contained in the given text and appends it to the given {@link StringBuilder}.
   * <p>
   * Reserved characters for e.g. segment and list separation cannot be used by custom fragment providers unless escaped, i.e. prefixed with a '\'. Such URI
   * segments need to be escaped when forming the URI fragment, and consequently unescaped when reading the URI segments.
   * </p>
   *
   * @param text
   *          the text to escape, must not be {@code null}
   * @param builder
   *          builder to append the escaped text to, must not be {@code null}
   */
  protected void appendEscaped(final String text, final StringBuilder builder) {
    appendEscaped(text, builder, CHARACTERS_TO_ESCAPE);
  }

  /**
   * Unescapes the reserved characters contained in the given text.
   * <p>
   * Reserved characters for e.g. segment and list separation cannot be used by custom fragment providers unless escaped, i.e. prefixed with a '\'. Such URI
   * segments need to be escaped when forming the URI fragment, and consequently unescaped when reading the URI segments.
   * </p>
   *
   * @param text
   *          the text to unescape, must not be {@code null}
   * @return the unescaped text, never {@code null}
   */
  protected String unescape(final String text) {
    return unescape(text, CHARACTERS_TO_ESCAPE);
  }

  /**
   * Escapes the escape characters contained in the given text and appends the result to the given {@link StringBuilder}.
   * <p>
   * Reserved characters for e.g. segment and list separation cannot be used by custom fragment providers unless escaped, i.e. prefixed with a '\'. Such URI
   * segments need to be escaped when forming the URI fragment, and consequently unescaped when reading the URI segments.
   * </p>
   *
   * @param text
   *          the text to escape, must not be {@code null}
   * @param builder
   *          builder to append the escaped text to, must not be {@code null}
   * @param charactersToEscape
   *          the characters to escape, must not be {@code null}
   */
  protected void appendEscaped(final String text, final StringBuilder builder, final CharMatcher charactersToEscape) {
    int lastIndex = 0;
    for (int index = 0; index < text.length(); index++) {
      char character = text.charAt(index);
      if (charactersToEscape.matches(character)) {
        builder.append(text.substring(lastIndex, index)).append(ESCAPE_CHARACTER).append(character);
        lastIndex = index + 1;
      }
    }
    builder.append(text.substring(lastIndex));
  }

  /**
   * Unescapes the given escape characters contained in the given text.
   * <p>
   * Reserved characters for e.g. segment and list separation cannot be used by custom fragment providers unless escaped, i.e. prefixed with a '\'. Such URI
   * segments need to be escaped when forming the URI fragment, and consequently unescaped when reading the URI segments.
   * </p>
   *
   * @param text
   *          the text to unescape, must not be {@code null}
   * @param charactersToEscape
   *          the characters to escape, must not be {@code null}
   * @return the unescaped text, never {@code null}
   */
  protected String unescape(final String text, final CharMatcher charactersToEscape) {
    StringBuilder result = null;
    int lastIndex = 0;
    boolean escaped = false;
    for (int index = 0; index < text.length(); index++) {
      char character = text.charAt(index);
      if (escaped) {
        escaped = false;
        if (charactersToEscape.matches(character)) {
          if (result == null) {
            result = new StringBuilder(text.length());
          }
          result.append(text.substring(lastIndex, index - 1)).append(character);
          lastIndex = index + 1;
        }
      } else if (character == ESCAPE_CHARACTER) {
        escaped = true;
      }
    }
    if (result == null) {
      return text;
    }
    result.append(text.substring(lastIndex));
    return result.toString();
  }
}
