/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.databasemanagement.connection.DatabaseService;
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
 * Service for generating payslip reports using JasperReports.
 * Supports constructor-based injection for easier testing and flexibility.
 */
public class PayslipService implements ReportService {
    private static final Logger LOGGER = Logger.getLogger(PayslipService.class.getName());
    
    // Path to the JasperReports template file
    private final String jasperTemplatePath = ReportTemplate.PAYSLIP.getPath();
    
    // JDBC connection to the payroll database
    private final Connection motorPhConnection;

    /**
     * Default constructor using the standard MotorPH database service.
     * Should be used in production or application runtime.
     */
    public PayslipService() {
        this(DatabaseService.connectToMotorPH());
    }

    /**
     * Constructor that accepts a custom {@link Connection}.
     * Recommended for unit testing or when injecting test/mocked connections.
     *
     * @param connection JDBC connection to be used
     */
    public PayslipService(Connection connection) {
        this.motorPhConnection = connection;
    }

    /**
     * Generates a payslip report for a specific employee and payroll ID.
     * Requires the parameter map to include:
     * - EMPLOYEE_ID (int)
     * - PAYROLL_ID (int)
     *
     * @param parameters the parameters required by the payslip report
     * @return filled {@link JasperPrint} object
     * @throws IllegalArgumentException if the parameters are null or empty
     * @throws RuntimeException if report compilation or filling fails
     */    
    @Override
    public JasperPrint generateReport(Map<String, Object> parameters) throws Exception {
        if (parameters == null || parameters.isEmpty()) {
            throw new IllegalArgumentException("Report parameters must not be null or empty.");
        }

        JasperReport report;

        try {
            report = JasperCompileManager.compileReport(jasperTemplatePath);
            LOGGER.info("Payslip report compiled successfully.");
        } catch (JRException e) {
            LOGGER.log(Level.SEVERE, "Failed to compile report template: " + jasperTemplatePath, e);
            throw new RuntimeException("Report compilation failed", e);
        }
        
        try {
            JasperPrint print = JasperFillManager.fillReport(report, parameters, motorPhConnection);

            if (print == null || print.getPages().isEmpty()) {
                throw new RuntimeException("No data available for the selected employee or payroll ID.");
            }

            return print;
        } catch (JRException e) {
            LOGGER.log(Level.SEVERE, "Error filling the payslip report with data", e);
            throw new RuntimeException("Failed to generate the payslip report", e);
        }
    }
}
