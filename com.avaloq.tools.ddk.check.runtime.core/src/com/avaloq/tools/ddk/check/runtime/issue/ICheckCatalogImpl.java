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

import org.eclipse.emf.ecore.resource.Resource;


/**
 * Basis interface for implementing check catalogs.
 * Resources containing check catalogs are identified by the optional {@code catalog} extension attribute.
 * Descriptors of this kind are created for check validator extensions.
 * 
 * @see {@link com.avaloq.tools.ddk.check.runtime.internal.CheckCatalogDescriptor}
 */
public interface ICheckCatalogImpl extends Resource {

}

