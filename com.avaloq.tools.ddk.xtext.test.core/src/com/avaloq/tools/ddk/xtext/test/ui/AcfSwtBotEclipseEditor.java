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
package com.avaloq.tools.ddk.xtext.test.ui;

import org.eclipse.osgi.util.NLS;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.utils.Position;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.ui.IEditorReference;


/**
 * Customization of {@link SWTBotEclipseEditor}.
 */
public class AcfSwtBotEclipseEditor extends SWTBotEclipseEditor {

  public AcfSwtBotEclipseEditor(final IEditorReference editorReference, final SWTWorkbenchBot bot) {
    super(editorReference, bot);
  }

  @Override
  public void navigateTo(final int line, final int column) {
    super.navigateTo(line, column);
    waitUntilCursorIsOnPosition(line, column);
  }

  @Override
  public void navigateTo(final Position position) {
    super.navigateTo(position);
    waitUntilCursorIsOnPosition(position.line, position.column);
  }

  /**
   * Synchronously wait until the cursor is on the given position.
   * 
   * @param line
   *          line number
   * @param column
   *          column number
   */
  private void waitUntilCursorIsOnPosition(final int line, final int column) {
    bot.waitUntil(new DefaultCondition() {

      public boolean test() {
        Position position = cursorPosition();
        return isActive() && position.line == line && position.column == column;
      }

      public String getFailureMessage() {
        return NLS.bind("The cursor should be on the position ({0}, {1}) and the editor should have focus.", line, column);
      }

    });
  }

}

