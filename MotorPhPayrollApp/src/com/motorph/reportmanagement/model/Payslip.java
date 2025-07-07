/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.model;

import java.util.Date;

/**
 *
 * @author 63909
 */
public class Payslip {
    private final String payslipID;
    private final String employeeID;
    private final String employeeName;
    private final String position;
    private final String department;

    private final double monthlyRate;
    private final double dailyRate;
    private final int daysWorked;
    private final double overtime;

    private final double riceSubsidy;
    private final double phoneAllowance;
    private final double clothingAllowance;
    private final double totalAllowance;

    private final double grossSalary;

    private final double sss;
    private final double philhealth;
    private final double pagIbig;
    private final double withholdingTax;
    private final double totalDeductions;

    private final double netSalary;
    private final Date generatedDate;

    private final Date payPeriodStart;
    private final Date payPeriodEnd;
    private final Date payDay;   

    // 🟢 Parameterized constructor
    public Payslip(Builder builder) {
        this.payslipID = builder.payslipID;
        this.employeeID = builder.employeeID;
        this.employeeName = builder.employeeName;
        this.position = builder.position;
        this.department = builder.department;
        this.monthlyRate = builder.monthlyRate;
        this.dailyRate = builder.dailyRate;
        this.daysWorked = builder.daysWorked;
        this.overtime = builder.overtime;
        this.riceSubsidy = builder.riceSubsidy;
        this.phoneAllowance = builder.phoneAllowance;
        this.clothingAllowance = builder.clothingAllowance;
        this.totalAllowance = builder.totalAllowance;
        this.grossSalary = builder.grossSalary;
        this.sss = builder.sss;
        this.philhealth = builder.philhealth;
        this.pagIbig = builder.pagIbig;
        this.withholdingTax = builder.withholdingTax;
        this.totalDeductions = builder.totalDeductions;
        this.netSalary = builder.netSalary;
        this.generatedDate = builder.generatedDate;
        this.payPeriodStart = builder.payPeriodStart;
        this.payPeriodEnd = builder.payPeriodEnd;
        this.payDay = builder.payDay;
    }

    // ✅ Static nested Builder class
    public static class Builder {
        // Mirror the fields (not final)
        private String payslipID;
        private String employeeID;
        private String employeeName;
        private String position;
        private String department;

        private double monthlyRate;
        private double dailyRate;
        private int daysWorked;
        private double overtime;

        private double riceSubsidy;
        private double phoneAllowance;
        private double clothingAllowance;
        private double totalAllowance;

        private double grossSalary;

        private double sss;
        private double philhealth;
        private double pagIbig;
        private double withholdingTax;
        private double totalDeductions;

        private double netSalary;
        private Date generatedDate;

        private Date payPeriodStart;
        private Date payPeriodEnd;
        private Date payDay; 

        // Chained "setter-like" methods
        public Builder payslipID(String payslipID) {
            this.payslipID = payslipID;
            return this;
        }

        public Builder employeeID(String employeeID) {
            this.employeeID = employeeID;
            return this;
        }

        public Builder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder monthlyRate(double monthlyRate) {
            this.monthlyRate = monthlyRate;
            return this;
        }

        public Builder dailyRate(double dailyRate) {
            this.dailyRate = dailyRate;
            return this;
        }

        public Builder daysWorked(int daysWorked) {
            this.daysWorked = daysWorked;
            return this;
        }

        public Builder overtime(double overtime) {
            this.overtime = overtime;
            return this;
        }

        public Builder riceSubsidy(double riceSubsidy) {
            this.riceSubsidy = riceSubsidy;
            return this;
        }

        public Builder phoneAllowance(double phoneAllowance) {
            this.phoneAllowance = phoneAllowance;
            return this;
        }

        public Builder clothingAllowance(double clothingAllowance) {
            this.clothingAllowance = clothingAllowance;
            return this;
        }

        public Builder totalAllowance(double totalAllowance) {
            this.totalAllowance = totalAllowance;
            return this;
        }

        public Builder grossSalary(double grossSalary) {
            this.grossSalary = grossSalary;
            return this;
        }

        public Builder sss(double sss) {
            this.sss = sss;
            return this;
        }

        public Builder philhealth(double philhealth) {
            this.philhealth = philhealth;
            return this;
        }

        public Builder pagIbig(double pagIbig) {
            this.pagIbig = pagIbig;
            return this;
        }

        public Builder withholdingTax(double withholdingTax) {
            this.withholdingTax = withholdingTax;
            return this;
        }

        public Builder totalDeductions(double totalDeductions) {
            this.totalDeductions = totalDeductions;
            return this;
        }

        public Builder netSalary(double netSalary) {
            this.netSalary = netSalary;
            return this;
        }

        public Builder generatedDate(Date generatedDate) {
            this.generatedDate = generatedDate;
            return this;
        }

        public Builder payPeriodStart(Date payPeriodStart) {
            this.payPeriodStart = payPeriodStart;
            return this;
        }

        public Builder payPeriodEnd(Date payPeriodEnd) {
            this.payPeriodEnd = payPeriodEnd;
            return this;
        }

        public Builder payDay(Date payDay) {
            this.payDay = payDay;
            return this;
        }    
        
        // Final build() method
        public Payslip build() {
            return new Payslip(this);
        }
    }
    
    @Override
    public String toString() {
        return "Payslip{" +
            "payslipID='" + payslipID + '\'' +
            ", employeeID='" + employeeID + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", position='" + position + '\'' +
            ", department='" + department + '\'' +
                
            ", monthlyRate=" + monthlyRate +
            ", dailyRate=" + dailyRate +
            ", daysWorked=" + daysWorked +
            ", overtime=" + overtime +
                
            ", riceSubsidy=" + riceSubsidy +
            ", phoneAllowance=" + phoneAllowance +
            ", clothingAllowance=" + clothingAllowance +
            ", totalAllowance=" + totalAllowance + 
                
            ", grossSalary=" + grossSalary +
                
            ", sss=" + sss +
            ", philhealth=" + philhealth +
            ", pagIbig=" + pagIbig + 
            ", withholdingTax=" + withholdingTax +
            ", totalDeductions=" + totalDeductions +
                
            ", netSalary=" + netSalary +
            ", generatedDate=" + generatedDate +
                
            ", payPeriodStart=" + payPeriodStart +
            ", payPeriodEnd=" + payPeriodEnd +
            ", payDay=" + payDay +   
            '}';
    }
    
    // Getters
    public String getPayslipID() {return payslipID;}
    public String getEmployeeID() {return employeeID;}
    public String getEmployeeName() {return employeeName;}    
    public String getPosition() {return position;}
    public String getDepartment() {return department;}
    
    public double getMonthlyRate() {return monthlyRate;}
    public double getDailyRate() {return dailyRate;}
    public int getDaysWorked() {return daysWorked;}
    public double getOvertime() {return overtime;}
    
    public double getRiceSubsidy() {return riceSubsidy;}        
    public double getPhoneAllowance() {return phoneAllowance;}          
    public double getClothingAllowance() {return clothingAllowance;}       
    public double getTotalAllowance() {return totalAllowance;} 
    
    public double getGrossSalary() {return grossSalary;}  
    
    public double getSss() {return sss;}
    public double getPhilhealth() {return philhealth;}         
    public double getPagIbig() {return pagIbig;}          
    public double getWithholdingTax() {return withholdingTax;} 
    public double getTotalDeductions() {return totalDeductions;}

    public double getNetSalary() {return netSalary;}
    public Date getGeneratedDate() {return generatedDate;}

    public Date getPayPeriodStart() {return payPeriodStart;} 
    public Date getPayPeriodEnd() {return payPeriodEnd;}   
    public Date getPayDay() {return payDay;}       
}
