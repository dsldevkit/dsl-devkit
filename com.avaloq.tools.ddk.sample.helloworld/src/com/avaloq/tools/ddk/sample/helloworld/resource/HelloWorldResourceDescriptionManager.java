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
package com.avaloq.tools.ddk.sample.helloworld.resource;

import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;


/**
 * Resource description manager for test language.
 * <p>
 * We need to extend {@link AbstractCachingResourceDescriptionManager} so check config will not exclude the language as a technical language.
 * </p>
 */
public class HelloWorldResourceDescriptionManager extends AbstractCachingResourceDescriptionManager {

}

