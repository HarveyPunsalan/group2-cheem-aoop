/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package EMS;

import com.motorph.EMS.Service.EmployeeCreationService;
import com.motorph.EMS.Service.EmployeeDeletionService;
import com.motorph.EMS.Service.EmployeeRetrievalService;
import com.motorph.EMS.Model.Employee;
import com.motorph.database.execution.SQLExecutor;
import java.sql.Connection;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Unit test class for EmployeeDeletionService.
 * 
 * This test verifies that an employee can be created, deleted, and properly removed from the database.
 */
public class EmployeeDeletionServiceTest {

    private static Connection conn;
    private static EmployeeCreationService creationService;
    private static EmployeeRetrievalService retrievalService;
    private static EmployeeDeletionService deletionService;
    private static SQLExecutor executor;

    /**
     * Initializes database connection and required services once before all tests.
     */
    @BeforeClass
    public static void setUpClass() throws SQLException {
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/payrollsystem_db",
            "root", "admin"
        );
        executor = new SQLExecutor(conn);
        creationService = new EmployeeCreationService(conn);
        retrievalService = new EmployeeRetrievalService(executor);
        deletionService = new EmployeeDeletionService(conn);
    }

    /**
     * Closes the database connection after all tests are done.
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    /**
     * Tests the entire employee deletion flow:
     * 1. Create a new employee named "Danilo".
     * 2. Retrieve the generated employee ID.
     * 3. Delete the employee.
     * 4. Verify the employee record is no longer retrievable.
     */
    @Test
    public void testDeleteEmployee() throws Exception {
        // Create a test employee
        Employee testEmp = deleteTestEmployee();
        creationService.addEmployee(testEmp);

        // Retrieve employee_id for "Danilo"
        int employeeId = -1;
        String sql = "SELECT employee_id FROM employee_personal_information WHERE first_name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "Danilo");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    employeeId = rs.getInt("employee_id");
                }
            }
        }

        assertTrue("Employee ID not found for Danilo", employeeId > 0);

        // Delete the employee using the service
        deletionService.deleteEmployee(employeeId);

        // Confirm employee no longer exists
        Employee deleted = retrievalService.getEmployeeById(employeeId);
        assertNull("Employee was not deleted from the database", deleted);
    }

    /**
     * Helper method to construct a test employee named "Danilo".
     * Adds a timestamp suffix to the email to ensure uniqueness.
     *
     * @return a new valid Employee object.
     */
    private Employee deleteTestEmployee() {
        long suffix = System.currentTimeMillis();
        Employee e = new Employee();
        e.setFirstName("Danilo");
        e.setLastName("Employee");
        e.setBirthday(new Date(90, 1, 1));
        e.setPhoneNumber("09999999999");
        e.setEmail("delete" + suffix + "@motorph.com");
        e.setHouseNumber("1");
        e.setStreet("Test Street");
        e.setBarangay("Test");
        e.setMunicipality("Makati");
        e.setProvince("Metro Manila");
        e.setPostalCode("1234");
        e.setCountry("Philippines");
        e.setAddressType("1");

        e.setSssNumber("9078764556");
        e.setPhilhealthNumber("285345086634");
        e.setPagibigNumber("678214775445");
        e.setTaxIdentificationNumber("078214775445");

        e.setEmploymentType("Regular");
        e.setEmploymentStatus("Active");
        e.setJobTitle("HR Manager");
        e.setDepartment("Human Resources");
        e.setDateHired(new Date());
        e.setSupervisorId(10001); // existing valid supervisor

        e.setSalaryGrade(1);
        e.setBasicSalary(new BigDecimal("10000"));
        e.setGrossSemiMonthlyRate(new BigDecimal("5000"));
        e.setHourlyRate(new BigDecimal("100"));
        e.setRiceSubsidy(new BigDecimal("500"));
        e.setPhoneAllowance(new BigDecimal("300"));
        e.setClothingAllowance(new BigDecimal("200"));

        return e;
    }
}