/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class AttendanceCalculator {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * Calculates worked hours, late hours, and overtime for a single day.
     */
    public static double[] calculateDailyAttendance(LocalTime timeIn, LocalTime timeOut) {
        double hoursLate = 0.0, hoursWorked = 0.0, hoursOvertime = 0.0;
        double hoursBreak = 1.0;

        LocalTime startShift = LocalTime.of(8, 0);
        LocalTime endShift = LocalTime.of(17, 0);

        if (timeIn != null && timeOut != null) {
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
        }

        return new double[]{
            formatDecimal(hoursWorked),
            formatDecimal(hoursLate),
            formatDecimal(hoursOvertime)
        };
    }

    public static double calculateRegularWorkedHours(List<DailyAttendance> records) {
        double total = 0.0;
        for (DailyAttendance d : records) {
            Double hours = d.getHoursWorked();
            total += (hours != null) ? hours : 0.0;
        }
        return formatDecimal(total);
    }

    public static double calculateLateHours(List<DailyAttendance> records) {
        double total = 0.0;
        for (DailyAttendance d : records) {
            Double hours = d.getHoursLate();
            total += (hours != null) ? hours : 0.0;
        }
        return formatDecimal(total);
    }

    public static double calculateApprovedOverTimeHours(List<DailyAttendance> records) {
        double total = 0.0;
        for (DailyAttendance d : records) {
            Double hours = d.getHoursOvertime();
            total += (hours != null) ? hours : 0.0;
        }
        return formatDecimal(total);
    }

    /**
     * Calculates total payable hours:
     * (regular worked hours - late hours, min 0) + overtime
     */
    public static double calculatePayableHours(List<DailyAttendance> records) {
        double regular = calculateRegularWorkedHours(records);
        double late = calculateLateHours(records);
        double overtime = calculateApprovedOverTimeHours(records);
        double payable = Math.max(regular - late, 0.0) + overtime;
        return formatDecimal(payable);
    }

    private static double formatDecimal(double value) {
        return Double.parseDouble(decimalFormat.format(value));
    }
}

//test