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
package com.avaloq.tools.ddk.xtext.generator.serializer;

import com.avaloq.tools.ddk.xtext.generator.serializer.AbstractFixedSemanticSequencer;
import com.google.inject.Binder;
import com.google.inject.binder.AnnotatedBindingBuilder;
import org.eclipse.xtext.generator.serializer.AbstractSemanticSequencer;

@SuppressWarnings("all")
public class SerializerFragment extends org.eclipse.xtext.generator.serializer.SerializerFragment {
  @Override
  protected void addLocalBindings(final Binder binder) {
    super.addLocalBindings(binder);
    AnnotatedBindingBuilder<AbstractSemanticSequencer> _bind = binder.<AbstractSemanticSequencer>bind(AbstractSemanticSequencer.class);
    _bind.to(AbstractFixedSemanticSequencer.class);
  }
}
