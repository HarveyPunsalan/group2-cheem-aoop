/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.database.connection.DatabaseService;
import com.motorph.reportmanagement.model.Payslip;
import java.sql.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration test for {@link PayslipResultMapper}.
 * 
 * ⚠️ This test requires:
 * - A running MotorPH database
 * - A valid row in `view_payslip_official` for employee_id = 10001
 * - A known expected value for payslip_id = "10000"
 */
public class PayslipResultMapperTest {
    
    /**
     * Tests that a real ResultSet row is correctly mapped into a Payslip object.
     */
    @Test
    public void testMap_withRealResultSet_returnsCorrectPayslip() throws Exception {
        try (
            Connection conn = DatabaseService.connectToMotorPH();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM view_payslip_official WHERE employee_id = 10001")
        ) {
            assertTrue("No record found for employee_id = 10001", resultSet.next());

            Payslip payslip = PayslipResultMapper.map(resultSet);

            assertNotNull("Mapped payslip should not be null", payslip);
            assertEquals("Expected payslip ID mismatch", "10494", payslip.getPayslipID());
            assertEquals("Expected employee ID mismatch", "10001", payslip.getEmployeeID());
            assertTrue("Net salary should be greater than 0", payslip.getNetSalary() > 0);

            // Optional: further confidence checks
            assertNotNull("Employee name should not be null", payslip.getEmployeeName());
            assertNotNull("Generated date should not be null", payslip.getGeneratedDate());
            assertNotNull("Pay period start should not be null", payslip.getPayPeriodStart());
        }
    }
}
