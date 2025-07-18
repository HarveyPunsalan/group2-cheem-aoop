/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import com.motorph.payrollprocessing.service.core.PayPeriodService;
import com.motorph.payrollprocessing.service.core.ServiceFactory;

import java.util.Arrays;

public class PayPeriodAttendance {
    private String payPeriodAttendanceID;
    private Employee employee;
    private PayPeriod payPeriod;
    private double totalWorkedHours;
    private double totalLateHours;
    private double totalApprovedOvertimeHours;
    private double totalPaidLeaveHours;
    private double payableHours;
    private String status;
    private Employee approvedBy;

    public PayPeriodAttendance() {}

    public PayPeriodAttendance(String payPeriodAttendanceID) {
        this.payPeriodAttendanceID = payPeriodAttendanceID;
    }

    public PayPeriodAttendance(String[] data) {
        if (data == null || data.length < 11) {
            throw new IllegalArgumentException("Invalid pay period attendance data: " + Arrays.toString(data));
        }

        try {
            this.payPeriodAttendanceID = data[0];

            // Create new Employee and set employeeId
            Employee emp = new Employee();
            emp.setEmployeeId(Integer.parseInt(data[1]));
            this.employee = emp;

            PayPeriodService payPeriodService = ServiceFactory.createPayPeriodServicewService();
            this.payPeriod = payPeriodService.searchByDateRange(data[2], data[3]).orElse(null);

            this.totalWorkedHours = Double.parseDouble(data[4]);
            this.totalLateHours = Double.parseDouble(data[5]);
            this.totalApprovedOvertimeHours = Double.parseDouble(data[6]);
            this.totalPaidLeaveHours = Double.parseDouble(data[7]);
            this.payableHours = Double.parseDouble(data[8]);
            this.status = data[9];

            // Handle approvedBy field (optional)
            if (data[10] != null && !data[10].trim().isEmpty()) {
                Employee approver = new Employee();
                approver.setEmployeeId(Integer.parseInt(data[10]));
                this.approvedBy = approver;
            } else {
                this.approvedBy = null;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing pay period attendance data: " + Arrays.toString(data), e);
        }
    }

    // Getters
    public String getPayPeriodAttendanceID() {
        return payPeriodAttendanceID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public PayPeriod getPayPeriod() {
        return payPeriod;
    }

    public double getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public double getTotalLateHours() {
        return totalLateHours;
    }

    public double getTotalApprovedOvertimeHours() {
        return totalApprovedOvertimeHours;
    }

    public double getTotalPaidLeaveHours() {
        return totalPaidLeaveHours;
    }

    public double getPayableHours() {
        return payableHours;
    }

    public String getStatus() {
        return status;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }
}
