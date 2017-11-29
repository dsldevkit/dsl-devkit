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
package com.avaloq.tools.ddk.test.core.util;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.osgi.util.NLS;


/**
 * An {@link IJobChangeListener} that logs job events.
 */
public class LoggingJobListener implements IJobChangeListener {

  /** The logger. */
  private static final Logger LOGGER = Logger.getLogger(LoggingJobListener.class);

  /** {@inheritDoc} */
  @Override
  public void aboutToRun(final IJobChangeEvent event) {
    LOGGER.info(NLS.bind("Job ''{0}'' family ''{1}'': about to run.", event.getJob().getName(), event.getJob().getClass().getName())); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void awake(final IJobChangeEvent event) {
    LOGGER.info(NLS.bind("Job ''{0}'' family ''{1}'': awake.", event.getJob().getName(), event.getJob().getClass().getName())); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void done(final IJobChangeEvent event) {
    LOGGER.info(NLS.bind("Job ''{0}'' family ''{1}'': done.", event.getJob().getName(), event.getJob().getClass().getName())); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void running(final IJobChangeEvent event) {
    LOGGER.info(NLS.bind("Job ''{0}'' family ''{1}'': running.", event.getJob().getName(), event.getJob().getClass().getName())); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void scheduled(final IJobChangeEvent event) {
    LOGGER.info(NLS.bind("Job ''{0}'' family ''{1}'': scheduled.", event.getJob().getName(), event.getJob().getClass().getName())); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void sleeping(final IJobChangeEvent event) {
    LOGGER.info(NLS.bind("Job ''{0}'' family ''{1}'': sleeping.", event.getJob().getName(), event.getJob().getClass().getName())); //$NON-NLS-1$
  }
}

