/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import java.time.LocalDate;

public class Leave {

    private int leaveRequestId;
    private int employeeId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalDays;

    public Leave() {}

    public Leave(int leaveRequestId, int employeeId, String leaveType, LocalDate startDate,
                 LocalDate endDate, double totalDays) {
        this.leaveRequestId = leaveRequestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
    }

    public int getLeaveRequestId() {
        return leaveRequestId;
    }

    public void setLeaveRequestId(int leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
    public String toString() {
        return "Leave{" +
                "leaveRequestId=" + leaveRequestId +
                ", employeeId=" + employeeId +
                ", leaveType='" + leaveType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalDays=" + totalDays +
                '}';
    }
}

