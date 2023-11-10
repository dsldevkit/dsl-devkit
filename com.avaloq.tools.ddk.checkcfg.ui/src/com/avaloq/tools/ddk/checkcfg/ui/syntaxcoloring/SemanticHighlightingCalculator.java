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
package com.avaloq.tools.ddk.checkcfg.ui.syntaxcoloring;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;


/**
 * Calculates semantic highlighting for Check Configuration models. Applies styles as defined in the
 * {@link com.avaloq.tools.ddk.checkcfg.ui.syntaxcoloring.SemanticHighlightingConfiguration highlighting configuration}.
 */
public class SemanticHighlightingCalculator implements ISemanticHighlightingCalculator {

  // NOTE: will never be called, is currently disabled (see UI module)

  
  @Override
  public void provideHighlightingFor(final XtextResource resource, final IHighlightedPositionAcceptor acceptor, final CancelIndicator cancelIndicator) {
    if (resource == null) {
      return;
    }
    Iterator<EObject> iter = EcoreUtil.getAllContents(resource, true);
    while (iter.hasNext()) {
      EObject current = iter.next();

      if (current instanceof ConfiguredCheck && ((ConfiguredCheck) current).getSeverity().equals(SeverityKind.IGNORE)) {
        INode node = getFirstParseTreeNode(current, CheckcfgPackage.Literals.CONFIGURED_CHECK__CHECK);
        highlightNode(node, SemanticHighlightingConfiguration.DISABLED_VALUE_ID, acceptor);
      }
    }
  }

  /**
   * Highlights a given parse tree node.
   *
   * @param node
   *          the node from the parse tree
   * @param id
   *          the id of the configuration
   * @param acceptor
   *          the acceptor
   */
  private void highlightNode(final INode node, final String id, final IHighlightedPositionAcceptor acceptor) {
    if (node == null) {
      return;
    }
    if (node instanceof ILeafNode) {
      acceptor.addPosition(node.getOffset(), node.getLength(), id);
    } else {
      for (ILeafNode leaf : node.getLeafNodes()) {
        if (!leaf.isHidden()) {
          acceptor.addPosition(leaf.getOffset(), leaf.getLength(), id);
        }
      }
    }
  }

  /**
   * Gets the first node from the parse tree for given semantic object and structural feature.
   *
   * @param semanticElement
   *          the semantic element
   * @param feature
   *          the structural feature
   * @return the first parse tree node or {@code null} if none found
   */
  private INode getFirstParseTreeNode(final EObject semanticElement, final EStructuralFeature feature) {
    if (feature == null) {
      return NodeModelUtils.findActualNodeFor(semanticElement);
    }
    List<INode> nodes = NodeModelUtils.findNodesForFeature(semanticElement, feature);
    if (!nodes.isEmpty()) {
      return nodes.get(0);
    }
    return null;
  }

}
