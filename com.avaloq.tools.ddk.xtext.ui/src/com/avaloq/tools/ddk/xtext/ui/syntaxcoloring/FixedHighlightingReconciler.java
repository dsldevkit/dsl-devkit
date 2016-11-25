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
package com.avaloq.tools.ddk.xtext.ui.syntaxcoloring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AttributedPosition;
import org.eclipse.xtext.ui.editor.syntaxcoloring.HighlightingPresenter;
import org.eclipse.xtext.ui.editor.syntaxcoloring.HighlightingReconciler;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ITextAttributeProvider;
import org.eclipse.xtext.ui.editor.syntaxcoloring.MergingHighlightedPositionAcceptor;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.collect.Ordering;
import com.google.inject.Inject;


/**
 * Copied from Xtext HighlightingReconciler. The {@link #addPosition(int, int, String...)} method implements a binary search method to improve performance.
 */
// CHECKSTYLE:OFF
@SuppressWarnings("PMD")
public class FixedHighlightingReconciler extends HighlightingReconciler {

  @Inject(optional = true)
  private ISemanticHighlightingCalculator calculator;

  @Inject
  private ITextAttributeProvider attributeProvider;

  /** The Xtext editor this highlighting reconciler is installed on. */
  private XtextEditor editor;
  /** The source viewer this highlighting reconciler is installed on. */
  private XtextSourceViewer sourceViewer;
  /** The highlighting presenter. */
  private HighlightingPresenter presenter;

  /** Background job's added highlighted positions. */
  private final List<AttributedPosition> addedPositions = new ArrayList<AttributedPosition>();
  /** Background job's removed highlighted positions. */
  private final List<AttributedPosition> removedPositions = new ArrayList<AttributedPosition>();

  private final Ordering<AttributedPosition> positionOrdering = Ordering.from(new Comparator<AttributedPosition>() {
    @Override
    public int compare(final AttributedPosition o1, final AttributedPosition o2) {
      if (o1 == null) {
        return o2 == null ? 0 : -1;
      } else if (o2 == null) {
        return 1;
      }
      int res = o1.offset - o2.offset;
      if (res != 0) {
        return res;
      }
      res = o1.length - o2.length;
      if (res != 0) {
        return res;
      }
      if (o1.isDeleted != o2.isDeleted) {
        return o1.isDeleted ? -1 : 1;
      }
      return o1.getHighlighting().hashCode() - o2.getHighlighting().hashCode();
    }
  });

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
  }

  /**
   * Reconcile positions based on the AST subtrees
   *
   * @param subtrees
   *          the AST subtrees
   */
  private void reconcilePositions(final XtextResource resource) {
    // for (int i= 0, n= subtrees.length; i < n; i++)
    // subtrees[i].accept(fCollector);
    MergingHighlightedPositionAcceptor acceptor = new MergingHighlightedPositionAcceptor(calculator);
    acceptor.provideHighlightingFor(resource, this);
    // calculator.provideHighlightingFor(resource, this);
    Collections.sort(removedPositions, positionOrdering);
  }

  /**
   * Add a position with the given range and highlighting if it does not exist already.
   *
   * @param offset
   *          The range offset
   * @param length
   *          The range length
   * @param highlighting
   *          The highlighting
   */
  @Override
  public void addPosition(final int offset, final int length, final String... ids) {
    TextAttribute highlighting = ids.length == 1 ? attributeProvider.getAttribute(ids[0]) : attributeProvider.getMergedAttributes(ids);
    AttributedPosition position = presenter.createHighlightedPosition(offset, length, highlighting);
    int idx = positionOrdering.binarySearch(removedPositions, position);
    boolean isExisting = idx >= 0 && removedPositions.get(idx).isEqual(offset, length, highlighting);

    if (isExisting) {
      removedPositions.remove(idx);
    } else {
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
   */
  private void updatePresentation(final TextPresentation textPresentation, final List<AttributedPosition> addedPositions, final List<AttributedPosition> removedPositions) {
    Runnable runnable = presenter.createUpdateRunnable(textPresentation, addedPositions, removedPositions);
    if (runnable == null) {
      return;
    }

    Display display = getDisplay();
    display.asyncExec(runnable);
  }

  private Display getDisplay() {
    XtextEditor editor = this.editor;
    if (editor == null) {
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
    if (calculator != null) {
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
   * Uninstall this reconciler from the editor.
   */
  @Override
  public void uninstall() {
    if (presenter != null) {
      presenter.setCanceled(true);
    }

    if (editor != null) {
      if (calculator != null) {
        if (editor.getDocument() != null) {
          editor.getDocument().removeModelListener(this);
        }
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

  /** {@inheritDoc} */
  @Override
  public void inputDocumentAboutToBeChanged(final IDocument oldInput, final IDocument newInput) {
    if (oldInput != null) {
      ((IXtextDocument) oldInput).removeModelListener(this);
    }
  }

  /** {@inheritDoc} */
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
    if (calculator != null) {
      IDocument document;
      if (editor != null) {
        document = editor.getDocument();
      } else {
        document = sourceViewer.getDocument();
      }
      if (document instanceof IXtextDocument) {
        ((IXtextDocument) document).readOnly(new IUnitOfWork.Void<XtextResource>() {
          @Override
          public void process(final XtextResource state) throws Exception {
            modelChanged(state);
          }
        });
      }
    } else {
      Display display = getDisplay();
      display.asyncExec(presenter.createSimpleUpdateRunnable());
    }
  }

  /** {@inheritDoc} */
  @Override
  public void modelChanged(final XtextResource resource, final CancelIndicator cancelIndicator) {
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

      highlightingPresenter.setCanceled(false);

      if (highlightingPresenter.isCanceled()) {
        return;
      }

      startReconcilingPositions();

      if (!highlightingPresenter.isCanceled()) {
        reconcilePositions(resource);
      }

      final TextPresentation[] textPresentation = new TextPresentation[1];
      if (!highlightingPresenter.isCanceled()) {
        textPresentation[0] = highlightingPresenter.createPresentation(addedPositions, removedPositions);
      }

      if (!highlightingPresenter.isCanceled()) {
        updatePresentation(textPresentation[0], addedPositions, removedPositions);
      }

      stopReconcilingPositions();
    } finally {
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

  @Override
  public void setCalculator(final ISemanticHighlightingCalculator calculator) {
    this.calculator = calculator;
  }

  @Override
  public ISemanticHighlightingCalculator getCalculator() {
    return calculator;
  }
}
