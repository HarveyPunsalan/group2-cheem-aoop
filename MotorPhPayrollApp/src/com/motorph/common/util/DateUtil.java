/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * Utility class for handling date formatting and parsing using {@link java.time.LocalDate}.
 * Supports default and custom patterns, and conversions between {@link LocalDate} and {@link java.sql.Date}.
 * 
 * <p>Default date pattern used: <b>MM-dd-yyyy</b></p>
 */
public class DateUtil {
    private static final String DEFAULT_PATTERN = "MM-dd-yyyy";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    /**
     * Formats a {@link LocalDate} into a string using the default pattern "MM-dd-yyyy".
     *
     * @param localDate the date to format
     * @return formatted date string, or null if the input is null
     */
    public static String formatDate(LocalDate localDate) {
        if (localDate == null) return null;
        return DEFAULT_FORMATTER.format(localDate);
    }

    /**
     * Formats a {@link LocalDate} into a string using a custom pattern.
     *
     * @param localDate the date to format
     * @param pattern the custom date pattern (e.g., "yyyy/MM/dd")
     * @return formatted date string, or null if either parameter is invalid
     * @throws IllegalArgumentException if the pattern is invalid
     */
    public static String formatDate(LocalDate localDate, String pattern) {
        if (localDate == null || pattern == null || pattern.isBlank()) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDate);
    }

    /**
     * Parses a date string using the default pattern "MM-dd-yyyy" and returns a {@link LocalDate}.
     *
     * @param dateString the string to parse (must match default format)
     * @return parsed {@link LocalDate}
     * @throws IllegalArgumentException if the string is null, blank, or improperly formatted
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.isBlank())
            throw new IllegalArgumentException("Date string must not be null or blank");

        try {
            return LocalDate.parse(dateString, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, expected: " + DEFAULT_PATTERN, e);
        }
    }

    /**
     * Parses a date string using a custom pattern and returns a {@link LocalDate}.
     *
     * @param dateString the string to parse
     * @param pattern the custom pattern to use
     * @return parsed {@link LocalDate}
     * @throws IllegalArgumentException if parameters are null/blank or pattern is invalid
     */
    public static LocalDate parseDate(String dateString, String pattern) {
        if (dateString == null || pattern == null || dateString.isBlank() || pattern.isBlank())
            throw new IllegalArgumentException("Date string and pattern must not be null or blank");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for pattern: " + pattern, e);
        }
    }

    /**
     * Converts a {@link LocalDate} to {@link java.sql.Date} (for JDBC operations).
     *
     * @param localDate the date to convert
     * @return SQL date, or null if input is null
     */
    public static java.sql.Date toSqlDate(LocalDate localDate) {
        if (localDate == null) return null;
        return java.sql.Date.valueOf(localDate);
    }

    /**
     * Converts a {@link java.sql.Date} to {@link LocalDate}.
     *
     * @param sqlDate the SQL date to convert
     * @return LocalDate, or null if input is null
     */
    public static LocalDate fromSqlDate(java.sql.Date sqlDate) {
        if (sqlDate == null) return null;
        return sqlDate.toLocalDate();
    }

    public static String formatDateRange(LocalDate start, LocalDate end) {
        if (start == null || end == null) return "";
        return DEFAULT_FORMATTER.format(start) + " : " + DEFAULT_FORMATTER.format(end);
    }
        
            // 1. 18th
    public static String format_ddo(LocalDate date) {
        if (date == null) return "";
        return formatDayOfMonthWithOrdinal(date.getDayOfMonth());
    }

    // 2. 18th Jul
    public static String format_ddo_MMM(LocalDate date) {
        if (date == null) return "";
        return String.format("%s %s",
            format_ddo(date),
            date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
    }

    // 3. 18th July
    public static String format_ddo_MMMM(LocalDate date) {
        if (date == null) return "";
        return String.format("%s %s",
            format_ddo(date),
            date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }

    // 4. 18th Jul 2025
    public static String format_ddo_MMM_yyyy(LocalDate date) {
        if (date == null) return "";
        return String.format("%s %s %d",
            format_ddo(date),
            date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            date.getYear());
    }

    // 5. Friday 18th Jul 2025
    public static String format_EEEE_ddo_MMM_yyyy(LocalDate date) {
        if (date == null) return "";
        return String.format("%s %s",
            date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
            format_ddo_MMM_yyyy(date));
    }

    // 6. Fri | 18th Jul 2025
    public static String format_E_ddo_MMM_yyyy(LocalDate date) {
        if (date == null) return "";
        return String.format("%s | %s",
            date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            format_ddo_MMM_yyyy(date));
    }

    // 7. Bi-weekly Payroll: Jul 1st - Jul 15th
    public static String format_RANGE_BiWeekly_MMM_ddo_ddo(LocalDate start, LocalDate end) {
        if (start == null || end == null) return "";
        String startStr = String.format("%s %s",
            start.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            format_ddo(start));
        String endStr = String.format("%s %s",
            end.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            format_ddo(end));
        return String.format("Bi-weekly Payroll: %s - %s", startStr, endStr);
    }
    
    // 7. Bi-weekly Payroll: Jul 1st - Jul 15th
    public static String format_RANGE_MMM_ddo_ddo(LocalDate start, LocalDate end) {
        if (start == null || end == null) return "";
        String startStr = String.format("%s %s",
            start.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            format_ddo(start));
        String endStr = String.format("%s %s",
            end.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            format_ddo(end));
        return String.format("%s - %s", startStr, endStr);
    }

    // 8. 1st - 15th Jul
    public static String format_RANGE_ddo_ddo_MMM(LocalDate start, LocalDate end) {
        if (start == null || end == null) return "";
        String range = String.format("%s - %s",
            format_ddo(start),
            format_ddo(end));
        String month = end.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        return range + " " + month;
    }

    // 9. Mon, 1st Jul 2025 - Tue, 15th Jul 2025
    public static String format_RANGE_E_ddo_MMM_yyyy(LocalDate start, LocalDate end) {
        if (start == null || end == null) return "";
        String startFormatted = String.format("%s, %s",
            start.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            format_ddo_MMM_yyyy(start));
        String endFormatted = String.format("%s, %s",
            end.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            format_ddo_MMM_yyyy(end));
        return startFormatted + " - " + endFormatted;
    }

    // 🔧 Internal helper for ordinal suffix
    private static String formatDayOfMonthWithOrdinal(int day) {
        if (day >= 11 && day <= 13) return day + "th";
        return switch (day % 10) {
            case 1 -> day + "st";
            case 2 -> day + "nd";
            case 3 -> day + "rd";
            default -> day + "th";
        };
    }
    
    public static LocalDate[] parse_LABEL_BiWeekly_MMM_ddo_ddo_to_MM_dd_yyyy(String biWeeklyLabel, int year) {
        if (biWeeklyLabel == null || !biWeeklyLabel.contains(":")) return new LocalDate[]{null, null};

        try {
            // Extract "Jul 1st - Jul 15th"
            String rangePart = biWeeklyLabel.split(":")[1].trim();
            String[] parts = rangePart.split(" - ");

            LocalDate start = parseShortOrdinalDate(parts[0], year);
            LocalDate end = parseShortOrdinalDate(parts[1], year);

            return new LocalDate[]{start, end};
        } catch (Exception e) {
            e.printStackTrace(); // 👈 Print the actual error
            return new LocalDate[]{null, null};
        }
    }

    private static LocalDate parseShortOrdinalDate(String text, int year) {
        // Remove ordinal suffix like "st", "nd", "rd", "th"
        String clean = text.trim().replaceAll("(?<=\\d)(st|nd|rd|th)", "");

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("MMM d")
                .toFormatter(Locale.ENGLISH);

        // Parse without a year
        TemporalAccessor accessor = formatter.parse(clean);
        int month = accessor.get(ChronoField.MONTH_OF_YEAR);
        int day = accessor.get(ChronoField.DAY_OF_MONTH);

        return LocalDate.of(year, month, day);
    }

}
