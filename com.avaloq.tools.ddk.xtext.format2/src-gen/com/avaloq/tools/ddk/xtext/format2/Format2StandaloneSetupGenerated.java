/*
 * generated by Xtext 2.14.0
 */
package com.avaloq.tools.ddk.xtext.format2;

import com.avaloq.tools.ddk.xtext.format2.format2.Format2Package;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.xbase.annotations.XbaseWithAnnotationsStandaloneSetup;

@SuppressWarnings("all")
public class Format2StandaloneSetupGenerated implements ISetup {

	@Override
	public Injector createInjectorAndDoEMFRegistration() {
		XbaseWithAnnotationsStandaloneSetup.doSetup();

		Injector injector = createInjector();
		register(injector);
		return injector;
	}
	
	public Injector createInjector() {
		return Guice.createInjector(new Format2RuntimeModule());
	}
	
	public void register(Injector injector) {
		if (!EPackage.Registry.INSTANCE.containsKey("http://www.avaloq.com/tools/ddk/xtext/format2/Format2")) {
			EPackage.Registry.INSTANCE.put("http://www.avaloq.com/tools/ddk/xtext/format2/Format2", Format2Package.eINSTANCE);
		}
		IResourceFactory resourceFactory = injector.getInstance(IResourceFactory.class);
		IResourceServiceProvider serviceProvider = injector.getInstance(IResourceServiceProvider.class);
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("format", resourceFactory);
		IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("format", serviceProvider);
	}
}
