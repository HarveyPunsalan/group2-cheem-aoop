/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.controller;

import com.motorph.reportmanagement.config.ReportConstants;
import com.motorph.reportmanagement.util.ReportFileUtils;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration test for {@link PayrollBiWeeklySummaryController}.
 * <p>Generates actual PDF reports and validates export output.</p>
 */
public class PayrollBiWeeklySummaryControllerTest {

    private final String exportDir = "test-output/";
    private PayrollBiWeeklySummaryController controller;
    
    private final int validPayPeriodID = 1;
    private final int invalidPayPeriodID = -999;

    @Before
    public void setUp() {
        ReportFileUtils.setExportDirectory(exportDir);
        new File(exportDir).mkdirs();
        
        controller = new PayrollBiWeeklySummaryController();
        controller.setPreviewEnabled(false); // Disable GUI preview during tests
    }

    @After
    public void tearDown() {
        ReportFileUtils.resetExportDirectory();
        File dir = new File(exportDir);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        if (!dir.delete()) {
            System.out.println("Warning: Unable to delete test-output/ directory.");
        }
    }
    
    /**
     * Utility method to get expected exported file based on payPeriodID.
     */
    private File getExportedFileFor(int payPeriodID) {
        String fileName = ReportFileUtils.buildFileName(
            ReportConstants.FILENAME_PREFIX_BI_WEEKLY,
            ReportConstants.PDF_EXTENSION,
            String.valueOf(payPeriodID)
        );
        return new File(exportDir, fileName);
    }

    /**
     * Verifies that a valid bi-weekly report is generated and exported as a PDF file.
     */
    @Test
    public void testGeneratePayrollBiWeeklySummaryPDF_createsPdfAndReturnsTrue() {
        // Act
        boolean result = controller.generatePayrollBiWeeklySummaryPDF(validPayPeriodID);

        // Assert
        assertTrue("Should return true for valid pay period", result);
        
        File outputFile = getExportedFileFor(validPayPeriodID);

        assertTrue("PDF file should be created", outputFile.exists());
        assertTrue("PDF file should not be empty", outputFile.length() > 0);
    }

    /**
     * Verifies that an invalid pay period ID does not generate a report.
     */
    @Test
    public void testGeneratePayrollBiWeeklySummaryPDF_withInvalidPayPeriodID_returnsFalse() {
        // Act
        boolean result = controller.generatePayrollBiWeeklySummaryPDF(invalidPayPeriodID);

        // Assert
        assertFalse("Should return false for invalid pay period ID", result);

        File outputFile = getExportedFileFor(invalidPayPeriodID);
        assertFalse("PDF file should not be created for invalid input", outputFile.exists());
    }
}
