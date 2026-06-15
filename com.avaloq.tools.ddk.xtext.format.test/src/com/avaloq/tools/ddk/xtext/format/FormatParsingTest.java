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
package com.avaloq.tools.ddk.xtext.format;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.inject.Inject;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;


@ExtendWith(InjectionExtension.class)
@InjectWith(FormatInjectorProvider.class)
@SuppressWarnings("nls")
public class FormatParsingTest {

  @Inject
  private ParseHelper<FormatConfiguration> parseHelper;

  @Test
  public void loadModel() throws Exception {
    final String input = """
        formatter for MyDsl

        const String SOME_STRING = "";
        const int SOME_INT = 2;

        Person {
        }
        """;
    final FormatConfiguration result = parseHelper.parse(input);
    assertNotNull(result);
    final boolean hasSyntaxErrors = ((XtextResource) result.eResource()).getParseResult().hasSyntaxErrors();
    assertFalse(hasSyntaxErrors,
        "Unexpected errors: %s".formatted(String.join(", ", result.eResource().getErrors().stream().map(Object::toString).toList())));
  }
}
