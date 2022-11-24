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
package com.avaloq.tools.ddk.xtext.ui.syntaxcoloring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ide.editor.syntaxcoloring.MergingHighlightedPositionAcceptor;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IBatchLinkableResource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AttributedPosition;
import org.eclipse.xtext.ui.editor.syntaxcoloring.HighlightingPresenter;
import org.eclipse.xtext.ui.editor.syntaxcoloring.HighlightingReconciler;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ITextAttributeProvider;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.concurrent.CancelableUnitOfWork;

import com.google.common.collect.Maps;
import com.google.inject.Inject;


/**
 * Copied from Xtext's {@link HighlightingReconciler} with the following customizations:
 * <ul>
 * <li>https://review.sits.avaloq.net/#/c/8982/</li>
 * <li>https://github.com/dsldevkit/dsl-devkit/commit/14b48aa8a35e560ddc229799f1c69cc3bddc9013#diff-cfa393424650d1e425e8727d54080250</li>
 * </ul>
 * .
 */
// CHECKSTYLE:OFF
@SuppressWarnings("PMD")
public class FixedHighlightingReconciler extends HighlightingReconciler {

  @Inject(optional = true)
  @SuppressWarnings("deprecation")
  private org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator oldCalculator;

  @Inject(optional = true)
  private ISemanticHighlightingCalculator newCalculator;

  @Inject
  private ITextAttributeProvider attributeProvider;

  /** The Xtext editor this highlighting reconciler is installed on */
  private XtextEditor editor;
  /** The source viewer this highlighting reconciler is installed on */
  private XtextSourceViewer sourceViewer;
  /** The highlighting presenter */
  private HighlightingPresenter presenter;

  /** Background job's added highlighted positions */
  private final List<AttributedPosition> addedPositions = new ArrayList<AttributedPosition>();
  /** Background job's removed highlighted positions */
  private List<AttributedPosition> removedPositions = new ArrayList<AttributedPosition>();

  private static class PositionHandle {
    private final int offset;
    private final int length;
    private final TextAttribute textAttribute;

    private PositionHandle(final int offset, final int length, final TextAttribute textAttribute) {
      this.offset = offset;
      this.length = length;
      this.textAttribute = textAttribute;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + length;
      result = prime * result + offset;
      result = prime * result + ((textAttribute == null) ? 0 : textAttribute.hashCode());
      return result;
    }

    /**
     * @see AttributedPosition#isEqual(int, int, TextAttribute)
     */
    @Override
    public boolean equals(final Object obj) {
      if (obj == null) {
        return false;
      }
      if (obj == this) {
        return true;
      }
      PositionHandle other = (PositionHandle) obj;
      return offset == other.offset && length == other.length && textAttribute == other.textAttribute;
    }

  }

  private final Map<PositionHandle, Integer> handleToListIndex = Maps.newHashMap();

  /** Number of removed positions */
  private int removedPositionCount;

  /**
   * Reconcile operation lock.
   *
   * @since 3.2
   */
  private final Object fReconcileLock = new Object();
  /**
   * <code>true</code> if any thread is executing <code>reconcile</code>, <code>false</code> otherwise.
   *
   * @since 3.2
   */
  private boolean reconciling = false;

  /** If the reconciler was uninstalled during a reconciliation process and therefore a cleanup is required after the process ends. */
  private boolean cleanUpAfterReconciliation = false;

  /**
   * Start reconciling positions.
   */
  private void startReconcilingPositions() {
    presenter.addAllPositions(removedPositions);
    removedPositionCount = removedPositions.size();
    for (int i = 0; i < removedPositionCount; i++) {
      AttributedPosition position = removedPositions.get(i);
      handleToListIndex.put(new PositionHandle(position.getOffset(), position.getLength(), position.getHighlighting()), i);
    }
  }

  /**
   * Reconcile positions using {@link MergingHighlightedPositionAcceptor}
   *
   * @param resource
   *          XtextResource
   * @param cancelIndicator
   *          an indicator that should be asked in order to cancel an operation early in case there are new pending changes.
   */
  @SuppressWarnings("deprecation")
  private void reconcilePositions(final XtextResource resource, final CancelIndicator cancelIndicator) {
    // for (int i= 0, n= subtrees.length; i < n; i++)
    // subtrees[i].accept(fCollector);

    // A default binding was registered from ISemanticHighlightingCalculator to DefaultHighlightingCalculator
    // thus clienst may have bound DefaultHighlightingCalculator to their custom impl
    // use the custom impl if available, otherwise go for the new impl
    if (oldCalculator != null && !org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator.class.equals(oldCalculator.getClass())) {
      org.eclipse.xtext.ui.editor.syntaxcoloring.MergingHighlightedPositionAcceptor acceptor = new org.eclipse.xtext.ui.editor.syntaxcoloring.MergingHighlightedPositionAcceptor(oldCalculator);
      acceptor.provideHighlightingFor(resource, this);
    } else if (newCalculator != null) {
      MergingHighlightedPositionAcceptor acceptor = new MergingHighlightedPositionAcceptor(newCalculator);
      acceptor.provideHighlightingFor(resource, this, cancelIndicator);
    } else {
      throw new IllegalStateException("No semantic highlighting calculator bound."); //$NON-NLS-1$
    }

    // calculator.provideHighlightingFor(resource, this);
    List<AttributedPosition> oldPositions = removedPositions;
    List<AttributedPosition> newPositions = new ArrayList<AttributedPosition>(removedPositionCount);
    for (int i = 0, n = oldPositions.size(); i < n; i++) {
      AttributedPosition current = oldPositions.get(i);
      if (current != null) {
        newPositions.add(current);
      }
    }
    removedPositions = newPositions;
  }

  /**
   * Add a position with the given range and highlighting if it does not exist already.
   *
   * @param offset
   *          The range offset
   * @param length
   *          The range length
   * @param ids
   *          The highlighting attribute ids
   */
  @Override
  public void addPosition(final int offset, final int length, final String... ids) {
    TextAttribute highlighting = ids.length == 1 ? attributeProvider.getAttribute(ids[0]) : attributeProvider.getMergedAttributes(ids);
    if (highlighting == null) {
      return;
    }
    boolean isExisting = false;

    PositionHandle handle = new PositionHandle(offset, length, highlighting);
    Integer index = handleToListIndex.remove(handle);
    if (index != null) {
      AttributedPosition position = removedPositions.get(index);
      if (position == null) {
        throw new IllegalStateException("Position may not be null if the handle is still present."); //$NON-NLS-1$
      }
      isExisting = true;
      removedPositions.set(index, null);
      removedPositionCount--;
    }

    if (!isExisting && presenter != null) { // in case we have been uninstalled due to exceptions
      AttributedPosition position = presenter.createHighlightedPosition(offset, length, highlighting);
      addedPositions.add(position);
    }
  }

  /**
   * Update the presentation.
   *
   * @param textPresentation
   *          the text presentation
   * @param addedPositions
   *          the added positions
   * @param removedPositions
   *          the removed positions
   * @param resource
   *          the resource for which the positions have been computed
   */
  private void updatePresentation(final TextPresentation textPresentation, final List<AttributedPosition> addedPositions, final List<AttributedPosition> removedPositions, final XtextResource resource) {
    final Runnable runnable = presenter.createUpdateRunnable(textPresentation, addedPositions, removedPositions);
    if (runnable == null) {
      return;
    }
    final XtextResourceSet resourceSet = (XtextResourceSet) resource.getResourceSet();
    final int modificationStamp = resourceSet.getModificationStamp();
    Display display = getDisplay();
    display.asyncExec(new Runnable() {
      @Override
      public void run() {
        // never apply outdated highlighting
        if (sourceViewer != null && modificationStamp == resourceSet.getModificationStamp()) {
          runnable.run();
        }
      }
    });
  }

  private Display getDisplay() {
    XtextEditor editor = this.editor;
    if (editor == null) {
      if (sourceViewer != null) {
        return sourceViewer.getControl().getDisplay();
      }
      return null;
    }
    IWorkbenchPartSite site = editor.getSite();
    if (site == null) {
      return null;
    }

    Shell shell = site.getShell();
    if (shell == null || shell.isDisposed()) {
      return null;
    }

    Display display = shell.getDisplay();
    if (display == null || display.isDisposed()) {
      return null;
    }
    return display;
  }

  /**
   * Stop reconciling positions.
   */
  private void stopReconcilingPositions() {
    removedPositions.clear();
    removedPositionCount = 0;
    handleToListIndex.clear();
    addedPositions.clear();
  }

  /**
   * Install this reconciler on the given editor and presenter.
   *
   * @param editor
   *          the editor
   * @param sourceViewer
   *          the source viewer
   * @param presenter
   *          the highlighting presenter
   */
  @Override
  public void install(final XtextEditor editor, final XtextSourceViewer sourceViewer, final HighlightingPresenter presenter) {
    synchronized (fReconcileLock) {
      cleanUpAfterReconciliation = false; // prevents a potentially already running reconciliation process to clean up after itself
    }
    this.presenter = presenter;
    this.editor = editor;
    this.sourceViewer = sourceViewer;
    if (oldCalculator != null || newCalculator != null) {
      if (editor == null) {
        ((IXtextDocument) sourceViewer.getDocument()).addModelListener(this);
      } else if (editor.getDocument() != null) {
        editor.getDocument().addModelListener(this);
      }

      sourceViewer.addTextInputListener(this);
    }
    refresh();
  }

  /**
   * Uninstall this reconciler from the editor
   */
  @Override
  public void uninstall() {
    if (presenter != null) {
      presenter.setCanceled(true);
    }

    if (sourceViewer.getDocument() != null) {
      if (oldCalculator != null || newCalculator != null) {
        XtextDocument document = (XtextDocument) sourceViewer.getDocument();
        document.removeModelListener(this);
        sourceViewer.removeTextInputListener(this);
      }
    }
    synchronized (fReconcileLock) {
      if (reconciling) {
        cleanUpAfterReconciliation = true;
      } else {
        editor = null;
        sourceViewer = null;
        presenter = null;
      }
    }
  }

  @Override
  public void inputDocumentAboutToBeChanged(final IDocument oldInput, final IDocument newInput) {
    if (oldInput != null) {
      ((IXtextDocument) oldInput).removeModelListener(this);
    }
  }

  @Override
  public void inputDocumentChanged(final IDocument oldInput, final IDocument newInput) {
    if (newInput != null) {
      refresh();
      ((IXtextDocument) newInput).addModelListener(this);
    }
  }

  /**
   * Refreshes the highlighting.
   */
  @Override
  public void refresh() {
    if (oldCalculator != null || newCalculator != null) {
      IDocument document = editor != null ? editor.getDocument() : sourceViewer.getDocument();
      if (document instanceof IXtextDocument) {
        Job job = new Job("Calculating highlighting") { //$NON-NLS-1$
          @Override
          protected IStatus run(final IProgressMonitor monitor) {
            ((XtextDocument) document).readOnly(new CancelableUnitOfWork<Void, XtextResource>() {
              @Override
              public java.lang.Void exec(final XtextResource state, final CancelIndicator cancelIndicator) throws Exception {
                beforeRefresh(state, cancelIndicator);
                modelChanged(state, cancelIndicator);
                return null;
              }
            });
            return Status.OK_STATUS;
          }
        };
        job.setSystem(true);
        job.schedule();
      }
    } else {
      Display display = getDisplay();
      display.asyncExec(presenter.createSimpleUpdateRunnable());
    }
  }

  /**
   * @since 2.8
   */
  @Override
  protected void beforeRefresh(final XtextResource resource, final CancelIndicator cancelIndicator) {
    if (resource instanceof DerivedStateAwareResource) {
      resource.getContents(); // trigger derived state computation
    }
    if (resource instanceof IBatchLinkableResource) {
      ((IBatchLinkableResource) resource).linkBatched(cancelIndicator);
    }
  }

  @Override
  public void modelChanged(final XtextResource resource) {
    modelChanged(resource, CancelIndicator.NullImpl);
  }

  /**
   * @since 2.7
   */
  @Override
  public void modelChanged(final XtextResource resource, final CancelIndicator cancelIndicator) {
    if (resource == null) {
      return;
    }

    // ensure at most one thread can be reconciling at any time
    synchronized (fReconcileLock) {
      if (reconciling) {
        return;
      }
      reconciling = true;
    }
    final HighlightingPresenter highlightingPresenter = presenter;
    try {
      if (highlightingPresenter == null) {
        return;
      }

      highlightingPresenter.setCanceled(cancelIndicator.isCanceled());

      if (highlightingPresenter.isCanceled()) {
        return;
      }
      checkCanceled(cancelIndicator);

      startReconcilingPositions();

      if (highlightingPresenter.isCanceled()) {
        return;
      }
      checkCanceled(cancelIndicator);
      reconcilePositions(resource, cancelIndicator);

      if (highlightingPresenter.isCanceled()) {
        return;
      }
      checkCanceled(cancelIndicator);
      final TextPresentation textPresentation = highlightingPresenter.createPresentation(addedPositions, removedPositions);

      if (highlightingPresenter.isCanceled()) {
        return;
      }
      checkCanceled(cancelIndicator);
      updatePresentation(textPresentation, addedPositions, removedPositions, resource);

    } finally {
      if (highlightingPresenter != null) {
        highlightingPresenter.setCanceled(false);
      }
      stopReconcilingPositions();
      synchronized (fReconcileLock) {
        reconciling = false;
        if (cleanUpAfterReconciliation) {
          editor = null;
          sourceViewer = null;
          presenter = null;
          cleanUpAfterReconciliation = false;
        }
      }
    }
  }

  /**
   * @since 2.8
   */
  @Override
  protected void checkCanceled(final CancelIndicator cancelIndicator) {
    if (cancelIndicator.isCanceled()) {
      throw new OperationCanceledException();
    }
  }

  @Override
  @Deprecated
  public void setCalculator(final org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator calculator) {
    this.oldCalculator = calculator;
  }

  @Override
  @Deprecated
  public org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator getCalculator() {
    return oldCalculator;
  }
}
