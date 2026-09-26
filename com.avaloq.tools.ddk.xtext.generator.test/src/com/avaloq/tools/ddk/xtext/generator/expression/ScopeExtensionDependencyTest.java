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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.scope.ScopeStandaloneSetup;
import com.google.inject.Injector;


/**
 * Tests that the classes named in {@code extension} declarations of a scope model, and of the scope models it includes, are
 * imported names of the model, so that a change to such a class affects it.
 */
@SuppressWarnings("nls")
public class ScopeExtensionDependencyTest {

  private static final String ECORE_IMPORT = "\n\nimport \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\n\n";

  private static final String BASE = "scoping test.Base" + ECORE_IMPORT + "extension com::acme::util::BaseExtensions\n";

  private static final String MAIN = "scoping test.Main with test.Base" + ECORE_IMPORT + "extension com::acme::^scope::MainExtensions\n";

  private static final String PLAIN = "scoping test.Plain" + ECORE_IMPORT;

  private static final String MAIN_EXTENSION_CLASS = "com.acme.scope.MainExtensions";

  private final Injector injector = new ScopeStandaloneSetup().createInjectorAndDoEMFRegistration();

  @Test
  void testOwnAndIncludedExtensionClassesAreImportedNames() throws IOException {
    final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    load(resourceSet, "Base", BASE);
    final Resource main = load(resourceSet, "Main", MAIN);
    EcoreUtil.resolveAll(resourceSet);
    final IResourceDescription description = describe(main);
    assertTrue(imports(description, MAIN_EXTENSION_CLASS), "The model's own extension class must be an imported name.");
    assertTrue(imports(description, "com.acme.util.BaseExtensions"), "The included model's extension class must be an imported name.");
  }

  @Test
  void testModelWithoutExtensionsImportsNoExtensionClass() throws IOException {
    final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    final Resource plain = load(resourceSet, "Plain", PLAIN);
    EcoreUtil.resolveAll(resourceSet);
    assertFalse(imports(describe(plain), MAIN_EXTENSION_CLASS));
  }

  private static Resource load(final XtextResourceSet resourceSet, final String name, final String source) throws IOException {
    final Resource resource = resourceSet.createResource(URI.createURI("memory:/" + name + ".scope"));
    resource.load(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), null);
    return resource;
  }

  private IResourceDescription describe(final Resource resource) {
    return injector.getInstance(IResourceDescription.Manager.class).getResourceDescription(resource);
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
