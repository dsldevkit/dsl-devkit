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
package com.avaloq.tools.ddk.xtext.ui.validation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;


/**
 * This manager class handles the extensions to plug-in extension point <code>ch.paranor.au.xtext.valid.core.valid</code>,
 * i.e. proxies for the Java extensions at first, then, after resolution, the actual
 * extension objects themselves.
 * Extension-point description:
 * A set of rules (in category) for a given Xtext language
 *
 * @see ValidExtensionPointManager.ValidExtension
 */
public final class ValidExtensionPointManager {

  private static final String UNKNOWN_EXTENSION_POINT = "Unknown extension point '{0}.{1}'"; //$NON-NLS-1$
  public static final String PLUGIN_ID = "com.avaloq.tools.ddk.xtext.ui"; //$NON-NLS-1$
  public static final String EXTENSION_POINT_NAME = "valid"; //$NON-NLS-1$

  private ValidExtensionPointManager() {
    // does nothing but keep Checkstyle happy!
  }

  /**
   * Returns proxies for all registered extensions; does not cause the extension plug-ins to be loaded.
   * The proxies contain -- and can therefore be queried for -- all the XML attribute values that the
   * extensions provide in their <i>plugin.xml</i> file. Throws IllegalArgumentException
   * if extension point is unknown
   *
   * @return array of proxies
   */
  public static ValidExtension[] getExtensions() {
    IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(PLUGIN_ID, EXTENSION_POINT_NAME);
    if (point == null) {
      throw new IllegalArgumentException(MessageFormat.format(UNKNOWN_EXTENSION_POINT, PLUGIN_ID, EXTENSION_POINT_NAME));
    }
    IExtension[] extensions = point.getExtensions();
    List<ValidExtension> found = new ArrayList<ValidExtension>();
    for (IExtension e : extensions) {
      ValidExtension obj = ValidExtension.parseExtension(e);
      if (obj != null) {
        found.add(obj);
      }
    }
    return found.toArray(new ValidExtension[found.size()]);
  }

}
