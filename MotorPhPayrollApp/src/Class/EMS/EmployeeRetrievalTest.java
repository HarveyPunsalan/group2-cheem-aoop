/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * This test class is used to verify and demonstrate the functionality of
 * EmployeeRetrievalService. It performs the following:
 * 1. Connects to the payroll database
 * 2. Retrieves a list of all active employees (basic info)
 * 3. Retrieves full information of a specific employee by ID
 * 4. Displays all retrieved data in a readable format
 */

import com.motorph.database.execution.SQLExecutor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class EmployeeRetrievalTest {

    public static void main(String[] args) {
        try {
            // Step 1: Connect to DB
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/payrollsystem_db",
                "root",
                "admin"
            );

            // Step 2: Initialize executor and service
            SQLExecutor executor = new SQLExecutor(conn);
            EmployeeRetrievalService service = new EmployeeRetrievalService(executor);

            // Step 3: Retrieve and display active employees
            System.out.println("=== ACTIVE EMPLOYEES ===");
            List<Employee> activeEmployees = service.getActiveEmployees();
            for (Employee emp : activeEmployees) {
                System.out.printf("ID: %d | Name: %s %s |  Birthday: %s | Phone: %s | Email: %s\n",
                    emp.getEmployeeId(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getBirthday(),
                    emp.getPhoneNumber(),
                    emp.getEmail()
                );
            }

            // Step 4: Retrieve and display full details of a specific employee
            int testEmployeeId = 10053; // Replace with an actual employee_id that exists in DB
            System.out.println("\n=== FULL DETAILS FOR EMPLOYEE ID: " + testEmployeeId + " ===");
            Employee full = service.getEmployeeById(testEmployeeId);
            if (full != null) {
                printFullEmployee(full);
            } else {
                System.out.println("Employee not found.");
            }

            // Step 5: Close the connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Helper method to print all details of a single employee in formatted form.
     */
    private static void printFullEmployee(Employee emp) {
        System.out.println("Employee No: " + emp.getEmployeeId());
        System.out.println("Name: " + emp.getFirstName() + " " + emp.getLastName());
        System.out.println("Birthday: " + emp.getBirthday());
        System.out.println("Phone: " + emp.getPhoneNumber());
        System.out.println("Email: " + emp.getEmail());

        System.out.println("\n--- Address ---");
        System.out.printf("%s %s, %s, %s, %s, %s (%s)\n",
            emp.getHouseNumber(), emp.getStreet(), emp.getBarangay(),
            emp.getMunicipality(), emp.getProvince(), emp.getPostalCode(),
            emp.getCountry());

        System.out.println("Address Type: " + emp.getAddressType());

        System.out.println("\n--- Government Info ---");
        System.out.println("SSS: " + emp.getSssNumber());
        System.out.println("Philhealth: " + emp.getPhilhealthNumber());
        System.out.println("Pag-IBIG: " + emp.getPagibigNumber());
        System.out.println("TIN: " + emp.getTaxIdentificationNumber());

        System.out.println("\n--- Employment Info ---");
        System.out.println("Job Title: " + emp.getJobTitle());
        System.out.println("Department: " + emp.getDepartment());
        System.out.println("Employment Type: " + emp.getEmploymentType());
        System.out.println("Status: " + emp.getEmploymentStatus());
        System.out.println("Date Hired: " + emp.getDateHired());
        System.out.println("Supervisor ID: " + emp.getSupervisorId());

        System.out.println("\n--- Salary ---");
        System.out.println("Salary Grade: " + emp.getSalaryGrade());
        System.out.println("Basic: " + emp.getBasicSalary());
        System.out.println("Gross Semi-Monthly: " + emp.getGrossSemiMonthlyRate());
        System.out.println("Hourly Rate: " + emp.getHourlyRate());

        System.out.println("\n--- Allowances ---");
        System.out.println("Rice Subsidy: " + emp.getRiceSubsidy());
        System.out.println("Phone Allowance: " + emp.getPhoneAllowance());
        System.out.println("Clothing Allowance: " + emp.getClothingAllowance());
    }
}