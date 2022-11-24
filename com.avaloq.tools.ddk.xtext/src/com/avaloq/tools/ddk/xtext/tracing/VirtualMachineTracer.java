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

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.management.ListenerNotFoundException;
import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.InvalidKeyException;

import com.google.common.collect.Maps;
import com.google.inject.Inject;


/**
 * {@link ITraceSet Tracing} for the Java virtual machine using JMX extensions.
 * <p>
 * Currently only support for the Oracle HotSpot VM, where events of types {@link FullGarbageCollectionEvent} and {@link MinorGarbageCollectionEvent}
 * representing full or minor garbage collections will be posted. For other VMs nothing will be traced.
 */
public class VirtualMachineTracer {

  private final ITraceSet traceSet;
  private final Map<NotificationEmitter, NotificationListener> gcListenerMap = Maps.newHashMap();

  @Inject
  public VirtualMachineTracer(final ITraceSet traceSet) {
    this.traceSet = traceSet;
  }

  @SuppressWarnings("nls")
  public static boolean isHotSpotVM() {
    return System.getProperty("java.vm.vendor").equals("Oracle Corporation") && System.getProperty("java.vm.name").indexOf("HotSpot") != -1;
  }

  /**
   * Determines the approximate VM start time using {@link java.lang.management.RuntimeMXBean#getStartTime()} in the base of {@link System#nanoTime()}. This is
   * necessary because {@link ITraceSet} uses {@link System#nanoTime()}-based timestamps.
   *
   * @return VM start time in the base of {@link System#nanoTime()}
   */
  private static long getApproximateNanoStartTime() {
    long nanoTime = System.nanoTime();
    long currentTimeMillis = System.currentTimeMillis();
    long vmStartTimeMillis = ManagementFactory.getRuntimeMXBean().getStartTime();
    return nanoTime - TimeUnit.NANOSECONDS.convert(currentTimeMillis - vmStartTimeMillis, TimeUnit.MILLISECONDS);
  }

  /**
   * Installs a listener that will publish all full GC events as {@link FullGarbageCollectionEvent} objects.
   */
  public void start() {
    // This code only works with Oracle HotSpot JVM as there is no standard API to retrieve information about GC events
    if (!isHotSpotVM()) {
      return;
    }

    long vmStartTime = getApproximateNanoStartTime();

    for (GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans()) {
      Class<? extends TraceEvent> eventType = gcBean.getName().equals("ConcurrentMarkSweep") || gcBean.getName().equals("MarkSweepCompact") //$NON-NLS-1$ //$NON-NLS-2$
          ? FullGarbageCollectionEvent.class
          : MinorGarbageCollectionEvent.class;
      NotificationEmitter emitter = (NotificationEmitter) gcBean;
      NotificationListener listener = new NotificationListener() {

        @Override
        public void handleNotification(final Notification notification, final Object handback) {
          try {
            // we only handle GARBAGE_COLLECTION_NOTIFICATION notifications here
            if (notification.getType().equals("com.sun.management.gc.notification")) { //$NON-NLS-1$
              CompositeData cd = (CompositeData) notification.getUserData();
              String gcAction = (String) cd.get("gcAction"); //$NON-NLS-1$
              String gcCause = (String) cd.get("gcCause"); //$NON-NLS-1$
              CompositeData gcInfo = (CompositeData) cd.get("gcInfo"); //$NON-NLS-1$
              long startTime = TimeUnit.NANOSECONDS.convert((Long) gcInfo.get("startTime"), TimeUnit.MILLISECONDS); //$NON-NLS-1$
              long duration = TimeUnit.NANOSECONDS.convert((Long) gcInfo.get("duration"), TimeUnit.MILLISECONDS); //$NON-NLS-1$
              if (duration > 0) {
                // "startTime" and "duration" are relative to VM start time
                traceSet.started(eventType, vmStartTime + startTime, gcAction, gcCause);
                traceSet.ended(eventType, vmStartTime + startTime + duration);
              }
            }
          } catch (InvalidKeyException e) {
            // ignore
          }
        }
      };
      emitter.addNotificationListener(listener, null, null);
      gcListenerMap.put(emitter, listener);
    }
  }

  /**
   * Uninstalls any attached GC listeners.
   *
   * @see #start()
   */
  public void stop() {
    for (Map.Entry<NotificationEmitter, NotificationListener> entry : gcListenerMap.entrySet()) {
      try {
        entry.getKey().removeNotificationListener(entry.getValue());
      } catch (ListenerNotFoundException e) {
        // should never happen, ignore
      }
    }
  }

}
