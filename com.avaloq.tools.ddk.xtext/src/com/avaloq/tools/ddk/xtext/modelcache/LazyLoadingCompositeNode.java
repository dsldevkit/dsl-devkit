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
package com.avaloq.tools.ddk.xtext.modelcache;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.BidiIterable;
import org.eclipse.xtext.nodemodel.BidiTreeIterable;
import org.eclipse.xtext.nodemodel.BidiTreeIterator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.nodemodel.util.NodeTreeIterator;

import com.avaloq.tools.ddk.xtext.linking.ILazyLinkingResource2;


/**
 * ICompositeNode implementation that triggers the loading of the node model of the resource and delegates all calls to the real node.
 */
public class LazyLoadingCompositeNode extends CompositeNodeWithSemanticElement {

  private CompositeNodeWithSemanticElement delegateNode;

  /**
   * TreeIterator implementation that replaces all instances of a real node by its lazy equivalent.
   * <p>
   * This class required in order to allow for the direct pointer comparison used in NodeModelUtils.
   * </p>
   */
  private class LazyNodeTreeIterator extends NodeTreeIterator {
    private final INode lazyNode;
    private final INode realNode;

    LazyNodeTreeIterator(final INode realNode, final INode lazyNode) {
      super(realNode);
      this.lazyNode = lazyNode;
      this.realNode = realNode;
    }

    @Override
    public INode next() {
      INode node = super.next();
      if (node.equals(realNode)) {
        return lazyNode;
      }
      return node;
    }

    @Override
    public INode previous() {
      INode node = super.previous();
      if (node.equals(realNode)) {
        return lazyNode;
      }
      return node;
    }
  }

  /**
   * Initializes the parent resource's node model and fetches the real node.
   */
  private void initializeDelegateNode() {
    if (delegateNode == null) {
      EObject proxiedObject = basicGetSemanticElement();
      Resource resource = proxiedObject.eResource();
      if (resource instanceof ILazyLinkingResource2) {
        ILazyLinkingResource2 lazyLinkingResource = (ILazyLinkingResource2) resource;
        lazyLinkingResource.getModelManager().loadBinaryModels(ResourceModelType.NODE);
        ICompositeNode compositeNode = NodeModelUtils.getNode(proxiedObject);
        if (compositeNode instanceof CompositeNodeWithSemanticElement) {
          delegateNode = (CompositeNodeWithSemanticElement) compositeNode;
        }
        if (delegateNode == null) {
          throw new IllegalStateException("Resource " + resource.getURI() + " does not have an INode for one of its EObjects"); //$NON-NLS-1$ //$NON-NLS-2$
        }
      } else {
        throw new IllegalStateException("Unexpected resource type '" + resource.getClass().getSimpleName() + "' for resource " + resource.getURI()); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public ICompositeNode getParent() {
    initializeDelegateNode();
    return delegateNode.getParent();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasSiblings() {
    initializeDelegateNode();
    return delegateNode.hasSiblings();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasPreviousSibling() {
    initializeDelegateNode();
    return delegateNode.hasPreviousSibling();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasNextSibling() {
    initializeDelegateNode();
    return delegateNode.hasNextSibling();
  }

  /** {@inheritDoc} */
  @Override
  public INode getPreviousSibling() {
    initializeDelegateNode();
    return delegateNode.getPreviousSibling();
  }

  /** {@inheritDoc} */
  @Override
  public INode getNextSibling() {
    initializeDelegateNode();
    return delegateNode.getNextSibling();
  }

  /** {@inheritDoc} */
  @Override
  public ICompositeNode getRootNode() {
    initializeDelegateNode();
    return delegateNode.getRootNode();
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<ILeafNode> getLeafNodes() {
    initializeDelegateNode();
    return delegateNode.getLeafNodes();
  }

  /** {@inheritDoc} */
  @Override
  public int getTotalOffset() {
    initializeDelegateNode();
    return delegateNode.getTotalOffset();
  }

  /** {@inheritDoc} */
  @Override
  public int getOffset() {
    initializeDelegateNode();
    return delegateNode.getOffset();
  }

  /** {@inheritDoc} */
  @Override
  public int getTotalLength() {
    initializeDelegateNode();
    return delegateNode.getTotalLength();
  }

  /** {@inheritDoc} */
  @Override
  public int getLength() {
    initializeDelegateNode();
    return delegateNode.getLength();
  }

  /** {@inheritDoc} */
  @Override
  public int getTotalEndOffset() {
    initializeDelegateNode();
    return delegateNode.getTotalEndOffset();
  }

  /** {@inheritDoc} */
  @Override
  public int getTotalStartLine() {
    initializeDelegateNode();
    return delegateNode.getTotalStartLine();
  }

  /** {@inheritDoc} */
  @Override
  public int getStartLine() {
    initializeDelegateNode();
    return delegateNode.getStartLine();
  }

  /** {@inheritDoc} */
  @Override
  public int getTotalEndLine() {
    initializeDelegateNode();
    return delegateNode.getTotalEndLine();
  }

  /** {@inheritDoc} */
  @Override
  public int getEndLine() {
    initializeDelegateNode();
    return delegateNode.getEndLine();
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {
    initializeDelegateNode();
    return delegateNode.getText();
  }

  /** {@inheritDoc} */
  @Override
  public EObject getGrammarElement() {
    initializeDelegateNode();
    return delegateNode.getGrammarElement();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasDirectSemanticElement() {
    initializeDelegateNode();
    return delegateNode.hasDirectSemanticElement();
  }

  /** {@inheritDoc} */
  @Override
  public SyntaxErrorMessage getSyntaxErrorMessage() {
    initializeDelegateNode();
    return delegateNode.getSyntaxErrorMessage();
  }

  /** {@inheritDoc} */
  @Override
  public BidiTreeIterable<INode> getAsTreeIterable() {
    initializeDelegateNode();
    return this; // delegateNode.getAsTreeIterable();
  }

  /** {@inheritDoc} */
  @Override
  public BidiTreeIterator<INode> iterator() {
    return new LazyNodeTreeIterator(delegateNode, this);
  }

  /** {@inheritDoc} */
  @Override
  public BidiIterable<INode> getChildren() {
    initializeDelegateNode();
    return delegateNode.getChildren();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasChildren() {
    initializeDelegateNode();
    return delegateNode.hasChildren();
  }

  /** {@inheritDoc} */
  @Override
  public INode getFirstChild() {
    initializeDelegateNode();
    return delegateNode.getFirstChild();
  }

  /** {@inheritDoc} */
  @Override
  public INode getLastChild() {
    initializeDelegateNode();
    return delegateNode.getLastChild();
  }

  /** {@inheritDoc} */
  @Override
  public int getLookAhead() {
    initializeDelegateNode();
    return delegateNode.getLookAhead();
  }

  /** {@inheritDoc} */
  @Override
  public void setTarget(final Notifier newTarget) {
    if (newTarget instanceof EObject) {
      super.setTarget(newTarget);
    }
  }

  /** {@inheritDoc} */
  @Override
  public boolean isAdapterForType(final Object type) {
    Class<?> typeClass = (type instanceof Class<?>) ? (Class<?>) type : type.getClass();
    return LazyLoadingCompositeNode.class.isAssignableFrom(typeClass);
  }
}
