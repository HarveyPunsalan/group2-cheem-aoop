/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.allowancemapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.allowance.EmployeeDefaultAllowance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EmployeeDefaultAllowanceMapper extends AbstractEntityMapper<EmployeeDefaultAllowance> {

    @Override
    public EmployeeDefaultAllowance map(ResultSet resultSet) throws SQLException {
        return new EmployeeDefaultAllowance.Builder()
            .employeeAllowanceId(resultSet.getInt("employee_allowance_id"))
            .allowanceId(resultSet.getInt("allowance_id"))
            .employeeId(resultSet.getInt("employee_id"))
            .monthlyAmount(resultSet.getBigDecimal("monthly_amount"))
            .biweeklyAmount(resultSet.getBigDecimal("biweekly_amount"))
            .effectiveDate(resultSet.getDate("effective_date").toLocalDate())
            .createdDate(resultSet.getDate("created_date").toLocalDate())
            .allowanceFrequency(resultSet.getString("allowance_frequency"))
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement preparedStatement, EmployeeDefaultAllowance employeeDefaultAllowance) throws SQLException {
        preparedStatement.setInt(1, employeeDefaultAllowance.getAllowanceId());
        preparedStatement.setInt(2, employeeDefaultAllowance.getEmployeeId());
        preparedStatement.setBigDecimal(3, employeeDefaultAllowance.getMonthlyAmount());
        preparedStatement.setBigDecimal(4, employeeDefaultAllowance.getBiweeklyAmount());
        preparedStatement.setDate(5, Date.valueOf(employeeDefaultAllowance.getEffectiveDate()));
        preparedStatement.setDate(6, Date.valueOf(employeeDefaultAllowance.getCreatedDate()));
        preparedStatement.setString(7, employeeDefaultAllowance.getAllowanceFrequency());
    }

    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, EmployeeDefaultAllowance employeeDefaultAllowance) throws SQLException {
        preparedStatement.setBigDecimal(1, employeeDefaultAllowance.getMonthlyAmount());
        preparedStatement.setBigDecimal(2, employeeDefaultAllowance.getBiweeklyAmount());
        preparedStatement.setDate(3, Date.valueOf(employeeDefaultAllowance.getEffectiveDate()));
        preparedStatement.setString(4, employeeDefaultAllowance.getAllowanceFrequency());
        preparedStatement.setInt(5, employeeDefaultAllowance.getEmployeeAllowanceId()); // WHERE clause
    }

    @Override
    public void toDeleteParams(PreparedStatement preparedStatement, EmployeeDefaultAllowance employeeDefaultAllowance) throws SQLException {
        preparedStatement.setInt(1, employeeDefaultAllowance.getEmployeeAllowanceId());
    }
}

