/*
 * Copyright (c) Avaloq Licence AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.tools.ddk.check.runtime.label;

import static com.avaloq.tools.ddk.check.runtime.CheckRuntimeConstants.ISSUE_CODES_CLASS_NAME_SUFFIX;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * {@inheritDoc}
 * Map of labels is cached, to avoid repeatedly populating it.
 */
@Singleton
public class CheckRuleLabelProvider implements ICheckRuleLabelProvider {

  private static final Logger LOGGER = LogManager.getLogger(CheckRuleLabelProvider.class);

  private static final String CHECK_ISSUE_CODE_SUBSTRING = ISSUE_CODES_CLASS_NAME_SUFFIX + "."; //$NON-NLS-1$

  @Inject
  private ICheckValidatorRegistry checkValidatorRegistry;

  private final AtomicReference<Map<String, String>> issueCodeToLabelMap = new AtomicReference<Map<String, String>>(null);

  @Override
  public String getLabel(final String issueCode) {

    // Is this a Check?
    if (!StringUtils.contains(issueCode, CHECK_ISSUE_CODE_SUBSTRING)) {
      return null;
    }

    // Lazily create the map
    issueCodeToLabelMap.updateAndGet(existingMap -> (null != existingMap) ? existingMap
        : Collections.unmodifiableMap(createIssueCodeToLabelMap(checkValidatorRegistry)));

    // Get the label
    return issueCodeToLabelMap.get().get(issueCode);
  }

  /**
   * Create a new map for translating Check rule issue codes into human-readable labels.
   *
   * @param checkValidatorRegistry
   *          {@link ICheckValidatorRegistry} to use, must not be {@code null}
   * @return new map for translating Check rule issue codes into human-readable labels, never {@code null}
   */
  private static Map<String, String> createIssueCodeToLabelMap(final ICheckValidatorRegistry checkValidatorRegistry) {

    // Get all validators
    final Collection<ICheckValidatorImpl> validators = checkValidatorRegistry.getValidators();

    // Get the maps from all validators
    final Stream<Map<String, String>> issueCodeToLabelMaps = validators.stream().map(validatorClass -> getIssueCodeToLabelMap(validatorClass)).filter(Objects::nonNull);

    // Munge the maps together
    return merge(issueCodeToLabelMaps);
  }

  /**
   * Get the map for translating Check rule issue codes into human-readable labels from a validator class.
   *
   * @param validator
   *          validator from which to get the map, must not be {@code null}
   * @return map for translating Check rule issue codes into human-readable labels, or {@code null} if an error occurs
   */
  private static Map<String, String> getIssueCodeToLabelMap(final ICheckValidatorImpl validator) {
    try {
      return validator.getIssueCodeToLabelMap();
    } catch (AbstractMethodError e) {
      LOGGER.warn(String.format("Method not found: %1$s.getIssueCodeToLabelMap(). To fix this problem, recompile and deploy %1$s.", validator.getQualifiedCatalogName())); //$NON-NLS-1$
      return null; // NOPMD ReturnEmptyCollectionRatherThanNull
    }
  }

  /**
   * Merge a stream of maps of issue code to label.
   *
   * @param maps
   *          a stream of maps of issue code to label, must not be {@code null}
   * @return combined map of issue code to label, never {@code null}
   */
  private static Map<String, String> merge(final Stream<? extends Map<String, String>> maps) {
    final Map<String, String> mergedMap = new HashMap<String, String>();
    maps.map(Map::entrySet).flatMap(Set::stream).forEach(entry -> {
      if (null != mergedMap.putIfAbsent(entry.getKey(), entry.getValue())) {
        LOGGER.warn("Non-unique Check issue code found: " + entry.getKey()); //$NON-NLS-1$
      }
    });
    return mergedMap;
  }

  /**
   * Invalidate the cached map, thread-safely.
   */
  protected void invalidateCache() {

    // Nuke the cached map
    issueCodeToLabelMap.set(null);
  }

}

/* Copyright (c) Avaloq Licence AG */