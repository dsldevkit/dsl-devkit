/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.imports.test;

import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase;
import com.avaloq.tools.ddk.check.imports.CheckRewritableImportSectionFactory;
import com.google.inject.Inject;
import junit.framework.TestCase;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.imports.RewritableImportSection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

/**
 * Tests for {@link CheckRewritableImportSectionFactoryTest}.
 */
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class CheckRewritableImportSectionFactoryTest extends AbstractCheckTestCase {
  @Inject
  private CheckRewritableImportSectionFactory factory;
  
  /**
   * Test parse() when passed a null XtextResource
   */
  @Test(expected = NullPointerException.class)
  public void testParseWithNullXtextResource() {
    this.factory.parse(null);
  }
  
  /**
   * Happy-path test for parse()
   */
  @Test
  public void testParseSuccessful() {
    final XtextResource mockResource = Mockito.<XtextResource>mock(XtextResource.class);
    EList<EObject> _contents = mockResource.getContents();
    OngoingStubbing<EList<EObject>> _when = Mockito.<EList<EObject>>when(_contents);
    BasicEList<EObject> _basicEList = new BasicEList<EObject>();
    _when.thenReturn(_basicEList);
    URI _uRI = mockResource.getURI();
    OngoingStubbing<URI> _when_1 = Mockito.<URI>when(_uRI);
    URI _mock = Mockito.<URI>mock(URI.class);
    _when_1.thenReturn(_mock);
    final RewritableImportSection rewritableImportSection = this.factory.parse(mockResource);
    TestCase.assertNotNull("parse() should return an object", rewritableImportSection);
    TestCase.assertTrue("parse() should return a RewritableImportSection", (rewritableImportSection instanceof RewritableImportSection));
    boolean _isSort = rewritableImportSection.isSort();
    TestCase.assertTrue("parse() should return a RewritableImportSection with sort=true", _isSort);
  }
}
