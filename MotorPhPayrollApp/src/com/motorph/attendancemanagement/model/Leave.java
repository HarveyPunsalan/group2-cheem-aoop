/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import java.time.LocalDate;
import java.sql.Date;
import java.sql.Timestamp;

public class Leave extends Request {

    private String leaveID;         
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalDays;

    public Leave() {
        super();
    }

    public Leave(String leaveID, String leaveType, LocalDate startDate, LocalDate endDate, double totalDays,
                 int requestId, int employeeId, Date requestDate, String reason, String requestStatus,
                 Timestamp createdAt, int processedBy, Date processedDate, String remarks) {
        super(requestId, employeeId, requestDate, reason, requestStatus, createdAt, processedBy, processedDate, remarks);
        this.leaveID = leaveID;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
    }

    public String getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(String leaveID) {
        this.leaveID = leaveID;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
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

    public double getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(double totalDays) {
        this.totalDays = totalDays;
    }

    @Override
    public String getID() {
        return leaveID;
    }

    // Add alias methods to match what's being called in RequestService
    public void setEmployeeID(int employeeId) {
        setEmployeeId(employeeId); // inherited from Request
    }

    public int getEmployeeID() {
        return getEmployeeId(); // inherited from Request
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveID='" + leaveID + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalDays=" + totalDays +
                ", requestID=" + getRequestId() +
                ", employeeID=" + getEmployeeId() +
                '}';
    }
}
