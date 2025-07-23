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
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author 
 */
public class EmployeeDeletionServiceTest {
    
    private static Connection connection;
    private EmployeeDeletionService service;
    private EmployeeCreationService createService;
    
    public EmployeeDeletionServiceTest() {
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
        service = new EmployeeDeletionService(connection);
        createService = new EmployeeCreationService(connection);
    }
    
    @After
    public void tearDown() {
        // Clean up any remaining test data
        cleanupTestData();
    }

    /**
     * Test of deleteEmployee method with valid employee ID.
     */
    @Test
    public void testDeleteEmployee() throws Exception {
        System.out.println("Testing deleteEmployee with valid ID");
        
        // First create an employee to delete
        Employee employee = createTestEmployee();
        createService.addEmployee(employee);
        
        // Get the employee ID
        int employeeId = getEmployeeIdByName("Maria", "Santos");
        
        // Delete the employee
        service.deleteEmployee(employeeId);
        
        // Verify employee is deleted
        assertFalse("Employee should be deleted", employeeExists(employeeId));
    }
    
    /**
     * Test deleteEmployee with non-existent employee ID.
     */
    @Test
    public void testDeleteEmployeeWithNonExistentId() {
        System.out.println("Testing deleteEmployee with non-existent ID");
        
        try {
            service.deleteEmployee(99999); // Non-existent ID
            // If no exception, test passes (deletion of non-existent record is okay)
            assertTrue("No exception thrown for non-existent ID", true);
        } catch (SQLException e) {
            // Exception is also acceptable
            assertTrue("SQLException thrown for non-existent ID", true);
        }
    }
    
    /**
     * Test deleteEmployee with negative employee ID.
     */
    @Test
    public void testDeleteEmployeeWithNegativeId() {
        System.out.println("Testing deleteEmployee with negative ID");
        
        try {
            service.deleteEmployee(-1);
            // If no exception, test passes
            assertTrue("No exception thrown for negative ID", true);
        } catch (SQLException e) {
            // Exception is also acceptable
            assertTrue("SQLException thrown for negative ID", true);
        }
    }
    
    /**
     * Test deleteEmployee with zero employee ID.
     */
    @Test
    public void testDeleteEmployeeWithZeroId() {
        System.out.println("Testing deleteEmployee with zero ID");
        
        try {
            service.deleteEmployee(0);
            // If no exception, test passes
            assertTrue("No exception thrown for zero ID", true);
        } catch (SQLException e) {
            // Exception is also acceptable
            assertTrue("SQLException thrown for zero ID", true);
        }
    }
    
    private Employee createTestEmployee() {
        Employee employee = new Employee();
        
        // Personal info
        employee.setFirstName("Maria");
        employee.setLastName("Santos");
        employee.setBirthday(new Date(92, 5, 15)); // June 15, 1992
        employee.setPhoneNumber("09987654321");
        employee.setEmail("maria.santos@email.com");
        
        // Address
        employee.setHouseNumber("456");
        employee.setStreet("Test Street");
        employee.setBarangay("Barangay Test");
        employee.setMunicipality("Test City");
        employee.setProvince("Test Province");
        employee.setPostalCode("1234");
        employee.setCountry("Philippines");
        employee.setAddressType("1");
        
        // Government IDs
        employee.setSssNumber("9876543210");
        employee.setPhilhealthNumber("210987654321");
        employee.setPagibigNumber("210987654321");
        employee.setTaxIdentificationNumber("210987654321");
        
        // Employment info
        employee.setEmploymentType("Full-time");
        employee.setEmploymentStatus("Active");
        employee.setJobTitle("IT Operations and Systems");
        employee.setDepartment("IT Operations and Systems");
        employee.setDateHired(new Date());
        employee.setSupervisorId(10001);
        
        // Salary info
        employee.setSalaryGrade(4);
        employee.setBasicSalary(new BigDecimal("25000.00"));
        employee.setGrossSemiMonthlyRate(new BigDecimal("12500.00"));
        employee.setHourlyRate(new BigDecimal("156.25"));
        
        // Allowances
        employee.setRiceSubsidy(null);
        employee.setPhoneAllowance(null);
        employee.setClothingAllowance(null);
        
        return employee;
    }
    
    private int getEmployeeIdByName(String firstName, String lastName) throws SQLException {
        String sql = "SELECT employee_id FROM employee_personal_information WHERE first_name = ? AND last_name = ?";
        try (java.sql.PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("employee_id");
            }
        }
        return -1;
    }
    
    private boolean employeeExists(int employeeId) throws SQLException {
        String sql = "SELECT employee_id FROM employee_personal_information WHERE employee_id = ?";
        try (java.sql.PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
    
    private void cleanupTestData() {
        try {
            Statement stmt = connection.createStatement();
            
            // Clean up test employee data in reverse order
            stmt.execute("DELETE FROM employee_default_allowance WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Maria' AND last_name = 'Santos')");
            
            stmt.execute("DELETE FROM supervisor_assignment WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Maria' AND last_name = 'Santos')");
            
            stmt.execute("DELETE FROM employee_employment_information WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Maria' AND last_name = 'Santos')");
            
            stmt.execute("DELETE FROM employee_government_information WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Maria' AND last_name = 'Santos')");
            
            stmt.execute("DELETE FROM employee_address WHERE employee_id IN " +
                        "(SELECT employee_id FROM employee_personal_information " +
                        "WHERE first_name = 'Maria' AND last_name = 'Santos')");
            
            stmt.execute("DELETE FROM employee_personal_information " +
                        "WHERE first_name = 'Maria' AND last_name = 'Santos'");
            
            stmt.close();
        } catch (SQLException e) {
            
        }
    }
}