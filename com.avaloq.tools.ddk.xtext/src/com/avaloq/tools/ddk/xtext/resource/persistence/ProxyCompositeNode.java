/*
 * Copyright (c) Avaloq Licence AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList.FeatureIterator;
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
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.persistence.IResourceStorageFacade;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;

import com.avaloq.tools.ddk.annotations.SuppressFBWarnings;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.collect.Lists;


/**
 * Proxying implementation which will load and replace itself with the real node model on demand.
 * <p>
 * To conserve memory this class implements the primary interfaces rather than extending an implementation like
 * {@link org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement}.
 */
class ProxyCompositeNode implements ICompositeNode, BidiTreeIterable<INode>, Adapter {

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

    ArrayList<EObject> idToEObjectMap = Lists.newArrayList();
    EObject root = resource.getContents().get(0);

    ProxyCompositeNode rootNode = installProxyNodeModel(root, idToEObjectMap);
    idToEObjectMap.trimToSize();
    rootNode.idToEObjectMap = idToEObjectMap;

    if (resource instanceof XtextResource) {
      ((XtextResource) resource).setParseResult(new ParseResult(root, rootNode, false));
    }
  }

  private static ProxyCompositeNode installProxyNodeModel(final EObject eObject, final List<EObject> map) {
    ProxyCompositeNode result = new ProxyCompositeNode();
    eObject.eAdapters().add(result);
    map.add(eObject);

    for (FeatureIterator<EObject> it = (FeatureIterator<EObject>) eObject.eContents().iterator(); it.hasNext();) {
      EObject child = it.next();
      if (!it.feature().isTransient()) {
        installProxyNodeModel(child, map);
      }
    }
    return result;
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
        for (TreeIterator<EObject> it = root.eAllContents(); it.hasNext();) {
          uninstallProxyNode(it.next());
        }
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
      StorageAwareResource storageAwareResource = (StorageAwareResource) resource;
      IResourceStorageFacade storageFacade = storageAwareResource.getResourceStorageFacade();
      DirectLinkingResourceStorageLoadable loadable = (DirectLinkingResourceStorageLoadable) storageFacade.getOrCreateResourceStorageLoadable(storageAwareResource);
      try {
        loadable.loadIntoResource(storageAwareResource, ResourceLoadMode.ONLY_NODE_MODEL);
      } catch (IOException e) {
        throw new WrappedException(e);
      }
      ICompositeNode compositeNode = NodeModelUtils.getNode(semanticElement);
      if (compositeNode == null) {
        throw new IllegalStateException("No node found for " + EObjectUtil.getLocationString(semanticElement)); //$NON-NLS-1$
      }
      delegate = (CompositeNode) compositeNode;
    }
    return delegate;
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

}

/* Copyright (c) Avaloq Licence AG */