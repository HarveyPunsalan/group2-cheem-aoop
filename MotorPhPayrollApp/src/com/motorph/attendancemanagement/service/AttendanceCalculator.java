/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.DailyAttendance;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class AttendanceCalculator {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static double[] calculateDailyAttendance(LocalTime timeIn, LocalTime timeOut) {
        double hoursLate = 0.0;
        double hoursWorked = 0.0;
        double hoursOvertime = 0.0;
        double hoursBreak = 1.0;

        LocalTime shiftStart = LocalTime.of(8, 0);
        LocalTime shiftEnd = LocalTime.of(17, 0);

        // Late if after 8:10 AM
        if (timeIn.isAfter(shiftStart.plusMinutes(10))) {
            hoursLate = Duration.between(shiftStart, timeIn).toMinutes() / 60.0;
        }

        // Cap timeOut at shift end for regular hours
        LocalTime effectiveTimeOut = timeOut.isAfter(shiftEnd) ? shiftEnd : timeOut;
        hoursWorked = Duration.between(timeIn, effectiveTimeOut).toMinutes() / 60.0;

        // Deduct 1-hour lunch if span includes 12-1 PM
        if (timeIn.isBefore(LocalTime.NOON) && timeOut.isAfter(LocalTime.of(13, 0))) {
            hoursWorked -= hoursBreak;
        }

        // Add overtime if timeOut is after 5 PM
        if (timeOut.isAfter(shiftEnd)) {
            hoursOvertime = Duration.between(shiftEnd, timeOut).toMinutes() / 60.0;
        }

        // Round to 2 decimal places
        return new double[] {
            roundToTwoDecimalPlaces(hoursWorked),
            roundToTwoDecimalPlaces(hoursLate),
            roundToTwoDecimalPlaces(hoursOvertime)
        };
    }

    public static double calculateRegularWorkedHours(List<DailyAttendance> records) {
        double total = 0.0;
        for (DailyAttendance record : records) {
            total += record.getHoursWorked();
        }
        return roundToTwoDecimalPlaces(total);
    }

    public static double calculateLateHours(List<DailyAttendance> records) {
        double total = 0.0;
        for (DailyAttendance record : records) {
            total += record.getHoursLate();
        }
        return roundToTwoDecimalPlaces(total);
    }

    public static double calculateApprovedOverTimeHours(List<DailyAttendance> records) {
        double total = 0.0;
        for (DailyAttendance record : records) {
            total += record.getHoursOvertime();
        }
        return roundToTwoDecimalPlaces(total);
    }

    public static double calculatePayableHours(List<DailyAttendance> records) {
        double regular = calculateRegularWorkedHours(records);
        double late = calculateLateHours(records);
        double overtime = calculateApprovedOverTimeHours(records);

        double adjustedRegular = Math.max(regular - late, 0.0);
        double total = adjustedRegular + overtime;

        return roundToTwoDecimalPlaces(total);
    }
    
        public static double calculateTotalHours(List<DailyAttendance> records) {
        double total = 0.0;
        for (DailyAttendance record : records) {
            total += record.getHoursWorked();       // regular
            total += record.getHoursOvertime();     // overtime
        }
        return roundToTwoDecimalPlaces(total);
    }

    private static double roundToTwoDecimalPlaces(double value) {
        return Double.parseDouble(decimalFormat.format(value));
    }
}
