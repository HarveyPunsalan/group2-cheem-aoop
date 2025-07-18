/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import com.motorph.employeemanagement.model.Employee;
import java.time.LocalDate;
import java.time.LocalTime;
import java.text.DecimalFormat;

public class DailyAttendance {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    private String attendanceId;
    private Employee employee;
    private int employeeId;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private double hoursWorked;
    private double hoursLate;
    private double hoursOvertime;
    private boolean approvedOvertime;

    public DailyAttendance() {
        this.approvedOvertime = false;
        this.hoursWorked = 0.0;
        this.hoursLate = 0.0;
        this.hoursOvertime = 0.0;
    }

    public DailyAttendance(String attendanceId, Employee employee, LocalDate date,
                           LocalTime timeIn, LocalTime timeOut,
                           double hoursLate, double hoursOvertime, double hoursWorked) {
        this.attendanceId = attendanceId;
        this.employee = employee;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.hoursLate = round(hoursLate);
        this.hoursOvertime = round(hoursOvertime);
        this.hoursWorked = round(hoursWorked);
        this.approvedOvertime = false;
    }

    public DailyAttendance(String attendanceId, int employeeId, LocalDate date,
                           LocalTime timeIn, LocalTime timeOut,
                           double hoursLate, double hoursOvertime, double hoursWorked) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.hoursLate = round(hoursLate);
        this.hoursOvertime = round(hoursOvertime);
        this.hoursWorked = round(hoursWorked);
        this.approvedOvertime = false;
    }

    private double round(double value) {
        return Double.parseDouble(DECIMAL_FORMAT.format(value));
    }

    public boolean isApprovedOvertime() {
        return approvedOvertime;
    }

    public void setApprovedOvertime(boolean approvedOvertime) {
        this.approvedOvertime = approvedOvertime;
    }

    public String getAttendanceId() { return attendanceId; }
    public void setAttendanceId(String attendanceId) { this.attendanceId = attendanceId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTimeIn() { return timeIn; }
    public void setTimeIn(LocalTime timeIn) { this.timeIn = timeIn; }

    public LocalTime getTimeOut() { return timeOut; }
    public void setTimeOut(LocalTime timeOut) { this.timeOut = timeOut; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = round(hoursWorked); }

    public double getHoursLate() { return hoursLate; }
    public void setHoursLate(double hoursLate) { this.hoursLate = round(hoursLate); }

    public double getHoursOvertime() { return hoursOvertime; }
    public void setHoursOvertime(double hoursOvertime) { this.hoursOvertime = round(hoursOvertime); }
}
