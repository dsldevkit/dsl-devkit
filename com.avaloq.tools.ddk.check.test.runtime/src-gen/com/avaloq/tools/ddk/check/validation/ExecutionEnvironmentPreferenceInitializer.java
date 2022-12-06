package com.avaloq.tools.ddk.check.validation;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

@SuppressWarnings("all")
public class ExecutionEnvironmentPreferenceInitializer extends AbstractPreferenceInitializer {
  private static final String RUNTIME_NODE_NAME = "com.avaloq.tools.ddk.check.test.runtime";

  public static final String GET_GREETING_NAME_LENGTH_DEFAULT_NAME_DEFAULT = "Franz";

  @Override
  public void initializeDefaultPreferences() {
    IEclipsePreferences preferences = org.eclipse.core.runtime.preferences.InstanceScope.INSTANCE.getNode(RUNTIME_NODE_NAME);
    
    initializeSeverities(preferences);
    initializeFormalParameters(preferences);
  }

  private void initializeSeverities(final IEclipsePreferences preferences) {
    preferences.putInt("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.EXECUTIONENVIRONMENTISSUECODES.GREETING.NAME.LENGTH$SEVERITY", 0);
    
  }

  private void initializeFormalParameters(final IEclipsePreferences preferences) {
    preferences.put("COM.AVALOQ.TOOLS.DDK.CHECK.VALIDATION.EXECUTIONENVIRONMENTISSUECODES.GREETING.NAME.LENGTH.DEFAULTNAME$PARAMETER", GET_GREETING_NAME_LENGTH_DEFAULT_NAME_DEFAULT);
    
  }
}
