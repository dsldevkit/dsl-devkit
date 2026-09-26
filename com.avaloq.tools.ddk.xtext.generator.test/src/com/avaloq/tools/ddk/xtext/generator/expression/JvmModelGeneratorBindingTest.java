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
package com.avaloq.tools.ddk.xtext.generator.expression;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.eclipse.xtext.generator.IGenerator;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.export.ExportStandaloneSetup;
import com.avaloq.tools.ddk.xtext.export.generator.ExportJvmModelGenerator;
import com.avaloq.tools.ddk.xtext.scope.ScopeStandaloneSetup;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeJvmModelGenerator;


/**
 * Tests that the Scope and Export languages generate with their own JVM model generators, which establish the project
 * resource loader once per generated resource.
 */
public class JvmModelGeneratorBindingTest {

  @Test
  void testScopeGeneratorIsBound() {
    assertInstanceOf(ScopeJvmModelGenerator.class, new ScopeStandaloneSetup().createInjector().getInstance(IGenerator.class));
  }

  @Test
  void testExportGeneratorIsBound() {
    assertInstanceOf(ExportJvmModelGenerator.class, new ExportStandaloneSetup().createInjector().getInstance(IGenerator.class));
  }

}
