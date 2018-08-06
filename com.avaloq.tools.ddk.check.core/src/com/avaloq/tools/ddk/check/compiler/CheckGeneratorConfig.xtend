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

package com.avaloq.tools.ddk.check.compiler

import org.eclipse.xtext.xbase.compiler.GeneratorConfig
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class CheckGeneratorConfig extends GeneratorConfig {

	/**
	 * Whether generators should produce code for DSL-internal checks.
	 */
	boolean generateLanguageInternalChecks = false

}
