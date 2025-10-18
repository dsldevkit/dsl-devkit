/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.util.function.Function;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.avaloq.tools.ddk.xtext.linking.LazyLinkingResource2;
import com.google.inject.Key;
import com.google.inject.name.Names;


/**
 * Specifies how a resource should be loaded from binary storage (if at all).
 */
@SuppressWarnings("nls")
public interface ResourceLoadMode {

  /**
   * Key to use for named binding for {@link #BINDING_KEY default load mode}.
   */
  String DEFAULT_LOAD_MODE = "DEFAULT_LOAD_MODE"; //$NON-NLS-1$

  Key<ResourceLoadMode> BINDING_KEY = Key.get(ResourceLoadMode.class, Names.named(DEFAULT_LOAD_MODE));

  /**
   * Constituent to load from binary storage.
   * <p>
   * Note: It is important that the order of these literals reflect the order of the entries in the zip archives.
   */
  enum Constituent {
    /**
     * This represents the resource as a whole. Use {@link Instruction#SKIP} to parse the resource from source text.
     */
    RESOURCE,
    /**
     * This represents the {@link Resource#getContents() resource contents}.
     */
    CONTENT,
    /**
     * This represents the {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations model associations} between AST and inferred model
     * elements in the resource contents.
     */
    ASSOCIATIONS,
    /**
     * This represents the source text behind the node model.
     */
    SOURCE,
    /**
     * This represents the {@link org.eclipse.xtext.parser.IParseResult#getRootNode() node model}.
     */
    NODE_MODEL
  }

  /**
   * Instruction on how to load a particular {@link Constituent}.
   */
  enum Instruction {
    /**
     * Load constituent. When
     * {@link DirectLinkingResourceStorageLoadable#loadIntoResource(org.eclipse.xtext.resource.persistence.StorageAwareResource, ResourceLoadMode)} is invoked
     * for the first time for a particular resource this means that the constituent in question will be loaded up-front. If in the first invocation the
     * instruction {@link #PROXY} was specified, a subsequent invocation can use {@link #LOAD} to demand-load that constituent.
     */
    LOAD,
    /**
     * Install a proxy for the constituent. This requires demand-loading logic which will automatically replace the proxy using a {@link #LOAD} instruction.
     */
    PROXY,
    /**
     * Skip constituent in binary storage. As a result the resource may be "incomplete".
     */
    SKIP
  }

  /**
   * Returns the load mode to be used to load the given resource:
   * <ol>
   * <li>If the resource is not contained in any resource set this method will return {@link #PARSE}.</li>
   * <li>If the resource set's load options specify a load mode, that is returned.</li>
   * <li>If the resource is an instance of {@link LazyLinkingResource2} it will return {@link LazyLinkingResource2#getDefaultLoadMode()}.</li>
   * <li>Otherwise {@link #FULL} is returned.</li>
   * </ol>
   *
   * @param resource
   *          resource to be loaded, must not be {@code null}
   * @return load mode, never {@code null}
   */
  static ResourceLoadMode get(final Resource resource) {
    ResourceSet resourceSet = resource.getResourceSet();
    if (resourceSet == null) {
      return PARSE;
    }
    ResourceLoadMode fromLoadOptions = (ResourceLoadMode) resourceSet.getLoadOptions().get(ResourceLoadMode.class);
    if (fromLoadOptions != null) {
      return fromLoadOptions;
    }
    if (resource instanceof LazyLinkingResource2) {
      ResourceLoadMode defaultMode = ((LazyLinkingResource2) resource).getDefaultLoadMode();
      if (defaultMode != null) {
        return defaultMode;
      }
    }
    return FULL;
  }

  /**
   * Load mode which returns the {@link Instruction#SKIP} instruction for all constituents. As a result the resource must be parsed from source text.
   */
  ResourceLoadMode PARSE = of(c -> Instruction.SKIP, "PARSE");

  /**
   * Load mode for loading a resource fully from binary storage (i.e. using {@link Instruction#LOAD} for all constituents).
   */
  ResourceLoadMode FULL = of(c -> Instruction.LOAD, "FULL");

  /**
   * Load mode for loading a resource fully from binary storage except for the {@link Constituent#NODE_MODEL} and {@link Constituent#SOURCE}, which will be
   * {@link Instruction#PROXY proxied}.
   */
  ResourceLoadMode PROXIED_NODE_MODEL = of(c -> c == Constituent.NODE_MODEL || c == Constituent.SOURCE ? Instruction.PROXY
      : Instruction.LOAD, "PROXIED_NODE_MODEL");

  /**
   * Load mode for loading a resource fully from binary storage except for the {@link Constituent#ASSOCIATIONS}, {@link Constituent#NODE_MODEL}, and
   * {@link Constituent#SOURCE}, which will all be {@link Instruction#PROXY proxied}.
   */
  ResourceLoadMode PROXIED_NODE_MODEL_AND_ASSOCIATIONS = of(c -> c == Constituent.NODE_MODEL || c == Constituent.SOURCE || c == Constituent.ASSOCIATIONS
      ? Instruction.PROXY
      : Instruction.LOAD, "PROXIED_NODE_MODEL_AND_ASSOCIATIONS");

  /**
   * Load mode for loading a resource from binary storage without any node model.
   */
  ResourceLoadMode NO_NODE_MODEL = of(c -> c == Constituent.NODE_MODEL || c == Constituent.SOURCE ? Instruction.SKIP : Instruction.LOAD, "NO_NODE_MODEL");

  /**
   * Load mode for demand-loading a resource's node model from binary storage. This is intended to be used in conjunction with {@link #PROXIED_NODE_MODEL} and
   * {@link #PROXIED_NODE_MODEL_AND_ASSOCIATIONS}.
   *
   * @return load mode for demand-loading a resource's node model, never {@code null}
   */
  ResourceLoadMode ONLY_NODE_MODEL = of(c -> c == Constituent.RESOURCE || c == Constituent.NODE_MODEL || c == Constituent.SOURCE ? Instruction.LOAD
      : Instruction.SKIP, "ONLY_NODE_MODEL");

  /**
   * Load mode for demand-loading a resource's associations from binary storage. This is intended to be used in conjunction with
   * {@link #PROXIED_NODE_MODEL_AND_ASSOCIATIONS}.
   *
   * @return load mode for demand-loading a resource's associations, never {@code null}
   */
  ResourceLoadMode ONLY_ASSOCIATIONS = of(c -> c == Constituent.RESOURCE || c == Constituent.ASSOCIATIONS ? Instruction.LOAD
      : Instruction.SKIP, "ONLY_ASSOCIATIONS");

  /**
   * Helper method to create a load mode using a function. The additional name will be returned by the load mode's {@link #toString()} method to assist while
   * debugging.
   *
   * @param function
   *          mapping function, must not be {@code null}
   * @param name
   *          name of load mode, can be {@code null}
   * @return load mode implemented by given function, never {@code null}
   */
  @SuppressWarnings("PMD.ShortMethodName")
  static ResourceLoadMode of(final Function<Constituent, Instruction> function, final String name) {
    return new ResourceLoadMode() {
      @Override
      public Instruction instruction(final Constituent constituent) {
        return function.apply(constituent);
      }

      @Override
      public String toString() {
        return name;
      }
    };
  }

  /**
   * Returns the load instruction to apply for the given constituent.
   *
   * @param constituent
   *          constituent, must not be {@code null}
   * @return load instruction, never {@code null}
   */
  Instruction instruction(Constituent constituent);
}

/* Copyright (c) Avaloq Group AG */