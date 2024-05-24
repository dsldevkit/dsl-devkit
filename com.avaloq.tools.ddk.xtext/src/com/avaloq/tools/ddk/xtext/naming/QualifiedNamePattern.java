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
package com.avaloq.tools.ddk.xtext.naming;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Pattern;

import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.xtext.util.Regexps;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Simple marker subclass of QualifiedName.
 */
public class QualifiedNamePattern extends QualifiedName {

  public static final char WILDCARD_CHAR = '*';
  public static final String RECURSIVE_WILDCARD_SEGMENT = "**"; //$NON-NLS-1$

  private static final String EMPTY_SEGMENT = ""; //$NON-NLS-1$

  /**
   * Comparator which sorts name patterns in front of every name they match.
   */
  public static final class Comparator implements java.util.Comparator<QualifiedName> {

    @Override
    public int compare(final QualifiedName o1, final QualifiedName o2) {
      return o1 instanceof QualifiedNamePattern ? o1.compareTo(o2) : -o2.compareTo(o1);
    }
  }

  /**
   * Low-level factory method. Consider using a {@link org.eclipse.xtext.naming.IQualifiedNameConverter} instead.
   *
   * @param singleSegment
   *          the single segment of the newly created qualified name
   * @return qualified name pattern
   */
  public static QualifiedNamePattern create(final String singleSegment) {
    if (singleSegment == null) {
      throw new IllegalArgumentException("Segment cannot be null"); //$NON-NLS-1$
    }
    return new QualifiedNamePattern(new String[] {singleSegment}, true);
  }

  /**
   * Creates a new instance of {@link QualifiedNamePattern}.
   *
   * @param segments
   *          Qualified name segments
   * @return qualified name pattern
   */
  public static QualifiedNamePattern create(final String... segments) {
    return new QualifiedNamePattern(segments, true);
  }

  /**
   * Creates a new instance of {@link QualifiedNamePattern}.
   *
   * @param base
   *          Qualified name
   * @return qualified name pattern
   */
  public static QualifiedNamePattern create(final QualifiedName base) {
    return new QualifiedNamePattern(base.getSegments().toArray(new String[base.getSegmentCount()]), true);
  }

  /**
   * Creates a new instance of {@link QualifiedNamePattern} based on GLOB patterns.
   * TODO: replace with equivalent of org.h2.expression.CompareLike (does not require regular expressions)
   *
   * @param globs
   *          GLOB patterns
   * @return qualified name
   */
  public static QualifiedNamePattern createFromGlobs(final String... globs) {
    QualifiedNamePattern pattern = new QualifiedNamePattern(globs, false);
    pattern.globRegexps = Lists.transform(Lists.newArrayList(globs), new Function<String, Pattern>() {
      @Override
      public Pattern apply(final String from) {
        return Regexps.fromGlob(from);
      }
    }).toArray(new Pattern[globs.length]);
    return pattern;
  }

  private Pattern[] globRegexps;
  private QualifiedName lowerCase;
  private QualifiedName lowerBound;
  private QualifiedName upperBound;

  /**
   * Creates a new instance of {@link QualifiedNamePattern}.
   *
   * @param segments
   *          Qualified name segments
   * @param verify
   *          {@code true} if pattern should be verified
   */
  protected QualifiedNamePattern(final String[] segments, final boolean verify) {
    super(segments);
    if (verify) {
      for (int i = 0; i < segments.length; i++) {
        String seg = segments[i];
        int wildcardIdx = seg.indexOf(WILDCARD_CHAR);
        if (wildcardIdx != -1 && i != segments.length - 1) {
          throw new IllegalArgumentException("Wildcard must be in last segment: " + Arrays.toString(segments)); //$NON-NLS-1$
        } else if (wildcardIdx != -1) {
          if (wildcardIdx == seg.length() - 1 || (wildcardIdx == seg.length() - 2 && seg.charAt(wildcardIdx + 1) == WILDCARD_CHAR)) {
            continue;
          }
          throw new IllegalArgumentException("Wildcard must be at end of segment: " + Arrays.toString(segments)); //$NON-NLS-1$
        }
      }
    }
  }

  /**
   * Creates a new instance of {@link QualifiedNamePattern}.
   *
   * @param base
   *          Qualified name
   */
  protected QualifiedNamePattern(final QualifiedName base) {
    super(base.getSegments().toArray(new String[base.getSegmentCount()]));
  }

  /**
   * Checks if this is a recursive wildcard pattern like "a.**" which in contrast to a simple "a.*" pattern also will match names like "a.b.c".
   *
   * @return returns {@code true} if this is a recursive wildcard pattern
   */
  public boolean isRecursivePattern() {
    return getLastSegment().endsWith(RECURSIVE_WILDCARD_SEGMENT);
  }

  @Override
  public QualifiedName append(final String segment) {
    return new QualifiedNamePattern(super.append(segment));
  }

  @Override
  public QualifiedName append(final QualifiedName relativeQualifiedName) {
    return new QualifiedNamePattern(super.append(relativeQualifiedName));
  }

  @Override
  public QualifiedName skipFirst(final int skipCount) {
    return new QualifiedNamePattern(super.skipFirst(skipCount));
  }

  @Override
  public QualifiedName skipLast(final int skipCount) {
    return new QualifiedNamePattern(super.skipLast(skipCount));
  }

  @Override
  public QualifiedName toLowerCase() {
    if (lowerCase == null) {
      lowerCase = new QualifiedNamePattern(super.toLowerCase()) {
        @Override
        public QualifiedName toLowerCase() {
          return this;
        }
      };
      if (globRegexps != null) {
        ((QualifiedNamePattern) lowerCase).globRegexps = globRegexps;
      }
    }
    return lowerCase;
  }

  @Override
  public QualifiedName toUpperCase() {
    QualifiedNamePattern upperCase = new QualifiedNamePattern(super.toUpperCase()) {
      @Override
      public QualifiedName toUpperCase() {
        return this;
      }
    };
    if (globRegexps != null) {
      upperCase.globRegexps = globRegexps;
    }
    return upperCase;
  }

  @Override
  protected int compareTo(final QualifiedName other, final boolean ignoreCase) { // NOPMD NPathComplexity
    if (other instanceof QualifiedNamePattern) {
      return super.compareTo(other, ignoreCase);
    }
    int o1SegmentCount = getSegmentCount();
    String seg1;
    String seg2;
    int o2SegmentCount = other.getSegmentCount();
    for (int i = 0; i < o1SegmentCount; i++) {
      if (i == o2SegmentCount) {
        return 1;
      }
      seg1 = this.getSegment(i);
      seg2 = other.getSegment(i);
      if (seg1.length() == 0) {
        return -1;
      } else if ("*".equals(seg1)) { //$NON-NLS-1$
        continue;
      }
      int wildcardIdx = seg1.indexOf(WILDCARD_CHAR);
      if (wildcardIdx == -1) {
        int res = ignoreCase ? seg1.compareToIgnoreCase(seg2) : seg1.compareTo(seg2);
        if (res != 0) {
          return res;
        }
      } else {
        if (seg2.length() < wildcardIdx) {
          seg1 = seg1.substring(0, wildcardIdx);
          return ignoreCase ? seg1.compareToIgnoreCase(seg2) : seg1.compareTo(seg2);
        } else {
          seg1 = seg1.substring(0, wildcardIdx);
          seg2 = seg2.substring(0, wildcardIdx);
          int res = ignoreCase ? seg1.compareToIgnoreCase(seg2) : seg1.compareTo(seg2);
          if (res != 0) {
            return res;
          }
        }
      }
    }
    return -1;
  }

  /**
   * Checks whether the given qualified name (which shouldn't itself be a {@link QualifiedNamePattern}) matches the pattern of this {@link QualifiedNamePattern}
   * .
   *
   * @param other
   *          other name to match
   * @return <code>true</code> if the given other name matches this pattern
   */
  public boolean matches(final QualifiedName other) {
    if (other.getSegmentCount() < getSegmentCount()) {
      return false;
    } else if (other instanceof QualifiedNamePattern) {
      return false;
    }
    if (globRegexps != null) {
      String lastSeg = getLastSegment();
      if (lastSeg.charAt(lastSeg.length() - 1) != WILDCARD_CHAR && other.getSegmentCount() > getSegmentCount()) {
        return false;
      }
      for (int i = 0; i < globRegexps.length; i++) {
        String otherSeg = other.getSegment(i);
        if (!globRegexps[i].matcher(otherSeg).matches()) {
          return false;
        }
      }
    } else {
      for (int i = 0; i < getSegmentCount(); i++) {
        String seg = getSegment(i);
        int wildcardIdx = seg.indexOf(WILDCARD_CHAR);
        if (wildcardIdx == 0) {
          if (RECURSIVE_WILDCARD_SEGMENT.equals(seg)) {
            return true;
          } else if (other.getSegmentCount() > getSegmentCount()) {
            return false;
          }
          continue;
        }
        String otherSeg = other.getSegment(i);
        if (wildcardIdx != -1) {
          if (seg.length() > wildcardIdx + 1 && seg.charAt(wildcardIdx + 1) == WILDCARD_CHAR) {
            return seg.regionMatches(0, otherSeg, 0, wildcardIdx);
          } else if (other.getSegmentCount() > getSegmentCount()) {
            return false;
          } else if (seg.regionMatches(0, otherSeg, 0, wildcardIdx)) {
            continue;
          }
        }
        if (!seg.equals(otherSeg)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns the lowest (as per {{@link Comparator}) qualified name matched by this pattern.
   *
   * @return lowest matched qualified name
   */
  public QualifiedName lowerInclusive() {
    if (lowerBound == null) {
      if (globRegexps != null) {
        int firstWildcardSeg = 0;
        int wildcardIdx = 0;
        for (int i = 0; i < getSegmentCount(); i++) {
          String pattern = getSegment(i);
          int anyIdx = pattern.indexOf(WILDCARD_CHAR);
          int oneIdx = pattern.indexOf('?');
          if (anyIdx != -1 || oneIdx != -1) {
            firstWildcardSeg = i;
            if (anyIdx != -1) {
              wildcardIdx = anyIdx;
            }
            if (oneIdx != -1 && (oneIdx < anyIdx || anyIdx == -1)) {
              wildcardIdx = oneIdx;
            }
            break;
          }
        }
        if (firstWildcardSeg != -1) {
          lowerBound = super.skipLast(getSegmentCount() - firstWildcardSeg).append(getSegment(firstWildcardSeg).substring(0, wildcardIdx));
        } else {
          lowerBound = QualifiedName.create(EMPTY_SEGMENT);
        }
      } else {
        String lastSeg = getLastSegment();
        int wildcardIdx = lastSeg.lastIndexOf(WILDCARD_CHAR);
        lowerBound = wildcardIdx == -1 ? this
            : super.skipLast(1).append(lastSeg.substring(0, lastSeg.endsWith(RECURSIVE_WILDCARD_SEGMENT) ? wildcardIdx - 1 : wildcardIdx));
      }
    }
    return lowerBound;
  }

  /**
   * Returns the lowest (as per {{@link Comparator}) qualified name <em>not</em> matched by this pattern.
   *
   * @return lowest unmatched qualified name
   */
  public QualifiedName upperExclusive() {
    if (upperBound == null) {
      if (globRegexps != null) {
        QualifiedName lowerInclusive = lowerInclusive();
        String lastSeg = lowerInclusive.getLastSegment();
        if (lastSeg.length() == 0) {
          if (lowerInclusive.getSegmentCount() == 1) {
            upperBound = QualifiedName.create("!"); //$NON-NLS-1$
          } else {
            lowerInclusive = lowerInclusive.skipLast(1);
            lastSeg = lowerInclusive.getLastSegment();
            upperBound = lowerInclusive.skipLast(1).append(lastSeg + '!');
          }
        } else {
          int lastCharIdx = lastSeg.length() - 1;
          upperBound = lowerInclusive.skipLast(1).append(lastSeg.substring(0, lastCharIdx) + ((char) (lastSeg.charAt(lastCharIdx) + 1)));
        }
      } else {
        String lastSeg = getLastSegment();
        int wildcardIdx = lastSeg.lastIndexOf(WILDCARD_CHAR);
        if (wildcardIdx == 0 || wildcardIdx == 1 && RECURSIVE_WILDCARD_SEGMENT.equals(lastSeg)) {
          if (getSegmentCount() == 1) {
            upperBound = QualifiedName.create("!"); //$NON-NLS-1$
          } else {
            upperBound = super.skipLast(1);
            lastSeg = upperBound.getLastSegment();
            if (lastSeg.length() > 0) {
              upperBound = upperBound.skipLast(1).append(lastSeg + '!');
            } else {
              // handle empty segment
              upperBound = upperBound.skipLast(1).append("!"); //$NON-NLS-1$
            }
          }
        } else if (wildcardIdx == -1) {
          upperBound = super.skipLast(1).append(lastSeg + '!');
        } else {
          int lastCharIdx = lastSeg.charAt(wildcardIdx - 1) == WILDCARD_CHAR ? wildcardIdx - 2 : wildcardIdx - 1;
          upperBound = super.skipLast(1).append(lastSeg.substring(0, lastCharIdx) + ((char) (lastSeg.charAt(lastCharIdx) + 1)));
        }
      }
    }
    return upperBound;
  }

  /**
   * Returns a concatenated iterable of the subset of entries in the given map whose key matches this name pattern. This requires that the input map has been
   * sorted using the {@link Comparator}.
   *
   * @param lookupMap
   *          input to match
   * @param excludeDuplicates
   *          whether to exclude duplicates in the result
   * @param <T>
   *          element type
   * @return matches
   */
  public <T> Collection<T> findNestedArrayMatches(final SortedMap<QualifiedName, T[]> lookupMap, final boolean excludeDuplicates) {
    // TODO implement strict matching for regexp patterns
    Collection<T> candidates = excludeDuplicates ? Sets.<T> newHashSet() : Lists.<T> newArrayList();
    if (!isRecursivePattern()) {
      int segmentCount = getSegmentCount();
      for (Map.Entry<QualifiedName, T[]> entry : lookupMap.subMap(lowerInclusive(), upperExclusive()).entrySet()) {
        if (entry.getKey().getSegmentCount() == segmentCount) {
          for (T element : entry.getValue()) {
            candidates.add(element);
          }
        }
      }
    } else {
      for (T[] values : lookupMap.subMap(lowerInclusive(), upperExclusive()).values()) {
        candidates.addAll(Arrays.asList(values));
      }
    }
    return candidates;
  }

  /**
   * Returns a concatenated iterable of the subset of entries in the given map whose key matches this name pattern. This requires that the input map has been
   * sorted using the {@link Comparator}.
   *
   * @param lookupMap
   *          input to match
   * @param excludeDuplicates
   *          whether to exclude duplicates in the result
   * @param <T>
   *          element type
   * @return matches
   */
  public <T> Collection<T> findNestedLookupMatches(final QualifiedNameLookup<T> lookupMap, final boolean excludeDuplicates) {
    return lookupMap.get(this, excludeDuplicates);
  }

  /**
   * Returns a concatenated iterable of the subset of entries in the given map whose key matches this name pattern. This requires that the input map has been
   * sorted using the {@link Comparator}.
   *
   * @param from
   *          input to match
   * @param excludeDuplicates
   *          whether to exclude duplicates in the result
   * @param <T>
   *          element type
   * @param <C>
   *          a collection type with T elements
   * @return matches
   */
  public <T, C extends Collection<T>> Iterable<T> findNestedMatches(final SortedMap<QualifiedName, C> from, final boolean excludeDuplicates) {
    // TODO implement strict matching for regexp patterns
    if (excludeDuplicates) {
      Set<T> candidates = Sets.newHashSet();
      for (Collection<T> entry : from.subMap(lowerInclusive(), upperExclusive()).values()) {
        candidates.addAll(entry);
      }
      return candidates;
    } else {
      return Iterables.concat(from.subMap(lowerInclusive(), upperExclusive()).values());
    }
  }
}
