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
package com.avaloq.tools.ddk.xtext.format.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;

import com.avaloq.tools.ddk.xtext.format.ui.builder.FormatBuilderParticipant;
import com.avaloq.tools.ddk.xtext.format.ui.hyperlinking.FormatHyperlinkHelper;
import com.avaloq.tools.ddk.xtext.format.ui.typing.FormatJdtTypeProviderFactory;


/**
 * Use this class to register components to be used within the IDE.
 */
public class FormatUiModule extends AbstractFormatUiModule {
  public FormatUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  /**
   * Bind hyperlink helper to provide hyperlinking from "override" keyword to extended rule.
   *
   * @return FormatHyperlinkHelper.class
   */
  @Override
  public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
    return FormatHyperlinkHelper.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<? extends IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
    return FormatBuilderParticipant.class;
  }

  /**
   * Binds a JDT type provider factory capable of creating custimized providers for format language, that are able to refer to model types from local project.
   *
   * @return the JDT type provider factory
   */
  public Class<? extends JdtTypeProviderFactory> bindJdtTypeProviderFactory() {
    return FormatJdtTypeProviderFactory.class;
  }

}
