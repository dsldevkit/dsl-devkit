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
package com.avaloq.tools.ddk.xtext.ui.templates;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.templates.AbstractTemplateVariableResolver;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Resolves a resource name.
 */
public class ResourceNameTemplateVariableResolver extends AbstractTemplateVariableResolver {

  private static final Logger LOGGER = LogManager.getLogger(ResourceNameTemplateVariableResolver.class);

  public ResourceNameTemplateVariableResolver() {
    super("ResourceName", "Resolves a resource name"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public List<String> resolveValues(final TemplateVariable variable, final XtextTemplateContext templateContext) {
    final List<String> result = Lists.newArrayList();
    final IDocument document = templateContext.getDocument();
    final Object obj = variable.getVariableType().getParams().iterator().next();
    if (obj instanceof String) {
      final String variableName = (String) obj;
      final IXtextDocument xtextDocument = (IXtextDocument) document;
      final IFile file = xtextDocument.getAdapter(IFile.class);

      switch (variableName) {
      case "package": //$NON-NLS-1$
        if (document instanceof IXtextDocument && file != null && file.getParent() instanceof IFolder) {
          final IJavaProject javaProject = JavaCore.create(file.getProject());
          try {
            final IPackageFragment packageFragment = javaProject.findPackageFragment(file.getParent().getFullPath());
            result.add(packageFragment.getElementName());
          } catch (final JavaModelException e) {
            LOGGER.error("Could not determine package for file of given document"); //$NON-NLS-1$
          }
        }
        break;
      case "file": //$NON-NLS-1$
        final String fileName = file.getName();
        result.add(fileName.indexOf('.') > 0 ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName);
      }
    }
    return Lists.newArrayList(Iterables.filter(result, Predicates.notNull()));
  }
}
