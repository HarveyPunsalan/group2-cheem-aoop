/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link ReportParameterBuilder}.
 *
 * This test suite validates:
 * <ul>
 *   <li>Parameter map construction for payslip and summary reports</li>
 *   <li>Custom parameter merging and timestamp behavior</li>
 *   <li>Input validation and exception handling</li>
 * </ul>
 */
public class ReportParameterBuilderTest {    

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");    

    // ---------- buildPayslipParams ----------

    /**
     * Verifies that payslip parameters contain all expected keys and correct values.
     */    
    @Test
    public void testBuildPayslipParams_containsExpectedKeys() {
        Map<String, Object> params = ReportParameterBuilder.buildPayslipParams(101, 2024);

        assertEquals(3, params.size());
        assertEquals(101, params.get("EMPLOYEE_ID"));
        assertEquals(2024, params.get("PAY_PERIOD_ID"));
        assertNotNull(params.get("generatedOn"));

        //Validate timestamp format
        assertTrue(params.get("generatedOn") instanceof String);
        assertEquals(19, ((String) params.get("generatedOn")).length());
        
        try {
            LocalDateTime.parse((String) params.get("generatedOn"), TIMESTAMP_FORMAT);
        } catch (DateTimeParseException e) {
            fail("generatedOn timestamp is not in valid format.");
        }
    }

    /**
     * Expects an exception when IDs are negative.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildPayslipParams_withNegativeIds_throwsException() {
        ReportParameterBuilder.buildPayslipParams(-1, -100);
    }

    // ---------- buildMonthlySummaryParams ----------

    /**
     * Verifies correct keys and timestamp format for monthly summary parameters.
     */    
    @Test
    public void testBuildMonthlySummaryParams_containsExpectedKeys() {
        String input = "2024-06";
        Map<String, Object> params = ReportParameterBuilder.buildMonthlySummaryParams(input);

        assertEquals(2, params.size());
        assertEquals(input, params.get("YEAR_MONTH"));
        assertNotNull(params.get("generatedOn"));        
        
        assertEquals(19, ((String) params.get("generatedOn")).length());
        
        try {
            LocalDateTime.parse((String) params.get("generatedOn"), TIMESTAMP_FORMAT);
        } catch (DateTimeParseException e) {
            fail("generatedOn timestamp is not in valid format.");
        }
    }    

    /**
     * Expects an exception when input is null.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildMonthlySummaryParams_withNullInput_throwsException() {
        ReportParameterBuilder.buildMonthlySummaryParams(null);
    }
    
    /**
     * Expects an exception when input is blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBuildMonthlySummaryParams_withBlankInput_throwsException() {
        ReportParameterBuilder.buildMonthlySummaryParams("  ");
    }

    /**
     * Expects an exception when format is invalid.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildMonthlySummaryParams_withInvalidFormat_throwsException() {
        ReportParameterBuilder.buildMonthlySummaryParams("06-2025");
    }
    
    @Test
    public void testBuildBiWeeklySummaryParams_containsExpectedKeys() {
        Map<String, Object> params = ReportParameterBuilder.buildBiWeeklySummaryParams(5);
        assertEquals(2, params.size());
        assertEquals(5, params.get("PAY_PERIOD_ID"));
        assertTrue(params.containsKey("GeneratedOn"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildBiWeeklySummaryParams_withNegativeID_throwsException() {
        ReportParameterBuilder.buildBiWeeklySummaryParams(-1);
    }

    // ---------- buildCustomParams ----------

    /**
     * Ensures custom parameters are preserved and timestamp is added.
     */ 
    @Test
    public void testBuildCustomParams_mergesCustomAndAddsGeneratedOn() {
        Map<String, Object> input = new HashMap<>();
        input.put("DEPARTMENT", "Finance");
        input.put("MONTH", "2025-05");

        Map<String, Object> result = ReportParameterBuilder.buildCustomParams(input);

        assertEquals(3, result.size()); // 2 custom + 1 generatedOn
        assertEquals("Finance", result.get("DEPARTMENT"));
        assertEquals("2025-05", result.get("MONTH"));
        assertTrue(result.containsKey("generatedOn"));
    }

    /**
     * Ensures "generatedOn" value is preserved if already supplied.
     */    
    @Test
    public void testBuildCustomParams_preservesGeneratedOnIfPresent() {
        String manualTimestamp = "2025-06-01 10:00:00";
        Map<String, Object> input = new HashMap<>();
        input.put("generatedOn", manualTimestamp);

        Map<String, Object> result = ReportParameterBuilder.buildCustomParams(input);

        assertEquals(manualTimestamp, result.get("generatedOn")); // Should not be overwritten
    }

    /**
     * Expects an exception if the input map is null.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildCustomParams_withNullMap_throwsException() {
        ReportParameterBuilder.buildCustomParams(null);
    }
}
