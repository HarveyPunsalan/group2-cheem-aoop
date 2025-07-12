/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.tablemodel;

import com.motorph.common.util.NumberFormatter;
import com.motorph.reportmanagement.model.PayrollBiWeeklySummaryReport;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 63909
 */
public class BiWeeklyPayrollSummaryTableModel extends AbstractTableModel {
//    private final List<BiWeeklyPayrollViewModel> biWeeklyPayrollViewModelist;
    private final List<PayrollBiWeeklySummaryReport> payrollBiWeeklySummaryReportist;
    private final String[] columnNames = {"Employee No", "Employee Full Name", "Position", "Department",
                                          "Gross Income", "SSS Contribution", "Philhealth Contribution", "Pag-Ibig Contribution",	
                                          "Withholding  Tax", "Net Pay"};

    public BiWeeklyPayrollSummaryTableModel(List<PayrollBiWeeklySummaryReport> payrollBiWeeklySummaryReportist) {
        this.payrollBiWeeklySummaryReportist = payrollBiWeeklySummaryReportist;
    }

    @Override
    public int getRowCount() {
        return payrollBiWeeklySummaryReportist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PayrollBiWeeklySummaryReport record = payrollBiWeeklySummaryReportist.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getEmployeeID();
            case 1 -> record.getEmployeeName();
            case 2 -> record.getJobTitle();
            case 3 -> record.getDepartment();
                
            case 4 -> NumberFormatter.formatDecimal(record.getGrossIncome());
                
            case 5 -> NumberFormatter.formatDecimal(record.getSssContribution());
            case 6 -> NumberFormatter.formatDecimal(record.getPhilhealthContribution());
            case 7 -> NumberFormatter.formatDecimal(record.getPagibigContribution());
            case 8 -> NumberFormatter.formatDecimal(record.getWithholdingTax()); 
            case 9 -> NumberFormatter.formatCurrency(record.getNetPay());
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