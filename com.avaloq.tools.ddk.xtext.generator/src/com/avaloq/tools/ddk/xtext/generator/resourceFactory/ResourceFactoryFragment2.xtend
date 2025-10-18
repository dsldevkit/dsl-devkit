/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.xtext.generator.resourceFactory

import com.google.inject.Inject
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming
import org.eclipse.xtext.resource.IResourceFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.List

/**
 * Implementation that allows the fileExtensions for the fragment to be distinct from language.fileExtensions
 */
class ResourceFactoryFragment2 extends AbstractXtextGeneratorFragment {

  @Accessors(PROTECTED_GETTER) List<String> fileExtensions

  @Inject
  extension XtextGeneratorNaming

  /**
   * Either a single file extension or a comma-separated list of extensions for which the language
   * shall be registered.
   */
  def void setFileExtensions(String fileExtensions) {
    this.fileExtensions = fileExtensions.trim.split('\\s*,\\s*').toList
  }

  // This is ResourceFactoryFragment2#generate with language.fileExtensions replaced by getFileExtensions
  override generate() {

    language.runtimeGenSetup.registrations.add('''
			«IResourceFactory» resourceFactory = injector.getInstance(«IResourceFactory».class);
			«IResourceServiceProvider» serviceProvider = injector.getInstance(«IResourceServiceProvider».class);

			«FOR fileExtension : getFileExtensions»
				«Resource».Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("«fileExtension»", resourceFactory);
				«IResourceServiceProvider».Registry.INSTANCE.getExtensionToFactoryMap().put("«fileExtension»", serviceProvider);
			«ENDFOR»
		''')

    if (projectConfig.eclipsePlugin?.pluginXml !== null) {
      projectConfig.eclipsePlugin.pluginXml.entries += '''
				<!-- adding resource factories -->
				«FOR fileExtension : getFileExtensions»
					<extension
					  point="org.eclipse.emf.ecore.extension_parser">
					  <parser
					    class="«grammar.eclipsePluginExecutableExtensionFactory»:org.eclipse.xtext.resource.IResourceFactory"
					    type="«fileExtension»">
					  </parser>
					</extension>
					<extension point="org.eclipse.xtext.extension_resourceServiceProvider">
					  <resourceServiceProvider
					    class="«grammar.eclipsePluginExecutableExtensionFactory»:org.eclipse.xtext.ui.resource.IResourceUIServiceProvider"
					    uriExtension="«fileExtension»">
					  </resourceServiceProvider>
					</extension>
				«ENDFOR»
			'''
    }
  }

}

/* Copyright (c) Avaloq Group AG */