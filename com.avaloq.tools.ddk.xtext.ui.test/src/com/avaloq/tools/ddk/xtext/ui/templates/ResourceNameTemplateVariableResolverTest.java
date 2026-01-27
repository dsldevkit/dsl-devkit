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

package com.avaloq.tools.ddk.xtext.ui.templates;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.google.common.collect.Iterables;
import com.google.inject.Guice;


@ExtendWith(InjectionExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResourceNameTemplateVariableResolverTest {

  private static final Object[] FILE = new Object[] {"file"}; //$NON-NLS-1$
  private static final String FILENAME = "filename"; //$NON-NLS-1$

  private static XtextTemplateContext mockContext;
  private static IFile mockFile;

  private static TemplateVariableResolverTestHelper helper;

  private static ResourceNameTemplateVariableResolver resolver;

  @BeforeAll
  void beforeAll() {
    mockContext = Mockito.mock(XtextTemplateContext.class);
    mockFile = Mockito.mock(IFile.class);

    helper = Guice.createInjector(new XtextRuntimeModule()).getInstance(TemplateVariableResolverTestHelper.class);

    resolver = new ResourceNameTemplateVariableResolver();

    IXtextDocument mockDocument = Mockito.mock(IXtextDocument.class);
    Mockito.when(mockContext.getDocument()).thenReturn(mockDocument);
    Mockito.when(mockDocument.getAdapter(IFile.class)).thenReturn(mockFile);
  }

  @AfterAll
  void afterAll() {
    mockContext = null;
    mockFile = null;

    helper = null;

    resolver = null;
  }

  @Test
  void testResolveValuesWithNullVariable() {
    assertThrows(NullPointerException.class, () -> resolver.resolveValues(null, mockContext));
  }

  @Test
  void testResolveValuesWithNullContext() {
    assertThrows(NullPointerException.class, () -> resolver.resolveValues(Mockito.mock(TemplateVariable.class), null));
  }

  @Test
  void testResolveValuesWithFileWithoutExtension() throws TemplateException {
    final String filename = "filenamewithnoextension"; //$NON-NLS-1$
    testResolveValues(FILE, filename, filename);
  }

  @Test
  void testResolveValuesWithFileWithExtension() throws TemplateException {
    testResolveValues(FILE, "filename.with.extension", "filename.with"); //$NON-NLS-1$//$NON-NLS-2$
  }

  @Test
  void testResolveValuesWithExtraParams() throws TemplateException {
    testResolveValues(new Object[] {FILE[0], "other", "random", "values"}, FILENAME, FILENAME); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  @Test
  void testResolveValuesWithUnknownParam() throws TemplateException {
    testResolveValues(new Object[] {"This is not the parameter you are looking for"}, FILENAME); //$NON-NLS-1$
  }

  @Test
  void testResolveValuesWithWrongTypeOfParam() throws TemplateException {
    testResolveValues(new Object[] {42}, FILENAME);
  }

  /**
   * Test resolveValues().
   *
   * @param values
   *          values to resolve
   * @param filename
   *          filename to return from the mock {@link IFile}
   * @param expectedResolvedValues
   *          expected return value
   */
  void testResolveValues(final Object[] values, final String filename, final String... expectedResolvedValues) throws TemplateException {
    // ARRANGE
    final TemplateVariable variable = helper.createTemplateVariable(resolver, "name", values); //$NON-NLS-1$
    Mockito.when(mockFile.getName()).thenReturn(filename);

    // ACT
    final String[] actualResolvedValues = Iterables.toArray(resolver.resolveValues(variable, mockContext), String.class);

    // ASSERT
    assertArrayEquals(expectedResolvedValues, actualResolvedValues, "Resolved values"); //$NON-NLS-1$
  }

}
