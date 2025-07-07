/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Utility class for calculating allowances.
 *
 * <p>This class provides methods to calculate various allowances based on employee data,
 * salary, or other relevant parameters. Extend this class with specific calculation logic as needed.</p>
 */
public class AllowanceCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * Calculates the total allowance.
     *
     * <p>This method sums the rice, phone, and clothing allowances, divides the total by 2,
     * formats the result using a predefined decimal format, and then converts it back to a double.</p>
     *
     * @param rice the rice allowance.
     * @param phone the phone allowance.
     * @param clothing the clothing allowance.
     * @return the calculated total allowance.
     */
    public static BigDecimal calculateTotalAllowance(BigDecimal rice, BigDecimal phone, BigDecimal clothing) {
        // Calculate the sum of allowances and divide by 2.
        return rice.add(phone).add(clothing).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);// Format the calculated allowance and parse it back to a double.
    }
}
