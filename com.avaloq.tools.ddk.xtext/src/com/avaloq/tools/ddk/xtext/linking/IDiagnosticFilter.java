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
package com.avaloq.tools.ddk.xtext.linking;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.Triple;

import com.google.inject.ImplementedBy;


/**
 * This interface may be implemented and bound in Guice module by clients who
 * wish to suppress the creation of diagnostics for errors.
 * It is intended that this interface be used to suppress creation of linking
 * errors for optional cross references.
 */
@ImplementedBy(LazyLinkingResource2.DefaultDiagnosticFilter.class)
public interface IDiagnosticFilter {

  /**
   * Suppress the creation of a diagnostic in {@link LazyLinkingResource2#createAndAddDiagnostic(Triple)}.
   * 
   * @param triple
   *          the triple
   * @return true, if the creation of diagnostic should be suppressed
   */
  boolean suppressDiagnostic(Triple<EObject, EReference, INode> triple);
}
