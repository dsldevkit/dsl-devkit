/*******************************************************************************
 * Copyright (c) 2017 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList.FeatureIterator;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;
import org.eclipse.xtext.resource.XtextResource;


/**
 * Slightly improved performance over the Xtext standard implementation.
 * <p>
 * For consistency follows the same naming scheme as {@link ProxyAwareDeserializationConversionContext}.
 */
class ProxyAwareSerializationConversionContext extends SerializationConversionContext {

  ProxyAwareSerializationConversionContext(final XtextResource resource) {
    super(resource);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void fillEObjectToIdMap(final Resource resource) {
    List<EObject> idToEObjectMap = new ArrayList<EObject>();

    fillIdToEObjectMap(resource, idToEObjectMap);

    Map<EObject, Integer> eObjectToIdMap;
    try {
      Field field = SerializationConversionContext.class.getDeclaredField("eObjectToIdMap"); //$NON-NLS-1$
      field.setAccessible(true);
      eObjectToIdMap = (Map<EObject, Integer>) field.get(this);
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      throw new IllegalStateException("Could not read 'eObjectToIdMap' field", e); //$NON-NLS-1$
    }

    for (int id = 0; id < idToEObjectMap.size(); ++id) {
      eObjectToIdMap.put(idToEObjectMap.get(id), id);
    }
  }

  /**
   * Fixed method from {@link org.eclipse.xtext.nodemodel.serialization.SerializationUtil}.
   *
   * @param resource
   *          resource
   * @param map
   *          map
   */
  static void fillIdToEObjectMap(final Resource resource, final List<EObject> map) {
    if (!resource.getContents().isEmpty()) {
      fillIdToEObjectMap(resource.getContents().get(0), map);
    }
  }

  /**
   * Fixed method from {@link org.eclipse.xtext.nodemodel.serialization.SerializationUtil}.
   *
   * @param eObject
   *          eObject
   * @param map
   *          map
   */
  static void fillIdToEObjectMap(final EObject eObject, final List<EObject> map) {
    map.add(eObject);

    for (FeatureIterator<EObject> it = (FeatureIterator<EObject>) eObject.eContents().iterator(); it.hasNext();) {
      EObject child = it.next();
      if (!it.feature().isTransient()) {
        fillIdToEObjectMap(child, map);
      }
    }
  }

}
