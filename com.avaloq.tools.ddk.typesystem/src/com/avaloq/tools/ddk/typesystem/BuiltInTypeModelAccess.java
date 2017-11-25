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
package com.avaloq.tools.ddk.typesystem;

import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedType;
import com.avaloq.tools.ddk.xtext.resource.GlobalResources;
import com.google.common.collect.Maps;


/**
 * Manages access to the contents of the "built-in" type model, i.e., all predefined type-model elements.
 * <p>
 * Thread-safe singleton.
 * <p>
 */
public final class BuiltInTypeModelAccess {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(BuiltInTypeModelAccess.class);

  /** Plugin name for platform-plugin URI. */
  private static final String PLUGIN_NAME = "com.avaloq.tools.ddk.typesystem"; //$NON-NLS-1$

  /** The location of the concrete model of built-in types. */
  private static final String MODEL_LOCATION = "BuiltInTypeModel.xmi"; //$NON-NLS-1$

  /** The name of the any type is {@value} . */
  public static final String ANY_TYPE_NAME = "*"; //$NON-NLS-1$
  /** The name of the error type is {@value} . */
  public static final String ERROR_TYPE_NAME = "error"; //$NON-NLS-1$
  /** The name of the void type is {@value} . */
  public static final String VOID_TYPE_NAME = "void"; //$NON-NLS-1$
  /** The name of the undetermined type is {@value} . */
  public static final String UNDETERMINED_TYPE_NAME = "undetermined"; //$NON-NLS-1$

  /** URI to resource containing all type-model built-ins. */
  private static final URI MODEL_URI = URI.createPlatformPluginURI('/' + PLUGIN_NAME + '/' + MODEL_LOCATION, true);

  /**
   * The singleton instance.
   */
  private static class InstanceHolder {
    // Initialize-on-demand holder pattern.
    @SuppressWarnings("PMD.AccessorClassGeneration")
    // This is a common idiom, and PMD even says to use it, see http://pmd.sourceforge.net/pmd-5.1.0/rules/java/design.html, Rule NonThreadSafeSingleton
    private static final BuiltInTypeModelAccess INSTANCE = new BuiltInTypeModelAccess();
  }

  /** In-memory representation of the internal type model's contents. */
  private BuiltInTypeModel model;

  private final Map<String, InternalType> internalTypesByName = Maps.newHashMap();

  /** Constructor; no public instantiation. */
  private BuiltInTypeModelAccess() {
    load();
  }

  /**
   * Singleton instance accessor.
   *
   * @return The singleton instance of the built-in type model.
   */
  public static BuiltInTypeModelAccess getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gets the URI of the resource that contains the built-in type-model elements.
   *
   * @return the URI of the resource that contains the built-in type-model elements, never {@code null}
   */
  public URI getBuiltInElementsResourceURI() {
    return MODEL_URI;
  }

  /**
   * Load the concrete meta model instance from modelLocation.
   */
  private synchronized void load() {
    if (model == null) {
      URI modelURI = getBuiltInElementsResourceURI();
      try {
        ResourceSet rs = new ResourceSetImpl();
        Resource resource = rs.createResource(modelURI);
        // Stand-alone builder cannot handle platform:/plugin/ URIs...
        final InputStream is = BuiltInTypeModelAccess.class.getClassLoader().getResourceAsStream(MODEL_LOCATION);
        resource.load(is, null);
        EcoreUtil.resolveAll(resource);
        model = (BuiltInTypeModel) resource.getContents().get(0);
        // CHECKSTYLE:CHECK-OFF IllegalCatch
        // We *do* want to catch any exception here because we are in construction and need to initialize with something
      } catch (Exception ex) {
        // CHECKSTYLE:CHECK-ON IllegalCatch
        LOGGER.error("Error loading metamodel from " + modelURI, ex); //$NON-NLS-1$
        // Create an empty model...
        model = BuiltInTypeModelPackage.eINSTANCE.getBuiltInTypeModelFactory().createBuiltInTypeModel();
      }
    }
    GlobalResources.INSTANCE.addResource(model.eResource());
    for (InternalType type : model.getInternalTypes()) {
      String typeName = type.getName();
      if (!Strings.isEmpty(typeName)) {
        internalTypesByName.put(typeName, type);
      } else {
        LOGGER.error("incomplete internal type in " + MODEL_LOCATION); //$NON-NLS-1$
      }
    }
  }

  /**
   * Gets the internal type model.
   * Package access for internal use, in particular, testing.
   *
   * @return the internal type model if loaded, null otherwise
   */
  BuiltInTypeModel getModel() {
    return model;
  }

  /**
   * Gets an internal type's instance by its name.
   * <p>
   * The implementation guarantees that there is exactly one instance of each validly named, internal type, that is two invocations with the same {@code name}
   * return the same instance. More specifically<br>
   * for all x, x != {@code null} and x != "" implies getInternalType(x) == getInternalType(x).
   * </p>
   *
   * @param name
   *          The internal type's name
   * @return The instance of the type with {@code name} or {@code null} if {@code name} == {@code null}, {@code name} == "", or there is no type with
   *         {@code name}
   */
  public INamedType getInternalType(final String name) {
    if (!Strings.isEmpty(name)) {
      return internalTypesByName.get(name);
    } else {
      return null;
    }
  }

}
