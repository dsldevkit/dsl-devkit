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
package com.avaloq.tools.ddk.check.generator;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;

import com.avaloq.tools.ddk.check.runtime.validation.AbstractCheckValidator;
import com.avaloq.tools.ddk.check.runtime.validation.DefaultCheckValidator;


/**
 * This generator fragment supplies default bindings for languages using Static Code Analysis.
 */
public class CheckValidatorFragment extends AbstractGeneratorFragment {

  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    BindFactory factory = new BindFactory();

    factory.addTypeToTypeEagerSingleton(AbstractCheckValidator.class.getName(), DefaultCheckValidator.class.getName());
    // Uncomment once not conflicting with ValidValidatorFragment anymore
    // factory.addTypeToType(CompositeEValidator.class.getName(), CheckCompositeEValidator.class.getName()).getBindings();

    return factory.getBindings();
  }

  @Override
  public String[] getRequiredBundlesRt(final Grammar grammar) {
    return new String[] {"com.avaloq.tools.ddk.check.runtime.core"}; //$NON-NLS-1$ 
  }

}

