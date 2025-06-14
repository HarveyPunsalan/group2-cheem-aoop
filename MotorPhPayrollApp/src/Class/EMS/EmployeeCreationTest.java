/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * This is a test class used to simulate and verify the full creation process of a new employee.
 * It connects to the database, populates an Employee object with mock data, and invokes the
 * EmployeeCreationService to persist all related data.
 *
 * This test can be used to validate:
 * - Employee information insertion
 * - Job, department, and salary association
 * - Allowance and supervisor assignment
 */

import com.motorph.database.execution.SQLExecutor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeCreationTest {

    public static void main(String[] args) {
        try {
            // 1. Establish a connection to the database
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/payrollsystem_db",
                "root",
                "admin"
            );

            // 2. Optionally use SQLExecutor for custom queries (if needed)
            SQLExecutor executor = new SQLExecutor(conn);

            // 3. Instantiate the service class
            EmployeeCreationService service = new EmployeeCreationService(conn);

            // 4. Create a test employee with mock data
            Employee employee = new Employee();
            employee.setFirstName("Chammy");
            employee.setLastName("Dela Cruz");
            employee.setBirthday(new Date(90, 0, 1)); 
            employee.setPhoneNumber("09173434341");
            employee.setEmail("cdelacruz@motorph.com");
            employee.setHouseNumber("123");
            employee.setStreet("Main St");
            employee.setBarangay("Barangay Uno");
            employee.setMunicipality("Quezon City");
            employee.setProvince("Metro Manila");
            employee.setPostalCode("1100");
            employee.setCountry("Philippines");
            employee.setAddressType("1"); 

            employee.setSssNumber("4090909095");
            employee.setPhilhealthNumber("411292233323");
            employee.setPagibigNumber("173456789912");
            employee.setTaxIdentificationNumber("079599910341");

            employee.setEmploymentType("Regular");
            employee.setEmploymentStatus("Active");
            employee.setJobTitle("HR Manager");
            employee.setSupervisorId(1); // supervisor's employee_id
            employee.setDepartment("Human Resources");
            employee.setDateHired(new Date()); // today

            employee.setSalaryGrade(5);
            employee.setBasicSalary(new BigDecimal("50000"));
            employee.setGrossSemiMonthlyRate(new BigDecimal("25000"));
            employee.setHourlyRate(new BigDecimal("300"));

            employee.setRiceSubsidy(new BigDecimal("1500"));
            employee.setPhoneAllowance(new BigDecimal("800"));
            employee.setClothingAllowance(new BigDecimal("1000"));
            employee.setSupervisorId(10001);

            // 5. Call the service method to add the employee to the database
            service.addEmployee(employee);

            System.out.println("Employee added successfully!");

            // 6. Close the database connection
            conn.close();

        } catch (SQLException e) {
        }
    }
}