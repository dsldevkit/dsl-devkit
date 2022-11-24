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
package com.avaloq.tools.ddk.check.runtime.issue; //TODO rename package

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;


/**
 * Interface defining an issue.
 */
public interface IIssue {

  /**
   * Accept a validation issue with given validation message acceptor.
   * 
   * @param acceptor
   *          the message acceptor used to create diagnostics, may not be <code>null</code>
   * @param object
   *          the object or the feature holder. May not be <code>null</code>
   * @param feature
   *          the feature or <code>null</code> if the complete instance should be annotated
   * @param message
   *          the validation message to be associated with the diagnostic
   * @param severityKind
   *          the severity kind, may not be <code>null</code>
   * @param index
   *          index of the erroneous value or {@link org.eclipse.xtext.validation.ValidationMessageAcceptor.INSIGNIFICANT_INDEX} if all values are considered to
   *          be invalid. The index is ignored if the feature is null or the feature is a single value feature.
   * @param issueCode
   *          the issue code or <code>null</code>
   * @param issueData
   *          the optional issue data to be associated with the validation issue; may be re-used in quickfixes
   * @see org.eclipse.xtext.validation.ValidationMessageAcceptor
   * @see com.avaloq.tools.ddk.check.runtime.issue.SeverityKind
   */
  void accept(ValidationMessageAcceptor acceptor, EObject object, EStructuralFeature feature, String message, SeverityKind severityKind, int index, String issueCode, String... issueData);

}

