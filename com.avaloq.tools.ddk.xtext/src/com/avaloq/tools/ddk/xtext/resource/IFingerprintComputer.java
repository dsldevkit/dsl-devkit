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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * Fingerprint computer used by builder to determine if a resource's dependents need rebuilding.
 */
public interface IFingerprintComputer {

  /** Constant user data key for the resource fingerprint. */
  String RESOURCE_FINGERPRINT = " ##"; //$NON-NLS-1$

  /** Constant user data key for the resource fingerprint. */
  String OBJECT_FINGERPRINT = " #"; //$NON-NLS-1$

  /**
   * Returns the computed fingerprint for the given resource.
   *
   * @param resource
   *          the resource
   * @return the fingerprint
   */
  String computeFingerprint(Resource resource);

  /**
   * Returns the computed fingerprint for the given object.
   *
   * @param object
   *          the object
   * @return the fingerprint
   */
  String computeFingerprint(EObject object);

}
