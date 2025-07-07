/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution.script.allowance;

import com.motorph.database.execution.script.Script;

/**
 *
 * @author 63909
 */
public enum EmployeeDefaultAllowanceScript implements Script {
    INSERT("""
        INSERT INTO employee_default_allowance 
        (allowance_id, employee_id, monthly_amount, biweekly_amount, effective_date, created_date, allowance_frequency) 
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """),    

    UPDATE("""
        UPDATE employee_default_allowance SET 
        monthly_amount = ?, 
        biweekly_amount = ?, 
        effective_date = ?, 
        created_date = ?, 
        allowance_frequency = ? 
        WHERE employee_allowance_id = ?
        """),

    DELETE_BY_ID("DELETE FROM employee_default_allowance WHERE employee_allowance_id = ?"),
    
    SELECT_BY_ID("SELECT * FROM employee_default_allowance WHERE employee_allowance_id = ?"),

    SELECT_ALL("SELECT * FROM employee_default_allowance"),

    SELECT_BY_EMPLOYEE_AND_ALLOWANCE("""
        SELECT * FROM employee_default_allowance 
        WHERE employee_id = ? AND allowance_id = ?
        """);
       
    private final String query;
    
    EmployeeDefaultAllowanceScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}