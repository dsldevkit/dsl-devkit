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
package com.avaloq.tools.ddk.check.runtime.validation;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.GeneratedMetamodel;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * Default validator implementation which registers the validator using the LANGUAGE_NAME as injected by Guice and all its generated EPackages.
 */
public class DefaultCheckValidator extends AbstractCheckValidator {

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String hostLanguage;

  @Inject(optional = true)
  private IGrammarAccess grammarAccess;

  @Override
  protected String getHostLanguage() {
    return hostLanguage;
  }

  /**
   * Override with empty implementation since indirectly referenced {@link #grammarAccess} has not been injected at this point. {@inheritDoc}
   */
  @Override
  public void register(final EValidatorRegistrar registrar) {
  }

  /**
   * At this point, {@link #grammarAccess} has been injected. See {@link #register(EValidatorRegistrar)}.
   *
   * @param registrar
   *          the validator registrar
   */
  @Inject
  public void register2(final EValidatorRegistrar registrar) {
    super.register(registrar);
  }

  @Override
  protected List<EPackage> getEPackages() {
    List<EPackage> result = Lists.newArrayList();
    if (grammarAccess != null) {
      for (AbstractMetamodelDeclaration decl : grammarAccess.getGrammar().getMetamodelDeclarations()) {
        // TODO what about imported meta models?
        if (decl instanceof GeneratedMetamodel) {
          result.add(EPackage.Registry.INSTANCE.getEPackage(decl.getEPackage().getNsURI()));
        }
      }
    }
    return result;
  }

}
