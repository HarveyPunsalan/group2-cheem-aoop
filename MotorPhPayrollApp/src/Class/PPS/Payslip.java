/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;

/**
 *
 * @author 63909
 */
public class Payslip {
    
    private int        payslipId;      // payslip.payslip_id
    private int        payrollId;      // FK into payroll_record.id
    private int        employeeId;     // FK into employee.id
    private String     position;       // position
    private String     department;     // department
    private BigDecimal monthlyRate;    // DECIMAL(10,2)
    private BigDecimal dailyRate;      // DECIMAL(10,2)
    private int        daysWorked;     // INT
    private BigDecimal overtime;       // DECIMAL(10,2)
    private BigDecimal totalAllowance; // DECIMAL(10,2)
    private BigDecimal grossSalary;    // DECIMAL(10,2)
    private BigDecimal totalDeductions;// DECIMAL(10,2)
    private BigDecimal net;            // DECIMAL(10,2)
    
    private PayrollRecord payrollRecord;

    public Payslip() {}
    
    public Payslip(PayrollRecord payrollRecord) {
        this.payrollRecord = payrollRecord;
        // Optionally, copy relevant fields
        this.payrollId = payrollRecord.getId();
        this.employeeId = payrollRecord.getEmployeeId();
        this.grossSalary = payrollRecord.getGrossPay();
        this.totalDeductions = payrollRecord.getTotalDeductions();
        this.net = payrollRecord.getNetPay();
        // position, department, etc. could be filled via other lookups as needed
    }

    public Payslip(int payrollId,
                   int employeeId,
                   String position,
                   String department,
                   BigDecimal monthlyRate,
                   BigDecimal dailyRate,
                   int daysWorked,
                   BigDecimal overtime,
                   BigDecimal totalAllowance,
                   BigDecimal grossSalary,
                   BigDecimal totalDeductions,
                   BigDecimal net) {
        this.payrollId       = payrollId;
        this.employeeId      = employeeId;
        this.position        = position;
        this.department      = department;
        this.monthlyRate     = monthlyRate;
        this.dailyRate       = dailyRate;
        this.daysWorked      = daysWorked;
        this.overtime        = overtime;
        this.totalAllowance  = totalAllowance;
        this.grossSalary     = grossSalary;
        this.totalDeductions = totalDeductions;
        this.net             = net;
    }

    // — Getters & setters —

    public int getPayslipId() { return payslipId; }
    public void setPayslipId(int payslipId) { this.payslipId = payslipId; }
    
    public PayrollRecord getPayrollRecord() {return payrollRecord;}
    public void setPayrollRecord(PayrollRecord payrollRecord) {this.payrollRecord = payrollRecord;}
    public int getPayrollId() { return payrollId; }
    public void setPayrollId(int payrollId) { this.payrollId = payrollId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public BigDecimal getMonthlyRate() { return monthlyRate; }
    public void setMonthlyRate(BigDecimal monthlyRate) { this.monthlyRate = monthlyRate; }

    public BigDecimal getDailyRate() { return dailyRate; }
    public void setDailyRate(BigDecimal dailyRate) { this.dailyRate = dailyRate; }

    public int getDaysWorked() { return daysWorked; }
    public void setDaysWorked(int daysWorked) { this.daysWorked = daysWorked; }

    public BigDecimal getOvertime() { return overtime; }
    public void setOvertime(BigDecimal overtime) { this.overtime = overtime; }

    public BigDecimal getTotalAllowance() { return totalAllowance; }
    public void setTotalAllowance(BigDecimal totalAllowance) { this.totalAllowance = totalAllowance; }

    public BigDecimal getGrossSalary() { return grossSalary; }
    public void setGrossSalary(BigDecimal grossSalary) { this.grossSalary = grossSalary; }

    public BigDecimal getTotalDeductions() { return totalDeductions; }
    public void setTotalDeductions(BigDecimal totalDeductions) { this.totalDeductions = totalDeductions; }

    public BigDecimal getNet() { return net; }
    public void setNet(BigDecimal net) { this.net = net; }
    
}
