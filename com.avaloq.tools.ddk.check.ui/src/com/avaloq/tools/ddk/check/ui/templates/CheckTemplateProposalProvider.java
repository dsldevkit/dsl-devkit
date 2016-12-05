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

import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.templates.ContextTypeIdHelper;
import org.eclipse.xtext.ui.editor.templates.DefaultTemplateProposalProvider;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.ui.labeling.CheckImages;
import com.google.inject.Inject;


/**
 * A custom template proposal provider for Check.
 */
public class CheckTemplateProposalProvider extends DefaultTemplateProposalProvider {

  @Inject
  private CheckImages images;

  @Inject
  public CheckTemplateProposalProvider(final TemplateStore templateStore, final ContextTypeRegistry registry, final ContextTypeIdHelper helper) {
    super(templateStore, registry, helper);
  }

  @Override
  public Image getImage(final Template template) {
    if (Strings.equal("Catalog", template.getName())) {
      return images.forCheckCatalog();
    } else if (Strings.equal("Check", template.getName())) {
      return images.forCheck(SeverityKind.ERROR);
    } else if (Strings.equal("Category", template.getName())) {
      return images.forCategory();
    } else if (Strings.equal("Severity range", template.getName())) {
      return images.forSeverityRange();
    }
    return super.getImage(template);
  }

}

