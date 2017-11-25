/*******************************************************************************
 * Copyright (c) 2017 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.nodemodel.serialization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;
import org.eclipse.xtext.resource.XtextResource;


/**
 * Fix for issue https://github.com/eclipse/xtext-core/issues/65 (serialization of transient features). Can be deleted after upgrade to Xtext 2.11 or later.
 */
public class FixedSerializationConversionContext extends SerializationConversionContext {

  public FixedSerializationConversionContext(final XtextResource resource) {
    super(resource);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void fillEObjectToIdMap(final Resource resource) {
    ArrayList<EObject> idToEObjectMap = new ArrayList<EObject>();

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
  private static void fillIdToEObjectMap(final Resource resource, final List<EObject> map) {
    TreeIterator<EObject> allContents = EcoreUtil.getAllContents(resource, false);

    if (allContents.hasNext()) {
      EObject root = allContents.next();
      fillIdToEObjectMap(root, map);
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
  private static void fillIdToEObjectMap(final EObject eObject, final List<EObject> map) {
    if (eObject.eContainingFeature() == null || !eObject.eContainingFeature().isTransient()) {
      map.add(eObject);
      EList<EObject> eContents = eObject.eContents();

      for (EObject child : eContents) {
        fillIdToEObjectMap(child, map);
      }
    }
  }
}
