/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author 63909
 */
public class SalaryCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    /**
     * Calculates the basic salary based on hours worked and hourly rate.
     * @param hoursWorked
     * @param hourlyRate
     * @return 
     */
    public static BigDecimal calculateBasicSalary(BigDecimal hoursWorked, BigDecimal hourlyRate) {
        return new BigDecimal(decimalFormat.format(hoursWorked.multiply(hourlyRate)));
    }

    /**
     * Calculates the gross salary by adding the basic salary and total allowances.
     * @param basicSalary
     * @param totalAllowance
     * @return 
     */
    public static BigDecimal calculateGrossSalary(BigDecimal basicSalary, BigDecimal totalAllowance) {
        return basicSalary.add(totalAllowance);
    } 
    
    /**
     * Calculates the net salary by orchestrating the basic salary, allowances,
     * government contributions, and tax deductions.
     * @param grossSalary
     * @param totalDeductions
     * @param withHtax
     * @return 
     */
    public static BigDecimal calculateNetSalary(BigDecimal grossSalary, BigDecimal totalDeductions, BigDecimal withHtax) {
            return grossSalary.subtract(totalDeductions.add(withHtax)).setScale(2, RoundingMode.HALF_UP);
    }
}
