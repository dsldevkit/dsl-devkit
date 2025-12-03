/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.xtext.builder;

import org.eclipse.xtext.builder.IXtextBuilderParticipant;


/**
 * Stand-alone version of {@link IXtextBuilderParticipant}. The stand-alone builder will process only build participants that implement this interface. Classes
 * implementing this must be prepared to deal with being called in {@link IXtextBuilderParticipant#build build()} with an
 * {@link IXtextBuilderParticipant.IBuildContext} that <em>will</em> return {@code null} in {@link IXtextBuilderParticipant.IBuildContext#getProject
 * getProject()}.
 */
public interface IStandaloneBuilderParticipant extends IXtextBuilderParticipant {

  /**
   * Determines what builder participants should actually do when get executed.
   */
  enum ExecutionMode {
    /**
     * If an indexing build is running it has the purpose to restore the build state only.
     * So generators must not change the system. However, we still want to determine what
     * derived objects generators would produce. So we let them run as normal, but ignore
     * the results. Implementers of the generators may decide to do less work in this mode
     * for performance reasons.
     */
    METADATA,

    /**
     * When ASMD really need to run server-side to produce sources, this mode is used.
     * Currently not used.
     */
    FULL
  }

  /**
   * Sets the execution mode for the builder participants.
   *
   * @param mode
   *          the new execution mode
   */
  void setExecutionMode(ExecutionMode mode);

  /**
   * When running in {@link ExecutionMode#FULL} mode some of the generators may still be disabled.
   * Those that are configured to actually perform generation in the standalone builder need to be
   * explicitly enabled by the standalone language setup via this method.
   * <p>
   * Note that disabled builder participants will still run, the metadata will still be written,
   * but the results will have no effect and generated sources will be ignored.
   * </p>
   *
   * @param isActive
   *          whether the builder participant is fully enabled for this language
   */
  void setActive(boolean isActive);

}

/* Copyright (c) Avaloq Group AG */