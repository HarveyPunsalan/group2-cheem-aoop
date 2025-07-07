/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.payroll;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author 63909
 */
public class PayrollSummary {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private BigDecimal totalBasicSalary;
    private BigDecimal totalGrossSalary;
    private BigDecimal totalGovernmentContribution;
    private BigDecimal totalWithholdingTax;
    private BigDecimal totalDeduction;
    private BigDecimal totalNetSalary;

    public PayrollSummary(BigDecimal totalBasicSalary, BigDecimal totalGrossSalary,
                          BigDecimal totalGovernmentContribution, BigDecimal totalWithholdingTax,
                          BigDecimal totalDeduction, BigDecimal totalNetSalary) {
        this.totalBasicSalary = totalBasicSalary;
        this.totalGrossSalary = totalGrossSalary;
        this.totalGovernmentContribution = totalGovernmentContribution;
        this.totalWithholdingTax = totalWithholdingTax;
        this.totalDeduction = totalDeduction;
        this.totalNetSalary = totalNetSalary;
    }

    // Getters for each field
    public BigDecimal getTotalBasicSalary() { return totalBasicSalary; }
    public BigDecimal getTotalGrossSalary() { return totalGrossSalary; }
    public BigDecimal getTotalGovernmentContribution() { return totalGovernmentContribution; }
    public BigDecimal getTotalWithholdingTax() { return totalWithholdingTax; }
    public BigDecimal getTotalDeductions() { return totalDeduction; }
    public BigDecimal getTotalNetSalary() { return totalNetSalary; }

    @Override
    public String toString() {
        return "PayrollSummary{" +
                "totalBasicSalary=" + totalBasicSalary +
                ", totalGrossSalary=" + totalGrossSalary +
                ", totalGovernmentContribution=" + totalGovernmentContribution +
                ", totalWithholdingTax=" + totalWithholdingTax +
                ", totalDeduction=" + totalDeduction +
                ", totalNetSalary=" + totalNetSalary +
                '}';
    }
}

