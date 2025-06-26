/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.databasemanagement.connection.DatabaseService;
import com.motorph.reportmanagement.util.ReportParameterBuilder;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration tests for {@link PayslipService}.
 * 
 * These tests verify:
 * <ul>
 *   <li>Successful generation of payslip report with valid parameters</li>
 *   <li>Proper exception handling for null or invalid input</li>
 *   <li>Behavior when no data is available for the given employee or pay period</li>
 * </ul>
 * 
 * ⚠️ Requires a live database and valid report template (JRXML or .jasper file).
 */
public class PayslipServiceTest {    

    private PayslipService service;

    @Before
    public void setUp() {
        // Use real DB connection (ensure test DB is available and seeded)
        service = new PayslipService(DatabaseService.connectToMotorPH());
    }

    
    /**
     * Validates that a payslip is generated with real employee and pay period.
     */
    @Test
    public void testGenerateReport_validParams_returnsJasperPrint() throws Exception {
        Map<String, Object> params = ReportParameterBuilder.buildPayslipParams(10001, 1);

        JasperPrint print = service.generateReport(params);

        assertNotNull("JasperPrint should not be null", print);
        assertFalse("Payslip should contain pages", print.getPages().isEmpty());

        System.out.println("Payslip generated with " + print.getPages().size() + " page(s).");
    }

    /**
     * Expects IllegalArgumentException for null parameter map.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateReport_nullParams_throwsException() throws Exception {
        service.generateReport(null);
    }

    /**
     * Expects IllegalArgumentException for empty parameter map.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateReport_emptyParams_throwsException() throws Exception {
        service.generateReport(new HashMap<>());
    }

    /**
     * Expects RuntimeException when no matching data exists in DB.
     */
    @Test(expected = RuntimeException.class)
    public void testGenerateReport_noMatchingData_throwsException() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("EMPLOYEE_ID", -1);   // Invalid
        params.put("PAY_PERIOD_ID", -1);    // Invalid

        service.generateReport(params);
    }
}
