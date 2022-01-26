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
package com.avaloq.tools.ddk.typesystem;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Triple;
import org.eclipse.xtext.util.Tuples;

import com.avaloq.tools.ddk.typesystem.typemodel.IExpression;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.IType;
import com.google.common.collect.Sets;
import com.google.inject.Provider;


/**
 * Abstract base class for implementing a type provider.
 * <p>
 * Based on xtext's AbstractTypeProvider for xbase.
 * <br>
 * As well as the abstract methods, implementations should typically override the methods that return null by default (type, expectedType and
 * getTypeProviderFor).
 * <br>
 * AbstractTypeProvider caches the results of type computation, detects cycles, and handles delegation to other type providers
 * The calls to ITypeProvider end up as follows, assuming the corresponding provides*TypeFor* returns true
 * - getType(IExpression expression) -> calls type(expression)
 * - getExpectedType(IExpression expression) -> calls expectedType(expression.container, containerReferenceForExpression, indexOfReference)
 * - getTypeForNamedElement(INamedElement element) -> calls typeForNamedElement(element)
 * <br>
 * If any of these provides*TypeFor* calls returns false, getTypeProviderFor(EObject object) should return the appropriate type provider.
 * </p>
 * 
 * @see org.eclipse.xtext.xbase.typing.AbstractTypeProvider
 */
public abstract class AbstractTypeProvider implements ITypeProvider {

  // CHECKSTYL:OFF - code copied (with modifications) from org.eclipse.xtext.common.types.access.AbstractTypeProviderFactory

  private static final Logger LOGGER = Logger.getLogger(AbstractTypeProvider.class);

  /**
   * A linked list item whose hash depends on both the content and previous linked items.
   * <p>
   * This class is final because of the assumptions that are made in {@code equals} and {@code hashcode}.
   * </p>
   *
   * @param <T>
   *          the type of the item
   */
  protected static final class ImmutableLinkedItem<T> {

    private final T object;
    private final ImmutableLinkedItem<T> prev;
    private final int storedHashCode;
    private final int size;

    public ImmutableLinkedItem(final T object, final ImmutableLinkedItem<T> immutableStack) {
      this.object = object;
      prev = immutableStack;
      size = immutableStack == null ? 1 : immutableStack.size + 1;
      if (prev != null) {
        // CHECKSTYLE:OFF-MagicNumber
        storedHashCode = 31 * size * prev.hashCode() + object.hashCode();
        // CHECKSTYLE:ON-MagicNumber
      } else {
        storedHashCode = object.hashCode();
      }
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == null) {
        return false;
      }
      if (obj == this) {
        return true;
      }
      if (obj.hashCode() != hashCode() || obj.getClass() != ImmutableLinkedItem.class) {
        return false;
      }
      ImmutableLinkedItem<?> other = (ImmutableLinkedItem<?>) obj;
      return other.object.equals(object) && other.size == size && (other.prev == prev || prev != null && prev.equals(other.prev));
    }

    @Override
    public int hashCode() {
      return storedHashCode;
    }

  }

  /**
   * A class for handling cyclic references during a computation.
   *
   * @param <T>
   *          the type of the items we iterate over
   */
  abstract static class AbstractCyclicHandlingSupport<T> {

    /**
     * A thread local that holds the ongoing type computations.
     */
    private final ThreadLocal<ComputationData<T>> ongoing = ThreadLocal.withInitial(ComputationData::new);

    /**
     * Gets the ongoing, thread-local computations.
     *
     * @return the ongoing, thread-local computations
     */
    protected ComputationData<T> getTypeComputations() {
      ThreadLocal<ComputationData<T>> computations = ongoing;
      return computations.get();
    }

    private final OnChangeEvictingCache typeReferenceAwareCache = new OnChangeEvictingCache() {

      @Override
      public <E> E get(final Object key, final Resource resource, final Provider<E> provider) {
        if (resource == null) {
          return provider.get();
        }
        CacheAdapter adapter = getOrCreate(resource);
        Pair<AbstractCyclicHandlingSupport<T>, Object> composedKey = Tuples.create(AbstractCyclicHandlingSupport.this, key);
        E element = adapter.<E> get(composedKey);
        // we could have unloaded cached values that were contained in another resource
        // thus #resource was never notified about the change
        boolean validEntry = element != null;
        if (validEntry && element instanceof EObject) {
          validEntry = !((EObject) element).eIsProxy(); // NOPMD
        }
        if (!validEntry) {
          cacheMiss(adapter);
          element = provider.get();
          @SuppressWarnings("unchecked")
          ImmutableLinkedItem<E> linkedItem = (ImmutableLinkedItem<E>) key;
          if (!(linkedItem.object instanceof EObject)) {
            if (LOGGER.isDebugEnabled()) {
              LOGGER.debug("cache skip: " + element); //$NON-NLS-1$
            }
            return element;
          }
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("cache: " + element); //$NON-NLS-1$
          }
          adapter.set(composedKey, element);
        } else {
          cacheHit(adapter);
        }
        return element;
      }

    };

    /**
     * Holds the set of items being computed.
     *
     * @param <T>
     *          the type of the items we iterate over
     */
    protected static class ComputationData<T> {
      private final Set<T> computations = Sets.newHashSet();
      private ImmutableLinkedItem<T> queryState;
      private Resource resource;
      private boolean resourceLeftOrCyclic;

      /**
       * Adds an item to compute.
       *
       * @param t
       *          the item to add
       * @param resourceMapper
       *          function returning resource of primary object for {@code t}, must not be {@code null}
       * @return {@code true} if {@code t} was added to the set, i.e., was not already present
       */
      protected boolean add(final T t, final Function<T, Resource> resourceMapper) {
        boolean result = computations.add(t);
        if (result) {
          if (queryState == null) {
            resource = resourceMapper.apply(t);
          }
          queryState = new ImmutableLinkedItem<T>(t, queryState);
        }
        return result;
      }

      /**
       * Removes an item to compute.
       *
       * @param t
       *          the item to remove
       */
      protected void remove(final T t) {
        computations.remove(t);
        queryState = queryState.prev;
        if (queryState == null) {
          resource = null;
        }
      }

      /**
       * Gives the number of items in the set.
       *
       * @return the number of items in the set
       */
      protected int size() {
        return computations.size();
      }
    }

    /**
     * Gets the primary eObject for an eObject.
     *
     * @param t
     *          the object to find the primary object for
     * @return the associated primary object or the object itself if there is no associated object.
     */
    protected abstract EObject getPrimaryEObject(T t);

    /**
     * Gets the type for an item.
     *
     * @param t
     *          the item to get the type for
     * @return type type of {@code t}
     */
    public IType getType(final T t) {
      if (t == null) {
        return null;
      }
      EObject eObject = getPrimaryEObject(t);
      if (eObject == null || eObject.eIsProxy()) {
        return null;
      }
      ComputationData<T> computationData = getTypeComputations();
      if (computationData.add(t, k -> getPrimaryEObject(k).eResource())) {
        try {
          if (computationData.resource == eObject.eResource() && !computationData.resourceLeftOrCyclic) {
            final boolean[] hit = new boolean[] {true};
            IType result = typeReferenceAwareCache.get(computationData.queryState, computationData.resource, new Provider<IType>() {
              @Override
              public IType get() {
                hit[0] = false;
                return doComputation(t);
              }
            });
            if (LOGGER.isDebugEnabled()) {
              LOGGER.debug("cache hit: " + hit[0] + " for: " + t); //$NON-NLS-1$ //$NON-NLS-2$
            }
            return result;
          } else {
            if (computationData.resourceLeftOrCyclic) {
              return doComputation(t);
            }
            try {
              computationData.resourceLeftOrCyclic = true;
              return doComputation(t);
            } finally {
              computationData.resourceLeftOrCyclic = false;
            }
          }

        } finally {
          computationData.remove(t);
        }
      } else {
        if (computationData.resourceLeftOrCyclic) {
          return doHandleCyclicCall(t);
        }
        try {
          computationData.resourceLeftOrCyclic = true;
          return doHandleCyclicCall(t);
        } finally {
          computationData.resourceLeftOrCyclic = false;
        }
      }
    }

    protected int getOngoingComputationsSize() {
      return getTypeComputations().size();
    }

    /**
     * Does the actual computation.
     *
     * @param t
     *          the item to compute the type for
     * @return the type of {@code t}
     */
    protected abstract IType doComputation(T t);

    /**
     * Handles a cyclic reference when encountered.
     *
     * @param t
     *          the item that was encountered in the cycle
     * @return the type for {@code t}
     */
    protected abstract IType doHandleCyclicCall(T t);
  }

  /**
   * Gets the containing info for an expression.
   * <p>
   * The containing info for an expression consists of
   * <ul>
   * <li>the EObject that contains the expression,
   * <li>the containment EReference of that containing EObject
   * <li>if the multiplicity is > 1, the index of the expression in the containing EReference; else -1
   * </ul>
   *
   * @param expression
   *          the expression to get the containing info for
   * @return the containing info for {@code expression}
   */
  protected Triple<EObject, EReference, Integer> getContainingInfo(final IExpression expression) {
    if (expression == null) {
      return null;
    }
    if (expression.eIsProxy()) {
      return null;
    }
    EReference containmentReference = expression.eContainmentFeature();
    if (containmentReference == null) {
      return null;
    }
    EObject container = expression.eContainer();
    int index = (containmentReference.isMany()) ? ((List<?>) container.eGet(containmentReference)).indexOf(expression) : -1;
    return Tuples.create(container, containmentReference, index);
  }

  /**
   * Gets the indentation for debugging.
   *
   * @return an indent corresponding to the depth of the current ongoing computations
   */
  protected String getDebugIndentation() {
    int size = typeComputer.getOngoingComputationsSize() + expectedTypeComputer.getOngoingComputationsSize()
        + typeForNamedElementComputer.getOngoingComputationsSize();
    char[] chars = new char[size];
    Arrays.fill(chars, '|');
    return String.valueOf(chars);
  }

  /**
   * Computes the language-specific type of an object given a cycle-handling computer for computing that type.
   *
   * @param <T>
   *          the (Java) type of {@code object} to compute the (language-specific) type for
   * @param key
   *          the kind of computation being performed. Used for debugging.
   * @param object
   *          the object to compute the type for
   * @param cycleHandlingComputer
   *          the cycle-handling computer to compute the type of {@code object}
   * @return the type for {@code object} or null if no type was computed
   */
  protected <T> IType computeType(final String key, final T object, final AbstractCyclicHandlingSupport<T> cycleHandlingComputer) {
    IType result = cycleHandlingComputer.getType(object);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(getDebugIndentation() + "result: " + result + " " + key + "(" + "parameterized" + ") : " + object); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    }
    return result;
  }

  // --------------------------------------------------------------------------
  // Fields and methods for getType.
  //

  private final AbstractCyclicHandlingSupport<IExpression> typeComputer = new AbstractCyclicHandlingSupport<IExpression>() {
    @Override
    protected IType doComputation(final IExpression expression) {
      return type(expression);
    }

    @Override
    protected EObject getPrimaryEObject(final IExpression expression) {
      return expression;
    }

    @Override
    protected IType doHandleCyclicCall(final IExpression expression) {
      return handleCyclicGetType(expression);
    }
  };

  /**
   * Handles the computation of getType when a cycle is encountered.
   *
   * @param expression
   *          the expression to get the type for
   * @return the type of {@code expression} if it can be computed; otherwise null.
   */
  protected IType handleCyclicGetType(final IExpression expression) {
    return null;
  }

  /**
   * Internal method for computing the type of an expression.
   * <p>
   * Should be overridden by subclasses.
   * </p>
   *
   * @param expression
   *          the expression to compute the type for
   * @return the type of {@code expression}
   */
  protected IType type(final IExpression expression) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IType getType(final IExpression expression) {
    if (providesTypeFor(expression)) {
      return computeType("getType", expression, typeComputer); //$NON-NLS-1$
    }
    ITypeProvider provider = getTypeProviderFor(expression);
    if (provider != null && provider != this) {
      return provider.getType(expression);
    }
    return null;
  }

  // --------------------------------------------------------------------------
  // Fields and methods for getExpectedType.
  //

  private final AbstractCyclicHandlingSupport<IExpression> expectedTypeComputer = new AbstractCyclicHandlingSupport<IExpression>() {
    @Override
    protected IType doComputation(final IExpression expression) {
      Triple<EObject, EReference, Integer> triple = getContainingInfo(expression);
      if (triple != null) {
        return expectedType(triple.getFirst(), triple.getSecond(), triple.getThird());
      } else {
        return null;
      }
    }

    @Override
    protected IType doHandleCyclicCall(final IExpression expression) {
      return handleCycleGetExpectedType(expression);
    }

    @Override
    protected EObject getPrimaryEObject(final IExpression expression) {
      return expression;
    }
  };

  /**
   * Handles the computation of getExpectedType when a cycle is encountered.
   *
   * @param expression
   *          the expression to get the expected type for
   * @return the expected type of {@code expression} if it can be computed; otherwise null.
   */
  protected IType handleCycleGetExpectedType(final IExpression expression) {
    return null;
  }

  /**
   * Internal method for computing the expected type of an expression given its container.
   * <p>
   * Should be overridden by subclasses.
   * </p>
   *
   * @see #getContainingInfo(IExpression)
   * @param container
   *          the containing EObject
   * @param reference
   *          the containing EReference
   * @param index
   *          if {@code reference} is a list, then the index within that list; otherwise -1
   * @return the expected type of {@code expression}
   */
  protected IType expectedType(final EObject container, final EReference reference, final int index) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IType getExpectedType(final IExpression expression) {
    if (providesExpectedTypeFor(expression)) {
      return computeType("getExpectedType", expression, expectedTypeComputer); //$NON-NLS-1$
    }
    ITypeProvider provider = getTypeProviderFor(expression);
    if (provider != null && provider != this) {
      return provider.getExpectedType(expression);
    }
    return null;
  }

  // --------------------------------------------------------------------------
  // Fields and methods for getTypeForNamedElement.
  //

  private final AbstractCyclicHandlingSupport<INamedElement> typeForNamedElementComputer = new AbstractCyclicHandlingSupport<INamedElement>() {
    @Override
    protected IType doComputation(final INamedElement element) {
      return typeForNamedElement(element);
    }

    @Override
    protected IType doHandleCyclicCall(final INamedElement element) {
      return handleCycleGetTypeForNamedElement(element);
    }

    @Override
    protected EObject getPrimaryEObject(final INamedElement element) {
      return element;
    }
  };

  /**
   * Handles the computation of getTypeForNamedElement when a cycle is encountered.
   *
   * @param element
   *          the named element to get the type for
   * @return the type of {@code element} if it can be computed; otherwise null.
   */
  protected IType handleCycleGetTypeForNamedElement(final INamedElement element) {
    return null;
  }

  /**
   * Internal method for computing the type of a named element.
   * <p>
   * Should be overridden by subclasses.
   * </p>
   *
   * @param element
   *          the named element to compute the type for
   * @return the type of {@code element}
   */
  protected IType typeForNamedElement(final INamedElement element) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IType getTypeForNamedElement(final INamedElement element) {
    if (providesTypeForNamedElement(element)) {
      return computeType("getTypeForNamedElement", element, typeForNamedElementComputer); //$NON-NLS-1$
    }
    ITypeProvider provider = getTypeProviderFor(element);
    if (provider != null && provider != this) {
      return provider.getTypeForNamedElement(element);
    }
    return null;
  }

  /*
   * --------------------------------------------------------------------------
   * Interface for re-dispatching to other type providers.
   * <p>
   * A language may reference expressions or named elements from other languages. For
   * example, AvqScript can reference elements in a PL/SQL package. Each language has
   * its own type provider and this type provider can delegate "get" functionality to
   * another language's type provider.
   * <p>
   * The "get" methods of {@link AbstractTypeProvider} first ask "this" implementing subclass
   * if it provides for the expression or element. If so then it delegates the type computation
   * to the subclass. If the subclass does not provide for the expression or element it will
   * ask the subclass for a type provider for that expression or element. If the subclass
   * supplies a type provider then it will re-dispatch to that type provider.
   * <p>
   * Note - we assume that there are no cyclic type-definition dependencies between languages.
   * <p>
   * TODO - if or when the type system supplies a central dispatching function then use that.
   * It may actually be more efficient to have a subclass locate type providers for re-dispatching
   * because the type provider for a given language must know what other languages it can
   * reference.
   */

  /**
   * Determines if this type provider provides a type for the given expression.
   *
   * @param expression
   *          the expression to provide the type for
   * @return true if this type provider provides a type for <code>expression</code>
   */
  protected abstract boolean providesTypeFor(IExpression expression);

  /**
   * Determines if this type provider provides an expected type for the given expression.
   *
   * @param expression
   *          the expression to provide the expected type for
   * @return true if this type provider provides an expected type for <code>expression</code>
   */
  protected abstract boolean providesExpectedTypeFor(IExpression expression);

  /**
   * Determines if this type provider provides a type for the given named element.
   *
   * @param element
   *          the named element to provide the type for.
   * @return true if this type provider provides a type for a named <code>element</code>
   */
  protected abstract boolean providesTypeForNamedElement(INamedElement element);

  /**
   * Gets the type provider for a given object when one of the "providesX" methods above.
   *
   * @param eObject
   *          the object to get the provider for
   * @return the type provider for {@code eObject} or {@code null} if it is not known.
   */
  protected ITypeProvider getTypeProviderFor(final EObject eObject) {
    return null;
  }

}
