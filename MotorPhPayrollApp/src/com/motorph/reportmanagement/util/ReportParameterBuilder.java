/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for building parameter maps used in JasperReports.
 * 
 * <p>Includes methods for:</p>
 * <ul>
 *     <li>Standard payslip reports</li>
 *     <li>Monthly payroll summaries</li>
 *     <li>Generic custom reports with fallback timestamp</li>
 * </ul>
 */
public class ReportParameterBuilder {
    // Default format used for timestamp fields in reports (e.g., "2025-06-22 16:25:00")
    private static final DateTimeFormatter DEFAULT_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Builds a parameter map for a payslip report.
     *
     * @param employeeID The unique identifier of the employee
     * @param payslipID  The unique identifier of the payslip/payroll record
     * @return a Map<String, Object> containing required report parameters
     * @throws IllegalArgumentException if either ID is negative
     */
    public static Map<String, Object> buildPayslipParams(int employeeID, int payslipID) {
        if (employeeID < 0 || payslipID < 0) {
            throw new IllegalArgumentException("Employee ID and Payslip ID must be non-negative.");
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("EMPLOYEE_ID", employeeID);
        params.put("PAY_PERIOD_ID", payslipID);
        params.put("generatedOn", LocalDateTime.now().format(DEFAULT_TIMESTAMP_FORMAT));
        return params;
    }

    /**
     * Builds a parameter map for a monthly payroll summary report.
     *
     * @param yearMonth a string in the format "YYYY-MM" (e.g., "2025-06")
     * @return a Map<String, Object> with required parameters for the summary
     * @throws IllegalArgumentException if format is invalid or input is null/blank
     */
    public static Map<String, Object> buildMonthlySummaryParams(String yearMonth) {
        if (yearMonth == null || yearMonth.isBlank()) {
            throw new IllegalArgumentException("YearMonth must not be null or blank.");
        }
        
        // Regex ensures format is YYYY-MM and MM is within 01 to 12
        if (!yearMonth.matches("\\d{4}-(0[1-9]|1[0-2])")) {
            throw new IllegalArgumentException("YearMonth must be in format YYYY-MM and use a valid month (01-12).");
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("YEAR_MONTH", yearMonth);
        params.put("generatedOn", LocalDateTime.now().format(DEFAULT_TIMESTAMP_FORMAT));
        return params;
    }
    
    /**
     * Builds parameter map for bi-weekly payroll summary reports.
     * @param payPeriodID the ID of the bi-weekly pay period
     * @return Map of parameters for the report
     */
    public static Map<String, Object> buildBiWeeklySummaryParams(int payPeriodID) {
        if (payPeriodID < 0) {
            throw new IllegalArgumentException("PayPeriod ID must be non-negative.");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("PAY_PERIOD_ID", payPeriodID);
        params.put("GeneratedOn", LocalDateTime.now().format(DEFAULT_TIMESTAMP_FORMAT));
        return params;
    }

    /**
     * Builds a parameter map using a custom key-value set.
     * If "generatedOn" is not already included, it will be added automatically.
     *
     * @param customParams the user-defined parameters map
     * @return a Map<String, Object> containing all user parameters plus timestamp
     * @throws IllegalArgumentException if the provided map is null
     */
    public static Map<String, Object> buildCustomParams(Map<String, Object> customParams) {
        if (customParams == null) {
            throw new IllegalArgumentException("Custom parameters map must not be null.");
        }
        
        // Clone to avoid modifying the caller's original map
        Map<String, Object> params = new HashMap<>(customParams);
        params.putIfAbsent("generatedOn", LocalDateTime.now().format(DEFAULT_TIMESTAMP_FORMAT));
        return params;
    }
}