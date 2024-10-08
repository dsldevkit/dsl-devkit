package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.runtime.issue.DispatchingCheckImpl;
import com.avaloq.tools.ddk.check.testLanguage.Greeting;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

/**
 * Validator for ExecutionEnvironment.
 */
@SuppressWarnings("all")
public class ExecutionEnvironmentCheckImpl extends DispatchingCheckImpl {
  @Inject
  private ExecutionEnvironmentCheckCatalog executionEnvironmentCatalog;

  public String getQualifiedCatalogName() {
    return "com.avaloq.tools.ddk.check.validation.ExecutionEnvironment";
  }

  public final ImmutableMap<String, String> getIssueCodeToLabelMap() {
    return ExecutionEnvironmentCheckCatalog.getIssueCodeToLabelMap();
  }

  @Override
  public void validate(final CheckMode checkMode, final EObject object, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
    if (checkMode.shouldCheck(CheckType.NORMAL)) {
      diagnosticCollector.setCurrentCheckType(CheckType.NORMAL);
      if (object instanceof final com.avaloq.tools.ddk.check.testLanguage.Greeting castObject) {
        validate("greetingNameLengthGreeting", "ExecutionEnvironment.greetingNameLengthGreeting", object,
                 () -> greetingNameLengthGreeting(castObject, diagnosticCollector), diagnosticCollector);
      }
    }
  }

  private class GreetingNameLengthClass {

    public void runGreeting(final Greeting g, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
      int _length = g.getName().length();
      boolean _greaterThan = (_length > 5);
      if (_greaterThan) {
        
        // Issue diagnostic
        executionEnvironmentCatalog.accept(diagnosticCollector, //
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
          executionEnvironmentCatalog.accept(diagnosticCollector, //
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
  public void greetingNameLengthGreeting(final Greeting context, final DispatchingCheckImpl.DiagnosticCollector diagnosticCollector) {
    greetingNameLengthImpl.runGreeting(context, diagnosticCollector);
  }
}
