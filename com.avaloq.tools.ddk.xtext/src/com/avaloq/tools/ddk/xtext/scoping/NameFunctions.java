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
package com.avaloq.tools.ddk.xtext.scoping;

import java.text.MessageFormat;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Utility functions for creating and applying name functions.
 */
public final class NameFunctions {

  // TODO better constant names
  private static final String CONSTANT = "Constant:";//$NON-NLS-1$

  private static final String FEATURE_NAME = "FeatureName:";//$NON-NLS-1$

  private static final String UNKNOWN_FEATURE_0_FROM_1 = "Unknown feature {0} from {1}";//$NON-NLS-1$

  private static final String COULD_NOT_READ_FEATURE_0_FROM_1 = "Could not read feature {0} from {1}";//$NON-NLS-1$

  private static final String NO_USER_DATA_0_FOUND_FOR_1 = "No user data \'\'{0}\'\' found for {1}."; //$NON-NLS-1$

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(NameFunctions.class);

  private static final INameFunction EXPORT_NAME_FUNCTION = new AbstractNameFunction("Export") { //$NON-NLS-1$

    @Override
    public QualifiedName apply(final IEObjectDescription from) {
      return from.getName();
    }

    @Override
    public QualifiedName apply(final EObject object) {
      throw new UnsupportedOperationException();
    }
  };

  /**
   * No public constructor.
   */
  private NameFunctions() {
    // Prevent instantiation.
  }

  /**
   * Create a name function based of the value of a structural feature.
   *
   * @param feature
   *          The feature
   * @return The name function
   */
  public static INameFunction fromFeature(final EStructuralFeature feature) {
    return new AbstractNameFunction("Feature:" + feature) { //$NON-NLS-1$
      @Override
      public QualifiedName apply(final IEObjectDescription from) {
        String res = from.getUserData(feature.getName());
        if (res == null && LOGGER.isDebugEnabled()) {
          LOGGER.debug(MessageFormat.format(NO_USER_DATA_0_FOUND_FOR_1, feature.getName(), from.getEObjectOrProxy()));
        }
        return QualifiedNames.safeQualifiedName(res);
      }

      @Override
      public QualifiedName apply(final EObject from) {
        Object value;
        try {
          value = from.eGet(feature);
          // CHECKSTYLE:OFF
        } catch (final RuntimeException e) {
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(MessageFormat.format(COULD_NOT_READ_FEATURE_0_FROM_1, feature, from), e);
          }
          return null;
        }
        // CHECKSTYLE:ON
        return value != null ? QualifiedNames.safeQualifiedName(value.toString()) : null;
      }

    };
  }

  /**
   * Create name functions based on a list of structural features.
   *
   * @param nameFeatures
   *          The features
   * @return The name functions
   */
  public static Iterable<INameFunction> fromFeatures(final EStructuralFeature... nameFeatures) {
    return Iterables.transform(Lists.newArrayList(nameFeatures), new Function<EStructuralFeature, INameFunction>() {
      @Override
      public INameFunction apply(final EStructuralFeature from) {
        return fromFeature(from);
      }
    });
  }

  /**
   * Create a name function returning a constant value.
   *
   * @param value
   *          The constant value
   * @return The name function
   */
  public static INameFunction fromConstant(final String value) {
    return new AbstractNameFunction(CONSTANT + value) {
      @Override
      public QualifiedName apply(final EObject from) {
        return QualifiedNames.safeQualifiedName(value);
      }

      @Override
      public QualifiedName apply(final IEObjectDescription from) {
        return QualifiedNames.safeQualifiedName(value);
      }
    };
  }

  /**
   * Create one Iterable from two name functions.
   *
   * @param first
   *          The first name function
   * @param second
   *          The second name function
   * @return An iterable containing both.
   */
  public static Iterable<INameFunction> pair(final INameFunction first, final INameFunction second) {
    return Lists.<INameFunction> newArrayList(first, second);
  }

  /**
   * Create a name function based on a structural feature identified by name. The name function will return null if there is no
   * such feature for a particular object.
   *
   * @param featureName
   *          The name of the structural feature
   * @return The name function.
   */
  public static INameFunction fromFeatureName(final String featureName) {
    return new AbstractNameFunction(FEATURE_NAME + featureName) {
      @Override
      public QualifiedName apply(final EObject from) {
        final EStructuralFeature feature = from.eClass().getEStructuralFeature(featureName);
        if (feature == null && LOGGER.isDebugEnabled()) {
          LOGGER.debug(MessageFormat.format(UNKNOWN_FEATURE_0_FROM_1, featureName, from));
        }
        Object value;
        try {
          value = from.eGet(feature);
          // CHECKSTYLE:OFF
        } catch (final RuntimeException e) {
          // CHECKSTYLE:ON
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(MessageFormat.format(COULD_NOT_READ_FEATURE_0_FROM_1, featureName, from), e);
          }
          return null;
        }
        return value != null ? QualifiedNames.safeQualifiedName(value.toString()) : null;
      }

    };
  }

  /**
   * Returns the index name of an "index object" ({@link org.eclipse.xtext.resource.IEObjectDescription}).
   *
   * @return the name of an {@link org.eclipse.xtext.resource.IEObjectDescription} as it appears in the index
   */
  public static INameFunction exportNameFunction() {
    return EXPORT_NAME_FUNCTION;
  }
}
