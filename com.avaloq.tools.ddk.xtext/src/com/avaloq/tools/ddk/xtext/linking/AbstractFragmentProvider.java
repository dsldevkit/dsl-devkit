/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.linking;

import java.util.Iterator;
import java.util.Stack;

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

  private static final char ESCAPE_CHARACTER = '\\';
  private static final CharMatcher CHARACTERS_TO_ESCAPE = CharMatcher.anyOf(new String(new char[] {SEGMENT_SEPARATOR, ESCAPE_CHARACTER,
      REP_SEPARATOR})).precomputed();
  private static final CharMatcher SEGMENT_SEPARATOR_MATCHER = CharMatcher.is(SEGMENT_SEPARATOR).precomputed();
  private static final CharMatcher END_MATCHER = CharMatcher.anyOf(new String(new char[] {SEGMENT_SEPARATOR, REP_SEPARATOR})).precomputed();

  /**
   * Helper object used to iterate over the segments in a URI fragment and extract the number of repetitions of the segments.
   */
  protected class FragmentSegmentIterator implements Iterator<String> {
    private final String fragment;
    private final int length;
    private int startIdx;
    private int endIdx;
    private int reps;

    /**
     * Creates a new instance of {@link FragmentSegmentIterator}.
     *
     * @param fragment
     *          URI fragment to create iterator for, must not be {@code null}
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
    public String next() {
      startIdx = endIdx + 1;
      int idx = indexOfUnescapedChar(fragment, END_MATCHER, startIdx);
      int repIdx = idx < length && fragment.charAt(idx) == REP_SEPARATOR ? idx : -1;
      endIdx = repIdx == -1 ? idx : indexOfUnescapedChar(fragment, SEGMENT_SEPARATOR_MATCHER, repIdx);
      reps = repIdx < startIdx ? 1 : Integer.parseInt(fragment.substring(repIdx + 1, endIdx));
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

    /** {@inheritDoc} */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /** {@inheritDoc} */
  @Override
  public String getFragment(final EObject object, final Fallback fallback) {
    final Stack<EObject> containingObjects = new Stack<EObject>();
    EObject current = object;
    while (current != null) {
      containingObjects.push(current);
      current = current.eContainer();
    }
    if (containingObjects.peek().eResource() == null) {
      // could happen while unloading objects
      return fallback.getFragment(object);
    }
    final StringBuilder fragment = new StringBuilder(containingObjects.size() * 16);
    CharSequence previousSegment = null;
    CharSequence segment = internalGetFragmentSegment(containingObjects.pop());
    int reps = 1;
    fragment.append(SEGMENT_SEPARATOR);
    fragment.append(segment);
    while (!containingObjects.isEmpty()) {
      previousSegment = segment;
      segment = internalGetFragmentSegment(containingObjects.pop());
      if (equal(previousSegment, segment)) {
        reps++;
      } else {
        if (reps == 2 && previousSegment.length() == 1) {
          fragment.append(SEGMENT_SEPARATOR).append(previousSegment);
          reps = 1;
        } else if (reps > 1) {
          fragment.append(REP_SEPARATOR).append(reps);
          reps = 1;
        }
        fragment.append(SEGMENT_SEPARATOR).append(segment);
      }
    }
    if (reps == 2 && previousSegment.length() == 1) {
      fragment.append(SEGMENT_SEPARATOR).append(previousSegment);
    } else if (reps > 1) {
      fragment.append(REP_SEPARATOR).append(reps);
    }
    return fragment.toString();
  }

  /**
   * Internal method which calls {@link InferenceContainer#getFragmentSegment(EObject)} if {@code object}'s container is an {@link InferenceContainer} and
   * otherwise calls {@link #getFragmentSegment(EObject)}.
   *
   * @param object
   *          the {@link EObject} for which to calculate the fragment segment, must not be {@code null}
   * @return the calculated fragment segment for the given object, never {@code null} or empty
   */
  private CharSequence internalGetFragmentSegment(final EObject object) {
    if (object.eContainer() instanceof InferenceContainer) {
      return ((InferenceContainer) object.eContainer()).getFragmentSegment(object);
    }
    return getFragmentSegment(object);
  }

  /**
   * Checks whether the two given CharSequence objects represent the same string value.
   *
   * @param seq1
   *          first sequence, must not be {@code null}
   * @param seq2
   *          second sequence, must not be {@code null}
   * @return {@code true} if both represent the same string, {@code false} otherwise
   */
  private boolean equal(final CharSequence seq1, final CharSequence seq2) {
    if (seq1.equals(seq2)) {
      return true;
    }
    int length = seq1.length();
    if (seq2.length() != length) {
      return false;
    }
    for (int i = 0; i < length; i++) {
      if (seq1.charAt(i) != seq2.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Calculates and returns the URI fragment segment for the given object, without segment separators.
   *
   * @param object
   *          the {@link EObject} for which to calculate the fragment segment, must not be {@code null}
   * @return the calculated fragment segment for the given object, never {@code null} or empty
   */
  public abstract CharSequence getFragmentSegment(final EObject object);

  /** {@inheritDoc} */
  @Override
  public EObject getEObject(final Resource resource, final String fragment, final Fallback fallback) {
    try {
      FragmentSegmentIterator iterator = new FragmentSegmentIterator(fragment);
      String segment = iterator.next();
      int reps = iterator.repetitions();

      final int contentsIndex = Integer.parseInt(segment);
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
  public abstract EObject getEObjectFromSegment(final EObject container, final String segment);

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
   * Escapes the reserved characters contained in the given text.
   * <p>
   * Reserved characters for e.g. segment and list separation cannot be used by custom fragment providers unless escaped, i.e. prefixed with a '\'. Such URI
   * segments need to be escaped when forming the URI fragment, and consequently unescaped when reading the URI segments.
   * </p>
   *
   * @param text
   *          the text to escape, must not be {@code null}
   * @return the escaped text, never {@code null}
   */
  protected String escape(final String text) {
    return escape(text, CHARACTERS_TO_ESCAPE);
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
   * Escapes the escape characters contained in the given text.
   * <p>
   * Reserved characters for e.g. segment and list separation cannot be used by custom fragment providers unless escaped, i.e. prefixed with a '\'. Such URI
   * segments need to be escaped when forming the URI fragment, and consequently unescaped when reading the URI segments.
   * </p>
   *
   * @param text
   *          the text to escape, must not be {@code null}
   * @param charactersToEscape
   *          the characters to escape, must not be {@code null}
   * @return the escaped text, never {@code null}
   */
  protected String escape(final String text, final CharMatcher charactersToEscape) {
    if (CharMatcher.NONE.equals(charactersToEscape)) {
      return text;
    }
    final StringBuilder result = new StringBuilder(text.length());
    int lastIndex = 0;
    for (int index = 0; index < text.length(); index++) {
      char character = text.charAt(index);
      if (charactersToEscape.matches(character)) {
        result.append(text.substring(lastIndex, index)).append(ESCAPE_CHARACTER).append(character);
        lastIndex = index + 1;
      }
    }
    if (result.length() == 0) {
      return text;
    }
    result.append(text.substring(lastIndex));
    return result.toString();
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
    if (CharMatcher.NONE.equals(charactersToEscape)) {
      return text;
    }
    final StringBuilder result = new StringBuilder(text.length());
    int lastIndex = 0;
    boolean escaped = false;
    for (int index = 0; index < text.length(); index++) {
      char character = text.charAt(index);
      if (escaped) {
        escaped = false;
        if (charactersToEscape.matches(character)) {
          result.append(text.substring(lastIndex, index - 1)).append(character);
          lastIndex = index + 1;
        }
      } else if (character == ESCAPE_CHARACTER) {
        escaped = true;
      }
    }
    if (result.length() == 0) {
      return text;
    }
    result.append(text.substring(lastIndex));
    return result.toString();
  }
}
