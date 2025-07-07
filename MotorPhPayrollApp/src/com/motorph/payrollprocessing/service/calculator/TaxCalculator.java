/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author 63909
 */
public class TaxCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    /**
     * Calculates the withholding tax based on the gross salary and current total deductions.
     * @param gross
     * @param totalDeductions
     * @return 
     */
    public static BigDecimal calculateWithHoldingTax(BigDecimal gross, BigDecimal totalDeductions) {
        // Define tax brackets as arrays: [rate, base, fixed amount]
        BigDecimal[] n20832 = {bd("0.00"), bd("0.00"), bd("0.00")};
        BigDecimal[] n20833 = {bd("0.20"), bd("20833.00"), bd("0.00")};
        BigDecimal[] n33333 = {bd("0.25"), bd("33333.00"), bd("2500.00")};
        BigDecimal[] n66667 = {bd("0.30"), bd("66667.00"), bd("10833.00")};
        BigDecimal[] n166667 = {bd("0.32"), bd("166667.00"), bd("40833.33")};
        BigDecimal[] n666667 = {bd("0.35"), bd("666667.00"), bd("200833.33")};

        TreeMap<BigDecimal, BigDecimal[]> taxChart = new TreeMap<>();
        taxChart.put(bd("0.00"), n20832);
        taxChart.put(bd("20833.00"), n20833);
        taxChart.put(bd("33333.00"), n33333);
        taxChart.put(bd("66667.00"), n66667);
        taxChart.put(bd("166667.00"), n166667);
        taxChart.put(bd("666667.00"), n666667);

        // Taxable income
        BigDecimal taxable = gross.subtract(totalDeductions);

        // Get applicable tax bracket
        Map.Entry<BigDecimal, BigDecimal[]> entry = taxChart.floorEntry(taxable);
        if (entry == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal[] tax = entry.getValue();
        BigDecimal rate = tax[0];
        BigDecimal base = tax[1];
        BigDecimal fixed = tax[2];

        // Formula: ((taxable - base) * rate) + fixed
        BigDecimal excess = taxable.subtract(base);
        BigDecimal computedTax = excess.multiply(rate).add(fixed);

        return computedTax.setScale(2, RoundingMode.HALF_UP);
    }

    // Helper to shorten BigDecimal creation
    private static BigDecimal bd(String value) {
        return new BigDecimal(value);
    }
}
