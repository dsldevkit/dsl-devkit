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
package com.avaloq.tools.ddk.xtext.test.jvmmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;


/**
 * Utility methods for inferred JVM models.
 */
public class InferredJvmModelUtil {

  /**
   * Creates a new instance of {@link InferredJvmModelUtil}.
   */
  protected InferredJvmModelUtil() { // No public constructor for utility class
  }

  /**
   * Returns the model associations corresponding to an EObject.
   * <p>
   * Model associations are maintained per resource. This returns the model associations for the EObject's containing resource.
   * </p>
   * 
   * @param eObject
   *          the object to get the model associations from, must not be {@code null}
   * @return the model associations for {@code eObject}, or null if there are none
   */
  public static IJvmModelAssociations getModelAssociations(final EObject eObject) {
    if (eObject != null) {
      final Resource resource = eObject.eResource();
      if (resource instanceof XtextResource) {
        return ((XtextResource) resource).getResourceServiceProvider().get(IJvmModelAssociations.class);
      }
    }
    return null;
  }

  /**
   * Returns an inferred model element of a given type for a given source element.
   * 
   * @param <T>
   *          the type of inferred model element to return
   * @param source
   *          the source of the inferred model element, must not be {@code null}
   * @param clazz
   *          the class of the inferred model element to return, must not be {@code null}
   * @return the inferred model element, or {@code null} if there is none
   */
  @SuppressWarnings("unchecked")
  public static <T extends EObject> T getInferredElement(final EObject source, final Class<T> clazz) {
    final IJvmModelAssociations modelAssociations = getModelAssociations(source);
    if (modelAssociations != null) {
      for (EObject eObj : modelAssociations.getJvmElements(source)) {
        if (clazz.isInstance(eObj)) {
          return (T) eObj;
        }
      }
    }
    return null;
  }

  /**
   * Returns an inferred element of specified name and type for a given source element.
   * 
   * @param sourceElement
   *          a source {@link EObject}, must not be {@code null}
   * @param name
   *          the name of the element, must not be {@code null}
   * @param clazz
   *          the type of the element, must not be {@code null}
   * @return an inferred element, or {@code null} if nothing has been found
   */
  public static EObject getInferredElement(final EObject sourceElement, final String name, final Class<? extends EObject> clazz) {
    final Collection<? extends EObject> inferredElements = getInferredElements(sourceElement, clazz);
    EObject target = null;
    for (final EObject object : inferredElements) {
      if (clazz.isAssignableFrom(object.getClass()) && object instanceof JvmIdentifiableElement
          && ((JvmIdentifiableElement) object).getQualifiedName().equals(name)) {
        target = EcoreUtil.resolve(object, sourceElement);
        break;
      }
    }
    return target;
  }

  /**
   * Returns all inferred model elements of a given type for a given source element.
   * 
   * @param <T>
   *          the type of inferred model elements to return
   * @param source
   *          the source of the inferred model elements, must not be {@code null}
   * @param clazz
   *          the class of the inferred model elements to return, must not be {@code null}
   * @return the set of inferred model elements or an empty set if there are none
   */
  @SuppressWarnings("unchecked")
  public static <T extends EObject> Set<T> getInferredElements(final EObject source, final Class<T> clazz) {
    final IJvmModelAssociations modelAssociations = getModelAssociations(source);
    if (modelAssociations != null) {
      final Set<EObject> inferredModelElements = modelAssociations.getJvmElements(source);
      return (Set<T>) Sets.filter(inferredModelElements, new Predicate<EObject>() {
        public boolean apply(final EObject input) {
          return clazz.isInstance(input);
        }
      });
    }
    return Collections.emptySet();
  }

  /**
   * Returns all inferred model elements for a given source element.
   * 
   * @param source
   *          the source of the inferred model elements, must not be {@code null}
   * @return the set of inferred model elements or an empty set if there are none
   */
  public static Set<EObject> getInferredElements(final EObject source) {
    final IJvmModelAssociations modelAssociations = getModelAssociations(source);
    if (modelAssociations != null) {
      return modelAssociations.getJvmElements(source);
    }
    return Collections.emptySet();
  }

  /**
   * Gets the primary source model element that inferred a given {@link EObject}, if there is one.
   * 
   * @param eObject
   *          the object to get the primary source for, must not be {@code null}
   * @return the primary source model element for the given {link EObject}, or null if there is none
   */
  public static EObject getPrimarySource(final EObject eObject) {
    final IJvmModelAssociations modelAssociations = getModelAssociations(eObject);
    if (modelAssociations != null) {
      return modelAssociations.getPrimarySourceElement(eObject);
    } else {
      return null;
    }
  }
}

