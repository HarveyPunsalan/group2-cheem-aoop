/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.databasemanagement.connection.DatabaseService;
import com.motorph.reportmanagement.util.ReportParameterBuilder;
import net.sf.jasperreports.engine.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link PayrollBiWeeklySummaryReportService}.These tests verify:
 <ul>
   <li>Report generation with valid parameters</li>
   <li>Proper handling of null and empty parameter maps</li>
 </ul>
 *
 */
public class PayrollBiWeeklySummaryReportServiceTest {

    private PayrollBiWeeklySummaryReportService service;

    @Before
    public void setUp() {
        // Uses a real database connection; requires the DB to be running with expected data.
        service = new PayrollBiWeeklySummaryReportService(DatabaseService.connectToMotorPH());
    }    
       
    /**
     * Verifies that report generation returns a non-null, non-empty JasperPrint object.
     * This test assumes that data for the specified YEAR_MONTH ("2024-01") exists in the database.
     */   
    @Test
    public void testGenerateReport_validParams_returnsJasperPrint() throws Exception {
        Map<String, Object> params = ReportParameterBuilder.buildBiWeeklySummaryParams(1);
        
        JasperPrint print = service.generateReport(params);

        assertNotNull("JasperPrint should not be null", print);
        assertFalse("Report should have pages", print.getPages().isEmpty());

        System.out.println("Generated " + print.getPages().size() + " page(s).");
    }

    /**
     * Expects IllegalArgumentException when parameters are null.
     */    
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateReport_nullParams_throwsException() throws Exception {
        service.generateReport(null);
    }

    /**
     * Expects RuntimeException when parameters are valid but yield no matching data in the database.
     * This simulates a scenario where the YEAR_MONTH is syntactically correct but unused.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateReport_emptyParams_throwsException() throws Exception {
        service.generateReport(new HashMap<>());
    }
    
    @Test(expected = RuntimeException.class)
    public void testGenerateReport_noMatchingData_throwsException() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("PAY_PERIOD_ID", 27);   // Invalid

        service.generateReport(params);
    }

//    @Test
//    public void testMockGenerateReport_validParams_returnsJasperPrint() throws Exception {
//        // Arrange
//        Map<String, Object> params = Collections.singletonMap("YEAR_MONTH", "2024-01");
//
//        JasperReport mockReport = mock(JasperReport.class);
//        JasperPrint mockPrint = mock(JasperPrint.class);
//        Connection mockConnection = mock(Connection.class);
//
//        // Use try-with-resources for mocking static methods
//        try (
//            MockedStatic<JasperCompileManager> compileManager = Mockito.mockStatic(JasperCompileManager.class);
//            MockedStatic<JasperFillManager> fillManager = Mockito.mockStatic(JasperFillManager.class);
//            MockedStatic<DatabaseService> dbService = Mockito.mockStatic(DatabaseService.class)
//        ) {
//            compileManager.when(() -> JasperCompileManager.compileReport(anyString())).thenReturn(mockReport);
//            fillManager.when(() -> JasperFillManager.fillReport(eq(mockReport), eq(params), any(Connection.class))).thenReturn(mockPrint);
//            when(mockPrint.getPages()).thenReturn(Collections.singletonList(mock(JRPrintPage.class)));
//            dbService.when(DatabaseService::connectToMotorPH).thenReturn(mockConnection);
//
//            // Act
//            JasperPrint result = service.generateReport(params);
//
//            // Assert
//            assertNotNull(result);
//        }
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void testMockGenerateReport_noPages_throwsException() throws Exception {
//        Map<String, Object> params = Collections.singletonMap("YEAR_MONTH", "2024-01");
//
//        JasperReport mockReport = mock(JasperReport.class);
//        JasperPrint emptyPrint = mock(JasperPrint.class);
//        Connection mockConnection = mock(Connection.class);
//
//        try (
//            MockedStatic<JasperCompileManager> compileManager = Mockito.mockStatic(JasperCompileManager.class);
//            MockedStatic<JasperFillManager> fillManager = Mockito.mockStatic(JasperFillManager.class);
//            MockedStatic<DatabaseService> dbService = Mockito.mockStatic(DatabaseService.class)
//        ) {
//            compileManager.when(() -> JasperCompileManager.compileReport(anyString())).thenReturn(mockReport);
//            fillManager.when(() -> JasperFillManager.fillReport(eq(mockReport), eq(params), any(Connection.class))).thenReturn(emptyPrint);
//            when(emptyPrint.getPages()).thenReturn(Collections.emptyList());
//            dbService.when(DatabaseService::connectToMotorPH).thenReturn(mockConnection);
//
//            // Act (should throw)
//            service.generateReport(params);
//        }
//    }
}
