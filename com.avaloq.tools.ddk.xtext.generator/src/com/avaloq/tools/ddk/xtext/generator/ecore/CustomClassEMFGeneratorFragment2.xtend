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

package com.avaloq.tools.ddk.xtext.generator.ecore

import com.google.common.base.Function
import com.google.common.collect.Lists
import com.google.common.collect.Sets
import java.io.IOException
import java.io.OutputStream
import java.util.List
import java.util.Set
import org.apache.log4j.Logger
import org.eclipse.emf.codegen.ecore.generator.Generator
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter
import org.eclipse.emf.codegen.merge.java.JControlModel
import org.eclipse.emf.common.util.BasicMonitor
import org.eclipse.emf.common.util.Diagnostic
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.common.util.WrappedException
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.URIConverter
import org.eclipse.emf.mwe.core.ConfigurationException
import org.eclipse.emf.mwe.utils.GenModelHelper
import org.eclipse.emf.mwe.utils.StandaloneSetup
import org.eclipse.xtext.generator.GenModelAccess
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.xtext.generator.ecore.EMFGeneratorFragment2

/**
 * This fragment is our previous alternative to the default {@link org.eclipse.xtext.generator.ecore.EcoreGeneratorFragment} migrated to Fragment2. In addition it also supports the generation gap pattern with FooImplCustom
 * classes in the {@link #getJavaModelSrcDirectory() source directory}.
 * <p>
 * This class will also register any referenced / generated GenModels in the global URI map, where other fragments can find them (see GeneratorUtil and
 * GenModels classes). Also see https://bugs.eclipse.org/bugs/show_bug.cgi?id=329741 for details.
 * <p>
 * A lot of the code here originates from org.eclipse.emf.mwe2.ecore.EcoreGenerator which is a workflow component (not a generator fragment!) implementing the
 * same basic idea. This component can however only be used for hand maintained Ecore models, whereas this fragment works equally well with Xtext generated
 * Ecore models (and GenModels).
 * <p>
 * To enable the migration to Format2, some of the code here has been copied from org.eclipse.xtext.generator.ecore.EcoreGeneratorFragment which is now deprecated - any methods that were marked deprecated
 * in have been left deprecated here, and we should move to using the suggested replacements in some future cleanup.
 */
@SuppressWarnings("deprecation")
// TODO: DSL-369: figure out how to refactor this properly to not use deprecated stuff
class CustomClassEMFGeneratorFragment2 extends EMFGeneratorFragment2 {

  /** Class-wide logger. */
  val static Logger LOGGER = Logger.getLogger(CustomClassEcoreGeneratorFragment);
  val Set<String> loggedTypeMappings = Sets.newHashSet();

  val List<String> javaModelSrcDirectories = Lists.newArrayList();

  var boolean generateCustomClasses;
  var boolean generateNonNLSMarkers;
  var String referencedGenModels;

  /** Added because base class does not provide getter */
  var boolean generateEdit = false
  var boolean generateEditor = false

  new () {
    super.setUpdateBuildProperties(false);
  }

  /** Override because base class does not provide getter */
  override setGenerateEdit(boolean genEdit) {
    generateEdit = genEdit
    super.generateEdit = genEdit
  }

  /** Override because base class does not provide getter */
  override setGenerateEditor(boolean genEditor) {
    generateEditor = genEditor
    super.generateEditor = genEditor
  }

  override setUpdateBuildProperties(boolean updateBuildProperties) {
    // to recreate the behavior of EcoreGeneratorFragment, this property is always false
    super.setUpdateBuildProperties(false);
  }

  def String getReferencedGenModels() {
    return referencedGenModels;
  }

  /**
   * Use {@link GenModelAccess#getGenPackage(EPackage)}
   */
  @Deprecated
  def protected List<GenPackage> loadReferencedGenModels(ResourceSet rs) {
    val List<GenPackage> result = Lists.newArrayList();
    if (getReferencedGenModels() !== null) {
      for (String uri : getReferencedGenModels().split(",")) {
        try {
          val Resource resource = rs.getResource(URI.createURI(uri.trim()), true);
          val GenModel genmodel = resource.getContents().get(0) as GenModel;
          val EList<GenPackage> genPackages = genmodel.getGenPackages();
          for (GenPackage genPackage : genPackages) {
            genPackage.getEcorePackage().getEClassifiers();
            result.add(genPackage);
          }
        } catch (Exception e) {
          LOGGER.error("Couldn't find genmodel for uri '" + uri + "'");
          throw new WrappedException(e);
        }
      }
    }
    return result;
  }

  /**
   * use {@link StandaloneSetup#addRegisterGenModelFile(String)}
   */
  @Deprecated
  def void setReferencedGenModels(String referencedGenModel) {
    if ("".equals(referencedGenModel)) {
      return;
    }
    this.referencedGenModels = referencedGenModel;
    LOGGER.warn("The property 'referencedGenModels' is deprecated. Please use 'StandaloneSetup.registerGenModelFile' instead.");
  }

  /**
   * @since 2.0
   */
  @Deprecated
  def protected void registerReferencedGenModels() {
    try {
      if (getReferencedGenModels() !== null && getReferencedGenModels().length() > 0) {
        val ResourceSet rs = new XtextResourceSet();
        val GenModelHelper gmh = new GenModelHelper();
        for (String uriStr : getReferencedGenModels().split(",")) {
          val URI uri = URI.createURI(uriStr.trim());
          gmh.registerGenModel(rs, uri);
        }
      }
    } catch (ConfigurationException ce) {
      throw ce;
    } catch (Exception e) {
      LOGGER.error(e, e);
    }
  }

  /**
   * Sets the URIs for the generated EMF generator models (aka genmodels).
   * use {@link StandaloneSetup#addRegisterGenModelFile(String)}
   *
   * @param uris
   * @deprecated
   */
  @Deprecated
  def void setGenModels(String uris) {
    LOGGER.warn("The property 'genModels' is deprecated. Please use 'referencedGenModels' instead.");
    setReferencedGenModels(uris);
  }

  /**
   * Class to map to the implementation class for a given EClass. The implementation will check for the existence of a class FooImplCustom in the SRC directory
   * and return that if it exists. Otherwise simply FooImpl. Also it will generate a stub of FooImplCustom if
   * {@link CustomClassEcoreGeneratorFragment#isGenerateCustomClasses() required}.
   */
  protected final static class TypeMapper implements Function<String, String> {
    val List<String> baseDirs;
    val CustomClassEMFGeneratorFragment2 fragment

    new(CustomClassEMFGeneratorFragment2 fragment, List<String> baseDirs) {
      this.baseDirs = baseDirs
      this.fragment = fragment
    }

    /** {@inheritDoc} */
    @SuppressWarnings("nls")
    override apply(String from) {
      if (from.startsWith("org.eclipse.emf.ecore")) {
        return null;
      }
      for (String dir : baseDirs) {
        val URI createURI = URI.createURI(dir + "/" + from.replace('.', '/') + "Custom.java");
        val String customClassName = from + "Custom";
        if (URIConverter.INSTANCE.exists(createURI, null)) {
          return customClassName;
        }
        if (from.endsWith("Impl") && fragment.isGenerateCustomClasses()) {
          fragment.generateCustomClassStub(from, customClassName, createURI);
          return customClassName;
        }
      }
      return null;
    }
  }

  /**
   * A list of Java source folders containing potential *ImplCustom classes. This needs to be a list as extended types may generate into source folders of other
   * plug-ins.
   *
   * @param dir
   *          java source folder to add
   */
  def void addJavaModelSrcDirectory(String dir) {
    javaModelSrcDirectories.add(dir);
  }

  /**
   * Gets the java model source directory.
   * Analog {@link #getJavaModelDirectory(XpandExecutionContext)} from superclass, but for src instead of src-gen
   *
   * @return the java model source directory
   */
  def List<String> getJavaModelSrcDirectories() {
    return javaModelSrcDirectories;
  }

  def void setGenerateCustomClasses(boolean value) {
    generateCustomClasses = value;
  }

  def boolean isGenerateCustomClasses() {
    return generateCustomClasses;
  }

  def void setGenerateNonNLSMarkers(boolean value) {
    generateNonNLSMarkers = value;
  }

  def boolean isGenerateNonNLSMarkers() {
    return generateNonNLSMarkers;
  }


  override generate() {
    if (javaModelSrcDirectories.isEmpty()) {
      javaModelSrcDirectories.add(projectConfig.runtime.src.getPath());
    }
    // register all explicitly referenced genmodels
    // since this may cause side effects on global singeltons, we don't guard it
    registerReferencedGenModels();
    super.generate();
  }

  override protected doGenerate(GenModel genModel) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("generating EMF code for " + genModel.eResource().getURI()); //$NON-NLS-1$
    }

    val Generator generator = new Generator() {
      override getJControlModel() {
        return new JControlModel() {
          override canMerge() {
            return false;
          }
        };
      }
    };

    genModel.setNonNLSMarkers(generateNonNLSMarkers);

    generator.getAdapterFactoryDescriptorRegistry().addDescriptor(GenModelPackage.eNS_URI, new GeneratorAdapterDescriptor(loggedTypeMappings, getTypeMapper()));
    generator.setInput(genModel);

    val Diagnostic diagnostic = generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, new BasicMonitor());

    if (diagnostic.getSeverity() != Diagnostic.OK) {
      LOGGER.info(diagnostic);
    }

    if (generateEdit) {
      val Diagnostic editDiag = generator.generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, new BasicMonitor());
      if (editDiag.getSeverity() != Diagnostic.OK) {
        LOGGER.info(editDiag);
      }
    }

    if (generateEditor) {
      val Diagnostic editorDiag = generator.generate(genModel, GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, new BasicMonitor());
      if (editorDiag.getSeverity() != Diagnostic.OK) {
        LOGGER.info(editorDiag);
      }
    }
  }

  def protected Function<String, String> getTypeMapper() {
    return new TypeMapper(this, getJavaModelSrcDirectories());
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
  def protected void generateCustomClassStub(String from, String customClassName, URI path) {
    val StringBuilder sb = new StringBuilder();
    // sb.append(copyright()).append("\n");
    val int lastIndexOfDot = customClassName.lastIndexOf('.');
    sb.append("package ").append(customClassName.substring(0, lastIndexOfDot)).append(";\n\n\n");
    sb.append("public class ").append(customClassName.substring(lastIndexOfDot + 1)).append(" extends ").append(from).append(" {\n\n");
    sb.append("}\n");

    try {
      val OutputStream stream = URIConverter.INSTANCE.createOutputStream(path);
      stream.write(sb.toString().getBytes());
      stream.close();
    } catch (IOException e) {
      throw new WrappedException(e);
    }
  }

}
