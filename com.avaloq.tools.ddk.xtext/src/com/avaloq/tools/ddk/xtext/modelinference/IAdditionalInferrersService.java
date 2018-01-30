/*
 * Copyright (c) Avaloq Licence AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.tools.ddk.xtext.modelinference;

import com.google.inject.ImplementedBy;


/**
 * This service allows additional inference for a given DSL to be executed outside of its plugin.
 */
@ImplementedBy(DefaultAdditionalInferrersService.class)
public interface IAdditionalInferrersService extends IModelInferrer {

}

/* Copyright (c) Avaloq Licence AG */