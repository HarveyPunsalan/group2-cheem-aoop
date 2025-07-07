/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.allowancemapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.allowance.PayrollAllowance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PayrollAllowanceMapper extends AbstractEntityMapper<PayrollAllowance> {

    @Override
    public PayrollAllowance map(ResultSet resultSet) throws SQLException {
        return new PayrollAllowance.Builder()
            .payrollAllowanceId(resultSet.getInt("payroll_allowance_id"))
            .payrollId(resultSet.getInt("payroll_id"))
            .sourceAllowanceId(resultSet.getInt("source_allowance_id"))
            .amount(resultSet.getBigDecimal("amount"))
            .createdDate(resultSet.getTimestamp("created_date").toLocalDateTime())
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement preparedStatement, PayrollAllowance payrollAllowance) throws SQLException {
        preparedStatement.setInt(1, payrollAllowance.getPayrollId());
        preparedStatement.setInt(2, payrollAllowance.getSourceAllowanceId());
        preparedStatement.setBigDecimal(3, payrollAllowance.getAmount());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(payrollAllowance.getCreatedDate()));
        // payroll_allowance_id is auto-generated (AI PK)
    }

    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, PayrollAllowance payrollAllowance) throws SQLException {
        preparedStatement.setInt(1, payrollAllowance.getPayrollId());
        preparedStatement.setInt(2, payrollAllowance.getSourceAllowanceId());
        preparedStatement.setBigDecimal(3, payrollAllowance.getAmount());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(payrollAllowance.getCreatedDate()));
        preparedStatement.setInt(5, payrollAllowance.getPayrollAllowanceId()); // WHERE clause
    }

    @Override
    public void toDeleteParams(PreparedStatement preparedStatement, PayrollAllowance payrollAllowance) throws SQLException {
        preparedStatement.setInt(1, payrollAllowance.getPayrollAllowanceId());
    }
}
