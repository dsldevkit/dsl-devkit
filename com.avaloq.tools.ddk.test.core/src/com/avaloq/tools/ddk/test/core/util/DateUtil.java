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
package com.avaloq.tools.ddk.test.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.eclipse.emf.common.util.WrappedException;


/**
 * Date Utility Class.
 */
public final class DateUtil {

  /**
   * Instantiates a new date util.
   */
  private DateUtil() {
    // Utility Class
  }

  /**
   * Date to string.
   *
   * @param date
   *          the date
   * @param dateFormat
   *          the date format (i.e. "dd.MM.yyyy")
   * @return the date as string
   */
  public static String dateToString(final Date date, final String dateFormat) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMANY);
    return sdf.format(date);
  }

  /**
   * Gets the current time.
   *
   * @param dateFormat
   *          the date format (i.e. "dd.MM.yyyy")
   * @return the current time
   */
  public static String getCurrentTime(final String dateFormat) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMANY);
    return sdf.format(cal.getTime());
  }

  /**
   * String to date.
   *
   * @param date
   *          the date
   * @param dateFormat
   *          the date format (i.e. "dd.MM.yyyy")
   * @return the string date as date
   */
  public static Date stringToDate(final String date, final String dateFormat) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMANY);
    Date d = null;
    try {
      d = sdf.parse(date);
    } catch (ParseException e) {
      throw new WrappedException("Error during parsing string to date", e);
    }
    return d;
  }
}
