/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.comboboxmodel;

import com.motorph.payrollprocessing.comboboxmodel.viewmapper.PayPeriodViewMapper;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import java.util.List;
import java.util.function.Function;
import javax.swing.DefaultComboBoxModel;

public class ComboBoxModelFactory {

    public static DefaultComboBoxModel<String> createPayPeriodModel(List<PayPeriod> payPeriods, String dateFormat) {
        return createModel(payPeriods, new PayPeriodViewMapper(dateFormat));
    }

    public static <T> DefaultComboBoxModel<String> createModel(List<T> items, Function<T, String> mapper) {
        String[] data = items.stream().map(mapper).toArray(String[]::new);
        return new DefaultComboBoxModel<>(data);
    }
}
