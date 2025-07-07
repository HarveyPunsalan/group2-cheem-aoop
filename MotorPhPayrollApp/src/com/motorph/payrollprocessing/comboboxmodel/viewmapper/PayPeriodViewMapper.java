/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.comboboxmodel.viewmapper;

import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class PayPeriodViewMapper implements Function<PayPeriod, String> {

    private final DateTimeFormatter dateFormatter;

    public PayPeriodViewMapper(String dateFormat) {
        this.dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public String apply(PayPeriod pp) {
        return pp.getStartDate().format(dateFormatter) + " : " + pp.getEndDate().format(dateFormatter);
    }
}
