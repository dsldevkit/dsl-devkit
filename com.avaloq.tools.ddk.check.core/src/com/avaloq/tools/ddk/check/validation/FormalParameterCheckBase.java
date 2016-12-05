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
package com.avaloq.tools.ddk.check.validation;

import java.util.Collections;
import java.util.List;

import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.EValidatorRegistrar;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XUnaryOperation;


/**
 * Common infrastructure for formal parameter checking in check and checkcfg.
 */
public class FormalParameterCheckBase extends AbstractDeclarativeValidator {

  @Override
  public void register(final EValidatorRegistrar registrar) {
    // do nothing
  }

  /**
   * Validates that all XNumberLiterals in this expression, which occurs on the right-hand side of a formal parameter
   * declaration/definition, have indeed integral values.
   * 
   * @param value
   *          to check
   * @param issueCode
   *          to issue if the validation fails
   */
  protected void checkRightHandSideHasOnlyIntegralNumbers(final XExpression value, final String issueCode) {
    if (value != null) {
      List<XExpression> exprs = (value instanceof XListLiteral) ? ((XListLiteral) value).getElements() : Collections.singletonList(value);
      for (XExpression expr : exprs) {
        XExpression e = expr;
        while (e instanceof XUnaryOperation) {
          e = ((XUnaryOperation) e).getOperand();
        }
        if (e instanceof XNumberLiteral) {
          try {
            Integer.decode(((XNumberLiteral) e).getValue());
          } catch (NumberFormatException ex) {
            error("Only integral values as numbers are allowed in check parameters", expr, null, issueCode); // TODO: NLS
          }
        }
      }
    }
  }

}

