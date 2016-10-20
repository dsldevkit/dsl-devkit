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
package com.avaloq.tools.ddk.xtext.scoping;

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Supplier;


/**
 * A supplier for scope contexts for delegating scopes, to avoid that we evaluate scope expressions too early.
 */
public interface IContextSupplier extends Supplier<Iterable<? extends EObject>> {

}
