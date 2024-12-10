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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;

import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.xtext.resource.Messages;
import com.google.common.collect.Iterables;


/**
 * A scope that doesn't flatten the whole hierarchy in getContentByName but does indeed traverse
 * the scope hierarchy.
 */
public abstract class AbstractRecursiveScope extends AbstractScope {

  // CHECKSTYLE:OFF
  private static boolean DEBUG = Boolean.getBoolean("com.avaloq.tools.ddk.xtext.scoping.AbstractRecursiveScope.debugCyclicDependency"); //$NON-NLS-1$
  // CHECKSTYLE:ON

  /**
   * Set is used to trace invocations of {@link #getSingleElement(QualifiedName)} and {@link #getAllContentsByEObject(EObject)}.
   * A duplicate insertion indicates an infinite recursion, i.e. it would lead to a stack overflow.
   */
  private Set<Object> invocationTrace;

  /**
   * Marker for the null value for the lookups to be able to distinguish unsuccessful lookups
   * from lookups not yet made.
   */
  protected static final IEObjectDescription NULL_DESCRIPTION = EObjectDescription.create("<null>", null); //$NON-NLS-1$

  /**
   * Cache to avoid repeated lookups.
   */
  private final Map<QualifiedName, IEObjectDescription> nameCache = CacheManager.getInstance().createMapCache("AbstractRecursiveScope#nameCache"); //$NON-NLS-1$

  /**
   * Inverse cache.
   */
  private final Map<URI, IEObjectDescription> objectCache = CacheManager.getInstance().createMapCache("AbstractRecursiveScope#objectCache"); //$NON-NLS-1$

  /** Cached contents. */
  private Iterable<IEObjectDescription> contents;

  /**
   * Iterator; repeated calls to getContentByName continue where the last lookup stopped.
   */
  private Iterator<IEObjectDescription> contentsIterator;

  /**
   * Id of the scope; useful for debugging.
   */
  private final String id;

  /**
   * Create a case sensitive scope with a given id.
   *
   * @param id
   *          The id.
   * @param parent
   *          parent scope
   */
  public AbstractRecursiveScope(final String id, final IScope parent) {
    this(id, parent, false);
  }

  /**
   * Create a scope with specified case sensitivity and id.
   *
   * @param id
   *          The id.
   * @param parent
   *          parent scope
   * @param caseInsensitive
   *          True or false.
   */
  public AbstractRecursiveScope(final String id, final IScope parent, final boolean caseInsensitive) {
    super(parent, caseInsensitive);
    this.id = id;
  }

  @Override
  public synchronized Iterable<IEObjectDescription> getAllElements() {
    if (contents == null) {
      // contents = super.getAllElements() eliminates duplicates using a very inefficient algorithm
      contents = Iterables.concat(getAllLocalElements(), getParent().getAllElements());
    }
    return contents;
  }

  private void addInvocationTrace(final Object object) {
    if (invocationTrace == null) {
      invocationTrace = new HashSet<>();
    }
    if (!invocationTrace.add(object)) {
      throw new IllegalStateException(Messages.bind("Cyclic dependency detected for \"{0}\".", String.valueOf(object))); //$NON-NLS-1$
    }
  }

  @Override
  public synchronized IEObjectDescription getSingleElement(final QualifiedName name) { // NOPMD NPathComplexity
    if (name == null) {
      throw new IllegalArgumentException("Null name in getContentByName"); //$NON-NLS-1$
    }
    try {
      if (DEBUG) {
        addInvocationTrace(name);
      }
      final QualifiedName lookupName = isIgnoreCase() ? name.toLowerCase() : name; // NOPMD UseLocaleWithCaseConversions
      IEObjectDescription result = nameCache.get(lookupName);
      if (result != null) {
        return result == NULL_DESCRIPTION ? null : result; // NOPMD CompareObjectsWithEquals
      }

      if (contentsIterator == null) {
        contentsIterator = getAllLocalElements().iterator();
      }

      while (result == null && contentsIterator.hasNext()) {
        result = contentsIterator.next();
        QualifiedName thisName = result.getName();
        if (isIgnoreCase()) {
          thisName = thisName.toLowerCase(); // NOPMD UseLocaleWithCaseConversions
        }
        if (!isIgnoreCase() || nameCache.get(thisName) == null) {
          nameCache.put(thisName, result);
        }
        if (!lookupName.equals(thisName)) {
          result = null; // NOPMD NullAssignment
        }
      }

      if (result == null) {
        result = getParent().getSingleElement(name);
        // Possibly cache this result, too. For the time being, let the outer scope use its own cache, otherwise we'll duplicate
        // a lot.
      }
      if (result == null) {
        nameCache.put(lookupName, NULL_DESCRIPTION);
      }
      return result;
    } catch (ConcurrentModificationException e) {
      // cache invalidated: retry
      reset();
      // Remove name from invocation trace, otherwise the intended retry recursive call will fail
      if (DEBUG) {
        invocationTrace.remove(name);
      }
      return getSingleElement(name);
    } finally {
      if (DEBUG) {
        invocationTrace.remove(name);
      }
    }
  }

  /**
   * Resets this scope by resetting all cached data.
   */
  private void reset() {
    nameCache.clear();
    objectCache.clear();
    contentsIterator = null; // NOPMD NullAssignment
    contents = null; // NOPMD NullAssignment
  }

  @Override
  public synchronized IEObjectDescription getSingleElement(final EObject object) {
    try {
      if (DEBUG) {
        addInvocationTrace(object);
      }

      final URI key = EcoreUtil.getURI(object);
      IEObjectDescription result = objectCache.get(key);
      if (result == NULL_DESCRIPTION) { // NOPMD CompareObjectsWithEquals
        return null;
      } else if (result == null) {
        result = super.getSingleElement(object);
        if (result == null) {
          objectCache.put(key, NULL_DESCRIPTION);
        } else {
          objectCache.put(key, result);
        }
      }
      return result;
    } finally {
      if (DEBUG) {
        invocationTrace.remove(object);
      }
    }
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * For debugging.
   *
   * @return A string representation of the scope useful for debugging.
   */
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder(getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    result.append(" (id: ");
    result.append(getId());
    result.append(')');

    final IScope outerScope = getParent();
    if (outerScope != IScope.NULLSCOPE) {
      result.append("\n  >> ");
      result.append(outerScope.toString().replaceAll("\\\n", "\n  "));
    }
    return result.toString();
  }

}
