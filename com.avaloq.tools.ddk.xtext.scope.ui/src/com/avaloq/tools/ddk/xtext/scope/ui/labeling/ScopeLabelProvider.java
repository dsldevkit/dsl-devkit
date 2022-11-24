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
package com.avaloq.tools.ddk.xtext.scope.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.google.inject.Inject;


/**
 * Provides labels for EObjects.
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
@SuppressWarnings("restriction")
public class ScopeLabelProvider extends DefaultEObjectLabelProvider {

  private static final int MAX_GUARD_LABEL_LENGTH = 30;

  @Inject
  private ISerializer serializer;

  @Inject
  public ScopeLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }

  /**
   * Return label text for an ScopeDefinition.
   * 
   * @param context
   *          The ScopeDefinition.
   * @return The label text.
   */
  @SuppressWarnings("nls")
  public String text(final ScopeDefinition context) {
    StringBuffer res = new StringBuffer("scope ");
    if (context.getName() != null) {
      res.append('(').append(context.getName()).append(") ");
    }
    if (context.getReference() != null) {
      res.append(context.getContextType().getName()).append('#').append(context.getReference().getName());
    } else {
      res.append(context.getTargetType().getName());
    }
    return res.toString();
  }

  /**
   * Return label text for an ScopeRule.
   * 
   * @param rule
   *          The ScopeRule.
   * @return The label text.
   */
  @SuppressWarnings("nls")
  public String text(final ScopeRule rule) {
    StringBuffer res = new StringBuffer();
    final ScopeContext context = rule.getContext();
    if (context.isGlobal()) {
      res.append("* ");
    } else {
      res.append(context.getContextType().getName());
      res.append(' ');
    }
    if (context.getGuard() != null) {
      String guard = serializer.serialize(context.getGuard());
      guard = guard.length() > MAX_GUARD_LABEL_LENGTH ? guard.substring(0, MAX_GUARD_LABEL_LENGTH) + "..." : guard; // NOPMD
      res.append('[').append(guard).append("] ");
    }
    return res.toString();
  }

  /**
   * The image for Import.
   * 
   * @param impaurt
   *          :-)
   *          the import
   * @return the import image
   */
  Image image(final Import impaurt) {
    return JavaPlugin.getImageDescriptorRegistry().get(JavaPluginImages.DESC_OBJS_IMPDECL);
  }

  /**
   * Text label for Import.
   * 
   * @param impaurt
   *          the import
   * @return the label, evtl. "unresolved ..." if the reference is not found
   */
  String text(final Import impaurt) {
    return (impaurt.getPackage() != null) ? impaurt.getPackage().getName() : "<unresolved Package>"; //$NON-NLS-1$
  }

}
