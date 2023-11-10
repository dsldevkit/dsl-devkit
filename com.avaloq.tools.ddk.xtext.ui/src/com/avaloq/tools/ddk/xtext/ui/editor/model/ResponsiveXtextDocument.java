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
package com.avaloq.tools.ddk.xtext.ui.editor.model;

import org.eclipse.xtext.ui.editor.model.DocumentTokenSource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer;

import com.google.inject.Inject;


/**
 * Custom implementation of {@link XtextDocument} which delays the execution of the validation job to prevent repeated and unnecessary calls.
 */
public class ResponsiveXtextDocument extends XtextDocument {

  private static final long VALIDATION_JOB_DELAY = 500;

  @Inject
  public ResponsiveXtextDocument(final DocumentTokenSource tokenSource, final ITextEditComposer composer) {
    super(tokenSource, composer);
  }

  @Override
  public void checkAndUpdateAnnotations() {
    if (getValidationJob() != null) {
      getValidationJob().cancel();
      getValidationJob().schedule(VALIDATION_JOB_DELAY);
    }
  }
}
