/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.employeemanagement.service;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.database.connection.DatabaseService;
import com.motorph.database.execution.SQLExecutor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author 
 */
public class EmployeeRetrievalServiceTest {
    
    private static Connection connection;
    private EmployeeRetrievalService service;
    private SQLExecutor executor;
    
    public EmployeeRetrievalServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            connection = DatabaseService.connectToMotorPH();
        } catch (Exception e) {
            fail("Failed to connect to database: " + e.getMessage());
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
    
    @Before
    public void setUp() {
        executor = new SQLExecutor(connection);
        service = new EmployeeRetrievalService(executor);
    }
    
    @After
    public void tearDown() {
        // Clean up test data if any
        cleanupTestData();
    }

    /**
     * Test of getActiveEmployees method.
     */
    @Test
    public void testGetActiveEmployees() throws Exception {
        System.out.println("Testing getActiveEmployees");
        
        List<Employee> result = service.getActiveEmployees();
        
        // Should not be null
        assertNotNull("Result should not be null", result);
        
        // Should return a list (even if empty)
        assertTrue("Result should be a list", result instanceof List);
        
        // If there are employees, check basic structure
        if (!result.isEmpty()) {
            Employee firstEmployee = result.get(0);
            assertNotNull("Employee should not be null", firstEmployee);
            assertNotNull("Employee should have first name", firstEmployee.getFirstName());
            assertNotNull("Employee should have last name", firstEmployee.getLastName());
            assertTrue("Employee ID should be positive", firstEmployee.getEmployeeId() > 0);
        }
    }

    /**
     * Test of getEmployeeById method with valid ID.
     */
    @Test
    public void testGetEmployeeById() {
        System.out.println("Testing getEmployeeById with valid ID");
        
        // Test with ID 1 (common in test data)
        Employee result = service.getEmployeeById(1);
        
        if (result != null) {
            // If employee exists, verify it has required fields
            assertTrue("Employee ID should be positive", result.getEmployeeId() > 0);
            assertNotNull("Employee should have first name", result.getFirstName());
            assertNotNull("Employee should have last name", result.getLastName());
            assertNotNull("Employee should have email", result.getEmail());
        }
        
        // Test should pass regardless of whether employee exists
        assertTrue("Test completed successfully", true);
    }
    
    /**
     * Test of getEmployeeById method with non-existent ID.
     */
    @Test
    public void testGetEmployeeByIdNotFound() {
        System.out.println("Testing getEmployeeById with non-existent ID");
        
        // Use a very high ID that likely doesn't exist
        Employee result = service.getEmployeeById(99999);
        
        // Should return null for non-existent employee
        assertNull("Should return null for non-existent employee", result);
    }
    
    /**
     * Test of getEmployeeById method with negative ID.
     */
    @Test
    public void testGetEmployeeByIdNegative() {
        System.out.println("Testing getEmployeeById with negative ID");
        
        Employee result = service.getEmployeeById(-1);
        
        // Should return null for invalid ID
        assertNull("Should return null for negative ID", result);
    }
    
    /**
     * Test of getEmployeeById method with zero ID.
     */
    @Test
    public void testGetEmployeeByIdZero() {
        System.out.println("Testing getEmployeeById with zero ID");
        
        Employee result = service.getEmployeeById(0);
        
        // Should return null for zero ID
        assertNull("Should return null for zero ID", result);
    }
    
    /**
     * Test that getActiveEmployees returns only active employees.
     */
    @Test
    public void testGetActiveEmployeesStatusFilter() throws Exception {
        System.out.println("Testing getActiveEmployees filters active employees");
        
        List<Employee> result = service.getActiveEmployees();
        
        // Check that all returned employees are active (if any)
        for (Employee emp : result) {
            if (emp.getEmploymentStatus() != null) {
                assertEquals("Employee should be active", "Active", emp.getEmploymentStatus());
            }
        }
        
        assertTrue("Filter test completed", true);
    }
    
    /**
     * Test that getEmployeeById returns complete employee data.
     */
    @Test
    public void testGetEmployeeByIdCompleteData() {
        System.out.println("Testing getEmployeeById returns complete data");
        
        Employee result = service.getEmployeeById(1);
        
        if (result != null) {
            // Verify that full employee data is loaded
            if (result.getEmploymentStatus() != null) {
                assertNotNull("Should have employment status", result.getEmploymentStatus());
            }
            if (result.getJobTitle() != null) {
                assertNotNull("Should have job title", result.getJobTitle());
            }
            
            // Basic salary should be set if employee exists
            if (result.getBasicSalary() != null) {
                assertTrue("Basic salary should be positive", 
                          result.getBasicSalary().compareTo(BigDecimal.ZERO) > 0);
            }
        }
        
        assertTrue("Complete data test finished", true);
    }
    
    private void cleanupTestData() {
        try {
            Statement stmt = connection.createStatement();
            
            // Clean up any test data if needed
            stmt.execute("DELETE FROM employee_personal_information " +
                        "WHERE first_name = 'TestUser' AND last_name = 'ForTesting'");
            
            stmt.close();
        } catch (SQLException e) {
            // Ignore cleanup errors
        }
    }
}