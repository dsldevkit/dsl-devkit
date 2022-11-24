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

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.test.core.AfterAll;
import com.avaloq.tools.ddk.test.core.BeforeAll;
import com.avaloq.tools.ddk.test.core.junit.runners.ClassRunner;
import com.google.inject.Guice;


@RunWith(ClassRunner.class)
public class SimpleEnumTemplateVariableResolverTest {

  private static XtextTemplateContext mockContext;
  private static TemplateVariableResolverTestHelper helper;
  private static SimpleEnumTemplateVariableResolver resolver;

  @BeforeAll
  public void beforeAll() {
    mockContext = mock(XtextTemplateContext.class);
    helper = Guice.createInjector(new XtextRuntimeModule()).getInstance(TemplateVariableResolverTestHelper.class);
    resolver = new SimpleEnumTemplateVariableResolver();
  }

  @AfterAll
  public void afterAll() {
    mockContext = null;
    helper = null;
    resolver = null;
  }

  @Test(expected = NullPointerException.class)
  public void testResolveValuesWithNullVariable() {
    resolver.resolveValues(null, mockContext);
  }

  @Test
  public void testResolveValuesWithOneParam() throws TemplateException {
    testResolveValues("Value"); //$NON-NLS-1$
  }

  @Test
  public void testResolveValuesWithMultipleParams() throws TemplateException {
    testResolveValues("Value 1", "Value 2", "Value 3"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
  }

  private void testResolveValues(final Object... values) throws TemplateException {
    // ARRANGE
    final TemplateVariable variable = helper.createTemplateVariable(resolver, "name", values); //$NON-NLS-1$

    // ACT
    final List<String> resolvedValues = resolver.resolveValues(variable, mockContext);

    // ASSERT
    assertArrayEquals("Resolved values", values, resolvedValues.toArray(new String[resolvedValues.size()])); //$NON-NLS-1$
  }

}
