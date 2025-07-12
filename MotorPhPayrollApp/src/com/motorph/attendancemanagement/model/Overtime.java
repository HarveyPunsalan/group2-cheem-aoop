/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import Class.IDManagement.Identifiable;
import com.motorph.common.util.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class Overtime implements Identifiable {
    private String overtimeID;
    private int employeeID;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double totalHours;
    private Double payableHours; // Nullable
    private boolean isApproved;
    private int requestId; // FK to request table
    private int dtrId;     // FK to daily_time_record table

    public Overtime() {}

    // CSV or SQL Constructor
    public Overtime(String[] overtimeData) {
        if (overtimeData == null) throw new IllegalArgumentException("Overtime data cannot be null.");

        switch (overtimeData.length) {
            case 6 -> {
                // CSV or simple data: [employeeID, date, startTime, endTime, totalHours, requestId]
                this.overtimeID = null;
                this.employeeID = Integer.parseInt(overtimeData[0]);
                this.date = Parser.parseLocalDate(overtimeData[1], null);
                this.startTime = Parser.parseLocalTime(overtimeData[2], null, "H:mm");
                this.endTime = Parser.parseLocalTime(overtimeData[3], null, "H:mm");
                this.totalHours = Parser.parseDouble(overtimeData[4], 0.0);
                this.requestId = Parser.parseInteger(overtimeData[5], 0);
                this.payableHours = null;
                this.isApproved = false;
                this.dtrId = 0; // default, since it's not included in this constructor
            }
            case 10 -> {
                // Full SQL record: [overtimeID, employeeID, date, startTime, endTime, totalHours, payableHours, isApproved, requestId, dtrId]
                this.overtimeID = overtimeData[0];
                this.employeeID = Integer.parseInt(overtimeData[1]);
                this.date = Parser.parseLocalDate(overtimeData[2], null);
                this.startTime = Parser.parseLocalTime(overtimeData[3], null, "H:mm");
                this.endTime = Parser.parseLocalTime(overtimeData[4], null, "H:mm");
                this.totalHours = Parser.parseDouble(overtimeData[5], 0.0);
                this.payableHours = Parser.parseDouble(overtimeData[6], null);
                this.isApproved = Boolean.parseBoolean(overtimeData[7]);
                this.requestId = Parser.parseInteger(overtimeData[8], 0);
                this.dtrId = Parser.parseInteger(overtimeData[9], 0);
            }
            default -> throw new IllegalArgumentException("Invalid input data format.");
        }
    }

    // ✅ Getters and Setters
    @Override
    public String getID() {
        return overtimeID;
    }

    public void setID(String id) {
        this.overtimeID = id;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public Double getPayableHours() {
        return payableHours;
    }

    public void setPayableHours(Double payableHours) {
        this.payableHours = payableHours;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getDtrId() {
        return dtrId;
    }

    public void setDtrId(int dtrId) {
        this.dtrId = dtrId;
    }
}
