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

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.IAcceptor;

import com.avaloq.tools.ddk.xtext.scoping.ImplicitReferencesAdapter;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;


/**
 * Manager for associations between source and inferred model elements.
 * Registers implicit inference dependencies.
 * This is needed for those cases when a resource loads others to perform inference.
 * Registering dependencies will ensure that the inference will be re-triggered once one (or many) of those 'other' resources are changed.
 * Based on {@link org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator}
 */
@Singleton
public class InferredModelAssociator implements IInferredModelAssociations, IInferredModelAssociator, IDerivedStateComputer {

  private static final Logger LOGGER = Logger.getLogger(InferredModelAssociator.class);

  private final ThreadLocal<Deque<Resource>> inferenceStackLocal = ThreadLocal.withInitial(ArrayDeque::new);

  @Inject
  private IReferableElementsUnloader.GenericUnloader unloader;

  @Inject
  private Provider<IModelInferrer> inferrerProvider;

  /**
   * An adapter that holds the mapping between source- and inferred-model elements.
   */
  public static class Adapter extends AdapterImpl {
    private final Map<EObject, Deque<EObject>> sourceToInferredModelMap = newLinkedHashMapWithCapacity(40);
    private final Map<EObject, Deque<EObject>> inferredModelToSourceMap = newLinkedHashMapWithCapacity(40);

    @Override
    public boolean isAdapterForType(final Object type) {
      return Adapter.class == type;
    }

    public Map<EObject, Deque<EObject>> getSourceToInferredModelMap() {
      return sourceToInferredModelMap;
    }

    public Map<EObject, Deque<EObject>> getInferredModelToSourceMap() {
      return inferredModelToSourceMap;
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
  protected Map<EObject, Deque<EObject>> getSourceToInferredModelMap(final Resource resource) {
    return getOrInstall(resource).getSourceToInferredModelMap();
  }

  /**
   * Get the inferred-model- to source-model element map for the resource.
   *
   * @param resource
   *          the resource
   * @return the map
   */
  protected Map<EObject, Deque<EObject>> getInferredModelToSourceMap(final Resource resource) {
    return getOrInstall(resource).getInferredModelToSourceMap();
  }

  /** {@inheritDoc} */
  @Override
  public void associate(final EObject sourceModelElement, final EObject inferredModelElement) {
    if (sourceModelElement != null) {
      Resource resource = sourceModelElement.eResource();
      Map<EObject, Deque<EObject>> sourceToInferredModelMap = getSourceToInferredModelMap(resource);
      sourceToInferredModelMap.computeIfAbsent(sourceModelElement, k -> new ArrayDeque<>()).add(inferredModelElement);
      Map<EObject, Deque<EObject>> inferredModelToSourceMap = getInferredModelToSourceMap(resource);
      inferredModelToSourceMap.computeIfAbsent(inferredModelElement, k -> new ArrayDeque<>()).add(sourceModelElement);
      addInferenceDependency(sourceModelElement);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void associatePrimary(final EObject sourceModelElement, final EObject inferredModelElement) {
    if (sourceModelElement != null) {
      Resource resource = sourceModelElement.eResource();
      Map<EObject, Deque<EObject>> sourceToInferredModelMap = getSourceToInferredModelMap(resource);
      sourceToInferredModelMap.computeIfAbsent(sourceModelElement, k -> new ArrayDeque<>()).addFirst(inferredModelElement);
      Map<EObject, Deque<EObject>> inferredModelToSourceMap = getInferredModelToSourceMap(resource);
      inferredModelToSourceMap.computeIfAbsent(inferredModelElement, k -> new ArrayDeque<>()).addFirst(sourceModelElement);
      addInferenceDependency(sourceModelElement);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Set<EObject> getInferredModelElements(final EObject sourceModelElement) {
    if (sourceModelElement == null) {
      return Collections.emptySet();
    }
    Map<EObject, Deque<EObject>> map = getSourceToInferredModelMap(sourceModelElement.eResource());
    Deque<EObject> result = map.get(sourceModelElement);
    return result != null ? ImmutableSet.copyOf(result) : Collections.emptySet();
  }

  /** {@inheritDoc} */
  @Override
  public Set<EObject> getSourceModelElements(final EObject inferredModelElement) {
    if (inferredModelElement == null) {
      return Collections.emptySet();
    }
    Map<EObject, Deque<EObject>> map = getInferredModelToSourceMap(inferredModelElement.eResource());
    Deque<EObject> result = map.get(inferredModelElement);
    return result != null ? ImmutableSet.copyOf(result) : Collections.emptySet();
  }

  /** {@inheritDoc} */
  @Override
  public EObject getPrimarySourceModelElement(final EObject inferredModelElement) {
    if (inferredModelElement == null) {
      return null;
    }
    Map<EObject, Deque<EObject>> map = getInferredModelToSourceMap(inferredModelElement.eResource());
    Deque<EObject> result = map.get(inferredModelElement);
    return result != null && !result.isEmpty() ? result.getFirst() : null;
  }

  /** {@inheritDoc} */
  @Override
  public final void installDerivedState(final DerivedStateAwareResource resource, final boolean isPreLinkingPhase) {
    if (resource.getContents().isEmpty()) {
      return;
    }
    EObject eObject = resource.getContents().get(0);
    Deque<Resource> inferenceStack = getInferenceStack();
    inferenceStack.push(resource);
    try {
      inferTargetModel(eObject, createAcceptor(resource), isPreLinkingPhase);
      // CHECKSTYLE:OFF
    } catch (RuntimeException e) {
      // CHECKSTYLE:ON
      LOGGER.error("Failed to install derived state for resource " + resource.getURI(), e); //$NON-NLS-1$
    } finally {
      inferenceStack.pop();
    }
  }

  /**
   * Performs the inference for the given model element.
   *
   * @param eObject
   *          model element, must not be {@code null}
   * @param acceptor
   *          inferred elements acceptor, must not be {@code}
   * @param isPreLinkingPhase
   *          designates the current phase
   */
  protected void inferTargetModel(final EObject eObject, final IAcceptor<EObject> acceptor, final boolean isPreLinkingPhase) {
    IModelInferrer inferrer = inferrerProvider.get();
    inferrer.inferTargetModel(eObject, acceptor, isPreLinkingPhase);
  }

  protected Deque<Resource> getInferenceStack() {
    return inferenceStackLocal.get();
  }

  /**
   * Registers a dependency from the resource the inference was originally trigger for to the currently processed one.
   *
   * @param sourceModelElement
   *          primary model element, must not be {@code null}
   */
  private void addInferenceDependency(final EObject sourceModelElement) {
    Resource originalResource = getInferenceStack().peekFirst();
    if (originalResource != null) {
      final URI originalResourceUri = originalResource.getURI();
      final URI currentResourceUri = sourceModelElement.eResource().getURI();
      if (originalResourceUri != null && currentResourceUri != null && !originalResourceUri.equals(currentResourceUri)) {
        ImplicitReferencesAdapter.findOrCreate(originalResource).addInferenceDependency(currentResourceUri);
      }
    }
  }

  /**
   * Creates a new acceptor for the given resource.
   *
   * @param resource
   *          resource, must not be {@code null}
   * @return new acceptor, never {@code null}
   */
  protected IAcceptor<EObject> createAcceptor(final DerivedStateAwareResource resource) {
    return new IAcceptor<EObject>() {
      private InferenceContainer container;

      @Override
      public void accept(final EObject t) {
        if (t != null) {
          if (container == null) {
            container = ModelInferenceFactory.eINSTANCE.createInferenceContainer();
            resource.getContents().add(container);
          }
          container.getContents().add(t);
        }
      }
    };
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
    getInferredModelToSourceMap(resource).clear();
  }

  private static <K, V> Map<K, V> newLinkedHashMapWithCapacity(final int capacity) {
    return new LinkedHashMap<K, V>(capacity) {
      private static final long serialVersionUID = 1L;

      @Override
      public void clear() {
        if (isEmpty()) {
          return;
        }
        super.clear();
      }
    };
  }

}
