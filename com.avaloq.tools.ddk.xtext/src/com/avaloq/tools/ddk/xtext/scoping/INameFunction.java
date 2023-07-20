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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;


/**
 * A name function maps IEObjectDescriptions or EObjects to names.
 */
public interface INameFunction extends Function<EObject, QualifiedName> {

  /**
   * Additional apply method for IEObjectDescriptions.
   *
   * @param from
   *          The IEObjectDescription
   * @return The name.
   */
  default QualifiedName apply(final IEObjectDescription from) {
    LogManager.getLogger(INameFunction.class).warn("No explicit name function for description " + from.getEObjectURI() + " of type " //$NON-NLS-1$ //$NON-NLS-2$
        + EcoreUtil.getURI(from.getEClass()));
    return from.getName();
  }

}
