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

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newLinkedHashSet;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.IAcceptor;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Manager for associations between source and inferred model elements.
 * Based on {@link org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator}
 */
@Singleton
public class InferredModelAssociator implements IInferredModelAssociations, IInferredModelAssociator, IDerivedStateComputer {

  @Inject
  private IReferableElementsUnloader.GenericUnloader unloader;

  @Inject
  private IModelInferrer inferrer;

  /**
   * An adapter that holds the mapping between source- and inferred-model elements.
   */
  protected static class Adapter extends AdapterImpl {
    private final ListMultimap<EObject, EObject> sourceToInferredModelMap = LinkedListMultimap.create();

    @Override
    public boolean isAdapterForType(final Object type) {
      return Adapter.class == type;
    }

  }

  /**
   * Get the adaptor mapping for the resource and install it if not already installed.
   *
   * @param resource
   *          the resource
   * @return the adaptor mapping
   */
  protected Adapter getOrInstall(final Resource resource) {
    if (!(resource instanceof XtextResource)) {
      return new Adapter();
    }
    // if (!languageName.equals(((XtextResource) resource).getLanguageName())) {
    // return new Adapter();
    // }
    Adapter adapter = (Adapter) EcoreUtil.getAdapter(resource.eAdapters(), Adapter.class);
    if (adapter == null) {
      adapter = new Adapter();
      resource.eAdapters().add(adapter);
    }
    return adapter;
  }

  /**
   * Get the source- to inferred-model element map for the resource.
   *
   * @param resource
   *          the resource
   * @return the map
   */
  protected ListMultimap<EObject, EObject> getSourceToInferredModelMap(final Resource resource) {
    return getOrInstall(resource).sourceToInferredModelMap;
  }

  /** {@inheritDoc} */
  @Override
  public void associate(final EObject sourceModelElement, final EObject inferredModelElement) {
    if (sourceModelElement != null) {
      ListMultimap<EObject, EObject> map = getSourceToInferredModelMap(sourceModelElement.eResource());
      map.put(sourceModelElement, inferredModelElement);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void associatePrimary(final EObject sourceModelElement, final EObject inferredModelElement) {
    if (sourceModelElement != null) {
      ListMultimap<EObject, EObject> map = getSourceToInferredModelMap(sourceModelElement.eResource());
      if (map.containsKey(sourceModelElement)) {
        map.get(sourceModelElement).add(0, inferredModelElement);
      } else {
        map.put(sourceModelElement, inferredModelElement);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public Set<EObject> getInferredModelElements(final EObject sourceModelElement) {
    if (sourceModelElement != null) {
      final ListMultimap<EObject, EObject> sourceToInferredModelMap = getSourceToInferredModelMap(sourceModelElement.eResource());
      final List<EObject> elements = sourceToInferredModelMap.get(sourceModelElement);
      return newLinkedHashSet(elements);
    } else {
      return Collections.emptySet();
    }
  }

  /** {@inheritDoc} */
  @Override
  public Set<EObject> getSourceModelElements(final EObject inferredModelElement) {
    if (inferredModelElement != null) {
      ListMultimap<EObject, EObject> map = getSourceToInferredModelMap(inferredModelElement.eResource());
      Set<EObject> sourceElements = newLinkedHashSet();
      for (Map.Entry<EObject, EObject> entry : map.entries()) {
        if (entry.getValue() == inferredModelElement) {
          sourceElements.add(entry.getKey());
        }
      }
      return sourceElements;
    } else {
      return Collections.emptySet();
    }
  }

  /** {@inheritDoc} */
  @Override
  public EObject getPrimarySourceModelElement(final EObject inferredModelElement) {
    if (inferredModelElement != null) {
      ListMultimap<EObject, EObject> map = getSourceToInferredModelMap(inferredModelElement.eResource());
      for (Map.Entry<EObject, EObject> entry : map.entries()) {
        if (entry.getValue() == inferredModelElement) {
          return entry.getKey();
        }
      }
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public void installDerivedState(final DerivedStateAwareResource resource, final boolean isPreLinkingPhase) {
    if (resource.getContents().isEmpty()) {
      return;
    }
    EObject eObject = resource.getContents().get(0);
    inferrer.inferTargetModel(eObject, new IAcceptor<EObject>() {
      @Override
      public void accept(final EObject t) {
        if (t != null) {
          resource.getContents().add(t);
        }
      }
    }, isPreLinkingPhase);
  }

  /** {@inheritDoc} */
  @Override
  public void discardDerivedState(final DerivedStateAwareResource resource) {
    List<EObject> derived = newArrayList();
    EList<EObject> resourcesContentsList = resource.getContents();
    for (int i = 1; i < resourcesContentsList.size(); i++) {
      EObject eObject = resourcesContentsList.get(i);
      unloader.unloadRoot(eObject);
      derived.add(eObject);
    }
    resourcesContentsList.removeAll(derived);
    getSourceToInferredModelMap(resource).clear();
  }

}
