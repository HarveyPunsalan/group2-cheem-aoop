/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution.script.allowance;

import com.motorph.database.execution.script.Script;

public enum  AllowanceScript implements Script {
    INSERT("INSERT INTO allowance (allowance_name, description) VALUES (?, ?)"),    
    UPDATE("UPDATE allowance SET allowance_name = ?, description = ? WHERE allowance_id = ?"),
    DELETE_BY_ID("DELETE FROM allowance WHERE allowance_id = ?"),
    SELECT_BY_ID("SELECT * FROM allowance WHERE allowance_id = ?"),
    SELECT_ALL("SELECT * FROM allowance");
    
    private final String query;
    
    AllowanceScript(String query) {
        this.query = query;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
}
    