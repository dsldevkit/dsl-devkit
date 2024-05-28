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
package com.avaloq.tools.ddk.xtext.validation;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;


/**
 * The ValidPreferenceStore is a read-only Preference Store that uses the scopes
 * provided in org.eclipse.core.runtime.preferences.
 * <p>
 * A ValidPreferenceStore does the lookup of a preference based on it's search scopes and sets the value of the preference based on its store scope. It's
 * implementation is based on org.eclipse.ui.preferences.ScopedPreferenceStore, but removes the whole UI part.
 * </p>
 * <p>
 * The default scope is always included in the search scopes when searching for preference values.
 * </p>
 *
 * @see org.eclipse.core.runtime.preferences
 */
public class ValidPreferenceStore {

  /**
   * The default-default value for boolean preferences (<code>false</code>).
   */
  public static final boolean BOOLEAN_DEFAULT_DEFAULT = false;

  /**
   * The default-default value for double preferences (<code>0.0</code>).
   */
  public static final double DOUBLE_DEFAULT_DEFAULT = 0.0;

  /**
   * The default-default value for float preferences (<code>0.0f</code>).
   */
  public static final float FLOAT_DEFAULT_DEFAULT = 0.0f;

  /**
   * The default-default value for int preferences (<code>0</code>).
   */
  public static final int INT_DEFAULT_DEFAULT = 0;

  /**
   * The default-default value for long preferences (<code>0L</code>).
   */
  public static final long LONG_DEFAULT_DEFAULT = 0L;

  /**
   * The default-default value for String preferences (<code>""</code>).
   */
  public static final String STRING_DEFAULT_DEFAULT = ""; //$NON-NLS-1$

  /**
   * The string representation used for <code>true</code> (<code>"true"</code>).
   */
  public static final String TRUE = "true"; //$NON-NLS-1$

  /**
   * The string representation used for <code>false</code> (<code>"false"</code>).
   */
  public static final String FALSE = "false"; //$NON-NLS-1$

  /**
   * The storeContext is the context where values will stored with the
   * setValue methods. If there are no searchContexts this will be the search
   * context. (along with the "default" context)
   */
  private final IScopeContext storeContext;

  /**
   * The searchContext is the array of contexts that will be used by the get
   * methods for searching for values.
   */
  private IScopeContext[] searchContexts;

  /**
   * The default context is the context where getDefault and setDefault
   * methods will search. This context is also used in the search.
   */
  private static final IScopeContext DEFAULT_CONTEXT = DefaultScope.INSTANCE;

  /**
   * The nodeQualifer is the string used to look up the node in the contexts.
   */
  private final String nodeQualifier;

  /**
   * The defaultQualifier is the string used to look up the default node.
   */
  private final String defaultQualifier;

  /**
   * Create a new instance of the receiver. Store the values in context in the
   * node looked up by qualifier. <strong>NOTE:</strong> Any instance of
   * ScopedPreferenceStore should call
   *
   * @param context
   *          the scope to store to
   * @param qualifier
   *          the qualifier used to look up the preference node
   * @param defaultQualifierPath
   *          the qualifier used when looking up the defaults
   */
  public ValidPreferenceStore(final IScopeContext context, final String qualifier, final String defaultQualifierPath) {
    storeContext = context;
    this.nodeQualifier = qualifier;
    this.defaultQualifier = defaultQualifierPath;
  }

  /**
   * Create a new instance of the receiver. Store the values in context in the
   * node looked up by qualifier.
   *
   * @param context
   *          the scope to store to
   * @param qualifier
   *          the qualifier used to look up the preference node
   */
  public ValidPreferenceStore(final IScopeContext context, final String qualifier) {
    this(context, qualifier, qualifier);
  }

  /**
   * Does its best at determining the default value for the given key. Checks
   * the given object's type and then looks in the list of defaults to see if
   * a value exists. If not or if there is a problem converting the value, the
   * default default value for that type is returned.
   *
   * @param key
   *          the key to search
   * @param object
   *          the object who default we are looking for
   * @return Object or <code>null</code>
   */
  Object getDefault(final String key, final Object object) {
    final IEclipsePreferences defaults = getDefaultPreferences();
    if (object instanceof String) {
      return defaults.get(key, STRING_DEFAULT_DEFAULT);
    } else if (object instanceof Integer) {
      return defaults.getInt(key, INT_DEFAULT_DEFAULT);
    } else if (object instanceof Double) {
      return defaults.getDouble(key, DOUBLE_DEFAULT_DEFAULT);
    } else if (object instanceof Float) {
      return defaults.getFloat(key, FLOAT_DEFAULT_DEFAULT);
    } else if (object instanceof Long) {
      return defaults.getLong(key, LONG_DEFAULT_DEFAULT);
    } else if (object instanceof Boolean) {
      return defaults.getBoolean(key, BOOLEAN_DEFAULT_DEFAULT) ? Boolean.TRUE : Boolean.FALSE;
    } else {
      return null;
    }
  }

  /**
   * Return the IEclipsePreferences node associated with this store.
   *
   * @return the preference node for this store
   */
  IEclipsePreferences getStorePreferences() {
    return storeContext.getNode(nodeQualifier);
  }

  /**
   * Return the default IEclipsePreferences for this store.
   *
   * @return this store's default preference node
   */
  private IEclipsePreferences getDefaultPreferences() {
    return DEFAULT_CONTEXT.getNode(defaultQualifier);
  }

  /**
   * Return the preference path to search preferences on. This is the list of
   * preference nodes based on the scope contexts for this store. If there are
   * no search contexts set, then return this store's context.
   * <p>
   * Whether or not the default context should be included in the resulting list is specified by the <code>includeDefault</code> parameter.
   * </p>
   *
   * @param includeDefault
   *          <code>true</code> if the default context should be included
   *          and <code>false</code> otherwise
   * @return IEclipsePreferences[]
   * @since 3.4 public, was added in 3.1 as private method
   */
  public IEclipsePreferences[] getPreferenceNodes(final boolean includeDefault) {
    // if the user didn't specify a search order, then return the scope that
    // this store was created on. (and optionally the default)
    if (searchContexts == null) {
      if (includeDefault) {
        return new IEclipsePreferences[] {getStorePreferences(), getDefaultPreferences()};
      }
      return new IEclipsePreferences[] {getStorePreferences()};
    }
    // otherwise the user specified a search order so return the appropriate
    // nodes based on it
    int length = searchContexts.length;
    if (includeDefault) {
      length++;
    }
    final IEclipsePreferences[] preferences = new IEclipsePreferences[length];
    for (int i = 0; i < searchContexts.length; i++) {
      preferences[i] = searchContexts[i].getNode(nodeQualifier);
    }
    if (includeDefault) {
      preferences[length - 1] = getDefaultPreferences();
    }
    return preferences;
  }

  /**
   * Set the search contexts to scopes. When searching for a value the seach
   * will be done in the order of scope contexts and will not search the
   * storeContext unless it is in this list.
   * <p>
   * If the given list is <code>null</code>, then clear this store's search contexts. This means that only this store's scope context and default scope will be
   * used during preference value searching.
   * </p>
   * <p>
   * The defaultContext will be added to the end of this list automatically and <em>MUST NOT</em> be included by the user.
   * </p>
   *
   * @param scopes
   *          a list of scope contexts to use when searching, or <code>null</code>
   */
  public void setSearchContexts(final IScopeContext[] scopes) { // NOPMD
    this.searchContexts = scopes;
    if (scopes == null) {
      return;
    }

    // Assert that the default was not included (we automatically add it to
    // the end)
    for (final IScopeContext scope : scopes) {
      if (scope.equals(DEFAULT_CONTEXT)) {
        Assert.isTrue(false, "Do not add the default to the search contexts"); //$NON-NLS-1$
      }
    }
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#contains(java.lang.String)
   * @param name
   *          the name of the preference
   * @return <code>true</code> if either a current value or a default
   */
  public boolean contains(final String name) {
    return name != null && Platform.getPreferencesService().get(name, null, getPreferenceNodes(true)) != null;
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getBoolean(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the boolean-valued preference
   */
  public boolean getBoolean(final String name) {
    final String value = internalGet(name);
    return value == null ? BOOLEAN_DEFAULT_DEFAULT : Boolean.parseBoolean(value);
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultBoolean(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the default value of the named preference
   */
  public boolean getDefaultBoolean(final String name) {
    return getDefaultPreferences().getBoolean(name, BOOLEAN_DEFAULT_DEFAULT);
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultDouble(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the default value of the named preference
   */
  public double getDefaultDouble(final String name) {
    return getDefaultPreferences().getDouble(name, DOUBLE_DEFAULT_DEFAULT);
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultFloat(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the default value of the named preference
   */
  public float getDefaultFloat(final String name) {
    return getDefaultPreferences().getFloat(name, FLOAT_DEFAULT_DEFAULT);
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultInt(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the default value of the named preference
   */
  public int getDefaultInt(final String name) {
    return getDefaultPreferences().getInt(name, INT_DEFAULT_DEFAULT);
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultLong(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the default value of the named preference
   */
  public long getDefaultLong(final String name) {
    return getDefaultPreferences().getLong(name, LONG_DEFAULT_DEFAULT);
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getDefaultString(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the default value of the named preference
   */
  public String getDefaultString(final String name) {
    return getDefaultPreferences().get(name, STRING_DEFAULT_DEFAULT);
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getDouble(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the double-valued preference
   */
  public double getDouble(final String name) {
    final String value = internalGet(name);
    if (value == null) {
      return DOUBLE_DEFAULT_DEFAULT;
    }
    try {
      return Double.parseDouble(value);
    } catch (final NumberFormatException e) {
      return DOUBLE_DEFAULT_DEFAULT;
    }
  }

  /**
   * Return the string value for the specified key. Look in the nodes which
   * are specified by this object's list of search scopes. If the value does
   * not exist then return <code>null</code>.
   *
   * @param key
   *          the key to search with
   * @return String or <code>null</code> if the value does not exist or executing in non-Eclipse environment.
   */
  private String internalGet(final String key) {
    // TODO SUPPORT PREFERENCES IN STANDALONE BUILD ?
    return Platform.isRunning() ? Platform.getPreferencesService().get(key, null, getPreferenceNodes(true)) : null; // NOPMD:NullAssignment
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getFloat(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the float-valued preference
   */
  public float getFloat(final String name) {
    final String value = internalGet(name);
    if (value == null) {
      return FLOAT_DEFAULT_DEFAULT;
    }
    try {
      return Float.parseFloat(value);
    } catch (final NumberFormatException e) {
      return FLOAT_DEFAULT_DEFAULT;
    }
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getInt(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the int-valued preference
   */
  public int getInt(final String name) {
    final String value = internalGet(name);
    if (value == null) {
      return INT_DEFAULT_DEFAULT;
    }
    try {
      return Integer.parseInt(value);
    } catch (final NumberFormatException e) {
      return INT_DEFAULT_DEFAULT;
    }
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getLong(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the long-valued preference
   */
  public long getLong(final String name) {
    final String value = internalGet(name);
    if (value == null) {
      return LONG_DEFAULT_DEFAULT;
    }
    try {
      return Long.parseLong(value);
    } catch (final NumberFormatException e) {
      return LONG_DEFAULT_DEFAULT;
    }
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#getString(java.lang.String)
   * @param name
   *          the name of the preference
   * @return the string-valued preference
   */
  public String getString(final String name) {
    final String value = internalGet(name);
    return value == null ? STRING_DEFAULT_DEFAULT : value;
  }

  /**
   * @see org.eclipse.jface.preference.IPreferenceStore#isDefault(java.lang.String)
   * @param name
   *          the name of the preference
   * @return <code>true</code> if the preference has a known default value
   *         and its current value is the same, and <code>false</code> otherwise
   *         (including the case where the preference is unknown to this store)
   */
  public boolean isDefault(final String name) {
    return name != null && Platform.getPreferencesService().get(name, null, getPreferenceNodes(false)) == null;
  }

}
