/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.test.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.diagnostics.AbstractDiagnostic;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextSyntaxDiagnostic;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.AbstractValidationDiagnostic;
import org.eclipse.xtext.validation.FeatureBasedDiagnostic;
import org.eclipse.xtext.validation.RangeBasedDiagnostic;
import org.junit.Assert;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextMarkerBasedTest;
import com.avaloq.tools.ddk.xtext.test.XtextTestSource;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Provider;


/**
 * Base class for validation tests.
 */
public abstract class AbstractValidationTest extends AbstractXtextMarkerBasedTest {
  private static final String COULD_NOT_RESOLVE_REFERENCE_TO = "Couldn''t resolve reference to {0} ''{1}''.";
  public static final int NO_ERRORS = 0;
  public static final String FOUND_ERROR_ON_RESOURCE_MESSAGE = "Expecting error on resource";
  static final String NO_ERRORS_FOUND_ON_RESOURCE_MESSAGE = "Expecting no errors on resource";

  private static final ValidationHelper HELPER = new ValidationHelper();

  private static final int SEVERITY_UNDEFINED = -1;
  private static final Map<Integer, String> CODE_TO_NAME = Maps.newHashMap();

  static {
    CODE_TO_NAME.put(Diagnostic.INFO, "INFO");
    CODE_TO_NAME.put(Diagnostic.WARNING, "WARNING");
    CODE_TO_NAME.put(Diagnostic.ERROR, "ERROR");
  }

  private static final String LINE_BREAK = "\n";
  private static final String DOT_AND_LINEBREAK = "'." + LINE_BREAK;

  /**
   * All diagnostics of the current testing file.
   */
  private Diagnostic fileDiagnostics;

  /**
   * During validation of a source we monitor diagnostics, that are found in the source but were not expected by the test.
   * If the validation test is strict, then we will display these unexpected diagnostics as test error.
   */
  private final Set<Diagnostic> unexpectedDiagnostics = Sets.newHashSet();

  /**
   * validation results calculated during test setUp.
   *
   * @return the diagnostic for the primary test source file
   */
  private Diagnostic getPrimaryDiagnostics() {
    Object obj = getTestInformation().getTestObject(Diagnostic.class);
    assertNotNull("getPrimaryDiagnostics(): Diagnostics of primary source not null.", obj);
    return (Diagnostic) obj;
  }

  /**
   * Returns the unexpectedDiagnostics.
   *
   * @return the unexpectedDiagnostics
   */
  public Set<Diagnostic> getUnexpectedDiagnostics() {
    return unexpectedDiagnostics;
  }

  // --------------------------------------------------------------------------
  // AbstractValidationAssertion
  // --------------------------------------------------------------------------

  /**
   * Abstract interface for testing validation assertions on a given source position.
   */
  private class AbstractValidationAssertion extends AbstractModelAssertion {

    private static final int MINIMAL_STRINGBUILDER_CAPACITY = 100;

    /** Issue code of the diagnostic. */
    private final String issueCode;
    /** Issue message of the diagnostic. */
    private final String message;
    /**
     * Indicates whether the assertion must find the issue.
     * Assertion creates an error if the existence of issue code for the target eobject doesn't correspond to the value of issueMustBeFound.
     */
    private final boolean issueMustBeFound;

    private final int expectedSeverity;

    protected AbstractValidationAssertion(final String issueCode, final boolean issueMustBeFound) {
      this(issueCode, issueMustBeFound, SEVERITY_UNDEFINED, null);
    }

    protected AbstractValidationAssertion(final String issueCode, final boolean issueMustBeFound, final int severity, final String message) {
      this.issueCode = issueCode;
      this.issueMustBeFound = issueMustBeFound;
      this.expectedSeverity = severity;
      this.message = message;
    }

    /**
     * Check if the given issue code is found among issue codes for the object, located at the given position.
     *
     * @param root
     *          root object of the document
     * @param pos
     *          position to locate the target object
     */
    @Override
    public void apply(final EObject root, final Integer pos) {
      final Diagnostic diagnostics = validate(root);
      final BasicDiagnostic diagnosticsOnTargetPosition = new BasicDiagnostic();
      boolean issueFound = false;
      int actualSeverity = SEVERITY_UNDEFINED;
      boolean expectedSeverityMatches = false;
      boolean expectedMessageMatches = false;
      String actualMessage = "";

      for (AbstractValidationDiagnostic avd : Iterables.filter(diagnostics.getChildren(), AbstractValidationDiagnostic.class)) {
        if (diagnosticPositionEquals(pos, avd)) {
          // Add issue to the list of issues at the given position
          diagnosticsOnTargetPosition.add(avd);
          if (avd.getIssueCode().equals(issueCode)) {
            issueFound = true;
            actualSeverity = avd.getSeverity();
            // True if the expected severity is not set, or if matches with the actual one
            expectedSeverityMatches = expectedSeverity == SEVERITY_UNDEFINED || (expectedSeverity != SEVERITY_UNDEFINED && expectedSeverity == actualSeverity);
            actualMessage = avd.getMessage();
            // True if message matches with actual message or message is null
            expectedMessageMatches = (message == null) || (actualMessage.equals(message));
            if (issueMustBeFound) {
              // Remove the diagnostic from the list of non-expected diagnostics
              getUnexpectedDiagnostics().remove(avd);
              // Don't need to display error messages
              if (expectedSeverityMatches && expectedMessageMatches) {
                return;
              }
            }
          }
        }
      }

      // Create error message
      createErrorMessage(pos, diagnosticsOnTargetPosition, issueFound, expectedSeverityMatches, actualSeverity, expectedMessageMatches, actualMessage);
    }

    /**
     * Create an error message (if needed) based on the given input parameters.
     *
     * @param pos
     *          position in the source to associate the message with
     * @param diagnosticsOnTargetPosition
     *          diagnostics on the specifies position
     * @param issueFound
     *          specifies whether an issue has been found at the given position
     * @param expectedSeverityMatches
     *          true if expected severity equals actual one, false otherwise
     * @param actualSeverity
     *          actual severity
     * @param expectedMessageMatches
     *          expected message matches
     * @param actualMessage
     *          actual message
     */
    private void createErrorMessage(final Integer pos, final BasicDiagnostic diagnosticsOnTargetPosition, final boolean issueFound, final boolean expectedSeverityMatches, final int actualSeverity, final boolean expectedMessageMatches, final String actualMessage) {
      StringBuilder errorMessage = new StringBuilder(MINIMAL_STRINGBUILDER_CAPACITY);
      if (issueMustBeFound && !issueFound) {
        errorMessage.append("Expected issue not found. Code '" + issueCode + "'\n");
      } else if (!issueMustBeFound && issueFound) {
        errorMessage.append("There should be no issue with the code '" + issueCode + DOT_AND_LINEBREAK);
      }
      if (issueFound && !expectedMessageMatches) {
        errorMessage.append("Expected message does not match. Expected: '" + message + "', Actual: '" + actualMessage + "'\n");
      }
      // If the expected issue has been found, but the actual severity does not match with expected one
      if (issueMustBeFound && issueFound && !expectedSeverityMatches) {
        errorMessage.append("Severity does not match. Expected: " + CODE_TO_NAME.get(expectedSeverity) + ". Actual: " + CODE_TO_NAME.get(actualSeverity)
            + ".\n");
      }
      // Memorize error message
      if (errorMessage.length() > 0) {
        if (!diagnosticsOnTargetPosition.getChildren().isEmpty()) {
          errorMessage.append("  All issues at this position:\n");
          errorMessage.append(diagnosticsToString(diagnosticsOnTargetPosition, false));
        }
        memorizeErrorOnPosition(pos, errorMessage.toString());
      }
    }

    /**
     * Compare if the position of the given diagnostic equals to the given position in text.
     *
     * @param pos
     *          position in text
     * @param avd
     *          diagnostic that we check, if it has the same position as the given position in text
     * @return
     *         TRUE if diagnostic has the same position as the given one, FALSE otherwise.
     */
    private boolean diagnosticPositionEquals(final Integer pos, final AbstractValidationDiagnostic avd) {
      if (avd instanceof FeatureBasedDiagnostic && ((FeatureBasedDiagnostic) avd).getFeature() != null) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(avd.getSourceEObject(), ((FeatureBasedDiagnostic) avd).getFeature());
        if (nodes.isEmpty()) {
          INode node = NodeModelUtils.getNode(avd.getSourceEObject());
          if (getXtextTestUtil().findFirstNonHiddenLeafNode(node).getTotalOffset() == pos) {
            return true;
          }
        } else {
          for (INode node : nodes) {
            INode firstNonHiddenLeafNode = getXtextTestUtil().findFirstNonHiddenLeafNode(node);
            if (firstNonHiddenLeafNode.getTotalOffset() == pos) {
              return true;
            }
          }
        }
      } else if (avd instanceof RangeBasedDiagnostic) {
        if (((RangeBasedDiagnostic) avd).getOffset() == pos) {
          return true;
        }
      } else {
        INode node = NodeModelUtils.getNode(avd.getSourceEObject());
        if (getXtextTestUtil().findFirstNonHiddenLeafNode(node).getTotalOffset() == pos) {
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Get a cached version of an object associated with the root object for a given key.
   *
   * @param <T>
   *          type of the associated object
   * @param root
   *          root EObject
   * @param key
   *          key identifying the type of the associated object
   * @param provider
   *          provider to deliver an object if there is no cached version
   * @return
   *         cached version of the associated object
   */
  public <T> T getCached(final EObject root, final String key, final Provider<T> provider) {
    XtextResource res = (XtextResource) root.eResource();
    return res.getCache().get(key, res, provider);
  }

  /**
   * Validate the model.
   *
   * @param root
   *          root EObject to validate
   * @return
   *         validation results
   */
  protected Diagnostic validate(final EObject root) {
    return getCached(root, "DIAGNOSTIC", new Provider<Diagnostic>() {
      @Override
      public Diagnostic get() {
        return getXtextTestUtil().getDiagnostician().validate(root);
      }
    });
  }

  /**
   * Display the path from root object to the target EObject.
   *
   * @param eObject
   *          object to display the object path for
   * @param offset
   *          string offset that is added in the beginning of each line
   * @return
   *         object hierarchy as string (each object on a single line)
   */
  private String pathFromRootAsString(final EObject eObject, final String offset) {
    List<String> hierarchy = Lists.newArrayList();

    EObject currentObject = eObject;
    while (currentObject != null) {
      hierarchy.add(0, offset.concat(currentObject.toString()));
      currentObject = currentObject.eContainer();
    }

    return Strings.concat("\n", hierarchy);
  }

  /**
   * Persist list diagnostics into string to display the list of issue codes.
   *
   * @param diagnostics
   *          list of diagnostics
   * @param displayPathToTargetObject
   *          if true, the path through the object hierarchy is printed out up to the root node
   * @return
   *         string with list of issue codes, separated with a line break
   */
  // TODO (ACF-4153) generalize for all kinds of errors and move to AbstractXtextTest
  private String diagnosticsToString(final Diagnostic diagnostics, final boolean displayPathToTargetObject) {
    StringBuilder sb = new StringBuilder();
    for (Diagnostic diagnostic : diagnostics.getChildren()) {
      if (diagnostic instanceof AbstractValidationDiagnostic) {
        AbstractValidationDiagnostic avd = (AbstractValidationDiagnostic) diagnostic;
        sb.append("    ");
        sb.append(avd.getIssueCode());
        if (displayPathToTargetObject) {
          sb.append(" at line: ");
          sb.append(NodeModelUtils.findActualNodeFor(avd.getSourceEObject()).getStartLine());
          sb.append(" on \n");
          sb.append(pathFromRootAsString(avd.getSourceEObject(), "      "));
        }
        sb.append(LINE_BREAK);
      }
    }
    return sb.toString();
  }

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    if (getTestSource() != null) {
      Diagnostic primaryDiagnostics = getXtextTestUtil().getDiagnostician().validate(getSemanticModel());
      getTestInformation().putTestObject(Diagnostic.class, primaryDiagnostics);
    }
  }

  /**
   * Register a new validation marker with the given issue code. Expects an info.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String info(final String issueCode) {
    return info(issueCode, null);
  }

  /**
   * Register a new validation marker with the given issue code and message. Expects an info.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @param message
   *          the expected issue message
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String info(final String issueCode, final String message) {
    return addAssertion(new AbstractValidationAssertion(issueCode, true, Diagnostic.INFO, message));
  }

  /**
   * Register a new validation marker with the given issue code. Expects a warning.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String warning(final String issueCode) {
    return warning(issueCode, null);
  }

  /**
   * Register a new validation marker with the given issue code and message. Expects a warning.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @param message
   *          the expected issue message
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String warning(final String issueCode, final String message) {
    return addAssertion(new AbstractValidationAssertion(issueCode, true, Diagnostic.WARNING, message));
  }

  /**
   * Register a new validation marker with the given issue code. Expects an error.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String error(final String issueCode) {
    return error(issueCode, null);
  }

  /**
   * Register a new validation marker with the given issue code and message. Expects an error.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @param message
   *          the expected issue message
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String error(final String issueCode, final String message) {
    return addAssertion(new AbstractValidationAssertion(issueCode, true, Diagnostic.ERROR, message));
  }

  /**
   * Register a new validation marker with the given issue code.
   * The issue is expected to be found in the test file.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String diagnostic(final String issueCode) {
    return diagnostic(issueCode, null);
  }

  /**
   * Register a new validation marker with the given issue code and message.
   * The issue and message are expected to be found in the test file.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @param message
   *          the expected issue message
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String diagnostic(final String issueCode, final String message) {
    return addAssertion(new AbstractValidationAssertion(issueCode, true, SEVERITY_UNDEFINED, message));
  }

  /**
   * Register a new validation marker with the given issue code.
   * The issue is expected NOT to be found in the test file.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String noDiagnostic(final String issueCode) {
    return addAssertion(new AbstractValidationAssertion(issueCode, false));
  }

  @Override
  protected void beforeApplyAssertions(final XtextTestSource testSource) {
    super.beforeApplyAssertions(testSource);
    EObject root = testSource.getModel();
    // Get all diagnostics of the current testing file
    fileDiagnostics = validate(root);
    getUnexpectedDiagnostics().addAll(fileDiagnostics.getChildren());
  }

  @Override
  protected String getAdditionalErrorMessageInformation() {
    return diagnosticsToString(fileDiagnostics, true);
  }

  @Override
  protected void afterValidate() {
    super.afterValidate();
    // Garbage collection
    getUnexpectedDiagnostics().clear();
  }

  /**
   * Assert that diagnosticList contains a diagnostic of the given issueCode.
   *
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertDiagnostic(final String issueCode) {
    assertDiagnostic(getPrimaryDiagnostics(), issueCode);
  }

  /**
   * Assert that the given EObject model contains a diagnostic of the given issueCode.
   *
   * @param model
   *          the model in which to look for issues
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertDiagnostic(final EObject model, final String issueCode) {
    assertDiagnostic(getXtextTestUtil().getDiagnostician().validate(model), issueCode);
  }

  /**
   * Assert that diagnosticList does not contain a diagnostic of the given issueCode.
   *
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertNoDiagnostic(final String issueCode) {
    assertNoDiagnostic(getPrimaryDiagnostics(), issueCode);
  }

  /**
   * Assert that the given EObject model does not contain a diagnostic of the given issueCode.
   *
   * @param model
   *          the model in which to look for issues
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertNoDiagnostic(final EObject model, final String issueCode) {
    assertNoDiagnostic(getXtextTestUtil().getDiagnostician().validate(model), issueCode);
  }

  /**
   * Assert that diagnosticList does not contain any diagnostic.
   */
  protected void assertNoDiagnostics() {
    assertNoDiagnostics(getPrimaryDiagnostics());
  }

  /**
   * Assert that the given EObject model does not contain any diagnostic.
   *
   * @param model
   *          the model in which to look for issues
   */
  protected void assertNoDiagnostics(final EObject model) {
    assertNoDiagnostics(getXtextTestUtil().getDiagnostician().validate(model));
  }

  /**
   * Assert that diagnosticList contains a diagnostic with the given message.
   *
   * @param message
   *          the message of the issue to look for
   */
  protected void assertDiagnosticMessage(final String message) {
    assertDiagnosticMessage(getPrimaryDiagnostics(), message);
  }

  /**
   * Assert that the given EObject model contains a diagnostic with the given message.
   *
   * @param model
   *          the model in which to look for issues
   * @param message
   *          the message of the issue to look for
   */
  protected void assertDiagnosticMessage(final EObject model, final String message) {
    assertDiagnosticMessage(getXtextTestUtil().getDiagnostician().validate(model), message);
  }

  /**
   * Assert that diagnosticList contains a diagnostic with the given message.
   *
   * @param diagnostics
   *          the diagnostic to check for issues
   * @param message
   *          the message of the issue to look for
   */
  private static void assertDiagnosticMessage(final Diagnostic diagnostics, final String message) {
    for (Diagnostic diagnostic : diagnostics.getChildren()) {
      if (diagnostic.getMessage().equals(message)) {
        return;
      }
    }
    Assert.fail("Issue with message ' " + message + "' not found");
  }

  /**
   * Assert that diagnosticList contains a diagnostic of the given issueCode.
   *
   * @param diagnostics
   *          the diagnostic to check for issues
   * @param issueCode
   *          the code of the issue to look for
   */
  private void assertDiagnostic(final Diagnostic diagnostics, final String issueCode) {
    for (Diagnostic diagnostic : diagnostics.getChildren()) {
      if (diagnostic instanceof AbstractValidationDiagnostic && ((AbstractValidationDiagnostic) diagnostic).getIssueCode().equals(issueCode)) {
        return;
      }
    }
    Assert.fail("Issue with code '" + issueCode + "' not found");
  }

  /**
   * Assert that diagnosticList contains a diagnostic of the given issueCode on a given EObject.
   * For performance reasons one can validate the root object and afterwards use this method
   * to check that a particular diagnostic exists on one of the child objects of the validated model.
   *
   * @param diagnostics
   *          the diagnostic to check for issues
   * @param targetObject
   *          the object that should have a diagnostic with the given issueCode
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertDiagnosticOnObject(final Diagnostic diagnostics, final EObject targetObject, final String issueCode) {
    for (Diagnostic diagnostic : diagnostics.getChildren()) {
      if (diagnostic instanceof AbstractValidationDiagnostic) {
        AbstractValidationDiagnostic avd = (AbstractValidationDiagnostic) diagnostic;
        if (avd.getSourceEObject() == targetObject && avd.getIssueCode().equals(issueCode)) {
          return;
        }
      }
    }
    Assert.fail("Issue with code '" + issueCode + "' not found");
  }

  /**
   * Assert that diagnosticList does not contain a diagnostic of the given issueCode.
   *
   * @param diagnostics
   *          the diagnostic to check for issues
   * @param issueCode
   *          the code of the issue to look for
   */
  private void assertNoDiagnostic(final Diagnostic diagnostics, final String issueCode) {
    for (Diagnostic diagnostic : diagnostics.getChildren()) {
      if (((AbstractValidationDiagnostic) diagnostic).getIssueCode().equals(issueCode)) {
        Assert.fail("Issue with code '" + issueCode + "' found");
        return;
      }
    }
  }

  /**
   * Assert that diagnosticList does not contain a diagnostic of the given issueCode.
   *
   * @param diagnostics
   *          the diagnostic to check for issues
   * @param targetObject
   *          the object that should not have the target validation
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertNoDiagnosticOnObject(final Diagnostic diagnostics, final EObject targetObject, final String issueCode) {
    for (Diagnostic diagnostic : diagnostics.getChildren()) {
      AbstractValidationDiagnostic validationDiagnostic = (AbstractValidationDiagnostic) diagnostic;
      if (validationDiagnostic.getSourceEObject() == targetObject && validationDiagnostic.getIssueCode().equals(issueCode)) {
        Assert.fail("Issue with code '" + issueCode + "' found");
        return;
      }
    }
  }

  /**
   * Assert that diagnosticList does not contain any diagnostic.
   *
   * @param diagnostics
   *          the diagnostic to check for issues
   */
  private void assertNoDiagnostics(final Diagnostic diagnostics) {
    assertTrue(diagnostics.getCode() == Diagnostic.OK);
    assertTrue(diagnostics.getChildren().isEmpty());
  }

  /**
   * Tests whether given list of diagnostics contains an error with given message.
   *
   * @param errors
   *          the errors
   * @param message
   *          the message
   * @return true, if given list of errors contains an error with given message
   */
  public static boolean containsError(final EList<Resource.Diagnostic> errors, final String message) {
    return !Iterables.isEmpty(errors) && Iterables.contains(Iterables.transform(errors, new Function<Resource.Diagnostic, String>() {
      @Override
      public String apply(final Resource.Diagnostic d) {
        return d.getMessage();
      }
    }), message);
  }

  /**
   * Assert no errors on resource exist.
   *
   * @param object
   *          the object
   */
  public static void assertNoErrorsOnResource(final EObject object) {
    HELPER.assertNoSyntaxOrLinkingErrors(object);
  }

  /**
   * Assert no errors on resource with the given message exist.
   *
   * @param object
   *          the object
   * @param messages
   *          the messages
   */
  public static void assertNoErrorsOnResource(final EObject object, final String... messages) {
    List<String> messageList = Lists.newArrayList(messages);
    final EList<Resource.Diagnostic> errors = object.eResource().getErrors();
    for (String errorMessage : HELPER.getErrorMessages(errors)) {
      assertFalse(NO_ERRORS_FOUND_ON_RESOURCE_MESSAGE + " with message '" + errorMessage + "'.", messageList.contains(errorMessage));
    }
  }

  /**
   * Assert no linking errors on resource with the given message exist.
   *
   * @param object
   *          the object
   * @param referenceType
   *          the type of the referenced elements
   * @param referenceNames
   *          the names of the referenced elements
   */
  public static void assertNoLinkingErrorsOnResource(final EObject object, final String referenceType, final String... referenceNames) {
    List<String> messages = Lists.newArrayList();
    for (String referenceName : referenceNames) {
      messages.add(NLS.bind(COULD_NOT_RESOLVE_REFERENCE_TO, referenceType, referenceName));
    }
    assertNoErrorsOnResource(object, messages.toArray(new String[] {}));
  }

  /**
   * Assert linking errors on resource with the given message exist.
   *
   * @param object
   *          the object
   * @param referenceType
   *          the type of the referenced elements
   * @param referenceNames
   *          the names of the referenced elements
   */
  public static void assertLinkingErrorsOnResourceExist(final EObject object, final String referenceType, final String... referenceNames) {
    List<String> messages = Lists.newArrayList();
    for (String referenceName : referenceNames) {
      messages.add(NLS.bind(COULD_NOT_RESOLVE_REFERENCE_TO, referenceType, referenceName));
    }
    assertErrorsOnResourceExist(object, messages.toArray(new String[] {}));
  }

  /**
   * Expect given error messages on the resource of given model.
   *
   * @param object
   *          the object
   * @param errorStrings
   *          the error strings
   */
  public static void assertErrorsOnResourceExist(final EObject object, final String... errorStrings) {
    final EList<Resource.Diagnostic> errors = object.eResource().getErrors();
    final List<String> errorMessages = HELPER.getErrorMessages(errors);
    for (final String s : errorStrings) {
      assertTrue(NLS.bind("Expected error \"{0}\" but could not find it", s), errorMessages.contains(s));
    }
  }

  /**
   * Validates if there is a syntax error present in the source content.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, must not be {@code null}
   * @param sourceContent
   *          source, must not be {@code null}
   */
  protected void assertNoSyntaxErrorsOnResource(final String sourceFileName, final CharSequence sourceContent) {
    final XtextTestSource testSource = createTestSource(sourceFileName, sourceContent.toString());
    final List<Resource.Diagnostic> errors = testSource.getModel().eResource().getErrors().stream().filter(error -> error instanceof XtextSyntaxDiagnostic).collect(Collectors.toList());
    if (!errors.isEmpty()) {
      StringBuilder sb = new StringBuilder("Syntax error is present in the test source.\nList of all found syntax errors:");
      errors.forEach(err -> sb.append("\n\t " + err.getMessage()));
      Assert.fail(sb.toString());
    }
  }

  /**
   * Memorize the position and issue code of each resource error that appears in the file.
   *
   * @param root
   *          root node of the model to be analyzed
   */
  private void memorizeResourceErrors(final EObject root) {
    for (AbstractDiagnostic ad : Iterables.filter(root.eResource().getErrors(), AbstractDiagnostic.class)) {
      StringBuilder sb = new StringBuilder("Unexpected error: '");
      sb.append(ad.getMessage());
      sb.append(DOT_AND_LINEBREAK);
      memorizeErrorOnPosition(ad.getOffset(), sb.toString());
    }
  }

  /**
   * Memorize the position and issue code of each unexpected diagnostic that appears in the file.
   * A diagnostic is considered as expected if a marker with the issue code in the test file was set.
   */
  private void memorizeUnexpectedErrors() {
    for (Diagnostic diagnostic : unexpectedDiagnostics) {
      if (diagnostic instanceof AbstractValidationDiagnostic) {
        AbstractValidationDiagnostic avd = (AbstractValidationDiagnostic) diagnostic;
        // Create error message
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected issue found. Code '");
        sb.append(avd.getIssueCode());
        sb.append(DOT_AND_LINEBREAK);
        // Retrieve the position and add the error
        if (avd instanceof FeatureBasedDiagnostic && ((FeatureBasedDiagnostic) avd).getFeature() != null) {
          List<INode> nodes = NodeModelUtils.findNodesForFeature(avd.getSourceEObject(), ((FeatureBasedDiagnostic) avd).getFeature());
          if (nodes.isEmpty()) {
            INode node = NodeModelUtils.getNode(avd.getSourceEObject());
            memorizeErrorOnPosition(getXtextTestUtil().findFirstNonHiddenLeafNode(node).getTotalOffset(), sb.toString());
          } else {
            for (INode node : nodes) {
              memorizeErrorOnPosition(getXtextTestUtil().findFirstNonHiddenLeafNode(node).getTotalOffset(), sb.toString());
            }
          }
        } else if (avd instanceof RangeBasedDiagnostic) {
          memorizeErrorOnPosition(((RangeBasedDiagnostic) avd).getOffset(), sb.toString());
        } else {
          memorizeErrorOnPosition(NodeModelUtils.getNode(avd.getSourceEObject()).getTotalOffset(), sb.toString());
        }
      } else {
        // Create error message
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected diagnostic found. '");
        sb.append(diagnostic.toString());
        sb.append(DOT_AND_LINEBREAK);
        // Add error message
        memorizeErrorOnPosition(0, sb.toString());
      }
    }
  }

  /**
   * Strictly validates a source given by a file name and content.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, must not be {@code null}
   * @param sourceType
   *          defines if the source is a kernel or customer source, must not be {@code null}
   * @param sourceContent
   *          source, must not be {@code null}
   */
  protected void validateStrictly(final String sourceFileName, final TestSourceType sourceType, final CharSequence sourceContent) {
    XtextTestSource testSource = processMarkers(sourceFileName, sourceType, sourceContent);
    memorizeUnexpectedErrors();
    memorizeResourceErrors(testSource.getModel());
    processErrorsFound(testSource.getContent());
    afterValidate();
  }

  /**
   * Strictly validate a kernel source given by a file name and content.
   * All not expected diagnostics are considered as an error.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, not {@code null}
   * @param sourceContent
   *          source, not {@code null}
   */
  protected void validateKernelSourceStrictly(final String sourceFileName, final CharSequence sourceContent) {
    validateStrictly(sourceFileName, TestSourceType.CLIENT_ALL, sourceContent);
  }

  /**
   * Strictly validate a customer source given by a file name and content.
   * All not expected diagnostics are considered as an error.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, not {@code null}
   * @param sourceContent
   *          source, not {@code null}
   */
  protected void validateCustomerSourceStrictly(final String sourceFileName, final CharSequence sourceContent) {
    validateStrictly(sourceFileName, TestSourceType.CLIENT_CUSTOMER, sourceContent);
  }
}
