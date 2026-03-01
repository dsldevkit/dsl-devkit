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

package com.avaloq.tools.ddk.xtext.generator.model.project;

import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.SubProjectConfig;

public class ProjectConfig extends StandardProjectConfig {

  private String runtimeSuffix = "";
  private String testSuffix = "test";
  private String eclipsePluginSuffix = "ui";
  private String genericIdeSuffix = "ide";
  private boolean forceDisableIdeProject = true;

  @Override
  protected String computeName(final SubProjectConfig project) {
    if (isMavenLayout()) {
      return super.computeName(project);
    }
    if (project == getRuntime()) {
      return runtimeSuffix.isEmpty() ? getBaseName() : getBaseName() + "." + runtimeSuffix;
    } else if (project == getRuntimeTest()) {
      return runtimeSuffix.isEmpty() ? getBaseName() + "." + testSuffix : getBaseName() + "." + runtimeSuffix + "." + testSuffix;
    } else if (project == getGenericIde()) {
      return getBaseName() + "." + genericIdeSuffix;
    } else if (project == getEclipsePlugin()) {
      return getBaseName() + "." + eclipsePluginSuffix;
    } else if (project == getEclipsePluginTest()) {
      return getBaseName() + "." + eclipsePluginSuffix + "." + testSuffix;
    } else {
      return super.computeName(project);
    }
  }

  @Override
  public void setDefaults() {
    super.setDefaults();
    if (forceDisableIdeProject) {
      getGenericIde().setEnabled(false);
    }
  }

  public String getRuntimeSuffix() {
    return runtimeSuffix;
  }

  public void setRuntimeSuffix(final String runtimeSuffix) {
    this.runtimeSuffix = runtimeSuffix;
  }

  public String getTestSuffix() {
    return testSuffix;
  }

  public void setTestSuffix(final String testSuffix) {
    this.testSuffix = testSuffix;
  }

  public String getEclipsePluginSuffix() {
    return eclipsePluginSuffix;
  }

  public void setEclipsePluginSuffix(final String eclipsePluginSuffix) {
    this.eclipsePluginSuffix = eclipsePluginSuffix;
  }

  public String getGenericIdeSuffix() {
    return genericIdeSuffix;
  }

  public void setGenericIdeSuffix(final String genericIdeSuffix) {
    this.genericIdeSuffix = genericIdeSuffix;
  }

  public boolean isForceDisableIdeProject() {
    return forceDisableIdeProject;
  }

  public void setForceDisableIdeProject(final boolean forceDisableIdeProject) {
    this.forceDisableIdeProject = forceDisableIdeProject;
  }
}

/* Copyright (c) Avaloq Group AG */
