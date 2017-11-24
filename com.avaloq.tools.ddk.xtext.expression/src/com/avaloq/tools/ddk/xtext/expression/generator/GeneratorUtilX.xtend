package com.avaloq.tools.ddk.xtext.expression.generator

import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Grammar
import com.avaloq.tools.ddk.xtext.util.EObjectUtil

class GeneratorUtilX {

  def String xmlContributorComment(String source) {
    '<!-- contributed by ' + source + ' -->'
  }

  def String javaContributorComment(String source) {
    '// contributed by ' + source
  }

  def String location(EObject obj) {
    EObjectUtil.getFileLocation(obj)
  }

  def Set<EClass> allInstantiatedTypes(Grammar it) {
    GeneratorUtil.allInstantiatedTypes(it)
  }

  def boolean canContain(EClass it, Set<EClass> others, Grammar g) {
    GeneratorUtil.canContain(it, others, g)
  }
}