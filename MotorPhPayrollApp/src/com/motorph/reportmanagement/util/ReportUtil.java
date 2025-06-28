/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.util;

import com.motorph.database.connection.DatabaseService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author emmar
 */
public class ReportUtil {
    
    //Path/location to jrxml templates
    private static final String PAYSLIP_JRXML = "src/resources/report/template/MotorPH_Payslip.jrxml";
    private static final String SUMMARY_JRXML= "src/resources/report/template/Summary_Report.jrxml";
    
    public static void generatePayslipFromDB(int employeeId, int payrollId) {
        Connection conn = null;
        try {
            // Compile the JRXML into a JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(PAYSLIP_JRXML);

            // Prepare parameters map
            Map<String, Object> params = new HashMap<>();
            params.put("EMPLOYEE_ID", employeeId);
            params.put("PAY_PERIOD_ID", payrollId);
//            params.put("GeneratedOn", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            // Obtain a JDBC connection
            conn = DatabaseService.connectToMotorPH();

            // Fill the report with data from DB using stored procedure
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            // Output directory and filename
            String dirPath = "src/resources/report/output";
            File dir = new File(dirPath);
            if (!dir.exists()) dir.mkdirs();

            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String outputFile = String.format("%s/Payslip_%s_EID%d_PID%d.pdf", dirPath, ts, employeeId, payrollId);

            // Export to PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);
            java.awt.Desktop.getDesktop().open(new File(outputFile));

            System.out.println("Payslip generated: " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close JDBC connection
            if (conn != null) {
                try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }

    public static void generateSummaryReportFromDB(String yearMonth) {
        Connection conn = null;
        try {
            // Compile the JRXML into a JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(SUMMARY_JRXML);

            // Build the parameter map
            Map<String, Object> params = new HashMap<>();
            params.put("YEAR_MONTH", yearMonth);
            params.put("GeneratedOn", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            // Obtain a JDBC connection
            conn = DatabaseService.connectToMotorPH();

            // Fill the report: the SQL inside JRXML will run on this connection
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,conn);

            // Ensure output directory exists
            String dirPath = "src/resources/report/output";
            File dir = new File(dirPath);
            if (!dir.exists()) dir.mkdirs();

            // Build a timestamped filename
            String ts = LocalDateTime.now() .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = String.format("%s/Summary_%s.pdf", dirPath, ts);

            // Export to PDF and open on desktop
            JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
            java.awt.Desktop.getDesktop().open(new File(fileName));

            System.out.println("Summary report generated: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close JDBC connection (if opened)
            if (conn != null) {
                try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }

//    public static void generatePayrollSummaryReport(PayPeriod payrollPayPeriod) {
//        String periodStart = payrollPayPeriod.getStartDate().toString();
//        String periodEnd = payrollPayPeriod.getEndDate().toString();
//        generateSummaryReportFromDB(periodStart, periodEnd);
//    }
}
