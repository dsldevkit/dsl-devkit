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
package com.avaloq.tools.ddk.xtext.ui.editor.model.edit;

import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;


/**
 * An interface that subsubmes the properties of objects that are semantic modification,
 * may be bulk-applicable, and/or may be multi-fixes.
 */
public interface IBulkSemanticModification extends ISemanticModification, IBulkApplicable, IMultiFix {

}
