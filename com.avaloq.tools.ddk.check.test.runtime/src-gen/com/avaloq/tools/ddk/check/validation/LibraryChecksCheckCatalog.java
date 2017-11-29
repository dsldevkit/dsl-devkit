package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService;
import com.avaloq.tools.ddk.check.runtime.issue.AbstractIssue;
import com.avaloq.tools.ddk.check.runtime.issue.SeverityKind;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.emf.ecore.EObject;

/**
 * Issues for LibraryChecks.
 */
@Singleton
@SuppressWarnings("all")
public class LibraryChecksCheckCatalog extends AbstractIssue {
  @Inject
  private ICheckConfigurationStoreService checkConfigurationStoreService;
  
  private final static ImmutableMap<String, String> issueCodeToLabelMap = ImmutableMap.<String, String>builder()
      .put(LibraryChecksIssueCodes.CACHE_DOESNT_WORK, "Cache doesn't work")
      .put(LibraryChecksIssueCodes.CACHE_INJECTION_FAILED, "Cache injection failed")
      .put(LibraryChecksIssueCodes.CHECK_CATALOG_IS_ACTIVE, "Check catalog is active")
      .put(LibraryChecksIssueCodes.FORMAL_PARAMETERS, "Formal Parameters")
      .build()
    ;
  
  /**
   * Get map of issue code to label for LibraryChecks.
   * 
   * @returns Map of issue code to label for LibraryChecks.
   * 
   */
  public static final ImmutableMap<String, String> getIssueCodeToLabelMap() {
    return issueCodeToLabelMap;
  }
  
  /**
   * Gets the message associated with a violation of this check.
   * 
   * @param bindings
   *           the message bindings
   * @return the message associated with a violation of this check
   */
  public String getCheckCatalogIsActiveMessage(final Object... bindings) {
    return org.eclipse.osgi.util.NLS.bind("Catalog is active", bindings);
  }
  
  /**
   * Gets the {@link SeverityKind severity kind} of check
   * <em>Check catalog is active</em>. The severity kind returned is either the
   * default ({@code WARNING}), as is set in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *          the context object used to determine the current project in
   *          order to check if a configured value exists in a project scope
   * @return the severity kind of this check: returns the default (WARNING) if
   *         no configuration for this check was found, else the configured
   *         value looked up in the configuration store
   */
  public SeverityKind getCheckCatalogIsActiveSeverityKind(final EObject context) {
    final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.CHECK.CATALOG.IS.ACTIVE$SEVERITY", 1);
    return SeverityKind.values()[result];
  }
  
  /**
   * Gets the message associated with a violation of this check.
   * 
   * @param bindings
   *           the message bindings
   * @return the message associated with a violation of this check
   */
  public String getCacheInjectionFailedMessage(final Object... bindings) {
    return org.eclipse.osgi.util.NLS.bind("Cache was not injected", bindings);
  }
  
  /**
   * Gets the {@link SeverityKind severity kind} of check
   * <em>Cache injection failed</em>. The severity kind returned is either the
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
  public SeverityKind getCacheInjectionFailedSeverityKind(final EObject context) {
    final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.CACHE.INJECTION.FAILED$SEVERITY", 0);
    return SeverityKind.values()[result];
  }
  
  /**
   * Gets the message associated with a violation of this check.
   * 
   * @param bindings
   *           the message bindings
   * @return the message associated with a violation of this check
   */
  public String getCacheDoesntWorkMessage(final Object... bindings) {
    return org.eclipse.osgi.util.NLS.bind("{0}", bindings);
  }
  
  /**
   * Gets the {@link SeverityKind severity kind} of check
   * <em>Cache doesn't work</em>. The severity kind returned is either the
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
  public SeverityKind getCacheDoesntWorkSeverityKind(final EObject context) {
    final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.CACHE.DOESNT.WORK$SEVERITY", 0);
    return SeverityKind.values()[result];
  }
  
  /**
   * Gets the run-time value of formal parameter <em>param1</em>. The value
   * returned is either the default as defined in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *            the context object used to determine the current project in
   *            order to check if a configured value exists in a project scope
   * @return the run-time value of <em>param1</em>
   */
  public String getFormalParameters_Param1(final EObject context) {
    return checkConfigurationStoreService.getCheckConfigurationStore(context).getString("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.PARAM1$PARAMETER", LibraryChecksPreferenceInitializer.GET_FORMAL_PARAMETERS_PARAM_1_DEFAULT);
  }
  
  /**
   * Gets the run-time value of formal parameter <em>param2</em>. The value
   * returned is either the default as defined in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *            the context object used to determine the current project in
   *            order to check if a configured value exists in a project scope
   * @return the run-time value of <em>param2</em>
   */
  public boolean getFormalParameters_Param2(final EObject context) {
    return checkConfigurationStoreService.getCheckConfigurationStore(context).getBoolean("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.PARAM2$PARAMETER", LibraryChecksPreferenceInitializer.GET_FORMAL_PARAMETERS_PARAM_2_DEFAULT);
  }
  
  /**
   * Gets the run-time value of formal parameter <em>param3</em>. The value
   * returned is either the default as defined in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *            the context object used to determine the current project in
   *            order to check if a configured value exists in a project scope
   * @return the run-time value of <em>param3</em>
   */
  public Boolean getFormalParameters_Param3(final EObject context) {
    return checkConfigurationStoreService.getCheckConfigurationStore(context).getBoolean("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.PARAM3$PARAMETER", LibraryChecksPreferenceInitializer.GET_FORMAL_PARAMETERS_PARAM_3_DEFAULT);
  }
  
  /**
   * Gets the run-time value of formal parameter <em>names</em>. The value
   * returned is either the default as defined in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *            the context object used to determine the current project in
   *            order to check if a configured value exists in a project scope
   * @return the run-time value of <em>names</em>
   */
  public List<String> getFormalParameters_Names(final EObject context) {
    return checkConfigurationStoreService.getCheckConfigurationStore(context).getStrings("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.NAMES$PARAMETER", LibraryChecksPreferenceInitializer.GET_FORMAL_PARAMETERS_NAMES_DEFAULT);
  }
  
  /**
   * Gets the run-time value of formal parameter <em>ints</em>. The value
   * returned is either the default as defined in the check definition, or the
   * configured value, if existing.
   * 
   * @param context
   *            the context object used to determine the current project in
   *            order to check if a configured value exists in a project scope
   * @return the run-time value of <em>ints</em>
   */
  public List<Integer> getFormalParameters_Ints(final EObject context) {
    return checkConfigurationStoreService.getCheckConfigurationStore(context).getIntegers("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.INTS$PARAMETER", LibraryChecksPreferenceInitializer.GET_FORMAL_PARAMETERS_INTS_DEFAULT);
  }
  
  /**
   * Gets the message associated with a violation of this check.
   * 
   * @param bindings
   *           the message bindings
   * @return the message associated with a violation of this check
   */
  public String getFormalParametersMessage(final Object... bindings) {
    return org.eclipse.osgi.util.NLS.bind("{0}", bindings);
  }
  
  /**
   * Gets the {@link SeverityKind severity kind} of check
   * <em>Formal Parameters</em>. The severity kind returned is either the
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
  public SeverityKind getFormalParametersSeverityKind(final EObject context) {
    final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS$SEVERITY", 0);
    return SeverityKind.values()[result];
  }
}
