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

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.mwe.utils.GenModelHelper;

import com.google.common.collect.Sets;


/**
 * Fix for GenModelHelper which recursively registers all referenced GenModels.
 * Remove once https://bugs.eclipse.org/bugs/show_bug.cgi?id=363774 has been fixed.
 */
public class DdkGenModelHelper extends GenModelHelper {

  @Override
  protected Collection<GenPackage> collectGenPackages(final GenModel genModel) {
    Set<GenPackage> pkgs = Sets.newLinkedHashSet();
    for (GenPackage pkg : genModel.getGenPackages()) {
      pkgs.add(pkg);
      pkgs.addAll(collectGenPackages(pkg));
    }
    for (GenPackage pkg : genModel.getUsedGenPackages()) {
      pkgs.addAll(collectGenPackages(pkg.getGenModel()));
    }
    return pkgs;
  }

}
