/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.test;

import com.avaloq.tools.ddk.xtext.test.TagCompilationParticipant;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Initializes global tags in linking tests.
 * The annotated field must be of integer type.
 * Usage example: @Tag int MEM_DOC
 */
@Active(TagCompilationParticipant.class)
public @interface Tag {
}
