/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.core.test;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.check.core.test.AbstractCheckGenerationTestCase;
import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.google.common.collect.ImmutableMap;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import junit.framework.TestCase;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for autogeneration of check issue code to label map.
 */
@InjectWith(CheckInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class IssueCodeToLabelMapGenerationTest extends AbstractCheckGenerationTestCase {
  private final static String PACKAGE_NAME = "mypackage";
  
  private final static String CATALOG_NAME = "MyCatalog";
  
  /**
   * Test the map generated from a catalog with no checks.
   */
  @Test
  public void testMapGenerationWithNoChecks() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(IssueCodeToLabelMapGenerationTest.PACKAGE_NAME, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("catalog ");
    _builder.append(IssueCodeToLabelMapGenerationTest.CATALOG_NAME, "");
    _builder.newLineIfNotEmpty();
    _builder.append("for grammar com.avaloq.tools.ddk.check.Check {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String source = _builder.toString();
    final Map<String, String> expectedMap = Collections.<String, String>emptyMap();
    this.testMapGeneration(source, expectedMap);
  }
  
  /**
   * Test the map generated from a catalog with checks.
   */
  @Test
  public void testMapGeneration() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(IssueCodeToLabelMapGenerationTest.PACKAGE_NAME, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.check.Check");
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.check.Context");
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.check.Documented");
    _builder.newLine();
    _builder.newLine();
    _builder.append("catalog ");
    _builder.append(IssueCodeToLabelMapGenerationTest.CATALOG_NAME, "");
    _builder.newLineIfNotEmpty();
    _builder.append("for grammar com.avaloq.tools.ddk.check.Check {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("live error ID1 \"Label 1\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("message \"Message 1\" {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("for Documented elem {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("switch elem {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Context : issue on elem");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Check : issue on elem");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("live error ID2 \"Label 2\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("message \"Message 2\" {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("for Documented elem {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("switch elem {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Context : issue on elem");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Check : issue on elem");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String source = _builder.toString();
    Pair<String, String> _mappedTo = Pair.<String, String>of("mypackage.MyCatalogIssueCodes.id.1", "Label 1");
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("mypackage.MyCatalogIssueCodes.id.2", "Label 2");
    final Map<String, String> expectedMap = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1));
    this.testMapGeneration(source, expectedMap);
  }
  
  /**
   * Test the map generated from a catalog.
   * @param source
   *          the catalog, must not be {@code null}
   * @param expectedMap
   *          the expected map, may be {@code null}
   */
  public void testMapGeneration(final String source, final Map<String, String> expectedMap) {
    try {
      Map<String, Class<?>> compiledClassesMap = null;
      byte[] _bytes = source.getBytes();
      final ByteArrayInputStream sourceStream = new ByteArrayInputStream(_bytes);
      try {
        Map<String, Class<?>> _generateAndCompile = this.generateAndCompile(sourceStream);
        compiledClassesMap = _generateAndCompile;
      } finally {
        sourceStream.close();
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(IssueCodeToLabelMapGenerationTest.PACKAGE_NAME, "");
      _builder.append(".");
      _builder.append(IssueCodeToLabelMapGenerationTest.CATALOG_NAME, "");
      _builder.append(AbstractCheckGenerationTestCase.CATALOG_NAME_SUFFIX, "");
      final String catalogClassName = _builder.toString();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append(IssueCodeToLabelMapGenerationTest.PACKAGE_NAME, "");
      _builder_1.append(".");
      _builder_1.append(IssueCodeToLabelMapGenerationTest.CATALOG_NAME, "");
      _builder_1.append(AbstractCheckGenerationTestCase.VALIDATOR_NAME_SUFFIX, "");
      final String validatorClassName = _builder_1.toString();
      final String mapGetterName = "getIssueCodeToLabelMap";
      final Class<?> catalogClass = compiledClassesMap.get(catalogClassName);
      Class<?> _get = compiledClassesMap.get(validatorClassName);
      final Class<ICheckValidatorImpl> validatorClass = ((Class<ICheckValidatorImpl>) _get);
      Method _method = catalogClass.getMethod(mapGetterName);
      Object _invoke = _method.invoke(null);
      final ImmutableMap<String, String> mapFromCatalog = ((ImmutableMap<String, String>) _invoke);
      ICheckValidatorImpl _newInstance = validatorClass.newInstance();
      final ImmutableMap<String, String> mapFromValidator = _newInstance.getIssueCodeToLabelMap();
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append(catalogClassName, "");
      _builder_2.append(".");
      _builder_2.append(mapGetterName, "");
      _builder_2.append("() was generated correctly");
      TestCase.assertEquals(_builder_2.toString(), expectedMap, mapFromCatalog);
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append(validatorClassName, "");
      _builder_3.append(".");
      _builder_3.append(mapGetterName, "");
      _builder_3.append("() was generated correctly");
      TestCase.assertEquals(_builder_3.toString(), expectedMap, mapFromValidator);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
