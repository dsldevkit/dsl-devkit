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
package com.avaloq.tools.ddk.check.runtime.quickfix;

import org.eclipse.emf.common.util.WrappedException;


/**
 * Copy of Xtext's {@link org.eclipse.xtext.ui.editor.quickfix.IssueResolution IssueResolution} implementation.
 * <p>
 * This class is UI-independent.
 */
// CHECKSTYLE:OFF
public class CoreIssueResolution {

  private final String description;
  private final String label;
  private final String image;
  private final ICoreModification modification;
  private final ICoreModificationContext modificationContext;

  public CoreIssueResolution(final String label, final String description, final String image, final ICoreModificationContext modificationContext, final ICoreModification modification) {
    this.description = description;
    this.label = label;
    this.image = image;
    this.modificationContext = modificationContext;
    this.modification = modification;
  }

  public String getDescription() {
    return description;
  }

  public String getLabel() {
    return label;
  }

  public String getImage() {
    return image;
  }

  public ICoreModification getModification() {
    return modification;
  }

  public ICoreModificationContext getModificationContext() {
    return modificationContext;
  }

  public void apply() {
    try {
      modification.apply(modificationContext);
    } catch (Exception exc) {
      throw new WrappedException(exc);
    }
  }
}
