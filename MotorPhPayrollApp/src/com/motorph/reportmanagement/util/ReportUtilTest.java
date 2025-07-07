/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.util;

import com.motorph.reportmanagement.controller.PayrollBiWeeklySummaryController;
import com.motorph.reportmanagement.controller.PayslipController;

/**
 *
 * @author 63909
 */

public class ReportUtilTest {
    public static void main(String[] args) {
        PayrollBiWeeklySummaryController controller = new PayrollBiWeeklySummaryController();
        PayslipController payslipController = new PayslipController();
        // TEST 1: Generate Payslip PDF for specific PayslipID
//        ReportUtil.generatePayslipFromDB(10001,1);
//            payslipController.generatePayslip(10008, 1);

        // TEST 2: Generate Summary Report for specific period
//        ReportUtil.generateSummaryReportFromDB("2024-01");        
//        controller.generatePayrollMonthlySummaryPDF("2024-01");
        ReportUtil.generateSummaryReportFromDB("2024-01");     

//        // TEST 3: Using PayPeriod object (assuming it exists and is valid)
//        PayPeriod period = new PayPeriod(1, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
//        ReportUtil.generatePayrollSummaryReport(period);
    }
}