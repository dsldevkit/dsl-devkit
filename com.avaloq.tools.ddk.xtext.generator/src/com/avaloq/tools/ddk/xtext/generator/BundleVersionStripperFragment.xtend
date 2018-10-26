/*
 * Copyright (c) Avaloq Licence AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.tools.ddk.xtext.generator

import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.List
import org.eclipse.xtext.xtext.generator.model.ManifestAccess

/**
 * Removes the bundle-version from specific bundles in generated manifests
 *
 * In some projects, we choose not to include bundle-versions in the manifest, but various fragments generate them anyway.
 * Run this fragment at the end to remove versions for specific bundles
 */
class BundleVersionStripperFragment extends AbstractXtextGeneratorFragment {

  @Accessors(PROTECTED_GETTER)
  val List<String> bundles = newArrayList

  def void addBundle(String bundleName) {
    this.bundles.add(bundleName)
  }

  override generate() {
    if (projectConfig.runtime?.manifest !== null) {
      projectConfig.runtime.manifest.stripBundleVersions
    }
    if (projectConfig.eclipsePlugin?.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.stripBundleVersions
    }
  }

  def stripBundleVersions(ManifestAccess manifest) {
    bundles.forEach[ bundleName |
      if (manifest.requiredBundles.removeIf([it.startsWith(bundleName)])) {
        manifest.requiredBundles.add(bundleName)
      }
    ]

  }

}

/* Copyright (c) Avaloq Licence AG */