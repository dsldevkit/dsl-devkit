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
package com.avaloq.tools.ddk.check.ui.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


/**
 * Helper methods to export Java project as OSGi bundle into a JAR.
 */
public final class ProjectUtil {

  private ProjectUtil() {
    // Prevents creating an instance
  }

  /**
   * Retrieved the project from the active workbench window.
   *
   * @return the current selected project.
   */
  public static IProject getProject() {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
      Object firstElement = selection.getFirstElement();
      if (firstElement instanceof IAdaptable) {
        return ((IAdaptable) firstElement).getAdapter(IProject.class);
      }
    }
    return null;
  }
}
