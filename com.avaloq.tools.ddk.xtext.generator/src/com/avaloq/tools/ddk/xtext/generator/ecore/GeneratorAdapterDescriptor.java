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

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.mwe2.ecore.CvsIdFilteringGeneratorAdapterFactoryDescriptor;

import com.avaloq.tools.ddk.xtext.generator.ecore.CustomClassEcoreGeneratorFragment.TypeMapper;
import com.google.common.base.Function;


/**
 * Custom EMF GenModel generator adapter using the {@link TypeMapper} to make EMF properly generate import statements for the custom classes.
 */
class GeneratorAdapterDescriptor extends CvsIdFilteringGeneratorAdapterFactoryDescriptor {

  /**
   * Custom EMF GenModel generator adapter factory using the {@link TypeMapper} to make EMF properly generate import statements for the custom classes.
   */
  private final class CustomImplAwareGeneratorAdapterFactory extends IdFilteringGenModelGeneratorAdapterFactory {
    @Override
    public Adapter createGenClassAdapter() {
      return new IdFilteringGenClassAdapter(this) {
        @Override
        protected void createImportManager(final String packageName, final String className) {
          importManager = new ImportManagerHack(packageName, typeMapper);
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
          importManager = new ImportManagerHack(packageName, typeMapper);
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
            importManager = new ImportManagerHack(packageName, typeMapper);
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
          importManager = new ImportManagerHack(packageName, typeMapper);
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
  private final Logger logger;

  protected GeneratorAdapterDescriptor(final Set<String> loggedTypeMappings, final Function<String, String> typeMapper, final Logger logger) { // NOPMD
    this.typeMapper = typeMapper;
    this.loggedTypeMappings = loggedTypeMappings;
    this.logger = logger;
  }

  @Override
  public GeneratorAdapterFactory createAdapterFactory() {
    return new CustomImplAwareGeneratorAdapterFactory();
  }

  /**
   * Hacked EMF ImportManager which returns the name of the custom classes as returned by the {@link TypeMapper}.
   */
  protected class ImportManagerHack extends ImportManager {

    private final Function<String, String> typeMapper;

    public ImportManagerHack(final String compilationUnitPackage, final Function<String, String> typeMapper) {
      super(compilationUnitPackage);
      this.typeMapper = typeMapper;
    }

    @Override
    public String getImportedName(final String qualifiedName, final boolean autoImport) {
      String mapped = typeMapper.apply(qualifiedName);
      if (mapped != null) {
        if (loggedTypeMappings.add(qualifiedName)) {
          logger.debug("mapping " + qualifiedName + " to " + mapped); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return super.getImportedName(mapped, autoImport);
      } else {
        return super.getImportedName(qualifiedName, autoImport);
      }
    }
  }

}
