/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Class.EMS;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.employeemanagement.service.EmployeeRetrievalService;
import com.motorph.employeemanagement.service.EmployeeUpdateService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import com.motorph.database.execution.SQLExecutor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;

/**
 * Unit test class for EmployeeUpdateService.
 *
 * This class verifies:
 *  - That valid updates are correctly persisted to the database.
 *  - That duplicate constraints (on email, SSS, TIN, etc.) are enforced.
 *  - That invalid foreign key references (e.g., job title, department, supervisor) are rejected.
 */
public class EmployeeUpdateServiceTest {

    private static Connection conn;
    private static SQLExecutor executor;
    private static EmployeeUpdateService updateService;
    private static EmployeeRetrievalService retrievalService;

    /**
     * Establishes DB connection and initializes services once before all tests run.
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payrollsystem_db", "root", "admin");
        executor = new SQLExecutor(conn);
        updateService = new EmployeeUpdateService(conn);
        retrievalService = new EmployeeRetrievalService(executor);
    }

    /**
     * Closes the DB connection after all tests complete.
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    /**
     * Retrieves a known test employee from the database.
     * This employee (ID: 10208) must exist in the test data.
     */
    private Employee getTestEmployee() throws SQLException {
        Employee emp = retrievalService.getEmployeeById(10208);
        assertNotNull("Employee with ID 10208 not found.", emp);
        return emp;
    }

    /**
     * Test a full update on an employee record and verify all fields are persisted correctly.
     */
    @Test
    public void testSuccessfulUpdate() throws Exception {
        Employee emp = getTestEmployee();
        
        // Modify all fields
        emp.setFirstName("UpdatedFirst");
        emp.setLastName("UpdatedLast");
        emp.setBirthday(new Date(85, 5, 15)); // June 15, 1985
        emp.setPhoneNumber("09170044205");
        emp.setEmail("updated_email@motorph.com");

        emp.setHouseNumber("123");
        emp.setStreet("Updated Street");
        emp.setBarangay("Updated Barangay");
        emp.setMunicipality("Updated City");
        emp.setProvince("Updated Province");
        emp.setPostalCode("4321");
        emp.setCountry("Updated Country");
        emp.setAddressType("2");

        emp.setSssNumber("9999999999");
        emp.setPhilhealthNumber("888888888888");
        emp.setPagibigNumber("777777777777");
        emp.setTaxIdentificationNumber("666666666000");

        emp.setEmploymentType("Probationary");
        emp.setEmploymentStatus("Inactive");
        emp.setJobTitle("Payroll Manager");
        emp.setSupervisorId(10002); 
        emp.setDepartment("Payroll");
        emp.setDateHired(new Date(122, 0, 1)); // Jan 1, 2022

        emp.setSalaryGrade(9);
        emp.setBasicSalary(new BigDecimal("15000.00"));
        emp.setRiceSubsidy(new BigDecimal("1000.00"));
        emp.setPhoneAllowance(new BigDecimal("150.00"));
        emp.setClothingAllowance(new BigDecimal("300.00"));
        emp.setGrossSemiMonthlyRate(new BigDecimal("7500.00"));
        emp.setHourlyRate(new BigDecimal("120.00"));

        // Execute update
        updateService.updateEmployee(emp);

        // Verify the updated data
        Employee updated = retrievalService.getEmployeeById(emp.getEmployeeId());

        // Assertions
        assertEquals("UpdatedFirst", updated.getFirstName());
        assertEquals("UpdatedLast", updated.getLastName());
        assertEquals(Date.valueOf("1985-06-15"), updated.getBirthday());
        assertEquals("09170044205", updated.getPhoneNumber());
        assertEquals("updated_email@motorph.com", updated.getEmail());

        assertEquals("123", updated.getHouseNumber());
        assertEquals("Updated Street", updated.getStreet());
        assertEquals("Updated Barangay", updated.getBarangay());
        assertEquals("Updated City", updated.getMunicipality());
        assertEquals("Updated Province", updated.getProvince());
        assertEquals("4321", updated.getPostalCode());
        assertEquals("Updated Country", updated.getCountry());
        assertEquals("2", updated.getAddressType());

        assertEquals("9999999999", updated.getSssNumber());
        assertEquals("888888888888", updated.getPhilhealthNumber());
        assertEquals("777777777777", updated.getPagibigNumber());
        assertEquals("666666666000", updated.getTaxIdentificationNumber());

        assertEquals("Probationary", updated.getEmploymentType());
        assertEquals("Inactive", updated.getEmploymentStatus());
        assertEquals("Payroll Manager", updated.getJobTitle());
        assertEquals(10002, updated.getSupervisorId());
        assertEquals("Payroll", updated.getDepartment());
        assertEquals(Date.valueOf("2022-01-01"), updated.getDateHired());

        assertEquals(9, updated.getSalaryGrade());
        assertEquals(new BigDecimal("15000.00"), updated.getBasicSalary());
        assertEquals(new BigDecimal("1000.00"), updated.getRiceSubsidy());
        assertEquals(new BigDecimal("150.00"), updated.getPhoneAllowance());
        assertEquals(new BigDecimal("300.00"), updated.getClothingAllowance());
        assertEquals(new BigDecimal("7500.00"), updated.getGrossSemiMonthlyRate());
        assertEquals(new BigDecimal("120.00"), updated.getHourlyRate());
    }

    //DUPLICATE CHECKS to check validation Unique key constraints
    @Test(expected = SQLException.class)
    public void testDuplicateEmail() throws Exception {
        Employee emp = getTestEmployee();
        emp.setEmail("mgarcia@motorph.com"); // Existing email
        updateService.updateEmployee(emp);
    }

    @Test(expected = SQLException.class)
    public void testDuplicateSSSNumber() throws Exception {
        Employee emp = getTestEmployee();
        emp.setSssNumber("4445060573"); // Existing SSS
        updateService.updateEmployee(emp);
    }

    @Test(expected = SQLException.class)
    public void testDuplicatePhilhealthNumber() throws Exception {
        Employee emp = getTestEmployee();
        emp.setPhilhealthNumber("820126853951"); // Existing PhilHealth
        updateService.updateEmployee(emp);
    }

    @Test(expected = SQLException.class)
    public void testDuplicatePagibigNumber() throws Exception {
        Employee emp = getTestEmployee();
        emp.setPagibigNumber("691295330870"); // Existing Pag-IBIG
        updateService.updateEmployee(emp);
    }

    @Test(expected = SQLException.class)
    public void testDuplicateTaxIdentificationNumber() throws Exception {
        Employee emp = getTestEmployee();
        emp.setTaxIdentificationNumber("442605657000"); // Existing TIN
        updateService.updateEmployee(emp);
    }

    //INVALID FOREIGN KEYS to check that only values defined in db is accepted
    @Test(expected = SQLException.class)
    public void testInvalidDepartment() throws Exception {
        Employee emp = getTestEmployee();
        emp.setDepartment("INVALID_DEPT"); // Not in department table
        updateService.updateEmployee(emp);
    }

    @Test(expected = SQLException.class)
    public void testInvalidJobTitle() throws Exception {
        Employee emp = getTestEmployee();
        emp.setJobTitle("INVALID_JOB"); // Not in job_titles table
        updateService.updateEmployee(emp);
    }

    @Test(expected = SQLException.class)
    public void testInvalidSupervisorId() throws Exception {
        Employee emp = getTestEmployee();
        emp.setSupervisorId(999999); // Non-existent employee ID
        updateService.updateEmployee(emp);
    }
}