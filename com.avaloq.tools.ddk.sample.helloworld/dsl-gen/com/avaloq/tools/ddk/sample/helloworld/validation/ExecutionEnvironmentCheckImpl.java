package com.avaloq.tools.ddk.sample.helloworld.validation;

import com.avaloq.tools.ddk.check.runtime.issue.DefaultCheckImpl;
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.Greeting;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

/**
 * Validator for ExecutionEnvironment.
 */
@SuppressWarnings("all")
public class ExecutionEnvironmentCheckImpl extends DefaultCheckImpl {
  @Inject
  private ExecutionEnvironmentCheckCatalog executionEnvironmentCatalog;
  
  public String getQualifiedCatalogName() {
    return "com.avaloq.tools.ddk.sample.helloworld.validation.ExecutionEnvironment";
  }
  
  public final ImmutableMap<String, String> getIssueCodeToLabelMap() {
    return ExecutionEnvironmentCheckCatalog.getIssueCodeToLabelMap();
  }
  
  private class GreetingNameLengthClass {
    
    public void runGreeting(final Greeting g) {
      int _length = g.getName().length();
      boolean _greaterThan = (_length > 5);
      if (_greaterThan) {
        
        // Issue diagnostic
        executionEnvironmentCatalog.accept(getMessageAcceptor(), //
          g, // context EObject
          null, // EStructuralFeature
          executionEnvironmentCatalog.getGreetingNameLengthMessage("too long"), // Message
          executionEnvironmentCatalog.getGreetingNameLengthSeverityKind(g), // Severity 
          ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
          ExecutionEnvironmentIssueCodes.NAMELENGTH, null // Issue code & data
        );
      } else {
        boolean _equals = g.getName().equals(executionEnvironmentCatalog.getGreetingNameLength_DefaultName(g));
        if (_equals) {
          
          // Issue diagnostic
          executionEnvironmentCatalog.accept(getMessageAcceptor(), //
            g, // context EObject
            null, // EStructuralFeature
            executionEnvironmentCatalog.getGreetingNameLengthMessage("must not be Franz"), // Message
            executionEnvironmentCatalog.getGreetingNameLengthSeverityKind(g), // Severity 
            ValidationMessageAcceptor.INSIGNIFICANT_INDEX, // Marker index
            ExecutionEnvironmentIssueCodes.FRANZNAME, null // Issue code & data
          );
        }
      }
    }
  }
  
  private ExecutionEnvironmentCheckImpl.GreetingNameLengthClass greetingNameLengthImpl = new GreetingNameLengthClass();
  
  /**
   * greetingNameLengthGreeting.
   */
  @Check(CheckType.NORMAL)
  public void greetingNameLengthGreeting(final Greeting context) {
    greetingNameLengthImpl.runGreeting(context);
  }
}
