/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.payrollmapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.payroll.PayrollRecord;
import java.sql.*;

public class PayrollRecordMapper extends AbstractEntityMapper<PayrollRecord> {

    @Override
    public PayrollRecord map(ResultSet resultSet) throws SQLException {
        return new PayrollRecord.Builder()
            .payrollId(resultSet.getInt("payroll_id"))
            .payPeriodId(resultSet.getInt("pay_period_id"))
            .employeeId(resultSet.getInt("employee_id"))
            .attendanceProcessingId(resultSet.getInt("attendance_processing_id"))
            .basicSalary(resultSet.getBigDecimal("basic_salary"))
            .overtimePay(resultSet.getBigDecimal("overtime_pay"))
            .totalAllowance(resultSet.getBigDecimal("total_allowance"))
            .grossSalary(resultSet.getBigDecimal("gross_salary"))
            .totalDeduction(resultSet.getBigDecimal("total_deduction"))
            .netSalary(resultSet.getBigDecimal("net_salary"))
            .createdDate(resultSet.getTimestamp("created_date").toLocalDateTime())
            .updatedDate(resultSet.getTimestamp("updated_date").toLocalDateTime())
            .submittedDate(resultSet.getDate("submitted_date").toLocalDate())
            .build();
    }

    @Override
    public void toInsertParams(PreparedStatement preparedStatement, PayrollRecord payrollRecord) throws SQLException {
        preparedStatement.setInt(1, payrollRecord.getPayPeriodId());
        preparedStatement.setInt(2, payrollRecord.getEmployeeId());
        preparedStatement.setInt(3, payrollRecord.getAttendanceProcessingId());
        preparedStatement.setBigDecimal(4, payrollRecord.getBasicSalary());
        preparedStatement.setBigDecimal(5, payrollRecord.getOvertimePay());
        preparedStatement.setBigDecimal(6, payrollRecord.getTotalAllowance());
        preparedStatement.setBigDecimal(7, payrollRecord.getGrossSalary());
        preparedStatement.setBigDecimal(8, payrollRecord.getTotalDeduction());
        preparedStatement.setBigDecimal(9, payrollRecord.getNetSalary());
        preparedStatement.setTimestamp(10, Timestamp.valueOf(payrollRecord.getCreatedDate()));
        preparedStatement.setTimestamp(11, Timestamp.valueOf(payrollRecord.getUpdatedDate()));
        preparedStatement.setDate(12, Date.valueOf(payrollRecord.getSubmittedDate()));
    }

    @Override
    public void toUpdateParams(PreparedStatement preparedStatement, PayrollRecord payrollRecord) throws SQLException {
        preparedStatement.setInt(1, payrollRecord.getPayPeriodId());
        preparedStatement.setInt(2, payrollRecord.getEmployeeId());
        preparedStatement.setInt(3, payrollRecord.getAttendanceProcessingId());
        preparedStatement.setBigDecimal(4, payrollRecord.getBasicSalary());
        preparedStatement.setBigDecimal(5, payrollRecord.getOvertimePay());
        preparedStatement.setBigDecimal(6, payrollRecord.getTotalAllowance());
        preparedStatement.setBigDecimal(7, payrollRecord.getGrossSalary());
        preparedStatement.setBigDecimal(8, payrollRecord.getTotalDeduction());
        preparedStatement.setBigDecimal(9, payrollRecord.getNetSalary());
        preparedStatement.setTimestamp(10, Timestamp.valueOf(payrollRecord.getCreatedDate()));
        preparedStatement.setTimestamp(11, Timestamp.valueOf(payrollRecord.getUpdatedDate()));
        preparedStatement.setDate(12, Date.valueOf(payrollRecord.getSubmittedDate()));
        preparedStatement.setInt(13, payrollRecord.getPayrollId()); // WHERE clause
    }

    @Override
    public void toDeleteParams(PreparedStatement preparedStatement, PayrollRecord payrollRecord) throws SQLException {
        preparedStatement.setInt(1, payrollRecord.getPayrollId());
    }
}

