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
package com.avaloq.tools.ddk.test.ui.swtbot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.hamcrest.SelfDescribing;


/**
 * Customized {@link SWTBotRadio} to fix click method.
 */
public class SwtBotRadio extends SWTBotRadio {

  /**
   * Instantiates a new {@link SwtBotRadio} with the given widget.
   *
   * @param w
   *          the widget, must not be {@code null}
   */
  public SwtBotRadio(final Button w) {
    super(w);
  }

  /**
   * Instantiates a new {@link SwtBotRadio} with the given widget.
   *
   * @param w
   *          the widget, must not be {@code null}
   * @param description
   *          the description of the widget, this will be reported by {@link SelfDescribing#toString()}, may be {@code null}
   */
  public SwtBotRadio(final Button w, final SelfDescribing description) {
    super(w, description);
  }

  /**
   * {@inheritDoc} A customized version to fix bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=344484.
   */
  @Override
  public SwtBotRadio click() {
    if (isSelected()) {
      log.debug("Widget {} is already selected, not clicking again.", this); //$NON-NLS-1$
      return this;
    }
    waitForEnabled();

    log.debug("Clicking on {}", this); //$NON-NLS-1$

    final SwtBotRadio otherSelectedButton = otherSelectedButton();
    if (otherSelectedButton != null) {
      otherSelectedButton.notify(SWT.Deactivate);
      asyncExec(new VoidResult() {
        @Override
        public void run() {
          otherSelectedButton.widget.setSelection(false);
        }
      });
    }

    notify(SWT.Activate);
    notify(SWT.MouseDown, createMouseEvent(0, 0, 1, 0, 1));
    notify(SWT.MouseUp, createMouseEvent(0, 0, 1, SWT.BUTTON1, 1));
    asyncExec(new VoidResult() {
      @Override
      public void run() {
        widget.setSelection(true);
      }
    });
    notify(SWT.Selection);

    log.debug("Clicked on {}", this); //$NON-NLS-1$
    return this;
  }

  /**
   * Returns button that is selected in widget.
   *
   * @return the other selected button or {@code null} if none
   */
  private SwtBotRadio otherSelectedButton() {
    Button button = syncExec(new WidgetResult<Button>() {
      @Override
      public Button run() {
        if (hasStyle(widget.getParent(), SWT.NO_RADIO_GROUP)) {
          return null;
        }
        Widget[] siblings = SWTUtils.siblings(widget);
        for (Widget widget : siblings) {
          if ((widget instanceof Button) && hasStyle(widget, SWT.RADIO) && ((Button) widget).getSelection()) {
            return (Button) widget;
          }
        }
        return null;
      }
    });

    if (button != null) {
      return new SwtBotRadio(button);
    }
    return null;
  }
}
