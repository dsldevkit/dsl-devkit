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
package com.avaloq.tools.ddk.check.runtime.validation;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.validation.AbstractInjectableValidator;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * The Class AbstractCheckValidator.
 */
public abstract class AbstractCheckValidator extends AbstractInjectableValidator {

  private static final String VALIDATORS_CONTEXT_KEY = "CHECK_VALIDATORS"; //$NON-NLS-1$

  private static final Logger LOGGER = LogManager.getLogger(AbstractCheckValidator.class);

  @Inject
  private Injector injector;

  /**
   * Returns Xtext grammar of the host language (e.g. <em>org.eclipse.xtext.example.domainmodel.Domainmodel</em>).
   * 
   * @return the Xtext grammar ID
   */
  protected abstract String getHostLanguage();

  /**
   * Returns all validators for the language defined by {@link #getHostLanguage()}.
   * 
   * @param context
   *          validation context
   * @param eObject
   *          the object to be validated
   * @return a collection of validators
   */
  public Iterable<? extends ICheckValidatorImpl> getValidators(final Map<Object, Object> context, final EObject eObject) {
    @SuppressWarnings("unchecked")
    Iterable<? extends ICheckValidatorImpl> result = (Iterable<? extends ICheckValidatorImpl>) context.get(VALIDATORS_CONTEXT_KEY);
    if (result == null) {
      result = internalCollectValidators(getHostLanguage(), eObject);
      context.put(VALIDATORS_CONTEXT_KEY, result);
    }
    return result;
  }

  /**
   * Internal collect validators.
   * 
   * @param language
   *          the language
   * @param object
   *          the object to be validated
   * @return the iterable
   */
  protected Iterable<? extends ICheckValidatorImpl> internalCollectValidators(final String language, final EObject object) {
    if (language == null) {
      throw new IllegalArgumentException("Input language cannot be null"); //$NON-NLS-1$
    } else if (injector == null) {
      LOGGER.debug(NLS.bind("No injector found for {0}. Could not inject registered validators.", language)); //$NON-NLS-1$
    }

    final List<ICheckValidatorImpl> result = Lists.newArrayList();
    Collection<ICheckValidatorImpl> registered = ICheckValidatorRegistry.INSTANCE.getValidators(language);
    if (registered != null && !registered.isEmpty()) {
      for (ICheckValidatorImpl validator : registered) {
        try {
          if (injector != null) {
            injector.injectMembers(validator);
          }
          result.add(validator);
          // CHECKSTYLE:OFF
        } catch (Exception e) {
          // CHECKSTYLE:ON
          LOGGER.error("failed to inject validator " + validator, e); //$NON-NLS-1$
        }
      }
    }

    return result;
  }

  @Override
  protected boolean internalValidate(final EClass eClass, final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
    boolean result = true;
    for (ICheckValidatorImpl v : getValidators(context, eObject)) {
      result &= v.validate(eClass, eObject, diagnostics, context);
    }
    return result;
  }

}
