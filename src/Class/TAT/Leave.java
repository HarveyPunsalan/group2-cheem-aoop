/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.IDManagement.IDManager;
import Class.IDManagement.Identifiable;
import Class.IDManagement.IDPrefix;
import Class.Parser;
import java.time.LocalDate;

/**
 * Represents a leave request for an employee.
 * Matches the SQL table: employee_leave
 */
public class Leave implements Identifiable {
    private String leaveID;         // leave_id (PK)
    private String employeeID;      // employee_id
    private String leaveType;       // leave_type
    private LocalDate startDate;    // start_date
    private LocalDate endDate;      // end_date
    private double totalDays;       // total_days (DECIMAL)
    private String requestID;       // request_id (FK)

    private Boolean approved;       // ✅ New field for approval status

    // Default constructor
    public Leave() {}

    /**
     * Full constructor (SQL use)
     */
    public Leave(String leaveID, String employeeID, String leaveType,
                 LocalDate startDate, LocalDate endDate, double totalDays, String requestID) {
        this.leaveID = leaveID;
        this.employeeID = employeeID;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
        this.requestID = requestID;
        this.approved = false; // default
    }

    /**
     * Constructor from String[] (e.g. SQL result or CSV)
     * Expected order: [leaveID, employeeID, leaveType, startDate, endDate, totalDays, requestID]
     */
    public Leave(String[] data) {
        if (data == null || data.length != 7)
            throw new IllegalArgumentException("Expected 7 fields for Leave.");

        this.leaveID = data[0];
        this.employeeID = data[1];
        this.leaveType = data[2];
        this.startDate = Parser.parseLocalDate(data[3], null);
        this.endDate = Parser.parseLocalDate(data[4], null);
        this.totalDays = Parser.parseDouble(data[5], 0.0);
        this.requestID = data[6];
        this.approved = false; // default
    }

    // --- Getters ---
    @Override
    public String getID() { return leaveID; }

    public String getEmployeeID() { return employeeID; }

    public String getLeaveType() { return leaveType; }

    public LocalDate getStartDate() { return startDate; }

    public LocalDate getEndDate() { return endDate; }

    public double getTotalDays() { return totalDays; }

    public String getRequestID() { return requestID; }

    public Boolean isApproved() { return approved; }

    // --- Setters for new method ---
    public String getRequestId() {
        return this.requestID;
    }

    public void setIsApproved(boolean isApproved) {
        this.approved = isApproved;
    }

    @Override
    public String toString() {
        return String.format("Leave[id=%s, empID=%s, type=%s, %s to %s, days=%.2f, request=%s]",
                leaveID, employeeID, leaveType, startDate, endDate, totalDays, requestID);
    }
}
