/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.check.runtime.label;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Unit test for {@link DefaultCheckRuleLabelProvider}.
 */
@SuppressWarnings("nls")
public class CheckRuleLabelProviderTest {

  // Test data
  private static final int NUM_VALIDATORS = 3;
  private static final int NUM_CHECKS_PER_VALIDATOR = 3;
  private static final String[][] ISSUE_CODES = new String[NUM_VALIDATORS][NUM_CHECKS_PER_VALIDATOR];
  private static final String[][] LABELS = new String[NUM_VALIDATORS][NUM_CHECKS_PER_VALIDATOR];
  private static final String ISSUE_CODE;
  private static final String LABEL;
  static {
    for (int validator = 0; NUM_VALIDATORS > validator; ++validator) {
      for (int check = 0; NUM_CHECKS_PER_VALIDATOR > check; ++check) {
        ISSUE_CODES[validator][check] = String.format("package.%1$d.CheckCatalog%1$dIssueCodes.issue.code.%2$d", validator, check);
        LABELS[validator][check] = String.format("Catalog %d check rule %d label", validator, check);
      }
    }
    ISSUE_CODE = ISSUE_CODES[1][1];
    LABEL = LABELS[1][1];
  }

  // Mocks
  private ICheckValidatorRegistry mockCheckValidatorRegistry;
  private List<ICheckValidatorImpl> mockValidators;

  private Injector injector;

  // UUT
  private ICheckRuleLabelProvider checkRuleLabelProvider;

  /**
   * Setup done before each test.
   */
  @Before
  public void setUp() {

    // Create mocks
    mockCheckValidatorRegistry = mock(ICheckValidatorRegistry.class);
    mockValidators = new ArrayList<ICheckValidatorImpl>(NUM_VALIDATORS);
    for (int validator = 0; NUM_VALIDATORS > validator; ++validator) {
      mockValidators.add(mock(ICheckValidatorImpl.class));
    }

    // Create injector, with binding for mock ICheckValidatorRegistry
    injector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
        bind(ICheckValidatorRegistry.class).toInstance(mockCheckValidatorRegistry);
      }
    });

    // Create UUT
    checkRuleLabelProvider = injector.getInstance(ICheckRuleLabelProvider.class);
  }

  /**
   * Teardown done after each test.
   */
  @After
  public void tearDown() {

    mockCheckValidatorRegistry = null;
    mockValidators = null;

    injector = null;

    checkRuleLabelProvider = null;
  }

  /**
   * Set expectations on the mocks, with a typical set of checks.
   */
  private void mockValidatorsWithChecks() {
    when(mockCheckValidatorRegistry.getValidators()).thenReturn(mockValidators);

    for (int validator = 0; NUM_VALIDATORS > validator; ++validator) {
      final Builder<String, String> builder = ImmutableMap.builder();
      for (int check = 0; NUM_CHECKS_PER_VALIDATOR > check; ++check) {
        builder.put(ISSUE_CODES[validator][check], LABELS[validator][check]);
      }
      when(mockValidators.get(validator).getIssueCodeToLabelMap()).thenReturn(builder.build());
    }
  }

  /**
   * Test the default binding.
   */
  @Test
  public void testBinding() {
    // ASSERT
    assertSame("Binding of label provider incorrect.", CheckRuleLabelProvider.class, checkRuleLabelProvider.getClass());
  }

  /**
   * Test successfully getting a check label.
   */
  @Test
  public void testSuccess() {
    // ARRANGE
    mockValidatorsWithChecks();

    // ACT
    final String label = checkRuleLabelProvider.getLabel(ISSUE_CODE);

    // ASSERT
    assertEquals("Label should be " + LABEL + " but was " + label, LABEL, label);
  }

  /**
   * Test trying to get the check label for an issue code which is not present.
   */
  public void testWhenIssueCodeNotPresent() {
    // ARRANGE
    mockValidatorsWithChecks();

    // ACT
    final String label = checkRuleLabelProvider.getLabel("some.package.SomeCheckCatalogIssueCodes.no.such.issue.code");

    // ASSERT
    assertNull("Label should be null but was " + label, label);
  }

  /**
   * Test trying to get the check label for a null issue code.
   */
  @Test
  public void testWithNullID() {
    // ARRANGE
    mockValidatorsWithChecks();

    // ACT
    final String label = checkRuleLabelProvider.getLabel(null);

    // ASSERT
    assertNull("Label should be null but was " + label, label);
  }

  /**
   * Test trying to get a check label when there are no validators.
   */
  @Test
  public void testWithNoValidators() {
    // ARRANGE
    when(mockCheckValidatorRegistry.getValidators()).thenReturn(Collections.emptyList());

    // ACT
    final String label = checkRuleLabelProvider.getLabel(ISSUE_CODE);

    // ASSERT
    assertNull("Label should be null but was " + label, label);
  }

  /**
   * Test trying to get a check label when there are no checks registered.
   */
  @Test
  public void testWithNoChecks() {
    // ARRANGE
    when(mockCheckValidatorRegistry.getValidators()).thenReturn(mockValidators);
    for (ICheckValidatorImpl mockValidator : mockValidators) {
      when(mockValidator.getIssueCodeToLabelMap()).thenReturn(ImmutableMap.<String, String> builder().build());
    }

    // ACT
    final String label = checkRuleLabelProvider.getLabel(ISSUE_CODE);

    // ASSERT
    assertNull("Label should be null but was " + label, label);
  }

  /**
   * Test trying to get the check label for an issue code which is not a check issue code.
   */
  @Test
  public void testWhenIssueCodeIsNotACheckIssueCode() {
    // ARRANGE
    final String notACheckIssueCode = "package.name.SomeOtherClass.issue.code";

    final ICheckValidatorImpl mockValidator = mock(ICheckValidatorImpl.class);

    when(mockCheckValidatorRegistry.getValidators()).thenReturn(Arrays.asList(mockValidator));
    when(mockValidator.getIssueCodeToLabelMap()).thenReturn(ImmutableMap.<String, String> builder().put(notACheckIssueCode, LABEL).build());

    // ACT
    final String label = checkRuleLabelProvider.getLabel(notACheckIssueCode);

    // ASSERT
    assertNull("Label should be null but was " + label, label);
  }

  /**
   * Test that check labels are cached.
   */
  @Test
  public void testCaching() {
    // ARRANGE
    mockValidatorsWithChecks();

    // ACT
    final String label1 = checkRuleLabelProvider.getLabel(ISSUE_CODE);
    final String label2 = checkRuleLabelProvider.getLabel(ISSUE_CODE);

    // ASSERT
    verify(mockCheckValidatorRegistry, times(1)).getValidators();
    for (ICheckValidatorImpl mockValidator : mockValidators) {
      verify(mockValidator, times(1)).getIssueCodeToLabelMap();
    }

    assertEquals(label1 + " not equal to " + label2 + " . Equality expected", label1, label2);
  }

  /**
   * {@inheritDoc}
   * Override with invalidateCache() method exposed.
   */
  private static final class CheckRuleLabelProviderWithInvalidateCacheExposed extends CheckRuleLabelProvider {
    /**
     * Call protected method {link CheckRuleLabelProvider.invalidateCache}.
     */
    public void publicInvalidateCache() {
      super.invalidateCache();
    }
  }

  /**
   * Test that the cache can be invalidated by subclasses.
   */
  @Test
  public void testInvalidatingCache() {
    // ARRANGE
    final CheckRuleLabelProviderWithInvalidateCacheExposed checkRuleLabelProviderWithInvalidateCacheExposed = injector.getInstance(CheckRuleLabelProviderWithInvalidateCacheExposed.class);

    mockValidatorsWithChecks();

    // ACT
    final String label1 = checkRuleLabelProviderWithInvalidateCacheExposed.getLabel(ISSUE_CODE);
    checkRuleLabelProviderWithInvalidateCacheExposed.publicInvalidateCache();
    final String label2 = checkRuleLabelProviderWithInvalidateCacheExposed.getLabel(ISSUE_CODE);

    // ASSERT
    verify(mockCheckValidatorRegistry, times(2)).getValidators();
    for (ICheckValidatorImpl mockValidator : mockValidators) {
      verify(mockValidator, times(2)).getIssueCodeToLabelMap();
    }

    assertEquals(label1 + " not equal to " + label2 + " . Equality expected", label1, label2);
  }

  /**
   * Test that the class is bound as a singleton.
   */
  @Test
  public void testClassIsSingleton() {
    // ACT
    final ICheckRuleLabelProvider otherCheckRuleLabelProvider = injector.getInstance(CheckRuleLabelProvider.class);

    // ASSERT
    assertSame("Only one instance of CheckRuleLabelProvider expected", checkRuleLabelProvider, otherCheckRuleLabelProvider);
  }

}

/* Copyright (c) Avaloq Group AG */