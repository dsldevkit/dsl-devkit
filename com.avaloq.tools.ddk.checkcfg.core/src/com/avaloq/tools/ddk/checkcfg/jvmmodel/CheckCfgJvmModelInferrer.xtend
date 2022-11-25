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
package com.avaloq.tools.ddk.checkcfg.jvmmodel

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

/**
 * <p>Infers a JVM model from the source model.</p>
 *
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
class CheckCfgJvmModelInferrer extends AbstractModelInferrer {

  @Inject extension JvmTypesBuilder

  override void _infer(EObject element, IJvmDeclaredTypeAcceptor acceptor, boolean preIndexingPhase) {
    // Infer dummy class as type resolver expects at least one root Java type
    acceptor.accept(element.toClass("xxxyyyzzz.dummy.class.name")[])
  }

// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
// An example based on the initial hellow world example could look like this:
//   		acceptor.accept(element.toClass("my.company.greeting.MyGreetings") [
//   			for (greeting : element.greetings) {
//   				members += greeting.toMethod(greeting.name, greeting.newTypeRef(typeof(String))) [
//   					it.body ['''
//   						return "Hello «greeting.name»";
//   					''']
//   				]
//   			}
//   		])
}
