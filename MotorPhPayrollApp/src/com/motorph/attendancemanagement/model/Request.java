/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Request {
    private int requestId;
    private int employeeId;
    private Date requestDate;
    private String reason;
    private String requestStatus;
    private Timestamp createdAt;
    private int processedBy;
    private Date processedDate;
    private String remarks;

    // No-arg constructor
    public Request() {}

    // Full constructor
    public Request(int requestId, int employeeId, Date requestDate, String reason, String requestStatus,
                   Timestamp createdAt, int processedBy, Date processedDate, String remarks) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.reason = reason;
        this.requestStatus = requestStatus;
        this.createdAt = createdAt;
        this.processedBy = processedBy;
        this.processedDate = processedDate;
        this.remarks = remarks;
    }

    // ✅ Public getters and setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(int processedBy) {
        this.processedBy = processedBy;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // For polymorphic use
    public String getID() {
        return String.valueOf(requestId);
    }
}
