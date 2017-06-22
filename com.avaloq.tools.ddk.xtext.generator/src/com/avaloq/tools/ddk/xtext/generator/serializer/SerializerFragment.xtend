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
package com.avaloq.tools.ddk.xtext.generator.serializer

import com.google.inject.Binder
import org.eclipse.xtext.generator.serializer.AbstractSemanticSequencer

class SerializerFragment extends org.eclipse.xtext.generator.serializer.SerializerFragment {

    override protected addLocalBindings(Binder binder) {
        super.addLocalBindings(binder)
        binder.bind(AbstractSemanticSequencer).to(AbstractFixedSemanticSequencer)
    }

}
