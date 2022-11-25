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
package com.avaloq.tools.ddk.xtext.ui.editor.findrefs;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.ui.SearchResultEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescription.Event;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.ui.editor.StatefulResourceDescription;
import org.eclipse.xtext.ui.editor.findrefs.Messages;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchResult;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchResultContentProvider;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchResultEvents.Added;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchResultEvents.Reset;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchViewTreeNode;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;


/**
 * Specialized content provider implementation which is more efficient due to a map used to map from resources to search result nodes.
 */
public class FastReferenceSearchResultContentProvider extends ReferenceSearchResultContentProvider {

  private final IResourceDescriptions resourceDescriptions;

  private TreeViewer viewer;

  private final Map<URI, ReferenceSearchViewTreeNode> rootNodes = Maps.newConcurrentMap();

  private final List<ReferenceSearchViewTreeNode> batchAddNodes = Lists.newArrayList();
  private volatile boolean isUIUpdateScheduled; // NOPMD

  @Inject
  public FastReferenceSearchResultContentProvider(final IResourceDescriptions resourceDescriptions) {
    super(resourceDescriptions);
    this.resourceDescriptions = resourceDescriptions;
    if (resourceDescriptions instanceof IResourceDescription.Event.Source) {
      ((IResourceDescription.Event.Source) resourceDescriptions).addListener(this);
    }
  }

  @Override
  public Object[] getChildren(final Object parentElement) {
    if (parentElement instanceof ReferenceSearchViewTreeNode) {
      return Iterables.toArray(((ReferenceSearchViewTreeNode) parentElement).getChildren(), ReferenceSearchViewTreeNode.class);
    }
    return new Object[0];
  }

  @Override
  public Object getParent(final Object element) {
    if (element instanceof ReferenceSearchViewTreeNode) {
      return ((ReferenceSearchViewTreeNode) element).getParent();
    }
    return null;
  }

  @Override
  public boolean hasChildren(final Object element) {
    if (element instanceof ReferenceSearchViewTreeNode) {
      return !((ReferenceSearchViewTreeNode) element).getChildren().isEmpty();
    }
    return false;
  }

  @Override
  public Object[] getElements(final Object inputElement) {
    if (rootNodes.isEmpty()) {
      return new Object[0];
    }
    return Iterables.toArray(rootNodes.values(), ReferenceSearchViewTreeNode.class);
  }

  @Override
  public void dispose() {
    rootNodes.clear();
  }

  @Override
  public void inputChanged(final Viewer v, final Object oldInput, final Object newInput) {
    synchronized (v) {
      rootNodes.clear();
      if (oldInput instanceof ReferenceSearchResult) {
        ((ReferenceSearchResult) oldInput).removeListener(this);
      }
      if (newInput instanceof ReferenceSearchResult && v instanceof TreeViewer) {
        ((ReferenceSearchResult) newInput).addListener(this);
        this.viewer = (TreeViewer) v;
        for (IReferenceDescription referenceDescription : ((ReferenceSearchResult) newInput).getMatchingReferences()) {
          addReference(referenceDescription);
        }
      }
    }
  }

  /**
   * Adds the given reference to the contents.
   *
   * @param referenceDescription
   *          reference to add
   */
  private void addReference(final IReferenceDescription referenceDescription) {
    URI containerEObjectURI = referenceDescription.getContainerEObjectURI();
    final URI eObjectURI = (containerEObjectURI == null) ? referenceDescription.getSourceEObjectUri() : containerEObjectURI;
    IResourceDescription resourceDescription = resourceDescriptions.getResourceDescription(eObjectURI.trimFragment());
    if (resourceDescription != null) {
      ReferenceSearchViewTreeNode resourceNode = resourceNode(resourceDescription);
      new DynamicReferenceSearchViewTreeNode(resourceNode, referenceDescription, new Supplier<Object>() {
        @Override
        public Object get() {
          InternalEObject dummyProxy = (InternalEObject) EcoreFactory.eINSTANCE.createEObject();
          dummyProxy.eSetProxyURI(eObjectURI);
          Iterator<IEObjectDescription> sourceObjects = resourceDescriptions.getExportedObjectsByObject(dummyProxy).iterator();
          return sourceObjects.hasNext() ? sourceObjects.next() : referenceDescription;
        }
      });
    }
  }

  /**
   * Adds the given resource to the contents or returns an existing one.
   *
   * @param resourceDescription
   *          resource to add
   * @return added resource or already existing resource, neved {@code null}
   */
  private ReferenceSearchViewTreeNode resourceNode(final IResourceDescription resourceDescription) {
    URI uri = resourceDescription.getURI();
    ReferenceSearchViewTreeNode node = rootNodes.get(uri);
    if (node == null) {
      node = new ReferenceSearchViewTreeNode(null, resourceDescription, resourceDescription);
      rootNodes.put(uri, node);
      synchronized (batchAddNodes) {
        batchAddNodes.add(node);
      }
    }
    return node;
  }

  @Override
  public void searchResultChanged(final SearchResultEvent e) {
    if (e instanceof Added) {
      addReference(((Added) e).getReferenceDescription());
      if (!isUIUpdateScheduled) {
        isUIUpdateScheduled = true;
        new UIUpdater().schedule();
      }
    } else if (e instanceof Reset) {
      PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
        @Override
        public void run() {
          synchronized (viewer) {
            viewer.remove(viewer.getInput(), (Object[]) Iterables.toArray(rootNodes.values(), ReferenceSearchViewTreeNode.class));
            rootNodes.clear();
          }
          viewer.refresh();
          viewer.expandToLevel(1);
        }
      });
    }
  }

  /**
   * UI updater job.
   */
  private class UIUpdater extends UIJob {

    private static final int JOB_RESCHEDULE_DELAY = 250;

    UIUpdater() {
      super(Messages.ReferenceSearchResultContentProvider_label);
      setSystem(true);
    }

    @Override
    public IStatus runInUIThread(final IProgressMonitor monitor) {
      List<ReferenceSearchViewTreeNode> nodes;
      synchronized (batchAddNodes) {
        nodes = Lists.newArrayList(batchAddNodes);
        batchAddNodes.clear();
      }
      SubMonitor progress = SubMonitor.convert(monitor, nodes.size());
      for (ReferenceSearchViewTreeNode node : nodes) {
        viewer.add(viewer.getInput(), node);
        progress.worked(1);
      }
      viewer.refresh();
      if (!batchAddNodes.isEmpty()) {
        schedule(JOB_RESCHEDULE_DELAY);
      } else {
        isUIUpdateScheduled = false;
      }
      return Status.OK_STATUS;
    }
  }

  @Override
  public void descriptionsChanged(final Event event) {
    PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
      @Override
      public void run() {
        for (Delta delta : event.getDeltas()) {
          if (!(delta.getNew() instanceof StatefulResourceDescription)) {
            for (Iterator<ReferenceSearchViewTreeNode> i = rootNodes.values().iterator(); i.hasNext();) {
              ReferenceSearchViewTreeNode rootNode = i.next();
              if (((IResourceDescription) rootNode.getDescription()).getURI().equals(delta.getUri())) {
                if (delta.getNew() == null) {
                  i.remove();
                  viewer.remove(rootNode);
                  break;
                } else {
                  Iterable<IReferenceDescription> newReferenceDescriptions = delta.getNew().getReferenceDescriptions();
                  List<ReferenceSearchViewTreeNode> removedReferenceNodes = Lists.newArrayList();
                  for (ReferenceSearchViewTreeNode referenceNode : rootNode.getChildren()) {
                    final URI referenceSourceURI = ((IReferenceDescription) referenceNode.getDescription()).getSourceEObjectUri();
                    if (!Iterables.any(newReferenceDescriptions, new Predicate<IReferenceDescription>() {
                      @Override
                      public boolean apply(final IReferenceDescription input) {
                        return input.getSourceEObjectUri().equals(referenceSourceURI);
                      }
                    })) {
                      removedReferenceNodes.add(referenceNode);
                    }
                  }
                  for (ReferenceSearchViewTreeNode removedReferenceNode : removedReferenceNodes) {
                    rootNode.removeChild(removedReferenceNode);
                  }
                  if (rootNode.getChildren().isEmpty()) {
                    i.remove();
                    viewer.remove(rootNode);
                    break;
                  } else {
                    viewer.remove(rootNode, (Object[]) Iterables.toArray(removedReferenceNodes, ReferenceSearchViewTreeNode.class));
                  }
                }
              }
            }
          }
        }
      }
    });
  }

}
