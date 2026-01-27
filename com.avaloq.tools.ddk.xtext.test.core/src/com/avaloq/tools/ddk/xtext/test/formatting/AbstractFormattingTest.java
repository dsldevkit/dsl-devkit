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
package com.avaloq.tools.ddk.xtext.test.formatting;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.SaveOptions;
import org.junit.Assert;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;


/**
 * Base class for formatting tests.
 */
@SuppressWarnings("nls")
public abstract class AbstractFormattingTest extends AbstractXtextTest {

  private static final String CR_LF = "\r\n";
  private static final String LF = "\n";

  @Override
  protected List<String> getRequiredSourceFileNames() {
    List<String> result = super.getRequiredSourceFileNames();
    result.add(getExpectedTestSourceFileName());
    return result;
  }

  @Override
  protected String getTestSourceModelName() {
    return super.getTestSourceModelName() + "Input";
  }

  /**
   * The default implementation returns the default source model name for the test class and adds 'Expected'.
   * this test. A test class needs to override this, if the name of the expected formatting test source model differs from the default.
   *
   * @return the name of the expected formatting test source model
   */
  private String getExpectedTestSourceModelName() {
    return super.getTestSourceModelName() + "Expected";
  }

  /**
   * The default implementation returns the default source model name for the test class and adds 'Expected' and the default file extension for the grammar of
   * this test. A test class needs to override this, if the name of the expected formatting test source file differs from the default.
   *
   * @return the name of the expected formatting test source file
   */
  protected String getExpectedTestSourceFileName() {
    return getExpectedTestSourceModelName() + "." + getXtextTestUtil().getFileExtension();
  }

  /**
   * Test formatting based on the NodeModel.
   */
  @Test
  void formattedNodeModel() {
    assertFormattedNodeModel();
  }

  /**
   * Test formatting based on the ParseTreeConstructor.
   */
  public void formattedParseTreeConstructor() {
    assertFormattedParseTreeConstructor();
  }

  /**
   * Test preservation of formatting using ParseTreeConstructor.
   */
  public void preservedParseTreeConstructor() {
    assertPreservedParseTreeConstructor();
  }

  /**
   * Test preservation of formatting using NodeModelFormatter.
   */
  @Test
  void preservedNodeModel() {
    assertPreservedNodeModel();
  }

  /**
   * Test formatting based on the ParseTreeConstructor.
   */
  protected final void assertFormattedParseTreeConstructor() {
    assertFormattedParseTreeConstructor(getSemanticModel(), getTestSource(getExpectedTestSourceFileName()).getContent());
  }

  /**
   * Test formatting based on the NodeModel.
   *
   * @param offset
   *          Offset from which to start formatting
   * @param length
   *          Length of region to format
   */
  private void assertFormattedNodeModel(final int offset, final int length) {
    assertFormattedNodeModel(getSemanticModel(), getTestSource().getContent(), getTestSource(getExpectedTestSourceFileName()).getContent(), offset, length);
  }

  /**
   * Test formatting based on the NodeModel.
   */
  private void assertFormattedNodeModel() {
    assertFormattedNodeModel(0, getTestSource().getContent().length());
  }

  /**
   * Test preservation of formatting.
   */
  private void assertPreservedNodeModel() {
    String expectedContent = getTestSource(getExpectedTestSourceFileName()).getContent();
    assertFormattedNodeModel(getTestSource(getExpectedTestSourceFileName()).getModel(), expectedContent, expectedContent, 0, expectedContent.length());
  }

  /**
   * Test preservation of formatting.
   */
  protected final void assertPreservedParseTreeConstructor() {
    assertFormattedParseTreeConstructor(getTestSource(getExpectedTestSourceFileName()).getModel(), getTestSource(getExpectedTestSourceFileName()).getContent());
  }

  /**
   * Test formatting based on the ParseTreeConstructor.
   *
   * @param model
   *          the model to be serialized and compared with expected string
   * @param expected
   *          Expected formatted String
   */
  private void assertFormattedParseTreeConstructor(final EObject model, final String expected) {
    String actual = getXtextTestUtil().getSerializer().serialize(model, SaveOptions.newBuilder().format().getOptions());
    Assert.assertEquals("Formatted ParseTree", expected.replaceAll(CR_LF, LF), actual.replaceAll(CR_LF, LF));
  }

  /**
   * Test formatting based on the NodeModel.
   *
   * @param model
   *          the model to check
   * @param input
   *          String representing a serialized model
   * @param expected
   *          Expected formatted String
   * @param offset
   *          Offset from which to start formatting
   * @param length
   *          Length of region to format
   */
  private void assertFormattedNodeModel(final EObject model, final String input, final String expected, final int offset, final int length) {
    ICompositeNode node = NodeModelUtils.getNode(model).getRootNode();
    IFormattedRegion region = getXtextTestUtil().get(INodeModelFormatter.class).format(node, offset, length);
    String actual = input.substring(0, offset) + region.getFormattedText() + input.substring(length + offset);
    Assert.assertEquals("Formatted NodeModel", expected.replaceAll(CR_LF, LF), actual.replaceAll(CR_LF, LF));
  }

}
