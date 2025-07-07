/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution.script.payroll;

import com.motorph.database.execution.script.Script;

/**
 *
 * @author 63909
 */
public enum PayrollRecordScript implements Script {
    INSERT("""
        INSERT INTO payroll_record (
            pay_period_id, employee_id, attendance_processing_id,
            basic_salary, overtime_pay, total_allowance,
            gross_salary, total_deduction, net_salary,
            created_date, updated_date, submitted_date
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """),

    UPDATE("""
        UPDATE payroll_record SET
            pay_period_id = ?,
            employee_id = ?,
            attendance_processing_id = ?,
            basic_salary = ?,
            overtime_pay = ?,
            total_allowance = ?,
            gross_salary = ?,
            total_deduction = ?,
            net_salary = ?,
            updated_date = ?,
            submitted_date = ?
        WHERE payroll_id = ?
        """),

    DELETE_BY_ID("DELETE FROM payroll_record WHERE payroll_id = ?"),
    
    SELECT_BY_ID("SELECT * FROM payroll_record WHERE payroll_id = ?"),

    SELECT_ALL("SELECT * FROM payroll_record"),

    SELECT_BY_EMPLOYEE_AND_PERIOD("""
        SELECT * FROM payroll_record 
        WHERE employee_id = ? AND pay_period_id = ?
        """);
    
    private final String query;
    
    PayrollRecordScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
