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
package com.avaloq.tools.ddk.check.runtime.ui.quickfix;

import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreIssueResolutionAcceptor;
import com.google.inject.ImplementedBy;


/**
 * This is a hack: interface only added in order to define the ImplementedBy binding.
 * This plug-in should probably define create an injector and define a module that declares
 * how instances of ICoreIssueResolutionAcceptor should be created.
 */
@ImplementedBy(CoreIssueResolutionAcceptor.class)
public interface ICoreIssueResolutionAcceptor2 extends ICoreIssueResolutionAcceptor {
  // Deliberately empty
}

