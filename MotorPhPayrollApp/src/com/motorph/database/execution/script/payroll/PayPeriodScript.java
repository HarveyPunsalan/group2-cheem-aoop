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
public enum PayPeriodScript implements Script {
    INSERT("""
        INSERT INTO pay_period (start_date, end_date, pay_day, payroll_due) 
        VALUES (?, ?, ?, ?)
        """),
    
    UPDATE("""
        UPDATE pay_period SET 
        start_date = ?, 
        end_date = ?, 
        pay_day = ?, 
        payroll_due = ? 
        WHERE pay_period_id = ?
        """),

    DELETE_BY_ID("DELETE FROM pay_period WHERE pay_period_id = ?"),

    SELECT_BY_ID("SELECT * FROM pay_period WHERE pay_period_id = ?"),

    SELECT_ALL("SELECT * FROM pay_period"),

    SELECT_BY_DATES("""
        SELECT * FROM pay_period 
        WHERE start_date = ? AND end_date = ?
        """);
    
    private final String query;
    
    PayPeriodScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
