/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.Script;

import com.motorph.database.execution.script.Script;

/**
 *
 * @author 63909
 */
public enum BiWeeklyPayrollViewScipt implements Script {
    SELECT_ALL("SELECT * FROM pay_period_status_view"),
    SELECT_BY_ID("SELECT *FROM pay_period_status_view WHERE pay_period_id = ?");
    
    private final String query;
    
    BiWeeklyPayrollViewScipt(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
