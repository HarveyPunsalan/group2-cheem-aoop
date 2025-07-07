/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.payroll;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author 63909
 */
public class Payslip {
    private final int payslipID;
    private final int employeeID;
    private final String employeeName;
    private final String position;
    private final String department;

    private final BigDecimal monthlyRate;
    private final BigDecimal hourlyRate;
    private final BigDecimal payableHours;
    private final BigDecimal overtime;

    private final BigDecimal riceSubsidy;
    private final BigDecimal phoneAllowance;
    private final BigDecimal clothingAllowance;
    private final BigDecimal totalAllowance;

    private final BigDecimal grossSalary;

    private final BigDecimal sss;
    private final BigDecimal philhealth;
    private final BigDecimal pagIbig;
    private final BigDecimal withholdingTax;
    private final BigDecimal totalDeductions;

    private final BigDecimal netSalary;
    private final LocalDateTime generatedDate;

    private final LocalDate payPeriodStart;
    private final LocalDate payPeriodEnd;
    private final LocalDate payDay;   

    // 🟢 Parameterized constructor
    public Payslip(Builder builder) {
        this.payslipID = builder.payslipID;
        this.employeeID = builder.employeeID;
        this.employeeName = builder.employeeName;
        this.position = builder.position;
        this.department = builder.department;
        this.monthlyRate = builder.monthlyRate;
        this.hourlyRate = builder.hourlyRate;
        this.payableHours = builder.payableHours;
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
        private int payslipID;
        private int employeeID;
        private String employeeName;
        private String position;
        private String department;

        private BigDecimal monthlyRate;
        private BigDecimal hourlyRate;
        private BigDecimal payableHours;
        private BigDecimal overtime;

        private BigDecimal riceSubsidy;
        private BigDecimal phoneAllowance;
        private BigDecimal clothingAllowance;
        private BigDecimal totalAllowance;

        private BigDecimal grossSalary;

        private BigDecimal sss;
        private BigDecimal philhealth;
        private BigDecimal pagIbig;
        private BigDecimal withholdingTax;
        private BigDecimal totalDeductions;

        private BigDecimal netSalary;
        private LocalDateTime generatedDate;

        private LocalDate payPeriodStart;
        private LocalDate payPeriodEnd;
        private LocalDate payDay; 

        // Chained "setter-like" methods
        public Builder payslipID(int payslipID) {
            this.payslipID = payslipID;
            return this;
        }

        public Builder employeeID(int employeeID) {
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

        public Builder monthlyRate(BigDecimal monthlyRate) {
            this.monthlyRate = monthlyRate;
            return this;
        }

        public Builder hourlyRate(BigDecimal hourlyRate) {
            this.hourlyRate = hourlyRate;
            return this;
        }

        public Builder payableHours(BigDecimal payableHours) {
            this.payableHours = payableHours;
            return this;
        }

        public Builder overtime(BigDecimal overtime) {
            this.overtime = overtime;
            return this;
        }

        public Builder riceSubsidy(BigDecimal riceSubsidy) {
            this.riceSubsidy = riceSubsidy;
            return this;
        }

        public Builder phoneAllowance(BigDecimal phoneAllowance) {
            this.phoneAllowance = phoneAllowance;
            return this;
        }

        public Builder clothingAllowance(BigDecimal clothingAllowance) {
            this.clothingAllowance = clothingAllowance;
            return this;
        }

        public Builder totalAllowance(BigDecimal totalAllowance) {
            this.totalAllowance = totalAllowance;
            return this;
        }

        public Builder grossSalary(BigDecimal grossSalary) {
            this.grossSalary = grossSalary;
            return this;
        }

        public Builder sss(BigDecimal sss) {
            this.sss = sss;
            return this;
        }

        public Builder philhealth(BigDecimal philhealth) {
            this.philhealth = philhealth;
            return this;
        }

        public Builder pagIbig(BigDecimal pagIbig) {
            this.pagIbig = pagIbig;
            return this;
        }

        public Builder withholdingTax(BigDecimal withholdingTax) {
            this.withholdingTax = withholdingTax;
            return this;
        }

        public Builder totalDeductions(BigDecimal totalDeductions) {
            this.totalDeductions = totalDeductions;
            return this;
        }

        public Builder netSalary(BigDecimal netSalary) {
            this.netSalary = netSalary;
            return this;
        }

        public Builder generatedDate(LocalDateTime generatedDate) {
            this.generatedDate = generatedDate;
            return this;
        }

        public Builder payPeriodStart(LocalDate payPeriodStart) {
            this.payPeriodStart = payPeriodStart;
            return this;
        }

        public Builder payPeriodEnd(LocalDate payPeriodEnd) {
            this.payPeriodEnd = payPeriodEnd;
            return this;
        }

        public Builder payDay(LocalDate payDay) {
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
            ", hourlyRate=" +  hourlyRate +
            ", payableHours=" + payableHours +
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
    public int getPayslipID() {return payslipID;}
    public int getEmployeeID() {return employeeID;}
    public String getEmployeeName() {return employeeName;}    
    public String getPosition() {return position;}
    public String getDepartment() {return department;}
    
    public BigDecimal getMonthlyRate() {return monthlyRate;}
    public BigDecimal getHourlyRate() {return hourlyRate;}
    public BigDecimal getPayableHours() {return payableHours;}
    public BigDecimal getOvertime() {return overtime;}
    
    public BigDecimal getRiceSubsidy() {return riceSubsidy;}        
    public BigDecimal getPhoneAllowance() {return phoneAllowance;}          
    public BigDecimal getClothingAllowance() {return clothingAllowance;}       
    public BigDecimal getTotalAllowance() {return totalAllowance;} 
    
    public BigDecimal getGrossSalary() {return grossSalary;}  
    
    public BigDecimal getSss() {return sss;}
    public BigDecimal getPhilhealth() {return philhealth;}         
    public BigDecimal getPagIbig() {return pagIbig;}          
    public BigDecimal getWithholdingTax() {return withholdingTax;} 
    public BigDecimal getTotalDeductions() {return totalDeductions;}

    public BigDecimal getNetSalary() {return netSalary;}
    public LocalDateTime getGeneratedDate() {return generatedDate;}

    public LocalDate getPayPeriodStart() {return payPeriodStart;} 
    public LocalDate getPayPeriodEnd() {return payPeriodEnd;}   
    public LocalDate getPayDay() {return payDay;} 
}
