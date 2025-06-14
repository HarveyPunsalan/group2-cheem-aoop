/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import java.math.BigDecimal;

/**
 * Represents the salary details of an employee.
 * 
 */
public class Salary {

    private int salaryID;                    
    private int salaryGrade;                 
    private BigDecimal basicSalary;          
    private BigDecimal grossSemiMonthlyRate; 
    private BigDecimal hourlyRate;           

    public Salary() {
    }

    // Constructor for insertion
    public Salary(int salaryGrade, BigDecimal basicSalary, BigDecimal grossSemiMonthlyRate, BigDecimal hourlyRate) {
        this.salaryGrade = salaryGrade;
        this.basicSalary = basicSalary;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }

    // Constructor for retrieval
    public Salary(int salaryID, int salaryGrade, BigDecimal basicSalary, BigDecimal grossSemiMonthlyRate, BigDecimal hourlyRate) {
        this.salaryID = salaryID;
        this.salaryGrade = salaryGrade;
        this.basicSalary = basicSalary;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }

    // Getters and setters
    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public int getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(int salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public void setGrossSemiMonthlyRate(BigDecimal grossSemiMonthlyRate) {
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return String.format("Salary [ID: %d, Grade: %d, Basic: %.2f, Semi-Monthly: %.2f, Hourly: %.2f]",
                salaryID,
                salaryGrade,
                basicSalary != null ? basicSalary.doubleValue() : 0.0,
                grossSemiMonthlyRate != null ? grossSemiMonthlyRate.doubleValue() : 0.0,
                hourlyRate != null ? hourlyRate.doubleValue() : 0.0);
    }
}