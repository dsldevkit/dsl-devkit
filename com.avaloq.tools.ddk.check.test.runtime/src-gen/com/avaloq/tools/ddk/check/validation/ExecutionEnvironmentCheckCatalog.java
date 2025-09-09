package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService;
import com.avaloq.tools.ddk.check.runtime.issue.AbstractIssue;
import com.avaloq.tools.ddk.check.runtime.issue.SeverityKind;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.emf.ecore.EObject;

/**
 * Issues for ExecutionEnvironment.
 */
@Singleton
@SuppressWarnings("all")
public class ExecutionEnvironmentCheckCatalog extends AbstractIssue {
  @Inject
  private ICheckConfigurationStoreService checkConfigurationStoreService;

  private static final ImmutableMap<String, String> issueCodeToLabelMap = ImmutableMap.<String, String>builderWithExpectedSize(2)
      .put(ExecutionEnvironmentIssueCodes.FRANZNAME, "Greeting name length")
      .put(ExecutionEnvironmentIssueCodes.NAMELENGTH, "Greeting name length")
      .build()
    ;

  /**
   * Get map of issue code to label for ExecutionEnvironment.
   * 
   * @returns Map of issue code to label for ExecutionEnvironment.
   * 
   */
  public static final ImmutableMap<String, String> getIssueCodeToLabelMap() {
    return issueCodeToLabelMap;
  }

  /**
   * Gets the run-time value of formal parameter <em>defaultName</em>. The value
   * returned is either the default as defined in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *            the context object used to determine the current project in
   *            order to check if a configured value exists in a project scope
   * @return the run-time value of <em>defaultName</em>
   */
  public String getGreetingNameLength_DefaultName(final EObject context) {
    return checkConfigurationStoreService.getCheckConfigurationStore(context).getString("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.EXECUTIONENVIRONMENTISSUECODES.GREETINGNAMELENGTH.DEFAULTNAME$PARAMETER", ExecutionEnvironmentPreferenceInitializer.GET_GREETING_NAME_LENGTH_DEFAULT_NAME_DEFAULT);
  }

  /**
   * Gets the message associated with a violation of this check.
   * 
   * @param bindings
   *           the message bindings
   * @return the message associated with a violation of this check
   */
  public String getGreetingNameLengthMessage(final Object... bindings) {
    return org.eclipse.osgi.util.NLS.bind("Greeting name {0}", bindings);
  }

  /**
   * Gets the {@link SeverityKind severity kind} of check
   * <em>Greeting name length</em>. The severity kind returned is either the
   * default ({@code ERROR}), as is set in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *          the context object used to determine the current project in
   *          order to check if a configured value exists in a project scope
   * @return the severity kind of this check: returns the default (ERROR) if
   *         no configuration for this check was found, else the configured
   *         value looked up in the configuration store
   */
  public SeverityKind getGreetingNameLengthSeverityKind(final EObject context) {
    final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.EXECUTIONENVIRONMENTISSUECODES.GREETINGNAMELENGTH$SEVERITY", 0);
    return SeverityKind.values()[result];
  }
}
