/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.deductionmapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.deduction.EmployeeGovernmentDeduction;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeGovernmentDeductionMapper extends AbstractEntityMapper<EmployeeGovernmentDeduction> {

    @Override
    public EmployeeGovernmentDeduction map(ResultSet resultSet) throws SQLException {
        return new EmployeeGovernmentDeduction.Builder()
            .employeeGovernmentDeductionId(resultSet.getInt("employee_government_deduction_id"))
            .chartId(resultSet.getInt("chart_id"))
            .payrollId(resultSet.getInt("payroll_id"))
            .amount(resultSet.getBigDecimal("amount"))
            .effectiveDate(resultSet.getDate("effective_date").toLocalDate())
            .createdDate(resultSet.getDate("created_date").toLocalDate())
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement preparedStatement, EmployeeGovernmentDeduction employeeGovernmentDeduction) throws SQLException {
        preparedStatement.setInt(1, employeeGovernmentDeduction.getChartId());
        preparedStatement.setInt(2, employeeGovernmentDeduction.getPayrollId());
        preparedStatement.setBigDecimal(3, employeeGovernmentDeduction.getAmount());
        preparedStatement.setDate(4, Date.valueOf(employeeGovernmentDeduction.getEffectiveDate()));
        preparedStatement.setDate(5, Date.valueOf(employeeGovernmentDeduction.getCreatedDate()));
        // ID is auto-increment
    }

    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, EmployeeGovernmentDeduction employeeGovernmentDeduction) throws SQLException {
        preparedStatement.setBigDecimal(1, employeeGovernmentDeduction.getAmount());
        preparedStatement.setDate(2, Date.valueOf(employeeGovernmentDeduction.getEffectiveDate()));
        preparedStatement.setInt(3, employeeGovernmentDeduction.getEmployeeGovernmentDeductionId()); // WHERE clause
    }

    @Override
    public void toDeleteParams(PreparedStatement preparedStatement, EmployeeGovernmentDeduction employeeGovernmentDeduction) throws SQLException {
        preparedStatement.setInt(1, employeeGovernmentDeduction.getEmployeeGovernmentDeductionId());
    }
}

