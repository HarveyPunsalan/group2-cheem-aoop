/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.mapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.viewmodel.model.BiWeeklyPayrollViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 63909
 */
public class BiWeeklyPayrollViewMapper extends AbstractEntityMapper<BiWeeklyPayrollViewModel>{

    @Override
    public BiWeeklyPayrollViewModel map(ResultSet resultSet) throws SQLException {
        return new BiWeeklyPayrollViewModel.Builder()
            .payPeriodId(resultSet.getInt("pay_period_id"))
            .startDate(resultSet.getDate("start_date").toLocalDate())
            .endDate(resultSet.getDate("end_date").toLocalDate())
            .payDay(resultSet.getDate("pay_day").toLocalDate())
            .numberOfEmployees(resultSet.getLong("no_of_employees"))
            .totalPayment(resultSet.getBigDecimal("total_payment"))
            .status(resultSet.getString("status"))
            .submittedDate(toLocalDateSafe(resultSet, "submitted_date"))
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement ps, BiWeeklyPayrollViewModel model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void toUpdateParams(PreparedStatement ps, BiWeeklyPayrollViewModel model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void toDeleteParams(PreparedStatement ps, BiWeeklyPayrollViewModel model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
