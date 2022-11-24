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

/**
 * The interface ICoreModification is a copy of {@link org.eclipse.xtext.ui.editor.model.edit.IModification}.
 * <p>
 * This class is UI-independent.
 */
public interface ICoreModification {

  /**
   * Applies a textual quickfix.
   * 
   * @param context
   *          the modification context instance
   * @throws Exception
   *           the exception
   */
  void apply(ICoreModificationContext context) throws Exception; // NOPMD copy of IModification

  ICoreModification NULL = new ICoreModification() {
    public void apply(final ICoreModificationContext context) {}
  };
}

