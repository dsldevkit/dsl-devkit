package com.avaloq.tools.ddk.check.validation;

import java.util.Collections;
import java.util.List;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class LibraryChecksPreferenceInitializer extends AbstractPreferenceInitializer {
  private static final String RUNTIME_NODE_NAME = "com.avaloq.tools.ddk.check.test.runtime";
  
  public static final String GET_FORMAL_PARAMETERS_PARAM_1_DEFAULT = "param1";
  
  public static final boolean GET_FORMAL_PARAMETERS_PARAM_2_DEFAULT = (!(!true));
  
  public static final Boolean GET_FORMAL_PARAMETERS_PARAM_3_DEFAULT = Boolean.valueOf(false);
  
  public static final List<String> GET_FORMAL_PARAMETERS_NAMES_DEFAULT = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("foo", "bar", "ba\u0001\nz"));
  
  public static final List<Integer> GET_FORMAL_PARAMETERS_INTS_DEFAULT = Collections.<Integer>unmodifiableList(CollectionLiterals.<Integer>newArrayList(Integer.valueOf(5), Integer.valueOf((-42)), Integer.valueOf(7)));
  
  @Override
  public void initializeDefaultPreferences() {
    IEclipsePreferences preferences = org.eclipse.core.runtime.preferences.InstanceScope.INSTANCE.getNode(RUNTIME_NODE_NAME);
    
    initializeSeverities(preferences);
    initializeFormalParameters(preferences);
  }
  
  private void initializeSeverities(final IEclipsePreferences preferences) {
    preferences.putInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.CHECK.CATALOG.IS.ACTIVE$SEVERITY", 1);
    preferences.putInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.CACHE.INJECTION.FAILED$SEVERITY", 0);
    preferences.putInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.CACHE.DOESNT.WORK$SEVERITY", 0);
    preferences.putInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS$SEVERITY", 0);
    
  }
  
  private void initializeFormalParameters(final IEclipsePreferences preferences) {
    preferences.put("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.PARAM1$PARAMETER", GET_FORMAL_PARAMETERS_PARAM_1_DEFAULT);
    preferences.putBoolean("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.PARAM2$PARAMETER", GET_FORMAL_PARAMETERS_PARAM_2_DEFAULT);
    preferences.putBoolean("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.PARAM3$PARAMETER", GET_FORMAL_PARAMETERS_PARAM_3_DEFAULT);
    preferences.put("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.NAMES$PARAMETER", com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper.marshalStrings(GET_FORMAL_PARAMETERS_NAMES_DEFAULT));
    preferences.put("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.LIBRARYCHECKSISSUECODES.FORMAL.PARAMETERS.INTS$PARAMETER", com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper.marshalIntegers(GET_FORMAL_PARAMETERS_INTS_DEFAULT));
    
  }
}
