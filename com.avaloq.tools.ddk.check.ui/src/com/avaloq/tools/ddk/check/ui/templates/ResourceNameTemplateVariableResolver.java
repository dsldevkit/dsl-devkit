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
package com.avaloq.tools.ddk.check.ui.templates;

import java.util.List;

import org.apache.log4j.Logger;
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
 * The Class PackageNameResolver.
 */
public class ResourceNameTemplateVariableResolver extends AbstractTemplateVariableResolver {

  private static final Logger LOGGER = Logger.getLogger(ResourceNameTemplateVariableResolver.class);

  public ResourceNameTemplateVariableResolver() {
    super("ResourceName", "Resolves the current package name");
  }

  @Override
  public List<String> resolveValues(final TemplateVariable variable, final XtextTemplateContext templateContext) {
    final List<String> result = Lists.newArrayList();
    IDocument document = templateContext.getDocument();
    final Object obj = variable.getVariableType().getParams().iterator().next();
    if (obj instanceof String) {
      final String variableName = (String) obj;
      final IXtextDocument xtextDocument = (IXtextDocument) document;
      IFile file = xtextDocument.getAdapter(IFile.class);

      if ("package".equals(variableName) && document instanceof IXtextDocument) {
        if (file != null && file.getParent() instanceof IFolder) {
          IJavaProject javaProject = JavaCore.create(file.getProject());
          try {
            IPackageFragment packageFragment = javaProject.findPackageFragment(file.getParent().getFullPath());
            result.add(packageFragment.getElementName());
          } catch (JavaModelException e) {
            LOGGER.error("Could not determine package for file of given document");
          }
        }
      } else if ("catalog".equals(variableName)) {
        final String fileName = file.getName();
        result.add(fileName.indexOf('.') > 0 ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName);
      }
    }
    return Lists.newArrayList(Iterables.filter(result, Predicates.notNull()));
  }
}

