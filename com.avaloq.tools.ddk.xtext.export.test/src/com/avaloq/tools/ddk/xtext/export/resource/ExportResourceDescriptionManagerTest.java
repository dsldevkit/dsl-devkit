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
package com.avaloq.tools.ddk.xtext.export.resource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;


/**
 * Tests that the classes named in {@code extension} declarations are dependencies of an export model: a change to such a
 * class must make the model affected.
 */
@SuppressWarnings("nls")
public class ExportResourceDescriptionManagerTest extends AbstractXtextTest {

  /** Fully qualified name of the extension class; it need not exist, since the dependency is recorded by name. */
  private static final String EXTENSION_CLASS = "com.acme.export.util.NamingExtensions";

  private static final String WITH_EXTENSION = "WithExtension";

  private static final String ECORE_IMPORT = "import \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\n\n";

  private static final String ECLASS_EXPORT = "export ecore::EClass as name\n";

  private static final String MODEL_WITH_EXTENSION = ECORE_IMPORT + "extension com::acme::^export::util::NamingExtensions\n\n" + ECLASS_EXPORT;

  private static final String MODEL_WITHOUT_EXTENSION = ECORE_IMPORT + ECLASS_EXPORT;

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  /**
   * This test builds its sources in memory and has no test source file. {@inheritDoc}
   */
  @Override
  protected String getTestSourceFileName() {
    return null;
  }

  @Test
  public void testExtensionClassIsImportedName() throws IOException {
    final IResourceDescription description = describe(WITH_EXTENSION, MODEL_WITH_EXTENSION);
    final QualifiedName expected = QualifiedName.create(EXTENSION_CLASS.split("\\.")).toLowerCase();
    boolean found = false;
    for (final QualifiedName name : description.getImportedNames()) {
      found |= expected.equals(name);
    }
    assertTrue(found, "The extension class must be an imported name of the export model.");
  }

  @Test
  public void testChangedExtensionClassAffectsModel() throws IOException {
    final XtextResource resource = parse(WITH_EXTENSION, MODEL_WITH_EXTENSION);
    final IResourceDescription.Manager manager = resource.getResourceServiceProvider().getResourceDescriptionManager();
    assertInstanceOf(ExportResourceDescriptionManager.class, manager, "Export must bind its own resource description manager.");
    final IResourceDescription candidate = manager.getResourceDescription(resource);
    assertTrue(manager.isAffected(List.of(javaTypeChange(EXTENSION_CLASS)), candidate, new ResourceDescriptionsData(List.of(candidate))),
        "A change to an extension class must affect the export model.");
  }

  @Test
  public void testChangedClassDoesNotAffectModelWithoutExtension() throws IOException {
    final XtextResource resource = parse("WithoutExtension", MODEL_WITHOUT_EXTENSION);
    final IResourceDescription.Manager manager = resource.getResourceServiceProvider().getResourceDescriptionManager();
    final IResourceDescription candidate = manager.getResourceDescription(resource);
    assertFalse(manager.isAffected(List.of(javaTypeChange(EXTENSION_CLASS)), candidate, new ResourceDescriptionsData(List.of(candidate))),
        "A class the export model does not declare must not affect it.");
  }

  private IResourceDescription describe(final String name, final String source) throws IOException {
    final XtextResource resource = parse(name, source);
    return resource.getResourceServiceProvider().getResourceDescriptionManager().getResourceDescription(resource);
  }

  /**
   * Returns a change of the given Java type shaped like the ones JDT reports: the resource URI {@code java:/Objects/<FQN>},
   * exporting one object named by the fully qualified name.
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

  /**
   * Parses the given export model with its derived state installed. The inferrer resolves the grammar of an export model by
   * loading the {@code .xtext} file next to it, so an empty resource is registered under that URI.
   */
  private XtextResource parse(final String name, final String source) throws IOException {
    final URI modelUri = getTargetSourceUri(name + ".export");
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
    resource.load(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), null);
    EcoreUtil.resolveAll(resource);
    return resource;
  }
}
