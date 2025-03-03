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
package com.avaloq.tools.ddk.check.runtime.issue;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;


/**
 * An abstract implementation of {@link com.avaloq.tools.ddk.check.runtime.issue.IIssue}. The default
 * superclass of check catalogs created by the check compiler.
 */
public abstract class AbstractIssue implements IIssue {

  @Override
  public void accept(final ValidationMessageAcceptor acceptor, final EObject object, final EStructuralFeature feature, final String message, final SeverityKind severityKind, final int index, final String issueCode, final String... issueData) {
    switch (severityKind) {
    case ERROR:
      acceptor.acceptError(message, object, feature, index, issueCode, issueData);
      break;
    case WARNING:
      acceptor.acceptWarning(message, object, feature, index, issueCode, issueData);
      break;
    case INFO:
      acceptor.acceptInfo(message, object, feature, index, issueCode, issueData);
      break;
    default:
      // empty
    }
  }

}
