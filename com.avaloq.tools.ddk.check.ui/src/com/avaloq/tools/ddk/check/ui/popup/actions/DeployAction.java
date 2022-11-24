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
package com.avaloq.tools.ddk.check.ui.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.avaloq.tools.ddk.check.ui.Messages;
import com.avaloq.tools.ddk.check.ui.util.ProjectUtil;


/**
 * Action to deploy a check bundle.
 */
public class DeployAction implements IObjectActionDelegate {

  /** {@inheritDoc} */
  public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {}

  /** {@inheritDoc} */
  public void run(final IAction action) {
    new DeployJob(Messages.DeployAction_DeployBundleLocally, ProjectUtil.getProject()).schedule();
  }

  /** {@inheritDoc} */
  public void selectionChanged(final IAction action, final ISelection selection) {}

}
