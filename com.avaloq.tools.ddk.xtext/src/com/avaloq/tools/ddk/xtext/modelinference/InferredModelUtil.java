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
package com.avaloq.tools.ddk.xtext.modelinference;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;


/**
 * Utility methods for inferred models.
 */
public class InferredModelUtil {

  protected InferredModelUtil() { // No public constructor for utility class
  }

  /**
   * Gets the model associations corresponding to an EObject.
   * <p>
   * Model associations are maintained per resource. This returns the model associations for the EObject's containing resource.
   * </p>
   *
   * @param eObject
   *          the object to get the model associations from
   * @return the model associations for {@code eObject} or null if there aren't any
   */
  public static IInferredModelAssociations getModelAssociations(final EObject eObject) {
    if (eObject != null) {
      Resource resource = eObject.eResource();
      if (resource instanceof XtextResource) {
        return ((XtextResource) resource).getResourceServiceProvider().get(IInferredModelAssociations.class);
      }
    }
    return null;
  }

  /**
   * Gets an inferred model element of a given type for a given source.
   *
   * @param <T>
   *          the type of inferred model element to return
   * @param source
   *          the source of the inferred model element. Must not be null.
   * @param clazz
   *          the class of the inferred model element to return
   * @return the inferred model element or null if there is none.
   */
  @SuppressWarnings("unchecked")
  public static <T extends EObject> T getInferredModelElement(final EObject source, final Class<T> clazz) {
    IInferredModelAssociations modelAssociations = getModelAssociations(source);
    if (modelAssociations != null) {
      for (EObject eObj : modelAssociations.getInferredModelElements(source)) {
        if (clazz.isInstance(eObj)) {
          return (T) eObj;
        }
      }
    }
    return null;
  }

  /**
   * Gets all inferred model elements of a given type for a given source.
   *
   * @param <T>
   *          the type of inferred model elements to return
   * @param source
   *          the source of the inferred model elements. Must not be {@code null}.
   * @param clazz
   *          the class of the inferred model elements to return
   * @return the collection of inferred model elements or an empty set if there are none.
   */
  @SuppressWarnings("unchecked")
  public static <T extends EObject> Collection<T> getInferredModelElements(final EObject source, final Class<T> clazz) {
    IInferredModelAssociations modelAssociations = getModelAssociations(source);
    if (modelAssociations != null) {
      Collection<EObject> inferredModelElements = modelAssociations.getInferredModelElements(source);
      return (Collection<T>) Collections2.filter(inferredModelElements, new Predicate<EObject>() {
        @Override
        public boolean apply(final EObject input) {
          return clazz.isInstance(input);
        }
      });
    }
    return Collections.emptySet();
  }

  /**
   * Gets the primary source model element that inferred a given EObject, if there is one.
   *
   * @param eObject
   *          the object to get the primary source for
   * @return the primary source model element for {@code eObject} or null if there isn't one
   */
  public static EObject getPrimarySource(final EObject eObject) {
    IInferredModelAssociations modelAssociations = getModelAssociations(eObject);
    if (modelAssociations != null) {
      return modelAssociations.getPrimarySourceModelElement(eObject);
    } else {
      return null;
    }
  }
}
