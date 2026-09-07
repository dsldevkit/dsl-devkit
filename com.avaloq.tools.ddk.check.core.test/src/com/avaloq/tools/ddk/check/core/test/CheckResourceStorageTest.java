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
package com.avaloq.tools.ddk.check.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.resource.CheckBatchLinkableResourceStorageLoadable;
import com.avaloq.tools.ddk.check.resource.CheckBatchLinkableResourceStorageWritable;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * Tests that the binary model of a check catalog only contains the publicly visible API of the catalog and none of the implementation details of its checks.
 */
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckResourceStorageTest {

  private static final String MODEL_PATH = "storage/StorageChecks";
  private static final String RESOURCE_URI = "StorageChecks.check";

  /** Strings that only occur in the implementation of the catalog and must therefore not be persisted. */
  private static final String[] IMPLEMENTATION_MARKERS = { //
      "hidden-member-literal", "hidden-implementation-literal", "hidden-guard-literal", //
      "hiddenCatalogMember", "hiddenImplementation", "hiddenImplementationVariable"};

  @Inject
  private Injector injector;

  /**
   * Loads the test catalog from its source, including its inferred JVM model.
   *
   * @return the resource containing the test catalog, never {@code null}
   * @throws IOException
   *           if the test model cannot be read
   */
  private StorageAwareResource loadSource() throws IOException {
    XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    StorageAwareResource resource = (StorageAwareResource) resourceSet.createResource(URI.createURI(RESOURCE_URI));
    try (InputStream in = CheckResourceStorageTest.class.getResourceAsStream(MODEL_PATH)) {
      assertNotNull(in, "Test model must be available");
      resource.load(in, null);
    }
    EcoreUtil2.resolveLazyCrossReferences(resource, CancelIndicator.NullImpl);
    assertTrue(resource.getContents().stream().anyMatch(JvmType.class::isInstance), "Source resource should contain an inferred JVM model");
    return resource;
  }

  /**
   * Persists the given resource.
   *
   * @param resource
   *          the resource to persist, must not be {@code null}
   * @return the binary model, never {@code null}
   * @throws IOException
   *           if the resource cannot be persisted
   */
  private byte[] save(final StorageAwareResource resource) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    new CheckBatchLinkableResourceStorageWritable(out, false).writeResource(resource);
    return out.toByteArray();
  }

  /**
   * Loads a catalog from a binary model.
   *
   * @param storage
   *          the binary model, must not be {@code null}
   * @return the resource containing the loaded catalog, never {@code null}
   * @throws IOException
   *           if the binary model cannot be read
   */
  private StorageAwareResource load(final byte[] storage) throws IOException {
    XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    StorageAwareResource resource = (StorageAwareResource) resourceSet.createResource(URI.createURI(RESOURCE_URI));
    resource.loadFromStorage(new CheckBatchLinkableResourceStorageLoadable(new ByteArrayInputStream(storage), false));
    return resource;
  }

  /**
   * Persists and reloads the test catalog.
   *
   * @return the reloaded catalog, never {@code null}
   * @throws IOException
   *           if the catalog cannot be persisted or reloaded
   */
  private CheckCatalog saveAndLoad() throws IOException {
    StorageAwareResource loaded = load(save(loadSource()));
    assertEquals(1, loaded.getContents().size(), "Only the catalog itself should have been persisted");
    return (CheckCatalog) loaded.getContents().get(0);
  }

  /**
   * Returns the only check of the given catalog's category.
   *
   * @param catalog
   *          the catalog, must not be {@code null}
   * @return the categorized check, never {@code null}
   */
  private Check categorizedCheck(final CheckCatalog catalog) {
    assertEquals(1, catalog.getCategories().size(), "Catalog should have one category");
    Category category = catalog.getCategories().get(0);
    assertEquals(1, category.getChecks().size(), "Category should have one check");
    return category.getChecks().get(0);
  }

  /**
   * Tests that the data describing the catalog and its checks is persisted.
   */
  @Test
  void testPublicApiIsPersisted() throws IOException {
    CheckCatalog catalog = saveAndLoad();

    assertEquals("storage", catalog.getPackageName(), "Package name");
    assertEquals("StorageChecks", catalog.getName(), "Catalog name");
    assertNotNull(catalog.getGrammar(), "Grammar reference");
    assertEquals("Public category label", catalog.getCategories().get(0).getLabel(), "Category label");
    assertEquals(2, catalog.getAllChecks().size(), "Number of checks");

    Check check = categorizedCheck(catalog);
    assertEquals("PublicCheckId", check.getId(), "Check id");
    assertEquals("Public check label", check.getLabel(), "Check label");
    assertEquals("Public message", check.getMessage(), "Check message");
    assertEquals(SeverityKind.ERROR, check.getDefaultSeverity(), "Default severity");
    assertNotNull(check.getSeverityRange(), "Severity range");
    assertEquals(SeverityKind.WARNING, check.getSeverityRange().getMinSeverity(), "Minimum severity");
    assertNotNull(check.getKind(), "Trigger kind");
    assertFalse(check.isExternal(), "Categorized check is not external");
    assertTrue(catalog.getChecks().get(0).isExternal(), "Second check is external");
  }

  /**
   * Tests that the formal parameters of a check are persisted, including their type and default value.
   */
  @Test
  void testFormalParametersArePersisted() throws IOException {
    Check check = categorizedCheck(saveAndLoad());

    assertEquals(1, check.getFormalParameters().size(), "Number of formal parameters");
    FormalParameter parameter = check.getFormalParameters().get(0);
    assertEquals("publicParameter", parameter.getName(), "Parameter name");
    assertEquals("Public parameter label", parameter.getLabel(), "Parameter label");
    assertNotNull(parameter.getType().getType(), "Parameter type");
    assertEquals("int", parameter.getType().getType().getQualifiedName(), "Parameter type name");
    assertEquals("23", ((XNumberLiteral) parameter.getRight()).getValue(), "Parameter default value");
  }

  /**
   * Tests that the context variables of a check are persisted while their constraints are not.
   */
  @Test
  void testContextsArePersistedWithoutConstraint() throws IOException {
    Check check = categorizedCheck(saveAndLoad());

    assertEquals(1, check.getContexts().size(), "Number of contexts");
    Context context = check.getContexts().get(0);
    assertNotNull(context.getContextVariable(), "Context variable");
    assertEquals("publicContextVariable", context.getContextVariable().getName(), "Context variable name");
    assertNotNull(context.getContextVariable().getType().getType(), "Context variable type");
    assertEquals("com.avaloq.tools.ddk.check.check.Documented", context.getContextVariable().getType().getType().getQualifiedName(), "Context variable type name");
    assertNull(context.getConstraint(), "Constraint must not be persisted");
  }

  /**
   * Tests that the implementation of the catalog is not persisted.
   */
  @Test
  void testImplementationIsNotPersisted() throws IOException {
    CheckCatalog catalog = saveAndLoad();

    assertNull(catalog.getImports(), "Import section must not be persisted");
    assertTrue(catalog.getMembers().isEmpty(), "Members must not be persisted");
    assertTrue(catalog.getImplementations().isEmpty(), "Implementations must not be persisted");
    for (EObject content : catalog.eResource().getContents()) {
      assertFalse(content instanceof JvmType, "The inferred JVM model must not be persisted");
    }
  }

  /**
   * Tests that no implementation detail leaks into the binary model, whichever entry it might be written to.
   */
  @Test
  void testNoImplementationDetailsInBinaryModel() throws IOException {
    byte[] storage = save(loadSource());

    try (ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(storage))) {
      for (ZipEntry entry = zipIn.getNextEntry(); entry != null; entry = zipIn.getNextEntry()) {
        String contents = new String(zipIn.readAllBytes(), StandardCharsets.ISO_8859_1);
        for (String marker : IMPLEMENTATION_MARKERS) {
          assertFalse(contents.contains(marker), "Entry '" + entry.getName() + "' must not contain '" + marker + '\'');
        }
      }
    }
  }

  /**
   * Tests that persisting a resource does not modify the resource itself, as it is still used by the generator afterwards.
   */
  @Test
  void testSourceResourceIsNotModified() throws IOException {
    StorageAwareResource resource = loadSource();
    save(resource);

    CheckCatalog catalog = (CheckCatalog) resource.getContents().get(0);
    assertNotNull(catalog.getImports(), "Import section");
    assertEquals(1, catalog.getMembers().size(), "Members");
    assertEquals(1, catalog.getImplementations().size(), "Implementations");
    assertNotNull(categorizedCheck(catalog).getContexts().get(0).getConstraint(), "Constraint");
    assertTrue(resource.getContents().stream().anyMatch(JvmType.class::isInstance), "Inferred JVM model");
  }

  /**
   * Tests that persisting the same catalog twice yields the very same bytes, as the binary models are checked into source control.
   */
  @Test
  void testBinaryModelIsReproducible() throws IOException {
    assertArrayEquals(save(loadSource()), save(loadSource()), "Binary models of the same catalog should be identical");
  }

}
