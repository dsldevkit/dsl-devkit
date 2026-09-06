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
package repro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.xtext.JvmMemberInitializableResource;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.scope.ScopeStandaloneSetup;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeFactory;
import com.google.inject.Injector;

/** Tests Scope inference while model classifiers are temporarily unavailable. */
@SuppressWarnings("nls")
public class ScopeInferenceTest {

  /** Reading resource roots must not compile bodies or resolve method signatures. */
  @Test
  public void testResourceTraversalDuringModelLinking() throws IOException {
    assertRecovery(false, false);
  }

  /** An early member query must not make missing scope methods permanent. */
  @Test
  public void testEarlyMemberAccessRecoversBeforeGeneration() throws IOException {
    assertRecovery(true, false);
  }

  /** Invalid input must not write Java; repairing it in place must recover. */
  @Test
  public void testUnresolvedGenerationFailsWithoutWritingFiles() throws IOException {
    assertRecovery(true, true);
  }

  private void assertRecovery(final boolean accessMembers, final boolean generateWhileInvalid) throws IOException {
    final Injector injector = new ScopeStandaloneSetup().createInjectorAndDoEMFRegistration();
    final XtextResourceSet resources = injector.getInstance(XtextResourceSet.class);
    final XtextResource resource = (XtextResource) resources.createResource(URI.createURI("synthetic:/Example.scope"));
    final String source = "scoping repro.Example\n"
        + "import \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\n"
        + "scope ecore::EClass { context ecore::EPackage = eClassifiers; }\n";
    resource.load(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), null);
    final ScopeModel model = (ScopeModel) resource.getParseResult().getRootASTElement();
    final EClass detached = EcoreFactory.eINSTANCE.createEClass();
    detached.setName("EClass");
    model.getScopes().get(0).setTargetType(detached);
    assertEquals(3, resource.getContents().size(), "Root traversal must retain both inferred provider types");
    if (accessMembers) {
      ((JvmMemberInitializableResource) resource).ensureJvmMembersInitialized();
    }
    if (generateWhileInvalid) {
      final InMemoryFileSystemAccess invalidFiles = new InMemoryFileSystemAccess();
      assertThrows(IllegalStateException.class, () -> injector.getInstance(IGenerator.class).doGenerate(resource, invalidFiles));
      assertTrue(invalidFiles.getTextFiles().isEmpty(), "Incomplete providers must never be emitted");
    }
    model.getScopes().get(0).setTargetType(EcorePackage.Literals.ECLASS);
    final InMemoryFileSystemAccess files = new InMemoryFileSystemAccess();
    injector.getInstance(IGenerator.class).doGenerate(resource, files);
    final String generated = files.getTextFiles().values().toString();
    assertEquals(2, files.getTextFiles().size(), "Both providers must be generated after model linking finishes");
    assertHelperDeclared(resource, "scope_ecore_EClass");
    assertTrue(generated.contains("IScope scope_ecore_EClass("), "The helper must be declared, not merely called");
    assertTrue((generated.contains("EcorePackage.Literals.ECLASS") || generated.contains("EcorePackage.eINSTANCE.getEClass()")), "Generation must render the repaired model type");
  }

  /** An include that resolves after an early member query must contribute both methods and injections. */
  @Test
  public void testUnresolvedIncludeRecoversBeforeGeneration() throws IOException {
    final Injector injector = new ScopeStandaloneSetup().createInjectorAndDoEMFRegistration();
    final XtextResourceSet resources = injector.getInstance(XtextResourceSet.class);
    final XtextResource base = load(resources, "Base", "scoping repro.Base\n"
        + "import \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\n"
        + "inject java.lang.String as inheritedField\n"
        + "scope (inherited) ecore::EPackage#eClassifiers { context ecore::EPackage = eClassifiers; }\n");
    final XtextResource child = load(resources, "Child", "scoping repro.Child\n");
    final ScopeModel model = (ScopeModel) child.getParseResult().getRootASTElement();
    final ScopeModel unavailable = ScopeFactory.eINSTANCE.createScopeModel();
    ((InternalEObject) unavailable).eSetProxyURI(base.getURI().appendFragment("not-yet-linked"));
    model.getIncludedScopes().add(unavailable);
    assertEquals(3, child.getContents().size(), "Both provider roots must survive an unavailable include");
    ((JvmMemberInitializableResource) child).ensureJvmMembersInitialized();
    model.getIncludedScopes().set(0, (ScopeModel) base.getParseResult().getRootASTElement());
    final InMemoryFileSystemAccess files = new InMemoryFileSystemAccess();
    injector.getInstance(IGenerator.class).doGenerate(child, files);
    assertHelperDeclared(child, "inherited_ecore_EPackage_eClassifiers");
    final String generated = files.getTextFiles().values().toString();
    assertTrue(generated.contains("IScope inherited_ecore_EPackage_eClassifiers("), "The inherited reference helper must be declared");
    assertTrue(generated.contains("String inheritedField"), "The inherited injection must be declared");
  }

  private XtextResource load(final XtextResourceSet resources, final String name, final String source) throws IOException {
    final XtextResource resource = (XtextResource) resources.createResource(URI.createURI("synthetic:/" + name + ".scope"));
    resource.load(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), null);
    return resource;
  }

  private void assertHelperDeclared(final XtextResource resource, final String name) {
    final JvmGenericType provider = (JvmGenericType) resource.getContents().get(1);
    assertEquals(1, provider.getMembers().stream().filter(JvmOperation.class::isInstance)
        .filter(member -> name.equals(member.getSimpleName())).count(), "Exactly one helper declaration must exist");
  }

}
