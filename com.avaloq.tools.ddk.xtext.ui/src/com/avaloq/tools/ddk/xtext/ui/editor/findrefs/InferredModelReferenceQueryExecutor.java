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
package com.avaloq.tools.ddk.xtext.ui.editor.findrefs;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static org.eclipse.emf.ecore.util.EcoreUtil.getURI;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceQueryExecutor;

import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations;
import com.google.common.base.Predicate;
import com.google.inject.Inject;


/**
 * A reference query data factory for finding references not only to a source element, but
 * to the inferred model elements of a source element.
 */
// CHECKSTYLE:OFF This factory should not be abstract.
public class InferredModelReferenceQueryExecutor extends ReferenceQueryExecutor {
  // CHECKSTYLE:ON

  @Inject
  private IInferredModelAssociations modelAssociations;

  @Override
  protected Iterable<URI> getTargetURIs(final EObject target) {
    Set<URI> targetURIs = newLinkedHashSet();
    targetURIs.add(getURI(target));
    for (EObject jvmElement : modelAssociations.getInferredModelElements(target)) {
      targetURIs.add(getURI(jvmElement));
    }
    return targetURIs;
  }

  @Override
  protected Predicate<IReferenceDescription> getFilter(final EObject primaryTarget) {
    return new InferredModelReferenceFilter();
  }

}
