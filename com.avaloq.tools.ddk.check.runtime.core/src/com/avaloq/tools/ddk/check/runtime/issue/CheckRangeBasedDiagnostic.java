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
package com.avaloq.tools.ddk.check.runtime.issue;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.RangeBasedDiagnostic;


/**
 * Needed to get access to the functionality of {@link RangeBasedDiagnostic}.
 */
public class CheckRangeBasedDiagnostic extends RangeBasedDiagnostic {

  protected CheckRangeBasedDiagnostic(final int severity, final String message, final EObject source, final int offset, final int length, final CheckType checkType, final String issueCode, final String... issueData) {
    super(severity, message, source, offset, length, checkType, issueCode, issueData);
  }

}
