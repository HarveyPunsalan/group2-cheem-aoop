/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.model;

import com.motorph.attendancemanagement.service.AttendanceCalculator;
import com.motorph.employeemanagement.model.Employee;

import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class DailyAttendance {
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private String attendanceID;
    private Employee employee;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private double hoursWorked;
    private double hoursLate;
    private double hoursOvertime;

    public DailyAttendance() {}

    public DailyAttendance(String[] dailyAttendanceData) {
        if (dailyAttendanceData == null || dailyAttendanceData.length < 8) {
            throw new IllegalArgumentException("Invalid attendance data provided.");
        }

        try {
            this.attendanceID = dailyAttendanceData[0];
            this.employee = new Employee();
            this.date = LocalDate.parse(dailyAttendanceData[2], formatterDate);
            this.timeIn = LocalTime.parse(dailyAttendanceData[3], formatterTime);
            this.timeOut = LocalTime.parse(dailyAttendanceData[4], formatterTime);

            boolean needsUpdate = dailyAttendanceData[5].isEmpty() ||
                                  dailyAttendanceData[6].isEmpty() ||
                                  dailyAttendanceData[7].isEmpty();

            if (needsUpdate) {
                double[] calculatedValues = AttendanceCalculator.calculateDailyAttendance(timeIn, timeOut);
                this.hoursWorked = calculatedValues[0];
                this.hoursLate = calculatedValues[1];
                this.hoursOvertime = calculatedValues[2];
            } else {
                this.hoursLate = parseDoubleSafely(dailyAttendanceData[5]);
                this.hoursOvertime = parseDoubleSafely(dailyAttendanceData[6]);
                this.hoursWorked = parseDoubleSafely(dailyAttendanceData[7]);
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing attendance data: " + Arrays.toString(dailyAttendanceData), e);
        }
    }

    private double parseDoubleSafely(String value) {
        if (value == null || value.trim().isEmpty()) return 0.0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public double[] calculateDailyAttendance(LocalTime timeIn, LocalTime timeOut) {
        double hoursLate = 0.0;
        double hoursWorked = 0.0;
        double hoursOvertime = 0.0;
        double hoursBreak = 1.0;

        LocalTime startShift = LocalTime.of(8, 0);
        LocalTime endShift = LocalTime.of(17, 0);

        if (timeIn.isAfter(startShift.plusMinutes(10))) {
            hoursLate = Duration.between(startShift, timeIn).toMinutes() / 60.0;
        }

        LocalTime effectiveTimeOut = timeOut.isAfter(endShift) ? endShift : timeOut;
        hoursWorked = Duration.between(timeIn, effectiveTimeOut).toMinutes() / 60.0;

        if (timeIn.isBefore(LocalTime.NOON) && timeOut.isAfter(LocalTime.of(13, 0))) {
            hoursWorked -= hoursBreak;
        }

        if (timeOut.isAfter(endShift)) {
            hoursOvertime = Duration.between(endShift, timeOut).toMinutes() / 60.0;
        }

        hoursWorked = Double.parseDouble(decimalFormat.format(hoursWorked));
        hoursLate = Double.parseDouble(decimalFormat.format(hoursLate));
        hoursOvertime = Double.parseDouble(decimalFormat.format(hoursOvertime));

        return new double[]{hoursWorked, hoursLate, hoursOvertime};
    }

    public String getAttendanceID() {
        return attendanceID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public Double getHoursLate() {
        return hoursLate;
    }

    public Double getHoursOvertime() {
        return hoursOvertime;
    }

    public boolean hasOvertime() {
        return getHoursOvertime() > 0;
    }
}
