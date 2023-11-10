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
package com.avaloq.tools.ddk.check.check.impl;

import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.util.Strings;


/**
 * A generic implementation getDescription that returns the Document provider yielded value.
 */
public class DocumentedImplCustom extends DocumentedImpl {

  @Override
  public String getDescription() {
    if (!this.eIsProxy() && this.eResource() != null) {
      IResourceServiceProvider serviceProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(this.eResource().getURI());
      if (serviceProvider != null) {
        IEObjectDocumentationProvider documentationProvider = serviceProvider.get(IEObjectDocumentationProvider.class);
        String documentation = documentationProvider.getDocumentation(this);
        if (!Strings.isEmpty(documentation)) { // NOPMD
          return documentation;
        }
      }
    }
    return null;
  }
}
