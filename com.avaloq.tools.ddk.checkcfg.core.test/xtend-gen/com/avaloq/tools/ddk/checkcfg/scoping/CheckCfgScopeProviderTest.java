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
package com.avaloq.tools.ddk.checkcfg.scoping;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextMarkerBasedTest;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.scoping.AbstractScopingTest;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;

@SuppressWarnings("all")
public final class CheckCfgScopeProviderTest extends AbstractScopingTest {
  private final IScopeProvider scopeProvider = this.getScopeProvider();
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected void registerRequiredSources() {
  }
  
  /**
   * Regression test for DSL-1498 Incorrect Catalog Name inserted by Content Assist
   * <p>
   * All catalogs supplied to Context Assist should be in the correct fully-qualified package.
   * </p>
   */
  @BugTest(value = "DSL-1498")
  public void testCatalogsAreInCorrectPackage() {
    try {
      final List<String> EXP_PACKAGE_NAME = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("com", "avaloq", "tools", "ddk", "check", "validation"));
      final int CURSOR_POS = this.getTag();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("check configuration testCheckCfg {");
      _builder.newLine();
      _builder.append("  ");
      String _mark = this.mark(CURSOR_POS);
      _builder.append(_mark, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      final String SOURCE_CONTENT = _builder.toString();
      String _testSourceFileName = this.getTestSourceFileName();
      this.registerModel(_testSourceFileName, SOURCE_CONTENT);
      AbstractXtextMarkerBasedTest.MarkerTagsInfo _markerTagsInfo = this.getMarkerTagsInfo();
      final EObject context = _markerTagsInfo.getModel(CURSOR_POS);
      boolean _equals = Objects.equal(null, context);
      if (_equals) {
        throw new NullPointerException("Got null context model");
      }
      IScope _scope = this.scopeProvider.getScope(context, CheckcfgPackage.Literals.CONFIGURED_CATALOG__CATALOG);
      final Iterable<IEObjectDescription> elements = _scope.getAllElements();
      boolean _isEmpty = IterableExtensions.isEmpty(elements);
      if (_isEmpty) {
        throw new Exception("Scope has no elements");
      }
      final Consumer<IEObjectDescription> _function = (IEObjectDescription element) -> {
        QualifiedName _name = element.getName();
        final List<String> actualName = _name.getSegments();
        int _size = actualName.size();
        int _minus = (_size - 1);
        final Iterable<String> actualPackageName = IterableExtensions.<String>take(actualName, _minus);
        Assert.assertArrayEquals("Catalog must have the correct fully-qualified package name", ((Object[])Conversions.unwrapArray(EXP_PACKAGE_NAME, Object.class)), ((Object[])Conversions.unwrapArray(actualPackageName, Object.class)));
      };
      elements.forEach(_function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
