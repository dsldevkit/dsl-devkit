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
package com.avaloq.tools.ddk.xtext.generator.util;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xpand2.XpandExecutionContextImpl;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.OutputImpl;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;

import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.google.common.collect.Maps;


/**
 * Provides methods allowing for creation of a {@link XpandExecutionContext} need by e.g. org.eclipse.xtext.builder.BuilderParticipant.
 */
public final class XpandExecutionContextUtil {

  private static final String SRC_PATH = "/src"; //$NON-NLS-1$
  private static final String SRC_GEN_PATH = "/src-gen"; //$NON-NLS-1$

  /**
   * Private constructor for utility classes.
   */
  private XpandExecutionContextUtil() {}

  /**
   * Constructs {@link XpandExecutionContext} required by {@link org.eclipse.xtext.generator.AbstractGeneratorFragment} subclasses (fragment concepts from MWE2)
   * to generate output files.
   * Current configuration does not allow for overriding already generated files belonging to {@code src} i.e. if file does not exist it would be created and
   * generated, but if the file belonging {@code src} exists it would remain untouched.
   * 
   * @param resource
   *          resource to be processed by org.eclipse.xtext.builder.BuilderParticipant
   * @param delta
   *          change for which a BuilderParticipant is run
   * @param mapper
   *          class returning e.g. set of storages org.eclipse.core.resources.IStorage matching given URI; injected by concrete BuilderParticipant
   * @return context required by XPand to generate output files
   */
  public static XpandExecutionContext createExecutionContext(final Resource resource, final Delta delta, final IStorage2UriMapper mapper) {

    String pathRtProject = RuntimeProjectUtil.getPathProject(resource, mapper);

    if (pathRtProject == null) {
      return null;
    }

    String encoding = System.getProperty("file.encoding"); //$NON-NLS-1$

    OutputImpl output = new OutputImpl();

    output.addOutlet(new Outlet(false, encoding, org.eclipse.xtext.generator.Generator.PLUGIN_RT, false, pathRtProject));
    output.addOutlet(new Outlet(false, encoding, org.eclipse.xtext.generator.Generator.SRC, false, pathRtProject + SRC_PATH));
    output.addOutlet(new Outlet(false, encoding, org.eclipse.xtext.generator.Generator.SRC_GEN, true, pathRtProject + SRC_GEN_PATH));

    output.addOutlet(new Outlet(false, encoding, org.eclipse.xtext.generator.Generator.PLUGIN_UI, false, pathRtProject));
    output.addOutlet(new Outlet(false, encoding, org.eclipse.xtext.generator.Generator.SRC_UI, false, pathRtProject + SRC_PATH));
    output.addOutlet(new Outlet(false, encoding, org.eclipse.xtext.generator.Generator.SRC_GEN_UI, true, pathRtProject + SRC_GEN_PATH));

    Map<String, Variable> globalVars = Maps.newHashMap();
    globalVars.put(Naming.GLOBAL_VAR_NAME, new Variable(Naming.GLOBAL_VAR_NAME, new Naming()));

    XpandExecutionContextImpl execCtx = new XpandExecutionContextImpl(output, null, globalVars, null, null);
    execCtx.getResourceManager().setFileEncoding("ISO-8859-1"); //$NON-NLS-1$
    execCtx.registerMetaModel(new JavaBeansMetaModel());
    execCtx = (XpandExecutionContextImpl) execCtx.cloneWithVariable(new Variable("modelPluginID", RuntimeProjectUtil.getProject(delta.getUri(), mapper).getName())); //$NON-NLS-1$
    execCtx = (XpandExecutionContextImpl) execCtx.cloneWithVariable(new Variable("resourceUri", delta.getUri())); //$NON-NLS-1$
    return execCtx;
  }
}

