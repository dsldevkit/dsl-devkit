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
package com.avaloq.tools.ddk.test.ui.swtbot.util;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


/**
 * Utility providing access to shells.
 */
public final class ShellUiTestUtil {

  /**
   * Tests if a shell with a specific type of data contained (e.g. Window).
   * 
   * @param bot
   *          to use
   * @param clazz
   *          class of data contained to check for
   */
  @SuppressWarnings("rawtypes")
  public static void assertShellWithDataTypeVisible(final SWTWorkbenchBot bot, final Class clazz) {
    bot.waitUntil(Conditions.waitForShell(new BaseMatcher<Shell>() {
      @SuppressWarnings("unchecked")
      public boolean matches(final Object item) {
        return UIThreadRunnable.syncExec(new Result<Boolean>() {
          public Boolean run() {
            if (item instanceof Shell) {
              Object shellData = ((Shell) item).getData();
              if (shellData != null) {
                return clazz.isAssignableFrom(shellData.getClass());
              }
            }
            return false;
          }
        });
      }

      public void describeTo(final Description description) {
        description.appendText("Shell for " + clazz.getName());
      }
    }));
  }

  private ShellUiTestUtil() {}
}

