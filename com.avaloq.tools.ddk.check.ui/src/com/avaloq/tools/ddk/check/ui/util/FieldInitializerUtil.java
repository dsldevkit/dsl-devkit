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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.google.inject.Singleton;


/**
 * <p>
 * Copied from org.eclipse.xtext.xtend2.ui.wizards.FieldInitializerUtil.
 * </p>
 *
 * @author Robert von Massow - Initial contribution and API
 */
@Singleton
public class FieldInitializerUtil {

  /**
   * Gets the selected resource.
   *
   * @param selection
   *          the selection
   * @return the selected resource
   */
  public IJavaElement getSelectedResource(final IStructuredSelection selection) {
    IJavaElement elem = null;
    if (selection != null && !selection.isEmpty()) {
      Object o = selection.getFirstElement();
      if (o instanceof IAdaptable) {
        IAdaptable adaptable = (IAdaptable) o;
        elem = adaptable.getAdapter(IJavaElement.class);
        if (elem == null) { // NOPMD
          elem = getSelectedPackage(adaptable);
        }
      }
    }
    if (elem == null) {
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      IWorkbenchPart part = activePage.getActivePart();
      if (part instanceof ContentOutline) {
        part = activePage.getActiveEditor();
      }
      if (part instanceof XtextEditor) {
        IXtextDocument doc = ((XtextEditor) part).getDocument();
        IFile file = doc.getAdapter(IFile.class);
        elem = getSelectedPackage(file);
      }
    }
    if (elem == null || elem.getElementType() == IJavaElement.JAVA_MODEL) {
      try {
        IJavaProject[] projects = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot()).getJavaProjects();
        if (projects.length == 1) {
          elem = projects[0];
        }
      } catch (JavaModelException e) {
        throw new RuntimeException(e.getMessage()); // NOPMD
      }
    }
    return elem;
  }

  /**
   * Gets the selected package.
   *
   * @param adaptable
   *          the adaptable
   * @return the package
   */
  private IJavaElement getSelectedPackage(final IAdaptable adaptable) {
    IJavaElement elem = null;
    IResource resource = adaptable.getAdapter(IResource.class);
    if (resource != null && resource.getType() != IResource.ROOT) {
      while (elem == null && resource.getType() != IResource.PROJECT) {
        resource = resource.getParent();
        elem = resource.getAdapter(IJavaElement.class);
      }
    }
    if (elem == null) {
      elem = JavaCore.create(resource);
    }
    return elem;
  }
}
