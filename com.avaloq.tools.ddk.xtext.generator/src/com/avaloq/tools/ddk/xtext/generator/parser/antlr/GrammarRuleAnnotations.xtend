/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.parser.antlr

import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractRule

class GrammarRuleAnnotations {

  def static boolean hasNoBacktrackAnnotation(AbstractRule rule){
    return false
  }

  def static boolean hasNoBacktrackAnnotation(AbstractElement element){
    return false
  }

  def static boolean hasSemanticPredicate(AbstractElement element){
    return false
  }

  def static String generateSemanticPredicate(AbstractElement element){
    return ""
  }

  def static boolean isGatedPredicateRequired(AbstractElement element){
    return false;
  }

  def static String generateGatedPredicate(AbstractElement element){
    return "";
  }
}
