/*
 * generated by Xtext 2.19.0
 */
package com.avaloq.tools.ddk.sample.helloworld.ide;

import org.eclipse.xtext.util.Modules2;

import com.avaloq.tools.ddk.sample.helloworld.HelloWorldRuntimeModule;
import com.avaloq.tools.ddk.sample.helloworld.HelloWorldStandaloneSetup;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class HelloWorldIdeSetup extends HelloWorldStandaloneSetup {

    @Override
    public Injector createInjector() {
        return Guice.createInjector(Modules2.mixin(new HelloWorldRuntimeModule(), new HelloWorldIdeModule()));
    }

}
