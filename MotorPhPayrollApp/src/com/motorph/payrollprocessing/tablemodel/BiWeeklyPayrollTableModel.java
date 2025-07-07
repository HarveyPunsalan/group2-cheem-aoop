/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.tablemodel;

import com.motorph.common.util.DateUtil;
import com.motorph.common.util.NumberFormatter;
import com.motorph.payrollprocessing.viewmodel.model.BiWeeklyPayrollViewModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BiWeeklyPayrollTableModel extends AbstractTableModel {
    private final List<BiWeeklyPayrollViewModel> biWeeklyPayrollViewModelist;
    private final String[] columnNames = {"Pay Runs", "Pay Date", "Employee","Total Payment", "Status", "Submitted Date"};

    public BiWeeklyPayrollTableModel(List<BiWeeklyPayrollViewModel> biWeeklyPayrollViewModelist) {
        this.biWeeklyPayrollViewModelist = biWeeklyPayrollViewModelist;
    }

    @Override
    public int getRowCount() {
        return biWeeklyPayrollViewModelist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BiWeeklyPayrollViewModel record = biWeeklyPayrollViewModelist.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> DateUtil.format_RANGE_BiWeekly_MMM_ddo_ddo(record.getStartDate(),record.getEndDate());
            case 1 -> DateUtil.format_E_ddo_MMM_yyyy(record.getPayDay());
            case 2 -> record.getNumberOfEmployees();
            case 3 -> NumberFormatter.formatCurrency(record.getTotalPayment());
            case 4 -> record.getStatus();
            case 5 -> DateUtil.format_ddo_MMM_yyyy(record.getSubmittedDate());                    
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
