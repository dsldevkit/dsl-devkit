/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.ui.test.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.actions.ToggleBreakpointAction;
import org.eclipse.jdt.debug.core.JDIDebugModel;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.IVerticalRulerExtension;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.ui.editor.breakpoints.BreakpointRulerActionContributor;
import com.avaloq.tools.ddk.xtext.ui.editor.breakpoints.BreakpointRulerMenu;
import com.avaloq.tools.ddk.xtext.ui.util.Function;
import com.avaloq.tools.ddk.xtext.ui.util.UiThreadDispatcher;


/**
 * Tests the breakpoint actions of the vertical ruler in the Check, Format and Check Configuration editors.
 */
@SuppressWarnings("nls")
class BreakpointRulerActionsTest {

  private static final String CATALOG = "package com.test catalog c for grammar g { live error \"First Check\"{ for g { issue }}}";
  private static final String TOGGLE = "Toggle Breakpoint";
  private static final String PROPERTIES = "Breakpoint Properties...";
  private static final long TIMEOUT_MILLIS = 10_000;
  private static final long POLL_MILLIS = 100;

  private IProject project;

  @BeforeEach
  void setUp() throws CoreException {
    project = ResourcesPlugin.getWorkspace().getRoot().getProject("BreakpointRulerActionsTest");
    IProjectDescription description = ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
    // Without the Xtext nature the editor asks whether to add it, in a modal dialog.
    description.setNatureIds(new String[] {XtextProjectHelper.NATURE_ID});
    project.create(description, null);
    project.open(null);
  }

  @AfterEach
  void tearDown() throws CoreException {
    UiThreadDispatcher.dispatchAndWait(() -> {
      getActivePage().closeAllEditors(false);
    });
    DebugPlugin.getDefault().getBreakpointManager().removeBreakpoints(DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(), true);
    project.delete(true, true, null);
  }

  @Test
  void testCheckEditorHasBreakpointRulerActions() throws CoreException {
    assertHasBreakpointRulerActions(openEditor("c.check", CATALOG));
  }

  @Test
  void testFormatEditorHasBreakpointRulerActions() throws CoreException {
    assertHasBreakpointRulerActions(openEditor("f.format", "formatter for g"));
  }

  @Test
  void testCheckConfigurationEditorHasNoBreakpointRulerActions() throws CoreException {
    XtextEditor editor = openEditor("c.checkcfg", "check configuration c {}");

    assertFalse(editor.getAction(ITextEditorActionConstants.RULER_DOUBLE_CLICK) instanceof ToggleBreakpointAction, "Double-clicking the ruler must not toggle a breakpoint");
    assertNull(editor.getAction(BreakpointRulerActionContributor.ENABLE_DISABLE_ACTION_ID), "The enable/disable action must not be installed");
    assertNull(editor.getAction(BreakpointRulerActionContributor.PROPERTIES_ACTION_ID), "The properties action must not be installed");
    assertEquals(List.of(), getRulerMenuLabels(), "The ruler menu must not offer breakpoint actions");
  }

  @Test
  void testActionsFollowTheBreakpointOnTheRulerLine() throws CoreException {
    XtextEditor editor = openEditor("c.check", CATALOG);
    IAction enableDisable = editor.getAction(BreakpointRulerActionContributor.ENABLE_DISABLE_ACTION_ID);
    IAction properties = editor.getAction(BreakpointRulerActionContributor.PROPERTIES_ACTION_ID);
    clickRulerOnFirstLine(editor);

    assertFalse(updateAndGetEnabled(enableDisable), "Without a breakpoint the enable/disable action must be disabled");
    assertFalse(updateAndGetEnabled(properties), "Without a breakpoint the properties action must be disabled");

    final IBreakpoint breakpoint = JDIDebugModel.createStratumBreakpoint(project.getFile("c.check"), "Check", null, null, "com.test.*", 1, 0, 1, 0, true, null);
    waitUntilEnabled(enableDisable);

    assertTrue(updateAndGetEnabled(properties), "With a breakpoint the properties action must be enabled");
    assertEquals(List.of(TOGGLE, "Disable Breakpoint", PROPERTIES), getRulerMenuLabels(), "The ruler menu must offer the breakpoint actions");

    UiThreadDispatcher.dispatchAndWait(enableDisable::run);
    assertFalse(breakpoint.isEnabled(), "The action must disable the breakpoint");
    assertEquals(List.of(TOGGLE, "Enable Breakpoint", PROPERTIES), getRulerMenuLabels(), "The ruler menu must offer to enable the breakpoint");

    UiThreadDispatcher.dispatchAndWait(enableDisable::run);
    assertTrue(breakpoint.isEnabled(), "The action must enable the breakpoint");
  }

  private void assertHasBreakpointRulerActions(final XtextEditor editor) {
    assertInstanceOf(ToggleBreakpointAction.class, editor.getAction(ITextEditorActionConstants.RULER_DOUBLE_CLICK), "Double-clicking the ruler must toggle a breakpoint");
    assertNotNull(editor.getAction(BreakpointRulerActionContributor.ENABLE_DISABLE_ACTION_ID), "The enable/disable action must be installed");
    assertNotNull(editor.getAction(BreakpointRulerActionContributor.PROPERTIES_ACTION_ID), "The properties action must be installed");
    assertEquals(3, getRulerMenuLabels().size(), "The ruler menu must offer the three breakpoint actions");
  }

  private XtextEditor openEditor(final String fileName, final String content) throws CoreException {
    IFile file = project.getFile(fileName);
    file.create(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), true, null);
    return UiThreadDispatcher.dispatchAndWait(new Function<XtextEditor>() {
      @Override
      public XtextEditor run() {
        try {
          IEditorPart part = IDE.openEditor(getActivePage(), file);
          return assertInstanceOf(XtextEditor.class, part);
        } catch (PartInitException e) {
          throw new IllegalStateException(e);
        }
      }
    });
  }

  /**
   * Makes the first line the ruler line of the last mouse activity, as a click on the ruler does.
   *
   * @param editor
   *          the editor, must not be {@code null}
   */
  private void clickRulerOnFirstLine(final XtextEditor editor) {
    UiThreadDispatcher.dispatchAndWait(() -> {
      IVerticalRulerInfo rulerInfo = editor.getAdapter(IVerticalRulerInfo.class);
      assertInstanceOf(IVerticalRulerExtension.class, rulerInfo).setLocationOfLastMouseButtonActivity(0, 1);
      assertEquals(0, rulerInfo.getLineOfLastMouseButtonActivity(), "The ruler line must be the first line");
    });
  }

  private boolean updateAndGetEnabled(final IAction action) {
    return UiThreadDispatcher.dispatchAndWait(new Function<Boolean>() {
      @Override
      public Boolean run() {
        ((IUpdate) action).update();
        return action.isEnabled();
      }
    });
  }

  /**
   * Waits until the editor shows the breakpoint, which enables the given action.
   *
   * @param action
   *          the action, must not be {@code null}
   */
  private void waitUntilEnabled(final IAction action) {
    long deadline = System.currentTimeMillis() + TIMEOUT_MILLIS;
    while (!updateAndGetEnabled(action)) {
      assertTrue(System.currentTimeMillis() < deadline, "The editor must show the breakpoint on the ruler line");
      try {
        Thread.sleep(POLL_MILLIS);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new IllegalStateException(e);
      }
    }
  }

  /**
   * Returns the labels, without mnemonics and accelerators, that the breakpoint contribution adds to the ruler menu of the active editor.
   *
   * @return the labels, never {@code null}
   */
  private List<String> getRulerMenuLabels() {
    return UiThreadDispatcher.dispatchAndWait(new Function<List<String>>() {
      @Override
      public List<String> run() {
        Menu menu = new Menu(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        try {
          new BreakpointRulerMenu().fill(menu, 0);
          List<String> labels = new ArrayList<>();
          for (MenuItem item : menu.getItems()) {
            labels.add(item.getText().replace("&", "").split("\t")[0]);
          }
          return labels;
        } finally {
          menu.dispose();
        }
      }
    });
  }

  private static IWorkbenchPage getActivePage() {
    return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
  }
}
