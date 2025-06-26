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
 *
 * @author 63909
 */
public class PayslipControllerTest {

    private final String exportDir = "test-output/";
    private PayslipController controller;

    private final int employeeID = 10002;
    private final int payslipID = 1;

    @Before
    public void setUp() {
        ReportFileUtils.setExportDirectory(exportDir);
        new File(exportDir).mkdirs();
        controller = new PayslipController();
        controller.setPreviewEnabled(false); // disable GUI
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
        dir.delete();
    }

    @Test
    public void testGeneratePayslip_createsPdfAndReturnsTrue() {
        // Act
        boolean result = controller.generatePayslip(employeeID, payslipID);

        // Assert result
        assertTrue("generatePayslip should return true", result);

        // Assert file created
        String fileName = ReportFileUtils.buildFileName(
            ReportConstants.FILENAME_PREFIX_PAYSLIP,
            ReportConstants.PDF_EXTENSION,
            String.valueOf(employeeID),
            String.valueOf(payslipID)
        );
        File outputFile = new File(exportDir, fileName);

        assertTrue("PDF file should be created", outputFile.exists());
        assertTrue("PDF file should not be empty", outputFile.length() > 0);
    }
    
    @Test
    public void testGeneratePayslip_withInvalidEmployeeID_returnsFalse() {
        // Act
        boolean result = controller.generatePayslip(-999, -999); // Simulate invalid input

        // Assert
        assertFalse("generatePayslip should return false for invalid data", result);

        // Verify no PDF was created
        String fileName = ReportFileUtils.buildFileName(
            ReportConstants.FILENAME_PREFIX_PAYSLIP,
            ReportConstants.PDF_EXTENSION,
            String.valueOf(-999),
            String.valueOf(-999)
        );
        File outputFile = new File(exportDir, fileName);

        System.out.println("Checking if file was created at: " + outputFile.getPath());
        assertFalse("PDF file should not be created for invalid input", outputFile.exists());
    }
}
