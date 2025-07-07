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
public enum PayrollAllowanceScript implements Script {
    INSERT("""
        INSERT INTO payroll_allowance 
        (payroll_id, source_allowance_id, amount, created_date) 
        VALUES (?, ?, ?, ?)
        """),
    
    DELETE_BY_ID("DELETE FROM payroll_allowance WHERE payroll_allowance_id = ?"),

    SELECT_BY_ID("SELECT * FROM payroll_allowance WHERE payroll_allowance_id = ?"),

    SELECT_ALL("SELECT * FROM payroll_allowance"),    

    SELECT_BY_PAYROLL_AND_ALLOWANCE("""
        SELECT * FROM payroll_allowance 
        WHERE payroll_id = ? AND source_allowance_id = ?
        """);

    
    private final String query;
    
    PayrollAllowanceScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
