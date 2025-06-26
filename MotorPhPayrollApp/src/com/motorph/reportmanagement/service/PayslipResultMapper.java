/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.reportmanagement.model.Payslip;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps a {@link ResultSet} row to a {@link Payslip} domain object.
 * 
 * <p>This mapper is typically used after executing a SQL query that retrieves
 * full payslip data (earnings, deductions, and period).</p>
 */
public class PayslipResultMapper {    
    
    /**
     * Converts the current row of the given {@link ResultSet} into a {@link Payslip} object.
     *
     * @param resultSet the result set positioned at the current row
     * @return a populated {@link Payslip} object
     * @throws SQLException if any SQL error occurs or a required column is missing
     */
    public static Payslip map(ResultSet resultSet) throws SQLException {
        return new Payslip.Builder()
            // IDs and basic employee info
            .payslipID(resultSet.getString("payslip_id"))
            .employeeID(resultSet.getString("employee_id"))
            .employeeName(resultSet.getString("employee_name"))
            .position(resultSet.getString("position"))
            .department(resultSet.getString("department"))
            
            // Compensation structure    
            .monthlyRate(resultSet.getDouble("monthly_rate"))
            .dailyRate(resultSet.getDouble("daily_rate"))
            .daysWorked(resultSet.getInt("days_worked"))
            .overtime(resultSet.getDouble("overtime"))
            
            // Allowances    
            .riceSubsidy(resultSet.getDouble("rice_subsidy"))
            .phoneAllowance(resultSet.getDouble("phone_allowance"))
            .clothingAllowance(resultSet.getDouble("clothing_allowance"))
            .totalAllowance(resultSet.getDouble("total_allowance"))
            
            // Gross and deductions    
            .grossSalary(resultSet.getDouble("gross_salary"))
                
            .sss(resultSet.getDouble("sss"))
            .philhealth(resultSet.getDouble("philhealth"))
            .pagIbig(resultSet.getDouble("pag_ibig"))
            .withholdingTax(resultSet.getDouble("withholding_tax"))
            .totalDeductions(resultSet.getDouble("total_deductions"))
             
            // Net pay and generated info    
            .netSalary(resultSet.getDouble("net_salary"))
            .generatedDate(resultSet.getDate("generated_date"))
            
            // Pay period    
            .payPeriodStart(resultSet.getDate("pay_period_start"))
            .payPeriodEnd(resultSet.getDate("pay_period_end"))
            .payDay(resultSet.getDate("pay_day"))
            .build();
    }
}
