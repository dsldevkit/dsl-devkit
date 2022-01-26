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
package com.avaloq.tools.ddk.xtext.ui.editor.templates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.text.templates.persistence.TemplateReaderWriter;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.Constants;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;


/**
 * A collection of templates contributed from preference.ini via xml files.
 */
@Singleton
@SuppressWarnings("deprecation")
public class ConfigurableTemplateStore extends TemplateStore {

  private static final Logger LOG = Logger.getLogger(ConfigurableTemplateStore.class);
  private final URL res;
  private final IPreferenceStore preferenceStore;
  private final String key;

  @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
  @Inject
  public ConfigurableTemplateStore(final ContextTypeRegistry registry, final IPreferenceStore store, @Named(Constants.LANGUAGE_NAME) final String key, final AbstractUIPlugin plugin) {
    super(registry, store, key + ".templates"); //$NON-NLS-1$
    this.res = getTemplateFileURL(plugin);
    this.preferenceStore = store;
    this.key = key + ".sharedTemplates"; //$NON-NLS-1$
    try {
      load();
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    }
  }

  /**
   * Returns the URL of the templates.xml file from given plugin.
   *
   * @param plugin
   *          plugin from where template.xml will be loaded
   * @return URL of the xml file with templates
   */
  protected URL getTemplateFileURL(final AbstractUIPlugin plugin) {
    return plugin.getBundle().getEntry("templates/templates.xml"); //$NON-NLS-1$
  }

  @Override
  protected void loadContributedTemplates() throws IOException {
    if (preferenceStore != null) {
      String pref = preferenceStore.getString(key);
      if (pref != null && !StringUtils.isBlank(pref)) {
        URL sharedTemplate = new File(pref).toURI().toURL();
        addTemplatesFromFile(sharedTemplate);
      }
    }
    addTemplatesFromFile(res);
  }

  /**
   * Contribute templates defined in file with the give URL.
   *
   * @param templates
   *          the URL of the file with templates
   */
  private void addTemplatesFromFile(final URL templates) {
    if (templates != null) {
      TemplateReaderWriter reader = new TemplateReaderWriter();
      try {
        InputStream openStream = templates.openStream();
        try {
          TemplatePersistenceData[] datas = reader.read(openStream, null);
          int templateCounter = 0;
          for (TemplatePersistenceData data : datas) {
            if (data.getId() == null) {
              templateCounter++;
              TemplatePersistenceData dataWithGenId = new TemplatePersistenceData(data.getTemplate(), data.isEnabled(), templates.getPath() + "." //$NON-NLS-1$
                  + templateCounter);
              dataWithGenId.setDeleted(data.isDeleted());
              internalAdd(dataWithGenId);
            } else {
              // if contributed template has an id
              internalAdd(data);
            }
          }
        } finally {
          openStream.close();
        }
      } catch (IOException e) {
        LOG.error(e);
      }
    }
  }

  @Override
  protected void handleException(final IOException x) {
    LOG.error(x.getMessage(), x);
  }

}
