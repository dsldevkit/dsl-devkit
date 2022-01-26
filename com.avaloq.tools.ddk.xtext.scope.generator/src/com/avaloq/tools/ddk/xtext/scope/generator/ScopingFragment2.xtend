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

package com.avaloq.tools.ddk.xtext.scope.generator

import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess

import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*
import static extension org.eclipse.xtext.GrammarUtil.*
import org.eclipse.xtext.scoping.IScopeProvider
import com.avaloq.tools.ddk.xtext.scoping.IScopeNameProvider
import org.eclipse.xtext.linking.ILinkingService
import org.eclipse.xtext.xtext.generator.model.TypeReference
import com.avaloq.tools.ddk.xtext.linking.LinkingService

class ScopingFragment2 extends AbstractXtextGeneratorFragment {

	static val RUNTIME_PLUGIN = "com.avaloq.tools.ddk.xtext"

	override generate() {
		val prefix = grammar.namespace + ".scoping." + grammar.simpleName
		new GuiceModuleAccess.BindingFactory()
			.addTypeToType(IScopeProvider.typeRef, new TypeReference(prefix + "ScopeProvider"))
			.addTypeToType(IScopeNameProvider.typeRef, new TypeReference(prefix + "ScopeNameProvider"))
			.addTypeToType(ILinkingService.typeRef, LinkingService.typeRef)
			.contributeTo(language.runtimeGenModule)

		if (projectConfig.runtime.manifest !== null) {
			projectConfig.runtime.manifest.requiredBundles += "org.eclipse.emf.ecore"
			projectConfig.runtime.manifest.requiredBundles += RUNTIME_PLUGIN
			projectConfig.runtime.manifest.exportedPackages += grammar.namespace + ".scoping"
		}

		if (projectConfig.eclipsePlugin.manifest !== null) {
			projectConfig.runtime.manifest.requiredBundles += RUNTIME_PLUGIN
		}
	}
}