package com.avaloq.tools.ddk.xtext.generator.util

import com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Grammar

class GeneratorUtilX {
  def String indent(Integer it) {
      indent("  ", it)
  }

  def String indent(String it, Integer times) {
      if (times == 1) it else it + indent(it, times - 1)
  }

  def String xmlContributorComment(String source) {
      '<!-- contributed by ' + source + ' -->'
  }

  def String javaContributorComment(String source) {
      '// contributed by ' + source
  }

  def String location(EObject obj) {
      GeneratorUtil.getLocation(obj)
  }

  def Set<EClass> allInstantiatedTypes(Grammar it) {
      GeneratorUtil.allInstantiatedTypes(it)
  }

  def boolean canContain(EClass it, Set<EClass> others, Grammar g) {
    GeneratorUtil.canContain(it, others, g)
  }
}