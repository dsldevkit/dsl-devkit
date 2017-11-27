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
package com.avaloq.tools.ddk.xtextspy;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.avaloq.tools.ddk.xtextspy.internal.Activator;
import com.google.inject.Injector;


/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass.
 */
// CHECKSTYLE:OFF
public class XtextSpyExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  // CHECKSTYLE:ON

  @Override
  protected Bundle getBundle() {
    return Activator.getDefault().getBundle();
  }

  @Override
  protected Injector getInjector() {
    return Activator.getDefault().getInjector();
  }

}
