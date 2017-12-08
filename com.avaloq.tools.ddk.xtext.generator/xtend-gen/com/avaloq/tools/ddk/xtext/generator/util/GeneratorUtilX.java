package com.avaloq.tools.ddk.xtext.generator.util;

import com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Grammar;

@SuppressWarnings("all")
public class GeneratorUtilX {
  public String indent(final Integer it) {
    return this.indent("  ", it);
  }
  
  public String indent(final String it, final Integer times) {
    String _xifexpression = null;
    if (((times).intValue() == 1)) {
      _xifexpression = it;
    } else {
      String _indent = this.indent(it, Integer.valueOf(((times).intValue() - 1)));
      _xifexpression = (it + _indent);
    }
    return _xifexpression;
  }
  
  public String xmlContributorComment(final String source) {
    return (("<!-- contributed by " + source) + " -->");
  }
  
  public String javaContributorComment(final String source) {
    return ("// contributed by " + source);
  }
  
  public String location(final EObject obj) {
    return GeneratorUtil.getLocation(obj);
  }
  
  public Set<EClass> allInstantiatedTypes(final Grammar it) {
    return GeneratorUtil.allInstantiatedTypes(it);
  }
  
  public boolean canContain(final EClass it, final Set<EClass> others, final Grammar g) {
    return GeneratorUtil.canContain(it, others, g);
  }
}
