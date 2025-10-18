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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateTranslator;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

import com.google.inject.Inject;


/**
 * Common methods for testing implementations of {@link TemplateVariableResolver}
 */
public class TemplateVariableResolverTestHelper {

  @Inject
  private TemplateProposalProviderHelper helper;

  private final TemplateTranslator translator = new TemplateTranslator();

  /**
   * Create a {@link TemplateVariable}.
   *
   * @param resolver
   *          {@link TemplateVariableResolver@} of the type which will resolve the {@link TemplateVariable} created, may not be {@code null}
   * @param name
   *          the name of the variable, may not be {@code null}
   * @param values
   *          the values available at this variable, non-empty, may not be {@code null}
   * @return a {@link TemplateVariable}
   * @throws {@link
   *           NullPointerException} if resolver.getType(), name or values is null
   * @throws {@link
   *           IllegalArgumentException} if resolver.getType() or name contains whitespace or values is empty
   * @throws {@link
   *           TemplateException}
   *           if translation failed
   */
  public TemplateVariable createTemplateVariable(final TemplateVariableResolver resolver, final String name, final Object... values) throws NullPointerException, IllegalArgumentException, TemplateException {

    // Jump through hoops to create a real TemplateVariable because TemplateVariableType is final thus cannot be mocked,
    // and has protected constructors thus cannot be directly instantiated
    final String pattern = helper.createTemplateVariablePattern(resolver.getType(), name, values);
    final TemplateVariable[] variables = translator.translate(pattern).getVariables();
    assertEquals(1, variables.length, "Exactly one variable should be returned"); //$NON-NLS-1$
    return variables[0];
  }

}
