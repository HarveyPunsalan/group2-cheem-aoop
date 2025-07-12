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
public enum BiWeeklyPayrollViewScript implements Script {
    SELECT_ALL("SELECT * FROM pay_period_status_view"),
    SELECT_BY_ID("SELECT *FROM pay_period_status_view WHERE pay_period_id = ?"),
    SELECT_BY_IDs("SELECT * FROM view_bi_weekly_payroll_summary WHERE pay_period_id = ? AND `Employee No` IN (%s)");
    
    private final String query;
    
    BiWeeklyPayrollViewScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
    
    // Return Script instead of String
    public Script formatScriptWithPlaceholders(int numberOfPlaceholders) {
        if (numberOfPlaceholders <= 0) {
            throw new IllegalArgumentException("Number of placeholders must be positive");
        }
        String placeholders = String.join(",", java.util.Collections.nCopies(numberOfPlaceholders, "?"));
        String formattedQuery = String.format(query, placeholders);

        // Return anonymous Script implementation
        return new Script() {
            @Override
            public String getQuery() {
                return formattedQuery;
            }
        };
    }
}
