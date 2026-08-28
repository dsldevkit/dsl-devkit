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
package com.avaloq.tools.ddk.check.generator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.ContextVariable;
import com.avaloq.tools.ddk.check.check.Documented;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.check.SeverityRange;


/**
 * Compiles the public API of a check catalog into a check source file, a so-called catalog stub.
 * <p>
 * The stub declares the catalog, its categories, its checks with their severities, formal parameters and contexts, but none of the implementation of those
 * checks: the constraints of the contexts are emitted as empty blocks and the members, the implementations and the import section of the catalog are omitted
 * altogether. It can thus be shipped in place of the catalog source itself, allowing consumers to resolve references to the checks of the catalog without
 * disclosing how those checks are implemented.
 * </p>
 * <p>
 * Type references and default values are emitted in a self-contained manner, that is, with fully qualified type names, so that the stub can be parsed and
 * linked without the import section of the original catalog.
 * </p>
 */
@SuppressWarnings("nls")
public class CheckStubCompiler {

  private static final String INDENT = "  ";

  /**
   * Compiles the public API of the given catalog into check source.
   *
   * @param catalog
   *          the catalog to compile, must not be {@code null}
   * @return the source of the catalog stub, never {@code null}
   */
  public CharSequence compile(final CheckCatalog catalog) {
    StringBuilder out = new StringBuilder(1024);
    out.append("/*\n");
    out.append(" * Public API of check catalog ").append(catalog.getName()).append(".\n");
    out.append(" *\n");
    out.append(" * Generated file, do not edit. The implementation of the checks is deliberately not part of this file.\n");
    out.append(" */\n");
    out.append("package ").append(catalog.getPackageName()).append('\n');
    out.append('\n');
    appendDocumentation(out, catalog, "");
    if (catalog.isFinal()) {
      out.append("final ");
    }
    out.append("catalog ").append(catalog.getName());
    if (catalog.getGrammar() != null && catalog.getGrammar().getName() != null) {
      out.append(" for grammar ").append(catalog.getGrammar().getName());
    }
    out.append(" {\n");
    for (EObject content : catalog.eContents()) {
      if (content instanceof Category) {
        appendCategory(out, (Category) content, INDENT);
      } else if (content instanceof Check) {
        appendCheck(out, (Check) content, INDENT);
      }
    }
    out.append("}\n");
    return out;
  }

  /**
   * Appends the given category and its checks.
   *
   * @param out
   *          the builder to append to, must not be {@code null}
   * @param category
   *          the category to append, must not be {@code null}
   * @param indent
   *          the indentation to use, must not be {@code null}
   */
  private void appendCategory(final StringBuilder out, final Category category, final String indent) {
    out.append('\n');
    appendDocumentation(out, category, indent);
    out.append(indent).append("category ");
    if (category.getId() != null) {
      out.append(category.getId()).append(' ');
    }
    appendString(out, category.getLabel());
    out.append(" {\n");
    for (Check check : category.getChecks()) {
      appendCheck(out, check, indent + INDENT);
    }
    out.append(indent).append("}\n");
  }

  /**
   * Appends the given check without the constraints of its contexts.
   *
   * @param out
   *          the builder to append to, must not be {@code null}
   * @param check
   *          the check to append, must not be {@code null}
   * @param indent
   *          the indentation to use, must not be {@code null}
   */
  private void appendCheck(final StringBuilder out, final Check check, final String indent) {
    out.append('\n');
    appendDocumentation(out, check, indent);
    appendSeverityRange(out, check.getSeverityRange(), indent);
    out.append(indent);
    if (check.isFinal()) {
      out.append("final ");
    }
    if (check.isExternal()) {
      out.append("external ");
    }
    if (check.eIsSet(CheckPackage.Literals.CHECK__KIND)) {
      out.append(check.getKind().getLiteral()).append(' ');
    }
    out.append(literal(check.getDefaultSeverity())).append(' ');
    if (check.getId() != null) {
      out.append(check.getId()).append(' ');
    }
    appendString(out, check.getLabel());
    appendFormalParameters(out, check);
    if (check.getGivenMessage() != null) {
      out.append(" message ");
      appendString(out, check.getGivenMessage());
    }
    if (check.getContexts().isEmpty()) {
      out.append('\n');
    } else {
      out.append(" {\n");
      for (Context context : check.getContexts()) {
        appendContext(out, context, indent + INDENT);
      }
      out.append(indent).append("}\n");
    }
  }

  /**
   * Appends the given severity range, if any.
   *
   * @param out
   *          the builder to append to, must not be {@code null}
   * @param range
   *          the severity range to append, may be {@code null}
   * @param indent
   *          the indentation to use, must not be {@code null}
   */
  private void appendSeverityRange(final StringBuilder out, final SeverityRange range, final String indent) {
    if (range != null) {
      out.append(indent).append("@SeverityRange(").append(literal(range.getMinSeverity())).append(" .. ").append(literal(range.getMaxSeverity())).append(")\n");
    }
  }

  /**
   * Appends the formal parameters of the given check, if any.
   *
   * @param out
   *          the builder to append to, must not be {@code null}
   * @param check
   *          the check whose formal parameters to append, must not be {@code null}
   */
  private void appendFormalParameters(final StringBuilder out, final Check check) {
    if (check.getFormalParameters().isEmpty()) {
      return;
    }
    out.append(" (");
    boolean first = true;
    for (FormalParameter parameter : check.getFormalParameters()) {
      if (!first) {
        out.append(", ");
      }
      first = false;
      out.append(typeText(parameter.getType())).append(' ').append(parameter.getName()).append(" = ").append(nodeText(parameter.getRight()));
      if (parameter.getLabel() != null) {
        out.append(' ');
        appendString(out, parameter.getLabel());
      }
    }
    out.append(')');
  }

  /**
   * Appends the given context with an empty constraint.
   *
   * @param out
   *          the builder to append to, must not be {@code null}
   * @param context
   *          the context to append, must not be {@code null}
   * @param indent
   *          the indentation to use, must not be {@code null}
   */
  private void appendContext(final StringBuilder out, final Context context, final String indent) {
    ContextVariable variable = context.getContextVariable();
    out.append(indent).append("for ").append(typeText(variable.getType()));
    if (variable.getName() != null) {
      out.append(' ').append(variable.getName());
    }
    out.append(" {}\n");
  }

  /**
   * Appends the documentation of the given element, if any, as a multi line comment.
   *
   * @param out
   *          the builder to append to, must not be {@code null}
   * @param documented
   *          the documented element, must not be {@code null}
   * @param indent
   *          the indentation to use, must not be {@code null}
   */
  private void appendDocumentation(final StringBuilder out, final Documented documented, final String indent) {
    String description = documented.getDescription();
    if (Strings.isEmpty(description)) {
      return;
    }
    out.append(indent).append("/**\n");
    for (String line : description.replace("*/", "* /").split("\r?\n")) {
      out.append(indent).append(" *");
      String trimmed = line.trim();
      if (!trimmed.isEmpty()) {
        out.append(' ').append(trimmed);
      }
      out.append('\n');
    }
    out.append(indent).append(" */\n");
  }

  /**
   * Appends the given value as a string literal.
   *
   * @param out
   *          the builder to append to, must not be {@code null}
   * @param value
   *          the value to append, may be {@code null}
   */
  private void appendString(final StringBuilder out, final String value) {
    out.append('"').append(value == null ? "" : Strings.convertToJavaString(value)).append('"');
  }

  /**
   * Returns the keyword of the given severity.
   *
   * @param severity
   *          the severity, must not be {@code null}
   * @return the keyword of the severity, never {@code null}
   */
  private String literal(final SeverityKind severity) {
    return severity.getLiteral();
  }

  /**
   * Returns the fully qualified text of the given type reference.
   *
   * @param reference
   *          the type reference, must not be {@code null}
   * @return the text to emit for the type reference, never {@code null}
   */
  private String typeText(final JvmTypeReference reference) {
    String qualifiedName = reference.getQualifiedName();
    return Strings.isEmpty(qualifiedName) ? nodeText(reference) : qualifiedName.replace('$', '.');
  }

  /**
   * Returns the source text of the given object. Used for expressions that are known to be self-contained literals.
   *
   * @param object
   *          the object whose source text to return, must not be {@code null}
   * @return the source text of the object, or an empty string if it is not available
   */
  private String nodeText(final EObject object) {
    INode node = NodeModelUtils.findActualNodeFor(object);
    return node == null ? "" : NodeModelUtils.getTokenText(node).trim();
  }

}
