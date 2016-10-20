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
package com.avaloq.tools.ddk.xtext.ui.editor.findrefs;

import org.eclipse.search.ui.IQueryListener;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchViewPage;


/**
 * Subclass to make sure search result is incrementally updated as the search progresses.
 */
public class DdkReferenceSearchViewPage extends ReferenceSearchViewPage {

  @Override
  protected IQueryListener createQueryListener() {
    return new IQueryListener() {

      @Override
      public void queryStarting(final ISearchQuery query) {
        showBusyLabel(false);
      }

      @Override
      public void queryRemoved(final ISearchQuery query) {
        showBusyLabel(false);
      }

      @Override
      public void queryFinished(final ISearchQuery query) {
        showBusyLabel(false);
      }

      @Override
      public void queryAdded(final ISearchQuery query) {
        showBusyLabel(false);
      }
    };
  }

}
