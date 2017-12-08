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

package com.avaloq.tools.ddk.xtext.nodemodel.serialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.resource.XtextResource;


/**
 * Fix for issue https://github.com/eclipse/xtext-core/issues/65 (serialization of transient features). Can be deleted after upgrade to Xtext 2.11 or later.
 */
public class FixedDeserializationConversionContext extends DeserializationConversionContext {

  public FixedDeserializationConversionContext(final XtextResource xr, final String completeContent) throws IOException {
    super(xr, completeContent);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void fillIdToEObjectMap(final Resource resource) {
    List<EObject> idToEObjectMap = null;
    try {
      Field field = DeserializationConversionContext.class.getDeclaredField("idToEObjectMap"); //$NON-NLS-1$
      field.setAccessible(true);
      idToEObjectMap = (List<EObject>) field.get(this);
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      throw new IllegalStateException("Could not read 'idToEObjectMap' field", e); //$NON-NLS-1$
    }
    FixedSerializationConversionContext.fillIdToEObjectMap(resource, idToEObjectMap);
  }

}
