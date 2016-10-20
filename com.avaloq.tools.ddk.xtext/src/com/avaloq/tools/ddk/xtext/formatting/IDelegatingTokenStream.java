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
package com.avaloq.tools.ddk.xtext.formatting;

import org.eclipse.xtext.parsetree.reconstr.ITokenStream;


/**
 * This interface is used by {@link com.avaloq.tools.ddk.acf.common.formatting.RegionNodeModelFormatter}.
 */
public interface IDelegatingTokenStream extends ITokenStream {

  /**
   * Gets the delegate stream.
   *
   * @return ITokenStream - the delegate stream
   */
  ITokenStream getDelegateStream();

  /**
   * Sets the delegate stream.
   *
   * @param delegate
   *          - the stream to which output is to be written.
   */
  void setDelegateStream(ITokenStream delegate);

}
