package com.avaloq.tools.ddk.xtext.expression.generator;

import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Grammar;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;

public class GeneratorUtilX {

  public String xmlContributorComment(final String source) {
    return "<!-- contributed by " + source + " -->";
  }

  public String javaContributorComment(final String source) {
    return "// contributed by " + source;
  }

  public String location(final EObject obj) {
    return EObjectUtil.getFileLocation(obj);
  }

  public Set<EClass> allInstantiatedTypes(final Grammar grammar) {
    return GeneratorUtil.allInstantiatedTypes(grammar);
  }

  public boolean canContain(final EClass eClass, final Set<EClass> others, final Grammar g) {
    return GeneratorUtil.canContain(eClass, others, g);
  }
}
