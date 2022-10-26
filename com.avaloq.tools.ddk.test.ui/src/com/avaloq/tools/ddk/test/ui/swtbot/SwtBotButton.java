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
package com.avaloq.tools.ddk.test.ui.swtbot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.hamcrest.SelfDescribing;


/**
 * A customized {@link SWTBotButton}.
 */
public class SwtBotButton extends SWTBotButton {

  public SwtBotButton(final SWTBotButton button) {
    this(button.widget, null);
  }

  public SwtBotButton(final Button button) {
    this(button, null);
  }

  public SwtBotButton(final Button button, final SelfDescribing description) {
    super(button, description);
  }

  /**
   * {@inheritDoc} A customized version with less notifications, since the button could be disabled after the {@link SWT.Selection} event.
   */
  @Override
  public SwtBotButton click() {
    log.debug("Clicking on {}", SWTUtils.getText(widget)); //$NON-NLS-1$
    waitForEnabled();
    notify(SWT.MouseEnter);
    notify(SWT.MouseMove);
    notify(SWT.Activate);
    notify(SWT.FocusIn);
    notify(SWT.MouseDown);
    notify(SWT.MouseUp);
    notify(SWT.Selection);
    log.debug("Clicked on {}", SWTUtils.getText(widget)); //$NON-NLS-1$
    return this;
  }
}
