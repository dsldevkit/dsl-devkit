/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.nodemodel.BidiIterable;
import org.eclipse.xtext.nodemodel.BidiTreeIterable;
import org.eclipse.xtext.nodemodel.BidiTreeIterator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.impl.CompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.nodemodel.util.NodeTreeIterator;
import org.eclipse.xtext.nodemodel.util.ReversedBidiTreeIterable;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.persistence.IResourceStorageFacade;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;
import org.eclipse.xtext.util.LineAndColumn;

import com.avaloq.tools.ddk.annotations.SuppressFBWarnings;


/**
 * Proxying implementation which will load and replace itself with the real node model on demand.
 * <p>
 * To conserve memory this class implements the primary interfaces rather than extending an implementation like
 * {@link org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement}.
 */
public class ProxyCompositeNode implements ICompositeNode, BidiTreeIterable<INode>, Adapter {

  /** The root node proxy stores the original EObject ID map so that it can be used when installing the real node model. */
  private List<EObject> idToEObjectMap;

  private EObject semanticElement;

  /** Caching the delegate is required because the client may call multiple methods on the proxy. */
  @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
  private CompositeNode delegate;

  /**
   * Installs a proxy node model in the given resource which when accessed will {@link ResourceLoadMode#ONLY_NODE_MODEL demand-load} the real node model.
   *
   * @param resource
   *          resource, must not be {@code null}
   */
  static void installProxyNodeModel(final Resource resource) {
    if (resource.getContents().isEmpty()) {
      return;
    }

    if (resource instanceof XtextResource xtextResource) {
      EObject root = resource.getContents().get(0);
      xtextResource.setParseResult(new ParseResult(root, createRootNode(root), false));
    }
  }

  private static ProxyCompositeNode createRootNode(final EObject eObject) {
    ProxyCompositeNode result = new ProxyCompositeNode();
    eObject.eAdapters().add(result);
    return result;
  }

  /**
   * Fill id to EOject map.
   *
   * @param parseResult
   *          the {@link IParseResult}
   */
  public static void fillIdToEObjectMap(final IParseResult parseResult) {
    if (parseResult.getRootNode() instanceof ProxyCompositeNode rootNode && rootNode.idToEObjectMap == null) {
      rootNode.idToEObjectMap = new ArrayList<>();
      ProxyAwareSerializationConversionContext.fillIdToEObjectMap(parseResult.getRootASTElement(), rootNode.idToEObjectMap);
    }
  }

  /**
   * Uninstalls the proxy node model in the given resource (if present).
   *
   * @param resource
   *          resource, must not be {@code null}
   * @return original EObject ID map or {@code null} if no proxied node model was present
   */
  static List<EObject> uninstallProxyNodeModel(final Resource resource) {
    List<EObject> result = null;
    if (!resource.getContents().isEmpty()) {
      EObject root = resource.getContents().get(0);
      ProxyCompositeNode proxyNode = uninstallProxyNode(root);
      if (proxyNode != null) {
        result = proxyNode.idToEObjectMap;
      }
    }

    if (resource instanceof XtextResource) {
      ((XtextResource) resource).setParseResult(null);
    }
    return result;
  }

  private static ProxyCompositeNode uninstallProxyNode(final EObject eObject) {
    EList<Adapter> adapters = eObject.eAdapters();
    int size = adapters.size();
    for (int i = 0; i < size; ++i) {
      Adapter adapter = adapters.get(i);
      if (adapter.isAdapterForType(ProxyCompositeNode.class)) {
        if (adapter instanceof ProxyCompositeNode) {
          return (ProxyCompositeNode) adapters.remove(i);
        }
        break;
      }
    }
    return null;
  }

  private CompositeNode delegate() {
    if (delegate == null) {
      Resource resource = semanticElement.eResource();
      if (!(resource instanceof StorageAwareResource)) {
        throw new IllegalStateException("Unexpected resource type '" + resource.getClass() + "' for resource " + resource.getURI()); //$NON-NLS-1$ //$NON-NLS-2$
      }
      synchronized (resource) {
        // delegate may have been set while we were waiting, so check again
        if (delegate != null) {
          return delegate;
        }
        StorageAwareResource storageAwareResource = (StorageAwareResource) resource;
        IResourceStorageFacade storageFacade = storageAwareResource.getResourceStorageFacade();
        DirectLinkingResourceStorageLoadable loadable = (DirectLinkingResourceStorageLoadable) storageFacade.getOrCreateResourceStorageLoadable(storageAwareResource);
        try {
          loadable.loadIntoResource(storageAwareResource, ResourceLoadMode.ONLY_NODE_MODEL);
        } catch (IOException e) {
          throw new WrappedException(e);
        }
        ICompositeNode compositeNode = NodeModelUtils.getNode(semanticElement);
        if (!(compositeNode instanceof CompositeNode)) {
          URI uri = EcoreUtil.getURI(semanticElement);
          throw new IllegalStateException("No composite node found for " + uri.toString() + " (" + toString(semanticElement) + "). Found " + compositeNode); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        delegate = (CompositeNode) compositeNode;
      }
    }
    return delegate;
  }

  /**
   * A safe to string method that does not rely on possible custom implementations based on {@link org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()}.
   * <p>
   * Some custom implementations use the node model to give extra information,
   * and this does not work when the node model is being replaced.
   * </p>
   *
   * @param eObject
   *          the EObject
   * @return the string
   */
  private String toString(final EObject eObject) {
    StringBuilder result = new StringBuilder(eObject.getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    if (eObject.eIsProxy() && eObject instanceof InternalEObject internal) {
      result.append(" (eProxyURI: "); //$NON-NLS-1$
      result.append(internal.eProxyURI());
      result.append(')');
    }
    return result.toString();
  }

  @Override
  @SuppressFBWarnings("EQ_CHECK_FOR_OPERAND_NOT_COMPATIBLE_WITH_THIS")
  public boolean equals(final Object obj) {
    // this override is required for NodeTreeIterator (returned by iterator()) to work correctly in the context of NodeModelUtils
    return this == obj || obj instanceof CompositeNode && delegate() == obj;
  }

  @Override
  public int hashCode() {
    return delegate().hashCode();
  }

  @Override
  public ICompositeNode getParent() {
    return delegate().getParent();
  }

  @Override
  public boolean hasSiblings() {
    return delegate().hasSiblings();
  }

  @Override
  public boolean hasPreviousSibling() {
    return delegate().hasPreviousSibling();
  }

  @Override
  public boolean hasNextSibling() {
    return delegate().hasNextSibling();
  }

  @Override
  public INode getPreviousSibling() {
    return delegate().getPreviousSibling();
  }

  @Override
  public INode getNextSibling() {
    return delegate().getNextSibling();
  }

  @Override
  public ICompositeNode getRootNode() {
    return delegate().getRootNode();
  }

  @Override
  public Iterable<ILeafNode> getLeafNodes() {
    return delegate().getLeafNodes();
  }

  @Override
  public int getTotalOffset() {
    return delegate().getTotalOffset();
  }

  @Override
  public int getOffset() {
    return delegate().getOffset();
  }

  @Override
  public int getTotalLength() {
    return delegate().getTotalLength();
  }

  @Override
  public int getLength() {
    return delegate().getLength();
  }

  @Override
  public int getTotalEndOffset() {
    return delegate().getTotalEndOffset();
  }

  @Override
  public int getTotalStartLine() {
    return delegate().getTotalStartLine();
  }

  @Override
  public int getStartLine() {
    return delegate().getStartLine();
  }

  @Override
  public int getTotalEndLine() {
    return delegate().getTotalEndLine();
  }

  @Override
  public int getEndLine() {
    return delegate().getEndLine();
  }

  @Override
  public String getText() {
    return delegate().getText();
  }

  @Override
  public EObject getGrammarElement() {
    return delegate().getGrammarElement();
  }

  @Override
  public boolean hasDirectSemanticElement() {
    return delegate().hasDirectSemanticElement();
  }

  @Override
  public SyntaxErrorMessage getSyntaxErrorMessage() {
    return delegate().getSyntaxErrorMessage();
  }

  @Override
  public BidiIterable<INode> getChildren() {
    return delegate().getChildren();
  }

  @Override
  public boolean hasChildren() {
    return delegate().hasChildren();
  }

  @Override
  public INode getFirstChild() {
    return delegate().getFirstChild();
  }

  @Override
  public INode getLastChild() {
    return delegate().getLastChild();
  }

  @Override
  public int getLookAhead() {
    return delegate().getLookAhead();
  }

  @Override
  public int getEndOffset() {
    return delegate().getEndOffset();
  }

  @Override
  public EObject getSemanticElement() {
    return semanticElement;
  }

  @Override
  public ITextRegion getTextRegion() {
    return delegate().getTextRegion();
  }

  @Override
  public ITextRegion getTotalTextRegion() {
    return delegate().getTotalTextRegion();
  }

  @Override
  public ITextRegionWithLineInformation getTextRegionWithLineInformation() {
    return delegate().getTextRegionWithLineInformation();
  }

  @Override
  public ITextRegionWithLineInformation getTotalTextRegionWithLineInformation() {
    return delegate().getTotalTextRegionWithLineInformation();
  }

  @Override
  public BidiTreeIterable<INode> getAsTreeIterable() {
    // it is important to not delegate this method, because the iterator must return this instance for some clients such as NodeModelUtils to work correctly
    return this;
  }

  @Override
  public BidiTreeIterator<INode> iterator() {
    // it is important to not delegate this method, because the iterator must return this instance for some clients such as NodeModelUtils to work correctly
    return new NodeTreeIterator(this);
  }

  @Override
  public BidiTreeIterable<INode> reverse() {
    // it is important to not delegate this method, because the iterator must return this instance for some clients such as NodeModelUtils to work correctly
    return new ReversedBidiTreeIterable<INode>(this);
  }

  @Override
  public boolean isAdapterForType(final Object type) {
    return type == ProxyCompositeNode.class || type instanceof Class<?> && INode.class.isAssignableFrom((Class<?>) type);
  }

  @Override
  public void notifyChanged(final Notification notification) {
    // empty
  }

  @Override
  public Notifier getTarget() {
    return semanticElement;
  }

  @Override
  public void setTarget(final Notifier newTarget) {
    if (newTarget instanceof EObject) {
      semanticElement = (EObject) newTarget;
    }
  }

  @Override
  public NodeModelUtils.Implementation utils() {
    // return an implementation that adapts the proxy node to the delegate on each Node parameter
    return new NodeModelUtils.Implementation() {
      private final ICompositeNode loadedNode = delegate();

      @SuppressWarnings("unchecked")
      private <N extends INode> N adapt(final N node) {
        if (node == ProxyCompositeNode.this) {
          // adapt() is only ever called with INodes and ICompositeNodes, so it is safe to cast to N here.
          return (N) loadedNode;
        }
        return node;
      }

      @Override
      public ILeafNode findLeafNodeAtOffset(final INode node, final int leafNodeOffset) {
        return loadedNode.utils().findLeafNodeAtOffset(adapt(node), leafNodeOffset);
      }

      @Override
      public LineAndColumn getLineAndColumn(final INode anyNode, final int documentOffset) {
        return loadedNode.utils().getLineAndColumn(adapt(anyNode), documentOffset);
      }

      @Override
      public List<INode> findNodesForFeature(final EObject element, final INode node, final EStructuralFeature structuralFeature) {
        return loadedNode.utils().findNodesForFeature(element, adapt(node), structuralFeature);
      }

      @Override
      public ICompositeNode findActualNodeEnclosing(final ICompositeNode node) {
        return loadedNode.utils().findActualNodeEnclosing(adapt(node));
      }

      @Override
      public EObject findActualSemanticObjectFor(final INode node) {
        return loadedNode.utils().findActualSemanticObjectFor(adapt(node));
      }

      @Override
      public String compactDump(final INode node, final boolean showHidden) {
        return loadedNode.utils().compactDump(adapt(node), showHidden);
      }

      @Override
      public String getTokenText(final INode node) {
        return loadedNode.utils().getTokenText(adapt(node));
      }

      @Override
      public ParserRule getEntryParserRule(final INode node) {
        return loadedNode.utils().getEntryParserRule(adapt(node));
      }
    };
  }
}

/* Copyright (c) Avaloq Group AG */