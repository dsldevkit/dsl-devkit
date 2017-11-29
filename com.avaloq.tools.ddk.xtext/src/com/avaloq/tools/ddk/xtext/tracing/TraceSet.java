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
package com.avaloq.tools.ddk.xtext.tracing;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import com.avaloq.tools.ddk.xtext.tracing.TraceEvent.Trigger;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.Singleton;


/**
 * This implementation is based on Guava's {@link EventBus} mechanism and the TraceSet is in fact really only a very thin wrapper around that.
 *
 * @see TraceEvent
 * @see EventBus
 */
@Singleton
public class TraceSet implements ITraceSet {

  private final EventBus syncBus = new EventBus();
  private final EventBus asyncBus = new AsyncEventBus(Executors.newSingleThreadExecutor());

  private final ConcurrentMap<Class<?>, Constructor<?>> constructors = Maps.newConcurrentMap();

  private TraceConfiguration configuration = TraceConfiguration.disableAll();

  private final ThreadLocal<Map<Object, Object>> intermediateData = ThreadLocal.withInitial(() -> Maps.newHashMap());

  @Override
  public void configure(final TraceConfiguration newConfiguration) {
    Preconditions.checkArgument(newConfiguration != null);
    this.configuration = newConfiguration;
  }

  @Override
  public <T extends TraceEvent> boolean isEnabled(final Class<T> eventClass) {
    return configuration.isEnabled(eventClass);
  }

  /**
   * Posts a new {@link TraceEvent.Trigger#STARTED STARTED} event of the given type and the given data. The actual event being posted will be created using
   * {@link #newEvent(Class, Trigger, Object...)}. Note that the tracing for the given event must be {@link #isEnabled(Class) enabled} for the event to be
   * dispatched to registered handlers.
   *
   * @param <T>
   *          event type
   * @param eventClass
   *          type of event to post
   * @param data
   *          additional event data
   */
  @Override
  public <T extends TraceEvent> void started(final Class<T> eventClass, final Object... data) {
    if (isEnabled(eventClass)) {
      T event = newEvent(eventClass, Trigger.STARTED, data);
      doPost(event);
    }
  }

  /**
   * Posts a new {@link TraceEvent.Trigger#TRACE TRACE} event of the given type and the given data. The actual event being posted will be created using
   * {@link #newEvent(Class, Trigger, Object...)}. Note that the tracing for the given event must be {@link #isEnabled(Class) enabled} for the event to be
   * dispatched to registered handlers.
   *
   * @param <T>
   *          event type
   * @param eventClass
   *          type of event to post
   * @param data
   *          additional event data
   */
  @Override
  public <T extends TraceEvent> void trace(final Class<T> eventClass, final Object... data) {
    if (isEnabled(eventClass)) {
      T event = newEvent(eventClass, Trigger.TRACE, data);
      doPost(event);
    }
  }

  /**
   * Posts a new {@link TraceEvent.Trigger#ENDED ENDED} event of the given type and the given data. The actual event being posted will be created using
   * {@link #newEvent(Class, Trigger, Object...)}. Note that the tracing for the given event must be {@link #isEnabled(Class) enabled} for the event to be
   * dispatched to registered handlers.
   *
   * @param <T>
   *          event type
   * @param eventClass
   *          type of event to post
   * @param data
   *          additional event data
   */
  @Override
  public <T extends TraceEvent> void ended(final Class<T> eventClass, final Object... data) {
    if (isEnabled(eventClass)) {
      T event = newEvent(eventClass, Trigger.ENDED, data);
      doPost(event);
    }
  }

  /**
   * Posts the given event to all registered handlers. Note that the tracing for the given event must be {@link #isEnabled(Class) enabled} for the event to be
   * dispatched to registered handlers.
   *
   * @param <T>
   *          event type
   * @param event
   *          event to post
   */
  @Override
  public <T extends TraceEvent> void post(final T event) {
    if (isEnabled(event.getClass())) {
      doPost(event);
    }
  }

  /**
   * Implementation method called by {@link #started(Class, Object...)}, {@link #trace(Class, Object...)}, {@link #ended(Class, Object...)}, and
   * {@link #post(TraceEvent)}.
   *
   * @param <T>
   *          event type
   * @param event
   *          event to post, must not be {@code null}
   */
  protected <T extends TraceEvent> void doPost(final T event) {
    syncBus.post(event);
    asyncBus.post(event);
  }

  /**
   * Helper method used by {@link #started(Class, Object...)}, {@link #trace(Class, Object...)}, and {@link #ended(Class, Object...)} to create an event
   * instance of the given event type and with the given trigger and data. The event class must declare a constructor with two parameters in the given order:
   * {@link Trigger}, {@link Object...}.
   *
   * @param <T>
   *          event type
   * @param eventClass
   *          type of event to create
   * @param trigger
   *          trigger of event
   * @param data
   *          event data
   * @return new event instance
   */
  @SuppressWarnings("unchecked")
  protected <T extends TraceEvent> T newEvent(final Class<T> eventClass, final Trigger trigger, final Object... data) {
    try {
      Constructor<T> constructor = (Constructor<T>) constructors.get(eventClass);
      if (constructor == null) {
        constructor = eventClass.getDeclaredConstructor(Trigger.class, Object[].class);
        constructors.putIfAbsent(eventClass, constructor);
      }
      return constructor.newInstance(trigger, data);
    } catch (SecurityException e) {
      throw new IllegalStateException(e);
    } catch (NoSuchMethodException e) {
      throw new IllegalStateException(e);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e);
    } catch (InstantiationException e) {
      throw new IllegalStateException(e);
    } catch (IllegalAccessException e) {
      throw new IllegalStateException(e);
    } catch (InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * {@inheritDoc}
   * This implementation is based on EventBus. In order for any other registered handlers to be notified in a timely manner,
   * the here registered handler should not do any time handling processing in its {@link com.google.common.eventbus.Subscribe} annotated handler methods.
   * If required, this should be done asynchronously.
   *
   * @see EventBus#register(Object)
   */
  @Override
  public void registerAsync(final Object handler) {
    asyncBus.register(handler);
  }

  /**
   * {@inheritDoc}
   * It is very important that the handlers don't perform any expensive processing in their
   * {@link com.google.common.eventbus.Subscribe} annotated handler methods.
   *
   * @see EventBus#register(Object)
   */
  @Override
  public void registerSync(final Object handler) {
    syncBus.register(handler);
  }

  /**
   * {@inheritDoc}
   *
   * @see EventBus#unregister(Object)
   */
  @Override
  public void unregisterAsync(final Object handler) {
    asyncBus.unregister(handler);
  }

  /**
   * {@inheritDoc}
   *
   * @see EventBus#unregister(Object)
   */
  @Override
  public void unregisterSync(final Object handler) {
    syncBus.unregister(handler);
  }

  @Override
  public <T> T getIntermediateData(final Object key, final Supplier<T> initialValue) {
    @SuppressWarnings("unchecked")
    Map<Object, T> intermediateDataMap = (Map<Object, T>) intermediateData.get();
    T result = intermediateDataMap.get(key);
    if (result == null) {
      result = initialValue.get();
      intermediateDataMap.put(key, result);
    }
    return result;
  }

  @Override
  public <T> T clearIntermediateData(final Object key) {
    @SuppressWarnings("unchecked")
    Map<Object, T> intermediateDataMap = (Map<Object, T>) intermediateData.get();
    return intermediateDataMap.remove(key);
  }

}
