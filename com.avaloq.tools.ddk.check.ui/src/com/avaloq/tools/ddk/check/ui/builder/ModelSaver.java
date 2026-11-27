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
package com.avaloq.tools.ddk.check.ui.builder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.core.AbstractModel;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModelBase;
import org.eclipse.pde.internal.core.text.XMLEditingModel;


/**
 * Saves the PDE models of the Check builder only when that changes the content of their file, so that building an unchanged catalog causes no resource delta.
 */
@SuppressWarnings("restriction")
final class ModelSaver {

  private ModelSaver() {
    // static helpers only
  }

  /**
   * Saves an editing model unless its file already holds exactly the bytes {@link XMLEditingModel#save()} would write.
   *
   * @param model
   *          the model to save
   */
  static void saveIfChanged(final XMLEditingModel model) {
    if (model.getUnderlyingResource() instanceof IFile file && hasContents(file, model.getContents())) {
      return;
    }
    model.save();
  }

  /**
   * Saves a plug-in model unless its file already holds exactly the bytes {@link WorkspacePluginModelBase#save()} would write.
   *
   * @param model
   *          the model to save
   */
  static void saveIfChanged(final WorkspacePluginModelBase model) {
    IFile file = model.getFile();
    if (file != null && hasContents(file, AbstractModel.fixLineDelimiter(model.getContents(), file))) {
      return;
    }
    model.save();
  }

  /**
   * Whether the file exists and holds exactly the UTF-8 bytes of the given contents.
   *
   * @param file
   *          the file to read
   * @param contents
   *          the expected contents
   * @return {@code true} if the file holds exactly these contents, {@code false} if it differs, is missing or cannot be read
   */
  private static boolean hasContents(final IFile file, final String contents) {
    if (!file.exists()) {
      return false;
    }
    try (InputStream stream = file.getContents(true)) {
      return Arrays.equals(stream.readAllBytes(), contents.getBytes(StandardCharsets.UTF_8));
    } catch (CoreException | IOException e) {
      return false; // the save reports a file that cannot be accessed
    }
  }

}
