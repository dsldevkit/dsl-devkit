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
package com.avaloq.tools.ddk.test.ui.swtbot.condition;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;


/**
 * A condition checking if a {@link TreeItem} is visible.
 */
public class WaitForTree extends WaitForObjectCondition<TreeItem> {
  private final Tree parent;

  /**
   * Creates a new instance of {@link WaitForTree}.
   *
   * @param parent
   *          the parent {@link Tree}, must not be {@code null}
   */
  public WaitForTree(final Tree parent) {
    super(WidgetMatcherFactory.widgetOfType(TreeItem.class));
    Assert.isNotNull(parent, "parent");
    this.parent = parent;
  }

  /** {@inheritDoc} */
  @Override
  public String getFailureMessage() {
    return "Could not find tree item matching: " + this.matcher;
  }

  /** {@inheritDoc} */
  @Override
  protected java.util.List<TreeItem> findMatches() {
    return UIThreadRunnable.syncExec(new ListResult<TreeItem>() {
      @Override
      public java.util.List<TreeItem> run() {
        return new ArrayList<TreeItem>(Arrays.asList(parent.getItems()));
      }
    });
  }
}

