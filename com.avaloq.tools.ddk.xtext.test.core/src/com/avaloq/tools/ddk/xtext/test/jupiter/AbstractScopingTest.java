/*******************************************************************************
 * Copyright (c) 2025 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.test.jupiter;

import static com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProvider.REP_SEPARATOR;
import static com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProvider.SEGMENT_SEPARATOR;
import static com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider.EQ_OP;
import static com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider.SELECTOR_END;
import static com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider.SELECTOR_START;
import static com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider.UNIQUE;
import static com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider.VALUE_SEP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Triple;
import org.eclipse.xtext.xbase.lib.Pair;

import com.avaloq.tools.ddk.caching.Regexps;
import com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProvider;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;
import com.avaloq.tools.ddk.xtext.scoping.ContainerQuery;
import com.avaloq.tools.ddk.xtext.scoping.IDomain;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Base class for scoping tests.
 */
@SuppressWarnings("nls")
public abstract class AbstractScopingTest extends AbstractXtextMarkerBasedTest {
  private static final String PARAMETER_EXPECTED_OBJECTS = "expectedObjects";
  private static final String PARAMETER_REFERENCE = "reference";
  private static final String PARAMETER_CONTEXT = "context";
  public static final String TOP_LEVEL_OBJECT_FRAGMENT = SEGMENT_SEPARATOR + "0" + SEGMENT_SEPARATOR + "1";
  public static final String TOP_LEVEL_SURROGATE_FRAGMENT = SEGMENT_SEPARATOR + "0" + REP_SEPARATOR + "2";
  public static final String INFERRED_DATA_DICTIONARY_FRAGMENT = SEGMENT_SEPARATOR + "1";

  private static final String NUMBER_OF_ELEMENTS_MESSAGE = "Incorrect number of elements in scope.";
  private static final Splitter FRAGMENT_SEGMENT_SPLITTER = Splitter.onPattern("(?<!\\\\)\\" + SEGMENT_SEPARATOR);
  /** The Constant NO_NODE_MODEL_COULD_BE_A_DERIVED_OBJECT. */
  private static final String NO_NODE_MODEL_COULD_BE_A_DERIVED_OBJECT = "No node model. Could be a derived object."; //$NON-NLS-1$
  /** The Constant UNRESOLVED_REFERENCE. */
  private static final String UNRESOLVED_REFERENCE = "Unresolved reference."; //$NON-NLS-1$
  /** The Constant WHICH_CORRESPONDS_TO. */
  private static final String WHICH_CORRESPONDS_TO = "\nwhich corresponds to:\n"; //$NON-NLS-1$
  /** The Constant LINKS_TO. */
  private static final String LINKS_TO = "Links to:\n"; //$NON-NLS-1$

  private final IDomain.Mapper domainMapper;

  private final Collection<Runnable> expectedLinkAssertions = new ArrayList<Runnable>();

  /**
   * Creates a new instance of {@link AbstractScopingTest}.
   */
  public AbstractScopingTest() {
    this(new IDomain.NullMapper());

  }

  /**
   * Creates a new instance of {@link AbstractScopingTest}.
   *
   * @param domainMapper
   *          the domainMapper to use
   */
  public AbstractScopingTest(final IDomain.Mapper domainMapper) {
    this.domainMapper = domainMapper;
  }

  /**
   * Returns all contents of the main {@link XtextTestResource}.
   *
   * @return all contents of the main {@link XtextTestResource}
   */
  @SuppressWarnings("unchecked")
  public Iterable<EObject> getContents() {
    return (Iterable<EObject>) getTestInformation().getTestObject(Iterable.class);
  }

  /**
   * Set up scoping.
   */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    Iterable<EObject> allContents = new Iterable<EObject>() {
      @Override
      public Iterator<EObject> iterator() {
        return getXtextTestResource().getAllContents();
      }
    };
    getTestInformation().putTestObject(Iterable.class, allContents);
  }

  @Override
  protected void afterEachTest() {
    assertTrue(expectedLinkAssertions.isEmpty(), "Expected links were set with link(int) but testLinking(String, CharSequence) was never called");
    super.afterEachTest();
  }

  /**
   * Returns the scope provider used for unit testing.
   *
   * @return the scope provider instance
   */
  protected IScopeProvider getScopeProvider() {
    return getXtextTestUtil().get(IScopeProvider.class);
  }

  /**
   * Check if scope expected is found in context provided.
   *
   * @param context
   *          element from which an element shall be referenced
   * @param reference
   *          to be used to filter the elements
   * @param expectedSourceName
   *          (upper case!) name of scope element to look for (kernel source)
   * @param expectedSourceType
   *          type of scope element to look for
   */
  protected void assertScope(final EObject context, final EReference reference, final String expectedSourceName, final String expectedSourceType) {
    assertScope(context, reference, QualifiedNames.safeQualifiedName(expectedSourceName), getTargetSourceUri(expectedSourceName + '.'
        + expectedSourceType).appendFragment(TOP_LEVEL_OBJECT_FRAGMENT));
  }

  /**
   * Check if scope expected is found in context provided.
   *
   * @param context
   *          element from which an element shall be referenced
   * @param reference
   *          to be used to filter the elements
   * @param expectedElementName
   *          name of scope element to look for
   * @param expectedSourceName
   *          (upper case!) name of the source within to look for the scope element
   * @param expectedSourceType
   *          type of scope element to look for
   * @param referenceElementType
   *          the type of the referenced element
   */
  @SuppressWarnings("PMD.UseObjectForClearerAPI")
  protected void assertScopeForElement(final EObject context, final EReference reference, final String expectedElementName, final String expectedSourceName, final String expectedSourceType, final String referenceElementType) {
    assertScope(context, reference, QualifiedNames.safeQualifiedName(expectedElementName), getTargetSourceUri(expectedSourceName + '.'
        + expectedSourceType).appendFragment(TOP_LEVEL_OBJECT_FRAGMENT + SEGMENT_SEPARATOR + referenceElementType));
  }

  /**
   * Check if scope expected is found in context provided.
   *
   * @param context
   *          element from which an element shall be referenced
   * @param reference
   *          to be used to filter the elements
   * @param expectedUris
   *          of source referenced
   */
  protected void assertScope(final EObject context, final EReference reference, final URI... expectedUris) {
    assertScope(context, reference, Sets.newHashSet(expectedUris));
  }

  /**
   * Check if scope expected is found in context provided.
   *
   * @param context
   *          element from which an element shall be referenced
   * @param reference
   *          to be used to filter the elements
   * @param expectedUriSet
   *          of source referenced
   */
  protected void assertScope(final EObject context, final EReference reference, final Set<URI> expectedUriSet) {
    IScope scope = getScopeProvider().getScope(context, reference);
    for (IEObjectDescription description : scope.getAllElements()) {
      expectedUriSet.remove(description.getEObjectURI());
      if (expectedUriSet.isEmpty()) {
        return;
      }
    }
    assertTrue(expectedUriSet.isEmpty(), "Expected URIs not found in scope: " + expectedUriSet);
  }

  /**
   * Checks if the given objects are in scope of the given reference for the given context.
   *
   * @param context
   *          {@link EObject} element from which an element shall be referenced, must not be {@code null}
   * @param reference
   *          the structural feature of {@code context} for which the scope should be asserted, must not be {@code null} and part of the context element
   * @param expectedObjects
   *          for given scope, must not be {@code null}
   */
  protected void assertScopedObjects(final EObject context, final EReference reference, final EObject... expectedObjects) {
    Assert.isNotNull(expectedObjects, PARAMETER_EXPECTED_OBJECTS);
    assertScopedObjects(context, reference, Lists.newArrayList(expectedObjects));
  }

  /**
   * Checks if the given objects are in scope of the given reference for the given context.
   *
   * @param context
   *          {@link EObject} element from which an element shall be referenced, must not be {@code null}
   * @param reference
   *          the structural feature of {@code context} for which the scope should be asserted, must not be {@code null} and part of the context element
   * @param firstExpectedObjectCollection
   *          for given scope, must not be {@code null}
   * @param furtherExpectedObjectCollections
   *          for given scope, must not be {@code null}
   */
  @SuppressWarnings("unchecked")
  protected void assertScopedObjects(final EObject context, final EReference reference, final Collection<? extends EObject> firstExpectedObjectCollection, final Collection<? extends EObject>... furtherExpectedObjectCollections) {
    Assert.isNotNull(firstExpectedObjectCollection, "firstExpectedObjectCollection");
    Assert.isNotNull(furtherExpectedObjectCollections, "furtherExpectedObjectCollections");
    Collection<EObject> consolidatedList = Lists.newArrayList(firstExpectedObjectCollection);
    for (Collection<? extends EObject> expectedObjects : furtherExpectedObjectCollections) {
      consolidatedList.addAll(expectedObjects);
    }
    assertScopedObjects(context, reference, consolidatedList);
  }

  /**
   * Checks if the scope of the given reference for the given context contains only the expected objects.
   * In addition, checks that the reference of the given context references at least one of the expected
   * objects. If the reference has multiplicity > 1, then every reference must reference at least
   * one of the expected objects.
   *
   * @param context
   *          {@link EObject} from which the given objects shall be referenced, must not be {@code null}
   * @param reference
   *          the structural feature of {@code context} for which the scope should be asserted, must not be {@code null} and part of the context element
   * @param expectedObjects
   *          the objects expected in the scope, must not be {@code null}
   */
  protected void assertScopedObjects(final EObject context, final EReference reference, final Collection<? extends EObject> expectedObjects) {
    Assert.isNotNull(context, PARAMETER_CONTEXT);
    Assert.isNotNull(reference, PARAMETER_REFERENCE);
    Assert.isNotNull(expectedObjects, PARAMETER_EXPECTED_OBJECTS);
    Assert.isTrue(context.eClass().getEAllReferences().contains(reference), String.format("Contract for argument '%s' failed: Parameter is not within specified range (Expected: %s, Actual: %s).", PARAMETER_CONTEXT, "The context object must contain the given reference.", "Reference not contained by the context object!"));
    Set<URI> expectedUriSet = Sets.newHashSet();
    for (EObject object : expectedObjects) {
      expectedUriSet.add(EcoreUtil.getURI(object));
    }
    IScope scope = getScopeProvider().getScope(context, reference);
    Iterable<IEObjectDescription> allScopedElements = scope.getAllElements();
    Set<URI> scopedUriSet = Sets.newHashSet();
    for (IEObjectDescription description : allScopedElements) {
      URI uri = description.getEObjectURI();
      scopedUriSet.add(uri);
    }
    if (!expectedUriSet.equals(scopedUriSet)) {
      fail("The scope must exactly consist of the expected URIs. Missing " + Sets.difference(expectedUriSet, scopedUriSet) + " extra "
          + Sets.difference(scopedUriSet, expectedUriSet));
    }
    // test that link resolving worked
    boolean elementResolved;
    if (reference.isMany()) {
      @SuppressWarnings("unchecked")
      EList<EObject> objects = (EList<EObject>) context.eGet(reference, true);
      elementResolved = !objects.isEmpty(); // NOPMD
      for (Iterator<EObject> objectIter = objects.iterator(); objectIter.hasNext() && elementResolved;) {
        EObject eObject = EcoreUtil.resolve(objectIter.next(), context);
        elementResolved = expectedUriSet.contains(EcoreUtil.getURI(eObject));
      }
    } else {
      EObject resolvedObject = (EObject) context.eGet(reference, true);
      elementResolved = expectedUriSet.contains(EcoreUtil.getURI(resolvedObject));
    }
    assertTrue(elementResolved, "Linking must have resolved one of the expected objects.");
  }

  /**
   * Check if scope expected is found in context provided.
   *
   * @param context
   *          element from which an element shall be referenced
   * @param reference
   *          to be used to filter the elements
   * @param expectedName
   *          name of scope element to look for
   * @param expectedUri
   *          of source referenced
   */
  private void assertScope(final EObject context, final EReference reference, final QualifiedName expectedName, final URI expectedUri) {
    IScope scope = getScopeProvider().getScope(context, reference);
    Iterable<IEObjectDescription> descriptions = scope.getElements(expectedName);
    assertFalse(Iterables.isEmpty(descriptions), "Description missing for: " + expectedName);
    URI currentUri = null;
    for (IEObjectDescription desc : descriptions) {
      currentUri = desc.getEObjectURI();
      if (currentUri.equals(expectedUri)) {
        return;
      }
    }
    assertEquals(expectedUri, currentUri, "Scope URI is not equal to expected URI");
  }

  /**
   * Assert the scope for given elements.
   *
   * @param context
   *          the context
   * @param reference
   *          the reference
   * @param expectedSourceName
   *          the name of the referenced source (without file extension)
   * @param expectedSourceType
   *          type of scope element to look for
   * @param elementNames
   *          array of tuples with the name and uri of each element
   */
  protected void assertScopeForElements(final EObject context, final EReference reference, final String expectedSourceName, final String expectedSourceType, final String[]... elementNames) {
    for (String[] elementName : elementNames) {
      assertScopeForElement(context, reference, elementName[0], expectedSourceName, expectedSourceType, elementName[1]);
    }
    int actualScopeSize = Iterables.size(getScopeProvider().getScope(context, reference).getAllElements());
    assertEquals(elementNames.length, actualScopeSize, NUMBER_OF_ELEMENTS_MESSAGE);
  }

  /**
   * Asserts the scope for the given context, reference, source type, and elements.
   *
   * @param context
   *          the context object
   * @param reference
   *          the reference feature
   * @param expectedSourceType
   *          the source-type name
   * @param elements
   *          list of triples with the expected elements, each triple ordered as: {element name, source name, URI fragment}
   */
  protected void assertScopeForElements(final EObject context, final EReference reference, final String expectedSourceType, final List<Triple<String, String, String>> elements) {
    Iterable<IEObjectDescription> allElements = getScopeProvider().getScope(context, reference).getAllElements();

    // create a set containing the URIs (to avoid counting any duplicates the scope provider might have delivered)
    Set<URI> uris = new HashSet<URI>();
    for (IEObjectDescription d : allElements) {
      uris.add(d.getEObjectURI());
    }

    int actualScopeSizeWithoutDuplicates = uris.size();
    assertEquals(elements.size(), actualScopeSizeWithoutDuplicates, NUMBER_OF_ELEMENTS_MESSAGE);
    for (Triple<String, String, String> elementName : elements) {
      assertScopeForElement(context, reference, elementName.getFirst(), elementName.getSecond(), expectedSourceType, elementName.getThird());
    }
  }

  /**
   * Asserts that the scope of the reference in the given context contains exactly the given sources.
   *
   * @param scopeContext
   *          the context of the scope test
   * @param reference
   *          the reference to check its scope for
   * @param modelElementClass
   *          the {@link EClass} of the model element to find
   * @param sources
   *          the array of sources
   */
  protected void assertScopeForSources(final EObject scopeContext, final EReference reference, final EClass modelElementClass, final String... sources) {
    assertScope(scopeContext, reference, getExpectedURIs(scopeContext, modelElementClass, sources));
    int actualScopeSize = Iterables.size(getScopeProvider().getScope(scopeContext, reference).getAllElements());
    assertEquals(sources.length, actualScopeSize, NUMBER_OF_ELEMENTS_MESSAGE);
  }

  /**
   * Returns the expected uris for the given sources in the given context.
   *
   * @param context
   *          the context
   * @param modelElementClass
   *          the class of the exported model element
   * @param sources
   *          the sources to get the uris for
   * @return the expected uris for the given sources in the given context
   */
  private Set<URI> getExpectedURIs(final EObject context, final EClass modelElementClass, final String... sources) {
    Set<URI> expectedURIs = new HashSet<URI>();
    for (String source : sources) {
      expectedURIs.add(Iterables.get(getExportedObjects(context, modelElementClass, source), 0).getEObjectURI());
    }
    return expectedURIs;
  }

  /**
   * Gets the exported objects.
   *
   * @param context
   *          the context
   * @param type
   *          the type
   * @param resourcePattern
   *          the resource pattern
   * @return the exported objects
   */
  public Iterable<IEObjectDescription> getExportedObjects(final EObject context, final EClass type, final String resourcePattern) {
    Pattern regexp = Regexps.fromGlob(URI.encodeSegment(resourcePattern, true));
    return Iterables.filter(ContainerQuery.newBuilder(domainMapper, type).execute(context), (o) -> regexp.matcher(o.getEObjectURI().lastSegment()).matches());
  }

  /**
   * Gets the exported names.
   *
   * @param execute
   *          the execute
   * @return the exported names
   */
  public List<String> getExportedNames(final Iterable<IEObjectDescription> execute) {
    return Lists.newArrayList(Iterables.transform(execute, new Function<IEObjectDescription, String>() {
      @Override
      public String apply(final IEObjectDescription from) {
        return from.getName().toString();
      }
    }));
  }

  /**
   * Checks if an object with given name (case sensitive) and type is exported.
   *
   * @param context
   *          the context
   * @param name
   *          the name
   * @param type
   *          the type
   * @return true, if is exported
   */
  public boolean isExported(final EObject context, final String name, final EClass type) {
    return isExported(context, name, type, false);
  }

  /**
   * Checks if an object with given name, case sensitive or not, and type is exported.
   *
   * @param context
   *          the context
   * @param name
   *          the name
   * @param type
   *          the type
   * @param ignoreCase
   *          the ignore case
   * @return true, if is exported
   */
  public boolean isExported(final EObject context, final String name, final EClass type, final boolean ignoreCase) {
    List<String> exportedNames = getExportedNames(ContainerQuery.newBuilder(domainMapper, type).execute(context));
    if (ignoreCase) {
      return Iterables.contains(Iterables.transform(exportedNames, new Function<String, String>() {
        @Override
        public String apply(final String from) {
          return from.toLowerCase(); // NOPMD
        }
      }), name);
    } else {
      return exportedNames.contains(name);
    }
  }

  /**
   * Gets the resource description for a given Xtext resource.
   *
   * @param resource
   *          the resource
   * @return the resource description
   */
  protected final IResourceDescription getResourceDescription(final XtextResource resource) {
    final IResourceServiceProvider resourceServiceProvider = resource.getResourceServiceProvider();
    final IResourceDescription.Manager descriptionManager = resourceServiceProvider.getResourceDescriptionManager();
    return descriptionManager.getResourceDescription(resource);
  }

  /**
   * Gets the fingerprint for a given resource description, returns <code>null</code> if resource description does not export any objects or if a non-existing
   * user data field was queried.
   *
   * @param description
   *          the description
   * @return the fingerprint or <code>null</code> if no fingerprint found
   */
  protected String getFingerprint(final IResourceDescription description) {
    Iterable<IEObjectDescription> objects = description.getExportedObjects();
    if (!Iterables.isEmpty(objects)) {
      IEObjectDescription objectDescription = Iterables.get(objects, 0);
      return objectDescription.getUserData(IFingerprintComputer.RESOURCE_FINGERPRINT);
    }
    return null;
  }

  /**
   * Creates a top level URI fragment with a leading segment separator from the given segments
   * taking into account repetitions.
   *
   * @param segments
   *          list of feature IDs, indexes (for multi valued features), and other fragment segments
   * @return URI fragment
   */
  public static String createTopLevelURIFragment(final Object... segments) {
    return createURIFragment(true, segments);
  }

  /**
   * Creates a URI fragment from the given segments taking into account repetitions.
   *
   * @param segments
   *          list of feature IDs, indexes (for multi valued features), and other fragment segments
   * @return URI fragment
   */
  public static String createURIFragment(final Object... segments) {
    return createURIFragment(false, segments);
  }

  /**
   * Creates a URI fragment from the given segments taking into account repetitions.
   *
   * @param topLevel
   *          whether the fragment is top level resulting in a leading segment separator.
   * @param segments
   *          list of feature IDs, indexes (for multi valued features), and other fragment segments
   * @return URI fragment
   */
  @SuppressWarnings("PMD.UnusedPrivateMethod")
  private static String createURIFragment(final boolean topLevel, final Object... segments) {
    StringBuilder b = new StringBuilder();
    if (segments.length == 0) {
      return b.toString();
    }
    if (topLevel) {
      b.append(SEGMENT_SEPARATOR);
    }
    List<String> parsedSegments = Lists.newArrayList();
    for (Object segment : segments) {
      Iterables.addAll(parsedSegments, FRAGMENT_SEGMENT_SPLITTER.split(segment.toString()));
    }

    String lastSegment = parsedSegments.get(0);
    int reps = 1;
    for (int i = 1; i < parsedSegments.size(); i++) {
      if (parsedSegments.get(i).equals(lastSegment)) {
        reps++;
        continue;
      }
      b.append(lastSegment);
      if (reps > 1) {
        b.append(REP_SEPARATOR).append(reps);
        reps = 1;
      }
      b.append(SEGMENT_SEPARATOR);
      lastSegment = parsedSegments.get(i);
    }
    b.append(lastSegment);
    if (reps > 1) {
      b.append(REP_SEPARATOR).append(reps);
    }
    return b.toString();
  }

  /**
   * Creates a URI fragment list segment for the given feature selection string and list index.
   *
   * @param feature
   *          the feature selection string, must not be {@code null} or empty
   * @param index
   *          the list index, must not be negative
   * @return the URI fragment list segment, never {@code null} or empty
   */
  public static String listFragmentSegment(final String feature, final int index) {
    return feature + AbstractFragmentProvider.LIST_SEPARATOR + index;
  }

  /**
   * Creates a URI fragment list segment for the given feature id and list index.
   *
   * @param featureId
   *          the featureId, must not be negative
   * @param index
   *          the list index, must not be negative
   * @return the URI fragment list segment, never {@code null} or empty
   */
  public static String listFragmentSegment(final int featureId, final int index) {
    return listFragmentSegment(String.valueOf(featureId), index);
  }

  /**
   * Creates a URI fragment segment to be used for languages using the {@code AbstractSelectorFragmentProvider}.
   *
   * @param containmentFeature
   *          containment feature
   * @param selectorFeature
   *          selector feature
   * @param value
   *          value for selector feature
   * @param unique
   *          if value is unique
   * @return URI fragment segment
   */
  public static String selectorFragmentSegment(final int containmentFeature, final int selectorFeature, final String value, final boolean unique) {
    StringBuilder builder = new StringBuilder();
    builder.append(containmentFeature).append(SELECTOR_START).append(selectorFeature).append(EQ_OP).append(VALUE_SEP).append(value).append(VALUE_SEP);
    if (unique) {
      builder.append(UNIQUE);
    }
    builder.append(SELECTOR_END);
    return builder.toString();
  }

  /**
   * Creates an expectation of a link. Use this method in tests to insert an expectation that a cross reference does actually point to the object tagged by the
   * target tag. Expectations can be tested by calling {@link #testExpectedLinking()}. Implicit items will be traversed.
   *
   * @see #mark(int)
   * @see #testLinking(String, CharSequence)
   * @param targetTag
   *          Tag pointing to the destination object
   * @return Mark text to be inserted in the source file, never {@code null}
   */
  protected String link(final int targetTag) {
    return link(() -> getObjectForTag(targetTag));
  }

  /**
   * Creates an expectation of a link. Use this method in tests to insert an expectation that a cross reference does actually point to the object tagged by the
   * target tag. Expectations can be tested by calling {@link #testExpectedLinking()}. Implicit items will be traversed.
   *
   * @see #mark(int)
   * @see #testLinking(String, CharSequence)
   * @param getTargetObject
   *          supplier to get the destination object, must not be {@code null}
   * @return Mark text to be inserted in the source file, never {@code null}
   */
  protected String link(final Supplier<EObject> getTargetObject) {
    final int sourceTag = getTag();
    expectedLinkAssertions.add(() -> testLinking(sourceTag, getTargetObject.get()));
    return mark(sourceTag);
  }

  /**
   * Performs linking test. Checks expectations which were set in a source using {@link #link(int)} or {@link #link(Function, int).
   *
   * @see #link(int)
   * @see #link(Supplier)
   * @see #testLinking(int, EObject)
   * @param sourceFileNameAndContent
   *          the file name and content, given as the key and value of the pair, respectively, must not be {@code null}
   */
  protected void testLinking(final Pair<String, String> sourceFileNameAndContent) {
    testLinking(sourceFileNameAndContent.getKey(), sourceFileNameAndContent.getValue());
  }

  /**
   * Performs linking test. Checks expectations which were set in a source using {@link #link(int)} or {@link #link(Function, int).
   *
   * @see #link(int)
   * @see #link(Supplier)
   * @see #testLinking(int, EObject)
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, must not be {@code null}
   * @param sourceContent
   *          source, must not be {@code null}
   */
  protected void testLinking(final String sourceFileName, final CharSequence sourceContent) {
    registerModel(sourceFileName, sourceContent);
    expectedLinkAssertions.forEach(Runnable::run);
    expectedLinkAssertions.clear();
  }

  /**
   * Performs linking test. Checks that given cross reference tagged with sourceTag does actually point to the object tagged by the target tag.
   * Detailed error reporting can be viewed in a compare view. Implicit items will be traversed.
   *
   * @param sourceTag
   *          Tag pointing to cross reference
   * @param targetTag
   *          Tag pointing to the destination object
   */
  protected void testLinking(final int sourceTag, final int targetTag) {
    testLinking(sourceTag, getObjectForTag(targetTag), true);
  }

  /**
   * Performs linking test. Checks that the cross reference marked with sourceTag does actually point to the object provided as the second argument. Implicit
   * items will be traversed.
   *
   * @param sourceTag
   *          Tag pointing to a cross reference
   * @param targetObject
   *          Expected target object
   */
  protected void testLinking(final int sourceTag, final EObject targetObject) {
    testLinking(sourceTag, targetObject, true);
  }

  /**
   * Performs linking test. Checks that the source object is the same as the object pointed by targetTag provided as the second argument. Implicit
   * items will be traversed.
   *
   * @param sourceObject
   *          Source object
   * @param targetTag
   *          Tag to the expected target object
   */
  protected void testLinking(final EObject sourceObject, final int targetTag) {
    testLinking(sourceObject, targetTag, true);
  }

  /**
   * Performs linking test. Checks that the cross reference marked with sourceTag does actually point to the object provided as the second argument.
   *
   * @param sourceTag
   *          Tag pointing to a cross reference
   * @param targetObject
   *          Expected target object, must not be {@code null}
   * @param traverseImplicitItems
   *          If target of a reference is an implicit item and this parameter is set to true, the test will get and compare the original object from which this
   *          implicit item was created
   */
  protected void testLinking(final int sourceTag, final EObject targetObject, final boolean traverseImplicitItems) {
    assertNotNull(targetObject, "Target object must not be null."); //$NON-NLS-1$
    CrossReference crossReference = getMarkerTagsInfo().getCrossReference(sourceTag);
    EObject referencedSourceObject = getCrossReferencedObject(sourceTag, traverseImplicitItems, crossReference);
    assertEObjectsAreEqual(referencedSourceObject, targetObject, crossReference);
  }

  /**
   * Performs linking test. Checks that the source object is the same as the object pointed by targetTag provided as the second argument.
   * Does not deal with cross-referencing.
   *
   * @param sourceObject
   *          Source object, must not be {@code null}
   * @param targetTag
   *          Tag to the referenced target object
   * @param traverseImplicitItems
   *          If target of a reference is an implicit item and this parameter is set to true, the test will get and compare the original object from which this
   *          implicit item was created
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  protected void testLinking(final EObject sourceObject, final int targetTag, final boolean traverseImplicitItems) {
    assertNotNull(sourceObject, "Source object must not be null."); //$NON-NLS-1$
    EObject referencedTargetObject = getObjectForTag(targetTag);
    assertEObjectsAreEqual(sourceObject, referencedTargetObject, null);
  }

  /**
   * Asserts whether the two objects are equal.
   *
   * @param sourceObject
   *          First object needed for comparison.
   * @param targetObject
   *          Target object needed for comparison.
   * @param crossReference
   *          CrossReference object, can be {@code null}
   */
  protected void assertEObjectsAreEqual(final EObject sourceObject, final EObject targetObject, final CrossReference crossReference) {
    StringBuilder expected = new StringBuilder();
    StringBuilder found = new StringBuilder();
    if (crossReference != null) {
      String crossReferenceText = "Cross reference:\n" + crossReference.toString() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
      expected.append(crossReferenceText);
      found.append(crossReferenceText);
    }
    expected.append(LINKS_TO);
    found.append(LINKS_TO);
    URI targetUri = EcoreUtil.getURI(targetObject);
    expected.append(targetUri);
    String sourceObjectUri = EcoreUtil.getURI(sourceObject).toString();
    found.append(sourceObjectUri);
    expected.append(WHICH_CORRESPONDS_TO);
    INode node;
    node = NodeModelUtils.findActualNodeFor(targetObject);
    if (node != null) {
      expected.append(NodeModelUtils.getTokenText(node));
    } else {
      expected.append(NO_NODE_MODEL_COULD_BE_A_DERIVED_OBJECT);
    }
    found.append(WHICH_CORRESPONDS_TO);
    node = NodeModelUtils.findActualNodeFor(sourceObject);
    if (sourceObject.eIsProxy()) {
      found.append(UNRESOLVED_REFERENCE);
    } else if (node != null) {
      found.append(NodeModelUtils.getTokenText(node));
    } else {
      found.append(NO_NODE_MODEL_COULD_BE_A_DERIVED_OBJECT);
    }
    assertEquals(expected.toString(), found.toString(), "Errors found. Consider compare view."); //$NON-NLS-1$
  }

  /**
   * Returns the referenced {@link EObject} pointed to by the cross reference.
   * <p>
   * <em>Note</em>: For implicit item traversal to work, a custom implementation must be provided by overriding this method.
   * </p>
   *
   * @param sourceTag
   *          the source tag
   * @param traverseImplicitItems
   *          If target of a reference is an implicit item and this parameter is set to true, the test will get and compare the original object from which this
   *          implicit item was created.
   * @param crossReference
   *          Cross reference to be resolved, must not be {@code null}
   * @return the referenced {@link EObject}, must not be {@code null}
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  protected EObject getCrossReferencedObject(final int sourceTag, final boolean traverseImplicitItems, final CrossReference crossReference) {
    EObject context = getObjectForTag(sourceTag);
    if (crossReference == null) {
      throw new IllegalArgumentException(NLS.bind("Cross reference on object ''{0}'' could not be resolved.", context.toString())); //$NON-NLS-1$
    }
    // We only handle references in assignments
    Assignment assignment = EcoreUtil2.getContainerOfType(crossReference, Assignment.class);
    EObject sourceObject;
    String featureName = assignment.getFeature();
    EReference reference = (EReference) context.eClass().getEStructuralFeature(featureName);
    if (reference.isMany()) {
      Object featureValue = context.eGet(reference, false);
      assertInstanceOf(EObjectResolvingEList.class, featureValue, "List must be of type EObjectResolvingEList"); //$NON-NLS-1$
      @SuppressWarnings("unchecked")
      EList<? extends EObject> objects = (EObjectResolvingEList<? extends EObject>) context.eGet(reference, false);
      if (objects.size() == 1) {
        sourceObject = EcoreUtil.resolve(objects.get(0), context);
      } else {
        // TODO DSL-166: Handle this case when needed for tests.
        throw new AssertionError("Multiple references not supported yet"); //$NON-NLS-1$
      }
    } else {
      sourceObject = (EObject) context.eGet(reference, true);
    }
    assertNotNull(sourceObject, "Bad test. Referenced object is null."); //$NON-NLS-1$
    return sourceObject;
  }
}
