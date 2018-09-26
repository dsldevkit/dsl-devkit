/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.serializer.sequencer;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.LeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parsetree.reconstr.IHiddenTokenHelper;
import org.eclipse.xtext.parsetree.reconstr.impl.NodeIterator;
import org.eclipse.xtext.parsetree.reconstr.impl.TokenUtil;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.ISequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.ISyntacticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.IHiddenTokenSequencer;
import org.eclipse.xtext.util.Pair;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * Customized hidden token sequencer based on {@link org.eclipse.xtext.serializer.sequencer.HiddenTokenSequencer}.
 * Is capable of handling the cases with reordering of the semantic elements.
 */
public class ReorderingHiddenTokenSequencer implements IHiddenTokenSequencer, ISyntacticSequenceAcceptor {

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  private static final String NEW_LINE = "\n"; //$NON-NLS-1$

  @Inject
  private IHiddenTokenHelper hiddenTokenHelper;

  @Inject
  private TokenUtil tokenUtil;

  /** Contains the set of already processed whitespace/comment nodes. */
  private final Set<INode> emittedComments = Sets.newHashSet();

  private ISequenceAcceptor delegate;

  private INode lastNode;

  private INode lastEmittedNode;

  private INode rootNode;

  private int rootOffset;

  private int rootEndOffset;

  // Implementation of the IHiddenTokenSequencer interface.

  @Override
  @Deprecated
  public void init(final EObject context, final EObject semanticObject, final ISequenceAcceptor sequenceAcceptor, final Acceptor errorAcceptor) {
    this.delegate = sequenceAcceptor;
    this.lastNode = NodeModelUtils.findActualNodeFor(semanticObject);
    this.rootNode = lastNode;
    if (rootNode != null) {
      this.rootOffset = rootNode.getTotalOffset();
      this.rootEndOffset = rootNode.getTotalEndOffset();
    }
  }

  @Override
  public void init(final ISerializationContext context, final EObject semanticObject, final ISequenceAcceptor sequenceAcceptor, final Acceptor errorAcceptor) {
    this.delegate = sequenceAcceptor;
    this.lastNode = NodeModelUtils.findActualNodeFor(semanticObject);
    this.rootNode = lastNode;
    if (rootNode != null) {
      this.rootOffset = rootNode.getTotalOffset();
      this.rootEndOffset = rootNode.getTotalEndOffset();
    }
  }

  // Implementation of the ISyntacticSequenceAcceptor interface

  @Override
  public void acceptAssignedCrossRefDatatype(final RuleCall ruleCall, final String token, final EObject value, final int index, final ICompositeNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = getLastLeaf(node);
    delegate.acceptAssignedCrossRefDatatype(ruleCall, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptAssignedCrossRefEnum(final RuleCall ruleCall, final String token, final EObject value, final int index, final ICompositeNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = getLastLeaf(node);
    delegate.acceptAssignedCrossRefEnum(ruleCall, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptAssignedCrossRefKeyword(final Keyword keyword, final String token, final EObject value, final int index, final ILeafNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = node;
    delegate.acceptAssignedCrossRefKeyword(keyword, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptAssignedCrossRefTerminal(final RuleCall ruleCall, final String token, final EObject value, final int index, final ILeafNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = node;
    delegate.acceptAssignedCrossRefTerminal(ruleCall, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptAssignedDatatype(final RuleCall ruleCall, final String token, final Object value, final int index, final ICompositeNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = getLastLeaf(node);
    delegate.acceptAssignedDatatype(ruleCall, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptAssignedEnum(final RuleCall enumRuleCall, final String token, final Object value, final int index, final ICompositeNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = getLastLeaf(node);
    delegate.acceptAssignedEnum(enumRuleCall, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptAssignedKeyword(final Keyword keyword, final String token, final Object value, final int index, final ILeafNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = node;
    delegate.acceptAssignedKeyword(keyword, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptAssignedTerminal(final RuleCall terminalRuleCall, final String token, final Object value, final int index, final ILeafNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = node;
    delegate.acceptAssignedTerminal(terminalRuleCall, token, value, index, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptUnassignedAction(final Action action) {
    delegate.acceptUnassignedAction(action);
  }

  @Override
  public void acceptUnassignedDatatype(final RuleCall datatypeRuleCall, final String token, final ICompositeNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = getLastLeaf(node);
    delegate.acceptUnassignedDatatype(datatypeRuleCall, token, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptUnassignedEnum(final RuleCall enumRuleCall, final String token, final ICompositeNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = getLastLeaf(node);
    delegate.acceptUnassignedEnum(enumRuleCall, token, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptUnassignedKeyword(final Keyword keyword, final String token, final ILeafNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = node;
    delegate.acceptUnassignedKeyword(keyword, token, node);
    emitTrailingTokens(node);
  }

  @Override
  public void acceptUnassignedTerminal(final RuleCall terminalRuleCall, final String token, final ILeafNode node) {
    emitHiddenTokens(getHiddenNodesBetween(lastNode, node));
    lastNode = node;
    delegate.acceptUnassignedTerminal(terminalRuleCall, token, node);
    emitTrailingTokens(node);
  }

  @Override
  public boolean enterAssignedAction(final Action action, final EObject semanticChild, final ICompositeNode node) {
    return delegate.enterAssignedAction(action, semanticChild, node);
  }

  @Override
  public boolean enterAssignedParserRuleCall(final RuleCall ruleCall, final EObject semanticChild, final ICompositeNode node) {
    return delegate.enterAssignedParserRuleCall(ruleCall, semanticChild, node);
  }

  @Override
  @Deprecated
  public void enterUnassignedParserRuleCall(final RuleCall ruleCall) {
    delegate.enterUnassignedParserRuleCall(ruleCall);
  }

  @Override
  public void leaveAssignedAction(final Action action, final EObject semanticChild) {
    delegate.leaveAssignedAction(action, semanticChild);
  }

  @Override
  public void leaveAssignedParserRuleCall(final RuleCall ruleCall, final EObject semanticChild) {
    delegate.leaveAssignedParserRuleCall(ruleCall, semanticChild);
  }

  @Override
  @Deprecated
  public void leaveUnssignedParserRuleCall(final RuleCall ruleCall) {
    delegate.leaveUnssignedParserRuleCall(ruleCall);
  }

  @Override
  public void finish() {
    if (rootNode != null && rootNode.equals(rootNode.getRootNode())) {
      List<INode> hidden = getRemainingHiddenNodesInContainer(lastNode, rootNode);
      if (!hidden.isEmpty()) {
        emitHiddenTokens(hidden);
        lastNode = rootNode;
      }
    }
    delegate.finish();
  }

  /**
   * Outputs the list of given hidden tokens.
   *
   * @param hiddens
   *          list of hidden tokens, may be {@code null}
   */
  private void emitHiddenTokens(final List<INode> hiddens) {
    if (hiddens == null || hiddens.isEmpty()) {
      return;
    }
    boolean lastNonWhitespace = true;
    for (INode node : hiddens) {
      if (node instanceof LeadingCommentsMarkerNode && !(lastEmittedNode != null && lastEmittedNode.getText().endsWith(NEW_LINE))) {
        delegate.acceptWhitespace(hiddenTokenHelper.getWhitespaceRuleFor(null, NEW_LINE), NEW_LINE, null);
      }
      if (tokenUtil.isCommentNode(node)) {
        if (lastNonWhitespace) {
          delegate.acceptWhitespace(hiddenTokenHelper.getWhitespaceRuleFor(null, EMPTY_STRING), EMPTY_STRING, null);
        }
        lastNonWhitespace = true;
        delegate.acceptComment((AbstractRule) node.getGrammarElement(), node.getText(), (ILeafNode) node);
      } else {
        delegate.acceptWhitespace((AbstractRule) node.getGrammarElement(), node.getText(), (ILeafNode) node);
        lastNonWhitespace = false;
      }
      lastEmittedNode = node;
    }
    if (lastNonWhitespace) {
      delegate.acceptWhitespace(hiddenTokenHelper.getWhitespaceRuleFor(null, EMPTY_STRING), EMPTY_STRING, null);
    }
    // Register all the hiddens as already emitted ones not to output same nodes twice
    emittedComments.addAll(hiddens);
  }

  /**
   * Helper method to find and emit the trailing hidden tokens for the given node.
   *
   * @param node
   *          element of the node model, can be {@code null}
   */
  private void emitTrailingTokens(final INode node) {
    // We can now clear the set, as we are not really interested in what has been emitted before the given node
    // However the newly emitted trailing tokens will be registered in 'emitHiddenTokens'
    emittedComments.clear();
    emitHiddenTokens(getTrailingCommentsIncludingWhitespace(node));
  }

  /**
   * Returns a list of hidden tokens between two given nodes.
   *
   * @param from
   *          starting search point in the node model, can be {@code null}
   * @param to
   *          end search point in the node model, can be {@code null}
   * @return list of hidden tokens, can be {@code null}, if nothing found
   */
  private List<INode> getHiddenNodesBetween(final INode from, final INode to) {
    if (from == null && to == null) {
      return null;
    }

    List<INode> out = null;
    Set<EObject> deletedSemanticElements = Sets.newHashSet();

    if (to == null) {
      out = getFollowingHiddenTokens(from, deletedSemanticElements);
    } else if (from == null) {
      out = getPrecedingHiddenTokens(to, deletedSemanticElements);
    } else {
      out = getHiddenTokensBetween(from, to, deletedSemanticElements);
    }

    if (out.isEmpty()) {
      return null;
    } else {
      return filterNodesOfDeletedElements(out, deletedSemanticElements);
    }
  }

  /**
   * Helper method that collects all the hidden tokens that follow the given node.
   *
   * @param from
   *          starting point in the node model, must not be {@code null}
   * @param deletedSemanticElements
   *          set of semantic elements that have been deleted from the model, must not be {@code null}
   * @return list of hidden tokens, never {@code null}, can be empty
   */
  private List<INode> getFollowingHiddenTokens(final INode from, final Set<EObject> deletedSemanticElements) {
    List<INode> result = Lists.newArrayList();
    NodeIterator nodes = new NodeIterator(from);
    while (nodes.hasNext()) {
      INode next = nodes.next();
      if (next.getTotalOffset() > rootEndOffset || next.equals(lastEmittedNode)) {
        break;
      } else if (tokenUtil.isWhitespaceOrCommentNode(next)) {
        if (!emittedComments.contains(next)) {
          result.add(next);
        }
      } else if (belongsToDeletedElement(next)) {
        handleDeletedElement(result, deletedSemanticElements, next);
        nodes.prune();
      } else {
        break;
      }
    }
    return result;
  }

  /**
   * Helper method that collects all the preceding hidden tokens for the given node.
   *
   * @param to
   *          node model element to get the tokens for, must not be {@code null}
   * @param deletedSemanticElements
   *          set of semantic elements that have been deleted from the model, must not be {@code null}
   * @return list of hidden tokens, never {@code null}, can be empty
   */
  private List<INode> getPrecedingHiddenTokens(final INode to, final Set<EObject> deletedSemanticElements) {
    List<INode> result = Lists.newLinkedList();
    NodeIterator nodes = new NodeIterator(to);
    while (nodes.hasPrevious()) {
      INode previous = nodes.previous();
      if (previous.getTotalEndOffset() < rootOffset || previous.equals(lastEmittedNode)) {
        break;
      } else if (tokenUtil.isWhitespaceOrCommentNode(previous)) {
        if (!emittedComments.contains(previous)) {
          result.add(0, previous);
        }
      } else if (belongsToDeletedElement(previous)) {
        handleDeletedElement(result, deletedSemanticElements, previous);
        nodes.prune();
      } else {
        break;
      }
    }
    return result;
  }

  /**
   * Collects all the hidden tokens between two given nodes of the node model.
   *
   * @param from
   *          node that serves as a start point, must not be {@code null}
   * @param to
   *          search end point, must not be {@code null}
   * @param deletedSemanticElements
   *          set of the deleted semantic elements, must not be {@code null}
   * @return list of hidden tokens, never {@code null}, but can be empty
   */
  private List<INode> getHiddenTokensBetween(final INode from, final INode to, final Set<EObject> deletedSemanticElements) {
    EObject fromElement = NodeModelUtils.findActualSemanticObjectFor(from);
    if (from.equals(NodeModelUtils.getNode(fromElement))) {
      // If the starting node represents some container, emit the comments that belong to it
      // This is needed to correctly handle some edge cases like ImportList in AvqScript
      // Logic for distinguishing between container's comments and the ones of first element is expected to be implemented in 'isLeadingCommentFor'
      emitContainerComments(from);
    }
    List<INode> result = Lists.newArrayList();
    boolean handleReordering = from.getTotalOffset() > to.getTotalOffset();
    if (!handleReordering) {
      // Elements are not reordered
      // Just going through the interval and collecting comments, unless they have already been emitted
      NodeIterator nodes = new NodeIterator(from);
      while (nodes.hasNext()) {
        INode next = nodes.next();
        if (tokenUtil.isWhitespaceOrCommentNode(next)) {
          if (!emittedComments.contains(next)) {
            result.add(next);
          }
        } else if (next.equals(to)) {
          // We have hit the 'to' node
          // If it is a composite one, we have to iterate through its children
          // and collect whitespaces/comments until we encounter first token (keyword, identifier...)
          if (next instanceof ICompositeNode && (GrammarUtil.isDatatypeRuleCall(next.getGrammarElement())
              || GrammarUtil.isEnumRuleCall(next.getGrammarElement()) || next.getGrammarElement() instanceof CrossReference)) {
            while (nodes.hasNext()) {
              INode lastNodeChild = nodes.next();
              if (tokenUtil.isWhitespaceOrCommentNode(lastNodeChild)) {
                if (!emittedComments.contains(lastNodeChild)) {
                  result.add(lastNodeChild);
                }
              } else if (lastNodeChild instanceof ILeafNode) {
                break;
              }
            }
            break;
          } else {
            // 'to' node is not a composite one, nothing to do here, just exit the loop
            break;
          }
        } else if (belongsToDeletedElement(next)) {
          handleDeletedElement(result, deletedSemanticElements, next);
          nodes.prune();
        } else if (tokenUtil.isToken(next)) {
          // We have encountered some token, but not the one we expected
          // Will be handled by invoking 'getLeadingCommentsIncludingWhitespace' method later
          handleReordering = true;
          break;
        }
      }
    }
    if (handleReordering) {
      return getLeadingCommentsIncludingWhitespace(to);
    }
    return result;
  }

  /**
   * Collects & emits the set of comments/whitespaces that logically belong to a container of some objects and <b>not</b> to the first child of the container.
   *
   * @param container
   *          node model element that represents a container, must not be {@code null}
   */
  private void emitContainerComments(final INode container) {
    List<INode> containerComments = Lists.newArrayList();
    Set<INode> firstElementComments = getHiddenNodesBelongingTo(NodeModelUtils.findActualSemanticObjectFor(container));
    NodeIterator nodes = new NodeIterator(container);
    while (nodes.hasNext()) {
      INode next = nodes.next();
      if (firstElementComments.contains(next) || tokenUtil.isToken(next)) {
        break;
      } else if (tokenUtil.isWhitespaceOrCommentNode(next)) {
        containerComments.add(next);
      }
    }
    emitHiddenTokens(containerComments);
  }

  /**
   * Helper method to check if the given node is owned by the deleted semantic element.
   *
   * @param node
   *          element of the node model, can be {@code null}
   * @return true if node corresponds to some deleted semantic element, false otherwise
   */
  private boolean belongsToDeletedElement(final INode node) {
    return node instanceof ICompositeNode && node.getSemanticElement() != null && node.getSemanticElement().eResource() == null;
  }

  /**
   * Handles the case of deleted semantic element.
   * <ul>
   * <li>Deleted EObject is registered as such</li>
   * <li>Corresponding comments are added to the output collection, will be filtered out later</li>
   * </ul>
   *
   * @param out
   *          list of tokens to be emitted, must not be {@code null}
   * @param deletedSemanticElements
   *          set of deleted element, must not be {@code null}
   * @param nodeOfDeletedElement
   *          node model element that corresponds to the deleted semantic element, must not be {@code null}
   */
  private void handleDeletedElement(final List<INode> out, final Set<EObject> deletedSemanticElements, final INode nodeOfDeletedElement) {
    deletedSemanticElements.add(nodeOfDeletedElement.getSemanticElement());
    Pair<List<ILeafNode>, List<ILeafNode>> surroundingHiddenTokens = tokenUtil.getLeadingAndTrailingHiddenTokens(nodeOfDeletedElement);
    for (INode node : Iterables.concat(surroundingHiddenTokens.getFirst(), surroundingHiddenTokens.getSecond())) {
      if (!emittedComments.contains(node)) {
        out.add(node);
      }
    }
  }

  /**
   * Filters out all the whitespaces\comments that belong to the deleted model element.
   *
   * @param allHiddenTokens
   *          collection of all hidden token, must not be {@code null}
   * @param deletedElements
   *          set of deleted semantic elements, must not be {@code null}
   * @return filtered list of tokens, never {@code null}, can be empty
   */
  private List<INode> filterNodesOfDeletedElements(final List<INode> allHiddenTokens, final Set<EObject> deletedElements) {
    List<INode> filtered = Lists.newArrayList();
    filtered.addAll(allHiddenTokens);
    for (EObject deletedElement : deletedElements) {
      filtered.removeAll(getHiddenNodesBelongingTo(deletedElement));
    }
    return filtered;
  }

  /**
   * Returns all the hidden nodes that are owned by the given element of the semantic model.
   * That is, all the leading, trailing comments and the whitespaces in between.
   *
   * @param semanticElement
   *          element of the semantic model, can be {@code null}
   * @return set of hidden token, never {@code null}, can be empty
   */
  private Set<INode> getHiddenNodesBelongingTo(final EObject semanticElement) {
    ICompositeNode node = NodeModelUtils.findActualNodeFor(semanticElement);
    if (node == null) {
      return Collections.emptySet();
    }
    Set<INode> associatedNodes = Sets.newHashSet();
    associatedNodes.addAll(getLeadingCommentsIncludingWhitespace(node));
    associatedNodes.addAll(getTrailingCommentsIncludingWhitespace(node));
    return associatedNodes;
  }

  /**
   * Returns the trailing comments of the given node.
   * Last leaf node is considered to be a start point of the search.
   *
   * @param node
   *          element of the node model to find comments for, can be {@code null}
   * @return list of hidden tokens, can be empty if nothing found, never {@code null}
   */
  private List<INode> getTrailingCommentsIncludingWhitespace(final INode node) {
    if (node instanceof ICompositeNode) {
      for (INode child : node.getAsTreeIterable().reverse()) {
        if (child instanceof ILeafNode && !((ILeafNode) child).isHidden()) {
          return getTrailingCommentsIncludingWhitespace((ILeafNode) child);
        }
      }
    } else if (node instanceof ILeafNode) {
      return getTrailingCommentsIncludingWhitespace((ILeafNode) node);
    }
    return Collections.emptyList();
  }

  /**
   * Returns the leading comments of the given node.
   * First leaf node is considered to be an end point of the search.
   *
   * @param node
   *          element of the node model to find comments for, can be {@code null}
   * @return list of hidden tokens, can be empty if nothing found, never {@code null}
   */
  private List<INode> getLeadingCommentsIncludingWhitespace(final INode node) {
    if (node instanceof ICompositeNode) {
      for (INode child : node.getAsTreeIterable()) {
        if (child instanceof ILeafNode && !((ILeafNode) child).isHidden()) {
          return getLeadingCommentsIncludingWhitespace((ILeafNode) child);
        }
      }
    } else if (node instanceof ILeafNode) {
      return getLeadingCommentsIncludingWhitespace((ILeafNode) node);
    }
    return Collections.emptyList();
  }

  /**
   * Returns the leading comments of the given leaf node.
   *
   * @param node
   *          leaf element of the node model to look up to, must not be {@code null}
   * @return list of hidden tokens, can be empty, never {@code null}
   */
  private List<INode> getLeadingCommentsIncludingWhitespace(final ILeafNode node) {
    List<INode> associatedNodes = Lists.newLinkedList();
    Set<INode> pendingWhitespace = Sets.newHashSet();
    INode lastLink = node;
    NodeIterator nodes = new NodeIterator(lastLink);
    while (nodes.hasPrevious()) {
      INode prev = nodes.previous();
      if (tokenUtil.isCommentNode(prev)) {
        if (isLeadingCommentFor(prev, lastLink)) {
          lastLink = prev;
          associatedNodes.addAll(0, pendingWhitespace);
          pendingWhitespace.clear();
          associatedNodes.add(0, prev);
        } else {
          break;
        }
      } else if (tokenUtil.isWhitespaceNode(prev)) {
        pendingWhitespace.add(prev);
      } else if (prev instanceof ILeafNode) {
        break;
      }
    }
    associatedNodes.addAll(0, pendingWhitespace);
    // We want trailing comments to have precedence over leading comments
    // Therefore all the trailing comments of the previous node must be computed
    // and deleted from the results, as they have been already output in previous call of 'emitTrailingTokens' method
    EObject nodeObject = NodeModelUtils.findActualSemanticObjectFor(node);
    ICompositeNode actualNode = NodeModelUtils.findActualNodeFor(nodeObject);
    INode previousNode = actualNode.getPreviousSibling();
    if (previousNode instanceof ICompositeNode) {
      associatedNodes.removeAll(getTrailingCommentsIncludingWhitespace(previousNode));
    }

    // Following line is need to insert a new line after the leading comments if it is needed, but wasn't originally present
    // See also usage in 'emitHiddenTokens'
    associatedNodes.add(0, new LeadingCommentsMarkerNode());
    return associatedNodes;
  }

  /**
   * Returns trailing comments of the given leaf node.
   *
   * @param node
   *          leaf element of the node model to start looking from, must not be {@code null}
   * @return list of hidden tokens, can be empty, never {@code null}
   */
  private List<INode> getTrailingCommentsIncludingWhitespace(final ILeafNode node) {
    List<INode> associatedNodes = Lists.newLinkedList();
    Set<INode> pendingWhitespace = Sets.newHashSet();
    INode lastLink = node;
    NodeIterator nodes = new NodeIterator(lastLink);
    while (nodes.hasNext()) {
      INode next = nodes.next();
      if (tokenUtil.isCommentNode(next)) {
        if (isTrailingCommentFor(next, lastLink)) {
          lastLink = next;
          associatedNodes.addAll(pendingWhitespace);
          pendingWhitespace.clear();
          associatedNodes.add(next);
        } else {
          break;
        }
      } else if (tokenUtil.isWhitespaceNode(next)) {
        pendingWhitespace.add(next);
      } else if (next instanceof ILeafNode) {
        break;
      }
    }
    return associatedNodes;
  }

  /**
   * Checks whether the given node is the leading comment of another one.
   *
   * @param comment
   *          node to check, can be {@code null}
   * @param node
   *          node that is expected to own the comment, must not be {@code null}
   * @return true if 'comment' node represents the leading comment, false otherwise
   */
  protected boolean isLeadingCommentFor(final INode comment, final INode node) {
    return tokenUtil.isCommentNode(comment) && (node.getStartLine() >= comment.getEndLine());
  }

  /**
   * Checks whether the given node is the trailing comment of another one.
   *
   * @param comment
   *          node to check, can be {@code null}
   * @param node
   *          node that is expected to own the comment, must not be {@code null}
   * @return true if 'comment' node represents trailing comment, false otherwise
   */
  protected boolean isTrailingCommentFor(final INode comment, final INode node) {
    if (!tokenUtil.isCommentNode(comment)) {
      return false;
    }
    if (node.getText().endsWith(NEW_LINE)) {
      return false;
    }
    return comment.getStartLine() == node.getEndLine();
  }

  /**
   * Helper method that returns the last leaf node of the given node.
   *
   * @param node
   *          input to traverse, must not be {@code} null
   * @return last leaf node if input is composite node, node itself otherwise. Never {@code null}
   */
  private INode getLastLeaf(final INode node) {
    INode lastChild = node;
    while (lastChild instanceof ICompositeNode) {
      lastChild = ((ICompositeNode) lastChild).getLastChild();
    }
    return lastChild;
  }

  /**
   * Returns the list of nodes that belong to 'root' node and follow the 'from' node.
   *
   * @param from
   *          node model element to start from, must not be {@code null}
   * @param root
   *          top-level node model element, must not be {@code null}
   * @return list of hiddens nodes, possibly empty, but never {@code null}
   */
  private List<INode> getRemainingHiddenNodesInContainer(final INode from, final INode root) {
    if (from == null || root == null) {
      return Collections.emptyList();
    }
    List<INode> out = Lists.newArrayList();
    NodeIterator nodes = new NodeIterator(from);
    while (nodes.hasNext()) {
      INode next = nodes.next();
      if (next.getStartLine() > root.getEndLine()) {
        return out;
      } else if (tokenUtil.isWhitespaceOrCommentNode(next)) {
        out.add(next);
      } else if (tokenUtil.isToken(next)) {
        return out;
      }
    }
    return out;
  }

  /**
   * Auxiliary leaf node subtype used to indicate that a new line must be inserted at some point.
   */
  private static class LeadingCommentsMarkerNode extends LeafNode {
    @Override
    public String getText() {
      return NEW_LINE;
    }
  }

}
