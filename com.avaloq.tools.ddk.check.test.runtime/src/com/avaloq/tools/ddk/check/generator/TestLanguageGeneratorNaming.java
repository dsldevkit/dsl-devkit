/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.generator;

import org.eclipse.xtext.xtext.generator.model.TypeReference;

import com.avaloq.tools.ddk.xtext.generator.GeneratorNaming;


/**
 * Naming of the test language's generated classes, keeping the activator name that the UI manifest and the tests refer to.
 */
public class TestLanguageGeneratorNaming extends GeneratorNaming {

  @Override
  public TypeReference getEclipsePluginActivator() {
    return new TypeReference("com.avaloq.tools.ddk.check.ui.internal", "TestLanguageActivator"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
