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
package com.avaloq.tools.ddk.xtext.test.validation;

import static org.eclipse.xtext.validation.ValidationMessageAcceptor.INSIGNIFICANT_INDEX;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
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
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.diagnostics.AbstractDiagnostic;
import org.eclipse.xtext.linking.impl.XtextLinkingDiagnostic;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextSyntaxDiagnostic;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.AbstractValidationDiagnostic;
import org.eclipse.xtext.validation.FeatureBasedDiagnostic;
import org.eclipse.xtext.validation.RangeBasedDiagnostic;
import org.eclipse.xtext.xbase.lib.Pair;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextMarkerBasedTest;
import com.avaloq.tools.ddk.xtext.test.XtextTestSource;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Provider;


/**
 * Base class for validation tests.
 */
@SuppressWarnings({"nls", "PMD.ExcessiveClassLength"})
// CHECKSTYLE:CHECK-OFF MultipleStringLiterals
public abstract class AbstractValidationTest extends AbstractXtextMarkerBasedTest {

  public static final int NO_ERRORS = 0;

  static final String NO_ERRORS_FOUND_ON_RESOURCE_MESSAGE = "Expecting no errors on resource";

  private static final int SEVERITY_UNDEFINED = -1;
  private static final Map<Integer, String> CODE_TO_NAME = ImmutableMap.of(Diagnostic.INFO, "INFO", Diagnostic.WARNING, "WARNING", Diagnostic.ERROR, "ERROR");

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
  private final Set<Diagnostic> unexpectedDiagnostics = Sets.newLinkedHashSet();
  private final Set<Resource.Diagnostic> unexpectedResourceDiagnostics = Sets.newLinkedHashSet();

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
  protected Set<Diagnostic> getUnexpectedDiagnostics() {
    return unexpectedDiagnostics;
  }

  /**
   * Returns the unexpectedDiagnostics.
   *
   * @return the unexpectedDiagnostics
   */
  protected Set<Resource.Diagnostic> getUnexpectedResourceDiagnostics() {
    return unexpectedResourceDiagnostics;
  }

  /**
   * Assertion testing for {@link AbstractValidationDiagnostic validation issues} at a given source position.
   */
  protected class XtextDiagnosticAssertion extends AbstractModelAssertion {

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

    protected XtextDiagnosticAssertion(final String issueCode, final boolean issueMustBeFound) {
      this(issueCode, issueMustBeFound, SEVERITY_UNDEFINED, null);
    }

    protected XtextDiagnosticAssertion(final String issueCode, final boolean issueMustBeFound, final int severity, final String message) {
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
            expectedSeverityMatches = expectedSeverity == SEVERITY_UNDEFINED || expectedSeverity == actualSeverity;
            actualMessage = avd.getMessage();
            // True if message matches with actual message or message is null
            expectedMessageMatches = message == null || actualMessage.equals(message);
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
    protected boolean diagnosticPositionEquals(final Integer pos, final AbstractValidationDiagnostic avd) {
      if (avd instanceof FeatureBasedDiagnostic && ((FeatureBasedDiagnostic) avd).getFeature() != null) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(avd.getSourceEObject(), ((FeatureBasedDiagnostic) avd).getFeature());
        if (nodes.isEmpty()) {
          INode node = NodeModelUtils.getNode(avd.getSourceEObject());
          if (getXtextTestUtil().findFirstNonHiddenLeafNode(node).getTotalOffset() == pos) {
            return true;
          }
        } else {
          int avdIndex = ((FeatureBasedDiagnostic) avd).getIndex();
          for (int i = 0; i < nodes.size(); i++) {
            if (avdIndex == INSIGNIFICANT_INDEX || avdIndex == i) {
              INode firstNonHiddenLeafNode = getXtextTestUtil().findFirstNonHiddenLeafNode(nodes.get(i));
              if (firstNonHiddenLeafNode.getTotalOffset() == pos) {
                return true;
              }
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
   * Assertion testing for {@link AbstractValidationDiagnostic validation issues} at a given source position.
   */
  private class ResourceDiagnosticAssertion extends AbstractModelAssertion {

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

    protected ResourceDiagnosticAssertion(final String issueCode, final boolean issueMustBeFound, final int severity, final String message) {
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
      Iterable<Resource.Diagnostic> diagnostics = null;
      switch (expectedSeverity) {
      case Diagnostic.ERROR:
        diagnostics = root.eResource().getErrors();
        break;
      case Diagnostic.WARNING:
        diagnostics = root.eResource().getWarnings();
        break;
      case SEVERITY_UNDEFINED:
        diagnostics = Iterables.concat(root.eResource().getErrors(), root.eResource().getWarnings());
        break;
      }
      final List<Resource.Diagnostic> diagnosticsOnTargetPosition = Lists.newArrayList();
      boolean issueFound = false;
      int actualSeverity = expectedSeverity;
      boolean expectedMessageMatches = false;
      String actualMessage = "";

      for (AbstractDiagnostic diag : Iterables.filter(diagnostics, AbstractDiagnostic.class)) {
        if (diagnosticPositionEquals(pos, diag)) {
          // Add issue to the list of issues at the given position
          diagnosticsOnTargetPosition.add(diag);
          if (diag.getCode().equals(issueCode)) {
            issueFound = true;
            if (expectedSeverity == SEVERITY_UNDEFINED) {
              actualSeverity = root.eResource().getErrors().contains(diag) ? Diagnostic.ERROR : Diagnostic.WARNING;
            }
            actualMessage = diag.getMessage();
            // True if message matches with actual message or message is null
            expectedMessageMatches = message == null || actualMessage.equals(message);
            // Don't need to display error messages
            if (issueMustBeFound) {
              // Remove the diagnostic from the list of non-expected diagnostics
              getUnexpectedResourceDiagnostics().remove(diag);
              // Don't need to display error messages
              if (expectedMessageMatches) {
                return;
              }
            }
          }
        }
      }

      // Create error message
      createErrorMessage(pos, diagnosticsOnTargetPosition, issueFound, true, actualSeverity, expectedMessageMatches, actualMessage);
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
    private void createErrorMessage(final Integer pos, final List<Resource.Diagnostic> diagnosticsOnTargetPosition, final boolean issueFound, final boolean expectedSeverityMatches, final int actualSeverity, final boolean expectedMessageMatches, final String actualMessage) {
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
        if (!diagnosticsOnTargetPosition.isEmpty()) {
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
     * @param diagnostic
     *          diagnostic that we check, if it has the same position as the given position in text
     * @return
     *         {@code true} if diagnostic has the same position as the given one, {@code false} otherwise.
     */
    private boolean diagnosticPositionEquals(final Integer pos, final AbstractDiagnostic diagnostic) {
      return diagnostic.getOffset() == pos.intValue();
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
  protected <T> T getCached(final EObject root, final String key, final Provider<T> provider) {
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
    return getCached(root, "DIAGNOSTIC", () -> getXtextTestUtil().getDiagnostician().validate(root));
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
    List<String> hierarchy = Lists.newLinkedList();

    EObject currentObject = eObject;
    while (currentObject != null) {
      hierarchy.add(0, offset + currentObject.toString());
      currentObject = currentObject.eContainer();
    }

    return String.join("\n", hierarchy);
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
          ICompositeNode compositeNode = NodeModelUtils.findActualNodeFor(avd.getSourceEObject());
          if (compositeNode != null) {
            sb.append(compositeNode.getStartLine());
          } else {
            sb.append("Unknown");
          }
          sb.append(" on \n");
          sb.append(pathFromRootAsString(avd.getSourceEObject(), "      "));
        }
        sb.append(LINE_BREAK);
      }
    }
    return sb.toString();
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
  private String diagnosticsToString(final List<Resource.Diagnostic> diagnostics, final boolean displayPathToTargetObject) {
    StringBuilder sb = new StringBuilder();
    for (Resource.Diagnostic diagnostic : diagnostics) {
      if (diagnostic instanceof AbstractDiagnostic) {
        AbstractDiagnostic diag = (AbstractDiagnostic) diagnostic;
        sb.append("    ");
        sb.append(diag.getCode());
        if (displayPathToTargetObject) {
          sb.append(" at line: ");
          sb.append(diag.getLine());
          sb.append(" on \n");
          sb.append("      ");
          sb.append(diag.getUriToProblem());
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
    return addAssertion(new XtextDiagnosticAssertion(issueCode, true, Diagnostic.INFO, message));
  }

  /**
   * Register a new validation marker with the given issue code. Expects a warning if the condition is {@code true}, no diagnostic otherwise.
   *
   * @param condition
   *          the condition when the marker is expected
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String warningIf(final boolean condition, final String issueCode) {
    if (condition) {
      return warning(issueCode);
    } else {
      return noDiagnostic(issueCode);
    }
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
    return addAssertion(new XtextDiagnosticAssertion(issueCode, true, Diagnostic.WARNING, message));
  }

  /**
   * Register a new validation marker with the given issue code. Expects an error if the condition is {@code true}, no diagnostic otherwise.
   *
   * @param condition
   *          the condition when the marker is expected
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String errorIf(final boolean condition, final String issueCode) {
    if (condition) {
      return error(issueCode);
    } else {
      return noDiagnostic(issueCode);
    }
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
    return addAssertion(new XtextDiagnosticAssertion(issueCode, true, Diagnostic.ERROR, message));
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
    return addAssertion(new XtextDiagnosticAssertion(issueCode, true, SEVERITY_UNDEFINED, message));
  }

  /**
   * Register a new linking error validation marker.
   * The issue is expected to be found in the test file.
   *
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String linkingError() {
    return linkingError(null);
  }

  /**
   * Register a new linking error validation marker with the given message.
   * The issue is expected to be found in the test file.
   *
   * @param message
   *          issuethe expected issue message
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String linkingError(final String message) {
    return addAssertion(new ResourceDiagnosticAssertion(org.eclipse.xtext.diagnostics.Diagnostic.LINKING_DIAGNOSTIC, true, Diagnostic.ERROR, message));
  }

  /**
   * Register a new resource validation marker with the given issue code and message.
   * The issue is expected to be found in the test file.
   *
   * @param issueCode
   *          issue code (usually found as static constant of the JavaValidator class of the DSL being tested)
   * @param message
   *          the expected issue message
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated
   */
  protected String resourceDiagnostic(final String issueCode, final String message) {
    return addAssertion(new ResourceDiagnosticAssertion(issueCode, true, Diagnostic.ERROR, message));
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
    return addAssertion(new XtextDiagnosticAssertion(issueCode, false));
  }

  @Override
  protected void beforeApplyAssertions(final XtextTestSource testSource) {
    super.beforeApplyAssertions(testSource);
    EObject root = testSource.getModel();
    // Get all diagnostics of the current testing file
    EcoreUtil2.resolveLazyCrossReferences(root.eResource(), CancelIndicator.NullImpl);
    fileDiagnostics = validate(root);
    getUnexpectedDiagnostics().addAll(fileDiagnostics.getChildren());
    getUnexpectedResourceDiagnostics().addAll(root.eResource().getErrors());
    getUnexpectedResourceDiagnostics().addAll(root.eResource().getWarnings());
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
    getUnexpectedResourceDiagnostics().clear();
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
   *          the model in which to look for issues, may be {@code null}
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertDiagnostic(final EObject model, final String issueCode) {
    if (model == null) {
      fail("Issue with code '" + issueCode + "' cannot be found because the model is null");
    }
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
   *          the model in which to look for issues, may be {@code null}
   * @param issueCode
   *          the code of the issue to look for
   */
  protected void assertNoDiagnostic(final EObject model, final String issueCode) {
    if (model == null) {
      fail("Issue with code '" + issueCode + "' cannot be found because the model is null");
    }

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
   *          the model in which to look for issues, may be {@code null}
   */
  protected void assertNoDiagnostics(final EObject model) {
    if (model == null) {
      fail("Assertion cannot be checked because the model is null");
    }

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
   *          the model in which to look for issues, may be {@code null}
   * @param message
   *          the message of the issue to look for
   */
  protected void assertDiagnosticMessage(final EObject model, final String message) {
    if (model == null) {
      fail("Message '" + message + "' cannot be found because the model is null");
    }

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
    fail("Issue with message ' " + message + "' not found");
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
    fail("Issue with code '" + issueCode + "' not found");
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
    fail("Issue with code '" + issueCode + "' not found");
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
        fail("Issue with code '" + issueCode + "' found");
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
    assertTrue("Diagnostics should be in OK state.", diagnostics.getCode() == Diagnostic.OK);
    assertTrue("There should be no diagnostics. Instead found " + diagnostics.getChildren().size(), diagnostics.getChildren().isEmpty());
  }

  /**
   * Assert no errors on resource exist.
   *
   * @param object
   *          the object
   */
  public static void assertNoErrorsOnResource(final EObject object) {
    final EList<Resource.Diagnostic> errors = object.eResource().getErrors();
    if (!errors.isEmpty()) {
      fail(AbstractValidationTest.NO_ERRORS_FOUND_ON_RESOURCE_MESSAGE + "; found " + Lists.transform(errors, Resource.Diagnostic::getMessage)); //$NON-NLS-1$
    }
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
    List<String> messageList = Arrays.asList(messages);
    final EList<Resource.Diagnostic> errors = object.eResource().getErrors();
    for (String errorMessage : Lists.transform(errors, Resource.Diagnostic::getMessage)) {
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
    final List<Resource.Diagnostic> linkingErrors = object.eResource().getErrors().stream().filter(error -> error instanceof XtextLinkingDiagnostic).collect(Collectors.toList());
    final List<String> errorMessages = Lists.transform(linkingErrors, Resource.Diagnostic::getMessage);
    for (final String referenceName : referenceNames) {
      boolean found = false;
      for (final String errMessage : errorMessages) {
        if (errMessage.startsWith(referenceName)) {
          found = true;
          break;
        }
      }
      assertFalse(NLS.bind("Expecting no linking errors on resource for \"{0}\".", referenceName), found);
    }
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
    final List<Resource.Diagnostic> linkingErrors = object.eResource().getErrors().stream().filter(error -> error instanceof XtextLinkingDiagnostic).collect(Collectors.toList());
    final List<String> errorMessages = Lists.transform(linkingErrors, Resource.Diagnostic::getMessage);
    for (final String referenceName : referenceNames) {
      boolean found = false;
      for (final String errMessage : errorMessages) {
        if (errMessage.contains(referenceName)) {
          found = true;
          break;
        }
      }
      assertTrue(NLS.bind("Expected linking error on \"{0}\" but could not find it", referenceName), found);
    }
  }

  /**
   * Expect the given linking error messages on the resource of the given model.
   *
   * @param object
   *          the object, must not be {@code null}
   * @param errorStrings
   *          the expected linking error error messages, must not be {@code null}
   */
  public static void assertLinkingErrorsWithCustomMessageOnResourceExist(final EObject object, final String... errorStrings) {
    final List<Resource.Diagnostic> linkingErrors = object.eResource().getErrors().stream().filter(error -> error instanceof XtextLinkingDiagnostic).collect(Collectors.toList());
    final List<String> errorMessages = Lists.transform(linkingErrors, Resource.Diagnostic::getMessage);
    for (final String s : errorStrings) {
      assertTrue(NLS.bind("Expected linking error \"{0}\" but could not find it", s), errorMessages.contains(s));
    }
  }

  /**
   * Assert no linking errors on resource with the given message exist.
   *
   * @param object
   *          the object, must not be {@code null}
   * @param messages
   *          the linking error messages, must not be {@code null}
   */
  public static void assertNoLinkingErrorsWithCustomMessageOnResource(final EObject object, final String... messages) {
    List<String> messageList = Arrays.asList(messages);
    final List<Resource.Diagnostic> linkingErrors = object.eResource().getErrors().stream().filter(error -> error instanceof XtextLinkingDiagnostic).collect(Collectors.toList());
    for (String errorMessage : Lists.transform(linkingErrors, Resource.Diagnostic::getMessage)) {
      assertFalse(NLS.bind("Expecting no linking errors on resource with message \"{0}\".", errorMessage), messageList.contains(errorMessage));
    }
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
    final List<String> errorMessages = Lists.transform(errors, Resource.Diagnostic::getMessage);
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
      fail(sb.toString());
    }
  }

  /**
   * Memorize the position and issue code of each resource error that appears in the file.
   *
   * @param root
   *          root node of the model to be analyzed
   */
  protected void memorizeUnexpectedResourceErrors() {
    for (Resource.Diagnostic diagnostic : getUnexpectedResourceDiagnostics()) {
      if (diagnostic instanceof AbstractDiagnostic) {
        AbstractDiagnostic diag = (AbstractDiagnostic) diagnostic;
        // Create error message
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected diagnostic found. Code '");
        sb.append(diag.getCode());
        sb.append(DOT_AND_LINEBREAK);
        // Retrieve the position and add the error
        memorizeErrorOnPosition(diag.getOffset(), sb.toString());
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
   * Memorize the position and issue code of each unexpected diagnostic that appears in the file.
   * A diagnostic is considered as expected if a marker with the issue code in the test file was set.
   */
  protected void memorizeUnexpectedErrors() {
    for (Diagnostic diagnostic : getUnexpectedDiagnostics()) {
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
    memorizeUnexpectedResourceErrors();
    processErrorsFound(testSource.getContent());
    afterValidate();
  }

  /**
   * Strictly validate a kernel source given by a {@link Pair} of file name and content.
   * All not expected diagnostics are considered as an error.
   *
   * @param sourceFileNameAndContent
   *          the file name and content, given as the key and value of the pair, respectively, must not be {@code null}
   */
  protected void validateKernelSourceStrictly(final Pair<String, String> sourceFileNameAndContent) {
    validateKernelSourceStrictly(sourceFileNameAndContent.getKey(), sourceFileNameAndContent.getValue());
  }

  /**
   * Strictly validate a customer source given by a {@link Pair} of file name and content.
   * All not expected diagnostics are considered as an error.
   *
   * @param sourceFileNameAndContent
   *          the file name and content, given as the key and value of the pair, respectively, must not be {@code null}
   */
  protected void validateCustomerSourceStrictly(final Pair<String, String> sourceFileNameAndContent) {
    validateCustomerSourceStrictly(sourceFileNameAndContent.getKey(), sourceFileNameAndContent.getValue());
  }

  /**
   * Strictly validate a kernel source given by a file name and content.
   * All not expected diagnostics are considered as an error.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, must not be {@code null}
   * @param sourceContent
   *          source, must not be {@code null}
   */
  protected void validateKernelSourceStrictly(final String sourceFileName, final CharSequence sourceContent) {
    validateStrictly(sourceFileName, TestSourceType.CLIENT_ALL, sourceContent);
  }

  /**
   * Strictly validate a customer source given by a file name and content.
   * All not expected diagnostics are considered as an error.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, must not be {@code null}
   * @param sourceContent
   *          source, must not be {@code null}
   */
  protected void validateCustomerSourceStrictly(final String sourceFileName, final CharSequence sourceContent) {
    validateStrictly(sourceFileName, TestSourceType.CLIENT_CUSTOMER, sourceContent);
  }
}
