/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

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
    public static double calculateBasicSalary(double hoursWorked, double hourlyRate) {
        return Double.parseDouble(decimalFormat.format(hoursWorked * hourlyRate));
    }

    /**
     * Calculates the gross salary by adding the basic salary and total allowances.
     * @param basicSalary
     * @param totalAllowance
     * @return 
     */
    public static double calculateGrossSalary(double basicSalary, double totalAllowance) {
        return Double.parseDouble(decimalFormat.format(basicSalary + totalAllowance));
    } 
    
    /**
     * Calculates the net salary by orchestrating the basic salary, allowances,
     * government contributions, and tax deductions.
     * @param grossSalary
     * @param totalDeductions
     * @param withHtax
     * @return 
     */
    public static double calculateNetSalary(double grossSalary, double totalDeductions, double withholdingTax) {
            double result = grossSalary - (totalDeductions + withholdingTax);
            return Double.parseDouble(decimalFormat.format(result));
    }
    
    /** Overtime pay = hours x rate x 1.25 */
   public static double calculateOvertimePay(double hoursWorked, double hourlyRate) {
    if (hoursWorked <= 0 || hourlyRate <= 0) {
        return 0.0;
    }
    double result = hoursWorked * hourlyRate * 1.25;
    return Double.parseDouble(decimalFormat.format(result));
}
// ------------BigDecimal-based overload--------
    
    //Basic Salary = hoursWorked x hourlyRate
   public static BigDecimal calculateBasicSalary(BigDecimal hoursWorked, BigDecimal hourlyRate) {
       BigDecimal basic = hoursWorked.multiply(hourlyRate);
        return basic.setScale(2, RoundingMode.HALF_UP);
    }
   //Gross sa;ary = basicSalary + totalAllowance
   public static BigDecimal calculateGrossSalary(BigDecimal basicSalary, BigDecimal totalAllowance){
       BigDecimal gross = basicSalary.add(totalAllowance);
       return gross.setScale(2, RoundingMode.HALF_UP);
   }
   //Net Salary = grossSalary - (totalDeductions + withHoldingTax)
   public static BigDecimal calculateNetSalary(BigDecimal grossSalary, BigDecimal totalDeductions, BigDecimal withholdingTax){
       BigDecimal net = grossSalary.subtract(totalDeductions.add(withholdingTax));
       return net.setScale(2, RoundingMode.HALF_UP);
   }
   //Overtime Pay = hours x rate x 1.25
  public static BigDecimal calculateOvertimePay(BigDecimal hoursWorked, BigDecimal hourlyRate) {
    if (hoursWorked == null || hourlyRate == null
        || hoursWorked.compareTo(BigDecimal.ZERO) <= 0
        || hourlyRate.compareTo(BigDecimal.ZERO) <= 0) {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
    BigDecimal multiplier = new BigDecimal("1.25");
    BigDecimal overtime  = hoursWorked
            .multiply(hourlyRate)
            .multiply(multiplier);
    return overtime.setScale(2, RoundingMode.HALF_UP);
    }
}

