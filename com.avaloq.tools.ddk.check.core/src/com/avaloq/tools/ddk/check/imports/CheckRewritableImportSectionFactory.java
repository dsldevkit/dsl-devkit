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

package com.avaloq.tools.ddk.check.imports;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.imports.RewritableImportSection;


/**
 * Override of {@link RewritableImportSection.Factory}, to create {@link RewritableImportSection}s which automatically sort import lines.
 */
public class CheckRewritableImportSectionFactory extends RewritableImportSection.Factory {

  /**
   * Override of parse(), to create {@link RewritableImportSection}s which automatically sort import lines.
   */
  @Override
  public RewritableImportSection parse(final XtextResource resource) {
    RewritableImportSection rewritableImportSection = super.parse(resource);
    rewritableImportSection.setSort(true);
    return rewritableImportSection;
  }

}
