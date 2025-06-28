/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.database.connection.DatabaseService;
import com.motorph.reportmanagement.config.ReportTemplate;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service responsible for generating the Payroll Monthly Summary Report.
 * Uses a pre-defined Jasper template and a JDBC connection to fill the report with data.
 */
public class PayrollBiWeeklySummaryReportService implements ReportService {    

    private static final Logger LOGGER = Logger.getLogger(PayrollBiWeeklySummaryReportService.class.getName());

    // Path to the JRXML/Jasper file for the monthly summary report
    private final String jasperTemplatePath = ReportTemplate.PAYROLL_MONTHLY_SUMMARY.getPath();

    // JDBC connection to the payroll database
    private final Connection motorPhConnection;

    /**
     * Default constructor uses the application's database service.
     */
    public PayrollBiWeeklySummaryReportService() {
        this(DatabaseService.connectToMotorPH());
    }

    /**
     * Constructor for dependency injection (e.g., unit testing with mock Connection).
     * @param connection
     */
    public PayrollBiWeeklySummaryReportService(Connection connection) {
        this.motorPhConnection = connection;
    }

    /**
     * Compiles the Jasper report and fills it with data based on the provided parameters.
     *
     * @param parameters map of report parameters (e.g., YEAR_MONTH, generatedOn)
     * @return JasperPrint object ready for export
     * @throws Exception if report compilation or filling fails
     */
    @Override
    public JasperPrint generateReport(Map<String, Object> parameters) throws Exception {
        if (parameters == null || parameters.isEmpty()) {
            throw new IllegalArgumentException("Report parameters must not be null or empty.");
        }

        JasperReport report;

        try {
            report = JasperCompileManager.compileReport(jasperTemplatePath);
            LOGGER.info("Payroll Bi-Weekly Summary report compiled successfully.");
        } catch (JRException e) {
            LOGGER.log(Level.SEVERE, "Failed to compile report template: " + jasperTemplatePath, e);
            throw new RuntimeException("Report compilation failed", e);
        }
        
        try {
            JasperPrint print = JasperFillManager.fillReport(report, parameters, motorPhConnection);

            if (print == null || print.getPages().isEmpty()) {
                throw new RuntimeException("No data available for the selected pay period.");
            }

            return print;
        } catch (JRException e) {
            LOGGER.log(Level.SEVERE, "Error filling the report with data", e);
            throw new RuntimeException("Failed to generate the payroll summary report", e);
        }
    }
}
