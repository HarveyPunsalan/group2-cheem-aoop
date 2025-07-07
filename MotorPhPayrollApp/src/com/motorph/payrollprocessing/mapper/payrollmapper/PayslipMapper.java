/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.payrollmapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.payroll.Payslip;
import java.sql.*;

public class PayslipMapper extends AbstractEntityMapper<Payslip> {

    @Override
    public Payslip map(ResultSet resultSet) throws SQLException {
        return new Payslip.Builder()
            .payslipID(resultSet.getInt("payslip_id"))
            .employeeID(resultSet.getInt("employee_id"))
            .employeeName(resultSet.getString("employee_name")) // if available
            .position(resultSet.getString("position"))
            .department(resultSet.getString("department"))

            .monthlyRate(resultSet.getBigDecimal("monthly_rate"))
            .hourlyRate(resultSet.getBigDecimal("hourly_rate"))
            .payableHours(resultSet.getBigDecimal("payable_hours"))
            .overtime(resultSet.getBigDecimal("overtime"))

            .totalAllowance(resultSet.getBigDecimal("total_allowance")) // assume components are calculated separately
            .grossSalary(resultSet.getBigDecimal("gross_salary"))
            .totalDeductions(resultSet.getBigDecimal("total_deductions"))
            .netSalary(resultSet.getBigDecimal("net_salary"))

            .generatedDate(resultSet.getTimestamp("generated_date").toLocalDateTime())

            // these may require join or view to map in this DAO
            .payPeriodStart(resultSet.getDate("pay_period_start").toLocalDate())
            .payPeriodEnd(resultSet.getDate("pay_period_end").toLocalDate())
            .payDay(resultSet.getDate("pay_day").toLocalDate())

            // set 0 or fetch from joined table if needed
            .riceSubsidy(resultSet.getBigDecimal("rice_subsidy"))
            .phoneAllowance(resultSet.getBigDecimal("phone_allowance"))
            .clothingAllowance(resultSet.getBigDecimal("clothing_allowance"))
            .sss(resultSet.getBigDecimal("sss"))
            .philhealth(resultSet.getBigDecimal("philhealth"))
            .pagIbig(resultSet.getBigDecimal("pag_ibig"))
            .withholdingTax(resultSet.getBigDecimal("withholding_tax"))
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement preparedStatement, Payslip p) throws SQLException {
        preparedStatement.setInt(1, p.getPayslipID());
        preparedStatement.setInt(2, p.getEmployeeID());
        preparedStatement.setString(3, p.getPosition());
        preparedStatement.setString(4, p.getDepartment());
        preparedStatement.setBigDecimal(5, p.getMonthlyRate());
        preparedStatement.setBigDecimal(6, p.getHourlyRate());
        preparedStatement.setBigDecimal(7, p.getPayableHours());
        preparedStatement.setBigDecimal(8, p.getOvertime());
        preparedStatement.setBigDecimal(9, p.getTotalAllowance());
        preparedStatement.setBigDecimal(10, p.getGrossSalary());
        preparedStatement.setBigDecimal(11, p.getTotalDeductions());
        preparedStatement.setBigDecimal(12, p.getNetSalary());        
        preparedStatement.setTimestamp(13, Timestamp.valueOf(p.getGeneratedDate()));
    }

    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, Payslip p) throws SQLException {
        preparedStatement.setString(1, p.getPosition());
        preparedStatement.setString(2, p.getDepartment());
        preparedStatement.setBigDecimal(3, p.getMonthlyRate());
        preparedStatement.setBigDecimal(4, p.getHourlyRate());
        preparedStatement.setBigDecimal(5, p.getPayableHours());
        preparedStatement.setBigDecimal(6, p.getOvertime());
        preparedStatement.setBigDecimal(7, p.getTotalAllowance());
        preparedStatement.setBigDecimal(8, p.getGrossSalary());
        preparedStatement.setBigDecimal(9, p.getTotalDeductions());
        preparedStatement.setBigDecimal(10, p.getNetSalary());
        preparedStatement.setTimestamp(11, Timestamp.valueOf(p.getGeneratedDate()));
        preparedStatement.setInt(12, p.getPayslipID()); // WHERE payslip_id = ?
    }

    @Override
    public void toDeleteParams(PreparedStatement ps, Payslip p) throws SQLException {
        ps.setInt(1, p.getPayslipID());
    }
}


