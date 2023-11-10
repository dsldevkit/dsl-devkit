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
package com.avaloq.tools.ddk.xtext.builder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.impl.Messages;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.avaloq.tools.ddk.xtext.resource.ResourceServiceProviderLocator;
import com.avaloq.tools.ddk.xtext.util.BuilderParticipantSettings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.ConfigurationException;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;


/**
 * A custom {@link org.eclipse.xtext.builder.impl.RegistryBuilderParticipant}, able to support injection for
 * {@link ILanguageSpecificBuilderParticipant}s.
 * Unfortunately, the base class was not well extendible, so most of it had to be copied.
 */
@Singleton
public class RegistryBuilderParticipant extends org.eclipse.xtext.builder.impl.RegistryBuilderParticipant {
  private static final int MONITOR_PARTICIPANTS_PER_LANGUAGE = 1000;
  private static final String PARTICIPANT = "participant"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_ID = PARTICIPANT;
  private static final String ATT_CLASS = "class"; //$NON-NLS-1$
  private static final Logger LOG = LogManager.getLogger(RegistryBuilderParticipant.class);

  @Inject
  private IExtensionRegistry extensionRegistry;

  @Inject
  private ResourceServiceProviderLocator resourceServiceProviderLocator;

  private ImmutableList<IXtextBuilderParticipant> immutableCommonParticipants;

  private final Map<String, IXtextBuilderParticipant> classToParticipant = Maps.newHashMap();
  private final Map<String, Set<ILanguageSpecificBuilderParticipant>> serviceProviderToParticipants = Maps.newHashMap();
  private final Set<IXtextBuilderParticipant> initializedParticipants = Sets.newHashSet();

  
  @Override
  public void build(final IBuildContext buildContext, final IProgressMonitor monitor) throws CoreException {
    SubMonitor progress = SubMonitor.convert(monitor, 2);
    buildLanguageSpecificParticipants(buildContext, progress.newChild(1));
    buildOtherParticipants(buildContext, progress.newChild(1));
  }

  /**
   * For each {@link IResourceDescription.Delta} searches and calls the responsible {@link ILanguageSpecificBuilderParticipant}s.
   *
   * @param buildContext
   *          the {@link IBuildContext}, must not be {@code null}
   * @param monitor
   *          the {@link IProgressMonitor}, must not be {@code null}
   */
  protected void buildLanguageSpecificParticipants(final IBuildContext buildContext, final IProgressMonitor monitor) {
    initParticipants();
    SubMonitor progress = SubMonitor.convert(monitor, buildContext.getDeltas().size() * MONITOR_PARTICIPANTS_PER_LANGUAGE);
    Map<String, BuildContext> languageIdToBuildContext = Maps.newHashMap();
    for (IResourceDescription.Delta delta : buildContext.getDeltas()) {
      IResourceServiceProvider resourceServiceProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(delta.getUri());
      if (resourceServiceProvider == null) {
        progress.worked(MONITOR_PARTICIPANTS_PER_LANGUAGE);
        continue;
      }
      IGrammarAccess grammarAccess;
      try {
        grammarAccess = resourceServiceProvider.get(IGrammarAccess.class);
      } catch (ConfigurationException e) {
        progress.worked(MONITOR_PARTICIPANTS_PER_LANGUAGE);
        continue;
      }
      if (grammarAccess == null) {
        progress.worked(MONITOR_PARTICIPANTS_PER_LANGUAGE);
        continue;
      }
      String languageId = grammarAccess.getGrammar().getName();
      BuildContext entryBuildContext = languageIdToBuildContext.get(languageId);
      if (entryBuildContext == null) {
        entryBuildContext = new BuildContext(buildContext.getBuiltProject(), buildContext.getResourceSet(), buildContext.getBuildType());
        languageIdToBuildContext.put(languageId, entryBuildContext);
      }
      entryBuildContext.addDelta(delta);
    }
    builLanguageSpecificContext(buildContext, progress, languageIdToBuildContext);
  }

  /**
   * For the given {@link IBuildContext} calls the responsible {@link ILanguageSpecificBuilderParticipant}s.
   *
   * @param buildContext
   *          the {@link IBuildContext}, must not be {@code null}
   * @param progress
   *          the {@link SubMonitor}, must not be {@code null}
   * @param languageIdToBuildContext
   *          the map of which build context should be used with responsible {@link ILanguageSpecificBuilderParticipant}, must not be {@code null}
   */
  private void builLanguageSpecificContext(final IBuildContext buildContext, final SubMonitor progress, final Map<String, BuildContext> languageIdToBuildContext) {
    for (final Entry<String, BuildContext> entry : languageIdToBuildContext.entrySet()) {
      final String languageId = entry.getKey();
      final BuildContext entryBuildContext = entry.getValue();
      final Set<ILanguageSpecificBuilderParticipant> languageSpecificBuilderParticipants = serviceProviderToParticipants.get(languageId);
      if (languageSpecificBuilderParticipants != null && !languageSpecificBuilderParticipants.isEmpty()) {
        int workUnits = entryBuildContext.getDeltas().size() * MONITOR_PARTICIPANTS_PER_LANGUAGE / languageSpecificBuilderParticipants.size();
        for (final ILanguageSpecificBuilderParticipant languageSpecificBuilderParticipant : languageSpecificBuilderParticipants) {
          try {
            if (initializeParticipant(languageSpecificBuilderParticipant)) {
              languageSpecificBuilderParticipant.build(entryBuildContext, progress.newChild(workUnits));
              if (entryBuildContext.isRebuildRequired()) {
                buildContext.needRebuild(buildContext.getBuiltProject());
              }
            }
            // CHECKSTYLE:CHECK-OFF IllegalCatchCheck we need to recover from any exception and continue the build
          } catch (Throwable throwable) {
            // CHECKSTYLE:CHECK-ON IllegalCatchCheck
            LOG.error("Error occurred during build of an ILanguageSpecificBuilderParticipant: " //$NON-NLS-1$
                + languageSpecificBuilderParticipant.getClass().getName(), throwable);
          }
        }
      } else {
        progress.worked(entryBuildContext.getDeltas().size() * MONITOR_PARTICIPANTS_PER_LANGUAGE);
      }
    }
  }

  /**
   * Builds all other registered (non-language specific) {@link IXtextBuilderParticipant}s.
   *
   * @param buildContext
   *          the {@link IBuildContext}, must not be {@code null}
   * @param monitor
   *          the {@link IProgressMonitor}, must not be {@code null}
   * @throws CoreException
   *           caused by an {@link IXtextBuilderParticipant}
   */
  protected void buildOtherParticipants(final IBuildContext buildContext, final IProgressMonitor monitor) throws CoreException {
    ImmutableList<IXtextBuilderParticipant> otherBuilderParticipants = getParticipants();
    if (otherBuilderParticipants.isEmpty()) {
      return;
    }
    SubMonitor progress = SubMonitor.convert(monitor, otherBuilderParticipants.size());
    progress.subTask(Messages.RegistryBuilderParticipant_InvokingBuildParticipants);
    for (final IXtextBuilderParticipant participant : otherBuilderParticipants) {
      if (progress.isCanceled()) {
        throw new OperationCanceledException();
      }
      try {
        if (initializeParticipant(participant)) {
          participant.build(buildContext, progress.newChild(1));
        }
        // CHECKSTYLE:CHECK-OFF IllegalCatchCheck we need to recover from any exception and continue the build
      } catch (Throwable throwable) {
        // CHECKSTYLE:CHECK-ON IllegalCatchCheck
        LOG.error("Error occurred during build of builder participant: " //$NON-NLS-1$
            + participant.getClass().getName(), throwable);
      }
    }
  }

  /**
   * Initializes the given {@link IXtextBuilderParticipant}.
   *
   * @param participant
   *          the {@link IXtextBuilderParticipant} to initialize, must not be {@code null}
   * @return whether the builder participant was initialized successfully
   */
  private boolean initializeParticipant(final IXtextBuilderParticipant participant) {
    String languageId = null;
    if (participant instanceof IGeneratorModuleProvider) {
      languageId = ((IGeneratorModuleProvider) participant).getGeneratorModuleId();
    } else if (participant instanceof ILanguageSpecificBuilderParticipant) {
      languageId = ((ILanguageSpecificBuilderParticipant) participant).getLanguageId();
    }
    if (languageId != null && !BuilderParticipantSettings.isBuilderParticipantEnabled(languageId)) {
      return false;
    }
    if (!initializedParticipants.contains(participant)) {
      if (languageId != null) {
        final IResourceServiceProvider resourceServiceProvider = resourceServiceProviderLocator.getResourceServiceProviderById(languageId);
        if (resourceServiceProvider != null) {
          // inject members of the participant
          final Injector injector = resourceServiceProvider.get(Injector.class);
          injector.injectMembers(participant);
        } else {
          LOG.error(NLS.bind("No ResourceServiceProvider found for builder participant ''{0}'' and language id ''{1}''", participant.getClass().getName(), languageId)); //$NON-NLS-1$
          return false;
        }
      }
      initializedParticipants.add(participant);
    }
    return true;
  }

  
  @Override
  public ImmutableList<IXtextBuilderParticipant> getParticipants() {
    return initParticipants();
  }

  /**
   * This method should be used only in a system test.
   * In the productive code the list of participants should be initialized only once and never be reseted.
   * If one resets the list, nothing bad will happen - the list of builder participants will be loaded during the next build.
   */
  public synchronized void resetParticipants() {
    immutableCommonParticipants = null;
    classToParticipant.clear();
    serviceProviderToParticipants.clear();
    initializedParticipants.clear();
  }

  
  @Override
  protected synchronized ImmutableList<IXtextBuilderParticipant> initParticipants() {
    if (immutableCommonParticipants == null) {
      String pluginID = "org.eclipse.xtext.builder"; //$NON-NLS-1$ // Activator.getDefault().getBundle().getSymbolicName();
      String extensionPointID = EXTENSION_POINT_ID;
      ConditionalBuilderParticipantReader reader = new ConditionalBuilderParticipantReader(extensionRegistry, pluginID, extensionPointID);
      reader.readRegistry();
      immutableCommonParticipants = reader.getCommonParticipants();
    }
    return immutableCommonParticipants;
  }

  /**
   * Maintains the set of extensions.
   */
  public class ConditionalBuilderParticipantReader extends BuilderParticipantReader {

    private final List<IXtextBuilderParticipant> commonParticipants = Lists.newArrayList();

    public ConditionalBuilderParticipantReader(final IExtensionRegistry pluginRegistry, final String pluginID, final String extensionPointID) {
      super(pluginRegistry, pluginID, extensionPointID);
    }

    protected ImmutableList<IXtextBuilderParticipant> getCommonParticipants() {
      return ImmutableList.copyOf(commonParticipants);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean readElement(final IConfigurationElement element, final boolean add) {
      if (!PARTICIPANT.equals(element.getName())) {
        return false;
      }
      String className = element.getAttribute(ATT_CLASS);
      if (className == null) {
        logMissingAttribute(element, ATT_CLASS);
        return false;
      }
      if (add) {
        try {
          Object participant = element.createExecutableExtension(ATT_CLASS);
          if (participant instanceof IXtextBuilderParticipant) {
            IXtextBuilderParticipant xtextBuilderParticipant = (IXtextBuilderParticipant) participant;
            if (classToParticipant.containsKey(className)) {
              LOG.warn("The builder participant '" + className + "' was registered twice."); //$NON-NLS-1$ //$NON-NLS-2$
            }
            classToParticipant.put(className, xtextBuilderParticipant);
            if (participant instanceof ILanguageSpecificBuilderParticipant) {
              ILanguageSpecificBuilderParticipant languageSpecificBuilderParticipant = (ILanguageSpecificBuilderParticipant) participant;
              String languageId = languageSpecificBuilderParticipant.getLanguageId();
              registerLanguageSpecificBuilderParticipant(languageId, languageSpecificBuilderParticipant);
            } else {
              commonParticipants.add(xtextBuilderParticipant);
            }
            initializedParticipants.remove(participant);
          } else {
            logError(element, className + " did not yield an instance of IXtextBuilderParticipant but " + participant.getClass().getName()); //$NON-NLS-1$
          }
          return true;
        } catch (CoreException e) {
          logError(element, e.getMessage());
        }
      } else {
        IXtextBuilderParticipant xtextBuilderParticipant = classToParticipant.remove(className);
        if (xtextBuilderParticipant instanceof ILanguageSpecificBuilderParticipant) {
          ILanguageSpecificBuilderParticipant languageSpecificBuilderParticipant = ((ILanguageSpecificBuilderParticipant) xtextBuilderParticipant);
          String languageId = languageSpecificBuilderParticipant.getLanguageId();
          unregisterLanguageSpecificBuilderParticipant(languageId, languageSpecificBuilderParticipant);
        } else if (xtextBuilderParticipant != null) {
          commonParticipants.remove(xtextBuilderParticipant);
        }
        return true;
      }
      return false;
    }

    /**
     * Registers a {@link ILanguageSpecificBuilderParticipant} for the given {@link IResourceServiceProvider}.
     *
     * @param languageId
     *          the language id for which to register the {@link ILanguageSpecificBuilderParticipant}
     * @param languageSpecificBuilderParticipant
     *          the {@link ILanguageSpecificBuilderParticipant} to register
     * @return {@code true} if the {@link ILanguageSpecificBuilderParticipant} was not registered yet, {@code false} otherwise
     */
    private boolean registerLanguageSpecificBuilderParticipant(final String languageId, final ILanguageSpecificBuilderParticipant languageSpecificBuilderParticipant) {
      Set<ILanguageSpecificBuilderParticipant> languageSpecificBuilderParticipants = serviceProviderToParticipants.get(languageId);
      if (languageSpecificBuilderParticipants == null) {
        languageSpecificBuilderParticipants = Sets.newHashSet();
        serviceProviderToParticipants.put(languageId, languageSpecificBuilderParticipants);
      }
      return languageSpecificBuilderParticipants.add(languageSpecificBuilderParticipant);
    }

    /**
     * Unregisters a {@link ILanguageSpecificBuilderParticipant} for the given {@link IResourceServiceProvider}.
     *
     * @param languageId
     *          the language id for which to unregister the {@link ILanguageSpecificBuilderParticipant}.
     * @param languageSpecificBuilderParticipant
     *          the {@link ILanguageSpecificBuilderParticipant} to unregister
     * @return {@code true} if the {@link ILanguageSpecificBuilderParticipant} was registered before, {@code false} otherwise
     */
    private boolean unregisterLanguageSpecificBuilderParticipant(final String languageId, final ILanguageSpecificBuilderParticipant languageSpecificBuilderParticipant) {
      Set<ILanguageSpecificBuilderParticipant> languageSpecificBuilderParticipants = serviceProviderToParticipants.get(languageId);
      if (languageSpecificBuilderParticipants != null) {
        return languageSpecificBuilderParticipants.remove(languageSpecificBuilderParticipant);
      }
      return false;
    }
  }
}
