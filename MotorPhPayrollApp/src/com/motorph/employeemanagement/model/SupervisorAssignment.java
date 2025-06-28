/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.model;

import java.time.LocalDate;

/**
 * Represents the supervisor assignment of an employee.
 */
public class SupervisorAssignment {
    private int employeeID;
    private int supervisorAssignmentID; // Unique identifier for the assignment record
    private int supervisorID;  // employee ID of the assigned supervisor        
    private LocalDate startDate; // Date the supervision began
    private LocalDate endDate; // Date the supervision ended (nullable if ongoing)

    /**
     * Constructor used for inserting new supervisor assignment records.
     *
     * @param employeeID   the ID of the employee being supervised
     * @param supervisorID the employee ID of the supervisor
     * @param startDate    the date the assignment started
     * @param endDate      the date the assignment ended (nullable)
     */
    public SupervisorAssignment(int employeeID, int supervisorID, LocalDate startDate, LocalDate endDate) {
        this.employeeID = employeeID;
        this.supervisorID = supervisorID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor used for retrieving existing supervisor assignments from the database.
     *
     * @param supervisorAssignmentID unique ID of the assignment
     * @param employeeID             the ID of the employee being supervised
     * @param supervisorID           the employee ID of the supervisor
     * @param startDate              the date the assignment started
     * @param endDate                the date the assignment ended (nullable)
     */
    public SupervisorAssignment(int supervisorAssignmentID, int employeeID, int supervisorID,
                                LocalDate startDate, LocalDate endDate) {
        this.employeeID = employeeID;
        this.supervisorAssignmentID = supervisorAssignmentID;
        this.supervisorID = supervisorID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    public int getSupervisorAssignmentID() {
        return supervisorAssignmentID;
    }

    public void setSupervisorAssignmentID(int supervisorAssignmentID) {
        this.supervisorAssignmentID = supervisorAssignmentID;
    }

    public int getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(int supervisorID) {
        this.supervisorID = supervisorID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the supervisor assignment.
     */
    @Override
    public String toString() {
        return String.format("Supervisor Assignment [ID: %d, Employee ID: %d, Supervisor ID: %d, Start: %s, End: %s]",
                supervisorAssignmentID, employeeID, supervisorID,
                (startDate != null ? startDate.toString() : "N/A"),
                (endDate != null ? endDate.toString() : "N/A"));
    }
}