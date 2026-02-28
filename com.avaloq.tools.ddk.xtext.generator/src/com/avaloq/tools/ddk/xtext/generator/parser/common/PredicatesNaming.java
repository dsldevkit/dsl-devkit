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

package com.avaloq.tools.ddk.xtext.generator.parser.common;

import com.google.inject.Inject;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;

public class PredicatesNaming {

  @Inject
  private XtextGeneratorNaming naming;

  public String getSemanticPredicatesFullName(final Grammar grammar) {
    return getSemanticPredicatesPackageName(grammar) + "." + getSemanticPredicatesSimpleName(grammar);
  }

  public String getSemanticPredicatesPackageName(final Grammar grammar) {
    return naming.getRuntimeBasePackage(grammar) + ".grammar";
  }

  public String getSemanticPredicatesSimpleName(final Grammar grammar) {
    return "Abstract" + GrammarUtil.getSimpleName(grammar) + "SemanticPredicates";
  }

}
