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
package com.avaloq.tools.ddk.test.ui.swtbot.condition;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;


/**
 * Custom condition to wait for loading context menu.
 */
public class IsContextMenuVisibleCondition implements ICondition {
  // The SWTBot instance
  // private SWTBot bot;

  // The context menu to wait for
  private final SWTBotMenu ctx;

  public IsContextMenuVisibleCondition(final SWTBotMenu ctx) {
    this.ctx = ctx;
  }

  @Override
  public void init(final SWTBot bot) {
    // this.bot = bot;
  }

  /**
   * {@inheritDoc} Checks whether this context menu is visible, e.g. has been loaded.
   */
  @Override
  @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
  public boolean test() {
    return ctx.isVisible();
  }

  @Override
  public String getFailureMessage() {
    StringBuilder msg = new StringBuilder("Error in "); //$NON-NLS-1$
    msg.append(getClass().getName());
    return msg.toString();
  }

  /**
   * Convenience method to obtain a new condition of this type.
   *
   * @param ctx
   *          the ctx
   * @return the i condition
   */
  public static ICondition contextMenuIsVisible(final SWTBotMenu ctx) {
    return new IsContextMenuVisibleCondition(ctx);
  }
}
