/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.tablemodel;

import com.motorph.payrollprocessing.viewmodel.model.EmployeeWorkedHoursSummaryViewModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EmployeeWorkedHoursSummaryTableModel extends AbstractTableModel {
    private final List<EmployeeWorkedHoursSummaryViewModel> employeeWorkedHoursSummaryViewModelist;
    private final List<Boolean> selectedRows;
    
    private final String[] columnNames = {
        "Select", "Employee ID", "Employee Name", "Employee Type",
        "Total Regular Hours", "Total Overtime", "Payable Hours"
    };

    public EmployeeWorkedHoursSummaryTableModel(List<EmployeeWorkedHoursSummaryViewModel> employeeWorkedHoursSummaryViewModelist) {
        this.employeeWorkedHoursSummaryViewModelist = employeeWorkedHoursSummaryViewModelist;
        this.selectedRows = new ArrayList<>(Collections.nCopies(employeeWorkedHoursSummaryViewModelist.size(), false));
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
            case 0 -> selectedRows.get(rowIndex);
            case 1 -> record.getEmployeeId();
            case 2 -> record.getEmployeeName();
            case 3 -> record.getEmployeeType();
            case 4 -> record.getTotalRegularHours();
            case 5 -> record.getTotalOvertime();
            case 6 -> record.getPayableHours();                    
            default -> null;
        };
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0 && aValue instanceof Boolean) {
            selectedRows.set(rowIndex, (Boolean) aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Optional: make columns editable or not
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0; // Only the checkbox column is editable
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Boolean.class;
            case 1 -> Integer.class;
            case 4, 5, 6 -> Double.class;
            default -> String.class;
        };
    }

    // Optional: to retrieve selected rows
    public List<EmployeeWorkedHoursSummaryViewModel> getSelectedEmployees() {
        List<EmployeeWorkedHoursSummaryViewModel> selected = new ArrayList<>();
        for (int i = 0; i < selectedRows.size(); i++) {
            if (selectedRows.get(i)) {
                selected.add(employeeWorkedHoursSummaryViewModelist.get(i));
            }
        }
        return selected;
    }
    
    public void selectAll(boolean value) {
        for (int i = 0; i < selectedRows.size(); i++) {
            selectedRows.set(i, value);
        }
        fireTableRowsUpdated(0, getRowCount() - 1);
    }
    
    public int getSelectedCount() {
        int count = 0;
        for (Boolean b : selectedRows) {
            if (b) count++;
        }
        return count;
    }
}
