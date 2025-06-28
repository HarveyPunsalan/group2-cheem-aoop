/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.controller;

import com.motorph.reportmanagement.config.ReportConstants;
import com.motorph.reportmanagement.exporter.PdfReportExporter;
import com.motorph.reportmanagement.exporter.ReportExporter;
import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryReportService;
import com.motorph.reportmanagement.util.ReportFileUtils;
import com.motorph.reportmanagement.util.ReportParameterBuilder;
import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller responsible for generating and exporting bi-weekly payroll summary reports.
 * Handles report parameter building, PDF export, and optional preview.
 */
public class PayrollBiWeeklySummaryController extends BaseReportController<PayrollBiWeeklySummaryReportService> {
    
    private static final Logger logger = Logger.getLogger(PayrollBiWeeklySummaryController.class.getName());
    private boolean previewEnabled = true; // default: open the PDF after export

    public PayrollBiWeeklySummaryController() {
        super(new PayrollBiWeeklySummaryReportService(), PayrollBiWeeklySummaryController.class);
    }
    
    /**
     * Enables or disables automatic PDF preview after generation.
     *
     * @param enabled true to open PDF automatically; false to disable preview
     */
    public void setPreviewEnabled(boolean enabled) {
        this.previewEnabled = enabled;
    }

    /**
     * Generates a bi-weekly payroll summary report and exports it to PDF.
     *
     * @param payPeriodID The payroll period identifier
     * @return true if successful, false otherwise
     */
    public boolean generatePayrollBiWeeklySummaryPDF(int payPeriodID) {
        return executeReportGeneration(() -> {
                try {
                    // Step 1: Build parameters
                    Map<String, Object> params = ReportParameterBuilder.buildBiWeeklySummaryParams(payPeriodID);
                    
                    // Step 2: Generate the report
                    JasperPrint printPayrollMBiWeeklySummary = service.generateReport(params);
                                        
                    // Step 3: Build export path
                    String fileName = ReportFileUtils.buildFileName(ReportConstants.FILENAME_PREFIX_BI_WEEKLY, ReportConstants.PDF_EXTENSION, String.valueOf(payPeriodID));
                    String pdfExportPath = ReportFileUtils.getExportPath(fileName);
                    
                    // Step 4: Export the report
                    ReportExporter pdfExporter = new PdfReportExporter();
                    pdfExporter.exportToFile(printPayrollMBiWeeklySummary, pdfExportPath);
                    
                    // Step 5: Open preview if allowed
                    if (previewEnabled && Desktop.isDesktopSupported() && !GraphicsEnvironment.isHeadless()) {
                        Desktop.getDesktop().open(new File(pdfExportPath));
                    }
                    
                    return true;
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Report export failed", e);
                    return false;
                }
        }, "Failed to generate payroll summary for: " + payPeriodID);            
    }
}
