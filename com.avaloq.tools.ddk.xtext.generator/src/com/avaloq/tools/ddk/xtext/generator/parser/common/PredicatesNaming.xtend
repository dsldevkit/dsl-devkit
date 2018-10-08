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

package com.avaloq.tools.ddk.xtext.generator.parser.common

import com.google.inject.Inject
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming

class PredicatesNaming {

  @Inject extension XtextGeneratorNaming naming

  def String getSemanticPredicatesFullName(Grammar grammar) {
    return naming.getRuntimeBasePackage(grammar) + ".grammar." + getSemanticPredicatesSimpleName(grammar);
  }

  def String getSemanticPredicatesSimpleName(Grammar grammar) {
    return "Abstract" + GrammarUtil.getSimpleName(grammar) + "SemanticPredicates";
  }

}
