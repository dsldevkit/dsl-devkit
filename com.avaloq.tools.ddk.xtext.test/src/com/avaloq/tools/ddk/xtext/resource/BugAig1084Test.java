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
package com.avaloq.tools.ddk.xtext.resource;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;


class BugAig1084Test {

  /**
   * Test that recursive calls to {@link ResourceDescription2#getLookUp()} by {@link ResourceDescription2#computeExportedObjects()} do not cause
   * stack-overflow.
   */
  @Test
  void recursiveLookUp() {
    Resource resource = org.mockito.Mockito.mock(Resource.class);
    EList<Adapter> emptyEList = new BasicEList<Adapter>();
    org.mockito.Mockito.when(resource.eAdapters()).thenReturn(emptyEList);
    IResourceScopeCache cache = new OnChangeEvictingCache();
    new ResourceDescription2(resource, null, cache) {
      @Override
      protected List<IEObjectDescription> computeExportedObjects() {
        return Lists.newArrayList(getLookUp().getExportedObjects());
      }
    }.getExportedObjects();
  }
}
