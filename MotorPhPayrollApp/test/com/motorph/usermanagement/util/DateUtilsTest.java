/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.util;

import com.toedter.calendar.JDateChooser;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harvey
 */
public class DateUtilsTest {
    
    public DateUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCurrentTimestamp method, of class DateUtils.
     */
    @Test
    public void testGetCurrentTimestamp() {
        System.out.println("getCurrentTimestamp");
        Timestamp expResult = null;
        Timestamp result = DateUtils.getCurrentTimestamp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatDateTime method, of class DateUtils.
     */
    @Test
    public void testFormatDateTime_LocalDateTime() {
        System.out.println("formatDateTime");
        LocalDateTime dateTime = null;
        String expResult = "";
        String result = DateUtils.formatDateTime(dateTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatDateTime method, of class DateUtils.
     */
    @Test
    public void testFormatDateTime_LocalDateTime_String() {
        System.out.println("formatDateTime");
        LocalDateTime dateTime = null;
        String pattern = "";
        String expResult = "";
        String result = DateUtils.formatDateTime(dateTime, pattern);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatForDisplay method, of class DateUtils.
     */
    @Test
    public void testFormatForDisplay() {
        System.out.println("formatForDisplay");
        Timestamp timestamp = null;
        String expResult = "";
        String result = DateUtils.formatForDisplay(timestamp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of daysBetween method, of class DateUtils.
     */
    @Test
    public void testDaysBetween() {
        System.out.println("daysBetween");
        Timestamp start = null;
        Timestamp end = null;
        long expResult = 0L;
        long result = DateUtils.daysBetween(start, end);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWithinDays method, of class DateUtils.
     */
    @Test
    public void testIsWithinDays() {
        System.out.println("isWithinDays");
        Timestamp timestamp = null;
        int days = 0;
        boolean expResult = false;
        boolean result = DateUtils.isWithinDays(timestamp, days);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPast method, of class DateUtils.
     */
    @Test
    public void testIsPast() {
        System.out.println("isPast");
        Timestamp timestamp = null;
        boolean expResult = false;
        boolean result = DateUtils.isPast(timestamp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDays method, of class DateUtils.
     */
    @Test
    public void testAddDays() {
        System.out.println("addDays");
        Timestamp timestamp = null;
        int days = 0;
        Timestamp expResult = null;
        Timestamp result = DateUtils.addDays(timestamp, days);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeAgo method, of class DateUtils.
     */
    @Test
    public void testGetTimeAgo() {
        System.out.println("getTimeAgo");
        Timestamp timestamp = null;
        String expResult = "";
        String result = DateUtils.getTimeAgo(timestamp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDateValidation method, of class DateUtils.
     */
    @Test
    public void testAddDateValidation() {
        System.out.println("addDateValidation");
        JDateChooser startDateChooser = null;
        JDateChooser endDateChooser = null;
        JTextField totalDaysField = null;
        DateUtils.addDateValidation(startDateChooser, endDateChooser, totalDaysField);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
