/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

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

    // Constructors
    public Request() {}

    public Request(int requestId, int employeeId, Date requestDate, String reason,
                   String requestStatus, Timestamp createdAt, int processedBy,
                   Date processedDate, String remarks) {
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

    // Constructor to accept String[] (if you're using array-based instantiation)
    public Request(String[] data) {
        if (data.length < 9) {
            throw new IllegalArgumentException("Incomplete data for Request.");
        }

        try {
            this.requestId = Integer.parseInt(data[0]);
            this.employeeId = Integer.parseInt(data[1]);
            this.requestDate = Date.valueOf(data[2]);
            this.reason = data[3];
            this.requestStatus = data[4];
            this.createdAt = Timestamp.valueOf(data[5]);
            this.processedBy = Integer.parseInt(data[6]);
            this.processedDate = Date.valueOf(data[7]);
            this.remarks = data[8];
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid data provided to Request constructor.", e);
        }
    }

    // Getters and Setters
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

    // Added convenience method to match external usage
    public String getStatus() {
        return requestStatus;
    }

    // Action methods
    public void approve(String approverId) {
        this.requestStatus = "APPROVED";
        this.processedBy = Integer.parseInt(approverId);
        this.processedDate = new Date(System.currentTimeMillis());
    }

    public void reject(String approverId, String rejectionReason) {
        this.requestStatus = "REJECTED";
        this.processedBy = Integer.parseInt(approverId);
        this.processedDate = new Date(System.currentTimeMillis());
        this.remarks = rejectionReason;
    }
}


// test
