/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

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
    private static final DecimalFormat DECIMAL = new DecimalFormat("0.00");

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
    public static double calculateSSS(double gross) {
        TreeMap<Double, Double> sssChart = new TreeMap<>();
        sssChart.put(3249.00, 135.00);
        sssChart.put(3250.00, 157.50);
        sssChart.put(3750.00, 180.00);
        sssChart.put(4250.00, 202.50);
        sssChart.put(4750.00, 225.00);
        sssChart.put(5250.00, 247.50);
        sssChart.put(5750.00, 270.00);
        sssChart.put(6250.00, 292.50);
        sssChart.put(6750.00, 315.00);
        sssChart.put(7250.00, 337.50);
        sssChart.put(7750.00, 360.00);
        sssChart.put(8250.00, 382.50);
        sssChart.put(8750.00, 405.00);
        sssChart.put(9250.00, 427.50);
        sssChart.put(9750.00, 450.00);
        sssChart.put(10250.00, 472.50);
        sssChart.put(10750.00, 495.00);
        sssChart.put(11250.00, 517.50);
        sssChart.put(11750.00, 540.00);
        sssChart.put(12250.00, 562.50);
        sssChart.put(12750.00, 585.00);
        sssChart.put(13250.00, 607.50);
        sssChart.put(13750.00, 630.00);
        sssChart.put(14250.00, 652.50);
        sssChart.put(14750.00, 675.00);
        sssChart.put(15250.00, 697.50);
        sssChart.put(15750.00, 720.00);
        sssChart.put(16250.00, 742.50);
        sssChart.put(16750.00, 765.00);
        sssChart.put(17250.00, 787.50);
        sssChart.put(17750.00, 810.00);
        sssChart.put(18250.00, 832.50);
        sssChart.put(18750.00, 855.00);
        sssChart.put(19250.00, 877.50);
        sssChart.put(19750.00, 900.00);
        sssChart.put(20250.00, 922.50);
        sssChart.put(20750.00, 945.00);
        sssChart.put(21250.00, 967.50);
        sssChart.put(21750.00, 990.00);
        sssChart.put(22250.00, 1012.50);
        sssChart.put(22750.00, 1035.00);
        sssChart.put(23250.00, 1057.50);
        sssChart.put(23750.00, 1080.00);
        sssChart.put(24250.00, 1102.50);
        sssChart.put(24750.00, 1125.00);

        Map.Entry<Double, Double> entry = sssChart.floorEntry(gross);
        if (entry == null) return 0.0;
        return Double.parseDouble(DECIMAL.format(entry.getValue()));
    }

    public static BigDecimal calculateSSS(BigDecimal gross) {
        if (gross == null) return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        return BigDecimal.valueOf(calculateSSS(gross.doubleValue())).setScale(2, RoundingMode.HALF_UP);
    }

    // --- Double-based PhilHealth ---
    public static double calculatePhilHealth(double gross) {
        double value = (gross * 0.03) * 0.5;
        return Double.parseDouble(DECIMAL.format(value));
    }

    public static BigDecimal calculatePhilHealth(BigDecimal gross) {
        if (gross == null) return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal value = gross.multiply(new BigDecimal("0.03"))
                                .multiply(new BigDecimal("0.5"))
                                .setScale(2, RoundingMode.HALF_UP);
        return value;
    }

    // --- Double-based Pag-IBIG ---
    public static double calculatePagIbig(double gross) {
        TreeMap<Double, Double> pagibigChart = new TreeMap<>();
        pagibigChart.put(1500.00, 0.01);
        pagibigChart.put(1501.00, 0.02);

        Map.Entry<Double, Double> entry = pagibigChart.floorEntry(gross);
        if (entry == null) return 0.0;
        double pagibig = gross * entry.getValue();
        double capped = (pagibig >= 100) ? 100 : pagibig;
        return Double.parseDouble(DECIMAL.format(capped));
    }

    public static BigDecimal calculatePagIbig(BigDecimal gross) {
        if (gross == null) return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        return BigDecimal.valueOf(calculatePagIbig(gross.doubleValue())).setScale(2, RoundingMode.HALF_UP);
    }

    // --- Government Contribution ---
    public static double calculateGovernmentContribution(double sss, double philHealth, double pagibig) {
        double sum = sss + philHealth + pagibig;
        return Double.parseDouble(DECIMAL.format(sum));
    }

    public static BigDecimal calculateGovernmentContribution(BigDecimal sss, BigDecimal philHealth, BigDecimal pagibig) {
        if (sss == null) sss = BigDecimal.ZERO;
        if (philHealth == null) philHealth = BigDecimal.ZERO;
        if (pagibig == null) pagibig = BigDecimal.ZERO;
        return sss.add(philHealth).add(pagibig).setScale(2, RoundingMode.HALF_UP);
    }

    // --- Total Deductions ---
    public static double calculateTotalDeductions(double gov, double whTax) {
        return Double.parseDouble(DECIMAL.format(gov + whTax));
    }

    public static BigDecimal calculateTotalDeductions(BigDecimal gov, BigDecimal whTax) {
        if (gov == null) gov = BigDecimal.ZERO;
        if (whTax == null) whTax = BigDecimal.ZERO;
        return gov.add(whTax).setScale(2, RoundingMode.HALF_UP);
    }
}
