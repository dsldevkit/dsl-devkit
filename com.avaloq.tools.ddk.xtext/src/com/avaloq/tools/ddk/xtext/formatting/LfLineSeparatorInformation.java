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
 * Generated files are machine-owned, so their line endings must be deterministic across
 * platforms; git stores text blobs as LF, making LF output byte-stable against the
 * repository in every checkout configuration. Binding this in a language's <em>runtime</em>
 * module makes Xtext's {@code IFilePostProcessor} ({@code LineSeparatorHarmonizer}, or the
 * trace-preserving {@code TraceAwarePostProcessor} for Xbase languages) target LF for
 * {@code IFileSystemAccess} text writes in headless builds, replacing the
 * platform-dependent {@code System.lineSeparator()} default. In the IDE the UI module's
 * preference/sensing-based {@code IWhitespaceInformationProvider} takes precedence for
 * file writes, so workspaces keep converging to the checked-out form.
 * </p>
 */
public class LfLineSeparatorInformation implements ILineSeparatorInformation {

  @Override
  public String getLineSeparator() {
    return "\n"; //$NON-NLS-1$
  }

}
