/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.exporter;

import com.motorph.databasemanagement.connection.DatabaseService;
import com.motorph.reportmanagement.config.ReportConstants;
import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryReportService;
import com.motorph.reportmanagement.service.PayslipService;
import com.motorph.reportmanagement.util.ReportFileUtils;
import com.motorph.reportmanagement.util.ReportParameterBuilder;
import java.io.File;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Integration tests for {@link PdfReportExporter}.
 *
 * These tests verify that PDF reports are successfully generated to disk using:
 * - {@link PayslipService}
 * - {@link PayrollBiWeeklySummaryReportService}
 */
public class PdfReportExporterTest {
    
    private final String exportDir = "test-output/";
    
    @Before
    public void setUp() {
        ReportFileUtils.setExportDirectory(exportDir);
        new File(exportDir).mkdirs();
    }

    @After
    public void tearDown() {
        ReportFileUtils.resetExportDirectory();
        File dir = new File(exportDir);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            dir.delete(); // now safely deletes the empty directory
        }
    }
    
    /**
     * Generates a payslip PDF using live data and verifies the file is created.
     */
    @Test
    public void testExportToFile_generatesPayslipPdfSuccessfully() throws Exception {
        int employeeID = 10002;
        int payPeriodID = 1;  
        
        // Generate JasperPrint from actual report service
        PayslipService payslipService = new PayslipService(DatabaseService.connectToMotorPH());
        Map<String, Object> params = ReportParameterBuilder.buildPayslipParams(employeeID, payPeriodID);
        JasperPrint print = payslipService.generateReport(params);
        
        // Build file name and ensure path
        String fileName = ReportFileUtils.buildFileName(
            ReportConstants.FILENAME_PREFIX_PAYSLIP,
            ReportConstants.PDF_EXTENSION,
            String.valueOf(employeeID),
            String.valueOf(payPeriodID));
        
        File outputFile = new File(exportDir, fileName);
        outputFile.getParentFile().mkdirs(); // ensure path exists

        // Act
        new PdfReportExporter().exportToFile(print, outputFile.getPath());

        // Assert
        assertTrue("PDF file should be created", outputFile.exists());
        assertTrue("PDF file should not be empty", outputFile.length() > 0);
    }

    /**
     * Generates a bi-weekly payroll summary PDF using live data and verifies the file is created.
     */
    @Test
    public void testExportToFile_generatesPayrollBiWeeklySummaryPdfSuccessfully() throws Exception {
        int payPeriodID = 1; 

        // Generate JasperPrint from actual report service
        PayrollBiWeeklySummaryReportService service = new PayrollBiWeeklySummaryReportService(DatabaseService.connectToMotorPH());
        Map<String, Object> params = ReportParameterBuilder.buildBiWeeklySummaryParams(payPeriodID);
        JasperPrint print = service.generateReport(params);
        
        // Build file name and ensure path
        String fileName = ReportFileUtils.buildFileName(
            ReportConstants.FILENAME_PREFIX_BI_WEEKLY,
            ReportConstants.PDF_EXTENSION,
            String.valueOf(payPeriodID)
        );
        File outputFile = new File(exportDir, fileName);
        outputFile.getParentFile().mkdirs(); // ensure path exists

        // Act
        new PdfReportExporter().exportToFile(print, outputFile.getPath());

        // Assert
        assertTrue("PDF file should be created", outputFile.exists());
        assertTrue("PDF file should not be empty", outputFile.length() > 0);
    }
}
