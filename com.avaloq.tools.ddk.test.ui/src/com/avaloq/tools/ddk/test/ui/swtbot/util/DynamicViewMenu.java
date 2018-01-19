/*******************************************************************************
 * Copyright (c) 2016-2018 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.test.ui.swtbot.util;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PlatformUI;


/**
 * Use this class to find a menu item which has been created dynamically.
 */
public class DynamicViewMenu {

  /** The original menu which is used to find the menu item. */
  private static Menu originalMenu;

  /** The reference to the view. */
  private final IViewReference reference;

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
    this.reference = bot.activeView().getViewReference();
    getMenu();
  }

  /**
   * Get the original menu which is used to find the menu item.
   */
  private void getMenu() {
    final ToolBarManager toolBarManager = (ToolBarManager) ((IViewSite) reference.getPart(true).getSite()).getActionBars().getToolBarManager();
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        originalMenu = new Menu(toolBarManager.getControl());
      }
    });
  }

  /**
   * This method returns a SWTBotMenu item which was created dynamically.<br>
   * menuPath must contain at least one entry which is the menu item you want to access to. However you may indicate
   * the menu path where to find the menu item. <br>
   * If you have the following menu tree: <br>
   * Menu - SubMenu1 - MenuItem1 <br>
   * .........|------- MenuItem2 <br>
   * .........|------- MyMenu <br>
   * then call this method as menu("Menu", "SubMenu1", "MyMenu") <br>
   * If you omit the full menu path, the first "MyMenu" entry found will be returned: <br>
   * menu("MyMenu")
   *
   * @param menuPath
   *          the menu path including the menu item to find.
   * @return the sWT bot menu
   */
  public SWTBotMenu menu(final String... menuPath) {
    assertNotEquals("menuPath must contain at least one item", 0, menuPath.length); //$NON-NLS-1$
    final List<String> menuList = Arrays.asList(menuPath);

    final List<SWTBotMenu> menus = findMenus(reference, menuList.subList(0, menuList.size() - 1), true);
    for (final SWTBotMenu menuItem : menus) {
      if (menuItem.getText().equals(menuList.get(menuList.size() - 1))) {
        return menuItem;
      }
    }
    throw new WidgetNotFoundException("Could not find menu " + Arrays.toString(menuPath)); //$NON-NLS-1$
  }

  /**
   * Dispose the DynamicViewMenu object. The field menu might have allocated OS resources which must be disposed of.
   */
  public void dispose() {
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        originalMenu.dispose();
      }
    });
  }

  /**
   * Convenience method which clicks on the menu item found and disposes the object. See {@link menu}
   *
   * @param menuPath
   *          the menu path
   */
  public void click(final String... menuPath) {
    menu(menuPath).click();
    dispose();
  }

  /**
   * Find the menu items of a menu of a View.<br>
   * The menu items returned can be restricted by giving a menu path. If no path is given, all menu items found are
   * returned.
   *
   * @param view
   *          the view to probe for menus.
   * @param menuPath
   *          the menu path without the menu item to find.
   * @param recursive
   *          if set to <code>true</code>, will find sub-menus as well.
   * @return The list of SWTBotMenu items which match the menu path.
   * @since 2.0
   */
  private static List<SWTBotMenu> findMenus(final IViewReference view, final List<String> menuPath, final boolean recursive) {
    return UIThreadRunnable.syncExec(new ListResult<SWTBotMenu>() {
      @Override
      public List<SWTBotMenu> run() {
        final IViewSite viewSite = (IViewSite) view.getPart(false).getSite();
        final IMenuManager mgr = viewSite.getActionBars().getMenuManager();
        final List<SWTBotMenu> l = new ArrayList<SWTBotMenu>();
        l.addAll(getMenuItemsInternal(mgr.getItems(), menuPath, recursive));
        return l;
      }
    });
  }

  /**
   * Gets the menu items. This is expected to be called from within the UI thread. If not it will throw exceptions
   * based on invalid thread access.
   *
   * @param items
   *          the menu items to search through
   * @param menuPath
   *          the menu path without the menu item to find.
   * @param recursive
   *          if set to <code>true</code>, will find sub-menus as well.
   * @return The list of SWTBotMenu items which match the menu path.
   */
  @SuppressWarnings("PMD.CyclomaticComplexity")
  private static List<SWTBotMenu> getMenuItemsInternal(final IContributionItem[] items, final List<String> menuPath, final boolean recursive) {
    final List<SWTBotMenu> l = new ArrayList<SWTBotMenu>();
    final boolean findAnything = (menuPath == null) || menuPath.isEmpty();
    for (final IContributionItem item : items) {
      try {
        if ((item instanceof MenuManager) && recursive) {
          // Sub menus
          final MenuManager menuManager = (MenuManager) item;
          if (findAnything || menuManager.getMenuText().equals(menuPath.get(0))) {
            List<String> subList = null;
            if (menuPath.size() > 1) {
              subList = menuPath.subList(1, menuPath.size());
            }
            l.addAll(getMenuItemsInternal(menuManager.getItems(), subList, recursive));
          }
        } else if (item instanceof ContributionItem) {
          final ContributionItem dynItem = (ContributionItem) item;
          dynItem.fill(originalMenu, 0);
          final MenuItem[] items2 = originalMenu.getItems();
          for (final MenuItem item2 : items2) {
            l.add(new SWTBotMenu(item2));
          }
        }
      } catch (final WidgetNotFoundException widgetNotFoundException) {
        continue; // Do nothing
      }
    }

    return l;
  }

}
