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
package com.avaloq.tools.ddk.xtext.ui.editor.model;

import java.util.Iterator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;


/**
 * The custom Xtext document-provider.
 */
@Singleton
public class ResponsiveXtextDocumentProvider extends XtextDocumentProvider implements IPropertyChangeListener {
  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String languageName;

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(ResponsiveXtextDocumentProvider.class);

  /** The preferenceStore. */
  private IPreferenceStore preferenceStore;

  /**
   * Obtain the preference store for this validation provider. It is the convention that the language name is used as preference
   * store identifier
   *
   * @return a ScopedPreference store, based on an instance scope.
   */
  protected IPreferenceStore getPreferenceStore() {
    if (preferenceStore == null) {
      preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, languageName);
    }
    return preferenceStore;
  }

  /**
   * {@inheritDoc} Moreover, the created documents are registered so that preference changes can be applied to them.
   */
  @Override
  protected void connected() {
    getPreferenceStore().addPropertyChangeListener(this);
  }

  /**
   * {@inheritDoc} Moreover, the created documents are unregistered so that preference changes do not apply to them anymore.
   */
  @Override
  protected void disconnected() {
    getPreferenceStore().removePropertyChangeListener(this);
  }

  /**
   * Performs (schedule) a "fast-only" validation job on preference changes.
   *
   * @param event
   *          the event
   */
  @Override
  public void propertyChange(final PropertyChangeEvent event) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(NLS.bind("Preference Change: {0} => {1} -> {2}", new Object[] {event.getProperty(), event.getOldValue(), event.getNewValue()})); //$NON-NLS-1$
    }

    for (Iterator<?> i = getConnectedElements(); i.hasNext();) {
      ((XtextDocument) getDocument(i.next())).checkAndUpdateAnnotations();
    }
  }

}

