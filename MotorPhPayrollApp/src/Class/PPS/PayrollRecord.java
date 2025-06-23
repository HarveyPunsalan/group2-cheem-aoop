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
public class PayrollRecord {
    private int        id;              // payroll_record.id
    private int        employeeId;      // FK to employee.id
    private int        payPeriodId;     // FK to pay_period.pay_period_id
    private BigDecimal hoursWorked;     // DECIMAL(10,2)
    private BigDecimal basicPay;        // DECIMAL(10,2)
    private BigDecimal grossPay;        // DECIMAL(10,2)
    private BigDecimal sss;
    private BigDecimal philHealth;
    private BigDecimal pagIbig;
    private BigDecimal withholdingTax;
    private BigDecimal totalDeductions; // DECIMAL(10,2)
    private BigDecimal overtimeHours;   // DECIMAL(10,2)
    private BigDecimal overtimePay;     // DECIMAL(10,2)
    private BigDecimal netPay;          // DECIMAL(10,2)

    public PayrollRecord() {}

    public PayrollRecord(int employeeId,
                         int payPeriodId,
                         BigDecimal hoursWorked,
                         BigDecimal basicPay,
                         BigDecimal grossPay,
                         BigDecimal totalDeductions,
                         BigDecimal overtimeHours,
                         BigDecimal overtimePay,
                         BigDecimal netPay) {
        this.employeeId      = employeeId;
        this.payPeriodId     = payPeriodId;
        this.hoursWorked     = hoursWorked;
        this.basicPay        = basicPay;
        this.grossPay        = grossPay;
        this.totalDeductions = totalDeductions;
        this.overtimeHours   = overtimeHours;
        this.overtimePay     = overtimePay;
        this.netPay          = netPay;
    }

    // — Getters & setters for all fields — 

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public int getPayPeriodId() { return payPeriodId; }
    public void setPayPeriodId(int payPeriodId) { this.payPeriodId = payPeriodId; }

    public BigDecimal getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(BigDecimal hoursWorked) { this.hoursWorked = hoursWorked; }

    public BigDecimal getBasicPay() { return basicPay; }
    public void setBasicPay(BigDecimal basicPay) { this.basicPay = basicPay; }

    public BigDecimal getGrossPay() { return grossPay; }
    public void setGrossPay(BigDecimal grossPay) { this.grossPay = grossPay; }
    
    public BigDecimal getSss() { return sss; }
    public void setSss(BigDecimal sss) { this.sss = sss; }

    public BigDecimal getPhilHealth() { return philHealth; }
    public void setPhilHealth(BigDecimal philHealth) { this.philHealth = philHealth; }

    public BigDecimal getPagIbig() { return pagIbig; }
    public void setPagIbig(BigDecimal pagIbig) { this.pagIbig = pagIbig; }

    public BigDecimal getWithholdingTax() { return withholdingTax; }
    public void setWithholdingTax(BigDecimal withholdingTax) { this.withholdingTax = withholdingTax; }

    public BigDecimal getTotalDeductions() { return totalDeductions; }
    public void setTotalDeductions(BigDecimal totalDeductions) { this.totalDeductions = totalDeductions; }

    public BigDecimal getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(BigDecimal overtimeHours) { this.overtimeHours = overtimeHours; }

    public BigDecimal getOvertimePay() { return overtimePay; }
    public void setOvertimePay(BigDecimal overtimePay) { this.overtimePay = overtimePay; }

    public BigDecimal getNetPay() { return netPay; }
    public void setNetPay(BigDecimal netPay) { this.netPay = netPay; }
    
}
