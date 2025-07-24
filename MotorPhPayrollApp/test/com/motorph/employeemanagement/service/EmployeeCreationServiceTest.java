/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.employeemanagement.service;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.database.connection.DatabaseService;
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
import java.util.Date;

/**
 *
 * @author 
 */
public class EmployeeCreationServiceTest {
    
    private static Connection connection;
    private EmployeeCreationService service;
    
    public EmployeeCreationServiceTest() {
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
        service = new EmployeeCreationService(connection);
    }
    
    @After
    public void tearDown() {
        // Clean up test data
        cleanupTestData();
    }

    /**
     * Test of addEmployee method with valid employee data.
     */
    @Test
    public void testAddEmployee() throws Exception {
        System.out.println("Testing addEmployee with valid data");
        
        Employee employee = createTestEmployee();
        
        // This should not throw an exception
        service.addEmployee(employee);
        
        // If we get here, the test passed
        assertTrue("Employee was added successfully", true);
    }
    
    /**
     * Test addEmployee with null employee object.
     */
    @Test
    public void testAddEmployeeWithNullEmployee() {
        System.out.println("Testing addEmployee with null employee");
        
        try {
            service.addEmployee(null);
            fail("Expected SQLException for null employee");
        } catch (SQLException e) {
            // This is expected
            assertTrue("SQLException thrown for null employee", true);
        } catch (Exception e) {
            // Any other exception is also acceptable
            assertTrue("Exception thrown for null employee", true);
        }
    }
    
    /**
     * Test addEmployee with missing required fields.
     */
    @Test
    public void testAddEmployeeWithMissingData() {
        System.out.println("Testing addEmployee with missing required data");
        
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setBirthday(new Date()); // Add birthday to avoid null pointer
        employee.setPhoneNumber("09123456789");
        employee.setEmail("john.doe@email.com");
        // Missing other required fields like address, government IDs, etc.
        
        try {
            service.addEmployee(employee);
            fail("Expected SQLException for incomplete employee data");
        } catch (SQLException e) {
            // This is expected
            assertTrue("SQLException thrown for incomplete data", true);
        } catch (Exception e) {
            // Any other exception is also acceptable for incomplete data
            assertTrue("Exception thrown for incomplete data", true);
        }
    }
    
    /**
     * Test addEmployee with invalid job title.
     */
    @Test
    public void testAddEmployeeWithInvalidJobTitle() {
        System.out.println("Testing addEmployee with invalid job title");
        
        Employee employee = createTestEmployee();
        employee.setJobTitle("Non-existent Job");
        employee.setDepartment("Non-existent Department");
        
        try {
            service.addEmployee(employee);
            fail("Expected SQLException for invalid job title");
        } catch (SQLException e) {
            // This is expected
            assertTrue("SQLException thrown for invalid job title", true);
        }
    }
    
    private Employee createTestEmployee() {
        Employee employee = new Employee();
        
        // Personal info
        employee.setFirstName("Juan");
        employee.setLastName("Dela Cruz");
        employee.setBirthday(new Date(90, 0, 1)); // Jan 1, 1990
        employee.setPhoneNumber("09123456789");
        employee.setEmail("juan.delacruz" + System.currentTimeMillis() + "@email.com");
        
        // Address
        employee.setHouseNumber("123");
        employee.setStreet("Main Street");
        employee.setBarangay("Barangay 1");
        employee.setMunicipality("Quezon City");
        employee.setProvince("Metro Manila");
        employee.setPostalCode("1100");
        employee.setCountry("Philippines");
        employee.setAddressType("1");
        
        // Government IDs - Try different SSS formats
        employee.setSssNumber("1234567890"); // Try 10 digits first
        employee.setPhilhealthNumber("123456789012"); // 12 digits
        employee.setPagibigNumber("123456789012"); // 12 digits
        employee.setTaxIdentificationNumber("123456789012"); // 12 digits
        
        // Employment info
        employee.setEmploymentType("Full-time");
        employee.setEmploymentStatus("Active");
        employee.setJobTitle("IT Operations and Systems");
        employee.setDepartment("IT Operations and Systems");
        employee.setDateHired(new Date());
        employee.setSupervisorId(10001);
        
        // Salary info
        employee.setSalaryGrade(5);
        employee.setBasicSalary(new BigDecimal("30000.00"));
        employee.setGrossSemiMonthlyRate(new BigDecimal("15000.00"));
        employee.setHourlyRate(new BigDecimal("187.50"));
        
        // Allowances info
        employee.setRiceSubsidy(null);
        employee.setPhoneAllowance(null);
        employee.setClothingAllowance(null);
        
        return employee;
    }
    
    private void cleanupTestData() {
        try {
            Statement stmt = connection.createStatement();
            
            stmt.execute("DELETE FROM supervisor_assignment WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Juan' AND last_name = 'Dela Cruz')");
            
            stmt.execute("DELETE FROM employee_employment_information WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Juan' AND last_name = 'Dela Cruz')");
            
            stmt.execute("DELETE FROM employee_government_information WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Juan' AND last_name = 'Dela Cruz')");
            
            stmt.execute("DELETE FROM employee_address WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Juan' AND last_name = 'Dela Cruz')");
            
            stmt.execute("DELETE FROM employee_personal_information " +
                        "WHERE first_name = 'Juan' AND last_name = 'Dela Cruz'");
            
            stmt.close();
        } catch (SQLException e) {
        }
    }
}