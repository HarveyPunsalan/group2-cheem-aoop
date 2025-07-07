/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Unit tests for {@link ReportFileUtils}, verifying file naming, directory management, and safe deletion.
 */
public class ReportFileUtilsTest {

    private final String testDirPath = "test-output/";
    private final String testFileName = "test-file.txt";
    private final String fullPath = testDirPath + testFileName;

    @Before
    public void setUp() {
        ReportFileUtils.setExportDirectory(testDirPath);
        new File(testDirPath).mkdirs();
    }

    @After
    public void tearDown() {
        ReportFileUtils.resetExportDirectory();
        new File(fullPath).delete(); // test file cleanup
        new File(testDirPath).delete(); // test directory cleanup
    }

    // ---------- buildFileName Tests ----------

    /**
     * Tests filename generation with base, extension, and extra parts.
     */    
    @Test
    public void testBuildFileName_withAllParts() {
        String fileName = ReportFileUtils.buildFileName("Payslip", "pdf", "EID101", "PID123");
        assertTrue(fileName.startsWith("Payslip_"));
        assertTrue(fileName.contains("_EID101"));
        assertTrue(fileName.endsWith("PID123.pdf"));
    }
    
    /**
     * Tests filename generation with only base and extension.
     */    
    @Test
    public void testBuildFileName_withOnlyBaseAndExtension() {
        String fileName = ReportFileUtils.buildFileName("Summary", "csv");
        assertTrue(fileName.startsWith("Summary_"));
        assertTrue(fileName.endsWith(".csv"));
        assertFalse(fileName.contains("__")); // no empty parts
    }
    
    /**
     * Tests filename generation with empty/null parts is ignored properly.
     */   
    @Test
    public void testBuildFileName_withEmptyParts_shouldIgnore() {
        String fileName = ReportFileUtils.buildFileName("Report", "pdf", "", " ", null);
        assertTrue(fileName.startsWith("Report_"));
        assertFalse(fileName.contains("__"));
        assertTrue(fileName.endsWith(".pdf"));
    }
     
    /**
     * Throws exception if extension is null.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildFileName_nullExtension_throwsException() {
        ReportFileUtils.buildFileName("Report", null, "EID999");
    }
    
    /**
     * Throws exception if extension is blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBuildFileName_blankExtension_throwsException() {
        ReportFileUtils.buildFileName("Report", "", "EID999");
    }
    
    /**
     * Throws exception if base name is blank.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildFileName_blankBaseName_throws() {
        ReportFileUtils.buildFileName("   ", "pdf");
    }    

    // ---------- generateTimestampedFilename Tests ----------

    /**
     * Verifies basic format of generated timestamped filename.
     */    
    @Test
    public void testGenerateTimestampedFilename_format() {
        String fileName = ReportFileUtils.generateTimestampedFilename("Report", "csv");
        assertTrue(fileName.startsWith("Report_"));
        assertTrue(fileName.endsWith(".csv"));
    }

    // ---------- getExportPath Tests ----------

    /**
     * Confirms the full export path is correctly combined.
     */    
    @Test
    public void testGetExportPath_combinesDirectoryAndFilename() {
        String fileName = "example.pdf";
        String expectedPath = "test-output/" + fileName;
        String actualPath = ReportFileUtils.getExportPath(fileName);
        assertEquals(expectedPath, actualPath);
    }

    // ---------- ensureExportDirectoryExists Tests ----------

    /**
     * Ensures directory is created if it does not exist.
     */    
    @Test
    public void testEnsureExportDirectoryExists_createsDirectoryIfMissing() {
        File tempDir = new File("temp-export/");
        if (tempDir.exists()) tempDir.delete();
        assertFalse(tempDir.exists());

        // Override export dir temporarily
        ReportFileUtils.setExportDirectory("temp-export/");
        ReportFileUtils.ensureExportDirectoryExists();

        assertTrue(tempDir.exists());
        tempDir.delete(); // clean up
        ReportFileUtils.resetExportDirectory();
    }

    // ---------- deleteFileIfExists Tests ----------

    /**
     * Tests deletion of an existing file.
     */    
    @Test
    public void testDeleteFileIfExists_existingFile_returnsTrue() throws IOException {
        File file = new File(fullPath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("test");
        }
        assertTrue(file.exists());
        
        boolean result  = ReportFileUtils.deleteFileIfExists(fullPath);
        assertTrue(result );
        assertFalse(file.exists());
    }

    /**
     * Tests behavior when attempting to delete a non-existent file.
     */
    @Test
    public void testDeleteFileIfExists_missingFile_returnsFalse() {
        File file = new File(fullPath);
        file.delete();  // make sure it doesn’t exist

        boolean result = ReportFileUtils.deleteFileIfExists(fullPath);
        assertFalse(result);
    }
}
