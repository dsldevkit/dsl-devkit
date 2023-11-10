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
package com.avaloq.tools.ddk.xtext.documentation;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.documentation.IEObjectDocumentationProviderExtension;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * The documentation provider implementation retrieves the documentation stored from single line comments preceeding the declaration. If multiple single line
 * comments exist, it will merge them into the same documentation string.
 * <p>
 * This implementation is heavily based on {@link org.eclipse.xtext.documentation.impl.MultiLineCommentDocumentationProvider}.
 * </p>
 */
@SuppressWarnings({"nls", "PMD.FinalFieldCouldBeStatic"})
public class SingleLineCommentDocumentationProvider implements IEObjectDocumentationProvider, IEObjectDocumentationProviderExtension {

  public static final String SL_DOCU_PROVIDER = "singleLineDocuProvider";

  public static final String RULE = "com.avaloq.tools.ddk.xtext.documentation.SingleLineDocumentationProvider.ruleName";
  public static final String START_TAG = "com.avaloq.tools.ddk.xtext.documentation.SingleLineDocumentationProvider.startTag";
  public static final String END_TAG = "com.avaloq.tools.ddk.xtext.documentation.SingleLineDocumentationProvider.endTag";
  public static final String IGNORE = "com.avaloq.tools.ddk.xtext.documentation.SingleLineDocumentationProvider.ignore";

  // CHECKSTYLE:CHECK-OFF VisibilityModifierCheck
  @Inject(optional = true)
  @Named(RULE)
  protected String ruleName = "SL_COMMENT";

  @Inject(optional = true)
  @Named(START_TAG)
  protected String startTag = "-- ?"; // regular expression

  @Inject(optional = true)
  @Named(END_TAG)
  protected String endTag = "\\r?\\n"; // regular expression

  @Inject(optional = true)
  @Named(IGNORE)
  protected String ignore = "--[\\+-]+\\r?\\n"; // regular expression

  private Pattern commentPattern;

  // CHECKSTYLE:CHECK-ON VisibilityModifierCheck

  /**
   * Get single line comments before the context element. The returned documentation is never {@code null}.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public String getDocumentation(final EObject context) {
    StringBuilder builder = new StringBuilder();
    for (INode node : getDocumentationNodes(context)) {
      String comment = node.getText();
      builder.append(extractDocumentation(comment));
    }
    return builder.toString();
  }

  
  @Override
  public List<INode> getDocumentationNodes(final EObject object) {
    ICompositeNode node = NodeModelUtils.getNode(object);
    if (node == null) {
      return Collections.emptyList();
    }

    // get all single line comments before a non hidden leaf node
    List<INode> result = Lists.newArrayList();
    for (ILeafNode leaf : node.getLeafNodes()) {
      if (!leaf.isHidden()) {
        break;
      }
      EObject grammarElement = leaf.getGrammarElement();
      if (grammarElement instanceof AbstractRule && ruleName.equals(((AbstractRule) grammarElement).getName())) {
        String comment = leaf.getText();
        if (getCommentPattern().matcher(comment).matches() && !comment.matches(ignore)) {
          result.add(leaf);
        }
      }
    }

    return result;
  }

  /**
   * Returns the comment pattern defined by {@link #startTag} and {@link #endTag} which can be used to match an entire comment line.
   *
   * @return Regular expression pattern
   */
  protected Pattern getCommentPattern() {
    if (commentPattern == null) {
      commentPattern = Pattern.compile(startTag + ".*" + endTag);
    }
    return commentPattern;
  }

  /**
   * Finds trailing comment for a given context object. I.e. the comment after / on the same line as the context object.
   *
   * @param context
   *          the object
   * @return the documentation string
   */
  protected String findTrailingComment(final EObject context) {
    StringBuilder returnValue = new StringBuilder();
    ICompositeNode node = NodeModelUtils.getNode(context);
    if (node != null) {
      final int contextEndLine = node.getEndLine();
      // process all leaf nodes first
      for (ILeafNode leave : node.getLeafNodes()) {
        addComment(returnValue, leave, contextEndLine);
      }
      // we also need to process siblings (leave nodes only) due to the fact that the last comment after
      // a given element is not a leaf node of that element anymore.
      INode sibling = node.getNextSibling();
      while (sibling instanceof ILeafNode) {
        addComment(returnValue, (ILeafNode) sibling, contextEndLine);
        sibling = sibling.getNextSibling();
      }
    }
    return returnValue.toString();
  }

  /**
   * Add a comment for a given node (if applicable).
   *
   * @param builder
   *          add comments to this
   * @param node
   *          the node read comments from
   * @param contextEndLine
   *          the end line (number) of the root object, used for line number comparison
   */
  private void addComment(final StringBuilder builder, final ILeafNode node, final int contextEndLine) {
    if (node.getGrammarElement() instanceof TerminalRule && ruleName.equals(((TerminalRule) node.getGrammarElement()).getName())
        && node.getStartLine() == contextEndLine) {
      final String comment = node.getText();
      if (getCommentPattern().matcher(comment).matches()) {
        builder.append(comment);
      }
    }
  }

  /**
   * Get trailing single line comment on a given context element,
   * i.e., on the same line as the context element ends.
   * Returned documentation is never <code>null</code>.
   * <p>
   * {@inheritDoc}
   */
  public String getTrailingDocumentation(final EObject context) {
    String returnValue = findTrailingComment(context);
    return extractDocumentation(returnValue);
  }

  /**
   * Extract the documentation from a comment line by striping off comment pre- and postfixes and other irrelevant whitespaces.
   *
   * @param commentLine
   *          comment line to extract documentation from
   * @return stripped documentation
   */
  private String extractDocumentation(final String commentLine) {
    if (commentLine != null) {
      return commentLine.replaceFirst("\\A" + startTag, "");
    } else {
      return "";
    }
  }

}
