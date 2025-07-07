/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.common.util;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link DateUtil} class using JUnit 4.This class validates the following:
 - Date formatting (default and custom patterns)
 - Date parsing (default and custom patterns)
 - Conversion between {@link LocalDate} and {@link java.sql.Date}
 * 
 */
public class DateUtilsTest {

    // ----------- Format Date Tests -----------

    /**
     * Tests formatting with the default pattern (MM-dd-yyyy).
     */    
    @Test
    public void testFormatDate_defaultPattern() {
        LocalDate inputDate = LocalDate.of(2009, 9, 22);
        String result = DateUtil.formatDate(inputDate);
        assertEquals("09-22-2009", result);
    }

    /**
     * Tests formatting null LocalDate returns null.
     */    
    @Test
    public void testFormatDate_nullInput_returnsNull() {
        assertNull(DateUtil.formatDate(null));
    }
    
    /**
     * Tests formatting with a custom pattern.
     */
    @Test
    public void testFormatDate_customPattern() {
        LocalDate inputDate = LocalDate.of(2009, 9, 22);
        String result = DateUtil.formatDate(inputDate, "yyyy/MM/dd");
        assertEquals("2009/09/22", result);
    }

    /**
     * Tests formatting null LocalDate with a custom pattern returns null.
     */    
    @Test
    public void testFormatDate_customPattern_nullLocalDate_returnsNull() {
        assertNull(DateUtil.formatDate(null, "yyyy/MM/dd"));
    }

    /**
     * Tests formatting with a null pattern returns null.
     */    
    @Test
    public void testFormatDate_customPattern_nullPattern_returnsNull() {
        assertNull(DateUtil.formatDate(LocalDate.now(), null));
    }

    // ----------- Parse Date with Default Pattern -----------

    /**
     * Tests parsing a date string using the default pattern.
     */    
    @Test
    public void testParseDate_defaultPattern() {
        String input = "06-15-2025";
        LocalDate result = DateUtil.parseDate(input);
        assertEquals(LocalDate.of(2025, 6, 15), result);
    }

    /**
     * Tests parsing a string with the wrong pattern throws exception.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_wrongPattern_throwsParseException() {
        // Wrong pattern: uses slashes instead of dashes
        DateUtil.parseDate("2025/06/15");
    }
    
    /**
     * Tests parsing a null input throws an exception.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_defaultPattern_nullInput_throwsException() {
        DateUtil.parseDate(null);
    }

    /**
     * Tests parsing a non-date string throws an exception.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_invalidLocalDateInput_throwsParseException() {
        DateUtil.parseDate("not-a-date");
    }

    /**
     * Tests parsing an incorrectly structured date string that matches pattern but has invalid values.
     */   
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_invalidButFormattedCorrectly_throwsException() {
        DateUtil.parseDate("13-45-2025"); // Matches pattern but invalid calendar values
    }

    // ----------- Parse Date with Custom Pattern -----------

    /**
     * Tests parsing a date string using a custom pattern.
     */    
    @Test
    public void testParseDate_customPattern() {
        String inputDate = "2025/06/15";
        String pattern = "yyyy/MM/dd";
        LocalDate result = DateUtil.parseDate(inputDate, pattern);
        assertEquals(LocalDate.of(2025, 6, 15), result);
    }

    /**
     * Tests parsing with null input and custom pattern throws exception.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_customPattern_nullInput_throwsException() {
        DateUtil.parseDate(null, "yyyy/MM/dd");
    }

    /**
     * Tests parsing with null pattern throws exception.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_customPattern_nullPattern_throwsException() {
        DateUtil.parseDate("2025/01/01", null);
    }
    
    /**
     * Tests parsing invalid content using a valid pattern throws exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_customPattern_invalidLocalDateInput_throwsException() {
        DateUtil.parseDate("not-a-date", "yyyy/MM/dd");
    }

    /**
     * Tests parsing a string that matches the format but with invalid date values.
     */       
    @Test(expected = IllegalArgumentException.class)
    public void testParseDate_customPattern_invalidButFormattedCorrectly_throwsException() {
        DateUtil.parseDate("13-45-2025", "yyyy/MM/dd");
    }

    // ----------- Conversion: LocalDate <-> java.sql.Date -----------

    /**
     * Tests converting a valid LocalDate to SQL Date.
     */  
    @Test
    public void testToSqlDate_validLocalDate_returnsSqlDate() {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        java.sql.Date sqlDate = DateUtil.toSqlDate(localDate);
        assertNotNull(sqlDate);
        assertEquals("2024-12-31", sqlDate.toString());
    }
    
    /**
     * Tests converting null LocalDate to SQL Date returns null.
     */ 
    @Test
    public void testToSqlDate_nullInput_returnsNull() {
        assertNull(DateUtil.toSqlDate(null));
    }

    /**
     * Tests converting valid SQL Date to LocalDate.
     */   
    @Test
    public void testFromSqlDate_validSqlDate_returnsLocalDate() {
        java.sql.Date sqlDate = java.sql.Date.valueOf("2024-12-31");
        LocalDate result = DateUtil.fromSqlDate(sqlDate);
        assertEquals(LocalDate.of(2024, 12, 31), result);
    }

    /**
     * Tests converting null SQL Date to LocalDate returns null.
     */    
    @Test
    public void testFromSqlDate_nullInput_returnsNull() {
        assertNull(DateUtil.fromSqlDate(null));
    }
    
}
