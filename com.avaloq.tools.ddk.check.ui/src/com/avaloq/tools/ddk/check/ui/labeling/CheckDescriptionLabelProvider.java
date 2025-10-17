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
package com.avaloq.tools.ddk.check.ui.labeling;

import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.xbase.ui.labeling.XbaseDescriptionLabelProvider;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.google.inject.Inject;


/**
 * Provides labels for a IEObjectDescriptions and IResourceDescriptions.
 */
public class CheckDescriptionLabelProvider extends XbaseDescriptionLabelProvider {
  @Inject
  private CheckImages images;

  @Override
  public String text(final IEObjectDescription element) {
    return element.getName() + " - " + element.getEClass().getName(); //$NON-NLS-1$
  }

  @Override
  public Object image(final IEObjectDescription element) {
    String className = element.getEClass().getName();
    if (CheckCatalog.class.getSimpleName().equals(className)) {
      return images.forCheckCatalog();
    } else if (Category.class.getSimpleName().equals(className)) {
      return images.forCategory();
    } else if (Check.class.getSimpleName().equals(className)) {
      return images.forCheck(SeverityKind.ERROR);
    } else if (org.eclipse.xtext.xtype.XImportDeclaration.class.getSimpleName().equals(className)) {
      return images.forImport();
    }
    return super.image(element);

  }

}
