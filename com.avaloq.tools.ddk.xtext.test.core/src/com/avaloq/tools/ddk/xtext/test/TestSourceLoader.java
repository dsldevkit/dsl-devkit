/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.WrappedException;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * A loader used to load XtextTestSource objects.
 */
public final class TestSourceLoader {
  private final ITestSourceFactory testSourceFactory;

  @Inject
  public TestSourceLoader(final ITestSourceFactory testSourceFactory) {
    this.testSourceFactory = testSourceFactory;
  }

  /**
   * Loads the given set of test sources from the classpath.
   * 
   * @param clazz
   *          context class
   * @param sourceNames
   *          name of sources to load
   * @return an iterable of the sources
   */
  public Iterable<TestSource> loadFromClasspath(final Class<?> clazz, final String... sourceNames) {
    return Lists.transform(Lists.newArrayList(sourceNames), new Function<String, TestSource>() {
      public TestSource apply(final String input) {
        return testSourceFactory.createTestSource(input, TestSource.getResourceContent(clazz, input));
      }
    });
  }

  /**
   * Loads all test sources from the classpath. It will load all test sources residing in the same Java package as the given class.
   * 
   * @param clazz
   *          context class
   * @return an iterable of the sources
   */
  @SuppressWarnings({"nls", "PMD.AvoidDeeplyNestedIfStmts"})
  public Iterable<TestSource> loadAllFromClasspath(final Class<?> clazz) {
    URL url = clazz.getResource('/' + clazz.getPackage().getName().replace('.', '/') + '/');
    if (url == null) {
      throw new IllegalArgumentException("Cannot load test sources for " + clazz);
    }
    String protocol = url.getProtocol();
    if ("file".equals(protocol)) {
      List<String> sources = getTestSourceNamesFromDirectory(url);
      return loadFromClasspath(clazz, sources.toArray(new String[sources.size()]));
    } else if ("bundleresource".equals(protocol)) {
      Bundle bundle = FrameworkUtil.getBundle(clazz);
      if (bundle != null) {
        String packagePath = clazz.getPackage().getName().replace('.', '/');
        URL[] entries = FileLocator.findEntries(bundle, new Path(packagePath));
        if (entries.length == 0) {
          entries = FileLocator.findEntries(bundle, new Path("bin/" + packagePath));
        }
        if (entries.length != 0) {
          try {
            List<String> sources = getTestSourceNamesFromDirectory(FileLocator.toFileURL(entries[0]));
            return loadFromClasspath(clazz, sources.toArray(new String[sources.size()]));
          } catch (IOException e) {
            throw new WrappedException(e);
          }
        }
      }
    }
    throw new IllegalArgumentException("Cannot load test sources from URL " + url);
  }

  /**
   * Retrieves all Xtext test sources found in a given directory.
   * 
   * @param url
   *          URL to directory containing test sources
   * @return list of all test sources in that directory
   */
  private List<String> getTestSourceNamesFromDirectory(final URL url) {
    try {
      File dir = new File(url.toURI());
      if (dir.isDirectory()) {
        List<String> result = Lists.newArrayList();
        for (File f : dir.listFiles()) {
          if (!f.isFile()) {
            continue;
          }
          String name = f.getName();
          if (testSourceFactory.isFactoryFor(name)) {
            result.add(name);
          }
        }
        return result;
      } else {
        throw new IllegalArgumentException("URL is not a directory: " + url); //$NON-NLS-1$
      }
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }
}

