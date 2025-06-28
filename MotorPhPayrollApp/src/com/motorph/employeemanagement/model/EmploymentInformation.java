/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents the employment-related details of an employee.
 * 
 */
public class EmploymentInformation extends Information {

    private int employmentID;  // Unique ID for the employment record (from DB)
    private Job job; // Job information (title, department, etc.)        
    private Salary salary;   // Salary details  
    private String employmentType;  // e.g., "Regular", "Contractual"
    private String employmentStatus; // e.g., "Active", "Terminated", "On Leave"
    private LocalDate dateHired; // Date the employee was hired

    //Default constructor
    public EmploymentInformation() {
        super(0);
    }

    /**
     * Constructor used when inserting a new employment record (employmentID is auto-generated).
     *
     * @param employeeID        the employee's ID
     * @param job               the employee's job details
     * @param salary            the employee's salary details
     * @param employmentType    the nature of employment (e.g., "Regular")
     * @param employmentStatus  current status (e.g., "Active")
     * @param dateHired         the date the employee was hired
     */
    public EmploymentInformation(int employeeID, Job job, Salary salary, String employmentType,
                                 String employmentStatus, LocalDate dateHired) {
        super(employeeID);
        this.job = job;
        this.salary = salary;
        this.employmentType = employmentType;
        this.employmentStatus = employmentStatus;
        this.dateHired = dateHired;
    }

    /**
     * Constructor used when retrieving an existing employment record from the database.
     *
     * @param employmentID      the unique record ID
     * @param employeeID        the employee's ID
     * @param job               job information
     * @param salary            salary information
     * @param employmentType    the nature of employment
     * @param employmentStatus  current employment status
     * @param dateHired         hire date
     */
    public EmploymentInformation(int employmentID, int employeeID, Job job, Salary salary,
                                 String employmentType, String employmentStatus, LocalDate dateHired) {
        super(employeeID);
        this.employmentID = employmentID;
        this.job = job;
        this.salary = salary;
        this.employmentType = employmentType;
        this.employmentStatus = employmentStatus;
        this.dateHired = dateHired;
    }
    
    public List<Object> toInsertParams() {
        return List.of(
            employeeID,
//            jobTitle.getJobID(),
            salary.getSalaryID(),
            employmentType,
            employmentStatus,
            java.sql.Date.valueOf(dateHired)        
        );
    }

    public List<Object> toUpdateParams() {
        return List.of(
            employeeID,
//            jobTitle.getJobID(),
            salary.getSalaryID(),
            employmentType,
            employmentStatus,
            java.sql.Date.valueOf(dateHired),    
            employeeID
        );
    }    
    
    /**
     * Retrieves key employment information as an array of strings.
     *
     * @return an array containing the employee ID, job title, employment type, employment status,
     * and date hired.
     */
    @Override
    public String[] getInformation() {
        // Return an array with selected employment details.
        return new String[] {String.valueOf(employeeID),
//                            jobTitle.getJobName(),
                            employmentType,
                            employmentStatus,
                            dateHired.toString(),
//                            immediateSupervisor,
//                            basicSalary,
//                            riceSubsidy,
//                            phoneAllowance,
//                            clothingAllowance,
//                            grossSemiMonthlyRate,
//                            hourlyRate,
                            };
    }

    // Getters and setters
    public int getEmploymentID() {
        return employmentID;
    }

    public void setEmploymentID(int employmentID) {
        this.employmentID = employmentID;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }

    /**
     * @return a string representation of this employment information
     */
    @Override
    public String toString() {
        return String.format("Employment Info [Employment ID: %d, Employee ID: %d, Job: %s, Salary: %s, Type: %s, Status: %s, Hired: %s]",
                employmentID, employeeID,
                (job != null ? job.getJobTitle() : "N/A"),
                (salary != null ? String.valueOf(salary.getBasicSalary()) : "N/A"),
                employmentType, employmentStatus,
                (dateHired != null ? dateHired.toString() : "N/A"));
    }
}