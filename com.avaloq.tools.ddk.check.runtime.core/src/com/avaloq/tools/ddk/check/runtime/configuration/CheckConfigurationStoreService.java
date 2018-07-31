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
package com.avaloq.tools.ddk.check.runtime.configuration;

import static org.eclipse.xtext.diagnostics.Diagnostic.LINKING_DIAGNOSTIC;
import static org.eclipse.xtext.diagnostics.Diagnostic.SYNTAX_DIAGNOSTIC;

import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;
import org.eclipse.xtext.validation.Issue;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;


/**
 * The default check configuration service. Gets a {@link ICheckConfigurationStore check
 * configuration store} for any given context object associated to an {@link IProject}.
 * <p>
 * Subclasses may control how current project is derived from given context object.
 * </p>
 */
public class CheckConfigurationStoreService implements ICheckConfigurationStoreService {

  private static final Logger LOGGER = Logger.getLogger(CheckConfigurationStoreService.class);

  protected static final Set<String> LANGUAGE_AGNOSTIC_DIAGNOSTICS = ImmutableSet.of(SYNTAX_DIAGNOSTIC, LINKING_DIAGNOSTIC);

  // CHECKSTYLE:OFF
  protected IProject project;
  // CHECKSTYLE:ON

  private ICheckConfigurationStore configurationStore;

  public CheckConfigurationStoreService() { // NOPMD
    // does nothing yet
  }

  /** {@inheritDoc} */
  @Override
  public ICheckConfigurationStore getCheckConfigurationStore(final Object context) {
    setProject(context);
    ICheckConfigurationStore store = internalGetCheckConfigurationStore(context);
    if (store instanceof CheckConfigurationStore) {
      // project can be null!
      ((CheckConfigurationStore) store).setProject(this.project);
    }
    String language = getLanguage(context);
    if (language != null) {
      return new LanguageSpecificCheckConfigurationStore(store, language);
    } else {
      return store;
    }
  }

  /**
   * Gets the language for the given object.
   *
   * @param context
   *          object
   * @return the language the corresponding resource was parsed from, may be {@code null}
   */
  private String getLanguage(final Object context) {
    if (context instanceof EObject) {
      Resource resource = ((EObject) context).eResource();
      if (resource instanceof LazyLinkingResource) {
        return ((LazyLinkingResource) resource).getLanguageName();
      }
    } else if (context instanceof Issue && !LANGUAGE_AGNOSTIC_DIAGNOSTICS.contains(((Issue) context).getCode())) {
      URI uri = ((Issue) context).getUriToProblem();
      if (uri != null) {
        Registry registry = IResourceServiceProvider.Registry.INSTANCE;
        IResourceServiceProvider resourceServiceProvider = registry.getResourceServiceProvider(uri);
        if (resourceServiceProvider != null) {
          return resourceServiceProvider.get(Injector.class).getInstance(Key.get(String.class, Names.named(Constants.LANGUAGE_NAME)));
        } else {
          LOGGER.error("Could not fetch a ResourceServiceProvider for URI: " + uri); //$NON-NLS-1$
        }
      } else {
        LOGGER.warn("Could not fetch eResource from issue: URI to problem is null"); //$NON-NLS-1$
      }
    }

    return null;
  }

  /**
   * Gets the check configuration store instance. Clients may override this method in order
   * to specify different implementations of stores depending on given context object.
   *
   * @param context
   *          the context object of the validation being executed
   * @return the configuration store instance
   */
  protected ICheckConfigurationStore internalGetCheckConfigurationStore(final Object context) {
    if (configurationStore == null) {
      configurationStore = new CheckConfigurationStore(DEFAULT_CHECK_CONFIGURATION_NODE);
    }
    return configurationStore;
  }

  /**
   * Sets the project if an associated instance can be found for given context object.
   * <p>
   * This is the default implementation. Only platform URI-based schemas are supported. Other implementations may overwrite {@link #getProject()}.
   * </p>
   *
   * @param context
   *          the context object, potentially contained by an IProject
   */
  protected void setProject(final Object context) {
    if (context instanceof IProject) {
      this.project = (IProject) context;
    } else if (context instanceof IFile) {
      this.project = ((IFile) context).getProject();
    } else {
      URI uri = null;
      if (context instanceof EObject && ((EObject) context).eResource() != null) {
        uri = ((EObject) context).eResource().getURI();
      }
      if (context instanceof Issue) {
        uri = ((Issue) context).getUriToProblem();
      }
      if (uri != null && uri.isPlatform()) {
        final IFile file = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(uri.toPlatformString(true));
        this.project = file.getProject();
      }
    }
  }
}
