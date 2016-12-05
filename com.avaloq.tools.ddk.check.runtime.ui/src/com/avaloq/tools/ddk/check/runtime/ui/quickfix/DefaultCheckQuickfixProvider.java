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
package com.avaloq.tools.ddk.check.runtime.ui.quickfix;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.ui.editor.quickfix.AbstractDeclarativeQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.check.runtime.quickfix.CoreFix;
import com.avaloq.tools.ddk.check.runtime.quickfix.CoreIssueResolution;
import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreIssueResolutionAcceptor;
import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreQuickfixProvider;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckQuickfixRegistry;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;


/**
 * The default Check quickfix provider is an extension of Xtext's {@link DefaultQuickfixProvider
 * default quickfix provider}. Main additions in this class are:
 * <ul>
 * <li>any number of providers can be used for providing quickfix resolutions
 * <li>providers may register using the Check quickfix extension point
 * <li>providers must implement the {@link com.avaloq.tools.ddk.check.runtime.quickfix.ICoreQuickfixProvider
 * ICoreQuickfixProvider} interface
 * <li>providers must not have any dependencies to UI plug-ins
 * </ul>
 * Behavior of Xtext's DefaultQuickfixProvider is unchanged.
 */
public class DefaultCheckQuickfixProvider extends DefaultQuickfixProvider {

  private static final Logger LOGGER = Logger.getLogger(DefaultQuickfixProvider.class);

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String hostLanguage;

  /** The default Check core issue resolution acceptor provider. */
  @Inject
  private Provider<ICoreIssueResolutionAcceptor2> coreIssueResolutionAcceptorProvider;

  /** The Xtext default issue resolution acceptor provider. */
  @Inject
  private Provider<IssueResolutionAcceptor> issueResolutionAcceptorProvider;

  /** Cache for the list of quickfix methods available per issue code. */
  private final Map<String, Iterable<Method>> issueCodeToQuickfixMethods = Maps.newHashMap();

  @Override
  public boolean hasResolutionFor(final String issueCode) {
    if (Diagnostic.LINKING_DIAGNOSTIC.equals(issueCode)) {
      return false;
    }
    return super.hasResolutionFor(issueCode);
  }

  /**
   * Gets <em>all</em> provider methods by examining the Check quickfix extension point's registered clients. Includes eventual
   * "fix methods" of current class.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected Iterable<Method> collectMethods(final Class<? extends AbstractDeclarativeQuickfixProvider> clazz, final String issueCode) {
    if (!issueCodeToQuickfixMethods.containsKey(issueCode)) {
      Iterable<Method> result = Lists.newArrayList(clazz.getMethods()); // Adds this class' methods: this class potentially is a quickfix provider
      for (final ICoreQuickfixProvider provider : getProviders()) {
        result = Iterables.concat(result, methodsForProvider(provider, issueCode));
      }
      issueCodeToQuickfixMethods.put(issueCode, Iterables.filter(result, getFixMethodPredicate(issueCode)));
    }
    return issueCodeToQuickfixMethods.get(issueCode);
  }

  /**
   * Returns a method predicate indicating whether a given method is an executable quickfix method. Both Check
   * quickfix methods and native quickfix methods are considered.
   *
   * @param issueCode
   *          the issue code
   * @return the fix method predicate
   * @see com.avaloq.tools.ddk.check.runtime.quickfix.CoreFix CoreFix annotation
   * @see org.eclipse.xtext.ui.editor.quickfix.Fix Fix annotation
   */
  @Override
  protected Predicate<Method> getFixMethodPredicate(final String issueCode) {
    return new Predicate<Method>() {
      @Override
      public boolean apply(final Method input) {
        CoreFix coreFixAnnotation = input.getAnnotation(CoreFix.class);
        Fix fixAnnotation = input.getAnnotation(Fix.class);

        if (coreFixAnnotation == null && fixAnnotation == null) {
          return false; // Definitely no candidate
        }

        final boolean typesMatch = Void.TYPE == input.getReturnType() && input.getParameterTypes().length == 2
            && input.getParameterTypes()[0].isAssignableFrom(Issue.class);

        boolean result;
        if (coreFixAnnotation != null) {
          result = coreFixAnnotation != null && issueCode.equals(coreFixAnnotation.value()) && typesMatch
              && input.getParameterTypes()[1].isAssignableFrom(CoreIssueResolutionAcceptor.class);
        } else {
          result = fixAnnotation != null && issueCode.equals(fixAnnotation.value()) && typesMatch
              && input.getParameterTypes()[1].isAssignableFrom(IssueResolutionAcceptor.class);
        }
        return result;
      }
    };
  }

  /**
   * Returns all fix methods for a given quickfix provider.
   *
   * @param provider
   *          the provider
   * @param issueCode
   *          the issue code
   * @return the fix methods
   */
  private Iterable<Method> methodsForProvider(final ICoreQuickfixProvider provider, final String issueCode) {
    final List<Method> result = Lists.newArrayList();
    result.addAll(Arrays.asList(provider.getClass().getMethods()));
    return Iterables.filter(result, getFixMethodPredicate(issueCode));
  }

  /**
   * Invokes quickfix methods and creates IssueResolution objects. Both "native" quickfix providers and Check
   * quickfix providers are considered.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected List<IssueResolution> getResolutions(final Issue issue, final List<Method> fixMethods) {
    final ICoreIssueResolutionAcceptor coreIssueResolutionAcceptor = coreIssueResolutionAcceptorProvider.get();
    final IssueResolutionAcceptor defaultIssueResolutionAcceptor = issueResolutionAcceptorProvider.get();

    for (Method fixMethod : fixMethods) {
      try {
        fixMethod.setAccessible(true);

        if (Lists.newArrayList(getClass().getMethods()).contains(fixMethod)) {
          if (fixMethod.getAnnotation(CoreFix.class) != null) {
            fixMethod.invoke(this, issue, coreIssueResolutionAcceptor);
          } else {
            // Legacy code: executes native quickfixes
            fixMethod.invoke(this, issue, defaultIssueResolutionAcceptor);
          }
        } else {
          // Check quickfixes: collect from Check quickfix providers
          // TODO optimize the following: it is required to find the provider on which to execute
          // the fix method
          for (final ICoreQuickfixProvider p : getProviders()) {
            if (Iterables.contains(methodsForProvider(p, issue.getCode()), fixMethod)) {
              fixMethod.invoke(p, issue, coreIssueResolutionAcceptor);
            }
          }
        }

        // CHECKSTYLE:OFF
      } catch (Exception e) {
        // CHECKSTYLE:ON
        LOGGER.error("Error executing fix method", e); //$NON-NLS-1$
      }
    }
    final Iterable<IssueResolution> allResolutions = Iterables.concat(Lists.transform(coreIssueResolutionAcceptor.getIssueResolutions(), new Function<CoreIssueResolution, IssueResolution>() {
      @Override
      public IssueResolution apply(final CoreIssueResolution from) {
        return new IssueResolutionWrapper(from);
      }
    }), defaultIssueResolutionAcceptor.getIssueResolutions());
    return Lists.newArrayList(allResolutions);
  }

  /**
   * Returns all Check quickfix providers for the language defined by {@link #getHostLanguage()}.
   *
   * @return a collection of quickfix providers
   */
  public Iterable<? extends ICoreQuickfixProvider> getProviders() {
    return internalCollectProviders(hostLanguage);
  }

  /**
   * Collect quickfix providers from registered plug-in extensions.
   *
   * @param language
   *          the language
   * @return the iterable
   */
  private Iterable<? extends ICoreQuickfixProvider> internalCollectProviders(final String language) {
    if (language == null) {
      throw new IllegalArgumentException("Input language cannot be null"); //$NON-NLS-1$
    }

    final List<ICoreQuickfixProvider> result = Lists.newArrayList();
    Collection<ICoreQuickfixProvider> registered = ICheckQuickfixRegistry.INSTANCE.getCoreQuickfixProviders(language);
    if (registered != null && !registered.isEmpty()) {
      result.addAll(registered);
    }

    return result;
  }

}

