/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service.processor;

import com.motorph.payrollprocessing.model.payroll.PayrollRecord;
import com.motorph.payrollprocessing.model.payroll.PayrollSummary;
import com.motorph.payrollprocessing.service.calculator.AllowanceCalculator;
import com.motorph.payrollprocessing.service.calculator.DeductionCalculator;
import com.motorph.payrollprocessing.service.calculator.SalaryCalculator;
import com.motorph.payrollprocessing.service.calculator.TaxCalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author 63909
 */
public class PayrollCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    
    /**
     * Calculates payroll details for a single employee and returns a PayrollRecord.
     * @param hoursWorked
     * @param hourlyRate
     * @param rice
     * @param phone
     * @param clothing
     * @return 
     */
    public static PayrollRecord calculatePayrollRecord(BigDecimal hoursWorked, BigDecimal hourlyRate, BigDecimal rice, BigDecimal phone, BigDecimal clothing) {
        BigDecimal basicSalary = SalaryCalculator.calculateBasicSalary(hoursWorked, hourlyRate);
        BigDecimal totalAllowance = AllowanceCalculator.calculateTotalAllowance(rice, phone, clothing);
        BigDecimal grossSalary = SalaryCalculator.calculateGrossSalary(basicSalary, totalAllowance);

        BigDecimal sss = DeductionCalculator.calculateSSS(grossSalary);
        BigDecimal philHealth = DeductionCalculator.calculatePhilHealth(grossSalary);
        BigDecimal pagIbig = DeductionCalculator.calculatePagIbig(grossSalary);
        BigDecimal governmentContribution = DeductionCalculator.calculateGovernmentContribution(sss, philHealth, pagIbig);

        BigDecimal withHoldingTax = TaxCalculator.calculateWithHoldingTax(grossSalary, governmentContribution);
        BigDecimal totalDeductions = DeductionCalculator.calculateTotalDeductions(governmentContribution, withHoldingTax);
        BigDecimal netSalary = grossSalary.subtract(totalDeductions).setScale(2, RoundingMode.HALF_UP);


        return new PayrollRecord();
    }
    
    /**
     * Aggregates payroll records for a pay period and returns a PayrollSummary.
     * @param records
     * @return 
     */
    public static PayrollSummary calculatePayrollSummary(List<PayrollRecord> records) {
        BigDecimal totalBasicSalary = BigDecimal.ZERO;
        BigDecimal totalGrossSalary = BigDecimal.ZERO;
        BigDecimal totalGovernmentContribution = BigDecimal.ZERO;
        BigDecimal totalWithholdingTax = BigDecimal.ZERO;
        BigDecimal totalDeductions = BigDecimal.ZERO;
        BigDecimal totalNetSalary = BigDecimal.ZERO;

        for (PayrollRecord record : records) {
            totalBasicSalary = totalBasicSalary.add(record.getBasicSalary());
            totalGrossSalary = totalGrossSalary.add(record.getGrossSalary());
//            totalGovernmentContribution = totalGovernmentContribution.add(record.getGovernmentContribution());
//            totalWithholdingTax = totalWithholdingTax.add(record.getWithHoldingTax());
            totalDeductions = totalDeductions.add(record.getTotalDeduction());
            totalNetSalary = totalNetSalary.add(record.getNetSalary());
        }

        return new PayrollSummary(
            totalBasicSalary.setScale(2),
            totalGrossSalary.setScale(2),
            totalGovernmentContribution.setScale(2),
            totalWithholdingTax.setScale(2),
            totalDeductions.setScale(2),
            totalNetSalary.setScale(2)
        );
    }
}
