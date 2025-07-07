/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.payrollmapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayPeriodMapper extends AbstractEntityMapper<PayPeriod> {

    @Override
    public PayPeriod map(ResultSet resultSet) throws SQLException {
        return new PayPeriod.Builder()
            .payPeriodId(resultSet.getInt("pay_period_id"))
            .startDate(resultSet.getDate("start_date").toLocalDate())
            .endDate(resultSet.getDate("end_date").toLocalDate())
            .payDate(resultSet.getDate("pay_day").toLocalDate())
            .payrollDueDate(resultSet.getDate("payroll_due").toLocalDate())
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement preparedStatement, PayPeriod payPeriod) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(payPeriod.getStartDate()));
        preparedStatement.setDate(2, Date.valueOf(payPeriod.getEndDate()));
        preparedStatement.setDate(3, Date.valueOf(payPeriod.getPayDate()));
        preparedStatement.setDate(4, Date.valueOf(payPeriod.getPayrollDueDate()));
    }

    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, PayPeriod payPeriod) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(payPeriod.getStartDate()));
        preparedStatement.setDate(2, Date.valueOf(payPeriod.getEndDate()));
        preparedStatement.setDate(3, Date.valueOf(payPeriod.getPayDate()));
        preparedStatement.setDate(4, Date.valueOf(payPeriod.getPayrollDueDate()));
        preparedStatement.setInt(5, payPeriod.getPayPeriodId()); // WHERE clause
    }

    @Override
    public void toDeleteParams(PreparedStatement preparedStatement, PayPeriod payPeriod) throws SQLException {
        preparedStatement.setInt(1, payPeriod.getPayPeriodId());
    }
}

