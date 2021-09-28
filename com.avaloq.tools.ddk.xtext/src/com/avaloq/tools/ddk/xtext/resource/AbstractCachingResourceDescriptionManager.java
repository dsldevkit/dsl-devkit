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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.DerivedStateAwareResourceDescriptionManager;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2.ReferenceMatchPolicy;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * Resource description manager; with some caching.
 */
public abstract class AbstractCachingResourceDescriptionManager extends DerivedStateAwareResourceDescriptionManager {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(AbstractCachingResourceDescriptionManager.class);

  /** Cache key for the resource description of a resource. */
  public static final String CACHE_KEY = DefaultResourceDescriptionManager.class.getName() + "#getResourceDescription"; //$NON-NLS-1$

  @Inject
  private IResourceDescriptions index; // TODO IBuilderState is problematic as it isn't available in standalone mode

  @Inject
  private IAllContainersState.Provider containersStateProvider;

  @Inject
  private IDefaultResourceDescriptionStrategy descriptionStrategy;

  @Inject
  private IFileExtensionResolver fileExtensionResolver;

  /** The file extensions of the DSL this resource description manager is for. */
  private ImmutableSet<String> fileExtensions;

  /**
   * Set the file extensions of our own language.
   *
   * @param fileExtensions
   *          The file extensions
   */
  @Inject
  public void setFileExtensions(@Named(Constants.FILE_EXTENSIONS) final String fileExtensions) {
    this.fileExtensions = ImmutableSet.copyOf(Arrays.asList(fileExtensions.split(","))); //$NON-NLS-1$
  }

  @Override
  public IResourceDescription getResourceDescription(final Resource resource) {
    if (!isManagerFor(resource.getURI())) {
      throw new IllegalStateException("Wrong language: " + resource.getURI()); //$NON-NLS-1$
    }
    return internalGetResourceDescription(resource, descriptionStrategy);
  }

  /**
   * Checks whether this manager manages resources with the given URI.
   *
   * @param uri
   *          URI
   * @return true if managed
   */
  protected boolean isManagerFor(final URI uri) {
    return fileExtensions.contains(fileExtensionResolver.resolveExtension(uri));
  }

  @Override
  protected IResourceDescription internalGetResourceDescription(final Resource resource, final IDefaultResourceDescriptionStrategy strategy) {
    return getCache().get(CACHE_KEY, resource, () -> createResourceDescription(resource, descriptionStrategy));
  }

  @Override
  protected IResourceDescription createResourceDescription(final Resource resource, final IDefaultResourceDescriptionStrategy strategy) {
    return new ResourceDescription2(resource, strategy, getCache());
  }

  /**
   * Given a resource delta, tell whether resources managed by this manager may be affected by it.
   *
   * @param delta
   *          The resource delta
   * @return true if the delta potentially affects this resource, false otherwise
   */
  protected boolean isInterestedIn(final Delta delta) {
    Set<String> interestingExtensions = getInterestingExtensions();
    return interestingExtensions == null || interestingExtensions.contains(delta.getUri().fileExtension());
  }

  /**
   * Returns the set of file extensions which can cause a managed resource to become invalidated.
   *
   * @return a set of file extensions or <code>null</code> if all resources are interesting
   */
  protected Set<String> getInterestingExtensions() { // NOPMD
    return null;
  }

  @Override
  public boolean isAffected(final Collection<Delta> deltas, final IResourceDescription candidate, final IResourceDescriptions context) { // NOPMD(NPathComplexity)
    final Collection<Delta> filteredDeltas = getInterestingDeltas(deltas);
    if (filteredDeltas.isEmpty()) {
      return false;
    }

    final List<IContainer> containers = getContainerManager().getVisibleContainers(candidate, context);
    final IAllContainersState containersState = containersStateProvider.get(context);

    for (final Delta delta : filteredDeltas) {
      final URI deltaURI = delta.getUri();// NOPMD - potentially could be lost due to call on getNew() after
      // deleted resources are no longer visible resources so we test them, too.
      if (delta.getNew() == null && isReferencedBy(delta, candidate, context)) {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug(candidate.getURI() + " is affected by " + delta.getUri()); //$NON-NLS-1$
        }
        return true;
      }

      String containerHandle = containersState.getContainerHandle(deltaURI);
      if (overlap(containerHandle, containers)) {
        for (IContainer container : containers) {
          if (container.getResourceDescription(deltaURI) != null) {
            if (isReferencedBy(delta, candidate, context)) {
              if (LOGGER.isDebugEnabled()) { // NOPMD AvoidDeeplyNestedIfStmts
                LOGGER.debug(candidate.getURI() + " is affected by " + delta.getUri()); //$NON-NLS-1$
              }
              return true;
            }
            break;
          }
        }
      }
    }
    return false;
  }

  /**
   * Filters the set of deltas to those which are of {@link #isInterestedIn(Delta) interest} to resources managed by this manager.
   *
   * @param deltas
   *          deltas to filter
   * @return filtered deltas
   */
  private Collection<Delta> getInterestingDeltas(final Collection<Delta> deltas) {
    final Collection<Delta> filteredDeltas = Lists.newArrayList();
    for (final Delta delta : deltas) {
      if (isInterestedIn(delta) && delta.haveEObjectDescriptionsChanged()) {
        filteredDeltas.add(delta);
      }
    }
    return filteredDeltas;
  }

  /**
   * Bulk method to return all resources (managed by this manager) which are affected by the given set of deltas.
   *
   * @param deltas
   *          deltas
   * @param candidates
   *          candidates
   * @param context
   *          context index
   * @return collection of affected resources
   */
  public Collection<URI> getAffectedResources(final Collection<Delta> deltas, final Collection<URI> candidates, final IResourceDescriptions context) {
    // Don't filter by isInterestedIn() in this bulk method. First, isInterestedIn() is based on file extensions, which may or may not
    // work precisely. Second, it filters by haveEObjectdescriptionsChanged(), but the MonitoredClusteringBuilderState already ensures that
    // the delta does not contain unchanged resource descriptions.
    //
    // By not pre-filtering the deltas, we can also ensure that resource description managers for different languages will end up making
    // identical queries to findAllReferencingResources() and findExactReferencingResources(), which is crucial to make the cache in
    // MonitoredClusteringBuilderState be effective.
    //
    // See also the comment on MonitoredClusteringBuilderState.FindReferenceCachingState.
    if (deltas.isEmpty() || candidates.isEmpty()) {
      return Collections.emptySet();
    }

    return getAffectedResources(deltas, uri -> candidates.contains(uri) && isManagerFor(uri), (IResourceDescriptions2) context);
  }

  /**
   * Bulk method to return all resources (managed by this manager) which are affected by the given set of deltas.
   *
   * @param deltas
   *          deltas
   * @param filter
   *          filter for affected resources
   * @param context
   *          context index
   * @return collection of affected resources
   */
  protected Set<URI> getAffectedResources(final Collection<Delta> deltas, final Predicate<URI> filter, final IResourceDescriptions2 context) { // NOPMD
    final Set<IResourceDescription> changedOrDeletedResources = Sets.newHashSet();
    final Set<IEObjectDescription> changedOrDeletedObjects = Sets.newHashSet();
    final SetMultimap<String, IResourceDescription> addedResources = HashMultimap.create();
    final SetMultimap<String, IEObjectDescription> addedObjects = HashMultimap.create();

    final IAllContainersState containersState = containersStateProvider.get(context);

    for (Delta delta : deltas) {
      if (delta.haveEObjectDescriptionsChanged()) {
        if (delta instanceof ResourceDescriptionDelta && ((ResourceDescriptionDelta) delta).hasObjectFingerprints()) {
          ResourceDescriptionDelta detailedDelta = (ResourceDescriptionDelta) delta;
          changedOrDeletedObjects.addAll(detailedDelta.getChangedObjects());
          changedOrDeletedObjects.addAll(detailedDelta.getDeletedObjects());
          addedObjects.putAll(containersState.getContainerHandle(delta.getUri()), detailedDelta.getAddedObjects());
        } else {
          IResourceDescription oldDesc = delta.getOld();
          IResourceDescription newDesc = delta.getNew();
          if (oldDesc != null) {
            changedOrDeletedResources.add(newDesc != null ? newDesc : oldDesc);
          } else {
            addedResources.put(containersState.getContainerHandle(delta.getUri()), newDesc);
          }
        }
      }
    }

    // find all resources matching provided filter with physical references to changed-or-deleted resources and objects
    Iterable<IResourceDescription> resourceReferences = context.findAllReferencingResources(changedOrDeletedResources, ReferenceMatchPolicy.REFERENCES);
    Iterable<IResourceDescription> objectReferences = context.findExactReferencingResources(changedOrDeletedObjects, ReferenceMatchPolicy.REFERENCES);
    Set<URI> references = StreamSupport.stream(Iterables.concat(resourceReferences, objectReferences).spliterator(), false) //
        .map(description -> description.getURI()).filter(filter).collect(Collectors.toSet());

    // find all resources matching provided filter with physical or imported name references to added resources and objects
    for (String containerHandle : addedResources.keySet()) {
      // using ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES is not enough: a lower index layer may contain physical references
      for (IResourceDescription res : context.findAllReferencingResources(addedResources.get(containerHandle), ReferenceMatchPolicy.ALL)) {
        URI uri = res.getURI();
        if (!references.contains(uri) && filter.test(uri)) {
          final List<IContainer> containers = getContainerManager().getVisibleContainers(res, context);
          if (overlap(containerHandle, containers)) {
            for (IContainer container : containers) {
              if (container.getResourceDescription(uri) != null) {
                references.add(uri);
              }
            }
          }
        }
      }
    }
    for (String containerHandle : addedObjects.keySet()) {
      // using ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES is not enough: a lower index layer may contain physical references
      for (IResourceDescription res : context.findExactReferencingResources(addedObjects.get(containerHandle), ReferenceMatchPolicy.ALL)) {
        URI uri = res.getURI();
        if (!references.contains(uri) && filter.test(uri)) {
          final List<IContainer> containers = getContainerManager().getVisibleContainers(res, context);
          if (overlap(containerHandle, containers)) {
            for (IContainer container : containers) {
              if (container.getResourceDescription(uri) != null) {
                references.add(uri);
              }
            }
          }
        }
      }
    }

    return references;
  }

  private boolean overlap(final String containerHandle, final List<IContainer> containers) {
    boolean hasStateBasedContainerWithHandle = false;
    for (IContainer container : containers) {
      if (container instanceof StateBasedContainerWithHandle) {
        hasStateBasedContainerWithHandle = true;
        String handle = ((StateBasedContainerWithHandle) container).getHandle();
        if (handle.equals(containerHandle)) {
          return true;
        }
      }
    }
    return !hasStateBasedContainerWithHandle;
  }

  /**
   * Determines if a given candidate is affected by a given delta.
   *
   * @param delta
   *          delta
   * @param candidate
   *          candidate
   * @param context
   *          context index
   * @return true if the candidate is affected
   */
  protected boolean isReferencedBy(final Delta delta, final IResourceDescription candidate, final IResourceDescriptions context) {
    URI candidateURI = candidate.getURI();

    if (delta instanceof ResourceDescriptionDelta && ((ResourceDescriptionDelta) delta).hasObjectFingerprints()) {
      ResourceDescriptionDelta detailedDelta = (ResourceDescriptionDelta) delta;

      final Set<IEObjectDescription> changedOrDeletedObjects = Sets.newHashSet(Iterables.concat(detailedDelta.getChangedObjects(), detailedDelta.getDeletedObjects()));
      for (IResourceDescription desc : ((IResourceDescriptions2) context).findExactReferencingResources(changedOrDeletedObjects, ReferenceMatchPolicy.REFERENCES)) {
        if (desc.getURI().equals(candidateURI)) {
          return true;
        }
      }

      for (IResourceDescription desc : ((IResourceDescriptions2) context).findExactReferencingResources(Sets.newHashSet(detailedDelta.getAddedObjects()), ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES)) {
        if (desc.getURI().equals(candidateURI)) {
          return true;
        }
      }
    } else {
      IResourceDescription resource = delta.getNew() != null ? delta.getNew() : delta.getOld();
      for (IResourceDescription desc : ((IResourceDescriptions2) context).findAllReferencingResources(ImmutableSet.of(resource), ReferenceMatchPolicy.ALL)) {
        if (desc.getURI().equals(candidateURI)) {
          return true;
        }
      }
    }

    return false;
  }

  /** Cache for the exported names from a delta. */
  private static final WeakHashMap<Delta, Set<QualifiedName>> RESOLVED_EXPORTED_NAMES = new WeakHashMap<Delta, Set<QualifiedName>>();
  private static final WeakHashMap<Delta, Set<QualifiedName>> UNRESOLVED_EXPORTED_NAMES = new WeakHashMap<Delta, Set<QualifiedName>>();

  @Override
  // PMD : readability ok, nesting level of "returns" is always one
  @SuppressWarnings("PMD.NPathComplexity")
  public boolean isAffected(final Delta delta, final IResourceDescription candidate) {
    if (delta.getUri().equals(candidate.getURI())) {
      // If the delta is for ourselves, we're always affected; otherwise the dirty state manager may omit to update the editor
      // state.
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug(delta.getUri() + " is the same as " + candidate.getURI()); //$NON-NLS-1$
      }
      return true;
    }
    if (!delta.haveEObjectDescriptionsChanged()) {
      return false;
    }
    if (!isInterestedIn(delta)) {
      return false;
    }

    Set<QualifiedName> resolvedNames = RESOLVED_EXPORTED_NAMES.get(delta);
    Set<QualifiedName> unresolvedNames = UNRESOLVED_EXPORTED_NAMES.get(delta);
    if (resolvedNames == null || unresolvedNames == null) {
      resolvedNames = Sets.newHashSet();
      unresolvedNames = Sets.newHashSet();
      addExportedNames(resolvedNames, unresolvedNames, delta.getOld());
      addExportedNames(resolvedNames, unresolvedNames, delta.getNew());
      RESOLVED_EXPORTED_NAMES.put(delta, resolvedNames);
      UNRESOLVED_EXPORTED_NAMES.put(delta, unresolvedNames);
    }
    Pair<Collection<QualifiedName>, Collection<QualifiedName>> resolvedAndUnresolvedNames = getResolvedAndUnresolvedImportedNames(candidate);
    boolean disjointResolved = Collections.disjoint(resolvedNames, resolvedAndUnresolvedNames.getFirst());
    if (!disjointResolved && LOGGER.isDebugEnabled()) {
      resolvedNames.retainAll(getImportedNames(candidate));
      LOGGER.debug("resolved names imported by   " + candidate.getURI() + " are exported by " + delta.getUri() + " intersection:" + resolvedNames.toString()); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
    if (!disjointResolved) {
      return true;
    }
    boolean disjointUnresolved = Collections.disjoint(unresolvedNames, resolvedAndUnresolvedNames.getSecond());
    if (!disjointUnresolved && LOGGER.isDebugEnabled()) {
      unresolvedNames.retainAll(getImportedNames(candidate));
      LOGGER.debug("unrevolved names imported by " + candidate.getURI() + " are exported by " + delta.getUri() + " intersection:" + unresolvedNames.toString()); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
    return !disjointUnresolved;
  }

  /**
   * Returns all resolved imported names from a given resource description.
   *
   * @param candidate
   *          the resource description
   * @return a collection of names
   */
  protected Pair<Collection<QualifiedName>, Collection<QualifiedName>> getResolvedAndUnresolvedImportedNames(final IResourceDescription candidate) {
    Iterable<QualifiedName> importedNames = candidate.getImportedNames();
    Set<QualifiedName> resolved = Sets.newHashSetWithExpectedSize(Iterables.size(importedNames));
    Set<QualifiedName> unresolved = Sets.newHashSet();
    for (QualifiedName name : importedNames) {
      if (QualifiedNames.isUnresolvedName(name)) {
        unresolved.add(name);
      } else {
        resolved.add(name);
      }
    }
    return Tuples.<Collection<QualifiedName>, Collection<QualifiedName>> pair(resolved, unresolved);
  }

  /**
   * Add the exported objects in given resourceDescriptor to the set of the resolvedNames and unresolvedNames names.
   *
   * @param resolvedNames
   *          the set of resolved names, may be {@code null}
   * @param unresolvedNames
   *          the set of unresolved names, may be {@code null}
   * @param resourceDescriptor
   *          the resource descriptor, may be {@code null}
   */
  protected void addExportedNames(final Set<QualifiedName> resolvedNames, final Set<QualifiedName> unresolvedNames, final IResourceDescription resourceDescriptor) {
    if (resourceDescriptor == null) {
      return;
    }
    for (final IEObjectDescription obj : resourceDescriptor.getExportedObjects()) {
      final QualifiedName name = obj.getName();
      resolvedNames.add(name); // compare with resolved names
      // If we're using qualified names, then let's add also only the last component here. For unresolved links, we may not
      // have a qualified name to look for, but maybe only a simple name.
      unresolvedNames.add(QualifiedNames.toUnresolvedName(name)); // compare with unresolved names
    }
  }

  @Override
  protected void addExportedNames(final Set<QualifiedName> names, final IResourceDescription resourceDescriptor) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ResourceDescriptionDelta createDelta(final IResourceDescription oldState, final IResourceDescription newState) {
    return new ResourceDescriptionDelta(oldState, newState, index);
  }

  /**
   * All extensions.
   *
   * @return all extensions ({@code null})
   */
  protected static ImmutableSet<String> all() { // NOPMD
    return null;
  }

  /**
   * Returns an immutable set for the given list of extensions. The returned extensions are properly encoded.
   *
   * @param exts
   *          list of extensions
   * @return immutable set of extensions
   */
  protected static ImmutableSet<String> of(final String... exts) { // NOPMD
    return ImmutableSet.copyOf(Stream.of(exts).map(ext -> URI.encodeSegment(ext, true)).iterator());
  }

}
