/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.tablemodel;

import com.motorph.attendancemanagement.model.DailyAttendance;
import com.motorph.common.util.DateUtil;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 63909
 */
public class DailyAttendanceTableModel extends AbstractTableModel {
    private final List<DailyAttendance> dailyAttendanceList;
    private final String[] columnNames = {"Date", "Time In", "Time Out","Hours Late", "Overtime", "Worked Hours"};

    public DailyAttendanceTableModel(List<DailyAttendance> dailyAttendanceList) {
        this.dailyAttendanceList = dailyAttendanceList;
    }

    @Override
    public int getRowCount() {
        return dailyAttendanceList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DailyAttendance record = dailyAttendanceList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> DateUtil.formatDate(record.getDate());
            case 1 -> record.getTimeIn();
            case 2 -> record.getTimeOut();
            case 3 -> record.getHoursLate();
            case 4 -> record.getHoursOvertime();
            case 5 -> record.getHoursWorked();                
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
