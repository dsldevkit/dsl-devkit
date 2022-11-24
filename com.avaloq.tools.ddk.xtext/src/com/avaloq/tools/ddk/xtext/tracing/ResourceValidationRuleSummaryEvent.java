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
package com.avaloq.tools.ddk.xtext.tracing;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.common.collect.Maps;


/**
 * An event summarizing the total execution time of a specific validation rule for a resource during the build's second build phase. This event will have a
 * {@link ResourceValidationEvent} as its parent. The validation rule and execution time will be attached as event data.
 */
public class ResourceValidationRuleSummaryEvent extends ResourceEvent {

  /**
   * Class used to collect information about executed validation rules and eventually post corresponding {@link ResourceValidationRuleSummaryEvent} events.
   */
  public static class Collector {

    private final Map<String, long[]> ruleExecutionData = Maps.newHashMap();

    /** Currently executing rule, between subsequent calls to {@link #ruleStarted(String, EObject)} and {@link #ruleEnded(String, EObject)}. */
    private String currentRule;
    /**
     * Data logged for {@link #currentRule}, where the array's first element (index 0) represents the total execution time of all executions of
     * {@link #currentRule} and the array's second element (index 1) represents the total number of executions of {@link #currentRule}.
     */
    private long[] currentData;

    /**
     * Adds a new collector to the given resource set. Any existing collector will be replaced.
     *
     * @param resourceSet
     *          resource set to which collector should be added
     * @return the new collector
     */
    public static Collector addToLoadOptions(final ResourceSet resourceSet) {
      Collector dataCollector = new Collector();
      resourceSet.getLoadOptions().put(ResourceValidationRuleSummaryEvent.Collector.class.getName(), dataCollector);
      return dataCollector;
    }

    /**
     * Retrieves the collector from the load options of the given resource set.
     *
     * @param resourceSet
     *          resource set to get collector from
     * @return collector from load options or {@code null} if none
     */
    public static Collector extractFromLoadOptions(final ResourceSet resourceSet) {
      return (Collector) resourceSet.getLoadOptions().get(ResourceValidationRuleSummaryEvent.Collector.class.getName());
    }

    /**
     * Records that the given validation rule started executing for the given context object.
     *
     * @param rule
     *          validation rule to be executed
     * @param obj
     *          object against which the validation will be executed
     */
    public void ruleStarted(final String rule, final EObject obj) {
      if (currentRule == null || !currentRule.equals(rule)) {
        currentRule = rule;
        currentData = ruleExecutionData.get(rule);
        if (currentData == null) {
          currentData = new long[2];
          ruleExecutionData.put(rule, currentData);
        }
      }
      // subtract start time now and add end time in ruleEnded() to get elapsed time added to currentData[0]
      currentData[0] -= System.nanoTime();
    }

    /**
     * Records that the given validation rule (previously registered with {@link #ruleStarted(String, EObject)}) finished executing.
     *
     * @param rule
     *          executed validation rule
     * @param obj
     *          object against which the validation was executed
     */
    public void ruleEnded(final String rule, final EObject obj) {
      // see comment in ruleStarted() for explanation
      currentData[0] += System.nanoTime();
      currentData[1] += 1;
    }

    /**
     * Posts {@link ResourceValidationRuleSummaryEvent} events for all collected rule executions.
     *
     * @param uri
     *          URI of validated resource
     * @param traceSet
     *          trace set to post events to
     */
    public void postEvents(final URI uri, final ITraceSet traceSet) {
      for (Map.Entry<String, long[]> entry : ruleExecutionData.entrySet()) {
        traceSet.post(new ResourceValidationRuleSummaryEvent(Trigger.TRACE, new Object[] {uri, entry.getKey(), entry.getValue()[0], entry.getValue()[1]}));
      }
    }

  }

  /**
   * Creates a new instance of {@link ResourceValidationRuleSummaryEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data, where the first data object is expected to be the resource's {@link org.eclipse.emf.common.util.URI} this event pertains to, the
   *          second object the validation rule's name, and the third object the total execution time in nanoseconds
   */
  public ResourceValidationRuleSummaryEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
  }

}
