/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.Timestamp;

/**
 * Utility class for date and time operations used in the user management system
 * Provides methods for formatting dates, calculating time differences, and working with timestamps
 * 
 * @author harvey punsalan
 */

public class DateUtils {
    // Common date format patterns
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DISPLAY_DATETIME_FORMAT = "MMM dd, yyyy HH:mm:ss";
    public static final String DISPLAY_DATE_FORMAT = "MMM dd, yyyy";
    
    // Private constructor to prevent instantiation
    private DateUtils() {
        throw new IllegalStateException("Utility class should not be instantiated");
    }
    
    /**
     * Gets current timestamp as a SQL Timestamp object.
     * Commonly used for database operations like account creation and last login.
     * 
     * @return Current timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
    
    /**
     * Formats a LocalDateTime object to a string using the default format.
     * 
     * @param dateTime The LocalDateTime to format
     * @return Formatted date string, or empty string if dateTime is null
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
    }
    
    /**
     * Formats a LocalDateTime object to a string using a custom format.
     * 
     * @param dateTime The LocalDateTime to format
     * @param pattern The format pattern to use
     * @return Formatted date string, or empty string if dateTime is null
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null || pattern == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    /**
     * Formats a Timestamp for display purposes using a user-friendly format.
     * 
     * @param timestamp The Timestamp to format
     * @return Formatted date string for display
     */
    public static String formatForDisplay(Timestamp timestamp) {
        if (timestamp == null) {
            return "Never";
        }
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        return dateTime.format(DateTimeFormatter.ofPattern(DISPLAY_DATETIME_FORMAT));
    }
    
    /**
     * Calculates the number of days between two timestamps.
     * Useful for calculating password age or account inactivity periods.
     * 
     * @param start The start timestamp
     * @param end The end timestamp
     * @return Number of days between the timestamps
     */
    public static long daysBetween(Timestamp start, Timestamp end) {
        if (start == null || end == null) {
            return 0;
        }
        
        LocalDateTime startDateTime = start.toLocalDateTime();
        LocalDateTime endDateTime = end.toLocalDateTime();
        
        return ChronoUnit.DAYS.between(startDateTime, endDateTime);
    }
    
    /**
     * Checks if a timestamp is within a specified number of days from now.
     * Useful for checking if login attempts or password changes are recent.
     * 
     * @param timestamp The timestamp to check
     * @param days Number of days to check within
     * @return true if timestamp is within the specified days, false otherwise
     */
    public static boolean isWithinDays(Timestamp timestamp, int days) {
        if (timestamp == null) {
            return false;
        }
        
        LocalDateTime timestampDate = timestamp.toLocalDateTime();
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
        
        return timestampDate.isAfter(cutoffDate);
    }
    
    /**
     * Checks if a timestamp represents a date in the past.
     * 
     * @param timestamp The timestamp to check
     * @return true if timestamp is in the past, false otherwise
     */
    public static boolean isPast(Timestamp timestamp) {
        if (timestamp == null) {
            return false;
        }
        return timestamp.before(getCurrentTimestamp());
    }
    
    /**
     * Adds specified number of days to a timestamp.
     * Useful for calculating expiration dates or scheduling future events.
     * 
     * @param timestamp The base timestamp
     * @param days Number of days to add
     * @return New timestamp with added days
     */
    public static Timestamp addDays(Timestamp timestamp, int days) {
        if (timestamp == null) {
            return null;
        }
        
        LocalDateTime dateTime = timestamp.toLocalDateTime().plusDays(days);
        return Timestamp.valueOf(dateTime);
    }
    
    /**
     * Creates a user-friendly string showing how long ago a timestamp was.
     * Examples: "2 hours ago", "3 days ago", "Just now"
     * 
     * @param timestamp The timestamp to describe
     * @return Human-readable time difference string
     */
    public static String getTimeAgo(Timestamp timestamp) {
        if (timestamp == null) {
            return "Unknown";
        }
        
        LocalDateTime then = timestamp.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        
        long minutes = ChronoUnit.MINUTES.between(then, now);
        long hours = ChronoUnit.HOURS.between(then, now);
        long days = ChronoUnit.DAYS.between(then, now);
        
        if (minutes < 1) {
            return "Just now";
        } else if (minutes < 60) {
            return minutes + " minute" + (minutes == 1 ? "" : "s") + " ago";
        } else if (hours < 24) {
            return hours + " hour" + (hours == 1 ? "" : "s") + " ago";
        } else if (days < 30) {
            return days + " day" + (days == 1 ? "" : "s") + " ago";
        } else {
            return formatForDisplay(timestamp);
        }
    }
}
