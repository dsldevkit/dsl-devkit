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
package com.avaloq.tools.ddk.check.ui.test.contentassist;

import static org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.findJavaProject;

import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.ui.testing.util.ResourceLoadHelper;

import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase;
import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager;


/**
 * Class used for content assist UI tests requiring a project.
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "restriction"})
public abstract class AbstractCheckContentAssistBugTest extends AbstractCheckTestCase implements IJavaProjectProvider, ResourceLoadHelper {

  /** {@inheritDoc} */
  @Override
  public IJavaProject getJavaProject(final ResourceSet resourceSet) {
    IJavaProject javaProject = findJavaProject(PluginTestProjectManager.TEST_PROJECT_NAME);
    if (javaProject == null || !javaProject.exists()) {
      javaProject = findJavaProject(PluginTestProjectManager.TEST_PROJECT_NAME);
    }
    return javaProject;
  }

  /** {@inheritDoc} */
  @Override
  public XtextResource getResourceFor(final InputStream stream) {
    try {
      XtextResourceSet set = get(XtextResourceSet.class);
      XtextResource resource = (XtextResource) set.createResource(URI.createURI("Test." + getFileExtension())); //$NON-NLS-1$
      resource.load(stream, null);
      initializeTypeProvider(set);
      return resource;
      // CHECKSTYLE:OFF
    } catch (Exception e) {
      // CHECKSTYLE:ON
      fail(e.getMessage());
    }
    return null;
  }

  /**
   * Initializes the type provider used for accessing JvmTypes.
   *
   * @param set
   *          the resource set
   */
  protected void initializeTypeProvider(final XtextResourceSet set) {
    IJvmTypeProvider.Factory typeProviderFactory = new JdtTypeProviderFactory(this);
    typeProviderFactory.findOrCreateTypeProvider(set);
    set.setClasspathURIContext(getJavaProject(set));
  }

  /**
   * Creates a new content assist processor test builder.
   *
   * @return the content assist processor test builder
   * @throws Exception
   *           the exception
   */
  protected ContentAssistProcessorTestBuilder newBuilder() throws Exception {
    return new ContentAssistProcessorTestBuilder(getInjector(), this);
  }

}
