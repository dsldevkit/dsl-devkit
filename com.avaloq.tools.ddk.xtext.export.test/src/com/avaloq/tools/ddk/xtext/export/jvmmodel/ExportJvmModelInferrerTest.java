/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.export.jvmmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;


/**
 * Tests the JVM model inferred for an export model without an {@code export &lt;name&gt; for &lt;grammar&gt;} header.
 * <p>
 * That header is optional, so {@code ExportModel.name} is {@code null} for such models. The documentation of the inferred
 * resource description manager must then be rendered with an empty name rather than with the string {@code "null"}.
 * </p>
 * <p>
 * The inferrer must also leave the {@link GenModelUtilX} context as it found it, since a context left set keeps the
 * model's resource reachable from the builder thread.
 * </p>
 */
@SuppressWarnings("nls")
public class ExportJvmModelInferrerTest extends AbstractXtextTest {

  /** Name of the header-less export model parsed by this test. */
  private static final String MODEL_NAME = "HeaderlessExportModel";

  /** Source of the header-less export model parsed by this test. */
  private static final String MODEL_SOURCE = "import \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\n"
      + "\n"
      + "export ecore::EClass as name\n";

  /** Simple name suffix of the inferred resource description manager. */
  private static final String MANAGER_SUFFIX = "ResourceDescriptionManager";

  /** Documentation expected on the inferred resource description manager; the name is empty, hence the two blanks. */
  private static final String EXPECTED_DOCUMENTATION = "Resource description manager for  resources.";

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  /**
   * This test builds its source in memory and has no test source file. {@inheritDoc}
   */
  @Override
  protected String getTestSourceFileName() {
    return null;
  }

  @Test
  public void testResourceDescriptionManagerDocumentationOfHeaderlessModel() throws IOException {
    final Resource resource = parseHeaderlessModel();
    final JvmGenericType manager = findInferredManager(resource);
    assertNotNull(manager, "The inferrer must infer a resource description manager for a header-less export model.");
    final JvmTypesBuilder typesBuilder = getXtextTestUtil().get(JvmTypesBuilder.class);
    assertEquals(EXPECTED_DOCUMENTATION, typesBuilder.getDocumentation(manager),
        "A null export model name must render as an empty string, not as \"null\".");
  }

  @Test
  public void testInferenceRestoresGenModelContext() throws IOException, ReflectiveOperationException {
    final Resource resource = parseHeaderlessModel();
    final ExportModel model = (ExportModel) resource.getContents().get(0);
    final ExportJvmModelInferrer inferrer = getXtextTestUtil().get(ExportJvmModelInferrer.class);
    final GenModelUtilX genModelUtil = genModelUtilOf(inferrer);
    final Resource previousContext = new ResourceImpl(URI.createURI("memory:/previous-context"));
    genModelUtil.setResource(previousContext);
    try {
      final RecordingAcceptor acceptor = new RecordingAcceptor(resource);
      // as the JVM model associator does before inferring; setContext is package-private
      final Method setContext = AbstractModelInferrer.class.getDeclaredMethod("setContext", Resource.class);
      setContext.setAccessible(true);
      setContext.invoke(inferrer, resource);
      inferrer.infer(model, acceptor, false);
      assertSame(previousContext, genModelUtil.getContext(), "Inference must restore the previous GenModelUtilX context.");
      assertFalse(acceptor.initializers.isEmpty(), "The inferrer must register type initializers.");
      acceptor.initializers.forEach(Runnable::run);
      assertSame(previousContext, genModelUtil.getContext(), "Type initializers must restore the previous GenModelUtilX context.");
    } finally {
      genModelUtil.setResource(null);
    }
  }

  /**
   * Returns the {@link GenModelUtilX} the given inferrer sets its context on.
   *
   * @param inferrer
   *          the inferrer, must not be {@code null}
   * @return the inferrer's utility, never {@code null}
   * @throws ReflectiveOperationException
   *           if the field cannot be read
   */
  private static GenModelUtilX genModelUtilOf(final ExportJvmModelInferrer inferrer) throws ReflectiveOperationException {
    final Field field = ExportJvmModelInferrer.class.getDeclaredField("genModelUtil");
    field.setAccessible(true);
    return (GenModelUtilX) field.get(inferrer);
  }

  /**
   * Adds the accepted types to the resource, as the JVM model associator does, and records their initializers so that the
   * test can run them after inference.
   */
  private static final class RecordingAcceptor implements IJvmDeclaredTypeAcceptor {

    private final Resource resource;
    private final List<Runnable> initializers = new ArrayList<>();

    RecordingAcceptor(final Resource resource) {
      this.resource = resource;
    }

    @Override
    public <T extends JvmDeclaredType> IPostIndexingInitializing<T> accept(final T type) {
      resource.getContents().add(type);
      return initializer -> initializers.add(() -> initializer.apply(type));
    }

    @Override
    public <T extends JvmDeclaredType> void accept(final T type, final Procedure1<? super T> lateInitialization) {
      resource.getContents().add(type);
      if (lateInitialization != null) {
        initializers.add(() -> lateInitialization.apply(type));
      }
    }
  }

  /**
   * Parses the header-less export model and installs its derived state.
   * <p>
   * The inferrer resolves the grammar belonging to an export model by loading the {@code .xtext} file next to it. An empty
   * resource is registered under that URI so the lookup resolves without demand-loading a grammar; the documentation under
   * test does not depend on the grammar. Both resources have to share one resource set, hence the explicit set up here
   * instead of the usual test utility call, which creates a new resource set per invocation.
   *
   * @return the loaded resource, never {@code null}
   * @throws IOException
   *           if the model cannot be parsed
   */
  private Resource parseHeaderlessModel() throws IOException {
    final URI modelUri = getTargetSourceUri(MODEL_NAME + ".export");
    final URI grammarUri = modelUri.trimFileExtension().appendFileExtension("xtext");
    final Resource grammarResource = new ResourceImpl(grammarUri) {
      @Override
      public boolean isLoaded() {
        return true;
      }
    };
    final XtextResourceSet resourceSet = getXtextTestUtil().getResourceSet();
    resourceSet.getResources().add(grammarResource);
    resourceSet.getURIResourceMap().put(grammarUri, grammarResource);

    final XtextResource resource = (XtextResource) resourceSet.createResource(modelUri);
    resourceSet.getResources().add(resource);
    resource.load(new ByteArrayInputStream(MODEL_SOURCE.getBytes(StandardCharsets.UTF_8)), null);
    EcoreUtil.resolveAll(resource);
    return resource;
  }

  /**
   * Returns the inferred resource description manager of the given resource.
   *
   * @param resource
   *          the resource of the export model, must not be {@code null}
   * @return the inferred type, or {@code null} if the inferrer did not produce one
   */
  private JvmGenericType findInferredManager(final Resource resource) {
    for (final EObject content : resource.getContents()) {
      if (content instanceof JvmGenericType && ((JvmGenericType) content).getSimpleName().endsWith(MANAGER_SUFFIX)) {
        return (JvmGenericType) content;
      }
    }
    return null;
  }

}
