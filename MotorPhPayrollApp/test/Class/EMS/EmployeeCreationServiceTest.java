/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Class.EMS;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test class for EmployeeCreationService.
 * 
 * It tests the ability to add new employee records to the database.
 * The tests require an active database connection to a test instance of payrollsystem_db.
 */
public class EmployeeCreationServiceTest {

    private static Connection conn;
    private EmployeeCreationService service;

    /**
     * Establishes a single database connection before any tests run.
     * Uses local MySQL instance with test database.
     * @throws SQLException if connection fails.
     */
    @BeforeClass
    public static void setUpClass() throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/payrollsystem_db",
                "root",
                "admin"
        );
    }

    /**
     * Closes the database connection after all tests complete.
     * @throws SQLException if closing connection fails.
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    /**
     * Initializes a fresh instance of EmployeeCreationService before each test.
     */
    @Before
    public void setUp() {
        service = new EmployeeCreationService(conn);
    }

    /**
     * Utility method to create a valid Employee object.
     * Ensures uniqueness in key fields by appending a timestamp suffix.
     * 
     * @return a new Employee instance populated with valid and unique test data.
     */
    private Employee buildValidEmployee() {
        long uniqueSuffix = System.currentTimeMillis();

        Employee e = new Employee();
        e.setFirstName("Francine");
        e.setLastName("Dela Cruz");
        e.setBirthday(new Date(95, 11, 12));
        e.setPhoneNumber("09170044203");
        e.setEmail("fdelacruz" + uniqueSuffix + "@motorph.com");

        e.setHouseNumber("193");
        e.setStreet("Sampaguita St");
        e.setBarangay("Barangay Pembo");
        e.setMunicipality("Makati City");
        e.setProvince("Metro Manila");
        e.setPostalCode("5600");
        e.setCountry("Philippines");
        e.setAddressType("1");

        // Unique government IDs by appending uniqueSuffix 
        e.setSssNumber("3445060" + (000 + uniqueSuffix % 999));       // SSS Number
        e.setPhilhealthNumber("8208498" + (53951 + uniqueSuffix % 9999)); // Philhealth
        e.setPagibigNumber("6912955" + (78870 + uniqueSuffix % 9999));    // Pagibig
        e.setTaxIdentificationNumber("4426000" + (57000 + uniqueSuffix % 9999)); // TIN

        e.setEmploymentType("Regular");
        e.setEmploymentStatus("Active");
        e.setJobTitle("HR Manager");
        e.setDepartment("Human Resources");
        e.setDateHired(new Date());
        e.setSupervisorId(10001); // ensure this employee ID exists

        e.setSalaryGrade(5);
        e.setBasicSalary(new BigDecimal("50000"));
        e.setGrossSemiMonthlyRate(new BigDecimal("25000"));
        e.setHourlyRate(new BigDecimal("300"));

        e.setRiceSubsidy(new BigDecimal("1500"));
        e.setPhoneAllowance(new BigDecimal("800"));
        e.setClothingAllowance(new BigDecimal("1000"));
        return e;
    }

    /**
     * Tests adding a valid employee to the database.
     * The test will fail if an exception is thrown during the add operation.
     * @throws Exception if adding the employee fails.
     */
    @Test
    public void testAddEmployee_Success() throws Exception {
        Employee e = buildValidEmployee();
        try {
            service.addEmployee(e);
        } catch (SQLException ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
}