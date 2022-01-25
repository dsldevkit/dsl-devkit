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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.Collections;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;


/**
 * Utilities class to create scoped elements from EObjects. Every EObject can be associated with one or multiple names in the
 * resulting scope.
 */
public final class EObjectDescriptions {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(NameFunctions.class);

  /** No public instantiation. */
  private EObjectDescriptions() {
    // Utility class.
  }

  /**
   * Returns scoped elements for all given objects by using the given features as names. The result is first ordered by name
   * feature then by object. If a feature is not valid for a given object or returns null no corresponding scoped element will be
   * included in the result.
   *
   * @param <T>
   *          the generic type
   * @param objects
   *          model objects
   * @param nameFeatures
   *          name features
   * @return scoped element iterable
   */

  public static <T extends EObject> Iterable<IEObjectDescription> all(final Iterable<T> objects, final EStructuralFeature... nameFeatures) {
    return all(objects, NameFunctions.fromFeatures(nameFeatures));
  }

  /**
   * Returns scoped elements for all given objects by applying all given name functions. The result is first ordered by function
   * then by object. If a function returns null for a given object no corresponding scoped element will be included in the result.
   *
   * @param <T>
   *          type of model objects
   * @param objects
   *          model objects iterable
   * @param nameFunction
   *          the name function
   * @return scoped element iterable
   * @see #all(Iterable, Iterable)
   */
  public static <T extends EObject> Iterable<IEObjectDescription> all(final Iterable<T> objects, final INameFunction nameFunction) {
    return all(objects, Collections.<INameFunction> singletonList(nameFunction));
  }

  /**
   * Returns scoped elements for all given objects by applying all given name functions. The result is first ordered by function
   * then by object. If a function returns null for a given object no corresponding scoped element will be included in the result.
   *
   * @param <T>
   *          type of model objects
   * @param objects
   *          model objects iterable
   * @param nameFunctions
   *          list of name functions
   * @return scoped element iterable
   */

  public static <T extends EObject> Iterable<IEObjectDescription> all(final Iterable<T> objects, final Iterable<INameFunction> nameFunctions) {
    return Iterables.concat(Iterables.transform(nameFunctions, new Function<INameFunction, Iterable<IEObjectDescription>>() {
      @Override
      public Iterable<IEObjectDescription> apply(final INameFunction param) {
        return Iterables.filter(Iterables.transform(objects, new Function<T, IEObjectDescription>() {
          @Override
          public IEObjectDescription apply(final T from) {
            if (from == null) {
              return null;
            }
            final QualifiedName name = param.apply(from);
            return (name == null) ? null : EObjectDescription.create(name, from); // NOPMD
          }
        }), Predicates.notNull());
      }
    }));
  }

  /**
   * Returns scoped elements for all given objects by applying all given name functions. The result is first ordered by function
   * then by object. If a function returns null for a given object no corresponding scoped element will be included in the result.
   *
   * @param <T>
   *          type of model objects
   * @param objects
   *          model objects iterable
   * @param nameFunctions
   *          list of name functions
   * @return scoped element iterable
   */
  public static <T extends IEObjectDescription> Iterable<IEObjectDescription> map(final Iterable<T> objects, final Iterable<INameFunction> nameFunctions) {
    return Iterables.concat(Iterables.transform(nameFunctions, new Function<INameFunction, Iterable<IEObjectDescription>>() {
      @Override
      public Iterable<IEObjectDescription> apply(final INameFunction param) {
        return Iterables.filter(Iterables.transform(objects, new Function<T, IEObjectDescription>() {
          @Override
          public IEObjectDescription apply(final T from) {
            try {
              if (from == null) {
                return null;
              }
              final QualifiedName name = param.apply(from);
              return (name == null) ? null : (name.equals(from.getName()) ? from : new AliasingEObjectDescription(name, from)); // NOPMD
              // CHECKSTYLE:OFF
            } catch (final Exception e) {
              // CHECKSTYLE:ON
              LOGGER.error(e.getMessage(), e);
              throw new WrappedException(e);
            }
          }
        }), Predicates.notNull());
      }
    }));
  }
}
