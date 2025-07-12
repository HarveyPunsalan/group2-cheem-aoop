/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.DailyAttendance;
import com.motorph.employeemanagement.model.Employee;
import CSVFileManager.CsvFile;
import com.motorph.common.util.CollectionUtils;
import com.motorph.common.ui.model.TableModel;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceService {
    private List<DailyAttendance> dailyAttendanceList;
    private List<Employee> employeeList;

    public AttendanceService() {
        this.dailyAttendanceList = CsvFile.DAILYATTENDANCE.readFile(DailyAttendance::new);
        // this.employeeList = CsvFile.EMPLOYEEINFORMATION.readFile(Employee::new);
    }

    public DailyAttendance getEmployeeDailyAttendance(Employee employee, LocalDate date) {
        return dailyAttendanceList.stream()
                .filter(dtr -> dtr.getEmployee().getEmployeeId() == employee.getEmployeeId())
                .filter(dtr -> dtr.getDate().equals(date))
                .findFirst()
                .orElse(null);
    }

    public DefaultComboBoxModel<String> getOvertimeDatesComboBoxModel(List<DailyAttendance> dtrList) {
        String[] overtimeDatesArray = dtrList.stream()
                .filter(DailyAttendance::hasOvertime)
                .map(dtr -> dtr.getDate().toString())
                .toArray(String[]::new);
        return new DefaultComboBoxModel<>(overtimeDatesArray);
    }

    public List<DailyAttendance> getFilteredDailyAttendance(Employee employee, PayPeriod payPeriod) {
        return dailyAttendanceList.stream()
                .filter(dtr -> dtr.getEmployee().getEmployeeId() == employee.getEmployeeId())
                .filter(dtr -> !dtr.getDate().isBefore(payPeriod.getStartDate())
                        && !dtr.getDate().isAfter(payPeriod.getEndDate()))
                .collect(Collectors.toList());
    }

    public DefaultTableModel getAttendanceTableModel(Employee employee, PayPeriod payPeriod) {
        int[] selectedColumns = {2, 3, 4, 5, 6, 7};
        String[] selectedHeaders = new String[selectedColumns.length];

        for (int i = 0; i < selectedColumns.length; i++) {
            selectedHeaders[i] = CsvFile.DAILYATTENDANCE.getTableHeader()[selectedColumns[i]];
        }

        List<LocalDate> allDates = payPeriod.getStartDate().datesUntil(payPeriod.getEndDate().plusDays(1))
                .collect(Collectors.toList());

        List<DailyAttendance> filteredList = getFilteredDailyAttendance(employee, payPeriod);
        Map<LocalDate, DailyAttendance> attendanceMap = CollectionUtils.listToMap(filteredList, DailyAttendance::getDate);

        List<String[]> tableRows = new ArrayList<>();
        for (LocalDate date : allDates) {
            DailyAttendance dtr = attendanceMap.get(date);

            String[] row = new String[]{
                    date.format(DateTimeFormatter.ofPattern("M/d/yyyy")),
                    (dtr != null && dtr.getTimeIn() != null) ? dtr.getTimeIn().format(DateTimeFormatter.ofPattern("H:mm")) : "N/A",
                    (dtr != null && dtr.getTimeOut() != null) ? dtr.getTimeOut().format(DateTimeFormatter.ofPattern("H:mm")) : "N/A",
                    (dtr != null) ? String.valueOf(dtr.getHoursLate()) : "0.0",
                    (dtr != null) ? String.valueOf(dtr.getHoursOvertime()) : "0.0",
                    (dtr != null) ? String.valueOf(dtr.getHoursWorked()) : "0.0"
            };

            tableRows.add(row);
        }

        return new TableModel(tableRows, selectedHeaders, row -> row).getTableModel();
    }

    public void updateDailyAttendanceFile() {
        boolean updateNeeded = dailyAttendanceList.stream().anyMatch(d ->
                d.getTimeIn() != null && d.getTimeOut() != null &&
                        (d.getHoursLate() == 0.0 && d.getHoursWorked() == 0.0 && d.getHoursOvertime() == 0.0)
        );

        if (!updateNeeded) return;

        List<String[]> dataToWrite = dailyAttendanceList.stream()
                .map(dtr -> new String[]{
                        dtr.getAttendanceID(),
                        String.valueOf(dtr.getEmployee().getEmployeeId()),
                        dtr.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")),
                        (dtr.getTimeIn() != null) ? dtr.getTimeIn().format(DateTimeFormatter.ofPattern("H:mm")) : "",
                        (dtr.getTimeOut() != null) ? dtr.getTimeOut().format(DateTimeFormatter.ofPattern("H:mm")) : "",
                        String.valueOf(dtr.getHoursLate()),
                        String.valueOf(dtr.getHoursOvertime()),
                        String.valueOf(dtr.getHoursWorked())
                })
                .collect(Collectors.toList());

        CsvFile.DAILYATTENDANCE.writeFile(dataToWrite);
    }
}

