/*
 * generated by Xtext 2.14.0
 */
package com.avaloq.tools.ddk.sample.helloworld


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class HelloWorldStandaloneSetup extends HelloWorldStandaloneSetupGenerated {

	def static void doSetup() {
		new HelloWorldStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
