/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution.script.deduction;

import com.motorph.database.execution.script.Script;

public enum DeductionChartScript implements Script {

    INSERT("""
        INSERT INTO government_deduction_chart (
            gov_deduction_type_id,
            minimum_range,
            maximum_range,
            fixed_amount,
            employee_deduction_rate,
            employer_contribution_share,
            has_contribution_cap,
            min_contribution,
            max_contribution,
            effective_at,
            updated_at,
            notes
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """),

    UPDATE("""
        UPDATE government_deduction_chart SET
            gov_deduction_type_id = ?,
            minimum_range = ?,
            maximum_range = ?,
            fixed_amount = ?,
            employee_deduction_rate = ?,
            employer_contribution_share = ?,
            has_contribution_cap = ?,
            min_contribution = ?,
            max_contribution = ?,
            effective_at = ?,
            updated_at = ?,
            notes = ?
        WHERE gov_deduction_chart_id = ?
        """),

    DELETE_BY_ID("DELETE FROM government_deduction_chart WHERE gov_deduction_chart_id = ?"),
    
    SELECT_BY_ID("SELECT * FROM government_deduction_chart WHERE gov_deduction_chart_id = ?"),

    SELECT_ALL("SELECT * FROM government_deduction_chart");

    private final String query;

    DeductionChartScript(String query) {
        this.query = query;
    }

    @Override
    public String getQuery() {
        return query;
    }
}

