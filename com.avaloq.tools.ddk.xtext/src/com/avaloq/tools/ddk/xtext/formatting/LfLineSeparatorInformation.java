/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.formatting;

import org.eclipse.xtext.formatting.ILineSeparatorInformation;


/**
 * Fixes the generated-file line separator to LF ({@code \n}).
 * <p>
 * Binding this in a language's <em>runtime</em> module makes Xtext's
 * {@code IFilePostProcessor} ({@code LineSeparatorHarmonizer}, or the trace-preserving
 * {@code TraceAwarePostProcessor} for Xbase languages) use LF for
 * {@code IFileSystemAccess} text writes in headless builds, independently of the host's
 * default line separator.
 * In the IDE, the preference-based {@code IWhitespaceInformationProvider} controls writes
 * with a resource URI; its fallback for a missing resource URI uses this binding.
 * </p>
 *
 * @since 17.4
 */
public class LfLineSeparatorInformation implements ILineSeparatorInformation {

  @Override
  public String getLineSeparator() {
    return "\n"; //$NON-NLS-1$
  }

}
