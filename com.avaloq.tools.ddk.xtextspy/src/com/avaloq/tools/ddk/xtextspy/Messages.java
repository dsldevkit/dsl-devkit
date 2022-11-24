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
package com.avaloq.tools.ddk.xtextspy;

import org.eclipse.osgi.util.NLS;


//CHECKSTYLE:OFF
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.xtextspy.messages"; //$NON-NLS-1$
  public static String GrammarView_GrammarLabel;
  public static String GrammarView_NodeLabel;
  public static String GrammarView_OffsetLabel;
  public static String GrammarView_RuleLabel;
  public static String SpyViewPart_GroupByEClass;
  public static String SpyViewPart_GroupByEClassTT;
  public static String SpyViewPart_GroupByFeature;
  public static String SpyViewPart_GroupByFeatureTT;
  public static String SpyViewPart_LimitToEClass;
  public static String SpyViewPart_LimitToEClassTT;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}
