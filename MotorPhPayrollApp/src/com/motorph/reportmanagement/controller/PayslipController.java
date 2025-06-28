/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.controller;

import com.motorph.reportmanagement.config.ReportConstants;
import com.motorph.reportmanagement.exporter.PdfReportExporter;
import com.motorph.reportmanagement.exporter.ReportExporter;
import com.motorph.reportmanagement.service.PayslipService;
import com.motorph.reportmanagement.util.ReportFileUtils;
import com.motorph.reportmanagement.util.ReportParameterBuilder;
import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Controller for generating and exporting payslip reports.
 * Delegates to {@link PayslipService} and handles PDF export and preview.
 */
public class PayslipController extends BaseReportController<PayslipService> {
    
    private static final Logger logger = Logger.getLogger(PayslipController.class.getName());
    private boolean previewEnabled = true; // default: open the PDF after export

    public PayslipController() {
        super(new PayslipService(), PayslipController.class);
    }
    
    /**
     * Enables or disables automatic PDF preview after generation.
     * @param enabled true to open PDF automatically; false to skip preview
     */
    public void setPreviewEnabled(boolean enabled) {
        this.previewEnabled = enabled;
    }

    /**
     * Generates and exports a payslip report for a given employee and pay period.
     *
     * @param employeeID the employee's ID
     * @param payPeriodID the payroll period ID
     * @return true if the payslip was generated and exported successfully; false otherwise
     */
    public boolean generatePayslip(int employeeID, int payPeriodID) {
        return executeReportGeneration(() -> {    
                try {
                    // Step 1: Build parameters
                    Map<String, Object> params = ReportParameterBuilder.buildPayslipParams(employeeID, payPeriodID);

                    // Step 2: Generate the report
                    JasperPrint printPayslip = service.generateReport(params);
                                        
                    // Step 3: Build export path
                    String fileName = ReportFileUtils.buildFileName(ReportConstants.FILENAME_PREFIX_PAYSLIP, ReportConstants.PDF_EXTENSION, String.valueOf(employeeID), String.valueOf(payPeriodID));
                    String pdfExportPath = ReportFileUtils.getExportPath(fileName);
                    
                    // Step 4: Export the report
                    ReportExporter pdfExporter = new PdfReportExporter(); // later this can be ExcelReportExporter, etc.
                    pdfExporter.exportToFile(printPayslip, pdfExportPath);
                    
                    // Step 5: Open preview if allowed
                    if (previewEnabled && Desktop.isDesktopSupported() && !GraphicsEnvironment.isHeadless()) {
                        Desktop.getDesktop().open(new File(pdfExportPath));
                    }
                    
                    return true;                    
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Report export failed", e);
                    return false;
                }
        }, "Failed to generate payroll summary for: " + employeeID + " " + payPeriodID);
    }
}
