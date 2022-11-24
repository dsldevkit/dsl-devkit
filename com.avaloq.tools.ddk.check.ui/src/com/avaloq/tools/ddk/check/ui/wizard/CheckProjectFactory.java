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
package com.avaloq.tools.ddk.check.ui.wizard;

import java.util.Iterator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.ui.util.PluginProjectFactory;


/**
 * A factory for creating CustomCheckProject objects.
 */
// CHECKSTYLE:OFF
@SuppressWarnings("nls")
public class CheckProjectFactory extends PluginProjectFactory {
  // CHECKSTYLE:ON

  @Override
  protected void enhanceProject(final IProject project, final SubMonitor subMonitor, final Shell shell) throws CoreException {
    super.enhanceProject(project, subMonitor, shell);
    createPluginXML(project, subMonitor.newChild(1));
  }

  /**
   * Creates the initial {@code plugin.xml} file. Note that the file created is empty, actual contents
   * for Catalogs are created by the builder participant.
   *
   * @param project
   *          the project
   * @param monitor
   *          the monitor
   * @throws CoreException
   *           the core exception
   */
  @SuppressWarnings("PMD.InsufficientStringBufferDeclaration")
  private void createPluginXML(final IProject project, final IProgressMonitor monitor) throws CoreException {
    final StringBuilder content = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<?eclipse version=\"3.4\"?>\n");
    content.append("<plugin>\n</plugin>\n");

    SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
    try {
      createFile("plugin.xml", project, content.toString(), subMonitor.newChild(1));
    } finally {
      subMonitor.done();
    }
  }

  @Override
  @SuppressWarnings("PMD.InsufficientStringBufferDeclaration")
  protected void createBuildProperties(final IProject project, final IProgressMonitor progressMonitor) {
    final StringBuilder content = new StringBuilder("source.. = ");
    for (final Iterator<String> iterator = folders.iterator(); iterator.hasNext();) {
      content.append(iterator.next()).append('/');
      if (iterator.hasNext()) {
        content.append(",\\\n");
        // source.. =
        content.append("          ");
      }
    }
    content.append('\n');
    content.append("bin.includes = META-INF/,\\\n");
    content.append("               .,\\\n");
    content.append("               plugin.xml,\\\n");
    content.append("               docs/");

    createFile("build.properties", project, content.toString(), progressMonitor);
  }

  /**
   * Overridden in order to change BundleVendor. {@inheritDoc}
   */
  @SuppressWarnings("PMD.InsufficientStringBufferDeclaration")
  @Override
  protected void createManifest(final IProject project, final IProgressMonitor progressMonitor) throws CoreException {
    final StringBuilder content = new StringBuilder("Manifest-Version: 1.0\n");
    content.append("Bundle-ManifestVersion: 2\n");
    content.append("Bundle-Name: " + projectName + "\n");
    content.append("Bundle-Vendor: %Bundle-Vendor\n");
    content.append("Bundle-Version: 1.0.0.qualifier\n");
    content.append("Bundle-SymbolicName: " + projectName + ";singleton:=true\n");
    if (null != activatorClassName) {
      content.append("Bundle-Activator: " + activatorClassName + "\n");
    }
    content.append("Bundle-ActivationPolicy: lazy\n");

    addToContent(content, requiredBundles, "Require-Bundle");
    addToContent(content, exportedPackages, "Export-Package");
    addToContent(content, importedPackages, "Import-Package");

    content.append("Bundle-RequiredExecutionEnvironment: JavaSE-1.7\n");

    final IFolder metaInf = project.getFolder("META-INF");
    SubMonitor subMonitor = SubMonitor.convert(progressMonitor, 2);
    try {
      if (metaInf.exists()) {
        metaInf.delete(false, progressMonitor);
      }
      metaInf.create(false, true, subMonitor.newChild(1));
      createFile("MANIFEST.MF", metaInf, content.toString(), subMonitor.newChild(1));
    } finally {
      subMonitor.done();
    }
  }
}
