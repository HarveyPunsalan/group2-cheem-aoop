/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.tablemodel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderCheckBoxHandler {
    public static void addHeaderCheckBox(JTable table, EmployeeWorkedHoursSummaryTableModel model) {
        // Create checkbox
        JCheckBox selectAllCheckBox = new JCheckBox();
        selectAllCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        selectAllCheckBox.setOpaque(false);

        // Set the renderer for the checkbox in header
        JTableHeader header = table.getTableHeader();
        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(0).setHeaderRenderer((table1, value, isSelected, hasFocus, row, column) -> selectAllCheckBox);

        // Add listener to toggle all checkboxes in the model
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int viewColumn = table.columnAtPoint(e.getPoint());
                int modelColumn = table.convertColumnIndexToModel(viewColumn);

                if (modelColumn == 0) {
                    boolean newState = !selectAllCheckBox.isSelected();
                    selectAllCheckBox.setSelected(newState);

                    model.selectAll(newState); // call the custom method
                }
            }
        });
    }
}
