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
package com.avaloq.tools.ddk.check.ui.builder;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.pde.internal.core.util.CoreUtility;
import org.eclipse.pde.internal.ua.core.ctxhelp.ICtxHelpConstants;
import org.eclipse.pde.internal.ua.core.ctxhelp.text.CtxHelpContext;
import org.eclipse.pde.internal.ua.core.ctxhelp.text.CtxHelpDocumentFactory;
import org.eclipse.pde.internal.ua.core.ctxhelp.text.CtxHelpModel;
import org.eclipse.pde.internal.ua.core.ctxhelp.text.CtxHelpRoot;
import org.eclipse.pde.internal.ua.core.ctxhelp.text.CtxHelpTopic;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckContextsExtensionHelper;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckProjectHelper;
import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * The class responsible for the manipulation of contexts.xml file.
 */
@SuppressWarnings({"restriction", "nls"})
public class CheckContextsGenerator {

  @Inject
  private CheckProjectHelper projectHelper;

  @Inject
  private CheckGeneratorNaming generatorNaming;

  @Inject
  private IStorage2UriMapper mapper;

  /**
   * Update contexts file.
   *
   * @param uri
   *          the uri to the current catalog
   * @param buildContext
   *          the context
   * @throws CoreException
   *           the core exception
   */
  public void updateContextsFile(final URI uri, final IBuildContext buildContext) throws CoreException {
    IFile file = projectHelper.getHelpFile(uri, CheckContextsExtensionHelper.CONTEXTS_FILE_NAME);

    CtxHelpModel model = new CtxHelpModel(CoreUtility.getTextDocument(file.getContents()), false);
    model.setUnderlyingResource(file);
    model.load();

    CtxHelpRoot root = model.getCtxHelpRoot();
    CtxHelpDocumentFactory factory = model.getFactory();

    Iterable<CtxHelpContext> contexts = Iterables.filter(model.getCtxHelpRoot().getChildren(), CtxHelpContext.class);

    // all context ids belonging to the catalog
    Set<String> ids = Sets.newHashSet();
    String contextIdPrefix = projectHelper.getCatalog(buildContext, uri).getName().toLowerCase() + "_";
    for (CtxHelpContext context : contexts) {
      if (context.getId().startsWith(contextIdPrefix)) {
        ids.add(context.getId());
      }
    }
    Set<String> checkIds = Sets.newHashSet();

    // Add context for all checks which have been added
    for (Check check : projectHelper.getCatalog(buildContext, uri).getAllChecks()) {
      if (!ids.contains(generatorNaming.getContextId(check))) {
        root.addChild(createContextForCheck(factory, check));
      }
      // all contextIds constructed of the checks of the catalog
      checkIds.add(generatorNaming.getContextId(check));
    }

    // Remove context for checks which have been deleted.
    for (String id : ids) {
      if (!checkIds.contains(id)) {
        for (CtxHelpContext context : contexts) {
          if (context.getId().equals(id)) {
            root.removeChild(context);
          }
        }
      }
    }

    sortAllContexts(model);

    model.save();
    model.dispose();
  }

  /**
   * This method sorts all contexts alphabetically so that the file always gets generated in the same order.
   *
   * @param model
   *          context model
   */
  private void sortAllContexts(final CtxHelpModel model) {
    Ordering<CtxHelpContext> ordering = Ordering.from((a, b) -> ComparisonChain.start().compare(a.getId(), b.getId()).result());
    List<CtxHelpContext> allContexts = Lists.newArrayList(Iterables.filter(model.getCtxHelpRoot().getChildren(), CtxHelpContext.class));
    List<CtxHelpContext> expectedContexts = ordering.sortedCopy(allContexts);
    CtxHelpRoot root = model.getCtxHelpRoot();

    if (!expectedContexts.equals(allContexts)) {
      for (int i = 0; i < expectedContexts.size(); i++) {
        CtxHelpContext expected = expectedContexts.get(i);
        CtxHelpContext actual = allContexts.get(i);
        if (!actual.getId().equals(expected.getId())) {
          root.swap(actual, expected);
          allContexts.set(allContexts.indexOf(expected), actual);
          allContexts.set(i, expected);
        }
      }
    }
  }

  /**
   * Creates a context for every check.
   *
   * @param factory
   *          the model factory
   * @param check
   *          the check
   * @return the new help context
   */
  private CtxHelpContext createContextForCheck(final CtxHelpDocumentFactory factory, final Check check) {
    CtxHelpContext context = factory.createContext();
    context.setID(generatorNaming.getContextId(check));
    CtxHelpTopic topic = factory.createTopic();
    topic.setLabel(check.getLabel());
    Path contextFilePath = new Path("docs/content/" + EcoreUtil2.getContainerOfType(check, CheckCatalog.class).getName() + ".html");
    topic.setXMLAttribute(ICtxHelpConstants.ATTRIBUTE_HREF, contextFilePath.toPortableString() + '#' + generatorNaming.getContextId(check));
    context.addChild(topic);
    return context;
  }

  /**
   * Removes the contexts for all checks of a deleted catalog.
   *
   * @param delta
   *          the delta
   * @throws CoreException
   *           the core exception
   */
  @SuppressWarnings("unchecked")
  public void removeContexts(final Delta delta) throws CoreException {
    final IProject project = RuntimeProjectUtil.getProject(delta.getUri(), mapper);
    if (project != null) {
      IFile file = projectHelper.getHelpFile(project, CheckContextsExtensionHelper.CONTEXTS_FILE_NAME);

      CtxHelpModel model = new CtxHelpModel(CoreUtility.getTextDocument(file.getContents()), false);
      model.setUnderlyingResource(file);
      model.load();

      CtxHelpRoot root = model.getCtxHelpRoot();
      Iterable<CtxHelpContext> contexts = Iterables.filter(Lists.newArrayList(root.getChildren()), CtxHelpContext.class);
      for (CtxHelpContext context : contexts) {
        Iterable<IEObjectDescription> catalogs = delta.getOld().getExportedObjectsByType(CheckPackage.Literals.CHECK_CATALOG);
        for (IEObjectDescription catalog : catalogs) {
          if (context.getId().startsWith(catalog.getName().getLastSegment().toLowerCase() + "_")) {
            root.removeChild(context);
          }
        }
      }
      model.save();
      model.dispose();
    }
  }
}
