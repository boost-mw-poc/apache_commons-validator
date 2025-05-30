/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.validator.routines;

import java.text.DateFormat;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <p><strong>Date Validation</strong> and Conversion routines ({@code java.util.Date}).</p>
 *
 * <p>This validator provides a number of methods for validating/converting
 *    a {@link String} date value to a {@code java.util.Date} using
 *    {@link DateFormat} to parse either:</p>
 *    <ul>
 *       <li>using the default format for the default {@link Locale}</li>
 *       <li>using a specified pattern with the default {@link Locale}</li>
 *       <li>using the default format for a specified {@link Locale}</li>
 *       <li>using a specified pattern with a specified {@link Locale}</li>
 *    </ul>
 *
 * <p>For each of the above mechanisms, conversion method (that is, the
 *    {@code validate} methods) implementations are provided which
 *    either use the default {@code TimeZone} or allow the
 *    {@code TimeZone} to be specified.</p>
 *
 * <p>Use one of the {@code isValid()} methods to just validate or
 *    one of the {@code validate()} methods to validate and receive a
 *    <em>converted</em> {@code Date} value.</p>
 *
 * <p>Implementations of the {@code validate()} method are provided
 *    to create {@code Date} objects for different <em>time zones</em>
 *    if the system default is not appropriate.</p>
 *
 * <p>Once a value has been successfully converted the following
 *    methods can be used to perform various date comparison checks:</p>
 *    <ul>
 *       <li>{@code compareDates()} compares the day, month and
 *           year of two dates, returning 0, -1 or +1 indicating
 *           whether the first date is equal, before or after the second.</li>
 *       <li>{@code compareWeeks()} compares the week and
 *           year of two dates, returning 0, -1 or +1 indicating
 *           whether the first week is equal, before or after the second.</li>
 *       <li>{@code compareMonths()} compares the month and
 *           year of two dates, returning 0, -1 or +1 indicating
 *           whether the first month is equal, before or after the second.</li>
 *       <li>{@code compareQuarters()} compares the quarter and
 *           year of two dates, returning 0, -1 or +1 indicating
 *           whether the first quarter is equal, before or after the second.</li>
 *       <li>{@code compareYears()} compares the
 *           year of two dates, returning 0, -1 or +1 indicating
 *           whether the first year is equal, before or after the second.</li>
 *    </ul>
 *
 * <p>So that the same mechanism used for parsing an <em>input</em> value
 *    for validation can be used to format <em>output</em>, corresponding
 *    {@code format()} methods are also provided. That is you can
 *    format either:</p>
 *    <ul>
 *       <li>using a specified pattern</li>
 *       <li>using the format for a specified {@link Locale}</li>
 *       <li>using the format for the <em>default</em> {@link Locale}</li>
 *    </ul>
 *
 * @since 1.3.0
 */
public class DateValidator extends AbstractCalendarValidator {

    private static final long serialVersionUID = -3966328400469953190L;

    private static final DateValidator VALIDATOR = new DateValidator();

    /**
     * Gets the singleton instance of this validator.
     * @return A singleton instance of the DateValidator.
     */
    public static DateValidator getInstance() {
        return VALIDATOR;
    }

    /**
     * Constructs a <em>strict</em> instance with <em>short</em>
     * date style.
     */
    public DateValidator() {
        this(true, DateFormat.SHORT);
    }

    /**
     * Constructs an instance with the specified <em>strict</em>
     * and <em>date style</em> parameters.
     *
     * @param strict {@code true} if strict
     *        {@code Format} parsing should be used.
     * @param dateStyle the date style to use for Locale validation.
     */
    public DateValidator(final boolean strict, final int dateStyle) {
        super(strict, dateStyle, -1);
    }

    /**
     * <p>Compare Dates (day, month and year - not time).</p>
     *
     * @param value The {@link Calendar} value to check.
     * @param compare The {@link Calendar} to compare the value to.
     * @param timeZone The Time Zone used to compare the dates, system default if null.
     * @return Zero if the dates are equal, -1 if first
     * date is less than the seconds and +1 if the first
     * date is greater than.
     */
    public int compareDates(final Date value, final Date compare, final TimeZone timeZone) {
        final Calendar calendarValue = getCalendar(value, timeZone);
        final Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.DATE);
    }

    /**
     * <p>Compare Months (month and year).</p>
     *
     * @param value The {@code Date} value to check.
     * @param compare The {@code Date} to compare the value to.
     * @param timeZone The Time Zone used to compare the dates, system default if null.
     * @return Zero if the months are equal, -1 if first
     * parameter's month is less than the seconds and +1 if the first
     * parameter's month is greater than.
     */
    public int compareMonths(final Date value, final Date compare, final TimeZone timeZone) {
        final Calendar calendarValue = getCalendar(value, timeZone);
        final Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.MONTH);
    }

    /**
     * <p>Compare Quarters (quarter and year).</p>
     *
     * @param value The {@code Date} value to check.
     * @param compare The {@code Date} to compare the value to.
     * @param timeZone The Time Zone used to compare the dates, system default if null.
     * @return Zero if the months are equal, -1 if first
     * parameter's quarter is less than the seconds and +1 if the first
     * parameter's quarter is greater than.
     */
    public int compareQuarters(final Date value, final Date compare, final TimeZone timeZone) {
        return compareQuarters(value, compare, timeZone, 1);
    }

    /**
     * <p>Compare Quarters (quarter and year).</p>
     *
     * @param value The {@code Date} value to check.
     * @param compare The {@code Date} to compare the value to.
     * @param timeZone The Time Zone used to compare the dates, system default if null.
     * @param monthOfFirstQuarter The  month that the first quarter starts.
     * @return Zero if the quarters are equal, -1 if first
     * parameter's quarter is less than the seconds and +1 if the first
     * parameter's quarter is greater than.
     */
    public int compareQuarters(final Date value, final Date compare, final TimeZone timeZone, final int monthOfFirstQuarter) {
        final Calendar calendarValue = getCalendar(value, timeZone);
        final Calendar calendarCompare = getCalendar(compare, timeZone);
        return super.compareQuarters(calendarValue, calendarCompare, monthOfFirstQuarter);
    }

    /**
     * <p>Compare Weeks (week and year).</p>
     *
     * @param value The {@code Date} value to check.
     * @param compare The {@code Date} to compare the value to.
     * @param timeZone The Time Zone used to compare the dates, system default if null.
     * @return Zero if the weeks are equal, -1 if first
     * parameter's week is less than the seconds and +1 if the first
     * parameter's week is greater than.
     */
    public int compareWeeks(final Date value, final Date compare, final TimeZone timeZone) {
        final Calendar calendarValue = getCalendar(value, timeZone);
        final Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.WEEK_OF_YEAR);
    }

    /**
     * <p>Compare Years.</p>
     *
     * @param value The {@code Date} value to check.
     * @param compare The {@code Date} to compare the value to.
     * @param timeZone The Time Zone used to compare the dates, system default if null.
     * @return Zero if the years are equal, -1 if first
     * parameter's year is less than the seconds and +1 if the first
     * parameter's year is greater than.
     */
    public int compareYears(final Date value, final Date compare, final TimeZone timeZone) {
        final Calendar calendarValue = getCalendar(value, timeZone);
        final Calendar calendarCompare = getCalendar(compare, timeZone);
        return compare(calendarValue, calendarCompare, Calendar.YEAR);
    }

    /**
     * <p>Convert a {@code Date} to a {@link Calendar}.</p>
     *
     * @param value The date value to be converted.
     * @return The converted {@link Calendar}.
     */
    private Calendar getCalendar(final Date value, final TimeZone timeZone) {
        final Calendar calendar;
        if (timeZone != null) {
            calendar = Calendar.getInstance(timeZone);
        } else {
            calendar = Calendar.getInstance();
        }
        calendar.setTime(value);
        return calendar;

    }

    /**
     * <p>Returns the parsed {@code Date} unchanged.</p>
     *
     * @param value The parsed {@code Date} object created.
     * @param formatter The Format used to parse the value with.
     * @return The parsed value converted to a {@link Calendar}.
     */
    @Override
    protected Object processParsedValue(final Object value, final Format formatter) {
        return value;
    }

    /**
     * <p>Validate/convert a {@code Date} using the default
     *    {@link Locale} and {@code TimeZone}.
     *
     * @param value The value validation is being performed on.
     * @return The parsed {@code Date} if valid or {@code null}
     *  if invalid.
     */
    public Date validate(final String value) {
        return (Date) parse(value, (String) null, (Locale) null, (TimeZone) null);
    }

    /**
     * <p>Validate/convert a {@code Date} using the specified
     *    {@link Locale} and default {@code TimeZone}.
     *
     * @param value The value validation is being performed on.
     * @param locale The locale to use for the date format, system default if null.
     * @return The parsed {@code Date} if valid or {@code null} if invalid.
     */
    public Date validate(final String value, final Locale locale) {
        return (Date) parse(value, (String) null, locale, (TimeZone) null);
    }

    /**
     * <p>Validate/convert a {@code Date} using the specified
     *    {@link Locale} and {@code TimeZone}.
     *
     * @param value The value validation is being performed on.
     * @param locale The locale to use for the date format, system default if null.
     * @param timeZone The Time Zone used to parse the date, system default if null.
     * @return The parsed {@code Date} if valid or {@code null} if invalid.
     */
    public Date validate(final String value, final Locale locale, final TimeZone timeZone) {
        return (Date) parse(value, (String) null, locale, timeZone);
    }

    /**
     * <p>Validate/convert a {@code Date} using the specified
     *    <em>pattern</em> and default {@code TimeZone}.
     *
     * @param value The value validation is being performed on.
     * @param pattern The pattern used to validate the value against, or the
     *        default for the {@link Locale} if {@code null}.
     * @return The parsed {@code Date} if valid or {@code null} if invalid.
     */
    public Date validate(final String value, final String pattern) {
        return (Date) parse(value, pattern, (Locale) null, (TimeZone) null);
    }

    /**
     * <p>Validate/convert a {@code Date} using the specified pattern
     *    and {@link Locale} and the default {@code TimeZone}.
     *
     * @param value The value validation is being performed on.
     * @param pattern The pattern used to validate the value against, or the
     *        default for the {@link Locale} if {@code null}.
     * @param locale The locale to use for the date format, system default if null.
     * @return The parsed {@code Date} if valid or {@code null} if invalid.
     */
    public Date validate(final String value, final String pattern, final Locale locale) {
        return (Date) parse(value, pattern, locale, (TimeZone) null);
    }

    /**
     * <p>Validate/convert a {@code Date} using the specified
     *    pattern, and {@link Locale} and {@code TimeZone}.
     *
     * @param value The value validation is being performed on.
     * @param pattern The pattern used to validate the value against, or the
     *        default for the {@link Locale} if {@code null}.
     * @param locale The locale to use for the date format, system default if null.
     * @param timeZone The Time Zone used to parse the date, system default if null.
     * @return The parsed {@code Date} if valid or {@code null} if invalid.
     */
    public Date validate(final String value, final String pattern, final Locale locale, final TimeZone timeZone) {
        return (Date) parse(value, pattern, locale, timeZone);
    }

    /**
     * <p>Validate/convert a {@code Date} using the specified
     *    <em>pattern</em> and {@code TimeZone}.
     *
     * @param value The value validation is being performed on.
     * @param pattern The pattern used to validate the value against, or the
     *        default for the {@link Locale} if {@code null}.
     * @param timeZone The Time Zone used to parse the date, system default if null.
     * @return The parsed {@code Date} if valid or {@code null} if invalid.
     */
    public Date validate(final String value, final String pattern, final TimeZone timeZone) {
        return (Date) parse(value, pattern, (Locale) null, timeZone);
    }

    /**
     * <p>Validate/convert a {@code Date} using the specified
     *    {@code TimeZone} and default {@link Locale}.
     *
     * @param value The value validation is being performed on.
     * @param timeZone The Time Zone used to parse the date, system default if null.
     * @return The parsed {@code Date} if valid or {@code null} if invalid.
     */
    public Date validate(final String value, final TimeZone timeZone) {
        return (Date) parse(value, (String) null, (Locale) null, timeZone);
    }

}
