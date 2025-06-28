/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.attendancemanagement.model.PayPeriodAttendance;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author 63909
 */
public class PayrollRecord {
    private static final DateTimeFormatter formatterDate  = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private String payrollID;
    private PayPeriod payPeriodID;
    private Employee employeeID;
    private PayPeriodAttendance payPeriodAttendance;
    private BigDecimal basicSalary;
    private BigDecimal overtimePay;
    private Allowance allowance;
    private BigDecimal grossSalary;
    private Deduction deduction;
    private BigDecimal governmentContribution, withHoldingTax, totalDeduction;
    private BigDecimal netSalary;
    private LocalDate createdDate;
    private LocalDate submittedDate;

    public PayrollRecord() {
    }
    
    public PayrollRecord(BigDecimal basicSalary, BigDecimal grossSalary,
                         BigDecimal governmentContribution, BigDecimal withHoldingTax,
                         BigDecimal totalDeduction, BigDecimal netSalary) {
        this.basicSalary = basicSalary;
        this.grossSalary = grossSalary;
        this.governmentContribution = governmentContribution;
        this.withHoldingTax = withHoldingTax;
        this.totalDeduction = totalDeduction;
        this.netSalary = netSalary;
    }
    
    public PayrollRecord(String[] payrollData) {
        // Validate input length (expected 14 elements in this example)
        if (payrollData == null || payrollData.length < 14) {
            throw new IllegalArgumentException("Insufficient payroll data provided.");
        }
        
        // Assigning the values from the array:
        this.payrollID = payrollData[0];
        
        // Assuming PayPeriod has a constructor that takes a String (e.g., a date or period identifier)
        this.payPeriodID = new PayPeriod(payrollData[1]);
        
        // Assuming Employee has a constructor that takes a String (e.g., employee ID)
        this.employeeID = new Employee();
        
        // Assuming PayPeriodAttendance has a similar constructor
        this.payPeriodAttendance = new PayPeriodAttendance(payrollData[4]);
        
        // Convert numeric values from String to double
        this.basicSalary = new BigDecimal(payrollData[5]);
        this.overtimePay = new BigDecimal(payrollData[6]);
        
        // For allowance, assume the data is a comma-separated string "allowanceID,allowanceName,description"
        String[] allowanceParts = payrollData[7].split(",");
        if (allowanceParts.length >= 3) {
            this.allowance = new Allowance(allowanceParts[0].trim(), allowanceParts[1].trim(), allowanceParts[2].trim());
        } else {
            this.allowance = null; // Alternatively, handle the error as needed
        }
        
        this.grossSalary = new BigDecimal(payrollData[8]);
        
        // For deduction, assume the data is a comma-separated string "deductionID,deductionName,description"
        String[] deductionParts = payrollData[9].split(",");
        if (deductionParts.length >= 3) {
            this.deduction = new Deduction(deductionParts[0].trim(), deductionParts[1].trim(), deductionParts[2].trim());
        } else {
            this.deduction = null; // Alternatively, handle the error as needed
        }
        
        this.totalDeduction = new BigDecimal(payrollData[10]);
        this.netSalary = new BigDecimal(payrollData[11]);
        
        try {
            this.createdDate = LocalDate.parse(payrollData[12], formatterDate);
            this.submittedDate = LocalDate.parse(payrollData[13], formatterDate);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., rethrow as a runtime exception)
        }
    }
    
    public String getPayrollID() {
        return payrollID;
    }

    public PayPeriod getPayPeriodID() {
        return payPeriodID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public PayPeriodAttendance getPayPeriodAttendance() {
        return payPeriodAttendance;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getOvertimePay() {
        return overtimePay;
    }

    public Allowance getAllowance() {
        return allowance;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public Deduction getDeduction() {
        return deduction;
    }

    public BigDecimal getGovernmentContribution() {
        return governmentContribution;
    }

    public BigDecimal getWithHoldingTax() {
        return withHoldingTax;
    }   
    
    public BigDecimal getTotalDeduction() {
        return totalDeduction;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }
    
    
    
}
