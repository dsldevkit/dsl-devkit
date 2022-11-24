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
package com.avaloq.tools.ddk.xtext.extensions;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.builder.impl.DirtyStateAwareResourceDescriptions;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.ui.editor.IDirtyStateManager;
import org.eclipse.xtext.ui.notification.IStateChangeEventBroker;

import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * A version of {@link DirtyStateAwareResourceDescriptions} that implements the {@link IResourceDescriptions2} interface with
 * the new find... operations.
 */
public class DirtyStateAwareResourceDescriptions2 extends DirtyStateAwareResourceDescriptions implements IResourceDescriptions2 {

  private final IDirtyStateManager dirtyStateManager;
  private final IResourceDescriptions2 globalDescriptions;

  private final Set<URI> dirtyResources;

  @Inject
  public DirtyStateAwareResourceDescriptions2(final IBuilderState globalDescriptions, final IDirtyStateManager dirtyStateManager, final IStateChangeEventBroker stateChangeEventBroker) {
    super(globalDescriptions, dirtyStateManager, stateChangeEventBroker);
    dirtyResources = Sets.newHashSet();
    this.dirtyStateManager = dirtyStateManager;
    this.globalDescriptions = (IResourceDescriptions2) (globalDescriptions instanceof IResourceDescriptions2 ? globalDescriptions
        : new ResourceDescriptions2(globalDescriptions));
  }

  /**
   * Dirty state listener that keeps our list of URIs of dirty resources up to date.
   */
  protected class DirtyStateListener2 extends DirtyStateListener {

    @Override
    public void descriptionsChanged(final IResourceDescription.Event event) {
      for (Delta delta : event.getDeltas()) {
        IResourceDescription newDesc = delta.getNew();
        if (newDesc == null) {
          dirtyResources.remove(delta.getUri());
        } else {
          dirtyResources.add(delta.getUri());
        }
      }
      dirtyDescriptionsChanged(event);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected IResourceDescription.Event.Listener createDirtyStateListener() {
    return new DirtyStateListener2();
  }

  /** {@inheritDoc} */
  public Set<URI> getAllURIs() {
    Set<URI> allURIs = Sets.newHashSet(dirtyResources);
    allURIs.addAll(globalDescriptions.getAllURIs());
    return allURIs;
  }

  /** {@inheritDoc} */
  public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    return merge(globalDescriptions.findAllReferencingResources(targetResources, matchPolicy), targetResources);
  }

  /** {@inheritDoc} */
  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    // FIXME merge
    return globalDescriptions.findExactReferencingResources(targetObjects, matchPolicy);
  }

  /**
   * Merge globally found referencing resources with those from the dirty state.
   * 
   * @param referencingDescriptions
   *          global referencing resources
   * @param targetResources
   *          Set of URIs of the target resources
   * @return An iterable of all resource descriptions of resources referencing any of the URIs given, such that descriptions from the dirty state take
   *         precedence over global descriptions.
   */
  private Iterable<IResourceDescription> merge(final Iterable<IResourceDescription> referencingDescriptions, final Set<IResourceDescription> targetResources) {
    Set<URI> targetResourceUris = Sets.newHashSetWithExpectedSize(targetResources.size());
    for (IResourceDescription res : targetResources) {
      targetResourceUris.add(res.getURI());
    }
    Set<URI> dirtyUris = Sets.newHashSet(dirtyResources);
    List<IResourceDescription> combined = Lists.newArrayList();
    for (IResourceDescription global : referencingDescriptions) {
      if (dirtyUris.remove(global.getURI())) {
        // Was contained, so check if it still has a reference to the target resources. Only add the dirty resource description if so.
        IResourceDescription desc = dirtyStateManager.getDirtyResourceDescription(global.getURI());
        if (hasReferenceTo(desc, targetResourceUris)) {
          combined.add(desc);
        }
      } else {
        combined.add(global);
      }
    }
    // Now add all the remaining dirty resources that do reference one of the target resources
    for (URI uri : dirtyUris) {
      IResourceDescription desc = dirtyStateManager.getDirtyResourceDescription(uri);
      if (hasReferenceTo(desc, targetResourceUris)) {
        combined.add(desc);
      }
    }
    return combined;
  }

  /**
   * determine whether a given resource description contains any reference to a set of other resources identified by their URIs.
   * 
   * @param desc
   *          Resource description to test
   * @param targetResources
   *          URIs of the target resources
   * @return true, if {@code desc} links to one of the {@code targetResources}.
   */
  private boolean hasReferenceTo(final IResourceDescription desc, final Set<URI> targetResources) {
    if (desc == null || targetResources.contains(desc.getURI())) {
      return false;
    }
    return !Iterables.isEmpty(Iterables.filter(desc.getReferenceDescriptions(), new Predicate<IReferenceDescription>() {
      public boolean apply(final IReferenceDescription input) {
        return targetResources.contains(input.getTargetEObjectUri().trimFragment());
      }
    }));
  }

  /** {@inheritDoc} */
  public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
    final Set<URI> dirtyReferencingUris = Sets.newHashSet();
    Iterable<IReferenceDescription> dirtyReferences = Collections.emptyList();
    for (URI dirtyURI : dirtyResources) {
      IResourceDescription desc = dirtyStateManager.getDirtyResourceDescription(dirtyURI);
      if (desc == null) {
        continue;
      }
      Iterable<IReferenceDescription> local = Iterables.filter(desc.getReferenceDescriptions(), new Predicate<IReferenceDescription>() {
        public boolean apply(final IReferenceDescription input) {
          return targetObjects.contains(input.getTargetEObjectUri());
        }
      });
      if (!Iterables.isEmpty(local)) {
        dirtyReferences = Iterables.concat(dirtyReferences, local);
        dirtyReferencingUris.add(desc.getURI());
      }
    }
    return Iterables.concat(dirtyReferences, Iterables.filter(globalDescriptions.findReferencesToObjects(targetObjects), new Predicate<IReferenceDescription>() {
      public boolean apply(final IReferenceDescription input) {
        return !dirtyReferencingUris.contains(input.getSourceEObjectUri().trimQuery().trimFragment());
      }
    }));
  }

}

