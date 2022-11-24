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
package com.avaloq.tools.ddk.check.runtime.quickfix;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;


/**
 * Copy of Xtext's {@link org.eclipse.xtext.ui.editor.model.edit.SemanticModificationWrapper
 * SemanticModificationWrapper}.
 * <p>
 * This class is UI-independent.
 */
// CHECKSTYLE:OFF
public class CoreSemanticModificationWrapper implements ICoreModification {

  private final URI uriToProblem;

  private final ICoreSemanticModification semanticModification;

  public CoreSemanticModificationWrapper(final URI uriToProblem, final ICoreSemanticModification semanticModification) {
    this.semanticModification = semanticModification;
    this.uriToProblem = uriToProblem;
  }

  public void apply(final ICoreModificationContext context) {
    context.getXtextDocument().modify(new IUnitOfWork.Void<XtextResource>() {
      @Override
      public void process(final XtextResource state) throws Exception { // NOPMD (as in org.eclipse.xtext)
        EObject eObject = state.getEObject(uriToProblem.fragment());
        semanticModification.apply(eObject, context);
      }
    });
  }

}

