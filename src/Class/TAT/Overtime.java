/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.IDManagement.IDManager;
import Class.IDManagement.Identifiable;
import Class.IDManagement.IDPrefix;

import java.time.LocalDate;
import java.time.LocalTime;

public class Overtime implements Identifiable {
    private String overtimeID;
    private String requestID;
    private String employeeID;
    private String dtrID;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double totalHours;
    private Double payableHours;
    private Boolean approved;
    private String notes;

    private static final LocalTime REGULAR_END_TIME = LocalTime.of(17, 0); // 5:00 PM

    // Default constructor
    public Overtime() {}

    // Constructor used by OvertimeService with 4 string fields
    public Overtime(String overtimeID, String requestID, String employeeID, String dtrID) {
        this.overtimeID = overtimeID;
        this.requestID = requestID;
        this.employeeID = employeeID;
        this.dtrID = dtrID;
    }

    // Constructor from DailyAttendance (if you use it)
    public Overtime(DailyAttendance attendance) {
        if (attendance.hasOvertime()) {
            this.overtimeID = IDManager.generateID(IDPrefix.OVERTIME);
            IDManager.saveIDCounters();
            this.employeeID = attendance.getEmployee().getEmployeeID();
            this.date = attendance.getDate();
            this.startTime = attendance.getTimeOut().isAfter(REGULAR_END_TIME)
                    ? REGULAR_END_TIME
                    : attendance.getTimeOut();
            this.endTime = attendance.getTimeOut();
            this.totalHours = attendance.getHoursOvertime();
            this.payableHours = null;
            this.approved = false;
            this.notes = null;
            this.requestID = null;
        } else {
            throw new IllegalArgumentException("No overtime recorded for this attendance.");
        }
    }

    // --- Getters ---
    @Override
    public String getID() {
        return overtimeID;
    }

    public String getOvertimeID() { return overtimeID; }
    public String getRequestID() { return requestID; }
    public String getEmployeeID() { return employeeID; }
    public String getDtrId() { return dtrID; }
    public LocalDate getDate() { return date; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public double getTotalHours() { return totalHours; }
    public Double getPayableHours() { return payableHours; }
    public Boolean isApproved() { return approved; }
    public String getNotes() { return notes; }

    // --- Setters ---
    public void setRequestID(String requestID) { this.requestID = requestID; }
    public void setPayableHours(Double payableHours) { this.payableHours = payableHours; }
    public void setApproved(Boolean approved) { this.approved = approved; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setDtrId(String dtrID) { this.dtrID = dtrID; }

    // ✅ New setters added:
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }

    // ✅ Missing Method Fixes
    public String getRequestId() {
        return this.requestID;
    }

    public void setIsApproved(boolean isApproved) {
        this.approved = isApproved;
    }
}
