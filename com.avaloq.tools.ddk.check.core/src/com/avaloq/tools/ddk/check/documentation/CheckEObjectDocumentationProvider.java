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

import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * An implementation of a JavaDoc-like documentation providers that enriches documentation with
 * other elements from the Check language.
 */
public class CheckEObjectDocumentationProvider extends JavaDocCommentDocumentationProvider {

  @Override
  public String getDocumentation(final EObject object) { // NOPMD
    // TODO possibly extend with other documentation
    return super.getDocumentation(object);
  }

  @Inject
  private CheckGrammarAccess grammarAccess;

  /**
   * Performs a special check on check catalogs: the first node belonging to check catalog and
   * having a multi-line comment as grammar element is used for {@link #getJavaDocComment(INode)}.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected String findComment(final EObject object) {
    if (object instanceof CheckCatalog) {
      ICompositeNode node = NodeModelUtils.getNode(object);
      try {
        INode find = Iterables.find(node.getAsTreeIterable(), abstractNode -> //
        abstractNode.getSemanticElement() instanceof CheckCatalog && abstractNode.getGrammarElement() == grammarAccess.getML_COMMENTRule());
        return getJavaDocComment(find);
      } catch (NoSuchElementException e) {
        return null;
      }
    }
    return super.findComment(object);
  }

}
