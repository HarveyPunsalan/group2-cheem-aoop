/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.common.util;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Unit tests for the {@link NumberFormatter} utility class.
 *
 * This test suite covers:
 * <ul>
 *     <li>Formatting currency values in Philippine Peso</li>
 *     <li>Formatting decimals with comma separators</li>
 *     <li>Formatting percentages</li>
 *     <li>Rounding to two decimal places</li>
 * </ul>
 */
public class NumberFormatterTest {

    // -------------------- formatCurrency --------------------

    /**
     * Tests formatting a regular positive currency value.
     */    
    @Test
    public void testFormatCurrency_basicValue() {
        String formatted = NumberFormatter.formatCurrency(12345.67123);
        assertEquals("₱12,345.67", formatted);
    }

    /**
     * Tests formatting a zero currency value.
     */    
    @Test
    public void testFormatCurrency_zero() {
        String formatted = NumberFormatter.formatCurrency(0);
        assertEquals("₱0.00", formatted);
    }

    /**
     * Tests formatting a negative currency value.
     */    
    @Test
    public void testFormatCurrency_negative() {
        String formatted = NumberFormatter.formatCurrency(-9876.54);
        assertEquals("-₱9,876.54", formatted);
    }

    // -------------------- formatDecimal --------------------

    /**
     * Tests formatting a number with more than two decimal places.
     */
    @Test
    public void testFormatDecimal_basicValue() {
        String formatted = NumberFormatter.formatDecimal(12345.67892);
        assertEquals("12,345.68", formatted);
    }

    /**
     * Tests formatting a small decimal number.
     */
    @Test
    public void testFormatDecimal_smallValue() {
        String formatted = NumberFormatter.formatDecimal(0.5023);
        assertEquals("0.50", formatted);
    }

    // -------------------- formatPercentage --------------------

    /**
     * Tests formatting a decimal value into percentage.
     */
    @Test
    public void testFormatPercentage_basicValue() {
        String formatted = NumberFormatter.formatPercentage(0.12341);
        assertEquals("12.34%", formatted);
    }

    /**
     * Tests formatting a whole number (1.0 = 100%) as percentage.
     */
    @Test
    public void testFormatPercentage_fullValue() {
        String formatted = NumberFormatter.formatPercentage(1.0);
        assertEquals("100.00%", formatted);
    }

    // -------------------- roundToTwoDecimals --------------------

    /**
     * Tests rounding a number that requires rounding up.
     */
    @Test
    public void testRoundToTwoDecimals_exact() {
        double result = NumberFormatter.roundToTwoDecimals(123.456);
        assertEquals(123.46, result, 0.0001); // third param = delta tolerance
    }

    /**
     * Tests rounding a number that already has two decimals.
     */
    @Test
    public void testRoundToTwoDecimals_noRoundingNeeded() {
        double result = NumberFormatter.roundToTwoDecimals(100.00);
        assertEquals(100.00, result, 0.0001);
    }

    /**
     * Tests rounding a number that rounds down.
     */
    @Test
    public void testRoundToTwoDecimals_roundDown() {
        double result = NumberFormatter.roundToTwoDecimals(9.994);
        assertEquals(9.99, result, 0.0001);
    }
}
