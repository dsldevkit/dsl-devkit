/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.xtext.generator.resourceFactory;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenationClient;

/**
 * Implementation that allows the fileExtensions for the fragment to be distinct from language.fileExtensions
 */
public class ResourceFactoryFragment2 extends AbstractXtextGeneratorFragment {

  private List<String> fileExtensions;

  @Inject
  private XtextGeneratorNaming _xtextGeneratorNaming;

  protected List<String> getFileExtensions() {
    return fileExtensions;
  }

  /**
   * Either a single file extension or a comma-separated list of extensions for which the language
   * shall be registered.
   */
  public void setFileExtensions(final String fileExtensions) {
    this.fileExtensions = Arrays.asList(fileExtensions.trim().split("\\s*,\\s*"));
  }

  // This is ResourceFactoryFragment2#generate with language.fileExtensions replaced by getFileExtensions
  @Override
  public void generate() {

    getLanguage().getRuntimeGenSetup().getRegistrations().add(new StringConcatenationClient() {
      @Override
      protected void appendTo(TargetStringConcatenation target) {
        target.append(TypeReference.typeRef(IResourceFactory.class));
        target.append(" resourceFactory = injector.getInstance(");
        target.append(TypeReference.typeRef(IResourceFactory.class));
        target.append(".class);");
        target.newLineIfNotEmpty();
        target.append(TypeReference.typeRef(IResourceServiceProvider.class));
        target.append(" serviceProvider = injector.getInstance(");
        target.append(TypeReference.typeRef(IResourceServiceProvider.class));
        target.append(".class);");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        for (final String fileExtension : getFileExtensions()) {
          target.append(TypeReference.typeRef(org.eclipse.emf.ecore.resource.Resource.class));
          target.append(".Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(\"");
          target.append(fileExtension);
          target.append("\", resourceFactory);");
          target.newLineIfNotEmpty();
          target.append(TypeReference.typeRef(IResourceServiceProvider.class));
          target.append(".Registry.INSTANCE.getExtensionToFactoryMap().put(\"");
          target.append(fileExtension);
          target.append("\", serviceProvider);");
          target.newLineIfNotEmpty();
        }
      }
    });

    if (getProjectConfig().getEclipsePlugin() != null && getProjectConfig().getEclipsePlugin().getPluginXml() != null) {
      final StringBuilder builder = new StringBuilder();
      builder.append("<!-- adding resource factories -->\n");
      for (final String fileExtension : getFileExtensions()) {
        builder.append("<extension\n");
        builder.append("  point=\"org.eclipse.emf.ecore.extension_parser\">\n");
        builder.append("  <parser\n");
        builder.append("    class=\"").append(_xtextGeneratorNaming.getEclipsePluginExecutableExtensionFactory(getGrammar())).append(":org.eclipse.xtext.resource.IResourceFactory\"\n");
        builder.append("    type=\"").append(fileExtension).append("\">\n");
        builder.append("  </parser>\n");
        builder.append("</extension>\n");
        builder.append("<extension point=\"org.eclipse.xtext.extension_resourceServiceProvider\">\n");
        builder.append("  <resourceServiceProvider\n");
        builder.append("    class=\"").append(_xtextGeneratorNaming.getEclipsePluginExecutableExtensionFactory(getGrammar())).append(":org.eclipse.xtext.ui.resource.IResourceUIServiceProvider\"\n");
        builder.append("    uriExtension=\"").append(fileExtension).append("\">\n");
        builder.append("  </resourceServiceProvider>\n");
        builder.append("</extension>\n");
      }
      getProjectConfig().getEclipsePlugin().getPluginXml().getEntries().add(builder.toString());
    }
  }

}

/* Copyright (c) Avaloq Group AG */
