/*
 * generated by Xtext 2.14.0
 */
package com.avaloq.tools.ddk.xtext.format2.ui;

import com.avaloq.tools.ddk.xtext.format2.ui.internal.Format2Activator;
import com.google.inject.Injector;
import org.eclipse.core.runtime.Platform;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class Format2ExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Platform.getBundle(Format2Activator.PLUGIN_ID);
	}
	
	@Override
	protected Injector getInjector() {
		Format2Activator activator = Format2Activator.getInstance();
		return activator != null ? activator.getInjector(Format2Activator.COM_AVALOQ_TOOLS_DDK_XTEXT_FORMAT2_FORMAT2) : null;
	}

}
