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
 * Integration test suite for {@link PayslipController}.
 *
 * This class verifies that payslip reports are generated correctly under valid and invalid conditions.
 * It relies on a live database and the availability of the JasperReports engine.
 * 
 * Assumptions:
 * - The employee with ID 10001 exists in the database.
 * - The pay period ID 1 is valid and associated with that employee.
 * - The system is able to export PDF files to the file system.
 */
public class PayslipControllerTest {

    private final String exportDir = "test-output/";
    private PayslipController controller;

    private final int validEmployeeID = 10001;
    private final int validPayPeriodID = 1;
    
    private final int invalidEmployeeID = -999;
    private final int invalidPayslipID = -999;

    /**
     * Prepares the test environment:
     * - Creates a clean export directory
     * - Initializes the controller and disables GUI preview
     */
    @Before
    public void setUp() {
        ReportFileUtils.setExportDirectory(exportDir);
        new File(exportDir).mkdirs();
        
        controller = new PayslipController();
        controller.setPreviewEnabled(false); // disable GUI
    }

    /**
     * Cleans up the test environment:
     * - Resets export path to default
     * - Deletes generated PDF files and the export directory
     */
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
     * Helper method to build the expected output file path based on employee and pay period IDs.
     * 
     * @param employeeID the employee's ID
     * @param payPeriodID the pay period ID
     * @return the expected File object
     */
    private File getExportedFileFor(int employeeID, int payPeriodID) {
        String fileName = ReportFileUtils.buildFileName(
            ReportConstants.FILENAME_PREFIX_PAYSLIP,
            ReportConstants.PDF_EXTENSION,
            String.valueOf(employeeID),
            String.valueOf(payPeriodID)
        );
        return new File(exportDir, fileName);
    }

    /**
     * Tests the happy path where a valid employee and pay period ID should generate a PDF successfully.
     * Asserts that the file exists and is non-empty.
     */
    @Test
    public void testGeneratePayslip_createsPdfAndReturnsTrue() {
        // Act
        boolean result = controller.generatePayslip(validEmployeeID, validPayPeriodID);

        // Assert result
        assertTrue("generatePayslip should return true", result);

        File outputFile = getExportedFileFor(validEmployeeID, validPayPeriodID);
        
        assertTrue("PDF file should be created", outputFile.exists());
        assertTrue("PDF file should not be empty", outputFile.length() > 0);
    }
    
    /**
     * Tests the edge case where invalid IDs are used.
     * The method should return false and no file should be generated.
     */
    @Test
    public void testGeneratePayslip_withInvalidEmployeeID_returnsFalse() {
        // Act
        boolean result = controller.generatePayslip(invalidEmployeeID, invalidPayslipID); // Simulate invalid input

        // Assert
        assertFalse("generatePayslip should return false for invalid data", result);

        File outputFile = getExportedFileFor(invalidEmployeeID, invalidPayslipID);
        
        assertFalse("PDF file should not be created for invalid input", outputFile.exists());
    }
}
