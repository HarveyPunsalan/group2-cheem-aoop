/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Utility class for calculating allowances.
 * <p>
 * Provides methods to calculate total allowance values for employees, based on 
 * rice, phone, clothing, and other allowances. All calculations are for a 
 * semi-monthly period (divided by 2 and rounded to 2 decimals).
 * </p>
 */
public class AllowanceCalculator {
   private static final DecimalFormat DECIMAL = new DecimalFormat("0.00");

    // double-based 3-arg: (rice + phone + clothing) / 2.0, rounded to 2 decimals.
    public static double calculateTotalAllowance(double rice, double phone, double clothing) {
        double raw = (rice + phone + clothing) / 2.0;
        return Double.parseDouble(DECIMAL.format(raw));
    }

    // double-based 4-arg: (rice + phone + clothing + other) / 2.0, rounded to 2 decimals.
    public static double calculateTotalAllowance(double rice, double phone, double clothing, double other) {
        double raw = (rice + phone + clothing + other) / 2.0;
        return Double.parseDouble(DECIMAL.format(raw));
    }

    // BigDecimal-based 3-arg: returns zero if all null, always setScale(2)
    public static BigDecimal calculateTotalAllowance(BigDecimal rice, BigDecimal phone, BigDecimal clothing) {
        if (rice == null && phone == null && clothing == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal r = rice     == null ? BigDecimal.ZERO : rice;
        BigDecimal p = phone    == null ? BigDecimal.ZERO : phone;
        BigDecimal c = clothing == null ? BigDecimal.ZERO : clothing;
        BigDecimal sum = r.add(p).add(c);
        return sum.divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP)
                  .setScale(2, RoundingMode.HALF_UP);
    }

    // BigDecimal-based 4-arg: returns zero if all null, always setScale(2)
    public static BigDecimal calculateTotalAllowance(BigDecimal rice, BigDecimal phone, BigDecimal clothing, BigDecimal other) {
        if (rice == null && phone == null && clothing == null && other == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal r = rice     == null ? BigDecimal.ZERO : rice;
        BigDecimal p = phone    == null ? BigDecimal.ZERO : phone;
        BigDecimal c = clothing == null ? BigDecimal.ZERO : clothing;
        BigDecimal o = other    == null ? BigDecimal.ZERO : other;
        BigDecimal sum = r.add(p).add(c).add(o);
        return sum.divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP)
                  .setScale(2, RoundingMode.HALF_UP);
    }
}