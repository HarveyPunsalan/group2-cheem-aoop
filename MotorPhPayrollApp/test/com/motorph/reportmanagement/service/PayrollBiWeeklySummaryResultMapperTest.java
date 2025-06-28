/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.database.connection.DatabaseService;
import com.motorph.reportmanagement.model.PayrollBiWeeklySummaryReport;
import java.sql.*;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration test for {@link PayrollBiWeeklySummaryResultMapper}.
 *
 * ⚠️ This test assumes:
 * - The stored procedure `generate_bi_weekly_payroll_summary('2024-01')` exists
 * - It returns a valid ResultSet with at least one row
 */
public class PayrollBiWeeklySummaryResultMapperTest {
    
    /**
     * Tests that mapAll correctly transforms a real ResultSet into a list of summary report DTOs.
     *
     * @throws Exception if any SQL or mapping error occurs
     */
    @Test
    public void testMapAll_withRealResultSet_returnsListOfReports() throws Exception {
        int testPayPeriod = 1;
        
        try (
            Connection conn = DatabaseService.connectToMotorPH();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("CALL generate_bi_weekly_payroll_summary('%d')", testPayPeriod))
        ) {

            List<PayrollBiWeeklySummaryReport> reports = PayrollBiWeeklySummaryResultMapper.mapAll(rs);

            assertNotNull("Report list should not be null", reports);
            assertFalse("Report list should not be empty", reports.isEmpty());

            PayrollBiWeeklySummaryReport first = reports.get(0);
            assertNotNull(first.getEmployeeID());
            assertNotNull(first.getEmployeeName());
            assertTrue(first.getGrossIncome() >= 0);
            assertTrue(first.getNetPay() >= 0);

            System.out.println("Mapped " + reports.size() + " payroll summary entries.");
        }
    }
}
