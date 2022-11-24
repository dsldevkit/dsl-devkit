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
package com.avaloq.tools.ddk.check.documentation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * @author Christoph Kulla - Initial contribution and API
 */
public class JavaDocCommentDocumentationProvider implements IEObjectDocumentationProvider {

  // TODO refactor with MultilineCommentDocumentationProvider

  public static final String RULE = "com.avaloq.tools.ddk.check.documentation.JavaDocCommentDocumentationProvider.ruleName";
  public static final String START_TAG = "com.avaloq.tools.ddk.check.documentation.JavaDocCommentDocumentationProvider.startTag";
  public static final String END_TAG = "com.avaloq.tools.ddk.check.documentation.JavaDocCommentDocumentationProvider.endTag";
  public static final String LINE_PREFIX = "com.avaloq.tools.ddk.check.documentation.JavaDocCommentDocumentationProvider.linePrefix";
  public static final String LINE_POSTFIX = "com.avaloq.tools.ddk.check.documentation.JavaDocCommentDocumentationProvider.linePostfix";
  public static final String WHITESPACE = "com.avaloq.tools.ddk.check.documentation.JavaDocCommentDocumentationProvider.whitespace";

  @Inject(optional = true)
  @Named(RULE)
  private final String ruleName = "ML_COMMENT"; // NOPMD

  @Inject(optional = true)
  @Named(START_TAG)
  private final String startTag = "/\\*\\*"; // regular expression //NOPMD

  @Inject(optional = true)
  @Named(END_TAG)
  private final String endTag = "\\*/"; // regular expression //NOPMD

  @Inject(optional = true)
  @Named(LINE_PREFIX)
  private final String linePrefix = "\\** ?"; // regular expression //NOPMD

  @Inject(optional = true)
  @Named(LINE_POSTFIX)
  private final String linePostfix = "\\**"; // regular expression //NOPMD

  @Inject(optional = true)
  @Named(WHITESPACE)
  private final String whitespace = "( |\\t)*"; // regular expression //NOPMD

  /**
   * Returns the textual value of the node, if it is a JavaDoc-like comment.
   * 
   * @param node
   *          a node
   * @return the comment as a string if any, else null
   */
  protected String getJavaDocComment(final INode node) {
    if (node instanceof ILeafNode && !((ILeafNode) node).isHidden()) {
      return null;
    }
    if (node instanceof ILeafNode && node.getGrammarElement() instanceof TerminalRule
        && ruleName.equalsIgnoreCase(((TerminalRule) node.getGrammarElement()).getName())) {
      String comment = ((ILeafNode) node).getText();
      if (comment.matches("(?s)" + startTag + ".*")) {
        return comment;
      }
    }
    return null;
  }

  /**
   * Returns true if the node is a JavaDoc-like comment.
   * 
   * @param node
   *          a node
   * @return the comment as a string if any, else null
   */
  public boolean isJavaDocComment(final INode node) {
    return getJavaDocComment(node) != null;
  }

  /**
   * Retrieves a comment for the given object.
   * 
   * @param object
   *          an object
   * @return the comment corresponding to the closest JavaDoc-like comment
   */
  protected String findComment(final EObject object) {
    String returnValue = null;
    ICompositeNode node = NodeModelUtils.getNode(object);
    if (node != null) {
      // get the last multi line comment before a non hidden leaf node
      for (INode abstractNode : node.getAsTreeIterable()) {
        String comment = getJavaDocComment(abstractNode);
        if (comment != null) {
          returnValue = comment;
          break;
        }
      }
    }
    return returnValue;
  }

  /**
   * Retrieves a comment for the given object.
   * 
   * @param object
   *          an object
   * @return the description corresponding to the closest JavaDoc-like comment
   */
  public String getDocumentation(final EObject object) {
    String returnValue = findComment(object);
    if (returnValue != null) {
      returnValue = returnValue.replaceAll("\\A" + startTag, "");
      returnValue = returnValue.replaceAll(endTag + "\\z", "");
      returnValue = returnValue.replaceAll("(?m)^" + whitespace + linePrefix, "");
      returnValue = returnValue.replaceAll("(?m)" + whitespace + linePostfix + whitespace + "$", "");
      return returnValue.trim();
    } else {
      return "";
    }
  }
}

