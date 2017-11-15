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
package com.avaloq.tools.ddk.xtext.generator.expression;

import static org.junit.Assert.assertEquals;

import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.generator.test.util.GeneratorTestUtil;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;


/**
 * Tests the code generation as implemented by the CodeGeneration.ext extension wrapped by {@link Compiler}.
 */
public class CodeGenerationXTest extends AbstractXtextTest {

  private CompilerX getCompiler() {
    return (CompilerX) getTestInformation().getTestObject(CompilerX.class);
  }

  @Override
  protected GeneratorTestUtil getXtextTestUtil() {
    return GeneratorTestUtil.getInstance();
  }

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    final ExecutionContextImpl executionContext = new ExecutionContextImpl();
    executionContext.registerMetaModel(new JavaBeansMetaModel());
    executionContext.registerMetaModel(new EmfRegistryMetaModel());
    final CompilationContext context = new CompilationContext(executionContext);
    getTestInformation().putTestObject(CompilerX.class, new CompilerX(context));
  }

  /**
   * This test class does not have a test source file. {@inheritDoc}
   */
  @Override
  protected String getTestSourceFileName() {
    return null;
  }

  @Test
  public void testliterals() throws Exception {
    // CHECKSTYLE:CONSTANTS-OFF
    assertEquals("42", compile("42")); // NOPMD
    assertEquals("4.2", compile("4.2")); // NOPMD
    assertEquals("true", compile("true")); // NOPMD
    assertEquals("null", compile("null")); // NOPMD
    assertEquals("\"foo\"", compile("\"foo\"")); // NOPMD
    assertEquals("\"foo\"", compile("\'foo\'")); // NOPMD
    // CHECKSTYLE:CONSTANTS-ON
  }

  @Test
  public void testListLiterals() throws Exception {
    assertEquals("java.util.Collections.<org.eclipse.emf.ecore.EObject> emptyList()", compile("{}")); // NOPMD
    assertEquals("java.util.Collections.singletonList(1)", compile("{1}")); // NOPMD
    assertEquals("com.google.common.collect.Lists.newArrayList(1, 2, 3)", compile("{1,2,3}")); // NOPMD
  }

  @Test
  public void testIdentifiers() throws Exception {
    assertEquals("obj.getTrue()", compile("^true")); // NOPMD
  }

  @Test
  public void testBracketing() throws Exception {
    // CHECKSTYLE:CONSTANTS-OFF
    assertEquals("(4 + 2) * 3", compile("(4 + 2) * 3")); // NOPMD
    assertEquals("(4 + 2) * 3 * 4", compile("(4 + 2) * 3 * 4")); // NOPMD
    assertEquals("\"x\" + 2 + 3 + 4", compile("(\'x\' + 2) + 3 + 4")); // NOPMD
    assertEquals("obj.getFoo() + 2 + 3 + 4", compile("(foo + 2) + (3 + 4)")); // NOPMD
    assertEquals("true && false && false", compile("(true && false) && false")); // NOPMD
    assertEquals("(true || false) && false", compile("(true || false) && false")); // NOPMD
    // CHECKSTYLE:CONSTANTS-ON
  }

  @Test
  public void testBooleanLogic() throws Exception {
    // CHECKSTYLE:CONSTANTS-OFF
    assertEquals("true", compile("true")); // NOPMD
    assertEquals("false", compile("false")); // NOPMD
    assertEquals("!true", compile("!true")); // NOPMD
    assertEquals("true && false", compile("true && false")); // NOPMD
    assertEquals("true || false", compile("true || false")); // NOPMD
    assertEquals("true ? 1 : 2", compile("true ? 1 : 2")); // NOPMD
    assertEquals("true ? 1 : 2", compile("if true then 1 else 2")); // NOPMD
    assertEquals("1 == 2", compile("1 == 2")); // NOPMD
    assertEquals("1 != 2", compile("1 != 2")); // NOPMD
    assertEquals("1 >= 2", compile("1 >= 2")); // NOPMD
    assertEquals("1 <= 2", compile("1 <= 2")); // NOPMD
    assertEquals("1 > 2", compile("1 > 2")); // NOPMD
    assertEquals("1 < 2", compile("1 < 2")); // NOPMD
    // CHECKSTYLE:CONSTANTS-ON
  }

  @Test
  public void testArithmetics() throws Exception {
    // CHECKSTYLE:CONSTANTS-OFF
    assertEquals("4 + 2", compile("4 + 2")); // NOPMD
    assertEquals("4 - 2", compile("4 - 2")); // NOPMD
    assertEquals("4 / 2", compile("4 / 2")); // NOPMD
    assertEquals("4 * 2", compile("4 * 2")); // NOPMD
    assertEquals("-42", compile("-42")); // NOPMD
    // CHECKSTYLE:CONSTANTS-ON
  }

  @Test
  public void testPrefixExpressions() throws Exception {
    // CHECKSTYLE:CONSTANTS-OFF
    assertEquals("-(4 * 2)", compile("-(4 * 2)")); // NOPMD
    assertEquals("-(-42)", compile("-(-42)")); // NOPMD
    assertEquals("!(!true)", compile("!(!true)")); // NOPMD

    assertEquals("!true", compile("!true")); // NOPMD
    assertEquals("!true", compile("!(true)")); // NOPMD
    assertEquals("!true && false", compile("!(true) && false")); // NOPMD
    assertEquals("!(true && false)", compile("!(true && false)")); // NOPMD
    // CHECKSTYLE:CONSTANTS-ON
  }

  @Test
  public void testInfixExpressions() throws Exception {
    // CHECKSTYLE:CONSTANTS-OFF
    assertEquals("(true ? 1 : 2) + 3", compile("(true ? 1 : 2) + 3")); // NOPMD
    assertEquals("!(true ? true : false)", compile("!(true ? true : false)")); // NOPMD
    assertEquals("!(obj instanceof org.eclipse.emf.ecore.EObject)", compile("!ecore::EObject.isInstance(this)")); // NOPMD
    // CHECKSTYLE:CONSTANTS-ON
  }

  @Test
  public void testImplicitVariable() throws Exception {
    assertEquals("obj", compile("this")); // NOPMD
  }

  @Test
  public void testCasting() throws Exception {
    assertEquals("((org.eclipse.emf.ecore.EObject) obj)", compile("(ecore::EObject) this")); // NOPMD
  }

  @Test
  public void testTypes() throws Exception {
    assertEquals("org.eclipse.emf.ecore.EObject", compile("ecore::EObject")); // NOPMD
    assertEquals("String", compile("java::lang::String")); // NOPMD
  }

  @Test
  public void testIsInstance() throws Exception {
    assertEquals("obj instanceof org.eclipse.emf.ecore.EObject", compile("ecore::EObject.isInstance(this)")); // NOPMD
  }

  @Test
  public void testEContainerNavigation() throws Exception {
    // CHECKSTYLE:CONSTANTS-OFF
    assertEquals("obj.eContainer()", compile("this.eContainer")); // NOPMD
    assertEquals("obj.eContainer()", compile("this.eContainer()")); // NOPMD
    // CHECKSTYLE:CONSTANTS-ON
  }

  @Test
  public void testTypeSelect() throws Exception {
    assertEquals(// NOPMD
        "com.google.common.collect.Iterables.filter(obj.getFoos(), org.eclipse.emf.ecore.EObject.class)", compile("this.foos.typeSelect(ecore::EObject)"));
    assertEquals(// NOPMD
        "com.google.common.collect.Iterables.filter(java.util.Collections.singletonList(obj), org.eclipse.emf.ecore.EObject.class)", compile("{this}.typeSelect(ecore::EObject)"));
  }

  @Test
  public void testCollectionExpression() throws Exception {
    assertEquals(// NOPMD
        "com.google.common.collect.Iterables.filter(java.util.Collections.singletonList(obj), new com.google.common.base.Predicate<Object>() { public boolean apply(Object e) {return true;} })", compile("{this}.select(e|true)"));
  }

  @Test
  public void testMultipleNavigations() throws Exception {
    assertEquals(// NOPMD
        "/* NOT COMPILABLE: Complex expressions like \"this.eContainer.eContainer\" cannot be translated to Java. Consider rewriting the expression or using a JAVA extension. */", compile("this.eContainer.eContainer"));
    assertEquals(// NOPMD
        "com.google.common.collect.Iterables.filter(obj.eContainer(), org.eclipse.emf.ecore.EObject.class)", compile("this.eContainer.typeSelect(ecore::EObject)"));
  }

  private String compile(final String str) throws Exception {
    return getCompiler().compile((Expression) getXtextTestUtil().getModel("test.expression", str));
  }

}
