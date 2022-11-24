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
package com.avaloq.tools.ddk.check.runtime.configuration;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.IScopeContext;

import com.google.inject.Inject;


/**
 * The default check configuration store looks up configurable values for check
 * properties in the Eclipse preferences. Looks up the {@link ProjectScope
 * project scope} in order to check if custom check configurations have been defined.
 * <p>
 * If no customized check configurations exist, given default value is used.
 * </p>
 */
public class CheckConfigurationStore implements ICheckConfigurationStore {

  private final String[] nodes;
  private ProjectScope projectScope;
  private IProject project;

  /**
   * Instantiates a new check configuration store. No nodes are configured, which means that
   * default values will be used for all checks. No project scope is looked up.
   */
  @Inject
  public CheckConfigurationStore() {
    this.nodes = new String[] {};
  }

  /**
   * Instantiates a new check configuration store with a given collection of nodes. The nodes
   * will be traversed in given order. The first node with a given key having a value which is
   * different from the default will be returned.
   * 
   * @param nodes
   *          the nodes
   */
  public CheckConfigurationStore(final String... nodes) {
    this.nodes = nodes;
  }

  public void setProject(final IProject project) {
    this.project = project;
  }

  /**
   * Gets the scope context to be used. Returns {@code null} if the {@link IProject project} is {@code null}.
   * 
   * @return the scope context
   */
  private IScopeContext getProjectScope() {
    if (project == null) {
      return null;
    }
    if (projectScope == null) {
      projectScope = new ProjectScope(project);
    }
    return projectScope;
  }

  /** {@inheritDoc} */
  public boolean getBoolean(final String key, final boolean defaultValue) {
    IScopeContext scope = getProjectScope();
    if (scope != null) {
      for (final String node : nodes) {
        boolean result = scope.getNode(node).getBoolean(key, defaultValue);
        if (result != defaultValue) {
          return result;
        }
      }
    }
    return defaultValue;
  }

  /** {@inheritDoc} */
  public int getInt(final String key, final int defaultValue) {
    IScopeContext scope = getProjectScope();
    if (scope != null) {
      for (final String node : nodes) {
        int result = scope.getNode(node).getInt(key, defaultValue);
        if (result != defaultValue) {
          return result;
        }
      }
    }
    return defaultValue;
  }

  /** {@inheritDoc} */
  public String getString(final String key, final String defaultValue) {
    IScopeContext scope = getProjectScope();
    if (scope != null) {
      for (final String node : nodes) {
        String result = scope.getNode(node).get(key, null);
        if (result != null) {
          return result;
        }
      }
    }
    return defaultValue;
  }

  /** {@inheritDoc} */
  public List<String> getStrings(final String key, final List<String> defaultValue) {
    String marshaled = getString(key, null);
    return marshaled == null ? defaultValue : CheckPreferencesHelper.unmarshalStrings(marshaled);
  }

  /** {@inheritDoc} */
  public List<Boolean> getBooleans(final String key, final List<Boolean> defaultValue) {
    String marshaled = getString(key, null);
    return marshaled == null ? defaultValue : CheckPreferencesHelper.unmarshalBooleans(marshaled);
  }

  /** {@inheritDoc} */
  public List<Integer> getIntegers(final String key, final List<Integer> defaultValue) {
    String marshaled = getString(key, null);
    return marshaled == null ? defaultValue : CheckPreferencesHelper.unmarshalIntegers(marshaled);
  }

}

