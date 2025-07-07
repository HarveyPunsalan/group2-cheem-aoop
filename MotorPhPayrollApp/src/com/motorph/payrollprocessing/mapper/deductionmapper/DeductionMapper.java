/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.deductionmapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.deduction.Deduction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DeductionMapper extends AbstractEntityMapper<Deduction> {

    @Override
    public Deduction map(ResultSet resultSet) throws SQLException {
        return new Deduction.Builder()
            .deductionId(resultSet.getInt("chart_id"))
            .deductionCode(resultSet.getString("chart_code"))
            .deductionName(resultSet.getString("chart_name"))
            .deductionDescription(resultSet.getString("chart_description"))
            .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
            .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement preparedStatement, Deduction deduction) throws SQLException {
        preparedStatement.setString(1, deduction.getDeductionCode());
        preparedStatement.setString(2, deduction.getDeductionName());
        preparedStatement.setString(3, deduction.getDeductionDescription());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(deduction.getCreatedAt()));
        preparedStatement.setTimestamp(5, Timestamp.valueOf(deduction.getUpdatedAt()));
    }

    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, Deduction deduction) throws SQLException {
        preparedStatement.setString(1, deduction.getDeductionCode());
        preparedStatement.setString(2, deduction.getDeductionName());
        preparedStatement.setString(3, deduction.getDeductionDescription());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(deduction.getUpdatedAt()));
        preparedStatement.setInt(5, deduction.getDeductionId()); // WHERE chart_id = ?
    }

    @Override
    public void toDeleteParams(PreparedStatement preparedStatement, Deduction deduction) throws SQLException {
        preparedStatement.setInt(1, deduction.getDeductionId());
    }
}

