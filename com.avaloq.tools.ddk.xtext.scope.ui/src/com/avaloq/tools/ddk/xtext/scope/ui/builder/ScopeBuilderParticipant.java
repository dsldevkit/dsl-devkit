/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.scope.ui.builder;

import com.avaloq.tools.ddk.xtext.builder.ConditionalBuilderParticipant;


/**
 * A builder participant for the Scope DSL. Honors the workspace-wide DDK master switch that disables regeneration of the generated artifacts on workspace
 * builds (see {@link ConditionalBuilderParticipant#isBuilderParticipantEnabled()}).
 */
public class ScopeBuilderParticipant extends ConditionalBuilderParticipant {

}
