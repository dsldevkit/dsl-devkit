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
import static org.mockito.Mockito.mock;

import java.util.List;

import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.inject.Guice;


@ExtendWith(InjectionExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleEnumTemplateVariableResolverTest {

  private static XtextTemplateContext mockContext;
  private static TemplateVariableResolverTestHelper helper;
  private static SimpleEnumTemplateVariableResolver resolver;

  @BeforeAll
  void beforeAll() {
    mockContext = mock(XtextTemplateContext.class);
    helper = Guice.createInjector(new XtextRuntimeModule()).getInstance(TemplateVariableResolverTestHelper.class);
    resolver = new SimpleEnumTemplateVariableResolver();
  }

  @AfterAll
  void afterAll() {
    mockContext = null;
    helper = null;
    resolver = null;
  }

  @Test
  void testResolveValuesWithNullVariable() {
    assertThrows(NullPointerException.class, () -> resolver.resolveValues(null, mockContext));
  }

  @Test
  void testResolveValuesWithOneParam() throws TemplateException {
    testResolveValues("Value"); //$NON-NLS-1$
  }

  @Test
  void testResolveValuesWithMultipleParams() throws TemplateException {
    testResolveValues("Value 1", "Value 2", "Value 3"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
  }

  private void testResolveValues(final Object... values) throws TemplateException {
    // ARRANGE
    final TemplateVariable variable = helper.createTemplateVariable(resolver, "name", values); //$NON-NLS-1$

    // ACT
    final List<String> resolvedValues = resolver.resolveValues(variable, mockContext);

    // ASSERT
    assertArrayEquals(values, resolvedValues.toArray(new String[resolvedValues.size()]), "Resolved values"); //$NON-NLS-1$
  }

}
