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

import com.google.inject.ImplementedBy;


/**
 * This service allows additional export rules for a given DSL to be defined outside of its plug-in.
 */
@ImplementedBy(DefaultExportFeatureExtensionService.class)
public interface IExportFeatureExtensionService extends IExportComputer {

}
