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
package com.avaloq.tools.ddk.test.ui.swtbot.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;


/**
 * Extension utility for {@link SWTBotToolbarButton}.
 */
public final class SwtBotToolbarButtonUtil {

  /**
   * Returns whether the toolbar button is selected.
   *
   * @param toolbarButton
   *          the {@link SWTBotToolbarButton}, must not be {@code null}
   * @return {@code true} if the toolbar button is selected, {@code false} otherwise
   */
  public static boolean isSelected(final SWTBotToolbarButton toolbarButton) {
    Assert.isNotNull(toolbarButton, "toolbarButton");
    return UIThreadRunnable.syncExec(toolbarButton.display, new BoolResult() {
      @Override
      public Boolean run() {
        return toolbarButton.widget.getSelection();
      }
    });
  }

  /**
   * Creates a new instance of {@link SwtBotToolbarButtonUtil}.
   */
  private SwtBotToolbarButtonUtil() {}
}

