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
package com.avaloq.tools.ddk.xtext.resource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.linking.impl.ImportedNamesAdapter;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.resource.impl.EObjectDescriptionLookUp;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.IResourceScopeCache;

import com.avaloq.tools.ddk.xtext.build.BuildPhases;
import com.avaloq.tools.ddk.xtext.linking.ILazyLinkingResource2;
import com.avaloq.tools.ddk.xtext.linking.ImportedNamesTypesAdapter;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescription2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;


/**
 * Resource description with support for partial export and for fingerprinting.
 */
public class ResourceDescription2 extends DefaultResourceDescription implements IResourceDescription2 {

  private static final Logger LOG = Logger.getLogger(ResourceDescription2.class);

  private final IDefaultResourceDescriptionStrategy strategy;

  public ResourceDescription2(final Resource resource, final IDefaultResourceDescriptionStrategy strategy, final IResourceScopeCache cache) {
    super(resource, strategy, cache);
    this.strategy = strategy;
  }

  @Override
  protected EObjectDescriptionLookUp getLookUp() {
    if (lookup == null) {
      lookup = new PatternAwareEObjectDescriptionLookUp(Collections.emptyList());
      lookup.setExportedObjects(computeExportedObjects());
    }
    return lookup;
  }

  @Override
  protected List<IEObjectDescription> computeExportedObjects() {
    if (!getResource().isLoaded()) {
      try {
        getResource().load(null);
      } catch (IOException e) {
        LOG.error(e.getMessage(), e);
        return Collections.emptyList();
      }
    }
    // Maybe we need to install the derived state first. Installing/discarding the derived state will clear the resource cache, so we must
    // make sure at least that the resource description is re-added.
    LazyLinkingResource resource = (LazyLinkingResource) getResource();
    boolean doInitialize = (resource instanceof ILazyLinkingResource2) && !((ILazyLinkingResource2) resource).isInitialized();
    final ResourceDescription2 self = this;
    try {
      if (doInitialize) {
        resource.eSetDeliver(false);
        ((ILazyLinkingResource2) resource).installDerivedState(BuildPhases.isIndexing(resource));
        // Make sure we have at least this resource description itself in the resource cache.
        resource.getCache().get(AbstractCachingResourceDescriptionManager.CACHE_KEY, resource, () -> self);
      }
      return createDescriptions(resource);

    } finally {
      if (doInitialize) {
        // DerivedStateAwareResourceManager does discard the state. Should we do so, too?
        resource.eSetDeliver(true);
        // Make sure we have at least this resource description itself in the resource cache.
        resource.getCache().get(AbstractCachingResourceDescriptionManager.CACHE_KEY, resource, () -> self);
      }
    }
  }

  /**
   * Create EObjectDescriptions for exported objects.
   *
   * @param resource
   *          LazyLinkingResource
   * @return list of object descriptions
   */
  protected List<IEObjectDescription> createDescriptions(final LazyLinkingResource resource) {
    final ImmutableList.Builder<IEObjectDescription> exportedEObjects = ImmutableList.builder();
    IAcceptor<IEObjectDescription> acceptor = decorateExportedObjectsAcceptor(exportedEObjects::add);
    TreeIterator<EObject> allProperContents = EcoreUtil.getAllProperContents(resource, false);
    while (allProperContents.hasNext()) {
      EObject content = allProperContents.next();
      if (!strategy.createEObjectDescriptions(content, acceptor)) {
        allProperContents.prune();
      }
    }
    return exportedEObjects.build();
  }

  /**
   * Returns the acceptor to be used to accept the exported objects to be added to this resource description. The returned acceptor must call
   * {@link IAcceptor#accept(Object)} on the given {@code acceptor} for each object to be added.
   * <p>
   * The default implementation just returns {@code acceptor} itself.
   *
   * @param acceptor
   *          acceptor to be decorated, must not be {@code null}
   * @return acceptor, never {@code null}
   */
  protected IAcceptor<IEObjectDescription> decorateExportedObjectsAcceptor(final IAcceptor<IEObjectDescription> acceptor) {
    return acceptor;
  }

  @Override
  protected List<IReferenceDescription> computeReferenceDescriptions() {
    final ImmutableList.Builder<IReferenceDescription> referenceDescriptions = ImmutableList.builder();
    EcoreUtil2.resolveLazyCrossReferences(getResource(), CancelIndicator.NullImpl);
    Map<EObject, IEObjectDescription> eObject2exportedEObjects = createEObject2ExportedEObjectsMap(getExportedObjects());
    TreeIterator<EObject> contents = EcoreUtil.getAllProperContents(getResource(), true);
    while (contents.hasNext()) {
      EObject eObject = contents.next();
      URI exportedContainerURI = findExportedContainerURI(eObject, eObject2exportedEObjects);
      if (!strategy.createReferenceDescriptions(eObject, exportedContainerURI, referenceDescriptions::add)) {
        contents.prune();
      }
    }
    if (strategy instanceof AbstractResourceDescriptionStrategy) {
      ((AbstractResourceDescriptionStrategy) strategy).createImplicitReferenceDescriptions(getResource(), referenceDescriptions::add);
    }
    return referenceDescriptions.build();
  }

  /**
   * Figure out whether we do export objects of the given type at all.
   *
   * @param type
   *          to check
   * @return {@code true} if this resource description can basically export objects of the given type; {@code false} otherwise.
   */
  private boolean isExportedEClass(final EClass type) {
    if (type == EcorePackage.Literals.EOBJECT || !(strategy instanceof AbstractResourceDescriptionStrategy)) {
      // Anything is an EObject; and if it's not one of our strategies, we have no information and must assume we might have a match.
      return true;
    }
    Set<EClass> exportedEClasses = ((AbstractResourceDescriptionStrategy) strategy).getExportedEClasses(getResource());
    if (exportedEClasses == null || exportedEClasses.contains(type)) {
      // If no information is available, assume there might be matching EObjectDescriptions
      // Otherwise, if the type itself is in the set, we don't need to do costly supertype checks.
      return true;
    }
    for (EClass exported : exportedEClasses) {
      // if its direct superclass in java is exported
      if (type.isSuperTypeOf(exported) || type.getESuperTypes().contains(exported)) {
        return true;
      }
    }
    // We know that this resource description cannot export objects of a matching type.
    return false;
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByType(final EClass type) {
    // If we know that we cannot possibly have an exported object matching the desired type, delay creating the EObjectDescriptions.
    // If we already created the EObjectDescriptions, skip the extra check for super type relationship.
    if (lookup == null && !isExportedEClass(type)) {
      return Collections.emptyList();
    }
    return super.getExportedObjectsByType(type);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjects(final EClass type, final QualifiedName name, final boolean ignoreCase) {
    // If we know that we cannot possibly have an exported object matching the desired type, delay creating the EObjectDescriptions.
    // If we already created the EObjectDescriptions, skip the extra check for super type relationship.
    if (lookup == null && !isExportedEClass(type)) {
      return Collections.emptyList();
    }
    return super.getExportedObjects(type, name, ignoreCase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<QualifiedName, Set<EClass>> getImportedNamesTypes() {
    ImportedNamesAdapter adapter = ImportedNamesAdapter.find(getResource());
    if (adapter instanceof ImportedNamesTypesAdapter) {
      return ImmutableMap.copyOf(((ImportedNamesTypesAdapter) adapter).getImportedNamesTypes());
    }
    return Collections.emptyMap();
  }
}
