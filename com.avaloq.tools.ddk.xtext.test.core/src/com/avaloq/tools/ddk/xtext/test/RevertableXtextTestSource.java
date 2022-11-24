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
package com.avaloq.tools.ddk.xtext.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResource;

import com.google.common.io.Files;


/**
 * Implementation of {@link XtextTestSource} which supports {@link #reload()} to reload a test source's contents from its {@link #getiFile() workspace location}
 * .
 */
public class RevertableXtextTestSource extends XtextTestSource {

  private String content;

  public RevertableXtextTestSource(final String sourceFileName, final String content, final ResourceSet resourceSet) {
    super(sourceFileName, content, resourceSet);
    this.content = content;
  }

  @Override
  public String getContent() {
    return content;
  }

  /**
   * Reloads the contents of this source with the workspace file.
   */
  public void reload() {
    try {
      XtextResource resource = basicGetXtextResource();
      if (resource != null) {
        EList<Resource> resources = resource.getResourceSet().getResources();
        resources.clear();
        resources.add(resource);
      }
      content = Files.asCharSource(new File(getiFile().getLocationURI()), Charset.forName(getiFile().getCharset())).read();
      load();
    } catch (IOException e) {
      throw new WrappedException(e);
    } catch (CoreException e) {
      throw new WrappedException(e);
    }
  }

}
