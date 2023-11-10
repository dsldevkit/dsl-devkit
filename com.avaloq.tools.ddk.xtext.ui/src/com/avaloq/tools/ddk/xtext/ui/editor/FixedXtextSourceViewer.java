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
package com.avaloq.tools.ddk.xtext.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.SlaveDocumentEvent;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;


/**
 * Fixes for {@link XtextSourceViewer}.
 */
public class FixedXtextSourceViewer extends XtextSourceViewer {
  /**
   * Factory for {@link FixedXtextSourceViewer}.
   */
  // CHECKSTYLE:OFF
  public static class Factory implements XtextSourceViewer.Factory {
    // CHECKSTYLE:ON

    @Override
    public XtextSourceViewer createSourceViewer(final Composite parent, final IVerticalRuler ruler, final IOverviewRuler overviewRuler, final boolean showsAnnotationOverview, final int styles) {
      return new FixedXtextSourceViewer(parent, ruler, overviewRuler, showsAnnotationOverview, styles);
    }
  }

  /**
   * Creates a new instance of {@link FixedXtextSourceViewer}.
   *
   * @param parent
   *          the {@link Composite} parent
   * @param ruler
   *          the {@link IVerticalRuler}
   * @param overviewRuler
   *          the {@link IOverviewRuler}
   * @param showsAnnotationOverview
   *          boolean flag whether to show annotation overview
   * @param styles
   *          styles flags
   */
  public FixedXtextSourceViewer(final Composite parent, final IVerticalRuler ruler, final IOverviewRuler overviewRuler, final boolean showsAnnotationOverview, final int styles) {
    super(parent, ruler, overviewRuler, showsAnnotationOverview, styles);
  }

  /**
   * Informs all registered text listeners about the change specified by the
   * widget command. This method does not use a robust iterator.
   *
   * @param cmd
   *          the widget command translated into a text event sent to all text listeners
   */
  @Override
  protected void updateTextListeners(final WidgetCommand cmd) {
    List<ITextListener> textListeners = fTextListeners;
    if (textListeners != null) {
      textListeners = new ArrayList<ITextListener>(textListeners);
      DocumentEvent event = cmd.event;
      if (event instanceof SlaveDocumentEvent) {
        event = ((SlaveDocumentEvent) event).getMasterEvent();
      }
      TextEvent e = new TextEvent(cmd.start, cmd.length, cmd.text, cmd.preservedText, event, redraws()) {
      };
      for (int i = 0; i < textListeners.size(); i++) {
        ITextListener l = textListeners.get(i);
        l.textChanged(e);
      }
    }
  }

}
