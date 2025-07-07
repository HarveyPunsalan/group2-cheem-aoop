/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.reportmanagement.model.PayrollBiWeeklySummaryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for mapping {@link ResultSet} rows into {@link PayrollBiWeeklySummaryReport} DTOs.
 * 
 * This is commonly used for transforming SQL query results into domain objects for reporting.
 */
public class PayrollBiWeeklySummaryResultMapper {
    
    // Prevent instantiation
    private PayrollBiWeeklySummaryResultMapper() {
        throw new AssertionError("Cannot instantiate utility class");
    }
       
    /**
     * Maps a single row of the given {@link ResultSet} into a {@link PayrollBiWeeklySummaryReport} object.
     *
     * @param resultSet the ResultSet positioned at a valid row
     * @return a populated PayrollBiWeeklySummaryReport
     * @throws SQLException if column access fails
     */
    public static PayrollBiWeeklySummaryReport map(ResultSet resultSet) throws SQLException {
        return new PayrollBiWeeklySummaryReport.Builder()
            // Employee info
            .employeeID(resultSet.getString("Employee No"))
            .employeeName(resultSet.getString("Employee Full Name"))
            .jobTitle(resultSet.getString("Position"))
            .department(resultSet.getString("Department"))
            
            // Income
            .grossIncome(resultSet.getDouble("Gross Income"))
            
            // Government deductions
            .sssNumber(resultSet.getString("Social Security No."))
            .sssContribution(resultSet.getDouble("Social Security Contribution"))
            
            .philhealthNumber(resultSet.getString("Philhealth No."))
            .philhealthContribution(resultSet.getDouble("Philhealth Contribution"))
                
            .pagibigNumber(resultSet.getString("Pag-ibig No."))
            .pagibigContribution(resultSet.getDouble("Pag-Ibig Contribution"))
                
            .taxIdentificationNumber(resultSet.getString("TIN"))
            .withholdingTax(resultSet.getDouble("Withholding Tax"))
            
            // Final salary
            .netPay(resultSet.getDouble("Net Pay"))
                
            .build();
    }
    
    /**
     * Maps all rows of a {@link ResultSet} into a list of {@link PayrollBiWeeklySummaryReport} objects.
     *
     * @param resultSet the ResultSet containing multiple rows
     * @return list of mapped report DTOs
     * @throws SQLException if reading from ResultSet fails
     */
    public static List<PayrollBiWeeklySummaryReport> mapAll(ResultSet resultSet) throws SQLException {
        List<PayrollBiWeeklySummaryReport> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(map(resultSet));
        }
        return list;
    }

//    /**
//     * Maps JasperPrint pages into a list of DTOs.Useful when using Jasper to execute queries and produce JRPrintElements directly.
//     * @param jasperPrint
//     * @return 
//     */
//    public List<PayrollMonthlySummaryReport> map(JasperPrint jasperPrint) {
//        List<PayrollMonthlySummaryReport> list = new ArrayList<>();
//        for (JRPrintPage page : jasperPrint.getPages()) {
//            for (JRPrintElement element : page.getElements()) {
////                 Pseudocode: extract field values from element
//                 String empId = element.getFieldValue("employee_id").toString();
//                 String name  = element.getFieldValue("employee_name").toString();
//                 double gross = ((Number) element.getFieldValue("gross_income")).doubleValue();
//                 double net   = ((Number) element.getFieldValue("net_pay")).doubleValue();
//                 list.add(new PayrollMonthlySummaryReport(empId, name, gross, net /* … */));
//            }
//        }
//        return list;
//    }
}