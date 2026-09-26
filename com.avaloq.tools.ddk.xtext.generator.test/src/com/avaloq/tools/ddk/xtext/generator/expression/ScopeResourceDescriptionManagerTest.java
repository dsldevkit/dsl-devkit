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
package com.avaloq.tools.ddk.xtext.generator.expression;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.scope.ScopeStandaloneSetup;
import com.google.inject.Injector;


/**
 * Tests that the Java types a scope model and the scope models it includes name are imported names of the model, so that a
 * change to such a type affects it.
 */
@SuppressWarnings("nls")
public class ScopeResourceDescriptionManagerTest {

  private static final String ECORE_IMPORT = "\n\nimport \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\n\n";

  private static final String BASE = "scoping test.Base" + ECORE_IMPORT + "extension com::acme::util::BaseExtensions\n\n"
      + "inject com.acme.util.Helper as helper\n\n" + "naming {\n  ecore::EClass = factory com::acme::util::Names.nameOf(this);\n}\n";

  private static final String MAIN = "scoping test.Main with test.Base" + ECORE_IMPORT + "extension com::acme::^scope::MainExtensions\n";

  private static final String PLAIN = "scoping test.Plain" + ECORE_IMPORT;

  private static final String MAIN_EXTENSION_CLASS = "com.acme.scope.MainExtensions";

  private static final String INCLUDED_EXTENSION_CLASS = "com.acme.util.BaseExtensions";

  private static final String INJECTED_TYPE = "com.acme.util.Helper";

  private static final String FACTORY_CLASS = "com.acme.util.Names";

  private static final String NOT_NAMED = "A type the model does not name must not be an imported name.";

  /** Resources are created with this injector rather than through the global registry, which the shared test runtime uses. */
  private static final Injector INJECTOR = new ScopeStandaloneSetup().createInjector();

  @Test
  public void testOwnAndIncludedDependenciesAreImportedNames() throws IOException {
    final IResourceDescription main = describe(loadAll().main());
    assertTrue(imports(main, MAIN_EXTENSION_CLASS), "The model's own extension class must be an imported name.");
    assertTrue(imports(main, INCLUDED_EXTENSION_CLASS), "The included model's extension class must be an imported name.");
    assertTrue(imports(main, INJECTED_TYPE), "A type injected by an included model must be an imported name.");
    assertTrue(imports(main, FACTORY_CLASS), "A factory type named in an included model must be an imported name.");
  }

  @Test
  public void testChangedTypesAffectModel() throws IOException {
    final IResourceDescription main = describe(loadAll().main());
    assertTrue(isAffected(MAIN_EXTENSION_CLASS, main), "A change to the model's own extension class must affect it.");
    assertTrue(isAffected(INJECTED_TYPE, main), "A change to a type only an included model names must affect the model.");
  }

  @Test
  public void testModelWithoutDependenciesIsNotAffected() throws IOException {
    final IResourceDescription plain = describe(loadAll().plain());
    assertFalse(imports(plain, MAIN_EXTENSION_CLASS), NOT_NAMED);
    assertFalse(imports(plain, FACTORY_CLASS), NOT_NAMED);
    assertFalse(isAffected(INCLUDED_EXTENSION_CLASS, plain), "A change to a type named only by other models must not affect the model.");
  }

  /** The three models, loaded into one resource set so that names leaking between models would be detected. */
  private record Models(Resource main, Resource plain) {
  }

  private static Models loadAll() throws IOException {
    final XtextResourceSet resourceSet = INJECTOR.getInstance(XtextResourceSet.class);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("scope", INJECTOR.getInstance(IResourceFactory.class));
    final Resource base = load(resourceSet, "Base", BASE);
    final Resource main = load(resourceSet, "Main", MAIN);
    final Resource plain = load(resourceSet, "Plain", PLAIN);
    EcoreUtil.resolveAll(resourceSet);
    for (final Resource resource : List.of(base, main, plain)) {
      assertTrue(resource.getErrors().isEmpty(), () -> "The model must parse: " + resource.getURI() + " " + resource.getErrors());
    }
    return new Models(main, plain);
  }

  private static Resource load(final XtextResourceSet resourceSet, final String name, final String source) throws IOException {
    final Resource resource = resourceSet.createResource(URI.createURI("memory:/" + name + ".scope"));
    resource.load(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), null);
    return resource;
  }

  private static IResourceDescription describe(final Resource resource) {
    return INJECTOR.getInstance(IResourceDescription.Manager.class).getResourceDescription(resource);
  }

  private static boolean isAffected(final String fqn, final IResourceDescription candidate) {
    return INJECTOR.getInstance(IResourceDescription.Manager.class).isAffected(List.of(javaTypeChange(fqn)), candidate,
        new ResourceDescriptionsData(List.of(candidate)));
  }

  /**
   * Returns a change of the given Java type shaped like the ones JDT reports: the resource URI {@code java:/Objects/<FQN>},
   * exporting one object named by the dot-separated fully qualified name.
   */
  private static IResourceDescription.Delta javaTypeChange(final String fqn) {
    final URI uri = URI.createURI("java:/Objects/" + fqn);
    final JvmGenericType type = TypesFactory.eINSTANCE.createJvmGenericType();
    ((InternalEObject) type).eSetProxyURI(uri.appendFragment(fqn));
    final IEObjectDescription exported = EObjectDescription.create(QualifiedName.create(fqn.split("\\.")), type);
    final IResourceDescription changed = new AbstractResourceDescription() {
      @Override
      protected List<IEObjectDescription> computeExportedObjects() {
        return List.of(exported);
      }

      @Override
      public Iterable<QualifiedName> getImportedNames() {
        return List.of();
      }

      @Override
      public Iterable<IReferenceDescription> getReferenceDescriptions() {
        return List.of();
      }

      @Override
      public URI getURI() {
        return uri;
      }
    };
    return new DefaultResourceDescriptionDelta(null, changed);
  }

  private static boolean imports(final IResourceDescription description, final String fqn) {
    final QualifiedName expected = QualifiedName.create(fqn.split("\\.")).toLowerCase();
    for (final QualifiedName name : description.getImportedNames()) {
      if (expected.equals(name)) {
        return true;
      }
    }
    return false;
  }
}
