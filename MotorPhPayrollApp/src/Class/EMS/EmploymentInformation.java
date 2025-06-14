/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import java.time.LocalDate;

/**
 * Represents employment information of an employee.
 * 
 */
public class EmploymentInformation extends Information {

    private int employmentID;  
    private Job job;         
    private Salary salary;     
    private String employmentType;  
    private String employmentStatus;
    private LocalDate dateHired;

    public EmploymentInformation() {
        super(0);
    }

    // Constructor for insert
    public EmploymentInformation(int employeeID, Job job, Salary salary, String employmentType,
                                 String employmentStatus, LocalDate dateHired) {
        super(employeeID);
        this.job = job;
        this.salary = salary;
        this.employmentType = employmentType;
        this.employmentStatus = employmentStatus;
        this.dateHired = dateHired;
    }

    // Constructor for retrieval
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