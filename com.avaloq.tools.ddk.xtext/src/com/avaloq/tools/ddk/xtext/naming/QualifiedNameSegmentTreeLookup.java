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
package com.avaloq.tools.ddk.xtext.naming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.caching.CacheStatistics;
import com.avaloq.tools.ddk.xtext.util.ArrayUtils;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Implementation which merges all {@link QualifiedName} into a tree by breaking up every qualified name into its segments and arranging them in a tree. Thus
 * the four qualified names "a", "a.b", "a.c", and "b" would result in a tree like this: {@code root -> (a -> (b, c), b)}. As a result this lookup structure
 * will no longer reference any {@link QualifiedName} objects anymore which allows saving memory. Additionally this implementation will also use
 * {@link String#String(String) copies} of the name segments to save additional memory. Also, if a child node happens to be associated with the exact same set
 * of values as its parent, then this array will also be shared.
 * <p>
 * Both in terms of update and lookup the performance seems to be on par with the {@link TreeSetLookup} implementation. But the memory overhead is typically
 * much smaller (around 50%), which can be quite significant when there are lots of names.
 *
 * @param <T>
 *          value element type
 */
public class QualifiedNameSegmentTreeLookup<T> implements QualifiedNameLookup<T> {

  /**
   * A node in the tree representing a single segment of a qualified name.
   */
  // CHECKSTYLE:CHECK-OFF ParameterAssignmentCheck
  // CHECKSTYLE:CHECK-OFF FinalParametersCheck
  // CHECKSTYLE:CHECK-OFF VisibilityModifier
  private static class SegmentNode {

    protected static final int DEFAULT_CHILD_CAPACITY = 4;

    private final String segment;
    protected Object[] values;
    protected List<SegmentNode> children;

    /**
     * Creates a new node for the given qualified name segment.
     *
     * @param segment
     *          qualified name segment, must not be {@code null}
     */
    @SuppressWarnings("PMD.StringInstantiation")
    SegmentNode(final String segment) {
      this.segment = segment;
    }

    /**
     * Finds the node for the given qualified name.
     *
     * @param name
     *          name to find node for
     * @param segIdx
     *          index into segment of {@code name} (used for recursion), initially 0 (when receiver is root node)
     * @param exactMatch
     *          when {@code false} the returned node will be the successor node if there is no node with the exact name looked for
     * @return matching node or successor node
     */
    @SuppressWarnings("PMD.NPathComplexity")
    public SegmentNode find(final QualifiedName name, int segIdx, final boolean exactMatch) {
      if (children == null || children.isEmpty()) {
        return null;
      }
      String seg = name.getSegment(segIdx++);
      boolean lastSeg = name.getSegmentCount() == segIdx;
      int idx = binarySearch(children, seg);
      if (idx < 0) {
        if (exactMatch || !lastSeg) {
          return null;
        }
        idx = -(idx + 1);
      }
      if (idx == children.size()) {
        return null;
      }
      if (lastSeg) {
        return children.get(idx);
      }
      SegmentNode result = children.get(idx++).find(name, segIdx, exactMatch);
      if (result == null && !exactMatch) {
        result = idx == children.size() ? null : children.get(idx);
      }
      return result;
    }

    /**
     * Returns the mapped values of all nodes starting with the node for {@code lower} and up to (in depth-first search order) but excluding {@code upper}.
     *
     * @param lower
     *          name of first node to visit, must not be {@code null}
     * @param lowerIdx
     *          index into {@code lower} used for recursion, initially 0 (when receiver is root node)
     * @param upper
     *          marker node to know when to stop search (not given to visitor), must not be {@code null}
     * @param recursive
     *          whether to return {@link QualifiedNamePattern#isRecursivePattern() recursive matches} or not
     * @param excludeDuplicates
     *          whether duplicate values should be excluded in the result
     * @return collection of all values mapped by the nodes in the given range, never {@code null}
     */
    @SuppressWarnings("unchecked")
    public <T> Collection<T> matches(final QualifiedName lower, final int lowerIdx, final SegmentNode upper, final boolean recursive, final boolean excludeDuplicates) {
      final Collection<T> result = excludeDuplicates ? Sets.<T> newHashSet() : Lists.<T> newArrayList();
      Visitor visitor = new Visitor() {
        @Override
        public void visit(final SegmentNode node) {
          if (node.values != null) {
            for (Object value : node.values) {
              result.add((T) value);
            }
          }
        }
      };
      collectMatches(lower, lowerIdx, upper, recursive, visitor);
      return result;
    }

    /**
     * Visits all nodes starting with the node for {@code lower} and up to {@code upper} using the given visitor.
     *
     * @param lower
     *          name of first node to visit, must not be {@code null}
     * @param lowerIdx
     *          index into {@code lower} used for recursion, initially 0 (when receiver is root node)
     * @param upper
     *          marker node to know when to stop search (not given to visitor), must not be {@code null}
     * @param recursive
     *          whether to return {@link QualifiedNamePattern#isRecursivePattern() recursive matches} or not
     * @param visitor
     *          visitor to visit matching nodes with, must not be {@code null}
     * @return {@code false} if marker {@code upper} node was found and search should be stopped
     */
    @SuppressWarnings({"PMD.CompareObjectsWithEquals", "PMD.NPathComplexity"})
    protected boolean collectMatches(final QualifiedName lower, int lowerIdx, final SegmentNode upper, final boolean recursive, final Visitor visitor) {
      if (children == null || this == upper || upper == null) {
        return false;
      }
      String seg = lower.getSegment(lowerIdx++);
      int idx = binarySearch(children, seg);
      if (idx < 0 && lower.getSegmentCount() == lowerIdx) {
        idx = -(idx + 1);
      } else if (idx < 0) {
        return false;
      }
      if (idx == children.size()) {
        return true;
      }
      if (lower.getSegmentCount() == lowerIdx) {
        for (; idx < children.size(); idx++) {
          SegmentNode child = children.get(idx);
          if (child == upper) {
            return false;
          }
          visitor.visit(children.get(idx));
          if (recursive) {
            children.get(idx).visitChildren(visitor, upper);
          }
        }
        return true;
      } else {
        boolean collect = children.get(idx++).collectMatches(lower, lowerIdx, upper, recursive, visitor);
        if (collect) {
          for (; idx < children.size(); idx++) {
            SegmentNode child = children.get(idx);
            if (child == upper || !child.visitChildren(visitor, upper)) {
              return false;
            }
          }
        }
        return collect;
      }
    }

    /**
     * Visits the children (recursively, in depth-first search order) of this node using the given visitor and stops the process as soon as the given node has
     * been found (which will not be visited).
     *
     * @param visitor
     *          visitor to use to visit children, must not be {@code null}
     * @param stopOn
     *          node to stop visit process on when found, must not be {@code null}
     * @return {@code false} when the search should stop because the marker {@code stopOn} node was found
     */
    private boolean visitChildren(final Visitor visitor, final SegmentNode stopOn) {
      if (children == null) {
        return true;
      }
      for (SegmentNode child : children) {
        if (stopOn.equals(child)) {
          return false;
        }
        visitor.visit(child);
        if (!child.visitChildren(visitor, stopOn)) {
          return false;
        }
      }
      return true;
    }

    /**
     * Merges the given mapping into the tree. This is a recursive method which iterates over the segments in the given name using the additional {@code segIdx}
     * parameter as an index of the current name segment. When {@code segIdx} corresponds to the last segment's index, the target node is an immediate child of
     * this node. Until then the method must call itself recursively for the corresponding child. Any missing intermediate nodes will be created automatically.
     * If the target node already exists, the mappings will be completed with {@code newValues}.
     *
     * @param name
     *          qualified name to associate values with
     * @param segIdx
     *          index of current qualified name segment, initially 0 (when receiver is tree root)
     * @param newValues
     *          new values to associate qualified name with; if mappings already exist any missing mapping will be added
     */
    public void merge(final QualifiedName name, int segIdx, final Object[] newValues) { // NOPMD - varargs doesn't make sense here
      if (children == null) {
        children = new ArrayList<SegmentNode>(DEFAULT_CHILD_CAPACITY);
      }
      String seg = name.getSegment(segIdx++);
      int idx = binarySearch(children, seg);
      if (idx < 0) {
        SegmentNode child = new SegmentNode(seg);
        idx = -(idx + 1);
        children.add(idx, child);
        if (name.getSegmentCount() == segIdx) {
          child.values = newValues;
          return;
        }
        child.merge(name, segIdx, newValues);
      } else if (name.getSegmentCount() == segIdx) {
        Object[] tmp = ArrayUtils.addAll(children.get(idx).values, newValues);
        children.get(idx).values = tmp;
      } else {
        children.get(idx).merge(name, segIdx, newValues);
      }
    }

    /**
     * Accepts the given visitor and uses it to visit all children in depth-first search order.
     *
     * @param visitor
     *          visitor to visit this node with, must not be {@code null}
     */
    public void accept(final Visitor visitor) {
      visitor.visit(this);
      if (children != null) {
        for (SegmentNode child : children) {
          child.accept(visitor);
        }
      }
    }

    /**
     * Adopted from {@link java.util.Collections#binarySearch(List, Object)} which can't be used here because the element being searched has a different type.
     *
     * @param list
     *          list of nodes to search
     * @param key
     *          key, corresponding to {@link SegmentNode#segment}, to search for
     * @return index of found node or (-(insertion point) - 1)
     */
    protected int binarySearch(final List<SegmentNode> list, final String key) {
      int low = 0;
      int high = list.size() - 1;

      while (low <= high) {
        int mid = (low + high) >>> 1;
        SegmentNode midVal = list.get(mid);
        int cmp = midVal.segment.compareTo(key);

        if (cmp < 0) {
          low = mid + 1;
        } else if (cmp > 0) {
          high = mid - 1;
        } else {
          return mid; // key found
        }
      }
      return -(low + 1); // key not found
    }

    @Override
    public String toString() {
      return "Node(" + segment + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * Subclass which implements value sharing between nodes and child nodes to reduce the memory footprint.
   */
  private static class ValueSharingSegmentNode extends SegmentNode {

    ValueSharingSegmentNode(final String segment) {
      super(segment);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Collection<T> matches(final QualifiedName lower, final int lowerIdx, final SegmentNode upper, final boolean recursive, final boolean excludeDuplicates) {
      final Collection<T> result = excludeDuplicates ? Sets.<T> newHashSet() : Lists.<T> newArrayList();
      final Set<Object[]> arrays = Sets.newHashSet();
      Visitor visitor = new Visitor() {
        @Override
        public void visit(final SegmentNode node) {
          if (node.values != null) {
            arrays.add(node.values);
          }
        }
      };
      collectMatches(lower, lowerIdx, upper, recursive, visitor);
      for (Object[] array : arrays) {
        for (Object value : array) {
          result.add((T) value);
        }
      }
      return result;
    }

    @Override
    public void merge(final QualifiedName name, int segIdx, final Object[] newValues) { // NOPMD - varargs doesn't make sense here
      if (children == null) {
        children = new ArrayList<SegmentNode>(DEFAULT_CHILD_CAPACITY);
      }
      String seg = name.getSegment(segIdx++);
      int idx = binarySearch(children, seg);
      if (idx < 0) {
        SegmentNode child = new ValueSharingSegmentNode(seg);
        idx = -(idx + 1);
        children.add(idx, child);
        if (name.getSegmentCount() == segIdx) {
          child.values = Arrays.equals(values, newValues) ? values : newValues;
          return;
        }
        child.merge(name, segIdx, newValues);
      } else if (name.getSegmentCount() == segIdx) {
        Object[] tmp = ArrayUtils.addAll(children.get(idx).values, newValues);
        children.get(idx).values = Arrays.equals(values, tmp) ? values : tmp;
      } else {
        children.get(idx).merge(name, segIdx, newValues);
      }
    }

  }

  // CHECKSTYLE:CHECK-ON VisibilityModifier
  // CHECKSTYLE:CHECK-ON ParameterAssignmentCheck
  // CHECKSTYLE:CHECK-ON FinalParametersCheck

  /**
   * A visitor to visit the nodes of the tree in a {@link SegmentNode#accept(Visitor) depth-first order}.
   */
  // CHECKSTYLE:CHECK-OFF AbstractClassNameCheck
  private abstract static class Visitor {
    // CHECKSTYLE:CHECK-ON AbstractClassNameCheck
    /**
     * Visits the given tree node.
     *
     * @param node
     *          node to visit, never {@code null}
     */
    public abstract void visit(final SegmentNode node);
  }

  private final SegmentNode root;

  private long size;
  private long hits;
  private long misses;

  public QualifiedNameSegmentTreeLookup(final Class<T> elementType, final boolean shareValues) { // NOPMD
    root = shareValues ? new ValueSharingSegmentNode("") : new SegmentNode(""); //$NON-NLS-1$ //$NON-NLS-2$
    init();
  }

  /**
   * Initiailizes the tree by clearing it (if not empty) and adding a dummy upper bound node.
   */
  private void init() {
    if (root.children != null) {
      root.children.clear();
    }
    // add dummy serving as last upper bound
    root.merge(QualifiedName.create(new String(new char[] {Character.MAX_VALUE})), 0, null);
    size = 0;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  public Collection<T> get(final QualifiedName name) {
    if (name.isEmpty()) {
      return null;
    }
    SegmentNode result = root.find(name, 0, true);
    if (result != null && result.values != null) {
      hits++;
      return (Collection<T>) Arrays.asList(result.values);
    } else {
      misses++;
      return null;
    }
  }

  /** {@inheritDoc} */
  @Override
  public void removeMappings(final T value) {
    root.accept(new Visitor() {
      @Override
      public void visit(final SegmentNode node) {
        Object[] newValues = ArrayUtils.remove(node.values, value);
        if (newValues != node.values) {
          node.values = newValues;
          size--;
        }
      }
    });
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {
    init();
  }

  /** {@inheritDoc} */
  @Override
  public Collection<T> get(final QualifiedNamePattern pattern, final boolean excludeDuplicates) {
    return root.matches(pattern.lowerInclusive(), 0, root.find(pattern.upperExclusive(), 0, false), pattern.isRecursivePattern(), excludeDuplicates);
  }

  /** {@inheritDoc} */
  @Override
  public void put(final QualifiedName name, final T value) {
    if (name == null || value == null) {
      throw new IllegalArgumentException("QualifiedNameLookup does not support null keys or values: put(" + name + ", " + value + ")"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
    Object[] values = ArrayUtils.newArray(1);
    values[0] = value;
    size++;
    root.merge(name, 0, values);
  }

  /** {@inheritDoc} */
  @Override
  public void putAll(final QualifiedName name, final Collection<T> values) {
    if (name == null || values == null || values.contains(null)) {
      String valuesAsString = values != null ? Iterables.toString(values) : "<null>"; //$NON-NLS-1$
      throw new IllegalArgumentException("QualifiedNameLookup does not support null keys or values: putAll(" + name + ", " + valuesAsString + ")"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
    size += values.size();
    root.merge(name, 0, values.toArray(ArrayUtils.newArray(values.size())));
  }

  /** {@inheritDoc} */
  @Override
  public void remove(final QualifiedName name, final T value) {
    SegmentNode node = root.find(name, 0, true);
    if (node != null) {
      Object[] newValues = ArrayUtils.remove(node.values, value);
      if (newValues != node.values) {
        node.values = newValues;
        size--;
      }
    }
  }

  @Override
  public CacheStatistics getStatistics() {
    return new CacheStatistics(size, hits, misses);
  }

}
