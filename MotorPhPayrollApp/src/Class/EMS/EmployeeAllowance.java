/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents the allowance details for an employee.
 * 
 */
public class EmployeeAllowance extends Information {
    private int employeeAllowanceID;
    private Allowance allowance;
    private BigDecimal amount;
    private LocalDate effectiveDate;
    private LocalDate createdDate;
    private String allowanceFrequency;

    public EmployeeAllowance() {
        super(0); 
    }

    // Constructor for inserting new record
    public EmployeeAllowance(int employeeID, Allowance allowance, BigDecimal amount,
                             LocalDate effectiveDate, LocalDate createdDate, String allowanceFrequency) {
        super(employeeID);
        this.allowance = allowance;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
        this.createdDate = createdDate;
        this.allowanceFrequency = allowanceFrequency;
    }

    // Constructor with employeeAllowanceID (for fetching from DB)
    public EmployeeAllowance(int employeeAllowanceID, int employeeID, Allowance allowance, BigDecimal amount,
                             LocalDate effectiveDate, LocalDate createdDate, String allowanceFrequency) {
        super(employeeID);
        this.employeeAllowanceID = employeeAllowanceID;
        this.allowance = allowance;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
        this.createdDate = createdDate;
        this.allowanceFrequency = allowanceFrequency;
    }

    // Getters and setters

    public int getEmployeeAllowanceID() {
        return employeeAllowanceID;
    }

    public void setEmployeeAllowanceID(int employeeAllowanceID) {
        this.employeeAllowanceID = employeeAllowanceID;
    }

    public Allowance getAllowance() {
        return allowance;
    }

    public void setAllowance(Allowance allowance) {
        this.allowance = allowance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getAllowanceFrequency() {
        return allowanceFrequency;
    }

    public void setAllowanceFrequency(String allowanceFrequency) {
        this.allowanceFrequency = allowanceFrequency;
    }

    @Override
    public String toString() {
        return String.format("Employee Allowance [ID: %d, Employee ID: %d, Allowance: %s, Amount: %s, Effective: %s, Created: %s, Frequency: %s]",
                employeeAllowanceID, employeeID,
                (allowance != null ? allowance.getAllowanceName() : "N/A"),
                (amount != null ? amount.toPlainString() : "N/A"),
                (effectiveDate != null ? effectiveDate.toString() : "N/A"),
                (createdDate != null ? createdDate.toString() : "N/A"),
                (allowanceFrequency != null ? allowanceFrequency : "N/A"));
    }
}