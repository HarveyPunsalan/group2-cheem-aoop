/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.swing;

import javax.swing.*;
import javax.swing.table.*;

public class TableConfigurator {
    
    public static void configureTable(JTable table, int[] columnWidths, int[] alignments) {
        TableColumnModel columnModel = table.getColumnModel();
        int columnCount = columnModel.getColumnCount();

        for (int i = 0; i < columnCount; i++) {
            if (i < columnWidths.length) {
                columnModel.getColumn(i).setPreferredWidth(columnWidths[i]);
            }

            if (i < alignments.length) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(alignments[i]);
                columnModel.getColumn(i).setCellRenderer(renderer);
            }
        }

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
    }
    
    public static void configureBiWeeklyPayrollTable(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        int[] preferredWidths = {250, 180, 100, 140, 100, 100};
        int[] alignments = {
            SwingConstants.LEFT,  // Pay Runs
            SwingConstants.LEFT,  // Pay Date
            SwingConstants.CENTER,// Employee Count
            SwingConstants.RIGHT, // Total Payment
            SwingConstants.RIGHT,// Status
            SwingConstants.LEFT   // Submitted Date
        };

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(preferredWidths[i]);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(alignments[i]);
            column.setCellRenderer(renderer);
        }

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
    }
    
    public static void configureEmployeeWorkedHoursSummaryTable(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        int[] preferredWidths = {15, 80, 250, 100, 140, 120, 120};
        int[] alignments = {
            SwingConstants.CENTER,
            SwingConstants.LEFT,  // Employee ID
            SwingConstants.LEFT,  // Employee Name
            SwingConstants.LEFT,// Employee Type
            SwingConstants.RIGHT, // Total Regular Hours
            SwingConstants.RIGHT,// Total Overtime
            SwingConstants.RIGHT   // Payable Hours
        };
        
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(preferredWidths[i]);

            // ✅ Skip renderer for checkbox column (Boolean.class)
            if (!Boolean.class.equals(table.getColumnClass(i))) {
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(alignments[i]);
                column.setCellRenderer(renderer);
            }
        }

//        for (int i = 0; i < columnModel.getColumnCount(); i++) {
//            TableColumn column = columnModel.getColumn(i);
//            column.setPreferredWidth(preferredWidths[i]);
//
//            // Only apply a custom renderer to non-checkbox columns
//            if (!Boolean.class.equals(table.getColumnClass(i))) {
//                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//                renderer.setHorizontalAlignment(alignments[i]);
//                column.setCellRenderer(renderer);
//            } else {
//                // Optional: align checkbox to center
//                DefaultTableCellRenderer checkboxRenderer = new DefaultTableCellRenderer();
//                checkboxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//                column.setCellRenderer(checkboxRenderer);
//            }
//        }

        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
    }
    
    public static void configureBiWeeklyPayrollSummaryTable(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        int[] preferredWidths = {50, 150, 120, 100, 100, 100, 100, 100, 100, 100, 100};
        int[] alignments = {
            SwingConstants.LEFT,  // Employee No
            SwingConstants.LEFT,  // Employee Full Name
            SwingConstants.LEFT,// Position
            SwingConstants.LEFT, // Department
            
            SwingConstants.RIGHT,   // Gross Income
            
            SwingConstants.RIGHT, // SSS Contribution
            SwingConstants.RIGHT, // Philhealth Contribution
            SwingConstants.RIGHT,   // Pag-Ibig Contribution
            SwingConstants.RIGHT, // Withholding  Tax
            SwingConstants.RIGHT   // Net Pay
        };

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(preferredWidths[i]);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(alignments[i]);
            column.setCellRenderer(renderer);
        }

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);
    }
}

