/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utility class for formatting numeric values such as currency, decimals, percentages, and rounding.
 * <p>This class is tailored for the Philippine locale.</p>
 */
public class NumberFormatter {
    
    // Currency format for Philippine Peso (₱)
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH")); 
    static {
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setMaximumFractionDigits(2);
    }
    
    // Decimal format for standard numbers (e.g., 12,345.68)
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    // Decimal format for percentage (e.g., 0.1234 -> 12.34%)
    private static final DecimalFormat percentFormat = new DecimalFormat("0.00'%'");

    /**
     * Formats a numeric value into Philippine Peso currency format.
     * Example: 12345.67 → ₱12,345.67
     *
     * @param value the numeric value to format
     * @return the formatted string with peso symbol and thousand separators
     */
    public static String formatCurrency(double value) {
        return currencyFormat.format(value);
    }
    
    public static String formatCurrency(BigDecimal value) {
        return currencyFormat.format(value);
    }

    /**
     * Formats a numeric value to a string with comma separators and two decimal places.
     * Example: 12345.678 → "12,345.68"
     *
     * @param value the numeric value to format
     * @return formatted string with two decimal places
     */
    public static String formatDecimal(double value) {
        return decimalFormat.format(value);
    }
    
    public static String formatDecimal(Object value) {
        return decimalFormat.format(value);
    }

    /**
     * Formats a numeric value as a percentage string with two decimal places.
     * The value is automatically multiplied by 100.
     * Example: 0.1234 → "12.34%"
     *
     * @param value the decimal value to convert to percentage
     * @return formatted percentage string
     */
    public static String formatPercentage(double value) {
        return percentFormat.format(value * 100);
    }

    /**
     * Rounds a numeric value to the nearest two decimal places.
     * Example: 12.3456 → 12.35
     *
     * @param value the number to round
     * @return the rounded value to two decimals
     */
    public static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
