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
package com.avaloq.tools.ddk.xtext.export.generator;

import java.util.List;

import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.generator.trace.LocationData;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.TreeAppendableUtil;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;

import com.google.inject.Inject;


/**
 * Emits the Java source of the inferred export types, laying out the members of the generated
 * {@code <Name>ResourceDescriptionConstants} interface without blank lines in between and writing
 * empty Javadoc lines without a trailing space.
 * <p>
 * {@link JvmModelGenerator} separates every pair of members with a blank line. That reads well for
 * methods and for the fields holding the generated switches, but the resource description constants
 * are a plain list of string literals which the templates that preceded the JVM model inferrers
 * emitted on consecutive lines. Only interfaces are laid out this way; the constants interface is
 * the sole interface contributed by
 * {@link com.avaloq.tools.ddk.xtext.export.jvmmodel.ExportJvmModelInferrer}, so classes keep the
 * default spacing.
 */
public class ExportJvmModelGenerator extends JvmModelGenerator {

  /** The Javadoc prefix of a line that carries text. */
  private static final String DOC_LINE_PREFIX = " * "; //$NON-NLS-1$

  /** The Javadoc prefix of a line that carries no text. */
  private static final String EMPTY_DOC_LINE = " *"; //$NON-NLS-1$

  @Inject
  private TreeAppendableUtil treeAppendableUtil;

  /**
   * {@inheritDoc}
   * <p>
   * Members of an interface are emitted on consecutive lines instead of being separated by a blank
   * line. All other types are delegated to the default implementation.
   */
  @Override
  public ITreeAppendable generateMembersInBody(final JvmDeclaredType type, final ITreeAppendable appendable, final GeneratorConfig config) {
    if (!isInterface(type)) {
      return super.generateMembersInBody(type, appendable, config);
    }
    appendable.append("{").increaseIndentation(); //$NON-NLS-1$
    for (final JvmMember member : getMembersToBeCompiled(type)) {
      final ITreeAppendable memberAppendable = treeAppendableUtil.traceWithComments(appendable, member);
      memberAppendable.openScope();
      generateMember(member, memberAppendable, config);
      memberAppendable.closeScope();
    }
    return appendable.decreaseIndentation().newLine().append("}"); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   * <p>
   * Documentation lines that carry no text are emitted as {@code " *"} instead of {@code " * "}. The default
   * implementation builds the comment with {@code StringConcatenation.append(text, " * ")}, which prefixes every line
   * with {@code " * "} whether or not the line has any content, so the blank line that separates a description from
   * its block tags is left with a trailing space.
   */
  @Override
  protected ITreeAppendable generateDocumentation(final String text, final List<INode> documentationNodes, final ITreeAppendable appendable,
      final GeneratorConfig config) {
    final StringBuilder doc = new StringBuilder("/**"); //$NON-NLS-1$
    for (final String line : text.split("\r?\n", -1)) { //$NON-NLS-1$
      final String content = line.stripTrailing();
      doc.append('\n');
      if (content.isEmpty()) {
        doc.append(EMPTY_DOC_LINE);
      } else {
        doc.append(DOC_LINE_PREFIX).append(content);
      }
    }
    doc.append("\n */"); //$NON-NLS-1$
    if (documentationNodes.isEmpty()) {
      return appendable.append(doc.toString()).newLine();
    }
    ITextRegionWithLineInformation documentationTrace = ITextRegionWithLineInformation.EMPTY_REGION;
    for (final INode node : documentationNodes) {
      documentationTrace = documentationTrace.merge(node.getTextRegionWithLineInformation());
    }
    appendable.trace(new LocationData(documentationTrace, null)).append(doc.toString());
    return appendable.newLine();
  }

  /**
   * Tests whether the given type is an interface.
   *
   * @param type
   *          the type to test, must not be {@code null}
   * @return {@code true} if the type is an interface
   */
  private boolean isInterface(final JvmDeclaredType type) {
    return type instanceof JvmGenericType && ((JvmGenericType) type).isInterface();
  }

}
