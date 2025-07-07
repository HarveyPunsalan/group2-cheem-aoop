/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.allowancemapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.allowance.Allowance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllowanceMapper extends AbstractEntityMapper<Allowance> {
    
    // Maps a single row to an Allowance
    @Override
    public Allowance map(ResultSet resultSet) throws SQLException {
        return new Allowance.Builder()
            .allowanceId(resultSet.getInt("allowance_id"))
            .allowanceName(resultSet.getString("allowance_name"))            
            .description(resultSet.getString("description"))
            .build();
    }
    
    // Used for INSERTs
    @Override
    public void toInsertParams(PreparedStatement preparedStatement, Allowance allowance) throws SQLException {
        preparedStatement.setString(1, allowance.getAllowanceName());
        preparedStatement.setString(2, allowance.getDescription());
    }

    // Used for UPDATEs (code is the primary key, placed in WHERE)
    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, Allowance allowance) throws SQLException {        
        preparedStatement.setString(1, allowance.getAllowanceName());
        preparedStatement.setString(2, allowance.getDescription());
        preparedStatement.setInt(3, allowance.getAllowanceId());
    }

    @Override
    public void toDeleteParams(PreparedStatement preparedStatement, Allowance allowance) throws SQLException {
        preparedStatement.setInt(1, allowance.getAllowanceId());
    }
}
