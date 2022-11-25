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
package com.avaloq.tools.ddk.xtext.validation;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.xtext.validation.CompositeEValidator;


/**
 * This class overrides {@link CompositeEValidator} so that the method
 * {@link EObjectValidator#validate_EveryReferenceIsContained(EObject, DiagnosticChain, Map)} can be overridden with a null-implementation.
 * The {@link EObjectValidator} implementation causes all proxies to be resolved, which is very expensive.
 */
public class ValidCompositeEValidator extends CompositeEValidator {

  @Override
  protected void initDefaults() {
    if (isUseEObjectValidator()) {
      // CHECKSTYLE:OFF
      this.addValidator(new EObjectValidator() {
        @Override
        public boolean validate_EveryProxyResolves(final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
          // this implementation is copied from CompositeEValidator.initDefaults()
          return true;
        }

        @Override
        public boolean validate_NoCircularContainment(final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
          // don't check
          return true;
        }

        @Override
        public boolean validate_EveryReferenceIsContained(final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
          // this implementation suppresses the resolution of all references during validation
          return true;
        }
      });
      // CHECKSTYLE:ON
    }
  }

}
