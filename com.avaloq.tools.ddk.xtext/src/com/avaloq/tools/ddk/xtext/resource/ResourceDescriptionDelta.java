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

import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Triple;
import org.eclipse.xtext.util.Tuples;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;


/**
 * A resource description delta, with a "hasChanges" operation optimized for fingerprints. Additionally the new resource description is only soft referenced.
 */
@SuppressWarnings({"PMD.CompareObjectsWithEquals", "PMD.NPathComplexity"})
public class ResourceDescriptionDelta extends AbstractResourceDescriptionDelta {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(ResourceDescriptionDelta.class);

  private static final Ordering<IEObjectDescription> URI_ORDERING = Ordering.natural().onResultOf(new Function<IEObjectDescription, String>() {
    @Override
    public String apply(final IEObjectDescription from) {
      return from.getEObjectURI().fragment();
    }
  });

  private final URI uri;
  private IResourceDescription oldDesc;
  private SoftReference<IResourceDescription> newDesc;
  private final IResourceDescriptions index;

  private Boolean hasChanges;
  private Boolean objectFingerprints;
  private Triple<Collection<IEObjectDescription>, Collection<Pair<IEObjectDescription, IEObjectDescription>>, Collection<IEObjectDescription>> diff;

  /**
   * Create a new delta from an old and a new resource description.
   *
   * @param oldDesc
   *          The old description
   * @param newDesc
   *          The new description
   * @param index
   *          index
   */
  public ResourceDescriptionDelta(final IResourceDescription oldDesc, final IResourceDescription newDesc, final IResourceDescriptions index) {
    super();
    if (oldDesc == newDesc) {
      throw new AssertionError("'old != new' constraint violated"); //$NON-NLS-1$
    }
    if (newDesc != null && oldDesc != null && !oldDesc.getURI().equals(newDesc.getURI())) {
      URI oldURI = oldDesc.getURI();
      URI newURI = newDesc.getURI();
      throw new AssertionError("'new != null && old != null && !old.getURI().equals(new.getURI())' constraint violated, old was " + oldURI + " new was: " //$NON-NLS-1$ //$NON-NLS-2$
          + newURI);
    }

    this.uri = oldDesc == null ? newDesc.getURI() : oldDesc.getURI();
    this.oldDesc = oldDesc;
    if (newDesc != null) {
      this.newDesc = new SoftReference<IResourceDescription>(newDesc);
    }
    this.index = index;
  }

  /** {@inheritDoc} */
  @Override
  public URI getUri() {
    return uri;
  }

  /** {@inheritDoc} */
  @Override
  public IResourceDescription getNew() {
    if (newDesc == null) {
      return null;
    }
    IResourceDescription res = newDesc.get();
    return res != null ? res : index.getResourceDescription(uri);
  }

  /** {@inheritDoc} */
  @Override
  public IResourceDescription getOld() {
    if (oldDesc != null) {
      return oldDesc;
    } else if (!haveEObjectDescriptionsChanged()) {
      return getNew();
    } else if (diff != null && newDesc != null) {
      // reconstruct old description
      List<IEObjectDescription> oldObjects = recomputeOldObjects();
      oldDesc = new SimpleResourceDescription(uri, oldObjects);
    }
    return oldDesc;
  }

  /** {@inheritDoc} */
  @Override
  public boolean haveEObjectDescriptionsChanged() {
    if (hasChanges == null) {
      hasChanges = internalHasChanges();
      if (!hasChanges) {
        discardOld();
      }
    }
    return hasChanges;
  }

  /**
   * Discards the old descriptions as it's either equivalent to the new or can be computed from a diff against the new.
   *
   * @see #recomputeOldObjects()
   */
  private void discardOld() {
    oldDesc = null; // NOPMD
  }

  /** {@inheritDoc} */
  @SuppressWarnings({"PMD.CompareObjectsWithEquals", "PMD.NPathComplexity"})
  protected boolean internalHasChanges() {
    if (getNew() == null || oldDesc == null) {
      return true; // Deleted or new resource
    }

    final Iterator<IEObjectDescription> oldObjectsIter = oldDesc.getExportedObjects().iterator();
    final Iterator<IEObjectDescription> newObjectsIter = getNew().getExportedObjects().iterator();

    final boolean empty = !oldObjectsIter.hasNext();
    if (empty != !newObjectsIter.hasNext()) {
      return true; // One is empty, but the other one is not
    } else if (empty) {
      return false; // Both are empty
    }

    final IEObjectDescription oldFirst = oldObjectsIter.next();
    final IEObjectDescription newFirst = newObjectsIter.next();

    objectFingerprints = newFirst.getUserData(IFingerprintComputer.OBJECT_FINGERPRINT) != null;

    final String oldHash = oldFirst.getUserData(IFingerprintComputer.RESOURCE_FINGERPRINT);
    final String newHash = newFirst.getUserData(IFingerprintComputer.RESOURCE_FINGERPRINT);
    if (oldHash != null && newHash != null) {
      // Both have fingerprints: it suffices to compare those!
      return !oldHash.equals(newHash);
    }

    // At least one has no fingerprint. If both have none, try the default method, otherwise, there is a change.
    if (oldHash != null || newHash != null) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug(getNew().getURI().toString() + ": fingerprint has changed from: " + oldHash + " to " + newHash); //$NON-NLS-1$ //$NON-NLS-2$
      }
      return true;
    }

    if (!equals(oldFirst, newFirst)) {
      return true;
    }

    while (oldObjectsIter.hasNext()) {
      if (!newObjectsIter.hasNext() || !equals(oldObjectsIter.next(), newObjectsIter.next())) {
        return true;
      }
    }

    return newObjectsIter.hasNext();
  }

  /**
   * Checks if two IEObjectDescriptions are equal.
   * The implementation assumes the descriptions' fingerprints include all the necessary data, including name, EClass and relevant user data items.
   *
   * @param oldObj
   *          old object
   * @param newObj
   *          new object
   * @return true if both are equal
   */
  protected boolean equals(final IEObjectDescription oldObj, final IEObjectDescription newObj) {
    if (oldObj != newObj) {
      String newFingerprint = newObj.getUserData(IFingerprintComputer.OBJECT_FINGERPRINT);
      if (newFingerprint != null && !newFingerprint.equals(oldObj.getUserData(IFingerprintComputer.OBJECT_FINGERPRINT))) {
        return false;
      }
      if (!oldObj.getEObjectURI().equals(newObj.getEObjectURI())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the delta contains object fingerprints.
   *
   * @return true if object fingerprints are available
   */
  public boolean hasObjectFingerprints() {
    if (objectFingerprints == null) {
      objectFingerprints = internalHasObjectFingerprints();
    }
    return objectFingerprints;
  }

  /**
   * Internal helper method to initialize {@link #objectFingerprints}.
   *
   * @return {@code true} if this delta has object fingerprints
   */
  private boolean internalHasObjectFingerprints() {
    IResourceDescription desc = newDesc == null ? oldDesc : getNew();
    final Iterator<IEObjectDescription> objectIter = desc.getExportedObjects().iterator();

    if (!objectIter.hasNext()) {
      return false;
    } else {
      return objectIter.next().getUserData(IFingerprintComputer.OBJECT_FINGERPRINT) != null;
    }
  }

  public Collection<IEObjectDescription> getDeletedObjects() {
    return computeDetailedDiff().getFirst();
  }

  /**
   * Returns a collection of all changed objects.
   *
   * @return changed objects
   */
  public Collection<IEObjectDescription> getChangedObjects() {
    Collection<Pair<IEObjectDescription, IEObjectDescription>> changes = computeDetailedDiff().getSecond();
    List<IEObjectDescription> changedObjects = Lists.newArrayListWithCapacity(changes.size());
    for (Pair<IEObjectDescription, IEObjectDescription> change : changes) {
      changedObjects.add(change.getSecond());
    }
    return changedObjects;
  }

  public Collection<IEObjectDescription> getAddedObjects() {
    return computeDetailedDiff().getThird();
  }

  /**
   * Computes a detailed diff for this delta.
   *
   * @return triple containing collections of deleted objects, changed objects, and added objects (in that order)
   */
  public Triple<Collection<IEObjectDescription>, Collection<Pair<IEObjectDescription, IEObjectDescription>>, Collection<IEObjectDescription>> computeDetailedDiff() {
    if (diff == null) {
      diff = computeDetailedDiff(oldDesc, getNew());
      discardOld();
    }

    return diff;
  }

  /**
   * Computes a detailed diff for the given resource descriptions.
   *
   * @param oldRes
   *          old description
   * @param newRes
   *          new description
   * @return triple containing collections of deleted objects, changed objects, and added objects
   */
  private Triple<Collection<IEObjectDescription>, Collection<Pair<IEObjectDescription, IEObjectDescription>>, Collection<IEObjectDescription>> computeDetailedDiff(final IResourceDescription oldRes, final IResourceDescription newRes) {
    Collection<IEObjectDescription> deletedObjects = Sets.newHashSet();
    Collection<Pair<IEObjectDescription, IEObjectDescription>> changedObjects = Sets.newHashSet();
    Collection<IEObjectDescription> newObjects = Sets.newHashSet();

    if (oldRes == null) {
      Iterables.addAll(newObjects, newRes.getExportedObjects());
    } else if (newRes == null) {
      Iterables.addAll(deletedObjects, oldRes.getExportedObjects());
    } else {
      final List<IEObjectDescription> oldEObjects = URI_ORDERING.sortedCopy(oldRes.getExportedObjects());
      final List<IEObjectDescription> newEObjects = URI_ORDERING.sortedCopy(newRes.getExportedObjects());
      int oldIdx = 0;
      int newIdx = 0;
      for (;;) {
        IEObjectDescription oldObj = oldIdx >= oldEObjects.size() ? null : oldEObjects.get(oldIdx); // NOPMD
        IEObjectDescription newObj = newIdx >= newEObjects.size() ? null : newEObjects.get(newIdx); // NOPMD
        if (oldObj == null && newObj == null) {
          break;
        } else if (oldObj == null) {
          newObjects.add(newObj);
          newIdx++;
        } else if (newObj == null) {
          deletedObjects.add(oldObj);
          oldIdx++;
        } else {
          int compare = URI_ORDERING.compare(oldObj, newObj);
          if (compare == 0) {
            if (!equals(oldObj, newObj)) {
              changedObjects.add(Tuples.create(oldObj, newObj));
            }
            oldIdx++;
            newIdx++;
          } else if (compare < 0) {
            deletedObjects.add(oldObj);
            oldIdx++;
          } else {
            newObjects.add(newObj);
            newIdx++;
          }
        }
      }
    }
    if (deletedObjects.isEmpty() && changedObjects.isEmpty() && newObjects.isEmpty()) {
      hasChanges = false;
    }
    return Tuples.create(deletedObjects, changedObjects, newObjects);
  }

  /**
   * Recomputes the set of old objects based on the diff and the new description. This can be expensive but should be unusual.
   *
   * @return set of old objects
   */
  private List<IEObjectDescription> recomputeOldObjects() {
    List<IEObjectDescription> oldObjects = Lists.newArrayList(getDeletedObjects());

    Collection<URI> ignoredObjectURIs = Sets.newHashSet();
    for (IEObjectDescription addedObject : getAddedObjects()) {
      ignoredObjectURIs.add(addedObject.getEObjectURI());
    }
    for (IEObjectDescription changedObj : getChangedObjects()) {
      ignoredObjectURIs.add(changedObj.getEObjectURI());
      oldObjects.add(changedObj);
    }
    for (IEObjectDescription obj : getNew().getExportedObjects()) {
      if (!ignoredObjectURIs.contains(obj.getEObjectURI())) {
        oldObjects.add(obj);
      }
    }

    return oldObjects;
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return getClass().getSimpleName() + " for " + getUri() + " old :" + (getOld() != null) + ",new :" + (getNew() != null);
  }

}
