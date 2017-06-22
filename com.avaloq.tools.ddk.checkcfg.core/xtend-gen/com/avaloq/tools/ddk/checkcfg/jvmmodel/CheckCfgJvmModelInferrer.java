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
package com.avaloq.tools.ddk.checkcfg.jvmmodel;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class CheckCfgJvmModelInferrer extends AbstractModelInferrer {
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  @Override
  public void _infer(final EObject element, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
    };
    JvmGenericType _class = this._jvmTypesBuilder.toClass(element, "xxxyyyzzz.dummy.class.name", _function);
    acceptor.<JvmGenericType>accept(_class);
  }
}
