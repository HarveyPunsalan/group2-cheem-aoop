/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class for calculating deductions.
 *
 * <p>This class provides static methods to calculate various deductions based on employee data,
 * salary, or other parameters. Extend this class with specific deduction calculation logic as needed.</p>
 */
public class DeductionCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * Calculates the SSS deduction based on the gross salary.
     *
     * <p>This method uses a predefined SSS chart stored in a TreeMap, where each key represents a salary threshold and
     * each value represents the corresponding SSS deduction. The method finds the greatest key less than or equal to the
     * given gross salary and returns the corresponding deduction value. If no entry is found (i.e., the gross salary is
     * below the lowest threshold), the method returns 0.0.</p>
     *
     * @param gross the gross salary.
     * @return the SSS deduction corresponding to the gross salary, or 0.0 if below threshold.
     */
    public static BigDecimal calculateSSS(BigDecimal gross) {
        // Create a TreeMap to store salary thresholds and corresponding SSS deduction amounts.
        TreeMap<BigDecimal, BigDecimal> sssChart = new TreeMap<>();
        sssChart.put(new BigDecimal(3249.00), new BigDecimal(135.00));
        sssChart.put(new BigDecimal(3250.00), new BigDecimal(157.50));
        sssChart.put(new BigDecimal(3750.00), new BigDecimal(180.00));
        sssChart.put(new BigDecimal(4250.00), new BigDecimal(202.50));
        sssChart.put(new BigDecimal(4750.00), new BigDecimal(225.00));
        sssChart.put(new BigDecimal(5250.00), new BigDecimal(247.50));
        sssChart.put(new BigDecimal(5750.00), new BigDecimal(270.00));
        sssChart.put(new BigDecimal(6250.00), new BigDecimal(292.50));
        sssChart.put(new BigDecimal(6750.00), new BigDecimal(315.00));
        sssChart.put(new BigDecimal(7250.00), new BigDecimal(337.50));
        sssChart.put(new BigDecimal(7750.00), new BigDecimal(360.00));
        sssChart.put(new BigDecimal(8250.00), new BigDecimal(382.50));
        sssChart.put(new BigDecimal(8750.00), new BigDecimal(405.00));
        sssChart.put(new BigDecimal(9250.00), new BigDecimal(427.50));
        sssChart.put(new BigDecimal(9750.00), new BigDecimal(450.00));
        sssChart.put(new BigDecimal(10250.00), new BigDecimal(472.50));
        sssChart.put(new BigDecimal(10750.00), new BigDecimal(495.00));
        sssChart.put(new BigDecimal(11250.00), new BigDecimal(517.50));
        sssChart.put(new BigDecimal(11750.00), new BigDecimal(540.00));
        sssChart.put(new BigDecimal(12250.00), new BigDecimal(562.50));
        sssChart.put(new BigDecimal(12750.00), new BigDecimal(585.00));
        sssChart.put(new BigDecimal(13250.00), new BigDecimal(607.50));
        sssChart.put(new BigDecimal(13750.00), new BigDecimal(630.00));
        sssChart.put(new BigDecimal(14250.00), new BigDecimal(652.50));
        sssChart.put(new BigDecimal(14750.00), new BigDecimal(675.00));
        sssChart.put(new BigDecimal(15250.00), new BigDecimal(697.50));
        sssChart.put(new BigDecimal(15750.00), new BigDecimal(720.00));
        sssChart.put(new BigDecimal(16250.00), new BigDecimal(742.50));
        sssChart.put(new BigDecimal(16750.00), new BigDecimal(765.00));
        sssChart.put(new BigDecimal(17250.00), new BigDecimal(787.50));
        sssChart.put(new BigDecimal(17750.00), new BigDecimal(810.00));
        sssChart.put(new BigDecimal(18250.00), new BigDecimal(832.50));
        sssChart.put(new BigDecimal(18750.00), new BigDecimal(855.00));
        sssChart.put(new BigDecimal(19250.00), new BigDecimal(877.50));
        sssChart.put(new BigDecimal(19750.00), new BigDecimal(900.00));
        sssChart.put(new BigDecimal(20250.00), new BigDecimal(922.50));
        sssChart.put(new BigDecimal(20750.00), new BigDecimal(945.00));
        sssChart.put(new BigDecimal(21250.00), new BigDecimal(967.50));
        sssChart.put(new BigDecimal(21750.00), new BigDecimal(990.00));
        sssChart.put(new BigDecimal(22250.00), new BigDecimal(1012.50));
        sssChart.put(new BigDecimal(22750.00), new BigDecimal(1035.00));
        sssChart.put(new BigDecimal(23250.00), new BigDecimal(1057.50));
        sssChart.put(new BigDecimal(23750.00), new BigDecimal(1080.00));
        sssChart.put(new BigDecimal(24250.00), new BigDecimal(1102.50));
        sssChart.put(new BigDecimal(24750.00), new BigDecimal(1125.00));
        
        // Get the entry with the greatest key less than or equal to the gross salary.
        Map.Entry<BigDecimal, BigDecimal> entry = sssChart.floorEntry(gross);
        
        if (entry == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP); // Return 0.0 if the gross salary is below the lowest threshold.
        }

        return entry.getValue().setScale(2, RoundingMode.HALF_UP); // Return the corresponding SSS deduction value.
    }

    /**
     * Calculates the PhilHealth deduction based on the gross salary.
     *
     * <p>This method calculates the deduction by taking 3% of the gross salary and then applying 50%
     * to that amount. The result is formatted using a predefined decimal format and then converted back
     * to a double.</p>
     *
     * @param gross the gross salary.
     * @return the calculated PhilHealth deduction.
     */
    public static BigDecimal calculatePhilHealth(BigDecimal gross) {
        BigDecimal rate = new BigDecimal("0.03");     // 3% total contribution
        BigDecimal half = new BigDecimal("0.5");      // employee share

        // (gross * 0.03) * 0.5 = employee share of PhilHealth
        BigDecimal contribution = gross.multiply(rate).multiply(half);

        return contribution.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the Pag-IBIG deduction based on the gross salary.
     *
     * <p>This method uses a predefined Pag-IBIG chart stored in a TreeMap where each key represents a salary
     * threshold and the associated value represents the deduction rate. The method finds the appropriate rate for
     * the given gross salary, calculates the deduction, caps it at 100 if necessary, formats the result, and returns it as a double.</p>
     *
     * @param gross the gross salary.
     * @return the calculated Pag-IBIG deduction.
     */
    public static BigDecimal calculatePagIbig(BigDecimal gross) {
        // Define rate chart using BigDecimal
        TreeMap<BigDecimal, BigDecimal> pagibigChart = new TreeMap<>();
        pagibigChart.put(new BigDecimal("1500.00"), new BigDecimal("0.01"));
        pagibigChart.put(new BigDecimal("1501.00"), new BigDecimal("0.02"));

        // Find appropriate rate for the given gross salary
        Map.Entry<BigDecimal, BigDecimal> entry = pagibigChart.floorEntry(gross);

        // If gross is less than lowest threshold, return 0.00
        if (entry == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal rate = entry.getValue();
        BigDecimal deduction = gross.multiply(rate);

        // Cap at 100.00
        BigDecimal max = new BigDecimal("100.00");
        if (deduction.compareTo(max) > 0) {
            deduction = max;
        }

        return deduction.setScale(2, RoundingMode.HALF_UP);
    }
    /**
     * Calculates the total government contribution.
     *
     * <p>This method adds the SSS, PhilHealth, and Pag-IBIG contributions together, formats the sum using a
     * predefined decimal format, and returns the result as a double.</p>
     *
     * @param sssContri the SSS contribution.
     * @param philhealthContri the PhilHealth contribution.
     * @param pagibigContri the Pag-IBIG contribution.
     * @return the total government contribution.
     */
    public static BigDecimal calculateGovernmentContribution(BigDecimal sssContri, BigDecimal philhealthContri, BigDecimal pagibigContri) {
        BigDecimal total = sssContri.add(philhealthContri).add(pagibigContri);
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the total deductions.
     *
     * <p>This method sums the government contribution and withholding tax, formats the result using a predefined
     * decimal format, and returns the formatted value as a double.</p>
     *
     * @param governmentContribution the total government contribution.
     * @param withHoldingTax the withholding tax amount.
     * @return the total deductions.
     */
    public static BigDecimal calculateTotalDeductions(BigDecimal governmentContribution, BigDecimal withHoldingTax) {
        BigDecimal total = governmentContribution.add(withHoldingTax);
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
