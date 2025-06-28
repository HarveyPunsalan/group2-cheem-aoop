/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents the allowance details assigned to a specific employee.
 *
 * <p>This class extends {Information}, linking the record to an employee via employee ID.
 * Each record represents a single type of allowance (e.g., Phone, Rice) with an amount, frequency,
 * and effective/creation dates. It supports both insertion and retrieval from a data source.</p>
 * 
 */
public class EmployeeAllowance {
    private int employeeID;
    private int employeeAllowanceID;
    private Allowance allowance;
    private BigDecimal amount;
    private LocalDate effectiveDate;
    private LocalDate createdDate;
    private String allowanceFrequency;

    /**
     * Constructor used for inserting a new allowance record.
     *
     * @param employeeID          the ID of the employee
     * @param allowance           the type of allowance
     * @param amount              the monetary amount of the allowance
     * @param effectiveDate       the allowance effective date
     * @param createdDate         the date the allowance was recorded
     * @param allowanceFrequency  how often the allowance is given
     */
    public EmployeeAllowance(int employeeID, Allowance allowance, BigDecimal amount,
                             LocalDate effectiveDate, LocalDate createdDate, String allowanceFrequency) {
        this.employeeID = employeeID;
        this.allowance = allowance;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
        this.createdDate = createdDate;
        this.allowanceFrequency = allowanceFrequency;
    }

    /**
     * Constructor used for retrieving a record from the database.
     *
     * @param employeeAllowanceID the unique ID of the allowance record
     * @param employeeID          the ID of the employee
     * @param allowance           the type of allowance
     * @param amount              the monetary amount of the allowance
     * @param effectiveDate       the allowance effective date
     * @param createdDate         the date the allowance was recorded
     * @param allowanceFrequency  how often the allowance is given
     */
    public EmployeeAllowance(int employeeAllowanceID, int employeeID, Allowance allowance, BigDecimal amount,
                             LocalDate effectiveDate, LocalDate createdDate, String allowanceFrequency) {
        this.employeeID = employeeID;
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

    /**
     * @return a string representation of the allowance record.
     */
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