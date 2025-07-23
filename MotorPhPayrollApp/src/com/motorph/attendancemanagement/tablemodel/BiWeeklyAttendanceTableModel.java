/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.tablemodel;

import com.motorph.payrollprocessing.viewmodel.model.EmployeeWorkedHoursSummaryViewModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 63909
 */
public class BiWeeklyAttendanceTableModel extends AbstractTableModel {
    private final List<EmployeeWorkedHoursSummaryViewModel> employeeWorkedHoursSummaryViewModelist;
    private final String[] columnNames = {
        "Employee ID", "Employee Name", "Employee Type",
        "Total Regular Hours", "Total Overtime", "Payable Hours"
    };

    public BiWeeklyAttendanceTableModel(List<EmployeeWorkedHoursSummaryViewModel> employeeWorkedHoursSummaryViewModelist) {
        this.employeeWorkedHoursSummaryViewModelist = employeeWorkedHoursSummaryViewModelist;
    }

    @Override
    public int getRowCount() {
        return employeeWorkedHoursSummaryViewModelist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EmployeeWorkedHoursSummaryViewModel record = employeeWorkedHoursSummaryViewModelist.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getEmployeeId();
            case 1 -> record.getEmployeeName();
            case 2 -> record.getEmployeeType();
            case 3 -> record.getTotalRegularHours();
            case 4 -> record.getTotalOvertime();
            case 5 -> record.getPayableHours();                    
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Optional: make columns editable or not
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // All cells are read-only
    }
    
}
