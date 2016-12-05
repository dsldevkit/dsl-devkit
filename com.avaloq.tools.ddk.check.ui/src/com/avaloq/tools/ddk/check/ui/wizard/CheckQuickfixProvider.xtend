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
package com.avaloq.tools.ddk.check.ui.wizard

import org.eclipse.xtext.generator.IFileSystemAccess

class CheckQuickfixProvider {

  def doGenerate(CheckProjectInfo info, IFileSystemAccess fsa){
    fsa.generateFile(info.fileName, info.fileContent)
  }

  def String fileName(CheckProjectInfo info){
    '''«info.path + info.catalogName + "QuickfixProvider.java"»'''
  }

  def fileContent(CheckProjectInfo info){
  '''
    package «info.packageName»;

    import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreQuickfixProvider;

    /**
     * Default quickfix provider for «info.catalogName».
     * <p>
     * Note that this class name must start with the catalog name and have <em>QuickfixProvider</em>
     * as suffix. It must be located in the same Java package as the catalog file.
     * </p>
     */
    public class «info.catalogName»QuickfixProvider implements ICoreQuickfixProvider  {

    //  @CoreFix(value = MyIssueCodes.NAME_ENTITY_0)
    //  public void fixEntityNameFirstUpper(final Issue issue,
    //      ICoreIssueResolutionAcceptor acceptor) {
    //    acceptor.accept(issue, "Correct entity name",
    //        "Correct name by setting first letter to upper case.",
    //        null, new ICoreSemanticModification() {
    //          public void apply(EObject element, ICoreModificationContext context) {
    //            if (element instanceof Entity) {
    //              final Entity entity = (Entity) element;
    //              String newName = String.valueOf(entity.getName().charAt(0)).toUpperCase();
    //              if (entity.getName().length() > 1) {
    //                newName += entity.getName().substring(1, entity.getName().length());
    //              }
    //              entity.setName(newName);
    //            }
    //          }
    //        });
    //  }

    }
    '''
  }

}
