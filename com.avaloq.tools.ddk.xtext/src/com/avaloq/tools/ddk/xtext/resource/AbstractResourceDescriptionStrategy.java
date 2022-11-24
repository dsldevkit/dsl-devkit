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

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.avaloq.tools.ddk.xtext.build.BuildPhases;
import com.avaloq.tools.ddk.xtext.linking.ICrossReferenceHelper;
import com.avaloq.tools.ddk.xtext.scoping.ImplicitReferencesAdapter;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.inject.Inject;


/**
 * Resource description strategy with support for partial export and for fingerprinting.
 */
public abstract class AbstractResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(AbstractResourceDescriptionStrategy.class);

  @Inject
  private ICrossReferenceHelper crossReferenceHelper;

  @Inject(optional = true)
  private IFingerprintComputer fingerprintComputer;

  /** {@inheritDoc} */
  @Override
  public boolean createEObjectDescriptions(final EObject from, final IAcceptor<IEObjectDescription> acceptor) {
    final IQualifiedNameProvider nameProvider = getQualifiedNameProvider();
    if (nameProvider == null) {
      return false;
    }
    try {
      return doCreateEObjectDescriptions(from, acceptor);
      // CHECKSTYLE:OFF (IllegalCatch)
    } catch (final Exception e) {
      // We need to catch arbitrary exceptions here, otherwise building may fail spectacularly;
      // and the cause may be very hard to find (likely some bug in an export specification).
      // CHECKSTYLE:ON
      LOGGER.error(MessageFormat.format(Messages.AbstractSdkResourceDescription_OBJECT_DESCRIPTION_FAILURE, EObjectUtil.getLocationString(from)), e);
    }
    return true;
  }

  /**
   * Retrieve qualified name and add it to the acceptor.
   *
   * @param object
   *          object
   * @param acceptor
   *          Acceptor to store retrieved qualified names
   * @return true if this object's content should be processed
   */
  protected abstract boolean doCreateEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor);

  /**
   * Adds a description for the provided EObject to the resource description.
   *
   * @param object
   *          EObject to create descriptor for
   * @param data
   *          user data for descriptor
   * @param acceptor
   *          acceptor to add description to
   */
  protected void acceptEObjectDescription(final EObject object, final Map<String, String> data, final IAcceptor<IEObjectDescription> acceptor) {
    QualifiedName name = getQualifiedNameProvider().getFullyQualifiedName(object);
    if (name != null && !name.isEmpty()) {
      acceptor.accept(DetachableEObjectDescription.create(name, object, data));
    }
  }

  /**
   * Adds a description for the provided EObject to the resource description.
   *
   * @param object
   *          EObject to create descriptor for
   * @param acceptor
   *          acceptor to add description to
   */
  protected void acceptEObjectDescription(final EObject object, final IAcceptor<IEObjectDescription> acceptor) {
    QualifiedName name = getQualifiedNameProvider().getFullyQualifiedName(object);
    if (name != null && !name.isEmpty()) {
      acceptor.accept(DetachableEObjectDescription.create(name, object));
    }
  }

  /**
   * Computes and returns the fingerprint for the given object.
   *
   * @param object
   *          object
   * @return fingerprint or null if object fingerprint computation is not enabled or no fingerprint is defined for the given object
   */
  protected String getFingerprint(final EObject object) {
    return doComputeObjectFingerprint(object) ? fingerprintComputer.computeFingerprint(object) : null; // NOPMD
  }

  /**
   * Checks if object fingerprint computation is enabled.
   *
   * @param object
   *          object to check enablement for
   * @return true if object fingerprints can be computed
   */
  protected boolean doComputeObjectFingerprint(final EObject object) {
    if (fingerprintComputer == null) {
      return false;
    }
    return !BuildPhases.isIndexing(object);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean createReferenceDescriptions(final EObject from, final URI exportedContainerURI, final IAcceptor<IReferenceDescription> acceptor) {
    final EList<EReference> references = from.eClass().getEAllReferences();
    for (EReference eReference : references) {
      if (isIndexable(from, eReference)) {
        final Object val = from.eGet(eReference, false);
        if (val != null) {
          if (eReference.isMany()) {
            final InternalEList<EObject> list = (InternalEList<EObject>) val;
            for (int i = 0; i < list.size(); i++) {
              EObject to = list.basicGet(i);
              acceptResolvedReference(from, to, eReference, exportedContainerURI, i, acceptor);
            }
          } else {
            final EObject to = (EObject) val;
            acceptResolvedReference(from, to, eReference, exportedContainerURI, -1, acceptor);
          }
        }
      }
    }
    return true;
  }

  /**
   * Creates all inferred and implicit reference descriptions and adds them to the acceptor.
   *
   * @param resource
   *          resource to create implicit references for
   * @param acceptor
   *          acceptor
   */
  public void createImplicitReferenceDescriptions(final Resource resource, final IAcceptor<IReferenceDescription> acceptor) {
    ImplicitReferencesAdapter adapter = ImplicitReferencesAdapter.find(resource);
    if (adapter != null) {
      for (IReferenceDescription ref : adapter.getImplicitReferences()) {
        acceptor.accept(ref);
      }
    }
  }

  /**
   * Add an external resolved reference to the acceptor.
   *
   * @param from
   *          referencing object
   * @param to
   *          referenced object
   * @param eReference
   *          ECore reference
   * @param exportedContainerURI
   *          URI of the exported container
   * @param indexInList
   *          index in list (for multi valued references otherwise -1)
   * @param acceptor
   *          acceptor for accepted references
   */
  private void acceptResolvedReference(final EObject from, final EObject to, final EReference eReference, final URI exportedContainerURI, final int indexInList, final IAcceptor<IReferenceDescription> acceptor) {
    if (isResolvedAndExternal(from, eReference, to)) {
      acceptor.accept(createReferenceDescription(from, exportedContainerURI, eReference, indexInList, to));
    }
  }

  @Override
  protected IReferenceDescription createReferenceDescription(final EObject owner, final URI exportedContainerURI, final EReference eReference, final int indexInList, final EObject target) {
    return new ReferenceDescription(owner, target, eReference, exportedContainerURI, indexInList);
  }

  /** {@inheritDoc} */
  protected boolean isIndexable(final EObject from, final EReference eReference) {
    return !eReference.isContainment() && !eReference.isContainer();
  }

  /**
   * Checks whether the given cross reference should be exported to the index. This will use the cross reference helper if available.
   *
   * @param from
   *          reference source
   * @param eReference
   *          the EReference
   * @param to
   *          unresolved reference target
   * @return true if to be exported
   */
  protected boolean isResolvedAndExternal(final EObject from, final EReference eReference, final EObject to) {
    return crossReferenceHelper.exportReference(from, eReference, to);
  }

  /**
   * Retrieves the set of all possibly exported types for a given resource. (I.e., all types of all possibly exported objects.)
   *
   * @param resource
   *          the resource
   * @return the set of types, or {@code null} if all types are allowed (or if no information available).
   */
  public Set<EClass> getExportedEClasses(final Resource resource) {
    return null; // TODO: once ASMD is regenerated, make this operation abstract.
  }

}
