/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import java.time.LocalDate;

/**
 * Represents the supervisor assignment of an employee.
 */
public class SupervisorAssignment extends Information {
    private int supervisorAssignmentID; 
    private int supervisorID;          
    private LocalDate startDate;
    private LocalDate endDate;

    public SupervisorAssignment() {
        super(0);
    }

    // Constructor for insertion
    public SupervisorAssignment(int employeeID, int supervisorID, LocalDate startDate, LocalDate endDate) {
        super(employeeID);
        this.supervisorID = supervisorID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Constructor for retrieval
    public SupervisorAssignment(int supervisorAssignmentID, int employeeID, int supervisorID,
                                LocalDate startDate, LocalDate endDate) {
        super(employeeID);
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

    @Override
    public String toString() {
        return String.format("Supervisor Assignment [ID: %d, Employee ID: %d, Supervisor ID: %d, Start: %s, End: %s]",
                supervisorAssignmentID, employeeID, supervisorID,
                (startDate != null ? startDate.toString() : "N/A"),
                (endDate != null ? endDate.toString() : "N/A"));
    }
}