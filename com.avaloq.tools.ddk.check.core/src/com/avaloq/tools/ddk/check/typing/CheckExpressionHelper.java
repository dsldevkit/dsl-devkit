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
package com.avaloq.tools.ddk.check.typing;

import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.util.XExpressionHelper;

import com.avaloq.tools.ddk.check.check.XGuardExpression;
import com.avaloq.tools.ddk.check.check.XIssueExpression;


/**
 * Expression helper for Check. Required to mark certain expressions as having side effects.
 */
public class CheckExpressionHelper extends XExpressionHelper {

  @Override
  public boolean hasSideEffects(final XExpression expr) {
    if (expr instanceof XGuardExpression || expr instanceof XIssueExpression) {
      return true;
    }
    return super.hasSideEffects(expr);
  }

}
