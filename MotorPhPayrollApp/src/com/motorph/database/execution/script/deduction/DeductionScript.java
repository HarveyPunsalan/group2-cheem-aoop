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
public enum DeductionScript implements Script {
    INSERT("""
        INSERT INTO deduction_chart_type 
        (chart_code, chart_name, chart_description, created_at, updated_at) 
        VALUES (?, ?, ?, ?, ?)
        """),

    UPDATE("""
        UPDATE deduction_chart_type SET 
        chart_code = ?, 
        chart_name = ?, 
        chart_description = ?, 
        updated_at = ? 
        WHERE chart_id = ?
        """),

    DELETE_BY_ID("DELETE FROM deduction_chart_type WHERE chart_id = ?"),

    SELECT_BY_ID("SELECT * FROM deduction_chart_type WHERE chart_id = ?"),

    SELECT_ALL("SELECT * FROM deduction_chart_type");
    
    private final String query;
    
    DeductionScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
