/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution.script.payroll;

import com.motorph.database.execution.script.Script;

public enum PayslipScript implements Script {

    INSERT("""
        INSERT INTO payslip (
            payroll_id, employee_id, position, department,
            monthly_rate, hourly_rate, payable_hours, overtime,
            total_allowance, gross_salary, total_deductions,
            net_salary, generated_date
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """),

    UPDATE("""
        UPDATE payslip SET
            position = ?, department = ?, monthly_rate = ?, hourly_rate = ?,
            payable_hours = ?, overtime = ?, total_allowance = ?, gross_salary = ?,
            total_deductions = ?, net_salary = ?, generated_date = ?
        WHERE payslip_id = ?
    """),

    DELETE_BY_ID("DELETE FROM payslip WHERE payslip_id = ?"),
    
    SELECT_BY_ID("SELECT * FROM view_payslip_official WHERE payslip_id = ?"),

    SELECT_ALL("SELECT * FROM view_payslip_official"),
    
    SELECT_BY_PAYPERIOD_AND_EMPLOYEE("""
        SELECT * FROM view_payslip_official 
        WHERE pay_period_id = ? AND employee_id = ?
    """);

    private final String query;

    PayslipScript(String query) {
        this.query = query;
    }

    @Override
    public String getQuery() {
        return query;
    }
}
