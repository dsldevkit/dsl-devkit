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

import org.eclipse.emf.ecore.EObject;


/**
 * Copy of Xtext's {@link org.eclipse.xtext.ui.editor.model.edit.ISemanticModification ISemanticModification}.
 * <p>
 * This class is UI-independent.
 */
public interface ICoreSemanticModification {
  /**
   * Applies a semantic quickfix.
   * 
   * @param element
   *          the context element for the semantic quickfix; the context element for which a validation issue has been created
   * @param context
   *          the modification context
   */
  void apply(EObject element, ICoreModificationContext context);

}
