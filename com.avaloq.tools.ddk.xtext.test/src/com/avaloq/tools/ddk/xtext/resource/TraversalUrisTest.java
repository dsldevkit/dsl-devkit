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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.linking.ShortFragmentProvider;


/**
 * Tests that {@link TraversalUris} and the resource descriptions using it compute the same URIs as {@link EcoreUtil#getURI(EObject)}, computing the fragment
 * segment of each object once per traversal.
 */
@SuppressWarnings({"nls", "unchecked", "PMD.JUnitAssertionsShouldIncludeMessage"})
public class TraversalUrisTest {

  private EAttribute name;
  private EReference refs;
  private EReference child;
  private EReference children;
  private EClass node;

  private CountingFragmentProvider fragmentProvider;
  private LazyLinkingResource resourceA;
  private LazyLinkingResource resourceB;
  private EObject deepest;
  private EObject secondLeaf;

  /**
   * Fragment provider counting how often the segment of each object is computed.
   */
  private static final class CountingFragmentProvider extends ShortFragmentProvider {
    private final Map<EObject, Integer> segmentComputations = new IdentityHashMap<>();

    @Override
    public boolean appendFragmentSegment(final EObject object, final StringBuilder builder) {
      segmentComputations.merge(object, 1, Integer::sum);
      return super.appendFragmentSegment(object, builder);
    }

    int maxSegmentComputations() {
      return segmentComputations.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }
  }

  /**
   * Strategy exporting every node under its name and the references to other resources.
   */
  private static final class SampleResourceDescriptionStrategy extends AbstractResourceDescriptionStrategy {
    SampleResourceDescriptionStrategy(final EAttribute name) {
      setQualifiedNameProvider(new IQualifiedNameProvider.AbstractImpl() {
        @Override
        public QualifiedName getFullyQualifiedName(final EObject obj) {
          return QualifiedName.create((String) obj.eGet(name));
        }
      });
    }

    @Override
    protected boolean doCreateEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor) {
      acceptEObjectDescription(object, acceptor);
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
    ePackage.setNsURI("http://www.avaloq.com/ddk/test/traversal");
    node = EcoreFactory.eINSTANCE.createEClass();
    node.setName("Node");
    ePackage.getEClassifiers().add(node);
    name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    node.getEStructuralFeatures().add(name);
    refs = createReference("refs", true, false);
    child = createReference("child", false, true);
    children = createReference("children", true, true);

    fragmentProvider = new CountingFragmentProvider();
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceA = createResource("test:/a.sample", resourceSet);
    resourceA.setFragmentProvider(fragmentProvider);
    resourceB = createResource("test:/b.sample", resourceSet);

    // single-valued chains of two and three equal segments, list chains of equal segments, siblings and a second root
    EObject root = createNode("root", resourceA);
    EObject c1 = addChild(root, "c1");
    EObject c2 = addChild(root, "c2");
    EObject x1 = setChild(c1, "x1");
    EObject x2 = setChild(x1, "x2");
    EObject x3 = setChild(x2, "x3");
    EObject y1 = addChild(x3, "y1");
    addChild(x3, "y2");
    EObject z1 = addChild(y1, "z1");
    deepest = setChild(setChild(addChild(z1, "w1"), "v1"), "u1");
    setChild(setChild(c2, "p1"), "p2");
    EObject secondRoot = createNode("secondRoot", resourceA);
    secondLeaf = addChild(addChild(secondRoot, "q1"), "q2");

    EObject target = createNode("target", resourceB);
    EObject targetChild = addChild(target, "targetChild");
    ((EList<EObject>) x3.eGet(refs)).addAll(List.of(targetChild, target, c2));
    ((EList<EObject>) deepest.eGet(refs)).add(targetChild);
    ((EList<EObject>) c2.eGet(refs)).add(target);
  }

  private EReference createReference(final String referenceName, final boolean many, final boolean containment) {
    EReference reference = EcoreFactory.eINSTANCE.createEReference();
    reference.setName(referenceName);
    reference.setEType(node);
    reference.setUpperBound(many ? -1 : 1);
    reference.setContainment(containment);
    node.getEStructuralFeatures().add(reference);
    return reference;
  }

  private LazyLinkingResource createResource(final String uri, final ResourceSet resourceSet) {
    LazyLinkingResource resource = new LazyLinkingResource() {
      @Override
      public void resolveLazyCrossReferences(final CancelIndicator monitor) {
        // the sample model has no lazy links, and this resource is not injected
      }
    };
    resource.setEncoder(new LazyURIEncoder());
    resource.setFragmentProvider(new ShortFragmentProvider());
    resource.setURI(URI.createURI(uri));
    resourceSet.getResources().add(resource);
    return resource;
  }

  private EObject createNode(final String nodeName, final Resource resource) {
    EObject result = EcoreUtil.create(node);
    result.eSet(name, nodeName);
    if (resource != null) {
      resource.getContents().add(result);
    }
    return result;
  }

  private EObject addChild(final EObject parent, final String nodeName) {
    EObject result = createNode(nodeName, null);
    ((EList<EObject>) parent.eGet(children)).add(result);
    return result;
  }

  private EObject setChild(final EObject parent, final String nodeName) {
    EObject result = createNode(nodeName, null);
    parent.eSet(child, result);
    return result;
  }

  private List<EObject> allContents(final Resource resource) {
    List<EObject> result = new ArrayList<>();
    resource.getAllContents().forEachRemaining(result::add);
    return result;
  }

  @Test
  public void testUrisEqualInTraversalOrder() {
    List<EObject> objects = allContents(resourceA);
    TraversalUris uris = new TraversalUris(resourceA);
    for (EObject object : objects) {
      assertEquals(EcoreUtil.getURI(object), uris.getURI(object));
      assertEquals(EcoreUtil.getURI(object), uris.getURI(object));
    }
    assertTrue(EcoreUtil.getURI(deepest).fragment().contains("*3"), "covers a run of more than two segments");
    assertTrue(EcoreUtil.getURI(deepest).fragment().endsWith("/2/2"), "covers a run of two one-character segments");
    assertTrue(EcoreUtil.getURI(secondLeaf).fragment().endsWith("*2"), "covers a run of two longer segments");
  }

  @Test
  public void testUrisEqualInAnyOrder() {
    List<EObject> objects = allContents(resourceA);
    for (int seed = 0; seed < 20; seed++) {
      List<EObject> shuffled = new ArrayList<>(objects);
      shuffled.addAll(objects);
      Collections.shuffle(shuffled, new Random(seed));
      TraversalUris uris = new TraversalUris(resourceA);
      for (EObject object : shuffled) {
        assertEquals(EcoreUtil.getURI(object), uris.getURI(object));
      }
    }
  }

  @Test
  public void testComputesEachSegmentOnceInTraversalOrder() {
    TraversalUris uris = new TraversalUris(resourceA);
    for (EObject object : allContents(resourceA)) {
      uris.getURI(object);
    }
    assertEquals(1, fragmentProvider.maxSegmentComputations());
  }

  @Test
  public void testFallsBackOutsideTraversedResource() {
    EObject proxy = createNode("proxy", null);
    ((InternalEObject) proxy).eSetProxyURI(URI.createURI("test:/missing.sample#/0"));
    EObject detached = addChild(createNode("detached", null), "detachedChild");
    Resource plainResource = new ResourceImpl(URI.createURI("test:/plain.sample"));
    EObject plain = addChild(createNode("plain", plainResource), "plainChild");
    TraversalUris uris = new TraversalUris(resourceA);
    for (EObject object : List.of(deepest, resourceB.getContents().get(0), proxy, detached, deepest)) {
      assertEquals(EcoreUtil.getURI(object), uris.getURI(object));
    }
    assertEquals(EcoreUtil.getURI(plain), new TraversalUris(plainResource).getURI(plain));
  }

  @Test
  public void testFallsBackForOverriddenGetFragment() {
    resourceA.setFragmentProvider(new ShortFragmentProvider() {
      @Override
      public String getFragment(final EObject object, final Fallback fallback) {
        return "custom" + super.getFragment(object, fallback);
      }
    });
    assertTrue(EcoreUtil.getURI(deepest).fragment().startsWith("custom"), "the override changes the fragments");
    TraversalUris uris = new TraversalUris(resourceA);
    for (EObject object : allContents(resourceA)) {
      assertEquals(EcoreUtil.getURI(object), uris.getURI(object));
    }
  }

  @Test
  public void testFallsBackForOverriddenGetFragmentProvider() {
    LazyLinkingResource resource = new LazyLinkingResource() {
      @Override
      public IFragmentProvider getFragmentProvider() {
        return new ShortFragmentProvider();
      }
    };
    resource.setURI(URI.createURI("test:/c.sample"));
    resource.setFragmentProvider(new ShortFragmentProvider() {
      @Override
      public boolean appendFragmentSegment(final EObject object, final StringBuilder builder) {
        builder.append('s');
        return super.appendFragmentSegment(object, builder);
      }
    });
    EObject leaf = setChild(addChild(addChild(createNode("root", resource), "a"), "b"), "c");
    assertTrue(EcoreUtil.getURI(leaf).fragment().contains("s"), "the resource uses another provider than the one it returns");
    TraversalUris uris = new TraversalUris(resource);
    for (EObject object : allContents(resource)) {
      assertEquals(EcoreUtil.getURI(object), uris.getURI(object));
    }
  }

  @Test
  public void testResourceDescriptionUrisUnchanged() {
    SampleResourceDescriptionStrategy strategy = new SampleResourceDescriptionStrategy(name);
    ResourceDescription2 description = new ResourceDescription2(resourceA, strategy, IResourceScopeCache.NullImpl.INSTANCE);

    List<IEObjectDescription> exported = new ArrayList<>();
    description.getExportedObjects().forEach(exported::add);
    List<IReferenceDescription> references = new ArrayList<>();
    description.getReferenceDescriptions().forEach(references::add);
    final int maxSegmentComputations = fragmentProvider.maxSegmentComputations();

    List<EObject> objects = allContents(resourceA);
    assertEquals(objects.size(), exported.size());
    for (IEObjectDescription export : exported) {
      assertEquals(EcoreUtil.getURI(export.getEObjectOrProxy()), export.getEObjectURI());
    }
    List<String> expected = new ArrayList<>();
    for (EObject source : objects) {
      List<EObject> targets = (List<EObject>) source.eGet(refs);
      for (int i = 0; i < targets.size(); i++) {
        if (targets.get(i).eResource() != resourceA) {
          expected.add(EcoreUtil.getURI(source) + " " + i + " -> " + EcoreUtil.getURI(targets.get(i)) + " in " + EcoreUtil.getURI(source));
        }
      }
    }
    List<String> actual = new ArrayList<>();
    for (IReferenceDescription reference : references) {
      actual.add(reference.getSourceEObjectUri() + " " + reference.getIndexInList() + " -> " + reference.getTargetEObjectUri() + " in "
          + reference.getContainerEObjectURI());
    }
    assertEquals(4, expected.size());
    assertEquals(expected, actual);
    // one traversal for the exported objects and one for the references; previously the root's segment was computed for every object
    assertEquals(2, maxSegmentComputations);
  }
}
