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

import java.util.Arrays;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.ui.IViewSite;


/**
 * Use this class to find a menu item which has been created dynamically.
 */
public class DynamicViewMenu {

  /** The view whose menu is searched. */
  private final SWTBotView view;

  /**
   * Creates an instance of {@link DynamicViewMenu}.
   *
   * @param bot
   *          the bot to work with
   * @return A new dynamic view menu
   */
  public static DynamicViewMenu create(final SWTWorkbenchBot bot) {
    return new DynamicViewMenu(bot);
  }

  /**
   * Instantiates a new dynamic view menu.
   *
   * @param bot
   *          the bot to work with
   */
  public DynamicViewMenu(final SWTWorkbenchBot bot) {
    this.view = bot.activeView();
  }

  /**
   * Convenience method which clicks on the menu item found and disposes the object. See {@link menu}
   *
   * @param menuPath
   *          the menu path
   * @throws IllegalStateException
   *           if the menu manager is not a MenuManager
   * @throws WidgetNotFoundException
   *           if the menu item cannot be found
   */
  public void click(final String... menuPath) {
    final IViewSite viewSite = (IViewSite) view.getViewReference().getPart(false).getSite();
    final IMenuManager m = viewSite.getActionBars().getMenuManager();

    if (!(m instanceof MenuManager)) {
      throw new IllegalStateException("cannot work with " + m); //$NON-NLS-1$
    }

    // Since Eclipse 4.41 the Markers views only get their menu contributions once the view menu is opened.
    view.viewMenu();

    MenuItem theItem = UIThreadRunnable.syncExec(() -> {
      MenuManager mgr = (MenuManager) m;
      mgr.createMenuBar((Decorations) viewSite.getShell());
      m.updateAll(true);
      MenuItem[] initialItems = ((MenuManager) m).getMenu().getItems();
      MenuItem item = findItem(initialItems, menuPath);
      return item != null && item.getText().equals(menuPath[menuPath.length - 1]) ? item : null;
    });

    // thrown outside syncExec, which would otherwise mask it as an IndexOutOfBoundsException
    if (theItem == null) {
      throw new WidgetNotFoundException("Could not find menu " + Arrays.toString(menuPath)); //$NON-NLS-1$
    }
    new SWTBotMenu(theItem).click();
  }

  private MenuItem findItem(final MenuItem[] startWith, final String... menuPath) {
    MenuItem theItem = null;
    MenuItem[] items = startWith;

    for (String element : menuPath) {
      for (MenuItem anItem : items) {
        if (anItem.getText().equals(element)) {
          theItem = anItem;
          Menu m = anItem.getMenu();
          if (m == null) {
            items = new MenuItem[] {};
          } else {
            items = m.getItems();
          }
          break;
        }
      }
    }
    return theItem;
  }

}
