/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.service;

import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Service interface for generating reports based on supplied parameters.
 * 
 * <p>This abstraction allows different types of reports (e.g., payslips, summaries)
 * to be generated consistently and passed to exporters such as PDF or Excel.</p>
 */
public interface ReportService {
    
    /**
     * Generates a {@link JasperPrint} report using the given parameters.
     *
     * @param parameters a map of parameter names and values required by the report template
     * @return a filled {@link JasperPrint} object ready for export or display
     * @throws Exception if report generation fails (e.g., due to compilation or data access issues)
     */
    JasperPrint generateReport(Map<String, Object> parameters) throws Exception;
}