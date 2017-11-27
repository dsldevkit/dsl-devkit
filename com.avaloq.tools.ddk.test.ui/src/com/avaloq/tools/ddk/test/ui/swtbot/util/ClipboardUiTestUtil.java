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

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;


/**
 * Utility providing clipboard access.
 */
public final class ClipboardUiTestUtil {

  /**
   * Content from the clipboard.
   * 
   * @param bot
   *          to work with
   * @return clipboard content
   */
  public static String clipboardContent(final SWTWorkbenchBot bot) {
    return UIThreadRunnable.syncExec(new Result<String>() {
      public String run() {
        Clipboard clipboard = new Clipboard(bot.getDisplay());
        return (String) clipboard.getContents(TextTransfer.getInstance());
      }
    });
  }

  /**
   * Utility classes should not have a public or default constructor.
   */
  private ClipboardUiTestUtil() {}
}

