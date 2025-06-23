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
 *
 * @author 63909
 */
public class TaxCalculator {
    private static final DecimalFormat DECIMAL = new DecimalFormat("0.00");

    public static double calculateWithHoldingTax(double gross, double totalDeductions) {
        // Define brackets
        Double[] n20832 = {0.00, 0.00, 0.00};
        Double[] n20833 = {0.20, 20833.00, 0.00};
        Double[] n33333 = {0.25, 33333.00, 2500.00};
        Double[] n66667 = {0.30, 66667.00, 10833.00};
        Double[] n166667 = {0.32, 166667.00, 40833.33};
        Double[] n666667 = {0.35, 666667.00, 200833.33};

        TreeMap<Double, Double[]> withHTaxChart = new TreeMap<>();
        withHTaxChart.put(0.00, n20832);
        withHTaxChart.put(20833.00, n20833);
        withHTaxChart.put(33333.00, n33333);
        withHTaxChart.put(66667.00, n66667);
        withHTaxChart.put(166667.00, n166667);
        withHTaxChart.put(666667.00, n666667);

        Map.Entry<Double, Double[]> entry = withHTaxChart.floorEntry(gross);
        if (entry == null) return 0.0;

        Double[] bracket = entry.getValue();
        double rate      = bracket[0];
        double threshold = bracket[1];
        double baseTax   = bracket[2];

        double taxable   = (gross - totalDeductions) - threshold;
        double tax       = (taxable * rate) + baseTax;
        return Double.parseDouble(DECIMAL.format(tax < 0 ? 0 : tax));
    }

    public static BigDecimal calculateWithHoldingTax(BigDecimal gross, BigDecimal totalDeductions) {
        if (gross == null) gross = BigDecimal.ZERO;
        if (totalDeductions == null) totalDeductions = BigDecimal.ZERO;
        double dGross = gross.doubleValue();
        double dDeduct = totalDeductions.doubleValue();
        double result = calculateWithHoldingTax(dGross, dDeduct);
        return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP);
    }
}
