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
package com.avaloq.tools.ddk.check.ui.builder.util;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * The extension point utility class for context sensitive help. Intended to be used by the Check builder participant.
 */
public class CheckContextsExtensionHelper extends AbstractCheckExtensionHelper implements ICheckExtensionHelper {

  public static final String FILE_ATTRIBUTE_TAG = "file";
  public static final String CONTEXTS_FILE_NAME = "docs/contexts.xml";
  public static final String CONTEXTS_EXTENSION_POINT_ID = "org.eclipse.help.contexts";
  public static final String CONTEXT_ELEMENT = "contexts";

  /** {@inheritDoc} */
  public Iterable<IPluginElement> getElements(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException {
    List<IPluginElement> result = Lists.newArrayList();
    result.add(updateContextsElement(extension.getModel().getFactory().createElement(extension)));
    return result;
  }

  /** {@inheritDoc} */
  public String getExtensionPointId() {
    return CONTEXTS_EXTENSION_POINT_ID;
  }

  /** {@inheritDoc} */
  public String getExtensionPointName(final CheckCatalog catalog) {
    return "Context sensitive help for check";
  }

  /** {@inheritDoc} */
  @Override
  public void doUpdateExtension(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) throws CoreException {
    if (Iterables.size(elements) == 1) {
      updateContextsElement(Iterables.get(elements, 0));
    }
  }

  /** {@inheritDoc} */
  public IPluginElement updateContextsElement(final IPluginElement element) throws CoreException {
    element.setName(CONTEXT_ELEMENT);
    element.setAttribute(FILE_ATTRIBUTE_TAG, CONTEXTS_FILE_NAME);
    return element;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isExtensionUpdateRequired(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) {
    if (extension.getPoint().equals(CONTEXTS_EXTENSION_POINT_ID) && extension.getName().equals(getExtensionPointName(catalog))) {
      for (IPluginElement element : elements) {
        if (element.getAttribute(FILE_ATTRIBUTE_TAG).getValue().equals(CONTEXTS_FILE_NAME)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final CheckCatalog catalog, final ExtensionType type) throws CoreException {
    // do nothing, the extension must not be removed
  }

  @Override
  public void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final IEObjectDescription obj, final ExtensionType type) throws CoreException {
    // do nothing, the extension must not be removed
  }

}

