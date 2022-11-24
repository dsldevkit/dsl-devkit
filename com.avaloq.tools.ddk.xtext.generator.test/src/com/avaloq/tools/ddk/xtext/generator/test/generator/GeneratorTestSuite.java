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
package com.avaloq.tools.ddk.xtext.generator.test.generator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.xtext.generator.expression.CodeGenerationXTest;
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContextTest;
import com.avaloq.tools.ddk.xtext.generator.expression.ExpressionsExtentionsTest;
import com.avaloq.tools.ddk.xtext.generator.test.util.EClassComparatorTest;
import com.avaloq.tools.ddk.xtext.generator.test.util.GraphTest;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
// @Format-Off
  GraphTest.class,
  EClassComparatorTest.class,
  CodeGenerationXTest.class,
  CompilationContextTest.class,
  ExpressionsExtentionsTest.class
// @Format-On
})
public class GeneratorTestSuite {
}
