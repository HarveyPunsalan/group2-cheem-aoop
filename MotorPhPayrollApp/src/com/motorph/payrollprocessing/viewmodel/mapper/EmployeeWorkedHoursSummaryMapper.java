/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.mapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.viewmodel.model.EmployeeWorkedHoursSummaryViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeWorkedHoursSummaryMapper extends AbstractEntityMapper<EmployeeWorkedHoursSummaryViewModel> {

    @Override
    public EmployeeWorkedHoursSummaryViewModel map(ResultSet rs) throws SQLException {
        return new EmployeeWorkedHoursSummaryViewModel.Builder()
                .employeeId(rs.getInt("employee_id"))
                .payPeriodId(rs.getInt("pay_period_id"))
                .employeeName(rs.getString("employee_name"))
                .employeeType(rs.getString("employee_type"))
                .totalRegularHours(rs.getBigDecimal("total_regular_hours"))
                .totalOvertime(rs.getBigDecimal("total_overtime"))
                .payableHours(rs.getBigDecimal("payable_hours"))
                .build();
    }

    @Override
    public void toInsertParams(PreparedStatement ps, EmployeeWorkedHoursSummaryViewModel model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void toUpdateParams(PreparedStatement ps, EmployeeWorkedHoursSummaryViewModel model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void toDeleteParams(PreparedStatement ps, EmployeeWorkedHoursSummaryViewModel model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
