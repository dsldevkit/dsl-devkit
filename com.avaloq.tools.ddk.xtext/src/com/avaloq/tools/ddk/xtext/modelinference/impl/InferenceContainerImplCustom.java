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

package com.avaloq.tools.ddk.xtext.modelinference.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.avaloq.tools.ddk.xtext.modelinference.IInferredElementFragmentProvider;
import com.avaloq.tools.ddk.xtext.modelinference.ModelInferencePackage;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


/**
 * Custom class implementing EOperations as well as overriding {@link #getContents()} to return a specialized implementation, which assigns and tracks the
 * fragment segments for the contained objects.
 */
public class InferenceContainerImplCustom extends InferenceContainerImpl {

  private static final Logger LOG = Logger.getLogger(InferenceContainerImplCustom.class);

  private final BiMap<String, EObject> fragmentToObjectMap = HashBiMap.create();
  private final Set<String> fragmentSet = Collections.unmodifiableSet(fragmentToObjectMap.keySet());
  private final Map<EObject, String> objectToFragmentMap = fragmentToObjectMap.inverse();
  private Map<String, AtomicInteger> collisionFragmentIndexMap;

  private IInferredElementFragmentProvider fragmentProvider;

  protected InferenceContainerImplCustom() {
    eAdapters().add(new AdapterImpl() {
      @SuppressWarnings("unchecked")
      @Override
      public void notifyChanged(final Notification msg) {
        if (msg.getFeature() != ModelInferencePackage.Literals.INFERENCE_CONTAINER__CONTENTS || eResource() == null) {
          return;
        }
        switch (msg.getEventType()) {
        case Notification.ADD:
          addFragmentMapping((EObject) msg.getNewValue());
          break;
        case Notification.ADD_MANY:
          for (EObject newObject : (List<EObject>) msg.getNewValue()) {
            addFragmentMapping(newObject);
          }
          break;
        case Notification.REMOVE:
          objectToFragmentMap.remove(msg.getOldValue());
          break;
        case Notification.REMOVE_MANY:
          for (EObject oldObject : (List<EObject>) msg.getOldValue()) {
            objectToFragmentMap.remove(oldObject);
          }
          break;
        case Notification.MOVE:
        case Notification.SET:
          throw new UnsupportedOperationException("Operations move() and set() are not supported."); //$NON-NLS-1$
        default:
          break;
        }
      }
    });
  }

  /**
   * Adds a fragment mapping for the given EObject, once it has been added.
   *
   * @param object
   *          object which was added, must not be {@code null}
   */
  private void addFragmentMapping(final EObject object) {
    String fragmentSegment = getFragmentProvider().getFragmentSegment(object, fragmentSet);
    if (fragmentSegment == null) {
      // fallback: use positional fragment segment
      fragmentSegment = Integer.toString(getContents().size() - 1);
    }
    String key = fragmentSegment;

    while (fragmentToObjectMap.putIfAbsent(key, object) != null) {
      // append sequence number suffix in case fragment is already assigned (collision)
      if (collisionFragmentIndexMap == null) {
        collisionFragmentIndexMap = new HashMap<>();
      }
      AtomicInteger collisionFragmentIndex = collisionFragmentIndexMap.computeIfAbsent(fragmentSegment, v -> new AtomicInteger(1));
      key = fragmentSegment + '.' + collisionFragmentIndex.getAndIncrement();
    }
    ((InternalEList<String>) getFragments()).addUnique(key);
  }

  /**
   * Helper method to lazily get the {@link IInferredElementFragmentProvider} service associated with this resource.
   *
   * @return {@link IInferredElementFragmentProvider} implementation, never {@code null}
   */
  private IInferredElementFragmentProvider getFragmentProvider() {
    if (fragmentProvider == null) {
      fragmentProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(eResource().getURI()).get(IInferredElementFragmentProvider.class);
    }
    return fragmentProvider;
  }

  @Override
  public String getFragmentSegment(final EObject object) {
    ensureInitialized();
    return objectToFragmentMap.get(object);
  }

  @Override
  public EObject getEObject(final String fragmentSegment) {
    ensureInitialized();
    return fragmentToObjectMap.get(fragmentSegment);
  }

  /**
   * Ensures that the URI fragments have been initialized. This could for instance be a problem during deserialization
   */
  private void ensureInitialized() {
    if (fragmentToObjectMap.isEmpty()) {
      if (eResource() == null) {
        LOG.warn("InferenceContainer must be contained in a resource to compute URI fragments."); //$NON-NLS-1$
      } else {
        int size = getContents().size();
        if (getFragments().size() != size) {
          LOG.warn("Fragments need to be recomputed for the InferenceContainer in " + eResource().getURI()); //$NON-NLS-1$
          getFragments().clear();
          for (EObject element : getContents()) {
            addFragmentMapping(element);
          }
        } else {
          for (int i = 0; i < size; i++) {
            fragmentToObjectMap.put(getFragments().get(i), getContents().get(i));
          }
        }
      }
    }
  }

}
