/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution.script.deduction;

import com.motorph.database.execution.script.Script;

/**
 *
 * @author 63909
 */
public enum EmployeeGovernmentDeductionScript implements Script {
    INSERT("""
        INSERT INTO employee_government_deduction 
        (chart_id, payroll_id, amount, effective_date, created_date) 
        VALUES (?, ?, ?, ?, ?)
        """),
    UPDATE("""
        UPDATE employee_government_deduction SET 
        amount = ?, 
        effective_date = ? 
        WHERE employee_government_deduction_id = ?
        """),

    DELETE_BY_ID("DELETE FROM employee_government_deduction WHERE employee_government_deduction_id = ?"),
    
    SELECT_BY_ID("SELECT * FROM employee_government_deduction WHERE employee_government_deduction_id = ?"),

    SELECT_ALL("SELECT * FROM employee_government_deduction"),

    SELECT_BY_CHART_AND_PAYROLL("""
        SELECT * FROM employee_government_deduction 
        WHERE chart_id = ? AND payroll_id = ?
        """);
    
    private final String query;
    
    EmployeeGovernmentDeductionScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
