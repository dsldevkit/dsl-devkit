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
package com.avaloq.tools.ddk.xtext.test; //NOPMD

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.xtext.diagnostics.AbstractDiagnostic;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.testing.util.ResourceLoadHelper;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.validation.AbstractValidationDiagnostic;
import org.eclipse.xtext.validation.FeatureBasedDiagnostic;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.validation.RangeBasedDiagnostic;
import org.eclipse.xtext.xbase.lib.Pair;

import com.avaloq.tools.ddk.xtext.test.model.ModelUtil;
import com.avaloq.tools.ddk.xtext.test.validation.ValidationHelper;
import com.avaloq.tools.ddk.xtext.ui.util.Function;
import com.avaloq.tools.ddk.xtext.ui.util.UiThreadDispatcher;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Injector;


/**
 * Utility for Xtext tests.
 */
public abstract class AbstractXtextTestUtil extends AbstractTestUtil implements ResourceLoadHelper /* , IInjectorProvider */ {

  private final ModelUtil modelUtil = new ModelUtil();
  private final ValidationHelper validationHelper = new ValidationHelper();

  private static final String ERROR = "ERROR %d: ";
  private static final String ERROR_MARKER = "<ERROR %d>";
  private static final String SPLITTER = "------------------------\n";

  /**
   * Returns the current injector used in this plugin.
   *
   * @return the current injector for this language
   * @deprecated this method should be implemented only and not called directly; use {@link #get(Class)} instead
   */
  @Deprecated
  protected abstract Injector getInjector();

  /**
   * Opens an editor for a specific {@link URI}.
   *
   * @param uri
   *          to open editor for
   * @return editor opened
   */
  public XtextEditor openEditor(final URI uri) {
    XtextEditor editor = UiThreadDispatcher.dispatchAndWait(new Function<XtextEditor>() {
      @Override
      public XtextEditor run() {
        closeWelcomePage();
        return (XtextEditor) get(GlobalURIEditorOpener.class).open(uri, false);
      }
    });
    waitForEditorJobs(editor);
    return editor;
  }

  /**
   * Parses the given instance and returns the model representation.
   *
   * @param sourceFileName
   *          to associate the source with, used to determine the source content type
   * @param content
   *          String representation of the content to parse
   * @return model representation
   * @throws IOException
   *           may be thrown if the instance cannot be parsed
   */
  public final EObject getModel(final String sourceFileName, final String content) throws IOException {
    XtextResource resource = getResource(getTestProjectManager().createTestSourceUri(sourceFileName), content);
    return resource.getParseResult().getRootASTElement();
  }

  /**
   * Creates a resource with the given URI and content.
   *
   * @param uri
   *          to associate the model with
   * @param content
   *          String representation of the create a resource from
   * @return {@link XtextResource} created
   * @throws IOException
   *           may be thrown when trying to load the given content
   */
  protected final XtextResource getResource(final URI uri, final String content) throws IOException {
    StringInputStream instanceStream = new StringInputStream(content);
    XtextResourceSet rs = getResourceSet();
    XtextResource resource = (XtextResource) rs.createResource(uri);
    rs.getResources().add(resource);
    resource.load(instanceStream, null);
    EcoreUtil.resolveAll(resource);
    return resource;
  }

  public String getDefaultSourceName() {
    return "mytestmodel";
  }

  /** {@inheritDoc} */
  @Override
  public XtextResource getResourceFor(final InputStream stream) {
    XtextResourceSet rs = getResourceSet();
    XtextResource resource = (XtextResource) rs.createResource(getTestProjectManager().createTestSourceUri(getDefaultSourceName() + '.' + getFileExtension()));
    rs.getResources().add(resource);
    try {
      resource.load(stream, null);
    } catch (IOException e) {
      throw new WrappedException("Could not create XtextResource from input stream.", e);
    }
    EcoreUtil.resolveAll(resource);
    return resource;
  }

  /**
   * Validates the provided document and returns a list of issues found.
   *
   * @param document
   *          to validate
   * @return list of issues found
   */
  public List<Issue> getIssues(final IXtextDocument document) {
    return validationHelper.getIssues(document);
  }

  /**
   * Use current injector to get a class instance.
   *
   * @param <T>
   *          Class type
   * @param clazz
   *          class of object to get.
   * @return object of given class
   */
  public <T> T get(final Class<T> clazz) {
    return getInjector().getInstance(clazz);
  }

  /**
   * File extension associated with the instance's grammar.
   *
   * @return grammar specific file extension
   */
  public String getFileExtension() {
    return get(FileExtensionProvider.class).getPrimaryFileExtension();
  }

  /**
   * Creates a grammar specific instance of {@link XtextResourceSet}.
   *
   * @return grammar specific {@link XtextResourceSet}
   */
  public XtextResourceSet getResourceSet() {
    return get(XtextResourceSet.class);
  }

  /**
   * Creates a grammar specific instance of {@link ISerializer}.
   *
   * @return grammar specific {@link ISerializer}
   */
  public ISerializer getSerializer() {
    return get(ISerializer.class);
  }

  /**
   * Creates a grammar specific instance of {@link Diagnostician}.
   *
   * @return grammar specific {@link Diagnostician}
   */
  public Diagnostician getDiagnostician() {
    return get(Diagnostician.class);
  }

  /**
   * Closes the welcome page if it is open.
   */
  protected void closeWelcomePage() {
    UiThreadDispatcher.dispatchAndWait(new Runnable() {
      @Override
      public void run() {
        IIntroPart intro = PlatformUI.getWorkbench().getIntroManager().getIntro();
        if (intro != null) {
          PlatformUI.getWorkbench().getIntroManager().closeIntro(intro);
        }
      }
    });
  }

  /**
   * Gets the first instance of given type containing a given structural feature with given value using a given context object.
   *
   * @param <T>
   *          the generic type
   * @param context
   *          the context object
   * @param type
   *          the type
   * @return the first instance found or null if none found matching given criteria
   */
  public <T extends EObject> T getFirstInstanceOf(final EObject context, final Class<T> type) {
    return modelUtil.getFirstInstanceOf(context, type);
  }

  /**
   * Gets the all instances of given type <code>type</code> having given value <code>value</code> on structural feature <code>feature</code>.
   *
   * @param <T>
   *          the generic type
   * @param context
   *          the context
   * @param type
   *          the type
   * @param feature
   *          the feature
   * @param value
   *          the value
   * @return the all instances of
   */
  public <T extends EObject> Iterable<T> getAllInstancesOf(final EObject context, final Class<T> type, final EStructuralFeature feature, final Object value) {
    return modelUtil.getAllInstancesOf(context, type, feature, value);
  }

  /**
   * Gets the first instance of given type containing a given structural feature with given value using a given context object.
   *
   * @param <T>
   *          the generic type
   * @param context
   *          the context object
   * @param type
   *          the type
   * @param feature
   *          the structural feature
   * @param value
   *          the value
   * @return the first instance found or null if none found matching given criteria
   */
  public <T extends EObject> T getFirstInstanceOf(final EObject context, final Class<T> type, final EStructuralFeature feature, final Object value) {
    return modelUtil.getFirstInstanceOf(context, type, feature, value);
  }

  /**
   * Validates a source with a given name and content.
   *
   * @param sourceFileName
   *          source name
   * @param sourceContent
   *          content
   */
  public void validateSource(final String sourceFileName, final CharSequence sourceContent) {
    String sourceContentAsString = sourceContent.toString();
    EObject root;
    try {
      root = getModel(sourceFileName, sourceContentAsString);
    } catch (IOException e) {
      fail("Model creation failed: " + e.getMessage());
      return;
    }

    StringBuilder sourceContentWithErrors = new StringBuilder(sourceContent);
    // Store all the validation errors
    Set<Diagnostic> errors = Sets.newHashSet();
    errors.addAll(Collections2.filter(getDiagnostician().validate(root).getChildren(), new Predicate<Diagnostic>() {
      @Override
      public boolean apply(final Diagnostic input) {
        return input.getSeverity() == Diagnostic.ERROR;
      }
    }));

    Map<Integer, String> errorMessages = Maps.newHashMap();
    for (Diagnostic diagnostic : errors) {
      Pair<Integer, String> result = processDiagnostic(diagnostic);
      if (result != null) {
        errorMessages.put(result.getKey(), result.getValue());
      }
    }

    // Store all the resource errors
    for (AbstractDiagnostic diagnostic : Iterables.filter(root.eResource().getErrors(), AbstractDiagnostic.class)) {
      errorMessages.put(diagnostic.getOffset(), diagnostic.getMessage());
    }

    List<Integer> offsets = Lists.newArrayListWithExpectedSize(errorMessages.size());
    offsets.addAll(errorMessages.keySet());
    Collections.sort(offsets);

    // Append all the error messages to the end of the source
    int errorNumber = 1;
    ListIterator<Integer> offsetIterator = offsets.listIterator();
    while (offsetIterator.hasNext()) {
      sourceContentWithErrors.append(SPLITTER);
      sourceContentWithErrors.append(String.format(ERROR, errorNumber++));
      sourceContentWithErrors.append(errorMessages.get(offsetIterator.next())).append('\n');
    }
    if (errorNumber > 1) {
      sourceContentWithErrors.append(SPLITTER);
    }

    // Insert all the error markers (<ERROR X>) to the source
    // Done in inverse order to avoid the confusion with offset adjustments
    while (offsetIterator.hasPrevious()) {
      sourceContentWithErrors.insert(offsetIterator.previous(), String.format(ERROR_MARKER, --errorNumber));
    }

    assertEquals("Errors found: ", sourceContentAsString, sourceContentWithErrors.toString());
  }

  /**
   * Gets the offset and the error message for a given {@link Diagnostic}.
   *
   * @param diagnostic
   *          instance of {@link Diagnostic}
   * @return
   *         offset and error message
   */
  private Pair<Integer, String> processDiagnostic(final Diagnostic diagnostic) {
    StringBuilder errorMessage = new StringBuilder();
    if (diagnostic instanceof AbstractValidationDiagnostic) {
      AbstractValidationDiagnostic avd = (AbstractValidationDiagnostic) diagnostic;
      errorMessage.append("Unexpected issue found. Code '");
      errorMessage.append(avd.getIssueCode()).append("'\n");
      errorMessage.append(avd.getMessage());
      if (avd instanceof FeatureBasedDiagnostic && ((FeatureBasedDiagnostic) avd).getFeature() != null) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(avd.getSourceEObject(), ((FeatureBasedDiagnostic) avd).getFeature());
        if (nodes != null && !nodes.isEmpty()) {
          return new Pair<Integer, String>(findFirstNonHiddenLeafNode(nodes.get(0)).getTotalOffset(), errorMessage.toString());
        }
      } else if (avd instanceof RangeBasedDiagnostic) {
        return new Pair<Integer, String>(((RangeBasedDiagnostic) avd).getOffset(), errorMessage.toString());
      } else {
        return new Pair<Integer, String>(NodeModelUtils.getNode(avd.getSourceEObject()).getTotalOffset(), errorMessage.toString());
      }
    }
    return null;
  }

  /**
   * Given an AST node, find the first non-hidden leaf node among child nodes using deep search.
   * For the sake of compatibility the method can handle LeafNodes and CompositeNodes.
   * In case of a LeafNode the result is the input node itself.
   *
   * @param node
   *          entry point
   * @return
   *         first node for which isHidden() is false or the original node
   */
  public INode findFirstNonHiddenLeafNode(final INode node) {
    if (node instanceof ICompositeNode) {
      for (ILeafNode leaf : node.getLeafNodes()) {
        if (!leaf.isHidden()) {
          return leaf;
        }
      }
    }
    return node;
  }
}
