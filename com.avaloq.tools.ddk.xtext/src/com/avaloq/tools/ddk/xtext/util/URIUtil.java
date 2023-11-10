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
package com.avaloq.tools.ddk.xtext.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;


/**
 * Various utility methods for URIs.
 */
public final class URIUtil {

  /**
   * Private constructor for utility classes.
   */
  private URIUtil() {
  }

  /**
   * Gets the workspace file corresponding to the given URI. Returns null if
   * not available -- for instance when called from a workflow
   * fragment (the workbench is closed.)
   *
   * @param uri
   *          the uri
   * @return the workspace file
   */
  public static IFile getWorkspaceFile(final URI uri) {
    return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
  }

}
