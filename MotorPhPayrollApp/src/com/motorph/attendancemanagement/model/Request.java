/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import Class.EntityManagement.EntityManager;
import Class.EntityManagement.EntityType;
import Class.IDManagement.IDManager;
import Class.IDManagement.Identifiable;
import com.motorph.common.util.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Request implements Identifiable {
    private String requestID;
    private String requestTypeID;
    private int employeeID;
    private LocalDate requestDate;
    private String reason;
    private String status;
    private int processedBy;
    private LocalDate processedDate;
    private String remarks;
    private LocalDateTime createdAt; // ✅ SQL-aligned

    public Request() {
    }

    public Request(String[] requestData) {
        if (requestData == null) {
            throw new IllegalArgumentException("Request data cannot be null.");
        }

        switch (requestData.length) {
            case 4 -> {
                // New request (without requestID, processed fields)
                EntityManager request = new EntityManager(EntityType.REQUEST);
                this.requestID = IDManager.generateID(request.getEntityType().getIdPrefix());
                IDManager.saveIDCounters();

                this.requestTypeID = requestData[0];
                this.employeeID = Parser.parseInteger(requestData[1], 0);
                this.requestDate = Parser.parseLocalDate(requestData[2], null);
                this.reason = requestData[3].isEmpty() ? null : requestData[3];

                this.status = RequestStatus.PENDING.toString();
                this.processedBy = 0;
                this.processedDate = null;
                this.remarks = null;
                this.createdAt = LocalDateTime.now(); // ✅ auto timestamp
            }
            case 10 -> {
                // Fully loaded from SQL
                this.requestID = requestData[0];
                this.requestTypeID = requestData[1];
                this.employeeID = Parser.parseInteger(requestData[2], 0);
                this.requestDate = Parser.parseLocalDate(requestData[3], null);
                this.reason = requestData[4].isEmpty() ? null : requestData[4];
                this.status = RequestStatus.valueOf(requestData[5]).toString();
                this.processedBy = Parser.parseInteger(requestData[6], -1);
                this.processedDate = Parser.parseLocalDate(requestData[7], null);
                this.remarks = requestData[8].isEmpty() ? null : requestData[8];
                this.createdAt = requestData[9].isEmpty() ? null : LocalDateTime.parse(requestData[9]);
            }
            default -> throw new IllegalArgumentException("Invalid input data format. Expected 4 or 10 parameters.");
        }
    }

    @Override
    public String getID() {
        return requestID;
    }

    public String getRequestTypeID() {
        return requestTypeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProcessedBy() {
        return processedBy;
    }

    public LocalDate getProcessedDate() {
        return processedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void approve(int processedBy) {
        if (processedBy <= 0) {
            throw new IllegalArgumentException("Processed by must be a valid employee ID.");
        }
        this.processedBy = processedBy;
        this.status = RequestStatus.APPROVED.toString();
        this.processedDate = LocalDate.now();
    }

    public void reject(int processedBy, String remarks) {
        if (processedBy <= 0) {
            throw new IllegalArgumentException("Processed by must be a valid employee ID.");
        }
        if (remarks == null || remarks.isEmpty()) {
            throw new IllegalArgumentException("Rejection remarks cannot be empty.");
        }

        this.processedBy = processedBy;
        this.status = RequestStatus.REJECTED.toString();
        this.remarks = remarks;
        this.processedDate = LocalDate.now();
    }
}
