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
package com.avaloq.tools.ddk.xtext.validation;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.xtext.validation.CompositeEValidator;

import com.avaloq.tools.ddk.xtext.util.EObjectUtil;


/**
 * This class overrides {@link CompositeEValidator} so that the method
 * {@link EObjectValidator#validate_EveryReferenceIsContained(EObject, DiagnosticChain, Map)} can be overridden with a null-implementation.
 * The {@link EObjectValidator} implementation causes all proxies to be resolved, which is very expensive.
 * <p>
 * This class also fixes the Xtext bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=396726.
 */
public class ValidCompositeEValidator extends CompositeEValidator {

  private static final String VALIDATION_ERROR = "Error executing EValidator"; //$NON-NLS-1$
  private static final Logger LOG = Logger.getLogger(ValidCompositeEValidator.class);

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

  /**
   * Fixes the bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=396726.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
    boolean result = true;
    for (int i = 0; i < getContents().size(); i++) {
      EValidatorEqualitySupport val = getContents().get(i);
      try {
        result &= val.getDelegate().validate(eObject, diagnostics, context);
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        // CHECKSTYLE:ON
        LOG.error(VALIDATION_ERROR + ": " + EObjectUtil.getLocationString(eObject), e); //$NON-NLS-1$
        diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, eObject.toString(), 0, VALIDATION_ERROR, new Object[] {e}));
      }
    }
    return result;
  }

  /**
   * Fixes the bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=396726.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final EClass eClass, final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
    boolean result = true;
    for (int i = 0; i < getContents().size(); i++) {
      EValidatorEqualitySupport val = getContents().get(i);
      try {
        // always trigger validation
        result &= val.getDelegate().validate(eClass, eObject, diagnostics, context);
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        // CHECKSTYLE:ON
        LOG.error(VALIDATION_ERROR + ": " + EObjectUtil.getLocationString(eObject), e); //$NON-NLS-1$
        diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, eClass.toString(), 0, VALIDATION_ERROR, new Object[] {e}));
      }
    }
    return result;
  }

  /**
   * Fixes the bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=396726.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final EDataType eDataType, final Object value, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
    boolean result = true;
    for (int i = 0; i < getContents().size(); i++) {
      EValidatorEqualitySupport val = getContents().get(i);
      try {
        result &= val.getDelegate().validate(eDataType, value, diagnostics, context);
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        // CHECKSTYLE:ON
        LOG.error(VALIDATION_ERROR, e);
        diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, eDataType.toString(), 0, VALIDATION_ERROR, new Object[] {e}));
      }
    }
    return result;
  }

}
