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
public enum EmployeeWorkedHoursSummaryScript implements Script {
    SELECT_ALL("SELECT * FROM view_employee_work_hours_summary"),
    SELECT_BY_PAY_PERIOD("SELECT * FROM view_employee_work_hours_summary WHERE pay_period_id = ?"),
    SELECT_BY_PAY_PERIOD_AND_EMPLOYEE_ID("""
                                         SELECT * FROM view_employee_work_hours_summary
                                         WHERE pay_period_id = ? AND employee_id = ?""");
    
    private final String query;
    
    EmployeeWorkedHoursSummaryScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
