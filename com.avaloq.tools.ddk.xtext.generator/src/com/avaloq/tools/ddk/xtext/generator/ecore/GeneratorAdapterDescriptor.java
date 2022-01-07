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

package com.avaloq.tools.ddk.xtext.generator.ecore;

import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.mwe2.ecore.CvsIdFilteringGeneratorAdapterFactoryDescriptor;

import com.google.common.base.Function;


/**
 * Custom EMF GenModel generator adapter using one of
 * <ul>
 * <li>{@link com.avaloq.tools.ddk.xtext.generator.ecore.CustomClassEcoreGeneratorFragment.TypeMapper CustomClassEcoreGeneratorFragment.TypeMapper}</li>
 * <li>{@link com.avaloq.tools.ddk.xtext.generator.ecore.CustomClassEMFGeneratorFragment2.TypeMapper CustomClassEMFGeneratorFragment2.TypeMapper}</li>
 * </ul>
 * to make EMF properly generate import statements for the custom classes.
 */
class GeneratorAdapterDescriptor extends CvsIdFilteringGeneratorAdapterFactoryDescriptor {

  /**
   * Custom EMF GenModel generator adapter using one of
   * <ul>
   * <li>{@link com.avaloq.tools.ddk.xtext.generator.ecore.CustomClassEcoreGeneratorFragment.TypeMapper CustomClassEcoreGeneratorFragment.TypeMapper}</li>
   * <li>{@link com.avaloq.tools.ddk.xtext.generator.ecore.CustomClassEMFGeneratorFragment2.TypeMapper CustomClassEMFGeneratorFragment2.TypeMapper}</li>
   * </ul>
   * to make EMF properly generate import statements for the custom classes.
   */
  private final class CustomImplAwareGeneratorAdapterFactory extends IdFilteringGenModelGeneratorAdapterFactory {
    @Override
    public Adapter createGenClassAdapter() {
      return new IdFilteringGenClassAdapter(this) {
        @Override
        protected void createImportManager(final String packageName, final String className) {
          importManager = new CustomImportManager(packageName, typeMapper);
          importManager.addMasterImport(packageName, className);
          if (generatingObject != null) {
            ((GenBase) generatingObject).getGenModel().setImportManager(importManager);
          }
        }
      };
    }

    @Override
    public Adapter createGenEnumAdapter() {
      return new IdFilteringGenEnumAdapter(this) {
        @Override
        protected void createImportManager(final String packageName, final String className) {
          importManager = new CustomImportManager(packageName, typeMapper);
          importManager.addMasterImport(packageName, className);
          if (generatingObject != null) {
            ((GenBase) generatingObject).getGenModel().setImportManager(importManager);
          }
        }
      };
    }

    @Override
    public Adapter createGenModelAdapter() {
      if (genModelGeneratorAdapter == null) {
        genModelGeneratorAdapter = new GenModelGeneratorAdapter(this) {
          @Override
          protected void createImportManager(final String packageName, final String className) {
            importManager = new CustomImportManager(packageName, typeMapper);
            importManager.addMasterImport(packageName, className);
            if (generatingObject != null) {
              ((GenBase) generatingObject).getGenModel().setImportManager(importManager);
            }
          }

          /**
           * We handle this one on our own, see
           * {@link org.eclipse.xtext.generator.ecore.CvsIdFilteringGeneratorAdapterFactoryDescriptor.IdFilteringGenModelGeneratorAdapterFactory# createGenModelAdapter()}
           * . Note that the original source comment vanishes in de-compiled Java code.
           */
          @Override
          protected void generateModelPluginProperties(final GenModel genModel, final org.eclipse.emf.common.util.Monitor monitor) {
            // Do nothing.
          }
        };
      }
      return genModelGeneratorAdapter;
    }

    @Override
    public Adapter createGenPackageAdapter() {
      return new IdFilteringGenPackageAdapter(this) {
        @Override
        protected void createImportManager(final String packageName, final String className) {
          importManager = new CustomImportManager(packageName, typeMapper);
          importManager.addMasterImport(packageName, className);
          if (generatingObject != null) {
            ((GenBase) generatingObject).getGenModel().setImportManager(importManager);
          }
        }
      };
    }
  }

  private final Function<String, String> typeMapper;
  private final Set<String> loggedTypeMappings;
  private static final Logger LOGGER = LogManager.getLogger(GeneratorAdapterDescriptor.class);

  protected GeneratorAdapterDescriptor(final Set<String> loggedTypeMappings, final Function<String, String> typeMapper) { // NOPMD
    this.typeMapper = typeMapper;
    this.loggedTypeMappings = loggedTypeMappings;
  }

  @Override
  public GeneratorAdapterFactory createAdapterFactory() {
    return new CustomImplAwareGeneratorAdapterFactory();
  }

  /**
   * Adapted EMF ImportManager which returns the name of the custom classes as returned by the given type mapping function.
   */
  protected class CustomImportManager extends ImportManager {

    private final Function<String, String> typeMapper;

    public CustomImportManager(final String compilationUnitPackage, final Function<String, String> typeMapper) {
      super(compilationUnitPackage);
      this.typeMapper = typeMapper;
    }

    @Override
    public String getImportedName(final String qualifiedName, final boolean autoImport) {
      String mapped = typeMapper.apply(qualifiedName);
      if (mapped != null) {
        if (loggedTypeMappings.add(qualifiedName)) {
          LOGGER.debug("mapping " + qualifiedName + " to " + mapped); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return super.getImportedName(mapped, autoImport);
      } else {
        return super.getImportedName(qualifiedName, autoImport);
      }
    }
  }

}
