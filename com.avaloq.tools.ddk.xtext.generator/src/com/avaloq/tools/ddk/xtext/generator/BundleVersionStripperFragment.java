/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.xtext.generator;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.ManifestAccess;
import org.eclipse.xtext.xtext.generator.model.project.IBundleProjectConfig;

/**
 * Removes the bundle-version from specific bundles in generated manifests
 *
 * In some projects, we choose not to include bundle-versions in the manifest, but various fragments generate them anyway.
 * Run this fragment at the end to remove versions for specific bundles
 */
public class BundleVersionStripperFragment extends AbstractXtextGeneratorFragment {

  private final List<String> bundles = new ArrayList<>();

  public void addBundle(final String bundleName) {
    bundles.add(bundleName);
  }

  @Override
  public void generate() {
    for (IBundleProjectConfig project : Iterables.filter(getProjectConfig().getEnabledProjects(), IBundleProjectConfig.class)) {
      ManifestAccess manifest = project.getManifest();
      if (manifest != null) {
        stripBundleVersions(manifest);
      }
    }
  }

  public void stripBundleVersions(final ManifestAccess manifest) {
    for (String bundleName : bundles) {
      if (manifest.getRequiredBundles().removeIf(it -> it.startsWith(bundleName))) {
        manifest.getRequiredBundles().add(bundleName);
      }
    }
  }

  protected List<String> getBundles() {
    return bundles;
  }
}

/* Copyright (c) Avaloq Group AG */
