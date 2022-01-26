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
package com.avaloq.tools.ddk.check.runtime.context;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;


/**
 * Abstract implementation of {@link ICheckContext}.
 * Reflectively decides whether an issue code is valid in a certain context by applying an issue code annotated predicate.
 * An issue code is valid only if it is not disabled by any possible predicate.
 */
public class AbstractCheckContext implements ICheckContext {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(AbstractCheckContext.class);

  /** A constant that easily reads that this check should be enabled. */
  public static final boolean ENABLED = true;
  /** A constant that easily reads that this check should be disabled. */
  public static final boolean DISABLED = false;

  /** Maps issue codes to the predicate methods that contain that issue code in their annotation. */
  private Multimap<String, Method> predicatesForIssueCode;

  /** {@inheritDoc} */
  @Override
  public boolean isCheckValid(final EObject context, final String issueCode) {
    if (predicatesForIssueCode == null) {
      populatePredicatesForIssueCode();
    }
    return checkIssueCodePredicates(context, issueCode);

  }

  /**
   * Uses the precomputed predicate cache to check if an issue code should be disabled by any of the associated predicates.
   *
   * @param context
   *          the context object to apply the predicate to, must not be {@code null}
   * @param issueCode
   *          the issue code to check in this context, must not be {@code null}
   * @return
   *         false if any predicate associated with this issue code wishes it disabled; true otherwise
   */
  private boolean checkIssueCodePredicates(final EObject context, final String issueCode) {
    Collection<Method> methods = predicatesForIssueCode.get(issueCode);
    boolean valid = true;
    if (methods != null) {
      for (Method method : methods) {
        try {
          valid = valid && (Boolean) method.invoke(this, context); // A validation is only enabled if it is not disabled in *any* contexts.
          // CHECKSTYLE:CHECK-OFF IllegalCatch We really want to catch anything here. The method invoked is user-written code that may fail arbitrarily.
          // We will log the failure, remove the method, and assume that this issue can be executed. Any failure of the check will be
          // caught and disabled by the usual check infrastructure.
        } catch (Exception e) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          LOGGER.error("Failed to execute predicate " + method.getName() + " for issue code " + issueCode + ". Removing predicate for this issue code.", e); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
          predicatesForIssueCode.remove(issueCode, method);
        }
      }

    }
    return valid;
  }

  /**
   * Populates the {@link predicatesForIssueCode} map.
   */
  private void populatePredicatesForIssueCode() {
    predicatesForIssueCode = HashMultimap.create();
    Method[] methods = this.getClass().getDeclaredMethods();
    for (Method method : methods) {
      Set<String> elements = new HashSet<String>();
      if (method.getParameterTypes().length == 1 && boolean.class.equals(method.getReturnType())
          && EObject.class.isAssignableFrom(method.getParameterTypes()[0])) {
        CheckContext info = method.getAnnotation(CheckContext.class);
        if (info != null) {
          elements = getIssueCodes(info.issueCodes());
        }
      }
      for (String element : elements) {
        predicatesForIssueCode.put(element, method);
      }
    }
  }

  /**
   * For now simply converts issue codes array to a set. Could perform transformation in future
   *
   * @param ids
   *          Array of constants defining issue codes
   * @return
   *         Set of constants defining issue codes
   */
  private Set<String> getIssueCodes(final String... ids) {
    return Sets.newHashSet(ids);
  }

}
