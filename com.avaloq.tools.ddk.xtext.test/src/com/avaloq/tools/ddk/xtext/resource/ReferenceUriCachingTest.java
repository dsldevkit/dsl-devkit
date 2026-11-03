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
package com.avaloq.tools.ddk.xtext.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.util.IAcceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.hash.Hasher;


/**
 * Tests that the URIs computed for fingerprints and reference descriptions are unchanged when objects are referenced several times, and that each object's
 * URI is computed once per fingerprint and per {@link AbstractResourceDescriptionStrategy#createReferenceDescriptions} run.
 */
@SuppressWarnings({"nls", "unchecked", "PMD.JUnitAssertionsShouldIncludeMessage"})
public class ReferenceUriCachingTest {

  private EAttribute name;
  private EReference refs;
  private EReference main;
  private EReference children;

  private CountingResource resourceA;
  private CountingResource resourceB;
  private EObject a;
  private EObject a1;
  private EObject a2;
  private EObject b1;

  /**
   * Resource that counts how often the fragment of each object is computed.
   */
  private static final class CountingResource extends ResourceImpl {
    private final Map<EObject, Integer> fragmentComputations = new IdentityHashMap<>();

    CountingResource(final URI uri) {
      super(uri);
    }

    @Override
    public String getURIFragment(final EObject eObject) {
      fragmentComputations.merge(eObject, 1, Integer::sum);
      return super.getURIFragment(eObject);
    }

    int maxFragmentComputations() {
      return fragmentComputations.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }
  }

  /**
   * Fingerprint computer using every way of fingerprinting the references of the sample model.
   */
  private final class SampleFingerprintComputer extends AbstractStreamingFingerprintComputer {
    @Override
    protected void fingerprint(final EObject eObject, final Hasher hasher) {
      hasher.putUnencodedChars(String.valueOf(eObject.eGet(name)));
      fingerprintRef(eObject, refs, hasher);
      fingerprintRef(eObject, refs, FingerprintOrder.UNORDERED, hasher);
      fingerprintFeature(eObject, refs, hasher);
      fingerprintFeature(eObject, refs, FingerprintOrder.UNORDERED, hasher);
      fingerprintRef(eObject, main, hasher);
      fingerprintExpr(eObject.eGet(refs), eObject, FingerprintOrder.UNORDERED, FingerprintIndirection.DIRECT, hasher);
      fingerprintRef(eObject, children, hasher);
    }
  }

  /**
   * Strategy which exports the references to other resources.
   */
  private static final class SampleResourceDescriptionStrategy extends AbstractResourceDescriptionStrategy {
    @Override
    protected boolean doCreateEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor) {
      return true;
    }

    @Override
    protected boolean isResolvedAndExternal(final EObject from, final EReference eReference, final EObject to) {
      return to != null && !to.eIsProxy() && to.eResource() != from.eResource();
    }

    @Override
    public Set<EClass> getExportedEClasses(final Resource resource) {
      return null; // NOPMD ReturnEmptyCollectionRatherThanNull
    }
  }

  @BeforeEach
  public void setUp() {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("sample");
    ePackage.setNsURI("http://www.avaloq.com/ddk/test/sample");
    EClass node = EcoreFactory.eINSTANCE.createEClass();
    node.setName("Node");
    ePackage.getEClassifiers().add(node);
    name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    refs = createReference(node, "refs", true, false);
    main = createReference(node, "main", false, false);
    children = createReference(node, "children", true, true);
    node.getEStructuralFeatures().add(0, name);

    // a1 references b1 three times and b2 once, a2 references b1 once and a1 (same resource) once
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceA = new CountingResource(URI.createURI("test:/a.sample"));
    resourceB = new CountingResource(URI.createURI("test:/b.sample"));
    resourceSet.getResources().add(resourceA);
    resourceSet.getResources().add(resourceB);
    a = createNode(node, "a", resourceA);
    a1 = createNode(node, "a1", null);
    a2 = createNode(node, "a2", null);
    ((EList<EObject>) a.eGet(children)).add(a1);
    ((EList<EObject>) a.eGet(children)).add(a2);
    EObject b = createNode(node, "b", resourceB);
    b1 = createNode(node, "b1", null);
    EObject b2 = createNode(node, "b2", null);
    ((EList<EObject>) b.eGet(children)).add(b1);
    ((EList<EObject>) b.eGet(children)).add(b2);
    ((EList<EObject>) a1.eGet(refs)).addAll(List.of(b1, b2, b1));
    a1.eSet(main, b1);
    ((EList<EObject>) a2.eGet(refs)).add(b1);
    a2.eSet(main, a1);
  }

  private EReference createReference(final EClass node, final String referenceName, final boolean many, final boolean containment) {
    EReference reference = EcoreFactory.eINSTANCE.createEReference();
    reference.setName(referenceName);
    reference.setEType(node);
    reference.setUpperBound(many ? -1 : 1);
    reference.setUnique(false);
    reference.setContainment(containment);
    node.getEStructuralFeatures().add(reference);
    return reference;
  }

  private EObject createNode(final EClass node, final String nodeName, final Resource resource) {
    EObject result = EcoreUtil.create(node);
    result.eSet(name, nodeName);
    if (resource != null) {
      resource.getContents().add(result);
    }
    return result;
  }

  @Test
  public void testFingerprintsUnchanged() {
    // values computed before the URIs were cached
    SampleFingerprintComputer computer = new SampleFingerprintComputer();
    assertEquals(List.of("08cb2b6b617be3d92edcad665f5dbfcd", "02aebf35247afae07c7f3c0881f20fef", "70ffb76b371e9e9ebf919be5a75fd028", "7652ee7fa2cef7e20e5f181f1ee0882d"),
        List.of(computer.computeFingerprint(a), computer.computeFingerprint(a1), computer.computeFingerprint(a2), computer.computeFingerprint(resourceA)));
  }

  @Test
  public void testFingerprintComputesEachUriOnce() {
    SampleFingerprintComputer computer = new SampleFingerprintComputer();
    computer.computeFingerprint(a1);
    assertEquals(1, resourceB.maxFragmentComputations());
    computer.computeFingerprint(resourceA);
    assertEquals(2, resourceB.maxFragmentComputations());
  }

  @Test
  public void testReferenceDescriptionsUnchanged() {
    List<String> expected = new ArrayList<>();
    for (EObject source : List.of(a1, a2)) {
      List<EObject> targets = (List<EObject>) source.eGet(refs);
      for (int i = 0; i < targets.size(); i++) {
        expected.add(describe(EcoreUtil.getURI(source), refs, i, EcoreUtil.getURI(targets.get(i)), EcoreUtil.getURI(a)));
      }
      if (source == a1) {
        expected.add(describe(EcoreUtil.getURI(a1), main, -1, EcoreUtil.getURI(b1), EcoreUtil.getURI(a)));
      }
    }
    assertEquals(5, expected.size());

    List<String> actual = new ArrayList<>();
    SampleResourceDescriptionStrategy strategy = new SampleResourceDescriptionStrategy();
    for (EObject source : List.of(a, a1, a2)) {
      strategy.createReferenceDescriptions(source, EcoreUtil.getURI(a), r -> actual.add(describe(r)));
    }
    assertEquals(expected, actual);
  }

  @Test
  public void testReferenceDescriptionsComputeEachUriOnce() {
    new SampleResourceDescriptionStrategy().createReferenceDescriptions(a1, null, r -> {});
    assertEquals(1, resourceA.maxFragmentComputations());
    assertEquals(1, resourceB.maxFragmentComputations());
  }

  private static String describe(final IReferenceDescription description) {
    return describe(description.getSourceEObjectUri(), description.getEReference(), description.getIndexInList(), description.getTargetEObjectUri(), description.getContainerEObjectURI());
  }

  private static String describe(final URI source, final EReference reference, final int index, final URI target, final URI container) {
    return source + " " + reference.getName() + "[" + index + "] -> " + target + " in " + container;
  }
}
