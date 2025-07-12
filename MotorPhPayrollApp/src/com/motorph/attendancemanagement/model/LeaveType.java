/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import Class.IDManagement.Identifiable;

/**
 * Represents a type of leave with metadata including name, description,
 * whether it's paid, and allowed duration range.
 */
public class LeaveType implements Identifiable {

    private String leaveTypeID;
    private String leaveTypeName;
    private String description;
    private boolean isPaidLeave;
    private int minDaysAllowed;
    private int maxDaysAllowed;

    // 🔹 Default constructor
    public LeaveType() {}

    // 🔹 Full constructor
    public LeaveType(String leaveTypeID, String leaveTypeName, String description,
                     boolean isPaidLeave, int minDaysAllowed, int maxDaysAllowed) {
        this.leaveTypeID = leaveTypeID;
        this.leaveTypeName = leaveTypeName;
        this.description = description;
        this.isPaidLeave = isPaidLeave;
        this.minDaysAllowed = minDaysAllowed;
        this.maxDaysAllowed = maxDaysAllowed;
    }

    /**
     * Constructs a LeaveType object from a String array.
     * Expected format:
     * [0] leaveTypeID
     * [1] leaveTypeName
     * [2] description
     * [3] isPaidLeave (true/false)
     * [4] minDaysAllowed (int)
     * [5] maxDaysAllowed (int)
     *
     * @param leaveTypeData Array containing leave type data.
     */
    public LeaveType(String[] leaveTypeData) {
        if (leaveTypeData == null || leaveTypeData.length < 6) {
            throw new IllegalArgumentException("Insufficient leave type data provided.");
        }

        this.leaveTypeID = leaveTypeData[0];
        this.leaveTypeName = leaveTypeData[1];
        this.description = leaveTypeData[2];
        this.isPaidLeave = Boolean.parseBoolean(leaveTypeData[3]);
        this.minDaysAllowed = parseSafeInt(leaveTypeData[4]);
        this.maxDaysAllowed = parseSafeInt(leaveTypeData[5]);
    }

    // 🔹 Implemented from Identifiable interface
    @Override
    public String getID() {
        return leaveTypeID;
    }

    // 🔹 Getters
    public String getLeaveTypeID() {
        return leaveTypeID;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPaidLeave() {
        return isPaidLeave;
    }

    public int getMinDaysAllowed() {
        return minDaysAllowed;
    }

    public int getMaxDaysAllowed() {
        return maxDaysAllowed;
    }

    // 🔹 Safe integer parser
    private int parseSafeInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
