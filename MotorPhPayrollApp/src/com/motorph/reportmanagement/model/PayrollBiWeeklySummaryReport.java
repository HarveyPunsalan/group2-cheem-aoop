/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.model;

/**
 *
 * @author 63909
 */
public class PayrollBiWeeklySummaryReport {
    private final String employeeID;
    private final String employeeName;
    private final String jobTitle;
    private final String department;

    private final double grossIncome;

    private final String sssNumber;
    private final double sssContribution;

    private final String philhealthNumber;
    private final double philhealthContribution;

    private final String pagibigNumber;
    private final double pagibigContribution;

    private final String taxIdentificationNumber;
    private final double withholdingTax;

    private final double netPay;
    
    public PayrollBiWeeklySummaryReport(Builder builder){
        this.employeeID = builder.employeeID;
        this.employeeName = builder.employeeName;
        this.jobTitle = builder.jobTitle;
        this.department = builder.department;
        this.grossIncome = builder.grossIncome;
        this.sssNumber = builder.sssNumber;
        this.sssContribution = builder.sssContribution;
        this.philhealthNumber = builder.philhealthNumber;
        this.philhealthContribution = builder.philhealthContribution;
        this.pagibigNumber = builder.pagibigNumber;
        this.pagibigContribution = builder.pagibigContribution;
        this.taxIdentificationNumber = builder.taxIdentificationNumber;
        this.withholdingTax = builder.withholdingTax;
        this.netPay = builder.netPay;
    }

    public static class Builder {
        String employeeID;
        String employeeName;
        String jobTitle;
        String department;
        
        double grossIncome;
        
        String sssNumber;
        double sssContribution;
        
        String philhealthNumber;
        double philhealthContribution;
        
        String pagibigNumber;
        double pagibigContribution;
        
        String taxIdentificationNumber;
        double withholdingTax;
        
        double netPay;

        public Builder employeeID(String employeeID) {
            this.employeeID = employeeID;
            return this;
        }

        public Builder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder grossIncome(double grossIncome) {
            this.grossIncome = grossIncome;
            return this;
        }

        public Builder sssNumber(String sssNumber) {
            this.sssNumber = sssNumber;
            return this;
        }

        public Builder sssContribution(double sssContribution) {
            this.sssContribution = sssContribution;
            return this;
        }

        public Builder philhealthNumber(String philhealthNumber) {
            this.philhealthNumber = philhealthNumber;
            return this;
        }

        public Builder philhealthContribution(double philhealthContribution) {
            this.philhealthContribution = philhealthContribution;
            return this;
        }

        public Builder pagibigNumber(String pagibigNumber) {
            this.pagibigNumber = pagibigNumber;
            return this;
        }

        public Builder pagibigContribution(double pagibigContribution) {
            this.pagibigContribution = pagibigContribution;
            return this;
        }

        public Builder taxIdentificationNumber(String taxIdentificationNumber) {
            this.taxIdentificationNumber = taxIdentificationNumber;
            return this;
        }

        public Builder withholdingTax(double withholdingTax) {
            this.withholdingTax = withholdingTax;
            return this;
        }

        public Builder netPay(double netPay) {
            this.netPay = netPay;
            return this;
        }
        
        public PayrollBiWeeklySummaryReport build() {
            return new PayrollBiWeeklySummaryReport(this);
        }
    }
    
    @Override
    public String toString() {
        return "PayrollMonthlySummaryReportData{" +
            "employeeID='" + employeeID + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", jobTitle='" + jobTitle + '\'' +
            ", department='" + department + '\'' +
                
            ", grossIncome=" + grossIncome +
                
            ", sssNumber='" + sssNumber + '\'' +
            ", sssContribution=" + sssContribution +
                
            ", philhealthNumber='" + philhealthNumber + '\'' +
            ", philhealthContribution=" + philhealthContribution +
                
            ", pagibigNumber='" + pagibigNumber + '\'' +
            ", pagibigContribution=" + pagibigContribution +
                
            ", taxIdentificationNumber='" + taxIdentificationNumber + '\'' +
            ", withholdingTax=" + withholdingTax +
                
            ", netPay=" + netPay +
            '}';
    }

    // Getters only (immutable)
    public String getEmployeeID() { return employeeID; }
    public String getEmployeeName() { return employeeName; }
    public String getJobTitle() { return jobTitle; }
    public String getDepartment() { return department; }
    
    public double getGrossIncome() { return grossIncome; }
    
    public String getSssNumber() { return sssNumber; }
    public double getSssContribution() { return sssContribution; }
    
    public String getPhilhealthNumber() { return philhealthNumber; }
    public double getPhilhealthContribution() { return philhealthContribution; }
    
    public String getPagibigNumber() { return pagibigNumber; }
    public double getPagibigContribution() { return pagibigContribution; }
    
    public String getTaxIdentificationNumber() { return taxIdentificationNumber; }
    public double getWithholdingTax() { return withholdingTax; }
    
    public double getNetPay() { return netPay; }
}

