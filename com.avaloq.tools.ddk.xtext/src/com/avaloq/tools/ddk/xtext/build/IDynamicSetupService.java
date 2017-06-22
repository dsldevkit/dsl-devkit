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
package com.avaloq.tools.ddk.xtext.build;

import com.google.inject.Injector;
import com.google.inject.Module;


/**
 * Specialized setup accepting an override Guice module.
 */
public interface IDynamicSetupService extends Comparable<IDynamicSetupService> {

  /**
   * Do the normal setup like the normal runtime module's {@link org.eclipse.xtext.ISetup#createInjectorAndDoEMFRegistration
   * createInjectorAndDoEMFRegistration()}, but take into account an override
   * module that can override the language's runtime module. (The stand-alone
   * builder uses this override module to provide special bindings across
   * languages, like a special IStorage2UriMapper.)
   *
   * @param overrideModule
   *          to use.
   * @param additionalModules
   *          additional optional modules to use to create the injector
   * @return the injector used to initialize the language
   */
  Injector doSetup(Module overrideModule, Module... additionalModules);

}
