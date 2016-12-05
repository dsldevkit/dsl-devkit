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
package com.avaloq.tools.ddk.check.runtime.ui.editor;

import java.io.InputStream;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.ui.editor.LanguageSpecificURIEditorOpener;
import org.eclipse.xtext.ui.editor.XtextReadonlyEditorInput;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;

import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * {@link org.eclipse.xtext.ui.editor.IURIEditorOpener URI editor opener} capable of dealing
 * with <em>platform plugin</em> URIs. Opens content with platform plugin URIs in read-only
 * editors.
 */
public class PlatformPluginAwareEditorOpener extends LanguageSpecificURIEditorOpener {

  /** Bundle resource URL protocol. */
  public static final String OSGI_RESOURCE_URL_PROTOCOL = "bundleresource"; //$NON-NLS-1$

  private static final Logger LOG = Logger.getLogger(PlatformPluginAwareEditorOpener.class);

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String editorID;

  @Inject(optional = true)
  private IWorkbench workbench;

  /**
   * If a platform plugin URI is given, a read-only Xtext editor is opened and returned. {@inheritDoc}
   *
   * @see {@link org.eclipse.emf.common.util.URI#isPlatformPlugin()}
   */
  @Override
  public IEditorPart open(final URI uri, final EReference crossReference, final int indexInList, final boolean select) {
    IEditorPart result = super.open(uri, crossReference, indexInList, select);
    if (result == null && (uri.isPlatformPlugin() || OSGI_RESOURCE_URL_PROTOCOL.equals(uri.scheme()))) {
      final IModelLocation modelLocation = getModelLocation(uri.trimFragment());
      if (modelLocation != null) {
        PlatformPluginStorage storage = new PlatformPluginStorage(modelLocation);
        IEditorInput editorInput = new XtextReadonlyEditorInput(storage);
        IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
        try {
          IEditorPart editor = IDE.openEditor(activePage, editorInput, editorID);
          selectAndReveal(editor, uri, crossReference, indexInList, select);
          return EditorUtils.getXtextEditor(editor);
        } catch (WrappedException e) {
          LOG.error("Error while opening editor part for EMF URI '" + uri + "'", e.getCause()); //$NON-NLS-1$ //$NON-NLS-2$
        } catch (PartInitException partInitException) {
          LOG.error("Error while opening editor part for EMF URI '" + uri + "'", partInitException); //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
    }
    return result;
  }

  /**
   * Gets the model location for a given org.eclipse.emf.common.util.URI or {@code null} if none could be determined,
   * meaning that no registered model location with a catalog java.net.URI equal to given org.eclipse.emf.common.util.URI
   * was found in the catalog registry.
   *
   * @param uri
   *          the org.eclipse.emf.common.util.URI
   * @return the model location
   */
  private IModelLocation getModelLocation(final URI uri) {
    try {
      return Iterables.find(ICheckCatalogRegistry.INSTANCE.getAllCheckModelLocations(), new Predicate<IModelLocation>() {
        @Override
        public boolean apply(final IModelLocation input) {
          return input.getCatalogUri().toString().equals(uri.toString());
        }
      });
    } catch (NoSuchElementException e) {
      LOG.error("Could not find a model location for URI " + uri); //$NON-NLS-1$
      return null;
    }
  }

  /**
   * Defines an editor storage providing read-only access in an Xtext editor.
   */
  public static class PlatformPluginStorage implements IStorage {

    @Inject(optional = true)
    private IWorkbench workbench;
    private final IModelLocation location;

    public PlatformPluginStorage(final IModelLocation location) {
      this.location = location;
    }

    /** {@inheritDoc} */
    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") final Class adapter) {
      return null;
    }

    /**
     * Gets the name of the project in which the currently active editor's resource is contained.
     *
     * @return the project name
     */
    private String getProjectName() {
      try {
        final IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {
          IEditorPart editorPart = activeWorkbenchWindow.getActivePage().getActiveEditor();
          if (editorPart != null) {
            IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
            IProject activeProject = input.getFile().getProject();
            return activeProject.getName();
          }
        }
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        // CHECKSTYLE:ON
      }
      return "<unknown plugin>"; //$NON-NLS-1$
    }

    /** {@inheritDoc} */
    @Override
    public InputStream getContents() throws CoreException {
      try {
        return location.getCatalogStream();
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        throw new CoreException(new Status(IStatus.ERROR, getProjectName(), e.getMessage())); // NOPMD
        // CHECKSTYLE:ON
      }
    }

    @Override
    public IPath getFullPath() {
      return new Path(location.getCatalogPath());
    }

    @Override
    public String getName() {
      return URI.createURI(location.getCatalogUri().toString(), true).trimFragment().lastSegment();
    }

    @Override
    public boolean isReadOnly() {
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31; // NOPMD
      int result = 1;
      result = prime * result + ((location.getCatalogUri() == null) ? 0 : location.getCatalogUri().hashCode());
      return result;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      PlatformPluginStorage other = (PlatformPluginStorage) obj;
      if (location == null) {
        if (other.location != null) {
          return false;
        }
      } else if (!location.getCatalogUri().equals(other.location.getCatalogUri())) {
        return false;
      }
      return true;
    }
  }

}

