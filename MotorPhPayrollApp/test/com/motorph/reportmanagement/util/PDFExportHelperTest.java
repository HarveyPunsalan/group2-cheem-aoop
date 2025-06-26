/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.util;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 63909
 */
public class PDFExportHelperTest {

    private static final String TEST_PDF_PATH = "test-output/sample-test.pdf";

    @Before
    public void setUp() {
        new File(TEST_PDF_PATH).delete(); // Ensure clean start
        new File("test-output/").mkdirs(); // Make sure directory exists
    }

    @After
    public void tearDown() {
        new File(TEST_PDF_PATH).delete();
    }

    @Test
    public void testCreateSimplePdf_createsFile() {
        PDFExportHelper.createSimplePdf(TEST_PDF_PATH);
        File pdf = new File(TEST_PDF_PATH);
        assertTrue(pdf.exists());
        assertTrue(pdf.length() > 0);
    }

    @Test
    public void testCreateSimplePdf_overwritesExistingFile() {
        // Create dummy file first
        try {
            File file = new File(TEST_PDF_PATH);
            if (file.createNewFile()) {
                assertTrue(file.exists());
            }
        } catch (IOException e) {
            fail("Failed to create initial dummy PDF");
        }

        PDFExportHelper.createSimplePdf(TEST_PDF_PATH);
        File pdf = new File(TEST_PDF_PATH);
        assertTrue(pdf.exists());
        assertTrue(pdf.length() > 0);
    }
}
