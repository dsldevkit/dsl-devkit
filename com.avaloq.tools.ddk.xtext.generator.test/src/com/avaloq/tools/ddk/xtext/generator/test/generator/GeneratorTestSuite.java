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
package com.avaloq.tools.ddk.xtext.generator.test.generator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.xtext.generator.expression.CodeGenerationTest;
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContextTest;
import com.avaloq.tools.ddk.xtext.generator.expression.ExpressionsExtentionsTest;
import com.avaloq.tools.ddk.xtext.generator.test.util.EClassComparatorTest;
import com.avaloq.tools.ddk.xtext.generator.test.util.GraphTest;
import com.avaloq.tools.ddk.xtext.generator.test.util.XpandExecutionContextUtilTest;
import com.avaloq.tools.ddk.xtext.generator.xbase.test.FixedXbaseGeneratorFragmentTest;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
// @Format-Off
  GraphTest.class,
  EClassComparatorTest.class,
  CodeGenerationTest.class,
  CompilationContextTest.class,
  ExpressionsExtentionsTest.class,
  XpandExecutionContextUtilTest.class,
  FixedXbaseGeneratorFragmentTest.class
// @Format-On
})
public class GeneratorTestSuite {}
