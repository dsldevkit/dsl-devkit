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

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;


/**
 * A condition checking if a {@link TableItem} is visible.
 */
public class WaitForTable extends WaitForObjectCondition<TableItem> {
  private final Table parent;

  /**
   * Creates a new instance of {@link WaitForTable}.
   *
   * @param parent
   *          the parent {@link Table}, must not be {@code null}
   */
  public WaitForTable(final Table parent) {
    super(WidgetMatcherFactory.widgetOfType(TableItem.class));
    Assert.isNotNull(parent, "parent");
    this.parent = parent;
  }

  
  @Override
  public String getFailureMessage() {
    return "Could not find table item matching: " + this.matcher;
  }

  
  @Override
  protected java.util.List<TableItem> findMatches() {
    return UIThreadRunnable.syncExec(new ListResult<TableItem>() {
      @Override
      public java.util.List<TableItem> run() {
        return new ArrayList<TableItem>(Arrays.asList(parent.getItems()));
      }
    });
  }
}
