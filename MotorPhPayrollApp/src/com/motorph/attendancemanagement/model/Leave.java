/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import Class.IDManagement.Identifiable;
import com.motorph.common.util.Parser;

import java.time.LocalDate;

public class Leave implements Identifiable {

    private String leaveID;
    private int employeeID;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalDays;
    private int requestId; // Foreign key to the request table

    public Leave() {}

    // Constructor from raw String[] data (CSV or SQL)
    public Leave(String[] data) {
        if (data == null) throw new IllegalArgumentException("Leave data cannot be null.");

        switch (data.length) {
            case 6 -> {
                // SQL data: [leave_id, employee_id, leave_type, start_date, end_date, total_days]
                this.leaveID = data[0];
                this.employeeID = Integer.parseInt(data[1]);
                this.leaveType = data[2];
                this.startDate = Parser.parseLocalDate(data[3], null);
                this.endDate = Parser.parseLocalDate(data[4], null);
                this.totalDays = Parser.parseDouble(data[5], 0.0);
                this.requestId = 0;
            }
            case 7 -> {
                // SQL data with request ID: [leave_id, employee_id, leave_type, start_date, end_date, total_days, request_id]
                this.leaveID = data[0];
                this.employeeID = Integer.parseInt(data[1]);
                this.leaveType = data[2];
                this.startDate = Parser.parseLocalDate(data[3], null);
                this.endDate = Parser.parseLocalDate(data[4], null);
                this.totalDays = Parser.parseDouble(data[5], 0.0);
                this.requestId = Parser.parseInteger(data[6], 0);
            }
            default -> throw new IllegalArgumentException("Invalid input data format.");
        }
    }

    // ✅ Getters and Setters
    @Override
    public String getID() {
        return leaveID;
    }

    public void setLeaveID(String leaveID) {
        this.leaveID = leaveID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
