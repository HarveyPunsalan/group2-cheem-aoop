/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.swing.validation;

import com.motorph.payrollprocessing.viewmodel.model.BiWeeklyPayrollViewModel;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class SelectionValidator {

    public static boolean isRowSelected(JTable table, String message) {
        if (table.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(null, message, "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isItemSelected(JComboBox<?> comboBox, String message) {
        if (comboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, message, "Validation", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    public static boolean isListSelectionValid(JList<?> list, String message) {
        if (list.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, message, "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * Checks if a payroll record has already been processed/submitted.
     * @param payrollRecord the view model or data object
     * @param component optional parent component for dialog
     * @return true if payroll is NOT processed and can proceed, false if already processed
     */
    public static boolean isPayrollProcessed(BiWeeklyPayrollViewModel payrollRecord, Component component) {
        if (payrollRecord == null) {
            JOptionPane.showMessageDialog(component, "No payroll record selected.", "Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if ("Submitted".equalsIgnoreCase(payrollRecord.getStatus())) {
            JOptionPane.showMessageDialog(component,
                    "This payroll record has already been submitted and cannot be modified.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}
