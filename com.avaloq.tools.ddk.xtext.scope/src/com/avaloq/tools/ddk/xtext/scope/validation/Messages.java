/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.scope.validation;

import org.eclipse.osgi.util.NLS;


// CHECKSTYLE:OFF
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.xtext.scope.validation.messages"; //$NON-NLS-1$
  public static String contextReferenceMustNotBeContainment;
  public static String referenceNotOwnedByType;
  public static String duplicatedScopeDefinition;
  public static String wrongScopeDefinitionSignature;
  public static String duplicatedScopeRule;
  public static String extensionNotFound;
  public static String overriddenInheritedScopeRule;
  public static String uriPatternFound;
  public static String typeMismatch;
  public static String missingNameFunction;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}
// CHECKSTYLE:ON
