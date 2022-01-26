/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Switch to disable generation for specified BuilderParticipants.
 */
@SuppressWarnings("nls")
public final class BuilderParticipantSettings {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(BuilderParticipantSettings.class);

  /**
   * BuilderParticipantStatus shows if generator is activated.
   */
  public enum BuilderParticipantStatus {
    ENABLED,
    DISABLED
  }

  private BuilderParticipantSettings() {
    // Prevent instantiation
  }

  /**
   * Determines the status of the BuilderParticipant.
   *
   * @param languageName
   *          the full name of the builder participant for which the status is checked
   * @return builder participant status as defined by a system property.
   */
  public static boolean isBuilderParticipantEnabled(final String languageName) {
    if (languageName == null) {
      return true;
    }
    BuilderParticipantStatus result = null;
    String value = System.getProperty(languageName, BuilderParticipantStatus.ENABLED.name()).trim().toUpperCase();
    try {
      result = BuilderParticipantStatus.valueOf(value);
    } catch (IllegalArgumentException ex) {
      LOGGER.error("Property " + languageName + " has an invalid value: " + value + " (should be one of enabled, disabled)");
      result = BuilderParticipantStatus.ENABLED;
    }
    return result == BuilderParticipantStatus.ENABLED;
  }
}
