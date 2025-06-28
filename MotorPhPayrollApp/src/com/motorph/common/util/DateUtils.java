/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date formatting and parsing using {@link java.time.LocalDate}.
 * Supports default and custom patterns, and conversions between {@link LocalDate} and {@link java.sql.Date}.
 * 
 * <p>Default date pattern used: <b>MM-dd-yyyy</b></p>
 */
public class DateUtils {
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
}
