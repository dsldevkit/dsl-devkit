/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.ui.templates

import com.google.inject.Singleton
import java.util.Objects
import org.apache.commons.lang.StringEscapeUtils
import org.apache.commons.lang.Validate
import org.eclipse.jface.text.templates.Template
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider

/**
 * Helper methods for {@link ITemplateProposalProvider} implementations.
 */
@Singleton
class TemplateProposalProviderHelper {

  static val SIMPLE_ENUM_TYPE = new SimpleEnumTemplateVariableResolver().type

  /**
   * Create a literal value pattern, including quotes if necessary, for a {@link Template}.
   *
   * @param name          the name of the variable, may not be {@code null} nor contain whitespace
   * @param defaultValue  default value, may be {@code null}
   * @return  pattern, never {@code null}
   * @throws  {@link NullPointerException}      if name is null
   * @throws  {@link IllegalArgumentException}  if name contains whitespace
   */
  def String createLiteralValuePattern(String name, Object defaultValue) throws NullPointerException, IllegalArgumentException {
    val pattern = createTemplateVariablePattern(SIMPLE_ENUM_TYPE, name, defaultValue)

    return if (defaultValue instanceof String) {
      // Surround pattern with quotes
      '''"«pattern»"'''
    } else {
      pattern
    }
  }

  /**
   * Create a variable pattern for a {@link Template}.
   *
   * @param   type    the type of the variable, may not be {@code null} nor contain whitespace
   * @param   name    the name of the variable, may not be {@code null} nor contain whitespace
   * @param   values  the values available at this variable, may not be {@code null} nor empty
   * @return  pattern, never {@code null}
   * @throws  {@link NullPointerException}      if type, name or values is null
   * @throws  {@link IllegalArgumentException}  if type or name contains whitespace or values is empty
   */
  def String createTemplateVariablePattern(String type, String name, Object... values) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(type);
    Objects.requireNonNull(name);
    Objects.requireNonNull(values);
    Validate.isTrue(!type.chars().anyMatch[Character.isWhitespace(it)]);
    Validate.isTrue(!name.chars().anyMatch[Character.isWhitespace(it)]);
    Validate.notEmpty(values);

    val Object[] useValues = if (values.length == 1 && values.get(0) instanceof Boolean) {
        // Offer both false and true as dropdown options
        #[values.get(0), !(values.get(0) as Boolean)]
      } else {
        values
      }

    val sanitisedValues = useValues.map [
      // Escape double-quotes to comply with string grammar.
      // Double-up single-quotes to comply with template pattern grammar.
      // Wrap each value in single quotes.
      "'" + StringEscapeUtils::escapeJava(String.valueOf(it)).replace("'", "''") + "'"
    ]

    return '''${«name»:«type»(«String.join(", ", sanitisedValues)»)}'''
  }

}
