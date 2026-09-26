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
package com.avaloq.tools.ddk.xtext.test.export.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.avaloq.tools.ddk.xtext.export.ExportConstants;
import com.avaloq.tools.ddk.xtext.export.ui.internal.ExportActivator;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.inject.Injector;


public final class ExportTestUtil extends AbstractXtextTestUtil {
  private ExportTestUtil() {
    // private constructor
  }

  /**
   * The singleton instance.
   */
  private static final class InstanceHolder {
    // Initialize-on-demand holder pattern.
    private static final ExportTestUtil INSTANCE = new ExportTestUtil();

    static ExportTestUtil get() {
      return INSTANCE;
    }
  }

  public static ExportTestUtil getInstance() {
    return InstanceHolder.get();
  }

  @Override
  protected Injector getInjector() {
    return ExportActivator.getInstance().getInjector(ExportConstants.GRAMMAR);
  }

  /**
   * Parses an export model from the given source and installs its derived state.
   * <p>
   * The inferrer resolves the grammar belonging to an export model by loading the {@code .xtext} file next to it. An empty
   * resource is registered under that URI so the lookup resolves without demand-loading a grammar; the model must therefore
   * not depend on its grammar. Both resources share one new resource set.
   * </p>
   *
   * @param modelUri
   *          the URI of the export model, must not be {@code null}
   * @param source
   *          the source of the export model, must not be {@code null}
   * @return the loaded resource, never {@code null}
   * @throws IOException
   *           if the model cannot be parsed
   */
  public XtextResource parseWithoutGrammar(final URI modelUri, final String source) throws IOException {
    final URI grammarUri = modelUri.trimFileExtension().appendFileExtension("xtext"); //$NON-NLS-1$
    final Resource grammarResource = new ResourceImpl(grammarUri) {
      @Override
      public boolean isLoaded() {
        return true;
      }
    };
    final XtextResourceSet resourceSet = getResourceSet();
    resourceSet.getResources().add(grammarResource);
    resourceSet.getURIResourceMap().put(grammarUri, grammarResource);

    final XtextResource resource = (XtextResource) resourceSet.createResource(modelUri);
    resourceSet.getResources().add(resource);
    resource.load(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), null);
    EcoreUtil.resolveAll(resource);
    return resource;
  }

}
