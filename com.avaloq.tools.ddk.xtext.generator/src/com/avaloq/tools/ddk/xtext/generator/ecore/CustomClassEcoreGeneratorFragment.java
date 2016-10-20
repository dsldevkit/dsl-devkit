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

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.mwe2.ecore.CvsIdFilteringGeneratorAdapterFactoryDescriptor;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.ecore.EcoreGeneratorFragment;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * This fragment is an alternative to the default {@link EcoreGeneratorFragment}. In addition it also supports the generation gap pattern with FooImplCustom
 * classes in the {@link #getJavaModelSrcDirectory() source directory}.
 * <p>
 * This class will also register any referenced / generated GenModels in the global URI map, where other fragments can find them (see GeneratorUtil and
 * GenModels classes). Also see https://bugs.eclipse.org/bugs/show_bug.cgi?id=329741 for details.
 * <p>
 * A lot of the code here originates from org.eclipse.emf.mwe2.ecore.EcoreGenerator which is a workflow component (not a generator fragment!) implementing the
 * same basic idea. This component can however only be used for hand maintained Ecore models, whereas this fragment works equally well with Xtext generated
 * Ecore models (and GenModels).
 */
@SuppressWarnings("deprecation")
// TODO: DSL-369: figure out how to refactor this properly to not use deprecated stuff
public class CustomClassEcoreGeneratorFragment extends EcoreGeneratorFragment {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(CustomClassEcoreGeneratorFragment.class);
  private final Set<String> loggedTypeMappings = Sets.newHashSet();

  private final List<String> javaModelSrcDirectories = Lists.newArrayList();

  private boolean generateCustomClasses;
  private boolean generateNonNLSMarkers;

  /**
   * Class to map to the implementation class for a given EClass. The implementation will check for the existence of a class FooImplCustom in the SRC directory
   * and return that if it exists. Otherwise simply FooImpl. Also it will generate a stub of FooImplCustom if
   * {@link CustomClassEcoreGeneratorFragment#isGenerateCustomClasses() required}.
   */
  protected final class TypeMapper implements Function<String, String> {
    private final List<String> baseDirs;

    public TypeMapper(final List<String> baseDirs) {
      this.baseDirs = baseDirs;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("nls")
    public String apply(final String from) {
      if (from.startsWith("org.eclipse.emf.ecore")) {
        return null;
      }
      for (String dir : baseDirs) {
        URI createURI = URI.createURI(dir + "/" + from.replace('.', '/') + "Custom.java");
        String customClassName = from + "Custom";
        if (URIConverter.INSTANCE.exists(createURI, null)) {
          return customClassName;
        }
        if (from.endsWith("Impl") && isGenerateCustomClasses()) {
          generateCustomClassStub(from, customClassName, createURI);
          return customClassName;
        }
      }
      return null;
    }
  }

  /**
   * Custom EMF GenModel generator adapter using the {@link TypeMapper} to make EMF properly generate import statements for the custom classes.
   */
  protected class GeneratorAdapterDescriptor extends CvsIdFilteringGeneratorAdapterFactoryDescriptor {

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

    protected GeneratorAdapterDescriptor(final Function<String, String> typeMapper) { // NOPMD
      this.typeMapper = typeMapper;
    }

    @Override
    public GeneratorAdapterFactory createAdapterFactory() {
      return new CustomImplAwareGeneratorAdapterFactory();
    }
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
          LOGGER.debug("mapping " + qualifiedName + " to " + mapped); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return super.getImportedName(mapped, autoImport);
      } else {
        return super.getImportedName(qualifiedName, autoImport);
      }
    }
  }

  /**
   * A list of Java source folders containing potential *ImplCustom classes. This needs to be a list as extended types may generate into source folders of other
   * plug-ins.
   *
   * @param dir
   *          java source folder to add
   */
  public void addJavaModelSrcDirectory(final String dir) {
    javaModelSrcDirectories.add(dir);
  }

  /**
   * Gets the java model source directory.
   * Analog {@link #getJavaModelDirectory(XpandExecutionContext)} from superclass, but for src instead of src-gen
   *
   * @return the java model source directory
   */
  public List<String> getJavaModelSrcDirectories() {
    return javaModelSrcDirectories;
  }

  public void setGenerateCustomClasses(final boolean value) {
    generateCustomClasses = value;
  }

  public boolean isGenerateCustomClasses() {
    return generateCustomClasses;
  }

  public void setGenerateNonNLSMarkers(final boolean value) {
    generateNonNLSMarkers = value;
  }

  public boolean isGenerateNonNLSMarkers() {
    return generateNonNLSMarkers;
  }

  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (javaModelSrcDirectories.isEmpty()) {
      javaModelSrcDirectories.add(ctx.getOutput().getOutlet(org.eclipse.xtext.generator.Generator.SRC).getPath());
    }
    super.generate(grammar, ctx);
  }

  @Override
  protected void doGenerate(final GenModel genModel) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("generating EMF code for " + genModel.eResource().getURI()); //$NON-NLS-1$
    }

    Generator generator = new Generator() {
      @Override
      public JControlModel getJControlModel() {
        return new JControlModel() {
          @Override
          public boolean canMerge() {
            return false;
          }
        };
      }
    };

    genModel.setNonNLSMarkers(generateNonNLSMarkers);

    generator.getAdapterFactoryDescriptorRegistry().addDescriptor(GenModelPackage.eNS_URI, new GeneratorAdapterDescriptor(getTypeMapper()));
    generator.setInput(genModel);

    Diagnostic diagnostic = generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, new BasicMonitor());

    if (diagnostic.getSeverity() != Diagnostic.OK) {
      LOGGER.info(diagnostic);
    }

    if (isGenerateEdit()) {
      Diagnostic editDiag = generator.generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, new BasicMonitor());
      if (editDiag.getSeverity() != Diagnostic.OK) {
        LOGGER.info(editDiag);
      }
    }

    if (isGenerateEditor()) {
      Diagnostic editorDiag = generator.generate(genModel, GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, new BasicMonitor());
      if (editorDiag.getSeverity() != Diagnostic.OK) {
        LOGGER.info(editorDiag);
      }
    }
  }

  @Override
  protected String getTemplate() {
    return EcoreGeneratorFragment.class.getName().replaceAll("\\.", "::"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  protected Function<String, String> getTypeMapper() {
    return new TypeMapper(getJavaModelSrcDirectories());
  }

  /**
   * Generates a stub for a custom class (e.g. FooImplCustom) into the {@link CustomClassEcoreGeneratorFragment#javaModelSrcDirectories specified SRC folder}.
   *
   * @param from
   *          qualified name of default implementation class (e.g. FooImpl) to extend
   * @param customClassName
   *          qualified name of custom class to generate
   * @param path
   *          URI for resource to generate into
   */
  @SuppressWarnings("nls")
  protected void generateCustomClassStub(final String from, final String customClassName, final URI path) {
    StringBuilder sb = new StringBuilder();
    // sb.append(copyright()).append("\n");
    int lastIndexOfDot = customClassName.lastIndexOf('.');
    sb.append("package ").append(customClassName.substring(0, lastIndexOfDot)).append(";\n\n\n");
    sb.append("public class ").append(customClassName.substring(lastIndexOfDot + 1)).append(" extends ").append(from).append(" {\n\n");
    sb.append("}\n");

    try {
      OutputStream stream = URIConverter.INSTANCE.createOutputStream(path);
      stream.write(sb.toString().getBytes());
      stream.close();
    } catch (IOException e) {
      throw new WrappedException(e);
    }
  }

}
