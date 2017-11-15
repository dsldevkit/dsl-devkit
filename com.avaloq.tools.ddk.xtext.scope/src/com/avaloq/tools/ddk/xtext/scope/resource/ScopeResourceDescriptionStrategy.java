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

package com.avaloq.tools.ddk.xtext.scope.resource;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;


/**
 * Implementation which only exports the top-level {@link ScopeModel} with a hash of the source contents as a fingerprint, so that any change invalidates any
 * referencing resources. The fingerprint also includes the hash of all included scopes, so that regeneration will be triggered if the included scope was
 * changed.
 */
public class ScopeResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {

  private static final Logger LOG = Logger.getLogger(ScopeResourceDescriptionStrategy.class);

  private static final int HASHER_CAPACITY = 2048;

  @Override
  public boolean createEObjectDescriptions(final EObject eObject, final IAcceptor<IEObjectDescription> acceptor) {
    if (getQualifiedNameProvider() == null || !(eObject instanceof ScopeModel)) {
      return false;
    }
    ScopeModel model = (ScopeModel) eObject;
    try {
      QualifiedName qualifiedName = getQualifiedNameProvider().getFullyQualifiedName(model);
      if (qualifiedName != null) {
        Hasher hasher = Hashing.murmur3_32().newHasher(HASHER_CAPACITY);
        hasher.putUnencodedChars(getSourceText(model));
        for (ScopeModel include : model.getIncludedScopes()) {
          hasher.putUnencodedChars(getSourceText(include));
        }
        acceptor.accept(EObjectDescription.create(qualifiedName, model, Collections.singletonMap("fingerprint", hasher.hash().toString())));
      }
      // CHECKSTYLE:CHECK-OFF IllegalCatch
    } catch (RuntimeException e) {
      // CHECKSTYLE:CHECK-ON
      LOG.error(e.getMessage(), e);
    }
    return false;
  }

  private String getSourceText(final EObject obj) {
    return ((XtextResource) obj.eResource()).getParseResult().getRootNode().getText();
  }
}
