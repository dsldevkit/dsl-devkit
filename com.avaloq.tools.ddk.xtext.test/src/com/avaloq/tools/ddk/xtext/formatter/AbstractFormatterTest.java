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
package com.avaloq.tools.ddk.xtext.formatter;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.ISerializer;
import org.junit.Assert;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;


/**
 * Base class for formatting tests.
 * The assertXyz methods are essentially copied from {@link com.avaloq.tools.ddk.xtext.test.formatting.AbstractAcfFormattingTest}.
 */
@SuppressWarnings("nls")
public abstract class AbstractFormatterTest extends AbstractXtextTest {

  /**
   * Loads a model from a string representation of a source.
   *
   * @param input
   *          String representing a serialized model
   * @return Loaded model
   */
  private EObject getModel(final String input) throws IOException {
    return getXtextTestUtil().getModel("mytestmodel." + getXtextTestUtil().getFileExtension(), input);
  }

  /**
   * Gets the Guice injected serializer.
   *
   * @return Serializer the DI serializer
   */
  protected ISerializer getSerializer() {
    return getXtextTestUtil().getSerializer();
  }

  // test formatting based on the ParseTreeConstructorin
  protected void assertFormattedPTC(final String expected, final String model) throws IOException {
    EObject m = getModel(model);
    String res = getSerializer().serialize(m, SaveOptions.newBuilder().format().getOptions());
    Assert.assertEquals("Serialization not equal", expected, res);
  }

  protected void assertPreserved(final String model) throws IOException {
    EObject m = getModel(model);
    String res = getSerializer().serialize(m, SaveOptions.newBuilder().getOptions());
    Assert.assertEquals("Preserved node model", model, res);
  }

  // test formatting based on the NodeModel
  protected void assertFormattedNM(final String expected, final String model, final int offset, final int length) throws IOException {
    ICompositeNode node = NodeModelUtils.getNode(getModel(model)).getRootNode();
    IFormattedRegion r = getXtextTestUtil().get(INodeModelFormatter.class).format(node, offset, length);
    String actual = model.substring(0, r.getOffset()) + r.getFormattedText() + model.substring(r.getLength() + r.getOffset());
    Assert.assertEquals("Formatting based on the NodeModel", expected, actual);
  }

}
