/**
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Group AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.ui.templates;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.inject.Singleton;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.Validate;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider;

/**
 * Helper methods for {@link ITemplateProposalProvider} implementations.
 */
@Singleton
public class TemplateProposalProviderHelper {

  private static final String SIMPLE_ENUM_TYPE = new SimpleEnumTemplateVariableResolver().getType();

  /**
   * Create a literal value pattern, including quotes if necessary, for a {@link Template}.
   *
   * @param name          the name of the variable, may not be {@code null} nor contain whitespace
   * @param defaultValue  default value, may be {@code null}
   * @return  pattern, never {@code null}
   * @throws NullPointerException if name is null
   * @throws IllegalArgumentException if name contains whitespace
   */
  public String createLiteralValuePattern(final String name, final Object defaultValue) throws NullPointerException, IllegalArgumentException {
    final String pattern = createTemplateVariablePattern(SIMPLE_ENUM_TYPE, name, defaultValue);

    if (defaultValue instanceof String) {
      // Surround pattern with quotes
      return "\"" + pattern + "\"";
    } else {
      return pattern;
    }
  }

  /**
   * Create a variable pattern for a {@link Template}.
   *
   * @param   type    the type of the variable, may not be {@code null} nor contain whitespace
   * @param   name    the name of the variable, may not be {@code null} nor contain whitespace
   * @param   values  the values available at this variable, may not be {@code null} nor empty
   * @return  pattern, never {@code null}
   * @throws NullPointerException if type, name or values is null
   * @throws IllegalArgumentException if type or name contains whitespace or values is empty
   */
  public String createTemplateVariablePattern(final String type, final String name, final Object... values) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(type);
    Objects.requireNonNull(name);
    Objects.requireNonNull(values);
    Validate.isTrue(!type.chars().anyMatch(Character::isWhitespace));
    Validate.isTrue(!name.chars().anyMatch(Character::isWhitespace));
    Validate.notEmpty(values);

    final Object[] useValues;
    if (values.length == 1 && values[0] instanceof Boolean) {
      // Offer both false and true as dropdown options
      useValues = new Object[]{values[0], !((Boolean) values[0])};
    } else {
      useValues = values;
    }

    final String sanitisedValues = Arrays.stream(useValues)
        .map(it ->
            // Escape double-quotes to comply with string grammar.
            // Double-up single-quotes to comply with template pattern grammar.
            // Wrap each value in single quotes.
            "'" + StringEscapeUtils.escapeJava(String.valueOf(it)).replace("'", "''") + "'")
        .collect(Collectors.joining(", "));

    return "${" + name + ":" + type + "(" + sanitisedValues + ")}";
  }

}
