/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.EMS.Employee;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class DailyAttendance {
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private String dtrID;
    private Employee employee;
    private LocalDate date;
    private LocalTime login;
    private LocalTime logout;
    private Double workedHours;
    private Double lateHours;
    private Double overtimeHours;

    public DailyAttendance() {}

    public DailyAttendance(String dtrID, String employeeID, LocalDate date,
                           LocalTime login, LocalTime logout,
                           double lateHours, double overtimeHours, double workedHours) {
        this.dtrID = dtrID;
        this.employee = new Employee(employeeID);
        this.date = date;
        this.login = login;
        this.logout = logout;
        this.lateHours = lateHours;
        this.overtimeHours = overtimeHours;
        this.workedHours = workedHours;
    }

    public DailyAttendance(String[] data) {
        if (data == null || data.length < 8)
            throw new IllegalArgumentException("Invalid attendance data.");

        try {
            this.dtrID = data[0];
            this.employee = new Employee(data[1]);
            this.date = LocalDate.parse(data[2], formatterDate);
            this.login = LocalTime.parse(data[3], formatterTime);
            this.logout = LocalTime.parse(data[4], formatterTime);

            boolean needsUpdate = data[5].isEmpty() || data[6].isEmpty() || data[7].isEmpty();

            if (needsUpdate) {
                double[] calculated = calculateDailyAttendance(login, logout);
                this.workedHours = calculated[0];
                this.lateHours = calculated[1];
                this.overtimeHours = calculated[2];
            } else {
                this.lateHours = Double.parseDouble(data[5]);
                this.overtimeHours = Double.parseDouble(data[6]);
                this.workedHours = Double.parseDouble(data[7]);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing attendance data: " + Arrays.toString(data), e);
        }
    }

    public double[] calculateDailyAttendance(LocalTime timeIn, LocalTime timeOut) {
        double hoursLate = 0.0, hoursWorked = 0.0, hoursOvertime = 0.0;
        double hoursBreak = 1.0;

        LocalTime shiftStart = LocalTime.of(8, 0);
        LocalTime shiftEnd = LocalTime.of(17, 0);

        if (timeIn.isAfter(shiftStart.plusMinutes(10))) {
            hoursLate = Duration.between(shiftStart, timeIn).toMinutes() / 60.0;
        }

        LocalTime effectiveOut = timeOut.isAfter(shiftEnd) ? shiftEnd : timeOut;
        hoursWorked = Duration.between(timeIn, effectiveOut).toMinutes() / 60.0;

        if (timeIn.isBefore(LocalTime.NOON) && timeOut.isAfter(LocalTime.of(13, 0))) {
            hoursWorked -= hoursBreak;
        }

        if (timeOut.isAfter(shiftEnd)) {
            hoursOvertime = Duration.between(shiftEnd, timeOut).toMinutes() / 60.0;
        }

        return new double[] {
            Double.parseDouble(decimalFormat.format(hoursWorked)),
            Double.parseDouble(decimalFormat.format(hoursLate)),
            Double.parseDouble(decimalFormat.format(hoursOvertime))
        };
    }

    // Getters
    public String getDtrID() { return dtrID; }
    public Employee getEmployee() { return employee; }
    public String getEmployeeID() { return employee != null ? employee.getEmployeeID() : null; }
    public LocalDate getDate() { return date; }
    public LocalTime getLogin() { return login; }
    public LocalTime getLogout() { return logout; }
    public Double getWorkedHours() { return workedHours != null ? workedHours : 0.0; }
    public Double getLateHours() { return lateHours != null ? lateHours : 0.0; }
    public Double getOvertimeHours() { return overtimeHours != null ? overtimeHours : 0.0; }

    // Compatibility aliases
    public String getAttendanceID() { return getDtrID(); }
    public LocalTime getTimeIn() { return getLogin(); }
    public LocalTime getTimeOut() { return getLogout(); }
    public Double getHoursWorked() { return getWorkedHours(); }
    public Double getHoursLate() { return getLateHours(); }
    public Double getHoursOvertime() { return getOvertimeHours(); }

    // ✅ Check if overtime exists
    public boolean hasOvertime() {
        LocalTime regularEndTime = LocalTime.of(17, 0);
        return logout != null && logout.isAfter(regularEndTime);
    }
}

