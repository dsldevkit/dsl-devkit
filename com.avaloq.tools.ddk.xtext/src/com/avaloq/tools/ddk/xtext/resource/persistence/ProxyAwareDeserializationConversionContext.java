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

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.resource.XtextResource;


/**
 * Implementation which supports deserializing a node model into a resource with a {@link ProxyCompositeNode proxy node model}.
 */
public class ProxyAwareDeserializationConversionContext extends DeserializationConversionContext {

  public ProxyAwareDeserializationConversionContext(final XtextResource xr, final String completeContent) throws IOException {
    super(xr, completeContent);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void fillIdToEObjectMap(final Resource resource) {
    List<EObject> idToEObjectMap = ProxyCompositeNode.uninstallProxyNodeModel(resource);
    try {
      Field field = DeserializationConversionContext.class.getDeclaredField("idToEObjectMap"); //$NON-NLS-1$
      field.setAccessible(true);
      if (idToEObjectMap != null) {
        field.set(this, idToEObjectMap);
      } else {
        List<EObject> map = (List<EObject>) field.get(this);
        ProxyAwareSerializationConversionContext.fillIdToEObjectMap(resource, map);
      }
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      throw new IllegalStateException("Could not read 'idToEObjectMap' field", e); //$NON-NLS-1$
    }
  }

}
