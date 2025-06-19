/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Class.EMS;

import com.motorph.database.execution.SQLExecutor;
import java.sql.Connection;
import java.io.IOException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Unit test class for EmployeeRetrievalService.
 * 
 * These tests verify the functionality of retrieving employee records
 * from the database, including fetching all active employees and 
 * retrieving specific employee details by ID.
 */
public class EmployeeRetrievalServiceTest {

    private static Connection conn;
    private static SQLExecutor executor;
    private static EmployeeRetrievalService service;

    /**
     * Sets up database connection and initializes service once before all tests.
     */
    @BeforeClass
    public static void setUpClass() throws SQLException {
        conn = (Connection) DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/payrollsystem_db",
            "root", "admin"
        );
        executor = new SQLExecutor((java.sql.Connection) conn);
        service = new EmployeeRetrievalService(executor);
    }

    /**
     * Closes the database connection after all tests have run.
     */
    @AfterClass
    public static void tearDownClass() throws SQLException, IOException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    /**
     * Test retrieving all active employees.
     * Ensures the result is non-null and safely handles 0 or more employees.
     */
    @Test
    public void testGetActiveEmployees() throws Exception {
        List<Employee> employees = service.getActiveEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() >= 0); 
    }

    //Test fetching a known employee record by ID.
    @Test
    public void testGetEmployeeById() throws Exception {
        int id = 10001;
        Employee employee = service.getEmployeeById(id);

        assertNotNull("Employee should be found", employee);

        // Personal Info
        assertEquals("Manuel III", employee.getFirstName());
        assertEquals("Garcia", employee.getLastName());
        assertEquals(java.sql.Date.valueOf("1983-10-11"), new java.sql.Date(employee.getBirthday().getTime()));
        assertEquals("09171234567", employee.getPhoneNumber());
        assertEquals("mgarcia@motorph.com", employee.getEmail());

        // Address
        assertNull(employee.getHouseNumber()); // "nul"
        assertEquals("Valero Carpark Building Valero Street", employee.getStreet());
        assertNull(employee.getBarangay()); // "nul"
        assertEquals("Makati", employee.getMunicipality());
        assertNull(employee.getProvince()); // "nul"
        assertEquals("1227", employee.getPostalCode());
        assertEquals("Philippines", employee.getCountry());
        assertEquals("1", employee.getAddressType());

        // Government Info
        assertEquals("4445060573", employee.getSssNumber());
        assertEquals("820126853951", employee.getPhilhealthNumber());
        assertEquals("691295330870", employee.getPagibigNumber());
        assertEquals("442605657000", employee.getTaxIdentificationNumber());

        // Employment Info
        assertEquals("Regular", employee.getEmploymentType());
        assertEquals("Active", employee.getEmploymentStatus());
        assertEquals(java.sql.Date.valueOf("2000-10-08"), new java.sql.Date(employee.getDateHired().getTime()));
        assertEquals("Chief Executive Officer", employee.getJobTitle());
        assertEquals("Executive", employee.getDepartment());
        assertEquals(0, employee.getSupervisorId());

        // Salary
        assertEquals(5, employee.getSalaryGrade());
        assertEquals(new BigDecimal("90000.00"), employee.getBasicSalary());
        assertEquals(new BigDecimal("45000.00"), employee.getGrossSemiMonthlyRate());
        assertEquals(new BigDecimal("535.71"), employee.getHourlyRate());

        // Allowances
        assertEquals(new BigDecimal("1500.00"), employee.getRiceSubsidy());
        assertEquals(new BigDecimal("2000.00"), employee.getPhoneAllowance());
        assertEquals(new BigDecimal("1000.00"), employee.getClothingAllowance());
    }
    
}
