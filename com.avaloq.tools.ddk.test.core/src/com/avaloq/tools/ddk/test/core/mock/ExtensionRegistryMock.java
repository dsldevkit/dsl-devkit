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

package com.avaloq.tools.ddk.test.core.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.common.util.WrappedException;
import org.junit.Assert;
import org.mockito.stubbing.Answer;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;


/** Provides a mocking framework for the extension point registry. */
public final class ExtensionRegistryMock {

  private static IExtensionRegistry registry;
  private static IExtensionRegistry registrySpy;
  private static Multimap<String, IConfigurationElement> configurationElements = LinkedHashMultimap.create();
  private static Map<String, Answer<IConfigurationElement[]>> configurationAnswers = Maps.newHashMap();

  private ExtensionRegistryMock() {}

  /**
   * Mocks the extension registry.
   *
   * @throws CoreException
   */
  @SuppressWarnings("restriction") // for accessing RegistryProviderFactory
  public static synchronized void mockRegistry() {
    if (registrySpy == null) {
      registry = RegistryFactory.getRegistry();
      registrySpy = spy(registry);
      org.eclipse.core.internal.registry.RegistryProviderFactory.releaseDefault();
      try {
        RegistryFactory.setDefaultRegistryProvider(() -> {
          return registrySpy;
        });
      } catch (CoreException e) {
        // This shouldn't happen as the default was released.
        throw new WrappedException(e);
      }
    }
  }

  /**
   * Mocks {@link IConfigurationElement#createExecutableExtension(String)} for the given {@link IConfigurationElement} attribute with the given contribution
   * object.
   *
   * @param mockedElement
   *          the {@link IConfigurationElement} mock. Must already be a mock and must not be {@code null}
   * @param attribute
   *          the name of the attribute to mock, must not be {@code null}
   * @param contribution
   *          the object that {@code mockedElement.createExecutableExtension(attribute)} will return, may be {@code null}
   * @throws IllegalArgumentException
   *           if {@code mockedElement} is not a mockito mock.
   */
  public static void mockExecutableExtension(final IConfigurationElement mockedElement, final String attribute, final Object contribution) {
    if (!mockingDetails(mockedElement).isMock()) {
      throw new IllegalArgumentException("The configuration element must be a mock.");
    }
    try {
      when(mockedElement.createExecutableExtension(attribute)).thenReturn(contribution);
    } catch (CoreException e) {
      // Do nothing, mocked method will not throw anything.
    }
  }

  /**
   * Mocks an {@link IConfigurationElement} for an extension point with the given id. The extension point is only mocked via
   * {@link IExtensionRegistry#getConfigurationElementsFor(String) and will not be visible otherwise.
   *
   * @param extensionPointId
   *          the fully-qualified id for the extension point, must not be {@code null}
   * @return the mocked {@link IConfigurationElement}, never {@code null}
   */
  public static IConfigurationElement mockConfigurationElement(final String extensionPointId) {
    if (registrySpy == null) {
      mockRegistry();
    }
    Answer<IConfigurationElement[]> answer = configurationAnswers.get(extensionPointId);
    if (answer == null) {
      answer = invocation -> {
        List<IConfigurationElement> mockedResult = Lists.newArrayList((IConfigurationElement[]) invocation.callRealMethod());
        mockedResult.addAll(configurationElements.get(extensionPointId));
        return mockedResult.toArray(new IConfigurationElement[mockedResult.size()]);
      };
      configurationAnswers.put(extensionPointId, answer);
      when(registrySpy.getConfigurationElementsFor(extensionPointId)).thenAnswer(answer);
    }
    IConfigurationElement mocked = mock(IConfigurationElement.class);
    configurationElements.put(extensionPointId, mocked);
    return mocked;
  }

  /**
   * Unmocks the given element from the given extension point.
   *
   * @param extensionPointId
   *          the fully-qualified id for the extension point, must not be {@code null}
   * @param mockedElement
   *          the {@link IConfigurationElement} to unmock, must not be {@code null}
   */
  public static void unMock(final String extensionPointId, final IConfigurationElement mockedElement) {
    configurationElements.remove(extensionPointId, mockedElement);
    if (configurationElements.isEmpty()) {
      unMockRegistry();
    }
  }

  /**
   * Unmocks the given extension point.
   *
   * @param extensionPointId
   *          the fully-qualified id for the extension point, must not be {@code null}
   */
  public static void unMock(final String extensionPointId) {
    configurationElements.removeAll(extensionPointId);
    if (configurationElements.isEmpty()) {
      unMockRegistry();
    }
  }

  /**
   * Unmocks the extension registry.
   */
  @SuppressWarnings("restriction") // for accessing RegistryProviderFactory
  public static void unMockRegistry() {
    configurationElements.clear();
    configurationAnswers.clear();
    registrySpy = null;
    org.eclipse.core.internal.registry.RegistryProviderFactory.releaseDefault(); // restricted
    try {
      RegistryFactory.setDefaultRegistryProvider(() -> {
        return registry;
      });
    } catch (CoreException e) {
      // This shouldn't happen as the default was released.
      throw new WrappedException(e);
    }
  }

  /**
   * Asserts that the extension registry is unmocked.
   */
  public static void assertUnMocked() {
    if (registrySpy != null) {
      try {
        String extensionPointId = configurationElements.keySet().iterator().next();
        Assert.fail("Extension point " + extensionPointId + " still has mocked configuration elements."); //$NON-NLS-1$ //$NON-NLS-2$
      } catch (NoSuchElementException e) { // shouldn't happen
        throw new IllegalStateException("The extension registry mock is in an unexpected state.", e); //$NON-NLS-1$
      }
    }
  }

}
