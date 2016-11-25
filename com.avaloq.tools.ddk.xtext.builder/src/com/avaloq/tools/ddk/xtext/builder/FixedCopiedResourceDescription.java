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
package com.avaloq.tools.ddk.xtext.builder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.resource.impl.EObjectDescriptionLookUp;

import com.avaloq.tools.ddk.xtext.resource.IDetachableDescription;
import com.avaloq.tools.ddk.xtext.resource.PatternAwareEObjectDescriptionLookUp;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescription2;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;


/**
 * Fix a contract-breaking default implementation in Xtext 2.0.1. Further use LinkedHashMap instead of HashMap to preserve order of user data entries.
 * Also the {@link IDetachableDescription} is respected in order to reuse descriptions and computed data where possible.
 */
public class FixedCopiedResourceDescription extends AbstractResourceDescription implements IResourceDescription2 {

  private final URI uri;
  private final ImmutableList<IEObjectDescription> exported;

  public FixedCopiedResourceDescription(final IResourceDescription original) {
    super();
    this.uri = original.getURI();
    this.exported = ImmutableList.copyOf(Iterables.transform(original.getExportedObjects(), new Function<IEObjectDescription, IEObjectDescription>() {
      @Override
      @SuppressWarnings("unchecked")
      public IEObjectDescription apply(final IEObjectDescription from) {
        if (from.getEObjectOrProxy().eIsProxy()) {
          return from;
        } else if (from instanceof IDetachableDescription) {
          return ((IDetachableDescription<IEObjectDescription>) from).detach();
        }
        InternalEObject result = (InternalEObject) EcoreUtil.create(from.getEClass());
        result.eSetProxyURI(from.getEObjectURI());
        ImmutableMap.Builder<String, String> userData = ImmutableMap.builder();
        for (final String key : from.getUserDataKeys()) {
          userData.put(key, from.getUserData(key));
        }
        return EObjectDescription.create(from.getName(), result, userData.build());
      }
    }));
  }

  @Override
  public URI getURI() {
    return uri;
  }

  @Override
  protected List<IEObjectDescription> computeExportedObjects() {
    return exported;
  }

  @Override
  protected EObjectDescriptionLookUp getLookUp() {
    if (lookup == null) {
      lookup = new PatternAwareEObjectDescriptionLookUp(computeExportedObjects());
    }
    return lookup;
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<QualifiedName> getImportedNames() {
    // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=344373
    return Collections.emptyList();
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IReferenceDescription> getReferenceDescriptions() {
    // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=344373
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    result.append(" (URI: "); //$NON-NLS-1$
    result.append(uri);
    result.append(')');

    return result.toString();
  }

  /**
   * {@inheritDoc}
   * see https://bugs.eclipse.org/bugs/show_bug.cgi?id=344373
   */
  @Override
  public Map<QualifiedName, Set<EClass>> getImportedNamesTypes() {
    return Maps.newHashMap();
  }
}

