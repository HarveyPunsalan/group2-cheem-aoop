/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

public class Overtime extends Request {

    private int overtimeId;
    private int dtrId;
    private boolean isApproved;

    // Constructors
    public Overtime() {
        super();
    }

    public Overtime(int overtimeId, int requestId, int employeeId, java.sql.Date requestDate, String reason,
                    String requestStatus, java.sql.Timestamp createdAt, int processedBy, java.sql.Date processedDate,
                    String remarks, int dtrId, boolean isApproved) {
        super(requestId, employeeId, requestDate, reason, requestStatus, createdAt, processedBy, processedDate, remarks);
        this.overtimeId = overtimeId;
        this.dtrId = dtrId;
        this.isApproved = isApproved;
    }

    // Getters and Setters
    public int getOvertimeId() {
        return overtimeId;
    }

    public void setOvertimeId(int overtimeId) {
        this.overtimeId = overtimeId;
    }

    public int getDtrId() {
        return dtrId;
    }

    public void setDtrId(int dtrId) {
        this.dtrId = dtrId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    @Override
    public String getID() {
        return String.valueOf(overtimeId);
    }

    @Override
    public String toString() {
        return "Overtime{" +
                "overtimeId=" + overtimeId +
                ", dtrId=" + dtrId +
                ", isApproved=" + isApproved +
                ", requestID=" + getRequestId() +
                ", employeeID=" + getEmployeeId() +
                '}';
    }
}
